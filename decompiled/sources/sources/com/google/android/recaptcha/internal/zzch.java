package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes5.dex */
final class zzch extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzlp zzc;
    final /* synthetic */ zzcl zzd;
    final /* synthetic */ zzakg zze;

    public zzch(zzlp r1, zzcl r2, zzakg r3, Continuation r4) {
        this.zzc = r1;
        this.zzd = r2;
        this.zze = r3;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r3, Continuation r4) {
        return new zzch(this.zzc, this.zzd, this.zze, r4);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzch) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r5) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzb;
        if (r1 == 0) goto L8;
        if (r1 == 1) goto L7;
        ResultKt.throwOnFailure(r5);
        return r5;
    L7:
        zzlp r12 = (zzlp) this.zza;
        ResultKt.throwOnFailure(r5);
    L10:
        this.zza = null;
        this.zzb = 2;
        Object r4 = ((zzmc) r5).zza(r12.zza(), this);
        if (r4 == r0) goto L14;
        return r4;
    L14:
        return r0;
    L8:
        ResultKt.throwOnFailure(r5);
        r12 = this.zzc;
        zzcl r52 = this.zzd;
        zzakg r3 = this.zze;
        this.zza = r12;
        this.zzb = 1;
        r5 = r52.zzb(r3, this);
        if (r5 == r0) goto L14;
        goto L10
    }
}
