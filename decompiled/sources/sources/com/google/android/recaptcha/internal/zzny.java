package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.sync.Mutex;

/* loaded from: classes5.dex */
final class zzny extends SuspendLambda implements Function2 {
    Object zza;
    Object zzb;
    Object zzc;
    int zzd;
    final /* synthetic */ zzoa zze;
    final /* synthetic */ zzagx zzf;
    private /* synthetic */ Object zzg;

    public zzny(zzoa r1, zzagx r2, Continuation r3) {
        this.zze = r1;
        this.zzf = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r3, Continuation r4) {
        zzny r0 = new zzny(this.zze, this.zzf, r4);
        r0.zzg = r3;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzny) create((zzlp) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r7) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzd;
        if (r1 == 0) goto L11;
        if (r1 == 1) goto L10;
        Mutex r6 = (Mutex) this.zzg;
        ResultKt.throwOnFailure(r7);     // Catch: Throwable -> L8
    L17:
        Unit r72 = Unit.INSTANCE;     // Catch: Throwable -> L8
        r6.unlock(null);
        return Unit.INSTANCE;
    L8:
        Throwable th = th;
    L22:
        r6.unlock(null);
        throw th;
    L10:
        zzagx r12 = (zzagx) this.zzc;
        zzoa r2 = (zzoa) this.zzb;
        Mutex r4 = (Mutex) this.zza;
        zzlp r5 = (zzlp) this.zzg;
        ResultKt.throwOnFailure(r7);
    L25:
        String r73 = r5.zza().zza().zza();     // Catch: Throwable -> L20
        this.zzg = r4;     // Catch: Throwable -> L20
        this.zza = null;     // Catch: Throwable -> L20
        this.zzb = null;     // Catch: Throwable -> L20
        this.zzc = null;     // Catch: Throwable -> L20
        this.zzd = 2;     // Catch: Throwable -> L20
        if (zzoa.zzj(r2, r12, r73, this) == r0) goto L24;
        r6 = r4;
    L24:
        return r0;
    L20:
        th = move-exception;
        th = th;
        r6 = r4;
        goto L22
    L11:
        ResultKt.throwOnFailure(r7);
        r5 = (zzlp) this.zzg;
        zzoa r74 = this.zze;
        r12 = this.zzf;
        r4 = r74.zzd();
        this.zzg = r5;
        this.zza = r4;
        this.zzb = r74;
        this.zzc = r12;
        this.zzd = 1;
        if (r4.lock(null, this) == r0) goto L24;
        r2 = r74;
        goto L25
    }
}
