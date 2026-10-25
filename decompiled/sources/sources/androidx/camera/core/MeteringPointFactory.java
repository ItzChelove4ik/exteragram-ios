package androidx.camera.core;

import android.graphics.PointF;
import android.util.Rational;

/* loaded from: classes3.dex */
public abstract class MeteringPointFactory {
    private Rational mSurfaceAspectRatio;

    public MeteringPointFactory() {
        this(null);
    }

    public MeteringPointFactory(Rational r1) {
        this.mSurfaceAspectRatio = r1;
    }

    public static float getDefaultPointSize() {
        return 0.15f;
    }

    public abstract PointF convertPoint(float r1, float r2);

    public final MeteringPoint createPoint(float r2, float r3) {
        return createPoint(r2, r3, getDefaultPointSize());
    }

    public final MeteringPoint createPoint(float r2, float r3, float r4) {
        PointF r22 = convertPoint(r2, r3);
        return new MeteringPoint(r22.x, r22.y, r4, this.mSurfaceAspectRatio);
    }
}
