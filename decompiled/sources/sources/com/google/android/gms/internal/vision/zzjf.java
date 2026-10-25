package com.google.android.gms.internal.vision;

import c.f$$ExternalSyntheticBUOutline2;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* loaded from: classes5.dex */
public abstract class zzjf {
    static final Charset zza = null;
    public static final byte[] zzb = null;
    private static final Charset zzc = null;
    private static final ByteBuffer zzd = null;
    private static final zzif zze = null;

    static {
        zza = Charset.forName("UTF-8");
        zzc = Charset.forName("ISO-8859-1");
        byte[] r1 = new byte[0];
        zzb = r1;
        zzd = ByteBuffer.wrap(r1);
        zze = zzif.zza(r1, 0, r1.length, false);
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

    public static int zza(long r2) {
        return (int) (r2 ^ (r2 >>> 32));
    }

    public static int zza(boolean r0) {
        if (r0 == false) goto L5;
        return 1231;
    L5:
        return 1237;
    }

    public static <T> T zza(T r0) {
        r0.getClass();
        return r0;
    }

    public static Object zza(Object r0, Object r1) {
        return ((zzkk) r0).zzp().zza((zzkk) r1).zze();
    }

    public static <T> T zza(T r0, String r1) {
        if (r0 == null) goto L4;
        return r0;
    L4:
        f$$ExternalSyntheticBUOutline2.m(r1);
        return null;
    }

    public static boolean zza(zzkk r0) {
        return false;
    }

    public static boolean zza(byte[] r0) {
        return zzmd.zza(r0);
    }

    public static String zzb(byte[] r2) {
        return new String(r2, zza);
    }

    public static int zzc(byte[] r2) {
        int r0 = r2.length;
        int r22 = zza(r0, r2, 0, r0);
        if (r22 != 0) goto L6;
        return 1;
    L6:
        return r22;
    }
}
