package com.google.android.gms.internal.vision;

import com.google.android.gms.cast.internal.zzar$$ExternalSyntheticBUOutline0;
import okio.AsyncTimeout$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
abstract class zzmd {
    private static final zzme zza = null;

    static {
        if (zzma.zza() == true) goto L5;
    L9:
        zzme r0 = new zzmh();
    L10:
        zza = r0;
        return;
    L5:
        if (zzma.zzb() == false) goto L9;
        if (zzhi.zza() == true) goto L9;
        r0 = new zzmj();
        goto L10
    }

    public static /* synthetic */ int zza(int r0) {
        return zzb(r0);
    }

    public static /* synthetic */ int zza(int r0, int r1) {
        return zzb(r0, r1);
    }

    public static /* synthetic */ int zza(int r0, int r1, int r2) {
        return zzb(r0, r1, r2);
    }

    public static int zza(CharSequence r9) {
        int r0 = r9.length();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L7;
        if (r9.charAt(r2) >= 128) goto L7;
        r2 = r2 + 1;
    L7:
        int r3 = r0;
    L8:
        if (r2 >= r0) goto L28;
        char r4 = r9.charAt(r2);
        if (r4 >= 2048) goto L12;
        r3 = r3 + ((127 - r4) >>> 31);
        r2 = r2 + 1;
        goto L8
    L12:
        int r42 = r9.length();
        int r6 = 0;
    L13:
        if (r2 >= r42) goto L27;
        char r7 = r9.charAt(r2);
        if (r7 >= 2048) goto L17;
        r6 = r6 + ((127 - r7) >>> 31);
    L26:
        r2 = r2 + 1;
        goto L13
    L17:
        r6 = r6 + 2;
        if (55296 > r7) goto L26;
        if (r7 > 57343) goto L26;
        if (Character.codePointAt(r9, r2) < 65536) goto L25;
        r2 = r2 + 1;
        goto L26
    L25:
        throw new zzmg(r2, r42);
    L27:
        r3 = r3 + r6;
    L28:
        if (r3 < r0) goto L30;
        return r3;
    L30:
        zzar$$ExternalSyntheticBUOutline0.m(54, "UTF-8 length does not fit in int: ", r3 + 4294967296L);
        return 0;
    }

    public static int zza(CharSequence r1, byte[] r2, int r3, int r4) {
        return zza.zza(r1, r2, r3, r4);
    }

    public static boolean zza(byte[] r3) {
        return zza.zza(r3, 0, r3.length);
    }

    public static boolean zza(byte[] r1, int r2, int r3) {
        return zza.zza(r1, r2, r3);
    }

    private static int zzb(int r1) {
        if (r1 <= (-12)) goto L6;
        return -1;
    L6:
        return r1;
    }

    private static int zzb(int r1, int r2) {
        if (r1 <= (-12)) goto L5;
        return -1;
    L5:
        if (r2 <= (-65)) goto L8;
        return -1;
    L8:
        return r1 ^ (r2 << 8);
    }

    private static int zzb(int r1, int r2, int r3) {
        if (r1 <= (-12)) goto L5;
        return -1;
    L5:
        if (r2 > (-65)) goto L12;
        if (r3 <= (-65)) goto L9;
        return -1;
    L9:
        return (r1 ^ (r2 << 8)) ^ (r3 << 16);
    L12:
        return -1;
    }

    public static String zzb(byte[] r1, int r2, int r3) {
        return zza.zzb(r1, r2, r3);
    }

    public static /* synthetic */ int zzc(byte[] r0, int r1, int r2) {
        return zzd(r0, r1, r2);
    }

    private static int zzd(byte[] r3, int r4, int r5) {
        byte r0 = r3[r4 - 1];
        int r52 = r5 - r4;
        if (r52 == 0) goto L15;
        if (r52 == 1) goto L13;
        if (r52 == 2) goto L9;
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return 0;
    L9:
        return zzb(r0, r3[r4], r3[r4 + 1]);
    L13:
        return zzb(r0, r3[r4]);
    L15:
        return zzb(r0);
    }
}
