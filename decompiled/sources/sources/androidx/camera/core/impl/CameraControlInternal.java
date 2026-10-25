package androidx.camera.core.impl;

import androidx.camera.core.CameraControl;
import androidx.camera.core.FocusMeteringAction;
import androidx.camera.core.FocusMeteringResult;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.utils.futures.Futures;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes3.dex */
public interface CameraControlInternal extends CameraControl {
    public static final CameraControlInternal DEFAULT_EMPTY_INSTANCE = null;

    /* renamed from: androidx.camera.core.impl.CameraControlInternal$2, reason: invalid class name */
    public class AnonymousClass2 implements CameraControlInternal {
        public AnonymousClass2() {
        }

        @Override // androidx.camera.core.impl.CameraControlInternal
        public void addInteropConfig(Config r1) {
        }

        @Override // androidx.camera.core.impl.CameraControlInternal
        public void addZslConfig(SessionConfig.Builder r1) {
        }

        @Override // androidx.camera.core.impl.CameraControlInternal
        public void clearInteropConfig() {
        }

        @Override // androidx.camera.core.impl.CameraControlInternal
        public void clearZslConfig() {
        }

        @Override // androidx.camera.core.CameraControl
        public ListenableFuture<Void> enableTorch(boolean r1) {
            return Futures.immediateFuture(null);
        }

        @Override // androidx.camera.core.impl.CameraControlInternal
        public Config getInteropConfig() {
            return null;
        }

        @Override // androidx.camera.core.impl.CameraControlInternal
        public void setFlashMode(int r1) {
        }

        @Override // androidx.camera.core.CameraControl
        public ListenableFuture<Void> setZoomRatio(float r1) {
            return Futures.immediateFuture(null);
        }

        @Override // androidx.camera.core.CameraControl
        public ListenableFuture<FocusMeteringResult> startFocusAndMetering(FocusMeteringAction r1) {
            return Futures.immediateFuture(FocusMeteringResult.emptyInstance());
        }
    }

    static {
        DEFAULT_EMPTY_INSTANCE = new AnonymousClass2();
    }

    void addInteropConfig(Config r1);

    void addZslConfig(SessionConfig.Builder r1);

    void clearInteropConfig();

    void clearZslConfig();

    Config getInteropConfig();

    void setFlashMode(int r1);

    default void setScreenFlash(ImageCapture.ScreenFlash r1) {
    }
}
