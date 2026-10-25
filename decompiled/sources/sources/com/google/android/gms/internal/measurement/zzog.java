package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public final class zzog {
    private final String zza;
    private final zzph zzb;

    public zzog(String r1, zzph r2) {
        this.zza = "com.google.android.gms.measurement";
        this.zzb = r2;
    }

    public final zzom zza(String r3, boolean r4) {
        zzph r0 = this.zzb;
        return new zznx(this.zza, r3, r0, r4);
    }

    public final zzom zzb(String r7, long r8) {
        zzph r3 = this.zzb;
        return new zzob(this.zza, r7, r3, r8);
    }

    public final zzom zzc(String r7, double r8) {
        zzph r3 = this.zzb;
        return new zznz(this.zza, "measurement.test.double_flag", r3, -3.0d);
    }

    public final zzom zzd(String r3, String r4) {
        zzph r0 = this.zzb;
        return new zzod(this.zza, r3, r0, r4);
    }
}
