package com.google.android.gms.internal.measurement;

import c.f$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public enum zzabw extends Enum implements zzady {
    public static final zzabw zza = null;
    public static final zzabw zzb = null;
    public static final zzabw zzc = null;
    public static final zzabw zzd = null;
    public static final zzabw zze = null;
    public static final zzabw zzf = null;
    public static final zzabw zzg = null;
    public static final zzabw zzh = null;
    public static final zzabw zzi = null;
    public static final zzabw zzj = null;
    public static final zzabw zzk = null;
    public static final zzabw zzl = null;
    public static final zzabw zzm = null;
    private static final /* synthetic */ zzabw[] zzo = null;
    private final int zzn;

    static {
        zzabw r0 = new zzabw("IAB_TCF_PURPOSE_UNKNOWN", 0, 0);
        zza = r0;
        zzabw r1 = new zzabw("IAB_TCF_PURPOSE_STORE_AND_ACCESS_INFORMATION_ON_A_DEVICE", 1, 1);
        zzb = r1;
        zzabw r2 = new zzabw("IAB_TCF_PURPOSE_SELECT_BASIC_ADS", 2, 2);
        zzc = r2;
        zzabw r3 = new zzabw("IAB_TCF_PURPOSE_CREATE_A_PERSONALISED_ADS_PROFILE", 3, 3);
        zzd = r3;
        zzabw r4 = new zzabw("IAB_TCF_PURPOSE_SELECT_PERSONALISED_ADS", 4, 4);
        zze = r4;
        zzabw r5 = new zzabw("IAB_TCF_PURPOSE_CREATE_A_PERSONALISED_CONTENT_PROFILE", 5, 5);
        zzf = r5;
        zzabw r6 = new zzabw("IAB_TCF_PURPOSE_SELECT_PERSONALISED_CONTENT", 6, 6);
        zzg = r6;
        zzabw r7 = new zzabw("IAB_TCF_PURPOSE_MEASURE_AD_PERFORMANCE", 7, 7);
        zzh = r7;
        zzabw r8 = new zzabw("IAB_TCF_PURPOSE_MEASURE_CONTENT_PERFORMANCE", 8, 8);
        zzi = r8;
        zzabw r9 = new zzabw("IAB_TCF_PURPOSE_APPLY_MARKET_RESEARCH_TO_GENERATE_AUDIENCE_INSIGHTS", 9, 9);
        zzj = r9;
        zzabw r10 = new zzabw("IAB_TCF_PURPOSE_DEVELOP_AND_IMPROVE_PRODUCTS", 10, 10);
        zzk = r10;
        zzabw r11 = new zzabw("IAB_TCF_PURPOSE_USE_LIMITED_DATA_TO_SELECT_CONTENT", 11, 11);
        zzl = r11;
        zzabw r12 = new zzabw("UNRECOGNIZED", 12, -1);
        zzm = r12;
        zzo = new zzabw[]{r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12};
    }

    zzabw(String r1, int r2, int r3) {
        this.zzn = r3;
    }

    public static zzabw[] values() {
        return (zzabw[]) zzo.clone();
    }

    @Override // java.lang.Enum
    public final String toString() {
        return Integer.toString(this.zzn);
    }

    @Override // com.google.android.gms.internal.measurement.zzady
    public final int zza() {
        if (this != zzm) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Can't get the number of an unknown enum value.");
        return 0;
    L5:
        return this.zzn;
    }
}
