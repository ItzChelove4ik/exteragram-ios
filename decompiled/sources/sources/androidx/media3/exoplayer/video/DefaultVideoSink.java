package androidx.media3.exoplayer.video;

import android.media.MediaFormat;
import android.view.Surface;
import androidx.media3.common.Format;
import androidx.media3.common.VideoSize;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Size;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.video.VideoFrameRenderControl;
import androidx.media3.exoplayer.video.VideoSink;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
final class DefaultVideoSink implements VideoSink {
    private Format inputFormat;
    private VideoSink.Listener listener;
    private Executor listenerExecutor;
    private Surface outputSurface;
    private long streamStartPositionUs;
    private final Queue<VideoSink.VideoFrameHandler> videoFrameHandlers;
    private VideoFrameMetadataListener videoFrameMetadataListener;
    private final VideoFrameReleaseControl videoFrameReleaseControl;
    private final VideoFrameRenderControl videoFrameRenderControl;

    /* renamed from: androidx.media3.exoplayer.video.DefaultVideoSink$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public final class FrameRendererImpl implements VideoFrameRenderControl.FrameRenderer {
        private Format outputFormat;
        final /* synthetic */ DefaultVideoSink this$0;

        /* renamed from: $r8$lambda$0jhtdi3S3P-z6LrrnfH_fKjPfEs, reason: not valid java name */
        public static /* synthetic */ void m849$r8$lambda$0jhtdi3S3Pz6LrrnfH_fKjPfEs(FrameRendererImpl r0) {
            DefaultVideoSink.access$500(r0.this$0).onFirstFrameRendered();
        }

        /* renamed from: $r8$lambda$2u_yU4WVW-H5-nFZ8EcEhLRBask, reason: not valid java name */
        public static /* synthetic */ void m850$r8$lambda$2u_yU4WVWH5nFZ8EcEhLRBask(FrameRendererImpl r0) {
            DefaultVideoSink.access$500(r0.this$0).onFrameDropped();
        }

        public static /* synthetic */ void $r8$lambda$aePYIxVMt8t6uIUdqIaOlLCNIlY(FrameRendererImpl r0, VideoSize r1) {
            DefaultVideoSink.access$500(r0.this$0).onVideoSizeChanged(r1);
        }

