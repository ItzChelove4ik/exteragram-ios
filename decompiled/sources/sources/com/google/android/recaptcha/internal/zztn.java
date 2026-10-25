package com.google.android.recaptcha.internal;

import java.util.Arrays;

/* loaded from: classes5.dex */
final class zztn {
    final long[] zza;
    final long[] zzb;
    final long[] zzc;

    public zztn() {
        this(new long[10], new long[10], new long[10]);
    }

    public zztn(zztm r1) {
        this();
        zza(this, r1);
    }

    public zztn(zztn r3) {
        this.zza = Arrays.copyOf(r3.zza, 10);
        this.zzb = Arrays.copyOf(r3.zzb, 10);
        this.zzc = Arrays.copyOf(r3.zzc, 10);
    }

    public zztn(long[] r1, long[] r2, long[] r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    public static zztn zza(zztn r3, zztm r4) {
        zztn r0 = r4.zza;
        long[] r1 = r3.zza;
        long[] r2 = r0.zza;
        long[] r42 = r4.zzb;
        zztw.zze(r1, r2, r42);
        long[] r12 = r3.zzb;
        long[] r22 = r0.zzb;
        long[] r02 = r0.zzc;
        zztw.zze(r12, r22, r02);
        zztw.zze(r3.zzc, r02, r42);
        return r3;
    }

    public final byte[] zzb() {
        long[] r1 = new long[10];
        long[] r2 = new long[10];
        long[] r3 = new long[10];
        int r4 = zztw.$r8$clinit;
        long[] r42 = new long[10];
        long[] r5 = new long[10];
        long[] r6 = new long[10];
        long[] r7 = new long[10];
        long[] r8 = new long[10];
        long[] r9 = new long[10];
        long[] r10 = new long[10];
        long[] r11 = new long[10];
        long[] r12 = new long[10];
        long[] r13 = new long[10];
        long[] r14 = this.zzc;
        zztw.zzf(r42, r14);
        zztw.zzf(r13, r42);
        zztw.zzf(r12, r13);
        zztw.zze(r5, r12, r14);
        zztw.zze(r6, r5, r42);
        zztw.zzf(r12, r6);
        zztw.zze(r7, r12, r5);
        zztw.zzf(r12, r7);
        zztw.zzf(r13, r12);
        zztw.zzf(r12, r13);
        zztw.zzf(r13, r12);
        zztw.zzf(r12, r13);
        zztw.zze(r8, r12, r7);
        zztw.zzf(r12, r8);
        zztw.zzf(r13, r12);
        int r43 = 2;
        int r52 = 2;
    L3:
        if (r52 >= 10) goto L5;
        zztw.zzf(r12, r13);
        zztw.zzf(r13, r12);
        r52 = r52 + 2;
        goto L3
    L5:
        zztw.zze(r9, r13, r8);
        zztw.zzf(r12, r9);
        zztw.zzf(r13, r12);
        int r53 = 2;
    L7:
        if (r53 >= 20) goto L9;
        zztw.zzf(r12, r13);
        zztw.zzf(r13, r12);
        r53 = r53 + 2;
        goto L7
    L9:
        zztw.zze(r12, r13, r9);
        zztw.zzf(r13, r12);
        zztw.zzf(r12, r13);
        int r54 = 2;
    L10:
        if (r54 >= 10) goto L12;
        zztw.zzf(r13, r12);
        zztw.zzf(r12, r13);
        r54 = r54 + 2;
        goto L10
    L12:
        zztw.zze(r10, r12, r8);
        zztw.zzf(r12, r10);
        zztw.zzf(r13, r12);
        int r0 = 2;
    L14:
        if (r0 >= 50) goto L16;
        zztw.zzf(r12, r13);
        zztw.zzf(r13, r12);
        r0 = r0 + 2;
        goto L14
    L16:
        zztw.zze(r11, r13, r10);
        zztw.zzf(r13, r11);
        zztw.zzf(r12, r13);
        int r02 = 2;
    L18:
        if (r02 >= 100) goto L20;
        zztw.zzf(r13, r12);
        zztw.zzf(r12, r13);
        r02 = r02 + 2;
        goto L18
    L20:
        zztw.zze(r13, r12, r11);
        zztw.zzf(r12, r13);
        zztw.zzf(r13, r12);
    L21:
        if (r43 >= 50) goto L23;
        zztw.zzf(r12, r13);
        zztw.zzf(r13, r12);
        r43 = r43 + 2;
        goto L21
    L23:
        zztw.zze(r12, r13, r10);
        zztw.zzf(r13, r12);
        zztw.zzf(r12, r13);
        zztw.zzf(r13, r12);
        zztw.zzf(r12, r13);
        zztw.zzf(r13, r12);
        zztw.zze(r1, r13, r6);
        zztw.zze(r2, this.zza, r1);
        zztw.zze(r3, this.zzb, r1);
        byte[] r15 = zztw.zzh(r3);
        r15[31] = (byte) (r15[31] ^ (zztp.zzf(r2) << 7));
        return r15;
    }
}
