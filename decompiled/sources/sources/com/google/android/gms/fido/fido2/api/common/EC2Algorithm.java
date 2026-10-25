package com.google.android.gms.fido.fido2.api.common;

/* loaded from: classes4.dex */
public enum EC2Algorithm extends Enum<EC2Algorithm> implements Algorithm {
    public static final EC2Algorithm ECDH_HKDF_256 = null;
    public static final EC2Algorithm ED25519 = null;
    public static final EC2Algorithm ED256 = null;
    public static final EC2Algorithm ED512 = null;
    public static final EC2Algorithm ES256 = null;
    public static final EC2Algorithm ES384 = null;
    public static final EC2Algorithm ES512 = null;
    private static final /* synthetic */ EC2Algorithm[] zza = null;
    private final int zzb;

    static {
        EC2Algorithm r0 = new EC2Algorithm("ED256", 0, -260);
        ED256 = r0;
        EC2Algorithm r1 = new EC2Algorithm("ED512", 1, -261);
        ED512 = r1;
        EC2Algorithm r2 = new EC2Algorithm("ED25519", 2, -8);
        ED25519 = r2;
        EC2Algorithm r3 = new EC2Algorithm("ES256", 3, -7);
        ES256 = r3;
        EC2Algorithm r4 = new EC2Algorithm("ECDH_HKDF_256", 4, -25);
        ECDH_HKDF_256 = r4;
        EC2Algorithm r5 = new EC2Algorithm("ES384", 5, -35);
        ES384 = r5;
        EC2Algorithm r6 = new EC2Algorithm("ES512", 6, -36);
        ES512 = r6;
        zza = new EC2Algorithm[]{r0, r1, r2, r3, r4, r5, r6};
    }

    EC2Algorithm(String r1, int r2, int r3) {
        this.zzb = r3;
    }

    public static EC2Algorithm valueOf(String r1) {
        return (EC2Algorithm) Enum.valueOf(EC2Algorithm.class, r1);
    }

    public static EC2Algorithm[] values() {
        return (EC2Algorithm[]) zza.clone();
    }

    @Override // com.google.android.gms.fido.fido2.api.common.Algorithm
    public int getAlgoValue() {
        return this.zzb;
    }
}
