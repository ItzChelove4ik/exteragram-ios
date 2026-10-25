package androidx.camera.core.processing.util;

import androidx.camera.core.processing.util.GraphicDeviceInfo;
import c.f$$ExternalSyntheticBUOutline2;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
final class AutoValue_GraphicDeviceInfo extends GraphicDeviceInfo {
    private final String eglExtensions;
    private final String eglVersion;
    private final String glExtensions;
    private final String glVersion;

    /* renamed from: androidx.camera.core.processing.util.AutoValue_GraphicDeviceInfo$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder extends GraphicDeviceInfo.Builder {
        private String eglExtensions;
        private String eglVersion;
        private String glExtensions;
        private String glVersion;

        public Builder() {
        }

        @Override // androidx.camera.core.processing.util.GraphicDeviceInfo.Builder
        public GraphicDeviceInfo build() {
            if (this.glVersion != null) goto L5;
            String r0 = " glVersion";
        L7:
            if (this.eglVersion != null) goto L10;
            r0 = r0.concat(" eglVersion");
        L10:
            if (this.glExtensions != null) goto L13;
            r0 = r0.concat(" glExtensions");
        L13:
            if (this.eglExtensions != null) goto L16;
            r0 = r0.concat(" eglExtensions");
        L16:
            if (r0.isEmpty() == true) goto L18;
            Segment$$ExternalSyntheticBUOutline1.m("Missing required properties:".concat(r0));
            return null;
        L18:
            return new AutoValue_GraphicDeviceInfo(this.glVersion, this.eglVersion, this.glExtensions, this.eglExtensions, null);
        L5:
            r0 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L7
        }

        @Override // androidx.camera.core.processing.util.GraphicDeviceInfo.Builder
        public GraphicDeviceInfo.Builder setEglExtensions(String r1) {
            if (r1 == null) goto L5;
            this.eglExtensions = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null eglExtensions");
            return null;
        }

        @Override // androidx.camera.core.processing.util.GraphicDeviceInfo.Builder
        public GraphicDeviceInfo.Builder setEglVersion(String r1) {
            if (r1 == null) goto L5;
            this.eglVersion = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null eglVersion");
            return null;
        }

        @Override // androidx.camera.core.processing.util.GraphicDeviceInfo.Builder
        public GraphicDeviceInfo.Builder setGlExtensions(String r1) {
            if (r1 == null) goto L5;
            this.glExtensions = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null glExtensions");
            return null;
        }

        @Override // androidx.camera.core.processing.util.GraphicDeviceInfo.Builder
        public GraphicDeviceInfo.Builder setGlVersion(String r1) {
            if (r1 == null) goto L5;
            this.glVersion = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null glVersion");
            return null;
        }
    }

    private AutoValue_GraphicDeviceInfo(String r1, String r2, String r3, String r4) {
        this.glVersion = r1;
        this.eglVersion = r2;
        this.glExtensions = r3;
        this.eglExtensions = r4;
    }

    public /* synthetic */ AutoValue_GraphicDeviceInfo(String r1, String r2, String r3, String r4, AnonymousClass1 r5) {
        this(r1, r2, r3, r4);
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof GraphicDeviceInfo) == false) goto L16;
        GraphicDeviceInfo r52 = (GraphicDeviceInfo) r5;
        if (this.glVersion.equals(r52.getGlVersion()) == false) goto L16;
        if (this.eglVersion.equals(r52.getEglVersion()) == false) goto L16;
        if (this.glExtensions.equals(r52.getGlExtensions()) == false) goto L16;
        if (this.eglExtensions.equals(r52.getEglExtensions()) == false) goto L16;
        return true;
    L16:
        return false;
    }

    @Override // androidx.camera.core.processing.util.GraphicDeviceInfo
    public String getEglExtensions() {
        return this.eglExtensions;
    }

    @Override // androidx.camera.core.processing.util.GraphicDeviceInfo
    public String getEglVersion() {
        return this.eglVersion;
    }

    @Override // androidx.camera.core.processing.util.GraphicDeviceInfo
    public String getGlExtensions() {
        return this.glExtensions;
    }

    @Override // androidx.camera.core.processing.util.GraphicDeviceInfo
    public String getGlVersion() {
        return this.glVersion;
    }

    public int hashCode() {
        int r0 = (((((this.glVersion.hashCode() ^ 1000003) * 1000003) ^ this.eglVersion.hashCode()) * 1000003) ^ this.glExtensions.hashCode()) * 1000003;
        return this.eglExtensions.hashCode() ^ r0;
    }

    public String toString() {
        return "GraphicDeviceInfo{glVersion=" + this.glVersion + ", eglVersion=" + this.eglVersion + ", glExtensions=" + this.glExtensions + ", eglExtensions=" + this.eglExtensions + "}";
    }
}
