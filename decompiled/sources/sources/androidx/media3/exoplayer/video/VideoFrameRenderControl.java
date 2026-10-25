package androidx.media3.exoplayer.video;

import androidx.media3.common.VideoSize;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.LongArrayQueue;
import androidx.media3.common.util.TimedValueQueue;
import androidx.media3.exoplayer.video.VideoFrameReleaseControl;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class VideoFrameRenderControl {
    private final FrameRenderer frameRenderer;
    private long lastPresentationTimeUs;
    private long latestInputPresentationTimeUs;
    private long latestOutputPresentationTimeUs;
    private long outputStreamStartPositionUs;
    private VideoSize outputVideoSize;
    private final LongArrayQueue presentationTimestampsUs;
    private final TimedValueQueue<Long> streamStartPositionsUs;
    private final VideoFrameReleaseControl videoFrameReleaseControl;
    private final VideoFrameReleaseControl.FrameReleaseInfo videoFrameReleaseInfo;
    private final TimedValueQueue<VideoSize> videoSizes;

    public interface FrameRenderer {
        void dropFrame();

        void onVideoSizeChanged(VideoSize r1);

        void renderFrame(long r1, long r3, boolean r5);
    }

    public VideoFrameRenderControl(FrameRenderer r2, VideoFrameReleaseControl r3) {
        this.frameRenderer = r2;
        this.videoFrameReleaseControl = r3;
        this.videoFrameReleaseInfo = new VideoFrameReleaseControl.FrameReleaseInfo();
        this.videoSizes = new TimedValueQueue();
        this.streamStartPositionsUs = new TimedValueQueue();
        this.presentationTimestampsUs = new LongArrayQueue();
        this.latestInputPresentationTimeUs = -9223372036854775807L;
        this.outputVideoSize = VideoSize.UNKNOWN;
        this.latestOutputPresentationTimeUs = -9223372036854775807L;
        this.lastPresentationTimeUs = -9223372036854775807L;
    }

    private void dropFrame() {
        this.presentationTimestampsUs.remove();
        this.frameRenderer.dropFrame();
    }

    private static <T> T getLastAndClear(TimedValueQueue<T> r2) {
        if (r2.size() <= 0) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkArgument(r0);
    L8:
        if (r2.size() <= 1) goto L11;
        r2.pollFirst();
        goto L8
    L11:
        return (T) Assertions.checkNotNull(r2.pollFirst());
    L5:
        r0 = false;
        goto L6
    }

    private boolean maybeUpdateOutputStream(long r5) {
        Long r52 = this.streamStartPositionsUs.pollFloor(r5);
        if (r52 != null) goto L5;
        return false;
    L5:
        if (r52.longValue() == this.outputStreamStartPositionUs) goto L10;
        this.outputStreamStartPositionUs = r52.longValue();
        return true;
    L10:
        return false;
    }

    private boolean maybeUpdateOutputVideoSize(long r2) {
        VideoSize r22 = this.videoSizes.pollFloor(r2);
        if (r22 != null) goto L5;
        return false;
    L5:
        if (r22.equals(VideoSize.UNKNOWN) == false) goto L7;
        return false;
    L7:
        if (r22.equals(this.outputVideoSize) == true) goto L13;
        this.outputVideoSize = r22;
        return true;
    L13:
        return false;
    }

    private void renderFrame(boolean r8) {
        long r4 = this.presentationTimestampsUs.remove();
        if (maybeUpdateOutputVideoSize(r4) == false) goto L5;
        this.frameRenderer.onVideoSizeChanged(this.outputVideoSize);
    L5:
        if (r8 == false) goto L8;
        long r0 = Clock.DEFAULT.nanoTime();
    L7:
        long r2 = r0;
        this.frameRenderer.renderFrame(r2, r4, this.videoFrameReleaseControl.onFrameReleasedIsFirstFrame());
        return;
    L8:
        r0 = this.videoFrameReleaseInfo.getReleaseTimeNs();
        goto L7
    }

    public void flush() {
        this.presentationTimestampsUs.clear();
        this.latestInputPresentationTimeUs = -9223372036854775807L;
        this.latestOutputPresentationTimeUs = -9223372036854775807L;
        this.lastPresentationTimeUs = -9223372036854775807L;
        if (this.streamStartPositionsUs.size() <= 0) goto L6;
        this.outputStreamStartPositionUs = ((Long) getLastAndClear(this.streamStartPositionsUs)).longValue();
    L6:
        if (this.videoSizes.size() <= 0) goto L9;
        VideoSize r0 = (VideoSize) getLastAndClear(this.videoSizes);
        this.videoSizes.add(0, r0);
        return;
    }

    public boolean isEnded() {
        long r0 = this.lastPresentationTimeUs;
        if (r0 != (-9223372036854775807L)) goto L5;
        return false;
    L5:
        if (this.latestOutputPresentationTimeUs != r0) goto L10;
        return true;
    L10:
        return false;
    }

    public void onFrameAvailableForRendering(long r2) {
        this.presentationTimestampsUs.add(r2);
        this.latestInputPresentationTimeUs = r2;
        this.lastPresentationTimeUs = -9223372036854775807L;
    }

    public void onStreamChanged(int r5, long r6) {
        if (this.presentationTimestampsUs.isEmpty() == false) goto L6;
        this.videoFrameReleaseControl.onStreamChanged(r5);
        this.outputStreamStartPositionUs = r6;
        return;
    L6:
        TimedValueQueue<Long> r52 = this.streamStartPositionsUs;
        long r0 = this.latestInputPresentationTimeUs;
        if (r0 != (-9223372036854775807L)) goto L9;
        long r02 = -4611686018427387904L;
    L10:
        r52.add(r02, Long.valueOf(r6));
        return;
    L9:
        r02 = r0 + 1;
        goto L10
    }

    public void onVideoSizeChanged(int r6, int r7) {
        TimedValueQueue<VideoSize> r0 = this.videoSizes;
        long r1 = this.latestInputPresentationTimeUs;
        if (r1 != (-9223372036854775807L)) goto L5;
        long r12 = 0;
    L6:
        r0.add(r12, new VideoSize(r6, r7));
        return;
    L5:
        r12 = r1 + 1;
        goto L6
    }

    public void render(long r15, long r17) {
    L3:
        if (this.presentationTimestampsUs.isEmpty() == true) goto L40;
        long r2 = this.presentationTimestampsUs.element();
        if (maybeUpdateOutputStream(r2) == false) goto L7;
        this.videoFrameReleaseControl.onStreamChanged(2);
    L7:
        int r0 = this.videoFrameReleaseControl.getFrameReleaseAction(r2, r15, r17, this.outputStreamStartPositionUs, false, false, this.videoFrameReleaseInfo);
        boolean r1 = true;
        if (r0 == 0) goto L22;
        if (r0 == 1) goto L22;
        if (r0 == 2) goto L21;
        if (r0 == 3) goto L21;
        if (r0 != 4) goto L16;
        this.latestOutputPresentationTimeUs = r2;
        goto L3
    L16:
        if (r0 == 5) goto L27;
        Segment$$ExternalSyntheticBUOutline1.m(String.valueOf(r0));
        return;
    L27:
        return;
    L21:
        this.latestOutputPresentationTimeUs = r2;
        dropFrame();
    L22:
        this.latestOutputPresentationTimeUs = r2;
        if (r0 == 0) goto L26;
        r1 = false;
    L26:
        renderFrame(r1);
        goto L3
    }

    public void signalEndOfInput() {
        if (this.latestInputPresentationTimeUs != (-9223372036854775807L)) goto L5;
        this.latestInputPresentationTimeUs = Long.MIN_VALUE;
        this.latestOutputPresentationTimeUs = Long.MIN_VALUE;
    L5:
        this.lastPresentationTimeUs = this.latestInputPresentationTimeUs;
    }
}
