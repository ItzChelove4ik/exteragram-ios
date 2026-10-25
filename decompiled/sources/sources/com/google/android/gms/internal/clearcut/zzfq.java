package com.google.android.gms.internal.clearcut;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public enum zzfq extends Enum<zzfq> {
    public static final zzfq zzqx = null;
    public static final zzfq zzqy = null;
    public static final zzfq zzqz = null;
    public static final zzfq zzra = null;
    public static final zzfq zzrb = null;
    public static final zzfq zzrc = null;
    public static final zzfq zzrd = null;
    public static final zzfq zzre = null;
    public static final zzfq zzrf = null;
    private static final /* synthetic */ zzfq[] zzrg = null;
    private final Object zzlj;

    static {
        zzfq r0 = new zzfq("INT", 0, 0);
        zzqx = r0;
        zzfq r1 = new zzfq("LONG", 1, 0L);
        zzqy = r1;
        zzfq r2 = new zzfq("FLOAT", 2, Float.valueOf(0.0f));
        zzqz = r2;
        zzfq r3 = new zzfq("DOUBLE", 3, Double.valueOf(0.0d));
        zzra = r3;
        zzfq r4 = new zzfq("BOOLEAN", 4, Boolean.FALSE);
        zzrb = r4;
        zzfq r5 = new zzfq("STRING", 5, _UrlKt.FRAGMENT_ENCODE_SET);
        zzrc = r5;
        zzfq r6 = new zzfq("BYTE_STRING", 6, zzbb.zzfi);
        zzrd = r6;
        zzfq r7 = new zzfq("ENUM", 7, null);
        zzre = r7;
        zzfq r8 = new zzfq("MESSAGE", 8, null);
        zzrf = r8;
        zzrg = new zzfq[]{r0, r1, r2, r3, r4, r5, r6, r7, r8};
    }

    zzfq(String r1, int r2, Object r3) {
        this.zzlj = r3;
    }

    public static zzfq[] values() {
        return (zzfq[]) zzrg.clone();
    }
}
