package kotlin;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import kotlin.internal.InlineOnly;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u008a\u0004\u001a\u0016\u0010\u0003\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u008a\u0004\u001a\u0016\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u008a\u0004\u001a\u0016\u0010\u0005\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u008a\u0004\u001a\u0016\u0010\u0006\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u008a\u0004\u001a\u000e\u0010\u0007\u001a\u00020\u0001*\u00020\u0001H\u0087\u008a\u0004\u001a\u000e\u0010\b\u001a\u00020\u0001*\u00020\u0001H\u0087\u008a\u0004\u001a\u000e\u0010\t\u001a\u00020\u0001*\u00020\u0001H\u0087\u008a\u0004\u001a\u000e\u0010\n\u001a\u00020\u0001*\u00020\u0001H\u0087\u0088\u0004\u001a\u0016\u0010\u000b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u008c\u0004\u001a\u0016\u0010\f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u008c\u0004\u001a\u0016\u0010\r\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u008c\u0004\u001a\u0016\u0010\u000e\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\u008c\u0004\u001a\u0016\u0010\u0011\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\u008c\u0004\u001a\u000e\u0010\u0012\u001a\u00020\u0001*\u00020\u0010H\u0087\u0088\u0004\u001a\u000e\u0010\u0012\u001a\u00020\u0001*\u00020\u0013H\u0087\u0088\u0004\u001a\u000e\u0010\u0014\u001a\u00020\u0015*\u00020\u0001H\u0087\u0088\u0004\u001a\"\u0010\u0014\u001a\u00020\u0015*\u00020\u00012\b\b\u0002\u0010\u0016\u001a\u00020\u00102\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0087\u0088\u0004¨\u0006\u0019"}, d2 = {"plus", "Ljava/math/BigInteger;", "other", "minus", "times", "div", "rem", "unaryMinus", "inc", "dec", "inv", "and", PluginsConstants.HookFilterTypes.OR, "xor", "shl", "n", _UrlKt.FRAGMENT_ENCODE_SET, "shr", "toBigInteger", _UrlKt.FRAGMENT_ENCODE_SET, "toBigDecimal", "Ljava/math/BigDecimal;", "scale", "mathContext", "Ljava/math/MathContext;", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/NumbersKt")
/* loaded from: classes5.dex */
class NumbersKt__BigIntegersKt extends NumbersKt__BigDecimalsKt {
    public NumbersKt__BigIntegersKt() {
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger and(BigInteger r0, BigInteger r1) {
        return r0.and(r1);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger dec(BigInteger r1) {
        return r1.subtract(BigInteger.ONE);
    }

    @InlineOnly
    private static final BigInteger div(BigInteger r0, BigInteger r1) {
        return r0.divide(r1);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger inc(BigInteger r1) {
        return r1.add(BigInteger.ONE);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger inv(BigInteger r0) {
        return r0.not();
    }

    @InlineOnly
    private static final BigInteger minus(BigInteger r0, BigInteger r1) {
        return r0.subtract(r1);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger or(BigInteger r0, BigInteger r1) {
        return r0.or(r1);
    }

    @InlineOnly
    private static final BigInteger plus(BigInteger r0, BigInteger r1) {
        return r0.add(r1);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final BigInteger rem(BigInteger r0, BigInteger r1) {
        return r0.remainder(r1);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger shl(BigInteger r0, int r1) {
        return r0.shiftLeft(r1);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger shr(BigInteger r0, int r1) {
        return r0.shiftRight(r1);
    }

    @InlineOnly
    private static final BigInteger times(BigInteger r0, BigInteger r1) {
        return r0.multiply(r1);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(BigInteger r1) {
        return new BigDecimal(r1);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(BigInteger r1, int r2, MathContext r3) {
        return new BigDecimal(r1, r2, r3);
    }

    public static /* synthetic */ BigDecimal toBigDecimal$default(BigInteger r0, int r1, MathContext r2, int r3, Object r4) {
        if ((r3 & 1) == 0) goto L6;
        r1 = 0;
    L6:
        if ((r3 & 2) == 0) goto L9;
        r2 = MathContext.UNLIMITED;
    L9:
        return new BigDecimal(r0, r1, r2);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger toBigInteger(int r2) {
        return BigInteger.valueOf(r2);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger toBigInteger(long r0) {
        return BigInteger.valueOf(r0);
    }

    @InlineOnly
    private static final BigInteger unaryMinus(BigInteger r0) {
        return r0.negate();
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger xor(BigInteger r0, BigInteger r1) {
        return r0.xor(r1);
    }
}
