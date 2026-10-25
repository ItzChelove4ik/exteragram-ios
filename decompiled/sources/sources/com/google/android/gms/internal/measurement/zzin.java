package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public enum zzin extends Enum implements zzady {
    public static final zzin zza = null;
    public static final zzin zzb = null;
    public static final zzin zzc = null;
    public static final zzin zzd = null;
    public static final zzin zze = null;
    public static final zzin zzf = null;
    public static final zzin zzg = null;
    public static final zzin zzh = null;
    public static final zzin zzi = null;
    public static final zzin zzj = null;
    public static final zzin zzk = null;
    public static final zzin zzl = null;
    private static final /* synthetic */ zzin[] zzn = null;
    private final int zzm;

    static {
        zzin r0 = new zzin("CLIENT_UPLOAD_ELIGIBILITY_UNKNOWN", 0, 0);
        zza = r0;
        zzin r1 = new zzin("CLIENT_UPLOAD_ELIGIBLE", 1, 1);
        zzb = r1;
        zzin r2 = new zzin("MEASUREMENT_SERVICE_NOT_ENABLED", 2, 2);
        zzc = r2;
        zzin r3 = new zzin("ANDROID_TOO_OLD", 3, 3);
        zzd = r3;
        zzin r4 = new zzin("NON_PLAY_MODE", 4, 4);
        zze = r4;
        zzin r5 = new zzin("SDK_TOO_OLD", 5, 5);
        zzf = r5;
        zzin r6 = new zzin("MISSING_JOB_SCHEDULER", 6, 6);
        zzg = r6;
        zzin r7 = new zzin("NOT_ENABLED_IN_MANIFEST", 7, 7);
        zzh = r7;
        zzin r8 = new zzin("CLIENT_FLAG_OFF", 8, 8);
        zzi = r8;
        zzin r9 = new zzin("SERVICE_FLAG_OFF", 9, 20);
        zzj = r9;
        zzin r10 = new zzin("PINNED_TO_SERVICE_UPLOAD", 10, 21);
        zzk = r10;
        zzin r11 = new zzin("MISSING_SGTM_SERVER_URL", 11, 22);
        zzl = r11;
        zzn = new zzin[]{r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11};
    }

    zzin(String r1, int r2, int r3) {
        this.zzm = r3;
    }

    public static zzin[] values() {
        return (zzin[]) zzn.clone();
    }

    public static zzin zzb(int r0) {
        switch(r0) {
            case 0: goto L29;
            case 1: goto L27;
            case 2: goto L25;
            case 3: goto L23;
            case 4: goto L21;
            case 5: goto L19;
            case 6: goto L17;
            case 7: goto L15;
            case 8: goto L13;
            default: goto L3;
        };
    L3:
        switch(r0) {
            case 20: goto L11;
            case 21: goto L9;
            case 22: goto L7;
            default: goto L4;
        };
    L4:
        return null;
    L7:
        return zzl;
    L9:
        return zzk;
    L11:
        return zzj;
    L13:
        return zzi;
    L15:
        return zzh;
    L17:
        return zzg;
    L19:
        return zzf;
    L21:
        return zze;
    L23:
        return zzd;
    L25:
        return zzc;
    L27:
        return zzb;
    L29:
        return zza;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return Integer.toString(this.zzm);
    }

    @Override // com.google.android.gms.internal.measurement.zzady
    public final int zza() {
        return this.zzm;
    }
}
