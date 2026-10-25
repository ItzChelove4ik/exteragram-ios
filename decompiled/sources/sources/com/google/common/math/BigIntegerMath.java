package com.google.common.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/* loaded from: classes5.dex */
public abstract class BigIntegerMath {
    private static final double LN_10 = 0.0d;
    private static final double LN_2 = 0.0d;
    static final BigInteger SQRT2_PRECOMPUTED_BITS = null;

    static {
        SQRT2_PRECOMPUTED_BITS = new BigInteger("16a09e667f3bcc908b2fb1366ea957d3e3adec17512775099da2f590b0667322a", 16);
        LN_10 = Math.log(10.0d);
        LN_2 = Math.log(2.0d);
    }

    public static BigInteger divide(BigInteger r1, BigInteger r2, RoundingMode r3) {
        return new BigDecimal(r1).divide(new BigDecimal(r2), 0, r3).toBigIntegerExact();
    }
}
