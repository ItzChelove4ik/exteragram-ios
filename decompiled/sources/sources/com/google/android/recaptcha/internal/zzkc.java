package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes5.dex */
final class zzkc extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzke zzb;
    final /* synthetic */ CompletableDeferred zzc;
    final /* synthetic */ zzmg zzd;
    final /* synthetic */ long zze;

    public zzkc(zzke r1, CompletableDeferred r2, zzmg r3, long r4, Continuation r6) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        this.zze = r4;
        super(2, r6);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r8, Continuation r9) {
        return new zzkc(this.zzb, this.zzc, this.zzd, this.zze, r9);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzkc) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r14) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.zza != 0) goto L27;
        ResultKt.throwOnFailure(r14);
        zzhp r1 = zzhp.zza;     // Catch: zzhg -> L18
        zzke r5 = this.zzb;     // Catch: zzhg -> L18
        zzjz r2 = new zzjz(r5);     // Catch: zzhg -> L18
        zzkb r3 = new zzkb(this.zzd, r5, this.zze, this.zzc, null);     // Catch: zzhg -> L18
        this.zza = 1;     // Catch: zzhg -> L18
        zzkc r10 = this;
        r14 = zzhp.zzd(r1, r2, 0, 0, 0.0d, r3, r10, 14, null);     // Catch: zzhg -> L16
        if (r14 != r0) goto L14;
        return r0;
    L14:
        ((Boolean) r14).getClass();     // Catch: zzhg -> L16
    L22:
        return Unit.INSTANCE;
    L16:
        zzhg e = e;
    L17:
        zzhg r142 = e;
    L20:
        zzke.zzg(r10.zzb, zzkm.zzd());
        r10.zzc.completeExceptionally(r142);
    L18:
        e = e;
        r10 = this;
        goto L17
    L27:
        ResultKt.throwOnFailure(r14);     // Catch: zzhg -> L6
        r10 = this;
    L6:
        e = move-exception;
        r142 = e;
        r10 = this;
        goto L20
    }
}
