package com.google.android.recaptcha.internal;

import java.nio.charset.Charset;

/* loaded from: classes5.dex */
abstract class zzqx implements zzrf {
    public zzqx() {
    }

    @Override // com.google.android.recaptcha.internal.zzrf
    public zzre zza(CharSequence r1, Charset r2) {
        zzrg r0 = zzc();
        r0.zzd(r1, r2);
        return r0.zzf();
    }

    public zzre zzb(byte[] r1, int r2, int r3) {
        throw null;
    }
}
