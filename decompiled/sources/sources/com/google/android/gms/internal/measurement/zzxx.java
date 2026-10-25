package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public abstract class zzxx {
    public static final zzyl zza = null;
    public static final zzyl zzb = null;
    public static final zzyl zzc = null;
    public static final zzyl zzd = null;
    public static final zzyl zze = null;
    public static final zzyl zzf = null;
    public static final zzyl zzg = null;
    public static final zzyl zzh = null;
    public static final zzyl zzi = null;

    static {
        zza = zzyl.zzc("cause", Throwable.class);
        zzb = zzyl.zzc("ratelimit_count", Integer.class);
        zzc = zzyl.zzc("sampling_count", Integer.class);
        zzd = zzyl.zzc("ratelimit_period", zzxm.class);
        zze = zzyl.zzc("skipped", Integer.class);
        zzf = new zzxv("group_by", Object.class, true);
        zzg = zzyl.zzc("forced", Boolean.class);
        zzh = new zzxw("tags", zzabe.class, false);
        zzi = zzyl.zzc("stack_size", zzyv.class);
    }
}
