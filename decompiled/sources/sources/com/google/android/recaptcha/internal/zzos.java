package com.google.android.recaptcha.internal;

import java.util.Locale;
import java.util.concurrent.TimeUnit;
import okio.AsyncTimeout$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class zzos {
    private boolean zza;
    private long zzb;
    private long zzc;

    public zzos() {
        int r0 = zzov.$r8$clinit;
    }

    public static zzos zza() {
        zzos r0 = new zzos();
        zzoq.zzc(!r0.zza, "This stopwatch is already running.");
        r0.zza = true;
        r0.zzc = System.nanoTime();
        return r0;
    }

    private final long zzd() {
        if (this.zza == false) goto L7;
        return (System.nanoTime() - this.zzc) + this.zzb;
    L7:
        return this.zzb;
    }

    public final String toString() {
        long r0 = zzd();
        long r2 = r0 / 86400000000000L;
        TimeUnit r22 = TimeUnit.NANOSECONDS;
        if (r2 <= 0) goto L6;
        TimeUnit r8 = TimeUnit.DAYS;
    L21:
        String r02 = String.format(Locale.ROOT, "%.4g", new Object[]{Double.valueOf(r0 / r22.convert(1, r8))});
        switch(zzor.zza[r8.ordinal()]) {
            case 1: goto L31;
            case 2: goto L30;
            case 3: goto L29;
            case 4: goto L28;
            case 5: goto L27;
            case 6: goto L26;
            case 7: goto L25;
            default: goto L23;
        };
    L23:
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return null;
    L25:
        String r82 = "d";
    L32:
        StringBuilder r23 = new StringBuilder((r02.length() + 1) + r82.length());
        r23.append(r02);
        r23.append(" ");
        r23.append(r82);
        return r23.toString();
    L26:
        r82 = "h";
        goto L32
    L27:
        r82 = "min";
        goto L32
    L28:
        r82 = "s";
        goto L32
    L29:
        r82 = "ms";
        goto L32
    L30:
        r82 = "μs";
        goto L32
    L31:
        r82 = "ns";
        goto L32
    L6:
        if ((r0 / 3600000000000L) <= 0) goto L9;
        r8 = TimeUnit.HOURS;
        goto L21
    L9:
        if ((r0 / 60000000000L) <= 0) goto L12;
        r8 = TimeUnit.MINUTES;
        goto L21
    L12:
        if ((r0 / 1000000000) <= 0) goto L15;
        r8 = TimeUnit.SECONDS;
        goto L21
    L15:
        if ((r0 / 1000000) <= 0) goto L18;
        r8 = TimeUnit.MILLISECONDS;
        goto L21
    L18:
        if ((r0 / 1000) <= 0) goto L20;
        r8 = TimeUnit.MICROSECONDS;
        goto L21
    L20:
        r8 = r22;
        goto L21
    }

    public final zzos zzb() {
        long r0 = System.nanoTime();
        zzoq.zzc(this.zza, "This stopwatch is already stopped.");
        this.zza = false;
        this.zzb += r0 - this.zzc;
        return this;
    }

    public final long zzc(TimeUnit r3) {
        return r3.convert(zzd(), TimeUnit.NANOSECONDS);
    }
}
