package kotlin.ranges;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00182\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0018B!\b@\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\u000e\u001a\u00020\u000fH\u0096\u0082\u0004J\n\u0010\u0010\u001a\u00020\u0011H\u0096\u0080\u0004J\u0014\u0010\u0012\u001a\u00020\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0096\u0082\u0004J\n\u0010\u0015\u001a\u00020\u0002H\u0096\u0080\u0004J\n\u0010\u0016\u001a\u00020\u0017H\u0096\u0080\u0004R\u0015\u0010\b\u001a\u00020\u0002X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0015\u0010\u000b\u001a\u00020\u0002X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0015\u0010\u0005\u001a\u00020\u0002X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u0019"}, d2 = {"Lkotlin/ranges/IntProgression;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "start", "endInclusive", "step", "<init>", "(III)V", "first", "getFirst", "()I", "last", "getLast", "getStep", "iterator", "Lkotlin/collections/IntIterator;", "isEmpty", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "other", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public class IntProgression implements Iterable<Integer>, KMappedMarker {
    public static final Companion Companion = null;
    private final int first;
    private final int last;
    private final int step;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0086\u0080\u0004¨\u0006\n"}, d2 = {"Lkotlin/ranges/IntProgression$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "fromClosedRange", "Lkotlin/ranges/IntProgression;", "rangeStart", _UrlKt.FRAGMENT_ENCODE_SET, "rangeEnd", "step", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final IntProgression fromClosedRange(int r1, int r2, int r3) {
            return new IntProgression(r1, r2, r3);
        }
    }

    static {
        Companion = new Companion(null);
    }

    public IntProgression(int r2, int r3, int r4) {
        if (r4 != 0) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Step must be non-zero.");
        throw null;
    L5:
        if (r4 == Integer.MIN_VALUE) goto L8;
        this.first = r2;
        this.last = ProgressionUtilKt.getProgressionLastElement(r2, r3, r4);
        this.step = r4;
        return;
    L8:
        f$$ExternalSyntheticBUOutline1.m("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
        throw null;
    }

    public boolean equals(Object r3) {
        if ((r3 instanceof IntProgression) == true) goto L5;
        return false;
    L5:
        if (isEmpty() == true) goto L7;
    L8:
        IntProgression r32 = (IntProgression) r3;
        if (this.first == r32.first) goto L11;
        return false;
    L11:
        if (this.last == r32.last) goto L13;
        return false;
    L13:
        if (this.step != r32.step) goto L21;
        return true;
    L21:
        return false;
    L7:
        if (((IntProgression) r3).isEmpty() == false) goto L8;
        return true;
    }

    public final int getFirst() {
        return this.first;
    }

    public final int getLast() {
        return this.last;
    }

    public final int getStep() {
        return this.step;
    }

    public int hashCode() {
        if (isEmpty() == false) goto L7;
        return -1;
    L7:
        return (((this.first * 31) + this.last) * 31) + this.step;
    }

    public boolean isEmpty() {
        int r0 = this.step;
        int r1 = this.first;
        int r4 = this.last;
        if (r0 <= 0) goto L7;
        if (r1 <= r4) goto L6;
        return true;
    L6:
        return false;
    L7:
        if (r1 >= r4) goto L9;
        return true;
    L9:
        return false;
    }

    @Override // java.lang.Iterable
    public /* bridge */ /* synthetic */ Iterator<Integer> iterator() {
        return iterator2();
    }

    @Override // java.lang.Iterable
    /* renamed from: iterator, reason: avoid collision after fix types in other method */
    public Iterator<Integer> iterator2() {
        return new IntProgressionIterator(this.first, this.last, this.step);
    }

    public String toString() {
        int r0 = this.step;
        int r1 = this.first;
        if (r0 <= 0) goto L7;
        StringBuilder r02 = new StringBuilder();
        r02.append(r1);
        r02.append("..");
        r02.append(this.last);
        r02.append(" step ");
        int r3 = this.step;
    L5:
        r02.append(r3);
        return r02.toString();
    L7:
        r02 = new StringBuilder();
        r02.append(r1);
        r02.append(" downTo ");
        r02.append(this.last);
        r02.append(" step ");
        r3 = -this.step;
        goto L5
    }
}
