package com.google.android.gms.internal.fido;

/* loaded from: classes4.dex */
public final class zzic implements zzib {
    public static final zzaq zza = null;
    public static final zzaq zzb = null;
    public static final zzaq zzc = null;
    public static final zzaq zzd = null;
    public static final zzaq zze = null;
    public static final zzaq zzf = null;
    public static final zzaq zzg = null;
    public static final zzaq zzh = null;
    public static final zzaq zzi = null;
    public static final zzaq zzj = null;
    public static final zzaq zzk = null;
    public static final zzaq zzl = null;
    public static final zzaq zzm = null;
    public static final zzaq zzn = null;
    public static final zzaq zzo = null;
    public static final zzaq zzp = null;
    public static final zzaq zzq = null;
    public static final zzaq zzr = null;
    public static final zzaq zzs = null;

    static {
        zzay r0 = new zzay("com.google.android.gms.fido").zze(zzcf.zzl("FIDO")).zzd();
        zza = r0.zzc("Passkeys__check_all_keys", false);
        zzb = r0.zzc("Passkeys__check_sync_status", true);
        zzc = r0.zzc("Passkeys__client_data_hash_override_for_security_keys", false);
        zzd = r0.zzc("Passkeys__direct_assetlinks", false);
        zze = r0.zza("Passkeys__direct_assetlinks_cache_seconds", 604800);
        zzf = r0.zzb("Passkeys__direct_assetlinks_rpids", "*");
        zzg = r0.zzc("Passkeys__dispatch_prf_via_credman", true);
        zzh = r0.zzb("Passkeys__help_center_url", "https://support.google.com/accounts/answer/6208650");
        zzi = r0.zzc("Passkeys__hide_consent_page_in_registration_enabled", false);
        zzj = r0.zzc("Passkeys__ignore_stop_during_hybrid_request", false);
        zzk = r0.zzc("Passkeys__json_for_parcelables", false);
        zzl = r0.zzc("Passkeys__passkey_entries_use_gpm_icon", false);
        zzm = r0.zzc("Passkeys__reencrypt_passkey", false);
        zzn = r0.zzc("Passkeys__return_cryptauth_status", false);
        zzo = r0.zzc("Passkeys__set_key_version", true);
        zzp = r0.zza("Passkeys__should_show_welcome_fragment", -1);
        zzq = r0.zzc("Passkeys__skip_consent_after_retrieval", false);
        zzr = r0.zzc("Passkeys__skip_consent_screen", false);
        zzs = r0.zzc("Passkeys__use_result_receiver", false);
    }

    public zzic() {
    }

    @Override // com.google.android.gms.internal.fido.zzib
    public final boolean zza() {
        return ((Boolean) zzk.zza()).booleanValue();
    }
}
