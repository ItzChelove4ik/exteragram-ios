package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.sync.Mutex;

/* loaded from: classes5.dex */
final class zznh extends SuspendLambda implements Function2 {
    Object zza;
    Object zzb;
    Object zzc;
    int zzd;
    final /* synthetic */ zzni zze;
    final /* synthetic */ String zzf;

    public zznh(zzni r1, String r2, Continuation r3) {
        this.zze = r1;
        this.zzf = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r2, Continuation r3) {
        return new zznh(this.zze, this.zzf, r3);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zznh) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r8) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzd;
        if (r1 == 0) goto L11;
        if (r1 == 1) goto L10;
        Mutex r7 = (Mutex) this.zza;
        ResultKt.throwOnFailure(r8);     // Catch: Throwable -> L8
    L17:
        Unit r82 = Unit.INSTANCE;     // Catch: Throwable -> L8
        r7.unlock(null);
        return Unit.INSTANCE;
    L8:
        Throwable th = th;
    L22:
        r7.unlock(null);
        throw th;
    L10:
        String r12 = (String) this.zzc;
        zzni r2 = (zzni) this.zzb;
        Mutex r4 = (Mutex) this.zza;
        ResultKt.throwOnFailure(r8);
    L27:
        zzne r83 = r2.zza();     // Catch: Throwable -> L20
        String r22 = r2.zzc();     // Catch: Throwable -> L20
        StringBuilder r6 = new StringBuilder(r22.length() + String.valueOf(r12).length());     // Catch: Throwable -> L20
        r6.append(r22);     // Catch: Throwable -> L20
        r6.append(r12);     // Catch: Throwable -> L20
        String r13 = r6.toString();     // Catch: Throwable -> L20
        this.zza = r4;     // Catch: Throwable -> L20
        this.zzb = null;     // Catch: Throwable -> L20
        this.zzc = null;     // Catch: Throwable -> L20
        this.zzd = 2;     // Catch: Throwable -> L20
        if (r83.zza(r13, this) == r0) goto L24;
        r7 = r4;
    L24:
        return r0;
    L20:
        th = move-exception;
        th = th;
        r7 = r4;
        goto L22
    L11:
        ResultKt.throwOnFailure(r8);
        zzni r84 = this.zze;
        r12 = this.zzf;
        r4 = r84.zzd();
        this.zza = r4;
        this.zzb = r84;
        this.zzc = r12;
        this.zzd = 1;
        if (r4.lock(null, this) == r0) goto L24;
        r2 = r84;
        goto L27
    }
}
