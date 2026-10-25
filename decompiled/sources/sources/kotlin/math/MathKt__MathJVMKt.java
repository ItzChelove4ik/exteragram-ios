package kotlin.math;

import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.cast.MediaTrack;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0006\n\u0002\b%\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\t\n\u0002\u0010\u0007\n\u0002\b\b\u001a\u0012\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u0012\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u0012\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u0012\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u0012\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u0012\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u001a\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u0012\u0010\n\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u0012\u0010\u000b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u0012\u0010\f\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u0012\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u0080\u0004\u001a\u0012\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u0080\u0004\u001a\u0012\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u0080\u0004\u001a\u001a\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u0012\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u0012\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u0012\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u001a\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0001H\u0087\u0080\u0004\u001a\u0012\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u0012\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u0012\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u0080\u0004\u001a\u0012\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u0012\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u0012\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u0012\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u0080\u0004\u001a\u0012\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u0012\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u0012\u0010\u001f\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u001a\u0010 \u001a\u00020\u00012\u0006\u0010!\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u001a\u0010#\u001a\u00020\u00012\u0006\u0010!\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u0012\u0010$\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u0016\u0010%\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u0016\u0010%\u001a\u00020\u0001*\u00020\u00012\u0006\u0010&\u001a\u00020'H\u0087\u0088\u0004\u001a\u0016\u0010(\u001a\u00020\u0001*\u00020\u00012\u0006\u0010)\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u0016\u00101\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u001f\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u0016\u00101\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u001f\u001a\u00020'H\u0087\u0088\u0004\u001a\u000e\u00105\u001a\u00020\u0001*\u00020\u0001H\u0087\u0088\u0004\u001a\u000e\u00106\u001a\u00020\u0001*\u00020\u0001H\u0087\u0088\u0004\u001a\u0016\u00107\u001a\u00020\u0001*\u00020\u00012\u0006\u00108\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u000e\u00109\u001a\u00020'*\u00020\u0001H\u0087\u0080\u0004\u001a\u000e\u0010:\u001a\u00020;*\u00020\u0001H\u0087\u0080\u0004\u001a\u0012\u0010\u0000\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020<H\u0087\u0088\u0004\u001a\u0012\u0010\u0003\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020<H\u0087\u0088\u0004\u001a\u0012\u0010\u0004\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020<H\u0087\u0088\u0004\u001a\u0012\u0010\u0005\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020<H\u0087\u0088\u0004\u001a\u0012\u0010\u0006\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020<H\u0087\u0088\u0004\u001a\u0012\u0010\u0007\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020<H\u0087\u0088\u0004\u001a\u001a\u0010\b\u001a\u00020<2\u0006\u0010\t\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020<H\u0087\u0088\u0004\u001a\u0012\u0010\n\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020<H\u0087\u0088\u0004\u001a\u0012\u0010\u000b\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020<H\u0087\u0088\u0004\u001a\u0012\u0010\f\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020<H\u0087\u0088\u0004\u001a\u0012\u0010\r\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020<H\u0087\u0088\u0004\u001a\u0012\u0010\u000e\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020<H\u0087\u0088\u0004\u001a\u0012\u0010\u000f\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020<H\u0087\u0088\u0004\u001a\u001a\u0010\u0010\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020<2\u0006\u0010\t\u001a\u00020<H\u0087\u0088\u0004\u001a\u0012\u0010\u0011\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020<H\u0087\u0088\u0004\u001a\u0012\u0010\u0012\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020<H\u0087\u0088\u0004\u001a\u0012\u0010\u0013\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020<H\u0087\u0088\u0004\u001a\u001a\u0010\u0014\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020<2\u0006\u0010\u0015\u001a\u00020<H\u0087\u0080\u0004\u001a\u0012\u0010\u0016\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020<H\u0087\u0088\u0004\u001a\u0012\u0010\u0017\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020<H\u0087\u0088\u0004\u001a\u0012\u0010\u0018\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020<H\u0087\u0080\u0004\u001a\u0012\u0010\u0019\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020<H\u0087\u0088\u0004\u001a\u0012\u0010\u001a\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020<H\u0087\u0088\u0004\u001a\u0012\u0010\u001b\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020<H\u0087\u0088\u0004\u001a\u0012\u0010\u001c\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020<H\u0087\u0080\u0004\u001a\u0012\u0010\u001d\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020<H\u0087\u0088\u0004\u001a\u0012\u0010\u001e\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020<H\u0087\u0088\u0004\u001a\u0012\u0010\u001f\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020<H\u0087\u0088\u0004\u001a\u001a\u0010 \u001a\u00020<2\u0006\u0010!\u001a\u00020<2\u0006\u0010\"\u001a\u00020<H\u0087\u0088\u0004\u001a\u001a\u0010#\u001a\u00020<2\u0006\u0010!\u001a\u00020<2\u0006\u0010\"\u001a\u00020<H\u0087\u0088\u0004\u001a\u0012\u0010$\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020<H\u0087\u0088\u0004\u001a\u0016\u0010%\u001a\u00020<*\u00020<2\u0006\u0010\u0002\u001a\u00020<H\u0087\u0088\u0004\u001a\u0016\u0010%\u001a\u00020<*\u00020<2\u0006\u0010&\u001a\u00020'H\u0087\u0088\u0004\u001a\u0016\u0010(\u001a\u00020<*\u00020<2\u0006\u0010)\u001a\u00020<H\u0087\u0088\u0004\u001a\u0016\u00101\u001a\u00020<*\u00020<2\u0006\u0010\u001f\u001a\u00020<H\u0087\u0088\u0004\u001a\u0016\u00101\u001a\u00020<*\u00020<2\u0006\u0010\u001f\u001a\u00020'H\u0087\u0088\u0004\u001a\u000e\u00105\u001a\u00020<*\u00020<H\u0087\u0088\u0004\u001a\u000e\u00106\u001a\u00020<*\u00020<H\u0087\u0088\u0004\u001a\u0016\u00107\u001a\u00020<*\u00020<2\u0006\u00108\u001a\u00020<H\u0087\u0088\u0004\u001a\u000e\u00109\u001a\u00020'*\u00020<H\u0087\u0080\u0004\u001a\u000e\u0010:\u001a\u00020;*\u00020<H\u0087\u0080\u0004\u001a\u0012\u0010\u001e\u001a\u00020'2\u0006\u0010&\u001a\u00020'H\u0087\u0088\u0004\u001a\u001a\u0010 \u001a\u00020'2\u0006\u0010!\u001a\u00020'2\u0006\u0010\"\u001a\u00020'H\u0087\u0088\u0004\u001a\u001a\u0010#\u001a\u00020'2\u0006\u0010!\u001a\u00020'2\u0006\u0010\"\u001a\u00020'H\u0087\u0088\u0004\u001a\u0012\u0010\u001e\u001a\u00020;2\u0006\u0010&\u001a\u00020;H\u0087\u0088\u0004\u001a\u001a\u0010 \u001a\u00020;2\u0006\u0010!\u001a\u00020;2\u0006\u0010\"\u001a\u00020;H\u0087\u0088\u0004\u001a\u001a\u0010#\u001a\u00020;2\u0006\u0010!\u001a\u00020;2\u0006\u0010\"\u001a\u00020;H\u0087\u0088\u0004\" \u0010*\u001a\u00020\u0001*\u00020\u00018Æ\u0002X\u0087\u0084\b¢\u0006\f\u0012\u0004\b+\u0010,\u001a\u0004\b-\u0010.\" \u0010\u001f\u001a\u00020\u0001*\u00020\u00018Æ\u0002X\u0087\u0084\b¢\u0006\f\u0012\u0004\b/\u0010,\u001a\u0004\b0\u0010.\" \u00102\u001a\u00020\u0001*\u00020\u00018Æ\u0002X\u0087\u0084\b¢\u0006\f\u0012\u0004\b3\u0010,\u001a\u0004\b4\u0010.\" \u0010*\u001a\u00020<*\u00020<8Æ\u0002X\u0087\u0084\b¢\u0006\f\u0012\u0004\b+\u0010=\u001a\u0004\b-\u0010>\" \u0010\u001f\u001a\u00020<*\u00020<8Æ\u0002X\u0087\u0084\b¢\u0006\f\u0012\u0004\b/\u0010=\u001a\u0004\b0\u0010>\" \u00102\u001a\u00020<*\u00020<8Æ\u0002X\u0087\u0084\b¢\u0006\f\u0012\u0004\b3\u0010=\u001a\u0004\b4\u0010>\" \u0010*\u001a\u00020'*\u00020'8Æ\u0002X\u0087\u0084\b¢\u0006\f\u0012\u0004\b+\u0010?\u001a\u0004\b-\u0010@\"\u001f\u0010\u001f\u001a\u00020'*\u00020'8FX\u0087\u0084\b¢\u0006\f\u0012\u0004\b/\u0010?\u001a\u0004\b0\u0010@\" \u0010*\u001a\u00020;*\u00020;8Æ\u0002X\u0087\u0084\b¢\u0006\f\u0012\u0004\b+\u0010A\u001a\u0004\b-\u0010B\"\u001f\u0010\u001f\u001a\u00020'*\u00020;8FX\u0087\u0084\b¢\u0006\f\u0012\u0004\b/\u0010A\u001a\u0004\b0\u0010C¨\u0006D"}, d2 = {"sin", _UrlKt.FRAGMENT_ENCODE_SET, "x", "cos", "tan", "asin", "acos", "atan", "atan2", "y", "sinh", "cosh", "tanh", "asinh", "acosh", "atanh", "hypot", "sqrt", "exp", "expm1", "log", "base", "ln", "log10", "log2", "ln1p", "ceil", "floor", "truncate", "round", "abs", MediaTrack.ROLE_SIGN, "min", "a", "b", "max", "cbrt", "pow", "n", _UrlKt.FRAGMENT_ENCODE_SET, "IEEErem", "divisor", "absoluteValue", "getAbsoluteValue$annotations", "(D)V", "getAbsoluteValue", "(D)D", "getSign$annotations", "getSign", "withSign", "ulp", "getUlp$annotations", "getUlp", "nextUp", "nextDown", "nextTowards", "to", "roundToInt", "roundToLong", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "(F)V", "(F)F", "(I)V", "(I)I", "(J)V", "(J)J", "(J)I", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/math/MathKt")
/* loaded from: classes5.dex */
class MathKt__MathJVMKt extends MathKt__MathHKt {
    public MathKt__MathJVMKt() {
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final double IEEErem(double r0, double r2) {
        return Math.IEEEremainder(r0, r2);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float IEEErem(float r2, float r3) {
        return (float) Math.IEEEremainder(r2, r3);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final double abs(double r0) {
        return Math.abs(r0);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float abs(float r0) {
        return Math.abs(r0);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final int abs(int r0) {
        return Math.abs(r0);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final long abs(long r0) {
        return Math.abs(r0);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final double acos(double r0) {
        return Math.acos(r0);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float acos(float r2) {
        return (float) Math.acos(r2);
    }

    @SinceKotlin(version = "1.2")
    public static final double acosh(double r6) {
        if (r6 >= 1.0d) goto L7;
        return Double.NaN;
    L7:
        if (r6 > Constants.upper_taylor_2_bound) goto L9;
        double r2 = r6 - 1.0d;
        if (r2 >= Constants.taylor_n_bound) goto L13;
        double r62 = Math.sqrt(r2);
        if (r62 < Constants.taylor_2_bound) goto L18;
        r62 = r62 - (((r62 * r62) * r62) / 12.0d);
    L18:
        return Math.sqrt(2.0d) * r62;
    L13:
        return Math.log(r6 + Math.sqrt((r6 * r6) - 1.0d));
    L9:
        return Math.log(r6) + Constants.LN2;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float acosh(float r2) {
        return (float) acosh(r2);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final double asin(double r0) {
        return Math.asin(r0);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float asin(float r2) {
        return (float) Math.asin(r2);
    }

    @SinceKotlin(version = "1.2")
    public static final double asinh(double r5) {
        double r0 = Constants.taylor_n_bound;
        if (r5 < r0) goto L15;
        if (r5 <= Constants.upper_taylor_n_bound) goto L13;
        if (r5 > Constants.upper_taylor_2_bound) goto L9;
        double r52 = r5 * 2.0d;
        return Math.log(r52 + (1.0d / r52));
    L9:
        return Math.log(r5) + Constants.LN2;
    L13:
        return Math.log(r5 + Math.sqrt((r5 * r5) + 1.0d));
    L15:
        if (r5 > (-r0)) goto L19;
        return -asinh(-r5);
    L19:
        if (Math.abs(r5) >= Constants.taylor_2_bound) goto L21;
        return r5;
    L21:
        return r5 - (((r5 * r5) * r5) / 6.0d);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float asinh(float r2) {
        return (float) asinh(r2);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final double atan(double r0) {
        return Math.atan(r0);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float atan(float r2) {
        return (float) Math.atan(r2);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final double atan2(double r0, double r2) {
        return Math.atan2(r0, r2);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float atan2(float r2, float r3) {
        return (float) Math.atan2(r2, r3);
    }

    @SinceKotlin(version = "1.2")
    public static final double atanh(double r4) {
        if (Math.abs(r4) >= Constants.taylor_n_bound) goto L9;
        if (Math.abs(r4) > Constants.taylor_2_bound) goto L7;
        return r4;
    L7:
        return r4 + (((r4 * r4) * r4) / 3.0d);
    L9:
        return Math.log((1.0d + r4) / (1.0d - r4)) / 2.0d;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float atanh(float r2) {
        return (float) atanh(r2);
    }

    @SinceKotlin(version = "1.8")
    @InlineOnly
    private static final double cbrt(double r0) {
        return Math.cbrt(r0);
    }

    @SinceKotlin(version = "1.8")
    @InlineOnly
    private static final float cbrt(float r2) {
        return (float) Math.cbrt(r2);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final double ceil(double r0) {
        return Math.ceil(r0);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float ceil(float r2) {
        return (float) Math.ceil(r2);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final double cos(double r0) {
        return Math.cos(r0);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float cos(float r2) {
        return (float) Math.cos(r2);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final double cosh(double r0) {
        return Math.cosh(r0);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float cosh(float r2) {
        return (float) Math.cosh(r2);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final double exp(double r0) {
        return Math.exp(r0);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float exp(float r2) {
        return (float) Math.exp(r2);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final double expm1(double r0) {
        return Math.expm1(r0);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float expm1(float r2) {
        return (float) Math.expm1(r2);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final double floor(double r0) {
        return Math.floor(r0);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float floor(float r2) {
        return (float) Math.floor(r2);
    }

    private static final double getAbsoluteValue(double r0) {
        return Math.abs(r0);
    }

    private static final float getAbsoluteValue(float r0) {
        return Math.abs(r0);
    }

    private static final int getAbsoluteValue(int r0) {
        return Math.abs(r0);
    }

    private static final long getAbsoluteValue(long r0) {
        return Math.abs(r0);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    public static /* synthetic */ void getAbsoluteValue$annotations(double r0) {
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    public static /* synthetic */ void getAbsoluteValue$annotations(float r0) {
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    public static /* synthetic */ void getAbsoluteValue$annotations(int r0) {
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    public static /* synthetic */ void getAbsoluteValue$annotations(long r0) {
    }

    private static final double getSign(double r0) {
        return Math.signum(r0);
    }

    private static final float getSign(float r0) {
        return Math.signum(r0);
    }

    public static int getSign(int r0) {
        return Integer.signum(r0);
    }

    public static int getSign(long r0) {
        return Long.signum(r0);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    public static /* synthetic */ void getSign$annotations(double r0) {
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    public static /* synthetic */ void getSign$annotations(float r0) {
    }

    @SinceKotlin(version = "1.2")
    public static /* synthetic */ void getSign$annotations(int r0) {
    }

    @SinceKotlin(version = "1.2")
    public static /* synthetic */ void getSign$annotations(long r0) {
    }

    private static final double getUlp(double r0) {
        return Math.ulp(r0);
    }

    private static final float getUlp(float r0) {
        return Math.ulp(r0);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    public static /* synthetic */ void getUlp$annotations(double r0) {
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    public static /* synthetic */ void getUlp$annotations(float r0) {
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final double hypot(double r0, double r2) {
        return Math.hypot(r0, r2);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float hypot(float r2, float r3) {
        return (float) Math.hypot(r2, r3);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final double ln(double r0) {
        return Math.log(r0);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float ln(float r2) {
        return (float) Math.log(r2);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final double ln1p(double r0) {
        return Math.log1p(r0);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float ln1p(float r2) {
        return (float) Math.log1p(r2);
    }

    @SinceKotlin(version = "1.2")
    public static final double log(double r2, double r4) {
        if (r4 > 0.0d) goto L5;
        return Double.NaN;
    L5:
        if (r4 != 1.0d) goto L8;
        return Double.NaN;
    L8:
        return Math.log(r2) / Math.log(r4);
    }

    @SinceKotlin(version = "1.2")
    public static final float log(float r2, float r3) {
        if (r3 > 0.0f) goto L5;
        return Float.NaN;
    L5:
        if (r3 != 1.0f) goto L8;
        return Float.NaN;
    L8:
        return (float) (Math.log(r2) / Math.log(r3));
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final double log10(double r0) {
        return Math.log10(r0);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float log10(float r2) {
        return (float) Math.log10(r2);
    }

    @SinceKotlin(version = "1.2")
    public static double log2(double r2) {
        return Math.log(r2) / Constants.LN2;
    }

    @SinceKotlin(version = "1.2")
    public static final float log2(float r4) {
        return (float) (Math.log(r4) / Constants.LN2);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final double max(double r0, double r2) {
        return Math.max(r0, r2);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float max(float r0, float r1) {
        return Math.max(r0, r1);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final int max(int r0, int r1) {
        return Math.max(r0, r1);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final long max(long r0, long r2) {
        return Math.max(r0, r2);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final double min(double r0, double r2) {
        return Math.min(r0, r2);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float min(float r0, float r1) {
        return Math.min(r0, r1);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final int min(int r0, int r1) {
        return Math.min(r0, r1);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final long min(long r0, long r2) {
        return Math.min(r0, r2);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final double nextDown(double r2) {
        return Math.nextAfter(r2, Double.NEGATIVE_INFINITY);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float nextDown(float r2) {
        return Math.nextAfter(r2, Double.NEGATIVE_INFINITY);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final double nextTowards(double r0, double r2) {
        return Math.nextAfter(r0, r2);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float nextTowards(float r2, float r3) {
        return Math.nextAfter(r2, r3);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final double nextUp(double r0) {
        return Math.nextUp(r0);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float nextUp(float r0) {
        return Math.nextUp(r0);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final double pow(double r0, double r2) {
        return Math.pow(r0, r2);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final double pow(double r2, int r4) {
        return Math.pow(r2, r4);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float pow(float r2, float r3) {
        return (float) Math.pow(r2, r3);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float pow(float r2, int r3) {
        return (float) Math.pow(r2, r3);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final double round(double r0) {
        return Math.rint(r0);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float round(float r2) {
        return (float) Math.rint(r2);
    }

    @SinceKotlin(version = "1.2")
    public static int roundToInt(double r2) {
        if (Double.isNaN(r2) == false) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Cannot round NaN value.");
        return 0;
    L5:
        if (r2 <= 2.147483647E9d) goto L9;
        return Integer.MAX_VALUE;
    L9:
        if (r2 >= (-2.147483648E9d)) goto L13;
        return Integer.MIN_VALUE;
    L13:
        return (int) Math.round(r2);
    }

    @SinceKotlin(version = "1.2")
    public static int roundToInt(float r1) {
        if (Float.isNaN(r1) == false) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Cannot round NaN value.");
        return 0;
    L5:
        return Math.round(r1);
    }

    @SinceKotlin(version = "1.2")
    public static long roundToLong(double r1) {
        if (Double.isNaN(r1) == false) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Cannot round NaN value.");
        return 0;
    L5:
        return Math.round(r1);
    }

    @SinceKotlin(version = "1.2")
    public static final long roundToLong(float r2) {
        return roundToLong(r2);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final double sign(double r0) {
        return Math.signum(r0);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float sign(float r0) {
        return Math.signum(r0);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final double sin(double r0) {
        return Math.sin(r0);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float sin(float r2) {
        return (float) Math.sin(r2);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final double sinh(double r0) {
        return Math.sinh(r0);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float sinh(float r2) {
        return (float) Math.sinh(r2);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final double sqrt(double r0) {
        return Math.sqrt(r0);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float sqrt(float r2) {
        return (float) Math.sqrt(r2);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final double tan(double r0) {
        return Math.tan(r0);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float tan(float r2) {
        return (float) Math.tan(r2);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final double tanh(double r0) {
        return Math.tanh(r0);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float tanh(float r2) {
        return (float) Math.tanh(r2);
    }

    @SinceKotlin(version = "1.2")
    public static final double truncate(double r2) {
        if (Double.isNaN(r2) == false) goto L5;
        return r2;
    L5:
        if (Double.isInfinite(r2) == false) goto L8;
        return r2;
    L8:
        if (r2 <= 0.0d) goto L12;
        return Math.floor(r2);
    L12:
        return Math.ceil(r2);
    }

    @SinceKotlin(version = "1.2")
    public static final float truncate(float r2) {
        if (Float.isNaN(r2) == false) goto L5;
    L13:
        return r2;
    L5:
        if (Float.isInfinite(r2) == true) goto L13;
        if (r2 <= 0.0f) goto L12;
        double r0 = Math.floor(r2);
    L11:
        return (float) r0;
    L12:
        r0 = Math.ceil(r2);
        goto L11
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final double withSign(double r0, double r2) {
        return Math.copySign(r0, r2);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final double withSign(double r2, int r4) {
        return Math.copySign(r2, r4);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float withSign(float r0, float r1) {
        return Math.copySign(r0, r1);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float withSign(float r0, int r1) {
        return Math.copySign(r0, r1);
    }
}
