package kotlin.ranges;

import kotlin.Deprecated;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u001b2\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0012\u0004\u0012\u00020\u00030\u0004:\u0001\u001bB\u0019\bF\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0012\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0003H\u0096\u0082\u0004J\n\u0010\u0013\u001a\u00020\u0011H\u0096\u0080\u0004J\u0014\u0010\u0014\u001a\u00020\u00112\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0096\u0082\u0004J\n\u0010\u0017\u001a\u00020\u0018H\u0096\u0080\u0004J\n\u0010\u0019\u001a\u00020\u001aH\u0096\u0080\u0004R\u0015\u0010\u0005\u001a\u00020\u00038VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0015\u0010\u0006\u001a\u00020\u00038VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u000b\u0010\nR\u001b\u0010\f\u001a\u00020\u00038VX\u0097\u0084\b¢\u0006\f\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\n¨\u0006\u001c"}, d2 = {"Lkotlin/ranges/CharRange;", "Lkotlin/ranges/CharProgression;", "Lkotlin/ranges/ClosedRange;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ranges/OpenEndRange;", "start", "endInclusive", "<init>", "(CC)V", "getStart", "()Ljava/lang/Character;", "getEndInclusive", "endExclusive", "getEndExclusive$annotations", "()V", "getEndExclusive", "contains", _UrlKt.FRAGMENT_ENCODE_SET, "value", "isEmpty", "equals", "other", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "toString", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class CharRange extends CharProgression implements ClosedRange<Character>, OpenEndRange<Character> {
    public static final Companion Companion = null;
    private static final CharRange EMPTY = null;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0015\u0010\u0004\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lkotlin/ranges/CharRange$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "EMPTY", "Lkotlin/ranges/CharRange;", "getEMPTY", "()Lkotlin/ranges/CharRange;", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final CharRange getEMPTY() {
            return CharRange.access$getEMPTY$cp();
        }
    }

    static {
        Companion = new Companion(null);
        EMPTY = new CharRange(1, 0);
    }

    public CharRange(char r2, char r3) {
        super(r2, r3, 1);
    }

    public static final /* synthetic */ CharRange access$getEMPTY$cp() {
        return EMPTY;
    }

    @Deprecated(message = "Can throw an exception when it's impossible to represent the value with Char type, for example, when the range includes MAX_VALUE. It's recommended to use 'endInclusive' property that doesn't throw.")
    @SinceKotlin(version = "1.9")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    public static /* synthetic */ void getEndExclusive$annotations() {
    }

    public boolean contains(char r2) {
        if (Intrinsics.compare(getFirst(), r2) <= 0) goto L5;
        return false;
    L5:
        if (Intrinsics.compare(r2, getLast()) > 0) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // kotlin.ranges.ClosedRange, kotlin.ranges.OpenEndRange
    public /* bridge */ /* synthetic */ boolean contains(Comparable r1) {
        return contains(((Character) r1).charValue());
    }

    @Override // kotlin.ranges.CharProgression
    public boolean equals(Object r3) {
        if ((r3 instanceof CharRange) == true) goto L5;
        return false;
    L5:
        if (isEmpty() == true) goto L7;
    L8:
        CharRange r32 = (CharRange) r3;
        if (getFirst() == r32.getFirst()) goto L11;
        return false;
    L11:
        if (getLast() != r32.getLast()) goto L18;
        return true;
    L18:
        return false;
    L7:
        if (((CharRange) r3).isEmpty() == false) goto L8;
        return true;
    }

    @Override // kotlin.ranges.OpenEndRange
    public Character getEndExclusive() {
        if (getLast() != 65535) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Cannot return the exclusive upper bound of a range that includes MAX_VALUE.");
        return null;
    L5:
        return Character.valueOf((char) (getLast() + 1));
    }

    @Override // kotlin.ranges.OpenEndRange
    public /* bridge */ /* synthetic */ Comparable getEndExclusive() {
        return getEndExclusive();
    }

    @Override // kotlin.ranges.ClosedRange
    public Character getEndInclusive() {
        return Character.valueOf(getLast());
    }

    @Override // kotlin.ranges.ClosedRange
    public /* bridge */ /* synthetic */ Comparable getEndInclusive() {
        return getEndInclusive();
    }

    @Override // kotlin.ranges.ClosedRange, kotlin.ranges.OpenEndRange
    public Character getStart() {
        return Character.valueOf(getFirst());
    }

    @Override // kotlin.ranges.ClosedRange, kotlin.ranges.OpenEndRange
    public /* bridge */ /* synthetic */ Comparable getStart() {
        return getStart();
    }

    @Override // kotlin.ranges.CharProgression
    public int hashCode() {
        if (isEmpty() == false) goto L7;
        return -1;
    L7:
        return (getFirst() * 31) + getLast();
    }

    @Override // kotlin.ranges.CharProgression, kotlin.ranges.ClosedRange, kotlin.ranges.OpenEndRange
    public boolean isEmpty() {
        if (Intrinsics.compare(getFirst(), getLast()) <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // kotlin.ranges.CharProgression
    public String toString() {
        return getFirst() + ".." + getLast();
    }
}
