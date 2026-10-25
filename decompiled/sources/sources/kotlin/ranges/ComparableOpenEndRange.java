package kotlin.ranges;

import java.lang.Comparable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.OpenEndRange;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0012\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0019\bF\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\u0006\u0010\u0005\u001a\u00028\u0000¢\u0006\u0004\b\u0006\u0010\u0007J\u0014\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096\u0082\u0004J\n\u0010\u0010\u001a\u00020\u0011H\u0096\u0080\u0004J\n\u0010\u0012\u001a\u00020\u0013H\u0096\u0080\u0004R\u0017\u0010\u0004\u001a\u00028\u0000X\u0096\u0084\b¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0005\u001a\u00028\u0000X\u0096\u0084\b¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\u000b\u0010\t¨\u0006\u0014"}, d2 = {"Lkotlin/ranges/ComparableOpenEndRange;", "T", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ranges/OpenEndRange;", "start", "endExclusive", "<init>", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)V", "getStart", "()Ljava/lang/Comparable;", "Ljava/lang/Comparable;", "getEndExclusive", "equals", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "toString", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
class ComparableOpenEndRange<T extends Comparable<? super T>> implements OpenEndRange<T> {
    private final T endExclusive;
    private final T start;

    public ComparableOpenEndRange(T r1, T r2) {
        this.start = r1;
        this.endExclusive = r2;
    }

    @Override // kotlin.ranges.OpenEndRange
    public /* bridge */ boolean contains(T r1) {
        return OpenEndRange.DefaultImpls.contains(this, r1);
    }

    public boolean equals(Object r3) {
        if ((r3 instanceof ComparableOpenEndRange) == true) goto L5;
        return false;
    L5:
        if (isEmpty() == true) goto L7;
    L8:
        ComparableOpenEndRange r32 = (ComparableOpenEndRange) r3;
        if (Intrinsics.areEqual(getStart(), r32.getStart()) == true) goto L11;
        return false;
    L11:
        if (Intrinsics.areEqual(getEndExclusive(), r32.getEndExclusive()) == false) goto L18;
        return true;
    L18:
        return false;
    L7:
        if (((ComparableOpenEndRange) r3).isEmpty() == false) goto L8;
        return true;
    }

    @Override // kotlin.ranges.OpenEndRange
    public T getEndExclusive() {
        return this.endExclusive;
    }

    @Override // kotlin.ranges.OpenEndRange
    public T getStart() {
        return this.start;
    }

    public int hashCode() {
        if (isEmpty() == false) goto L7;
        return -1;
    L7:
        return (getStart().hashCode() * 31) + getEndExclusive().hashCode();
    }

    @Override // kotlin.ranges.OpenEndRange
    public /* bridge */ boolean isEmpty() {
        return OpenEndRange.DefaultImpls.isEmpty(this);
    }

    public String toString() {
        return getStart() + "..<" + getEndExclusive();
    }
}
