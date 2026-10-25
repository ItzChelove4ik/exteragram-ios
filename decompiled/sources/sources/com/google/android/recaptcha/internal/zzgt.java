package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Deferred;

/* loaded from: classes5.dex */
final class zzgt extends SuspendLambda implements Function2 {
    final /* synthetic */ zzgy zza;
    private /* synthetic */ Object zzb;

    public zzgt(zzgy r1, Continuation r2) {
        this.zza = r1;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r2, Continuation r3) {
        zzgt r0 = new zzgt(this.zza, r3);
        r0.zzb = r2;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzgt) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r8) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(r8);
        zzmg r82 = (zzmg) this.zzb;
        zzgy r7 = this.zza;
        monitor-enter(r82);
    L11:
        th = move-exception;
        throw th;
    L5:
        if (zzgy.zzd(r7) == null) goto L13;
        Deferred r0 = zzgy.zzd(r7);     // Catch: Throwable -> L11
        if (r0 != null) goto L9;
    L14:
        monitor-exit(r82);
        return Unit.INSTANCE;
    L9:
        if (r0.isCompleted() != true) goto L14;
    L13:
        zzgy.zze(r7, BuildersKt.async$default(r7.zzg().zzc(), null, null, new zzgs(r82, r7, null), 3, null));     // Catch: Throwable -> L11
        goto L14
    }
}
