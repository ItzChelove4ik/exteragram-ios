package androidx.media3.extractor.ts;

import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.DtsUtil;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import com.google.common.primitives.Ints;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class DtsReader implements ElementaryStreamReader {
    private int bytesRead;
    private final String containerMimeType;
    private int extensionSubstreamHeaderSize;
    private Format format;
    private String formatId;
    private int frameType;
    private final ParsableByteArray headerScratchBytes;
    private final String language;
    private TrackOutput output;
    private final int roleFlags;
    private long sampleDurationUs;
    private int sampleSize;
    private int state;
    private int syncBytes;
    private long timeUs;
    private final AtomicInteger uhdAudioChunkId;
    private int uhdHeaderSize;

    public DtsReader(String r3, int r4, int r5, String r6) {
        this.headerScratchBytes = new ParsableByteArray(new byte[r5]);
        this.state = 0;
        this.timeUs = -9223372036854775807L;
        this.uhdAudioChunkId = new AtomicInteger();
        this.extensionSubstreamHeaderSize = -1;
        this.uhdHeaderSize = -1;
        this.language = r3;
        this.roleFlags = r4;
        this.containerMimeType = r6;
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
    private void parseCoreHeader() {
        byte[] r1 = this.headerScratchBytes.getData();
        if (this.format != null) goto L5;
        Format r0 = DtsUtil.parseDtsFormat(r1, this.formatId, this.language, this.roleFlags, this.containerMimeType, null);
        this.format = r0;
        this.output.format(r0);
    L5:
        this.sampleSize = DtsUtil.getDtsFrameSize(r1);
        this.sampleDurationUs = Ints.checkedCast(Util.sampleCountToDurationUs(DtsUtil.parseDtsAudioSampleCount(r1), this.format.sampleRate));
    }

    @RequiresNonNull({"output"})
    private void parseExtensionSubstreamHeader() {
        DtsUtil.DtsHeader r0 = DtsUtil.parseDtsHdHeader(this.headerScratchBytes.getData());
        updateFormatWithDtsHeaderInfo(r0);
        this.sampleSize = r0.frameSize;
        long r02 = r0.frameDurationUs;
        if (r02 != (-9223372036854775807L)) goto L5;
        r02 = 0;
    L5:
        this.sampleDurationUs = r02;
    }

    @RequiresNonNull({"output"})
    private void parseUhdHeader() {
        DtsUtil.DtsHeader r0 = DtsUtil.parseDtsUhdHeader(this.headerScratchBytes.getData(), this.uhdAudioChunkId);
        if (this.frameType != 3) goto L5;
        updateFormatWithDtsHeaderInfo(r0);
    L5:
        this.sampleSize = r0.frameSize;
        long r02 = r0.frameDurationUs;
        if (r02 != (-9223372036854775807L)) goto L8;
        r02 = 0;
    L8:
        this.sampleDurationUs = r02;
    }

    private boolean skipToNextSyncWord(ParsableByteArray r6) {
    L3:
        if (r6.bytesLeft() <= 0) goto L8;
        int r0 = this.syncBytes << 8;
        this.syncBytes = r0;
        int r02 = r0 | r6.readUnsignedByte();
        this.syncBytes = r02;
        int r03 = DtsUtil.getFrameType(r02);
        this.frameType = r03;
        if (r03 == 0) goto L3;
        byte[] r62 = this.headerScratchBytes.getData();
        int r04 = this.syncBytes;
        r62[0] = (byte) ((r04 >> 24) & 255);
        r62[1] = (byte) ((r04 >> 16) & 255);
        r62[2] = (byte) ((r04 >> 8) & 255);
        r62[3] = (byte) (r04 & 255);
        this.bytesRead = 4;
        this.syncBytes = 0;
        return true;
    L8:
        return false;
    }

    @RequiresNonNull({"output"})
    private void updateFormatWithDtsHeaderInfo(DtsUtil.DtsHeader r5) {
        int r0 = r5.sampleRate;
        if (r0 == (-2147483647)) goto L21;
        int r1 = r5.channelCount;
        if (r1 == (-1)) goto L23;
        Format r2 = this.format;
        if (r2 != null) goto L10;
    L15:
        Format r02 = this.format;
        if (r02 != null) goto L18;
        Format.Builder r03 = new Format.Builder();
    L19:
        Format r52 = r03.setId(this.formatId).setContainerMimeType(this.containerMimeType).setSampleMimeType(r5.mimeType).setChannelCount(r5.channelCount).setSampleRate(r5.sampleRate).setLanguage(this.language).setRoleFlags(this.roleFlags).build();
        this.format = r52;
        this.output.format(r52);
        return;
    L18:
        r03 = r02.buildUpon();
        goto L19
    L10:
        if (r1 != r2.channelCount) goto L15;
        if (r0 != r2.sampleRate) goto L15;
        if (Objects.equals(r5.mimeType, r2.sampleMimeType) == false) goto L15;
        return;
    L23:
        return;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray r14) {
        Assertions.checkStateNotNull(this.output);
    L4:
        if (r14.bytesLeft() <= 0) goto L48;
        switch(this.state) {
            case 0: goto L39;
            case 1: goto L36;
            case 2: goto L33;
            case 3: goto L30;
            case 4: goto L24;
            case 5: goto L21;
            case 6: goto L9;
            default: goto L7;
        };
    L7:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return;
    L21:
        if (continueRead(r14, this.headerScratchBytes.getData(), this.uhdHeaderSize) == false) goto L4;
        parseUhdHeader();
        this.headerScratchBytes.setPosition(0);
        this.output.sampleData(this.headerScratchBytes, this.uhdHeaderSize);
        this.state = 6;
        goto L4
    L24:
        if (continueRead(r14, this.headerScratchBytes.getData(), 6) == false) goto L4;
        int r0 = DtsUtil.parseDtsUhdHeaderSize(this.headerScratchBytes.getData());
        this.uhdHeaderSize = r0;
        int r1 = this.bytesRead;
        if (r1 <= r0) goto L28;
        int r02 = r1 - r0;
        this.bytesRead = r1 - r02;
        r14.setPosition(r14.getPosition() - r02);
    L28:
        this.state = 5;
        goto L4
    L30:
        if (continueRead(r14, this.headerScratchBytes.getData(), this.extensionSubstreamHeaderSize) == false) goto L4;
        parseExtensionSubstreamHeader();
        this.headerScratchBytes.setPosition(0);
        this.output.sampleData(this.headerScratchBytes, this.extensionSubstreamHeaderSize);
        this.state = 6;
        goto L4
    L33:
        if (continueRead(r14, this.headerScratchBytes.getData(), 7) == false) goto L4;
        this.extensionSubstreamHeaderSize = DtsUtil.parseDtsHdHeaderSize(this.headerScratchBytes.getData());
        this.state = 3;
        goto L4
    L36:
        if (continueRead(r14, this.headerScratchBytes.getData(), 18) == false) goto L4;
        parseCoreHeader();
        this.headerScratchBytes.setPosition(0);
        this.output.sampleData(this.headerScratchBytes, 18);
        this.state = 6;
        goto L4
    L39:
        if (skipToNextSyncWord(r14) == false) goto L4;
        int r03 = this.frameType;
        if (r03 == 3) goto L47;
        if (r03 == 4) goto L47;
        if (r03 == 1) goto L45;
        this.state = 2;
        goto L4
    L45:
        this.state = 1;
    L47:
        this.state = 4;
        goto L4
    L9:
        int r04 = Math.min(r14.bytesLeft(), this.sampleSize - this.bytesRead);
        this.output.sampleData(r14, r04);
        int r12 = this.bytesRead + r04;
        this.bytesRead = r12;
        if (r12 != this.sampleSize) goto L4;
        if (this.timeUs == (-9223372036854775807L)) goto L14;
        boolean r05 = true;
    L15:
        Assertions.checkState(r05);
        TrackOutput r6 = this.output;
        long r7 = this.timeUs;
        if (this.frameType != 4) goto L18;
        int r9 = 0;
    L19:
        r6.sampleMetadata(r7, r9, this.sampleSize, 0, null);
        this.timeUs += this.sampleDurationUs;
        this.state = 0;
        goto L4
    L18:
        r9 = 1;
        goto L19
    L14:
        r05 = false;
        goto L15
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
        this.syncBytes = 0;
        this.timeUs = -9223372036854775807L;
        this.uhdAudioChunkId.set(0);
    }
}
