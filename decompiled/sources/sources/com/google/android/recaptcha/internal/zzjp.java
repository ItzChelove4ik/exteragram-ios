package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzjp extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzjt zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ long zzd;
    final /* synthetic */ zziw zze;
    private /* synthetic */ Object zzf;

    public zzjp(zzjt r1, String r2, long r3, zziw r5, Continuation r6) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        this.zze = r5;
        super(2, r6);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r8, Continuation r9) {
        zzjp r0 = new zzjp(this.zzb, this.zzc, this.zzd, this.zze, r9);
        r0.zzf = r8;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzjp) create((zzmd) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r10) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        ResultKt.throwOnFailure(r10);
        if (r1 == 0) goto L5;
        return r10;
    L5:
        zzmd r7 = (zzmd) this.zzf;
        zzjo r12 = new zzjo(this.zzb, this.zzc, this.zzd, this.zze, r7, null);
        this.zza = 1;
        Object r9 = r7.zzc(r12, this);
        if (r9 != r0) goto L8;
        return r0;
    L8:
        return r9;
    }
}
