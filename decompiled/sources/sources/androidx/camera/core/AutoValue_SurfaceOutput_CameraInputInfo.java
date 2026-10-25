package androidx.camera.core;

import android.graphics.Rect;
import android.util.Size;
import androidx.camera.core.SurfaceOutput;
import androidx.camera.core.impl.CameraInternal;
import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes3.dex */
final class AutoValue_SurfaceOutput_CameraInputInfo extends SurfaceOutput.CameraInputInfo {
    private final CameraInternal cameraInternal;
    private final Rect inputCropRect;
    private final Size inputSize;
    private final boolean mirroring;
    private final int rotationDegrees;

    public AutoValue_SurfaceOutput_CameraInputInfo(Size r2, Rect r3, CameraInternal r4, int r5, boolean r6) {
        if (r2 == null) goto L10;
        this.inputSize = r2;
        if (r3 == null) goto L8;
        this.inputCropRect = r3;
        this.cameraInternal = r4;
        this.rotationDegrees = r5;
        this.mirroring = r6;
        return;
    L8:
        f$$ExternalSyntheticBUOutline2.m("Null inputCropRect");
        throw null;
    L10:
        f$$ExternalSyntheticBUOutline2.m("Null inputSize");
        throw null;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof SurfaceOutput.CameraInputInfo) == false) goto L23;
        SurfaceOutput.CameraInputInfo r52 = (SurfaceOutput.CameraInputInfo) r5;
        if (this.inputSize.equals(r52.getInputSize()) == false) goto L23;
        if (this.inputCropRect.equals(r52.getInputCropRect()) == false) goto L23;
        CameraInternal r1 = this.cameraInternal;
        if (r1 != null) goto L17;
        if (r52.getCameraInternal() != null) goto L23;
    L19:
        if (this.rotationDegrees != r52.getRotationDegrees()) goto L23;
        if (this.mirroring != r52.getMirroring()) goto L23;
        return true;
    L17:
        if (r1.equals(r52.getCameraInternal()) == true) goto L19;
    L23:
        return false;
    }

    @Override // androidx.camera.core.SurfaceOutput.CameraInputInfo
    public CameraInternal getCameraInternal() {
        return this.cameraInternal;
    }

    @Override // androidx.camera.core.SurfaceOutput.CameraInputInfo
    public Rect getInputCropRect() {
        return this.inputCropRect;
    }

    @Override // androidx.camera.core.SurfaceOutput.CameraInputInfo
    public Size getInputSize() {
        return this.inputSize;
    }

    @Override // androidx.camera.core.SurfaceOutput.CameraInputInfo
    public boolean getMirroring() {
        return this.mirroring;
    }

    @Override // androidx.camera.core.SurfaceOutput.CameraInputInfo
    public int getRotationDegrees() {
        return this.rotationDegrees;
    }

    public int hashCode() {
        int r0 = (((this.inputSize.hashCode() ^ 1000003) * 1000003) ^ this.inputCropRect.hashCode()) * 1000003;
        CameraInternal r2 = this.cameraInternal;
        if (r2 != null) goto L5;
        int r22 = 0;
    L6:
        int r02 = (((r0 ^ r22) * 1000003) ^ this.rotationDegrees) * 1000003;
        if (this.mirroring == false) goto L9;
        int r3 = 1231;
    L11:
        return r3 ^ r02;
    L9:
        r3 = 1237;
        goto L11
    L5:
        r22 = r2.hashCode();
        goto L6
    }

    public String toString() {
        return "CameraInputInfo{inputSize=" + this.inputSize + ", inputCropRect=" + this.inputCropRect + ", cameraInternal=" + this.cameraInternal + ", rotationDegrees=" + this.rotationDegrees + ", mirroring=" + this.mirroring + "}";
    }
}
