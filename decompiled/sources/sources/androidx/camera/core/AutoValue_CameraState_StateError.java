package androidx.camera.core;

import androidx.camera.core.CameraState;

/* loaded from: classes3.dex */
final class AutoValue_CameraState_StateError extends CameraState.StateError {
    private final Throwable cause;
    private final int code;

    public AutoValue_CameraState_StateError(int r1, Throwable r2) {
        this.code = r1;
        this.cause = r2;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof CameraState.StateError) == false) goto L17;
        CameraState.StateError r52 = (CameraState.StateError) r5;
        if (this.code != r52.getCode()) goto L17;
        Throwable r4 = this.cause;
        if (r4 != null) goto L15;
        if (r52.getCause() != null) goto L17;
    L16:
        return true;
    L15:
        if (r4.equals(r52.getCause()) == true) goto L16;
    L17:
        return false;
    }

    @Override // androidx.camera.core.CameraState.StateError
    public Throwable getCause() {
        return this.cause;
    }

    @Override // androidx.camera.core.CameraState.StateError
    public int getCode() {
        return this.code;
    }

    public int hashCode() {
        int r0 = (this.code ^ 1000003) * 1000003;
        Throwable r2 = this.cause;
        if (r2 != null) goto L5;
        int r22 = 0;
    L7:
        return r22 ^ r0;
    L5:
        r22 = r2.hashCode();
        goto L7
    }

    public String toString() {
        return "StateError{code=" + this.code + ", cause=" + this.cause + "}";
    }
}
