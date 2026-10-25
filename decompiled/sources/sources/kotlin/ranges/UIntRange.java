package kotlin.ranges;

import kotlin.Deprecated;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte$$ExternalSyntheticBackport4;
import kotlin.UInt;
import kotlin.WasExperimental;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@SinceKotlin(version = "1.5")
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u0000 \u001d2\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0012\u0004\u0012\u00020\u00030\u0004:\u0001\u001dB\u0019\bF\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0003H\u0096\u0082\u0004¢\u0006\u0004\b\u0013\u0010\u0014J\n\u0010\u0015\u001a\u00020\u0011H\u0096\u0080\u0004J\u0014\u0010\u0016\u001a\u00020\u00112\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0096\u0082\u0004J\n\u0010\u0019\u001a\u00020\u001aH\u0096\u0080\u0004J\n\u0010\u001b\u001a\u00020\u001cH\u0096\u0080\u0004R\u0015\u0010\u0005\u001a\u00020\u00038VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0015\u0010\u0006\u001a\u00020\u00038VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u000b\u0010\nR\u001b\u0010\f\u001a\u00020\u00038VX\u0097\u0084\b¢\u0006\f\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\n¨\u0006\u001e"}, d2 = {"Lkotlin/ranges/UIntRange;", "Lkotlin/ranges/UIntProgression;", "Lkotlin/ranges/ClosedRange;", "Lkotlin/UInt;", "Lkotlin/ranges/OpenEndRange;", "start", "endInclusive", "<init>", "(IILkotlin/jvm/internal/DefaultConstructorMarker;)V", "getStart-pVg5ArA", "()I", "getEndInclusive-pVg5ArA", "endExclusive", "getEndExclusive-pVg5ArA$annotations", "()V", "getEndExclusive-pVg5ArA", "contains", _UrlKt.FRAGMENT_ENCODE_SET, "value", "contains-WZ4Q5Ns", "(I)Z", "isEmpty", "equals", "other", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "toString", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class UIntRange extends UIntProgression implements ClosedRange<UInt>, OpenEndRange<UInt> {
    public static final Companion Companion = null;
    private static final UIntRange EMPTY = null;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0015\u0010\u0004\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lkotlin/ranges/UIntRange$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "EMPTY", "Lkotlin/ranges/UIntRange;", "getEMPTY", "()Lkotlin/ranges/UIntRange;", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final UIntRange getEMPTY() {
            return UIntRange.access$getEMPTY$cp();
        }
    }

    static {
        Companion = new Companion(null);
        EMPTY = new UIntRange(-1, 0, null);
    }

    private UIntRange(int r3, int r4) {
        super(r3, r4, 1, null);
    }

    public /* synthetic */ UIntRange(int r1, int r2, DefaultConstructorMarker r3) {
        this(r1, r2);
    }

    public static final /* synthetic */ UIntRange access$getEMPTY$cp() {
        return EMPTY;
    }

    @Deprecated(message = "Can throw an exception when it's impossible to represent the value with UInt type, for example, when the range includes MAX_VALUE. It's recommended to use 'endInclusive' property that doesn't throw.")
    @SinceKotlin(version = "1.9")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    /* renamed from: getEndExclusive-pVg5ArA$annotations, reason: not valid java name */
    public static /* synthetic */ void m3262getEndExclusivepVg5ArA$annotations() {
    }

    @Override // kotlin.ranges.ClosedRange, kotlin.ranges.OpenEndRange
    public /* bridge */ /* synthetic */ boolean contains(Comparable r1) {
        return m3263containsWZ4Q5Ns(((UInt) r1).m2165unboximpl());
    }

    /* renamed from: contains-WZ4Q5Ns, reason: not valid java name */
    public boolean m3263containsWZ4Q5Ns(int r2) {
        if (UByte$$ExternalSyntheticBackport4.m(m3258getFirstpVg5ArA(), r2) <= 0) goto L5;
        return false;
    L5:
        if (UByte$$ExternalSyntheticBackport4.m(r2, m3259getLastpVg5ArA()) > 0) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // kotlin.ranges.UIntProgression
    public boolean equals(Object r3) {
        if ((r3 instanceof UIntRange) == true) goto L5;
        return false;
    L5:
        if (isEmpty() == true) goto L7;
    L8:
        UIntRange r32 = (UIntRange) r3;
        if (m3258getFirstpVg5ArA() == r32.m3258getFirstpVg5ArA()) goto L11;
        return false;
    L11:
        if (m3259getLastpVg5ArA() != r32.m3259getLastpVg5ArA()) goto L18;
        return true;
    L18:
        return false;
    L7:
        if (((UIntRange) r3).isEmpty() == false) goto L8;
        return true;
    }

    @Override // kotlin.ranges.OpenEndRange
    public /* bridge */ /* synthetic */ Comparable getEndExclusive() {
        return UInt.m2107boximpl(m3264getEndExclusivepVg5ArA());
    }

    /* renamed from: getEndExclusive-pVg5ArA, reason: not valid java name */
    public int m3264getEndExclusivepVg5ArA() {
        if (m3259getLastpVg5ArA() != (-1)) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Cannot return the exclusive upper bound of a range that includes MAX_VALUE.");
        return 0;
    L5:
        return UInt.m2113constructorimpl(m3259getLastpVg5ArA() + 1);
    }

    @Override // kotlin.ranges.ClosedRange
    public /* bridge */ /* synthetic */ Comparable getEndInclusive() {
        return UInt.m2107boximpl(m3265getEndInclusivepVg5ArA());
    }

    /* renamed from: getEndInclusive-pVg5ArA, reason: not valid java name */
    public int m3265getEndInclusivepVg5ArA() {
        return m3259getLastpVg5ArA();
    }

    @Override // kotlin.ranges.ClosedRange, kotlin.ranges.OpenEndRange
    public /* bridge */ /* synthetic */ Comparable getStart() {
        return UInt.m2107boximpl(m3266getStartpVg5ArA());
    }

    /* renamed from: getStart-pVg5ArA, reason: not valid java name */
    public int m3266getStartpVg5ArA() {
        return m3258getFirstpVg5ArA();
    }

    @Override // kotlin.ranges.UIntProgression
    public int hashCode() {
        if (isEmpty() == false) goto L7;
        return -1;
    L7:
        return (m3258getFirstpVg5ArA() * 31) + m3259getLastpVg5ArA();
    }

    @Override // kotlin.ranges.UIntProgression, kotlin.ranges.ClosedRange, kotlin.ranges.OpenEndRange
    public boolean isEmpty() {
        if (UByte$$ExternalSyntheticBackport4.m(m3258getFirstpVg5ArA(), m3259getLastpVg5ArA()) <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // kotlin.ranges.UIntProgression
    public String toString() {
        return UInt.m2159toStringimpl(m3258getFirstpVg5ArA()) + ".." + UInt.m2159toStringimpl(m3259getLastpVg5ArA());
    }
}
