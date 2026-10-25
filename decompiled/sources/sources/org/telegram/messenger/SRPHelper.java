package org.telegram.messenger;

import java.math.BigInteger;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public class SRPHelper {
    public SRPHelper() {
    }

    public static byte[] getBigIntegerBytes(BigInteger r5) {
        byte[] r52 = r5.toByteArray();
        if (r52.length <= 256) goto L7;
        byte[] r0 = new byte[256];
        System.arraycopy(r52, 1, r0, 0, 256);
        return r0;
    L7:
        if (r52.length >= 256) goto L13;
        byte[] r02 = new byte[256];
        System.arraycopy(r52, 0, r02, 256 - r52.length, r52.length);
        int r3 = 0;
    L10:
        if (r3 >= (256 - r52.length)) goto L12;
        r02[r3] = 0;
        r3 = r3 + 1;
        goto L10
    L12:
        return r02;
    L13:
        return r52;
    }

    public static BigInteger getV(byte[] r4, TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow r5) {
        BigInteger r0 = BigInteger.valueOf(r5.g);
        getBigIntegerBytes(r0);
        return r0.modPow(new BigInteger(1, getX(r4, r5)), new BigInteger(1, r5.p));
    }

    public static byte[] getVBytes(byte[] r2, TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow r3) {
        if (Utilities.isGoodPrime(r3.p, r3.g) == true) goto L7;
        return null;
    L7:
        return getBigIntegerBytes(getV(r2, r3));
    }

    public static byte[] getX(byte[] r1, TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow r2) {
        byte[] r0 = r2.salt1;
        byte[] r12 = Utilities.computeSHA256(new byte[][]{r0, r1, r0});
        byte[] r02 = r2.salt2;
        byte[] r13 = Utilities.computePBKDF2(Utilities.computeSHA256(new byte[][]{r02, r12, r02}), r2.salt1);
        byte[] r22 = r2.salt2;
        return Utilities.computeSHA256(new byte[][]{r22, r13, r22});
    }

    public static TLRPC.TL_inputCheckPasswordSRP startCheck(byte[] r17, long r18, byte[] r20, TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow r21) {
        if (r17 == null) goto L30;
        if (r20 == null) goto L30;
        if (r20.length == 0) goto L30;
        if (Utilities.isGoodPrime(r21.p, r21.g) == false) goto L30;
        BigInteger r4 = BigInteger.valueOf(r21.g);
        byte[] r5 = getBigIntegerBytes(r4);
        BigInteger r6 = new BigInteger(1, r21.p);
        BigInteger r9 = new BigInteger(1, Utilities.computeSHA256(new byte[][]{r21.p, r5}));
        BigInteger r7 = new BigInteger(1, r17);
        byte[] r0 = new byte[256];
        Utilities.random.nextBytes(r0);
        BigInteger r10 = new BigInteger(1, r0);
        byte[] r14 = getBigIntegerBytes(r4.modPow(r10, r6));
        BigInteger r02 = new BigInteger(1, r20);
        BigInteger r1 = BigInteger.ZERO;
        if (r02.compareTo(r1) <= 0) goto L30;
        if (r02.compareTo(r6) >= 0) goto L30;
        byte[] r15 = getBigIntegerBytes(r02);
        BigInteger r12 = new BigInteger(1, Utilities.computeSHA256(new byte[][]{r14, r15}));
        if (r12.compareTo(r1) != 0) goto L18;
        return null;
    L18:
        BigInteger r03 = r02.subtract(r9.multiply(r4.modPow(r7, r6)).mod(r6));
        if (r03.compareTo(r1) >= 0) goto L22;
        r03 = r03.add(r6);
    L22:
        if (Utilities.isGoodGaAndGb(r03, r6) == true) goto L24;
        return null;
    L24:
        byte[] r16 = Utilities.computeSHA256(getBigIntegerBytes(r03.modPow(r10.add(r12.multiply(r7)), r6)));
        byte[] r11 = Utilities.computeSHA256(r21.p);
        byte[] r04 = Utilities.computeSHA256(r5);
        int r13 = 0;
    L26:
        if (r13 >= r11.length) goto L28;
        r11[r13] = (byte) (r04[r13] ^ r11[r13]);
        r13 = r13 + 1;
        goto L26
    L28:
        TLRPC.TL_inputCheckPasswordSRP r05 = new TLRPC.TL_inputCheckPasswordSRP();
        r05.M1 = Utilities.computeSHA256(new byte[][]{r11, Utilities.computeSHA256(r21.salt1), Utilities.computeSHA256(r21.salt2), r14, r15, r16});
        r05.A = r14;
        r05.srp_id = r18;
        return r05;
    L30:
        return null;
    }
}
