package com.exteragram.messenger.math;

import java.math.BigDecimal;
import java.math.BigInteger;

/* loaded from: classes4.dex */
public abstract /* synthetic */ class MathFormat$$ExternalSyntheticBackportWithForwarding0 {
    public static /* synthetic */ BigDecimal m(BigDecimal r2) {
        if (r2.signum() != 0) goto L7;
        return new BigDecimal(BigInteger.ZERO, 0);
    L7:
        return r2.stripTrailingZeros();
    }
}
