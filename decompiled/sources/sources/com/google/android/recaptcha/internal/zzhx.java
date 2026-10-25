package com.google.android.recaptcha.internal;

import java.math.BigInteger;
import java.security.SecureRandom;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;

/* loaded from: classes5.dex */
public final class zzhx {
    private final zzaer zza;
    private final byte[] zzb;

    public zzhx(zzaer r2) {
        this.zza = r2;
        int r22 = zzair.$r8$clinit;
        byte[] r23 = new byte[12];
        new SecureRandom().nextBytes(r23);
        this.zzb = r23;
    }

    public final byte[] zza() {
        return this.zzb;
    }

    public final byte[] zzb(byte[] r5, int r6) {
        byte[] r62 = BigInteger.valueOf(r6).toByteArray();
        return CollectionsKt.toByteArray(ArraysKt.drop(zzair.zzb(r5, this.zza.zzy(), ArraysKt.plus(ArraysKt.copyOfRange(this.zzb, 0, 12 - r62.length), r62)), 12));
    }
}
