package com.google.android.recaptcha.internal;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.Locale;

/* loaded from: classes5.dex */
final class zzaex extends zzafa {
    private final byte[] zzb;
    private final int zzc;
    private int zzd;

    public zzaex(byte[] r3, int r4, int r5, zzaey r6) {
        super(null);
        int r62 = r3.length;
        if (((r62 - r5) | r5) < 0) goto L6;
        this.zzb = r3;
        this.zzd = 0;
        this.zzc = r5;
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m(String.format(Locale.US, "Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(r62), 0, Integer.valueOf(r5)}));
        throw null;
    }

    @Override // com.google.android.recaptcha.internal.zzafa
    public final void zza(int r1, int r2) {
        zzn((r1 << 3) | r2);
    }

    @Override // com.google.android.recaptcha.internal.zzafa
    public final void zzb(int r1, int r2) {
        zzn(r1 << 3);
        zzm(r2);
    }

    @Override // com.google.android.recaptcha.internal.zzafa
    public final void zzc(int r1, int r2) {
        zzn(r1 << 3);
        zzn(r2);
    }

    @Override // com.google.android.recaptcha.internal.zzafa
    public final void zzd(int r1, int r2) {
        zzn((r1 << 3) | 5);
        zzo(r2);
    }

    @Override // com.google.android.recaptcha.internal.zzafa
    public final void zze(int r1, long r2) {
        zzn(r1 << 3);
        zzp(r2);
    }

    @Override // com.google.android.recaptcha.internal.zzafa
    public final void zzf(int r1, long r2) {
        zzn((r1 << 3) | 1);
        zzq(r2);
    }

    @Override // com.google.android.recaptcha.internal.zzafa
    public final void zzg(int r1, boolean r2) {
        zzn(r1 << 3);
        zzl(r2 ? 1 : 0);
    }

    @Override // com.google.android.recaptcha.internal.zzafa
    public final void zzh(int r1, String r2) {
        zzn((r1 << 3) | 2);
        zzs(r2);
    }

    @Override // com.google.android.recaptcha.internal.zzafa
    public final void zzi(int r1, zzaer r2) {
        zzn((r1 << 3) | 2);
        zzn(r2.zzb());
        r2.zzg(this);
    }

    @Override // com.google.android.recaptcha.internal.zzafa
    public final void zzl(byte r10) {
        int r1 = this.zzd;
        int r2 = r1 + 1;
        this.zzb[r1] = r10;     // Catch: IndexOutOfBoundsException -> L8
        this.zzd = r2;
        return;
    L8:
        e = e;
        r1 = r2;
    L14:
        throw new zzaez(r1, this.zzc, 1, e);
    L11:
        e = e;
        goto L14
    }

    @Override // com.google.android.recaptcha.internal.zzafa
    public final void zzm(int r10) {
        if (r10 < 0) goto L5;
        zzn(r10);
        return;
    L5:
        int r1 = this.zzd;
        byte[] r0 = this.zzb;     // Catch: IndexOutOfBoundsException -> L29
        long r2 = r10;
        int r102 = r1 + 1;
        r0[r1] = (byte) (((int) r2) | 128);     // Catch: IndexOutOfBoundsException -> L31
        int r4 = r1 + 2;
        r0[r102] = (byte) (((int) (r2 >>> 7)) | 128);     // Catch: IndexOutOfBoundsException -> L35
        int r103 = r1 + 3;
        r0[r4] = (byte) (((int) (r2 >>> 14)) | 128);     // Catch: IndexOutOfBoundsException -> L31
        r4 = r1 + 4;
        r0[r103] = (byte) (((int) (r2 >>> 21)) | 128);     // Catch: IndexOutOfBoundsException -> L35
        int r104 = r1 + 5;
        r0[r4] = (byte) (((int) (r2 >>> 28)) | 128);     // Catch: IndexOutOfBoundsException -> L31
        int r22 = r1 + 6;
        r0[r104] = -1;     // Catch: IndexOutOfBoundsException -> L33
        int r105 = r1 + 7;
        r0[r22] = -1;     // Catch: IndexOutOfBoundsException -> L31
        r22 = r1 + 8;
        r0[r105] = -1;     // Catch: IndexOutOfBoundsException -> L33
        r102 = r1 + 9;
        r0[r22] = -1;     // Catch: IndexOutOfBoundsException -> L31
        r1 = r1 + 10;
        r0[r102] = 1;     // Catch: IndexOutOfBoundsException -> L29
        this.zzd = r1;
        return;
    L33:
        e = move-exception;
        IndexOutOfBoundsException r8 = e;
        r1 = r22;
    L38:
        throw new zzaez(r1, this.zzc, 10, r8);
    L35:
        e = move-exception;
        r8 = e;
        r1 = r4;
    L31:
        e = move-exception;
        r1 = r102;
        r8 = e;
    L29:
        e = move-exception;
        r8 = e;
        goto L38
    }

    @Override // com.google.android.recaptcha.internal.zzafa
    public final void zzn(int r10) {
        int r0 = this.zzd;
        int r1 = r10 & (-128);
        byte[] r2 = this.zzb;
        if (r1 != 0) goto L8;
        int r12 = r0 + 1;
        r2[r0] = (byte) r10;     // Catch: IndexOutOfBoundsException -> L39
        this.zzd = r12;     // Catch: IndexOutOfBoundsException -> L39
        return;
    L8:
        int r13 = r0 + 1;     // Catch: IndexOutOfBoundsException -> L39
        r2[r0] = (byte) (r10 | 128);     // Catch: IndexOutOfBoundsException -> L39
        int r3 = r10 >>> 7;
        if ((r3 & (-128)) != 0) goto L16;
        int r102 = r0 + 2;
        r2[r13] = (byte) r3;     // Catch: IndexOutOfBoundsException -> L14
        this.zzd = r102;     // Catch: IndexOutOfBoundsException -> L14
        return;
    L16:
        int r4 = r0 + 2;
        r2[r13] = (byte) (r3 | 128);     // Catch: IndexOutOfBoundsException -> L35
        int r14 = r10 >>> 14;
        if ((r14 & (-128)) != 0) goto L23;
        int r103 = r0 + 3;
        r2[r4] = (byte) r14;     // Catch: IndexOutOfBoundsException -> L14
        this.zzd = r103;     // Catch: IndexOutOfBoundsException -> L14
        return;
    L23:
        int r32 = r0 + 3;
        r2[r4] = (byte) (r14 | 128);     // Catch: IndexOutOfBoundsException -> L37
        int r15 = r10 >>> 21;
        if ((r15 & (-128)) != 0) goto L30;
        int r104 = r0 + 4;
        r2[r32] = (byte) r15;     // Catch: IndexOutOfBoundsException -> L14
        this.zzd = r104;     // Catch: IndexOutOfBoundsException -> L14
        return;
    L30:
        r4 = r0 + 4;
        r2[r32] = (byte) (r15 | 128);     // Catch: IndexOutOfBoundsException -> L35
        int r16 = r0 + 5;
        r2[r4] = (byte) (r10 >>> 28);     // Catch: IndexOutOfBoundsException -> L39
        this.zzd = r16;     // Catch: IndexOutOfBoundsException -> L39
        return;
    L37:
        e = move-exception;
        IndexOutOfBoundsException r8 = e;
        r1 = r32;
    L42:
        throw new zzaez(r1, this.zzc, 1, r8);
    L35:
        e = move-exception;
        r8 = e;
        r1 = r4;
    L14:
        e = move-exception;
        r1 = r10;
        r8 = e;
    L39:
        e = move-exception;
        r8 = e;
        goto L42
    }

    @Override // com.google.android.recaptcha.internal.zzafa
    public final void zzo(int r10) {
        int r1 = this.zzd;
        byte[] r0 = this.zzb;     // Catch: IndexOutOfBoundsException -> L6
        r0[r1] = (byte) r10;     // Catch: IndexOutOfBoundsException -> L6
        r0[r1 + 1] = (byte) (r10 >> 8);     // Catch: IndexOutOfBoundsException -> L6
        r0[r1 + 2] = (byte) (r10 >> 16);     // Catch: IndexOutOfBoundsException -> L6
        r0[r1 + 3] = (byte) (r10 >> 24);     // Catch: IndexOutOfBoundsException -> L6
        this.zzd = r1 + 4;
        return;
    L6:
        e = move-exception;
        throw new zzaez(r1, this.zzc, 4, e);
    }

    @Override // com.google.android.recaptcha.internal.zzafa
    public final void zzp(long r13) {
        long r2 = r13 & (-128);
        int r4 = this.zzd;
        byte[] r3 = this.zzb;
        if (r2 == 0) goto L5;
        r3[r4] = (byte) (((int) r13) | 128);     // Catch: IndexOutOfBoundsException -> L7
        int r22 = r4 + 1;     // Catch: IndexOutOfBoundsException -> L7
        long r7 = r13 >>> 7;     // Catch: IndexOutOfBoundsException -> L7
        long r9 = r7 & (-128);     // Catch: IndexOutOfBoundsException -> L7
        int r72 = (int) r7;     // Catch: IndexOutOfBoundsException -> L7
        if (r9 != 0) goto L13;
        r3[r22] = (byte) r72;     // Catch: IndexOutOfBoundsException -> L7
        this.zzd = r4 + 2;     // Catch: IndexOutOfBoundsException -> L7
        return;
    L13:
        r3[r22] = (byte) (r72 | 128);     // Catch: IndexOutOfBoundsException -> L7
        int r23 = r4 + 2;     // Catch: IndexOutOfBoundsException -> L7
        long r73 = r13 >>> 14;     // Catch: IndexOutOfBoundsException -> L7
        long r92 = r73 & (-128);     // Catch: IndexOutOfBoundsException -> L7
        int r74 = (int) r73;     // Catch: IndexOutOfBoundsException -> L7
        if (r92 != 0) goto L17;
        r3[r23] = (byte) r74;     // Catch: IndexOutOfBoundsException -> L7
        this.zzd = r4 + 3;     // Catch: IndexOutOfBoundsException -> L7
        return;
    L17:
        r3[r23] = (byte) (r74 | 128);     // Catch: IndexOutOfBoundsException -> L7
        int r24 = r4 + 3;     // Catch: IndexOutOfBoundsException -> L7
        long r75 = r13 >>> 21;     // Catch: IndexOutOfBoundsException -> L7
        long r93 = r75 & (-128);     // Catch: IndexOutOfBoundsException -> L7
        int r76 = (int) r75;     // Catch: IndexOutOfBoundsException -> L7
        if (r93 != 0) goto L21;
        r3[r24] = (byte) r76;     // Catch: IndexOutOfBoundsException -> L7
        this.zzd = r4 + 4;     // Catch: IndexOutOfBoundsException -> L7
        return;
    L21:
        r3[r24] = (byte) (r76 | 128);     // Catch: IndexOutOfBoundsException -> L7
        int r25 = r4 + 4;     // Catch: IndexOutOfBoundsException -> L7
        long r77 = r13 >>> 28;     // Catch: IndexOutOfBoundsException -> L7
        long r94 = r77 & (-128);     // Catch: IndexOutOfBoundsException -> L7
        int r78 = (int) r77;     // Catch: IndexOutOfBoundsException -> L7
        if (r94 != 0) goto L25;
        r3[r25] = (byte) r78;     // Catch: IndexOutOfBoundsException -> L7
        this.zzd = r4 + 5;     // Catch: IndexOutOfBoundsException -> L7
        return;
    L25:
        r3[r25] = (byte) (r78 | 128);     // Catch: IndexOutOfBoundsException -> L7
        int r26 = r4 + 5;     // Catch: IndexOutOfBoundsException -> L7
        long r79 = r13 >>> 35;     // Catch: IndexOutOfBoundsException -> L7
        long r95 = r79 & (-128);     // Catch: IndexOutOfBoundsException -> L7
        int r710 = (int) r79;     // Catch: IndexOutOfBoundsException -> L7
        if (r95 != 0) goto L29;
        r3[r26] = (byte) r710;     // Catch: IndexOutOfBoundsException -> L7
        this.zzd = r4 + 6;     // Catch: IndexOutOfBoundsException -> L7
        return;
    L29:
        r3[r26] = (byte) (r710 | 128);     // Catch: IndexOutOfBoundsException -> L7
        int r27 = r4 + 6;     // Catch: IndexOutOfBoundsException -> L7
        long r711 = r13 >>> 42;     // Catch: IndexOutOfBoundsException -> L7
        long r96 = r711 & (-128);     // Catch: IndexOutOfBoundsException -> L7
        int r712 = (int) r711;     // Catch: IndexOutOfBoundsException -> L7
        if (r96 != 0) goto L33;
        r3[r27] = (byte) r712;     // Catch: IndexOutOfBoundsException -> L7
        this.zzd = r4 + 7;     // Catch: IndexOutOfBoundsException -> L7
        return;
    L33:
        r3[r27] = (byte) (r712 | 128);     // Catch: IndexOutOfBoundsException -> L7
        int r28 = r4 + 7;     // Catch: IndexOutOfBoundsException -> L7
        long r713 = r13 >>> 49;     // Catch: IndexOutOfBoundsException -> L7
        long r97 = r713 & (-128);     // Catch: IndexOutOfBoundsException -> L7
        int r714 = (int) r713;     // Catch: IndexOutOfBoundsException -> L7
        if (r97 != 0) goto L37;
        r3[r28] = (byte) r714;     // Catch: IndexOutOfBoundsException -> L7
        this.zzd = r4 + 8;     // Catch: IndexOutOfBoundsException -> L7
        return;
    L37:
        r3[r28] = (byte) (r714 | 128);     // Catch: IndexOutOfBoundsException -> L7
        int r29 = r4 + 8;     // Catch: IndexOutOfBoundsException -> L7
        long r715 = r13 >>> 56;     // Catch: IndexOutOfBoundsException -> L7
        int r1 = (int) r715;     // Catch: IndexOutOfBoundsException -> L7
        if (((-128) & r715) != 0) goto L41;
        r3[r29] = (byte) r1;     // Catch: IndexOutOfBoundsException -> L7
        this.zzd = r4 + 9;     // Catch: IndexOutOfBoundsException -> L7
        return;
    L41:
        r3[r29] = (byte) (r1 | 128);     // Catch: IndexOutOfBoundsException -> L7
        r3[r4 + 9] = (byte) (r13 >>> 63);     // Catch: IndexOutOfBoundsException -> L7
        this.zzd = r4 + 10;     // Catch: IndexOutOfBoundsException -> L7
        return;
    L5:
        r3[r4] = (byte) r13;     // Catch: IndexOutOfBoundsException -> L7
        this.zzd = r4 + 1;     // Catch: IndexOutOfBoundsException -> L7
        return;
    L7:
        e = move-exception;
        throw new zzaez(r4, this.zzc, 1, e);
    }

    @Override // com.google.android.recaptcha.internal.zzafa
    public final void zzq(long r10) {
        int r1 = this.zzd;
        byte[] r0 = this.zzb;     // Catch: IndexOutOfBoundsException -> L6
        r0[r1] = (byte) r10;     // Catch: IndexOutOfBoundsException -> L6
        r0[r1 + 1] = (byte) (r10 >> 8);     // Catch: IndexOutOfBoundsException -> L6
        r0[r1 + 2] = (byte) (r10 >> 16);     // Catch: IndexOutOfBoundsException -> L6
        r0[r1 + 3] = (byte) (r10 >> 24);     // Catch: IndexOutOfBoundsException -> L6
        r0[r1 + 4] = (byte) (r10 >> 32);     // Catch: IndexOutOfBoundsException -> L6
        r0[r1 + 5] = (byte) (r10 >> 40);     // Catch: IndexOutOfBoundsException -> L6
        r0[r1 + 6] = (byte) (r10 >> 48);     // Catch: IndexOutOfBoundsException -> L6
        r0[r1 + 7] = (byte) (r10 >> 56);     // Catch: IndexOutOfBoundsException -> L6
        this.zzd = r1 + 8;
        return;
    L6:
        e = move-exception;
        throw new zzaez(r1, this.zzc, 8, e);
    }

    public final void zzr(byte[] r8, int r9, int r10) {
        System.arraycopy(r8, r9, this.zzb, this.zzd, r10);     // Catch: IndexOutOfBoundsException -> L5
        this.zzd += r10;
        return;
    L5:
        e = move-exception;
        throw new zzaez(this.zzd, this.zzc, r10, e);
    }

    public final void zzs(String r6) {
        int r0 = this.zzd;
        int r1 = zzafa.zzu(r6.length() * 3);     // Catch: IndexOutOfBoundsException -> L9
        int r2 = zzafa.zzu(r6.length());     // Catch: IndexOutOfBoundsException -> L9
        if (r2 != r1) goto L7;
        int r12 = r0 + r2;     // Catch: IndexOutOfBoundsException -> L9
        this.zzd = r12;     // Catch: IndexOutOfBoundsException -> L9
        byte[] r3 = this.zzb;     // Catch: IndexOutOfBoundsException -> L9
        int r62 = zzail.zzb(r6, r3, r12, r3.length - r12);     // Catch: IndexOutOfBoundsException -> L9
        this.zzd = r0;     // Catch: IndexOutOfBoundsException -> L9
        zzn((r62 - r0) - r2);     // Catch: IndexOutOfBoundsException -> L9
        this.zzd = r62;     // Catch: IndexOutOfBoundsException -> L9
        return;
    L7:
        int r02 = zzail.$r8$clinit;     // Catch: IndexOutOfBoundsException -> L9
        zzn(zzaij.zzb(r6));     // Catch: IndexOutOfBoundsException -> L9
        byte[] r03 = this.zzb;     // Catch: IndexOutOfBoundsException -> L9
        int r13 = this.zzd;     // Catch: IndexOutOfBoundsException -> L9
        this.zzd = zzail.zzb(r6, r03, r13, r03.length - r13);     // Catch: IndexOutOfBoundsException -> L9
        return;
    L9:
        e = move-exception;
        throw new zzaez(e);
    }

    @Override // com.google.android.recaptcha.internal.zzafa
    public final int zzt() {
        return this.zzc - this.zzd;
    }
}
