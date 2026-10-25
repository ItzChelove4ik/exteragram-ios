package androidx.media3.extractor;

import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.metadata.flac.PictureFrame;
import com.google.android.gms.cast.MediaStatus;
import java.util.Collections;
import java.util.List;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes4.dex */
public final class FlacStreamMetadata {
    public final int bitsPerSample;
    public final int bitsPerSampleLookupKey;
    public final int channels;
    public final int maxBlockSizeSamples;
    public final int maxFrameSize;
    private final Metadata metadata;
    public final int minBlockSizeSamples;
    public final int minFrameSize;
    public final int sampleRate;
    public final int sampleRateLookupKey;
    public final SeekTable seekTable;
    public final long totalSamples;

    public static class SeekTable {
        public final long[] pointOffsets;
        public final long[] pointSampleNumbers;

        public SeekTable(long[] r1, long[] r2) {
            this.pointSampleNumbers = r1;
            this.pointOffsets = r2;
        }
    }

    private FlacStreamMetadata(int r1, int r2, int r3, int r4, int r5, int r6, int r7, long r8, SeekTable r10, Metadata r11) {
        this.minBlockSizeSamples = r1;
        this.maxBlockSizeSamples = r2;
        this.minFrameSize = r3;
        this.maxFrameSize = r4;
        this.sampleRate = r5;
        this.sampleRateLookupKey = getSampleRateLookupKey(r5);
        this.channels = r6;
        this.bitsPerSample = r7;
        this.bitsPerSampleLookupKey = getBitsPerSampleLookupKey(r7);
        this.totalSamples = r8;
        this.seekTable = r10;
        this.metadata = r11;
    }

    public FlacStreamMetadata(byte[] r2, int r3) {
        ParsableBitArray r0 = new ParsableBitArray(r2);
        r0.setPosition(r3 * 8);
        this.minBlockSizeSamples = r0.readBits(16);
        this.maxBlockSizeSamples = r0.readBits(16);
        this.minFrameSize = r0.readBits(24);
        this.maxFrameSize = r0.readBits(24);
        int r22 = r0.readBits(20);
        this.sampleRate = r22;
        this.sampleRateLookupKey = getSampleRateLookupKey(r22);
        this.channels = r0.readBits(3) + 1;
        int r23 = r0.readBits(5) + 1;
        this.bitsPerSample = r23;
        this.bitsPerSampleLookupKey = getBitsPerSampleLookupKey(r23);
        this.totalSamples = r0.readBitsToLong(36);
        this.seekTable = null;
        this.metadata = null;
    }

    private static int getBitsPerSampleLookupKey(int r1) {
        if (r1 != 8) goto L5;
        return 1;
    L5:
        if (r1 != 12) goto L7;
        return 2;
    L7:
        if (r1 != 16) goto L9;
        return 4;
    L9:
        if (r1 != 20) goto L11;
        return 5;
    L11:
        if (r1 != 24) goto L13;
        return 6;
    L13:
        if (r1 == 32) goto L16;
        return -1;
    L16:
        return 7;
    }

    private static int getSampleRateLookupKey(int r0) {
        switch(r0) {
            case 8000: goto L25;
            case 16000: goto L23;
            case 22050: goto L21;
            case 24000: goto L19;
            case 32000: goto L17;
            case 44100: goto L15;
            case 48000: goto L13;
            case 88200: goto L11;
            case 96000: goto L9;
            case 176400: goto L7;
            case 192000: goto L5;
            default: goto L3;
        };
    L3:
        return -1;
    L5:
        return 3;
    L7:
        return 2;
    L9:
        return 11;
    L11:
        return 1;
    L13:
        return 10;
    L15:
        return 9;
    L17:
        return 8;
    L19:
        return 7;
    L21:
        return 6;
    L23:
        return 5;
    L25:
        return 4;
    }

    public FlacStreamMetadata copyWithPictureFrames(List<PictureFrame> r14) {
        Metadata r12 = getMetadataCopyWithAppendedEntriesFrom(new Metadata(r14));
        return new FlacStreamMetadata(this.minBlockSizeSamples, this.maxBlockSizeSamples, this.minFrameSize, this.maxFrameSize, this.sampleRate, this.channels, this.bitsPerSample, this.totalSamples, this.seekTable, r12);
    }

    public FlacStreamMetadata copyWithSeekTable(SeekTable r13) {
        return new FlacStreamMetadata(this.minBlockSizeSamples, this.maxBlockSizeSamples, this.minFrameSize, this.maxFrameSize, this.sampleRate, this.channels, this.bitsPerSample, this.totalSamples, r13, this.metadata);
    }

    public FlacStreamMetadata copyWithVorbisComments(List<String> r13) {
        Metadata r11 = getMetadataCopyWithAppendedEntriesFrom(VorbisUtil.parseVorbisComments(r13));
        return new FlacStreamMetadata(this.minBlockSizeSamples, this.maxBlockSizeSamples, this.minFrameSize, this.maxFrameSize, this.sampleRate, this.channels, this.bitsPerSample, this.totalSamples, this.seekTable, r11);
    }

    public long getApproxBytesPerFrame() {
        int r0 = this.maxFrameSize;
        if (r0 <= 0) goto L7;
        long r02 = (r0 + this.minFrameSize) / 2;
        long r2 = 1;
    L6:
        return r02 + r2;
    L7:
        int r03 = this.minBlockSizeSamples;
        if (r03 != this.maxBlockSizeSamples) goto L11;
        if (r03 <= 0) goto L11;
        long r04 = r03;
    L12:
        r02 = ((r04 * this.channels) * this.bitsPerSample) / 8;
        r2 = 64;
    L11:
        r04 = MediaStatus.COMMAND_EDIT_TRACKS;
        goto L12
    }

    public long getDurationUs() {
        long r0 = this.totalSamples;
        if (r0 != 0) goto L7;
        return -9223372036854775807L;
    L7:
        return (r0 * 1000000) / this.sampleRate;
    }

    public Format getFormat(byte[] r4, Metadata r5) {
        r4[4] = ByteCompanionObject.MIN_VALUE;
        int r0 = this.maxFrameSize;
        if (r0 > 0) goto L6;
        r0 = -1;
    L6:
        Metadata r52 = getMetadataCopyWithAppendedEntriesFrom(r5);
        return new Format.Builder().setSampleMimeType("audio/flac").setMaxInputSize(r0).setChannelCount(this.channels).setSampleRate(this.sampleRate).setPcmEncoding(Util.getPcmEncoding(this.bitsPerSample)).setInitializationData(Collections.singletonList(r4)).setMetadata(r52).build();
    }

    public Metadata getMetadataCopyWithAppendedEntriesFrom(Metadata r1) {
        Metadata r0 = this.metadata;
        if (r0 != null) goto L6;
        return r1;
    L6:
        return r0.copyWithAppendedEntriesFrom(r1);
    }

    public long getSampleNumber(long r9) {
        return Util.constrainValue((r9 * this.sampleRate) / 1000000, 0, this.totalSamples - 1);
    }
}
