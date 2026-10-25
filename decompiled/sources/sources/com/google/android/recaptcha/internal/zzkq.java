package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzkq extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzkw zzb;
    final /* synthetic */ zzala zzc;

    public zzkq(zzkw r1, zzala r2, Continuation r3) {
        this.zzb = r1;
        this.zzc = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r2, Continuation r3) {
        return new zzkq(this.zzb, this.zzc, r3);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzkq) create((zzlp) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r5) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.zza == 0) goto L8;
        ResultKt.throwOnFailure(r5);     // Catch: Exception -> L6 Throwable -> L14
    L12:
        return (zzalc) r5;
    L8:
        ResultKt.throwOnFailure(r5);
        zzkw r52 = this.zzb;     // Catch: Exception -> L6 Throwable -> L14
        zzmw r1 = zzkw.zzc(r52);     // Catch: Exception -> L6 Throwable -> L14
        zzhq r53 = zzkw.zzd(r52);     // Catch: Exception -> L6 Throwable -> L14
        zzala r2 = this.zzc;     // Catch: Exception -> L6 Throwable -> L14
        this.zza = 1;     // Catch: Exception -> L6 Throwable -> L14
        r5 = r1.zza(r53, r2, this);     // Catch: Exception -> L6 Throwable -> L14
        if (r5 != r0) goto L12;
        return r0;
    L6:
        e = move-exception;
        throw zzkw.zzb(this.zzb, e);
    L14:
        th = move-exception;
        throw th;
    }
}
