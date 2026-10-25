package com.google.android.gms.wallet;

import com.google.android.gms.common.Feature;

/* loaded from: classes5.dex */
public abstract class zzk {
    public static final Feature zza = null;
    public static final Feature zzb = null;
    public static final Feature zzc = null;
    public static final Feature zzd = null;
    public static final Feature zze = null;
    public static final Feature zzf = null;
    public static final Feature zzg = null;
    public static final Feature zzh = null;
    public static final Feature[] zzi = null;

    static {
        Feature r0 = new Feature("wallet", 1);
        zza = r0;
        Feature r1 = new Feature("wallet_biometric_auth_keys", 1);
        zzb = r1;
        Feature r2 = new Feature("wallet_payment_dynamic_update", 2);
        zzc = r2;
        Feature r3 = new Feature("wallet_1p_initialize_buyflow", 1);
        zzd = r3;
        Feature r4 = new Feature("wallet_warm_up_ui_process", 1);
        zze = r4;
        Feature r5 = new Feature("wallet_get_setup_wizard_intent", 4);
        zzf = r5;
        Feature r6 = new Feature("wallet_get_payment_card_recognition_intent", 1);
        zzg = r6;
        Feature r7 = new Feature("wallet_save_instrument", 1);
        zzh = r7;
        zzi = new Feature[]{r0, r1, r2, r3, r4, r5, r6, r7};
    }
}
