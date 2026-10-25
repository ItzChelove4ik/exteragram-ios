package androidx.webkit.internal;

import java.util.HashSet;
import java.util.Set;

/* loaded from: classes4.dex */
public abstract class StartupApiFeature {
    private static final Set<StartupApiFeature> sValues = null;
    private final String mInternalFeatureValue;
    private final String mPublicFeatureValue;

    public static class NoFramework extends StartupApiFeature {
        public NoFramework(String r1, String r2) {
            super(r1, r2);
        }
    }

    public static class P extends StartupApiFeature {
        public P(String r1, String r2) {
            super(r1, r2);
        }
    }

    static {
        sValues = new HashSet();
    }

    public StartupApiFeature(String r1, String r2) {
        this.mPublicFeatureValue = r1;
        this.mInternalFeatureValue = r2;
        sValues.add(this);
    }
}
