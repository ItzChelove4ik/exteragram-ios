package androidx.camera.core;

import androidx.camera.core.CameraState;
import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes3.dex */
final class AutoValue_CameraState extends CameraState {
    private final CameraState.StateError error;
    private final CameraState.Type type;

    public AutoValue_CameraState(CameraState.Type r1, CameraState.StateError r2) {
        if (r1 == null) goto L6;
        this.type = r1;
        this.error = r2;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("Null type");
        throw null;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof CameraState) == false) goto L17;
        CameraState r52 = (CameraState) r5;
        if (this.type.equals(r52.getType()) == false) goto L17;
        CameraState.StateError r4 = this.error;
        if (r4 != null) goto L15;
        if (r52.getError() != null) goto L17;
    L16:
        return true;
    L15:
        if (r4.equals(r52.getError()) == true) goto L16;
    L17:
        return false;
    }

    @Override // androidx.camera.core.CameraState
    public CameraState.StateError getError() {
        return this.error;
    }

    @Override // androidx.camera.core.CameraState
    public CameraState.Type getType() {
        return this.type;
    }

    public int hashCode() {
        int r0 = (this.type.hashCode() ^ 1000003) * 1000003;
        CameraState.StateError r2 = this.error;
        if (r2 != null) goto L5;
        int r22 = 0;
    L7:
        return r22 ^ r0;
    L5:
        r22 = r2.hashCode();
        goto L7
    }

    public String toString() {
        return "CameraState{type=" + this.type + ", error=" + this.error + "}";
    }
}
