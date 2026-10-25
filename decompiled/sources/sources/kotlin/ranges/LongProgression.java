package kotlin.ranges;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00192\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0019B!\b@\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\u000e\u001a\u00020\u000fH\u0096\u0082\u0004J\n\u0010\u0010\u001a\u00020\u0011H\u0096\u0080\u0004J\u0014\u0010\u0012\u001a\u00020\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0096\u0082\u0004J\n\u0010\u0015\u001a\u00020\u0016H\u0096\u0080\u0004J\n\u0010\u0017\u001a\u00020\u0018H\u0096\u0080\u0004R\u0015\u0010\b\u001a\u00020\u0002X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0015\u0010\u000b\u001a\u00020\u0002X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0015\u0010\u0005\u001a\u00020\u0002X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u001a"}, d2 = {"Lkotlin/ranges/LongProgression;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "start", "endInclusive", "step", "<init>", "(JJJ)V", "first", "getFirst", "()J", "last", "getLast", "getStep", "iterator", "Lkotlin/collections/LongIterator;", "isEmpty", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "other", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "toString", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public class LongProgression implements Iterable<Long>, KMappedMarker {
    public static final Companion Companion = null;
    private final long first;
    private final long last;
    private final long step;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0086\u0080\u0004¨\u0006\n"}, d2 = {"Lkotlin/ranges/LongProgression$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "fromClosedRange", "Lkotlin/ranges/LongProgression;", "rangeStart", _UrlKt.FRAGMENT_ENCODE_SET, "rangeEnd", "step", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final LongProgression fromClosedRange(long r1, long r3, long r5) {
            return new LongProgression(r1, r3, r5);
        }
    }

    static {
        Companion = new Companion(null);
    }

    public LongProgression(long r3, long r5, long r7) {
        if (r7 != 0) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Step must be non-zero.");
        throw null;
    L5:
        if (r7 == Long.MIN_VALUE) goto L8;
        this.first = r3;
        this.last = ProgressionUtilKt.getProgressionLastElement(r3, r5, r7);
        this.step = r7;
        return;
    L8:
        f$$ExternalSyntheticBUOutline1.m("Step must be greater than Long.MIN_VALUE to avoid overflow on negation.");
        throw null;
    }

    public boolean equals(Object r5) {
        if ((r5 instanceof LongProgression) == true) goto L5;
        return false;
    L5:
        if (isEmpty() == true) goto L7;
    L8:
        LongProgression r52 = (LongProgression) r5;
        if (this.first == r52.first) goto L11;
        return false;
    L11:
        if (this.last == r52.last) goto L13;
        return false;
    L13:
        if (this.step != r52.step) goto L21;
        return true;
    L21:
        return false;
    L7:
        if (((LongProgression) r5).isEmpty() == false) goto L8;
        return true;
    }

    public final long getFirst() {
        return this.first;
    }

    public final long getLast() {
        return this.last;
    }

    public final long getStep() {
        return this.step;
    }

    public int hashCode() {
        if (isEmpty() == false) goto L6;
        return -1;
    L6:
        long r0 = this.first;
        long r5 = this.last;
        long r3 = 31 * (((r0 ^ (r0 >>> 32)) * 31) + (r5 ^ (r5 >>> 32)));
        long r02 = this.step;
        return (int) (r3 + (r02 ^ (r02 >>> 32)));
    }

    public boolean isEmpty() {
        long r0 = this.step;
        long r1 = this.first;
        long r5 = this.last;
        if (r0 <= 0) goto L7;
        if (r1 <= r5) goto L6;
        return true;
    L6:
        return false;
    L7:
        if (r1 >= r5) goto L9;
        return true;
    L9:
        return false;
    }

    @Override // java.lang.Iterable
    public /* bridge */ /* synthetic */ Iterator<Long> iterator() {
        return iterator2();
    }

    @Override // java.lang.Iterable
    /* renamed from: iterator, reason: avoid collision after fix types in other method */
    public Iterator<Long> iterator2() {
        return new LongProgressionIterator(this.first, this.last, this.step);
    }

    public String toString() {
        long r0 = this.step;
        long r1 = this.first;
        if (r0 <= 0) goto L7;
        StringBuilder r02 = new StringBuilder();
        r02.append(r1);
        r02.append("..");
        r02.append(this.last);
        r02.append(" step ");
        long r12 = this.step;
    L5:
        r02.append(r12);
        return r02.toString();
    L7:
        r02 = new StringBuilder();
        r02.append(r1);
        r02.append(" downTo ");
        r02.append(this.last);
        r02.append(" step ");
        r12 = -this.step;
        goto L5
    }
}
