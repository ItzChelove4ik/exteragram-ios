package com.google.android.gms.internal.measurement;

import androidx.core.app.NavUtils$$ExternalSyntheticBUOutline0;
import c.f$$ExternalSyntheticBUOutline1;
import java.io.InputStream;

/* loaded from: classes4.dex */
public abstract class zzacv {
    private static volatile int zze = 100;
    int zza;
    int zzb;
    final int zzc;
    Object zzd;

    static {
    }

    public /* synthetic */ zzacv(byte[] r1) {
        this.zzc = zze;
    }

    public static zzacv zzM(InputStream r2, int r3) {
        if (r3 <= 0) goto L9;
        if (r2 != null) goto L8;
        return zzN(zzaed.zza, 0, 0, false);
    L8:
        return new zzacu(r2, r3, null);
    L9:
        f$$ExternalSyntheticBUOutline1.m("bufferSize must be > 0");
        return null;
    }

    public static zzacv zzN(byte[] r6, int r7, int r8, boolean r9) {
        zzact r0 = new zzact(r6, 0, 0, false, null);
        r0.zzD(0);     // Catch: zzaeh -> L5
        return r0;
    L5:
        e = move-exception;
        NavUtils$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    public static int zzR(int r1) {
        int r0 = r1 & 1;
        return (r1 >>> 1) ^ (-r0);
    }

    public static long zzS(long r3) {
        long r0 = 1 & r3;
        return (r3 >>> 1) ^ (-r0);
    }

    public static /* synthetic */ void zzT(byte[] r0, int r1, int r2) {
        if (((r0.length - r1) - r2) < 0) goto L8;
        if ((r1 | r2) < 0) goto L8;
        return;
    L8:
        throw new IndexOutOfBoundsException();
    }

    public abstract int zzD(int r1);

    public abstract void zzE(int r1);

    public abstract int zzF();

    public abstract boolean zzG();

    public abstract int zzH();

    public abstract int zzK(byte[] r1, int r2, int r3);

    public abstract void zzL(int r1);

    public final void zzO() {
        if ((this.zza + this.zzb) >= this.zzc) goto L5;
        return;
    L5:
        zzmw$$ExternalSyntheticBUOutline0.m("Protocol message had too many levels of nesting.  May be malicious.  Use setRecursionLimit() to increase the recursion depth limit.");
    }

    public final void zzP() {
        if (this.zzb != 0) goto L6;
        zzb(0);
        return;
    }

    public final void zzQ() {
    L2:
        int r0 = zza();
        if (r0 == 0) goto L7;
        zzO();
        this.zzb++;
        this.zzb--;
        if (zzc(r0) == true) goto L2;
        return;
    }

    public abstract int zza();

    public abstract void zzb(int r1);

    public abstract boolean zzc(int r1);

    public abstract double zzd();

    public abstract float zze();

    public abstract long zzf();

    public abstract long zzg();

    public abstract int zzh();

    public abstract long zzi();

    public abstract int zzj();

    public abstract boolean zzk();

    public abstract String zzl();

    public abstract String zzm();

    public abstract zzacr zzn();

    public abstract byte[] zzo();

    public abstract int zzp();

    public abstract int zzq();

    public abstract int zzr();

    public abstract long zzs();

    public abstract int zzt();

    public abstract long zzu();

    public abstract int zzx();

    public abstract long zzz();
}
