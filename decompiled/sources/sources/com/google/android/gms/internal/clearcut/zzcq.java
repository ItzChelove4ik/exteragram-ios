package com.google.android.gms.internal.clearcut;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public enum zzcq extends Enum<zzcq> {
    public static final zzcq zzkx = null;
    public static final zzcq zzky = null;
    public static final zzcq zzkz = null;
    public static final zzcq zzla = null;
    public static final zzcq zzlb = null;
    public static final zzcq zzlc = null;
    public static final zzcq zzld = null;
    public static final zzcq zzle = null;
    public static final zzcq zzlf = null;
    public static final zzcq zzlg = null;
    private static final /* synthetic */ zzcq[] zzlk = null;
    private final Class<?> zzlh;
    private final Class<?> zzli;
    private final Object zzlj;

    static {
        zzcq r0 = new zzcq("VOID", 0, Void.class, Void.class, null);
        zzkx = r0;
        Class r4 = Integer.TYPE;
        zzcq r1 = new zzcq("INT", 1, r4, Integer.class, 0);
        zzky = r1;
        zzcq r2 = new zzcq("LONG", 2, Long.TYPE, Long.class, 0L);
        zzkz = r2;
        Float r10 = Float.valueOf(0.0f);
        zzcq r3 = new zzcq("FLOAT", 3, Float.TYPE, Float.class, r10);
        zzla = r3;
        Double r102 = Double.valueOf(0.0d);
        zzcq r5 = new zzcq("DOUBLE", 4, Double.TYPE, Double.class, r102);
        zzlb = r5;
        Boolean r11 = Boolean.FALSE;
        zzcq r6 = new zzcq("BOOLEAN", 5, Boolean.TYPE, Boolean.class, r11);
        zzlc = r6;
        zzcq r7 = new zzcq("STRING", 6, String.class, String.class, _UrlKt.FRAGMENT_ENCODE_SET);
        zzld = r7;
        zzcq r72 = new zzcq("BYTE_STRING", 7, zzbb.class, zzbb.class, zzbb.zzfi);
        zzle = r72;
        zzcq r73 = new zzcq("ENUM", 8, r4, Integer.class, null);
        zzlf = r73;
        zzcq r9 = new zzcq("MESSAGE", 9, Object.class, Object.class, null);
        zzlg = r9;
        zzlk = new zzcq[]{r0, r1, r2, r3, r5, r6, r7, r72, r73, r9};
    }

    zzcq(String r1, int r2, Class r3, Class r4, Object r5) {
        this.zzlh = r3;
        this.zzli = r4;
        this.zzlj = r5;
    }

    public static zzcq[] values() {
        return (zzcq[]) zzlk.clone();
    }

    public final Class<?> zzbq() {
        return this.zzli;
    }
}
