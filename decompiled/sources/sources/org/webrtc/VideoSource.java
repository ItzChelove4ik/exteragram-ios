package org.webrtc;

import org.webrtc.VideoProcessor;

/* loaded from: classes6.dex */
public class VideoSource extends MediaSource {
    private final CapturerObserver capturerObserver;
    private boolean isCapturerRunning;
    private final NativeAndroidVideoTrackSource nativeAndroidVideoTrackSource;
    private VideoProcessor videoProcessor;
    private final Object videoProcessorLock;

    /* renamed from: org.webrtc.VideoSource$1, reason: invalid class name */
    public class AnonymousClass1 implements CapturerObserver {
        final /* synthetic */ VideoSource this$0;

        public AnonymousClass1(VideoSource r1) {
            this.this$0 = r1;
        }

        @Override // org.webrtc.CapturerObserver
        public void onCapturerStarted(boolean r3) {
            VideoSource.m22407$$Nest$fgetnativeAndroidVideoTrackSource(this.this$0).setState(r3);
            Object r0 = VideoSource.m22409$$Nest$fgetvideoProcessorLock(this.this$0);
            monitor-enter(r0);
            VideoSource.m22410$$Nest$fputisCapturerRunning(this.this$0, r3);     // Catch: Throwable -> L7
            if (VideoSource.m22408$$Nest$fgetvideoProcessor(this.this$0) == null) goto L9;
            VideoSource.m22408$$Nest$fgetvideoProcessor(this.this$0).onCapturerStarted(r3);     // Catch: Throwable -> L7
        L9:
            monitor-exit(r0);     // Catch: Throwable -> L7
            return;
        L7:
            th = move-exception;
            throw th;
        }

        @Override // org.webrtc.CapturerObserver
        public void onCapturerStopped() {
            VideoSource.m22407$$Nest$fgetnativeAndroidVideoTrackSource(this.this$0).setState(false);
            Object r0 = VideoSource.m22409$$Nest$fgetvideoProcessorLock(this.this$0);
            monitor-enter(r0);
            VideoSource.m22410$$Nest$fputisCapturerRunning(this.this$0, false);     // Catch: Throwable -> L7
            if (VideoSource.m22408$$Nest$fgetvideoProcessor(this.this$0) == null) goto L9;
            VideoSource.m22408$$Nest$fgetvideoProcessor(this.this$0).onCapturerStopped();     // Catch: Throwable -> L7
        L9:
            monitor-exit(r0);     // Catch: Throwable -> L7
            return;
        L7:
            th = move-exception;
            throw th;
        }

        @Override // org.webrtc.CapturerObserver
        public void onFrameCaptured(VideoFrame r4) {
            VideoProcessor.FrameAdaptationParameters r0 = VideoSource.m22407$$Nest$fgetnativeAndroidVideoTrackSource(this.this$0).adaptFrame(r4);
            Object r1 = VideoSource.m22409$$Nest$fgetvideoProcessorLock(this.this$0);
            monitor-enter(r1);
        L9:
            th = move-exception;
            throw th;
        L5:
            if (VideoSource.m22408$$Nest$fgetvideoProcessor(this.this$0) == null) goto L11;
            VideoSource.m22408$$Nest$fgetvideoProcessor(this.this$0).onFrameCaptured(r4, r0);     // Catch: Throwable -> L9
            monitor-exit(r1);     // Catch: Throwable -> L9
            return;
        L11:
            monitor-exit(r1);     // Catch: Throwable -> L9
            VideoFrame r42 = VideoProcessor.applyFrameAdaptationParameters(r4, r0);
            if (r42 == null) goto L19;
            VideoSource.m22407$$Nest$fgetnativeAndroidVideoTrackSource(this.this$0).onFrameCaptured(r42);
            r42.release();
            return;
        }
    }

    public static class AspectRatio {
        public static final AspectRatio UNDEFINED = null;
        public final int height;
        public final int width;

        static {
            UNDEFINED = new AspectRatio(0, 0);
        }

        public AspectRatio(int r1, int r2) {
            this.width = r1;
            this.height = r2;
        }
    }

