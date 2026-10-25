package androidx.graphics.shapes;

import androidx.graphics.shapes.RoundedPolygon;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\u0014\n\u0002\b\u0003\u001a;\u0010\b\u001a\u00020\u0007*\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\b\u0010\t\u001aU\u0010\u0010\u001a\u00020\u0007*\u00020\u00002\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\f2\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000e2\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0010\u0010\u0011\u001ak\u0010\u0015\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\f2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\f2\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000e2\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u0015\u0010\u0016\u001a7\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Landroidx/graphics/shapes/RoundedPolygon$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "numVertices", _UrlKt.FRAGMENT_ENCODE_SET, "radius", "centerX", "centerY", "Landroidx/graphics/shapes/RoundedPolygon;", "circle", "(Landroidx/graphics/shapes/RoundedPolygon$Companion;IFFF)Landroidx/graphics/shapes/RoundedPolygon;", "width", "height", "Landroidx/graphics/shapes/CornerRounding;", "rounding", _UrlKt.FRAGMENT_ENCODE_SET, "perVertexRounding", "rectangle", "(Landroidx/graphics/shapes/RoundedPolygon$Companion;FFLandroidx/graphics/shapes/CornerRounding;Ljava/util/List;FF)Landroidx/graphics/shapes/RoundedPolygon;", "numVerticesPerRadius", "innerRadius", "innerRounding", "star", "(Landroidx/graphics/shapes/RoundedPolygon$Companion;IFFLandroidx/graphics/shapes/CornerRounding;Landroidx/graphics/shapes/CornerRounding;Ljava/util/List;FF)Landroidx/graphics/shapes/RoundedPolygon;", _UrlKt.FRAGMENT_ENCODE_SET, "starVerticesFromNumVerts", "(IFFFF)[F", "graphics-shapes_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nShapes.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Shapes.kt\nandroidx/graphics/shapes/ShapesKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,459:1\n1360#2:460\n1446#2,5:461\n1360#2:466\n1446#2,5:467\n*S KotlinDebug\n*F\n+ 1 Shapes.kt\nandroidx/graphics/shapes/ShapesKt\n*L\n155#1:460\n155#1:461,5\n294#1:466\n294#1:467,5\n*E\n"})
/* loaded from: classes3.dex */
public abstract class ShapesKt {
    @JvmOverloads
    public static final RoundedPolygon circle(RoundedPolygon.Companion r7) {
        return circle$default(r7, 0, 0.0f, 0.0f, 0.0f, 15, null);
    }

    @JvmOverloads
    public static final RoundedPolygon circle(RoundedPolygon.Companion r7, int r8) {
        return circle$default(r7, r8, 0.0f, 0.0f, 0.0f, 14, null);
    }

    @JvmOverloads
    public static final RoundedPolygon circle(RoundedPolygon.Companion r8, int r9, float r10, float r11, float r12) {
        if (r9 >= 3) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Circle must have at least three vertices");
        return null;
    L5:
        return RoundedPolygonKt.RoundedPolygon$default(r9, r10 / ((float) Math.cos(Utils.getFloatPi() / r9)), r11, r12, new CornerRounding(r10, 0.0f, 2, null), null, 32, null);
    }

    public static /* synthetic */ RoundedPolygon circle$default(RoundedPolygon.Companion r2, int r3, float r4, float r5, float r6, int r7, Object r8) {
        if ((r7 & 1) == 0) goto L6;
        r3 = 8;
    L6:
        if ((r7 & 2) == 0) goto L9;
        r4 = 1.0f;
    L9:
        if ((r7 & 4) == 0) goto L12;
        r5 = 0.0f;
    L12:
        if ((r7 & 8) == 0) goto L15;
        r6 = 0.0f;
    L15:
        return circle(r2, r3, r4, r5, r6);
    }

    public static final RoundedPolygon rectangle(RoundedPolygon.Companion r3, float r4, float r5, CornerRounding r6, List<CornerRounding> r7, float r8, float r9) {
        float r42 = r4 / 2.0f;
        float r0 = r8 - r42;
        float r52 = r5 / 2.0f;
        float r32 = r9 - r52;
        float r43 = r42 + r8;
        float r53 = r52 + r9;
        return RoundedPolygonKt.RoundedPolygon(new float[]{r43, r53, r0, r53, r0, r32, r43, r32}, r6, r7, r8, r9);
    }

