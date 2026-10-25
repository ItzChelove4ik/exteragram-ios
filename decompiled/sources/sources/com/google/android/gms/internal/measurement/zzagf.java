package com.google.android.gms.internal.measurement;

import sun.misc.Unsafe;

/* loaded from: classes4.dex */
abstract class zzagf {
    final Unsafe zza;

    public zzagf(Unsafe r1) {
        this.zza = r1;
    }

    public abstract void zza(Object r1, long r2, byte r4);

    public abstract boolean zzb(Object r1, long r2);

    public abstract void zzc(Object r1, long r2, boolean r4);

    public abstract float zzd(Object r1, long r2);

    public abstract void zze(Object r1, long r2, float r4);

    public abstract double zzf(Object r1, long r2);

    public abstract void zzg(Object r1, long r2, double r4);
}
