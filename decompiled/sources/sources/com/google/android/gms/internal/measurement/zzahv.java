package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public final class zzahv implements zzahu {

    @Deprecated
    public static final zzom zza = null;

    @Deprecated
    public static final zzom zzb = null;

    static {
        zzog r0 = zzagr.zzc();
        zza = r0.zza("45753512", false);
        zzb = r0.zza("measurement.gbraid_campaign.stop_lgclid", false);
    }

    public zzahv() {
    }

    @Override // com.google.android.gms.internal.measurement.zzahu
    public final boolean zza() {
        return ((Boolean) zza.get()).booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzahu
    public final boolean zzb() {
        return ((Boolean) zzb.get()).booleanValue();
    }
}
