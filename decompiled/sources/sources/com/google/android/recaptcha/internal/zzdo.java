package com.google.android.recaptcha.internal;

import kotlin.coroutines.Continuation;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* loaded from: classes5.dex */
public final class zzdo implements zzdp {
    private final zzcx zza;
    private final zzct zzb;
    private final zzng zzc;
    private final zzdv zzd;
    private final Mutex zze;
    private final zzhu zzf;

    public zzdo(zzcx r1, zzct r2, zzng r3, zzdv r4, zzhu r5) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zzf = r5;
        this.zze = MutexKt.Mutex$default(false, 1, null);
    }

    public static final /* synthetic */ zzcx zzd(zzdo r0) {
        return r0.zza;
    }

    public static final /* synthetic */ zzct zze(zzdo r0) {
        return r0.zzb;
    }

    public static final /* synthetic */ Object zzf(zzdo r1, String r2, zzaka r3, Continuation r4) {
        return new zzmb(67, new zzda(r1, r2, r3, null), null);
    }

    public static final /* synthetic */ Object zzg(zzdo r6, String r7, String r8, zzcz r9, Continuation r10) {
        return new zzmc(new zzdn(r6, r7, r9, r8, null));
    }

    public static final /* synthetic */ Mutex zzh(zzdo r0) {
        return r0.zze;
    }

    public static final /* synthetic */ zzdv zzi(zzdo r0) {
        return r0.zzd;
    }

    public static final /* synthetic */ zzng zzj(zzdo r0) {
        return r0.zzc;
    }

    public static final /* synthetic */ zzhu zzk(zzdo r0) {
        return r0.zzf;
    }

    @Override // com.google.android.recaptcha.internal.zzdp
    public final Object zza(long r7, zzakg r9, Continuation r10) {
        return new zzmb(65, new zzdj(this, r7, r9, null), null);
    }

    @Override // com.google.android.recaptcha.internal.zzdp
    public final Object zzb(String r9, String r10, zzaka r11, long r12, Continuation r14) {
        return new zzmb(66, new zzdd(this, r9, r10, r11, r12, null), null);
    }

    @Override // com.google.android.recaptcha.internal.zzdp
    public final Object zzc(String r1, String r2, zzaka r3, Continuation r4) {
        return new zzmc(new zzdl(this, r1, r2, null));
    }
}
