package com.google.android.gms.internal.vision;

/* loaded from: classes5.dex */
enum zzix extends Enum<zzix> {
    public static final zzix zza = null;
    public static final zzix zzb = null;
    public static final zzix zzc = null;
    public static final zzix zzd = null;
    private static final /* synthetic */ zzix[] zzf = null;
    private final boolean zze;

    static {
        zzix r0 = new zzix("SCALAR", 0, false);
        zza = r0;
        zzix r1 = new zzix("VECTOR", 1, true);
        zzb = r1;
        zzix r3 = new zzix("PACKED_VECTOR", 2, true);
        zzc = r3;
        zzix r4 = new zzix("MAP", 3, false);
        zzd = r4;
        zzf = new zzix[]{r0, r1, r3, r4};
    }

    zzix(String r1, int r2, boolean r3) {
        this.zze = r3;
    }

    public static zzix[] values() {
        return (zzix[]) zzf.clone();
    }
}
