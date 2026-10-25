package com.google.android.recaptcha.internal;

import c.f$$ExternalSyntheticBUOutline1;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.spec.ECPoint;

/* loaded from: classes5.dex */
public abstract class zzabk {
    private static final zzvi zza = null;
    private static final zzvf zzb = null;
    private static final zzue zzc = null;
    private static final zzub zzd = null;
    private static final zzue zze = null;
    private static final zzub zzf = null;

    static {
        zza = zzvi.zzb(zzabj.zza, zzxx.class);
        zzb = zzvf.zzb(zzabe.zza, "type.googleapis.com/google.crypto.tink.EcdsaPrivateKey");
        zzc = zzue.zzc(zzabf.zza, zzyb.class);
        zzd = zzub.zzc(zzabg.zza, "type.googleapis.com/google.crypto.tink.EcdsaPublicKey");
        zze = zzue.zzc(zzabh.zza, zzxz.class);
        zzf = zzub.zzc(zzabi.zza, "type.googleapis.com/google.crypto.tink.EcdsaPrivateKey");
    }

    public static void zza(zzvb r1) {
        r1.zzd(zza);
        r1.zze(zzb);
        r1.zzb(zzc);
        r1.zzc(zzd);
        r1.zzb(zze);
        r1.zzc(zzf);
    }

    public static /* synthetic */ zzsp zzb(zzyb r3, zzsy r4) {
        return zzsp.zza("type.googleapis.com/google.crypto.tink.EcdsaPublicKey", zzj(r3).zzv(), zzsn.zzd, zzf(r3.zzf().zze()), r3.zzb());
    }

    public static /* synthetic */ zzyb zzc(zzsp r6, zzsy r7) {
        if (r6.zzb().equals("type.googleapis.com/google.crypto.tink.EcdsaPublicKey") == true) goto L14;
        f$$ExternalSyntheticBUOutline1.m("Wrong type URL in call to EcdsaProtoSerialization.parsePublicKey: ".concat(String.valueOf(r6.zzb())));
        return null;
    L14:
        zzwi r72 = zzwi.zze(r6.zzc(), zzafh.zzb());     // Catch: Throwable -> L10
        if (r72.zza() != 0) goto L9;
        zzxs r1 = zzxx.zza();     // Catch: Throwable -> L10
        r1.zzc(zzg(r72.zzb().zza()));     // Catch: Throwable -> L10
        r1.zza(zzl(r72.zzb().zzh()));     // Catch: Throwable -> L10
        r1.zzb(zzk(r72.zzb().zzg()));     // Catch: Throwable -> L10
        r1.zzd(zzh(r6.zze()));     // Catch: Throwable -> L10
        zzxx r12 = r1.zze();     // Catch: Throwable -> L10
        zzya r2 = zzyb.zzc();     // Catch: Throwable -> L10
        r2.zza(r12);     // Catch: Throwable -> L10
        r2.zzb(new ECPoint(new BigInteger(1, r72.zzc().zzy()), new BigInteger(1, r72.zzd().zzy())));     // Catch: Throwable -> L10
        r2.zzc(r6.zzf());     // Catch: Throwable -> L10
        return r2.zzd();
    L9:
        throw new GeneralSecurityException("Only version 0 keys are accepted");     // Catch: Throwable -> L10
    L10:
        zzsj$$ExternalSyntheticBUOutline1.m("Parsing EcdsaPublicKey failed");
        return null;
    }

    public static /* synthetic */ zzsp zzd(zzxz r3, zzsy r4) {
        int r0 = zzi(r3.zzd().zzc());
        zzwf r1 = zzwg.zze();
        r1.zza(zzj(r3.zze()));
        byte[] r42 = zzth.zzb(r3.zzf().zzb(r4), r0);
        zzaer r02 = zzaer.zza;
        r1.zzb(zzaer.zzs(r42, 0, r42.length));
        return zzsp.zza("type.googleapis.com/google.crypto.tink.EcdsaPrivateKey", ((zzwg) r1.zzp()).zzv(), zzsn.zzc, zzf(r3.zzd().zze()), r3.zze().zzb());
    }

