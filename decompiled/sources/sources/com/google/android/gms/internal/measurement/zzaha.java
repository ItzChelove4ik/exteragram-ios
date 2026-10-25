package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public final class zzaha implements zzagz {

    @Deprecated
    public static final zzom zza = null;

    @Deprecated
    public static final zzom zzb = null;

    static {
        zzog r0 = zzagr.zzc();
        zza = r0.zza("measurement.set_default_event_parameters.fix_app_update_logging", true);
        zzb = r0.zza("measurement.set_default_event_parameters.fix_service_request_ordering", false);
    }

    public zzaha() {
    }

    @Override // com.google.android.gms.internal.measurement.zzagz
    public final boolean zza() {
        return ((Boolean) zza.get()).booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzagz
    public final boolean zzb() {
        return ((Boolean) zzb.get()).booleanValue();
    }
}
