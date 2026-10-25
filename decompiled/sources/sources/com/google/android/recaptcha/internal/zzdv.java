package com.google.android.recaptcha.internal;

import kotlinx.coroutines.TimeoutCancellationException;

/* loaded from: classes5.dex */
public final class zzdv {
    private final zzmu zza;
    private final zzhh zzb;
    private final zzhu zzc;

    public zzdv(zzmu r1, zzhh r2, zzhu r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    public static final /* synthetic */ zzhg zza(zzdv r7, Exception r8) {
        if ((r8 instanceof TimeoutCancellationException) == false) goto L7;
        return r7.zzb.zza(r8, new zzhg(zzhf.zzc, zzhe.zzb, r8.getMessage(), null, 8, null));
    L7:
        if ((r8 instanceof zzhg) == false) goto L11;
        return r7.zzb.zza(r8, (zzhg) r8);
    L11:
        return r7.zzb.zza(r8, new zzhg(zzhf.zzc, zzhe.zzk, r8.getMessage(), null, 8, null));
    }

    public static final /* synthetic */ zzmu zzb(zzdv r0) {
        return r0.zza;
    }

    public static final /* synthetic */ zzhu zzc(zzdv r0) {
        return r0.zzc;
    }
}
