package com.google.android.recaptcha.internal;

import androidx.core.app.NavUtils$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class zzaev {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static volatile int zzf = 100;
    int zza;
    int zzb;
    final int zzc;
    Object zzd;

    static {
    }

    public /* synthetic */ zzaev(byte[] r1) {
        this.zzc = zzf;
    }

    public static zzaev zzE(byte[] r6, int r7, int r8, boolean r9) {
        zzaet r0 = new zzaet(r6, r7, r8, r9, null);
        r0.zzA(r8);     // Catch: zzagk -> L5
        return r0;
    L5:
        e = move-exception;
        NavUtils$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    public static int zzG(int r1) {
        int r0 = r1 & 1;
        return (r1 >>> 1) ^ (-r0);
    }

    public static long zzH(long r3) {
        long r0 = 1 & r3;
        return (r3 >>> 1) ^ (-r0);
    }

    public abstract int zzA(int r1);

    public abstract void zzB(int r1);

    public abstract boolean zzC();

    public abstract int zzD();

    public final void zzF() {
        if ((this.zza + this.zzb) >= this.zzc) goto L5;
        return;
    L5:
        zzaeh$$ExternalSyntheticBUOutline0.m("Protocol message had too many levels of nesting.  May be malicious.  Use setRecursionLimit() to increase the recursion depth limit.");
    }

    public abstract int zza();

    public abstract void zzb(int r1);

    public abstract double zzc();

    public abstract float zzd();

    public abstract long zze();

    public abstract long zzf();

    public abstract int zzg();

    public abstract long zzh();

    public abstract int zzi();

    public abstract boolean zzj();

    public abstract String zzk();

    public abstract String zzl();

    public abstract zzaer zzm();

    public abstract int zzn();

    public abstract int zzo();

    public abstract int zzp();

    public abstract long zzq();

    public abstract int zzr();

    public abstract long zzs();
}
