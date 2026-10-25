package androidx.camera.core.impl;

import android.util.Range;
import android.util.Size;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.impl.UseCaseConfigFactory;
import c.f$$ExternalSyntheticBUOutline2;
import java.util.List;

/* loaded from: classes3.dex */
final class AutoValue_AttachedSurfaceInfo extends AttachedSurfaceInfo {
    private final List<UseCaseConfigFactory.CaptureType> captureTypes;
    private final int customMaxFrameRate;
    private final DynamicRange dynamicRange;
    private final int imageFormat;
    private final Config implementationOptions;
    private final int sessionType;
    private final Size size;
    private final boolean strictFrameRateRequired;
    private final SurfaceConfig surfaceConfig;
    private final Range<Integer> targetFrameRate;

    public AutoValue_AttachedSurfaceInfo(SurfaceConfig r2, int r3, Size r4, DynamicRange r5, List<UseCaseConfigFactory.CaptureType> r6, Config r7, int r8, Range<Integer> r9, boolean r10, int r11) {
        if (r2 == null) goto L22;
        this.surfaceConfig = r2;
        this.imageFormat = r3;
        if (r4 == null) goto L20;
        this.size = r4;
        if (r5 == null) goto L18;
        this.dynamicRange = r5;
        if (r6 == null) goto L16;
        this.captureTypes = r6;
        this.implementationOptions = r7;
        this.sessionType = r8;
        if (r9 == null) goto L14;
        this.targetFrameRate = r9;
        this.strictFrameRateRequired = r10;
        this.customMaxFrameRate = r11;
        return;
    L14:
        f$$ExternalSyntheticBUOutline2.m("Null targetFrameRate");
        throw null;
    L16:
        f$$ExternalSyntheticBUOutline2.m("Null captureTypes");
        throw null;
    L18:
        f$$ExternalSyntheticBUOutline2.m("Null dynamicRange");
        throw null;
    L20:
        f$$ExternalSyntheticBUOutline2.m("Null size");
        throw null;
    L22:
        f$$ExternalSyntheticBUOutline2.m("Null surfaceConfig");
        throw null;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof AttachedSurfaceInfo) == false) goto L33;
        AttachedSurfaceInfo r52 = (AttachedSurfaceInfo) r5;
        if (this.surfaceConfig.equals(r52.getSurfaceConfig()) == false) goto L33;
        if (this.imageFormat != r52.getImageFormat()) goto L33;
        if (this.size.equals(r52.getSize()) == false) goto L33;
        if (this.dynamicRange.equals(r52.getDynamicRange()) == false) goto L33;
        if (this.captureTypes.equals(r52.getCaptureTypes()) == false) goto L33;
        Config r1 = this.implementationOptions;
        if (r1 != null) goto L23;
        if (r52.getImplementationOptions() != null) goto L33;
    L25:
        if (this.sessionType != r52.getSessionType()) goto L33;
        if (this.targetFrameRate.equals(r52.getTargetFrameRate()) == false) goto L33;
        if (this.strictFrameRateRequired != r52.isStrictFrameRateRequired()) goto L33;
        if (this.customMaxFrameRate != r52.getCustomMaxFrameRate()) goto L33;
        return true;
    L23:
        if (r1.equals(r52.getImplementationOptions()) == true) goto L25;
    L33:
        return false;
    }

    @Override // androidx.camera.core.impl.AttachedSurfaceInfo
    public List<UseCaseConfigFactory.CaptureType> getCaptureTypes() {
        return this.captureTypes;
    }

    @Override // androidx.camera.core.impl.AttachedSurfaceInfo
    public int getCustomMaxFrameRate() {
        return this.customMaxFrameRate;
    }

    @Override // androidx.camera.core.impl.AttachedSurfaceInfo
    public DynamicRange getDynamicRange() {
        return this.dynamicRange;
    }

    @Override // androidx.camera.core.impl.AttachedSurfaceInfo
    public int getImageFormat() {
        return this.imageFormat;
    }

    @Override // androidx.camera.core.impl.AttachedSurfaceInfo
    public Config getImplementationOptions() {
        return this.implementationOptions;
    }

    @Override // androidx.camera.core.impl.AttachedSurfaceInfo
    public int getSessionType() {
        return this.sessionType;
    }

    @Override // androidx.camera.core.impl.AttachedSurfaceInfo
    public Size getSize() {
        return this.size;
    }

    @Override // androidx.camera.core.impl.AttachedSurfaceInfo
    public SurfaceConfig getSurfaceConfig() {
        return this.surfaceConfig;
    }

    @Override // androidx.camera.core.impl.AttachedSurfaceInfo
    public Range<Integer> getTargetFrameRate() {
        return this.targetFrameRate;
    }

    public int hashCode() {
        int r0 = (((((((((this.surfaceConfig.hashCode() ^ 1000003) * 1000003) ^ this.imageFormat) * 1000003) ^ this.size.hashCode()) * 1000003) ^ this.dynamicRange.hashCode()) * 1000003) ^ this.captureTypes.hashCode()) * 1000003;
        Config r2 = this.implementationOptions;
        if (r2 != null) goto L5;
        int r22 = 0;
    L6:
        int r02 = (((((r0 ^ r22) * 1000003) ^ this.sessionType) * 1000003) ^ this.targetFrameRate.hashCode()) * 1000003;
        if (this.strictFrameRateRequired == false) goto L9;
        int r23 = 1231;
    L10:
        int r3 = this.customMaxFrameRate;
        return r3 ^ ((r02 ^ r23) * 1000003);
    L9:
        r23 = 1237;
        goto L10
    L5:
        r22 = r2.hashCode();
        goto L6
    }

    @Override // androidx.camera.core.impl.AttachedSurfaceInfo
    public boolean isStrictFrameRateRequired() {
        return this.strictFrameRateRequired;
    }

    public String toString() {
        return "AttachedSurfaceInfo{surfaceConfig=" + this.surfaceConfig + ", imageFormat=" + this.imageFormat + ", size=" + this.size + ", dynamicRange=" + this.dynamicRange + ", captureTypes=" + this.captureTypes + ", implementationOptions=" + this.implementationOptions + ", sessionType=" + this.sessionType + ", targetFrameRate=" + this.targetFrameRate + ", strictFrameRateRequired=" + this.strictFrameRateRequired + ", customMaxFrameRate=" + this.customMaxFrameRate + "}";
    }
}
