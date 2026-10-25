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
final class zzle extends SuspendLambda implements Function2 {
    final /* synthetic */ zzlf zza;
    final /* synthetic */ zzamf zzb;
    final /* synthetic */ long zzc;
    private /* synthetic */ Object zzd;

    public zzle(zzlf r1, zzamf r2, long r3, Continuation r5) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        super(2, r5);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r7, Continuation r8) {
        zzle r0 = new zzle(this.zza, this.zzb, this.zzc, r8);
        r0.zzd = r7;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzle) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r13) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(r13);
        zzmg r1 = (zzmg) this.zzd;
        zzlf r2 = this.zza;
        zzamf r3 = this.zzb;
        long r4 = this.zzc;
        monitor-enter(r1);
    L11:
        th = move-exception;
        throw th;
    L5:
        if (zzlf.zzc(r2) == null) goto L13;
        Deferred r12 = zzlf.zzc(r2);     // Catch: Throwable -> L11
        if (r12 != null) goto L9;
    L14:
        monitor-exit(r1);
        return Unit.INSTANCE;
    L9:
        if (r12.isCompleted() != true) goto L14;
    L13:
        zzlf.zzd(r2, BuildersKt.async$default(zzlf.zzi(r2).zzc(), null, null, new zzld(r1, r2, r3, r4, null), 3, null));     // Catch: Throwable -> L11
        goto L14
    }
}
