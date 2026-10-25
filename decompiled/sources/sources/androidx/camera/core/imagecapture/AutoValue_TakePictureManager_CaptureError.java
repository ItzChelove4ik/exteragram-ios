package androidx.camera.core.imagecapture;

import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.imagecapture.TakePictureManager;
import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes3.dex */
final class AutoValue_TakePictureManager_CaptureError extends TakePictureManager.CaptureError {
    private final ImageCaptureException imageCaptureException;
    private final int requestId;

    public AutoValue_TakePictureManager_CaptureError(int r1, ImageCaptureException r2) {
        this.requestId = r1;
        if (r2 == null) goto L6;
        this.imageCaptureException = r2;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("Null imageCaptureException");
        throw null;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof TakePictureManager.CaptureError) == false) goto L12;
        TakePictureManager.CaptureError r52 = (TakePictureManager.CaptureError) r5;
        if (this.requestId != r52.getRequestId()) goto L12;
        if (this.imageCaptureException.equals(r52.getImageCaptureException()) == false) goto L12;
        return true;
    L12:
        return false;
    }

    @Override // androidx.camera.core.imagecapture.TakePictureManager.CaptureError
    public ImageCaptureException getImageCaptureException() {
        return this.imageCaptureException;
    }

    @Override // androidx.camera.core.imagecapture.TakePictureManager.CaptureError
    public int getRequestId() {
        return this.requestId;
    }

    public int hashCode() {
        int r0 = (this.requestId ^ 1000003) * 1000003;
        return this.imageCaptureException.hashCode() ^ r0;
    }

    public String toString() {
        return "CaptureError{requestId=" + this.requestId + ", imageCaptureException=" + this.imageCaptureException + "}";
    }
}
