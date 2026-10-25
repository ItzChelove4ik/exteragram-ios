package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
abstract class zzaih {
    public static /* synthetic */ boolean zza(byte r0) {
        if (r0 < 0) goto L5;
        return true;
    L5:
        return false;
    }

    public static /* synthetic */ void zzb(byte r1, byte r2, char[] r3, int r4) {
        if (r1 >= (-62)) goto L5;
    L8:
        zzaeh$$ExternalSyntheticBUOutline0.m("Protocol message had invalid UTF-8.");
        return;
    L5:
        if (zze(r2) == true) goto L8;
        r3[r4] = (char) (((r1 & 31) << 6) | (r2 & 63));
    }

    public static /* synthetic */ void zzc(byte r2, byte r3, byte r4, char[] r5, int r6) {
        if (zze(r3) == false) goto L5;
    L16:
        zzaeh$$ExternalSyntheticBUOutline0.m("Protocol message had invalid UTF-8.");
        return;
    L5:
        if (r2 != (-32)) goto L9;
        if (r3 < (-96)) goto L16;
        r2 = -32;
    L9:
        if (r2 != (-19)) goto L13;
        if (r3 >= (-96)) goto L16;
        r2 = -19;
    L13:
        if (zze(r4) == true) goto L16;
        r5[r6] = (char) ((((r2 & 15) << 12) | ((r3 & 63) << 6)) | (r4 & 63));
    }

    public static /* synthetic */ void zzd(byte r2, byte r3, byte r4, byte r5, char[] r6, int r7) {
        if (zze(r3) == false) goto L5;
    L12:
        zzaeh$$ExternalSyntheticBUOutline0.m("Protocol message had invalid UTF-8.");
        return;
    L5:
        if ((((r2 << 28) + (r3 + 112)) >> 30) != 0) goto L12;
        if (zze(r4) == true) goto L12;
        if (zze(r5) == true) goto L12;
        int r22 = (((r2 & 7) << 18) | ((r3 & 63) << 12)) | ((r4 & 63) << 6);
        int r23 = r22 | (r5 & 63);
        r6[r7] = (char) ((r23 >>> 10) + 55232);
        r6[r7 + 1] = (char) ((r23 & 1023) + 56320);
    }

    private static boolean zze(byte r1) {
        if (r1 <= (-65)) goto L6;
        return true;
    L6:
        return false;
    }
}
