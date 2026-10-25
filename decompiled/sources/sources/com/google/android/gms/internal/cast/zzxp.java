package com.google.android.gms.internal.cast;

import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public abstract class zzxp extends zzxd {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final boolean zzc = false;
    Object zza;

    static {
        zzc = zzaak.zza();
    }

    public /* synthetic */ zzxp(byte[] r1) {
    }

    public static int zzv(int r0) {
        return (352 - (Integer.numberOfLeadingZeros(r0) * 9)) >>> 6;
    }

    public static int zzw(long r0) {
        return (640 - (Long.numberOfLeadingZeros(r0) * 9)) >>> 6;
    }

    public static /* synthetic */ boolean zzy() {
        return zzc;
    }

    public abstract void zzb(int r1, int r2);

    public abstract void zzc(int r1, int r2);

    public abstract void zzd(int r1, int r2);

    public abstract void zze(int r1, int r2);

    public abstract void zzf(int r1, long r2);

    public abstract void zzg(int r1, long r2);

    public abstract void zzh(int r1, boolean r2);

    public abstract void zzi(int r1, String r2);

    public abstract void zzj(int r1, zzxk r2);

    public abstract void zzm(byte r1);

    public abstract void zzn(int r1);

    public abstract void zzo(int r1);

    public abstract void zzp(int r1);

    public abstract void zzq(long r1);

    public abstract void zzr(long r1);

    public abstract int zzu();

    public final void zzx() {
        if (zzu() != 0) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("Did not write as much data as expected.");
    }
}
