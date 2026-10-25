package com.google.android.recaptcha.internal;

import c.f$$ExternalSyntheticBUOutline1;
import java.security.GeneralSecurityException;

/* loaded from: classes5.dex */
public abstract class zzabt {
    private static final zzvi zza = null;
    private static final zzvf zzb = null;
    private static final zzue zzc = null;
    private static final zzub zzd = null;
    private static final zzue zze = null;
    private static final zzub zzf = null;

    static {
        zza = zzvi.zzb(zzabs.zza, zzyh.class);
        zzb = zzvf.zzb(zzabn.zza, "type.googleapis.com/google.crypto.tink.Ed25519PrivateKey");
        zzc = zzue.zzc(zzabo.zza, zzyo.class);
        zzd = zzub.zzc(zzabp.zza, "type.googleapis.com/google.crypto.tink.Ed25519PublicKey");
        zze = zzue.zzc(zzabq.zza, zzyi.class);
        zzf = zzub.zzc(zzabr.zza, "type.googleapis.com/google.crypto.tink.Ed25519PrivateKey");
    }

    public static void zza(zzvb r1) {
        r1.zzd(zza);
        r1.zze(zzb);
        r1.zzb(zzc);
        r1.zzc(zzd);
        r1.zzb(zze);
        r1.zzc(zzf);
    }

    public static /* synthetic */ zzsp zzb(zzyo r3, zzsy r4) {
        return zzsp.zza("type.googleapis.com/google.crypto.tink.Ed25519PublicKey", zzh(r3).zzv(), zzsn.zzd, zzf(r3.zzf().zzb()), r3.zzb());
    }

    public static /* synthetic */ zzyo zzc(zzsp r2, zzsy r3) {
        if (r2.zzb().equals("type.googleapis.com/google.crypto.tink.Ed25519PublicKey") == true) goto L14;
        f$$ExternalSyntheticBUOutline1.m("Wrong type URL in call to Ed25519ProtoSerialization.parsePublicKey: ".concat(String.valueOf(r2.zzb())));
        return null;
    L14:
        zzwn r32 = zzwn.zzc(r2.zzc(), zzafh.zzb());     // Catch: zzagk -> L10
        if (r32.zza() != 0) goto L9;
        return zzyo.zzc(zzg(r2.zze()), zzady.zza(r32.zzb().zzy()), r2.zzf());
    L9:
        throw new GeneralSecurityException("Only version 0 keys are accepted");     // Catch: zzagk -> L10
    L10:
        zzsj$$ExternalSyntheticBUOutline1.m("Parsing Ed25519PublicKey failed");
        return null;
    }

    public static /* synthetic */ zzsp zzd(zzyi r3, zzsy r4) {
        zzwk r0 = zzwl.zze();
        r0.zzb(zzh(r3.zze()));
        byte[] r42 = r3.zzf().zzb(r4);
        r0.zza(zzaer.zzs(r42, 0, r42.length));
        return zzsp.zza("type.googleapis.com/google.crypto.tink.Ed25519PrivateKey", ((zzwl) r0.zzp()).zzv(), zzsn.zzc, zzf(r3.zzd().zzb()), r3.zze().zzb());
    }

    public static /* synthetic */ zzyi zze(zzsp r5, zzsy r6) {
        if (r5.zzb().equals("type.googleapis.com/google.crypto.tink.Ed25519PrivateKey") == true) goto L19;
        f$$ExternalSyntheticBUOutline1.m("Wrong type URL in call to Ed25519ProtoSerialization.parsePrivateKey: ".concat(String.valueOf(r5.zzb())));
        return null;
    L19:
        zzwl r0 = zzwl.zzd(r5.zzc(), zzafh.zzb());     // Catch: zzagk -> L15
        if (r0.zza() != 0) goto L14;
        zzwn r2 = r0.zzc();     // Catch: zzagk -> L15
        if (r2.zza() != 0) goto L12;
        return zzyi.zzc(zzyo.zzc(zzg(r5.zze()), zzady.zza(r2.zzb().zzy()), r5.zzf()), zzaea.zza(r0.zzb().zzy(), r6));
    L12:
        throw new GeneralSecurityException("Only version 0 keys are accepted");     // Catch: zzagk -> L15
    L14:
        throw new GeneralSecurityException("Only version 0 keys are accepted");     // Catch: zzagk -> L15
    L15:
        zzsj$$ExternalSyntheticBUOutline1.m("Parsing Ed25519PrivateKey failed");
        return null;
    }

    private static zzso zzf(zzyg r2) {
        if (r2.equals(zzyg.zzd) == false) goto L7;
        return zzso.zzd;
    L7:
        if (r2 == zzyg.zza) goto L19;
        if (r2 == zzyg.zzb) goto L17;
        if (r2 != zzyg.zzc) goto L15;
        return zzso.zzc;
    L15:
        throw new GeneralSecurityException("Unable to serialize variant: ".concat(r2.toString()));
    L17:
        return zzso.zze;
    L19:
        return zzso.zzb;
    }

    private static zzyg zzg(zzso r2) {
        if (r2 != zzso.zzd) goto L7;
        return zzyg.zzd;
    L7:
        if (r2 != zzso.zzb) goto L11;
        return zzyg.zza;
    L11:
        if (r2 != zzso.zze) goto L15;
        return zzyg.zzb;
    L15:
        if (r2 != zzso.zzc) goto L19;
        return zzyg.zzc;
    L19:
        throw new GeneralSecurityException("Unable to parse OutputPrefixType: ".concat(r2.toString()));
    }

    private static zzwn zzh(zzyo r3) {
        zzwm r0 = zzwn.zzd();
        byte[] r32 = r3.zzd().zzc();
        r0.zza(zzaer.zzs(r32, 0, r32.length));
        return (zzwn) r0.zzp();
    }
}
