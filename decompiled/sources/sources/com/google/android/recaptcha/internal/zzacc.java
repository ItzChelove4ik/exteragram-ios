package com.google.android.recaptcha.internal;

import c.f$$ExternalSyntheticBUOutline1;
import java.math.BigInteger;
import java.security.GeneralSecurityException;

/* loaded from: classes5.dex */
public abstract class zzacc {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final zzvi zzb = null;
    private static final zzvf zzc = null;
    private static final zzue zzd = null;
    private static final zzub zze = null;
    private static final zzue zzf = null;
    private static final zzub zzg = null;
    private static final zztv zzh = null;

    static {
        zzb = zzvi.zzb(zzacb.zza, zzzv.class);
        zzc = zzvf.zzb(zzabw.zza, "type.googleapis.com/google.crypto.tink.RsaSsaPkcs1PrivateKey");
        zzd = zzue.zzc(zzabx.zza, zzzz.class);
        zze = zzub.zzc(zzaby.zza, "type.googleapis.com/google.crypto.tink.RsaSsaPkcs1PublicKey");
        zzf = zzue.zzc(zzabz.zza, zzzx.class);
        zzg = zzub.zzc(zzaca.zza, "type.googleapis.com/google.crypto.tink.RsaSsaPkcs1PrivateKey");
        zztu r0 = zztv.zza();
        r0.zza(zzwp.zzd, zzzt.zza);
        r0.zza(zzwp.zzc, zzzt.zzb);
        r0.zza(zzwp.zze, zzzt.zzc);
        zzh = r0.zzb();
    }

    public static void zza(zzvb r1) {
        r1.zzd(zzb);
        r1.zze(zzc);
        r1.zzb(zzd);
        r1.zzc(zze);
        r1.zzb(zzf);
        r1.zzc(zzg);
    }

    public static /* synthetic */ zzsp zzb(zzzz r3, zzsy r4) {
        return zzsp.zza("type.googleapis.com/google.crypto.tink.RsaSsaPkcs1PublicKey", zzh(r3).zzv(), zzsn.zzd, zzf(r3.zzf().zzd()), r3.zzb());
    }

    public static /* synthetic */ zzzz zzc(zzsp r7, zzsy r8) {
        if (r7.zzb().equals("type.googleapis.com/google.crypto.tink.RsaSsaPkcs1PublicKey") == true) goto L14;
        f$$ExternalSyntheticBUOutline1.m("Wrong type URL in call to RsaSsaPkcs1ProtoSerialization.parsePublicKey: ".concat(String.valueOf(r7.zzb())));
        return null;
    L14:
        zzxl r82 = zzxl.zze(r7.zzc(), zzafh.zzb());     // Catch: Throwable -> L10
        if (r82.zza() != 0) goto L9;
        BigInteger r2 = new BigInteger(1, r82.zzc().zzy());     // Catch: Throwable -> L10
        int r1 = r2.bitLength();     // Catch: Throwable -> L10
        zzzs r4 = zzzv.zza();     // Catch: Throwable -> L10
        r4.zzd((zzzt) zzh.zzc(r82.zzb().zza()));     // Catch: Throwable -> L10
        r4.zzb(new BigInteger(1, r82.zzd().zzy()));     // Catch: Throwable -> L10
        r4.zza(r1);     // Catch: Throwable -> L10
        r4.zzc(zzg(r7.zze()));     // Catch: Throwable -> L10
        zzzv r83 = r4.zze();     // Catch: Throwable -> L10
        zzzy r12 = zzzz.zzc();     // Catch: Throwable -> L10
        r12.zza(r83);     // Catch: Throwable -> L10
        r12.zzb(r2);     // Catch: Throwable -> L10
        r12.zzc(r7.zzf());     // Catch: Throwable -> L10
        return r12.zzd();
    L9:
        throw new GeneralSecurityException("Only version 0 keys are accepted");     // Catch: Throwable -> L10
    L10:
        zzsj$$ExternalSyntheticBUOutline1.m("Parsing RsaSsaPkcs1PublicKey failed");
        return null;
    }

    public static /* synthetic */ zzsp zzd(zzzx r4, zzsy r5) {
        zzxi r0 = zzxj.zzk();
        r0.zza(0);
        r0.zzb(zzh(r4.zze()));
        byte[] r2 = zzth.zza(r4.zzi().zzb(r5));
        zzaer r3 = zzaer.zza;
        r0.zzc(zzaer.zzs(r2, 0, r2.length));
        byte[] r22 = zzth.zza(r4.zzf().zzb(r5));
        r0.zzd(zzaer.zzs(r22, 0, r22.length));
        byte[] r23 = zzth.zza(r4.zzh().zzb(r5));
        r0.zze(zzaer.zzs(r23, 0, r23.length));
        byte[] r24 = zzth.zza(r4.zzj().zzb(r5));
        r0.zzf(zzaer.zzs(r24, 0, r24.length));
        byte[] r25 = zzth.zza(r4.zzk().zzb(r5));
        r0.zzg(zzaer.zzs(r25, 0, r25.length));
        byte[] r52 = zzth.zza(r4.zzl().zzb(r5));
        r0.zzh(zzaer.zzs(r52, 0, r52.length));
        return zzsp.zza("type.googleapis.com/google.crypto.tink.RsaSsaPkcs1PrivateKey", ((zzxj) r0.zzp()).zzv(), zzsn.zzc, zzf(r4.zzd().zzd()), r4.zze().zzb());
    }

