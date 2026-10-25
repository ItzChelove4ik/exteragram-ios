package com.google.android.gms.internal.measurement;

import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public abstract class zzada extends zzacj {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final boolean zzc = false;
    Object zza;

    static {
        zzc = zzagg.zza();
    }

    public /* synthetic */ zzada(byte[] r1) {
    }

    public static int zzE(int r0) {
        return (352 - (Integer.numberOfLeadingZeros(r0) * 9)) >>> 6;
    }

    public static int zzF(long r0) {
        return (640 - (Long.numberOfLeadingZeros(r0) * 9)) >>> 6;
    }

    public static int zzG(zzafc r1) {
        int r12 = r1.zzcq();
        return zzE(r12) + r12;
    }

    public static /* synthetic */ boolean zzI() {
        return zzc;
    }

    public final void zzH() {
        if (zzy() <= 0) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Did not write as much data as expected.");
        return;
    L5:
        if (zzy() < 0) goto L7;
        return;
    L7:
        Segment$$ExternalSyntheticBUOutline1.m("Wrote more data than expected.");
    }

    public abstract void zzb(int r1, int r2);

    public abstract void zzc(int r1, int r2);

    public abstract void zzd(int r1, int r2);

    public abstract void zze(int r1, int r2);

    public abstract void zzf(int r1, long r2);

    public abstract void zzg(int r1, long r2);

    public abstract void zzh(int r1, boolean r2);

    public abstract void zzi(int r1, String r2);

    public abstract void zzj(int r1, zzacr r2);

    public abstract void zzk(zzacr r1);

    public abstract void zzl(byte[] r1, int r2, int r3);

    public abstract void zzo(zzafc r1);

    public abstract void zzp(byte r1);

    public abstract void zzq(int r1);

    public abstract void zzr(int r1);

    public abstract void zzs(int r1);

    public abstract void zzt(long r1);

    public abstract void zzu(long r1);

    public abstract void zzw(String r1);

    public abstract void zzx();

    public abstract int zzy();
}
