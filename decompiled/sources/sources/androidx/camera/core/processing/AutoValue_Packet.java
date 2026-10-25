package androidx.camera.core.processing;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Size;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.utils.Exif;
import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes3.dex */
final class AutoValue_Packet<T> extends Packet<T> {
    private final CameraCaptureResult cameraCaptureResult;
    private final Rect cropRect;
    private final T data;
    private final Exif exif;
    private final int format;
    private final int rotationDegrees;
    private final Matrix sensorToBufferTransform;
    private final Size size;

    public AutoValue_Packet(T r2, Exif r3, int r4, Size r5, Rect r6, int r7, Matrix r8, CameraCaptureResult r9) {
        if (r2 == null) goto L22;
        this.data = r2;
        this.exif = r3;
        this.format = r4;
        if (r5 == null) goto L20;
        this.size = r5;
        if (r6 == null) goto L18;
        this.cropRect = r6;
        this.rotationDegrees = r7;
        if (r8 == null) goto L16;
        this.sensorToBufferTransform = r8;
        if (r9 == null) goto L14;
        this.cameraCaptureResult = r9;
        return;
    L14:
        f$$ExternalSyntheticBUOutline2.m("Null cameraCaptureResult");
        throw null;
    L16:
        f$$ExternalSyntheticBUOutline2.m("Null sensorToBufferTransform");
        throw null;
    L18:
        f$$ExternalSyntheticBUOutline2.m("Null cropRect");
        throw null;
    L20:
        f$$ExternalSyntheticBUOutline2.m("Null size");
        throw null;
    L22:
        f$$ExternalSyntheticBUOutline2.m("Null data");
        throw null;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof Packet) == false) goto L29;
        Packet r52 = (Packet) r5;
        if (this.data.equals(r52.getData()) == false) goto L29;
        Exif r1 = this.exif;
        if (r1 != null) goto L15;
        if (r52.getExif() != null) goto L29;
    L17:
        if (this.format != r52.getFormat()) goto L29;
        if (this.size.equals(r52.getSize()) == false) goto L29;
        if (this.cropRect.equals(r52.getCropRect()) == false) goto L29;
        if (this.rotationDegrees != r52.getRotationDegrees()) goto L29;
        if (this.sensorToBufferTransform.equals(r52.getSensorToBufferTransform()) == false) goto L29;
        if (this.cameraCaptureResult.equals(r52.getCameraCaptureResult()) == false) goto L29;
        return true;
    L15:
        if (r1.equals(r52.getExif()) == true) goto L17;
    L29:
        return false;
    }

    @Override // androidx.camera.core.processing.Packet
    public CameraCaptureResult getCameraCaptureResult() {
        return this.cameraCaptureResult;
    }

    @Override // androidx.camera.core.processing.Packet
    public Rect getCropRect() {
        return this.cropRect;
    }

    @Override // androidx.camera.core.processing.Packet
    public T getData() {
        return this.data;
    }

    @Override // androidx.camera.core.processing.Packet
    public Exif getExif() {
        return this.exif;
    }

    @Override // androidx.camera.core.processing.Packet
    public int getFormat() {
        return this.format;
    }

    @Override // androidx.camera.core.processing.Packet
    public int getRotationDegrees() {
        return this.rotationDegrees;
    }

    @Override // androidx.camera.core.processing.Packet
    public Matrix getSensorToBufferTransform() {
        return this.sensorToBufferTransform;
    }

    @Override // androidx.camera.core.processing.Packet
    public Size getSize() {
        return this.size;
    }

    public int hashCode() {
        int r0 = (this.data.hashCode() ^ 1000003) * 1000003;
        Exif r2 = this.exif;
        if (r2 != null) goto L5;
        int r22 = 0;
    L6:
        int r02 = (((((((((((r0 ^ r22) * 1000003) ^ this.format) * 1000003) ^ this.size.hashCode()) * 1000003) ^ this.cropRect.hashCode()) * 1000003) ^ this.rotationDegrees) * 1000003) ^ this.sensorToBufferTransform.hashCode()) * 1000003;
        return this.cameraCaptureResult.hashCode() ^ r02;
    L5:
        r22 = r2.hashCode();
        goto L6
    }

    public String toString() {
        return "Packet{data=" + this.data + ", exif=" + this.exif + ", format=" + this.format + ", size=" + this.size + ", cropRect=" + this.cropRect + ", rotationDegrees=" + this.rotationDegrees + ", sensorToBufferTransform=" + this.sensorToBufferTransform + ", cameraCaptureResult=" + this.cameraCaptureResult + "}";
    }
}
