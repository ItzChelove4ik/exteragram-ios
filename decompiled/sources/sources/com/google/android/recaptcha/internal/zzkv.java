package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;

/* loaded from: classes5.dex */
final class zzkv extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzkw zzb;
    final /* synthetic */ long zzc;
    final /* synthetic */ zzala zzd;
    private /* synthetic */ Object zze;

    public zzkv(zzkw r1, long r2, zzala r4, Continuation r5) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r4;
        super(2, r5);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r7, Continuation r8) {
        zzkv r0 = new zzkv(this.zzb, this.zzc, this.zzd, r8);
        r0.zze = r7;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzkv) create((zzlp) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r9) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        ResultKt.throwOnFailure(r9);
        if (r1 == 0) goto L5;
        return r9;
    L5:
        zzlp r5 = (zzlp) this.zze;
        zzkw r4 = this.zzb;
        CoroutineContext r92 = zzkw.zze(r4).zzc().getCoroutineContext();
        zzku r12 = new zzku(this.zzc, r4, r5, this.zzd, null);
        this.zza = 1;
        Object r8 = BuildersKt.withContext(r92, r12, this);
        if (r8 != r0) goto L8;
        return r0;
    L8:
        return r8;
    }
}