    public static /* synthetic */ zzxz zze(zzsp r8, zzsy r9) {
        if (r8.zzb().equals("type.googleapis.com/google.crypto.tink.EcdsaPrivateKey") == true) goto L19;
        f$$ExternalSyntheticBUOutline1.m("Wrong type URL in call to EcdsaProtoSerialization.parsePrivateKey: ".concat(String.valueOf(r8.zzb())));
        return null;
    L19:
        zzwg r0 = zzwg.zzd(r8.zzc(), zzafh.zzb());     // Catch: Throwable -> L15
        if (r0.zza() != 0) goto L14;
        zzwi r2 = r0.zzb();     // Catch: Throwable -> L15 Throwable -> L15
        if (r2.zza() != 0) goto L12;
        zzxs r3 = zzxx.zza();     // Catch: Throwable -> L15 Throwable -> L15
        r3.zzc(zzg(r2.zzb().zza()));     // Catch: Throwable -> L15 Throwable -> L15
        r3.zza(zzl(r2.zzb().zzh()));     // Catch: Throwable -> L15 Throwable -> L15
        r3.zzb(zzk(r2.zzb().zzg()));     // Catch: Throwable -> L15 Throwable -> L15
        r3.zzd(zzh(r8.zze()));     // Catch: Throwable -> L15 Throwable -> L15
        zzxx r32 = r3.zze();     // Catch: Throwable -> L15 Throwable -> L15
        zzya r4 = zzyb.zzc();     // Catch: Throwable -> L15 Throwable -> L15
        r4.zza(r32);     // Catch: Throwable -> L15 Throwable -> L15
        r4.zzb(new ECPoint(new BigInteger(1, r2.zzc().zzy()), new BigInteger(1, r2.zzd().zzy())));     // Catch: Throwable -> L15 Throwable -> L15
        r4.zzc(r8.zzf());     // Catch: Throwable -> L15 Throwable -> L15
        zzyb r82 = r4.zzd();     // Catch: Throwable -> L15 Throwable -> L15
        zzxy r22 = zzxz.zzc();     // Catch: Throwable -> L15 Throwable -> L15
        r22.zza(r82);     // Catch: Throwable -> L15 Throwable -> L15
        r22.zzb(zzadz.zza(new BigInteger(1, r0.zzc().zzy()), r9));     // Catch: Throwable -> L15 Throwable -> L15
        return r22.zzc();
    L12:
        throw new GeneralSecurityException("Only version 0 keys are accepted");     // Catch: Throwable -> L15 Throwable -> L15
    L14:
        throw new GeneralSecurityException("Only version 0 keys are accepted");     // Catch: Throwable -> L15 Throwable -> L15
    L15:
        zzsj$$ExternalSyntheticBUOutline1.m("Parsing EcdsaPrivateKey failed");
        return null;
    }

    private static zzso zzf(zzxw r2) {
        if (r2.equals(zzxw.zza) == false) goto L7;
        return zzso.zzb;
    L7:
        if (r2 == zzxw.zzb) goto L19;
        if (r2 == zzxw.zzd) goto L17;
        if (r2 != zzxw.zzc) goto L15;
        return zzso.zzc;
    L15:
        throw new GeneralSecurityException("Unable to serialize variant: ".concat(r2.toString()));
    L17:
        return zzso.zzd;
    L19:
        return zzso.zze;
    }

    private static zzxu zzg(zzwp r3) {
        int r0 = r3.ordinal();
        if (r0 == 2) goto L15;
        if (r0 == 3) goto L13;
        if (r0 == 4) goto L9;
        int r32 = r3.zza();
        StringBuilder r2 = new StringBuilder(String.valueOf(r32).length() + 26);
        r2.append("Unable to parse HashType: ");
        r2.append(r32);
        throw new GeneralSecurityException(r2.toString());
    L9:
        return zzxu.zzc;
    L13:
        return zzxu.zza;
    L15:
        return zzxu.zzb;
    }

