package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.DoubleCompanionObject;
import kotlin.jvm.internal.FloatCompanionObject;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\t\u001a\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\u0088\u0004\u001a\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u0003H\u0087\u0088\u0004\u001a\u000e\u0010\u0004\u001a\u00020\u0001*\u00020\u0002H\u0087\u0088\u0004\u001a\u000e\u0010\u0004\u001a\u00020\u0001*\u00020\u0003H\u0087\u0088\u0004\u001a\u000e\u0010\u0005\u001a\u00020\u0001*\u00020\u0002H\u0087\u0088\u0004\u001a\u000e\u0010\u0005\u001a\u00020\u0001*\u00020\u0003H\u0087\u0088\u0004\u001a\u000e\u0010\u0006\u001a\u00020\u0007*\u00020\u0002H\u0087\u0088\u0004\u001a\u000e\u0010\b\u001a\u00020\u0007*\u00020\u0002H\u0087\u0088\u0004\u001a\u0016\u0010\t\u001a\u00020\u0002*\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0007H\u0087\u0088\u0004\u001a\u000e\u0010\u0006\u001a\u00020\f*\u00020\u0003H\u0087\u0088\u0004\u001a\u000e\u0010\b\u001a\u00020\f*\u00020\u0003H\u0087\u0088\u0004\u001a\u0016\u0010\t\u001a\u00020\u0003*\u00020\r2\u0006\u0010\u000b\u001a\u00020\fH\u0087\u0088\u0004\u001a\u000e\u0010\u000e\u001a\u00020\f*\u00020\fH\u0087\u0088\u0004\u001a\u000e\u0010\u000f\u001a\u00020\f*\u00020\fH\u0087\u0088\u0004\u001a\u000e\u0010\u0010\u001a\u00020\f*\u00020\fH\u0087\u0088\u0004\u001a\u000e\u0010\u0011\u001a\u00020\f*\u00020\fH\u0087\u0088\u0004\u001a\u000e\u0010\u0012\u001a\u00020\f*\u00020\fH\u0087\u0088\u0004\u001a\u0016\u0010\u0013\u001a\u00020\f*\u00020\f2\u0006\u0010\u0014\u001a\u00020\fH\u0087\u0088\u0004\u001a\u0016\u0010\u0015\u001a\u00020\f*\u00020\f2\u0006\u0010\u0014\u001a\u00020\fH\u0087\u0088\u0004\u001a\u000e\u0010\u000e\u001a\u00020\f*\u00020\u0007H\u0087\u0088\u0004\u001a\u000e\u0010\u000f\u001a\u00020\f*\u00020\u0007H\u0087\u0088\u0004\u001a\u000e\u0010\u0010\u001a\u00020\f*\u00020\u0007H\u0087\u0088\u0004\u001a\u000e\u0010\u0011\u001a\u00020\u0007*\u00020\u0007H\u0087\u0088\u0004\u001a\u000e\u0010\u0012\u001a\u00020\u0007*\u00020\u0007H\u0087\u0088\u0004\u001a\u0016\u0010\u0013\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u0014\u001a\u00020\fH\u0087\u0088\u0004\u001a\u0016\u0010\u0015\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u0014\u001a\u00020\fH\u0087\u0088\u0004¨\u0006\u0016"}, d2 = {"isNaN", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "isInfinite", "isFinite", "toBits", _UrlKt.FRAGMENT_ENCODE_SET, "toRawBits", "fromBits", "Lkotlin/Double$Companion;", "bits", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/Float$Companion;", "countOneBits", "countLeadingZeroBits", "countTrailingZeroBits", "takeHighestOneBit", "takeLowestOneBit", "rotateLeft", "bitCount", "rotateRight", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/NumbersKt")
/* loaded from: classes5.dex */
class NumbersKt__NumbersJVMKt extends NumbersKt__FloorDivModKt {
    public NumbersKt__NumbersJVMKt() {
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final int countLeadingZeroBits(int r0) {
        return Integer.numberOfLeadingZeros(r0);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final int countLeadingZeroBits(long r0) {
        return Long.numberOfLeadingZeros(r0);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final int countOneBits(int r0) {
        return Integer.bitCount(r0);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final int countOneBits(long r0) {
        return Long.bitCount(r0);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final int countTrailingZeroBits(int r0) {
        return Integer.numberOfTrailingZeros(r0);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final int countTrailingZeroBits(long r0) {
        return Long.numberOfTrailingZeros(r0);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final double fromBits(DoubleCompanionObject r0, long r1) {
        return Double.longBitsToDouble(r1);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float fromBits(FloatCompanionObject r0, int r1) {
        return Float.intBitsToFloat(r1);
    }

    @InlineOnly
    private static final boolean isFinite(double r2) {
        if (Math.abs(r2) > Double.MAX_VALUE) goto L6;
        return true;
    L6:
        return false;
    }

    @InlineOnly
    private static final boolean isFinite(float r1) {
        if (Math.abs(r1) > Float.MAX_VALUE) goto L6;
        return true;
    L6:
        return false;
    }

    @InlineOnly
    private static final boolean isInfinite(double r0) {
        return Double.isInfinite(r0);
    }

    @InlineOnly
    private static final boolean isInfinite(float r0) {
        return Float.isInfinite(r0);
    }

    @InlineOnly
    private static final boolean isNaN(double r0) {
        return Double.isNaN(r0);
    }

    @InlineOnly
    private static final boolean isNaN(float r0) {
        return Float.isNaN(r0);
    }

    @SinceKotlin(version = "1.6")
    @InlineOnly
    private static final int rotateLeft(int r0, int r1) {
        return Integer.rotateLeft(r0, r1);
    }

    @SinceKotlin(version = "1.6")
    @InlineOnly
    private static final long rotateLeft(long r0, int r2) {
        return Long.rotateLeft(r0, r2);
    }

    @SinceKotlin(version = "1.6")
    @InlineOnly
    private static final int rotateRight(int r0, int r1) {
        return Integer.rotateRight(r0, r1);
    }

    @SinceKotlin(version = "1.6")
    @InlineOnly
    private static final long rotateRight(long r0, int r2) {
        return Long.rotateRight(r0, r2);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final int takeHighestOneBit(int r0) {
        return Integer.highestOneBit(r0);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final long takeHighestOneBit(long r0) {
        return Long.highestOneBit(r0);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final int takeLowestOneBit(int r0) {
        return Integer.lowestOneBit(r0);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final long takeLowestOneBit(long r0) {
        return Long.lowestOneBit(r0);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final int toBits(float r0) {
        return Float.floatToIntBits(r0);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final long toBits(double r0) {
        return Double.doubleToLongBits(r0);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final int toRawBits(float r0) {
        return Float.floatToRawIntBits(r0);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final long toRawBits(double r0) {
        return Double.doubleToRawLongBits(r0);
    }
}
