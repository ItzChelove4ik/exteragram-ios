package com.google.android.recaptcha.internal;

import c.f$$ExternalSyntheticBUOutline1;
import java.math.BigInteger;
import java.security.GeneralSecurityException;

/* loaded from: classes5.dex */
public abstract class zzacl {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final zzvi zzb = null;
    private static final zzvf zzc = null;
    private static final zzue zzd = null;
    private static final zzub zze = null;
    private static final zzue zzf = null;
    private static final zzub zzg = null;
    private static final zztv zzh = null;

    static {
        zzb = zzvi.zzb(zzack.zza, zzaah.class);
        zzc = zzvf.zzb(zzacf.zza, "type.googleapis.com/google.crypto.tink.RsaSsaPssPrivateKey");
        zzd = zzue.zzc(zzacg.zza, zzaal.class);
        zze = zzub.zzc(zzach.zza, "type.googleapis.com/google.crypto.tink.RsaSsaPssPublicKey");
        zzf = zzue.zzc(zzaci.zza, zzaaj.class);
        zzg = zzub.zzc(zzacj.zza, "type.googleapis.com/google.crypto.tink.RsaSsaPssPrivateKey");
        zztu r0 = zztv.zza();
        r0.zza(zzwp.zzd, zzaaf.zza);
        r0.zza(zzwp.zzc, zzaaf.zzb);
        r0.zza(zzwp.zze, zzaaf.zzc);
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

    public static /* synthetic */ zzsp zzb(zzaal r3, zzsy r4) {
        return zzsp.zza("type.googleapis.com/google.crypto.tink.RsaSsaPssPublicKey", zzh(r3).zzv(), zzsn.zzd, zzf(r3.zzf().zzd()), r3.zzb());
    }

    public static /* synthetic */ zzaal zzc(zzsp r7, zzsy r8) {
        if (r7.zzb().equals("type.googleapis.com/google.crypto.tink.RsaSsaPssPublicKey") == true) goto L14;
        f$$ExternalSyntheticBUOutline1.m("Wrong type URL in call to RsaSsaPssProtoSerialization.parsePublicKey: ".concat(String.valueOf(r7.zzb())));
        return null;
    L14:
        zzxr r82 = zzxr.zze(r7.zzc(), zzafh.zzb());     // Catch: Throwable -> L10
        if (r82.zza() != 0) goto L9;
        BigInteger r2 = new BigInteger(1, r82.zzc().zzy());     // Catch: Throwable -> L10
        int r1 = r2.bitLength();     // Catch: Throwable -> L10
        zzaae r4 = zzaah.zza();     // Catch: Throwable -> L10
        zztv r5 = zzh;     // Catch: Throwable -> L10
        r4.zzd((zzaaf) r5.zzc(r82.zzb().zza()));     // Catch: Throwable -> L10
        r4.zze((zzaaf) r5.zzc(r82.zzb().zzb()));     // Catch: Throwable -> L10
        r4.zzb(new BigInteger(1, r82.zzd().zzy()));     // Catch: Throwable -> L10
        r4.zza(r1);     // Catch: Throwable -> L10
        r4.zzf(r82.zzb().zzc());     // Catch: Throwable -> L10
        r4.zzc(zzg(r7.zze()));     // Catch: Throwable -> L10
        zzaah r83 = r4.zzg();     // Catch: Throwable -> L10
        zzaak r12 = zzaal.zzc();     // Catch: Throwable -> L10
        r12.zza(r83);     // Catch: Throwable -> L10
        r12.zzb(r2);     // Catch: Throwable -> L10
        r12.zzc(r7.zzf());     // Catch: Throwable -> L10
        return r12.zzd();
    L9:
        throw new GeneralSecurityException("Only version 0 keys are accepted");     // Catch: Throwable -> L10
    L10:
        zzsj$$ExternalSyntheticBUOutline1.m("Parsing RsaSsaPssPublicKey failed");
        return null;
    }

    public static /* synthetic */ zzsp zzd(zzaaj r4, zzsy r5) {
        zzxo r0 = zzxp.zzk();
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
        return zzsp.zza("type.googleapis.com/google.crypto.tink.RsaSsaPssPrivateKey", ((zzxp) r0.zzp()).zzv(), zzsn.zzc, zzf(r4.zzd().zzd()), r4.zze().zzb());
    }

    public static /* synthetic */ zzaaj zze(zzsp r9, zzsy r10) {
        if (r9.zzb().equals("type.googleapis.com/google.crypto.tink.RsaSsaPssPrivateKey") == true) goto L19;
        f$$ExternalSyntheticBUOutline1.m("Wrong type URL in call to RsaSsaPssProtoSerialization.parsePrivateKey: ".concat(String.valueOf(r9.zzb())));
        return null;
    L19:
        zzxp r0 = zzxp.zzj(r9.zzc(), zzafh.zzb());     // Catch: Throwable -> L15
        if (r0.zza() != 0) goto L14;
        zzxr r2 = r0.zzb();     // Catch: Throwable -> L15 Throwable -> L15
        if (r2.zza() != 0) goto L12;
        BigInteger r4 = new BigInteger(1, r2.zzc().zzy());     // Catch: Throwable -> L15 Throwable -> L15
        int r3 = r4.bitLength();     // Catch: Throwable -> L15 Throwable -> L15
        BigInteger r7 = new BigInteger(1, r2.zzd().zzy());     // Catch: Throwable -> L15 Throwable -> L15
        zzaae r5 = zzaah.zza();     // Catch: Throwable -> L15 Throwable -> L15
        zztv r6 = zzh;     // Catch: Throwable -> L15 Throwable -> L15
        r5.zzd((zzaaf) r6.zzc(r2.zzb().zza()));     // Catch: Throwable -> L15 Throwable -> L15
        r5.zze((zzaaf) r6.zzc(r2.zzb().zzb()));     // Catch: Throwable -> L15 Throwable -> L15
        r5.zzb(r7);     // Catch: Throwable -> L15 Throwable -> L15
        r5.zza(r3);     // Catch: Throwable -> L15 Throwable -> L15
        r5.zzf(r2.zzb().zzc());     // Catch: Throwable -> L15 Throwable -> L15
        r5.zzc(zzg(r9.zze()));     // Catch: Throwable -> L15 Throwable -> L15
        zzaah r22 = r5.zzg();     // Catch: Throwable -> L15 Throwable -> L15
        zzaak r32 = zzaal.zzc();     // Catch: Throwable -> L15 Throwable -> L15
        r32.zza(r22);     // Catch: Throwable -> L15 Throwable -> L15
        r32.zzb(r4);     // Catch: Throwable -> L15 Throwable -> L15
        r32.zzc(r9.zzf());     // Catch: Throwable -> L15 Throwable -> L15
        zzaal r92 = r32.zzd();     // Catch: Throwable -> L15 Throwable -> L15
        zzaai r23 = zzaaj.zzc();     // Catch: Throwable -> L15 Throwable -> L15
        r23.zza(r92);     // Catch: Throwable -> L15 Throwable -> L15
        r23.zzb(zzi(r0.zzd(), r10), zzi(r0.zze(), r10));     // Catch: Throwable -> L15 Throwable -> L15
        r23.zzc(zzi(r0.zzc(), r10));     // Catch: Throwable -> L15 Throwable -> L15
        r23.zzd(zzi(r0.zzg(), r10), zzi(r0.zzh(), r10));     // Catch: Throwable -> L15 Throwable -> L15
        r23.zze(zzi(r0.zzi(), r10));     // Catch: Throwable -> L15 Throwable -> L15
        return r23.zzf();
    L12:
        throw new GeneralSecurityException("Only version 0 keys are accepted");     // Catch: Throwable -> L15 Throwable -> L15
    L14:
        throw new GeneralSecurityException("Only version 0 keys are accepted");     // Catch: Throwable -> L15 Throwable -> L15
    L15:
        zzsj$$ExternalSyntheticBUOutline1.m("Parsing RsaSsaPssPrivateKey failed");
        return null;
    }

    private static zzso zzf(zzaag r2) {
        if (r2.equals(zzaag.zzd) == false) goto L7;
        return zzso.zzd;
    L7:
        if (r2 == zzaag.zza) goto L19;
        if (r2 == zzaag.zzb) goto L17;
        if (r2 != zzaag.zzc) goto L15;
        return zzso.zzc;
    L15:
        throw new GeneralSecurityException("Unable to serialize variant: ".concat(String.valueOf(r2)));
    L17:
        return zzso.zze;
    L19:
        return zzso.zzb;
    }

    private static zzaag zzg(zzso r2) {
        if (r2 != zzso.zzd) goto L7;
        return zzaag.zzd;
    L7:
        if (r2 != zzso.zzb) goto L11;
        return zzaag.zza;
    L11:
        if (r2 != zzso.zze) goto L15;
        return zzaag.zzb;
    L15:
        if (r2 != zzso.zzc) goto L19;
        return zzaag.zzc;
    L19:
        throw new GeneralSecurityException("Unable to parse OutputPrefixType: ".concat(r2.toString()));
    }

    private static zzxr zzh(zzaal r5) {
        zzxq r0 = zzxr.zzg();
        zzaah r1 = r5.zzf();
        zzxm r2 = zzxn.zzd();
        zztv r3 = zzh;
        r2.zza((zzwp) r3.zzb(r1.zze()));
        r2.zzb((zzwp) r3.zzb(r1.zzf()));
        r2.zzc(r1.zzg());
        r0.zzb((zzxn) r2.zzp());
        byte[] r12 = zzth.zza(r5.zzd());
        zzaer r22 = zzaer.zza;
        r0.zzc(zzaer.zzs(r12, 0, r12.length));
        byte[] r52 = zzth.zza(r5.zzf().zzc());
        r0.zzd(zzaer.zzs(r52, 0, r52.length));
        r0.zza(0);
        return (zzxr) r0.zzp();
    }

    private static zzadz zzi(zzaer r2, zzsy r3) {
        return zzadz.zza(new BigInteger(1, r2.zzy()), r3);
    }
}
