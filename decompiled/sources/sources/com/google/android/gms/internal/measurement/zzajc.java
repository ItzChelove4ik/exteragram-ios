package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public final class zzajc implements zzajb {

    @Deprecated
    public static final zzom zza = null;

    static {
        zza = zzagr.zzc().zza("measurement.integration.disable_firebase_instance_id", false);
    }

    public zzajc() {
    }

    @Override // com.google.android.gms.internal.measurement.zzajb
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzajb
    public final boolean zzb() {
        return ((Boolean) zza.get()).booleanValue();
    }
}
