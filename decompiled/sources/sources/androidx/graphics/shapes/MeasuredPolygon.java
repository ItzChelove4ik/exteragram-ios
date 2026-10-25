package androidx.graphics.shapes;

import androidx.collection.FloatList;
import androidx.collection.MutableFloatList;
import androidx.graphics.shapes.Feature;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.AbstractList;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0005\b\u0000\u0018\u0000 \u00182\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0002\u0018\u0019B3\b\u0012\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u000e\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0015J\u0015\u0010\u0016\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0010H\u0096\u0002R\u0018\u0010\b\u001a\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001a"}, d2 = {"Landroidx/graphics/shapes/MeasuredPolygon;", "Lkotlin/collections/AbstractList;", "Landroidx/graphics/shapes/MeasuredPolygon$MeasuredCubic;", "measurer", "Landroidx/graphics/shapes/Measurer;", "features", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/graphics/shapes/ProgressableFeature;", "cubics", "Landroidx/graphics/shapes/Cubic;", "outlineProgress", "Landroidx/collection/FloatList;", "(Landroidx/graphics/shapes/Measurer;Ljava/util/List;Ljava/util/List;Landroidx/collection/FloatList;)V", "getFeatures", "()Ljava/util/List;", "size", _UrlKt.FRAGMENT_ENCODE_SET, "getSize", "()I", "cutAndShift", "cuttingPoint", _UrlKt.FRAGMENT_ENCODE_SET, "get", "index", "Companion", "MeasuredCubic", "graphics-shapes_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPolygonMeasure.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PolygonMeasure.kt\nandroidx/graphics/shapes/MeasuredPolygon\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 Utils.kt\nandroidx/graphics/shapes/Utils\n*L\n1#1,353:1\n1#2:354\n350#3,7:355\n108#4,4:362\n*S KotlinDebug\n*F\n+ 1 PolygonMeasure.kt\nandroidx/graphics/shapes/MeasuredPolygon\n*L\n169#1:355,7\n182#1:362,4\n*E\n"})
/* loaded from: classes3.dex */
public final class MeasuredPolygon extends AbstractList<MeasuredCubic> {
    public static final Companion Companion = null;
    private final List<MeasuredCubic> cubics;
    private final List<ProgressableFeature> features;
    private final Measurer measurer;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001d\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0002\b\t¨\u0006\n"}, d2 = {"Landroidx/graphics/shapes/MeasuredPolygon$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "()V", "measurePolygon", "Landroidx/graphics/shapes/MeasuredPolygon;", "measurer", "Landroidx/graphics/shapes/Measurer;", "polygon", "Landroidx/graphics/shapes/RoundedPolygon;", "measurePolygon$graphics_shapes_release", "graphics-shapes_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nPolygonMeasure.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PolygonMeasure.kt\nandroidx/graphics/shapes/MeasuredPolygon$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Utils.kt\nandroidx/graphics/shapes/Utils\n*L\n1#1,353:1\n2949#2:354\n2847#2,3:355\n2850#2,6:359\n1#3:358\n108#4,4:365\n*S KotlinDebug\n*F\n+ 1 PolygonMeasure.kt\nandroidx/graphics/shapes/MeasuredPolygon$Companion\n*L\n259#1:354\n259#1:355,3\n259#1:359,6\n259#1:358\n275#1:365,4\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final MeasuredPolygon measurePolygon$graphics_shapes_release(Measurer r9, RoundedPolygon r10) {
            ArrayList r3 = new ArrayList();
            ArrayList r8 = new ArrayList();
            int r0 = r10.getFeatures$graphics_shapes_release().size();
            int r1 = 0;
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L13;
            Feature r4 = r10.getFeatures$graphics_shapes_release().get(r2);
            int r5 = r4.getCubics().size();
            int r6 = 0;
        L5:
            if (r6 >= r5) goto L12;
            if ((r4 instanceof Feature.Corner) == false) goto L11;
            if (r6 != (r4.getCubics().size() / 2)) goto L11;
            r8.add(TuplesKt.to(r4, Integer.valueOf(r3.size())));
        L11:
            r3.add(r4.getCubics().get(r6));
            r6 = r6 + 1;
            goto L5
        L12:
            r2 = r2 + 1;
            goto L3
        L13:
            Float r02 = Float.valueOf(0.0f);
            int r22 = CollectionsKt.collectionSizeOrDefault(r3, 9);
            if (r22 != 0) goto L16;
            List r102 = CollectionsKt.listOf(r02);
        L24:
            float r03 = ((Number) CollectionsKt.last(r102)).floatValue();
            MutableFloatList r42 = new MutableFloatList(r102.size());
            int r23 = r102.size();
            int r52 = 0;
        L25:
            if (r52 >= r23) goto L27;
            r42.add(((Number) r102.get(r52)).floatValue() / r03);
            r52 = r52 + 1;
            goto L25
        L27:
            PolygonMeasureKt.access$getLOG_TAG$p();
            List r103 = CollectionsKt.createListBuilder();
            int r04 = r8.size();
        L28:
            if (r1 >= r04) goto L31;
            int r24 = ((Number) ((Pair) r8.get(r1)).getSecond()).intValue();
            r103.add(new ProgressableFeature((r42.get(r24) + r42.get(r24 + 1)) / 2.0f, (Feature) ((Pair) r8.get(r1)).getFirst()));
            r1 = r1 + 1;
            goto L28
        L31:
            return new MeasuredPolygon(r9, CollectionsKt.build(r103), r3, r42, null);
        L16:
            ArrayList r43 = new ArrayList(r22 + 1);
            r43.add(r02);
            int r25 = r3.size();
            int r53 = 0;
        L17:
            if (r53 >= r25) goto L23;
            Object r62 = r3.get(r53);
            r53 = r53 + 1;
            float r05 = r02.floatValue();
            float r63 = r9.measureCubic((Cubic) r62);
            if (r63 < 0.0f) goto L21;
            Unit r7 = Unit.INSTANCE;
            r02 = Float.valueOf(r05 + r63);
            r43.add(r02);
            goto L17
        L21:
            f$$ExternalSyntheticBUOutline1.m("Measured cubic is expected to be greater or equal to zero");
            return null;
        L23:
            r102 = r43;
            goto L24
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\b\u0080\u0004\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ#\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\n\u0010\u000bJ)\u0010\u0010\u001a\u0016\u0012\b\u0012\u00060\u0000R\u00020\u000f\u0012\b\u0012\u00060\u0000R\u00020\u000f0\u000e2\u0006\u0010\r\u001a\u00020\u0004¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0018\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR$\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00048\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u0005\u0010\u0019\u001a\u0004\b\u001d\u0010\u001bR$\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00048\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u0006\u0010\u0019\u001a\u0004\b\u001e\u0010\u001b¨\u0006\u001f"}, d2 = {"Landroidx/graphics/shapes/MeasuredPolygon$MeasuredCubic;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/graphics/shapes/Cubic;", "cubic", _UrlKt.FRAGMENT_ENCODE_SET, "startOutlineProgress", "endOutlineProgress", "<init>", "(Landroidx/graphics/shapes/MeasuredPolygon;Landroidx/graphics/shapes/Cubic;FF)V", _UrlKt.FRAGMENT_ENCODE_SET, "updateProgressRange$graphics_shapes_release", "(FF)V", "updateProgressRange", "cutOutlineProgress", "Lkotlin/Pair;", "Landroidx/graphics/shapes/MeasuredPolygon;", "cutAtProgress", "(F)Lkotlin/Pair;", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "Landroidx/graphics/shapes/Cubic;", "getCubic", "()Landroidx/graphics/shapes/Cubic;", "measuredSize", "F", "getMeasuredSize", "()F", "<set-?>", "getStartOutlineProgress", "getEndOutlineProgress", "graphics-shapes_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nPolygonMeasure.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PolygonMeasure.kt\nandroidx/graphics/shapes/MeasuredPolygon$MeasuredCubic\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Utils.kt\nandroidx/graphics/shapes/Utils\n*L\n1#1,353:1\n1#2:354\n108#3,4:355\n*S KotlinDebug\n*F\n+ 1 PolygonMeasure.kt\nandroidx/graphics/shapes/MeasuredPolygon$MeasuredCubic\n*L\n128#1:355,4\n*E\n"})
    public final class MeasuredCubic {
        private final Cubic cubic;
        private float endOutlineProgress;
        private final float measuredSize;
        private float startOutlineProgress;
        final /* synthetic */ MeasuredPolygon this$0;

