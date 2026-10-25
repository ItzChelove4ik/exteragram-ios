package kotlin.collections;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.ranges.IntRange;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\b\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010!\n\u0002\b\u0002\u001a\u001f\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0082\u0080\u0004¢\u0006\u0002\b\u0004\u001a\u001f\u0010\u0005\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0082\u0080\u0004¢\u0006\u0002\b\u0006\u001a\u001f\u0010\u0007\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0082\u0080\u0004¢\u0006\u0002\b\b\u001a \u0010\t\u001a\b\u0012\u0004\u0012\u0002H\n0\u0002\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u0002H\u0086\u0080\u0004\u001a%\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\n0\u000b\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u000bH\u0087\u0080\u0004¢\u0006\u0002\b\f¨\u0006\r"}, d2 = {"reverseElementIndex", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "index", "reverseElementIndex$CollectionsKt__ReversedViewsKt", "reversePositionIndex", "reversePositionIndex$CollectionsKt__ReversedViewsKt", "reverseIteratorIndex", "reverseIteratorIndex$CollectionsKt__ReversedViewsKt", "asReversed", "T", _UrlKt.FRAGMENT_ENCODE_SET, "asReversedMutable", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/collections/CollectionsKt")
/* loaded from: classes.dex */
class CollectionsKt__ReversedViewsKt extends CollectionsKt__MutableCollectionsKt {
    public CollectionsKt__ReversedViewsKt() {
    }

    public static final /* synthetic */ int access$reverseElementIndex(List r0, int r1) {
        return reverseElementIndex$CollectionsKt__ReversedViewsKt(r0, r1);
    }

    public static final /* synthetic */ int access$reverseIteratorIndex(List r0, int r1) {
        return reverseIteratorIndex$CollectionsKt__ReversedViewsKt(r0, r1);
    }

    public static final /* synthetic */ int access$reversePositionIndex(List r0, int r1) {
        return reversePositionIndex$CollectionsKt__ReversedViewsKt(r0, r1);
    }

    public static <T> List<T> asReversed(List<? extends T> r1) {
        return new ReversedListReadOnly(r1);
    }

    @JvmName(name = "asReversedMutable")
    public static <T> List<T> asReversedMutable(List<T> r1) {
        return new ReversedList(r1);
    }

    private static final int reverseElementIndex$CollectionsKt__ReversedViewsKt(List<?> r3, int r4) {
        if (r4 < 0) goto L8;
        if (r4 > CollectionsKt__CollectionsKt.getLastIndex(r3)) goto L8;
        return CollectionsKt__CollectionsKt.getLastIndex(r3) - r4;
    L8:
        throw new IndexOutOfBoundsException("Element index " + r4 + " must be in range [" + new IntRange(0, CollectionsKt__CollectionsKt.getLastIndex(r3)) + "].");
    }

    private static final int reverseIteratorIndex$CollectionsKt__ReversedViewsKt(List<?> r0, int r1) {
        return CollectionsKt__CollectionsKt.getLastIndex(r0) - r1;
    }

    private static final int reversePositionIndex$CollectionsKt__ReversedViewsKt(List<?> r3, int r4) {
        if (r4 < 0) goto L8;
        if (r4 > r3.size()) goto L8;
        return r3.size() - r4;
    L8:
        throw new IndexOutOfBoundsException("Position index " + r4 + " must be in range [" + new IntRange(0, r3.size()) + "].");
    }
}
