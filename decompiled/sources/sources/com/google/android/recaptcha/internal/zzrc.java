package com.google.android.recaptcha.internal;

import java.io.Serializable;
import kotlin.UByte;

/* loaded from: classes5.dex */
final class zzrc extends zzre implements Serializable {
    final byte[] zza;

    public zzrc(byte[] r1) {
        r1.getClass();
        this.zza = r1;
    }

    @Override // com.google.android.recaptcha.internal.zzre
    public final int zza() {
        return this.zza.length * 8;
    }

    @Override // com.google.android.recaptcha.internal.zzre
    public final byte[] zzb() {
        return (byte[]) this.zza.clone();
    }

    @Override // com.google.android.recaptcha.internal.zzre
    public final int zzc() {
        byte[] r5 = this.zza;
        int r0 = r5.length;
        if (r0 < 4) goto L5;
        boolean r1 = true;
    L6:
        zzoq.zzd(r1, "HashCode#asInt() requires >= 4 bytes (it only has %s bytes).", r0);
        int r02 = r5[0] & UByte.MAX_VALUE;
        int r12 = r5[1] & UByte.MAX_VALUE;
        int r2 = r5[2] & UByte.MAX_VALUE;
        return ((r5[3] & UByte.MAX_VALUE) << 24) | ((r02 | (r12 << 8)) | (r2 << 16));
    L5:
        r1 = false;
        goto L6
    }

    @Override // com.google.android.recaptcha.internal.zzre
    public final long zzd() {
        byte[] r8 = this.zza;
        int r0 = r8.length;
        int r2 = 1;
        if (r0 < 8) goto L5;
        boolean r4 = true;
    L6:
        zzoq.zzd(r4, "HashCode#asLong() requires >= 8 bytes (it only has %s bytes).", r0);
        long r02 = r8[0] & UByte.MAX_VALUE;
    L8:
        if (r2 >= Math.min(r8.length, 8)) goto L10;
        r02 = r02 | ((r8[r2] & 255) << (r2 * 8));
        r2 = r2 + 1;
        goto L8
    L10:
        return r02;
    L5:
        r4 = false;
        goto L6
    }

    @Override // com.google.android.recaptcha.internal.zzre
    public final byte[] zze() {
        return this.zza;
    }

    @Override // com.google.android.recaptcha.internal.zzre
    public final boolean zzf(zzre r7) {
        int r0 = r7.zze().length;
        byte[] r6 = this.zza;
        if (r6.length != r0) goto L13;
        boolean r3 = true;
        int r1 = 0;
    L6:
        if (r1 >= r6.length) goto L12;
        if (r6[r1] != r7.zze()[r1]) goto L10;
        boolean r4 = true;
    L11:
        r3 = r3 & r4;
        r1 = r1 + 1;
        goto L6
    L10:
        r4 = false;
        goto L11
    L12:
        return r3;
    L13:
        return false;
    }
}
