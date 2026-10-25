package kotlin.ranges;

import androidx.collection.MutableScatterMap$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UByte$$ExternalSyntheticBackport4;
import kotlin.UInt;
import kotlin.UIntArray$Iterator$$ExternalSyntheticBUOutline0;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.random.URandomKt;
import kotlin.ranges.UIntProgression;
import kotlin.ranges.ULongProgression;
import okhttp3.MediaType$Companion$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;
import okhttp3.internal.ws.WebSocketProtocol;

@Metadata(d1 = {"\u0000X\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\b#\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0013\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0003\u001a\u0013\u0010\u0000\u001a\u00020\u0004*\u00020\u0005H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0006\u001a\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0087\u0080\u0004\u001a\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0004*\u00020\u0005H\u0087\u0080\u0004\u001a\u0013\u0010\b\u001a\u00020\u0001*\u00020\u0002H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0003\u001a\u0013\u0010\b\u001a\u00020\u0004*\u00020\u0005H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0006\u001a\u0010\u0010\t\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0087\u0080\u0004\u001a\u0010\u0010\t\u001a\u0004\u0018\u00010\u0004*\u00020\u0005H\u0087\u0080\u0004\u001a\u0013\u0010\n\u001a\u00020\u0001*\u00020\u000bH\u0087\u0088\u0004¢\u0006\u0002\u0010\f\u001a\u0013\u0010\n\u001a\u00020\u0004*\u00020\rH\u0087\u0088\u0004¢\u0006\u0002\u0010\u000e\u001a\u001b\u0010\n\u001a\u00020\u0001*\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000fH\u0087\u0080\u0004¢\u0006\u0002\u0010\u0010\u001a\u001b\u0010\n\u001a\u00020\u0004*\u00020\r2\u0006\u0010\n\u001a\u00020\u000fH\u0087\u0080\u0004¢\u0006\u0002\u0010\u0011\u001a\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0001*\u00020\u000bH\u0087\u0088\u0004\u001a\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0004*\u00020\rH\u0087\u0088\u0004\u001a\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u0001*\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000fH\u0087\u0080\u0004\u001a\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u0004*\u00020\r2\u0006\u0010\n\u001a\u00020\u000fH\u0087\u0080\u0004\u001a\u001d\u0010\u0013\u001a\u00020\u0014*\u00020\u000b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u0087\u008a\u0004¢\u0006\u0002\b\u0016\u001a\u001d\u0010\u0013\u001a\u00020\u0014*\u00020\r2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0004H\u0087\u008a\u0004¢\u0006\u0002\b\u0017\u001a\u001d\u0010\u0013\u001a\u00020\u0014*\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u0019H\u0087\u0082\u0004¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u001d\u0010\u0013\u001a\u00020\u0014*\u00020\r2\u0006\u0010\u0018\u001a\u00020\u0019H\u0087\u0082\u0004¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u001d\u0010\u0013\u001a\u00020\u0014*\u00020\r2\u0006\u0010\u0018\u001a\u00020\u0001H\u0087\u0082\u0004¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u001d\u0010\u0013\u001a\u00020\u0014*\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u0004H\u0087\u0082\u0004¢\u0006\u0004\b \u0010!\u001a\u001d\u0010\u0013\u001a\u00020\u0014*\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\"H\u0087\u0082\u0004¢\u0006\u0004\b#\u0010$\u001a\u001d\u0010\u0013\u001a\u00020\u0014*\u00020\r2\u0006\u0010\u0018\u001a\u00020\"H\u0087\u0082\u0004¢\u0006\u0004\b%\u0010&\u001a\u001d\u0010'\u001a\u00020\u0002*\u00020\u00192\u0006\u0010(\u001a\u00020\u0019H\u0087\u0084\u0004¢\u0006\u0004\b)\u0010*\u001a\u001d\u0010'\u001a\u00020\u0002*\u00020\u00012\u0006\u0010(\u001a\u00020\u0001H\u0087\u0084\u0004¢\u0006\u0004\b+\u0010,\u001a\u001d\u0010'\u001a\u00020\u0005*\u00020\u00042\u0006\u0010(\u001a\u00020\u0004H\u0087\u0084\u0004¢\u0006\u0004\b-\u0010.\u001a\u001d\u0010'\u001a\u00020\u0002*\u00020\"2\u0006\u0010(\u001a\u00020\"H\u0087\u0084\u0004¢\u0006\u0004\b/\u00100\u001a\u000e\u00101\u001a\u00020\u0002*\u00020\u0002H\u0087\u0080\u0004\u001a\u000e\u00101\u001a\u00020\u0005*\u00020\u0005H\u0087\u0080\u0004\u001a\u0016\u00102\u001a\u00020\u0002*\u00020\u00022\u0006\u00102\u001a\u000203H\u0087\u0084\u0004\u001a\u0016\u00102\u001a\u00020\u0005*\u00020\u00052\u0006\u00102\u001a\u000204H\u0087\u0084\u0004\u001a\u001d\u00105\u001a\u00020\u000b*\u00020\u00192\u0006\u0010(\u001a\u00020\u0019H\u0087\u0084\u0004¢\u0006\u0004\b6\u00107\u001a\u001d\u00105\u001a\u00020\u000b*\u00020\u00012\u0006\u0010(\u001a\u00020\u0001H\u0087\u0084\u0004¢\u0006\u0004\b8\u00109\u001a\u001d\u00105\u001a\u00020\r*\u00020\u00042\u0006\u0010(\u001a\u00020\u0004H\u0087\u0084\u0004¢\u0006\u0004\b:\u0010;\u001a\u001d\u00105\u001a\u00020\u000b*\u00020\"2\u0006\u0010(\u001a\u00020\"H\u0087\u0084\u0004¢\u0006\u0004\b<\u0010=\u001a\u001d\u0010>\u001a\u00020\u0001*\u00020\u00012\u0006\u0010?\u001a\u00020\u0001H\u0087\u0080\u0004¢\u0006\u0004\b@\u0010A\u001a\u001d\u0010>\u001a\u00020\u0004*\u00020\u00042\u0006\u0010?\u001a\u00020\u0004H\u0087\u0080\u0004¢\u0006\u0004\bB\u0010C\u001a\u001d\u0010>\u001a\u00020\u0019*\u00020\u00192\u0006\u0010?\u001a\u00020\u0019H\u0087\u0080\u0004¢\u0006\u0004\bD\u0010E\u001a\u001d\u0010>\u001a\u00020\"*\u00020\"2\u0006\u0010?\u001a\u00020\"H\u0087\u0080\u0004¢\u0006\u0004\bF\u0010G\u001a\u001d\u0010H\u001a\u00020\u0001*\u00020\u00012\u0006\u0010I\u001a\u00020\u0001H\u0087\u0080\u0004¢\u0006\u0004\bJ\u0010A\u001a\u001d\u0010H\u001a\u00020\u0004*\u00020\u00042\u0006\u0010I\u001a\u00020\u0004H\u0087\u0080\u0004¢\u0006\u0004\bK\u0010C\u001a\u001d\u0010H\u001a\u00020\u0019*\u00020\u00192\u0006\u0010I\u001a\u00020\u0019H\u0087\u0080\u0004¢\u0006\u0004\bL\u0010E\u001a\u001d\u0010H\u001a\u00020\"*\u00020\"2\u0006\u0010I\u001a\u00020\"H\u0087\u0080\u0004¢\u0006\u0004\bM\u0010G\u001a%\u0010N\u001a\u00020\u0001*\u00020\u00012\u0006\u0010?\u001a\u00020\u00012\u0006\u0010I\u001a\u00020\u0001H\u0087\u0080\u0004¢\u0006\u0004\bO\u0010P\u001a%\u0010N\u001a\u00020\u0004*\u00020\u00042\u0006\u0010?\u001a\u00020\u00042\u0006\u0010I\u001a\u00020\u0004H\u0087\u0080\u0004¢\u0006\u0004\bQ\u0010R\u001a%\u0010N\u001a\u00020\u0019*\u00020\u00192\u0006\u0010?\u001a\u00020\u00192\u0006\u0010I\u001a\u00020\u0019H\u0087\u0080\u0004¢\u0006\u0004\bS\u0010T\u001a%\u0010N\u001a\u00020\"*\u00020\"2\u0006\u0010?\u001a\u00020\"2\u0006\u0010I\u001a\u00020\"H\u0087\u0080\u0004¢\u0006\u0004\bU\u0010V\u001a#\u0010N\u001a\u00020\u0001*\u00020\u00012\f\u0010W\u001a\b\u0012\u0004\u0012\u00020\u00010XH\u0087\u0080\u0004¢\u0006\u0004\bY\u0010Z\u001a#\u0010N\u001a\u00020\u0004*\u00020\u00042\f\u0010W\u001a\b\u0012\u0004\u0012\u00020\u00040XH\u0087\u0080\u0004¢\u0006\u0004\b[\u0010\\¨\u0006]"}, d2 = {"first", "Lkotlin/UInt;", "Lkotlin/ranges/UIntProgression;", "(Lkotlin/ranges/UIntProgression;)I", "Lkotlin/ULong;", "Lkotlin/ranges/ULongProgression;", "(Lkotlin/ranges/ULongProgression;)J", "firstOrNull", "last", "lastOrNull", "random", "Lkotlin/ranges/UIntRange;", "(Lkotlin/ranges/UIntRange;)I", "Lkotlin/ranges/ULongRange;", "(Lkotlin/ranges/ULongRange;)J", "Lkotlin/random/Random;", "(Lkotlin/ranges/UIntRange;Lkotlin/random/Random;)I", "(Lkotlin/ranges/ULongRange;Lkotlin/random/Random;)J", "randomOrNull", "contains", _UrlKt.FRAGMENT_ENCODE_SET, "element", "contains-biwQdVI", "contains-GYNo2lE", "value", "Lkotlin/UByte;", "contains-68kG9v0", "(Lkotlin/ranges/UIntRange;B)Z", "contains-ULb-yJY", "(Lkotlin/ranges/ULongRange;B)Z", "contains-Gab390E", "(Lkotlin/ranges/ULongRange;I)Z", "contains-fz5IDCE", "(Lkotlin/ranges/UIntRange;J)Z", "Lkotlin/UShort;", "contains-ZsK3CEQ", "(Lkotlin/ranges/UIntRange;S)Z", "contains-uhHAxoY", "(Lkotlin/ranges/ULongRange;S)Z", "downTo", "to", "downTo-Kr8caGY", "(BB)Lkotlin/ranges/UIntProgression;", "downTo-J1ME1BU", "(II)Lkotlin/ranges/UIntProgression;", "downTo-eb3DHEI", "(JJ)Lkotlin/ranges/ULongProgression;", "downTo-5PvTz6A", "(SS)Lkotlin/ranges/UIntProgression;", "reversed", "step", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "until", "until-Kr8caGY", "(BB)Lkotlin/ranges/UIntRange;", "until-J1ME1BU", "(II)Lkotlin/ranges/UIntRange;", "until-eb3DHEI", "(JJ)Lkotlin/ranges/ULongRange;", "until-5PvTz6A", "(SS)Lkotlin/ranges/UIntRange;", "coerceAtLeast", "minimumValue", "coerceAtLeast-J1ME1BU", "(II)I", "coerceAtLeast-eb3DHEI", "(JJ)J", "coerceAtLeast-Kr8caGY", "(BB)B", "coerceAtLeast-5PvTz6A", "(SS)S", "coerceAtMost", "maximumValue", "coerceAtMost-J1ME1BU", "coerceAtMost-eb3DHEI", "coerceAtMost-Kr8caGY", "coerceAtMost-5PvTz6A", "coerceIn", "coerceIn-WZ9TVnA", "(III)I", "coerceIn-sambcqE", "(JJJ)J", "coerceIn-b33U2AM", "(BBB)B", "coerceIn-VKSA0NQ", "(SSS)S", "range", "Lkotlin/ranges/ClosedRange;", "coerceIn-wuiCnnA", "(ILkotlin/ranges/ClosedRange;)I", "coerceIn-JPwROB0", "(JLkotlin/ranges/ClosedRange;)J", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/ranges/URangesKt")
/* loaded from: classes5.dex */
class URangesKt___URangesKt {
    public URangesKt___URangesKt() {
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: coerceAtLeast-5PvTz6A, reason: not valid java name */
    public static final short m3276coerceAtLeast5PvTz6A(short r2, short r3) {
        if (Intrinsics.compare(r2 & UShort.MAX_VALUE, 65535 & r3) >= 0) goto L5;
        return r3;
    L5:
        return r2;
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: coerceAtLeast-J1ME1BU, reason: not valid java name */
    public static final int m3277coerceAtLeastJ1ME1BU(int r1, int r2) {
        if (UByte$$ExternalSyntheticBackport4.m(r1, r2) >= 0) goto L5;
        return r2;
    L5:
        return r1;
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: coerceAtLeast-Kr8caGY, reason: not valid java name */
    public static final byte m3278coerceAtLeastKr8caGY(byte r2, byte r3) {
        if (Intrinsics.compare(r2 & UByte.MAX_VALUE, r3 & UByte.MAX_VALUE) >= 0) goto L5;
        return r3;
    L5:
        return r2;
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: coerceAtLeast-eb3DHEI, reason: not valid java name */
    public static final long m3279coerceAtLeasteb3DHEI(long r1, long r3) {
        if (MutableScatterMap$$ExternalSyntheticBackport0.m(r1, r3) >= 0) goto L5;
        return r3;
    L5:
        return r1;
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: coerceAtMost-5PvTz6A, reason: not valid java name */
    public static final short m3280coerceAtMost5PvTz6A(short r2, short r3) {
        if (Intrinsics.compare(r2 & UShort.MAX_VALUE, 65535 & r3) <= 0) goto L5;
        return r3;
    L5:
        return r2;
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: coerceAtMost-J1ME1BU, reason: not valid java name */
    public static final int m3281coerceAtMostJ1ME1BU(int r1, int r2) {
        if (UByte$$ExternalSyntheticBackport4.m(r1, r2) <= 0) goto L5;
        return r2;
    L5:
        return r1;
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: coerceAtMost-Kr8caGY, reason: not valid java name */
    public static final byte m3282coerceAtMostKr8caGY(byte r2, byte r3) {
        if (Intrinsics.compare(r2 & UByte.MAX_VALUE, r3 & UByte.MAX_VALUE) <= 0) goto L5;
        return r3;
    L5:
        return r2;
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: coerceAtMost-eb3DHEI, reason: not valid java name */
    public static final long m3283coerceAtMosteb3DHEI(long r1, long r3) {
        if (MutableScatterMap$$ExternalSyntheticBackport0.m(r1, r3) <= 0) goto L5;
        return r3;
    L5:
        return r1;
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: coerceIn-JPwROB0, reason: not valid java name */
    public static final long m3284coerceInJPwROB0(long r2, ClosedRange<ULong> r4) {
        if ((r4 instanceof ClosedFloatingPointRange) == false) goto L7;
        return ((ULong) RangesKt___RangesKt.coerceIn(ULong.m2186boximpl(r2), (ClosedFloatingPointRange) r4)).m2244unboximpl();
    L7:
        if (r4.isEmpty() == false) goto L9;
        MediaType$Companion$$ExternalSyntheticBUOutline0.m("Cannot coerce value to an empty range: ", r4, 46);
        return 0;
    L9:
        if (MutableScatterMap$$ExternalSyntheticBackport0.m(r2, ((ULong) r4.getStart()).m2244unboximpl()) >= 0) goto L13;
        return ((ULong) r4.getStart()).m2244unboximpl();
    L13:
        if (MutableScatterMap$$ExternalSyntheticBackport0.m(r2, ((ULong) r4.getEndInclusive()).m2244unboximpl()) > 0) goto L15;
        return r2;
    L15:
        return ((ULong) r4.getEndInclusive()).m2244unboximpl();
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: coerceIn-VKSA0NQ, reason: not valid java name */
    public static final short m3285coerceInVKSA0NQ(short r4, short r5, short r6) {
        int r1 = r5 & UShort.MAX_VALUE;
        int r2 = r6 & UShort.MAX_VALUE;
        if (Intrinsics.compare(r1, r2) > 0) goto L11;
        int r0 = 65535 & r4;
        if (Intrinsics.compare(r0, r1) >= 0) goto L8;
        return r5;
    L8:
        if (Intrinsics.compare(r0, r2) <= 0) goto L10;
        return r6;
    L10:
        return r4;
    L11:
        StringBuilder r42 = new StringBuilder("Cannot coerce value to an empty range: maximum ");
        r42.append(UShort.m2343toStringimpl(r6));
        URangesKt___URangesKt$$ExternalSyntheticBUOutline0.m(r42, UShort.m2343toStringimpl(r5));
        return 0;
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: coerceIn-WZ9TVnA, reason: not valid java name */
    public static final int m3286coerceInWZ9TVnA(int r1, int r2, int r3) {
        if (UByte$$ExternalSyntheticBackport4.m(r2, r3) <= 0) goto L5;
        StringBuilder r12 = new StringBuilder("Cannot coerce value to an empty range: maximum ");
        r12.append(UInt.m2159toStringimpl(r3));
        URangesKt___URangesKt$$ExternalSyntheticBUOutline0.m(r12, UInt.m2159toStringimpl(r2));
        return 0;
    L5:
        if (UByte$$ExternalSyntheticBackport4.m(r1, r2) >= 0) goto L8;
        return r2;
    L8:
        if (UByte$$ExternalSyntheticBackport4.m(r1, r3) <= 0) goto L10;
        return r3;
    L10:
        return r1;
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: coerceIn-b33U2AM, reason: not valid java name */
    public static final byte m3287coerceInb33U2AM(byte r3, byte r4, byte r5) {
        int r0 = r4 & UByte.MAX_VALUE;
        int r1 = r5 & UByte.MAX_VALUE;
        if (Intrinsics.compare(r0, r1) > 0) goto L11;
        int r2 = r3 & UByte.MAX_VALUE;
        if (Intrinsics.compare(r2, r0) >= 0) goto L8;
        return r4;
    L8:
        if (Intrinsics.compare(r2, r1) <= 0) goto L10;
        return r5;
    L10:
        return r3;
    L11:
        StringBuilder r32 = new StringBuilder("Cannot coerce value to an empty range: maximum ");
        r32.append(UByte.m2080toStringimpl(r5));
        URangesKt___URangesKt$$ExternalSyntheticBUOutline0.m(r32, UByte.m2080toStringimpl(r4));
        return 0;
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: coerceIn-sambcqE, reason: not valid java name */
    public static final long m3288coerceInsambcqE(long r1, long r3, long r5) {
        if (MutableScatterMap$$ExternalSyntheticBackport0.m(r3, r5) <= 0) goto L5;
        StringBuilder r12 = new StringBuilder("Cannot coerce value to an empty range: maximum ");
        r12.append(ULong.m2238toStringimpl(r5));
        URangesKt___URangesKt$$ExternalSyntheticBUOutline0.m(r12, ULong.m2238toStringimpl(r3));
        return 0;
    L5:
        if (MutableScatterMap$$ExternalSyntheticBackport0.m(r1, r3) >= 0) goto L8;
        return r3;
    L8:
        if (MutableScatterMap$$ExternalSyntheticBackport0.m(r1, r5) <= 0) goto L10;
        return r5;
    L10:
        return r1;
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: coerceIn-wuiCnnA, reason: not valid java name */
    public static final int m3289coerceInwuiCnnA(int r1, ClosedRange<UInt> r2) {
        if ((r2 instanceof ClosedFloatingPointRange) == false) goto L7;
        return ((UInt) RangesKt___RangesKt.coerceIn(UInt.m2107boximpl(r1), (ClosedFloatingPointRange) r2)).m2165unboximpl();
    L7:
        if (r2.isEmpty() == false) goto L9;
        MediaType$Companion$$ExternalSyntheticBUOutline0.m("Cannot coerce value to an empty range: ", r2, 46);
        return 0;
    L9:
        if (UByte$$ExternalSyntheticBackport4.m(r1, ((UInt) r2.getStart()).m2165unboximpl()) >= 0) goto L13;
        return ((UInt) r2.getStart()).m2165unboximpl();
    L13:
        if (UByte$$ExternalSyntheticBackport4.m(r1, ((UInt) r2.getEndInclusive()).m2165unboximpl()) > 0) goto L15;
        return r1;
    L15:
        return ((UInt) r2.getEndInclusive()).m2165unboximpl();
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: contains-68kG9v0, reason: not valid java name */
    public static final boolean m3290contains68kG9v0(UIntRange r0, byte r1) {
        return r0.m3263containsWZ4Q5Ns(UInt.m2113constructorimpl(r1 & UByte.MAX_VALUE));
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    /* renamed from: contains-GYNo2lE, reason: not valid java name */
    private static final boolean m3291containsGYNo2lE(ULongRange r2, ULong r3) {
        if (r3 != null) goto L4;
        return false;
    L4:
        if (r2.m3272containsVKZWuLQ(r3.m2244unboximpl()) == false) goto L9;
        return true;
    L9:
        return false;
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: contains-Gab390E, reason: not valid java name */
    public static final boolean m3292containsGab390E(ULongRange r4, int r5) {
        return r4.m3272containsVKZWuLQ(ULong.m2192constructorimpl(r5 & 4294967295L));
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: contains-ULb-yJY, reason: not valid java name */
    public static final boolean m3293containsULbyJY(ULongRange r4, byte r5) {
        return r4.m3272containsVKZWuLQ(ULong.m2192constructorimpl(r5 & 255));
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: contains-ZsK3CEQ, reason: not valid java name */
    public static final boolean m3294containsZsK3CEQ(UIntRange r1, short r2) {
        return r1.m3263containsWZ4Q5Ns(UInt.m2113constructorimpl(r2 & UShort.MAX_VALUE));
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    /* renamed from: contains-biwQdVI, reason: not valid java name */
    private static final boolean m3295containsbiwQdVI(UIntRange r0, UInt r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (r0.m3263containsWZ4Q5Ns(r1.m2165unboximpl()) == false) goto L9;
        return true;
    L9:
        return false;
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: contains-fz5IDCE, reason: not valid java name */
    public static final boolean m3296containsfz5IDCE(UIntRange r4, long r5) {
        if (ULong.m2192constructorimpl(r5 >>> 32) == 0) goto L5;
        return false;
    L5:
        if (r4.m3263containsWZ4Q5Ns(UInt.m2113constructorimpl((int) r5)) == false) goto L10;
        return true;
    L10:
        return false;
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: contains-uhHAxoY, reason: not valid java name */
    public static final boolean m3297containsuhHAxoY(ULongRange r4, short r5) {
        return r4.m3272containsVKZWuLQ(ULong.m2192constructorimpl(r5 & WebSocketProtocol.PAYLOAD_SHORT_MAX));
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: downTo-5PvTz6A, reason: not valid java name */
    public static final UIntProgression m3298downTo5PvTz6A(short r2, short r3) {
        return UIntProgression.Companion.m3260fromClosedRangeNkh28Cs(UInt.m2113constructorimpl(r2 & UShort.MAX_VALUE), UInt.m2113constructorimpl(r3 & UShort.MAX_VALUE), -1);
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: downTo-J1ME1BU, reason: not valid java name */
    public static final UIntProgression m3299downToJ1ME1BU(int r2, int r3) {
        return UIntProgression.Companion.m3260fromClosedRangeNkh28Cs(r2, r3, -1);
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: downTo-Kr8caGY, reason: not valid java name */
    public static final UIntProgression m3300downToKr8caGY(byte r2, byte r3) {
        return UIntProgression.Companion.m3260fromClosedRangeNkh28Cs(UInt.m2113constructorimpl(r2 & UByte.MAX_VALUE), UInt.m2113constructorimpl(r3 & UByte.MAX_VALUE), -1);
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: downTo-eb3DHEI, reason: not valid java name */
    public static final ULongProgression m3301downToeb3DHEI(long r7, long r9) {
        return ULongProgression.Companion.m3269fromClosedRange7ftBX0g(r7, r9, -1);
    }

    @SinceKotlin(version = "1.7")
    public static final int first(UIntProgression r2) {
        if (r2.isEmpty() == false) goto L5;
        RangesKt___RangesKt$$ExternalSyntheticBUOutline0.m("Progression ", r2, " is empty.");
        return 0;
    L5:
        return r2.m3258getFirstpVg5ArA();
    }

    @SinceKotlin(version = "1.7")
    public static final long first(ULongProgression r2) {
        if (r2.isEmpty() == false) goto L5;
        RangesKt___RangesKt$$ExternalSyntheticBUOutline0.m("Progression ", r2, " is empty.");
        return 0;
    L5:
        return r2.m3267getFirstsVKNKU();
    }

    @SinceKotlin(version = "1.7")
    public static final UInt firstOrNull(UIntProgression r1) {
        if (r1.isEmpty() == false) goto L7;
        return null;
    L7:
        return UInt.m2107boximpl(r1.m3258getFirstpVg5ArA());
    }

    @SinceKotlin(version = "1.7")
    public static final ULong firstOrNull(ULongProgression r2) {
        if (r2.isEmpty() == false) goto L7;
        return null;
    L7:
        return ULong.m2186boximpl(r2.m3267getFirstsVKNKU());
    }

    @SinceKotlin(version = "1.7")
    public static final int last(UIntProgression r2) {
        if (r2.isEmpty() == false) goto L5;
        RangesKt___RangesKt$$ExternalSyntheticBUOutline0.m("Progression ", r2, " is empty.");
        return 0;
    L5:
        return r2.m3259getLastpVg5ArA();
    }

    @SinceKotlin(version = "1.7")
    public static final long last(ULongProgression r2) {
        if (r2.isEmpty() == false) goto L5;
        RangesKt___RangesKt$$ExternalSyntheticBUOutline0.m("Progression ", r2, " is empty.");
        return 0;
    L5:
        return r2.m3268getLastsVKNKU();
    }

    @SinceKotlin(version = "1.7")
    public static final UInt lastOrNull(UIntProgression r1) {
        if (r1.isEmpty() == false) goto L7;
        return null;
    L7:
        return UInt.m2107boximpl(r1.m3259getLastpVg5ArA());
    }

    @SinceKotlin(version = "1.7")
    public static final ULong lastOrNull(ULongProgression r2) {
        if (r2.isEmpty() == false) goto L7;
        return null;
    L7:
        return ULong.m2186boximpl(r2.m3268getLastsVKNKU());
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final int random(UIntRange r1) {
        return random(r1, Random.Default);
    }

    @SinceKotlin(version = "1.5")
    public static final int random(UIntRange r0, Random r1) {
        return URandomKt.nextUInt(r1, r0);
    L4:
        e = move-exception;
        UIntArray$Iterator$$ExternalSyntheticBUOutline0.m(e.getMessage());
        return 0;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final long random(ULongRange r2) {
        return random(r2, Random.Default);
    }

    @SinceKotlin(version = "1.5")
    public static final long random(ULongRange r0, Random r1) {
        return URandomKt.nextULong(r1, r0);
    L4:
        e = move-exception;
        UIntArray$Iterator$$ExternalSyntheticBUOutline0.m(e.getMessage());
        return 0;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final UInt randomOrNull(UIntRange r1) {
        return randomOrNull(r1, Random.Default);
    }

    @SinceKotlin(version = "1.5")
    public static final UInt randomOrNull(UIntRange r1, Random r2) {
        if (r1.isEmpty() == false) goto L7;
        return null;
    L7:
        return UInt.m2107boximpl(URandomKt.nextUInt(r2, r1));
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final ULong randomOrNull(ULongRange r1) {
        return randomOrNull(r1, Random.Default);
    }

    @SinceKotlin(version = "1.5")
    public static final ULong randomOrNull(ULongRange r1, Random r2) {
        if (r1.isEmpty() == false) goto L7;
        return null;
    L7:
        return ULong.m2186boximpl(URandomKt.nextULong(r2, r1));
    }

    @SinceKotlin(version = "1.5")
    public static final UIntProgression reversed(UIntProgression r3) {
        return UIntProgression.Companion.m3260fromClosedRangeNkh28Cs(r3.m3259getLastpVg5ArA(), r3.m3258getFirstpVg5ArA(), -r3.getStep());
    }

    @SinceKotlin(version = "1.5")
    public static final ULongProgression reversed(ULongProgression r7) {
        return ULongProgression.Companion.m3269fromClosedRange7ftBX0g(r7.m3268getLastsVKNKU(), r7.m3267getFirstsVKNKU(), -r7.getStep());
    }

    @SinceKotlin(version = "1.5")
    public static final UIntProgression step(UIntProgression r3, int r4) {
        if (r4 <= 0) goto L4;
        boolean r0 = true;
    L5:
        RangesKt__RangesKt.checkStepIsPositive(r0, Integer.valueOf(r4));
        UIntProgression.Companion r02 = UIntProgression.Companion;
        int r1 = r3.m3258getFirstpVg5ArA();
        int r2 = r3.m3259getLastpVg5ArA();
        if (r3.getStep() > 0) goto L10;
        r4 = -r4;
    L10:
        return r02.m3260fromClosedRangeNkh28Cs(r1, r2, r4);
    L4:
        r0 = false;
        goto L5
    }

    @SinceKotlin(version = "1.5")
    public static final ULongProgression step(ULongProgression r11, long r12) {
        if (r12 <= 0) goto L5;
        boolean r2 = true;
    L6:
        RangesKt__RangesKt.checkStepIsPositive(r2, Long.valueOf(r12));
        ULongProgression.Companion r4 = ULongProgression.Companion;
        long r5 = r11.m3267getFirstsVKNKU();
        long r7 = r11.m3268getLastsVKNKU();
        if (r11.getStep() > 0) goto L11;
        r12 = -r12;
    L11:
        return r4.m3269fromClosedRange7ftBX0g(r5, r7, r12);
    L5:
        r2 = false;
        goto L6
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: until-5PvTz6A, reason: not valid java name */
    public static final UIntRange m3302until5PvTz6A(short r2, short r3) {
        if (Intrinsics.compare(r3 & UShort.MAX_VALUE, 0) > 0) goto L7;
        return UIntRange.Companion.getEMPTY();
    L7:
        return new UIntRange(UInt.m2113constructorimpl(r2 & UShort.MAX_VALUE), UInt.m2113constructorimpl(UInt.m2113constructorimpl(r3) - 1), null);
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: until-J1ME1BU, reason: not valid java name */
    public static UIntRange m3303untilJ1ME1BU(int r2, int r3) {
        if (UByte$$ExternalSyntheticBackport4.m(r3, 0) > 0) goto L7;
        return UIntRange.Companion.getEMPTY();
    L7:
        return new UIntRange(r2, UInt.m2113constructorimpl(r3 - 1), null);
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: until-Kr8caGY, reason: not valid java name */
    public static final UIntRange m3304untilKr8caGY(byte r2, byte r3) {
        if (Intrinsics.compare(r3 & UByte.MAX_VALUE, 0) > 0) goto L7;
        return UIntRange.Companion.getEMPTY();
    L7:
        return new UIntRange(UInt.m2113constructorimpl(r2 & UByte.MAX_VALUE), UInt.m2113constructorimpl(UInt.m2113constructorimpl(r3) - 1), null);
    }

    @SinceKotlin(version = "1.5")
    /* renamed from: until-eb3DHEI, reason: not valid java name */
    public static ULongRange m3305untileb3DHEI(long r6, long r8) {
        if (MutableScatterMap$$ExternalSyntheticBackport0.m(r8, 0) > 0) goto L7;
        return ULongRange.Companion.getEMPTY();
    L7:
        return new ULongRange(r6, ULong.m2192constructorimpl(r8 - ULong.m2192constructorimpl(1)), null);
    }
}
