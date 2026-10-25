package androidx.camera.core.impl;

import android.util.Size;
import c.f$$ExternalSyntheticBUOutline2;
import java.util.Map;

/* loaded from: classes3.dex */
final class AutoValue_SurfaceSizeDefinition extends SurfaceSizeDefinition {
    private final Size analysisSize;
    private final Map<Integer, Size> maximum16x9SizeMap;
    private final Map<Integer, Size> maximum4x3SizeMap;
    private final Map<Integer, Size> maximumSizeMap;
    private final Size previewSize;
    private final Size recordSize;
    private final Map<Integer, Size> s1440pSizeMap;
    private final Map<Integer, Size> s720pSizeMap;
    private final Map<Integer, Size> ultraMaximumSizeMap;

    public AutoValue_SurfaceSizeDefinition(Size r2, Map<Integer, Size> r3, Size r4, Map<Integer, Size> r5, Size r6, Map<Integer, Size> r7, Map<Integer, Size> r8, Map<Integer, Size> r9, Map<Integer, Size> r10) {
        if (r2 == null) goto L38;
        this.analysisSize = r2;
        if (r3 == null) goto L36;
        this.s720pSizeMap = r3;
        if (r4 == null) goto L34;
        this.previewSize = r4;
        if (r5 == null) goto L32;
        this.s1440pSizeMap = r5;
        if (r6 == null) goto L30;
        this.recordSize = r6;
        if (r7 == null) goto L28;
        this.maximumSizeMap = r7;
        if (r8 == null) goto L26;
        this.maximum4x3SizeMap = r8;
        if (r9 == null) goto L24;
        this.maximum16x9SizeMap = r9;
        if (r10 == null) goto L22;
        this.ultraMaximumSizeMap = r10;
        return;
    L22:
        f$$ExternalSyntheticBUOutline2.m("Null ultraMaximumSizeMap");
        throw null;
    L24:
        f$$ExternalSyntheticBUOutline2.m("Null maximum16x9SizeMap");
        throw null;
    L26:
        f$$ExternalSyntheticBUOutline2.m("Null maximum4x3SizeMap");
        throw null;
    L28:
        f$$ExternalSyntheticBUOutline2.m("Null maximumSizeMap");
        throw null;
    L30:
        f$$ExternalSyntheticBUOutline2.m("Null recordSize");
        throw null;
    L32:
        f$$ExternalSyntheticBUOutline2.m("Null s1440pSizeMap");
        throw null;
    L34:
        f$$ExternalSyntheticBUOutline2.m("Null previewSize");
        throw null;
    L36:
        f$$ExternalSyntheticBUOutline2.m("Null s720pSizeMap");
        throw null;
    L38:
        f$$ExternalSyntheticBUOutline2.m("Null analysisSize");
        throw null;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof SurfaceSizeDefinition) == false) goto L26;
        SurfaceSizeDefinition r52 = (SurfaceSizeDefinition) r5;
        if (this.analysisSize.equals(r52.getAnalysisSize()) == false) goto L26;
        if (this.s720pSizeMap.equals(r52.getS720pSizeMap()) == false) goto L26;
        if (this.previewSize.equals(r52.getPreviewSize()) == false) goto L26;
        if (this.s1440pSizeMap.equals(r52.getS1440pSizeMap()) == false) goto L26;
        if (this.recordSize.equals(r52.getRecordSize()) == false) goto L26;
        if (this.maximumSizeMap.equals(r52.getMaximumSizeMap()) == false) goto L26;
        if (this.maximum4x3SizeMap.equals(r52.getMaximum4x3SizeMap()) == false) goto L26;
        if (this.maximum16x9SizeMap.equals(r52.getMaximum16x9SizeMap()) == false) goto L26;
        if (this.ultraMaximumSizeMap.equals(r52.getUltraMaximumSizeMap()) == false) goto L26;
        return true;
    L26:
        return false;
    }

    @Override // androidx.camera.core.impl.SurfaceSizeDefinition
    public Size getAnalysisSize() {
        return this.analysisSize;
    }

    @Override // androidx.camera.core.impl.SurfaceSizeDefinition
    public Map<Integer, Size> getMaximum16x9SizeMap() {
        return this.maximum16x9SizeMap;
    }

    @Override // androidx.camera.core.impl.SurfaceSizeDefinition
    public Map<Integer, Size> getMaximum4x3SizeMap() {
        return this.maximum4x3SizeMap;
    }

    @Override // androidx.camera.core.impl.SurfaceSizeDefinition
    public Map<Integer, Size> getMaximumSizeMap() {
        return this.maximumSizeMap;
    }

    @Override // androidx.camera.core.impl.SurfaceSizeDefinition
    public Size getPreviewSize() {
        return this.previewSize;
    }

    @Override // androidx.camera.core.impl.SurfaceSizeDefinition
    public Size getRecordSize() {
        return this.recordSize;
    }

    @Override // androidx.camera.core.impl.SurfaceSizeDefinition
    public Map<Integer, Size> getS1440pSizeMap() {
        return this.s1440pSizeMap;
    }

    @Override // androidx.camera.core.impl.SurfaceSizeDefinition
    public Map<Integer, Size> getS720pSizeMap() {
        return this.s720pSizeMap;
    }

    @Override // androidx.camera.core.impl.SurfaceSizeDefinition
    public Map<Integer, Size> getUltraMaximumSizeMap() {
        return this.ultraMaximumSizeMap;
    }

    public int hashCode() {
        int r0 = (((((((((((((((this.analysisSize.hashCode() ^ 1000003) * 1000003) ^ this.s720pSizeMap.hashCode()) * 1000003) ^ this.previewSize.hashCode()) * 1000003) ^ this.s1440pSizeMap.hashCode()) * 1000003) ^ this.recordSize.hashCode()) * 1000003) ^ this.maximumSizeMap.hashCode()) * 1000003) ^ this.maximum4x3SizeMap.hashCode()) * 1000003) ^ this.maximum16x9SizeMap.hashCode()) * 1000003;
        return this.ultraMaximumSizeMap.hashCode() ^ r0;
    }

    public String toString() {
        return "SurfaceSizeDefinition{analysisSize=" + this.analysisSize + ", s720pSizeMap=" + this.s720pSizeMap + ", previewSize=" + this.previewSize + ", s1440pSizeMap=" + this.s1440pSizeMap + ", recordSize=" + this.recordSize + ", maximumSizeMap=" + this.maximumSizeMap + ", maximum4x3SizeMap=" + this.maximum4x3SizeMap + ", maximum16x9SizeMap=" + this.maximum16x9SizeMap + ", ultraMaximumSizeMap=" + this.ultraMaximumSizeMap + "}";
    }
}
