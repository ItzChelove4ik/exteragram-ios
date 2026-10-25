package androidx.graphics.shapes;

import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\f\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\f\u0010\rR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u000e\u001a\u0004\b\u0011\u0010\u0010¨\u0006\u0012"}, d2 = {"Landroidx/graphics/shapes/AngleMeasurer;", "Landroidx/graphics/shapes/Measurer;", _UrlKt.FRAGMENT_ENCODE_SET, "centerX", "centerY", "<init>", "(FF)V", "Landroidx/graphics/shapes/Cubic;", "c", "measureCubic", "(Landroidx/graphics/shapes/Cubic;)F", "m", "findCubicCutPoint", "(Landroidx/graphics/shapes/Cubic;F)F", "F", "getCenterX", "()F", "getCenterY", "graphics-shapes_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AngleMeasurer implements Measurer {
    private final float centerX;
    private final float centerY;

    /* renamed from: $r8$lambda$Bzv6ODwqQqmceGWR-zm_cGw1Ksw, reason: not valid java name */
    public static float m727$r8$lambda$Bzv6ODwqQqmceGWRzm_cGw1Ksw(Cubic r2, AngleMeasurer r3, float r4, float r5, float r6) {
        long r0 = r2.m728pointOnCurveOOQOV4g$graphics_shapes_release(r6);
        return Math.abs(Utils.positiveModulo(Utils.angle(PointKt.m735getXDnnuFBc(r0) - r3.centerX, PointKt.m736getYDnnuFBc(r0) - r3.centerY) - r4, Utils.getTwoPi()) - r5);
    }

    public AngleMeasurer(float r1, float r2) {
        this.centerX = r1;
        this.centerY = r2;
    }

    @Override // androidx.graphics.shapes.Measurer
    public float findCubicCutPoint(Cubic r4, float r5) {
        return Utils.findMinimum(0.0f, 1.0f, 1.0E-5f, new AngleMeasurer$$ExternalSyntheticLambda0(r4, this, Utils.angle(r4.getAnchor0X() - this.centerX, r4.getAnchor0Y() - this.centerY), r5));
    }

    @Override // androidx.graphics.shapes.Measurer
    public float measureCubic(Cubic r4) {
        float r3 = Utils.positiveModulo(Utils.angle(r4.getAnchor1X() - this.centerX, r4.getAnchor1Y() - this.centerY) - Utils.angle(r4.getAnchor0X() - this.centerX, r4.getAnchor0Y() - this.centerY), Utils.getTwoPi());
        if (r3 <= (Utils.getTwoPi() - 1.0E-4f)) goto L6;
        return 0.0f;
    L6:
        return r3;
    }
}
