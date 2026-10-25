package androidx.camera.core.impl;

import androidx.camera.core.ImageInfo;
import androidx.camera.core.internal.CameraCaptureResultImageInfo;

/* loaded from: classes3.dex */
public abstract class CameraCaptureResults {
    public static CameraCaptureResult retrieveCameraCaptureResult(ImageInfo r1) {
        if ((r1 instanceof CameraCaptureResultImageInfo) == true) goto L5;
        return null;
    L5:
        return ((CameraCaptureResultImageInfo) r1).getCameraCaptureResult();
    }
}
