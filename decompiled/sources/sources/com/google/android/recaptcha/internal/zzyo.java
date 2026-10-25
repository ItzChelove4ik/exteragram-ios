package com.google.android.recaptcha.internal;

import java.security.GeneralSecurityException;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public final class zzyo extends zzaax {
    private final zzyh zza;
    private final zzady zzb;
    private final zzady zzc;
    private final Integer zzd;

    private zzyo(zzyh r1, zzady r2, zzady r3, Integer r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    public static zzyo zzc(zzyg r4, zzady r5, Integer r6) {
        zzyh r0 = zzyh.zza(r4);
        zzyg r1 = zzyg.zzd;
        if (r4.equals(r1) == true) goto L9;
        if (r6 != null) goto L9;
        String r42 = r4.toString();
        StringBuilder r02 = new StringBuilder(r42.length() + 62);
        r02.append("For given Variant ");
        r02.append(r42);
        r02.append(" the value of idRequirement must be non-null");
        throw new GeneralSecurityException(r02.toString());
    L9:
        if (r4 != r1) goto L13;
        if (r6 == null) goto L13;
        zzsj$$ExternalSyntheticBUOutline1.m("For given Variant NO_PREFIX the value of idRequirement must be null");
        return null;
    L13:
        if (r5.zzd() == 32) goto L15;
        int r52 = r5.zzd();
        StringBuilder r03 = new StringBuilder(String.valueOf(r52).length() + 65);
        r03.append("Ed25519 key must be constructed with key of length 32 bytes, not ");
        r03.append(r52);
        throw new GeneralSecurityException(r03.toString());
    L15:
        if (r0.zzb() != r1) goto L18;
        zzady r12 = zzvc.zza;
    L29:
        return new zzyo(r0, r5, r12, r6);
    L18:
        if (r0.zzb() != zzyg.zzb) goto L20;
    L27:
        r12 = zzvc.zza(r6.intValue());
        goto L29
    L20:
        if (r0.zzb() == zzyg.zzc) goto L27;
        if (r0.zzb() != zzyg.zza) goto L25;
        r12 = zzvc.zzb(r6.intValue());
        goto L29
    L25:
        Segment$$ExternalSyntheticBUOutline1.m("Unknown Variant: ".concat(r0.zzb().toString()));
        return null;
    }

    @Override // com.google.android.recaptcha.internal.zzse
    public final /* synthetic */ zzsl zza() {
        return this.zza;
    }

    @Override // com.google.android.recaptcha.internal.zzse
    public final Integer zzb() {
        return this.zzd;
    }

    public final zzady zzd() {
        return this.zzb;
    }

    @Override // com.google.android.recaptcha.internal.zzaax
    public final zzady zze() {
        return this.zzc;
    }

    public final zzyh zzf() {
        return this.zza;
    }
}
