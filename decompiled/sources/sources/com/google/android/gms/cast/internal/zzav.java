package com.google.android.gms.cast.internal;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.internal.cast.zzfk;
import java.util.Locale;

/* loaded from: classes4.dex */
public final class zzav {
    public static final Object zzf = null;
    protected final Logger zza;
    long zzb;
    long zzc;
    zzat zzd;
    Runnable zze;
    private final long zzg;
    private final String zzh;
    private final Handler zzi;
    private final Clock zzj;

    static {
        zzf = new Object();
    }

    public zzav(long r1, String r3) {
        this.zzg = r1;
        this.zzh = r3;
        this.zzj = DefaultClock.getInstance();
        this.zzb = -1;
        this.zzc = 0;
        this.zzi = new zzfk(Looper.getMainLooper());
        this.zza = new Logger("RequestTracker", r3);
    }

    private final boolean zzg(int r5, Object r6) {
        Object r62 = zzf;
        monitor-enter(r62);
    L10:
        th = move-exception;
        throw th;
    L5:
        if (zzb() == false) goto L12;
        zzh(r5, null, String.format(Locale.ROOT, "clearing request %d", new Object[]{Long.valueOf(this.zzb)}));     // Catch: Throwable -> L10
        monitor-exit(r62);     // Catch: Throwable -> L10
        return true;
    L12:
        monitor-exit(r62);     // Catch: Throwable -> L10
        return false;
    }

    private final void zzh(int r12, Object r13, String r14) {
        Logger r1 = this.zza;
        r1.d(r14, new Object[0]);
        Object r142 = zzf;
        monitor-enter(r142);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (this.zzd == null) goto L9;
        long r9 = this.zzj.currentTimeMillis();     // Catch: Throwable -> L7
        ((zzat) Preconditions.checkNotNull(this.zzd)).zzb(this.zzh, this.zzb, r12, r13, this.zzc, r9);     // Catch: Throwable -> L7
    L9:
        this.zzb = -1;     // Catch: Throwable -> L7
        this.zzd = null;     // Catch: Throwable -> L7
        monitor-enter(r142);     // Catch: Throwable -> L7
        Runnable r132 = this.zze;     // Catch: Throwable -> L15
        if (r132 != null) goto L17;
        monitor-exit(r142);     // Catch: Throwable -> L15
    L19:
        monitor-exit(r142);     // Catch: Throwable -> L7
        return;
    L17:
        this.zzi.removeCallbacks(r132);     // Catch: Throwable -> L15
        this.zze = null;     // Catch: Throwable -> L15
        monitor-exit(r142);     // Catch: Throwable -> L15
    L15:
        th = move-exception;
        throw th;     // Catch: Throwable -> L7
    }

    public final void zza(long r11, zzat r13) {
        long r7 = this.zzj.currentTimeMillis();
        Object r9 = zzf;
        monitor-enter(r9);
        zzat r1 = this.zzd;     // Catch: Throwable -> L19
        long r3 = this.zzb;     // Catch: Throwable -> L19
        long r5 = this.zzc;     // Catch: Throwable -> L19
        this.zzb = r11;     // Catch: Throwable -> L19
        this.zzd = r13;     // Catch: Throwable -> L19
        this.zzc = r7;     // Catch: Throwable -> L19
        monitor-exit(r9);     // Catch: Throwable -> L19
        if (r1 == null) goto L8;
        r1.zza(this.zzh, r3, r5, r7);
    L8:
        monitor-enter(r9);
        Runnable r112 = this.zze;     // Catch: Throwable -> L12
        if (r112 == null) goto L14;
        this.zzi.removeCallbacks(r112);     // Catch: Throwable -> L12
    L14:
        zzau r113 = new zzau(this);     // Catch: Throwable -> L12
        this.zze = r113;     // Catch: Throwable -> L12
        this.zzi.postDelayed(r113, this.zzg);     // Catch: Throwable -> L12
        monitor-exit(r9);     // Catch: Throwable -> L12
        return;
    L12:
        th = move-exception;
        throw th;
    L19:
        th = move-exception;
        throw th;
    }

    public final boolean zzb() {
        Object r0 = zzf;
        monitor-enter(r0);
    L10:
        th = move-exception;
        throw th;
    L5:
        if (this.zzb == (-1)) goto L7;
        boolean r5 = true;
    L8:
        monitor-exit(r0);     // Catch: Throwable -> L10
        return r5;
    L7:
        r5 = false;
        goto L8
    }

    public final boolean zzc(long r6) {
        Object r0 = zzf;
        monitor-enter(r0);
        long r1 = this.zzb;     // Catch: Throwable -> L11
        boolean r3 = false;
        if (r1 != (-1)) goto L7;
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L11
        return r3;
    L7:
        if (r1 != r6) goto L9;
        r3 = true;
    L11:
        th = move-exception;
        throw th;
    }

    public final boolean zzd(long r4, int r6, Object r7) {
        Object r0 = zzf;
        monitor-enter(r0);
    L10:
        th = move-exception;
        throw th;
    L5:
        if (zzc(r4) == false) goto L12;
        zzh(r6, r7, String.format(Locale.ROOT, "request %d completed", new Object[]{Long.valueOf(r4)}));     // Catch: Throwable -> L10
        monitor-exit(r0);     // Catch: Throwable -> L10
        return true;
    L12:
        monitor-exit(r0);     // Catch: Throwable -> L10
        return false;
    }

    public final boolean zze(int r2) {
        return zzg(CastStatusCodes.CANCELED, null);
    }

    public final /* synthetic */ void zzf() {
        Object r0 = zzf;
        monitor-enter(r0);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (zzb() == true) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L10:
        zzg(15, null);     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
    }
}
