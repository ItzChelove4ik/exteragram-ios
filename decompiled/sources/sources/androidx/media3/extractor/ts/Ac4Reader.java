package androidx.media3.extractor.ts;

import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.Ac4Util;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* loaded from: classes4.dex */
public final class Ac4Reader implements ElementaryStreamReader {
    private int bytesRead;
    private final String containerMimeType;
    private Format format;
    private String formatId;
    private boolean hasCRC;
    private final ParsableBitArray headerScratchBits;
    private final ParsableByteArray headerScratchBytes;
    private final String language;
    private boolean lastByteWasAC;
    private TrackOutput output;
    private final int roleFlags;
    private long sampleDurationUs;
    private int sampleSize;
    private int state;
    private long timeUs;

    public Ac4Reader(String r3) {
        this(null, 0, r3);
    }

    public Ac4Reader(String r3, int r4, String r5) {
        ParsableBitArray r0 = new ParsableBitArray(new byte[16]);
        this.headerScratchBits = r0;
        this.headerScratchBytes = new ParsableByteArray(r0.data);
        this.state = 0;
        this.bytesRead = 0;
        this.lastByteWasAC = false;
        this.hasCRC = false;
        this.timeUs = -9223372036854775807L;
        this.language = r3;
        this.roleFlags = r4;
        this.containerMimeType = r5;
    }

    private boolean continueRead(ParsableByteArray r3, byte[] r4, int r5) {
        int r0 = Math.min(r3.bytesLeft(), r5 - this.bytesRead);
        r3.readBytes(r4, this.bytesRead, r0);
        int r32 = this.bytesRead + r0;
        this.bytesRead = r32;
        if (r32 != r5) goto L6;
        return true;
    L6:
        return false;
    }

    @RequiresNonNull({"output"})
    private void parseHeader() {
        this.headerScratchBits.setPosition(0);
        Ac4Util.SyncFrameInfo r0 = Ac4Util.parseAc4SyncframeInfo(this.headerScratchBits);
        Format r1 = this.format;
        if (r1 != null) goto L5;
    L10:
        Format r12 = new Format.Builder().setId(this.formatId).setContainerMimeType(this.containerMimeType).setSampleMimeType("audio/ac4").setChannelCount(r0.channelCount).setSampleRate(r0.sampleRate).setLanguage(this.language).setRoleFlags(this.roleFlags).build();
        this.format = r12;
        this.output.format(r12);
    L11:
        this.sampleSize = r0.frameSize;
        this.sampleDurationUs = (r0.sampleCount * 1000000) / this.format.sampleRate;
        return;
    L5:
        if (r0.channelCount != r1.channelCount) goto L10;
        if (r0.sampleRate != r1.sampleRate) goto L10;
        if ("audio/ac4".equals(r1.sampleMimeType) == true) goto L11;
        goto L10
    }

    private boolean skipToNextSync(ParsableByteArray r6) {
    L2:
        boolean r1 = false;
        if (r6.bytesLeft() <= 0) goto L21;
        if (this.lastByteWasAC == false) goto L7;
        int r0 = r6.readUnsignedByte();
        if (r0 != 172) goto L13;
        boolean r2 = true;
    L14:
        this.lastByteWasAC = r2;
        if (r0 == 64) goto L17;
        if (r0 != 65) goto L2;
    L17:
        if (r0 != 65) goto L19;
        r1 = true;
    L19:
        this.hasCRC = r1;
        return true;
    L13:
        r2 = false;
        goto L14
    L7:
        if (r6.readUnsignedByte() != 172) goto L9;
        r1 = true;
    L9:
        this.lastByteWasAC = r1;
        goto L2
    L21:
        return false;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray r12) {
        Assertions.checkStateNotNull(this.output);
    L4:
        if (r12.bytesLeft() <= 0) goto L27;
        int r0 = this.state;
        boolean r3 = true;
        if (r0 != 0) goto L7;
        if (skipToNextSync(r12) == false) goto L4;
        this.state = 1;
        this.headerScratchBytes.getData()[0] = -84;
        byte[] r02 = this.headerScratchBytes.getData();
        if (this.hasCRC == false) goto L25;
        int r2 = 65;
    L26:
        r02[1] = (byte) r2;
        this.bytesRead = 2;
        goto L4
    L25:
        r2 = 64;
        goto L26
    L7:
        if (r0 != 1) goto L8;
        if (continueRead(r12, this.headerScratchBytes.getData(), 16) == false) goto L4;
        parseHeader();
        this.headerScratchBytes.setPosition(0);
        this.output.sampleData(this.headerScratchBytes, 16);
        this.state = 2;
        goto L4
    L8:
        if (r0 != 2) goto L4;
        int r03 = Math.min(r12.bytesLeft(), this.sampleSize - this.bytesRead);
        this.output.sampleData(r12, r03);
        int r1 = this.bytesRead + r03;
        this.bytesRead = r1;
        if (r1 != this.sampleSize) goto L4;
        if (this.timeUs != (-9223372036854775807L)) goto L16;
        r3 = false;
    L16:
        Assertions.checkState(r3);
        this.output.sampleMetadata(this.timeUs, 1, this.sampleSize, 0, null);
        this.timeUs += this.sampleDurationUs;
        this.state = 0;
        goto L4
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput r2, TsPayloadReader.TrackIdGenerator r3) {
        r3.generateNewId();
        this.formatId = r3.getFormatId();
        this.output = r2.track(r3.getTrackId(), 1);
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void packetFinished(boolean r1) {
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void packetStarted(long r1, int r3) {
        this.timeUs = r1;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void seek() {
        this.state = 0;
        this.bytesRead = 0;
        this.lastByteWasAC = false;
        this.hasCRC = false;
        this.timeUs = -9223372036854775807L;
    }
}
