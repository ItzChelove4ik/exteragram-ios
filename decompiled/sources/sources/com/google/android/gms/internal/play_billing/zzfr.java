package com.google.android.gms.internal.play_billing;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public enum zzfr extends Enum {
    public static final zzfr zza = null;
    public static final zzfr zzb = null;
    public static final zzfr zzc = null;
    public static final zzfr zzd = null;
    public static final zzfr zze = null;
    public static final zzfr zzf = null;
    public static final zzfr zzg = null;
    public static final zzfr zzh = null;
    public static final zzfr zzi = null;
    public static final zzfr zzj = null;
    private static final /* synthetic */ zzfr[] zzk = null;
    private final Class zzl;

    static {
        zzfr r0 = new zzfr("VOID", 0, Void.class, Void.class, null);
        zza = r0;
        Class r4 = Integer.TYPE;
        zzfr r1 = new zzfr("INT", 1, r4, Integer.class, 0);
        zzb = r1;
        zzfr r2 = new zzfr("LONG", 2, Long.TYPE, Long.class, 0L);
        zzc = r2;
        Float r10 = Float.valueOf(0.0f);
        zzfr r3 = new zzfr("FLOAT", 3, Float.TYPE, Float.class, r10);
        zzd = r3;
        Double r102 = Double.valueOf(0.0d);
        zzfr r5 = new zzfr("DOUBLE", 4, Double.TYPE, Double.class, r102);
        zze = r5;
        Boolean r11 = Boolean.FALSE;
        zzfr r6 = new zzfr("BOOLEAN", 5, Boolean.TYPE, Boolean.class, r11);
        zzf = r6;
        zzfr r7 = new zzfr("STRING", 6, String.class, String.class, _UrlKt.FRAGMENT_ENCODE_SET);
        zzg = r7;
        zzfr r72 = new zzfr("BYTE_STRING", 7, zzei.class, zzei.class, zzei.zzb);
        zzh = r72;
        zzfr r73 = new zzfr("ENUM", 8, r4, Integer.class, null);
        zzi = r73;
        zzfr r9 = new zzfr("MESSAGE", 9, Object.class, Object.class, null);
        zzj = r9;
        zzk = new zzfr[]{r0, r1, r2, r3, r5, r6, r7, r72, r73, r9};
    }

    zzfr(String r1, int r2, Class r3, Class r4, Object r5) {
        this.zzl = r4;
    }

    public static zzfr[] values() {
        return (zzfr[]) zzk.clone();
    }

    public final Class zza() {
        return this.zzl;
    }
}
