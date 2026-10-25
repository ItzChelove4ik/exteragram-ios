package com.google.android.recaptcha.internal;

import java.nio.charset.Charset;

/* loaded from: classes5.dex */
abstract class zzra extends zzqx {
    public zzra() {
    }

    @Override // com.google.android.recaptcha.internal.zzqx, com.google.android.recaptcha.internal.zzrf
    public final zzre zza(CharSequence r2, Charset r3) {
        byte[] r22 = r2.toString().getBytes(r3);
        return zzb(r22, 0, r22.length);
    }

    @Override // com.google.android.recaptcha.internal.zzrf
    public final zzrg zzc() {
        return new zzqz(this, 32);
    }
}
