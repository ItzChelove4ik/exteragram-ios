package com.google.android.gms.internal.measurement;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzahg implements zzahf {

    @Deprecated
    public static final zzom zza = null;

    @Deprecated
    public static final zzom zzb = null;

    @Deprecated
    public static final zzom zzc = null;

    static {
        zzog r0 = zzagr.zzc();
        zza = r0.zzd("45761323", _UrlKt.FRAGMENT_ENCODE_SET);
        zzb = r0.zzd("45762029", _UrlKt.FRAGMENT_ENCODE_SET);
        zzc = r0.zzd("45762030", _UrlKt.FRAGMENT_ENCODE_SET);
    }

    public zzahg() {
    }

    @Override // com.google.android.gms.internal.measurement.zzahf
    public final String zza() {
        return (String) zza.get();
    }

    @Override // com.google.android.gms.internal.measurement.zzahf
    public final String zzb() {
        return (String) zzb.get();
    }

    @Override // com.google.android.gms.internal.measurement.zzahf
    public final String zzc() {
        return (String) zzc.get();
    }
}
