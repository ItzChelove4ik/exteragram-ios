package com.google.android.recaptcha.internal;

import java.math.BigInteger;
import java.security.spec.ECField;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;

/* loaded from: classes5.dex */
public abstract class zztt {
    public static final ECParameterSpec zza = null;
    public static final ECParameterSpec zzb = null;
    public static final ECParameterSpec zzc = null;
    private static final BigInteger zzd = null;
    private static final BigInteger zze = null;
    private static final BigInteger zzf = null;
    private static final BigInteger zzg = null;

    static {
        zza = zzh("115792089210356248762697446949407573530086143415290314195533631308867097853951", "115792089210356248762697446949407573529996955224135760342422259061068512044369", "5ac635d8aa3a93e7b3ebbd55769886bc651d06b0cc53b0f63bce3c3e27d2604b", "6b17d1f2e12c4247f8bce6e563a440f277037d812deb33a0f4a13945d898c296", "4fe342e2fe1a7f9b8ee7eb4a7c0f9e162bce33576b315ececbb6406837bf51f5");
        zzb = zzh("39402006196394479212279040100143613805079739270465446667948293404245721771496870329047266088258938001861606973112319", "39402006196394479212279040100143613805079739270465446667946905279627659399113263569398956308152294913554433653942643", "b3312fa7e23ee7e4988e056be3f82d19181d9c6efe8141120314088f5013875ac656398d8a2ed19d2a85c8edd3ec2aef", "aa87ca22be8b05378eb1c71ef320ad746e1d3b628ba79b9859f741e082542a385502f25dbf55296c3a545e3872760ab7", "3617de4a96262c6f5d9e98bf9292dc29f8f41dbd289a147ce9da3113b5f0b8c00a60b1ce1d7e819d7a431d7c90ea0e5f");
        zzc = zzh("6864797660130609714981900799081393217269435300143305409394463459185543183397656052122559640661454554977296311391480858037121987999716643812574028291115057151", "6864797660130609714981900799081393217269435300143305409394463459185543183397655394245057746333217197532963996371363321113864768612440380340372808892707005449", "051953eb9618e1c9a1f929a21a0b68540eea2da725b99b315f3b8b489918ef109e156193951ec7e937b1652c0bd3bb1bf073573df883d2c34f1ef451fd46b503f00", "c6858e06b70404e9cd9e3ecb662395b4429c648139053fb521f828af606b4d3dbaa14b5e77efe75928fe1dc127a2ffa8de3348b3c1856a429bf97e7e31c2e5bd66", "11839296a789a3bc0045c8a5fb42c7d1bd998f54449579b446817afbd17273e662c97ee72995ef42640c550b9013fad0761353c7086a272c24088be94769fd16650");
        zzd = BigInteger.valueOf(2);
        zze = BigInteger.valueOf(3);
        zzf = BigInteger.valueOf(4);
        zzg = BigInteger.valueOf(8);
    }

    public static void zza(ECPoint r4, EllipticCurve r5) {
        BigInteger r0 = zzc(r5);
        BigInteger r1 = r4.getAffineX();
        BigInteger r42 = r4.getAffineY();
        if (r1 == null) goto L22;
        if (r42 == null) goto L22;
        if (r1.signum() != (-1)) goto L8;
    L20:
        zzsj$$ExternalSyntheticBUOutline1.m("x is out of range");
        return;
    L8:
        if (r1.compareTo(r0) >= 0) goto L20;
        if (r42.signum() != (-1)) goto L12;
    L18:
        zzsj$$ExternalSyntheticBUOutline1.m("y is out of range");
        return;
    L12:
        if (r42.compareTo(r0) >= 0) goto L18;
        if (r42.multiply(r42).mod(r0).equals(r1.multiply(r1).add(r5.getA()).multiply(r1).add(r5.getB()).mod(r0)) == false) goto L16;
        return;
    L16:
        zzsj$$ExternalSyntheticBUOutline1.m("Point is not on curve");
        return;
    L22:
        zzsj$$ExternalSyntheticBUOutline1.m("point is at infinity");
    }

    public static boolean zzb(ECParameterSpec r2, ECParameterSpec r3) {
        if (r2.getCurve().equals(r3.getCurve()) == true) goto L5;
        return false;
    L5:
        if (r2.getGenerator().equals(r3.getGenerator()) == true) goto L7;
        return false;
    L7:
        if (r2.getOrder().equals(r3.getOrder()) == true) goto L9;
        return false;
    L9:
        if (r2.getCofactor() != r3.getCofactor()) goto L16;
        return true;
    L16:
        return false;
    }

    public static BigInteger zzc(EllipticCurve r1) {
        ECField r12 = r1.getField();
        if ((r12 instanceof ECFieldFp) == true) goto L5;
        zzsj$$ExternalSyntheticBUOutline1.m("Only curves over prime order fields are supported");
        return null;
    L5:
        return ((ECFieldFp) r12).getP();
    }

