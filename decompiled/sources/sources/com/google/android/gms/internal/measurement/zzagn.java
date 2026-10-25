package com.google.android.gms.internal.measurement;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public enum zzagn extends Enum {
    public static final zzagn zza = null;
    public static final zzagn zzb = null;
    public static final zzagn zzc = null;
    public static final zzagn zzd = null;
    public static final zzagn zze = null;
    public static final zzagn zzf = null;
    public static final zzagn zzg = null;
    public static final zzagn zzh = null;
    public static final zzagn zzi = null;
    private static final /* synthetic */ zzagn[] zzj = null;

    static {
        zzagn r0 = new zzagn("INT", 0, 0);
        zza = r0;
        zzagn r1 = new zzagn("LONG", 1, 0L);
        zzb = r1;
        zzagn r2 = new zzagn("FLOAT", 2, Float.valueOf(0.0f));
        zzc = r2;
        zzagn r3 = new zzagn("DOUBLE", 3, Double.valueOf(0.0d));
        zzd = r3;
        zzagn r4 = new zzagn("BOOLEAN", 4, Boolean.FALSE);
        zze = r4;
        zzagn r5 = new zzagn("STRING", 5, _UrlKt.FRAGMENT_ENCODE_SET);
        zzf = r5;
        zzagn r6 = new zzagn("BYTE_STRING", 6, zzacr.zza);
        zzg = r6;
        zzagn r7 = new zzagn("ENUM", 7, null);
        zzh = r7;
        zzagn r8 = new zzagn("MESSAGE", 8, null);
        zzi = r8;
        zzj = new zzagn[]{r0, r1, r2, r3, r4, r5, r6, r7, r8};
    }

    zzagn(String r1, int r2, Object r3) {
    }

    public static zzagn[] values() {
        return (zzagn[]) zzj.clone();
    }
}
