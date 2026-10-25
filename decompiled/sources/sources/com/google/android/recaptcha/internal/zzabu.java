package com.google.android.recaptcha.internal;

import com.google.protobuf.FieldSet$$ExternalSyntheticBUOutline0;
import java.security.KeyFactory;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.spec.PKCS8EncodedKeySpec;

/* loaded from: classes5.dex */
public final class zzabu implements zzss {
    private static final byte[] zza = null;

    static {
        zza = new byte[]{48, 46, 2, 1, 0, 48, 5, 6, 3, 43, 101, 112, 4, 34, 4, 32};
    }

    private zzabu(byte[] r2, byte[] r3, byte[] r4, Provider r5) {
        if (zzte.zza(1) == true) goto L5;
        zzsj$$ExternalSyntheticBUOutline1.m("Can not use Ed25519 in FIPS-mode.");
        throw null;
    L5:
        if (r2.length != 32) goto L8;
        PKCS8EncodedKeySpec r42 = new PKCS8EncodedKeySpec(zzacu.zza(new byte[][]{zza, r2}));
        KeyFactory.getInstance("Ed25519", r5).generatePrivate(r42);
        return;
    L8:
        FieldSet$$ExternalSyntheticBUOutline0.m("Given private key's length is not %s", new Object[]{32});
        throw null;
    }

    public static zzss zzb(zzyi r5) {
        Provider r0 = zzti.zza();
        if (r0 == null) goto L11;
        byte[] r2 = r5.zzf().zzb(zzsd.zza());
        byte[] r3 = r5.zze().zze().zzc();
        if (r5.zzd().zzb().equals(zzyg.zzc) == false) goto L7;
        byte[] r52 = {0};
    L9:
        return new zzabu(r2, r3, r52, r0);
    L7:
        r52 = new byte[0];
        goto L9
    L11:
        throw new NoSuchProviderException("Ed25519SignJce requires the Conscrypt provider.");
    }

    @Override // com.google.android.recaptcha.internal.zzss
    public final byte[] zza(byte[] r1) {
        throw null;
    }
}
