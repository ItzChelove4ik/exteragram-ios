package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.sync.Mutex;

/* loaded from: classes5.dex */
final class zzdn extends SuspendLambda implements Function2 {
    Object zza;
    Object zzb;
    Object zzc;
    Object zzd;
    Object zze;
    int zzf;
    final /* synthetic */ zzdo zzg;
    final /* synthetic */ String zzh;
    final /* synthetic */ zzcz zzi;
    final /* synthetic */ String zzj;
    private /* synthetic */ Object zzk;

    public zzdn(zzdo r1, String r2, zzcz r3, String r4, Continuation r5) {
        this.zzg = r1;
        this.zzh = r2;
        this.zzi = r3;
        this.zzj = r4;
        super(2, r5);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r7, Continuation r8) {
        zzdn r0 = new zzdn(this.zzg, this.zzh, this.zzi, this.zzj, r8);
        r0.zzk = r7;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzdn) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r23) {
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r0 = this.zzf;
        if (r0 == 0) goto L12;
        if (r0 == 1) goto L10;
        Mutex r1 = (Mutex) this.zzk;
        ResultKt.throwOnFailure(r23);     // Catch: Throwable -> L8
    L20:
        Unit r02 = Unit.INSTANCE;     // Catch: Throwable -> L8
        r1.unlock(null);
        return Unit.INSTANCE;
    L8:
        th = th;
    L28:
        r1.unlock(null);
        throw th;
    L10:
        String r03 = (String) this.zze;
        zzcz r13 = (zzcz) this.zzd;
        String r2 = (String) this.zzc;
        zzdo r3 = (zzdo) this.zzb;
        Mutex r4 = (Mutex) this.zza;
        zzmg r5 = (zzmg) this.zzk;
        ResultKt.throwOnFailure(r23);
        String r19 = r03;
        zzcz r18 = r13;
        Mutex r14 = r4;
    L11:
        String r17 = r2;
        zzdo r16 = r3;
        zzmg r15 = r5;
        zzhp r04 = zzhp.zza;     // Catch: Throwable -> L25
        zzdm r142 = new zzdm(r15, r16, r17, r18, r19, null);     // Catch: Throwable -> L25
        this.zzk = r14;     // Catch: Throwable -> L25
        this.zza = null;     // Catch: Throwable -> L25
        this.zzb = null;     // Catch: Throwable -> L25
        this.zzc = null;     // Catch: Throwable -> L25
        this.zzd = null;     // Catch: Throwable -> L25
        this.zze = null;     // Catch: Throwable -> L25
        this.zzf = 2;     // Catch: Throwable -> L25
        Mutex r143 = r14;
        if (zzhp.zzb(r04, 5, 0, 0, 0.0d, r142, this, 14, null) == r12) goto L30;
        r1 = r143;
    L30:
        return r12;
    L23:
        th = th;
    L27:
        r1 = r143;
    L25:
        th = th;
        r143 = r14;
        goto L27
    L12:
        ResultKt.throwOnFailure(r23);
        r5 = (zzmg) this.zzk;
        r3 = this.zzg;
        r2 = this.zzh;
        zzcz r05 = this.zzi;
        String r42 = this.zzj;
        Mutex r6 = zzdo.zzh(r3);
        this.zzk = r5;
        this.zza = r6;
        this.zzb = r3;
        this.zzc = r2;
        this.zzd = r05;
        this.zze = r42;
        this.zzf = 1;
        if (r6.lock(null, this) == r12) goto L30;
        r18 = r05;
        r19 = r42;
        r14 = r6;
        goto L11
    }
}
