package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzcv extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzcx zzb;
    final /* synthetic */ long zzc;
    final /* synthetic */ zzakg zzd;
    private /* synthetic */ Object zze;

    public zzcv(zzcx r1, long r2, zzakg r4, Continuation r5) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r4;
        super(2, r5);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r7, Continuation r8) {
        zzcv r0 = new zzcv(this.zzb, this.zzc, this.zzd, r8);
        r0.zze = r7;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzcv) create((zzlp) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r7) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        if (r1 == 0) goto L7;
        if (r1 == 1) goto L6;
        ResultKt.throwOnFailure(r7);
    L13:
        return Unit.INSTANCE;
    L6:
        zzlp r12 = (zzlp) this.zze;
        ResultKt.throwOnFailure(r7);
    L9:
        this.zze = null;
        this.zza = 2;
        if (((zzmc) r7).zza(r12.zza(), this) != r0) goto L13;
    L14:
        return r0;
    L7:
        ResultKt.throwOnFailure(r7);
        r12 = (zzlp) this.zze;
        zzcx r72 = this.zzb;
        long r3 = this.zzc;
        zzakg r5 = this.zzd;
        zzct r73 = zzcx.zzd(r72);
        this.zze = r12;
        this.zza = 1;
        r7 = r73.zzb(r3, r5, this);
        if (r7 == r0) goto L14;
        goto L9
    }
}
