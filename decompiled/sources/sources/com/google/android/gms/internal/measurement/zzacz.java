package com.google.android.gms.internal.measurement;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import java.io.OutputStream;

/* loaded from: classes4.dex */
final class zzacz extends zzada {
    private final byte[] zzc;
    private final int zzd;
    private int zze;
    private int zzf;
    private final OutputStream zzg;

    public zzacz(OutputStream r2, int r3) {
        super(null);
        if (r2 == null) goto L10;
        this.zzg = r2;
        if (r3 < 0) goto L8;
        byte[] r22 = new byte[Math.max(r3, 20)];
        this.zzc = r22;
        this.zzd = r22.length;
        return;
    L8:
        f$$ExternalSyntheticBUOutline1.m("bufferSize must be >= 0");
        throw null;
    L10:
        f$$ExternalSyntheticBUOutline2.m("out");
        throw null;
    }

    private final void zzJ(int r3) {
        if ((this.zzd - this.zze) >= r3) goto L6;
        zzK();
        return;
    }

    private final void zzK() {
        int r0 = this.zze;
        this.zzg.write(this.zzc, 0, r0);
        this.zze = 0;
    }

    public final void zzA(long r13) {
        if (zzada.zzI() == false) goto L10;
        long r6 = this.zze;
    L5:
        long r8 = r13 & (-128);
        int r82 = (int) r13;
        byte[] r9 = this.zzc;
        if (r8 == 0) goto L7;
        int r0 = this.zze;
        this.zze = r0 + 1;
        zzagg.zzo(r9, r0, (byte) (r82 | 128));
        r13 = r13 >>> 7;
        goto L5
    L7:
        int r132 = this.zze;
        this.zze = r132 + 1;
        zzagg.zzo(r9, r132, (byte) r82);
        this.zzf += (int) (this.zze - r6);
        return;
    L10:
        long r62 = r13 & (-128);
        int r63 = (int) r13;
        byte[] r7 = this.zzc;
        if (r62 == 0) goto L12;
        int r02 = this.zze;
        this.zze = r02 + 1;
        r7[r02] = (byte) (r63 | 128);
        this.zzf++;
        r13 = r13 >>> 7;
        goto L10
    L12:
        int r133 = this.zze;
        this.zze = r133 + 1;
        r7[r133] = (byte) r63;
        this.zzf++;
    }

    public final void zzB(int r5) {
        int r0 = this.zze;
        byte[] r3 = this.zzc;
        r3[r0] = (byte) r5;
        r3[r0 + 1] = (byte) (r5 >> 8);
        r3[r0 + 2] = (byte) (r5 >> 16);
        r3[r0 + 3] = (byte) (r5 >> 24);
        this.zze = r0 + 4;
        this.zzf += 4;
    }

    public final void zzC(long r7) {
        int r0 = this.zze;
        byte[] r3 = this.zzc;
        r3[r0] = (byte) r7;
        r3[r0 + 1] = (byte) (r7 >> 8);
        r3[r0 + 2] = (byte) ((int) (r7 >> 16));
        r3[r0 + 3] = (byte) ((int) (r7 >> 24));
        r3[r0 + 4] = (byte) ((int) (r7 >> 32));
        r3[r0 + 5] = (byte) ((int) (r7 >> 40));
        r3[r0 + 6] = (byte) ((int) (r7 >> 48));
        r3[r0 + 7] = (byte) (r7 >> 56);
        this.zze = r0 + 8;
        this.zzf += 8;
    }

    public final void zzD(byte[] r5, int r6, int r7) {
        int r0 = this.zzd;
        int r1 = this.zze;
        int r2 = r0 - r1;
        byte[] r3 = this.zzc;
        if (r2 < r7) goto L6;
        System.arraycopy(r5, r6, r3, r1, r7);
        this.zze += r7;
        this.zzf += r7;
        return;
    L6:
        System.arraycopy(r5, r6, r3, r1, r2);
        int r62 = r6 + r2;
        this.zze = r0;
        this.zzf += r2;
        zzK();
        int r72 = r7 - r2;
        if (r72 > r0) goto L9;
        System.arraycopy(r5, r62, r3, 0, r72);
        this.zze = r72;
    L10:
        this.zzf += r72;
        return;
    L9:
        this.zzg.write(r5, r62, r72);
        goto L10
    }

    @Override // com.google.android.gms.internal.measurement.zzacj
    public final void zza(byte[] r1, int r2, int r3) {
        zzD(r1, r2, r3);
    }

