package com.google.android.recaptcha.internal;

import com.google.protobuf.FieldSet$$ExternalSyntheticBUOutline0;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;

/* loaded from: classes5.dex */
public final class zzabv implements zzst {
    private static final byte[] zza = null;
    private final PublicKey zzb;
    private final byte[] zzc;
    private final byte[] zzd;
    private final Provider zze;

    static {
        zza = new byte[]{48, 42, 48, 5, 6, 3, 43, 101, 112, 3, 33, 0};
    }

    private zzabv(byte[] r5, byte[] r6, byte[] r7, Provider r8) {
        if (zzte.zza(1) == true) goto L5;
        zzsj$$ExternalSyntheticBUOutline1.m("Can not use Ed25519 in FIPS-mode.");
        throw null;
    L5:
        if (r5.length != 32) goto L8;
        X509EncodedKeySpec r2 = new X509EncodedKeySpec(zzacu.zza(new byte[][]{zza, r5}));
        this.zzb = KeyFactory.getInstance("Ed25519", r8).generatePublic(r2);
        this.zzc = r6;
        this.zzd = r7;
        this.zze = r8;
        return;
    L8:
        FieldSet$$ExternalSyntheticBUOutline0.m("Given public key's length is not %s.", new Object[]{32});
        throw null;
    }

    public static zzst zzb(zzyo r6) {
        Provider r0 = zzti.zza();
        if (r0 == null) goto L15;
        if (zzte.zza(1) == false) goto L12;
        byte[] r3 = r6.zzd().zzc();
        byte[] r4 = r6.zze().zzc();
        if (r6.zzf().zzb().equals(zzyg.zzc) == false) goto L9;
        byte[] r62 = {0};
    L11:
        return new zzabv(r3, r4, r62, r0);
    L9:
        r62 = new byte[0];
        goto L11
    L12:
        zzsj$$ExternalSyntheticBUOutline1.m("Can not use Ed25519 in FIPS-mode.");
        return null;
    L15:
        throw new NoSuchProviderException("Ed25519VerifyJce requires the Conscrypt provider.");
    }

    @Override // com.google.android.recaptcha.internal.zzst
    public final void zza(byte[] r6, byte[] r7) {
        byte[] r0 = this.zzc;
        int r1 = r0.length;
        if (r6.length != (r1 + 64)) goto L15;
        if (zzwc.zzc(r0, r6) == false) goto L12;
        Provider r02 = this.zze;
        PublicKey r2 = this.zzb;
        Signature r03 = Signature.getInstance("Ed25519", r02);
        r03.initVerify(r2);
        r03.update(r7);
        r03.update(this.zzd);
        if (r03.verify(r6, r1, 64) == false) goto L10;
        return;
    L10:
        zzsj$$ExternalSyntheticBUOutline1.m("Signature check failed.");
        return;
    L12:
        zzsj$$ExternalSyntheticBUOutline1.m("Invalid signature (output prefix mismatch)");
        return;
    L15:
        throw new GeneralSecurityException(String.format("Invalid signature length: %s", new Object[]{64}));
    }
}
