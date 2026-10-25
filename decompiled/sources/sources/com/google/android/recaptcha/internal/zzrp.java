package com.google.android.recaptcha.internal;

import java.io.Serializable;
import java.security.MessageDigest;
import okio.Buffer$$ExternalSyntheticBUOutline2;

/* loaded from: classes5.dex */
final class zzrp extends zzqx implements Serializable {
    private final MessageDigest zza;
    private final int zzb;
    private final boolean zzc;
    private final String zzd;

    public zzrp(String r1, String r2) {
        MessageDigest r12 = zze("SHA-256");
        this.zza = r12;
        this.zzb = r12.getDigestLength();
        this.zzd = "Hashing.sha256()";
        this.zzc = zzd(r12);
    }

    private static boolean zzd(MessageDigest r0) {
        r0.clone();     // Catch: CloneNotSupportedException -> L5
        return true;
    L5:
        return false;
    }

    private static MessageDigest zze(String r0) {
        return MessageDigest.getInstance(r0);
    L4:
        e = move-exception;
        Buffer$$ExternalSyntheticBUOutline2.m(e);
        return null;
    }

    public final String toString() {
        return this.zzd;
    }

    @Override // com.google.android.recaptcha.internal.zzrf
    public final zzrg zzc() {
        if (this.zzc == false) goto L7;
        return new zzro((MessageDigest) this.zza.clone(), this.zzb, null);
    L7:
        return new zzro(zze(this.zza.getAlgorithm()), this.zzb, null);
    }
}
