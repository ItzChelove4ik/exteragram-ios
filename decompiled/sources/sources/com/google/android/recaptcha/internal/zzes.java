package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzes extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzev zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ zzaka zzd;
    private /* synthetic */ Object zze;

    public zzes(zzev r1, String r2, zzaka r3, Continuation r4) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r4, Continuation r5) {
        zzes r0 = new zzes(this.zzb, this.zzc, this.zzd, r5);
        r0.zze = r4;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzes) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r9) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        if (r1 == 0) goto L10;
        if (r1 == 1) goto L6;
        ResultKt.throwOnFailure(r9);
    L19:
        return (zzex) r9;
    L6:
        ResultKt.throwOnFailure(r9);     // Catch: Exception -> L8
    L13:
        return (zzex) r9;
    L10:
        ResultKt.throwOnFailure(r9);
        zzmg r92 = (zzmg) this.zze;
        zzev r12 = this.zzb;     // Catch: Exception -> L8
        Integer r3 = Boxing.boxInt(r12.zza());     // Catch: Exception -> L8
        zzer r4 = new zzer(r12, this.zzc, this.zzd, null);     // Catch: Exception -> L8
        this.zza = 1;     // Catch: Exception -> L8
        r9 = r92.zze(37, r3, r4, this);     // Catch: Exception -> L8
        if (r9 != r0) goto L13;
    L17:
        return r0;
    L8:
        e = move-exception;
        zzev r13 = this.zzb;
        this.zza = 2;
        r9 = r13.zzd(e, this);
        if (r9 != r0) goto L19;
        goto L17
    }
}
