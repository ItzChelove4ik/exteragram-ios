package androidx.camera.core;

import android.graphics.Matrix;
import android.graphics.Rect;
import androidx.camera.core.SurfaceRequest;
import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes3.dex */
final class AutoValue_SurfaceRequest_TransformationInfo extends SurfaceRequest.TransformationInfo {
    private final Rect getCropRect;
    private final int getRotationDegrees;
    private final Matrix getSensorToBufferTransform;
    private final int getTargetRotation;
    private final boolean hasCameraTransform;
    private final boolean isMirroring;

    public AutoValue_SurfaceRequest_TransformationInfo(Rect r2, int r3, int r4, boolean r5, Matrix r6, boolean r7) {
        if (r2 == null) goto L10;
        this.getCropRect = r2;
        this.getRotationDegrees = r3;
        this.getTargetRotation = r4;
        this.hasCameraTransform = r5;
        if (r6 == null) goto L8;
        this.getSensorToBufferTransform = r6;
        this.isMirroring = r7;
        return;
    L8:
        f$$ExternalSyntheticBUOutline2.m("Null getSensorToBufferTransform");
        throw null;
    L10:
        f$$ExternalSyntheticBUOutline2.m("Null getCropRect");
        throw null;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof SurfaceRequest.TransformationInfo) == false) goto L20;
        SurfaceRequest.TransformationInfo r52 = (SurfaceRequest.TransformationInfo) r5;
        if (this.getCropRect.equals(r52.getCropRect()) == false) goto L20;
        if (this.getRotationDegrees != r52.getRotationDegrees()) goto L20;
        if (this.getTargetRotation != r52.getTargetRotation()) goto L20;
        if (this.hasCameraTransform != r52.hasCameraTransform()) goto L20;
        if (this.getSensorToBufferTransform.equals(r52.getSensorToBufferTransform()) == false) goto L20;
        if (this.isMirroring != r52.isMirroring()) goto L20;
        return true;
    L20:
        return false;
    }

    @Override // androidx.camera.core.SurfaceRequest.TransformationInfo
    public Rect getCropRect() {
        return this.getCropRect;
    }

    @Override // androidx.camera.core.SurfaceRequest.TransformationInfo
    public int getRotationDegrees() {
        return this.getRotationDegrees;
    }

    @Override // androidx.camera.core.SurfaceRequest.TransformationInfo
    public Matrix getSensorToBufferTransform() {
        return this.getSensorToBufferTransform;
    }

    @Override // androidx.camera.core.SurfaceRequest.TransformationInfo
    public int getTargetRotation() {
        return this.getTargetRotation;
    }

    @Override // androidx.camera.core.SurfaceRequest.TransformationInfo
    public boolean hasCameraTransform() {
        return this.hasCameraTransform;
    }

    public int hashCode() {
        int r0 = (((((this.getCropRect.hashCode() ^ 1000003) * 1000003) ^ this.getRotationDegrees) * 1000003) ^ this.getTargetRotation) * 1000003;
        int r3 = 1237;
        if (this.hasCameraTransform == false) goto L5;
        int r2 = 1231;
    L6:
        int r02 = (((r0 ^ r2) * 1000003) ^ this.getSensorToBufferTransform.hashCode()) * 1000003;
        if (this.isMirroring == false) goto L10;
        r3 = 1231;
    L10:
        return r02 ^ r3;
    L5:
        r2 = 1237;
        goto L6
    }

    @Override // androidx.camera.core.SurfaceRequest.TransformationInfo
    public boolean isMirroring() {
        return this.isMirroring;
    }

    public String toString() {
        return "TransformationInfo{getCropRect=" + this.getCropRect + ", getRotationDegrees=" + this.getRotationDegrees + ", getTargetRotation=" + this.getTargetRotation + ", hasCameraTransform=" + this.hasCameraTransform + ", getSensorToBufferTransform=" + this.getSensorToBufferTransform + ", isMirroring=" + this.isMirroring + "}";
    }
}
