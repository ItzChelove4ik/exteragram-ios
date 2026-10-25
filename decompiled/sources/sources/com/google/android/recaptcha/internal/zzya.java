package com.google.android.recaptcha.internal;

import java.security.spec.ECPoint;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public final class zzya {
    private zzxx zza;
    private ECPoint zzb;
    private Integer zzc;

    public /* synthetic */ zzya(byte[] r1) {
        this.zza = null;
        this.zzb = null;
        this.zzc = null;
    }

    public final zzya zza(zzxx r1) {
        this.zza = r1;
        return this;
    }

    public final zzya zzb(ECPoint r1) {
        this.zzb = r1;
        return this;
    }

    public final zzya zzc(Integer r1) {
        this.zzc = r1;
        return this;
    }

    public final zzyb zzd() {
        zzxx r0 = this.zza;
        if (r0 == null) goto L39;
        ECPoint r2 = this.zzb;
        if (r2 == null) goto L37;
        zztt.zza(r2, r0.zzc().zza().getCurve());
        if (this.zza.zzf() == false) goto L14;
        if (this.zzc != null) goto L14;
        zzsj$$ExternalSyntheticBUOutline1.m("Cannot create key without ID requirement with parameters with ID requirement");
        return null;
    L14:
        if (this.zza.zzf() == true) goto L21;
        if (this.zzc == null) goto L21;
        zzsj$$ExternalSyntheticBUOutline1.m("Cannot create key with ID requirement with parameters without ID requirement");
        return null;
    L21:
        if (this.zza.zze() != zzxw.zzd) goto L25;
        zzady r02 = zzvc.zza;
    L23:
        zzady r4 = r02;
        return new zzyb(this.zza, this.zzb, r4, this.zzc, null);
    L25:
        if (this.zza.zze() != zzxw.zzc) goto L27;
    L34:
        r02 = zzvc.zza(this.zzc.intValue());
        goto L23
    L27:
        if (this.zza.zze() == zzxw.zzb) goto L34;
        if (this.zza.zze() != zzxw.zza) goto L32;
        r02 = zzvc.zzb(this.zzc.intValue());
        goto L23
    L32:
        Segment$$ExternalSyntheticBUOutline1.m("Unknown EcdsaParameters.Variant: ".concat(this.zza.zze().toString()));
        return null;
    L37:
        zzsj$$ExternalSyntheticBUOutline1.m("Cannot build without public point");
        return null;
    L39:
        zzsj$$ExternalSyntheticBUOutline1.m("Cannot build without parameters");
        return null;
    }
}
