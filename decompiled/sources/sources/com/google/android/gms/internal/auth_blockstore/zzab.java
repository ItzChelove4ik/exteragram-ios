package com.google.android.gms.internal.auth_blockstore;

import com.google.android.gms.common.Feature;

/* loaded from: classes4.dex */
public abstract class zzab {
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
    public static final Feature[] zzl = null;

    static {
        Feature r0 = new Feature("auth_blockstore", 3);
        zza = r0;
        Feature r1 = new Feature("blockstore_data_transfer", 1);
        zzb = r1;
        Feature r2 = new Feature("blockstore_notify_app_restore", 1);
        zzc = r2;
        Feature r3 = new Feature("blockstore_store_bytes_with_options", 2);
        zzd = r3;
        Feature r4 = new Feature("blockstore_is_end_to_end_encryption_available", 1);
        zze = r4;
        Feature r5 = new Feature("blockstore_enable_cloud_backup", 1);
        zzf = r5;
        Feature r6 = new Feature("blockstore_delete_bytes", 2);
        zzg = r6;
        Feature r7 = new Feature("blockstore_retrieve_bytes_with_options", 3);
        zzh = r7;
        Feature r8 = new Feature("auth_clear_restore_credential", 1);
        zzi = r8;
        Feature r9 = new Feature("auth_create_restore_credential", 1);
        zzj = r9;
        Feature r10 = new Feature("auth_get_restore_credential", 1);
        zzk = r10;
        zzl = new Feature[]{r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10};
    }
}