    /* renamed from: $r8$lambda$3Kwlk1veNmAIUYLe-TfCnMJUiz8, reason: not valid java name */
    public static /* synthetic */ void m22406$r8$lambda$3Kwlk1veNmAIUYLeTfCnMJUiz8(VideoSource r0, VideoFrame r1) {
        r0.lambda$setVideoProcessor$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$kZUVTlXagJ1gvgOa_j66Ie29A1U(VideoSource r0, VideoFrame r1) {
        r0.lambda$setVideoProcessor$0(r1);
    }

    /* renamed from: -$$Nest$fgetnativeAndroidVideoTrackSource, reason: not valid java name */
    public static /* bridge */ /* synthetic */ NativeAndroidVideoTrackSource m22407$$Nest$fgetnativeAndroidVideoTrackSource(VideoSource r0) {
        return r0.nativeAndroidVideoTrackSource;
    }

    /* renamed from: -$$Nest$fgetvideoProcessor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VideoProcessor m22408$$Nest$fgetvideoProcessor(VideoSource r0) {
        return r0.videoProcessor;
    }

    /* renamed from: -$$Nest$fgetvideoProcessorLock, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Object m22409$$Nest$fgetvideoProcessorLock(VideoSource r0) {
        return r0.videoProcessorLock;
    }

    /* renamed from: -$$Nest$fputisCapturerRunning, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22410$$Nest$fputisCapturerRunning(VideoSource r0, boolean r1) {
        r0.isCapturerRunning = r1;
    }

    public VideoSource(long r2) {
        super(r2);
        this.videoProcessorLock = new Object();
        this.capturerObserver = new AnonymousClass1(this);
        this.nativeAndroidVideoTrackSource = new NativeAndroidVideoTrackSource(r2);
    }

    private /* synthetic */ void lambda$setVideoProcessor$0(VideoFrame r1) {
        this.nativeAndroidVideoTrackSource.onFrameCaptured(r1);
    }

    private /* synthetic */ void lambda$setVideoProcessor$1(VideoFrame r2) {
        runWithReference(new VideoSource$$ExternalSyntheticLambda0(this, r2));
    }

    public void adaptOutputFormat(int r7, int r8, int r9) {
        int r1 = Math.max(r7, r8);
        int r2 = Math.min(r7, r8);
        adaptOutputFormat(r1, r2, r2, r1, r9);
    }

    public void adaptOutputFormat(int r2, int r3, int r4, int r5, int r6) {
        adaptOutputFormat(new AspectRatio(r2, r3), Integer.valueOf(r3 * r2), new AspectRatio(r4, r5), Integer.valueOf(r5 * r4), Integer.valueOf(r6));
    }

    public void adaptOutputFormat(AspectRatio r1, Integer r2, AspectRatio r3, Integer r4, Integer r5) {
        this.nativeAndroidVideoTrackSource.adaptOutputFormat(r1, r2, r3, r4, r5);
    }

    @Override // org.webrtc.MediaSource
    public void dispose() {
        setVideoProcessor(null);
        super.dispose();
    }

    public CapturerObserver getCapturerObserver() {
        return this.capturerObserver;
    }

    public long getNativeVideoTrackSource() {
        return getNativeMediaSource();
    }

    public void setIsScreencast(boolean r1) {
        this.nativeAndroidVideoTrackSource.setIsScreencast(r1);
    }

    public void setVideoProcessor(VideoProcessor r4) {
        Object r0 = this.videoProcessorLock;
        monitor-enter(r0);
        VideoProcessor r1 = this.videoProcessor;     // Catch: Throwable -> L9
        if (r1 == null) goto L11;
        r1.setSink(null);     // Catch: Throwable -> L9
        if (this.isCapturerRunning == false) goto L11;
        this.videoProcessor.onCapturerStopped();     // Catch: Throwable -> L9
    L11:
        this.videoProcessor = r4;     // Catch: Throwable -> L9
        if (r4 == null) goto L16;
        r4.setSink(new VideoSource$$ExternalSyntheticLambda1(this));     // Catch: Throwable -> L9
        if (this.isCapturerRunning == false) goto L16;
        r4.onCapturerStarted(true);     // Catch: Throwable -> L9
    L16:
        monitor-exit(r0);     // Catch: Throwable -> L9
        return;
    L9:
        th = move-exception;
        throw th;
    }
}
