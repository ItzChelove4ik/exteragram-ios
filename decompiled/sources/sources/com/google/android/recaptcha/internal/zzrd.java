package com.google.android.recaptcha.internal;

import java.io.Serializable;

/* loaded from: classes5.dex */
final class zzrd extends zzre implements Serializable {
    final long zza;

    public zzrd(long r1) {
        this.zza = r1;
    }

    @Override // com.google.android.recaptcha.internal.zzre
    public final int zza() {
        return 64;
    }

    @Override // com.google.android.recaptcha.internal.zzre
    public final byte[] zzb() {
        return new byte[]{(byte) this.zza, (byte) (r0 >> 8), (byte) (r0 >> 16), (byte) (r0 >> 24), (byte) (r0 >> 32), (byte) (r0 >> 40), (byte) (r0 >> 48), (byte) (r0 >> 56)};
    }

    @Override // com.google.android.recaptcha.internal.zzre
    public final int zzc() {
        return (int) this.zza;
    }

    @Override // com.google.android.recaptcha.internal.zzre
    public final long zzd() {
        return this.zza;
    }

    @Override // com.google.android.recaptcha.internal.zzre
    public final boolean zzf(zzre r3) {
        if (this.zza != r3.zzd()) goto L6;
        return true;
    L6:
        return false;
    }
}
