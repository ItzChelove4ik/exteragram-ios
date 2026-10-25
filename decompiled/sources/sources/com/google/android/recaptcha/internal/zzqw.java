package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
abstract class zzqw extends zzqy {
    public zzqw() {
    }

    public abstract void zza(byte[] r1, int r2, int r3);

    @Override // com.google.android.recaptcha.internal.zzqy
    public final zzrg zzb(byte[] r3) {
        r3.getClass();
        zza(r3, 0, r3.length);
        return this;
    }

    @Override // com.google.android.recaptcha.internal.zzqy
    public final zzrg zzc(byte[] r2, int r3, int r4) {
        zzoq.zzg(0, r4, r2.length);
        zza(r2, 0, r4);
        return this;
    }
}
