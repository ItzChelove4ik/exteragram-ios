package com.google.android.gms.internal.play_billing;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.Locale;

/* loaded from: classes5.dex */
final class zzem extends zzep {
    private final byte[] zzc;
    private final int zzd;
    private int zze;

    public zzem(byte[] r4, int r5, int r6) {
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

    @Override // com.google.android.gms.internal.play_billing.zzep
    public final int zza() {
        return this.zzd - this.zze;
    }

    @Override // com.google.android.gms.internal.play_billing.zzep
    public final void zzb(byte r10) {
        int r1 = this.zze;
        int r2 = r1 + 1;
        this.zzc[r1] = r10;     // Catch: IndexOutOfBoundsException -> L8
        this.zze = r2;
        return;
    L8:
        e = e;
        r1 = r2;
    L14:
        throw new zzen(r1, this.zzd, 1, e);
    L11:
        e = e;
        goto L14
    }

    public final void zzc(byte[] r8, int r9, int r10) {
        System.arraycopy(r8, 0, this.zzc, this.zze, r10);     // Catch: IndexOutOfBoundsException -> L5
        this.zze += r10;
        return;
    L5:
        e = move-exception;
        throw new zzen(this.zze, this.zzd, r10, e);
    }

    @Override // com.google.android.gms.internal.play_billing.zzep
    public final void zzd(int r1, boolean r2) {
        zzv(r1 << 3);
        zzb(r2 ? 1 : 0);
    }

    @Override // com.google.android.gms.internal.play_billing.zzep
    public final void zze(int r1, zzei r2) {
        zzv((r1 << 3) | 2);
        zzf(r2);
    }

    public final void zzf(zzei r2) {
        zzv(r2.zzd());
        r2.zzg(this);
    }

    @Override // com.google.android.gms.internal.play_billing.zzep
    public final void zzg(int r1, int r2) {
        zzv((r1 << 3) | 5);
        zzh(r2);
    }

    @Override // com.google.android.gms.internal.play_billing.zzep
    public final void zzh(int r10) {
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
        throw new zzen(r1, this.zzd, 4, e);
    }

    @Override // com.google.android.gms.internal.play_billing.zzep
    public final void zzi(int r1, long r2) {
        zzv((r1 << 3) | 1);
        zzj(r2);
    }

    @Override // com.google.android.gms.internal.play_billing.zzep
    public final void zzj(long r10) {
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
        throw new zzen(r1, this.zzd, 8, e);
    }

    @Override // com.google.android.gms.internal.play_billing.zzep
    public final void zzk(int r1, int r2) {
        zzv(r1 << 3);
        zzl(r2);
    }

    @Override // com.google.android.gms.internal.play_billing.zzep
    public final void zzl(int r3) {
        if (r3 < 0) goto L5;
        zzv(r3);
        return;
    L5:
        zzx(r3);
    }

    @Override // com.google.android.gms.internal.play_billing.zzep
    public final void zzm(byte[] r1, int r2, int r3) {
        zzc(r1, 0, r3);
    }

    @Override // com.google.android.gms.internal.play_billing.zzep
    public final void zzn(int r1, zzgl r2, zzgv r3) {
        zzv((r1 << 3) | 2);
        zzv(((zzds) r2).zze(r3));
        r3.zzi(r2, this.zza);
    }

    @Override // com.google.android.gms.internal.play_billing.zzep
    public final void zzr(int r1, String r2) {
        zzv((r1 << 3) | 2);
        zzs(r2);
    }

    public final void zzs(String r6) {
        int r0 = this.zze;
        int r1 = zzep.zzC(r6.length() * 3);     // Catch: zzhq -> L7 IndexOutOfBoundsException -> L11
        int r2 = zzep.zzC(r6.length());     // Catch: zzhq -> L7 IndexOutOfBoundsException -> L11
        if (r2 != r1) goto L9;
        int r12 = r0 + r2;     // Catch: zzhq -> L7 IndexOutOfBoundsException -> L11
        this.zze = r12;     // Catch: zzhq -> L7 IndexOutOfBoundsException -> L11
        int r13 = zzhr.zzb(r6, this.zzc, r12, this.zzd - r12);     // Catch: zzhq -> L7 IndexOutOfBoundsException -> L11
        this.zze = r0;     // Catch: zzhq -> L7 IndexOutOfBoundsException -> L11
        zzv((r13 - r0) - r2);     // Catch: zzhq -> L7 IndexOutOfBoundsException -> L11
        this.zze = r13;     // Catch: zzhq -> L7 IndexOutOfBoundsException -> L11
        return;
    L9:
        zzv(zzhr.zzc(r6));     // Catch: zzhq -> L7 IndexOutOfBoundsException -> L11
        byte[] r14 = this.zzc;     // Catch: zzhq -> L7 IndexOutOfBoundsException -> L11
        int r22 = this.zze;     // Catch: zzhq -> L7 IndexOutOfBoundsException -> L11
        this.zze = zzhr.zzb(r6, r14, r22, this.zzd - r22);     // Catch: zzhq -> L7 IndexOutOfBoundsException -> L11
        return;
    L7:
        e = move-exception;
        this.zze = r0;
        zzF(r6, e);
        return;
    L11:
        e = move-exception;
        throw new zzen(e);
    }

    @Override // com.google.android.gms.internal.play_billing.zzep
    public final void zzt(int r1, int r2) {
        zzv((r1 << 3) | r2);
    }

    @Override // com.google.android.gms.internal.play_billing.zzep
    public final void zzu(int r1, int r2) {
        zzv(r1 << 3);
        zzv(r2);
    }

    @Override // com.google.android.gms.internal.play_billing.zzep
    public final void zzv(int r10) {
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
        throw new zzen(r12, this.zzd, 1, e);
    L5:
        r12 = r0 + 1;
        r2[r0] = (byte) r10;     // Catch: IndexOutOfBoundsException -> L9
        this.zze = r12;
    }

    @Override // com.google.android.gms.internal.play_billing.zzep
    public final void zzw(int r1, long r2) {
        zzv(r1 << 3);
        zzx(r2);
    }

    @Override // com.google.android.gms.internal.play_billing.zzep
    public final void zzx(long r11) {
        int r0 = this.zze;
        if (zzep.zzG() == false) goto L10;
        if ((this.zzd - r0) < 10) goto L10;
    L6:
        long r7 = r11 & (-128);
        byte[] r72 = this.zzc;
        if (r7 == 0) goto L8;
        zzho.zzn(r72, r0, (byte) (((int) r11) | 128));
        r11 = r11 >>> 7;
        r0 = r0 + 1;
        goto L6
    L8:
        int r1 = r0 + 1;
        zzho.zzn(r72, r0, (byte) r11);
    L14:
        this.zze = r1;
        return;
    L10:
        long r73 = r11 & (-128);
        byte[] r74 = this.zzc;
        if (r73 == 0) goto L12;
        r1 = r0 + 1;
        r74[r0] = (byte) (((int) r11) | 128);     // Catch: IndexOutOfBoundsException -> L16
        r11 = r11 >>> 7;
        r0 = r1;
    L16:
        e = move-exception;
        throw new zzen(r1, this.zzd, 1, e);
    L12:
        r1 = r0 + 1;
        r74[r0] = (byte) r11;     // Catch: IndexOutOfBoundsException -> L16
        goto L14
    }
}
