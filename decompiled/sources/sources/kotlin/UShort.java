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
import okhttp3.internal.ws.WebSocketProtocol;

@SinceKotlin(version = "1.5")
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\n\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b-\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0005\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087@\u0018\u0000 s2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001sB\u0011\bA\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0087\u008a\u0004¢\u0006\u0004\b\f\u0010\rJ\u0019\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0000H\u0097\u008a\u0004¢\u0006\u0004\b\u000e\u0010\u000fJ\u0019\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0010H\u0087\u008a\u0004¢\u0006\u0004\b\u0011\u0010\u0012J\u0019\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0013H\u0087\u008a\u0004¢\u0006\u0004\b\u0014\u0010\u0015J\u0019\u0010\u0016\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000bH\u0087\u008a\u0004¢\u0006\u0004\b\u0017\u0010\rJ\u0019\u0010\u0016\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\b\u0018\u0010\u000fJ\u0019\u0010\u0016\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\u008a\u0004¢\u0006\u0004\b\u0019\u0010\u0012J\u0019\u0010\u0016\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\u008a\u0004¢\u0006\u0004\b\u001a\u0010\u001bJ\u0019\u0010\u001c\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000bH\u0087\u008a\u0004¢\u0006\u0004\b\u001d\u0010\rJ\u0019\u0010\u001c\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\b\u001e\u0010\u000fJ\u0019\u0010\u001c\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\u008a\u0004¢\u0006\u0004\b\u001f\u0010\u0012J\u0019\u0010\u001c\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\u008a\u0004¢\u0006\u0004\b \u0010\u001bJ\u0019\u0010!\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000bH\u0087\u008a\u0004¢\u0006\u0004\b\"\u0010\rJ\u0019\u0010!\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\b#\u0010\u000fJ\u0019\u0010!\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\u008a\u0004¢\u0006\u0004\b$\u0010\u0012J\u0019\u0010!\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\u008a\u0004¢\u0006\u0004\b%\u0010\u001bJ\u0019\u0010&\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000bH\u0087\u008a\u0004¢\u0006\u0004\b'\u0010\rJ\u0019\u0010&\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\b(\u0010\u000fJ\u0019\u0010&\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\u008a\u0004¢\u0006\u0004\b)\u0010\u0012J\u0019\u0010&\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\u008a\u0004¢\u0006\u0004\b*\u0010\u001bJ\u0019\u0010+\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000bH\u0087\u008a\u0004¢\u0006\u0004\b,\u0010\rJ\u0019\u0010+\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\b-\u0010\u000fJ\u0019\u0010+\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\u008a\u0004¢\u0006\u0004\b.\u0010\u0012J\u0019\u0010+\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\u008a\u0004¢\u0006\u0004\b/\u0010\u001bJ\u0019\u00100\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000bH\u0087\u0088\u0004¢\u0006\u0004\b1\u0010\rJ\u0019\u00100\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0087\u0088\u0004¢\u0006\u0004\b2\u0010\u000fJ\u0019\u00100\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\u0088\u0004¢\u0006\u0004\b3\u0010\u0012J\u0019\u00100\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\u0088\u0004¢\u0006\u0004\b4\u0010\u001bJ\u0019\u00105\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000bH\u0087\u0088\u0004¢\u0006\u0004\b6\u00107J\u0019\u00105\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\u0088\u0004¢\u0006\u0004\b8\u00109J\u0019\u00105\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\u0088\u0004¢\u0006\u0004\b:\u0010\u0012J\u0019\u00105\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\u0088\u0004¢\u0006\u0004\b;\u0010\u001bJ\u0011\u0010<\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\b=\u0010\u0005J\u0011\u0010>\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\b?\u0010\u0005J\u0019\u0010@\u001a\u00020A2\u0006\u0010\n\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\bB\u0010CJ\u0019\u0010D\u001a\u00020A2\u0006\u0010\n\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\bE\u0010CJ\u0019\u0010F\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\u008c\u0004¢\u0006\u0004\bG\u00109J\u0019\u0010H\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\u008c\u0004¢\u0006\u0004\bI\u00109J\u0019\u0010J\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\u008c\u0004¢\u0006\u0004\bK\u00109J\u0011\u0010L\u001a\u00020\u0000H\u0087\u0088\u0004¢\u0006\u0004\bM\u0010\u0005J\u0011\u0010N\u001a\u00020OH\u0087\u0088\u0004¢\u0006\u0004\bP\u0010QJ\u0011\u0010R\u001a\u00020\u0003H\u0087\u0088\u0004¢\u0006\u0004\bS\u0010\u0005J\u0011\u0010T\u001a\u00020\tH\u0087\u0088\u0004¢\u0006\u0004\bU\u0010VJ\u0011\u0010W\u001a\u00020XH\u0087\u0088\u0004¢\u0006\u0004\bY\u0010ZJ\u0011\u0010[\u001a\u00020\u000bH\u0087\u0088\u0004¢\u0006\u0004\b\\\u0010QJ\u0011\u0010]\u001a\u00020\u0000H\u0087\u0088\u0004¢\u0006\u0004\b^\u0010\u0005J\u0011\u0010_\u001a\u00020\u0010H\u0087\u0088\u0004¢\u0006\u0004\b`\u0010VJ\u0011\u0010a\u001a\u00020\u0013H\u0087\u0088\u0004¢\u0006\u0004\bb\u0010ZJ\u0011\u0010c\u001a\u00020dH\u0087\u0088\u0004¢\u0006\u0004\be\u0010fJ\u0011\u0010g\u001a\u00020hH\u0087\u0088\u0004¢\u0006\u0004\bi\u0010jJ\u0011\u0010k\u001a\u00020lH\u0097\u0080\u0004¢\u0006\u0004\bm\u0010nJ\u0014\u0010o\u001a\u00020p2\b\u0010\n\u001a\u0004\u0018\u00010qHÖ\u0083\u0004J\n\u0010r\u001a\u00020\tHÖ\u0081\u0004R\u0017\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0084\b¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006t"}, d2 = {"Lkotlin/UShort;", _UrlKt.FRAGMENT_ENCODE_SET, "data", _UrlKt.FRAGMENT_ENCODE_SET, "constructor-impl", "(S)S", "getData$annotations", "()V", "compareTo", _UrlKt.FRAGMENT_ENCODE_SET, "other", "Lkotlin/UByte;", "compareTo-7apg3OU", "(SB)I", "compareTo-xj2QHRw", "(SS)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(SI)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(SJ)I", "plus", "plus-7apg3OU", "plus-xj2QHRw", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "(SJ)J", "minus", "minus-7apg3OU", "minus-xj2QHRw", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "times", "times-7apg3OU", "times-xj2QHRw", "times-WZ4Q5Ns", "times-VKZWuLQ", "div", "div-7apg3OU", "div-xj2QHRw", "div-WZ4Q5Ns", "div-VKZWuLQ", "rem", "rem-7apg3OU", "rem-xj2QHRw", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "floorDiv", "floorDiv-7apg3OU", "floorDiv-xj2QHRw", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "mod", "mod-7apg3OU", "(SB)B", "mod-xj2QHRw", "(SS)S", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "inc", "inc-Mh2AYeg", "dec", "dec-Mh2AYeg", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-xj2QHRw", "(SS)Lkotlin/ranges/UIntRange;", "rangeUntil", "rangeUntil-xj2QHRw", "and", "and-xj2QHRw", PluginsConstants.HookFilterTypes.OR, "or-xj2QHRw", "xor", "xor-xj2QHRw", "inv", "inv-Mh2AYeg", "toByte", _UrlKt.FRAGMENT_ENCODE_SET, "toByte-impl", "(S)B", "toShort", "toShort-impl", "toInt", "toInt-impl", "(S)I", "toLong", _UrlKt.FRAGMENT_ENCODE_SET, "toLong-impl", "(S)J", "toUByte", "toUByte-w2LRezQ", "toUShort", "toUShort-Mh2AYeg", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toFloat", _UrlKt.FRAGMENT_ENCODE_SET, "toFloat-impl", "(S)F", "toDouble", _UrlKt.FRAGMENT_ENCODE_SET, "toDouble-impl", "(S)D", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "toString-impl", "(S)Ljava/lang/String;", "equals", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@JvmInline
/* loaded from: classes5.dex */
public final class UShort implements Comparable<UShort> {
    public static final Companion Companion = null;
    public static final short MAX_VALUE = -1;
    public static final short MIN_VALUE = 0;
    public static final int SIZE_BITS = 16;
    public static final int SIZE_BYTES = 2;
    private final short data;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005X\u0086Ô\b¢\u0006\u0004\n\u0002\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0005X\u0086Ô\b¢\u0006\u0004\n\u0002\u0010\u0006R\u000f\u0010\b\u001a\u00020\tX\u0086Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\n\u001a\u00020\tX\u0086Ô\b¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lkotlin/UShort$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "MIN_VALUE", "Lkotlin/UShort;", "S", "MAX_VALUE", "SIZE_BYTES", _UrlKt.FRAGMENT_ENCODE_SET, "SIZE_BITS", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
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
    private /* synthetic */ UShort(short r1) {
        this.data = r1;
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: and-xj2QHRw, reason: not valid java name */
    private static final short m2292andxj2QHRw(short r0, short r1) {
        return m2299constructorimpl((short) (r0 & r1));
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ UShort m2293boximpl(short r1) {
        return new UShort(r1);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: compareTo-7apg3OU, reason: not valid java name */
    private static final int m2294compareTo7apg3OU(short r1, byte r2) {
        return Intrinsics.compare(r1 & MAX_VALUE, r2 & UByte.MAX_VALUE);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: compareTo-VKZWuLQ, reason: not valid java name */
    private static final int m2295compareToVKZWuLQ(short r4, long r5) {
        return MutableScatterMap$$ExternalSyntheticBackport0.m(ULong.m2192constructorimpl(r4 & WebSocketProtocol.PAYLOAD_SHORT_MAX), r5);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: compareTo-WZ4Q5Ns, reason: not valid java name */
    private static final int m2296compareToWZ4Q5Ns(short r1, int r2) {
        return UByte$$ExternalSyntheticBackport4.m(UInt.m2113constructorimpl(r1 & MAX_VALUE), r2);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: compareTo-xj2QHRw, reason: not valid java name */
    private int m2297compareToxj2QHRw(short r2) {
        return Intrinsics.compare(m2349unboximpl() & MAX_VALUE, r2 & MAX_VALUE);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: compareTo-xj2QHRw, reason: not valid java name */
    private static int m2298compareToxj2QHRw(short r1, short r2) {
        return Intrinsics.compare(r1 & MAX_VALUE, r2 & MAX_VALUE);
    }

    @PublishedApi
    @IntrinsicConstEvaluation
    /* renamed from: constructor-impl, reason: not valid java name */
    public static short m2299constructorimpl(short r0) {
        return r0;
    }

    @InlineOnly
    /* renamed from: dec-Mh2AYeg, reason: not valid java name */
    private static final short m2300decMh2AYeg(short r0) {
        return m2299constructorimpl((short) (r0 - 1));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: div-7apg3OU, reason: not valid java name */
    private static final int m2301div7apg3OU(short r1, byte r2) {
        return UByte$$ExternalSyntheticBackport0.m(UInt.m2113constructorimpl(r1 & MAX_VALUE), UInt.m2113constructorimpl(r2 & UByte.MAX_VALUE));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: div-VKZWuLQ, reason: not valid java name */
    private static final long m2302divVKZWuLQ(short r4, long r5) {
        return UByte$$ExternalSyntheticBackport3.m(ULong.m2192constructorimpl(r4 & WebSocketProtocol.PAYLOAD_SHORT_MAX), r5);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: div-WZ4Q5Ns, reason: not valid java name */
    private static final int m2303divWZ4Q5Ns(short r1, int r2) {
        return UByte$$ExternalSyntheticBackport0.m(UInt.m2113constructorimpl(r1 & MAX_VALUE), r2);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: div-xj2QHRw, reason: not valid java name */
    private static final int m2304divxj2QHRw(short r1, short r2) {
        return UByte$$ExternalSyntheticBackport0.m(UInt.m2113constructorimpl(r1 & MAX_VALUE), UInt.m2113constructorimpl(r2 & MAX_VALUE));
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m2305equalsimpl(short r2, Object r3) {
        if ((r3 instanceof UShort) == true) goto L6;
        return false;
    L6:
        if (r2 == ((UShort) r3).m2349unboximpl()) goto L8;
        return false;
    L8:
        return true;
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2306equalsimpl0(short r0, short r1) {
        if (r0 != r1) goto L5;
        return true;
    L5:
        return false;
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: floorDiv-7apg3OU, reason: not valid java name */
    private static final int m2307floorDiv7apg3OU(short r1, byte r2) {
        return UByte$$ExternalSyntheticBackport0.m(UInt.m2113constructorimpl(r1 & MAX_VALUE), UInt.m2113constructorimpl(r2 & UByte.MAX_VALUE));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: floorDiv-VKZWuLQ, reason: not valid java name */
    private static final long m2308floorDivVKZWuLQ(short r4, long r5) {
        return UByte$$ExternalSyntheticBackport3.m(ULong.m2192constructorimpl(r4 & WebSocketProtocol.PAYLOAD_SHORT_MAX), r5);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: floorDiv-WZ4Q5Ns, reason: not valid java name */
    private static final int m2309floorDivWZ4Q5Ns(short r1, int r2) {
        return UByte$$ExternalSyntheticBackport0.m(UInt.m2113constructorimpl(r1 & MAX_VALUE), r2);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: floorDiv-xj2QHRw, reason: not valid java name */
    private static final int m2310floorDivxj2QHRw(short r1, short r2) {
        return UByte$$ExternalSyntheticBackport0.m(UInt.m2113constructorimpl(r1 & MAX_VALUE), UInt.m2113constructorimpl(r2 & MAX_VALUE));
    }

    @PublishedApi
    public static /* synthetic */ void getData$annotations() {
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m2311hashCodeimpl(short r0) {
        return Short.hashCode(r0);
    }

    @InlineOnly
    /* renamed from: inc-Mh2AYeg, reason: not valid java name */
    private static final short m2312incMh2AYeg(short r0) {
        return m2299constructorimpl((short) (r0 + 1));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: inv-Mh2AYeg, reason: not valid java name */
    private static final short m2313invMh2AYeg(short r0) {
        return m2299constructorimpl((short) (~r0));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: minus-7apg3OU, reason: not valid java name */
    private static final int m2314minus7apg3OU(short r1, byte r2) {
        return UInt.m2113constructorimpl(UInt.m2113constructorimpl(r1 & MAX_VALUE) - UInt.m2113constructorimpl(r2 & UByte.MAX_VALUE));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: minus-VKZWuLQ, reason: not valid java name */
    private static final long m2315minusVKZWuLQ(short r4, long r5) {
        return ULong.m2192constructorimpl(ULong.m2192constructorimpl(r4 & WebSocketProtocol.PAYLOAD_SHORT_MAX) - r5);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: minus-WZ4Q5Ns, reason: not valid java name */
    private static final int m2316minusWZ4Q5Ns(short r1, int r2) {
        return UInt.m2113constructorimpl(UInt.m2113constructorimpl(r1 & MAX_VALUE) - r2);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: minus-xj2QHRw, reason: not valid java name */
    private static final int m2317minusxj2QHRw(short r1, short r2) {
        return UInt.m2113constructorimpl(UInt.m2113constructorimpl(r1 & MAX_VALUE) - UInt.m2113constructorimpl(r2 & MAX_VALUE));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: mod-7apg3OU, reason: not valid java name */
    private static final byte m2318mod7apg3OU(short r1, byte r2) {
        return UByte.m2036constructorimpl((byte) UByte$$ExternalSyntheticBackport1.m(UInt.m2113constructorimpl(r1 & MAX_VALUE), UInt.m2113constructorimpl(r2 & UByte.MAX_VALUE)));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: mod-VKZWuLQ, reason: not valid java name */
    private static final long m2319modVKZWuLQ(short r4, long r5) {
        return UByte$$ExternalSyntheticBackport2.m(ULong.m2192constructorimpl(r4 & WebSocketProtocol.PAYLOAD_SHORT_MAX), r5);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: mod-WZ4Q5Ns, reason: not valid java name */
    private static final int m2320modWZ4Q5Ns(short r1, int r2) {
        return UByte$$ExternalSyntheticBackport1.m(UInt.m2113constructorimpl(r1 & MAX_VALUE), r2);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: mod-xj2QHRw, reason: not valid java name */
    private static final short m2321modxj2QHRw(short r1, short r2) {
        return m2299constructorimpl((short) UByte$$ExternalSyntheticBackport1.m(UInt.m2113constructorimpl(r1 & MAX_VALUE), UInt.m2113constructorimpl(r2 & MAX_VALUE)));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: or-xj2QHRw, reason: not valid java name */
    private static final short m2322orxj2QHRw(short r0, short r1) {
        return m2299constructorimpl((short) (r0 | r1));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: plus-7apg3OU, reason: not valid java name */
    private static final int m2323plus7apg3OU(short r1, byte r2) {
        return UInt.m2113constructorimpl(UInt.m2113constructorimpl(r1 & MAX_VALUE) + UInt.m2113constructorimpl(r2 & UByte.MAX_VALUE));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: plus-VKZWuLQ, reason: not valid java name */
    private static final long m2324plusVKZWuLQ(short r4, long r5) {
        return ULong.m2192constructorimpl(ULong.m2192constructorimpl(r4 & WebSocketProtocol.PAYLOAD_SHORT_MAX) + r5);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: plus-WZ4Q5Ns, reason: not valid java name */
    private static final int m2325plusWZ4Q5Ns(short r1, int r2) {
        return UInt.m2113constructorimpl(UInt.m2113constructorimpl(r1 & MAX_VALUE) + r2);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: plus-xj2QHRw, reason: not valid java name */
    private static final int m2326plusxj2QHRw(short r1, short r2) {
        return UInt.m2113constructorimpl(UInt.m2113constructorimpl(r1 & MAX_VALUE) + UInt.m2113constructorimpl(r2 & MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: rangeTo-xj2QHRw, reason: not valid java name */
    private static final UIntRange m2327rangeToxj2QHRw(short r2, short r3) {
        return new UIntRange(UInt.m2113constructorimpl(r2 & MAX_VALUE), UInt.m2113constructorimpl(r3 & MAX_VALUE), null);
    }

    @SinceKotlin(version = "1.9")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    /* renamed from: rangeUntil-xj2QHRw, reason: not valid java name */
    private static final UIntRange m2328rangeUntilxj2QHRw(short r1, short r2) {
        return URangesKt.m3303untilJ1ME1BU(UInt.m2113constructorimpl(r1 & MAX_VALUE), UInt.m2113constructorimpl(r2 & MAX_VALUE));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: rem-7apg3OU, reason: not valid java name */
    private static final int m2329rem7apg3OU(short r1, byte r2) {
        return UByte$$ExternalSyntheticBackport1.m(UInt.m2113constructorimpl(r1 & MAX_VALUE), UInt.m2113constructorimpl(r2 & UByte.MAX_VALUE));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: rem-VKZWuLQ, reason: not valid java name */
    private static final long m2330remVKZWuLQ(short r4, long r5) {
        return UByte$$ExternalSyntheticBackport2.m(ULong.m2192constructorimpl(r4 & WebSocketProtocol.PAYLOAD_SHORT_MAX), r5);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: rem-WZ4Q5Ns, reason: not valid java name */
    private static final int m2331remWZ4Q5Ns(short r1, int r2) {
        return UByte$$ExternalSyntheticBackport1.m(UInt.m2113constructorimpl(r1 & MAX_VALUE), r2);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: rem-xj2QHRw, reason: not valid java name */
    private static final int m2332remxj2QHRw(short r1, short r2) {
        return UByte$$ExternalSyntheticBackport1.m(UInt.m2113constructorimpl(r1 & MAX_VALUE), UInt.m2113constructorimpl(r2 & MAX_VALUE));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: times-7apg3OU, reason: not valid java name */
    private static final int m2333times7apg3OU(short r1, byte r2) {
        return UInt.m2113constructorimpl(UInt.m2113constructorimpl(r1 & MAX_VALUE) * UInt.m2113constructorimpl(r2 & UByte.MAX_VALUE));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: times-VKZWuLQ, reason: not valid java name */
    private static final long m2334timesVKZWuLQ(short r4, long r5) {
        return ULong.m2192constructorimpl(ULong.m2192constructorimpl(r4 & WebSocketProtocol.PAYLOAD_SHORT_MAX) * r5);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: times-WZ4Q5Ns, reason: not valid java name */
    private static final int m2335timesWZ4Q5Ns(short r1, int r2) {
        return UInt.m2113constructorimpl(UInt.m2113constructorimpl(r1 & MAX_VALUE) * r2);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: times-xj2QHRw, reason: not valid java name */
    private static final int m2336timesxj2QHRw(short r1, short r2) {
        return UInt.m2113constructorimpl(UInt.m2113constructorimpl(r1 & MAX_VALUE) * UInt.m2113constructorimpl(r2 & MAX_VALUE));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toByte-impl, reason: not valid java name */
    private static final byte m2337toByteimpl(short r0) {
        return (byte) r0;
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toDouble-impl, reason: not valid java name */
    private static final double m2338toDoubleimpl(short r2) {
        return UnsignedKt.uintToDouble(r2 & MAX_VALUE);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toFloat-impl, reason: not valid java name */
    private static final float m2339toFloatimpl(short r2) {
        return (float) UnsignedKt.uintToDouble(r2 & MAX_VALUE);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toInt-impl, reason: not valid java name */
    private static final int m2340toIntimpl(short r1) {
        return r1 & MAX_VALUE;
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toLong-impl, reason: not valid java name */
    private static final long m2341toLongimpl(short r4) {
        return r4 & WebSocketProtocol.PAYLOAD_SHORT_MAX;
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toShort-impl, reason: not valid java name */
    private static final short m2342toShortimpl(short r0) {
        return r0;
    }

    @IntrinsicConstEvaluation
    /* renamed from: toString-impl, reason: not valid java name */
    public static String m2343toStringimpl(short r1) {
        return String.valueOf(r1 & MAX_VALUE);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toUByte-w2LRezQ, reason: not valid java name */
    private static final byte m2344toUBytew2LRezQ(short r0) {
        return UByte.m2036constructorimpl((byte) r0);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toUInt-pVg5ArA, reason: not valid java name */
    private static final int m2345toUIntpVg5ArA(short r1) {
        return UInt.m2113constructorimpl(r1 & MAX_VALUE);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toULong-s-VKNKU, reason: not valid java name */
    private static final long m2346toULongsVKNKU(short r4) {
        return ULong.m2192constructorimpl(r4 & WebSocketProtocol.PAYLOAD_SHORT_MAX);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toUShort-Mh2AYeg, reason: not valid java name */
    private static final short m2347toUShortMh2AYeg(short r0) {
        return r0;
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: xor-xj2QHRw, reason: not valid java name */
    private static final short m2348xorxj2QHRw(short r0, short r1) {
        return m2299constructorimpl((short) (r0 ^ r1));
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(UShort r2) {
        short r22 = r2.m2349unboximpl();
        return Intrinsics.compare(m2349unboximpl() & MAX_VALUE, r22 & MAX_VALUE);
    }

    public boolean equals(Object r1) {
        return m2305equalsimpl(this.data, r1);
    }

    public int hashCode() {
        return m2311hashCodeimpl(this.data);
    }

    @IntrinsicConstEvaluation
    public String toString() {
        return m2343toStringimpl(this.data);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ short m2349unboximpl() {
        return this.data;
    }
}
