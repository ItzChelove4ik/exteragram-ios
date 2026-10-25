package com.google.android.recaptcha.internal;

import java.nio.charset.Charset;

/* loaded from: classes5.dex */
abstract class zzqy implements zzrg {
    public zzqy() {
    }

    public zzrg zzb(byte[] r3) {
        zzc(r3, 0, r3.length);
        return this;
    }

    public abstract zzrg zzc(byte[] r1, int r2, int r3);

    @Override // com.google.android.recaptcha.internal.zzrg
    public final zzrg zzd(CharSequence r1, Charset r2) {
        zzb(r1.toString().getBytes(r2));
        return this;
    }
}
