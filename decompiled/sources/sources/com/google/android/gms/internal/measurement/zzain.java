package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public final class zzain implements zzaim {

    @Deprecated
    public static final zzom zza = null;

    @Deprecated
    public static final zzom zzb = null;

    static {
        zzog r0 = zzagr.zzc();
        zza = r0.zza("measurement.experiment.enable_passthrough_experiment_reporting", true);
        zzb = r0.zza("measurement.experiment.enable_phenotype_experiment_reporting", true);
    }

    public zzain() {
    }

    @Override // com.google.android.gms.internal.measurement.zzaim
    public final boolean zza() {
        return ((Boolean) zza.get()).booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzaim
    public final boolean zzb() {
        return ((Boolean) zzb.get()).booleanValue();
    }
}
