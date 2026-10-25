package com.google.android.gms.internal.vision;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public enum zzjm extends Enum<zzjm> {
    public static final zzjm zza = null;
    public static final zzjm zzb = null;
    public static final zzjm zzc = null;
    public static final zzjm zzd = null;
    public static final zzjm zze = null;
    public static final zzjm zzf = null;
    public static final zzjm zzg = null;
    public static final zzjm zzh = null;
    public static final zzjm zzi = null;
    public static final zzjm zzj = null;
    private static final /* synthetic */ zzjm[] zzn = null;
    private final Class<?> zzk;
    private final Class<?> zzl;
    private final Object zzm;

    static {
        zzjm r0 = new zzjm("VOID", 0, Void.class, Void.class, null);
        zza = r0;
        Class r4 = Integer.TYPE;
        zzjm r1 = new zzjm("INT", 1, r4, Integer.class, 0);
        zzb = r1;
        zzjm r2 = new zzjm("LONG", 2, Long.TYPE, Long.class, 0L);
        zzc = r2;
        Float r10 = Float.valueOf(0.0f);
        zzjm r3 = new zzjm("FLOAT", 3, Float.TYPE, Float.class, r10);
        zzd = r3;
        Double r102 = Double.valueOf(0.0d);
        zzjm r5 = new zzjm("DOUBLE", 4, Double.TYPE, Double.class, r102);
        zze = r5;
        Boolean r11 = Boolean.FALSE;
        zzjm r6 = new zzjm("BOOLEAN", 5, Boolean.TYPE, Boolean.class, r11);
        zzf = r6;
        zzjm r7 = new zzjm("STRING", 6, String.class, String.class, _UrlKt.FRAGMENT_ENCODE_SET);
        zzg = r7;
        zzjm r72 = new zzjm("BYTE_STRING", 7, zzht.class, zzht.class, zzht.zza);
        zzh = r72;
        zzjm r73 = new zzjm("ENUM", 8, r4, Integer.class, null);
        zzi = r73;
        zzjm r9 = new zzjm("MESSAGE", 9, Object.class, Object.class, null);
        zzj = r9;
        zzn = new zzjm[]{r0, r1, r2, r3, r5, r6, r7, r72, r73, r9};
    }

    zzjm(String r1, int r2, Class r3, Class r4, Object r5) {
        this.zzk = r3;
        this.zzl = r4;
        this.zzm = r5;
    }

    public static zzjm[] values() {
        return (zzjm[]) zzn.clone();
    }

    public final Class<?> zza() {
        return this.zzl;
    }
}