    public static ECPoint zzd(BigInteger r6, ECParameterSpec r7) {
        if (zzb(r7, zza) == true) goto L12;
        if (zzb(r7, zzb) == true) goto L12;
        if (zzb(r7, zzc) == true) goto L12;
        zzsj$$ExternalSyntheticBUOutline1.m("spec must be NIST P256, P384 or P521");
        return null;
    L12:
        if (r6.signum() == 1) goto L14;
        zzsj$$ExternalSyntheticBUOutline1.m("k must be positive");
        return null;
    L14:
        if (r6.compareTo(r7.getOrder()) >= 0) goto L28;
        EllipticCurve r0 = r7.getCurve();
        ECPoint r1 = r7.getGenerator();
        zza(r1, r0);
        BigInteger r72 = r7.getCurve().getA();
        BigInteger r2 = zzc(r0);
        zzts r3 = zze(ECPoint.POINT_INFINITY, r2);
        zzts r12 = zze(r1, r2);
        int r4 = r6.bitLength();
    L16:
        if (r4 < 0) goto L23;
        if (r6.testBit(r4) == false) goto L20;
        r3 = zzg(r3, r12, r72, r2);
        r12 = zzf(r12, r72, r2);
    L21:
        r4 = r4 - 1;
        goto L16
    L20:
        r12 = zzg(r3, r12, r72, r2);
        r3 = zzf(r3, r72, r2);
        goto L21
    L23:
        if (r3.zza() == false) goto L25;
        ECPoint r62 = ECPoint.POINT_INFINITY;
    L26:
        zza(r62, r0);
        return r62;
    L25:
        BigInteger r63 = r3.zzc.modInverse(r2);
        BigInteger r73 = r63.multiply(r63).mod(r2);
        r62 = new ECPoint(r3.zza.multiply(r73).mod(r2), r3.zzb.multiply(r73).mod(r2).multiply(r63).mod(r2));
        goto L26
    L28:
        zzsj$$ExternalSyntheticBUOutline1.m("k must be smaller than the order of the generator");
        return null;
    }

    public static zzts zze(ECPoint r5, BigInteger r6) {
        if (r5.equals(ECPoint.POINT_INFINITY) == true) goto L5;
        BigInteger r0 = new BigInteger(1, zzvw.zza((r6.bitLength() + 8) / 8)).mod(r6);
        BigInteger r1 = r0.multiply(r0).mod(r6);
        BigInteger r2 = r1.multiply(r0).mod(r6);
        return new zzts(r5.getAffineX().multiply(r1).mod(r6), r5.getAffineY().multiply(r2).mod(r6), r0);
    L5:
        return zzts.zzd;
    }

    public static zzts zzf(zzts r8, BigInteger r9, BigInteger r10) {
        BigInteger r0 = r8.zzb;
        if (r0.equals(BigInteger.ZERO) == true) goto L5;
        BigInteger r1 = r8.zza;
        BigInteger r2 = r1.multiply(r1).mod(r10);
        BigInteger r3 = r0.multiply(r0).mod(r10);
        BigInteger r4 = r3.multiply(r3).mod(r10);
        BigInteger r82 = r8.zzc;
        BigInteger r5 = r82.multiply(r82).mod(r10);
        BigInteger r12 = r1.add(r3);
        BigInteger r13 = r12.multiply(r12).mod(r10).subtract(r2).subtract(r4);
        BigInteger r6 = zzd;
        BigInteger r14 = r13.multiply(r6);
        BigInteger r92 = r2.multiply(zze).add(r9.multiply(r5).multiply(r5).mod(r10));
        BigInteger r22 = r92.multiply(r92).mod(r10).subtract(r14.multiply(r6)).mod(r10);
        BigInteger r93 = r92.multiply(r14.subtract(r22)).mod(r10).subtract(r4.multiply(zzg)).mod(r10);
        BigInteger r83 = r0.add(r82);
        return new zzts(r22, r93, r83.multiply(r83).mod(r10).subtract(r3).subtract(r5).mod(r10));
    L5:
        return zzts.zzd;
    }

    public static zzts zzg(zzts r9, zzts r10, BigInteger r11, BigInteger r12) {
        if (r9.zza() == false) goto L6;
        return r10;
    L6:
        if (r10.zza() == false) goto L8;
        return r9;
    L8:
        BigInteger r0 = r9.zzc;
        BigInteger r1 = r0.multiply(r0).mod(r12);
        BigInteger r2 = r10.zzc;
        BigInteger r3 = r2.multiply(r2).mod(r12);
        BigInteger r4 = r9.zza.multiply(r3).mod(r12);
        BigInteger r5 = r10.zza.multiply(r1).mod(r12);
        BigInteger r6 = r9.zzb.multiply(r2).mod(r12).multiply(r3).mod(r12);
        BigInteger r102 = r10.zzb.multiply(r0).mod(r12).multiply(r1).mod(r12);
        if (r4.equals(r5) == true) goto L11;
        BigInteger r92 = r5.subtract(r4).mod(r12);
        BigInteger r112 = r92.multiply(zzf).multiply(r92).mod(r12);
        BigInteger r52 = r92.multiply(r112).mod(r12);
        BigInteger r103 = r102.subtract(r6);
        BigInteger r7 = zzd;
        BigInteger r104 = r103.multiply(r7).mod(r12);
        BigInteger r113 = r4.multiply(r112).mod(r12);
        BigInteger r42 = r104.multiply(r104).mod(r12).subtract(r52).subtract(r113.multiply(r7)).mod(r12);
        BigInteger r105 = r104.multiply(r113.subtract(r42)).subtract(r6.multiply(r7).multiply(r52)).mod(r12);
        BigInteger r114 = r0.add(r2);
        return new zzts(r42, r105, r114.multiply(r114).mod(r12).subtract(r1).subtract(r3).multiply(r92).mod(r12));
    L11:
        if (r6.equals(r102) == true) goto L15;
        return zzts.zzd;
    L15:
        return zzf(r9, r11, r12);
    }

    private static ECParameterSpec zzh(String r3, String r4, String r5, String r6, String r7) {
        BigInteger r0 = new BigInteger(r3);
        BigInteger r32 = new BigInteger(r4);
        BigInteger r42 = r0.subtract(new BigInteger("3"));
        BigInteger r1 = new BigInteger(r5, 16);
        BigInteger r52 = new BigInteger(r6, 16);
        BigInteger r62 = new BigInteger(r7, 16);
        return new ECParameterSpec(new EllipticCurve(new ECFieldFp(r0), r42, r1), new ECPoint(r52, r62), r32, 1);
    }
}
