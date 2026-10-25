package com.google.android.recaptcha.internal;

import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public abstract class zzafa extends zzaej {
    Object zza;

    public /* synthetic */ zzafa(byte[] r1) {
    }

    public static int zzu(int r0) {
        return (352 - (Integer.numberOfLeadingZeros(r0) * 9)) >>> 6;
    }

    public static int zzv(long r0) {
        return (640 - (Long.numberOfLeadingZeros(r0) * 9)) >>> 6;
    }

    public abstract void zza(int r1, int r2);

    public abstract void zzb(int r1, int r2);

    public abstract void zzc(int r1, int r2);

    public abstract void zzd(int r1, int r2);

    public abstract void zze(int r1, long r2);

    public abstract void zzf(int r1, long r2);

    public abstract void zzg(int r1, boolean r2);

    public abstract void zzh(int r1, String r2);

    public abstract void zzi(int r1, zzaer r2);

    public abstract void zzl(byte r1);

    public abstract void zzm(int r1);

    public abstract void zzn(int r1);

    public abstract void zzo(int r1);

    public abstract void zzp(long r1);

    public abstract void zzq(long r1);

    public abstract int zzt();

    public final void zzw() {
        if (zzt() <= 0) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Did not write as much data as expected.");
        return;
    L5:
        if (zzt() < 0) goto L7;
        return;
    L7:
        Segment$$ExternalSyntheticBUOutline1.m("Wrote more data than expected.");
    }
}
