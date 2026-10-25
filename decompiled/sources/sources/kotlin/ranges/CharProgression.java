package kotlin.ranges;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0016\u0018\u0000 \u001a2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001aB!\b@\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\n\u0010\u0010\u001a\u00020\u0011H\u0096\u0082\u0004J\n\u0010\u0012\u001a\u00020\u0013H\u0096\u0080\u0004J\u0014\u0010\u0014\u001a\u00020\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0096\u0082\u0004J\n\u0010\u0017\u001a\u00020\u0006H\u0096\u0080\u0004J\n\u0010\u0018\u001a\u00020\u0019H\u0096\u0080\u0004R\u0015\u0010\t\u001a\u00020\u0002X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0015\u0010\f\u001a\u00020\u0002X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0015\u0010\u0005\u001a\u00020\u0006X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lkotlin/ranges/CharProgression;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "start", "endInclusive", "step", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(CCI)V", "first", "getFirst", "()C", "last", "getLast", "getStep", "()I", "iterator", "Lkotlin/collections/CharIterator;", "isEmpty", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "other", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public class CharProgression implements Iterable<Character>, KMappedMarker {
    public static final Companion Companion = null;
    private final char first;
    private final char last;
    private final int step;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0086\u0080\u0004¨\u0006\u000b"}, d2 = {"Lkotlin/ranges/CharProgression$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "fromClosedRange", "Lkotlin/ranges/CharProgression;", "rangeStart", _UrlKt.FRAGMENT_ENCODE_SET, "rangeEnd", "step", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final CharProgression fromClosedRange(char r1, char r2, int r3) {
            return new CharProgression(r1, r2, r3);
        }
    }

    static {
        Companion = new Companion(null);
    }

    public CharProgression(char r2, char r3, int r4) {
        if (r4 != 0) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Step must be non-zero.");
        throw null;
    L5:
        if (r4 == Integer.MIN_VALUE) goto L8;
        this.first = r2;
        this.last = (char) ProgressionUtilKt.getProgressionLastElement(r2, r3, r4);
        this.step = r4;
        return;
    L8:
        f$$ExternalSyntheticBUOutline1.m("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
        throw null;
    }

    public boolean equals(Object r3) {
        if ((r3 instanceof CharProgression) == true) goto L5;
        return false;
    L5:
        if (isEmpty() == true) goto L7;
    L8:
        CharProgression r32 = (CharProgression) r3;
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
        if (((CharProgression) r3).isEmpty() == false) goto L8;
        return true;
    }

    public final char getFirst() {
        return this.first;
    }

    public final char getLast() {
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
        int r4 = Intrinsics.compare(this.first, this.last);
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
    public /* bridge */ /* synthetic */ Iterator<Character> iterator() {
        return iterator2();
    }

    @Override // java.lang.Iterable
    /* renamed from: iterator, reason: avoid collision after fix types in other method */
    public Iterator<Character> iterator2() {
        return new CharProgressionIterator(this.first, this.last, this.step);
    }

    public String toString() {
        int r0 = this.step;
        char r1 = this.first;
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
