package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.internal.IntrinsicConstEvaluation;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\b\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\u0010\t\n\u0002\u0010\u0007\n\u0002\u0010\u0006\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\u0088\u0004\u001a\u0016\u0010\u0004\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\u0088\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0005H\u0087\u0088\u0004\u001a\u0016\u0010\u0004\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0005H\u0087\u0088\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u0016\u0010\u0004\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0006H\u0087\u0088\u0004\u001a\u0016\u0010\u0004\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0006H\u0087\u0088\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\u0088\u0004\u001a\u0016\u0010\u0004\u001a\u00020\u0002*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\u0088\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0005H\u0087\u0088\u0004\u001a\u0016\u0010\u0004\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0005H\u0087\u0088\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u0016\u0010\u0004\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u0006*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0006H\u0087\u0088\u0004\u001a\u0016\u0010\u0004\u001a\u00020\u0006*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0006H\u0087\u0088\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\u0088\u0004\u001a\u0016\u0010\u0004\u001a\u00020\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\u0088\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0005H\u0087\u0088\u0004\u001a\u0016\u0010\u0004\u001a\u00020\u0005*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0005H\u0087\u0088\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u0016\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u0006*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0006H\u0087\u0088\u0004\u001a\u0016\u0010\u0004\u001a\u00020\u0006*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0006H\u0087\u0088\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\u0088\u0004\u001a\u0016\u0010\u0004\u001a\u00020\u0002*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\u0088\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0005H\u0087\u0088\u0004\u001a\u0016\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0005H\u0087\u0088\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u0016\u0010\u0004\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0006H\u0087\u0088\u0004\u001a\u0016\u0010\u0004\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0006H\u0087\u0088\u0004\u001a\u0016\u0010\u0004\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0007H\u0087\u0088\u0004\u001a\u0016\u0010\u0004\u001a\u00020\b*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\bH\u0087\u0088\u0004\u001a\u0016\u0010\u0004\u001a\u00020\b*\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0007H\u0087\u0088\u0004\u001a\u0016\u0010\u0004\u001a\u00020\b*\u00020\b2\u0006\u0010\u0003\u001a\u00020\bH\u0087\u0088\u0004¨\u0006\t"}, d2 = {"floorDiv", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "other", "mod", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/NumbersKt")
/* loaded from: classes5.dex */
class NumbersKt__FloorDivModKt extends NumbersKt__BigIntegersKt {
    public NumbersKt__FloorDivModKt() {
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final int floorDiv(byte r2, byte r3) {
        int r0 = r2 / r3;
        if ((r2 ^ r3) < 0) goto L5;
        return r0;
    L5:
        if ((r3 * r0) != r2) goto L7;
        return r0;
    L7:
        return r0 - 1;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final int floorDiv(byte r2, int r3) {
        int r0 = r2 / r3;
        if ((r2 ^ r3) < 0) goto L5;
        return r0;
    L5:
        if ((r3 * r0) != r2) goto L7;
        return r0;
    L7:
        return r0 - 1;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final int floorDiv(byte r2, short r3) {
        int r0 = r2 / r3;
        if ((r2 ^ r3) < 0) goto L5;
        return r0;
    L5:
        if ((r3 * r0) != r2) goto L7;
        return r0;
    L7:
        return r0 - 1;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final int floorDiv(int r2, byte r3) {
        int r0 = r2 / r3;
        if ((r2 ^ r3) < 0) goto L5;
        return r0;
    L5:
        if ((r3 * r0) != r2) goto L7;
        return r0;
    L7:
        return r0 - 1;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final int floorDiv(int r2, int r3) {
        int r0 = r2 / r3;
        if ((r2 ^ r3) < 0) goto L5;
        return r0;
    L5:
        if ((r3 * r0) != r2) goto L7;
        return r0;
    L7:
        return r0 - 1;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final int floorDiv(int r2, short r3) {
        int r0 = r2 / r3;
        if ((r2 ^ r3) < 0) goto L5;
        return r0;
    L5:
        if ((r3 * r0) != r2) goto L7;
        return r0;
    L7:
        return r0 - 1;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final int floorDiv(short r2, byte r3) {
        int r0 = r2 / r3;
        if ((r2 ^ r3) < 0) goto L5;
        return r0;
    L5:
        if ((r3 * r0) != r2) goto L7;
        return r0;
    L7:
        return r0 - 1;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final int floorDiv(short r2, int r3) {
        int r0 = r2 / r3;
        if ((r2 ^ r3) < 0) goto L5;
        return r0;
    L5:
        if ((r3 * r0) != r2) goto L7;
        return r0;
    L7:
        return r0 - 1;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final int floorDiv(short r2, short r3) {
        int r0 = r2 / r3;
        if ((r2 ^ r3) < 0) goto L5;
        return r0;
    L5:
        if ((r3 * r0) != r2) goto L7;
        return r0;
    L7:
        return r0 - 1;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final long floorDiv(byte r8, long r9) {
        long r0 = r8;
        long r2 = r0 / r9;
        if ((r0 ^ r9) < 0) goto L5;
        return r2;
    L5:
        if ((r9 * r2) != r0) goto L7;
        return r2;
    L7:
        return r2 - 1;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final long floorDiv(int r8, long r9) {
        long r0 = r8;
        long r2 = r0 / r9;
        if ((r0 ^ r9) < 0) goto L5;
        return r2;
    L5:
        if ((r9 * r2) != r0) goto L7;
        return r2;
    L7:
        return r2 - 1;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final long floorDiv(long r8, byte r10) {
        long r0 = r10;
        long r2 = r8 / r0;
        if ((r8 ^ r0) < 0) goto L5;
        return r2;
    L5:
        if ((r0 * r2) != r8) goto L7;
        return r2;
    L7:
        return r2 - 1;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final long floorDiv(long r8, int r10) {
        long r0 = r10;
        long r2 = r8 / r0;
        if ((r8 ^ r0) < 0) goto L5;
        return r2;
    L5:
        if ((r0 * r2) != r8) goto L7;
        return r2;
    L7:
        return r2 - 1;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final long floorDiv(long r6, long r8) {
        long r0 = r6 / r8;
        if ((r6 ^ r8) < 0) goto L5;
        return r0;
    L5:
        if ((r8 * r0) != r6) goto L7;
        return r0;
    L7:
        return r0 - 1;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final long floorDiv(long r8, short r10) {
        long r0 = r10;
        long r2 = r8 / r0;
        if ((r8 ^ r0) < 0) goto L5;
        return r2;
    L5:
        if ((r0 * r2) != r8) goto L7;
        return r2;
    L7:
        return r2 - 1;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final long floorDiv(short r8, long r9) {
        long r0 = r8;
        long r2 = r0 / r9;
        if ((r0 ^ r9) < 0) goto L5;
        return r2;
    L5:
        if ((r9 * r2) != r0) goto L7;
        return r2;
    L7:
        return r2 - 1;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final byte mod(byte r2, byte r3) {
        int r22 = r2 % r3;
        return (byte) (r22 + (r3 & (((r22 ^ r3) & ((-r22) | r22)) >> 31)));
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final byte mod(int r2, byte r3) {
        int r22 = r2 % r3;
        return (byte) (r22 + (r3 & (((r22 ^ r3) & ((-r22) | r22)) >> 31)));
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final byte mod(long r6, byte r8) {
        long r62 = r6 % r8;
        return (byte) (r62 + (r0 & (((r62 ^ r0) & ((-r62) | r62)) >> 63)));
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final byte mod(short r2, byte r3) {
        int r22 = r2 % r3;
        return (byte) (r22 + (r3 & (((r22 ^ r3) & ((-r22) | r22)) >> 31)));
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final double mod(double r4, double r6) {
        double r42 = r4 % r6;
        if (r42 != 0.0d) goto L6;
        return r42;
    L6:
        if (Math.signum(r42) != Math.signum(r6)) goto L9;
        return r42;
    L9:
        return r42 + r6;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final double mod(double r6, float r8) {
        double r0 = r8;
        double r62 = r6 % r0;
        if (r62 != 0.0d) goto L6;
        return r62;
    L6:
        if (Math.signum(r62) != Math.signum(r0)) goto L9;
        return r62;
    L9:
        return r62 + r0;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final double mod(float r6, double r7) {
        double r0 = r6 % r7;
        if (r0 != 0.0d) goto L6;
        return r0;
    L6:
        if (Math.signum(r0) != Math.signum(r7)) goto L9;
        return r0;
    L9:
        return r0 + r7;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final float mod(float r2, float r3) {
        float r22 = r2 % r3;
        if (r22 != 0.0f) goto L6;
        return r22;
    L6:
        if (Math.signum(r22) != Math.signum(r3)) goto L9;
        return r22;
    L9:
        return r22 + r3;
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final int mod(byte r2, int r3) {
        int r22 = r2 % r3;
        return r22 + (r3 & (((r22 ^ r3) & ((-r22) | r22)) >> 31));
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final int mod(int r2, int r3) {
        int r22 = r2 % r3;
        return r22 + (r3 & (((r22 ^ r3) & ((-r22) | r22)) >> 31));
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final int mod(long r6, int r8) {
        long r0 = r8;
        long r62 = r6 % r0;
        return (int) (r62 + (r0 & (((r62 ^ r0) & ((-r62) | r62)) >> 63)));
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final int mod(short r2, int r3) {
        int r22 = r2 % r3;
        return r22 + (r3 & (((r22 ^ r3) & ((-r22) | r22)) >> 31));
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final long mod(byte r6, long r7) {
        long r0 = r6 % r7;
        return r0 + (r7 & (((r0 ^ r7) & ((-r0) | r0)) >> 63));
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final long mod(int r6, long r7) {
        long r0 = r6 % r7;
        return r0 + (r7 & (((r0 ^ r7) & ((-r0) | r0)) >> 63));
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final long mod(long r4, long r6) {
        long r42 = r4 % r6;
        return r42 + (r6 & (((r42 ^ r6) & ((-r42) | r42)) >> 63));
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final long mod(short r6, long r7) {
        long r0 = r6 % r7;
        return r0 + (r7 & (((r0 ^ r7) & ((-r0) | r0)) >> 63));
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final short mod(byte r2, short r3) {
        int r22 = r2 % r3;
        return (short) (r22 + (r3 & (((r22 ^ r3) & ((-r22) | r22)) >> 31)));
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final short mod(int r2, short r3) {
        int r22 = r2 % r3;
        return (short) (r22 + (r3 & (((r22 ^ r3) & ((-r22) | r22)) >> 31)));
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final short mod(long r6, short r8) {
        long r62 = r6 % r8;
        return (short) (r62 + (r0 & (((r62 ^ r0) & ((-r62) | r62)) >> 63)));
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final short mod(short r2, short r3) {
        int r22 = r2 % r3;
        return (short) (r22 + (r3 & (((r22 ^ r3) & ((-r22) | r22)) >> 31)));
    }
}
