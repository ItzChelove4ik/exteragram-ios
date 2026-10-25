package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzcu extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzcx zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ zzaka zzd;
    final /* synthetic */ long zze;
    private /* synthetic */ Object zzf;

    public zzcu(zzcx r1, String r2, zzaka r3, long r4, Continuation r6) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        this.zze = r4;
        super(2, r6);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r8, Continuation r9) {
        zzcu r0 = new zzcu(this.zzb, this.zzc, this.zzd, this.zze, r9);
        r0.zzf = r8;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzcu) create((zzlp) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r10) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        if (r1 == 0) goto L8;
        if (r1 == 1) goto L7;
        ResultKt.throwOnFailure(r10);
        return r10;
    L7:
        zzlp r12 = (zzlp) this.zzf;
        ResultKt.throwOnFailure(r10);
        zzcu r8 = this;
    L10:
        r8.zzf = null;
        r8.zza = 2;
        Object r9 = ((zzmc) r10).zza(r12.zza(), r8);
        if (r9 == r0) goto L14;
        return r9;
    L14:
        return r0;
    L8:
        ResultKt.throwOnFailure(r10);
        r12 = (zzlp) this.zzf;
        zzcx r102 = this.zzb;
        String r4 = this.zzc;
        zzaka r5 = this.zzd;
        long r6 = this.zze;
        zzct r3 = zzcx.zzd(r102);
        this.zzf = r12;
        this.zza = 1;
        r8 = this;
        r10 = r3.zzc(r4, r5, r6, r8);
        if (r10 == r0) goto L14;
        goto L10
    }
}
