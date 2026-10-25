package androidx.camera.core;

import androidx.core.util.Pair;

/* loaded from: classes3.dex */
public class CompositionSettings {
    public static final CompositionSettings DEFAULT = null;
    private final float mAlpha;
    private final Pair<Float, Float> mOffset;
    private final Pair<Float, Float> mScale;

    /* renamed from: androidx.camera.core.CompositionSettings$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder {
        private float mAlpha;
        private Pair<Float, Float> mOffset;
        private Pair<Float, Float> mScale;

        public Builder() {
            Float r1 = Float.valueOf(1.0f);
            this.mAlpha = 1.0f;
            Float r0 = Float.valueOf(0.0f);
            this.mOffset = Pair.create(r0, r0);
            this.mScale = Pair.create(r1, r1);
        }

        public CompositionSettings build() {
            return new CompositionSettings(this.mAlpha, this.mOffset, this.mScale, null);
        }

        public Builder setAlpha(float r1) {
            this.mAlpha = r1;
            return this;
        }

        public Builder setOffset(float r1, float r2) {
            this.mOffset = Pair.create(Float.valueOf(r1), Float.valueOf(r2));
            return this;
        }

        public Builder setScale(float r1, float r2) {
            this.mScale = Pair.create(Float.valueOf(r1), Float.valueOf(r2));
            return this;
        }
    }

    static {
        DEFAULT = new Builder().setAlpha(1.0f).setOffset(0.0f, 0.0f).setScale(1.0f, 1.0f).build();
    }

    private CompositionSettings(float r1, Pair<Float, Float> r2, Pair<Float, Float> r3) {
        this.mAlpha = r1;
        this.mOffset = r2;
        this.mScale = r3;
    }

    public /* synthetic */ CompositionSettings(float r1, Pair r2, Pair r3, AnonymousClass1 r4) {
        this(r1, r2, r3);
    }

    public float getAlpha() {
        return this.mAlpha;
    }

    public Pair<Float, Float> getOffset() {
        return this.mOffset;
    }

    public Pair<Float, Float> getScale() {
        return this.mScale;
    }
}
