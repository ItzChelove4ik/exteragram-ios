package androidx.graphics.shapes;

import androidx.collection.FloatFloatPair;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\b \b\u0002\u0018\u00002\u00020\u0001B7\u0012\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u0003\u0012\n\u0010\u0005\u001a\u00060\u0002j\u0002`\u0003\u0012\n\u0010\u0006\u001a\u00060\u0002j\u0002`\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\r\u0010\u000eJf\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000b2\n\u0010\u0011\u001a\u00060\u0002j\u0002`\u00032\n\u0010\u0012\u001a\u00060\u0002j\u0002`\u00032\n\u0010\u0013\u001a\u00060\u0002j\u0002`\u00032\n\u0010\u0014\u001a\u00060\u0002j\u0002`\u00032\n\u0010\u0015\u001a\u00060\u0002j\u0002`\u00032\u0006\u0010\u0016\u001a\u00020\u000bH\u0002ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019JJ\u0010\u001f\u001a\n\u0018\u00010\u0002j\u0004\u0018\u0001`\u00032\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u00032\n\u0010\u001b\u001a\u00060\u0002j\u0002`\u00032\n\u0010\u0005\u001a\u00060\u0002j\u0002`\u00032\n\u0010\u001c\u001a\u00060\u0002j\u0002`\u0003H\u0002ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ'\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00170\"2\u0006\u0010 \u001a\u00020\u000b2\b\b\u0002\u0010!\u001a\u00020\u000bH\u0007¢\u0006\u0004\b#\u0010$R!\u0010\u0004\u001a\u00060\u0002j\u0002`\u00038\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0004\u0010%\u001a\u0004\b&\u0010'R!\u0010\u0005\u001a\u00060\u0002j\u0002`\u00038\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0005\u0010%\u001a\u0004\b(\u0010'R!\u0010\u0006\u001a\u00060\u0002j\u0002`\u00038\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0006\u0010%\u001a\u0004\b)\u0010'R\u0019\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010*\u001a\u0004\b+\u0010,R!\u0010\u001c\u001a\u00060\u0002j\u0002`\u00038\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u001c\u0010%\u001a\u0004\b-\u0010'R!\u0010.\u001a\u00060\u0002j\u0002`\u00038\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b.\u0010%\u001a\u0004\b/\u0010'R\u0017\u00100\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b0\u00101\u001a\u0004\b2\u00103R\u0017\u00104\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b4\u00101\u001a\u0004\b5\u00103R\u0017\u00106\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b6\u00101\u001a\u0004\b7\u00103R\u0017\u00108\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b8\u00101\u001a\u0004\b9\u00103R\u0017\u0010:\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b:\u00101\u001a\u0004\b;\u00103R,\u0010<\u001a\u00060\u0002j\u0002`\u00038\u0006@\u0006X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b<\u0010%\u001a\u0004\b=\u0010'\"\u0004\b>\u0010?R\u0011\u0010A\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b@\u00103\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006B"}, d2 = {"Landroidx/graphics/shapes/RoundedCorner;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/collection/FloatFloatPair;", "Landroidx/graphics/shapes/Point;", "p0", "p1", "p2", "Landroidx/graphics/shapes/CornerRounding;", "rounding", "<init>", "(JJJLandroidx/graphics/shapes/CornerRounding;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", _UrlKt.FRAGMENT_ENCODE_SET, "allowedCut", "calculateActualSmoothingValue", "(F)F", "actualRoundCut", "actualSmoothingValues", "corner", "sideStart", "circleSegmentIntersection", "otherCircleSegmentIntersection", "circleCenter", "actualR", "Landroidx/graphics/shapes/Cubic;", "computeFlankingCurve-oAJzIJU", "(FFJJJJJF)Landroidx/graphics/shapes/Cubic;", "computeFlankingCurve", "d0", "d1", "lineIntersection-CBFvKDc", "(JJJJ)Landroidx/collection/FloatFloatPair;", "lineIntersection", "allowedCut0", "allowedCut1", _UrlKt.FRAGMENT_ENCODE_SET, "getCubics", "(FF)Ljava/util/List;", "J", "getP0-1ufDz9w", "()J", "getP1-1ufDz9w", "getP2-1ufDz9w", "Landroidx/graphics/shapes/CornerRounding;", "getRounding", "()Landroidx/graphics/shapes/CornerRounding;", "getD1-1ufDz9w", "d2", "getD2-1ufDz9w", "cornerRadius", "F", "getCornerRadius", "()F", "smoothing", "getSmoothing", "cosAngle", "getCosAngle", "sinAngle", "getSinAngle", "expectedRoundCut", "getExpectedRoundCut", "center", "getCenter-1ufDz9w", "setCenter-DnnuFBc", "(J)V", "getExpectedCut", "expectedCut", "graphics-shapes_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
final class RoundedCorner {
    private long center;
    private final float cornerRadius;
    private final float cosAngle;
    private final long d1;
    private final long d2;
    private final float expectedRoundCut;
    private final long p0;
    private final long p1;
    private final long p2;
    private final CornerRounding rounding;
    private final float sinAngle;
    private final float smoothing;

    private RoundedCorner(long r5, long r7, long r9, CornerRounding r11) {
        this.p0 = r5;
        this.p1 = r7;
        this.p2 = r9;
        this.rounding = r11;
        long r52 = PointKt.m733getDirectionDnnuFBc(PointKt.m738minusybeJwSQ(r5, r7));
        this.d1 = r52;
        long r72 = PointKt.m733getDirectionDnnuFBc(PointKt.m738minusybeJwSQ(r9, r7));
        this.d2 = r72;
        if (r11 == null) goto L5;
        float r10 = r11.getRadius();
    L6:
        this.cornerRadius = r10;
        if (r11 == null) goto L9;
        float r112 = r11.getSmoothing();
    L10:
        this.smoothing = r112;
        float r53 = PointKt.m732dotProductybeJwSQ(r52, r72);
        this.cosAngle = r53;
        float r6 = (float) Math.sqrt(1.0f - Utils.square(r53));
        this.sinAngle = r6;
        if (r6 <= 0.001d) goto L13;
        float r102 = (r10 * (r53 + 1.0f)) / r6;
    L14:
        this.expectedRoundCut = r102;
        this.center = FloatFloatPair.m659constructorimpl(0.0f, 0.0f);
        return;
    L13:
        r102 = 0.0f;
        goto L14
    L9:
        r112 = 0.0f;
        goto L10
    L5:
        r10 = 0.0f;
        goto L6
    }

    public /* synthetic */ RoundedCorner(long r1, long r3, long r5, CornerRounding r7, DefaultConstructorMarker r8) {
        this(r1, r3, r5, r7);
    }

    private final float calculateActualSmoothingValue(float r3) {
        if (r3 > getExpectedCut()) goto L5;
        float r0 = this.expectedRoundCut;
        if (r3 > r0) goto L9;
        return 0.0f;
    L9:
        return (this.smoothing * (r3 - r0)) / (getExpectedCut() - this.expectedRoundCut);
    L5:
        return this.smoothing;
    }

    /* renamed from: computeFlankingCurve-oAJzIJU, reason: not valid java name */
    private final Cubic m743computeFlankingCurveoAJzIJU(float r18, float r19, long r20, long r22, long r24, long r26, long r28, float r30) {
        long r7 = PointKt.m733getDirectionDnnuFBc(PointKt.m738minusybeJwSQ(r22, r20));
        long r9 = PointKt.m739plusybeJwSQ(r20, PointKt.m740timesso9K2fw(PointKt.m740timesso9K2fw(r7, r18), 1.0f + r19));
        long r12 = r24;
        long r0 = PointKt.m737interpolatedLqxh1s(r12, PointKt.m730divso9K2fw(PointKt.m739plusybeJwSQ(r24, r26), 2.0f), r19);
        long r02 = PointKt.m739plusybeJwSQ(r28, PointKt.m740timesso9K2fw(Utils.directionVector(PointKt.m735getXDnnuFBc(r0) - PointKt.m735getXDnnuFBc(r28), PointKt.m736getYDnnuFBc(r0) - PointKt.m736getYDnnuFBc(r28)), r30));
        FloatFloatPair r03 = m744lineIntersectionCBFvKDc(r22, r7, r02, Utils.m748rotate90DnnuFBc(PointKt.m738minusybeJwSQ(r02, r28)));
        if (r03 == null) goto L6;
        r12 = r03.m664unboximpl();
    L6:
        return new Cubic(r9, PointKt.m730divso9K2fw(PointKt.m739plusybeJwSQ(r9, PointKt.m740timesso9K2fw(r12, 2.0f)), 3.0f), r12, r02, null);
    }

    /* renamed from: lineIntersection-CBFvKDc, reason: not valid java name */
    private final FloatFloatPair m744lineIntersectionCBFvKDc(long r4, long r6, long r8, long r10) {
        long r102 = Utils.m748rotate90DnnuFBc(r10);
        float r3 = PointKt.m732dotProductybeJwSQ(r6, r102);
        if (Math.abs(r3) >= 1.0E-4f) goto L5;
        return null;
    L5:
        float r82 = PointKt.m732dotProductybeJwSQ(PointKt.m738minusybeJwSQ(r8, r4), r102);
        if (Math.abs(r3) >= (Math.abs(r82) * 1.0E-4f)) goto L9;
        return null;
    L9:
        return FloatFloatPair.m658boximpl(PointKt.m739plusybeJwSQ(r4, PointKt.m740timesso9K2fw(r6, r82 / r3)));
    }

    /* renamed from: getCenter-1ufDz9w, reason: not valid java name */
    public final long m745getCenter1ufDz9w() {
        return this.center;
    }

    @JvmOverloads
    public final List<Cubic> getCubics(float r19, float r20) {
        float r1 = Math.min(r19, r20);
        float r2 = this.expectedRoundCut;
        if (r2 >= 1.0E-4f) goto L5;
    L11:
        long r12 = this.p1;
        this.center = r12;
        return CollectionsKt.listOf(Cubic.Companion.straightLine(PointKt.m735getXDnnuFBc(r12), PointKt.m736getYDnnuFBc(this.p1), PointKt.m735getXDnnuFBc(this.p1), PointKt.m736getYDnnuFBc(this.p1)));
    L5:
        if (r1 < 1.0E-4f) goto L11;
        if (this.cornerRadius < 1.0E-4f) goto L11;
        float r13 = Math.min(r1, r2);
        float r22 = calculateActualSmoothingValue(r19);
        float r14 = calculateActualSmoothingValue(r20);
        float r132 = (this.cornerRadius * r13) / this.expectedRoundCut;
        this.center = PointKt.m739plusybeJwSQ(this.p1, PointKt.m740timesso9K2fw(PointKt.m733getDirectionDnnuFBc(PointKt.m730divso9K2fw(PointKt.m739plusybeJwSQ(this.d1, this.d2), 2.0f)), (float) Math.sqrt(Utils.square(r132) + Utils.square(r13))));
        long r7 = PointKt.m739plusybeJwSQ(this.p1, PointKt.m740timesso9K2fw(this.d1, r13));
        long r9 = PointKt.m739plusybeJwSQ(this.p1, PointKt.m740timesso9K2fw(this.d2, r13));
        Cubic r15 = m743computeFlankingCurveoAJzIJU(r13, r22, this.p1, this.p0, r7, r9, this.center, r132);
        Cubic r16 = m743computeFlankingCurveoAJzIJU(r13, r14, this.p1, this.p2, r9, r7, this.center, r132).reverse();
        return CollectionsKt.listOf(new Cubic[]{r15, Cubic.Companion.circularArc(PointKt.m735getXDnnuFBc(this.center), PointKt.m736getYDnnuFBc(this.center), r15.getAnchor1X(), r15.getAnchor1Y(), r16.getAnchor0X(), r16.getAnchor0Y()), r16});
    }

    public final float getExpectedCut() {
        return (1.0f + this.smoothing) * this.expectedRoundCut;
    }

    public final float getExpectedRoundCut() {
        return this.expectedRoundCut;
    }
}
