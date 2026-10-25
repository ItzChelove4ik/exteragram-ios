package com.google.android.gms.internal.vision;

/* loaded from: classes5.dex */
abstract class zzme {
    public zzme() {
    }

    public abstract int zza(int r1, byte[] r2, int r3, int r4);

    public abstract int zza(CharSequence r1, byte[] r2, int r3, int r4);

    public final boolean zza(byte[] r2, int r3, int r4) {
        if (zza(0, r2, r3, r4) != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public abstract String zzb(byte[] r1, int r2, int r3);
}
