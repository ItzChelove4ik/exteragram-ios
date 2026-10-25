package kotlin;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import kotlin.internal.InlineOnly;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0010\u0007\n\u0002\u0010\u0006\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u008a\u0004\u001a\u0016\u0010\u0003\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u008a\u0004\u001a\u0016\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u008a\u0004\u001a\u0016\u0010\u0005\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u008a\u0004\u001a\u0016\u0010\u0006\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u008a\u0004\u001a\u000e\u0010\u0007\u001a\u00020\u0001*\u00020\u0001H\u0087\u008a\u0004\u001a\u000e\u0010\b\u001a\u00020\u0001*\u00020\u0001H\u0087\u008a\u0004\u001a\u000e\u0010\t\u001a\u00020\u0001*\u00020\u0001H\u0087\u008a\u0004\u001a\u000e\u0010\n\u001a\u00020\u0001*\u00020\u000bH\u0087\u0088\u0004\u001a\u0016\u0010\n\u001a\u00020\u0001*\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0087\u0088\u0004\u001a\u000e\u0010\n\u001a\u00020\u0001*\u00020\u000eH\u0087\u0088\u0004\u001a\u0016\u0010\n\u001a\u00020\u0001*\u00020\u000e2\u0006\u0010\f\u001a\u00020\rH\u0087\u0088\u0004\u001a\u000e\u0010\n\u001a\u00020\u0001*\u00020\u000fH\u0087\u0088\u0004\u001a\u0016\u0010\n\u001a\u00020\u0001*\u00020\u000f2\u0006\u0010\f\u001a\u00020\rH\u0087\u0088\u0004\u001a\u000e\u0010\n\u001a\u00020\u0001*\u00020\u0010H\u0087\u0088\u0004\u001a\u0016\u0010\n\u001a\u00020\u0001*\u00020\u00102\u0006\u0010\f\u001a\u00020\rH\u0087\u0088\u0004¨\u0006\u0011"}, d2 = {"plus", "Ljava/math/BigDecimal;", "other", "minus", "times", "div", "rem", "unaryMinus", "inc", "dec", "toBigDecimal", _UrlKt.FRAGMENT_ENCODE_SET, "mathContext", "Ljava/math/MathContext;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/NumbersKt")
/* loaded from: classes5.dex */
class NumbersKt__BigDecimalsKt {
    public NumbersKt__BigDecimalsKt() {
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal dec(BigDecimal r1) {
        return r1.subtract(BigDecimal.ONE);
    }

    @InlineOnly
    private static final BigDecimal div(BigDecimal r1, BigDecimal r2) {
        return r1.divide(r2, RoundingMode.HALF_EVEN);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal inc(BigDecimal r1) {
        return r1.add(BigDecimal.ONE);
    }

    @InlineOnly
    private static final BigDecimal minus(BigDecimal r0, BigDecimal r1) {
        return r0.subtract(r1);
    }

    @InlineOnly
    private static final BigDecimal plus(BigDecimal r0, BigDecimal r1) {
        return r0.add(r1);
    }

    @InlineOnly
    private static final BigDecimal rem(BigDecimal r0, BigDecimal r1) {
        return r0.remainder(r1);
    }

    @InlineOnly
    private static final BigDecimal times(BigDecimal r0, BigDecimal r1) {
        return r0.multiply(r1);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(double r1) {
        return new BigDecimal(String.valueOf(r1));
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(double r1, MathContext r3) {
        return new BigDecimal(String.valueOf(r1), r3);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(float r1) {
        return new BigDecimal(String.valueOf(r1));
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(float r1, MathContext r2) {
        return new BigDecimal(String.valueOf(r1), r2);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(int r2) {
        return BigDecimal.valueOf(r2);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(int r1, MathContext r2) {
        return new BigDecimal(r1, r2);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(long r0) {
        return BigDecimal.valueOf(r0);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(long r1, MathContext r3) {
        return new BigDecimal(r1, r3);
    }

    @InlineOnly
    private static final BigDecimal unaryMinus(BigDecimal r0) {
        return r0.negate();
    }
}
