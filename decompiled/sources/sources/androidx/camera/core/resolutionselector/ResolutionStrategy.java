package androidx.camera.core.resolutionselector;

import android.util.Size;

/* loaded from: classes3.dex */
public final class ResolutionStrategy {
    public static final ResolutionStrategy HIGHEST_AVAILABLE_STRATEGY = null;
    private Size mBoundSize;
    private int mFallbackRule;

    static {
        HIGHEST_AVAILABLE_STRATEGY = new ResolutionStrategy();
    }

    private ResolutionStrategy() {
        this.mBoundSize = null;
        this.mFallbackRule = 0;
    }

    public Size getBoundSize() {
        return this.mBoundSize;
    }

    public int getFallbackRule() {
        return this.mFallbackRule;
    }
}
