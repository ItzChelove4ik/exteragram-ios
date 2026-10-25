package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public final class zzahm implements zzahl {

    @Deprecated
    public static final zzom zza = null;

    @Deprecated
    public static final zzom zzb = null;

    static {
        zzog r0 = zzagr.zzc();
        zza = r0.zza("measurement.service.store_null_safelist", true);
        zzb = r0.zza("measurement.service.store_safelist", true);
    }

    public zzahm() {
    }

    @Override // com.google.android.gms.internal.measurement.zzahl
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzahl
    public final boolean zzb() {
        return ((Boolean) zza.get()).booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzahl
    public final boolean zzc() {
        return ((Boolean) zzb.get()).booleanValue();
    }
}