    private static zzxw zzh(zzso r2) {
        if (r2.equals(zzso.zzb) == false) goto L7;
        return zzxw.zza;
    L7:
        if (r2 == zzso.zze) goto L19;
        if (r2 == zzso.zzc) goto L17;
        if (r2 != zzso.zzd) goto L15;
        return zzxw.zzd;
    L15:
        throw new GeneralSecurityException("Unable to parse OutputPrefixType: ".concat(r2.toString()));
    L17:
        return zzxw.zzc;
    L19:
        return zzxw.zzb;
    }

    private static int zzi(zzxt r2) {
        if (r2.equals(zzxt.zza) == false) goto L7;
        return 33;
    L7:
        if (r2 != zzxt.zzb) goto L9;
        return 49;
    L9:
        if (r2 != zzxt.zzc) goto L13;
        return 67;
    L13:
        throw new GeneralSecurityException("Unable to serialize CurveType ".concat(r2.toString()));
    }

    private static zzwi zzj(zzyb r7) {
        int r0 = zzi(r7.zzf().zzc());
        ECPoint r1 = r7.zzd();
        zzwh r2 = zzwi.zzg();
        zzxx r72 = r7.zzf();
        zzwd r3 = zzwe.zzb();
        zzxu r4 = r72.zzd();
        if (r4.equals(zzxu.zza) == false) goto L6;
        zzwp r42 = zzwp.zzd;
    L13:
        r3.zza(r42);
        zzxt r43 = r72.zzc();
        int r6 = 4;
        if (r43.equals(zzxt.zza) == false) goto L17;
        int r44 = 4;
    L24:
        r3.zzb(r44);
        zzxv r73 = r72.zzb();
        if (r73.equals(zzxv.zza) == false) goto L28;
        r6 = 3;
    L29:
        r3.zzc(r6);
        r2.zza((zzwe) r3.zzp());
        byte[] r74 = zzth.zzb(r1.getAffineX(), r0);
        zzaer r32 = zzaer.zza;
        r2.zzb(zzaer.zzs(r74, 0, r74.length));
        byte[] r75 = zzth.zzb(r1.getAffineY(), r0);
        r2.zzc(zzaer.zzs(r75, 0, r75.length));
        return (zzwi) r2.zzp();
    L28:
        if (r73 == zzxv.zzb) goto L29;
        throw new GeneralSecurityException("Unable to serialize SignatureEncoding ".concat(r73.toString()));
    L17:
        if (r43 != zzxt.zzb) goto L19;
        r44 = 5;
        goto L24
    L19:
        if (r43 != zzxt.zzc) goto L22;
        r44 = 6;
        goto L24
    L22:
        throw new GeneralSecurityException("Unable to serialize CurveType ".concat(r43.toString()));
    L6:
        if (r4 != zzxu.zzb) goto L8;
        r42 = zzwp.zzc;
        goto L13
    L8:
        if (r4 != zzxu.zzc) goto L11;
        r42 = zzwp.zze;
        goto L13
    L11:
        throw new GeneralSecurityException("Unable to serialize HashType ".concat(r4.toString()));
    }

    private static zzxt zzk(int r3) {
        int r0 = r3 - 2;
        if (r0 == 2) goto L15;
        if (r0 == 3) goto L13;
        if (r0 == 4) goto L9;
        int r32 = zzwo.zza(r3);
        StringBuilder r2 = new StringBuilder(String.valueOf(r32).length() + 35);
        r2.append("Unable to parse EllipticCurveType: ");
        r2.append(r32);
        throw new GeneralSecurityException(r2.toString());
    L9:
        return zzxt.zzc;
    L13:
        return zzxt.zzb;
    L15:
        return zzxt.zza;
    }

    private static zzxv zzl(int r3) {
        int r0 = r3 - 2;
        if (r0 == 1) goto L11;
        if (r0 == 2) goto L7;
        int r32 = zzwj.zza(r3);
        StringBuilder r2 = new StringBuilder(String.valueOf(r32).length() + 40);
        r2.append("Unable to parse EcdsaSignatureEncoding: ");
        r2.append(r32);
        throw new GeneralSecurityException(r2.toString());
    L7:
        return zzxv.zzb;
    L11:
        return zzxv.zza;
    }
}
