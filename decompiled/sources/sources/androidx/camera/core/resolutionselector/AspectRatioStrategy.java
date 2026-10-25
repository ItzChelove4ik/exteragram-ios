package androidx.camera.core.resolutionselector;

/* loaded from: classes3.dex */
public final class AspectRatioStrategy {
    public static final AspectRatioStrategy RATIO_16_9_FALLBACK_AUTO_STRATEGY = null;
    public static final AspectRatioStrategy RATIO_4_3_FALLBACK_AUTO_STRATEGY = null;
    private final int mFallbackRule;
    private final int mPreferredAspectRatio;

    static {
        RATIO_4_3_FALLBACK_AUTO_STRATEGY = new AspectRatioStrategy(0, 1);
        RATIO_16_9_FALLBACK_AUTO_STRATEGY = new AspectRatioStrategy(1, 1);
    }

    public AspectRatioStrategy(int r1, int r2) {
        this.mPreferredAspectRatio = r1;
        this.mFallbackRule = r2;
    }

    public int getFallbackRule() {
        return this.mFallbackRule;
    }

    public int getPreferredAspectRatio() {
        return this.mPreferredAspectRatio;
    }
}
