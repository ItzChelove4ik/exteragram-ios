package androidx.camera.core;

import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes3.dex */
public interface CameraControl {

    public static final class OperationCanceledException extends Exception {
        public OperationCanceledException(String r1) {
            super(r1);
        }
    }

    ListenableFuture<Void> enableTorch(boolean r1);

    ListenableFuture<Void> setZoomRatio(float r1);

    ListenableFuture<FocusMeteringResult> startFocusAndMetering(FocusMeteringAction r1);
}
