package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.sync.Mutex;

/* loaded from: classes5.dex */
final class zzgk extends SuspendLambda implements Function2 {
    Object zza;
    Object zzb;
    Object zzc;
    int zzd;
    final /* synthetic */ zzgo zze;
    final /* synthetic */ zzakg zzf;
    private /* synthetic */ Object zzg;

    public zzgk(zzgo r1, zzakg r2, Continuation r3) {
        this.zze = r1;
        this.zzf = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r3, Continuation r4) {
        zzgk r0 = new zzgk(this.zze, this.zzf, r4);
        r0.zzg = r3;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzgk) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r12) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.zzd == 0) goto L5;
        zzakg r02 = (zzakg) this.zzc;
        zzgo r1 = (zzgo) this.zzb;
        Mutex r4 = (Mutex) this.zza;
        zzmg r11 = (zzmg) this.zzg;
        ResultKt.throwOnFailure(r12);
    L21:
    L13:
        th = move-exception;
        r4.unlock(null);
        throw th;
    L9:
        if (zzgo.zzm(r1) == false) goto L15;
        Unit r112 = Unit.INSTANCE;     // Catch: Throwable -> L13
        r4.unlock(null);
        return r112;
    L15:
        r1.zzb = BuildersKt.async$default(zzgo.zzC(r1).zzc(), null, null, new zzgj(r11, r1, r02, null), 3, null);     // Catch: Throwable -> L13
        zzgo.zzn(r1, true);     // Catch: Throwable -> L13
        Unit r113 = Unit.INSTANCE;     // Catch: Throwable -> L13
        r4.unlock(null);
        return Unit.INSTANCE;
    L5:
        ResultKt.throwOnFailure(r12);
        zzmg r122 = (zzmg) this.zzg;
        r1 = this.zze;
        zzakg r42 = this.zzf;
        Mutex r5 = zzgo.zzl(r1);
        this.zzg = r122;
        this.zza = r5;
        this.zzb = r1;
        this.zzc = r42;
        this.zzd = 1;
        if (r5.lock(null, this) == r0) goto L20;
        r11 = r122;
        r02 = r42;
        r4 = r5;
        goto L21
    L20:
        return r0;
    }
}
