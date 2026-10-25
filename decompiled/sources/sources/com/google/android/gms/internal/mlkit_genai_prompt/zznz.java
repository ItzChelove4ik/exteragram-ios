package com.google.android.gms.internal.mlkit_genai_prompt;

import j$.time.Duration;
import j$.time.temporal.ChronoUnit;
import java.math.BigDecimal;
import java.math.RoundingMode;
import kotlin.jvm.internal.LongCompanionObject;

/* loaded from: classes4.dex */
public abstract class zznz {
    static final Duration zza = null;
    public static final Duration zzb = null;

    static {
        zza = Duration.ofSeconds(Long.MIN_VALUE);
        zzb = Duration.ofSeconds(LongCompanionObject.MAX_VALUE, 999999999);
        Duration.ofMillis(LongCompanionObject.MAX_VALUE);
        Duration.ofMillis(Long.MIN_VALUE);
        ChronoUnit r2 = ChronoUnit.MICROS;
        Duration.of(LongCompanionObject.MAX_VALUE, r2);
        Duration.of(Long.MIN_VALUE, r2);
        Duration.ofNanos(LongCompanionObject.MAX_VALUE);
        Duration.ofNanos(Long.MIN_VALUE);
        BigDecimal.valueOf(9.223372036854776E18d);
        BigDecimal.valueOf(-9.223372036854776E18d);
        Duration.ofMillis(LongCompanionObject.MAX_VALUE).plusNanos(999999);
    }

    public static Duration zza(double r5) {
        if (r5 < 9.223372036854776E18d) goto L7;
        return zzb;
    L7:
        if (r5 <= (-9.223372036854776E18d)) goto L9;
        RoundingMode r0 = RoundingMode.FLOOR;
        long r1 = zznx.zza(r5, r0);
        return Duration.ofSeconds(r1, zznx.zza((r5 - r1) * 1.0E9d, r0));
    L9:
        return zza;
    }
}
