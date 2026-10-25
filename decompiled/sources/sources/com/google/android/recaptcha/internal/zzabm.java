package com.google.android.recaptcha.internal;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.Provider;
import java.security.Signature;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.EllipticCurve;
import java.util.Arrays;
import kotlin.UByte;

/* loaded from: classes5.dex */
public final class zzabm implements zzst {
    static final zztv zza = null;
    static final zztv zzb = null;
    static final zztv zzc = null;
    private static final byte[] zze = null;
    private static final byte[] zzf = null;
    private final ECPublicKey zzg;
    private final String zzh;
    private final zzacz zzi;
    private final byte[] zzj;
    private final byte[] zzk;
    private final Provider zzl;

    static {
        zze = new byte[0];
        zzf = new byte[]{0};
        zztu r0 = zztv.zza();
        r0.zza(zzado.zzc, zzxu.zza);
        r0.zza(zzado.zzd, zzxu.zzb);
        r0.zza(zzado.zze, zzxu.zzc);
        zza = r0.zzb();
        zztu r02 = zztv.zza();
        r02.zza(zzacz.zza, zzxv.zza);
        r02.zza(zzacz.zzb, zzxv.zzb);
        zzb = r02.zzb();
        zztu r03 = zztv.zza();
        r03.zza(zzacy.zza, zzxt.zza);
        r03.zza(zzacy.zzb, zzxt.zzb);
        r03.zza(zzacy.zzc, zzxt.zzc);
        zzc = r03.zzb();
    }

    private zzabm(ECPublicKey r2, zzado r3, zzacz r4, byte[] r5, byte[] r6, Provider r7) {
        if (zzte.zza(2) == false) goto L6;
        this.zzh = zzadw.zza(r3);
        this.zzg = r2;
        this.zzi = r4;
        this.zzj = r5;
        this.zzk = r6;
        this.zzl = r7;
        return;
    L6:
        zzsj$$ExternalSyntheticBUOutline1.m("Can not use ECDSA in FIPS-mode, as BoringCrypto is not available.");
        throw null;
    }

    public static zzst zzb(zzyb r8, Provider r9) {
        ECParameterSpec r0 = zzada.zzb((zzacy) zzc.zzb(r8.zzf().zzc()));
        ECPublicKeySpec r2 = new ECPublicKeySpec(r8.zzd(), r0);
        if (r9 == null) goto L5;
        KeyFactory r02 = KeyFactory.getInstance("EC", r9);
    L6:
        ECPublicKey r22 = (ECPublicKey) r02.generatePublic(r2);
        zzado r3 = (zzado) zza.zzb(r8.zzf().zzd());
        zzacz r4 = (zzacz) zzb.zzb(r8.zzf().zzb());
        byte[] r5 = r8.zze().zzc();
        if (r8.zzf().zze().equals(zzxw.zzc) == false) goto L10;
        byte[] r82 = zzf;
    L12:
        return new zzabm(r22, r3, r4, r5, r82, r9);
    L10:
        r82 = zze;
        goto L12
    L5:
        r02 = (KeyFactory) zzadf.zzc.zzb("EC");
        goto L6
    }

    private final void zzc(byte[] r12, byte[] r13) {
        if (this.zzi != zzacz.zza) goto L9;
        EllipticCurve r0 = this.zzg.getParams().getCurve();
        int r1 = r12.length;
        int r02 = (zztt.zzc(r0).subtract(BigInteger.ONE).bitLength() + 7) / 8;
        if (r1 != (r02 + r02)) goto L7;
        r12 = zzada.zza(r12);
        goto L9
    L7:
        zzsj$$ExternalSyntheticBUOutline1.m("Invalid signature");
        return;
    L9:
        int r03 = r12.length;
        if (r03 >= 8) goto L12;
    L57:
        zzsj$$ExternalSyntheticBUOutline1.m("Invalid signature");
        return;
    L12:
        if (r12[0] != 48) goto L57;
        int r2 = r12[1] & UByte.MAX_VALUE;
        if (r2 != 129) goto L18;
        r2 = r12[2] & UByte.MAX_VALUE;
        if (r2 < 128) goto L57;
        int r4 = 2;
    L22:
        if (r2 != ((r03 - 1) - r4)) goto L57;
        if (r12[r4 + 1] != 2) goto L57;
        int r22 = r12[r4 + 2] & UByte.MAX_VALUE;
        int r7 = r4 + 3;
        int r8 = r7 + r22;
        int r9 = r8 + 1;
        if (r9 >= r03) goto L57;
        if (r22 == 0) goto L57;
        byte r72 = r12[r7];
        if ((r72 & UByte.MAX_VALUE) >= 128) goto L57;
        if (r22 <= 1) goto L35;
        if (r72 != 0) goto L35;
        if ((r12[r4 + 4] & UByte.MAX_VALUE) < 128) goto L57;
    L35:
        if (r12[r8] != 2) goto L57;
        int r73 = r12[r9] & UByte.MAX_VALUE;
        if (((r8 + 2) + r73) != r03) goto L57;
        if (r73 == 0) goto L57;
        byte r04 = r12[(r4 + 5) + r22];
        if ((r04 & UByte.MAX_VALUE) >= 128) goto L57;
        if (r73 <= 1) goto L45;
        if (r04 != 0) goto L45;
        if ((r12[(r4 + 6) + r22] & UByte.MAX_VALUE) < 128) goto L57;
    L45:
        String r05 = this.zzh;
        Provider r14 = this.zzl;
        if (r14 == null) goto L48;
        Signature r06 = Signature.getInstance(r05, r14);
    L49:
        r06.initVerify(this.zzg);
        r06.update(r13);
        byte[] r11 = this.zzk;
        if (r11.length <= 0) goto L60;
        r06.update(r11);
    L60:
        if (r06.verify(r12) == false) goto L55;
        return;
    L55:
        zzsj$$ExternalSyntheticBUOutline1.m("Invalid signature");
        return;
    L48:
        r06 = (Signature) zzadf.zza.zzb(r05);
        goto L49
    L18:
        if (r2 == 128) goto L57;
        if (r2 > 129) goto L57;
        r4 = 1;
        goto L22
    }

    @Override // com.google.android.recaptcha.internal.zzst
    public final void zza(byte[] r3, byte[] r4) {
        byte[] r0 = this.zzj;
        int r1 = r0.length;
        if (r1 != 0) goto L7;
        zzc(r3, r4);
        return;
    L7:
        if (zzwc.zzc(r0, r3) == false) goto L10;
        zzc(Arrays.copyOfRange(r3, r1, r3.length), r4);
        return;
    L10:
        zzsj$$ExternalSyntheticBUOutline1.m("Invalid signature (output prefix mismatch)");
    }
}
