package org.webrtc;

import android.content.Context;
import android.graphics.Matrix;
import android.view.WindowManager;
import com.google.android.gms.cast.MediaError;
import org.webrtc.VideoFrame;

/* loaded from: classes6.dex */
interface CameraSession {

    public interface CreateSessionCallback {
        void onDone(CameraSession r1);

        void onFailure(FailureType r1, String r2);
    }

    public interface Events {
        void onCameraClosed(CameraSession r1);

        void onCameraDisconnected(CameraSession r1);

        void onCameraError(CameraSession r1, String r2);

        void onCameraOpening();

        void onFrameCaptured(CameraSession r1, VideoFrame r2);
    }

    public enum FailureType extends Enum<FailureType> {
        private static final /* synthetic */ FailureType[] $VALUES = null;
        public static final FailureType DISCONNECTED = null;
        public static final FailureType ERROR = null;

        private static /* synthetic */ FailureType[] $values() {
            return new FailureType[]{ERROR, DISCONNECTED};
        }

        static {
            ERROR = new FailureType(MediaError.ERROR_TYPE_ERROR, 0);
            DISCONNECTED = new FailureType("DISCONNECTED", 1);
            $VALUES = $values();
        }

        FailureType(String r1, int r2) {
        }

        public static FailureType valueOf(String r1) {
            return (FailureType) Enum.valueOf(FailureType.class, r1);
        }

        public static FailureType[] values() {
            return (FailureType[]) $VALUES.clone();
        }
    }

    static VideoFrame.TextureBuffer createTextureBufferWithModifiedTransformMatrix(TextureBufferImpl r2, boolean r3, int r4) {
        Matrix r0 = new Matrix();
        r0.preTranslate(0.5f, 0.5f);
        if (r3 == false) goto L5;
        r0.preScale(-1.0f, 1.0f);
    L5:
        r0.preRotate(r4);
        r0.preTranslate(-0.5f, -0.5f);
        return r2.applyTransformMatrix(r0, r2.getWidth(), r2.getHeight());
    }

    static int getDeviceOrientation(Context r1) {
        int r12 = ((WindowManager) r1.getSystemService("window")).getDefaultDisplay().getRotation();
        if (r12 != 1) goto L5;
        return 90;
    L5:
        if (r12 != 2) goto L7;
        return 180;
    L7:
        if (r12 == 3) goto L10;
        return 0;
    L10:
        return 270;
    }

    void stop();
}
