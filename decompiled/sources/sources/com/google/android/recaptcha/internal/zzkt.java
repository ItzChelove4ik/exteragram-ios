package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScopeKt;

/* loaded from: classes5.dex */
final class zzkt extends SuspendLambda implements Function1 {
    Object zza;
    int zzb;
    final /* synthetic */ zzlp zzc;
    final /* synthetic */ zzkw zzd;
    final /* synthetic */ zzala zze;
    final /* synthetic */ Ref.ObjectRef zzf;

    public zzkt(zzlp r1, zzkw r2, zzala r3, Ref.ObjectRef r4, Continuation r5) {
        this.zzc = r1;
        this.zzd = r2;
        this.zze = r3;
        this.zzf = r4;
        super(1, r5);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Continuation r7) {
        return new zzkt(this.zzc, this.zzd, this.zze, this.zzf, r7);
    }

    @Override // kotlin.jvm.functions.Function1
    public final /* bridge */ /* synthetic */ Object invoke(Object r1) {
        return ((zzkt) create((Continuation) r1)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [T, com.google.android.recaptcha.internal.zzhg, java.lang.Throwable] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r12) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzb;
        if (r1 == 0) goto L12;
        if (r1 == 1) goto L9;
        ResultKt.throwOnFailure(r12);     // Catch: zzhg -> L7
    L19:
        zzalc r122 = (zzalc) r12;     // Catch: zzhg -> L7
        if (r122.zza().length() != 0) goto L27;
        zzhf r5 = zzhf.zzb;     // Catch: zzhg -> L7
        zzhe r6 = zzhe.zzam;     // Catch: zzhg -> L7
        int r123 = r122.zzh();     // Catch: zzhg -> L7
        if (r123 != 1) goto L24;
        int r124 = zzagf.zza();     // Catch: zzhg -> L7
    L26:
        throw new zzhg(r5, r6, String.valueOf(r124), null, 8, null);     // Catch: zzhg -> L7
    L24:
        r124 = r123 - 2;
        goto L26
    L27:
        return r122;
    L9:
        zzlp r13 = (zzlp) this.zza;
        ResultKt.throwOnFailure(r12);     // Catch: zzhg -> L7
    L16:
        this.zza = null;     // Catch: zzhg -> L7
        this.zzb = 2;     // Catch: zzhg -> L7
        r12 = ((zzmb) r12).zza(r13.zza(), this);     // Catch: zzhg -> L7
        if (r12 != r0) goto L19;
    L18:
        return r0;
    L12:
        ResultKt.throwOnFailure(r12);
        r13 = this.zzc;     // Catch: zzhg -> L7
        zzkw r125 = this.zzd;     // Catch: zzhg -> L7
        zzala r4 = this.zze;     // Catch: zzhg -> L7
        this.zza = r13;     // Catch: zzhg -> L7
        this.zzb = 1;     // Catch: zzhg -> L7
        r12 = CoroutineScopeKt.coroutineScope(new zzkr(r125, r4, null), this);     // Catch: zzhg -> L7
        if (r12 != r0) goto L16;
    L7:
        e = move-exception;
        this.zzf.element = e;
        throw e;
    }
}
