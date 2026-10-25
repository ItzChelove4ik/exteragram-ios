package androidx.media3.extractor.ts;

import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.ConstantBitrateSeekMap;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.ts.TsPayloadReader;
import java.io.EOFException;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.telegram.messenger.MediaController;

/* loaded from: classes4.dex */
public final class AdtsExtractor implements Extractor {
    public static final ExtractorsFactory FACTORY = null;
    private int averageFrameSize;
    private ExtractorOutput extractorOutput;
    private long firstFramePosition;
    private long firstSampleTimestampUs;
    private final int flags;
    private boolean hasCalculatedAverageFrameSize;
    private boolean hasOutputSeekMap;
    private final ParsableByteArray packetBuffer;
    private final AdtsReader reader;
    private final ParsableByteArray scratch;
    private final ParsableBitArray scratchBits;
    private boolean startedPacket;

    public static /* synthetic */ Extractor[] $r8$lambda$S5zTG1XqhzUfrGDXuCVov7iZe5M() {
        return new Extractor[]{new AdtsExtractor()};
    }

    static {
        FACTORY = new AdtsExtractor$$ExternalSyntheticLambda0();
    }

    public AdtsExtractor() {
        this(0);
    }

    public AdtsExtractor(int r3) {
        if ((r3 & 2) == 0) goto L5;
        r3 = r3 | 1;
    L5:
        this.flags = r3;
        this.reader = new AdtsReader(true, MediaController.AUDIO_MIME_TYPE);
        this.packetBuffer = new ParsableByteArray(2048);
        this.averageFrameSize = -1;
        this.firstFramePosition = -1;
        ParsableByteArray r32 = new ParsableByteArray(10);
        this.scratch = r32;
        this.scratchBits = new ParsableBitArray(r32.getData());
    }

    private void calculateAverageFrameSize(ExtractorInput r10) {
        if (this.hasCalculatedAverageFrameSize == false) goto L5;
        return;
    L5:
        this.averageFrameSize = -1;
        r10.resetPeekPosition();
        long r3 = 0;
        if (r10.getPosition() != 0) goto L8;
        peekId3Header(r10);
    L8:
        int r1 = 0;
        int r2 = 0;
    L36:
        if (r10.peekFully(this.scratch.getData(), 0, 2, true) == false) goto L28;
        this.scratch.setPosition(0);     // Catch: EOFException -> L35
        if (AdtsReader.isAdtsSyncWord(this.scratch.readUnsignedShort()) == false) goto L29;
        if (r10.peekFully(this.scratch.getData(), 0, 4, true) == false) goto L28;
        this.scratchBits.setPosition(14);     // Catch: EOFException -> L35
        int r6 = this.scratchBits.readBits(13);     // Catch: EOFException -> L35
        if (r6 <= 6) goto L26;
        r3 = r3 + r6;     // Catch: EOFException -> L35
        r2 = r2 + 1;     // Catch: EOFException -> L35
        if (r2 == 1000) goto L28;
        if (r10.advancePeekPosition(r6 - 6, true) == true) goto L36;
    L26:
        this.hasCalculatedAverageFrameSize = true;     // Catch: EOFException -> L35
        throw ParserException.createForMalformedContainer("Malformed ADTS stream", null);     // Catch: EOFException -> L35
    L29:
        r10.resetPeekPosition();
        if (r1 <= 0) goto L32;
        this.averageFrameSize = (int) (r3 / r1);
    L33:
        this.hasCalculatedAverageFrameSize = true;
        return;
    L32:
        this.averageFrameSize = -1;
    L28:
        r1 = r2;
        goto L29
    }

    private static int getBitrateFromFrameSize(int r4, long r5) {
        return (int) ((r4 * 8000000) / r5);
    }

    private SeekMap getConstantBitrateSeekMap(long r12, boolean r14) {
        int r8 = getBitrateFromFrameSize(this.averageFrameSize, this.reader.getSampleDurationUs());
        return new ConstantBitrateSeekMap(r12, this.firstFramePosition, r8, this.averageFrameSize, r14);
    }

