package kotlin.ranges;

import androidx.collection.MutableScatterMap$$ExternalSyntheticBackport0;
import kotlin.Deprecated;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.ULong;
import kotlin.WasExperimental;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@SinceKotlin(version = "1.5")
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u0000 \u001d2\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0012\u0004\u0012\u00020\u00030\u0004:\u0001\u001dB\u0019\bF\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0003H\u0096\u0082\u0004¢\u0006\u0004\b\u0013\u0010\u0014J\n\u0010\u0015\u001a\u00020\u0011H\u0096\u0080\u0004J\u0014\u0010\u0016\u001a\u00020\u00112\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0096\u0082\u0004J\n\u0010\u0019\u001a\u00020\u001aH\u0096\u0080\u0004J\n\u0010\u001b\u001a\u00020\u001cH\u0096\u0080\u0004R\u0015\u0010\u0005\u001a\u00020\u00038VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0015\u0010\u0006\u001a\u00020\u00038VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u000b\u0010\nR\u001b\u0010\f\u001a\u00020\u00038VX\u0097\u0084\b¢\u0006\f\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\n¨\u0006\u001e"}, d2 = {"Lkotlin/ranges/ULongRange;", "Lkotlin/ranges/ULongProgression;", "Lkotlin/ranges/ClosedRange;", "Lkotlin/ULong;", "Lkotlin/ranges/OpenEndRange;", "start", "endInclusive", "<init>", "(JJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getStart-s-VKNKU", "()J", "getEndInclusive-s-VKNKU", "endExclusive", "getEndExclusive-s-VKNKU$annotations", "()V", "getEndExclusive-s-VKNKU", "contains", _UrlKt.FRAGMENT_ENCODE_SET, "value", "contains-VKZWuLQ", "(J)Z", "isEmpty", "equals", "other", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "toString", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class ULongRange extends ULongProgression implements ClosedRange<ULong>, OpenEndRange<ULong> {
    public static final Companion Companion = null;
    private static final ULongRange EMPTY = null;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0015\u0010\u0004\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lkotlin/ranges/ULongRange$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "EMPTY", "Lkotlin/ranges/ULongRange;", "getEMPTY", "()Lkotlin/ranges/ULongRange;", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final ULongRange getEMPTY() {
            return ULongRange.access$getEMPTY$cp();
        }
    }

    static {
        Companion = new Companion(null);
        EMPTY = new ULongRange(-1, 0, null);
    }

    private ULongRange(long r9, long r11) {
        super(r9, r11, 1, null);
    }

    public /* synthetic */ ULongRange(long r1, long r3, DefaultConstructorMarker r5) {
        this(r1, r3);
    }

    public static final /* synthetic */ ULongRange access$getEMPTY$cp() {
        return EMPTY;
    }

    @Deprecated(message = "Can throw an exception when it's impossible to represent the value with ULong type, for example, when the range includes MAX_VALUE. It's recommended to use 'endInclusive' property that doesn't throw.")
    @SinceKotlin(version = "1.9")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    /* renamed from: getEndExclusive-s-VKNKU$annotations, reason: not valid java name */
    public static /* synthetic */ void m3271getEndExclusivesVKNKU$annotations() {
    }

    @Override // kotlin.ranges.ClosedRange, kotlin.ranges.OpenEndRange
    public /* bridge */ /* synthetic */ boolean contains(Comparable r3) {
        return m3272containsVKZWuLQ(((ULong) r3).m2244unboximpl());
    }

    /* renamed from: contains-VKZWuLQ, reason: not valid java name */
    public boolean m3272containsVKZWuLQ(long r3) {
        if (MutableScatterMap$$ExternalSyntheticBackport0.m(m3267getFirstsVKNKU(), r3) <= 0) goto L5;
        return false;
    L5:
        if (MutableScatterMap$$ExternalSyntheticBackport0.m(r3, m3268getLastsVKNKU()) > 0) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // kotlin.ranges.ULongProgression
    public boolean equals(Object r5) {
        if ((r5 instanceof ULongRange) == true) goto L5;
        return false;
    L5:
        if (isEmpty() == true) goto L7;
    L8:
        ULongRange r52 = (ULongRange) r5;
        if (m3267getFirstsVKNKU() == r52.m3267getFirstsVKNKU()) goto L11;
        return false;
    L11:
        if (m3268getLastsVKNKU() != r52.m3268getLastsVKNKU()) goto L18;
        return true;
    L18:
        return false;
    L7:
        if (((ULongRange) r5).isEmpty() == false) goto L8;
        return true;
    }

    @Override // kotlin.ranges.OpenEndRange
    public /* bridge */ /* synthetic */ Comparable getEndExclusive() {
        return ULong.m2186boximpl(m3273getEndExclusivesVKNKU());
    }

    /* renamed from: getEndExclusive-s-VKNKU, reason: not valid java name */
    public long m3273getEndExclusivesVKNKU() {
        if (m3268getLastsVKNKU() != (-1)) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Cannot return the exclusive upper bound of a range that includes MAX_VALUE.");
        return 0;
    L5:
        return ULong.m2192constructorimpl(m3268getLastsVKNKU() + ULong.m2192constructorimpl(1));
    }

    @Override // kotlin.ranges.ClosedRange
    public /* bridge */ /* synthetic */ Comparable getEndInclusive() {
        return ULong.m2186boximpl(m3274getEndInclusivesVKNKU());
    }

    /* renamed from: getEndInclusive-s-VKNKU, reason: not valid java name */
    public long m3274getEndInclusivesVKNKU() {
        return m3268getLastsVKNKU();
    }

    @Override // kotlin.ranges.ClosedRange, kotlin.ranges.OpenEndRange
    public /* bridge */ /* synthetic */ Comparable getStart() {
        return ULong.m2186boximpl(m3275getStartsVKNKU());
    }

    /* renamed from: getStart-s-VKNKU, reason: not valid java name */
    public long m3275getStartsVKNKU() {
        return m3267getFirstsVKNKU();
    }

    @Override // kotlin.ranges.ULongProgression
    public int hashCode() {
        if (isEmpty() == false) goto L7;
        return -1;
    L7:
        return (((int) ULong.m2192constructorimpl(m3267getFirstsVKNKU() ^ ULong.m2192constructorimpl(m3267getFirstsVKNKU() >>> 32))) * 31) + ((int) ULong.m2192constructorimpl(m3268getLastsVKNKU() ^ ULong.m2192constructorimpl(m3268getLastsVKNKU() >>> 32)));
    }

    @Override // kotlin.ranges.ULongProgression, kotlin.ranges.ClosedRange, kotlin.ranges.OpenEndRange
    public boolean isEmpty() {
        if (MutableScatterMap$$ExternalSyntheticBackport0.m(m3267getFirstsVKNKU(), m3268getLastsVKNKU()) <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // kotlin.ranges.ULongProgression
    public String toString() {
        return ULong.m2238toStringimpl(m3267getFirstsVKNKU()) + ".." + ULong.m2238toStringimpl(m3268getLastsVKNKU());
    }
}
