package com.google.android.gms.measurement.internal;

/* loaded from: classes5.dex */
public enum zzls extends Enum {
    public static final zzls zza = null;
    public static final zzls zzb = null;
    public static final zzls zzc = null;
    public static final zzls zzd = null;
    public static final zzls zze = null;
    public static final zzls zzf = null;
    private static final /* synthetic */ zzls[] zzh = null;
    private final int zzg;

    static {
        zzls r0 = new zzls("GOOGLE_ANALYTICS", 0, 0);
        zza = r0;
        zzls r1 = new zzls("GOOGLE_SIGNAL", 1, 1);
        zzb = r1;
        zzls r2 = new zzls("SGTM", 2, 2);
        zzc = r2;
        zzls r3 = new zzls("SGTM_CLIENT", 3, 3);
        zzd = r3;
        zzls r4 = new zzls("GOOGLE_SIGNAL_PENDING", 4, 4);
        zze = r4;
        zzls r5 = new zzls("UNKNOWN", 5, 99);
        zzf = r5;
        zzh = new zzls[]{r0, r1, r2, r3, r4, r5};
    }

    zzls(String r1, int r2, int r3) {
        this.zzg = r3;
    }

    public static zzls[] values() {
        return (zzls[]) zzh.clone();
    }

    public static zzls zzb(int r5) {
        zzls[] r0 = values();
        int r1 = r0.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L9;
        zzls r3 = r0[r2];
        if (r3.zzg == r5) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return r3;
    L9:
        return zzf;
    }

    public final int zza() {
        return this.zzg;
    }
}
