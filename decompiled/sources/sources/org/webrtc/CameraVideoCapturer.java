package org.webrtc;

import android.media.MediaRecorder;
import c.f$$ExternalSyntheticBUOutline1;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes6.dex */
public interface CameraVideoCapturer extends VideoCapturer {

    public interface CameraEventsHandler {
        void onCameraClosed();

        void onCameraDisconnected();

        void onCameraError(String r1);

        void onCameraFreezed(String r1);

        void onCameraOpening(String r1);

        void onFirstFrameAvailable();
    }

    public static class CameraStatistics {
        private static final int CAMERA_FREEZE_REPORT_TIMOUT_MS = 4000;
        private static final int CAMERA_OBSERVER_PERIOD_MS = 2000;
        private static final String TAG = "CameraStatistics";
        private final Runnable cameraObserver;
        private final CameraEventsHandler eventsHandler;
        private int frameCount;
        private int freezePeriodCount;
        private final SurfaceTextureHelper surfaceTextureHelper;

        /* renamed from: org.webrtc.CameraVideoCapturer$CameraStatistics$1, reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ CameraStatistics this$0;

            public AnonymousClass1(CameraStatistics r1) {
                this.this$0 = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                Logging.d(CameraStatistics.TAG, "Camera fps: " + Math.round((CameraStatistics.m22340$$Nest$fgetframeCount(this.this$0) * 1000.0f) / 2000.0f) + ".");
                int r0 = CameraStatistics.m22340$$Nest$fgetframeCount(this.this$0);
                CameraStatistics r2 = this.this$0;
                if (r0 != 0) goto L14;
                CameraStatistics.m22344$$Nest$fputfreezePeriodCount(r2, CameraStatistics.m22341$$Nest$fgetfreezePeriodCount(r2) + 1);
                if ((CameraStatistics.m22341$$Nest$fgetfreezePeriodCount(this.this$0) * 2000) >= CameraStatistics.CAMERA_FREEZE_REPORT_TIMOUT_MS) goto L7;
            L15:
                CameraStatistics.m22343$$Nest$fputframeCount(this.this$0, 0);
                CameraStatistics.m22342$$Nest$fgetsurfaceTextureHelper(this.this$0).getHandler().postDelayed(this, 2000);
                return;
            L7:
                if (CameraStatistics.m22339$$Nest$fgeteventsHandler(this.this$0) == null) goto L15;
                Logging.e(CameraStatistics.TAG, "Camera freezed.");
                boolean r02 = CameraStatistics.m22342$$Nest$fgetsurfaceTextureHelper(this.this$0).isTextureInUse();
                CameraStatistics r4 = this.this$0;
                if (r02 == false) goto L12;
                CameraStatistics.m22339$$Nest$fgeteventsHandler(r4).onCameraFreezed("Camera failure. Client must return video buffers.");
                return;
            L12:
                CameraStatistics.m22339$$Nest$fgeteventsHandler(r4).onCameraFreezed("Camera failure.");
                return;
            L14:
                CameraStatistics.m22344$$Nest$fputfreezePeriodCount(r2, 0);
                goto L15
            }
        }

        /* renamed from: -$$Nest$fgeteventsHandler, reason: not valid java name */
        public static /* bridge */ /* synthetic */ CameraEventsHandler m22339$$Nest$fgeteventsHandler(CameraStatistics r0) {
            return r0.eventsHandler;
        }

        /* renamed from: -$$Nest$fgetframeCount, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m22340$$Nest$fgetframeCount(CameraStatistics r0) {
            return r0.frameCount;
        }

        /* renamed from: -$$Nest$fgetfreezePeriodCount, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m22341$$Nest$fgetfreezePeriodCount(CameraStatistics r0) {
            return r0.freezePeriodCount;
        }

        /* renamed from: -$$Nest$fgetsurfaceTextureHelper, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SurfaceTextureHelper m22342$$Nest$fgetsurfaceTextureHelper(CameraStatistics r0) {
            return r0.surfaceTextureHelper;
        }

        /* renamed from: -$$Nest$fputframeCount, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m22343$$Nest$fputframeCount(CameraStatistics r0, int r1) {
            r0.frameCount = r1;
        }

        /* renamed from: -$$Nest$fputfreezePeriodCount, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m22344$$Nest$fputfreezePeriodCount(CameraStatistics r0, int r1) {
            r0.freezePeriodCount = r1;
        }

        public CameraStatistics(SurfaceTextureHelper r2, CameraEventsHandler r3) {
            AnonymousClass1 r0 = new AnonymousClass1(this);
            this.cameraObserver = r0;
            if (r2 == null) goto L6;
            this.surfaceTextureHelper = r2;
            this.eventsHandler = r3;
            this.frameCount = 0;
            this.freezePeriodCount = 0;
            r2.getHandler().postDelayed(r0, 2000);
            return;
        L6:
            f$$ExternalSyntheticBUOutline1.m("SurfaceTextureHelper is null");
            throw null;
        }

        private void checkThread() {
            if (Thread.currentThread() != this.surfaceTextureHelper.getHandler().getLooper().getThread()) goto L5;
            return;
        L5:
            Segment$$ExternalSyntheticBUOutline1.m("Wrong thread");
        }

        public void addFrame() {
            checkThread();
            this.frameCount++;
        }

        public void release() {
            this.surfaceTextureHelper.getHandler().removeCallbacks(this.cameraObserver);
        }
    }

    public interface CameraSwitchHandler {
        void onCameraSwitchDone(boolean r1);

        void onCameraSwitchError(String r1);
    }

    @Deprecated
    public interface MediaRecorderHandler {
        void onMediaRecorderError(String r1);

        void onMediaRecorderSuccess();
    }

    @Deprecated
    default void addMediaRecorderToCamera(MediaRecorder r1, MediaRecorderHandler r2) {
        throw new UnsupportedOperationException("Deprecated and not implemented.");
    }

    @Deprecated
    default void removeMediaRecorderFromCamera(MediaRecorderHandler r1) {
        throw new UnsupportedOperationException("Deprecated and not implemented.");
    }

    void switchCamera(CameraSwitchHandler r1);

    void switchCamera(CameraSwitchHandler r1, String r2);
}
