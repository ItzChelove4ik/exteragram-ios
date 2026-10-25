package org.telegram.ui.Components;

import android.graphics.PointF;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import androidx.core.graphics.PathParser;
import c.f$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public class CubicBezierInterpolator implements Interpolator {
    public static final CubicBezierInterpolator DEFAULT = null;
    public static final CubicBezierInterpolator EASE_BOTH = null;
    public static final CubicBezierInterpolator EASE_IN = null;
    public static final CubicBezierInterpolator EASE_OUT = null;
    public static final CubicBezierInterpolator EASE_OUT_BACK = null;
    public static final CubicBezierInterpolator EASE_OUT_QUINT = null;
    public static final Interpolator Emphasized = null;
    public static final Interpolator EmphasizedAccelerate = null;
    public static final Interpolator EmphasizedDecelerate = null;
    public static final Interpolator Standard = null;
    public static final Interpolator StandardDecelerate = null;

    /* renamed from: a, reason: collision with root package name */
    protected PointF f506a;

    /* renamed from: b, reason: collision with root package name */
    protected PointF f507b;

    /* renamed from: c, reason: collision with root package name */
    protected PointF f508c;
    protected PointF end;
    protected PointF start;

    static {
        DEFAULT = new CubicBezierInterpolator(0.25d, 0.1d, 0.25d, 1.0d);
        EASE_OUT = new CubicBezierInterpolator(0.0d, 0.0d, 0.58d, 1.0d);
        EASE_OUT_QUINT = new CubicBezierInterpolator(0.23d, 1.0d, 0.32d, 1.0d);
        EASE_IN = new CubicBezierInterpolator(0.42d, 0.0d, 1.0d, 1.0d);
        EASE_BOTH = new CubicBezierInterpolator(0.42d, 0.0d, 0.58d, 1.0d);
        EASE_OUT_BACK = new CubicBezierInterpolator(0.34d, 1.56d, 0.64d, 1.0d);
        Emphasized = new PathInterpolator(PathParser.createPathFromPathData("M 0,0 C 0.05, 0, 0.133333, 0.06, 0.166666, 0.4 C 0.208333, 0.82, 0.25, 1, 1, 1"));
        EmphasizedDecelerate = new PathInterpolator(0.05f, 0.7f, 0.1f, 1.0f);
        EmphasizedAccelerate = new PathInterpolator(0.3f, 0.0f, 0.8f, 0.15f);
        Standard = new PathInterpolator(0.2f, 0.0f, 0.0f, 1.0f);
        StandardDecelerate = new PathInterpolator(0.0f, 0.0f, 0.0f, 1.0f);
    }

    public CubicBezierInterpolator(double r1, double r3, double r5, double r7) {
        this((float) r1, (float) r3, (float) r5, (float) r7);
    }

    public CubicBezierInterpolator(float r2, float r3, float r4, float r5) {
        this(new PointF(r2, r3), new PointF(r4, r5));
    }

    public CubicBezierInterpolator(PointF r5, PointF r6) {
        this.f506a = new PointF();
        this.f507b = new PointF();
        this.f508c = new PointF();
        float r0 = r5.x;
        if (r0 >= 0.0f) goto L5;
    L14:
        f$$ExternalSyntheticBUOutline1.m("startX value must be in the range [0, 1]");
        throw null;
    L5:
        if (r0 > 1.0f) goto L14;
        float r02 = r6.x;
        if (r02 >= 0.0f) goto L9;
    L12:
        f$$ExternalSyntheticBUOutline1.m("endX value must be in the range [0, 1]");
        throw null;
    L9:
        if (r02 > 1.0f) goto L12;
        this.start = r5;
        this.end = r6;
    }

    private float getBezierCoordinateX(float r7) {
        PointF r0 = this.f508c;
        PointF r1 = this.start;
        float r2 = r1.x * 3.0f;
        r0.x = r2;
        PointF r4 = this.f507b;
        float r5 = ((this.end.x - r1.x) * 3.0f) - r2;
        r4.x = r5;
        PointF r6 = this.f506a;
        float r12 = (1.0f - r0.x) - r5;
        r6.x = r12;
        return r7 * (r0.x + ((r4.x + (r12 * r7)) * r7));
    }

    private float getXDerivate(float r4) {
        return this.f508c.x + (r4 * ((this.f507b.x * 2.0f) + ((this.f506a.x * 3.0f) * r4)));
    }

    public float getBezierCoordinateY(float r7) {
        PointF r0 = this.f508c;
        PointF r1 = this.start;
        float r2 = r1.y * 3.0f;
        r0.y = r2;
        PointF r4 = this.f507b;
        float r5 = ((this.end.y - r1.y) * 3.0f) - r2;
        r4.y = r5;
        PointF r6 = this.f506a;
        float r12 = (1.0f - r0.y) - r5;
        r6.y = r12;
        return r7 * (r0.y + ((r4.y + (r12 * r7)) * r7));
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float r1) {
        return getBezierCoordinateY(getXForTime(r1));
    }

    public float getXForTime(float r8) {
        int r0 = 1;
        float r1 = r8;
    L4:
        if (r0 >= 14) goto L9;
        float r2 = getBezierCoordinateX(r1) - r8;
        if (Math.abs(r2) < 0.001d) goto L9;
        r1 = r1 - (r2 / getXDerivate(r1));
        r0 = r0 + 1;
    L9:
        return r1;
    }
}
