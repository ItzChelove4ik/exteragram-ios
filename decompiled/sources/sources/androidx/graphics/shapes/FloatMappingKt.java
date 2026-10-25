package androidx.graphics.shapes;

import androidx.collection.FloatList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.UIntArray$Iterator$$ExternalSyntheticBUOutline0;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import okhttp3.internal.url._UrlKt;
import okio.Options$Companion$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0001H\u0000\u001a \u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u0001H\u0000\u001a\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0003H\u0000¨\u0006\u000e"}, d2 = {"linearMap", _UrlKt.FRAGMENT_ENCODE_SET, "xValues", "Landroidx/collection/FloatList;", "yValues", "x", "progressInRange", _UrlKt.FRAGMENT_ENCODE_SET, "progress", "progressFrom", "progressTo", "validateProgress", _UrlKt.FRAGMENT_ENCODE_SET, "p", "graphics-shapes_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nFloatMapping.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FloatMapping.kt\nandroidx/graphics/shapes/FloatMappingKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 FloatList.kt\nandroidx/collection/FloatList\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,99:1\n1#2:100\n75#3:101\n190#3,3:104\n253#3,4:107\n193#3,2:111\n258#3:113\n195#3:114\n223#4,2:102\n1774#4,4:115\n*S KotlinDebug\n*F\n+ 1 FloatMapping.kt\nandroidx/graphics/shapes/FloatMappingKt\n*L\n42#1:101\n93#1:104,3\n93#1:107,4\n93#1:111,2\n93#1:113\n93#1:114\n42#1:102,2\n96#1:115,4\n*E\n"})
/* loaded from: classes3.dex */
public abstract class FloatMappingKt {
    public static final float linearMap(FloatList r7, FloatList r8, float r9) {
        if (0.0f > r9) goto L20;
        if (r9 > 1.0f) goto L20;
        Iterator<Integer> r2 = RangesKt.until(0, r7._size).iterator();
    L8:
        if (r2.hasNext() == false) goto L17;
        int r3 = ((IntIterator) r2).nextInt();
        int r5 = r3 + 1;
        if (progressInRange(r9, r7.get(r3), r7.get(r5 % r7.getSize())) == false) goto L8;
        int r52 = r5 % r7.getSize();
        float r0 = Utils.positiveModulo(r7.get(r52) - r7.get(r3), 1.0f);
        float r22 = Utils.positiveModulo(r8.get(r52) - r8.get(r3), 1.0f);
        if (r0 >= 0.001f) goto L14;
        float r72 = 0.5f;
    L16:
        return Utils.positiveModulo(r8.get(r3) + (r22 * r72), 1.0f);
    L14:
        r72 = Utils.positiveModulo(r9 - r7.get(r3), 1.0f) / r0;
        goto L16
    L17:
        UIntArray$Iterator$$ExternalSyntheticBUOutline0.m("Collection contains no element matching the predicate.");
        return 0.0f;
    L20:
        throw new IllegalArgumentException(("Invalid progress: " + r9).toString());
    }

    public static final boolean progressInRange(float r3, float r4, float r5) {
        if (r5 < r4) goto L11;
        if (r4 <= r3) goto L7;
    L9:
        return false;
    L7:
        if (r3 > r5) goto L9;
        return true;
    L11:
        if (r3 < r4) goto L13;
    L16:
        return true;
    L13:
        if (r3 <= r5) goto L16;
        return false;
    }

    public static final void validateProgress(FloatList r12) {
        Boolean r0 = Boolean.TRUE;
        float[] r1 = r12.content;
        int r2 = r12._size;
        boolean r3 = false;
        int r4 = 0;
    L3:
        boolean r5 = true;
        if (r4 >= r2) goto L15;
        float r6 = r1[r4];
        if (r0.booleanValue() == true) goto L8;
    L12:
        r5 = false;
    L13:
        r0 = Boolean.valueOf(r5);
        r4 = r4 + 1;
        goto L3
    L8:
        if (0.0f > r6) goto L12;
        if (r6 > 1.0f) goto L12;
    L15:
        if (r0.booleanValue() == false) goto L35;
        Iterable r02 = RangesKt.until(1, r12.getSize());
        if ((r02 instanceof Collection) == true) goto L19;
    L21:
        Iterator r03 = r02.iterator();
        int r13 = 0;
    L23:
        if (r03.hasNext() == false) goto L29;
        int r22 = ((IntIterator) r03).nextInt();
        if (r12.get(r22) >= r12.get(r22 - 1)) goto L23;
        r13 = r13 + 1;
        if (r13 >= 0) goto L23;
        CollectionsKt.throwCountOverflow();
    L29:
        if (r13 > 1) goto L31;
        r3 = true;
    L31:
        if (r3 == false) goto L33;
        return;
    L33:
        Options$Companion$$ExternalSyntheticBUOutline0.m("FloatMapping - Progress wraps more than once: ", FloatList.joinToString$default(r12, null, null, null, 0, null, 31, null));
        return;
    L19:
        if (((Collection) r02).isEmpty() == false) goto L21;
        r13 = 0;
        goto L29
    L35:
        Options$Companion$$ExternalSyntheticBUOutline0.m("FloatMapping - Progress outside of range: ", FloatList.joinToString$default(r12, null, null, null, 0, null, 31, null));
    }
}
