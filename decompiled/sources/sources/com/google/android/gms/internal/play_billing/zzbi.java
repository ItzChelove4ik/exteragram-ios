package com.google.android.gms.internal.play_billing;

import java.util.Locale;
import java.util.concurrent.TimeUnit;
import okio.AsyncTimeout$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class zzbi {
    private final zzbl zza;
    private boolean zzb;
    private long zzc;
    private long zzd;

    public zzbi(zzbl r2) {
        zzbg.zzc(r2, "ticker");
        this.zza = r2;
    }

    public static zzbi zzb(zzbl r1) {
        zzbi r0 = new zzbi(r1);
        r0.zze();
        return r0;
    }

    public static zzbi zzc(zzbl r1) {
        return new zzbi(r1);
    }

    private final long zzh() {
        if (this.zzb == false) goto L7;
        return (this.zza.zza() - this.zzd) + this.zzc;
    L7:
        return this.zzc;
    }

    public final String toString() {
        long r0 = zzh();
        long r2 = r0 / 86400000000000L;
        TimeUnit r22 = TimeUnit.NANOSECONDS;
        if (r2 <= 0) goto L6;
        TimeUnit r8 = TimeUnit.DAYS;
    L21:
        String r02 = String.format(Locale.ROOT, "%.4g", new Object[]{Double.valueOf(r0 / r22.convert(1, r8))});
        switch(zzbh.zza[r8.ordinal()]) {
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
    L33:
        return r02 + " " + r82;
    L26:
        r82 = "h";
        goto L33
    L27:
        r82 = "min";
        goto L33
    L28:
        r82 = "s";
        goto L33
    L29:
        r82 = "ms";
        goto L33
    L30:
        r82 = "μs";
        goto L33
    L31:
        r82 = "ns";
        goto L33
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

    public final long zza(TimeUnit r3) {
        return r3.convert(zzh(), TimeUnit.NANOSECONDS);
    }

    public final zzbi zzd() {
        this.zzc = 0;
        this.zzb = false;
        return this;
    }

    public final zzbi zze() {
        zzbg.zze(!this.zzb, "This stopwatch is already running.");
        this.zzb = true;
        this.zzd = this.zza.zza();
        return this;
    }

    public final zzbi zzf() {
        long r0 = this.zza.zza();
        zzbg.zze(this.zzb, "This stopwatch is already stopped.");
        this.zzb = false;
        this.zzc += r0 - this.zzd;
        return this;
    }

    public final boolean zzg() {
        return this.zzb;
    }
}
