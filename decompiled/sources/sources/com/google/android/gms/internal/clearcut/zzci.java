package com.google.android.gms.internal.clearcut;

import c.f$$ExternalSyntheticBUOutline2;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* loaded from: classes4.dex */
public abstract class zzci {
    private static final Charset ISO_8859_1 = null;
    static final Charset UTF_8 = null;
    public static final byte[] zzkt = null;
    private static final ByteBuffer zzku = null;
    private static final zzbk zzkv = null;

    static {
        UTF_8 = Charset.forName("UTF-8");
        ISO_8859_1 = Charset.forName("ISO-8859-1");
        byte[] r1 = new byte[0];
        zzkt = r1;
        zzku = ByteBuffer.wrap(r1);
        zzkv = zzbk.zza(r1, 0, r1.length, false);
    }

    public static <T> T checkNotNull(T r0) {
        r0.getClass();
        return r0;
    }

    public static int hashCode(byte[] r2) {
        int r0 = r2.length;
        int r22 = zza(r0, r2, 0, r0);
        if (r22 != 0) goto L6;
        return 1;
    L6:
        return r22;
    }

    public static int zza(int r2, byte[] r3, int r4, int r5) {
        int r0 = r4;
    L4:
        if (r0 >= (r4 + r5)) goto L6;
        r2 = (r2 * 31) + r3[r0];
        r0 = r0 + 1;
        goto L4
    L6:
        return r2;
    }

    public static Object zza(Object r0, Object r1) {
        return ((zzdo) r0).zzbc().zza((zzdo) r1).zzbi();
    }

    public static <T> T zza(T r0, String r1) {
        if (r0 == null) goto L4;
        return r0;
    L4:
        f$$ExternalSyntheticBUOutline2.m(r1);
        return null;
    }

    public static int zzc(boolean r0) {
        if (r0 == false) goto L5;
        return 1231;
    L5:
        return 1237;
    }

    public static boolean zze(byte[] r0) {
        return zzff.zze(r0);
    }

    public static String zzf(byte[] r2) {
        return new String(r2, UTF_8);
    }

    public static int zzl(long r2) {
        return (int) (r2 ^ (r2 >>> 32));
    }
}
