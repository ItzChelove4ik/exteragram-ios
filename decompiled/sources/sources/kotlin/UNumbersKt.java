package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\u0088\u0004¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u0002H\u0087\u0088\u0004¢\u0006\u0004\b\u0006\u0010\u0004\u001a\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u0002H\u0087\u0088\u0004¢\u0006\u0004\b\b\u0010\u0004\u001a\u0015\u0010\t\u001a\u00020\u0002*\u00020\u0002H\u0087\u0088\u0004¢\u0006\u0004\b\n\u0010\u0004\u001a\u0015\u0010\u000b\u001a\u00020\u0002*\u00020\u0002H\u0087\u0088\u0004¢\u0006\u0004\b\f\u0010\u0004\u001a\u001d\u0010\r\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0001H\u0087\u0088\u0004¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u001d\u0010\u0011\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0001H\u0087\u0088\u0004¢\u0006\u0004\b\u0012\u0010\u0010\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u0013H\u0087\u0088\u0004¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u0013H\u0087\u0088\u0004¢\u0006\u0004\b\u0016\u0010\u0015\u001a\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u0013H\u0087\u0088\u0004¢\u0006\u0004\b\u0017\u0010\u0015\u001a\u0015\u0010\t\u001a\u00020\u0013*\u00020\u0013H\u0087\u0088\u0004¢\u0006\u0004\b\u0018\u0010\u0019\u001a\u0015\u0010\u000b\u001a\u00020\u0013*\u00020\u0013H\u0087\u0088\u0004¢\u0006\u0004\b\u001a\u0010\u0019\u001a\u001d\u0010\r\u001a\u00020\u0013*\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u0001H\u0087\u0088\u0004¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u001d\u0010\u0011\u001a\u00020\u0013*\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u0001H\u0087\u0088\u0004¢\u0006\u0004\b\u001d\u0010\u001c\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u001eH\u0087\u0088\u0004¢\u0006\u0004\b\u001f\u0010 \u001a\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u001eH\u0087\u0088\u0004¢\u0006\u0004\b!\u0010 \u001a\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u001eH\u0087\u0088\u0004¢\u0006\u0004\b\"\u0010 \u001a\u0015\u0010\t\u001a\u00020\u001e*\u00020\u001eH\u0087\u0088\u0004¢\u0006\u0004\b#\u0010$\u001a\u0015\u0010\u000b\u001a\u00020\u001e*\u00020\u001eH\u0087\u0088\u0004¢\u0006\u0004\b%\u0010$\u001a\u001d\u0010\r\u001a\u00020\u001e*\u00020\u001e2\u0006\u0010\u000e\u001a\u00020\u0001H\u0087\u0088\u0004¢\u0006\u0004\b&\u0010'\u001a\u001d\u0010\u0011\u001a\u00020\u001e*\u00020\u001e2\u0006\u0010\u000e\u001a\u00020\u0001H\u0087\u0088\u0004¢\u0006\u0004\b(\u0010'\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020)H\u0087\u0088\u0004¢\u0006\u0004\b*\u0010+\u001a\u0015\u0010\u0005\u001a\u00020\u0001*\u00020)H\u0087\u0088\u0004¢\u0006\u0004\b,\u0010+\u001a\u0015\u0010\u0007\u001a\u00020\u0001*\u00020)H\u0087\u0088\u0004¢\u0006\u0004\b-\u0010+\u001a\u0015\u0010\t\u001a\u00020)*\u00020)H\u0087\u0088\u0004¢\u0006\u0004\b.\u0010/\u001a\u0015\u0010\u000b\u001a\u00020)*\u00020)H\u0087\u0088\u0004¢\u0006\u0004\b0\u0010/\u001a\u001d\u0010\r\u001a\u00020)*\u00020)2\u0006\u0010\u000e\u001a\u00020\u0001H\u0087\u0088\u0004¢\u0006\u0004\b1\u00102\u001a\u001d\u0010\u0011\u001a\u00020)*\u00020)2\u0006\u0010\u000e\u001a\u00020\u0001H\u0087\u0088\u0004¢\u0006\u0004\b3\u00102¨\u00064"}, d2 = {"countOneBits", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/UInt;", "countOneBits-WZ4Q5Ns", "(I)I", "countLeadingZeroBits", "countLeadingZeroBits-WZ4Q5Ns", "countTrailingZeroBits", "countTrailingZeroBits-WZ4Q5Ns", "takeHighestOneBit", "takeHighestOneBit-WZ4Q5Ns", "takeLowestOneBit", "takeLowestOneBit-WZ4Q5Ns", "rotateLeft", "bitCount", "rotateLeft-V7xB4Y4", "(II)I", "rotateRight", "rotateRight-V7xB4Y4", "Lkotlin/ULong;", "countOneBits-VKZWuLQ", "(J)I", "countLeadingZeroBits-VKZWuLQ", "countTrailingZeroBits-VKZWuLQ", "takeHighestOneBit-VKZWuLQ", "(J)J", "takeLowestOneBit-VKZWuLQ", "rotateLeft-JSWoG40", "(JI)J", "rotateRight-JSWoG40", "Lkotlin/UByte;", "countOneBits-7apg3OU", "(B)I", "countLeadingZeroBits-7apg3OU", "countTrailingZeroBits-7apg3OU", "takeHighestOneBit-7apg3OU", "(B)B", "takeLowestOneBit-7apg3OU", "rotateLeft-LxnNnR4", "(BI)B", "rotateRight-LxnNnR4", "Lkotlin/UShort;", "countOneBits-xj2QHRw", "(S)I", "countLeadingZeroBits-xj2QHRw", "countTrailingZeroBits-xj2QHRw", "takeHighestOneBit-xj2QHRw", "(S)S", "takeLowestOneBit-xj2QHRw", "rotateLeft-olVBNx4", "(SI)S", "rotateRight-olVBNx4", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
@JvmName(name = "UNumbersKt")
/* loaded from: classes5.dex */
public final class UNumbersKt {
    @SinceKotlin(version = "1.5")
    @InlineOnly
    /* renamed from: countLeadingZeroBits-7apg3OU, reason: not valid java name */
    private static final int m2264countLeadingZeroBits7apg3OU(byte r0) {
        return Integer.numberOfLeadingZeros(r0 & UByte.MAX_VALUE) - 24;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    /* renamed from: countLeadingZeroBits-VKZWuLQ, reason: not valid java name */
    private static final int m2265countLeadingZeroBitsVKZWuLQ(long r0) {
        return Long.numberOfLeadingZeros(r0);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    /* renamed from: countLeadingZeroBits-WZ4Q5Ns, reason: not valid java name */
    private static final int m2266countLeadingZeroBitsWZ4Q5Ns(int r0) {
        return Integer.numberOfLeadingZeros(r0);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    /* renamed from: countLeadingZeroBits-xj2QHRw, reason: not valid java name */
    private static final int m2267countLeadingZeroBitsxj2QHRw(short r1) {
        return Integer.numberOfLeadingZeros(r1 & UShort.MAX_VALUE) - 16;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    /* renamed from: countOneBits-7apg3OU, reason: not valid java name */
    private static final int m2268countOneBits7apg3OU(byte r0) {
        return Integer.bitCount(UInt.m2113constructorimpl(r0 & UByte.MAX_VALUE));
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    /* renamed from: countOneBits-VKZWuLQ, reason: not valid java name */
    private static final int m2269countOneBitsVKZWuLQ(long r0) {
        return Long.bitCount(r0);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    /* renamed from: countOneBits-WZ4Q5Ns, reason: not valid java name */
    private static final int m2270countOneBitsWZ4Q5Ns(int r0) {
        return Integer.bitCount(r0);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    /* renamed from: countOneBits-xj2QHRw, reason: not valid java name */
    private static final int m2271countOneBitsxj2QHRw(short r1) {
        return Integer.bitCount(UInt.m2113constructorimpl(r1 & UShort.MAX_VALUE));
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    /* renamed from: countTrailingZeroBits-7apg3OU, reason: not valid java name */
    private static final int m2272countTrailingZeroBits7apg3OU(byte r0) {
        return Integer.numberOfTrailingZeros(r0 | 256);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    /* renamed from: countTrailingZeroBits-VKZWuLQ, reason: not valid java name */
    private static final int m2273countTrailingZeroBitsVKZWuLQ(long r0) {
        return Long.numberOfTrailingZeros(r0);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    /* renamed from: countTrailingZeroBits-WZ4Q5Ns, reason: not valid java name */
    private static final int m2274countTrailingZeroBitsWZ4Q5Ns(int r0) {
        return Integer.numberOfTrailingZeros(r0);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    /* renamed from: countTrailingZeroBits-xj2QHRw, reason: not valid java name */
    private static final int m2275countTrailingZeroBitsxj2QHRw(short r1) {
        return Integer.numberOfTrailingZeros(r1 | UShort.MIN_VALUE);
    }

    @SinceKotlin(version = "1.6")
    @InlineOnly
    /* renamed from: rotateLeft-JSWoG40, reason: not valid java name */
    private static final long m2276rotateLeftJSWoG40(long r0, int r2) {
        return ULong.m2192constructorimpl(Long.rotateLeft(r0, r2));
    }

    @SinceKotlin(version = "1.6")
    @InlineOnly
    /* renamed from: rotateLeft-LxnNnR4, reason: not valid java name */
    private static final byte m2277rotateLeftLxnNnR4(byte r0, int r1) {
        return UByte.m2036constructorimpl(NumbersKt__NumbersKt.rotateLeft(r0, r1));
    }

    @SinceKotlin(version = "1.6")
    @InlineOnly
    /* renamed from: rotateLeft-V7xB4Y4, reason: not valid java name */
    private static final int m2278rotateLeftV7xB4Y4(int r0, int r1) {
        return UInt.m2113constructorimpl(Integer.rotateLeft(r0, r1));
    }

    @SinceKotlin(version = "1.6")
    @InlineOnly
    /* renamed from: rotateLeft-olVBNx4, reason: not valid java name */
    private static final short m2279rotateLeftolVBNx4(short r0, int r1) {
        return UShort.m2299constructorimpl(NumbersKt__NumbersKt.rotateLeft(r0, r1));
    }

    @SinceKotlin(version = "1.6")
    @InlineOnly
    /* renamed from: rotateRight-JSWoG40, reason: not valid java name */
    private static final long m2280rotateRightJSWoG40(long r0, int r2) {
        return ULong.m2192constructorimpl(Long.rotateRight(r0, r2));
    }

    @SinceKotlin(version = "1.6")
    @InlineOnly
    /* renamed from: rotateRight-LxnNnR4, reason: not valid java name */
    private static final byte m2281rotateRightLxnNnR4(byte r0, int r1) {
        return UByte.m2036constructorimpl(NumbersKt__NumbersKt.rotateRight(r0, r1));
    }

    @SinceKotlin(version = "1.6")
    @InlineOnly
    /* renamed from: rotateRight-V7xB4Y4, reason: not valid java name */
    private static final int m2282rotateRightV7xB4Y4(int r0, int r1) {
        return UInt.m2113constructorimpl(Integer.rotateRight(r0, r1));
    }

    @SinceKotlin(version = "1.6")
    @InlineOnly
    /* renamed from: rotateRight-olVBNx4, reason: not valid java name */
    private static final short m2283rotateRightolVBNx4(short r0, int r1) {
        return UShort.m2299constructorimpl(NumbersKt__NumbersKt.rotateRight(r0, r1));
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    /* renamed from: takeHighestOneBit-7apg3OU, reason: not valid java name */
    private static final byte m2284takeHighestOneBit7apg3OU(byte r0) {
        return UByte.m2036constructorimpl((byte) Integer.highestOneBit(r0 & UByte.MAX_VALUE));
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    /* renamed from: takeHighestOneBit-VKZWuLQ, reason: not valid java name */
    private static final long m2285takeHighestOneBitVKZWuLQ(long r0) {
        return ULong.m2192constructorimpl(Long.highestOneBit(r0));
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    /* renamed from: takeHighestOneBit-WZ4Q5Ns, reason: not valid java name */
    private static final int m2286takeHighestOneBitWZ4Q5Ns(int r0) {
        return UInt.m2113constructorimpl(Integer.highestOneBit(r0));
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    /* renamed from: takeHighestOneBit-xj2QHRw, reason: not valid java name */
    private static final short m2287takeHighestOneBitxj2QHRw(short r1) {
        return UShort.m2299constructorimpl((short) Integer.highestOneBit(r1 & UShort.MAX_VALUE));
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    /* renamed from: takeLowestOneBit-7apg3OU, reason: not valid java name */
    private static final byte m2288takeLowestOneBit7apg3OU(byte r0) {
        return UByte.m2036constructorimpl((byte) Integer.lowestOneBit(r0 & UByte.MAX_VALUE));
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    /* renamed from: takeLowestOneBit-VKZWuLQ, reason: not valid java name */
    private static final long m2289takeLowestOneBitVKZWuLQ(long r0) {
        return ULong.m2192constructorimpl(Long.lowestOneBit(r0));
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    /* renamed from: takeLowestOneBit-WZ4Q5Ns, reason: not valid java name */
    private static final int m2290takeLowestOneBitWZ4Q5Ns(int r0) {
        return UInt.m2113constructorimpl(Integer.lowestOneBit(r0));
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    /* renamed from: takeLowestOneBit-xj2QHRw, reason: not valid java name */
    private static final short m2291takeLowestOneBitxj2QHRw(short r1) {
        return UShort.m2299constructorimpl((short) Integer.lowestOneBit(r1 & UShort.MAX_VALUE));
    }
}