    public static /* synthetic */ zzzx zze(zzsp r8, zzsy r9) {
        if (r8.zzb().equals("type.googleapis.com/google.crypto.tink.RsaSsaPkcs1PrivateKey") == true) goto L19;
        f$$ExternalSyntheticBUOutline1.m("Wrong type URL in call to RsaSsaPkcs1ProtoSerialization.parsePrivateKey: ".concat(String.valueOf(r8.zzb())));
        return null;
    L19:
        zzxj r0 = zzxj.zzj(r8.zzc(), zzafh.zzb());     // Catch: Throwable -> L15
        if (r0.zza() != 0) goto L14;
        zzxl r2 = r0.zzb();     // Catch: Throwable -> L15 Throwable -> L15
        if (r2.zza() != 0) goto L12;
        BigInteger r4 = new BigInteger(1, r2.zzc().zzy());     // Catch: Throwable -> L15 Throwable -> L15
        int r3 = r4.bitLength();     // Catch: Throwable -> L15 Throwable -> L15
        BigInteger r7 = new BigInteger(1, r2.zzd().zzy());     // Catch: Throwable -> L15 Throwable -> L15
        zzzs r5 = zzzv.zza();     // Catch: Throwable -> L15 Throwable -> L15
        r5.zzd((zzzt) zzh.zzc(r2.zzb().zza()));     // Catch: Throwable -> L15 Throwable -> L15
        r5.zzb(r7);     // Catch: Throwable -> L15 Throwable -> L15
        r5.zza(r3);     // Catch: Throwable -> L15 Throwable -> L15
        r5.zzc(zzg(r8.zze()));     // Catch: Throwable -> L15 Throwable -> L15
        zzzv r22 = r5.zze();     // Catch: Throwable -> L15 Throwable -> L15
        zzzy r32 = zzzz.zzc();     // Catch: Throwable -> L15 Throwable -> L15
        r32.zza(r22);     // Catch: Throwable -> L15 Throwable -> L15
        r32.zzb(r4);     // Catch: Throwable -> L15 Throwable -> L15
        r32.zzc(r8.zzf());     // Catch: Throwable -> L15 Throwable -> L15
        zzzz r82 = r32.zzd();     // Catch: Throwable -> L15 Throwable -> L15
        zzzw r23 = zzzx.zzc();     // Catch: Throwable -> L15 Throwable -> L15
        r23.zza(r82);     // Catch: Throwable -> L15 Throwable -> L15
        r23.zzb(zzi(r0.zzd(), r9), zzi(r0.zze(), r9));     // Catch: Throwable -> L15 Throwable -> L15
        r23.zzc(zzi(r0.zzc(), r9));     // Catch: Throwable -> L15 Throwable -> L15
        r23.zzd(zzi(r0.zzg(), r9), zzi(r0.zzh(), r9));     // Catch: Throwable -> L15 Throwable -> L15
        r23.zze(zzi(r0.zzi(), r9));     // Catch: Throwable -> L15 Throwable -> L15
        return r23.zzf();
    L12:
        throw new GeneralSecurityException("Only version 0 keys are accepted");     // Catch: Throwable -> L15 Throwable -> L15
    L14:
        throw new GeneralSecurityException("Only version 0 keys are accepted");     // Catch: Throwable -> L15 Throwable -> L15
    L15:
        zzsj$$ExternalSyntheticBUOutline1.m("Parsing RsaSsaPkcs1PrivateKey failed");
        return null;
    }

    private static zzso zzf(zzzu r2) {
        if (r2.equals(zzzu.zzd) == false) goto L7;
        return zzso.zzd;
    L7:
        if (r2 == zzzu.zza) goto L19;
        if (r2 == zzzu.zzb) goto L17;
        if (r2 != zzzu.zzc) goto L15;
        return zzso.zzc;
    L15:
        throw new GeneralSecurityException("Unable to serialize variant: ".concat(String.valueOf(r2)));
    L17:
        return zzso.zze;
    L19:
        return zzso.zzb;
    }

    private static zzzu zzg(zzso r2) {
        if (r2 != zzso.zzd) goto L7;
        return zzzu.zzd;
    L7:
        if (r2 != zzso.zzb) goto L11;
        return zzzu.zza;
    L11:
        if (r2 != zzso.zze) goto L15;
        return zzzu.zzb;
    L15:
        if (r2 != zzso.zzc) goto L19;
        return zzzu.zzc;
    L19:
        throw new GeneralSecurityException("Unable to parse OutputPrefixType: ".concat(r2.toString()));
    }

    private static zzxl zzh(zzzz r4) {
        zzxk r0 = zzxl.zzg();
        zzzv r1 = r4.zzf();
        zzxg r2 = zzxh.zzb();
        r2.zza((zzwp) zzh.zzb(r1.zze()));
        r0.zza((zzxh) r2.zzp());
        byte[] r12 = zzth.zza(r4.zzd());
        zzaer r22 = zzaer.zza;
        r0.zzb(zzaer.zzs(r12, 0, r12.length));
        byte[] r42 = zzth.zza(r4.zzf().zzc());
        r0.zzc(zzaer.zzs(r42, 0, r42.length));
        return (zzxl) r0.zzp();
    }

    private static zzadz zzi(zzaer r2, zzsy r3) {
        return zzadz.zza(new BigInteger(1, r2.zzy()), r3);
    }
}
