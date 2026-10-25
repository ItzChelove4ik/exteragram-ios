package com.google.android.recaptcha.internal;

import java.util.Locale;
import java.util.Optional;

/* loaded from: classes5.dex */
public final class zzf extends Exception {
    public zzf(zze r8, zzc r9, long r10) {
        int r0 = (((((~1933084303) & 1081172490) | 1598823280) + ((1933084303 & 943317002) | 974030260)) - (-1740098486)) ^ (1578716908 % 62299853);
        Locale r1 = Locale.US;
        Object[] r02 = new Object[r0];
        r02[0] = Long.valueOf(r8.zza());
        r02[1] = Long.valueOf(r9.zza());
        int r82 = (((((~421825361) & 1965217800) | 1712162915) + ((421825361 & 295858312) | 210019829)) - 1704549116) ^ (1336194465 % 831099451);
        Long r102 = Long.valueOf(r10);
        r02[r82] = r102;
        super(String.format(r1, zzt.zza("bk3t6gFTc30="), r02));
        Optional.of(r9);
        Optional.of(r102);
    }

    public zzf(zze r4, Throwable r5) {
        Locale r0 = Locale.US;
        Object[] r42 = {Long.valueOf(r4.zza())};
        super(String.format(r0, zzt.zza("bk0="), r42), r5);
        Optional.empty();
        Optional.empty();
    }
}
