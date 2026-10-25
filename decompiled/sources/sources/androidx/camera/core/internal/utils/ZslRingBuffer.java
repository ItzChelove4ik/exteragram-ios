package androidx.camera.core.internal.utils;

import androidx.camera.core.ImageInfo;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.impl.CameraCaptureMetaData$AeState;
import androidx.camera.core.impl.CameraCaptureMetaData$AfState;
import androidx.camera.core.impl.CameraCaptureMetaData$AwbState;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.CameraCaptureResults;

/* loaded from: classes3.dex */
public final class ZslRingBuffer extends ArrayRingBuffer<ImageProxy> {
    public ZslRingBuffer(int r1, RingBuffer$OnRemoveCallback<ImageProxy> r2) {
        super(r1, r2);
    }

    private boolean isValidZslFrame(ImageInfo r3) {
        CameraCaptureResult r2 = CameraCaptureResults.retrieveCameraCaptureResult(r3);
        if (r2 != null) goto L6;
        return false;
    L6:
        if (r2.getAfState() == CameraCaptureMetaData$AfState.LOCKED_FOCUSED) goto L11;
        if (r2.getAfState() == CameraCaptureMetaData$AfState.PASSIVE_FOCUSED) goto L11;
        return false;
    L11:
        if (r2.getAeState() == CameraCaptureMetaData$AeState.CONVERGED) goto L14;
        return false;
    L14:
        if (r2.getAwbState() == CameraCaptureMetaData$AwbState.CONVERGED) goto L16;
        return false;
    L16:
        return true;
    }

    public void enqueue(ImageProxy r2) {
        if (isValidZslFrame(r2.getImageInfo()) == false) goto L6;
        super.enqueue(r2);
        return;
    L6:
        this.mOnRemoveCallback.onRemove(r2);
    }
}
