package kotlin.ranges;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte$$ExternalSyntheticBackport4;
import kotlin.UInt;
import kotlin.internal.UProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;
import okhttp3.internal.url._UrlKt;

@SinceKotlin(version = "1.5")
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010(\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0017\u0018\u0000 \u001a2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001aB!\b@\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u0011H\u0086\u0082\u0004J\n\u0010\u0012\u001a\u00020\u0013H\u0096\u0080\u0004J\u0014\u0010\u0014\u001a\u00020\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0096\u0082\u0004J\n\u0010\u0017\u001a\u00020\u0006H\u0096\u0080\u0004J\n\u0010\u0018\u001a\u00020\u0019H\u0096\u0080\u0004R\u0017\u0010\t\u001a\u00020\u0002X\u0086\u0084\b¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\r\u001a\u00020\u0002X\u0086\u0084\b¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u000e\u0010\u000bR\u0015\u0010\u0005\u001a\u00020\u0006X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u001b"}, d2 = {"Lkotlin/ranges/UIntProgression;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/UInt;", "start", "endInclusive", "step", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(IIILkotlin/jvm/internal/DefaultConstructorMarker;)V", "first", "getFirst-pVg5ArA", "()I", "I", "last", "getLast-pVg5ArA", "getStep", "iterator", _UrlKt.FRAGMENT_ENCODE_SET, "isEmpty", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "other", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public class UIntProgression implements Iterable<UInt>, KMappedMarker {
    public static final Companion Companion = null;
    private final int first;
    private final int last;
    private final int step;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J)\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0086\u0080\u0004¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lkotlin/ranges/UIntProgression$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "fromClosedRange", "Lkotlin/ranges/UIntProgression;", "rangeStart", "Lkotlin/UInt;", "rangeEnd", "step", _UrlKt.FRAGMENT_ENCODE_SET, "fromClosedRange-Nkh28Cs", "(III)Lkotlin/ranges/UIntProgression;", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        /* renamed from: fromClosedRange-Nkh28Cs, reason: not valid java name */
        public final UIntProgression m3260fromClosedRangeNkh28Cs(int r2, int r3, int r4) {
            return new UIntProgression(r2, r3, r4, null);
        }
    }

    static {
        Companion = new Companion(null);
    }

    private UIntProgression(int r2, int r3, int r4) {
        if (r4 != 0) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Step must be non-zero.");
        throw null;
    L5:
        if (r4 == Integer.MIN_VALUE) goto L8;
        this.first = r2;
        this.last = UProgressionUtilKt.m3240getProgressionLastElementNkh28Cs(r2, r3, r4);
        this.step = r4;
        return;
    L8:
        f$$ExternalSyntheticBUOutline1.m("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
        throw null;
    }

    public /* synthetic */ UIntProgression(int r1, int r2, int r3, DefaultConstructorMarker r4) {
        this(r1, r2, r3);
    }

    public boolean equals(Object r3) {
        if ((r3 instanceof UIntProgression) == true) goto L5;
        return false;
    L5:
        if (isEmpty() == true) goto L7;
    L8:
        UIntProgression r32 = (UIntProgression) r3;
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
        if (((UIntProgression) r3).isEmpty() == false) goto L8;
        return true;
    }

    /* renamed from: getFirst-pVg5ArA, reason: not valid java name */
    public final int m3258getFirstpVg5ArA() {
        return this.first;
    }

    /* renamed from: getLast-pVg5ArA, reason: not valid java name */
    public final int m3259getLastpVg5ArA() {
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
        int r4 = UByte$$ExternalSyntheticBackport4.m(this.first, this.last);
        if (r0 <= 0) goto L7;
        if (r4 <= 0) goto L6;
        return true;
    L6:
        return false;
    L7:
        if (r4 >= 0) goto L9;
        return true;
    L9:
        return false;
    }

    @Override // java.lang.Iterable
    public final Iterator<UInt> iterator() {
        return new UIntProgressionIterator(this.first, this.last, this.step, null);
    }

    public String toString() {
        int r0 = this.step;
        int r1 = this.first;
        if (r0 <= 0) goto L7;
        StringBuilder r02 = new StringBuilder();
        r02.append(UInt.m2159toStringimpl(r1));
        r02.append("..");
        r02.append(UInt.m2159toStringimpl(this.last));
        r02.append(" step ");
        int r3 = this.step;
    L5:
        r02.append(r3);
        return r02.toString();
    L7:
        r02 = new StringBuilder();
        r02.append(UInt.m2159toStringimpl(r1));
        r02.append(" downTo ");
        r02.append(UInt.m2159toStringimpl(this.last));
        r02.append(" step ");
        r3 = -this.step;
        goto L5
    }
}
