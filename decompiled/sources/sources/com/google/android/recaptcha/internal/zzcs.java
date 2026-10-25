package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScopeKt;

/* loaded from: classes5.dex */
final class zzcs extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzct zzb;
    final /* synthetic */ long zzc;
    final /* synthetic */ zzakg zzd;
    private /* synthetic */ Object zze;

    public zzcs(zzct r1, long r2, zzakg r4, Continuation r5) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r4;
        super(2, r5);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r7, Continuation r8) {
        zzcs r0 = new zzcs(this.zzb, this.zzc, this.zzd, r8);
        r0.zze = r7;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzcs) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r9) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        ResultKt.throwOnFailure(r9);
        if (r1 != 0) goto L9;
        zzmg r3 = (zzmg) this.zze;
        zzcr r12 = new zzcr(this.zzb, r3, this.zzc, this.zzd, null);
        this.zza = 1;
        if (CoroutineScopeKt.coroutineScope(r12, this) != r0) goto L9;
        return r0;
    L9:
        return Unit.INSTANCE;
    }
}
