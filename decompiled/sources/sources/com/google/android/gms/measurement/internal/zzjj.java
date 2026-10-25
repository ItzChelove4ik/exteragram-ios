package com.google.android.gms.measurement.internal;

/* loaded from: classes5.dex */
public enum zzjj extends Enum {
    public static final zzjj zza = null;
    public static final zzjj zzb = null;
    private static final /* synthetic */ zzjj[] zzd = null;
    private final zzjk[] zzc;

    static {
        zzjj r0 = new zzjj("STORAGE", 0, new zzjk[]{zzjk.zza, zzjk.zzb});
        zza = r0;
        zzjj r1 = new zzjj("DMA", 1, new zzjk[]{zzjk.zzc});
        zzb = r1;
        zzd = new zzjj[]{r0, r1};
    }

    zzjj(String r1, int r2, zzjk... r3) {
        this.zzc = r3;
    }

    public static zzjj[] values() {
        return (zzjj[]) zzd.clone();
    }

    public final zzjk[] zza() {
        return this.zzc;
    }

    public final /* synthetic */ zzjk[] zzb() {
        return this.zzc;
    }
}
