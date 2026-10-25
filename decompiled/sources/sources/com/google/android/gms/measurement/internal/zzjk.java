package com.google.android.gms.measurement.internal;

/* loaded from: classes5.dex */
public enum zzjk extends Enum {
    public static final zzjk zza = null;
    public static final zzjk zzb = null;
    public static final zzjk zzc = null;
    public static final zzjk zzd = null;
    private static final /* synthetic */ zzjk[] zzf = null;
    public final String zze;

    static {
        zzjk r0 = new zzjk("AD_STORAGE", 0, "ad_storage");
        zza = r0;
        zzjk r1 = new zzjk("ANALYTICS_STORAGE", 1, "analytics_storage");
        zzb = r1;
        zzjk r2 = new zzjk("AD_USER_DATA", 2, "ad_user_data");
        zzc = r2;
        zzjk r3 = new zzjk("AD_PERSONALIZATION", 3, "ad_personalization");
        zzd = r3;
        zzf = new zzjk[]{r0, r1, r2, r3};
    }

    zzjk(String r1, int r2, String r3) {
        this.zze = r3;
    }

    public static zzjk[] values() {
        return (zzjk[]) zzf.clone();
    }
}
