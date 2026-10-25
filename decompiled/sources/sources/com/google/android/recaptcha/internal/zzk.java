package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public final class zzk implements zzj {
    public zzk() {
    }

    @Override // com.google.android.recaptcha.internal.zzj
    public final byte zza(zzv r1, int r2) {
        return r1.zzb(r2);
    }

    @Override // com.google.android.recaptcha.internal.zzj
    public final zzv zzb(zzv r1, int r2, int r3) {
        if (r2 < 0) goto L11;
        if (r2 > r3) goto L11;
        byte[] r0 = r1.zza;
        int r12 = r0.length;
        if (r3 > r12) goto L11;
        if (r2 > r3) goto L11;
        if (r3 > r12) goto L11;
        return new zzv(zzv.zzh(r0, r2, r3 - r2));
    L11:
        throw new IndexOutOfBoundsException();
    }

    @Override // com.google.android.recaptcha.internal.zzj
    public final zzj zzc() {
        return new zzk();
    }
}
