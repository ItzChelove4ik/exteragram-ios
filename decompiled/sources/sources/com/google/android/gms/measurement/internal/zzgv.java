package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* loaded from: classes5.dex */
public final class zzgv {
    public final String zza;
    public final String zzb;
    public final long zzc;
    public final long zzd;
    public final Bundle zze;

    public zzgv(String r1, String r2, Bundle r3, long r4, long r6) {
        this.zza = r1;
        this.zzb = r2;
        this.zze = r3;
        this.zzc = r4;
        this.zzd = r6;
    }

    public static zzgv zza(zzbh r8) {
        return new zzgv(r8.zza, r8.zzc, r8.zzb.zzf(), r8.zzd, r8.zze);
    }

    public final String toString() {
        String r0 = this.zzb;
        String r1 = this.zze.toString();
        int r2 = String.valueOf(r0).length();
        String r5 = this.zza;
        int r3 = String.valueOf(r5).length();
        int r22 = ((r2 + 13) + r3) + 8;
        StringBuilder r32 = new StringBuilder(r22 + r1.length());
        r32.append("origin=");
        r32.append(r0);
        r32.append(",name=");
        r32.append(r5);
        r32.append(",params=");
        r32.append(r1);
        return r32.toString();
    }

    public final zzbh zzb() {
        zzbf r2 = new zzbf(new Bundle(this.zze));
        String r3 = this.zzb;
        long r4 = this.zzc;
        return new zzbh(this.zza, r2, r3, r4, this.zzd);
    }
}