        public MeasuredCubic(MeasuredPolygon r2, Cubic r3, float r4, float r5) {
            this.this$0 = r2;
            this.cubic = r3;
            if (r5 < r4) goto L6;
            this.measuredSize = MeasuredPolygon.access$getMeasurer$p(r2).measureCubic(r3);
            this.startOutlineProgress = r4;
            this.endOutlineProgress = r5;
            return;
        L6:
            f$$ExternalSyntheticBUOutline1.m("endOutlineProgress is expected to be equal or greater than startOutlineProgress");
            throw null;
        }

        public static /* synthetic */ void updateProgressRange$graphics_shapes_release$default(MeasuredCubic r0, float r1, float r2, int r3, Object r4) {
            if ((r3 & 1) == 0) goto L6;
            r1 = r0.startOutlineProgress;
        L6:
            if ((r3 & 2) == 0) goto L8;
            r2 = r0.endOutlineProgress;
        L8:
            r0.updateProgressRange$graphics_shapes_release(r1, r2);
        }

        public final Pair<MeasuredCubic, MeasuredCubic> cutAtProgress(float r6) {
            float r62 = RangesKt.coerceIn(r6, this.startOutlineProgress, this.endOutlineProgress);
            float r0 = this.endOutlineProgress;
            float r1 = this.startOutlineProgress;
            float r02 = r0 - r1;
            float r12 = (r62 - r1) / r02;
            float r03 = MeasuredPolygon.access$getMeasurer$p(this.this$0).findCubicCutPoint(this.cubic, r12 * this.measuredSize);
            if (0.0f <= r03) goto L5;
        L8:
            f$$ExternalSyntheticBUOutline1.m("Cubic cut point is expected to be between 0 and 1");
            return null;
        L5:
            if (r03 > 1.0f) goto L8;
            PolygonMeasureKt.access$getLOG_TAG$p();
            Pair<Cubic, Cubic> r04 = this.cubic.split(r03);
            Cubic r13 = r04.component1();
            Cubic r05 = r04.component2();
            return TuplesKt.to(new MeasuredCubic(this.this$0, r13, this.startOutlineProgress, r62), new MeasuredCubic(this.this$0, r05, r62, this.endOutlineProgress));
        }

