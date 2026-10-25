package androidx.graphics.shapes;

import androidx.graphics.shapes.Feature;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntRange;
import okhttp3.internal.url._UrlKt;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000(\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\u001a3\u0010\u0006\u001a\u00020\u00052\u0010\u0010\u0003\u001a\f\u0012\u0004\u0012\u00020\u00010\u0000j\u0002`\u00022\u0010\u0010\u0004\u001a\f\u0012\u0004\u0012\u00020\u00010\u0000j\u0002`\u0002H\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u001f\u0010\f\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0000¢\u0006\u0004\b\f\u0010\r\u001a=\u0010\u000e\u001a\f\u0012\u0004\u0012\u00020\u00010\u0000j\u0002`\u00022\u0010\u0010\t\u001a\f\u0012\u0004\u0012\u00020\u00010\u0000j\u0002`\u00022\u0010\u0010\n\u001a\f\u0012\u0004\u0012\u00020\u00010\u0000j\u0002`\u0002H\u0000¢\u0006\u0004\b\u000e\u0010\u000f*\u0018\b\u0000\u0010\u0010\"\b\u0012\u0004\u0012\u00020\u00010\u00002\b\u0012\u0004\u0012\u00020\u00010\u0000¨\u0006\u0011"}, d2 = {_UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/graphics/shapes/ProgressableFeature;", "Landroidx/graphics/shapes/MeasuredFeatures;", "features1", "features2", "Landroidx/graphics/shapes/DoubleMapper;", "featureMapper", "(Ljava/util/List;Ljava/util/List;)Landroidx/graphics/shapes/DoubleMapper;", "Landroidx/graphics/shapes/Feature;", "f1", "f2", _UrlKt.FRAGMENT_ENCODE_SET, "featureDistSquared", "(Landroidx/graphics/shapes/Feature;Landroidx/graphics/shapes/Feature;)F", "doMapping", "(Ljava/util/List;Ljava/util/List;)Ljava/util/List;", "MeasuredFeatures", "graphics-shapes_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nFeatureMapping.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FeatureMapping.kt\nandroidx/graphics/shapes/FeatureMappingKt\n+ 2 Utils.kt\nandroidx/graphics/shapes/Utils\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 5 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,131:1\n108#2,4:132\n108#2,4:138\n108#2,4:142\n37#3,2:136\n2310#4,14:146\n2310#4,14:161\n1#5:160\n*S KotlinDebug\n*F\n+ 1 FeatureMapping.kt\nandroidx/graphics/shapes/FeatureMappingKt\n*L\n65#1:132,4\n67#1:138,4\n90#1:142,4\n66#1:136,2\n111#1:146,14\n123#1:161,14\n*E\n"})
/* loaded from: classes3.dex */
public abstract class FeatureMappingKt {
    static {
    }

    public static final List<ProgressableFeature> doMapping(List<ProgressableFeature> r13, List<ProgressableFeature> r14) {
        Iterator<Integer> r0 = CollectionsKt.getIndices(r14).iterator();
        if (r0.hasNext() == false) goto L35;
        IntIterator r1 = (IntIterator) r0;
        int r3 = r1.nextInt();
        if (r0.hasNext() == false) goto L13;
        float r4 = featureDistSquared(r13.get(0).getFeature(), r14.get(r3).getFeature());
    L8:
        int r6 = r1.nextInt();
        float r7 = featureDistSquared(r13.get(0).getFeature(), r14.get(r6).getFeature());
        if (Float.compare(r4, r7) <= 0) goto L12;
        r3 = r6;
        r4 = r7;
    L12:
        if (r0.hasNext() == true) goto L8;
    L13:
        int r02 = r13.size();
        int r12 = r14.size();
        int r42 = 1;
        List<ProgressableFeature> r5 = CollectionsKt.mutableListOf(new ProgressableFeature[]{r14.get(r3)});
        int r62 = r3;
    L14:
        if (r42 >= r02) goto L34;
        int r72 = r3 - (r02 - r42);
        if (r72 > r62) goto L19;
        r72 = r72 + r12;
    L19:
        Iterator<Integer> r63 = new IntRange(r62 + 1, r72).iterator();
        if (r63.hasNext() == false) goto L32;
        IntIterator r73 = (IntIterator) r63;
        int r8 = r73.nextInt();
        if (r63.hasNext() == true) goto L24;
    L23:
        r62 = r8;
        r5.add(r14.get(r62 % r12));
        r42 = r42 + 1;
        goto L14
    L24:
        float r9 = featureDistSquared(r13.get(r42).getFeature(), r14.get(r8 % r12).getFeature());
    L25:
        int r10 = r73.nextInt();
        float r11 = featureDistSquared(r13.get(r42).getFeature(), r14.get(r10 % r12).getFeature());
        if (Float.compare(r9, r11) <= 0) goto L29;
        r8 = r10;
        r9 = r11;
    L29:
        if (r63.hasNext() == true) goto L25;
    L32:
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    L34:
        return r5;
    L35:
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    public static final float featureDistSquared(Feature r4, Feature r5) {
        if ((r4 instanceof Feature.Corner) == true) goto L5;
    L10:
        float r0 = (((Cubic) CollectionsKt.first(r4.getCubics())).getAnchor0X() + ((Cubic) CollectionsKt.last(r4.getCubics())).getAnchor1X()) / 2.0f;
        float r2 = (((Cubic) CollectionsKt.first(r4.getCubics())).getAnchor0Y() + ((Cubic) CollectionsKt.last(r4.getCubics())).getAnchor1Y()) / 2.0f;
        float r42 = (((Cubic) CollectionsKt.first(r5.getCubics())).getAnchor0X() + ((Cubic) CollectionsKt.last(r5.getCubics())).getAnchor1X()) / 2.0f;
        float r02 = r0 - r42;
        float r22 = r2 - ((((Cubic) CollectionsKt.first(r5.getCubics())).getAnchor0Y() + ((Cubic) CollectionsKt.last(r5.getCubics())).getAnchor1Y()) / 2.0f);
        return (r02 * r02) + (r22 * r22);
    L5:
        if ((r5 instanceof Feature.Corner) == false) goto L10;
        if (((Feature.Corner) r4).getConvex() == ((Feature.Corner) r5).getConvex()) goto L10;
        return Float.MAX_VALUE;
    }

    public static final DoubleMapper featureMapper(List<ProgressableFeature> r6, List<ProgressableFeature> r7) {
        List r0 = CollectionsKt.createListBuilder();
        int r1 = r6.size();
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L8;
        if ((r6.get(r3).getFeature() instanceof Feature.Corner) == false) goto L7;
        r0.add(r6.get(r3));
    L7:
        r3 = r3 + 1;
        goto L3
    L8:
        List r62 = CollectionsKt.build(r0);
        List r02 = CollectionsKt.createListBuilder();
        int r12 = r7.size();
        int r32 = 0;
    L9:
        if (r32 >= r12) goto L14;
        if ((r7.get(r32).getFeature() instanceof Feature.Corner) == false) goto L13;
        r02.add(r7.get(r32));
    L13:
        r32 = r32 + 1;
        goto L9
    L14:
        List r72 = CollectionsKt.build(r02);
        if (r62.size() <= r72.size()) goto L17;
        Pair r63 = TuplesKt.to(doMapping(r72, r62), r72);
    L18:
        List r73 = (List) r63.component1();
        List r64 = (List) r63.component2();
        List r03 = CollectionsKt.createListBuilder();
        int r13 = r73.size();
        int r33 = 0;
    L19:
        if (r33 >= r13) goto L23;
        if (r33 == r64.size()) goto L23;
        r03.add(TuplesKt.to(Float.valueOf(((ProgressableFeature) r73.get(r33)).getProgress()), Float.valueOf(((ProgressableFeature) r64.get(r33)).getProgress())));
        r33 = r33 + 1;
    L23:
        Pair[] r65 = (Pair[]) CollectionsKt.build(r03).toArray(new Pair[0]);
        return new DoubleMapper((Pair[]) Arrays.copyOf(r65, r65.length));
    L17:
        r63 = TuplesKt.to(r62, doMapping(r62, r72));
        goto L18
    }
}