    @RequiresNonNull({"extractorOutput"})
    private void maybeOutputSeekMap(long r8, boolean r10) {
        if (this.hasOutputSeekMap == true) goto L27;
        boolean r2 = false;
        if ((this.flags & 1) != 0) goto L8;
    L10:
        boolean r0 = false;
    L12:
        if (r0 == true) goto L14;
    L17:
        if (r0 == true) goto L19;
    L24:
        this.extractorOutput.seekMap(new SeekMap.Unseekable(-9223372036854775807L));
    L25:
        this.hasOutputSeekMap = true;
        return;
    L19:
        if (this.reader.getSampleDurationUs() == (-9223372036854775807L)) goto L24;
        ExtractorOutput r102 = this.extractorOutput;
        if ((this.flags & 2) == 0) goto L23;
        r2 = true;
    L23:
        r102.seekMap(getConstantBitrateSeekMap(r8, r2));
        goto L25
    L14:
        if (this.reader.getSampleDurationUs() != (-9223372036854775807L)) goto L17;
        if (r10 == true) goto L17;
        return;
    L8:
        if (this.averageFrameSize <= 0) goto L10;
        r0 = true;
        goto L12
    }

    private int peekId3Header(ExtractorInput r7) {
        int r1 = 0;
    L3:
        r7.peekFully(this.scratch.getData(), 0, 10);
        this.scratch.setPosition(0);
        if (this.scratch.readUnsignedInt24() != 4801587) goto L5;
        this.scratch.skipBytes(3);
        int r2 = this.scratch.readSynchSafeInt();
        r1 = r1 + (r2 + 10);
        r7.advancePeekPosition(r2);
        goto L3
    L5:
        r7.resetPeekPosition();
        r7.advancePeekPosition(r1);
        if (this.firstFramePosition != (-1)) goto L8;
        this.firstFramePosition = r1;
    L8:
        return r1;
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput r4) {
        this.extractorOutput = r4;
        this.reader.createTracks(r4, new TsPayloadReader.TrackIdGenerator(0, 1));
        r4.endTracks();
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput r7, PositionHolder r8) {
        Assertions.checkStateNotNull(this.extractorOutput);
        long r0 = r7.getLength();
        int r82 = this.flags;
        if ((r82 & 2) == 0) goto L5;
    L8:
        calculateAverageFrameSize(r7);
    L9:
        int r72 = r7.read(this.packetBuffer.getData(), 0, 2048);
        if (r72 != (-1)) goto L12;
        boolean r2 = true;
    L13:
        maybeOutputSeekMap(r0, r2);
        if (r2 == false) goto L16;
        return -1;
    L16:
        this.packetBuffer.setPosition(0);
        this.packetBuffer.setLimit(r72);
        if (this.startedPacket == true) goto L19;
        this.reader.packetStarted(this.firstSampleTimestampUs, 4);
        this.startedPacket = true;
    L19:
        this.reader.consume(this.packetBuffer);
        return 0;
    L12:
        r2 = false;
        goto L13
    L5:
        if ((r82 & 1) == 0) goto L9;
        if (r0 == (-1)) goto L9;
        goto L8
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long r1, long r3) {
        this.startedPacket = false;
        this.reader.seek();
        this.firstSampleTimestampUs = r3;
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput r9) {
        int r0 = peekId3Header(r9);
        int r3 = r0;
        int r2 = 0;
        int r4 = 0;
    L3:
        r9.peekFully(this.scratch.getData(), 0, 2);
        this.scratch.setPosition(0);
        if (AdtsReader.isAdtsSyncWord(this.scratch.readUnsignedShort()) == true) goto L7;
        r3 = r3 + 1;
        r9.resetPeekPosition();
        r9.advancePeekPosition(r3);
    L6:
        r2 = 0;
        r4 = 0;
    L17:
        if ((r3 - r0) < 8192) goto L3;
        return false;
    L7:
        r2 = r2 + 1;
        if (r2 >= 4) goto L10;
    L12:
        r9.peekFully(this.scratch.getData(), 0, 4);
        this.scratchBits.setPosition(14);
        int r5 = this.scratchBits.readBits(13);
        if (r5 > 6) goto L15;
        r3 = r3 + 1;
        r9.resetPeekPosition();
        r9.advancePeekPosition(r3);
        goto L6
    L15:
        r9.advancePeekPosition(r5 - 6);
        r4 = r4 + r5;
        goto L17
    L10:
        if (r4 <= 188) goto L12;
        return true;
    }
}
