package kotlin;

import androidx.collection.MutableScatterMap$$ExternalSyntheticBackport0;
import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.internal.InlineOnly;
import kotlin.internal.IntrinsicConstEvaluation;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.ULongRange;
import kotlin.ranges.URangesKt;
import okhttp3.internal.url._UrlKt;
import okhttp3.internal.ws.WebSocketProtocol;

@SinceKotlin(version = "1.5")
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b2\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0010\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087@\u0018\u0000 {2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001{B\u0011\bA\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0087\u008a\u0004¢\u0006\u0004\b\f\u0010\rJ\u0019\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000eH\u0087\u008a\u0004¢\u0006\u0004\b\u000f\u0010\u0010J\u0019\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0011H\u0087\u008a\u0004¢\u0006\u0004\b\u0012\u0010\u0013J\u0019\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0000H\u0097\u008a\u0004¢\u0006\u0004\b\u0014\u0010\u0015J\u0019\u0010\u0016\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0087\u008a\u0004¢\u0006\u0004\b\u0017\u0010\u0018J\u0019\u0010\u0016\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000eH\u0087\u008a\u0004¢\u0006\u0004\b\u0019\u0010\u001aJ\u0019\u0010\u0016\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0011H\u0087\u008a\u0004¢\u0006\u0004\b\u001b\u0010\u001cJ\u0019\u0010\u0016\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\b\u001d\u0010\u001eJ\u0019\u0010\u001f\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0087\u008a\u0004¢\u0006\u0004\b \u0010\u0018J\u0019\u0010\u001f\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000eH\u0087\u008a\u0004¢\u0006\u0004\b!\u0010\u001aJ\u0019\u0010\u001f\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0011H\u0087\u008a\u0004¢\u0006\u0004\b\"\u0010\u001cJ\u0019\u0010\u001f\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\b#\u0010\u001eJ\u0019\u0010$\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0087\u008a\u0004¢\u0006\u0004\b%\u0010\u0018J\u0019\u0010$\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000eH\u0087\u008a\u0004¢\u0006\u0004\b&\u0010\u001aJ\u0019\u0010$\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0011H\u0087\u008a\u0004¢\u0006\u0004\b'\u0010\u001cJ\u0019\u0010$\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\b(\u0010\u001eJ\u0019\u0010)\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0087\u008a\u0004¢\u0006\u0004\b*\u0010\u0018J\u0019\u0010)\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000eH\u0087\u008a\u0004¢\u0006\u0004\b+\u0010\u001aJ\u0019\u0010)\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0011H\u0087\u008a\u0004¢\u0006\u0004\b,\u0010\u001cJ\u0019\u0010)\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\b-\u0010\u001eJ\u0019\u0010.\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0087\u008a\u0004¢\u0006\u0004\b/\u0010\u0018J\u0019\u0010.\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000eH\u0087\u008a\u0004¢\u0006\u0004\b0\u0010\u001aJ\u0019\u0010.\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0011H\u0087\u008a\u0004¢\u0006\u0004\b1\u0010\u001cJ\u0019\u0010.\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\b2\u0010\u001eJ\u0019\u00103\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0087\u0088\u0004¢\u0006\u0004\b4\u0010\u0018J\u0019\u00103\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000eH\u0087\u0088\u0004¢\u0006\u0004\b5\u0010\u001aJ\u0019\u00103\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0011H\u0087\u0088\u0004¢\u0006\u0004\b6\u0010\u001cJ\u0019\u00103\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\u0088\u0004¢\u0006\u0004\b7\u0010\u001eJ\u0019\u00108\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000bH\u0087\u0088\u0004¢\u0006\u0004\b9\u0010:J\u0019\u00108\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u000eH\u0087\u0088\u0004¢\u0006\u0004\b;\u0010<J\u0019\u00108\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u0011H\u0087\u0088\u0004¢\u0006\u0004\b=\u0010\u0013J\u0019\u00108\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\u0088\u0004¢\u0006\u0004\b>\u0010\u001eJ\u0011\u0010?\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\b@\u0010\u0005J\u0011\u0010A\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\bB\u0010\u0005J\u0019\u0010C\u001a\u00020D2\u0006\u0010\n\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\bE\u0010FJ\u0019\u0010G\u001a\u00020D2\u0006\u0010\n\u001a\u00020\u0000H\u0087\u008a\u0004¢\u0006\u0004\bH\u0010FJ\u0019\u0010I\u001a\u00020\u00002\u0006\u0010J\u001a\u00020\tH\u0087\u008c\u0004¢\u0006\u0004\bK\u0010\u001cJ\u0019\u0010L\u001a\u00020\u00002\u0006\u0010J\u001a\u00020\tH\u0087\u008c\u0004¢\u0006\u0004\bM\u0010\u001cJ\u0019\u0010N\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\u008c\u0004¢\u0006\u0004\bO\u0010\u001eJ\u0019\u0010P\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\u008c\u0004¢\u0006\u0004\bQ\u0010\u001eJ\u0019\u0010R\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\u008c\u0004¢\u0006\u0004\bS\u0010\u001eJ\u0011\u0010T\u001a\u00020\u0000H\u0087\u0088\u0004¢\u0006\u0004\bU\u0010\u0005J\u0011\u0010V\u001a\u00020WH\u0087\u0088\u0004¢\u0006\u0004\bX\u0010YJ\u0011\u0010Z\u001a\u00020[H\u0087\u0088\u0004¢\u0006\u0004\b\\\u0010]J\u0011\u0010^\u001a\u00020\tH\u0087\u0088\u0004¢\u0006\u0004\b_\u0010`J\u0011\u0010a\u001a\u00020\u0003H\u0087\u0088\u0004¢\u0006\u0004\bb\u0010\u0005J\u0011\u0010c\u001a\u00020\u000bH\u0087\u0088\u0004¢\u0006\u0004\bd\u0010YJ\u0011\u0010e\u001a\u00020\u000eH\u0087\u0088\u0004¢\u0006\u0004\bf\u0010]J\u0011\u0010g\u001a\u00020\u0011H\u0087\u0088\u0004¢\u0006\u0004\bh\u0010`J\u0011\u0010i\u001a\u00020\u0000H\u0087\u0088\u0004¢\u0006\u0004\bj\u0010\u0005J\u0011\u0010k\u001a\u00020lH\u0087\u0088\u0004¢\u0006\u0004\bm\u0010nJ\u0011\u0010o\u001a\u00020pH\u0087\u0088\u0004¢\u0006\u0004\bq\u0010rJ\u0011\u0010s\u001a\u00020tH\u0097\u0080\u0004¢\u0006\u0004\bu\u0010vJ\u0014\u0010w\u001a\u00020x2\b\u0010\n\u001a\u0004\u0018\u00010yHÖ\u0083\u0004J\n\u0010z\u001a\u00020\tHÖ\u0081\u0004R\u0017\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0084\b¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006|"}, d2 = {"Lkotlin/ULong;", _UrlKt.FRAGMENT_ENCODE_SET, "data", _UrlKt.FRAGMENT_ENCODE_SET, "constructor-impl", "(J)J", "getData$annotations", "()V", "compareTo", _UrlKt.FRAGMENT_ENCODE_SET, "other", "Lkotlin/UByte;", "compareTo-7apg3OU", "(JB)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(JS)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(JI)I", "compareTo-VKZWuLQ", "(JJ)I", "plus", "plus-7apg3OU", "(JB)J", "plus-xj2QHRw", "(JS)J", "plus-WZ4Q5Ns", "(JI)J", "plus-VKZWuLQ", "(JJ)J", "minus", "minus-7apg3OU", "minus-xj2QHRw", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "times", "times-7apg3OU", "times-xj2QHRw", "times-WZ4Q5Ns", "times-VKZWuLQ", "div", "div-7apg3OU", "div-xj2QHRw", "div-WZ4Q5Ns", "div-VKZWuLQ", "rem", "rem-7apg3OU", "rem-xj2QHRw", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "floorDiv", "floorDiv-7apg3OU", "floorDiv-xj2QHRw", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "mod", "mod-7apg3OU", "(JB)B", "mod-xj2QHRw", "(JS)S", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "inc", "inc-s-VKNKU", "dec", "dec-s-VKNKU", "rangeTo", "Lkotlin/ranges/ULongRange;", "rangeTo-VKZWuLQ", "(JJ)Lkotlin/ranges/ULongRange;", "rangeUntil", "rangeUntil-VKZWuLQ", "shl", "bitCount", "shl-s-VKNKU", "shr", "shr-s-VKNKU", "and", "and-VKZWuLQ", PluginsConstants.HookFilterTypes.OR, "or-VKZWuLQ", "xor", "xor-VKZWuLQ", "inv", "inv-s-VKNKU", "toByte", _UrlKt.FRAGMENT_ENCODE_SET, "toByte-impl", "(J)B", "toShort", _UrlKt.FRAGMENT_ENCODE_SET, "toShort-impl", "(J)S", "toInt", "toInt-impl", "(J)I", "toLong", "toLong-impl", "toUByte", "toUByte-w2LRezQ", "toUShort", "toUShort-Mh2AYeg", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toFloat", _UrlKt.FRAGMENT_ENCODE_SET, "toFloat-impl", "(J)F", "toDouble", _UrlKt.FRAGMENT_ENCODE_SET, "toDouble-impl", "(J)D", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "toString-impl", "(J)Ljava/lang/String;", "equals", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@JvmInline
/* loaded from: classes5.dex */
public final class ULong implements Comparable<ULong> {
    public static final Companion Companion = null;
    public static final long MAX_VALUE = -1;
    public static final long MIN_VALUE = 0;
    public static final int SIZE_BITS = 64;
    public static final int SIZE_BYTES = 8;
    private final long data;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005X\u0086Ô\b¢\u0006\u0004\n\u0002\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0005X\u0086Ô\b¢\u0006\u0004\n\u0002\u0010\u0006R\u000f\u0010\b\u001a\u00020\tX\u0086Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\n\u001a\u00020\tX\u0086Ô\b¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lkotlin/ULong$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "MIN_VALUE", "Lkotlin/ULong;", "J", "MAX_VALUE", "SIZE_BYTES", _UrlKt.FRAGMENT_ENCODE_SET, "SIZE_BITS", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
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
    private /* synthetic */ ULong(long r1) {
        this.data = r1;
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: and-VKZWuLQ, reason: not valid java name */
    private static final long m2185andVKZWuLQ(long r0, long r2) {
        return m2192constructorimpl(r0 & r2);
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ ULong m2186boximpl(long r1) {
        return new ULong(r1);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: compareTo-7apg3OU, reason: not valid java name */
    private static final int m2187compareTo7apg3OU(long r4, byte r6) {
        return MutableScatterMap$$ExternalSyntheticBackport0.m(r4, m2192constructorimpl(r6 & 255));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: compareTo-VKZWuLQ, reason: not valid java name */
    private int m2188compareToVKZWuLQ(long r3) {
        return UnsignedKt.ulongCompare(m2244unboximpl(), r3);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: compareTo-VKZWuLQ, reason: not valid java name */
    private static int m2189compareToVKZWuLQ(long r0, long r2) {
        return UnsignedKt.ulongCompare(r0, r2);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: compareTo-WZ4Q5Ns, reason: not valid java name */
    private static final int m2190compareToWZ4Q5Ns(long r4, int r6) {
        return MutableScatterMap$$ExternalSyntheticBackport0.m(r4, m2192constructorimpl(r6 & 4294967295L));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: compareTo-xj2QHRw, reason: not valid java name */
    private static final int m2191compareToxj2QHRw(long r4, short r6) {
        return MutableScatterMap$$ExternalSyntheticBackport0.m(r4, m2192constructorimpl(r6 & WebSocketProtocol.PAYLOAD_SHORT_MAX));
    }

    @PublishedApi
    @IntrinsicConstEvaluation
    /* renamed from: constructor-impl, reason: not valid java name */
    public static long m2192constructorimpl(long r0) {
        return r0;
    }

    @InlineOnly
    /* renamed from: dec-s-VKNKU, reason: not valid java name */
    private static final long m2193decsVKNKU(long r2) {
        return m2192constructorimpl(r2 - 1);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: div-7apg3OU, reason: not valid java name */
    private static final long m2194div7apg3OU(long r4, byte r6) {
        return UByte$$ExternalSyntheticBackport3.m(r4, m2192constructorimpl(r6 & 255));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: div-VKZWuLQ, reason: not valid java name */
    private static final long m2195divVKZWuLQ(long r0, long r2) {
        return UnsignedKt.m2371ulongDivideeb3DHEI(r0, r2);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: div-WZ4Q5Ns, reason: not valid java name */
    private static final long m2196divWZ4Q5Ns(long r4, int r6) {
        return UByte$$ExternalSyntheticBackport3.m(r4, m2192constructorimpl(r6 & 4294967295L));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: div-xj2QHRw, reason: not valid java name */
    private static final long m2197divxj2QHRw(long r4, short r6) {
        return UByte$$ExternalSyntheticBackport3.m(r4, m2192constructorimpl(r6 & WebSocketProtocol.PAYLOAD_SHORT_MAX));
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m2198equalsimpl(long r4, Object r6) {
        if ((r6 instanceof ULong) == true) goto L6;
        return false;
    L6:
        if (r4 == ((ULong) r6).m2244unboximpl()) goto L8;
        return false;
    L8:
        return true;
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2199equalsimpl0(long r0, long r2) {
        if (r0 != r2) goto L6;
        return true;
    L6:
        return false;
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: floorDiv-7apg3OU, reason: not valid java name */
    private static final long m2200floorDiv7apg3OU(long r4, byte r6) {
        return UByte$$ExternalSyntheticBackport3.m(r4, m2192constructorimpl(r6 & 255));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: floorDiv-VKZWuLQ, reason: not valid java name */
    private static final long m2201floorDivVKZWuLQ(long r0, long r2) {
        return UByte$$ExternalSyntheticBackport3.m(r0, r2);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: floorDiv-WZ4Q5Ns, reason: not valid java name */
    private static final long m2202floorDivWZ4Q5Ns(long r4, int r6) {
        return UByte$$ExternalSyntheticBackport3.m(r4, m2192constructorimpl(r6 & 4294967295L));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: floorDiv-xj2QHRw, reason: not valid java name */
    private static final long m2203floorDivxj2QHRw(long r4, short r6) {
        return UByte$$ExternalSyntheticBackport3.m(r4, m2192constructorimpl(r6 & WebSocketProtocol.PAYLOAD_SHORT_MAX));
    }

    @PublishedApi
    public static /* synthetic */ void getData$annotations() {
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m2204hashCodeimpl(long r0) {
        return Long.hashCode(r0);
    }

    @InlineOnly
    /* renamed from: inc-s-VKNKU, reason: not valid java name */
    private static final long m2205incsVKNKU(long r2) {
        return m2192constructorimpl(r2 + 1);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: inv-s-VKNKU, reason: not valid java name */
    private static final long m2206invsVKNKU(long r0) {
        return m2192constructorimpl(~r0);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: minus-7apg3OU, reason: not valid java name */
    private static final long m2207minus7apg3OU(long r4, byte r6) {
        return m2192constructorimpl(r4 - m2192constructorimpl(r6 & 255));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: minus-VKZWuLQ, reason: not valid java name */
    private static final long m2208minusVKZWuLQ(long r0, long r2) {
        return m2192constructorimpl(r0 - r2);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: minus-WZ4Q5Ns, reason: not valid java name */
    private static final long m2209minusWZ4Q5Ns(long r4, int r6) {
        return m2192constructorimpl(r4 - m2192constructorimpl(r6 & 4294967295L));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: minus-xj2QHRw, reason: not valid java name */
    private static final long m2210minusxj2QHRw(long r4, short r6) {
        return m2192constructorimpl(r4 - m2192constructorimpl(r6 & WebSocketProtocol.PAYLOAD_SHORT_MAX));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: mod-7apg3OU, reason: not valid java name */
    private static final byte m2211mod7apg3OU(long r4, byte r6) {
        return UByte.m2036constructorimpl((byte) UByte$$ExternalSyntheticBackport2.m(r4, m2192constructorimpl(r6 & 255)));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: mod-VKZWuLQ, reason: not valid java name */
    private static final long m2212modVKZWuLQ(long r0, long r2) {
        return UByte$$ExternalSyntheticBackport2.m(r0, r2);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: mod-WZ4Q5Ns, reason: not valid java name */
    private static final int m2213modWZ4Q5Ns(long r4, int r6) {
        return UInt.m2113constructorimpl((int) UByte$$ExternalSyntheticBackport2.m(r4, m2192constructorimpl(r6 & 4294967295L)));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: mod-xj2QHRw, reason: not valid java name */
    private static final short m2214modxj2QHRw(long r4, short r6) {
        return UShort.m2299constructorimpl((short) UByte$$ExternalSyntheticBackport2.m(r4, m2192constructorimpl(r6 & WebSocketProtocol.PAYLOAD_SHORT_MAX)));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: or-VKZWuLQ, reason: not valid java name */
    private static final long m2215orVKZWuLQ(long r0, long r2) {
        return m2192constructorimpl(r0 | r2);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: plus-7apg3OU, reason: not valid java name */
    private static final long m2216plus7apg3OU(long r4, byte r6) {
        return m2192constructorimpl(r4 + m2192constructorimpl(r6 & 255));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: plus-VKZWuLQ, reason: not valid java name */
    private static final long m2217plusVKZWuLQ(long r0, long r2) {
        return m2192constructorimpl(r0 + r2);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: plus-WZ4Q5Ns, reason: not valid java name */
    private static final long m2218plusWZ4Q5Ns(long r4, int r6) {
        return m2192constructorimpl(r4 + m2192constructorimpl(r6 & 4294967295L));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: plus-xj2QHRw, reason: not valid java name */
    private static final long m2219plusxj2QHRw(long r4, short r6) {
        return m2192constructorimpl(r4 + m2192constructorimpl(r6 & WebSocketProtocol.PAYLOAD_SHORT_MAX));
    }

    @InlineOnly
    /* renamed from: rangeTo-VKZWuLQ, reason: not valid java name */
    private static final ULongRange m2220rangeToVKZWuLQ(long r6, long r8) {
        return new ULongRange(r6, r8, null);
    }

    @SinceKotlin(version = "1.9")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    /* renamed from: rangeUntil-VKZWuLQ, reason: not valid java name */
    private static final ULongRange m2221rangeUntilVKZWuLQ(long r0, long r2) {
        return URangesKt.m3305untileb3DHEI(r0, r2);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: rem-7apg3OU, reason: not valid java name */
    private static final long m2222rem7apg3OU(long r4, byte r6) {
        return UByte$$ExternalSyntheticBackport2.m(r4, m2192constructorimpl(r6 & 255));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: rem-VKZWuLQ, reason: not valid java name */
    private static final long m2223remVKZWuLQ(long r0, long r2) {
        return UnsignedKt.m2372ulongRemaindereb3DHEI(r0, r2);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: rem-WZ4Q5Ns, reason: not valid java name */
    private static final long m2224remWZ4Q5Ns(long r4, int r6) {
        return UByte$$ExternalSyntheticBackport2.m(r4, m2192constructorimpl(r6 & 4294967295L));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: rem-xj2QHRw, reason: not valid java name */
    private static final long m2225remxj2QHRw(long r4, short r6) {
        return UByte$$ExternalSyntheticBackport2.m(r4, m2192constructorimpl(r6 & WebSocketProtocol.PAYLOAD_SHORT_MAX));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: shl-s-VKNKU, reason: not valid java name */
    private static final long m2226shlsVKNKU(long r0, int r2) {
        return m2192constructorimpl(r0 << r2);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: shr-s-VKNKU, reason: not valid java name */
    private static final long m2227shrsVKNKU(long r0, int r2) {
        return m2192constructorimpl(r0 >>> r2);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: times-7apg3OU, reason: not valid java name */
    private static final long m2228times7apg3OU(long r4, byte r6) {
        return m2192constructorimpl(r4 * m2192constructorimpl(r6 & 255));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: times-VKZWuLQ, reason: not valid java name */
    private static final long m2229timesVKZWuLQ(long r0, long r2) {
        return m2192constructorimpl(r0 * r2);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: times-WZ4Q5Ns, reason: not valid java name */
    private static final long m2230timesWZ4Q5Ns(long r4, int r6) {
        return m2192constructorimpl(r4 * m2192constructorimpl(r6 & 4294967295L));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: times-xj2QHRw, reason: not valid java name */
    private static final long m2231timesxj2QHRw(long r4, short r6) {
        return m2192constructorimpl(r4 * m2192constructorimpl(r6 & WebSocketProtocol.PAYLOAD_SHORT_MAX));
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toByte-impl, reason: not valid java name */
    private static final byte m2232toByteimpl(long r0) {
        return (byte) r0;
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toDouble-impl, reason: not valid java name */
    private static final double m2233toDoubleimpl(long r0) {
        return UnsignedKt.ulongToDouble(r0);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toFloat-impl, reason: not valid java name */
    private static final float m2234toFloatimpl(long r0) {
        return (float) UnsignedKt.ulongToDouble(r0);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toInt-impl, reason: not valid java name */
    private static final int m2235toIntimpl(long r0) {
        return (int) r0;
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toLong-impl, reason: not valid java name */
    private static final long m2236toLongimpl(long r0) {
        return r0;
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toShort-impl, reason: not valid java name */
    private static final short m2237toShortimpl(long r0) {
        return (short) r0;
    }

    @IntrinsicConstEvaluation
    /* renamed from: toString-impl, reason: not valid java name */
    public static String m2238toStringimpl(long r1) {
        return UnsignedKt.ulongToString(r1, 10);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toUByte-w2LRezQ, reason: not valid java name */
    private static final byte m2239toUBytew2LRezQ(long r0) {
        return UByte.m2036constructorimpl((byte) r0);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toUInt-pVg5ArA, reason: not valid java name */
    private static final int m2240toUIntpVg5ArA(long r0) {
        return UInt.m2113constructorimpl((int) r0);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toULong-s-VKNKU, reason: not valid java name */
    private static final long m2241toULongsVKNKU(long r0) {
        return r0;
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: toUShort-Mh2AYeg, reason: not valid java name */
    private static final short m2242toUShortMh2AYeg(long r0) {
        return UShort.m2299constructorimpl((short) r0);
    }

    @IntrinsicConstEvaluation
    @InlineOnly
    /* renamed from: xor-VKZWuLQ, reason: not valid java name */
    private static final long m2243xorVKZWuLQ(long r0, long r2) {
        return m2192constructorimpl(r0 ^ r2);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(ULong r3) {
        long r0 = r3.m2244unboximpl();
        return UnsignedKt.ulongCompare(m2244unboximpl(), r0);
    }

    public boolean equals(Object r3) {
        return m2198equalsimpl(this.data, r3);
    }

    public int hashCode() {
        return m2204hashCodeimpl(this.data);
    }

    @IntrinsicConstEvaluation
    public String toString() {
        return m2238toStringimpl(this.data);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m2244unboximpl() {
        return this.data;
    }
}
