package com.google.android.gms.internal.fido;

/* loaded from: classes4.dex */
public final class zzhw implements zzhv {
    public static final zzaq zza = null;
    public static final zzaq zzb = null;
    public static final zzaq zzc = null;
    public static final zzaq zzd = null;
    public static final zzaq zze = null;
    public static final zzaq zzf = null;
    public static final zzaq zzg = null;
    public static final zzaq zzh = null;

    static {
        zzay r0 = new zzay("com.google.android.gms.fido").zze(zzcf.zzl("FIDO")).zzd();
        zza = r0.zzc("Fido2Ctap2Support__disable_transport_comparator", true);
        zzb = r0.zzc("Fido2Ctap2Support__enable_cancelling_requests", true);
        zzc = r0.zzc("Fido2Ctap2Support__enable_flow_separation_refactor", false);
        zzd = r0.zzc("Fido2Ctap2Support__nfc_activity_destroyed_check", true);
        zze = r0.zzc("Fido2Ctap2Support__remove_this_device_for_assertions", false);
        zzf = r0.zzc("Fido2Ctap2Support__skip_usb_permission_dialog", false);
        zzg = r0.zzc("Fido2Ctap2Support__transport_controller_refactor", false);
        zzh = r0.zzc("Fido2Ctap2Support__update_user_cancel_response", false);
    }

    public zzhw() {
    }

    @Override // com.google.android.gms.internal.fido.zzhv
    public final boolean zza() {
        return ((Boolean) zzc.zza()).booleanValue();
    }
}
