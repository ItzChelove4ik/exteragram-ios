package com.google.android.recaptcha.internal;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public enum zzagm extends Enum {
    public static final zzagm zza = null;
    public static final zzagm zzb = null;
    public static final zzagm zzc = null;
    public static final zzagm zzd = null;
    public static final zzagm zze = null;
    public static final zzagm zzf = null;
    public static final zzagm zzg = null;
    public static final zzagm zzh = null;
    public static final zzagm zzi = null;
    public static final zzagm zzj = null;
    private static final /* synthetic */ zzagm[] zzl = null;
    private final Class zzk;

    static {
        zzagm r0 = new zzagm("VOID", 0, Void.class, Void.class, null);
        zza = r0;
        Class r4 = Integer.TYPE;
        zzagm r1 = new zzagm("INT", 1, r4, Integer.class, 0);
        zzb = r1;
        zzagm r2 = new zzagm("LONG", 2, Long.TYPE, Long.class, 0L);
        zzc = r2;
        Float r10 = Float.valueOf(0.0f);
        zzagm r3 = new zzagm("FLOAT", 3, Float.TYPE, Float.class, r10);
        zzd = r3;
        Double r102 = Double.valueOf(0.0d);
        zzagm r5 = new zzagm("DOUBLE", 4, Double.TYPE, Double.class, r102);
        zze = r5;
        Boolean r11 = Boolean.FALSE;
        zzagm r6 = new zzagm("BOOLEAN", 5, Boolean.TYPE, Boolean.class, r11);
        zzf = r6;
        zzagm r7 = new zzagm("STRING", 6, String.class, String.class, _UrlKt.FRAGMENT_ENCODE_SET);
        zzg = r7;
        zzagm r72 = new zzagm("BYTE_STRING", 7, zzaer.class, zzaer.class, zzaer.zza);
        zzh = r72;
        zzagm r73 = new zzagm("ENUM", 8, r4, Integer.class, null);
        zzi = r73;
        zzagm r9 = new zzagm("MESSAGE", 9, Object.class, Object.class, null);
        zzj = r9;
        zzl = new zzagm[]{r0, r1, r2, r3, r5, r6, r7, r72, r73, r9};
    }

    zzagm(String r1, int r2, Class r3, Class r4, Object r5) {
        this.zzk = r4;
    }

    public static zzagm[] values() {
        return (zzagm[]) zzl.clone();
    }

    public final Class zza() {
        return this.zzk;
    }
}
