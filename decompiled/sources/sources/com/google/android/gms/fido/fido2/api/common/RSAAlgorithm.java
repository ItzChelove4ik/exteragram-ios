package com.google.android.gms.fido.fido2.api.common;

/* loaded from: classes4.dex */
public enum RSAAlgorithm extends Enum<RSAAlgorithm> implements Algorithm {

    @Deprecated
    public static final RSAAlgorithm LEGACY_RS1 = null;
    public static final RSAAlgorithm PS256 = null;
    public static final RSAAlgorithm PS384 = null;
    public static final RSAAlgorithm PS512 = null;
    public static final RSAAlgorithm RS1 = null;
    public static final RSAAlgorithm RS256 = null;
    public static final RSAAlgorithm RS384 = null;
    public static final RSAAlgorithm RS512 = null;
    private static final /* synthetic */ RSAAlgorithm[] zza = null;
    private final int zzb;

    static {
        RSAAlgorithm r0 = new RSAAlgorithm("RS256", 0, -257);
        RS256 = r0;
        RSAAlgorithm r1 = new RSAAlgorithm("RS384", 1, -258);
        RS384 = r1;
        RSAAlgorithm r2 = new RSAAlgorithm("RS512", 2, -259);
        RS512 = r2;
        RSAAlgorithm r3 = new RSAAlgorithm("LEGACY_RS1", 3, -262);
        LEGACY_RS1 = r3;
        RSAAlgorithm r4 = new RSAAlgorithm("PS256", 4, -37);
        PS256 = r4;
        RSAAlgorithm r5 = new RSAAlgorithm("PS384", 5, -38);
        PS384 = r5;
        RSAAlgorithm r6 = new RSAAlgorithm("PS512", 6, -39);
        PS512 = r6;
        RSAAlgorithm r7 = new RSAAlgorithm("RS1", 7, -65535);
        RS1 = r7;
        zza = new RSAAlgorithm[]{r0, r1, r2, r3, r4, r5, r6, r7};
    }

    RSAAlgorithm(String r1, int r2, int r3) {
        this.zzb = r3;
    }

    public static RSAAlgorithm valueOf(String r1) {
        return (RSAAlgorithm) Enum.valueOf(RSAAlgorithm.class, r1);
    }

    public static RSAAlgorithm[] values() {
        return (RSAAlgorithm[]) zza.clone();
    }

    @Override // com.google.android.gms.fido.fido2.api.common.Algorithm
    public int getAlgoValue() {
        return this.zzb;
    }
}
