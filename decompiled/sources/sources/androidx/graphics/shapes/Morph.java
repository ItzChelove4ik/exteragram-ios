package androidx.graphics.shapes;

import androidx.graphics.shapes.MeasuredPolygon;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u001b\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\rR\u0014\u0010\u0004\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\rR&\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\u000e0\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010¨\u0006\u0012"}, d2 = {"Landroidx/graphics/shapes/Morph;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/graphics/shapes/RoundedPolygon;", "start", "end", "<init>", "(Landroidx/graphics/shapes/RoundedPolygon;Landroidx/graphics/shapes/RoundedPolygon;)V", _UrlKt.FRAGMENT_ENCODE_SET, "progress", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/graphics/shapes/Cubic;", "asCubics", "(F)Ljava/util/List;", "Landroidx/graphics/shapes/RoundedPolygon;", "Lkotlin/Pair;", "_morphMatch", "Ljava/util/List;", "Companion", "graphics-shapes_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nMorph.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Morph.kt\nandroidx/graphics/shapes/Morph\n*L\n1#1,342:1\n173#1,10:343\n*S KotlinDebug\n*F\n+ 1 Morph.kt\nandroidx/graphics/shapes/Morph\n*L\n-1#1:343,10\n*E\n"})
/* loaded from: classes3.dex */
public final class Morph {
    public static final Companion Companion = null;
    private final List<Pair<Cubic, Cubic>> _morphMatch;
    private final RoundedPolygon end;
    private final RoundedPolygon start;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J/\u0010\u0003\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00050\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0001¢\u0006\u0002\b\n¨\u0006\u000b"}, d2 = {"Landroidx/graphics/shapes/Morph$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "()V", "match", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/Pair;", "Landroidx/graphics/shapes/Cubic;", "p1", "Landroidx/graphics/shapes/RoundedPolygon;", "p2", "match$graphics_shapes_release", "graphics-shapes_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nMorph.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Morph.kt\nandroidx/graphics/shapes/Morph$Companion\n+ 2 Utils.kt\nandroidx/graphics/shapes/Utils\n*L\n1#1,342:1\n108#2,4:343\n108#2,4:347\n108#2,4:351\n108#2,4:355\n108#2,4:359\n*S KotlinDebug\n*F\n+ 1 Morph.kt\nandroidx/graphics/shapes/Morph$Companion\n*L\n239#1:343,4\n284#1:347,4\n290#1:351,4\n297#1:355,4\n304#1:359,4\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final List<Pair<Cubic, Cubic>> match$graphics_shapes_release(RoundedPolygon r12, RoundedPolygon r13) {
            MeasuredPolygon.Companion r11 = MeasuredPolygon.Companion;
            MeasuredPolygon r122 = r11.measurePolygon$graphics_shapes_release(new AngleMeasurer(r12.getCenterX(), r12.getCenterY()), r12);
            MeasuredPolygon r112 = r11.measurePolygon$graphics_shapes_release(new AngleMeasurer(r13.getCenterX(), r13.getCenterY()), r13);
            DoubleMapper r132 = FeatureMappingKt.featureMapper(r122.getFeatures(), r112.getFeatures());
            float r0 = r132.map(0.0f);
            MorphKt.access$getLOG_TAG$p();
            MeasuredPolygon r113 = r112.cutAndShift(r0);
            ArrayList r1 = new ArrayList();
            MeasuredPolygon.MeasuredCubic r3 = (MeasuredPolygon.MeasuredCubic) CollectionsKt.getOrNull(r122, 0);
            MeasuredPolygon.MeasuredCubic r2 = (MeasuredPolygon.MeasuredCubic) CollectionsKt.getOrNull(r113, 0);
            int r4 = 1;
            int r5 = 1;
        L3:
            if (r3 == null) goto L22;
            if (r2 == null) goto L22;
            if (r4 != r122.size()) goto L8;
            float r6 = 1.0f;
        L10:
            if (r5 != r113.size()) goto L12;
            float r8 = 1.0f;
        L13:
            float r9 = Math.min(r6, r8);
            MorphKt.access$getLOG_TAG$p();
            float r10 = 1.0E-6f + r9;
            if (r6 <= r10) goto L16;
            MorphKt.access$getLOG_TAG$p();
            Pair<MeasuredPolygon.MeasuredCubic, MeasuredPolygon.MeasuredCubic> r32 = r3.cutAtProgress(r9);
        L17:
            MeasuredPolygon.MeasuredCubic r62 = r32.component1();
            r3 = r32.component2();
            if (r8 <= r10) goto L20;
            MorphKt.access$getLOG_TAG$p();
            Pair<MeasuredPolygon.MeasuredCubic, MeasuredPolygon.MeasuredCubic> r22 = r2.cutAtProgress(Utils.positiveModulo(r132.map(r9) - r0, 1.0f));
        L21:
            MeasuredPolygon.MeasuredCubic r7 = r22.component1();
            r2 = r22.component2();
            MorphKt.access$getLOG_TAG$p();
            r1.add(TuplesKt.to(r62.getCubic(), r7.getCubic()));
            goto L3
        L20:
            r22 = TuplesKt.to(r2, CollectionsKt.getOrNull(r113, r5));
            r5 = r5 + 1;
            goto L21
        L16:
            r32 = TuplesKt.to(r3, CollectionsKt.getOrNull(r122, r4));
            r4 = r4 + 1;
            goto L17
        L12:
            r8 = r132.mapBack(Utils.positiveModulo(r2.getEndOutlineProgress() + r0, 1.0f));
            goto L13
        L8:
            r6 = r3.getEndOutlineProgress();
        L22:
            if (r3 != null) goto L25;
            if (r2 != null) goto L25;
            return r1;
        L25:
            f$$ExternalSyntheticBUOutline1.m("Expected both Polygon's Cubic to be fully matched");
            return null;
        }
    }

    static {
        Companion = new Companion(null);
    }

    public Morph(RoundedPolygon r2, RoundedPolygon r3) {
        this.start = r2;
        this.end = r3;
        this._morphMatch = Companion.match$graphics_shapes_release(r2, r3);
    }

    public final List<Cubic> asCubics(float r17) {
        List r1 = CollectionsKt.createListBuilder();
        int r2 = this._morphMatch.size();
        Cubic r3 = null;
        Cubic r5 = null;
        int r6 = 0;
    L3:
        if (r6 >= r2) goto L13;
        float[] r8 = new float[8];
        int r9 = 0;
    L5:
        if (r9 >= 8) goto L7;
        r8[r9] = Utils.interpolate(this._morphMatch.get(r6).getFirst().getPoints$graphics_shapes_release()[r9], this._morphMatch.get(r6).getSecond().getPoints$graphics_shapes_release()[r9], r17);
        r9 = r9 + 1;
        goto L5
    L7:
        Cubic r7 = new Cubic(r8);
        if (r5 != null) goto L10;
        r5 = r7;
    L10:
        if (r3 == null) goto L12;
        r1.add(r3);
    L12:
        r6 = r6 + 1;
        r3 = r7;
        goto L3
    L13:
        if (r3 == null) goto L17;
        if (r5 == null) goto L17;
        r1.add(CubicKt.Cubic(r3.getAnchor0X(), r3.getAnchor0Y(), r3.getControl0X(), r3.getControl0Y(), r3.getControl1X(), r3.getControl1Y(), r5.getAnchor0X(), r5.getAnchor0Y()));
    L17:
        return CollectionsKt.build(r1);
    }
}
