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
final class zzds extends SuspendLambda implements Function1 {
    Object zza;
    int zzb;
    final /* synthetic */ zzlp zzc;
    final /* synthetic */ zzdv zzd;
    final /* synthetic */ zzalg zze;
    final /* synthetic */ Ref.ObjectRef zzf;

    public zzds(zzlp r1, zzdv r2, zzalg r3, Ref.ObjectRef r4, Continuation r5) {
        this.zzc = r1;
        this.zzd = r2;
        this.zze = r3;
        this.zzf = r4;
        super(1, r5);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Continuation r7) {
        return new zzds(this.zzc, this.zzd, this.zze, this.zzf, r7);
    }

    @Override // kotlin.jvm.functions.Function1
    public final /* bridge */ /* synthetic */ Object invoke(Object r1) {
        return ((zzds) create((Continuation) r1)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r6v1, types: [T, com.google.android.recaptcha.internal.zzhg, java.lang.Throwable] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r6) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzb;
        if (r1 == 0) goto L12;
        if (r1 == 1) goto L9;
        ResultKt.throwOnFailure(r6);     // Catch: zzhg -> L7
    L19:
        return (zzali) r6;
    L9:
        zzlp r12 = (zzlp) this.zza;
        ResultKt.throwOnFailure(r6);     // Catch: zzhg -> L7
    L16:
        this.zza = null;     // Catch: zzhg -> L7
        this.zzb = 2;     // Catch: zzhg -> L7
        r6 = ((zzmb) r6).zza(r12.zza(), this);     // Catch: zzhg -> L7
        if (r6 != r0) goto L19;
    L18:
        return r0;
    L12:
        ResultKt.throwOnFailure(r6);
        r12 = this.zzc;     // Catch: zzhg -> L7
        zzdv r62 = this.zzd;     // Catch: zzhg -> L7
        zzalg r4 = this.zze;     // Catch: zzhg -> L7
        this.zza = r12;     // Catch: zzhg -> L7
        this.zzb = 1;     // Catch: zzhg -> L7
        r6 = CoroutineScopeKt.coroutineScope(new zzdr(r62, r4, null), this);     // Catch: zzhg -> L7
        if (r6 != r0) goto L16;
    L7:
        e = move-exception;
        this.zzf.element = e;
        throw e;
    }
}
