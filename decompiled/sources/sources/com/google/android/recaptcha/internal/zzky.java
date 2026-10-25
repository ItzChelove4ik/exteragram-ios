package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes5.dex */
final class zzky extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzlp zzc;
    final /* synthetic */ zzlf zzd;
    final /* synthetic */ zzamf zze;

    public zzky(zzlp r1, zzlf r2, zzamf r3, Continuation r4) {
        this.zzc = r1;
        this.zzd = r2;
        this.zze = r3;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r3, Continuation r4) {
        return new zzky(this.zzc, this.zzd, this.zze, r4);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzky) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r5) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzb;
        if (r1 == 0) goto L7;
        if (r1 == 1) goto L6;
        ResultKt.throwOnFailure(r5);
    L13:
        return (zzakg) r5;
    L6:
        zzlp r12 = (zzlp) this.zza;
        ResultKt.throwOnFailure(r5);
    L9:
        this.zza = null;
        this.zzb = 2;
        r5 = ((zzmc) r5).zza(r12.zza(), this);
        if (r5 != r0) goto L13;
    L14:
        return r0;
    L7:
        ResultKt.throwOnFailure(r5);
        r12 = this.zzc;
        zzlf r52 = this.zzd;
        zzamf r3 = this.zze;
        this.zza = r12;
        this.zzb = 1;
        r5 = zzlf.zze(r52, r3, this);
        if (r5 == r0) goto L14;
        goto L9
    }
}
