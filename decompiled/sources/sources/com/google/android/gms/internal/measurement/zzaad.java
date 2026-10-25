package com.google.android.gms.internal.measurement;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/* loaded from: classes4.dex */
public abstract class zzaad {
    private static final String[] zzd = null;

    static {
        zzd = new String[]{"com.google.android.gms.internal.measurement.zzaaj", "com.google.common.flogger.backend.google.GooglePlatform", "com.google.common.flogger.backend.system.DefaultPlatform"};
    }

    public zzaad() {
    }

    public static int zza() {
        return zzabt.zza();
    }

    public static zzaac zzb() {
        return zzaab.zza().zzc();
    }

    public static zzzf zzd(String r1) {
        return zzaab.zza().zze(r1);
    }

    public static zzaat zzf() {
        return zzaab.zza().zzg();
    }

    public static boolean zzh(String r1, Level r2, boolean r3) {
        zzf().zzb(r1, r2, r3);
        return false;
    }

    public static zzabe zzi() {
        return zzf().zzc();
    }

    public static zzzj zzj() {
        return zzf().zzd();
    }

    public static long zzk() {
        return zzaab.zza().zzl();
    }

    public static /* synthetic */ String[] zzo() {
        return zzd;
    }

    public abstract zzaac zzc();

    public abstract zzzf zze(String r1);

    public abstract zzaat zzg();

    public long zzl() {
        return TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis());
    }
}
