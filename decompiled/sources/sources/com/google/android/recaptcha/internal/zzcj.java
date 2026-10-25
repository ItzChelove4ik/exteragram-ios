package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes5.dex */
final class zzcj extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzlp zzc;
    final /* synthetic */ zzcl zzd;
    final /* synthetic */ String zze;
    final /* synthetic */ zzaka zzf;

    public zzcj(zzlp r1, zzcl r2, String r3, zzaka r4, Continuation r5) {
        this.zzc = r1;
        this.zzd = r2;
        this.zze = r3;
        this.zzf = r4;
        super(2, r5);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r7, Continuation r8) {
        return new zzcj(this.zzc, this.zzd, this.zze, this.zzf, r8);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzcj) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r6) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzb;
        if (r1 == 0) goto L8;
        if (r1 == 1) goto L7;
        ResultKt.throwOnFailure(r6);
        return r6;
    L7:
        zzlp r12 = (zzlp) this.zza;
        ResultKt.throwOnFailure(r6);
    L10:
        this.zza = null;
        this.zzb = 2;
        Object r5 = ((zzmc) r6).zza(r12.zza(), this);
        if (r5 == r0) goto L14;
        return r5;
    L14:
        return r0;
    L8:
        ResultKt.throwOnFailure(r6);
        r12 = this.zzc;
        zzcl r62 = this.zzd;
        String r3 = this.zze;
        zzaka r4 = this.zzf;
        this.zza = r12;
        this.zzb = 1;
        r6 = r62.zzc(r3, r4, this);
        if (r6 == r0) goto L14;
        goto L10
    }
}
