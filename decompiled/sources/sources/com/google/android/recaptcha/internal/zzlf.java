package com.google.android.recaptcha.internal;

import kotlin.coroutines.Continuation;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Deferred;

/* loaded from: classes5.dex */
public final class zzlf implements zzkx {
    private final zzmt zza;
    private final zzhq zzb;
    private final zzon zzc;
    private final zzhh zzd;
    private Deferred zze;
    private final zzhu zzf;

    public zzlf(zzmt r1, zzhq r2, zzon r3, zzhu r4, zzhh r5) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzf = r4;
        this.zzd = r5;
    }

    public static final /* synthetic */ zzon zzb(zzlf r0) {
        return r0.zzc;
    }

    public static final /* synthetic */ Deferred zzc(zzlf r0) {
        return r0.zze;
    }

    public static final /* synthetic */ void zzd(zzlf r0, Deferred r1) {
        r0.zze = r1;
    }

    public static final /* synthetic */ Object zze(zzlf r3, zzamf r4, Continuation r5) {
        return BuildersKt.withContext(r3.zzf.zzc().getCoroutineContext(), new zzlc(r3, r4, null), r5);
    }

    public static final /* synthetic */ zzhh zzf(zzlf r0) {
        return r0.zzd;
    }

    public static final /* synthetic */ zzmt zzg(zzlf r0) {
        return r0.zza;
    }

    public static final /* synthetic */ zzhq zzh(zzlf r0) {
        return r0.zzb;
    }

    public static final /* synthetic */ zzhu zzi(zzlf r0) {
        return r0.zzf;
    }

    @Override // com.google.android.recaptcha.internal.zzkx
    public final Object zza(zzamf r7, long r8, Continuation r10) {
        return new zzmb(22, new zzla(this, r7, r8, null), null);
    }
}
