package kotlin;

import androidx.collection.MutableScatterMap$$ExternalSyntheticBackport0;
import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.internal.InlineOnly;
import kotlin.internal.IntrinsicConstEvaluation;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.UIntRange;
import kotlin.ranges.URangesKt;
import okhttp3.internal.url._UrlKt;

@SinceKotlin(version = "1.5")
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b-\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087@\u0018\u0000 x2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001xB\u0011\bA\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0087\u008a\u0004¢\u0006\u0004\b\u000b\u0010\fJ\u0019\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\rH\u0087\u008a\u0004¢\u0006\u0004\b\u000e\u0010\u000fJ\u0019\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0000H\u0097\u008a\u0004¢\u0006\u0004\b\u0010\u0010\u0011J\u0019\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0012H\u0087\u008a\u0004¢\u0006\u0004\b\u0013\u0010\u0014J\u0019\u0010\u0015\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nH\u0087\u008a\u0004¢\u0006\u0004\b\u0016\u0010\fJ\u0019\u0010\u0015\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\u008a\u0004¢\u0006\u0004\b\u0017\u0010\u000fJ\u0019\u0010\u0015\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\b\u0018\u0010\u0011J\u0019\u0010\u0015\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\u0012H\u0087\u008a\u0004¢\u0006\u0004\b\u0019\u0010\u001aJ\u0019\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nH\u0087\u008a\u0004¢\u0006\u0004\b\u001c\u0010\fJ\u0019\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\u008a\u0004¢\u0006\u0004\b\u001d\u0010\u000fJ\u0019\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\b\u001e\u0010\u0011J\u0019\u0010\u001b\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\u0012H\u0087\u008a\u0004¢\u0006\u0004\b\u001f\u0010\u001aJ\u0019\u0010 \u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nH\u0087\u008a\u0004¢\u0006\u0004\b!\u0010\fJ\u0019\u0010 \u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\u008a\u0004¢\u0006\u0004\b\"\u0010\u000fJ\u0019\u0010 \u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\b#\u0010\u0011J\u0019\u0010 \u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\u0012H\u0087\u008a\u0004¢\u0006\u0004\b$\u0010\u001aJ\u0019\u0010%\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nH\u0087\u008a\u0004¢\u0006\u0004\b&\u0010\fJ\u0019\u0010%\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\u008a\u0004¢\u0006\u0004\b'\u0010\u000fJ\u0019\u0010%\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\b(\u0010\u0011J\u0019\u0010%\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\u0012H\u0087\u008a\u0004¢\u0006\u0004\b)\u0010\u001aJ\u0019\u0010*\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nH\u0087\u008a\u0004¢\u0006\u0004\b+\u0010\fJ\u0019\u0010*\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\u008a\u0004¢\u0006\u0004\b,\u0010\u000fJ\u0019\u0010*\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\b-\u0010\u0011J\u0019\u0010*\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\u0012H\u0087\u008a\u0004¢\u0006\u0004\b.\u0010\u001aJ\u0019\u0010/\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nH\u0087\u0088\u0004¢\u0006\u0004\b0\u0010\fJ\u0019\u0010/\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\u0088\u0004¢\u0006\u0004\b1\u0010\u000fJ\u0019\u0010/\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\u0088\u0004¢\u0006\u0004\b2\u0010\u0011J\u0019\u0010/\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\u0012H\u0087\u0088\u0004¢\u0006\u0004\b3\u0010\u001aJ\u0019\u00104\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\nH\u0087\u0088\u0004¢\u0006\u0004\b5\u00106J\u0019\u00104\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\rH\u0087\u0088\u0004¢\u0006\u0004\b7\u00108J\u0019\u00104\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\u0088\u0004¢\u0006\u0004\b9\u0010\u0011J\u0019\u00104\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\u0012H\u0087\u0088\u0004¢\u0006\u0004\b:\u0010\u001aJ\u0011\u0010;\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\b<\u0010\u0005J\u0011\u0010=\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\b>\u0010\u0005J\u0019\u0010?\u001a\u00020@2\u0006\u0010\t\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\bA\u0010BJ\u0019\u0010C\u001a\u00020@2\u0006\u0010\t\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\bD\u0010BJ\u0019\u0010E\u001a\u00020\u00002\u0006\u0010F\u001a\u00020\u0003H\u0087\u008c\u0004¢\u0006\u0004\bG\u0010\u0011J\u0019\u0010H\u001a\u00020\u00002\u0006\u0010F\u001a\u00020\u0003H\u0087\u008c\u0004¢\u0006\u0004\bI\u0010\u0011J\u0019\u0010J\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\u008c\u0004¢\u0006\u0004\bK\u0010\u0011J\u0019\u0010L\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\u008c\u0004¢\u0006\u0004\bM\u0010\u0011J\u0019\u0010N\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\u008c\u0004¢\u0006\u0004\bO\u0010\u0011J\u0011\u0010P\u001a\u00020\u0000H\u0087\u0088\u0004¢\u0006\u0004\bQ\u0010\u0005J\u0011\u0010R\u001a\u00020SH\u0087\u0088\u0004¢\u0006\u0004\bT\u0010UJ\u0011\u0010V\u001a\u00020WH\u0087\u0088\u0004¢\u0006\u0004\bX\u0010YJ\u0011\u0010Z\u001a\u00020\u0003H\u0087\u0088\u0004¢\u0006\u0004\b[\u0010\u0005J\u0011\u0010\\\u001a\u00020]H\u0087\u0088\u0004¢\u0006\u0004\b^\u0010_J\u0011\u0010`\u001a\u00020\nH\u0087\u0088\u0004¢\u0006\u0004\ba\u0010UJ\u0011\u0010b\u001a\u00020\rH\u0087\u0088\u0004¢\u0006\u0004\bc\u0010YJ\u0011\u0010d\u001a\u00020\u0000H\u0087\u0088\u0004¢\u0006\u0004\be\u0010\u0005J\u0011\u0010f\u001a\u00020\u0012H\u0087\u0088\u0004¢\u0006\u0004\bg\u0010_J\u0011\u0010h\u001a\u00020iH\u0087\u0088\u0004¢\u0006\u0004\bj\u0010kJ\u0011\u0010l\u001a\u00020mH\u0087\u0088\u0004¢\u0006\u0004\bn\u0010oJ\u0011\u0010p\u001a\u00020qH\u0097\u0080\u0004¢\u0006\u0004\br\u0010sJ\u0014\u0010t\u001a\u00020u2\b\u0010\t\u001a\u0004\u0018\u00010vHÖ\u0083\u0004J\n\u0010w\u001a\u00020\u0003HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0084\b¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006y"}, d2 = {"Lkotlin/UInt;", _UrlKt.FRAGMENT_ENCODE_SET, "data", _UrlKt.FRAGMENT_ENCODE_SET, "constructor-impl", "(I)I", "getData$annotations", "()V", "compareTo", "other", "Lkotlin/UByte;", "compareTo-7apg3OU", "(IB)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(IS)I", "compareTo-WZ4Q5Ns", "(II)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(IJ)I", "plus", "plus-7apg3OU", "plus-xj2QHRw", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "(IJ)J", "minus", "minus-7apg3OU", "minus-xj2QHRw", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "times", "times-7apg3OU", "times-xj2QHRw", "times-WZ4Q5Ns", "times-VKZWuLQ", "div", "div-7apg3OU", "div-xj2QHRw", "div-WZ4Q5Ns", "div-VKZWuLQ", "rem", "rem-7apg3OU", "rem-xj2QHRw", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "floorDiv", "floorDiv-7apg3OU", "floorDiv-xj2QHRw", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "mod", "mod-7apg3OU", "(IB)B", "mod-xj2QHRw", "(IS)S", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "inc", "inc-pVg5ArA", "dec", "dec-pVg5ArA", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-WZ4Q5Ns", "(II)Lkotlin/ranges/UIntRange;", "rangeUntil", "rangeUntil-WZ4Q5Ns", "shl", "bitCount", "shl-pVg5ArA", "shr", "shr-pVg5ArA", "and", "and-WZ4Q5Ns", PluginsConstants.HookFilterTypes.OR, "or-WZ4Q5Ns", "xor", "xor-WZ4Q5Ns", "inv", "inv-pVg5ArA", "toByte", _UrlKt.FRAGMENT_ENCODE_SET, "toByte-impl", "(I)B", "toShort", _UrlKt.FRAGMENT_ENCODE_SET, "toShort-impl", "(I)S", "toInt", "toInt-impl", "toLong", _UrlKt.FRAGMENT_ENCODE_SET, "toLong-impl", "(I)J", "toUByte", "toUByte-w2LRezQ", "toUShort", "toUShort-Mh2AYeg", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toFloat", _UrlKt.FRAGMENT_ENCODE_SET, "toFloat-impl", "(I)F", "toDouble", _UrlKt.FRAGMENT_ENCODE_SET, "toDouble-impl", "(I)D", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "toString-impl", "(I)Ljava/lang/String;", "equals", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@JvmInline
/* loaded from: classes5.dex */
public final class UInt implements Comparable<UInt> {
    public static final Companion Companion = null;
    public static final int MAX_VALUE = -1;
    public static final int MIN_VALUE = 0;
    public static final int SIZE_BITS = 32;
    public static final int SIZE_BYTES = 4;
    private final int data;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005X\u0086Ô\b¢\u0006\u0004\n\u0002\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0005X\u0086Ô\b¢\u0006\u0004\n\u0002\u0010\u0006R\u000f\u0010\b\u001a\u00020\tX\u0086Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\n\u001a\u00020\tX\u0086Ô\b¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lkotlin/UInt$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "MIN_VALUE", "Lkotlin/UInt;", "I", "MAX_VALUE", "SIZE_BYTES", _UrlKt.FRAGMENT_ENCODE_SET, "SIZE_BITS", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
    }

    @PublishedApi
    @IntrinsicConstEvaluation
    private /* synthetic */ UInt(int r1) {
        this.data = r1;
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: and-WZ4Q5Ns, reason: not valid java name */
    private static final int m2106andWZ4Q5Ns(int r0, int r1) {
        return m2113constructorimpl(r0 & r1);
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ UInt m2107boximpl(int r1) {
        return new UInt(r1);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: compareTo-7apg3OU, reason: not valid java name */
    private static final int m2108compareTo7apg3OU(int r0, byte r1) {
        return UByte$$ExternalSyntheticBackport4.m(r0, m2113constructorimpl(r1 & UByte.MAX_VALUE));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: compareTo-VKZWuLQ, reason: not valid java name */
    private static final int m2109compareToVKZWuLQ(int r4, long r5) {
        return MutableScatterMap$$ExternalSyntheticBackport0.m(ULong.m2192constructorimpl(r4 & 4294967295L), r5);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: compareTo-WZ4Q5Ns, reason: not valid java name */
    private int m2110compareToWZ4Q5Ns(int r1) {
        return UnsignedKt.uintCompare(m2165unboximpl(), r1);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: compareTo-WZ4Q5Ns, reason: not valid java name */
    private static int m2111compareToWZ4Q5Ns(int r0, int r1) {
        return UnsignedKt.uintCompare(r0, r1);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: compareTo-xj2QHRw, reason: not valid java name */
    private static final int m2112compareToxj2QHRw(int r1, short r2) {
        return UByte$$ExternalSyntheticBackport4.m(r1, m2113constructorimpl(r2 & UShort.MAX_VALUE));
    }

    @PublishedApi
    @IntrinsicConstEvaluation
    /* renamed from: constructor-impl, reason: not valid java name */
    public static int m2113constructorimpl(int r0) {
        return r0;
    }

    @InlineOnly
    /* renamed from: dec-pVg5ArA, reason: not valid java name */
    private static final int m2114decpVg5ArA(int r0) {
        return m2113constructorimpl(r0 - 1);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: div-7apg3OU, reason: not valid java name */
    private static final int m2115div7apg3OU(int r0, byte r1) {
        return UByte$$ExternalSyntheticBackport0.m(r0, m2113constructorimpl(r1 & UByte.MAX_VALUE));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: div-VKZWuLQ, reason: not valid java name */
    private static final long m2116divVKZWuLQ(int r4, long r5) {
        return UByte$$ExternalSyntheticBackport3.m(ULong.m2192constructorimpl(r4 & 4294967295L), r5);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: div-WZ4Q5Ns, reason: not valid java name */
    private static final int m2117divWZ4Q5Ns(int r0, int r1) {
        return UnsignedKt.m2369uintDivideJ1ME1BU(r0, r1);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: div-xj2QHRw, reason: not valid java name */
    private static final int m2118divxj2QHRw(int r1, short r2) {
        return UByte$$ExternalSyntheticBackport0.m(r1, m2113constructorimpl(r2 & UShort.MAX_VALUE));
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m2119equalsimpl(int r2, Object r3) {
        if ((r3 instanceof UInt) == true) goto L6;
        return false;
    L6:
        if (r2 == ((UInt) r3).m2165unboximpl()) goto L8;
        return false;
    L8:
        return true;
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2120equalsimpl0(int r0, int r1) {
        if (r0 != r1) goto L5;
        return true;
    L5:
        return false;
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: floorDiv-7apg3OU, reason: not valid java name */
    private static final int m2121floorDiv7apg3OU(int r0, byte r1) {
        return UByte$$ExternalSyntheticBackport0.m(r0, m2113constructorimpl(r1 & UByte.MAX_VALUE));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: floorDiv-VKZWuLQ, reason: not valid java name */
    private static final long m2122floorDivVKZWuLQ(int r4, long r5) {
        return UByte$$ExternalSyntheticBackport3.m(ULong.m2192constructorimpl(r4 & 4294967295L), r5);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: floorDiv-WZ4Q5Ns, reason: not valid java name */
    private static final int m2123floorDivWZ4Q5Ns(int r0, int r1) {
        return UByte$$ExternalSyntheticBackport0.m(r0, r1);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: floorDiv-xj2QHRw, reason: not valid java name */
    private static final int m2124floorDivxj2QHRw(int r1, short r2) {
        return UByte$$ExternalSyntheticBackport0.m(r1, m2113constructorimpl(r2 & UShort.MAX_VALUE));
    }

    @PublishedApi
    public static /* synthetic */ void getData$annotations() {
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m2125hashCodeimpl(int r0) {
        return Integer.hashCode(r0);
    }

    @InlineOnly
    /* renamed from: inc-pVg5ArA, reason: not valid java name */
    private static final int m2126incpVg5ArA(int r0) {
        return m2113constructorimpl(r0 + 1);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: inv-pVg5ArA, reason: not valid java name */
    private static final int m2127invpVg5ArA(int r0) {
        return m2113constructorimpl(~r0);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: minus-7apg3OU, reason: not valid java name */
    private static final int m2128minus7apg3OU(int r0, byte r1) {
        return m2113constructorimpl(r0 - m2113constructorimpl(r1 & UByte.MAX_VALUE));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: minus-VKZWuLQ, reason: not valid java name */
    private static final long m2129minusVKZWuLQ(int r4, long r5) {
        return ULong.m2192constructorimpl(ULong.m2192constructorimpl(r4 & 4294967295L) - r5);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: minus-WZ4Q5Ns, reason: not valid java name */
    private static final int m2130minusWZ4Q5Ns(int r0, int r1) {
        return m2113constructorimpl(r0 - r1);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: minus-xj2QHRw, reason: not valid java name */
    private static final int m2131minusxj2QHRw(int r1, short r2) {
        return m2113constructorimpl(r1 - m2113constructorimpl(r2 & UShort.MAX_VALUE));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: mod-7apg3OU, reason: not valid java name */
    private static final byte m2132mod7apg3OU(int r0, byte r1) {
        return UByte.m2036constructorimpl((byte) UByte$$ExternalSyntheticBackport1.m(r0, m2113constructorimpl(r1 & UByte.MAX_VALUE)));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: mod-VKZWuLQ, reason: not valid java name */
    private static final long m2133modVKZWuLQ(int r4, long r5) {
        return UByte$$ExternalSyntheticBackport2.m(ULong.m2192constructorimpl(r4 & 4294967295L), r5);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: mod-WZ4Q5Ns, reason: not valid java name */
    private static final int m2134modWZ4Q5Ns(int r0, int r1) {
        return UByte$$ExternalSyntheticBackport1.m(r0, r1);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: mod-xj2QHRw, reason: not valid java name */
    private static final short m2135modxj2QHRw(int r1, short r2) {
        return UShort.m2299constructorimpl((short) UByte$$ExternalSyntheticBackport1.m(r1, m2113constructorimpl(r2 & UShort.MAX_VALUE)));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: or-WZ4Q5Ns, reason: not valid java name */
    private static final int m2136orWZ4Q5Ns(int r0, int r1) {
        return m2113constructorimpl(r0 | r1);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: plus-7apg3OU, reason: not valid java name */
    private static final int m2137plus7apg3OU(int r0, byte r1) {
        return m2113constructorimpl(r0 + m2113constructorimpl(r1 & UByte.MAX_VALUE));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: plus-VKZWuLQ, reason: not valid java name */
    private static final long m2138plusVKZWuLQ(int r4, long r5) {
        return ULong.m2192constructorimpl(ULong.m2192constructorimpl(r4 & 4294967295L) + r5);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: plus-WZ4Q5Ns, reason: not valid java name */
    private static final int m2139plusWZ4Q5Ns(int r0, int r1) {
        return m2113constructorimpl(r0 + r1);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: plus-xj2QHRw, reason: not valid java name */
    private static final int m2140plusxj2QHRw(int r1, short r2) {
        return m2113constructorimpl(r1 + m2113constructorimpl(r2 & UShort.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: rangeTo-WZ4Q5Ns, reason: not valid java name */
    private static final UIntRange m2141rangeToWZ4Q5Ns(int r2, int r3) {
        return new UIntRange(r2, r3, null);
    }

    @SinceKotlin(version = "1.9")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    /* renamed from: rangeUntil-WZ4Q5Ns, reason: not valid java name */
    private static final UIntRange m2142rangeUntilWZ4Q5Ns(int r0, int r1) {
        return URangesKt.m3303untilJ1ME1BU(r0, r1);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: rem-7apg3OU, reason: not valid java name */
    private static final int m2143rem7apg3OU(int r0, byte r1) {
        return UByte$$ExternalSyntheticBackport1.m(r0, m2113constructorimpl(r1 & UByte.MAX_VALUE));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: rem-VKZWuLQ, reason: not valid java name */
    private static final long m2144remVKZWuLQ(int r4, long r5) {
        return UByte$$ExternalSyntheticBackport2.m(ULong.m2192constructorimpl(r4 & 4294967295L), r5);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: rem-WZ4Q5Ns, reason: not valid java name */
    private static final int m2145remWZ4Q5Ns(int r0, int r1) {
        return UnsignedKt.m2370uintRemainderJ1ME1BU(r0, r1);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: rem-xj2QHRw, reason: not valid java name */
    private static final int m2146remxj2QHRw(int r1, short r2) {
        return UByte$$ExternalSyntheticBackport1.m(r1, m2113constructorimpl(r2 & UShort.MAX_VALUE));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: shl-pVg5ArA, reason: not valid java name */
    private static final int m2147shlpVg5ArA(int r0, int r1) {
        return m2113constructorimpl(r0 << r1);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: shr-pVg5ArA, reason: not valid java name */
    private static final int m2148shrpVg5ArA(int r0, int r1) {
        return m2113constructorimpl(r0 >>> r1);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: times-7apg3OU, reason: not valid java name */
    private static final int m2149times7apg3OU(int r0, byte r1) {
        return m2113constructorimpl(r0 * m2113constructorimpl(r1 & UByte.MAX_VALUE));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: times-VKZWuLQ, reason: not valid java name */
    private static final long m2150timesVKZWuLQ(int r4, long r5) {
        return ULong.m2192constructorimpl(ULong.m2192constructorimpl(r4 & 4294967295L) * r5);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: times-WZ4Q5Ns, reason: not valid java name */
    private static final int m2151timesWZ4Q5Ns(int r0, int r1) {
        return m2113constructorimpl(r0 * r1);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: times-xj2QHRw, reason: not valid java name */
    private static final int m2152timesxj2QHRw(int r1, short r2) {
        return m2113constructorimpl(r1 * m2113constructorimpl(r2 & UShort.MAX_VALUE));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toByte-impl, reason: not valid java name */
    private static final byte m2153toByteimpl(int r0) {
        return (byte) r0;
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toDouble-impl, reason: not valid java name */
    private static final double m2154toDoubleimpl(int r2) {
        return UnsignedKt.uintToDouble(r2);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toFloat-impl, reason: not valid java name */
    private static final float m2155toFloatimpl(int r2) {
        return (float) UnsignedKt.uintToDouble(r2);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toInt-impl, reason: not valid java name */
    private static final int m2156toIntimpl(int r0) {
        return r0;
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toLong-impl, reason: not valid java name */
    private static final long m2157toLongimpl(int r4) {
        return r4 & 4294967295L;
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toShort-impl, reason: not valid java name */
    private static final short m2158toShortimpl(int r0) {
        return (short) r0;
    }

    @IntrinsicConstEvaluation
    /* renamed from: toString-impl, reason: not valid java name */
    public static String m2159toStringimpl(int r4) {
        return String.valueOf(r4 & 4294967295L);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toUByte-w2LRezQ, reason: not valid java name */
    private static final byte m2160toUBytew2LRezQ(int r0) {
        return UByte.m2036constructorimpl((byte) r0);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toUInt-pVg5ArA, reason: not valid java name */
    private static final int m2161toUIntpVg5ArA(int r0) {
        return r0;
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toULong-s-VKNKU, reason: not valid java name */
    private static final long m2162toULongsVKNKU(int r4) {
        return ULong.m2192constructorimpl(r4 & 4294967295L);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toUShort-Mh2AYeg, reason: not valid java name */
    private static final short m2163toUShortMh2AYeg(int r0) {
        return UShort.m2299constructorimpl((short) r0);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: xor-WZ4Q5Ns, reason: not valid java name */
    private static final int m2164xorWZ4Q5Ns(int r0, int r1) {
        return m2113constructorimpl(r0 ^ r1);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(UInt r1) {
        int r12 = r1.m2165unboximpl();
        return UnsignedKt.uintCompare(m2165unboximpl(), r12);
    }

    public boolean equals(Object r1) {
        return m2119equalsimpl(this.data, r1);
    }

    public int hashCode() {
        return m2125hashCodeimpl(this.data);
    }

    @IntrinsicConstEvaluation
    public String toString() {
        return m2159toStringimpl(this.data);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m2165unboximpl() {
        return this.data;
    }
}
