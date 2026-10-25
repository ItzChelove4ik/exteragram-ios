package androidx.camera.core.impl;

import androidx.camera.core.DynamicRange;
import androidx.camera.core.impl.SessionConfig;
import c.f$$ExternalSyntheticBUOutline2;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
final class AutoValue_SessionConfig_OutputConfig extends SessionConfig.OutputConfig {
    private final DynamicRange dynamicRange;
    private final int mirrorMode;
    private final String physicalCameraId;
    private final List<DeferrableSurface> sharedSurfaces;
    private final DeferrableSurface surface;
    private final int surfaceGroupId;

    /* renamed from: androidx.camera.core.impl.AutoValue_SessionConfig_OutputConfig$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder extends SessionConfig.OutputConfig.Builder {
        private DynamicRange dynamicRange;
        private Integer mirrorMode;
        private String physicalCameraId;
        private List<DeferrableSurface> sharedSurfaces;
        private DeferrableSurface surface;
        private Integer surfaceGroupId;

        public Builder() {
        }

        @Override // androidx.camera.core.impl.SessionConfig.OutputConfig.Builder
        public SessionConfig.OutputConfig build() {
            if (this.surface != null) goto L5;
            String r0 = " surface";
        L7:
            if (this.sharedSurfaces != null) goto L10;
            r0 = r0.concat(" sharedSurfaces");
        L10:
            if (this.mirrorMode != null) goto L13;
            r0 = r0.concat(" mirrorMode");
        L13:
            if (this.surfaceGroupId != null) goto L16;
            r0 = r0.concat(" surfaceGroupId");
        L16:
            if (this.dynamicRange != null) goto L19;
            r0 = r0.concat(" dynamicRange");
        L19:
            if (r0.isEmpty() == true) goto L21;
            Segment$$ExternalSyntheticBUOutline1.m("Missing required properties:".concat(r0));
            return null;
        L21:
            return new AutoValue_SessionConfig_OutputConfig(this.surface, this.sharedSurfaces, this.physicalCameraId, this.mirrorMode.intValue(), this.surfaceGroupId.intValue(), this.dynamicRange, null);
        L5:
            r0 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L7
        }

        @Override // androidx.camera.core.impl.SessionConfig.OutputConfig.Builder
        public SessionConfig.OutputConfig.Builder setDynamicRange(DynamicRange r1) {
            if (r1 == null) goto L5;
            this.dynamicRange = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null dynamicRange");
            return null;
        }

        @Override // androidx.camera.core.impl.SessionConfig.OutputConfig.Builder
        public SessionConfig.OutputConfig.Builder setMirrorMode(int r1) {
            this.mirrorMode = Integer.valueOf(r1);
            return this;
        }

        @Override // androidx.camera.core.impl.SessionConfig.OutputConfig.Builder
        public SessionConfig.OutputConfig.Builder setPhysicalCameraId(String r1) {
            this.physicalCameraId = r1;
            return this;
        }

        @Override // androidx.camera.core.impl.SessionConfig.OutputConfig.Builder
        public SessionConfig.OutputConfig.Builder setSharedSurfaces(List<DeferrableSurface> r1) {
            if (r1 == null) goto L5;
            this.sharedSurfaces = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null sharedSurfaces");
            return null;
        }

        public SessionConfig.OutputConfig.Builder setSurface(DeferrableSurface r1) {
            if (r1 == null) goto L5;
            this.surface = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null surface");
            return null;
        }

        @Override // androidx.camera.core.impl.SessionConfig.OutputConfig.Builder
        public SessionConfig.OutputConfig.Builder setSurfaceGroupId(int r1) {
            this.surfaceGroupId = Integer.valueOf(r1);
            return this;
        }
    }

    private AutoValue_SessionConfig_OutputConfig(DeferrableSurface r1, List<DeferrableSurface> r2, String r3, int r4, int r5, DynamicRange r6) {
        this.surface = r1;
        this.sharedSurfaces = r2;
        this.physicalCameraId = r3;
        this.mirrorMode = r4;
        this.surfaceGroupId = r5;
        this.dynamicRange = r6;
    }

    public /* synthetic */ AutoValue_SessionConfig_OutputConfig(DeferrableSurface r1, List r2, String r3, int r4, int r5, DynamicRange r6, AnonymousClass1 r7) {
        this(r1, r2, r3, r4, r5, r6);
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof SessionConfig.OutputConfig) == false) goto L25;
        SessionConfig.OutputConfig r52 = (SessionConfig.OutputConfig) r5;
        if (this.surface.equals(r52.getSurface()) == false) goto L25;
        if (this.sharedSurfaces.equals(r52.getSharedSurfaces()) == false) goto L25;
        String r1 = this.physicalCameraId;
        if (r1 != null) goto L17;
        if (r52.getPhysicalCameraId() != null) goto L25;
    L19:
        if (this.mirrorMode != r52.getMirrorMode()) goto L25;
        if (this.surfaceGroupId != r52.getSurfaceGroupId()) goto L25;
        if (this.dynamicRange.equals(r52.getDynamicRange()) == false) goto L25;
        return true;
    L17:
        if (r1.equals(r52.getPhysicalCameraId()) == true) goto L19;
    L25:
        return false;
    }

    @Override // androidx.camera.core.impl.SessionConfig.OutputConfig
    public DynamicRange getDynamicRange() {
        return this.dynamicRange;
    }

    @Override // androidx.camera.core.impl.SessionConfig.OutputConfig
    public int getMirrorMode() {
        return this.mirrorMode;
    }

    @Override // androidx.camera.core.impl.SessionConfig.OutputConfig
    public String getPhysicalCameraId() {
        return this.physicalCameraId;
    }

    @Override // androidx.camera.core.impl.SessionConfig.OutputConfig
    public List<DeferrableSurface> getSharedSurfaces() {
        return this.sharedSurfaces;
    }

    @Override // androidx.camera.core.impl.SessionConfig.OutputConfig
    public DeferrableSurface getSurface() {
        return this.surface;
    }

    @Override // androidx.camera.core.impl.SessionConfig.OutputConfig
    public int getSurfaceGroupId() {
        return this.surfaceGroupId;
    }

    public int hashCode() {
        int r0 = (((this.surface.hashCode() ^ 1000003) * 1000003) ^ this.sharedSurfaces.hashCode()) * 1000003;
        String r2 = this.physicalCameraId;
        if (r2 != null) goto L5;
        int r22 = 0;
    L6:
        int r02 = (((((r0 ^ r22) * 1000003) ^ this.mirrorMode) * 1000003) ^ this.surfaceGroupId) * 1000003;
        return this.dynamicRange.hashCode() ^ r02;
    L5:
        r22 = r2.hashCode();
        goto L6
    }

    public String toString() {
        return "OutputConfig{surface=" + this.surface + ", sharedSurfaces=" + this.sharedSurfaces + ", physicalCameraId=" + this.physicalCameraId + ", mirrorMode=" + this.mirrorMode + ", surfaceGroupId=" + this.surfaceGroupId + ", dynamicRange=" + this.dynamicRange + "}";
    }
}