    @JvmOverloads
    public static final RoundedPolygon star(RoundedPolygon.Companion r11, int r12, float r13, float r14, CornerRounding r15) {
        return star$default(r11, r12, r13, r14, r15, null, null, 0.0f, 0.0f, 240, null);
    }

    @JvmOverloads
    public static final RoundedPolygon star(RoundedPolygon.Companion r2, int r3, float r4, float r5, CornerRounding r6, CornerRounding r7, List<CornerRounding> r8, float r9, float r10) {
        if (r4 > 0.0f) goto L5;
    L18:
        f$$ExternalSyntheticBUOutline1.m("Star radii must both be greater than 0");
        return null;
    L5:
        if (r5 <= 0.0f) goto L18;
        if (r5 >= r4) goto L16;
        if (r8 != null) goto L15;
        if (r7 == null) goto L15;
        IntRange r22 = RangesKt.until(0, r3);
        r8 = new ArrayList();
        Iterator<Integer> r23 = r22.iterator();
    L12:
        if (r23.hasNext() == false) goto L15;
        ((IntIterator) r23).nextInt();
        CollectionsKt.addAll(r8, CollectionsKt.listOf(new CornerRounding[]{r6, r7}));
    L15:
        return RoundedPolygonKt.RoundedPolygon(starVerticesFromNumVerts(r3, r4, r5, r9, r10), r6, r8, r9, r10);
    L16:
        f$$ExternalSyntheticBUOutline1.m("innerRadius must be less than radius");
        return null;
    }

    public static /* synthetic */ RoundedPolygon star$default(RoundedPolygon.Companion r8, int r9, float r10, float r11, CornerRounding r12, CornerRounding r13, List r14, float r15, float r16, int r17, Object r18) {
        if ((r17 & 2) == 0) goto L5;
        float r1 = 1.0f;
    L7:
        if ((r17 & 4) == 0) goto L9;
        float r2 = 0.5f;
    L11:
        if ((r17 & 8) == 0) goto L13;
        CornerRounding r3 = CornerRounding.Unrounded;
    L14:
        List r5 = null;
        if ((r17 & 16) == 0) goto L17;
        CornerRounding r4 = null;
    L19:
        if ((r17 & 32) != 0) goto L23;
        r5 = r14;
    L23:
        if ((r17 & 64) == 0) goto L25;
        float r6 = 0.0f;
    L27:
        if ((r17 & 128) == 0) goto L30;
        float r182 = 0.0f;
    L32:
        return star(r8, r9, r1, r2, r3, r4, r5, r6, r182);
    L30:
        r182 = r16;
        goto L32
    L25:
        r6 = r15;
        goto L27
    L17:
        r4 = r13;
        goto L19
    L13:
        r3 = r12;
        goto L14
    L9:
        r2 = r11;
        goto L11
    L5:
        r1 = r10;
        goto L7
    }

    private static final float[] starVerticesFromNumVerts(int r12, float r13, float r14, float r15, float r16) {
        float[] r0 = new float[r12 * 4];
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r12) goto L5;
        float r4 = r12;
        long r7 = Utils.m747radialToCartesianL6JJ3z0$default(r13, ((Utils.getFloatPi() / r4) * 2.0f) * r1, 0, 4, null);
        r0[r2] = PointKt.m735getXDnnuFBc(r7) + r15;
        r0[r2 + 1] = PointKt.m736getYDnnuFBc(r7) + r16;
        long r3 = Utils.m747radialToCartesianL6JJ3z0$default(r14, (Utils.getFloatPi() / r4) * ((r1 * 2) + 1), 0, 4, null);
        int r6 = r2 + 3;
        r0[r2 + 2] = PointKt.m735getXDnnuFBc(r3) + r15;
        r2 = r2 + 4;
        r0[r6] = PointKt.m736getYDnnuFBc(r3) + r16;
        r1 = r1 + 1;
        goto L3
    L5:
        return r0;
    }
}
