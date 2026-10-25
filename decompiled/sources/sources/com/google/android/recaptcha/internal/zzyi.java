package com.google.android.recaptcha.internal;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* loaded from: classes5.dex */
public final class zzyi extends zzaaw {
    private final zzyo zza;
    private final zzaea zzb;

    private zzyi(zzyo r1, zzaea r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    public static zzyi zzc(zzyo r2, zzaea r3) {
        if (r3.zzc() == 32) goto L5;
        int r32 = r3.zzc();
        StringBuilder r1 = new StringBuilder(String.valueOf(r32).length() + 65);
        r1.append("Ed25519 key must be constructed with key of length 32 bytes, not ");
        r1.append(r32);
        throw new GeneralSecurityException(r1.toString());
    L5:
        if (Arrays.equals(r2.zzd().zzc(), zztp.zza(zztp.zzb(r3.zzb(zzsd.zza())))) == true) goto L7;
        zzsj$$ExternalSyntheticBUOutline1.m("Ed25519 keys mismatch");
        return null;
    L7:
        return new zzyi(r2, r3);
    }

    @Override // com.google.android.recaptcha.internal.zzse
    public final /* synthetic */ zzsl zza() {
        return this.zza.zzf();
    }

    public final zzyh zzd() {
        return this.zza.zzf();
    }

    public final zzyo zze() {
        return this.zza;
    }

    public final zzaea zzf() {
        return this.zzb;
    }

    @Override // com.google.android.recaptcha.internal.zzaaw
    public final /* synthetic */ zzaax zzg() {
        return this.zza;
    }
}
