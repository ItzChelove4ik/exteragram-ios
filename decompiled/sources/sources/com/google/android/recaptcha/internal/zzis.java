package com.google.android.recaptcha.internal;

import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.AwaitKt;
import kotlinx.coroutines.JobKt;

/* loaded from: classes5.dex */
final class zzis extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzdp zzb;
    final /* synthetic */ long zzc;
    final /* synthetic */ zzakg zzd;
    final /* synthetic */ zziv zze;
    private /* synthetic */ Object zzf;

    public zzis(zzdp r1, long r2, zzakg r4, zziv r5, Continuation r6) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r4;
        this.zze = r5;
        super(2, r6);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r8, Continuation r9) {
        zzis r0 = new zzis(this.zzb, this.zzc, this.zzd, this.zze, r9);
        r0.zzf = r8;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzis) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r9) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        if (r1 == 0) goto L14;
        if (r1 == 1) goto L11;
        if (r1 != 2) goto L10;
        ResultKt.throwOnFailure(r9);     // Catch: zzhg -> L8
    L21:
        return Unit.INSTANCE;
    L10:
        zzhg r8 = (zzhg) this.zzf;
        ResultKt.throwOnFailure(r9);
        throw r8;
    L11:
        zzmg r12 = (zzmg) this.zzf;
        ResultKt.throwOnFailure(r9);     // Catch: zzhg -> L8
    L17:
        this.zzf = null;     // Catch: zzhg -> L8
        this.zza = 2;     // Catch: zzhg -> L8
        if (((zzmb) r9).zza(r12, this) != r0) goto L21;
    L24:
        return r0;
    L14:
        ResultKt.throwOnFailure(r9);
        r12 = (zzmg) this.zzf;
        zzdp r92 = this.zzb;     // Catch: zzhg -> L8
        long r5 = this.zzc;     // Catch: zzhg -> L8
        zzakg r7 = this.zzd;     // Catch: zzhg -> L8
        this.zzf = r12;     // Catch: zzhg -> L8
        this.zza = 1;     // Catch: zzhg -> L8
        r9 = r92.zza(r5, r7, this);     // Catch: zzhg -> L8
        if (r9 == r0) goto L24;
    L8:
        e = move-exception;
        zziv r13 = this.zze;
        JobKt.cancelChildren$default(zziv.zzd(r13).zzb().getCoroutineContext(), null, 1, null);
        List r14 = SequencesKt.toList(JobKt.getJob(zziv.zzd(r13).zzb().getCoroutineContext()).getChildren());
        this.zzf = e;
        this.zza = 3;
        if (AwaitKt.joinAll(r14, this) == r0) goto L24;
        throw e;
    }
}
