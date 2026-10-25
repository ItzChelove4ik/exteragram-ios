package com.google.android.gms.internal.clearcut;

import androidx.core.app.NavUtils$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public abstract class zzbk {
    private int zzfq;
    private int zzfr;
    private boolean zzfs;

    static {
    }

    private zzbk() {
        this.zzfq = 100;
        this.zzfr = Integer.MAX_VALUE;
        this.zzfs = false;
    }

    public /* synthetic */ zzbk(zzbl r1) {
        this();
    }

    public static long zza(long r4) {
        long r0 = r4 >>> 1;
        return (-(r4 & 1)) ^ r0;
    }

    public static zzbk zza(byte[] r6, int r7, int r8, boolean r9) {
        zzbm r0 = new zzbm(r6, 0, r8, false, null);
        r0.zzl(r8);     // Catch: zzco -> L5
        return r0;
    L5:
        e = move-exception;
        NavUtils$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    public static int zzm(int r1) {
        int r0 = r1 >>> 1;
        return (-(r1 & 1)) ^ r0;
    }

    public abstract int zzaf();

    public abstract int zzl(int r1);
}
