package kotlin;

import androidx.collection.MutableScatterMap$$ExternalSyntheticBackport0;
import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.internal.InlineOnly;
import kotlin.internal.IntrinsicConstEvaluation;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import kotlin.ranges.URangesKt;
import okhttp3.internal.url._UrlKt;

@SinceKotlin(version = "1.5")
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b-\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\n\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087@\u0018\u0000 s2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001sB\u0011\bA\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0000H\u0097\u008a\u0004¢\u0006\u0004\b\u000b\u0010\fJ\u0019\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\rH\u0087\u008a\u0004¢\u0006\u0004\b\u000e\u0010\u000fJ\u0019\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0010H\u0087\u008a\u0004¢\u0006\u0004\b\u0011\u0010\u0012J\u0019\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0013H\u0087\u008a\u0004¢\u0006\u0004\b\u0014\u0010\u0015J\u0019\u0010\u0016\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\b\u0017\u0010\fJ\u0019\u0010\u0016\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\rH\u0087\u008a\u0004¢\u0006\u0004\b\u0018\u0010\u000fJ\u0019\u0010\u0016\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\u008a\u0004¢\u0006\u0004\b\u0019\u0010\u0012J\u0019\u0010\u0016\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\u008a\u0004¢\u0006\u0004\b\u001a\u0010\u001bJ\u0019\u0010\u001c\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\b\u001d\u0010\fJ\u0019\u0010\u001c\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\rH\u0087\u008a\u0004¢\u0006\u0004\b\u001e\u0010\u000fJ\u0019\u0010\u001c\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\u008a\u0004¢\u0006\u0004\b\u001f\u0010\u0012J\u0019\u0010\u001c\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\u008a\u0004¢\u0006\u0004\b \u0010\u001bJ\u0019\u0010!\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\b\"\u0010\fJ\u0019\u0010!\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\rH\u0087\u008a\u0004¢\u0006\u0004\b#\u0010\u000fJ\u0019\u0010!\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\u008a\u0004¢\u0006\u0004\b$\u0010\u0012J\u0019\u0010!\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\u008a\u0004¢\u0006\u0004\b%\u0010\u001bJ\u0019\u0010&\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\b'\u0010\fJ\u0019\u0010&\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\rH\u0087\u008a\u0004¢\u0006\u0004\b(\u0010\u000fJ\u0019\u0010&\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\u008a\u0004¢\u0006\u0004\b)\u0010\u0012J\u0019\u0010&\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\u008a\u0004¢\u0006\u0004\b*\u0010\u001bJ\u0019\u0010+\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\b,\u0010\fJ\u0019\u0010+\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\rH\u0087\u008a\u0004¢\u0006\u0004\b-\u0010\u000fJ\u0019\u0010+\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\u008a\u0004¢\u0006\u0004\b.\u0010\u0012J\u0019\u0010+\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\u008a\u0004¢\u0006\u0004\b/\u0010\u001bJ\u0019\u00100\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0087\u0088\u0004¢\u0006\u0004\b1\u0010\fJ\u0019\u00100\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\rH\u0087\u0088\u0004¢\u0006\u0004\b2\u0010\u000fJ\u0019\u00100\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\u0088\u0004¢\u0006\u0004\b3\u0010\u0012J\u0019\u00100\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\u0088\u0004¢\u0006\u0004\b4\u0010\u001bJ\u0019\u00105\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\u0088\u0004¢\u0006\u0004\b6\u00107J\u0019\u00105\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\rH\u0087\u0088\u0004¢\u0006\u0004\b8\u00109J\u0019\u00105\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\u0088\u0004¢\u0006\u0004\b:\u0010\u0012J\u0019\u00105\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\u0088\u0004¢\u0006\u0004\b;\u0010\u001bJ\u0011\u0010<\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\b=\u0010\u0005J\u0011\u0010>\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\b?\u0010\u0005J\u0019\u0010@\u001a\u00020A2\u0006\u0010\n\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\bB\u0010CJ\u0019\u0010D\u001a\u00020A2\u0006\u0010\n\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\bE\u0010CJ\u0019\u0010F\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\u008c\u0004¢\u0006\u0004\bG\u00107J\u0019\u0010H\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\u008c\u0004¢\u0006\u0004\bI\u00107J\u0019\u0010J\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\u008c\u0004¢\u0006\u0004\bK\u00107J\u0011\u0010L\u001a\u00020\u0000H\u0087\u0088\u0004¢\u0006\u0004\bM\u0010\u0005J\u0011\u0010N\u001a\u00020\u0003H\u0087\u0088\u0004¢\u0006\u0004\bO\u0010\u0005J\u0011\u0010P\u001a\u00020QH\u0087\u0088\u0004¢\u0006\u0004\bR\u0010SJ\u0011\u0010T\u001a\u00020\tH\u0087\u0088\u0004¢\u0006\u0004\bU\u0010VJ\u0011\u0010W\u001a\u00020XH\u0087\u0088\u0004¢\u0006\u0004\bY\u0010ZJ\u0011\u0010[\u001a\u00020\u0000H\u0087\u0088\u0004¢\u0006\u0004\b\\\u0010\u0005J\u0011\u0010]\u001a\u00020\rH\u0087\u0088\u0004¢\u0006\u0004\b^\u0010SJ\u0011\u0010_\u001a\u00020\u0010H\u0087\u0088\u0004¢\u0006\u0004\b`\u0010VJ\u0011\u0010a\u001a\u00020\u0013H\u0087\u0088\u0004¢\u0006\u0004\bb\u0010ZJ\u0011\u0010c\u001a\u00020dH\u0087\u0088\u0004¢\u0006\u0004\be\u0010fJ\u0011\u0010g\u001a\u00020hH\u0087\u0088\u0004¢\u0006\u0004\bi\u0010jJ\u0011\u0010k\u001a\u00020lH\u0097\u0080\u0004¢\u0006\u0004\bm\u0010nJ\u0014\u0010o\u001a\u00020p2\b\u0010\n\u001a\u0004\u0018\u00010qHÖ\u0083\u0004J\n\u0010r\u001a\u00020\tHÖ\u0081\u0004R\u0017\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0084\b¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006t"}, d2 = {"Lkotlin/UByte;", _UrlKt.FRAGMENT_ENCODE_SET, "data", _UrlKt.FRAGMENT_ENCODE_SET, "constructor-impl", "(B)B", "getData$annotations", "()V", "compareTo", _UrlKt.FRAGMENT_ENCODE_SET, "other", "compareTo-7apg3OU", "(BB)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(BS)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(BI)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(BJ)I", "plus", "plus-7apg3OU", "plus-xj2QHRw", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "(BJ)J", "minus", "minus-7apg3OU", "minus-xj2QHRw", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "times", "times-7apg3OU", "times-xj2QHRw", "times-WZ4Q5Ns", "times-VKZWuLQ", "div", "div-7apg3OU", "div-xj2QHRw", "div-WZ4Q5Ns", "div-VKZWuLQ", "rem", "rem-7apg3OU", "rem-xj2QHRw", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "floorDiv", "floorDiv-7apg3OU", "floorDiv-xj2QHRw", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "mod", "mod-7apg3OU", "(BB)B", "mod-xj2QHRw", "(BS)S", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "inc", "inc-w2LRezQ", "dec", "dec-w2LRezQ", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-7apg3OU", "(BB)Lkotlin/ranges/UIntRange;", "rangeUntil", "rangeUntil-7apg3OU", "and", "and-7apg3OU", PluginsConstants.HookFilterTypes.OR, "or-7apg3OU", "xor", "xor-7apg3OU", "inv", "inv-w2LRezQ", "toByte", "toByte-impl", "toShort", _UrlKt.FRAGMENT_ENCODE_SET, "toShort-impl", "(B)S", "toInt", "toInt-impl", "(B)I", "toLong", _UrlKt.FRAGMENT_ENCODE_SET, "toLong-impl", "(B)J", "toUByte", "toUByte-w2LRezQ", "toUShort", "toUShort-Mh2AYeg", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toFloat", _UrlKt.FRAGMENT_ENCODE_SET, "toFloat-impl", "(B)F", "toDouble", _UrlKt.FRAGMENT_ENCODE_SET, "toDouble-impl", "(B)D", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "toString-impl", "(B)Ljava/lang/String;", "equals", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@JvmInline
/* loaded from: classes5.dex */
public final class UByte implements Comparable<UByte> {
    public static final Companion Companion = null;
    public static final byte MAX_VALUE = -1;
    public static final byte MIN_VALUE = 0;
    public static final int SIZE_BITS = 8;
    public static final int SIZE_BYTES = 1;
    private final byte data;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005X\u0086Ô\b¢\u0006\u0004\n\u0002\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0005X\u0086Ô\b¢\u0006\u0004\n\u0002\u0010\u0006R\u000f\u0010\b\u001a\u00020\tX\u0086Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\n\u001a\u00020\tX\u0086Ô\b¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lkotlin/UByte$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "MIN_VALUE", "Lkotlin/UByte;", "B", "MAX_VALUE", "SIZE_BYTES", _UrlKt.FRAGMENT_ENCODE_SET, "SIZE_BITS", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
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
    private /* synthetic */ UByte(byte r1) {
        this.data = r1;
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: and-7apg3OU, reason: not valid java name */
    private static final byte m2029and7apg3OU(byte r0, byte r1) {
        return m2036constructorimpl((byte) (r0 & r1));
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ UByte m2030boximpl(byte r1) {
        return new UByte(r1);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: compareTo-7apg3OU, reason: not valid java name */
    private int m2031compareTo7apg3OU(byte r1) {
        return Intrinsics.compare(m2086unboximpl() & MAX_VALUE, r1 & MAX_VALUE);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: compareTo-7apg3OU, reason: not valid java name */
    private static int m2032compareTo7apg3OU(byte r0, byte r1) {
        return Intrinsics.compare(r0 & MAX_VALUE, r1 & MAX_VALUE);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: compareTo-VKZWuLQ, reason: not valid java name */
    private static final int m2033compareToVKZWuLQ(byte r4, long r5) {
        return MutableScatterMap$$ExternalSyntheticBackport0.m(ULong.m2192constructorimpl(r4 & 255), r5);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: compareTo-WZ4Q5Ns, reason: not valid java name */
    private static final int m2034compareToWZ4Q5Ns(byte r0, int r1) {
        return UByte$$ExternalSyntheticBackport4.m(UInt.m2113constructorimpl(r0 & MAX_VALUE), r1);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: compareTo-xj2QHRw, reason: not valid java name */
    private static final int m2035compareToxj2QHRw(byte r1, short r2) {
        return Intrinsics.compare(r1 & MAX_VALUE, r2 & UShort.MAX_VALUE);
    }

    @PublishedApi
    @IntrinsicConstEvaluation
    /* renamed from: constructor-impl, reason: not valid java name */
    public static byte m2036constructorimpl(byte r0) {
        return r0;
    }

    @InlineOnly
    /* renamed from: dec-w2LRezQ, reason: not valid java name */
    private static final byte m2037decw2LRezQ(byte r0) {
        return m2036constructorimpl((byte) (r0 - 1));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: div-7apg3OU, reason: not valid java name */
    private static final int m2038div7apg3OU(byte r0, byte r1) {
        return UByte$$ExternalSyntheticBackport0.m(UInt.m2113constructorimpl(r0 & MAX_VALUE), UInt.m2113constructorimpl(r1 & MAX_VALUE));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: div-VKZWuLQ, reason: not valid java name */
    private static final long m2039divVKZWuLQ(byte r4, long r5) {
        return UByte$$ExternalSyntheticBackport3.m(ULong.m2192constructorimpl(r4 & 255), r5);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: div-WZ4Q5Ns, reason: not valid java name */
    private static final int m2040divWZ4Q5Ns(byte r0, int r1) {
        return UByte$$ExternalSyntheticBackport0.m(UInt.m2113constructorimpl(r0 & MAX_VALUE), r1);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: div-xj2QHRw, reason: not valid java name */
    private static final int m2041divxj2QHRw(byte r1, short r2) {
        return UByte$$ExternalSyntheticBackport0.m(UInt.m2113constructorimpl(r1 & MAX_VALUE), UInt.m2113constructorimpl(r2 & UShort.MAX_VALUE));
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m2042equalsimpl(byte r2, Object r3) {
        if ((r3 instanceof UByte) == true) goto L6;
        return false;
    L6:
        if (r2 == ((UByte) r3).m2086unboximpl()) goto L8;
        return false;
    L8:
        return true;
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2043equalsimpl0(byte r0, byte r1) {
        if (r0 != r1) goto L5;
        return true;
    L5:
        return false;
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: floorDiv-7apg3OU, reason: not valid java name */
    private static final int m2044floorDiv7apg3OU(byte r0, byte r1) {
        return UByte$$ExternalSyntheticBackport0.m(UInt.m2113constructorimpl(r0 & MAX_VALUE), UInt.m2113constructorimpl(r1 & MAX_VALUE));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: floorDiv-VKZWuLQ, reason: not valid java name */
    private static final long m2045floorDivVKZWuLQ(byte r4, long r5) {
        return UByte$$ExternalSyntheticBackport3.m(ULong.m2192constructorimpl(r4 & 255), r5);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: floorDiv-WZ4Q5Ns, reason: not valid java name */
    private static final int m2046floorDivWZ4Q5Ns(byte r0, int r1) {
        return UByte$$ExternalSyntheticBackport0.m(UInt.m2113constructorimpl(r0 & MAX_VALUE), r1);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: floorDiv-xj2QHRw, reason: not valid java name */
    private static final int m2047floorDivxj2QHRw(byte r1, short r2) {
        return UByte$$ExternalSyntheticBackport0.m(UInt.m2113constructorimpl(r1 & MAX_VALUE), UInt.m2113constructorimpl(r2 & UShort.MAX_VALUE));
    }

    @PublishedApi
    public static /* synthetic */ void getData$annotations() {
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m2048hashCodeimpl(byte r0) {
        return Byte.hashCode(r0);
    }

    @InlineOnly
    /* renamed from: inc-w2LRezQ, reason: not valid java name */
    private static final byte m2049incw2LRezQ(byte r0) {
        return m2036constructorimpl((byte) (r0 + 1));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: inv-w2LRezQ, reason: not valid java name */
    private static final byte m2050invw2LRezQ(byte r0) {
        return m2036constructorimpl((byte) (~r0));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: minus-7apg3OU, reason: not valid java name */
    private static final int m2051minus7apg3OU(byte r0, byte r1) {
        return UInt.m2113constructorimpl(UInt.m2113constructorimpl(r0 & MAX_VALUE) - UInt.m2113constructorimpl(r1 & MAX_VALUE));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: minus-VKZWuLQ, reason: not valid java name */
    private static final long m2052minusVKZWuLQ(byte r4, long r5) {
        return ULong.m2192constructorimpl(ULong.m2192constructorimpl(r4 & 255) - r5);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: minus-WZ4Q5Ns, reason: not valid java name */
    private static final int m2053minusWZ4Q5Ns(byte r0, int r1) {
        return UInt.m2113constructorimpl(UInt.m2113constructorimpl(r0 & MAX_VALUE) - r1);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: minus-xj2QHRw, reason: not valid java name */
    private static final int m2054minusxj2QHRw(byte r1, short r2) {
        return UInt.m2113constructorimpl(UInt.m2113constructorimpl(r1 & MAX_VALUE) - UInt.m2113constructorimpl(r2 & UShort.MAX_VALUE));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: mod-7apg3OU, reason: not valid java name */
    private static final byte m2055mod7apg3OU(byte r0, byte r1) {
        return m2036constructorimpl((byte) UByte$$ExternalSyntheticBackport1.m(UInt.m2113constructorimpl(r0 & MAX_VALUE), UInt.m2113constructorimpl(r1 & MAX_VALUE)));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: mod-VKZWuLQ, reason: not valid java name */
    private static final long m2056modVKZWuLQ(byte r4, long r5) {
        return UByte$$ExternalSyntheticBackport2.m(ULong.m2192constructorimpl(r4 & 255), r5);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: mod-WZ4Q5Ns, reason: not valid java name */
    private static final int m2057modWZ4Q5Ns(byte r0, int r1) {
        return UByte$$ExternalSyntheticBackport1.m(UInt.m2113constructorimpl(r0 & MAX_VALUE), r1);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: mod-xj2QHRw, reason: not valid java name */
    private static final short m2058modxj2QHRw(byte r1, short r2) {
        return UShort.m2299constructorimpl((short) UByte$$ExternalSyntheticBackport1.m(UInt.m2113constructorimpl(r1 & MAX_VALUE), UInt.m2113constructorimpl(r2 & UShort.MAX_VALUE)));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: or-7apg3OU, reason: not valid java name */
    private static final byte m2059or7apg3OU(byte r0, byte r1) {
        return m2036constructorimpl((byte) (r0 | r1));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: plus-7apg3OU, reason: not valid java name */
    private static final int m2060plus7apg3OU(byte r0, byte r1) {
        return UInt.m2113constructorimpl(UInt.m2113constructorimpl(r0 & MAX_VALUE) + UInt.m2113constructorimpl(r1 & MAX_VALUE));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: plus-VKZWuLQ, reason: not valid java name */
    private static final long m2061plusVKZWuLQ(byte r4, long r5) {
        return ULong.m2192constructorimpl(ULong.m2192constructorimpl(r4 & 255) + r5);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: plus-WZ4Q5Ns, reason: not valid java name */
    private static final int m2062plusWZ4Q5Ns(byte r0, int r1) {
        return UInt.m2113constructorimpl(UInt.m2113constructorimpl(r0 & MAX_VALUE) + r1);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: plus-xj2QHRw, reason: not valid java name */
    private static final int m2063plusxj2QHRw(byte r1, short r2) {
        return UInt.m2113constructorimpl(UInt.m2113constructorimpl(r1 & MAX_VALUE) + UInt.m2113constructorimpl(r2 & UShort.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: rangeTo-7apg3OU, reason: not valid java name */
    private static final UIntRange m2064rangeTo7apg3OU(byte r2, byte r3) {
        return new UIntRange(UInt.m2113constructorimpl(r2 & MAX_VALUE), UInt.m2113constructorimpl(r3 & MAX_VALUE), null);
    }

    @SinceKotlin(version = "1.9")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    /* renamed from: rangeUntil-7apg3OU, reason: not valid java name */
    private static final UIntRange m2065rangeUntil7apg3OU(byte r0, byte r1) {
        return URangesKt.m3303untilJ1ME1BU(UInt.m2113constructorimpl(r0 & MAX_VALUE), UInt.m2113constructorimpl(r1 & MAX_VALUE));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: rem-7apg3OU, reason: not valid java name */
    private static final int m2066rem7apg3OU(byte r0, byte r1) {
        return UByte$$ExternalSyntheticBackport1.m(UInt.m2113constructorimpl(r0 & MAX_VALUE), UInt.m2113constructorimpl(r1 & MAX_VALUE));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: rem-VKZWuLQ, reason: not valid java name */
    private static final long m2067remVKZWuLQ(byte r4, long r5) {
        return UByte$$ExternalSyntheticBackport2.m(ULong.m2192constructorimpl(r4 & 255), r5);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: rem-WZ4Q5Ns, reason: not valid java name */
    private static final int m2068remWZ4Q5Ns(byte r0, int r1) {
        return UByte$$ExternalSyntheticBackport1.m(UInt.m2113constructorimpl(r0 & MAX_VALUE), r1);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: rem-xj2QHRw, reason: not valid java name */
    private static final int m2069remxj2QHRw(byte r1, short r2) {
        return UByte$$ExternalSyntheticBackport1.m(UInt.m2113constructorimpl(r1 & MAX_VALUE), UInt.m2113constructorimpl(r2 & UShort.MAX_VALUE));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: times-7apg3OU, reason: not valid java name */
    private static final int m2070times7apg3OU(byte r0, byte r1) {
        return UInt.m2113constructorimpl(UInt.m2113constructorimpl(r0 & MAX_VALUE) * UInt.m2113constructorimpl(r1 & MAX_VALUE));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: times-VKZWuLQ, reason: not valid java name */
    private static final long m2071timesVKZWuLQ(byte r4, long r5) {
        return ULong.m2192constructorimpl(ULong.m2192constructorimpl(r4 & 255) * r5);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: times-WZ4Q5Ns, reason: not valid java name */
    private static final int m2072timesWZ4Q5Ns(byte r0, int r1) {
        return UInt.m2113constructorimpl(UInt.m2113constructorimpl(r0 & MAX_VALUE) * r1);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: times-xj2QHRw, reason: not valid java name */
    private static final int m2073timesxj2QHRw(byte r1, short r2) {
        return UInt.m2113constructorimpl(UInt.m2113constructorimpl(r1 & MAX_VALUE) * UInt.m2113constructorimpl(r2 & UShort.MAX_VALUE));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toByte-impl, reason: not valid java name */
    private static final byte m2074toByteimpl(byte r0) {
        return r0;
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toDouble-impl, reason: not valid java name */
    private static final double m2075toDoubleimpl(byte r2) {
        return UnsignedKt.uintToDouble(r2 & MAX_VALUE);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toFloat-impl, reason: not valid java name */
    private static final float m2076toFloatimpl(byte r2) {
        return (float) UnsignedKt.uintToDouble(r2 & MAX_VALUE);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toInt-impl, reason: not valid java name */
    private static final int m2077toIntimpl(byte r0) {
        return r0 & MAX_VALUE;
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toLong-impl, reason: not valid java name */
    private static final long m2078toLongimpl(byte r4) {
        return r4 & 255;
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toShort-impl, reason: not valid java name */
    private static final short m2079toShortimpl(byte r0) {
        return (short) (r0 & 255);
    }

    @IntrinsicConstEvaluation
    /* renamed from: toString-impl, reason: not valid java name */
    public static String m2080toStringimpl(byte r0) {
        return String.valueOf(r0 & MAX_VALUE);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toUByte-w2LRezQ, reason: not valid java name */
    private static final byte m2081toUBytew2LRezQ(byte r0) {
        return r0;
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toUInt-pVg5ArA, reason: not valid java name */
    private static final int m2082toUIntpVg5ArA(byte r0) {
        return UInt.m2113constructorimpl(r0 & MAX_VALUE);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toULong-s-VKNKU, reason: not valid java name */
    private static final long m2083toULongsVKNKU(byte r4) {
        return ULong.m2192constructorimpl(r4 & 255);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toUShort-Mh2AYeg, reason: not valid java name */
    private static final short m2084toUShortMh2AYeg(byte r0) {
        return UShort.m2299constructorimpl((short) (r0 & 255));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: xor-7apg3OU, reason: not valid java name */
    private static final byte m2085xor7apg3OU(byte r0, byte r1) {
        return m2036constructorimpl((byte) (r0 ^ r1));
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(UByte r1) {
        byte r12 = r1.m2086unboximpl();
        return Intrinsics.compare(m2086unboximpl() & MAX_VALUE, r12 & MAX_VALUE);
    }

    public boolean equals(Object r1) {
        return m2042equalsimpl(this.data, r1);
    }

    public int hashCode() {
        return m2048hashCodeimpl(this.data);
    }

    @IntrinsicConstEvaluation
    public String toString() {
        return m2080toStringimpl(this.data);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ byte m2086unboximpl() {
        return this.data;
    }
}
