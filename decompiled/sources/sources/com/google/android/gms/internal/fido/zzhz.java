package com.google.android.gms.internal.fido;

/* loaded from: classes4.dex */
public final class zzhz implements zzhy {
    public static final zzaq zza = null;
    public static final zzaq zzb = null;
    public static final zzaq zzc = null;
    public static final zzaq zzd = null;
    public static final zzaq zze = null;
    public static final zzaq zzf = null;
    public static final zzaq zzg = null;

    static {
        zzay r0 = new zzay("com.google.android.gms.fido").zze(zzcf.zzl("FIDO")).zzd();
        zza = r0.zzc("Hybrid__client_enabled", false);
        zzb = r0.zzc("Hybrid__disavow_location_permissions", true);
        zzc = r0.zza("Hybrid__number_of_seconds_to_wait_for_bt_scanning_radio_to_be_ready", 3);
        zzd = r0.zza("Hybrid__number_of_seconds_to_wait_for_bt_scanning_turning_on", 3);
        zze = r0.zzc("Hybrid__prf_eval_during_create", true);
        zzf = r0.zzc("Hybrid__use_hybrid_for_server_link", false);
        zzg = r0.zzc("Hybrid__websocket_close_socket", true);
    }

    public zzhz() {
    }

    @Override // com.google.android.gms.internal.fido.zzhy
    public final boolean zza() {
        return ((Boolean) zza.zza()).booleanValue();
    }
}
