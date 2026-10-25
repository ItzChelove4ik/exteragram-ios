package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
enum zzrj extends Enum implements zzrk {
    public static final zzrj zza = null;
    private static final /* synthetic */ zzrj[] zzb = null;

    static {
        zzrj r0 = new zzrj("INSTANCE", 0);
        zza = r0;
        zzb = new zzrj[]{r0};
    }

    zzrj(String r1, int r2) {
    }

    public static zzrj[] values() {
        return (zzrj[]) zzb.clone();
    }

    @Override // com.google.android.recaptcha.internal.zzrk
    public final /* synthetic */ long zza(byte[] r20, int r21) {
        if (ordinal() != 0) goto L7;
        byte r0 = r20[r21 + 7];
        byte r1 = r20[r21 + 6];
        byte r2 = r20[r21 + 5];
        byte r3 = r20[r21 + 4];
        byte r4 = r20[r21 + 3];
        long r8 = r0;
        return ((((((((r1 & 255) << 48) | ((r8 & 255) << 56)) | ((r2 & 255) << 40)) | ((r3 & 255) << 32)) | ((r4 & 255) << 24)) | ((r20[r21 + 2] & 255) << 16)) | ((r20[r21 + 1] & 255) << 8)) | (r20[r21] & 255);
    L7:
        throw null;
    }
}
