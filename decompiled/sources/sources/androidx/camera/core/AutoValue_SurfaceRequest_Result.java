package androidx.camera.core;

import android.view.Surface;
import androidx.camera.core.SurfaceRequest;
import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes3.dex */
final class AutoValue_SurfaceRequest_Result extends SurfaceRequest.Result {
    private final int resultCode;
    private final Surface surface;

    public AutoValue_SurfaceRequest_Result(int r1, Surface r2) {
        this.resultCode = r1;
        if (r2 == null) goto L6;
        this.surface = r2;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("Null surface");
        throw null;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof SurfaceRequest.Result) == false) goto L12;
        SurfaceRequest.Result r52 = (SurfaceRequest.Result) r5;
        if (this.resultCode != r52.getResultCode()) goto L12;
        if (this.surface.equals(r52.getSurface()) == false) goto L12;
        return true;
    L12:
        return false;
    }

    @Override // androidx.camera.core.SurfaceRequest.Result
    public int getResultCode() {
        return this.resultCode;
    }

    @Override // androidx.camera.core.SurfaceRequest.Result
    public Surface getSurface() {
        return this.surface;
    }

    public int hashCode() {
        int r0 = (this.resultCode ^ 1000003) * 1000003;
        return this.surface.hashCode() ^ r0;
    }

    public String toString() {
        return "Result{resultCode=" + this.resultCode + ", surface=" + this.surface + "}";
    }
}
