package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
final class zzsw implements zzsq {
    public /* synthetic */ zzsw(byte[] r1) {
    }

    @Override // com.google.android.recaptcha.internal.zzsq
    public final zzse zza(zzsp r2, zzsy r3) {
        zzvb r1 = zzvb.zza();
        if (r1.zzf(r2) == true) goto L7;
        return new zzui(r2, r3);
    L7:
        return r1.zzg(r2, r3);
    }

    @Override // com.google.android.recaptcha.internal.zzsq
    public final zzsp zzb(zzse r1, zzsy r2) {
        return zzvb.zza().zzh(r1, r2);
    }
}
