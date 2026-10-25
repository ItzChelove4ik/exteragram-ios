package androidx.camera.core.imagecapture;

import androidx.camera.core.ImageCaptureException;

/* loaded from: classes3.dex */
public interface TakePictureManager {

    public static abstract class CaptureError {
        public CaptureError() {
        }

        public static CaptureError of(int r1, ImageCaptureException r2) {
            return new AutoValue_TakePictureManager_CaptureError(r1, r2);
        }

        public abstract ImageCaptureException getImageCaptureException();

        public abstract int getRequestId();
    }

    public interface Provider {
        TakePictureManager newInstance(ImageCaptureControl r1);
    }

    void abortRequests();

    void pause();

    void resume();

    void setImagePipeline(ImagePipeline r1);
}
