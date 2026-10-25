package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.sync.Mutex;

/* loaded from: classes5.dex */
final class zznu extends SuspendLambda implements Function2 {
    Object zza;
    Object zzb;
    int zzc;
    final /* synthetic */ zzoa zzd;
    private /* synthetic */ Object zze;

    public zznu(zzoa r1, Continuation r2) {
        this.zzd = r1;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r2, Continuation r3) {
        zznu r0 = new zznu(this.zzd, r3);
        r0.zze = r2;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zznu) create((zzlp) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r7) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzc;
        if (r1 == 0) goto L11;
        if (r1 == 1) goto L10;
        Mutex r6 = (Mutex) this.zze;
        ResultKt.throwOnFailure(r7);     // Catch: Throwable -> L8
    L16:
        zzagx r72 = (zzagx) r7;     // Catch: Throwable -> L8
        r6.unlock(null);
        return r72;
    L8:
        Throwable th = th;
    L21:
        r6.unlock(null);
        throw th;
    L10:
        zzoa r12 = (zzoa) this.zzb;
        Mutex r2 = (Mutex) this.zza;
        zzlp r4 = (zzlp) this.zze;
        ResultKt.throwOnFailure(r7);
        Mutex r73 = r2;
    L26:
        String r22 = r4.zza().zza().zza();     // Catch: Throwable -> L19
        this.zze = r73;     // Catch: Throwable -> L19
        this.zza = null;     // Catch: Throwable -> L19
        this.zzb = null;     // Catch: Throwable -> L19
        this.zzc = 2;     // Catch: Throwable -> L19
        Object r62 = zzoa.zzk(r12, r22, this);     // Catch: Throwable -> L19
        if (r62 == r0) goto L23;
        Mutex r5 = r73;
        r7 = r62;
        r6 = r5;
    L23:
        return r0;
    L19:
        th = move-exception;
        Mutex r52 = r73;
        th = th;
        r6 = r52;
        goto L21
    L11:
        ResultKt.throwOnFailure(r7);
        r4 = (zzlp) this.zze;
        r12 = this.zzd;
        r73 = r12.zzd();
        this.zze = r4;
        this.zza = r73;
        this.zzb = r12;
        this.zzc = 1;
        if (r73.lock(null, this) == r0) goto L23;
        goto L23
    }
}
