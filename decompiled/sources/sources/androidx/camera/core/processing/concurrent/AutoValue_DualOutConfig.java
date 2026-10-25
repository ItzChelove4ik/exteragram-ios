package androidx.camera.core.processing.concurrent;

import androidx.camera.core.processing.util.OutConfig;
import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes3.dex */
final class AutoValue_DualOutConfig extends DualOutConfig {
    private final OutConfig primaryOutConfig;
    private final OutConfig secondaryOutConfig;

    public AutoValue_DualOutConfig(OutConfig r2, OutConfig r3) {
        if (r2 == null) goto L10;
        this.primaryOutConfig = r2;
        if (r3 == null) goto L8;
        this.secondaryOutConfig = r3;
        return;
    L8:
        f$$ExternalSyntheticBUOutline2.m("Null secondaryOutConfig");
        throw null;
    L10:
        f$$ExternalSyntheticBUOutline2.m("Null primaryOutConfig");
        throw null;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof DualOutConfig) == false) goto L12;
        DualOutConfig r52 = (DualOutConfig) r5;
        if (this.primaryOutConfig.equals(r52.getPrimaryOutConfig()) == false) goto L12;
        if (this.secondaryOutConfig.equals(r52.getSecondaryOutConfig()) == false) goto L12;
        return true;
    L12:
        return false;
    }

    @Override // androidx.camera.core.processing.concurrent.DualOutConfig
    public OutConfig getPrimaryOutConfig() {
        return this.primaryOutConfig;
    }

    @Override // androidx.camera.core.processing.concurrent.DualOutConfig
    public OutConfig getSecondaryOutConfig() {
        return this.secondaryOutConfig;
    }

    public int hashCode() {
        int r0 = (this.primaryOutConfig.hashCode() ^ 1000003) * 1000003;
        return this.secondaryOutConfig.hashCode() ^ r0;
    }

    public String toString() {
        return "DualOutConfig{primaryOutConfig=" + this.primaryOutConfig + ", secondaryOutConfig=" + this.secondaryOutConfig + "}";
    }
}
