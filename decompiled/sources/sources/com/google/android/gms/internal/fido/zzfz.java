package com.google.android.gms.internal.fido;

import java.security.MessageDigest;
import java.util.Arrays;

/* loaded from: classes4.dex */
final class zzfz extends zzfp {
    private final MessageDigest zza;
    private final int zzb;
    private boolean zzc;

    public /* synthetic */ zzfz(MessageDigest r1, int r2, zzfy r3) {
        this.zza = r1;
        this.zzb = r2;
    }

    private final void zzd() {
        zzbm.zzf(!this.zzc, "Cannot re-use a Hasher after calling hash() on it");
    }

    @Override // com.google.android.gms.internal.fido.zzfp
    public final void zzb(byte[] r1, int r2, int r3) {
        zzd();
        this.zza.update(r1, 0, r3);
    }

    @Override // com.google.android.gms.internal.fido.zzfv
    public final zzft zzc() {
        zzd();
        this.zzc = true;
        int r0 = this.zzb;
        int r1 = this.zza.getDigestLength();
        MessageDigest r2 = this.zza;
        if (r0 != r1) goto L6;
        byte[] r22 = r2.digest();
        int r02 = zzft.$r8$clinit;
        return new zzfs(r22);
    L6:
        byte[] r23 = Arrays.copyOf(r2.digest(), r0);
        int r03 = zzft.$r8$clinit;
        return new zzfs(r23);
    }
}
