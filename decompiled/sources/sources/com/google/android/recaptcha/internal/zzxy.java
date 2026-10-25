package com.google.android.recaptcha.internal;

import java.math.BigInteger;
import java.security.spec.ECPoint;

/* loaded from: classes5.dex */
public final class zzxy {
    private zzyb zza;
    private zzadz zzb;

    public /* synthetic */ zzxy(byte[] r1) {
        this.zza = null;
        this.zzb = null;
    }

    public final zzxy zza(zzyb r1) {
        this.zza = r1;
        return this;
    }

    public final zzxy zzb(zzadz r1) {
        this.zzb = r1;
        return this;
    }

    public final zzxz zzc() {
        zzyb r0 = this.zza;
        if (r0 == null) goto L20;
        zzadz r2 = this.zzb;
        if (r2 == null) goto L18;
        BigInteger r22 = r2.zzb(zzsd.zza());
        ECPoint r3 = r0.zzd();
        zzxt r02 = r0.zzf().zzc();
        BigInteger r4 = r02.zza().getOrder();
        if (r22.signum() > 0) goto L9;
    L16:
        zzsj$$ExternalSyntheticBUOutline1.m("Invalid private value");
        return null;
    L9:
        if (r22.compareTo(r4) >= 0) goto L16;
        if (zztt.zzd(r22, r02.zza()).equals(r3) == true) goto L13;
        zzsj$$ExternalSyntheticBUOutline1.m("Invalid private value");
        return null;
    L13:
        return new zzxz(this.zza, this.zzb, null);
    L18:
        zzsj$$ExternalSyntheticBUOutline1.m("Cannot build without a private value");
        return null;
    L20:
        zzsj$$ExternalSyntheticBUOutline1.m("Cannot build without a ecdsa public key");
        return null;
    }
}
