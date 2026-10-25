package com.google.android.recaptcha.internal;

import java.security.MessageDigest;
import java.util.Arrays;

/* loaded from: classes5.dex */
final class zzro extends zzqw {
    private final MessageDigest zza;
    private final int zzb;
    private boolean zzc;

    public /* synthetic */ zzro(MessageDigest r1, int r2, byte[] r3) {
        this.zza = r1;
        this.zzb = r2;
    }

    private final void zze() {
        zzoq.zzc(!this.zzc, "Cannot re-use a Hasher after calling hash() on it");
    }

    @Override // com.google.android.recaptcha.internal.zzqw
    public final void zza(byte[] r1, int r2, int r3) {
        zze();
        this.zza.update(r1, 0, r3);
    }

    @Override // com.google.android.recaptcha.internal.zzrg
    public final zzre zzf() {
        zze();
        this.zzc = true;
        int r0 = this.zzb;
        MessageDigest r2 = this.zza;
        if (r0 != r2.getDigestLength()) goto L6;
        byte[] r22 = r2.digest();
        int r02 = zzre.$r8$clinit;
        return new zzrc(r22);
    L6:
        byte[] r23 = Arrays.copyOf(r2.digest(), r0);
        int r03 = zzre.$r8$clinit;
        return new zzrc(r23);
    }
}
