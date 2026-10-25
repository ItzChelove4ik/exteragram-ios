package androidx.graphics.shapes;

import androidx.collection.FloatFloatPair;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0018\b\u0016\u0018\u0000 E2\u00020\u0001:\u0001EB\u0013\b\u0000\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005B9\b\u0010\u0012\n\u0010\b\u001a\u00060\u0006j\u0002`\u0007\u0012\n\u0010\t\u001a\u00060\u0006j\u0002`\u0007\u0012\n\u0010\n\u001a\u00060\u0006j\u0002`\u0007\u0012\n\u0010\u000b\u001a\u00060\u0006j\u0002`\u0007¢\u0006\u0004\b\u0004\u0010\fJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J!\u0010\u0015\u001a\u00060\u0006j\u0002`\u00072\u0006\u0010\u0012\u001a\u00020\rH\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0018\u001a\u00020\u000fH\u0000¢\u0006\u0004\b\u0016\u0010\u0017J#\u0010\u001e\u001a\u00020\u001b2\b\b\u0002\u0010\u0019\u001a\u00020\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u000fH\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ!\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000\u001f2\u0006\u0010\u0012\u001a\u00020\r¢\u0006\u0004\b \u0010!J\r\u0010\"\u001a\u00020\u0000¢\u0006\u0004\b\"\u0010#J\u000f\u0010%\u001a\u00020$H\u0016¢\u0006\u0004\b%\u0010&J\u001a\u0010(\u001a\u00020\u000f2\b\u0010'\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b(\u0010)J\u0015\u0010,\u001a\u00020\u00002\u0006\u0010+\u001a\u00020*¢\u0006\u0004\b,\u0010-J\u000f\u0010/\u001a\u00020.H\u0016¢\u0006\u0004\b/\u00100R\u001a\u0010\u0003\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0003\u00101\u001a\u0004\b2\u00103R\u0011\u00106\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b4\u00105R\u0011\u00108\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b7\u00105R\u0011\u0010:\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b9\u00105R\u0011\u0010<\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b;\u00105R\u0011\u0010>\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b=\u00105R\u0011\u0010@\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b?\u00105R\u0011\u0010B\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\bA\u00105R\u0011\u0010D\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\bC\u00105\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006F"}, d2 = {"Landroidx/graphics/shapes/Cubic;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "points", "<init>", "([F)V", "Landroidx/collection/FloatFloatPair;", "Landroidx/graphics/shapes/Point;", "anchor0", "control0", "control1", "anchor1", "(JJJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", _UrlKt.FRAGMENT_ENCODE_SET, "value", _UrlKt.FRAGMENT_ENCODE_SET, "zeroIsh", "(F)Z", "t", "pointOnCurve-OOQOV4g$graphics_shapes_release", "(F)J", "pointOnCurve", "zeroLength$graphics_shapes_release", "()Z", "zeroLength", "bounds", "approximate", _UrlKt.FRAGMENT_ENCODE_SET, "calculateBounds$graphics_shapes_release", "([FZ)V", "calculateBounds", "Lkotlin/Pair;", "split", "(F)Lkotlin/Pair;", "reverse", "()Landroidx/graphics/shapes/Cubic;", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "other", "equals", "(Ljava/lang/Object;)Z", "Landroidx/graphics/shapes/PointTransformer;", "f", "transformed", "(Landroidx/graphics/shapes/PointTransformer;)Landroidx/graphics/shapes/Cubic;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "[F", "getPoints$graphics_shapes_release", "()[F", "getAnchor0X", "()F", "anchor0X", "getAnchor0Y", "anchor0Y", "getControl0X", "control0X", "getControl0Y", "control0Y", "getControl1X", "control1X", "getControl1Y", "control1Y", "getAnchor1X", "anchor1X", "getAnchor1Y", "anchor1Y", "Companion", "graphics-shapes_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCubic.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Cubic.kt\nandroidx/graphics/shapes/Cubic\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,448:1\n1#2:449\n*E\n"})
/* loaded from: classes3.dex */
public class Cubic {
    public static final Companion Companion = null;
    private final float[] points;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J8\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0007J(\u0010\f\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0007¨\u0006\r"}, d2 = {"Landroidx/graphics/shapes/Cubic$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "()V", "circularArc", "Landroidx/graphics/shapes/Cubic;", "centerX", _UrlKt.FRAGMENT_ENCODE_SET, "centerY", "x0", "y0", "x1", "y1", "straightLine", "graphics-shapes_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final Cubic circularArc(float r18, float r19, float r20, float r21, float r22, float r23) {
            float r2 = r20 - r18;
            float r3 = r21 - r19;
            long r4 = Utils.directionVector(r2, r3);
            float r8 = r22 - r18;
            float r9 = r23 - r19;
            long r10 = Utils.directionVector(r8, r9);
            long r12 = Utils.m748rotate90DnnuFBc(r4);
            long r14 = Utils.m748rotate90DnnuFBc(r10);
            if (PointKt.m731dotProduct5P9i7ZU(r12, r8, r9) < 0.0f) goto L5;
            boolean r82 = true;
        L6:
            float r42 = PointKt.m732dotProductybeJwSQ(r4, r10);
            if (r42 > 0.999f) goto L9;
            float r24 = (Utils.distance(r2, r3) * 4.0f) / 3.0f;
            float r32 = 1.0f;
            float r25 = (r24 * (((float) Math.sqrt(2.0f * r5)) - ((float) Math.sqrt(1.0f - (r42 * r42))))) / (1.0f - r42);
            if (r82 == true) goto L14;
            r32 = -1.0f;
        L14:
            float r26 = r25 * r32;
            return CubicKt.Cubic(r20, r21, (PointKt.m735getXDnnuFBc(r12) * r26) + r20, (PointKt.m736getYDnnuFBc(r12) * r26) + r21, r22 - (PointKt.m735getXDnnuFBc(r14) * r26), r23 - (PointKt.m736getYDnnuFBc(r14) * r26), r22, r23);
        L9:
            return straightLine(r20, r21, r22, r23);
        L5:
            r82 = false;
            goto L6
        }

        @JvmStatic
        public final Cubic straightLine(float r9, float r10, float r11, float r12) {
            return CubicKt.Cubic(r9, r10, Utils.interpolate(r9, r11, 0.33333334f), Utils.interpolate(r10, r12, 0.33333334f), Utils.interpolate(r9, r11, 0.6666667f), Utils.interpolate(r10, r12, 0.6666667f), r11, r12);
        }
    }

    static {
        Companion = new Companion(null);
    }

    private Cubic(long r3, long r5, long r7, long r9) {
        this(new float[]{PointKt.m735getXDnnuFBc(r3), PointKt.m736getYDnnuFBc(r3), PointKt.m735getXDnnuFBc(r5), PointKt.m736getYDnnuFBc(r5), PointKt.m735getXDnnuFBc(r7), PointKt.m736getYDnnuFBc(r7), PointKt.m735getXDnnuFBc(r9), PointKt.m736getYDnnuFBc(r9)});
    }

    public /* synthetic */ Cubic(long r1, long r3, long r5, long r7, DefaultConstructorMarker r9) {
        this(r1, r3, r5, r7);
    }

    public Cubic(float[] r1) {
        this.points = r1;
        if (r1.length != 8) goto L5;
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m("Points array size should be 8");
        throw null;
    }

    public /* synthetic */ Cubic(float[] r1, int r2, DefaultConstructorMarker r3) {
        if ((r2 & 1) == 0) goto L5;
        r1 = new float[8];
    L5:
        this(r1);
    }

    private final boolean zeroIsh(float r1) {
        if (Math.abs(r1) >= 1.0E-4f) goto L6;
        return true;
    L6:
        return false;
    }

    public final void calculateBounds$graphics_shapes_release(float[] r22, boolean r23) {
        if (zeroLength$graphics_shapes_release() == false) goto L6;
        r22[0] = getAnchor0X();
        r22[1] = getAnchor0Y();
        r22[2] = getAnchor0X();
        r22[3] = getAnchor0Y();
        return;
    L6:
        float r1 = Math.min(getAnchor0X(), getAnchor1X());
        float r6 = Math.min(getAnchor0Y(), getAnchor1Y());
        float r7 = Math.max(getAnchor0X(), getAnchor1X());
        float r8 = Math.max(getAnchor0Y(), getAnchor1Y());
        if (r23 == false) goto L10;
        r22[0] = Math.min(r1, Math.min(getControl0X(), getControl1X()));
        r22[1] = Math.min(r6, Math.min(getControl0Y(), getControl1Y()));
        r22[2] = Math.max(r7, Math.max(getControl0X(), getControl1X()));
        r22[3] = Math.max(r8, Math.max(getControl0Y(), getControl1Y()));
        return;
    L10:
        float r9 = (((-getAnchor0X()) + (getControl0X() * 3.0f)) - (getControl1X() * 3.0f)) + getAnchor1X();
        float r10 = ((getAnchor0X() * 2.0f) - (getControl0X() * 4.0f)) + (getControl1X() * 2.0f);
        float r13 = (-getAnchor0X()) + getControl0X();
        if (zeroIsh(r9) == true) goto L13;
        float r15 = (r10 * r10) - ((4.0f * r9) * r13);
        if (r15 >= 0.0f) goto L28;
    L25:
        char r132 = 3;
        char r19 = 2;
        char r20 = 1;
        char r152 = 0;
    L48:
        float r2 = (((-getAnchor0Y()) + (getControl0Y() * 3.0f)) - (3.0f * getControl1Y())) + getAnchor1Y();
        float r3 = ((getAnchor0Y() * 2.0f) - (getControl0Y() * 4.0f)) + (getControl1Y() * 2.0f);
        float r4 = (-getAnchor0Y()) + getControl0Y();
        if (zeroIsh(r2) == true) goto L51;
        float r5 = (r3 * r3) - ((4.0f * r2) * r4);
        if (r5 < 0.0f) goto L85;
        float r32 = -r3;
        double r42 = r5;
        float r12 = 2.0f * r2;
        float r92 = (((float) Math.sqrt(r42)) + r32) / r12;
        if (0.0f <= r92) goto L68;
    L75:
        float r33 = (r32 - ((float) Math.sqrt(r42))) / r12;
        if (0.0f > r33) goto L85;
        if (r33 > 1.0f) goto L85;
        float r0 = PointKt.m736getYDnnuFBc(m728pointOnCurveOOQOV4g$graphics_shapes_release(r33));
        if (r0 >= r6) goto L83;
        r6 = r0;
    L83:
        if (r0 <= r8) goto L85;
    L62:
        r8 = r0;
        goto L85
    L68:
        if (r92 > 1.0f) goto L75;
        float r24 = PointKt.m736getYDnnuFBc(m728pointOnCurveOOQOV4g$graphics_shapes_release(r92));
        if (r24 >= r6) goto L73;
        r6 = r24;
    L73:
        if (r24 <= r8) goto L75;
        r8 = r24;
    L85:
        r22[r152] = r1;
        r22[r20] = r6;
        r22[r19] = r7;
        r22[r132] = r8;
        return;
    L51:
        if (r3 == 0.0f) goto L85;
        float r122 = (2.0f * r4) / ((-2.0f) * r3);
        if (0.0f > r122) goto L85;
        if (r122 > 1.0f) goto L85;
        r0 = PointKt.m736getYDnnuFBc(m728pointOnCurveOOQOV4g$graphics_shapes_release(r122));
        if (r0 >= r6) goto L61;
        r6 = r0;
    L61:
        if (r0 <= r8) goto L85;
    L28:
        float r102 = -r10;
        r132 = 3;
        r19 = 2;
        double r25 = r15;
        r20 = 1;
        r152 = 0;
        float r93 = r9 * 2.0f;
        float r43 = (((float) Math.sqrt(r25)) + r102) / r93;
        if (0.0f <= r43) goto L31;
    L38:
        float r103 = (r102 - ((float) Math.sqrt(r25))) / r93;
        if (0.0f > r103) goto L48;
        if (r103 > 1.0f) goto L48;
        float r26 = PointKt.m735getXDnnuFBc(m728pointOnCurveOOQOV4g$graphics_shapes_release(r103));
        if (r26 >= r1) goto L46;
        r1 = r26;
    L46:
        if (r26 <= r7) goto L48;
        r7 = r26;
        goto L48
    L31:
        if (r43 > 1.0f) goto L38;
        float r44 = PointKt.m735getXDnnuFBc(m728pointOnCurveOOQOV4g$graphics_shapes_release(r43));
        if (r44 >= r1) goto L36;
        r1 = r44;
    L36:
        if (r44 <= r7) goto L38;
        r7 = r44;
        goto L38
    L13:
        if (r10 == 0.0f) goto L25;
        float r133 = (r13 * 2.0f) / (r10 * (-2.0f));
        if (0.0f > r133) goto L25;
        if (r133 > 1.0f) goto L25;
        float r94 = PointKt.m735getXDnnuFBc(m728pointOnCurveOOQOV4g$graphics_shapes_release(r133));
        if (r94 >= r1) goto L23;
        r1 = r94;
    L23:
        if (r94 <= r7) goto L25;
        r7 = r94;
        goto L25
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof Cubic) == true) goto L10;
        return false;
    L10:
        return Arrays.equals(this.points, ((Cubic) r2).points);
    }

    public final float getAnchor0X() {
        return this.points[0];
    }

    public final float getAnchor0Y() {
        return this.points[1];
    }

    public final float getAnchor1X() {
        return this.points[6];
    }

    public final float getAnchor1Y() {
        return this.points[7];
    }

    public final float getControl0X() {
        return this.points[2];
    }

    public final float getControl0Y() {
        return this.points[3];
    }

    public final float getControl1X() {
        return this.points[4];
    }

    public final float getControl1Y() {
        return this.points[5];
    }

    public final float[] getPoints$graphics_shapes_release() {
        return this.points;
    }

    public int hashCode() {
        return Arrays.hashCode(this.points);
    }

    /* renamed from: pointOnCurve-OOQOV4g$graphics_shapes_release, reason: not valid java name */
    public final long m728pointOnCurveOOQOV4g$graphics_shapes_release(float r7) {
        float r0 = 1.0f - r7;
        float r2 = (r0 * r0) * r0;
        float r4 = 3.0f * r7;
        float r5 = (r4 * r0) * r0;
        float r42 = (r4 * r7) * r0;
        float r1 = ((getAnchor0X() * r2) + (getControl0X() * r5)) + (getControl1X() * r42);
        float r3 = (r7 * r7) * r7;
        return FloatFloatPair.m659constructorimpl(r1 + (getAnchor1X() * r3), (((getAnchor0Y() * r2) + (getControl0Y() * r5)) + (getControl1Y() * r42)) + (getAnchor1Y() * r3));
    }

    public final Cubic reverse() {
        return CubicKt.Cubic(getAnchor1X(), getAnchor1Y(), getControl1X(), getControl1Y(), getControl0X(), getControl0Y(), getAnchor0X(), getAnchor0Y());
    }

    public final Pair<Cubic, Cubic> split(float r23) {
        float r0 = 1.0f - r23;
        long r1 = m728pointOnCurveOOQOV4g$graphics_shapes_release(r23);
        float r11 = r0 * r0;
        float r12 = (2.0f * r0) * r23;
        float r13 = r23 * r23;
        return TuplesKt.to(CubicKt.Cubic(getAnchor0X(), getAnchor0Y(), (getAnchor0X() * r0) + (getControl0X() * r23), (getAnchor0Y() * r0) + (getControl0Y() * r23), ((getAnchor0X() * r11) + (getControl0X() * r12)) + (getControl1X() * r13), ((getAnchor0Y() * r11) + (getControl0Y() * r12)) + (getControl1Y() * r13), PointKt.m735getXDnnuFBc(r1), PointKt.m736getYDnnuFBc(r1)), CubicKt.Cubic(PointKt.m735getXDnnuFBc(r1), PointKt.m736getYDnnuFBc(r1), ((getControl0X() * r11) + (getControl1X() * r12)) + (getAnchor1X() * r13), ((getControl0Y() * r11) + (getControl1Y() * r12)) + (getAnchor1Y() * r13), (getControl1X() * r0) + (getAnchor1X() * r23), (getControl1Y() * r0) + (getAnchor1Y() * r23), getAnchor1X(), getAnchor1Y()));
    }

    public String toString() {
        return "anchor0: (" + getAnchor0X() + ", " + getAnchor0Y() + ") control0: (" + getControl0X() + ", " + getControl0Y() + "), control1: (" + getControl1X() + ", " + getControl1Y() + "), anchor1: (" + getAnchor1X() + ", " + getAnchor1Y() + ')';
    }

    public final Cubic transformed(PointTransformer r9) {
        MutableCubic r0 = new MutableCubic();
        ArraysKt.copyInto$default(this.points, r0.getPoints$graphics_shapes_release(), 0, 0, 0, 14, null);
        r0.transform(r9);
        return r0;
    }

    public final boolean zeroLength$graphics_shapes_release() {
        if (Math.abs(getAnchor0X() - getAnchor1X()) < 1.0E-4f) goto L5;
        return false;
    L5:
        if (Math.abs(getAnchor0Y() - getAnchor1Y()) >= 1.0E-4f) goto L10;
        return true;
    L10:
        return false;
    }
}
