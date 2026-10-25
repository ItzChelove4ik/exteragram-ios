package com.google.android.gms.internal.measurement;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public enum zzaei extends Enum {
    public static final zzaei zza = null;
    public static final zzaei zzb = null;
    public static final zzaei zzc = null;
    public static final zzaei zzd = null;
    public static final zzaei zze = null;
    public static final zzaei zzf = null;
    public static final zzaei zzg = null;
    public static final zzaei zzh = null;
    public static final zzaei zzi = null;
    public static final zzaei zzj = null;
    private static final /* synthetic */ zzaei[] zzl = null;
    private final Class zzk;

    static {
        zzaei r0 = new zzaei("VOID", 0, Void.class, Void.class, null);
        zza = r0;
        Class r4 = Integer.TYPE;
        zzaei r1 = new zzaei("INT", 1, r4, Integer.class, 0);
        zzb = r1;
        zzaei r2 = new zzaei("LONG", 2, Long.TYPE, Long.class, 0L);
        zzc = r2;
        Float r10 = Float.valueOf(0.0f);
        zzaei r3 = new zzaei("FLOAT", 3, Float.TYPE, Float.class, r10);
        zzd = r3;
        Double r102 = Double.valueOf(0.0d);
        zzaei r5 = new zzaei("DOUBLE", 4, Double.TYPE, Double.class, r102);
        zze = r5;
        Boolean r11 = Boolean.FALSE;
        zzaei r6 = new zzaei("BOOLEAN", 5, Boolean.TYPE, Boolean.class, r11);
        zzf = r6;
        zzaei r7 = new zzaei("STRING", 6, String.class, String.class, _UrlKt.FRAGMENT_ENCODE_SET);
        zzg = r7;
        zzaei r72 = new zzaei("BYTE_STRING", 7, zzacr.class, zzacr.class, zzacr.zza);
        zzh = r72;
        zzaei r73 = new zzaei("ENUM", 8, r4, Integer.class, null);
        zzi = r73;
        zzaei r9 = new zzaei("MESSAGE", 9, Object.class, Object.class, null);
        zzj = r9;
        zzl = new zzaei[]{r0, r1, r2, r3, r5, r6, r7, r72, r73, r9};
    }

    zzaei(String r1, int r2, Class r3, Class r4, Object r5) {
        this.zzk = r4;
    }

    public static zzaei[] values() {
        return (zzaei[]) zzl.clone();
    }

    public final Class zza() {
        return this.zzk;
    }
}
