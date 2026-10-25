package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public abstract class zzacx implements zzst {
    public static zzst zzb(zzyo r6) {
        if (zzte.zza(1) == true) goto L14;
        zzsj$$ExternalSyntheticBUOutline1.m("Can not use Ed25519 in FIPS-mode.");
        return null;
    L14:
        return zzabv.zzb(r6);
    L6:
        byte[] r3 = r6.zzd().zzc();
        byte[] r4 = r6.zze().zzc();
        if (r6.zzf().zzb().equals(zzyg.zzc) == false) goto L9;
        byte[] r62 = {0};
    L11:
        return new zzacw(r3, r4, r62, null);
    L9:
        r62 = new byte[0];
        goto L11
    }
}