        public final Cubic getCubic() {
            return this.cubic;
        }

        public final float getEndOutlineProgress() {
            return this.endOutlineProgress;
        }

        public final float getStartOutlineProgress() {
            return this.startOutlineProgress;
        }

        public String toString() {
            return "MeasuredCubic(outlineProgress=[" + this.startOutlineProgress + " .. " + this.endOutlineProgress + "], size=" + this.measuredSize + ", cubic=" + this.cubic + ')';
        }

        public final void updateProgressRange$graphics_shapes_release(float r2, float r3) {
            if (r3 < r2) goto L6;
            this.startOutlineProgress = r2;
            this.endOutlineProgress = r3;
            return;
        L6:
            f$$ExternalSyntheticBUOutline1.m("endOutlineProgress is expected to be equal or greater than startOutlineProgress");
        }
    }

    static {
        Companion = new Companion(null);
    }

    private MeasuredPolygon(Measurer r10, List<ProgressableFeature> r11, List<? extends Cubic> r12, FloatList r13) {
        if (r13.getSize() == (r12.size() + 1)) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Outline progress size is expected to be the cubics size + 1");
        throw null;
    L5:
        if (r13.first() == 0.0f) goto L7;
        f$$ExternalSyntheticBUOutline1.m("First outline progress value is expected to be zero");
        throw null;
    L7:
        if (r13.last() != 1.0f) goto L16;
        this.measurer = r10;
        this.features = r11;
        ArrayList r102 = new ArrayList();
        int r112 = r12.size();
        int r0 = 0;
        float r5 = 0.0f;
    L9:
        if (r0 >= r112) goto L14;
        int r6 = r0 + 1;
        if ((r13.get(r6) - r13.get(r0)) <= 1.0E-4f) goto L13;
        r102.add(new MeasuredCubic(this, r12.get(r0), r5, r13.get(r6)));
        r5 = r13.get(r6);
    L13:
        r0 = r6;
        goto L9
    L14:
        MeasuredCubic.updateProgressRange$graphics_shapes_release$default((MeasuredCubic) r102.get(CollectionsKt.getLastIndex(r102)), 0.0f, 1.0f, 1, null);
        this.cubics = r102;
        return;
    L16:
        f$$ExternalSyntheticBUOutline1.m("Last outline progress value is expected to be one");
        throw null;
    }

