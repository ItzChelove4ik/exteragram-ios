package androidx.media3.extractor.ts;

import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.MpegAudioUtil;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import kotlin.UByte;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class MpegAudioReader implements ElementaryStreamReader {
    private final String containerMimeType;
    private String formatId;
    private int frameBytesRead;
    private long frameDurationUs;
    private int frameSize;
    private boolean hasOutputFormat;
    private final MpegAudioUtil.Header header;
    private final ParsableByteArray headerScratch;
    private final String language;
    private boolean lastByteWasFF;
    private TrackOutput output;
    private final int roleFlags;
    private int state;
    private long timeUs;

    public MpegAudioReader(String r3) {
        this(null, 0, r3);
    }

    public MpegAudioReader(String r4, int r5, String r6) {
        this.state = 0;
        ParsableByteArray r1 = new ParsableByteArray(4);
        this.headerScratch = r1;
        r1.getData()[0] = -1;
        this.header = new MpegAudioUtil.Header();
        this.timeUs = -9223372036854775807L;
        this.language = r4;
        this.roleFlags = r5;
        this.containerMimeType = r6;
    }

    private void findHeader(ParsableByteArray r9) {
        byte[] r0 = r9.getData();
        int r1 = r9.getPosition();
        int r2 = r9.limit();
    L3:
        if (r1 >= r2) goto L19;
        byte r3 = r0[r1];
        if ((r3 & UByte.MAX_VALUE) != 255) goto L7;
        boolean r4 = true;
    L9:
        if (this.lastByteWasFF == true) goto L11;
    L13:
        boolean r32 = false;
    L14:
        this.lastByteWasFF = r4;
        if (r32 == true) goto L16;
        r1 = r1 + 1;
        goto L3
    L16:
        r9.setPosition(r1 + 1);
        this.lastByteWasFF = false;
        this.headerScratch.getData()[1] = r0[r1];
        this.frameBytesRead = 2;
        this.state = 1;
        return;
    L11:
        if ((r3 & 224) != 224) goto L13;
        r32 = true;
        goto L14
    L7:
        r4 = false;
        goto L9
    L19:
        r9.setPosition(r2);
    }

    @RequiresNonNull({"output"})
    private void readFrameRemainder(ParsableByteArray r9) {
        int r0 = Math.min(r9.bytesLeft(), this.frameSize - this.frameBytesRead);
        this.output.sampleData(r9, r0);
        int r92 = this.frameBytesRead + r0;
        this.frameBytesRead = r92;
        if (r92 >= this.frameSize) goto L6;
        return;
    L6:
        if (this.timeUs == (-9223372036854775807L)) goto L8;
        boolean r93 = true;
    L9:
        Assertions.checkState(r93);
        this.output.sampleMetadata(this.timeUs, 1, this.frameSize, 0, null);
        this.timeUs += this.frameDurationUs;
        this.frameBytesRead = 0;
        this.state = 0;
        return;
    L8:
        r93 = false;
        goto L9
    }

    @RequiresNonNull({"output"})
    private void readHeaderRemainder(ParsableByteArray r8) {
        int r0 = Math.min(r8.bytesLeft(), 4 - this.frameBytesRead);
        r8.readBytes(this.headerScratch.getData(), this.frameBytesRead, r0);
        int r82 = this.frameBytesRead + r0;
        this.frameBytesRead = r82;
        if (r82 >= 4) goto L5;
        return;
    L5:
        this.headerScratch.setPosition(0);
        if (this.header.setForHeaderData(this.headerScratch.readInt()) == true) goto L9;
        this.frameBytesRead = 0;
        this.state = 1;
        return;
    L9:
        this.frameSize = this.header.frameSize;
        if (this.hasOutputFormat == true) goto L12;
        this.frameDurationUs = (r8.samplesPerFrame * 1000000) / r8.sampleRate;
        this.output.format(new Format.Builder().setId(this.formatId).setContainerMimeType(this.containerMimeType).setSampleMimeType(this.header.mimeType).setMaxInputSize(4096).setChannelCount(this.header.channels).setSampleRate(this.header.sampleRate).setLanguage(this.language).setRoleFlags(this.roleFlags).build());
        this.hasOutputFormat = true;
    L12:
        this.headerScratch.setPosition(0);
        this.output.sampleData(this.headerScratch, 4);
        this.state = 2;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray r3) {
        Assertions.checkStateNotNull(this.output);
    L4:
        if (r3.bytesLeft() <= 0) goto L16;
        int r0 = this.state;
        if (r0 != 0) goto L8;
        findHeader(r3);
        goto L4
    L8:
        if (r0 != 1) goto L10;
        readHeaderRemainder(r3);
        goto L4
    L10:
        if (r0 != 2) goto L12;
        readFrameRemainder(r3);
        goto L4
    L12:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return;
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
        this.frameBytesRead = 0;
        this.lastByteWasFF = false;
        this.timeUs = -9223372036854775807L;
    }
}
