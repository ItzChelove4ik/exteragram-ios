package com.google.android.gms.internal.identity_credentials;

import com.google.android.gms.common.Feature;

/* loaded from: classes4.dex */
public abstract class zze {
    public static final Feature zza = null;
    public static final Feature zzb = null;
    public static final Feature zzc = null;
    public static final Feature zzd = null;
    public static final Feature zze = null;
    public static final Feature zzf = null;
    public static final Feature zzg = null;
    public static final Feature zzh = null;
    public static final Feature zzi = null;
    public static final Feature zzj = null;
    public static final Feature zzk = null;
    public static final Feature zzl = null;
    public static final Feature zzm = null;
    public static final Feature zzn = null;
    public static final Feature[] zzo = null;

    static {
        Feature r0 = new Feature("GET_CREDENTIAL", 1);
        zza = r0;
        Feature r1 = new Feature("CREDENTIAL_REGISTRY", 1);
        zzb = r1;
        Feature r4 = new Feature("CLEAR_REGISTRY", 2);
        zzc = r4;
        Feature r5 = new Feature("CLEAR_CREATION_OPTIONS", 1);
        zzd = r5;
        Feature r42 = new Feature("CLEAR_CREDENTIAL_STATE", 1);
        zze = r42;
        Feature r52 = new Feature("CREATE_CREDENTIAL", 3);
        zzf = r52;
        Feature r6 = new Feature("REGISTER_CREATION_OPTIONS", 1);
        zzg = r6;
        Feature r7 = new Feature("REGISTER_EXPORT", 1);
        zzh = r7;
        Feature r8 = new Feature("IMPORT_CREDENTIALS", 1);
        zzi = r8;
        Feature r13 = new Feature("SIGNAL_CREDENTIAL_STATE", 1);
        zzj = r13;
        Feature r14 = new Feature("CLEAR_EXPORT", 1);
        zzk = r14;
        Feature r11 = new Feature("IMPORT_CREDENTIALS_FOR_DEVICE_SETUP", 3);
        zzl = r11;
        Feature r12 = new Feature("EXPORT_CREDENTIALS_TO_DEVICE_SETUP", 3);
        zzm = r12;
        Feature r132 = new Feature("GET_CREDENTIAL_TRANSFER_CAPABILITIES", 3);
        zzn = r132;
        zzo = new Feature[]{r0, r1, r4, r5, r42, r52, r6, r7, r8, r13, r14, r11, r12, r132};
    }
}