    public /* synthetic */ MeasuredPolygon(Measurer r1, List r2, List r3, FloatList r4, DefaultConstructorMarker r5) {
        this(r1, r2, r3, r4);
    }

    public static final /* synthetic */ Measurer access$getMeasurer$p(MeasuredPolygon r0) {
        return r0.measurer;
    }

    public /* bridge */ boolean contains(MeasuredCubic r1) {
        return super.contains(r1);
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean contains(Object r2) {
        if ((r2 instanceof MeasuredCubic) == true) goto L7;
        return false;
    L7:
        return contains((MeasuredCubic) r2);
    }

    public final MeasuredPolygon cutAndShift(float r13) {
        if (0.0f <= r13) goto L5;
    L36:
        f$$ExternalSyntheticBUOutline1.m("Cutting point is expected to be between 0 and 1");
        return null;
    L5:
        if (r13 > 1.0f) goto L36;
        if (r13 >= 1.0E-4f) goto L9;
        return this;
    L9:
        Iterator<MeasuredCubic> r2 = this.cubics.iterator();
        int r3 = 0;
        int r4 = 0;
    L11:
        if (r2.hasNext() == false) goto L18;
        MeasuredCubic r5 = r2.next();
        float r6 = r5.getStartOutlineProgress();
        if (r13 > r5.getEndOutlineProgress()) goto L17;
        if (r6 > r13) goto L17;
    L19:
        Pair<MeasuredCubic, MeasuredCubic> r22 = this.cubics.get(r4).cutAtProgress(r13);
        MeasuredCubic r52 = r22.component1();
        MeasuredCubic r23 = r22.component2();
        PolygonMeasureKt.access$getLOG_TAG$p();
        List r24 = CollectionsKt.mutableListOf(new Cubic[]{r23.getCubic()});
        int r62 = this.cubics.size();
        int r8 = 1;
    L20:
        if (r8 >= r62) goto L22;
        List<MeasuredCubic> r9 = this.cubics;
        r24.add(r9.get((r8 + r4) % r9.size()).getCubic());
        r8 = r8 + 1;
        goto L20
    L22:
        r24.add(r52.getCubic());
        MutableFloatList r53 = new MutableFloatList(this.cubics.size() + 2);
        int r63 = this.cubics.size() + 2;
        int r82 = 0;
    L23:
        if (r82 >= r63) goto L31;
        if (r82 != 0) goto L27;
        float r92 = 0.0f;
    L30:
        r53.add(r92);
        r82 = r82 + 1;
        goto L23
    L27:
        if (r82 != (this.cubics.size() + 1)) goto L29;
        r92 = 1.0f;
        goto L30
    L29:
        r92 = Utils.positiveModulo(this.cubics.get(((r4 + r82) - 1) % this.cubics.size()).getEndOutlineProgress() - r13, 1.0f);
        goto L30
    L31:
        List r0 = CollectionsKt.createListBuilder();
        int r42 = this.features.size();
    L32:
        if (r3 >= r42) goto L35;
        r0.add(new ProgressableFeature(Utils.positiveModulo(this.features.get(r3).getProgress() - r13, 1.0f), this.features.get(r3).getFeature()));
        r3 = r3 + 1;
        goto L32
    L35:
        return new MeasuredPolygon(this.measurer, CollectionsKt.build(r0), r24, r53);
    L17:
        r4 = r4 + 1;
        goto L11
    L18:
        r4 = -1;
        goto L19
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public MeasuredCubic get(int r1) {
        return this.cubics.get(r1);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public /* bridge */ /* synthetic */ Object get(int r1) {
        return get(r1);
    }

    public final List<ProgressableFeature> getFeatures() {
        return this.features;
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return this.cubics.size();
    }

    public /* bridge */ int indexOf(MeasuredCubic r1) {
        return super.indexOf(r1);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object r2) {
        if ((r2 instanceof MeasuredCubic) == true) goto L7;
        return -1;
    L7:
        return indexOf((MeasuredCubic) r2);
    }

    public /* bridge */ int lastIndexOf(MeasuredCubic r1) {
        return super.lastIndexOf(r1);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object r2) {
        if ((r2 instanceof MeasuredCubic) == true) goto L7;
        return -1;
    L7:
        return lastIndexOf((MeasuredCubic) r2);
    }
}
