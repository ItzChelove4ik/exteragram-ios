package androidx.camera.core;

import android.util.Rational;

/* loaded from: classes3.dex */
public class MeteringPoint {
    private float mNormalizedX;
    private float mNormalizedY;
    private float mSize;
    private Rational mSurfaceAspectRatio;

    public MeteringPoint(float r1, float r2, float r3, Rational r4) {
        this.mNormalizedX = r1;
        this.mNormalizedY = r2;
        this.mSize = r3;
        this.mSurfaceAspectRatio = r4;
    }

    public float getSize() {
        return this.mSize;
    }

    public Rational getSurfaceAspectRatio() {
        return this.mSurfaceAspectRatio;
    }

    public float getX() {
        return this.mNormalizedX;
    }

    public float getY() {
        return this.mNormalizedY;
    }
}
