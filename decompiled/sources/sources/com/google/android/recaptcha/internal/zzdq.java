package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzdq extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzdv zzb;
    final /* synthetic */ zzalg zzc;

    public zzdq(zzdv r1, zzalg r2, Continuation r3) {
        this.zzb = r1;
        this.zzc = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r2, Continuation r3) {
        return new zzdq(this.zzb, this.zzc, r3);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzdq) create((zzlp) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r4) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.zza == 0) goto L8;
        ResultKt.throwOnFailure(r4);     // Catch: Exception -> L6 Throwable -> L14
    L12:
        return (zzali) r4;
    L8:
        ResultKt.throwOnFailure(r4);
        zzmu r42 = zzdv.zzb(this.zzb);     // Catch: Exception -> L6 Throwable -> L14
        zzalg r1 = this.zzc;     // Catch: Exception -> L6 Throwable -> L14
        this.zza = 1;     // Catch: Exception -> L6 Throwable -> L14
        r4 = r42.zza(r1, this);     // Catch: Exception -> L6 Throwable -> L14
        if (r4 != r0) goto L12;
        return r0;
    L6:
        e = move-exception;
        throw zzdv.zza(this.zzb, e);
    L14:
        th = move-exception;
        throw th;
    }
}
