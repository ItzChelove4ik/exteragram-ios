package com.google.android.gms.measurement.internal;

/* loaded from: classes5.dex */
enum zzam extends Enum {
    public static final zzam zza = null;
    public static final zzam zzb = null;
    public static final zzam zzc = null;
    public static final zzam zzd = null;
    public static final zzam zze = null;
    public static final zzam zzf = null;
    public static final zzam zzg = null;
    public static final zzam zzh = null;
    public static final zzam zzi = null;
    public static final zzam zzj = null;
    private static final /* synthetic */ zzam[] zzl = null;
    private final char zzk;

    static {
        zzam r0 = new zzam("UNSET", 0, '0');
        zza = r0;
        zzam r1 = new zzam("REMOTE_DEFAULT", 1, '1');
        zzb = r1;
        zzam r2 = new zzam("REMOTE_DELEGATION", 2, '2');
        zzc = r2;
        zzam r3 = new zzam("MANIFEST", 3, '3');
        zzd = r3;
        zzam r4 = new zzam("INITIALIZATION", 4, '4');
        zze = r4;
        zzam r5 = new zzam("API", 5, '5');
        zzf = r5;
        zzam r6 = new zzam("CHILD_ACCOUNT", 6, '6');
        zzg = r6;
        zzam r7 = new zzam("TCF", 7, '7');
        zzh = r7;
        zzam r8 = new zzam("REMOTE_ENFORCED_DEFAULT", 8, '8');
        zzi = r8;
        zzam r9 = new zzam("FAILSAFE", 9, '9');
        zzj = r9;
        zzl = new zzam[]{r0, r1, r2, r3, r4, r5, r6, r7, r8, r9};
    }

    zzam(String r1, int r2, char r3) {
        this.zzk = r3;
    }

    public static zzam[] values() {
        return (zzam[]) zzl.clone();
    }

    public static zzam zza(char r5) {
        zzam[] r0 = values();
        int r1 = r0.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L9;
        zzam r3 = r0[r2];
        if (r3.zzk == r5) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return r3;
    L9:
        return zza;
    }

    public final /* synthetic */ char zzb() {
        return this.zzk;
    }
}
