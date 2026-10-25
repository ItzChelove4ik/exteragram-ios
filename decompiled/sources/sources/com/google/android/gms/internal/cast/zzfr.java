package com.google.android.gms.internal.cast;

import com.google.android.gms.common.Feature;

/* loaded from: classes4.dex */
public abstract class zzfr {
    public static final Feature zza = null;
    public static final Feature zzb = null;
    public static final Feature zzc = null;
    public static final Feature zzd = null;
    public static final Feature zze = null;
    public static final Feature zzf = null;
    public static final Feature[] zzg = null;

    static {
        Feature r0 = new Feature("usage_and_diagnostics_listener", 1, true);
        zza = r0;
        Feature r1 = new Feature("usage_and_diagnostics_consents", 1, true);
        zzb = r1;
        Feature r2 = new Feature("usage_and_diagnostics_check_consents", 1, true);
        zzc = r2;
        Feature r3 = new Feature("usage_and_diagnostics_settings_access", 1, true);
        zzd = r3;
        Feature r4 = new Feature("el_capitan", 1, false);
        zze = r4;
        Feature r5 = new Feature("stats", 1, true);
        zzf = r5;
        zzg = new Feature[]{r0, r1, r2, r3, r4, r5};
    }
}
