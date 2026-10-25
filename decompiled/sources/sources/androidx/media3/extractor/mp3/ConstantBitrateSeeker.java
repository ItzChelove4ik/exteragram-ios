package androidx.media3.extractor.mp3;

import androidx.media3.extractor.ConstantBitrateSeekMap;
import androidx.media3.extractor.MpegAudioUtil;

/* loaded from: classes4.dex */
final class ConstantBitrateSeeker extends ConstantBitrateSeekMap implements Seeker {
    private final boolean allowSeeksIfLengthUnknown;
    private final int bitrate;
    private final long dataEndPosition;
    private final long firstFramePosition;
    private final int frameSize;

    public ConstantBitrateSeeker(long r1, long r3, int r5, int r6, boolean r7) {
        super(r1, r3, r5, r6, r7);
        this.firstFramePosition = r3;
        this.bitrate = r5;
        this.frameSize = r6;
        this.allowSeeksIfLengthUnknown = r7;
        if (r1 != (-1)) goto L6;
        r1 = -1;
    L6:
        this.dataEndPosition = r1;
    }

    public ConstantBitrateSeeker(long r9, long r11, MpegAudioUtil.Header r13, boolean r14) {
        this(r9, r11, r13.bitrate, r13.frameSize, r14);
    }

    public ConstantBitrateSeeker copyWithNewDataEndPosition(long r9) {
        return new ConstantBitrateSeeker(r9, this.firstFramePosition, this.bitrate, this.frameSize, this.allowSeeksIfLengthUnknown);
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public int getAverageBitrate() {
        return this.bitrate;
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public long getDataEndPosition() {
        return this.dataEndPosition;
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public long getDataStartPosition() {
        return this.firstFramePosition;
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public long getTimeUs(long r1) {
        return getTimeUsAtPosition(r1);
    }
}
