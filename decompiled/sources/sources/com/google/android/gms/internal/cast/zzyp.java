package com.google.android.gms.internal.cast;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public enum zzyp extends Enum {
    public static final zzyp zza = null;
    public static final zzyp zzb = null;
    public static final zzyp zzc = null;
    public static final zzyp zzd = null;
    public static final zzyp zze = null;
    public static final zzyp zzf = null;
    public static final zzyp zzg = null;
    public static final zzyp zzh = null;
    public static final zzyp zzi = null;
    public static final zzyp zzj = null;
    private static final /* synthetic */ zzyp[] zzl = null;
    private final Class zzk;

    static {
        zzyp r0 = new zzyp("VOID", 0, Void.class, Void.class, null);
        zza = r0;
        Class r4 = Integer.TYPE;
        zzyp r1 = new zzyp("INT", 1, r4, Integer.class, 0);
        zzb = r1;
        zzyp r2 = new zzyp("LONG", 2, Long.TYPE, Long.class, 0L);
        zzc = r2;
        Float r10 = Float.valueOf(0.0f);
        zzyp r3 = new zzyp("FLOAT", 3, Float.TYPE, Float.class, r10);
        zzd = r3;
        Double r102 = Double.valueOf(0.0d);
        zzyp r5 = new zzyp("DOUBLE", 4, Double.TYPE, Double.class, r102);
        zze = r5;
        Boolean r11 = Boolean.FALSE;
        zzyp r6 = new zzyp("BOOLEAN", 5, Boolean.TYPE, Boolean.class, r11);
        zzf = r6;
        zzyp r7 = new zzyp("STRING", 6, String.class, String.class, _UrlKt.FRAGMENT_ENCODE_SET);
        zzg = r7;
        zzyp r72 = new zzyp("BYTE_STRING", 7, zzxk.class, zzxk.class, zzxk.zza);
        zzh = r72;
        zzyp r73 = new zzyp("ENUM", 8, r4, Integer.class, null);
        zzi = r73;
        zzyp r9 = new zzyp("MESSAGE", 9, Object.class, Object.class, null);
        zzj = r9;
        zzl = new zzyp[]{r0, r1, r2, r3, r5, r6, r7, r72, r73, r9};
    }

    zzyp(String r1, int r2, Class r3, Class r4, Object r5) {
        this.zzk = r4;
    }

    public static zzyp[] values() {
        return (zzyp[]) zzl.clone();
    }

    public final Class zza() {
        return this.zzk;
    }
}
