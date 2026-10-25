package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public enum zzami extends Enum implements zzafz {
    public static final zzami zza = null;
    public static final zzami zzb = null;
    public static final zzami zzc = null;
    public static final zzami zzd = null;
    public static final zzami zze = null;
    public static final zzami zzf = null;

    @Deprecated
    public static final zzami zzg = null;
    public static final zzami zzh = null;
    public static final zzami zzi = null;
    public static final zzami zzj = null;
    public static final zzami zzk = null;
    private static final /* synthetic */ zzami[] zzm = null;
    private final int zzl;

    static {
        zzami r0 = new zzami("JS_CODE_UNSPECIFIED", 0, 0);
        zza = r0;
        zzami r1 = new zzami("JS_CODE_SUCCESS", 1, 1);
        zzb = r1;
        zzami r2 = new zzami("JS_NETWORK_ERROR", 2, 2);
        zzc = r2;
        zzami r3 = new zzami("JS_INTERNAL_ERROR", 3, 3);
        zzd = r3;
        zzami r4 = new zzami("JS_INVALID_SITE_KEY", 4, 4);
        zze = r4;
        zzami r5 = new zzami("JS_INVALID_SITE_KEY_TYPE", 5, 5);
        zzf = r5;
        zzami r6 = new zzami("JS_3P_APP_PACKAGE_NAME_NOT_ALLOWED", 6, 6);
        zzg = r6;
        zzami r7 = new zzami("JS_INVALID_ACTION", 7, 7);
        zzh = r7;
        zzami r8 = new zzami("JS_THIRD_PARTY_APP_PACKAGE_NAME_NOT_ALLOWED", 8, 8);
        zzi = r8;
        zzami r9 = new zzami("JS_PROGRAM_ERROR", 9, 9);
        zzj = r9;
        zzami r10 = new zzami("UNRECOGNIZED", 10, -1);
        zzk = r10;
        zzm = new zzami[]{r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10};
    }

    zzami(String r1, int r2, int r3) {
        this.zzl = r3;
    }

    public static zzami[] values() {
        return (zzami[]) zzm.clone();
    }

    @Override // java.lang.Enum
    public final String toString() {
        return Integer.toString(this.zzl);
    }
}
