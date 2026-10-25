package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.sync.Mutex;

/* loaded from: classes5.dex */
final class zzfo extends SuspendLambda implements Function2 {
    Object zza;
    Object zzb;
    int zzc;
    final /* synthetic */ zzfp zzd;
    private /* synthetic */ Object zze;

    public zzfo(zzfp r1, Continuation r2) {
        this.zzd = r1;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r2, Continuation r3) {
        zzfo r0 = new zzfo(this.zzd, r3);
        r0.zze = r2;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzfo) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r13) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzc;
        if (r1 == 0) goto L9;
        if (r1 == 1) goto L8;
        if (r1 == 2) goto L7;
        ResultKt.throwOnFailure(r13);
    L26:
        return Unit.INSTANCE;
    L7:
        zzmg r12 = (zzmg) this.zze;
        ResultKt.throwOnFailure(r13);
    L22:
        this.zze = null;
        this.zzc = 3;
        if (((zzmc) r13).zza(r12, this) != r0) goto L26;
    L29:
        return r0;
    L8:
        zzfp r14 = (zzfp) this.zzb;
        Mutex r5 = (Mutex) this.zza;
        zzmg r6 = (zzmg) this.zze;
        ResultKt.throwOnFailure(r13);
        zzmg r132 = r6;
    L30:
    L16:
        th = move-exception;
        r5.unlock(null);
        throw th;
    L12:
        if (Intrinsics.areEqual(zzfp.zze(r14), zzfq.zza) == true) goto L18;
        Unit r122 = Unit.INSTANCE;     // Catch: Throwable -> L16
        r5.unlock(null);
        return r122;
    L18:
        zzfp.zzf(r14, zzfq.zzb);     // Catch: Throwable -> L16
        r14.zza = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);     // Catch: Throwable -> L16
        Unit r15 = Unit.INSTANCE;     // Catch: Throwable -> L16
        r5.unlock(null);
        zzfp r16 = this.zzd;
        BuildersKt.launch$default(zzfp.zzk(r16).zzd(), null, null, new zzfn(r132, r16, null), 3, null);
        this.zze = r132;
        this.zza = null;
        this.zzb = null;
        this.zzc = 2;
        zzmc r17 = new zzmc(new zzfm(r16, null));
        if (r17 == r0) goto L29;
        r12 = r132;
        r13 = r17;
        goto L22
    L9:
        ResultKt.throwOnFailure(r13);
        r132 = (zzmg) this.zze;
        r14 = this.zzd;
        r5 = zzfp.zzd(r14);
        this.zze = r132;
        this.zza = r5;
        this.zzb = r14;
        this.zzc = 1;
        if (r5.lock(null, this) == r0) goto L29;
        goto L29
    }
}
