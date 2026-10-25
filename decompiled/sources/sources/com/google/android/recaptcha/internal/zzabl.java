package com.google.android.recaptcha.internal;

import java.security.KeyFactory;
import java.security.Provider;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPrivateKeySpec;

/* loaded from: classes5.dex */
public final class zzabl implements zzss {
    private static final byte[] zza = null;
    private static final byte[] zzb = null;

    static {
        zza = new byte[0];
        zzb = new byte[]{0};
    }

    private zzabl(ECPrivateKey r1, zzado r2, zzacz r3, byte[] r4, byte[] r5, Provider r6) {
        if (zzte.zza(2) == false) goto L6;
        zzadw.zza(r2);
        return;
    L6:
        zzsj$$ExternalSyntheticBUOutline1.m("Can not use ECDSA in FIPS-mode, as BoringCrypto is not available.");
        throw null;
    }

    public static zzss zzb(zzxz r7) {
        Provider r6 = zzti.zza();
        zzado r2 = (zzado) zzabm.zza.zzb(r7.zzd().zzd());
        zzacz r3 = (zzacz) zzabm.zzb.zzb(r7.zzd().zzb());
        ECParameterSpec r0 = zzada.zzb((zzacy) zzabm.zzc.zzb(r7.zzd().zzc()));
        ECPrivateKeySpec r1 = new ECPrivateKeySpec(r7.zzf().zzb(zzsd.zza()), r0);
        if (r6 == null) goto L5;
        KeyFactory r02 = KeyFactory.getInstance("EC", r6);
    L6:
        ECPrivateKey r12 = (ECPrivateKey) r02.generatePrivate(r1);
        byte[] r4 = r7.zze().zze().zzc();
        if (r7.zzd().zze().equals(zzxw.zzc) == false) goto L10;
        byte[] r72 = zzb;
    L12:
        return new zzabl(r12, r2, r3, r4, r72, r6);
    L10:
        r72 = zza;
        goto L12
    L5:
        r02 = (KeyFactory) zzadf.zzc.zzb("EC");
        goto L6
    }

    @Override // com.google.android.recaptcha.internal.zzss
    public final byte[] zza(byte[] r1) {
        throw null;
    }
}
