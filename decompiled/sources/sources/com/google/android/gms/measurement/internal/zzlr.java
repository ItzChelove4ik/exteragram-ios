package com.google.android.gms.measurement.internal;

/* loaded from: classes5.dex */
public enum zzlr extends Enum {
    public static final zzlr zza = null;
    public static final zzlr zzb = null;
    public static final zzlr zzc = null;
    public static final zzlr zzd = null;
    private static final /* synthetic */ zzlr[] zzf = null;
    private final int zze;

    static {
        zzlr r0 = new zzlr("UNKNOWN", 0, 0);
        zza = r0;
        zzlr r1 = new zzlr("SUCCESS", 1, 1);
        zzb = r1;
        zzlr r2 = new zzlr("FAILURE", 2, 2);
        zzc = r2;
        zzlr r3 = new zzlr("BACKOFF", 3, 3);
        zzd = r3;
        zzf = new zzlr[]{r0, r1, r2, r3};
    }

    zzlr(String r1, int r2, int r3) {
        this.zze = r3;
    }

    public static zzlr[] values() {
        return (zzlr[]) zzf.clone();
    }

    public final int zza() {
        return this.zze;
    }
}
