package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public final class zzahp implements zzaho {

    @Deprecated
    public static final zzom zza = null;

    @Deprecated
    public static final zzom zzb = null;

    @Deprecated
    public static final zzom zzc = null;

    static {
        zzog r0 = zzagr.zzc();
        zza = r0.zza("measurement.audience.refresh_event_count_filters_timestamp", false);
        zzb = r0.zza("measurement.audience.use_bundle_end_timestamp_for_non_sequence_property_filters", false);
        zzc = r0.zza("measurement.audience.use_bundle_timestamp_for_event_count_filters", false);
    }

    public zzahp() {
    }

    @Override // com.google.android.gms.internal.measurement.zzaho
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzaho
    public final boolean zzb() {
        return ((Boolean) zza.get()).booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzaho
    public final boolean zzc() {
        return ((Boolean) zzb.get()).booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzaho
    public final boolean zzd() {
        return ((Boolean) zzc.get()).booleanValue();
    }
}
