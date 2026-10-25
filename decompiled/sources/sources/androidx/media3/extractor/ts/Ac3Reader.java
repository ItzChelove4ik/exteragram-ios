package androidx.media3.extractor.ts;

import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.Ac3Util;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import java.util.Objects;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* loaded from: classes4.dex */
public final class Ac3Reader implements ElementaryStreamReader {
    private int bytesRead;
    private final String containerMimeType;
    private Format format;
    private String formatId;
    private final ParsableBitArray headerScratchBits;
    private final ParsableByteArray headerScratchBytes;
    private final String language;
    private boolean lastByteWas0B;
    private TrackOutput output;
    private final int roleFlags;
    private long sampleDurationUs;
    private int sampleSize;
    private int state;
    private long timeUs;

    public Ac3Reader(String r3) {
        this(null, 0, r3);
    }

    public Ac3Reader(String r3, int r4, String r5) {
        ParsableBitArray r0 = new ParsableBitArray(new byte[128]);
        this.headerScratchBits = r0;
        this.headerScratchBytes = new ParsableByteArray(r0.data);
        this.state = 0;
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
        Ac3Util.SyncFrameInfo r0 = Ac3Util.parseAc3SyncframeInfo(this.headerScratchBits);
        Format r1 = this.format;
        if (r1 != null) goto L5;
    L10:
        Format.Builder r12 = new Format.Builder().setId(this.formatId).setContainerMimeType(this.containerMimeType).setSampleMimeType(r0.mimeType).setChannelCount(r0.channelCount).setSampleRate(r0.sampleRate).setLanguage(this.language).setRoleFlags(this.roleFlags).setPeakBitrate(r0.bitrate);
        if ("audio/ac3".equals(r0.mimeType) == false) goto L13;
        r12.setAverageBitrate(r0.bitrate);
    L13:
        Format r13 = r12.build();
        this.format = r13;
        this.output.format(r13);
    L14:
        this.sampleSize = r0.frameSize;
        this.sampleDurationUs = (r0.sampleCount * 1000000) / this.format.sampleRate;
        return;
    L5:
        if (r0.channelCount != r1.channelCount) goto L10;
        if (r0.sampleRate != r1.sampleRate) goto L10;
        if (Objects.equals(r0.mimeType, r1.sampleMimeType) == true) goto L14;
        goto L10
    }

    private boolean skipToNextSync(ParsableByteArray r6) {
    L2:
        boolean r1 = false;
        if (r6.bytesLeft() <= 0) goto L17;
        if (this.lastByteWas0B == false) goto L7;
        int r0 = r6.readUnsignedByte();
        if (r0 == 119) goto L12;
        if (r0 != 11) goto L16;
        r1 = true;
    L16:
        this.lastByteWas0B = r1;
        goto L2
    L12:
        this.lastByteWas0B = false;
        return true;
    L7:
        if (r6.readUnsignedByte() != 11) goto L9;
        r1 = true;
    L9:
        this.lastByteWas0B = r1;
        goto L2
    L17:
        return false;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray r12) {
        Assertions.checkStateNotNull(this.output);
    L4:
        if (r12.bytesLeft() <= 0) goto L23;
        int r0 = this.state;
        boolean r3 = true;
        if (r0 != 0) goto L7;
        if (skipToNextSync(r12) == false) goto L4;
        this.state = 1;
        this.headerScratchBytes.getData()[0] = 11;
        this.headerScratchBytes.getData()[1] = 119;
        this.bytesRead = 2;
        goto L4
    L7:
        if (r0 != 1) goto L8;
        if (continueRead(r12, this.headerScratchBytes.getData(), 128) == false) goto L4;
        parseHeader();
        this.headerScratchBytes.setPosition(0);
        this.output.sampleData(this.headerScratchBytes, 128);
        this.state = 2;
        goto L4
    L8:
        if (r0 != 2) goto L4;
        int r02 = Math.min(r12.bytesLeft(), this.sampleSize - this.bytesRead);
        this.output.sampleData(r12, r02);
        int r1 = this.bytesRead + r02;
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
        this.lastByteWas0B = false;
        this.timeUs = -9223372036854775807L;
    }
}
