package androidx.camera.core;

/* loaded from: classes3.dex */
public class ImageCaptureException extends Exception {
    private final int mImageCaptureError;

    public ImageCaptureException(int r1, String r2, Throwable r3) {
        super(r2, r3);
        this.mImageCaptureError = r1;
    }

    public int getImageCaptureError() {
        return this.mImageCaptureError;
    }
}
