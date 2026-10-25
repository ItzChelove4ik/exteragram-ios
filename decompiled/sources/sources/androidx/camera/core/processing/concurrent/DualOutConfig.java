package androidx.camera.core.processing.concurrent;

import androidx.camera.core.processing.util.OutConfig;

/* loaded from: classes3.dex */
public abstract class DualOutConfig {
    public DualOutConfig() {
    }

    public static DualOutConfig of(OutConfig r1, OutConfig r2) {
        return new AutoValue_DualOutConfig(r1, r2);
    }

    public abstract OutConfig getPrimaryOutConfig();

    public abstract OutConfig getSecondaryOutConfig();
}
