package kotlin.ranges;

import androidx.collection.MutableScatterMap$$ExternalSyntheticBackport0;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.ULong;
import kotlin.internal.UProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;
import okhttp3.internal.url._UrlKt;

@SinceKotlin(version = "1.5")
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010(\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0017\u0018\u0000 \u001b2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001bB!\b@\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u0011H\u0086\u0082\u0004J\n\u0010\u0012\u001a\u00020\u0013H\u0096\u0080\u0004J\u0014\u0010\u0014\u001a\u00020\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0096\u0082\u0004J\n\u0010\u0017\u001a\u00020\u0018H\u0096\u0080\u0004J\n\u0010\u0019\u001a\u00020\u001aH\u0096\u0080\u0004R\u0017\u0010\t\u001a\u00020\u0002X\u0086\u0084\b¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\r\u001a\u00020\u0002X\u0086\u0084\b¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u000e\u0010\u000bR\u0015\u0010\u0005\u001a\u00020\u0006X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u001c"}, d2 = {"Lkotlin/ranges/ULongProgression;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ULong;", "start", "endInclusive", "step", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(JJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "first", "getFirst-s-VKNKU", "()J", "J", "last", "getLast-s-VKNKU", "getStep", "iterator", _UrlKt.FRAGMENT_ENCODE_SET, "isEmpty", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "other", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "toString", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public class ULongProgression implements Iterable<ULong>, KMappedMarker {
    public static final Companion Companion = null;
    private final long first;
    private final long last;
    private final long step;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J)\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0086\u0080\u0004¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lkotlin/ranges/ULongProgression$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "fromClosedRange", "Lkotlin/ranges/ULongProgression;", "rangeStart", "Lkotlin/ULong;", "rangeEnd", "step", _UrlKt.FRAGMENT_ENCODE_SET, "fromClosedRange-7ftBX0g", "(JJJ)Lkotlin/ranges/ULongProgression;", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        /* renamed from: fromClosedRange-7ftBX0g, reason: not valid java name */
        public final ULongProgression m3269fromClosedRange7ftBX0g(long r9, long r11, long r13) {
            return new ULongProgression(r9, r11, r13, null);
        }
    }

    static {
        Companion = new Companion(null);
    }

    private ULongProgression(long r3, long r5, long r7) {
        if (r7 != 0) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Step must be non-zero.");
        throw null;
    L5:
        if (r7 == Long.MIN_VALUE) goto L8;
        this.first = r3;
        this.last = UProgressionUtilKt.m3239getProgressionLastElement7ftBX0g(r3, r5, r7);
        this.step = r7;
        return;
    L8:
        f$$ExternalSyntheticBUOutline1.m("Step must be greater than Long.MIN_VALUE to avoid overflow on negation.");
        throw null;
    }

    public /* synthetic */ ULongProgression(long r1, long r3, long r5, DefaultConstructorMarker r7) {
        this(r1, r3, r5);
    }

    public boolean equals(Object r5) {
        if ((r5 instanceof ULongProgression) == true) goto L5;
        return false;
    L5:
        if (isEmpty() == true) goto L7;
    L8:
        ULongProgression r52 = (ULongProgression) r5;
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
        if (((ULongProgression) r5).isEmpty() == false) goto L8;
        return true;
    }

    /* renamed from: getFirst-s-VKNKU, reason: not valid java name */
    public final long m3267getFirstsVKNKU() {
        return this.first;
    }

    /* renamed from: getLast-s-VKNKU, reason: not valid java name */
    public final long m3268getLastsVKNKU() {
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
        int r02 = ((int) ULong.m2192constructorimpl(r0 ^ ULong.m2192constructorimpl(r0 >>> 32))) * 31;
        long r3 = this.last;
        int r03 = (r02 + ((int) ULong.m2192constructorimpl(r3 ^ ULong.m2192constructorimpl(r3 >>> 32)))) * 31;
        long r32 = this.step;
        return r03 + ((int) ((r32 >>> 32) ^ r32));
    }

    public boolean isEmpty() {
        long r0 = this.step;
        int r7 = MutableScatterMap$$ExternalSyntheticBackport0.m(this.first, this.last);
        if (r0 <= 0) goto L7;
        if (r7 <= 0) goto L6;
        return true;
    L6:
        return false;
    L7:
        if (r7 >= 0) goto L9;
        return true;
    L9:
        return false;
    }

    @Override // java.lang.Iterable
    public final Iterator<ULong> iterator() {
        return new ULongProgressionIterator(this.first, this.last, this.step, null);
    }

    public String toString() {
        long r0 = this.step;
        long r1 = this.first;
        if (r0 <= 0) goto L7;
        StringBuilder r02 = new StringBuilder();
        r02.append(ULong.m2238toStringimpl(r1));
        r02.append("..");
        r02.append(ULong.m2238toStringimpl(this.last));
        r02.append(" step ");
        long r12 = this.step;
    L5:
        r02.append(r12);
        return r02.toString();
    L7:
        r02 = new StringBuilder();
        r02.append(ULong.m2238toStringimpl(r1));
        r02.append(" downTo ");
        r02.append(ULong.m2238toStringimpl(this.last));
        r02.append(" step ");
        r12 = -this.step;
        goto L5
    }
}
