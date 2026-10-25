package com.google.android.recaptcha.internal;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Comparator;

/* loaded from: classes5.dex */
public final class zzv {
    public static final zzv zzb = null;
    public static final Comparator zzc = null;
    public final byte[] zza;

    static {
        zzb = new zzv(new byte[0]);
        zzc = new zzu();
    }

    public zzv(byte[] r1) {
        this.zza = r1;
    }

    public static zzv zze(byte[] r3) {
        return new zzv(zzh(r3, 0, r3.length));
    }

    public static zzv zzf(String r1) {
        return zze(r1.getBytes(Charset.forName(zzt.zza("Hn2H4l0="))));
    }

    public static int zzg(byte r9) {
        int[] r0 = {358984857, 11257410, 369424407, 615188036, 873771167, 1734030349, 488663950, 1985433483, 395279207};
        int r1 = r0[0];
        int r2 = r0[1];
        int r3 = r0[2];
        int r4 = r0[3];
        int r5 = r0[4];
        int r6 = r0[5];
        int r7 = r0[6];
        int r02 = r0[7];
        int r03 = r02 % 395279207;
        return r9 & (r03 ^ (r7 + ((((r2 & (~r1)) | r3) + ((r1 & r4) | r5)) - r6)));
    }

    public static byte[] zzh(byte[] r2, int r3, int r4) {
        if (r4 == 0) goto L5;
        byte[] r1 = new byte[r4];
        System.arraycopy(r2, r3, r1, 0, r4);
        return r1;
    L5:
        return new byte[0];
    }

    public final boolean equals(Object r2) {
        if ((r2 instanceof zzv) == true) goto L5;
        return false;
    L5:
        return Arrays.equals(this.zza, ((zzv) r2).zza);
    }

    public final int hashCode() {
        return Integer.hashCode(Arrays.hashCode(this.zza));
    }

    public final String toString() {
        int[] r0 = {95266356, 568641516, 183483904, 553669100, 504469011, 966284502, 91904737, 2026478004, 1659239833};
        int r1 = r0[0];
        int r2 = r0[1];
        int r3 = r0[2];
        int r4 = r0[3];
        int r5 = r0[4];
        int r6 = r0[5];
        int r7 = r0[6];
        int r02 = r0[7];
        int r72 = r7 + ((((r2 & (~r1)) | r3) + ((r1 & r4) | r5)) - r6);
        String r9 = Arrays.toString(this.zza);
        StringBuilder r03 = new StringBuilder(String.valueOf(r9).length() + ((r02 % 1659239833) ^ r72));
        r03.append(zzt.zza("CVC1qiQNJHikW0iU1TIPZA=="));
        r03.append(r9);
        r03.append(zzt.zza("Ng=="));
        return r03.toString();
    }

    public final byte[] zza() {
        byte[] r2 = this.zza;
        int r0 = r2.length;
        if (r0 != 0) goto L7;
        return new byte[0];
    L7:
        return zzh(r2, 0, r0);
    }

    public final byte zzb(int r7) {
        int r1 = ((((~1635905385) & 436500160) | 1627617040) + ((1635905385 & 1527677400) | 1092341018)) - (-1251599269);
        int r2 = 1253207672 % 570073850;
        int r3 = ((((~1280321648) & 1509448256) | 1074834751) + ((1280321648 & 434689637) | 67544103)) - 1396684710;
        int r4 = 1309383303 % 1129033333;
        byte[] r6 = this.zza;
        int r0 = r6.length;
        if (((r0 - (r7 + 1)) | r7) >= 0) goto L10;
        if (r7 >= 0) goto L7;
        int r62 = r1 ^ r2;
        StringBuilder r63 = new StringBuilder(String.valueOf(r7).length() + r62);
        r63.append(zzt.zza("Akelqh1fajntGgo="));
        r63.append(r7);
        throw new ArrayIndexOutOfBoundsException(r63.toString());
    L7:
        int r22 = String.valueOf(r7).length();
        StringBuilder r64 = new StringBuilder((r22 + (r3 ^ r4)) + String.valueOf(r0).length());
        String r23 = zzt.zza("Akelqh1faDmxRUSK1T9GeQ==");
        String r32 = zzt.zza("Zwk=");
        r64.append(r23);
        r64.append(r7);
        r64.append(r32);
        r64.append(r0);
        throw new ArrayIndexOutOfBoundsException(r64.toString());
    L10:
        return r6[r7];
    }

    public final String zzc() {
        Charset r0 = Charset.forName(zzt.zza("Hn2H4l0="));
        byte[] r4 = this.zza;
        return new String(r4, 0, r4.length, r0);
    }

    public final zzv zzd(zzv r5) {
        byte[] r52 = r5.zza;
        int r0 = r52.length;
        byte[] r4 = this.zza;
        int r1 = r4.length;
        byte[] r2 = new byte[r1 + r0];
        System.arraycopy(r4, 0, r2, 0, r1);
        System.arraycopy(r52, 0, r2, r1, r0);
        return zze(r2);
    }
}
