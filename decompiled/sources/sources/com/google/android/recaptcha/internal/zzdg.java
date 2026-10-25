package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes5.dex */
final class zzdg extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzlp zzc;
    final /* synthetic */ zzdo zzd;
    final /* synthetic */ long zze;
    final /* synthetic */ zzakg zzf;

    public zzdg(zzlp r1, zzdo r2, long r3, zzakg r5, Continuation r6) {
        this.zzc = r1;
        this.zzd = r2;
        this.zze = r3;
        this.zzf = r5;
        super(2, r6);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r8, Continuation r9) {
        return new zzdg(this.zzc, this.zzd, this.zze, this.zzf, r9);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzdg) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r7) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzb;
        if (r1 == 0) goto L7;
        if (r1 == 1) goto L6;
        ResultKt.throwOnFailure(r7);
    L13:
        return Unit.INSTANCE;
    L6:
        zzlp r12 = (zzlp) this.zza;
        ResultKt.throwOnFailure(r7);
    L9:
        this.zza = null;
        this.zzb = 2;
        if (((zzmc) r7).zza(r12.zza(), this) != r0) goto L13;
    L14:
        return r0;
    L7:
        ResultKt.throwOnFailure(r7);
        r12 = this.zzc;
        zzdo r72 = this.zzd;
        long r3 = this.zze;
        zzakg r5 = this.zzf;
        zzct r73 = zzdo.zze(r72);
        this.zza = r12;
        this.zzb = 1;
        r7 = r73.zzb(r3, r5, this);
        if (r7 == r0) goto L14;
        goto L9
    }
}
