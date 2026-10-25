package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public enum zzwp extends Enum implements zzafz {
    public static final zzwp zza = null;
    public static final zzwp zzb = null;
    public static final zzwp zzc = null;
    public static final zzwp zzd = null;
    public static final zzwp zze = null;
    public static final zzwp zzf = null;
    public static final zzwp zzg = null;
    private static final /* synthetic */ zzwp[] zzi = null;
    private final int zzh;

    static {
        zzwp r0 = new zzwp("UNKNOWN_HASH", 0, 0);
        zza = r0;
        zzwp r1 = new zzwp("SHA1", 1, 1);
        zzb = r1;
        zzwp r2 = new zzwp("SHA384", 2, 2);
        zzc = r2;
        zzwp r3 = new zzwp("SHA256", 3, 3);
        zzd = r3;
        zzwp r4 = new zzwp("SHA512", 4, 4);
        zze = r4;
        zzwp r5 = new zzwp("SHA224", 5, 5);
        zzf = r5;
        zzwp r6 = new zzwp("UNRECOGNIZED", 6, -1);
        zzg = r6;
        zzi = new zzwp[]{r0, r1, r2, r3, r4, r5, r6};
    }

    zzwp(String r1, int r2, int r3) {
        this.zzh = r3;
    }

    public static zzwp[] values() {
        return (zzwp[]) zzi.clone();
    }

    public static zzwp zzb(int r1) {
        if (r1 == 0) goto L26;
        if (r1 == 1) goto L24;
        if (r1 == 2) goto L22;
        if (r1 == 3) goto L20;
        if (r1 == 4) goto L18;
        if (r1 == 5) goto L16;
        return null;
    L16:
        return zzf;
    L18:
        return zze;
    L20:
        return zzd;
    L22:
        return zzc;
    L24:
        return zzb;
    L26:
        return zza;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return Integer.toString(this.zzh);
    }

    public final int zza() {
        if (this != zzg) goto L7;
        return zzagf.zza();
    L7:
        return this.zzh;
    }
}
