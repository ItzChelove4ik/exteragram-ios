package com.google.android.recaptcha.internal;

import org.chromium.net.UrlResponseInfo;

/* loaded from: classes5.dex */
public final class zznb {
    private final UrlResponseInfo zza;
    private final byte[] zzb;

    public zznb(UrlResponseInfo r1, byte[] r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    public final byte[] zza() {
        return this.zzb;
    }

    public final void zzb() {
        int r12 = this.zza.getHttpStatusCode();     // Catch: Exception -> L20
        if (r12 != 200) goto L6;
        return;
    L6:
        if (r12 == 400) goto L18;
        if (r12 == 503) goto L28;
        if (r12 == 403) goto L27;
        if (r12 == 404) goto L26;
        throw new zzhg(zzhf.zzc, zzhe.zzo, null, null, 12, null);
    L26:
        throw new zzhg(zzhf.zzc, zzhe.zzi, null, null, 12, null);
    L27:
        throw new zzhg(zzhf.zzi, zzhe.zzn, null, null, 12, null);
    L28:
        throw new zzhg(zzhf.zzi, zzhe.zzn, null, null, 12, null);
    L18:
        throw new zzhg(zzhf.zzc, zzhe.zzK, null, null, 12, null);
    L20:
        e = move-exception;
        throw new zzhg(zzhf.zzc, zzhe.zzv, e.getMessage(), null, 8, null);
    }

    public final int zzc() {
        return this.zza.getHttpStatusCode();
    }

    public final zzagx zzd(zzagx r9) {
        zzb();
        byte[] r8 = this.zzb;
        if (r8.length == 0) goto L10;
        return (zzagx) r9.zzB().zzb(r8);
    L6:
        e = move-exception;
        throw new zzhg(zzhf.zzc, zzhe.zzl, e.getMessage(), null, 8, null);
    L10:
        throw new zzhg(zzhf.zzc, zzhe.zzJ, null, null, 12, null);
    }
}