    @Override // com.google.android.gms.internal.measurement.zzada
    public final void zzb(int r1, int r2) {
        zzr((r1 << 3) | r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzada
    public final void zzc(int r2, int r3) {
        zzJ(20);
        zzz(r2 << 3);
        if (r3 < 0) goto L6;
        zzz(r3);
        return;
    L6:
        zzA(r3);
    }

    @Override // com.google.android.gms.internal.measurement.zzada
    public final void zzd(int r2, int r3) {
        zzJ(20);
        zzz(r2 << 3);
        zzz(r3);
    }

    @Override // com.google.android.gms.internal.measurement.zzada
    public final void zze(int r2, int r3) {
        zzJ(14);
        zzz((r2 << 3) | 5);
        zzB(r3);
    }

    @Override // com.google.android.gms.internal.measurement.zzada
    public final void zzf(int r2, long r3) {
        zzJ(20);
        zzz(r2 << 3);
        zzA(r3);
    }

    @Override // com.google.android.gms.internal.measurement.zzada
    public final void zzg(int r2, long r3) {
        zzJ(18);
        zzz((r2 << 3) | 1);
        zzC(r3);
    }

    @Override // com.google.android.gms.internal.measurement.zzada
    public final void zzh(int r2, boolean r3) {
        zzJ(11);
        zzz(r2 << 3);
        zzv(r3 ? 1 : 0);
    }

    @Override // com.google.android.gms.internal.measurement.zzada
    public final void zzi(int r1, String r2) {
        zzr((r1 << 3) | 2);
        zzw(r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzada
    public final void zzj(int r1, zzacr r2) {
        zzr((r1 << 3) | 2);
        zzk(r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzada
    public final void zzk(zzacr r2) {
        zzr(r2.zzb());
        r2.zze(this);
    }

    @Override // com.google.android.gms.internal.measurement.zzada
    public final void zzl(byte[] r1, int r2, int r3) {
        zzr(r3);
        zzD(r1, 0, r3);
    }

    @Override // com.google.android.gms.internal.measurement.zzada
    public final void zzo(zzafc r2) {
        zzr(r2.zzcq());
        r2.zzcH(this);
    }

    @Override // com.google.android.gms.internal.measurement.zzada
    public final void zzp(byte r3) {
        if (this.zze != this.zzd) goto L5;
        zzK();
    L5:
        zzv(r3);
    }

    @Override // com.google.android.gms.internal.measurement.zzada
    public final void zzq(int r3) {
        if (r3 < 0) goto L5;
        zzr(r3);
        return;
    L5:
        zzt(r3);
    }

    @Override // com.google.android.gms.internal.measurement.zzada
    public final void zzr(int r2) {
        zzJ(5);
        zzz(r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzada
    public final void zzs(int r2) {
        zzJ(4);
        zzB(r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzada
    public final void zzt(long r2) {
        zzJ(10);
        zzA(r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzada
    public final void zzu(long r2) {
        zzJ(8);
        zzC(r2);
    }

    public final void zzv(byte r3) {
        byte[] r0 = this.zzc;
        int r1 = this.zze;
        r0[r1] = r3;
        this.zze = r1 + 1;
        this.zzf++;
    }

    @Override // com.google.android.gms.internal.measurement.zzada
    public final void zzw(String r6) {
        int r0 = r6.length() * 3;
        int r1 = zzada.zzE(r0);
        int r2 = r1 + r0;
        int r3 = this.zzd;
        if (r2 <= r3) goto L7;
        byte[] r12 = new byte[r0];
        int r62 = zzagl.zzc(r6, r12, 0, r0);
        zzr(r62);
        zzD(r12, 0, r62);
        return;
    L7:
        if (r2 <= (r3 - this.zze)) goto L9;
        zzK();
    L9:
        int r02 = zzada.zzE(r6.length());
        int r22 = this.zze;
        if (r02 != r1) goto L13;
        int r13 = r22 + r02;
        this.zze = r13;     // Catch: ArrayIndexOutOfBoundsException -> L16
        int r63 = zzagl.zzc(r6, this.zzc, r13, r3 - r13);     // Catch: ArrayIndexOutOfBoundsException -> L16
        this.zze = r22;     // Catch: ArrayIndexOutOfBoundsException -> L16
        int r14 = (r63 - r22) - r02;     // Catch: ArrayIndexOutOfBoundsException -> L16
        zzz(r14);     // Catch: ArrayIndexOutOfBoundsException -> L16
        this.zze = r63;     // Catch: ArrayIndexOutOfBoundsException -> L16
    L14:
        this.zzf += r14;
        return;
    L13:
        r14 = zzagl.zzb(r6);     // Catch: ArrayIndexOutOfBoundsException -> L16
        zzz(r14);     // Catch: ArrayIndexOutOfBoundsException -> L16
        this.zze = zzagl.zzc(r6, this.zzc, this.zze, r14);     // Catch: ArrayIndexOutOfBoundsException -> L16
    L16:
        e = move-exception;
        throw new zzacy(e);
    }

    @Override // com.google.android.gms.internal.measurement.zzada
    public final void zzx() {
        if (this.zze <= 0) goto L6;
        zzK();
        return;
    }

    @Override // com.google.android.gms.internal.measurement.zzada
    public final int zzy() {
        throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array or ByteBuffer.");
    }

    public final void zzz(int r7) {
        if (zzada.zzI() == false) goto L10;
        long r0 = this.zze;
    L5:
        int r2 = r7 & (-128);
        byte[] r3 = this.zzc;
        if (r2 == 0) goto L7;
        int r22 = this.zze;
        this.zze = r22 + 1;
        zzagg.zzo(r3, r22, (byte) (r7 | 128));
        r7 = r7 >>> 7;
        goto L5
    L7:
        int r23 = this.zze;
        this.zze = r23 + 1;
        zzagg.zzo(r3, r23, (byte) r7);
        this.zzf += (int) (this.zze - r0);
        return;
    L10:
        int r02 = r7 & (-128);
        byte[] r1 = this.zzc;
        if (r02 == 0) goto L12;
        int r03 = this.zze;
        this.zze = r03 + 1;
        r1[r03] = (byte) (r7 | 128);
        this.zzf++;
        r7 = r7 >>> 7;
        goto L10
    L12:
        int r04 = this.zze;
        this.zze = r04 + 1;
        r1[r04] = (byte) r7;
        this.zzf++;
    }
}
