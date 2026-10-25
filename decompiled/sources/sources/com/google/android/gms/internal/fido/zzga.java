package com.google.android.gms.internal.fido;

import java.io.Serializable;
import java.security.MessageDigest;
import okio.Buffer$$ExternalSyntheticBUOutline2;

/* loaded from: classes4.dex */
final class zzga extends zzfq implements Serializable {
    private final MessageDigest zza;
    private final int zzb;
    private final boolean zzc;
    private final String zzd;

    public zzga(String r1, String r2) {
        MessageDigest r12 = zzb("SHA-256");
        this.zza = r12;
        this.zzb = r12.getDigestLength();
        this.zzd = "Hashing.sha256()";
        this.zzc = zzc(r12);
    }

    private static MessageDigest zzb(String r0) {
        return MessageDigest.getInstance(r0);
    L4:
        e = move-exception;
        Buffer$$ExternalSyntheticBUOutline2.m(e);
        return null;
    }

    private static boolean zzc(MessageDigest r0) {
        r0.clone();     // Catch: CloneNotSupportedException -> L5
        return true;
    L5:
        return false;
    }

    public final String toString() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.fido.zzfu
    public final zzfv zza() {
        if (this.zzc == false) goto L7;
        return new zzfz((MessageDigest) this.zza.clone(), this.zzb, null);
    L7:
        return new zzfz(zzb(this.zza.getAlgorithm()), this.zzb, null);
    }
}
