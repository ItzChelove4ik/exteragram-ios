package androidx.camera.core.impl;

import androidx.camera.core.Camera;
import androidx.camera.core.CameraControl;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.UseCase;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collection;

/* loaded from: classes3.dex */
public interface CameraInternal extends Camera, UseCase.StateChangeCallback {

    public enum State extends Enum<State> {
        private static final /* synthetic */ State[] $VALUES = null;
        public static final State CLOSED = null;
        public static final State CLOSING = null;
        public static final State CONFIGURED = null;
        public static final State OPEN = null;
        public static final State OPENING = null;
        public static final State PENDING_OPEN = null;
        public static final State RELEASED = null;
        public static final State RELEASING = null;
        private final boolean mHoldsCameraSlot;

        private static /* synthetic */ State[] $values() {
            return new State[]{RELEASED, RELEASING, CLOSED, PENDING_OPEN, CLOSING, OPENING, OPEN, CONFIGURED};
        }

        static {
            RELEASED = new State("RELEASED", 0, false);
            RELEASING = new State("RELEASING", 1, true);
            CLOSED = new State("CLOSED", 2, false);
            PENDING_OPEN = new State("PENDING_OPEN", 3, false);
            CLOSING = new State("CLOSING", 4, true);
            OPENING = new State("OPENING", 5, true);
            OPEN = new State("OPEN", 6, true);
            CONFIGURED = new State("CONFIGURED", 7, true);
            $VALUES = $values();
        }

        State(String r1, int r2, boolean r3) {
            this.mHoldsCameraSlot = r3;
        }

        public static State valueOf(String r1) {
            return (State) Enum.valueOf(State.class, r1);
        }

        public static State[] values() {
            return (State[]) $VALUES.clone();
        }
    }

    void attachUseCases(Collection<UseCase> r1);

    void detachUseCases(Collection<UseCase> r1);

    @Override // androidx.camera.core.Camera
    default CameraControl getCameraControl() {
        return getCameraControlInternal();
    }

    CameraControlInternal getCameraControlInternal();

    @Override // androidx.camera.core.Camera
    default CameraInfo getCameraInfo() {
        return getCameraInfoInternal();
    }

    CameraInfoInternal getCameraInfoInternal();

    default CameraConfig getExtendedConfig() {
        return CameraConfigs.defaultConfig();
    }

    default boolean getHasTransform() {
        return true;
    }

    default boolean isFrontFacing() {
        if (getCameraInfo().getLensFacing() != 0) goto L6;
        return true;
    L6:
        return false;
    }

    default boolean isRemoved() {
        return false;
    }

    default void onRemoved() {
    }

    ListenableFuture<Void> release();

    default void setActiveResumingMode(boolean r1) {
    }

    default void setExtendedConfig(CameraConfig r1) {
    }

    default void setPrimary(boolean r1) {
    }
}
