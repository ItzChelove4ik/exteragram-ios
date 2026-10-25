package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;

/* loaded from: classes5.dex */
final class zzdi extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzlp zzb;
    final /* synthetic */ zzdo zzc;
    final /* synthetic */ long zzd;
    final /* synthetic */ zzakg zze;
    private /* synthetic */ Object zzf;

    public zzdi(zzlp r1, zzdo r2, long r3, zzakg r5, Continuation r6) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        this.zze = r5;
        super(2, r6);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r8, Continuation r9) {
        zzdi r0 = new zzdi(this.zzb, this.zzc, this.zzd, this.zze, r9);
        r0.zzf = r8;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzdi) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r14) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        if (r1 == 0) goto L7;
        if (r1 == 1) goto L6;
        ResultKt.throwOnFailure(r14);
    L13:
        return Unit.INSTANCE;
    L6:
        Deferred r12 = (Deferred) this.zzf;
        ResultKt.throwOnFailure(r14);
    L9:
        this.zzf = null;
        this.zza = 2;
        if (r12.await(this) != r0) goto L13;
    L14:
        return r0;
    L7:
        ResultKt.throwOnFailure(r14);
        CoroutineScope r3 = (CoroutineScope) this.zzf;
        zzlp r5 = this.zzb;
        zzdo r6 = this.zzc;
        long r7 = this.zzd;
        zzakg r9 = this.zze;
        Deferred r122 = BuildersKt.async$default(r3, null, null, new zzdh(r5, r6, r7, r9, null), 3, null);
        r12 = BuildersKt.async$default(r3, null, null, new zzdg(r5, r6, r7, r9, null), 3, null);
        this.zzf = r12;
        this.zza = 1;
        if (r122.await(this) == r0) goto L14;
        goto L9
    }
}
