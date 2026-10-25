package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
abstract class zzahz {
    private static volatile int zza = 100;

    static {
    }

    public zzahz() {
    }

    public abstract void zza(Object r1, int r2, long r3);

    public abstract void zzb(Object r1, int r2, int r3);

    public abstract void zzc(Object r1, int r2, long r3);

    public abstract void zzd(Object r1, int r2, zzaer r3);

    public abstract void zze(Object r1, int r2, Object r3);

    public abstract Object zzf();

    public abstract Object zzg(Object r1);

    public final boolean zzh(Object r10, zzaew r11, int r12) {
        int r0 = r11.zzc();
        int r1 = r0 >>> 3;
        int r02 = r0 & 7;
        if (r02 == 0) goto L38;
        if (r02 != 1) goto L6;
        zzc(r10, r1, r11.zzi());
        return true;
    L6:
        if (r02 != 2) goto L8;
        zzd(r10, r1, r11.zzp());
        return true;
    L8:
        if (r02 == 3) goto L20;
        if (r02 != 4) goto L11;
        if (r12 == 0) goto L18;
        return false;
    L18:
        zzaeh$$ExternalSyntheticBUOutline0.m("Protocol message end-group tag did not match expected tag.");
        return false;
    L11:
        if (r02 != 5) goto L14;
        zzb(r10, r1, r11.zzj());
        return true;
    L14:
        zzaew$$ExternalSyntheticBUOutline0.m();
        return false;
    L20:
        Object r03 = zzf();
        int r6 = r1 << 3;
        int r122 = r12 + 1;
        if (r122 < zza) goto L23;
        zzaeh$$ExternalSyntheticBUOutline0.m("Protocol message had too many levels of nesting.  May be malicious.  Use setRecursionLimit() to increase the recursion depth limit.");
        return false;
    L23:
        if (r11.zzb() == Integer.MAX_VALUE) goto L27;
        if (zzh(r03, r11, r122) == true) goto L23;
    L27:
        if ((r6 | 4) != r11.zzc()) goto L30;
        zze(r10, r1, zzg(r03));
        return true;
    L30:
        zzaeh$$ExternalSyntheticBUOutline0.m("Protocol message end-group tag did not match expected tag.");
        return false;
    L38:
        zza(r10, r1, r11.zzg());
        return true;
    }
}
