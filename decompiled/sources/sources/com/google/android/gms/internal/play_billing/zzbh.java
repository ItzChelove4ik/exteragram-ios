package com.google.android.gms.internal.play_billing;

import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
abstract /* synthetic */ class zzbh {
    static final /* synthetic */ int[] zza = null;

    static {
        int[] r0 = new int[TimeUnit.values().length];
        zza = r0;
        r0[TimeUnit.NANOSECONDS.ordinal()] = 1;     // Catch: NoSuchFieldError -> L11
    L18:
        zza[TimeUnit.MICROSECONDS.ordinal()] = 2;     // Catch: NoSuchFieldError -> L12
    L22:
        zza[TimeUnit.MILLISECONDS.ordinal()] = 3;     // Catch: NoSuchFieldError -> L13
    L30:
        zza[TimeUnit.SECONDS.ordinal()] = 4;     // Catch: NoSuchFieldError -> L14
    L20:
        zza[TimeUnit.MINUTES.ordinal()] = 5;     // Catch: NoSuchFieldError -> L15
    L24:
        zza[TimeUnit.HOURS.ordinal()] = 6;     // Catch: NoSuchFieldError -> L16
    L26:
        zza[TimeUnit.DAYS.ordinal()] = 7;     // Catch: NoSuchFieldError -> L17
        return;
    }
}
