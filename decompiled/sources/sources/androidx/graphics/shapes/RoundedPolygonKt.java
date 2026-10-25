package androidx.graphics.shapes;

import androidx.collection.FloatFloatPair;
import androidx.collection.MutableFloatList;
import androidx.graphics.shapes.Feature;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aS\u0010\u000b\u001a\u00020\n2\b\b\u0001\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00062\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\bH\u0007¢\u0006\u0004\b\u000b\u0010\f\u001aG\u0010\u000b\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u0007\u001a\u00020\u00062\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\b2\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u000b\u0010\u000f\u001a\u001b\u0010\u0012\u001a\u00060\u0010j\u0002`\u00112\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0012\u0010\u0013\u001a/\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {_UrlKt.FRAGMENT_ENCODE_SET, "numVertices", _UrlKt.FRAGMENT_ENCODE_SET, "radius", "centerX", "centerY", "Landroidx/graphics/shapes/CornerRounding;", "rounding", _UrlKt.FRAGMENT_ENCODE_SET, "perVertexRounding", "Landroidx/graphics/shapes/RoundedPolygon;", "RoundedPolygon", "(IFFFLandroidx/graphics/shapes/CornerRounding;Ljava/util/List;)Landroidx/graphics/shapes/RoundedPolygon;", _UrlKt.FRAGMENT_ENCODE_SET, "vertices", "([FLandroidx/graphics/shapes/CornerRounding;Ljava/util/List;FF)Landroidx/graphics/shapes/RoundedPolygon;", "Landroidx/collection/FloatFloatPair;", "Landroidx/graphics/shapes/Point;", "calculateCenter", "([F)J", "verticesFromNumVerts", "(IFFF)[F", "graphics-shapes_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRoundedPolygon.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RoundedPolygon.kt\nandroidx/graphics/shapes/RoundedPolygonKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 FloatFloatPair.kt\nandroidx/collection/FloatFloatPair\n+ 4 PackingHelpers.jvm.kt\nandroidx/collection/internal/PackingHelpers_jvmKt\n*L\n1#1,686:1\n1549#2:687\n1620#2,3:688\n67#3:691\n81#3:693\n22#4:692\n22#4:694\n*S KotlinDebug\n*F\n+ 1 RoundedPolygon.kt\nandroidx/graphics/shapes/RoundedPolygonKt\n*L\n356#1:687\n356#1:688,3\n426#1:691\n425#1:693\n426#1:692\n425#1:694\n*E\n"})
/* loaded from: classes3.dex */
public abstract class RoundedPolygonKt {
    @JvmOverloads
    public static final RoundedPolygon RoundedPolygon(int r8, float r9, float r10, float r11, CornerRounding r12) {
        return RoundedPolygon$default(r8, r9, r10, r11, r12, null, 32, null);
    }

    @JvmOverloads
    public static final RoundedPolygon RoundedPolygon(int r0, float r1, float r2, float r3, CornerRounding r4, List<CornerRounding> r5) {
        return RoundedPolygon(verticesFromNumVerts(r0, r1, r2, r3), r4, r5, r2, r3);
    }

    @JvmOverloads
    public static final RoundedPolygon RoundedPolygon(float[] r22, CornerRounding r23, List<CornerRounding> r24, float r25, float r26) {
        Float r2 = Float.valueOf(1.0f);
        if (r22.length < 6) goto L52;
        int r4 = 2;
        if ((r22.length % 2) == 1) goto L50;
        if (r24 != null) goto L8;
    L12:
        ArrayList r3 = new ArrayList();
        int r5 = r22.length / 2;
        ArrayList r7 = new ArrayList();
        int r8 = 0;
        int r9 = 0;
    L13:
        if (r9 >= r5) goto L21;
        if (r24 == null) goto L19;
        CornerRounding r10 = r24.get(r9);
        if (r10 == null) goto L19;
        CornerRounding r18 = r10;
    L20:
        int r102 = (((r9 + r5) - 1) % r5) * 2;
        int r20 = r9 + 1;
        int r11 = (r20 % r5) * 2;
        int r92 = r9 * 2;
        r7.add(new RoundedCorner(FloatFloatPair.m659constructorimpl(r22[r102], r22[r102 + 1]), FloatFloatPair.m659constructorimpl(r22[r92], r22[r92 + 1]), FloatFloatPair.m659constructorimpl(r22[r11], r22[r11 + 1]), r18, null));
        r9 = r20;
    L19:
        r18 = r23;
        goto L20
    L21:
        IntRange r1 = RangesKt.until(0, r5);
        ArrayList r93 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r1, 10));
        Iterator<Integer> r12 = r1.iterator();
    L23:
        if (r12.hasNext() == false) goto L32;
        int r103 = ((IntIterator) r12).nextInt();
        int r122 = (r103 + 1) % r5;
        float r112 = ((RoundedCorner) r7.get(r103)).getExpectedRoundCut() + ((RoundedCorner) r7.get(r122)).getExpectedRoundCut();
        float r13 = ((RoundedCorner) r7.get(r103)).getExpectedCut() + ((RoundedCorner) r7.get(r122)).getExpectedCut();
        int r104 = r103 * 2;
        float r14 = r22[r104];
        float r105 = r22[r104 + 1];
        int r123 = r122 * 2;
        float r106 = Utils.distance(r14 - r22[r123], r105 - r22[r123 + 1]);
        if (r112 <= r106) goto L28;
        Pair r107 = TuplesKt.to(Float.valueOf(r106 / r112), Float.valueOf(0.0f));
    L31:
        r93.add(r107);
        goto L23
    L28:
        if (r13 <= r106) goto L30;
        r107 = TuplesKt.to(r2, Float.valueOf((r106 - r112) / (r13 - r112)));
        goto L31
    L30:
        r107 = TuplesKt.to(r2, r2);
        goto L31
    L32:
        int r15 = 0;
    L33:
        if (r15 >= r5) goto L38;
        MutableFloatList r27 = new MutableFloatList(2);
        int r108 = 0;
    L35:
        if (r108 >= 2) goto L37;
        Pair r113 = (Pair) r93.get((((r15 + r5) - 1) + r108) % r5);
        r27.add((((RoundedCorner) r7.get(r15)).getExpectedRoundCut() * ((Number) r113.component1()).floatValue()) + ((((RoundedCorner) r7.get(r15)).getExpectedCut() - ((RoundedCorner) r7.get(r15)).getExpectedRoundCut()) * ((Number) r113.component2()).floatValue()));
        r108 = r108 + 1;
        goto L35
    L37:
        r3.add(((RoundedCorner) r7.get(r15)).getCubics(r27.get(0), r27.get(1)));
        r15 = r15 + 1;
        goto L33
    L38:
        ArrayList r16 = new ArrayList();
    L39:
        if (r8 >= r5) goto L42;
        int r94 = r8 + 1;
        int r109 = r94 % r5;
        int r114 = r8 * 2;
        long r115 = FloatFloatPair.m659constructorimpl(r22[r114], r22[r114 + 1]);
        int r28 = (((r8 + r5) - 1) % r5) * r4;
        long r132 = FloatFloatPair.m659constructorimpl(r22[r28], r22[r28 + 1]);
        int r29 = r109 * 2;
        r16.add(new Feature.Corner((List) r3.get(r8), r115, ((RoundedCorner) r7.get(r8)).m745getCenter1ufDz9w(), PointKt.m729clockwiseybeJwSQ(PointKt.m738minusybeJwSQ(r115, r132), PointKt.m738minusybeJwSQ(FloatFloatPair.m659constructorimpl(r22[r29], r22[r29 + 1]), r115)), null));
        r16.add(new Feature.Edge(CollectionsKt.listOf(Cubic.Companion.straightLine(((Cubic) CollectionsKt.last((List) r3.get(r8))).getAnchor1X(), ((Cubic) CollectionsKt.last((List) r3.get(r8))).getAnchor1Y(), ((Cubic) CollectionsKt.first((List) r3.get(r109))).getAnchor0X(), ((Cubic) CollectionsKt.first((List) r3.get(r109))).getAnchor0Y()))));
        r8 = r94;
        r5 = r5;
        r4 = 2;
        goto L39
    L42:
        if (r25 != Float.MIN_VALUE) goto L45;
    L46:
        long r210 = calculateCenter(r22);
    L49:
        return new RoundedPolygon(r16, Float.intBitsToFloat((int) (r210 >> 32)), Float.intBitsToFloat((int) (r210 & 4294967295L)));
    L45:
        if (r26 == Float.MIN_VALUE) goto L46;
        r210 = FloatFloatPair.m659constructorimpl(r25, r26);
        goto L49
    L8:
        if ((r24.size() * 2) == r22.length) goto L12;
        f$$ExternalSyntheticBUOutline1.m("perVertexRounding list should be either null or the same size as the number of vertices (vertices.size / 2)");
        return null;
    L50:
        f$$ExternalSyntheticBUOutline1.m("The vertices array should have even size");
        return null;
    L52:
        f$$ExternalSyntheticBUOutline1.m("Polygons must have at least 3 vertices");
        return null;
    }

    public static /* synthetic */ RoundedPolygon RoundedPolygon$default(int r1, float r2, float r3, float r4, CornerRounding r5, List r6, int r7, Object r8) {
        if ((r7 & 2) == 0) goto L6;
        r2 = 1.0f;
    L6:
        if ((r7 & 4) == 0) goto L9;
        r3 = 0.0f;
    L9:
        if ((r7 & 8) == 0) goto L12;
        r4 = 0.0f;
    L12:
        if ((r7 & 16) == 0) goto L15;
        r5 = CornerRounding.Unrounded;
    L15:
        if ((r7 & 32) == 0) goto L17;
        r6 = null;
    L17:
        List r82 = r6;
        float r62 = r4;
        return RoundedPolygon(r1, r2, r3, r62, r5, r82);
    }

    private static final long calculateCenter(float[] r5) {
        float r0 = 0.0f;
        int r2 = 0;
        float r1 = 0.0f;
    L4:
        if (r2 >= r5.length) goto L7;
        int r3 = r2 + 1;
        r0 = r0 + r5[r2];
        r2 = r2 + 2;
        r1 = r1 + r5[r3];
        goto L4
    L7:
        return FloatFloatPair.m659constructorimpl((r0 / r5.length) / 2.0f, (r1 / r5.length) / 2.0f);
    }

    private static final float[] verticesFromNumVerts(int r11, float r12, float r13, float r14) {
        float[] r0 = new float[r11 * 2];
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r11) goto L5;
        float r5 = r12;
        long r3 = PointKt.m739plusybeJwSQ(Utils.m747radialToCartesianL6JJ3z0$default(r5, ((Utils.getFloatPi() / r11) * 2.0f) * r1, 0, 4, null), FloatFloatPair.m659constructorimpl(r13, r14));
        int r122 = r2 + 1;
        r0[r2] = PointKt.m735getXDnnuFBc(r3);
        r2 = r2 + 2;
        r0[r122] = PointKt.m736getYDnnuFBc(r3);
        r1 = r1 + 1;
        r12 = r5;
        goto L3
    L5:
        return r0;
    }
}
