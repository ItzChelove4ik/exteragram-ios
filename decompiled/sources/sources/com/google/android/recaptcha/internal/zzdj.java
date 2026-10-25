package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScopeKt;

/* loaded from: classes5.dex */
final class zzdj extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzdo zzb;
    final /* synthetic */ long zzc;
    final /* synthetic */ zzakg zzd;
    private /* synthetic */ Object zze;

    public zzdj(zzdo r1, long r2, zzakg r4, Continuation r5) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r4;
        super(2, r5);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r7, Continuation r8) {
        zzdj r0 = new zzdj(this.zzb, this.zzc, this.zzd, r8);
        r0.zze = r7;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzdj) create((zzlp) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r9) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        ResultKt.throwOnFailure(r9);
        if (r1 != 0) goto L9;
        zzdi r12 = new zzdi((zzlp) this.zze, this.zzb, this.zzc, this.zzd, null);
        this.zza = 1;
        if (CoroutineScopeKt.coroutineScope(r12, this) != r0) goto L9;
        return r0;
    L9:
        return Unit.INSTANCE;
    }
}
