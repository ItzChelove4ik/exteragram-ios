package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.Feature;

/* loaded from: classes4.dex */
public abstract class zzjn {
    public static final Feature zza = null;
    public static final Feature zzb = null;
    public static final Feature zzc = null;
    public static final Feature zzd = null;
    public static final Feature zze = null;
    public static final Feature zzf = null;
    public static final Feature zzg = null;
    public static final Feature zzh = null;
    public static final Feature zzi = null;
    public static final Feature[] zzj = null;

    static {
        Feature r0 = new Feature("commit_to_configuration_v2_api", 1, true);
        zza = r0;
        Feature r1 = new Feature("get_serving_version_api", 1, true);
        zzb = r1;
        Feature r2 = new Feature("get_experiment_tokens_api", 1, true);
        zzc = r2;
        Feature r3 = new Feature("register_flag_update_listener_api", 2, true);
        zzd = r3;
        Feature r4 = new Feature("sync_after_api", 1, true);
        zze = r4;
        Feature r5 = new Feature("sync_after_for_application_api", 1, true);
        zzf = r5;
        Feature r6 = new Feature("set_app_wide_properties_api", 1, true);
        zzg = r6;
        Feature r7 = new Feature("set_runtime_properties_api", 1, true);
        zzh = r7;
        Feature r8 = new Feature("get_storage_info_api", 1, true);
        zzi = r8;
        zzj = new Feature[]{r0, r1, r2, r3, r4, r5, r6, r7, r8};
    }
}
