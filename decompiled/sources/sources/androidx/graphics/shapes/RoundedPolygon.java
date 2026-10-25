package androidx.graphics.shapes;

import androidx.collection.FloatFloatPair;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 )2\u00020\u0001:\u0001)B'\b\u0000\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\f\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0013\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0013\u0010\u0014J#\u0010\u0017\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0016\u001a\u00020\u0015H\u0007¢\u0006\u0004\b\u0017\u0010\u0018J\u001a\u0010\u001a\u001a\u00020\u00152\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001d\u001a\u00020\u001cH\u0016¢\u0006\u0004\b\u001d\u0010\u001eR \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u001f\u001a\u0004\b \u0010!R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\"\u001a\u0004\b#\u0010$R\u0017\u0010\u0007\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0007\u0010\"\u001a\u0004\b%\u0010$R\u001d\u0010'\u001a\b\u0012\u0004\u0012\u00020&0\u00028\u0006¢\u0006\f\n\u0004\b'\u0010\u001f\u001a\u0004\b(\u0010!¨\u0006*"}, d2 = {"Landroidx/graphics/shapes/RoundedPolygon;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/graphics/shapes/Feature;", "features", _UrlKt.FRAGMENT_ENCODE_SET, "centerX", "centerY", "<init>", "(Ljava/util/List;FF)V", "Landroidx/graphics/shapes/PointTransformer;", "f", "transformed", "(Landroidx/graphics/shapes/PointTransformer;)Landroidx/graphics/shapes/RoundedPolygon;", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "bounds", "calculateMaxBounds", "([F)[F", _UrlKt.FRAGMENT_ENCODE_SET, "approximate", "calculateBounds", "([FZ)[F", "other", "equals", "(Ljava/lang/Object;)Z", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "Ljava/util/List;", "getFeatures$graphics_shapes_release", "()Ljava/util/List;", "F", "getCenterX", "()F", "getCenterY", "Landroidx/graphics/shapes/Cubic;", "cubics", "getCubics", "Companion", "graphics-shapes_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRoundedPolygon.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RoundedPolygon.kt\nandroidx/graphics/shapes/RoundedPolygon\n+ 2 Utils.kt\nandroidx/graphics/shapes/Utils\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,686:1\n108#2,4:687\n108#2,4:691\n108#2,4:695\n1#3:699\n*S KotlinDebug\n*F\n+ 1 RoundedPolygon.kt\nandroidx/graphics/shapes/RoundedPolygon\n*L\n93#1:687,4\n96#1:691,4\n101#1:695,4\n*E\n"})
/* loaded from: classes3.dex */
public final class RoundedPolygon {
    public static final Companion Companion = null;
    private final float centerX;
    private final float centerY;
    private final List<Cubic> cubics;
    private final List<Feature> features;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Landroidx/graphics/shapes/RoundedPolygon$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "()V", "graphics-shapes_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RoundedPolygon(List<? extends Feature> r20, float r21, float r22) {
        this.features = r20;
        this.centerX = r21;
        this.centerY = r22;
        List r2 = CollectionsKt.createListBuilder();
        int r5 = 0;
        if (r20.size() > 0) goto L5;
    L7:
        List<Cubic> r3 = null;
        List<Cubic> r7 = null;
    L8:
        int r1 = r20.size();
        if (r1 < 0) goto L34;
        int r8 = 0;
        Cubic r9 = null;
        Cubic r10 = null;
    L11:
        if (r8 != 0) goto L15;
        if (r3 == null) goto L15;
        List<Cubic> r11 = r3;
    L20:
        int r12 = r11.size();
        int r13 = 0;
    L21:
        if (r13 >= r12) goto L32;
        Cubic r14 = r11.get(r13);
        if (r14.zeroLength$graphics_shapes_release() == true) goto L29;
        if (r10 == null) goto L26;
        r2.add(r10);
    L26:
        if (r9 != null) goto L28;
        r9 = r14;
        r10 = r9;
    L31:
        r13 = r13 + 1;
        goto L21
    L28:
        r10 = r14;
        goto L31
    L29:
        if (r10 == null) goto L31;
        r10.getPoints$graphics_shapes_release()[6] = r14.getAnchor1X();
        r10.getPoints$graphics_shapes_release()[7] = r14.getAnchor1Y();
        goto L31
    L32:
        if (r8 == r1) goto L35;
        r8 = r8 + 1;
    L35:
        if (r10 == null) goto L38;
        if (r9 == null) goto L38;
        r2.add(CubicKt.Cubic(r10.getAnchor0X(), r10.getAnchor0Y(), r10.getControl0X(), r10.getControl0Y(), r10.getControl1X(), r10.getControl1Y(), r9.getAnchor0X(), r9.getAnchor0Y()));
    L38:
        List<Cubic> r15 = CollectionsKt.build(r2);
        this.cubics = r15;
        Cubic r23 = r15.get(r15.size() - 1);
        int r16 = r15.size();
    L39:
        if (r5 >= r16) goto L47;
        Cubic r32 = this.cubics.get(r5);
        Cubic r24 = r23;
        if (Math.abs(r32.getAnchor0X() - r24.getAnchor1X()) > 1.0E-4f) goto L45;
        if (Math.abs(r32.getAnchor0Y() - r24.getAnchor1Y()) > 1.0E-4f) goto L45;
        r5 = r5 + 1;
        r23 = r32;
    L45:
        f$$ExternalSyntheticBUOutline1.m("RoundedPolygon must be contiguous, with the anchor points of all curves matching the anchor points of the preceding and succeeding cubics");
        throw null;
    L47:
        return;
    L15:
        if (r8 != this.features.size()) goto L19;
        if (r7 == null) goto L35;
        r11 = r7;
        goto L20
    L19:
        r11 = this.features.get(r8).getCubics();
        goto L20
    L34:
        r9 = null;
        r10 = null;
        goto L35
    L5:
        if (((Feature) r20.get(0)).getCubics().size() != 3) goto L7;
        Pair<Cubic, Cubic> r33 = ((Feature) r20.get(0)).getCubics().get(1).split(0.5f);
        Cubic r72 = r33.component1();
        Cubic r34 = r33.component2();
        r7 = CollectionsKt.mutableListOf(new Cubic[]{((Feature) r20.get(0)).getCubics().get(0), r72});
        r3 = CollectionsKt.mutableListOf(new Cubic[]{r34, ((Feature) r20.get(0)).getCubics().get(2)});
        goto L8
    }

    public static /* synthetic */ float[] calculateBounds$default(RoundedPolygon r0, float[] r1, boolean r2, int r3, Object r4) {
        if ((r3 & 1) == 0) goto L6;
        r1 = new float[4];
    L6:
        if ((r3 & 2) == 0) goto L9;
        r2 = true;
    L9:
        return r0.calculateBounds(r1, r2);
    }

    @JvmOverloads
    public final float[] calculateBounds(float[] r4) {
        return calculateBounds$default(this, r4, false, 2, null);
    }

    @JvmOverloads
    public final float[] calculateBounds(float[] r12, boolean r13) {
        if (r12.length < 4) goto L10;
        int r0 = this.cubics.size();
        float r1 = Float.MIN_VALUE;
        float r4 = Float.MAX_VALUE;
        float r5 = Float.MAX_VALUE;
        int r6 = 0;
        float r2 = Float.MIN_VALUE;
    L6:
        if (r6 >= r0) goto L8;
        this.cubics.get(r6).calculateBounds$graphics_shapes_release(r12, r13);
        r4 = Math.min(r4, r12[0]);
        r5 = Math.min(r5, r12[1]);
        r1 = Math.max(r1, r12[2]);
        r2 = Math.max(r2, r12[3]);
        r6 = r6 + 1;
        goto L6
    L8:
        r12[0] = r4;
        r12[1] = r5;
        r12[2] = r1;
        r12[3] = r2;
        return r12;
    L10:
        f$$ExternalSyntheticBUOutline1.m("Required bounds size of 4");
        return null;
    }

    public final float[] calculateMaxBounds(float[] r10) {
        if (r10.length < 4) goto L9;
        int r0 = this.cubics.size();
        float r2 = 0.0f;
        int r3 = 0;
    L5:
        if (r3 >= r0) goto L7;
        Cubic r4 = this.cubics.get(r3);
        float r5 = Utils.distanceSquared(r4.getAnchor0X() - this.centerX, r4.getAnchor0Y() - this.centerY);
        long r6 = r4.m728pointOnCurveOOQOV4g$graphics_shapes_release(0.5f);
        r2 = Math.max(r2, Math.max(r5, Utils.distanceSquared(PointKt.m735getXDnnuFBc(r6) - this.centerX, PointKt.m736getYDnnuFBc(r6) - this.centerY)));
        r3 = r3 + 1;
        goto L5
    L7:
        float r02 = (float) Math.sqrt(r2);
        float r22 = this.centerX;
        r10[0] = r22 - r02;
        float r9 = this.centerY;
        r10[1] = r9 - r02;
        r10[2] = r22 + r02;
        r10[3] = r9 + r02;
        return r10;
    L9:
        f$$ExternalSyntheticBUOutline1.m("Required bounds size of 4");
        return null;
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof RoundedPolygon) == true) goto L10;
        return false;
    L10:
        return Intrinsics.areEqual(this.features, ((RoundedPolygon) r2).features);
    }

    public final float getCenterX() {
        return this.centerX;
    }

    public final float getCenterY() {
        return this.centerY;
    }

    public final List<Cubic> getCubics() {
        return this.cubics;
    }

    public final List<Feature> getFeatures$graphics_shapes_release() {
        return this.features;
    }

    public int hashCode() {
        return this.features.hashCode();
    }

    public String toString() {
        return "[RoundedPolygon. Cubics = " + CollectionsKt.joinToString$default(this.cubics, null, null, null, 0, null, null, 63, null) + " || Features = " + CollectionsKt.joinToString$default(this.features, null, null, null, 0, null, null, 63, null) + " || Center = (" + this.centerX + ", " + this.centerY + ")]";
    }

    public final RoundedPolygon transformed(PointTransformer r7) {
        long r0 = PointKt.m741transformedso9K2fw(FloatFloatPair.m659constructorimpl(this.centerX, this.centerY), r7);
        List r2 = CollectionsKt.createListBuilder();
        int r3 = this.features.size();
        int r4 = 0;
    L3:
        if (r4 >= r3) goto L6;
        r2.add(this.features.get(r4).transformed$graphics_shapes_release(r7));
        r4 = r4 + 1;
        goto L3
    L6:
        return new RoundedPolygon(CollectionsKt.build(r2), PointKt.m735getXDnnuFBc(r0), PointKt.m736getYDnnuFBc(r0));
    }
}
