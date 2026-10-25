package com.google.android.recaptcha.internal;

import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;

/* loaded from: classes5.dex */
final class zzads implements zzss {
    public /* synthetic */ zzads(RSAPrivateCrtKey r1, zzado r2, zzado r3, int r4, byte[] r5, byte[] r6, byte[] r7) {
        if (zztf.zza() == true) goto L10;
        zzadx.zza(r2);
        if (r2.equals(r3) == false) goto L8;
        zzadx.zzb(r1.getModulus().bitLength());
        zzadx.zzc(r1.getPublicExponent());
        RSAPublicKey r0 = (RSAPublicKey) ((KeyFactory) zzadf.zzc.zzb("RSA")).generatePublic(new RSAPublicKeySpec(r1.getModulus(), r1.getPublicExponent()));
        return;
    L8:
        zzsj$$ExternalSyntheticBUOutline1.m("sigHash and mgf1Hash must be the same");
        throw null;
    L10:
        zzsj$$ExternalSyntheticBUOutline1.m("Can not use RSA PSS in FIPS-mode, as BoringCrypto module is not available.");
        throw null;
    }

    @Override // com.google.android.recaptcha.internal.zzss
    public final byte[] zza(byte[] r1) {
        throw null;
    }
}
