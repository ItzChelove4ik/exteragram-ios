package kotlin;

import kotlin.internal.InlineOnly;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u0005\n\u0002\b\u0007\n\u0002\u0010\n\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\u0088\u0004\u001a\u000e\u0010\u0003\u001a\u00020\u0001*\u00020\u0002H\u0087\u0088\u0004\u001a\u000e\u0010\u0004\u001a\u00020\u0001*\u00020\u0002H\u0087\u0088\u0004\u001a\u000e\u0010\u0005\u001a\u00020\u0002*\u00020\u0002H\u0087\u0088\u0004\u001a\u000e\u0010\u0006\u001a\u00020\u0002*\u00020\u0002H\u0087\u0088\u0004\u001a\u0016\u0010\u0007\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\b\u001a\u00020\u0001H\u0087\u0080\u0004\u001a\u0016\u0010\t\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\b\u001a\u00020\u0001H\u0087\u0080\u0004\u001a\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\nH\u0087\u0088\u0004\u001a\u000e\u0010\u0003\u001a\u00020\u0001*\u00020\nH\u0087\u0088\u0004\u001a\u000e\u0010\u0004\u001a\u00020\u0001*\u00020\nH\u0087\u0088\u0004\u001a\u000e\u0010\u0005\u001a\u00020\n*\u00020\nH\u0087\u0088\u0004\u001a\u000e\u0010\u0006\u001a\u00020\n*\u00020\nH\u0087\u0088\u0004\u001a\u0016\u0010\u0007\u001a\u00020\n*\u00020\n2\u0006\u0010\b\u001a\u00020\u0001H\u0087\u0080\u0004\u001a\u0016\u0010\t\u001a\u00020\n*\u00020\n2\u0006\u0010\b\u001a\u00020\u0001H\u0087\u0080\u0004¨\u0006\u000b"}, d2 = {"countOneBits", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "countLeadingZeroBits", "countTrailingZeroBits", "takeHighestOneBit", "takeLowestOneBit", "rotateLeft", "bitCount", "rotateRight", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/NumbersKt")
/* loaded from: classes5.dex */
class NumbersKt__NumbersKt extends NumbersKt__NumbersJVMKt {
    public NumbersKt__NumbersKt() {
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final int countLeadingZeroBits(byte r0) {
        return Integer.numberOfLeadingZeros(r0 & UByte.MAX_VALUE) - 24;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final int countLeadingZeroBits(short r1) {
        return Integer.numberOfLeadingZeros(r1 & UShort.MAX_VALUE) - 16;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final int countOneBits(byte r0) {
        return Integer.bitCount(r0 & UByte.MAX_VALUE);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final int countOneBits(short r1) {
        return Integer.bitCount(r1 & UShort.MAX_VALUE);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final int countTrailingZeroBits(byte r0) {
        return Integer.numberOfTrailingZeros(r0 | 256);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final int countTrailingZeroBits(short r1) {
        return Integer.numberOfTrailingZeros(r1 | UShort.MIN_VALUE);
    }

    @SinceKotlin(version = "1.6")
    public static final byte rotateLeft(byte r1, int r2) {
        int r22 = r2 & 7;
        int r0 = r1 << r22;
        return (byte) (((r1 & 255) >>> (8 - r22)) | r0);
    }

    @SinceKotlin(version = "1.6")
    public static final short rotateLeft(short r2, int r3) {
        int r32 = r3 & 15;
        int r0 = r2 << r32;
        return (short) (((r2 & 65535) >>> (16 - r32)) | r0);
    }

    @SinceKotlin(version = "1.6")
    public static final byte rotateRight(byte r1, int r2) {
        int r22 = r2 & 7;
        int r0 = r1 << (8 - r22);
        return (byte) (((r1 & 255) >>> r22) | r0);
    }

    @SinceKotlin(version = "1.6")
    public static final short rotateRight(short r2, int r3) {
        int r32 = r3 & 15;
        int r0 = r2 << (16 - r32);
        return (short) (((r2 & 65535) >>> r32) | r0);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final byte takeHighestOneBit(byte r0) {
        return (byte) Integer.highestOneBit(r0 & UByte.MAX_VALUE);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final short takeHighestOneBit(short r1) {
        return (short) Integer.highestOneBit(r1 & UShort.MAX_VALUE);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final byte takeLowestOneBit(byte r0) {
        return (byte) Integer.lowestOneBit(r0);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final short takeLowestOneBit(short r0) {
        return (short) Integer.lowestOneBit(r0);
    }
}
