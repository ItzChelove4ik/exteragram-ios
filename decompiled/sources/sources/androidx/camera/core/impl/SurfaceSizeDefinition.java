package androidx.camera.core.impl;

import android.util.Size;
import java.util.Map;

/* loaded from: classes3.dex */
public abstract class SurfaceSizeDefinition {
    public SurfaceSizeDefinition() {
    }

    public static SurfaceSizeDefinition create(Size r10, Map<Integer, Size> r11, Size r12, Map<Integer, Size> r13, Size r14, Map<Integer, Size> r15, Map<Integer, Size> r16, Map<Integer, Size> r17, Map<Integer, Size> r18) {
        return new AutoValue_SurfaceSizeDefinition(r10, r11, r12, r13, r14, r15, r16, r17, r18);
    }

    public abstract Size getAnalysisSize();

    public Size getMaximum16x9Size(int r1) {
        return getMaximumSizeMap().get(Integer.valueOf(r1));
    }

    public abstract Map<Integer, Size> getMaximum16x9SizeMap();

    public Size getMaximum4x3Size(int r1) {
        return getMaximumSizeMap().get(Integer.valueOf(r1));
    }

    public abstract Map<Integer, Size> getMaximum4x3SizeMap();

    public Size getMaximumSize(int r1) {
        return getMaximumSizeMap().get(Integer.valueOf(r1));
    }

    public abstract Map<Integer, Size> getMaximumSizeMap();

    public abstract Size getPreviewSize();

    public abstract Size getRecordSize();

    public Size getS1440pSize(int r1) {
        return getS1440pSizeMap().get(Integer.valueOf(r1));
    }

    public abstract Map<Integer, Size> getS1440pSizeMap();

    public Size getS720pSize(int r1) {
        return getS720pSizeMap().get(Integer.valueOf(r1));
    }

    public abstract Map<Integer, Size> getS720pSizeMap();

    public Size getUltraMaximumSize(int r1) {
        return getUltraMaximumSizeMap().get(Integer.valueOf(r1));
    }

    public abstract Map<Integer, Size> getUltraMaximumSizeMap();
}
