package androidx.camera.core;

import android.graphics.Matrix;
import androidx.camera.core.impl.TagBundle;
import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes3.dex */
final class AutoValue_ImmutableImageInfo extends ImmutableImageInfo {
    private final int flashState;
    private final int rotationDegrees;
    private final Matrix sensorToBufferTransformMatrix;
    private final TagBundle tagBundle;
    private final long timestamp;

    public AutoValue_ImmutableImageInfo(TagBundle r2, long r3, int r5, Matrix r6, int r7) {
        if (r2 == null) goto L10;
        this.tagBundle = r2;
        this.timestamp = r3;
        this.rotationDegrees = r5;
        if (r6 == null) goto L8;
        this.sensorToBufferTransformMatrix = r6;
        this.flashState = r7;
        return;
    L8:
        f$$ExternalSyntheticBUOutline2.m("Null sensorToBufferTransformMatrix");
        throw null;
    L10:
        f$$ExternalSyntheticBUOutline2.m("Null tagBundle");
        throw null;
    }

    public boolean equals(Object r8) {
        if (r8 != this) goto L6;
        return true;
    L6:
        if ((r8 instanceof ImmutableImageInfo) == false) goto L18;
        ImmutableImageInfo r82 = (ImmutableImageInfo) r8;
        if (this.tagBundle.equals(r82.getTagBundle()) == false) goto L18;
        if (this.timestamp != r82.getTimestamp()) goto L18;
        if (this.rotationDegrees != r82.getRotationDegrees()) goto L18;
        if (this.sensorToBufferTransformMatrix.equals(r82.getSensorToBufferTransformMatrix()) == false) goto L18;
        if (this.flashState != r82.getFlashState()) goto L18;
        return true;
    L18:
        return false;
    }

    @Override // androidx.camera.core.ImmutableImageInfo, androidx.camera.core.ImageInfo
    public int getFlashState() {
        return this.flashState;
    }

    @Override // androidx.camera.core.ImmutableImageInfo
    public int getRotationDegrees() {
        return this.rotationDegrees;
    }

    @Override // androidx.camera.core.ImmutableImageInfo
    public Matrix getSensorToBufferTransformMatrix() {
        return this.sensorToBufferTransformMatrix;
    }

    @Override // androidx.camera.core.ImmutableImageInfo, androidx.camera.core.ImageInfo
    public TagBundle getTagBundle() {
        return this.tagBundle;
    }

    @Override // androidx.camera.core.ImmutableImageInfo, androidx.camera.core.ImageInfo
    public long getTimestamp() {
        return this.timestamp;
    }

    public int hashCode() {
        int r0 = (this.tagBundle.hashCode() ^ 1000003) * 1000003;
        long r2 = this.timestamp;
        int r02 = (((((r0 ^ ((int) (r2 ^ (r2 >>> 32)))) * 1000003) ^ this.rotationDegrees) * 1000003) ^ this.sensorToBufferTransformMatrix.hashCode()) * 1000003;
        return this.flashState ^ r02;
    }

    public String toString() {
        return "ImmutableImageInfo{tagBundle=" + this.tagBundle + ", timestamp=" + this.timestamp + ", rotationDegrees=" + this.rotationDegrees + ", sensorToBufferTransformMatrix=" + this.sensorToBufferTransformMatrix + ", flashState=" + this.flashState + "}";
    }
}
