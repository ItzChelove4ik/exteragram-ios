package com.google.android.gms.internal.mlkit_vision_common;

import kotlin.jvm.internal.LongCompanionObject;

/* loaded from: classes4.dex */
public final class zzin {
    private Long zza;
    private zzio zzb;
    private zzii zzc;
    private Integer zzd;
    private Integer zze;
    private Integer zzf;
    private Integer zzg;

    public zzin() {
    }

    public static /* bridge */ /* synthetic */ zzii zza(zzin r0) {
        return r0.zzc;
    }

    public static /* bridge */ /* synthetic */ zzio zzi(zzin r0) {
        return r0.zzb;
    }

    public static /* bridge */ /* synthetic */ Integer zzk(zzin r0) {
        return r0.zzd;
    }

    public static /* bridge */ /* synthetic */ Integer zzl(zzin r0) {
        return r0.zzf;
    }

    public static /* bridge */ /* synthetic */ Integer zzm(zzin r0) {
        return r0.zze;
    }

    public static /* bridge */ /* synthetic */ Integer zzn(zzin r0) {
        return r0.zzg;
    }

    public static /* bridge */ /* synthetic */ Long zzo(zzin r0) {
        return r0.zza;
    }

    public final zzin zzb(Long r5) {
        this.zza = Long.valueOf(r5.longValue() & LongCompanionObject.MAX_VALUE);
        return this;
    }

    public final zzin zzc(Integer r2) {
        this.zzd = Integer.valueOf(r2.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final zzin zzd(zzii r1) {
        this.zzc = r1;
        return this;
    }

    public final zzin zze(Integer r2) {
        this.zzf = Integer.valueOf(r2.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final zzin zzf(zzio r1) {
        this.zzb = r1;
        return this;
    }

    public final zzin zzg(Integer r2) {
        this.zze = Integer.valueOf(r2.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final zzin zzh(Integer r2) {
        this.zzg = Integer.valueOf(r2.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final zziq zzj() {
        return new zziq(this, null);
    }
}
