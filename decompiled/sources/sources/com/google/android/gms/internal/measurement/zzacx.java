package com.google.android.gms.internal.measurement;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.Locale;

/* loaded from: classes4.dex */
final class zzacx extends zzada {
    private final byte[] zzc;
    private final int zzd;
    private int zze;

    public zzacx(byte[] r4, int r5, int r6) {
        super(null);
        int r0 = r4.length;
        if (((r0 - r6) | r6) < 0) goto L6;
        this.zzc = r4;
        this.zze = 0;
        this.zzd = r6;
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m(String.format(Locale.US, "Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(r0), 0, Integer.valueOf(r6)}));
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzacj
    public final void zza(byte[] r1, int r2, int r3) {
        zzv(r1, r2, r3);
    }

    @Override // com.google.android.gms.internal.measurement.zzada
    public final void zzb(int r1, int r2) {
        zzr((r1 << 3) | r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzada
    public final void zzc(int r1, int r2) {
        zzr(r1 << 3);
        zzq(r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzada
    public final void zzd(int r1, int r2) {
        zzr(r1 << 3);
        zzr(r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzada
    public final void zze(int r1, int r2) {
        zzr((r1 << 3) | 5);
        zzs(r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzada
    public final void zzf(int r1, long r2) {
        zzr(r1 << 3);
        zzt(r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzada
    public final void zzg(int r1, long r2) {
        zzr((r1 << 3) | 1);
        zzu(r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzada
    public final void zzh(int r1, boolean r2) {
        zzr(r1 << 3);
        zzp(r2 ? 1 : 0);
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
        zzv(r1, 0, r3);
    }

    @Override // com.google.android.gms.internal.measurement.zzada
    public final void zzo(zzafc r2) {
        zzr(r2.zzcq());
        r2.zzcH(this);
    }

    @Override // com.google.android.gms.internal.measurement.zzada
    public final void zzp(byte r10) {
        int r1 = this.zze;
        int r2 = r1 + 1;
        this.zzc[r1] = r10;     // Catch: IndexOutOfBoundsException -> L8
        this.zze = r2;
        return;
    L8:
        e = e;
        r1 = r2;
    L14:
        throw new zzacy(r1, this.zzd, 1, e);
    L11:
        e = e;
        goto L14
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
    public final void zzr(int r10) {
        int r0 = this.zze;
    L3:
        int r1 = r10 & (-128);
        byte[] r2 = this.zzc;
        if (r1 == 0) goto L5;
        int r12 = r0 + 1;
        r2[r0] = (byte) (r10 | 128);     // Catch: IndexOutOfBoundsException -> L9
        r10 = r10 >>> 7;
        r0 = r12;
    L9:
        e = move-exception;
        throw new zzacy(r12, this.zzd, 1, e);
    L5:
        r12 = r0 + 1;
        r2[r0] = (byte) r10;     // Catch: IndexOutOfBoundsException -> L9
        this.zze = r12;
    }

    @Override // com.google.android.gms.internal.measurement.zzada
    public final void zzs(int r10) {
        int r1 = this.zze;
        byte[] r0 = this.zzc;     // Catch: IndexOutOfBoundsException -> L6
        r0[r1] = (byte) r10;     // Catch: IndexOutOfBoundsException -> L6
        r0[r1 + 1] = (byte) (r10 >> 8);     // Catch: IndexOutOfBoundsException -> L6
        r0[r1 + 2] = (byte) (r10 >> 16);     // Catch: IndexOutOfBoundsException -> L6
        r0[r1 + 3] = (byte) (r10 >> 24);     // Catch: IndexOutOfBoundsException -> L6
        this.zze = r1 + 4;
        return;
    L6:
        e = move-exception;
        throw new zzacy(r1, this.zzd, 4, e);
    }

    @Override // com.google.android.gms.internal.measurement.zzada
    public final void zzt(long r11) {
        boolean r0 = zzada.zzI();
        int r1 = this.zze;
        if (r0 == false) goto L10;
        if ((this.zzd - r1) < 10) goto L10;
    L6:
        long r7 = r11 & (-128);
        byte[] r72 = this.zzc;
        if (r7 == 0) goto L8;
        zzagg.zzo(r72, r1, (byte) (((int) r11) | 128));
        r11 = r11 >>> 7;
        r1 = r1 + 1;
        goto L6
    L8:
        int r02 = r1 + 1;
        zzagg.zzo(r72, r1, (byte) r11);
    L15:
        this.zze = r02;
        return;
    L10:
        long r73 = r11 & (-128);
        byte[] r74 = this.zzc;
        if (r73 == 0) goto L12;
        int r8 = r1 + 1;
        r74[r1] = (byte) (((int) r11) | 128);     // Catch: IndexOutOfBoundsException -> L22
        r11 = r11 >>> 7;
        r1 = r8;
    L22:
        e = e;
        int r2 = r8;
    L25:
        throw new zzacy(r2, this.zzd, 1, e);
    L12:
        r2 = r1 + 1;
        r74[r1] = (byte) r11;     // Catch: IndexOutOfBoundsException -> L17
        r02 = r2;
    L17:
        e = e;
        goto L25
    }

    @Override // com.google.android.gms.internal.measurement.zzada
    public final void zzu(long r10) {
        int r1 = this.zze;
        byte[] r0 = this.zzc;     // Catch: IndexOutOfBoundsException -> L6
        r0[r1] = (byte) r10;     // Catch: IndexOutOfBoundsException -> L6
        r0[r1 + 1] = (byte) (r10 >> 8);     // Catch: IndexOutOfBoundsException -> L6
        r0[r1 + 2] = (byte) (r10 >> 16);     // Catch: IndexOutOfBoundsException -> L6
        r0[r1 + 3] = (byte) (r10 >> 24);     // Catch: IndexOutOfBoundsException -> L6
        r0[r1 + 4] = (byte) (r10 >> 32);     // Catch: IndexOutOfBoundsException -> L6
        r0[r1 + 5] = (byte) (r10 >> 40);     // Catch: IndexOutOfBoundsException -> L6
        r0[r1 + 6] = (byte) (r10 >> 48);     // Catch: IndexOutOfBoundsException -> L6
        r0[r1 + 7] = (byte) (r10 >> 56);     // Catch: IndexOutOfBoundsException -> L6
        this.zze = r1 + 8;
        return;
    L6:
        e = move-exception;
        throw new zzacy(r1, this.zzd, 8, e);
    }

    public final void zzv(byte[] r8, int r9, int r10) {
        System.arraycopy(r8, r9, this.zzc, this.zze, r10);     // Catch: IndexOutOfBoundsException -> L5
        this.zze += r10;
        return;
    L5:
        e = move-exception;
        throw new zzacy(this.zze, this.zzd, r10, e);
    }

    @Override // com.google.android.gms.internal.measurement.zzada
    public final void zzw(String r6) {
        int r0 = this.zze;
        int r1 = zzada.zzE(r6.length() * 3);     // Catch: IndexOutOfBoundsException -> L9
        int r2 = zzada.zzE(r6.length());     // Catch: IndexOutOfBoundsException -> L9
        if (r2 != r1) goto L7;
        int r12 = r0 + r2;     // Catch: IndexOutOfBoundsException -> L9
        this.zze = r12;     // Catch: IndexOutOfBoundsException -> L9
        byte[] r3 = this.zzc;     // Catch: IndexOutOfBoundsException -> L9
        int r62 = zzagl.zzc(r6, r3, r12, r3.length - r12);     // Catch: IndexOutOfBoundsException -> L9
        this.zze = r0;     // Catch: IndexOutOfBoundsException -> L9
        zzr((r62 - r0) - r2);     // Catch: IndexOutOfBoundsException -> L9
        this.zze = r62;     // Catch: IndexOutOfBoundsException -> L9
        return;
    L7:
        zzr(zzagl.zzb(r6));     // Catch: IndexOutOfBoundsException -> L9
        byte[] r02 = this.zzc;     // Catch: IndexOutOfBoundsException -> L9
        int r13 = this.zze;     // Catch: IndexOutOfBoundsException -> L9
        this.zze = zzagl.zzc(r6, r02, r13, r02.length - r13);     // Catch: IndexOutOfBoundsException -> L9
        return;
    L9:
        e = move-exception;
        throw new zzacy(e);
    }

    @Override // com.google.android.gms.internal.measurement.zzada
    public final void zzx() {
    }

    @Override // com.google.android.gms.internal.measurement.zzada
    public final int zzy() {
        return this.zzd - this.zze;
    }
}
