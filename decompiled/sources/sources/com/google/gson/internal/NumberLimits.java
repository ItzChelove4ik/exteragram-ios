package com.google.gson.internal;

import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.math.BigDecimal;
import java.math.BigInteger;

/* loaded from: classes5.dex */
public abstract class NumberLimits {
    private static void checkNumberStringLength(String r3) {
        if (r3.length() > 10000) goto L6;
        return;
    L6:
        throw new NumberFormatException("Number string too large: " + r3.substring(0, 30) + "...");
    }

    public static BigDecimal parseBigDecimal(String r5) {
        checkNumberStringLength(r5);
        BigDecimal r0 = new BigDecimal(r5);
        if (Math.abs(r0.scale()) >= NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS) goto L6;
        return r0;
    L6:
        throw new NumberFormatException("Number has unsupported scale: " + r5);
    }

    public static BigInteger parseBigInteger(String r1) {
        checkNumberStringLength(r1);
        return new BigInteger(r1);
    }
}