        private FrameRendererImpl(DefaultVideoSink r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ FrameRendererImpl(DefaultVideoSink r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // androidx.media3.exoplayer.video.VideoFrameRenderControl.FrameRenderer
        public void dropFrame() {
            DefaultVideoSink.access$100(this.this$0).execute(new DefaultVideoSink$FrameRendererImpl$$ExternalSyntheticLambda1(this));
            ((VideoSink.VideoFrameHandler) DefaultVideoSink.access$400(this.this$0).remove()).skip();
        }

        @Override // androidx.media3.exoplayer.video.VideoFrameRenderControl.FrameRenderer
        public void onVideoSizeChanged(VideoSize r3) {
            this.outputFormat = new Format.Builder().setWidth(r3.width).setHeight(r3.height).setSampleMimeType("video/raw").build();
            DefaultVideoSink.access$100(this.this$0).execute(new DefaultVideoSink$FrameRendererImpl$$ExternalSyntheticLambda2(this, r3));
        }

        @Override // androidx.media3.exoplayer.video.VideoFrameRenderControl.FrameRenderer
        public void renderFrame(long r8, long r10, boolean r12) {
            if (r12 == true) goto L4;
        L6:
            Format r122 = this.outputFormat;
            if (r122 != null) goto L9;
            r122 = new Format.Builder().build();
        L9:
            Format r5 = r122;
            DefaultVideoSink.access$300(this.this$0).onVideoFrameAboutToBeRendered(r10, r8, r5, null);
            ((VideoSink.VideoFrameHandler) DefaultVideoSink.access$400(this.this$0).remove()).render(r8);
            return;
        L4:
            if (DefaultVideoSink.access$200(this.this$0) == null) goto L6;
            DefaultVideoSink.access$100(this.this$0).execute(new DefaultVideoSink$FrameRendererImpl$$ExternalSyntheticLambda0(this));
            goto L6
        }
    }

    public static /* synthetic */ void $r8$lambda$IB6b8HzFl0OLys1Rpft78WROMlk(Runnable r0) {
    }

    public static /* synthetic */ void $r8$lambda$tOEgdrACBlgcXzlr6Aj52jG1x5M(DefaultVideoSink r0) {
        r0.listener.onFrameAvailableForRendering();
    }

    public static /* synthetic */ void $r8$lambda$xJMgMqWR0enCpEBpyceyLkbtJ_8(long r0, long r2, Format r4, MediaFormat r5) {
    }

    public DefaultVideoSink(VideoFrameReleaseControl r3, Clock r4) {
        this.videoFrameReleaseControl = r3;
        r3.setClock(r4);
        this.videoFrameRenderControl = new VideoFrameRenderControl(new FrameRendererImpl(this, null), r3);
        this.videoFrameHandlers = new ArrayDeque();
        this.inputFormat = new Format.Builder().build();
        this.streamStartPositionUs = -9223372036854775807L;
        this.listener = VideoSink.Listener.NO_OP;
        this.listenerExecutor = new DefaultVideoSink$$ExternalSyntheticLambda0();
        this.videoFrameMetadataListener = new DefaultVideoSink$$ExternalSyntheticLambda1();
    }

    public static /* synthetic */ Executor access$100(DefaultVideoSink r0) {
        return r0.listenerExecutor;
    }

    public static /* synthetic */ Surface access$200(DefaultVideoSink r0) {
        return r0.outputSurface;
    }

    public static /* synthetic */ VideoFrameMetadataListener access$300(DefaultVideoSink r0) {
        return r0.videoFrameMetadataListener;
    }

    public static /* synthetic */ Queue access$400(DefaultVideoSink r0) {
        return r0.videoFrameHandlers;
    }

    public static /* synthetic */ VideoSink.Listener access$500(DefaultVideoSink r0) {
        return r0.listener;
    }

    @Override // androidx.media3.exoplayer.video.VideoSink
    public void allowReleaseFirstFrameBeforeStarted() {
        this.videoFrameReleaseControl.allowReleaseFirstFrameBeforeStarted();
    }

    @Override // androidx.media3.exoplayer.video.VideoSink
    public void clearOutputSurfaceInfo() {
        this.outputSurface = null;
        this.videoFrameReleaseControl.setOutputSurface(null);
    }

    @Override // androidx.media3.exoplayer.video.VideoSink
    public void flush(boolean r1) {
        if (r1 == false) goto L4;
        this.videoFrameReleaseControl.reset();
    L4:
        this.videoFrameRenderControl.flush();
        this.videoFrameHandlers.clear();
    }

    @Override // androidx.media3.exoplayer.video.VideoSink
    public Surface getInputSurface() {
        return (Surface) Assertions.checkStateNotNull(this.outputSurface);
    }

    @Override // androidx.media3.exoplayer.video.VideoSink
    public boolean handleInputFrame(long r2, VideoSink.VideoFrameHandler r4) {
        this.videoFrameHandlers.add(r4);
        this.videoFrameRenderControl.onFrameAvailableForRendering(r2);
        this.listenerExecutor.execute(new DefaultVideoSink$$ExternalSyntheticLambda2(this));
        return true;
    }

    @Override // androidx.media3.exoplayer.video.VideoSink
    public boolean initialize(Format r1) {
        return true;
    }

    @Override // androidx.media3.exoplayer.video.VideoSink
    public boolean isEnded() {
        return this.videoFrameRenderControl.isEnded();
    }

    @Override // androidx.media3.exoplayer.video.VideoSink
    public boolean isInitialized() {
        return true;
    }

    @Override // androidx.media3.exoplayer.video.VideoSink
    public boolean isReady(boolean r1) {
        return this.videoFrameReleaseControl.isReady(r1);
    }

    @Override // androidx.media3.exoplayer.video.VideoSink
    public void join(boolean r1) {
        this.videoFrameReleaseControl.join(r1);
    }

    @Override // androidx.media3.exoplayer.video.VideoSink
    public void onInputStreamChanged(int r2, Format r3, long r4, int r6, List<Object> r7) {
        Assertions.checkState(r7.isEmpty());
        int r22 = r3.width;
        Format r72 = this.inputFormat;
        if (r22 == r72.width) goto L5;
    L6:
        this.videoFrameRenderControl.onVideoSizeChanged(r22, r3.height);
    L7:
        float r23 = r3.frameRate;
        if (r23 == this.inputFormat.frameRate) goto L10;
        this.videoFrameReleaseControl.setFrameRate(r23);
    L10:
        this.inputFormat = r3;
        if (r4 == this.streamStartPositionUs) goto L14;
        this.videoFrameRenderControl.onStreamChanged(r6, r4);
        this.streamStartPositionUs = r4;
        return;
    L14:
        return;
    L5:
        if (r3.height == r72.height) goto L7;
        goto L6
    }

    @Override // androidx.media3.exoplayer.video.VideoSink
    public void redraw() {
        throw new UnsupportedOperationException();
    }

    @Override // androidx.media3.exoplayer.video.VideoSink
    public void release() {
    }

    @Override // androidx.media3.exoplayer.video.VideoSink
    public void render(long r2, long r4) {
        this.videoFrameRenderControl.render(r2, r4);     // Catch: ExoPlaybackException -> L4
        return;
    L4:
        e = move-exception;
        throw new VideoSink.VideoSinkException(e, this.inputFormat);
    }

    @Override // androidx.media3.exoplayer.video.VideoSink
    public void setBufferTimestampAdjustmentUs(long r1) {
        throw new UnsupportedOperationException();
    }

    @Override // androidx.media3.exoplayer.video.VideoSink
    public void setChangeFrameRateStrategy(int r1) {
        this.videoFrameReleaseControl.setChangeFrameRateStrategy(r1);
    }

    @Override // androidx.media3.exoplayer.video.VideoSink
    public void setListener(VideoSink.Listener r1, Executor r2) {
        this.listener = r1;
        this.listenerExecutor = r2;
    }

    @Override // androidx.media3.exoplayer.video.VideoSink
    public void setOutputSurfaceInfo(Surface r1, Size r2) {
        this.outputSurface = r1;
        this.videoFrameReleaseControl.setOutputSurface(r1);
    }

    @Override // androidx.media3.exoplayer.video.VideoSink
    public void setPlaybackSpeed(float r1) {
        this.videoFrameReleaseControl.setPlaybackSpeed(r1);
    }

    @Override // androidx.media3.exoplayer.video.VideoSink
    public void setVideoEffects(List<Object> r1) {
        throw new UnsupportedOperationException();
    }

    @Override // androidx.media3.exoplayer.video.VideoSink
    public void setVideoFrameMetadataListener(VideoFrameMetadataListener r1) {
        this.videoFrameMetadataListener = r1;
    }

    @Override // androidx.media3.exoplayer.video.VideoSink
    public void signalEndOfCurrentInputStream() {
        this.videoFrameRenderControl.signalEndOfInput();
    }

    @Override // androidx.media3.exoplayer.video.VideoSink
    public void startRendering() {
        this.videoFrameReleaseControl.onStarted();
    }

    @Override // androidx.media3.exoplayer.video.VideoSink
    public void stopRendering() {
        this.videoFrameReleaseControl.onStopped();
    }
}
