package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public final class zzxs {
    private zzxv zza;
    private zzxt zzb;
    private zzxu zzc;
    private zzxw zzd;

    public /* synthetic */ zzxs(byte[] r1) {
        this.zza = null;
        this.zzb = null;
        this.zzc = null;
        this.zzd = zzxw.zzd;
    }

    public final zzxs zza(zzxv r1) {
        this.zza = r1;
        return this;
    }

    public final zzxs zzb(zzxt r1) {
        this.zzb = r1;
        return this;
    }

    public final zzxs zzc(zzxu r1) {
        this.zzc = r1;
        return this;
    }

    public final zzxs zzd(zzxw r1) {
        this.zzd = r1;
        return this;
    }

    public final zzxx zze() {
        zzxv r1 = this.zza;
        if (r1 == null) goto L41;
        zzxt r2 = this.zzb;
        if (r2 == null) goto L39;
        zzxu r3 = this.zzc;
        if (r3 == null) goto L37;
        zzxw r4 = this.zzd;
        if (r4 != null) goto L11;
        zzsj$$ExternalSyntheticBUOutline1.m("variant is not set");
        return null;
    L11:
        if (r2 != zzxt.zza) goto L18;
        if (r3 == zzxu.zza) goto L18;
        zzsj$$ExternalSyntheticBUOutline1.m("NIST_P256 requires SHA256");
        return null;
    L18:
        if (r2 != zzxt.zzb) goto L27;
        if (r3 == zzxu.zzb) goto L27;
        if (r3 == zzxu.zzc) goto L27;
        zzsj$$ExternalSyntheticBUOutline1.m("NIST_P384 requires SHA384 or SHA512");
        return null;
    L27:
        if (r2 != zzxt.zzc) goto L34;
        if (r3 == zzxu.zzc) goto L34;
        zzsj$$ExternalSyntheticBUOutline1.m("NIST_P521 requires SHA512");
        return null;
    L34:
        return new zzxx(r1, r2, r3, r4, null);
    L37:
        zzsj$$ExternalSyntheticBUOutline1.m("hash type is not set");
        return null;
    L39:
        zzsj$$ExternalSyntheticBUOutline1.m("EC curve type is not set");
        return null;
    L41:
        zzsj$$ExternalSyntheticBUOutline1.m("signature encoding is not set");
        return null;
    }
}
