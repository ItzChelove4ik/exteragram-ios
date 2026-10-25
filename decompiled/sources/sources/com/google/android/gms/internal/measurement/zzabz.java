package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public enum zzabz extends Enum implements zzady {
    public static final zzabz zza = null;
    public static final zzabz zzb = null;
    public static final zzabz zzc = null;
    public static final zzabz zzd = null;
    public static final zzabz zze = null;
    public static final zzabz zzf = null;

    @Deprecated
    public static final zzabz zzg = null;
    private static final /* synthetic */ zzabz[] zzi = null;
    private final int zzh;

    static {
        zzabz r0 = new zzabz("UNKNOWN", 0, 0);
        zza = r0;
        zzabz r1 = new zzabz("SHARED_PREFS", 1, 1);
        zzb = r1;
        zzabz r2 = new zzabz("CONTENT_PROVIDER", 2, 2);
        zzc = r2;
        zzabz r3 = new zzabz("FILE", 3, 6);
        zzd = r3;
        zzabz r4 = new zzabz("TIKTOK", 4, 4);
        zze = r4;
        zzabz r5 = new zzabz("DEVICE_CONFIG", 5, 5);
        zzf = r5;
        zzabz r6 = new zzabz("PROCESS_STABLE_CONTENT_PROVIDER", 6, 3);
        zzg = r6;
        zzi = new zzabz[]{r0, r1, r2, r3, r4, r5, r6};
    }

    zzabz(String r1, int r2, int r3) {
        this.zzh = r3;
    }

    public static zzabz[] values() {
        return (zzabz[]) zzi.clone();
    }

    public static zzabz zzb(int r0) {
        switch(r0) {
            case 0: goto L18;
            case 1: goto L16;
            case 2: goto L14;
            case 3: goto L12;
            case 4: goto L10;
            case 5: goto L8;
            case 6: goto L6;
            default: goto L3;
        };
    L3:
        return null;
    L6:
        return zzd;
    L8:
        return zzf;
    L10:
        return zze;
    L12:
        return zzg;
    L14:
        return zzc;
    L16:
        return zzb;
    L18:
        return zza;
    }

    public static zzadz zzc() {
        return zzaby.zza;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return Integer.toString(this.zzh);
    }

    @Override // com.google.android.gms.internal.measurement.zzady
    public final int zza() {
        return this.zzh;
    }
}
