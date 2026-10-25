package androidx.camera.core;

import androidx.lifecycle.LifecycleOwner;
import java.util.List;

/* loaded from: classes3.dex */
public class ConcurrentCamera {
    private List<Camera> mCameras;

    public static final class SingleCameraConfig {
        private CameraSelector mCameraSelector;
        private CompositionSettings mCompositionSettings;
        private LifecycleOwner mLifecycleOwner;
        private UseCaseGroup mUseCaseGroup;

        public SingleCameraConfig(CameraSelector r1, UseCaseGroup r2, CompositionSettings r3, LifecycleOwner r4) {
            this.mCameraSelector = r1;
            this.mUseCaseGroup = r2;
            this.mCompositionSettings = r3;
            this.mLifecycleOwner = r4;
        }

        public SingleCameraConfig(CameraSelector r2, UseCaseGroup r3, LifecycleOwner r4) {
            this(r2, r3, CompositionSettings.DEFAULT, r4);
        }

        public CameraSelector getCameraSelector() {
            return this.mCameraSelector;
        }

        public CompositionSettings getCompositionSettings() {
            return this.mCompositionSettings;
        }

        public LifecycleOwner getLifecycleOwner() {
            return this.mLifecycleOwner;
        }

        public UseCaseGroup getUseCaseGroup() {
            return this.mUseCaseGroup;
        }
    }

    public ConcurrentCamera(List<Camera> r1) {
        this.mCameras = r1;
    }

    public List<Camera> getCameras() {
        return this.mCameras;
    }
}
