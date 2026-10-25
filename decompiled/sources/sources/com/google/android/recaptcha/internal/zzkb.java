package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CompletableDeferred;

/* loaded from: classes5.dex */
final class zzkb extends SuspendLambda implements Function1 {
    int zza;
    final /* synthetic */ zzmg zzb;
    final /* synthetic */ zzke zzc;
    final /* synthetic */ long zzd;
    final /* synthetic */ CompletableDeferred zze;

    public zzkb(zzmg r1, zzke r2, long r3, CompletableDeferred r5, Continuation r6) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        this.zze = r5;
        super(1, r6);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Continuation r8) {
        return new zzkb(this.zzb, this.zzc, this.zzd, this.zze, r8);
    }

    @Override // kotlin.jvm.functions.Function1
    public final /* bridge */ /* synthetic */ Object invoke(Object r1) {
        return ((zzkb) create((Continuation) r1)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r8) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        ResultKt.throwOnFailure(r8);
        if (r1 == 0) goto L5;
        return r8;
    L5:
        zzmg r82 = this.zzb;
        zzka r12 = new zzka(this.zzc, this.zzd, this.zze, null);
        this.zza = 1;
        Object r7 = r82.zze(41, null, r12, this);
        if (r7 != r0) goto L8;
        return r0;
    L8:
        return r7;
    }
}
