package com.google.android.gms.internal.vision;

/* loaded from: classes5.dex */
abstract class zzmf {
    public static /* synthetic */ void zza(byte r0, byte r1, byte r2, byte r3, char[] r4, int r5) {
        zzb(r0, r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void zza(byte r0, byte r1, byte r2, char[] r3, int r4) {
        zzb(r0, r1, r2, r3, r4);
    }

    public static /* synthetic */ void zza(byte r0, byte r1, char[] r2, int r3) {
        zzb(r0, r1, r2, r3);
    }

    public static /* synthetic */ void zza(byte r0, char[] r1, int r2) {
        zzb(r0, r1, r2);
    }

    public static /* synthetic */ boolean zza(byte r0) {
        return zzd(r0);
    }

    private static void zzb(byte r2, byte r3, byte r4, byte r5, char[] r6, int r7) {
        if (zzg(r3) == true) goto L13;
        if ((((r2 << 28) + (r3 + 112)) >> 30) != 0) goto L13;
        if (zzg(r4) == true) goto L13;
        if (zzg(r5) == true) goto L13;
        int r22 = ((((r2 & 7) << 18) | ((r3 & 63) << 12)) | ((r4 & 63) << 6)) | (r5 & 63);
        r6[r7] = (char) ((r22 >>> 10) + 55232);
        r6[r7 + 1] = (char) ((r22 & 1023) + 56320);
        return;
    L13:
        throw zzjk.zzh();
    }

    private static void zzb(byte r2, byte r3, byte r4, char[] r5, int r6) {
        if (zzg(r3) == true) goto L15;
        if (r2 != (-32)) goto L8;
        if (r3 < (-96)) goto L15;
    L8:
        if (r2 != (-19)) goto L11;
        if (r3 >= (-96)) goto L15;
    L11:
        if (zzg(r4) == true) goto L15;
        r5[r6] = (char) ((((r2 & 15) << 12) | ((r3 & 63) << 6)) | (r4 & 63));
        return;
    L15:
        throw zzjk.zzh();
    }

    private static void zzb(byte r1, byte r2, char[] r3, int r4) {
        if (r1 < (-62)) goto L9;
        if (zzg(r2) == true) goto L9;
        r3[r4] = (char) (((r1 & 31) << 6) | (r2 & 63));
        return;
    L9:
        throw zzjk.zzh();
    }

    private static void zzb(byte r0, char[] r1, int r2) {
        r1[r2] = (char) r0;
    }

    public static /* synthetic */ boolean zzb(byte r0) {
        return zze(r0);
    }

    public static /* synthetic */ boolean zzc(byte r0) {
        return zzf(r0);
    }

    private static boolean zzd(byte r0) {
        if (r0 < 0) goto L5;
        return true;
    L5:
        return false;
    }

    private static boolean zze(byte r1) {
        if (r1 >= (-32)) goto L6;
        return true;
    L6:
        return false;
    }

    private static boolean zzf(byte r1) {
        if (r1 >= (-16)) goto L6;
        return true;
    L6:
        return false;
    }

    private static boolean zzg(byte r1) {
        if (r1 <= (-65)) goto L6;
        return true;
    L6:
        return false;
    }
}
