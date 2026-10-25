package com.google.android.recaptcha.internal;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.TimeoutKt;

/* loaded from: classes5.dex */
final class zzci extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzcl zzb;
    final /* synthetic */ long zzc;
    final /* synthetic */ zzakg zzd;
    private /* synthetic */ Object zze;

    public zzci(zzcl r1, long r2, zzakg r4, Continuation r5) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r4;
        super(2, r5);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r7, Continuation r8) {
        zzci r0 = new zzci(this.zzb, this.zzc, this.zzd, r8);
        r0.zze = r7;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzci) create((zzlp) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r12) {
        Object r1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r0 = this.zza;
        if (r0 == 0) goto L12;
        if (r0 == 1) goto L8;
        if (r0 != 2) goto L7;
        ResultKt.throwOnFailure(r12);
    L24:
        zzhg r122 = (zzhg) r12;
        this.zze = r122;
        this.zza = 3;
        if (Unit.INSTANCE != r1) goto L28;
    L26:
        return r1;
    L28:
        throw r122;
    L7:
        zzhg r11 = (zzhg) this.zze;
        ResultKt.throwOnFailure(r12);
        throw r11;
    L8:
        ResultKt.throwOnFailure(r12);     // Catch: Exception -> L10
    L18:
        ResultKt.throwOnFailure(((Result) r12).m2027unboximpl());     // Catch: Exception -> L10
        zzcl.zze(this.zzb, true);     // Catch: Exception -> L10
        return Unit.INSTANCE;
    L12:
        ResultKt.throwOnFailure(r12);
        zzlp r123 = (zzlp) this.zze;
        zzcl r02 = this.zzb;
        if (r02.zza() == true) goto L15;
        long r4 = this.zzc;     // Catch: Exception -> L10
        zzch r6 = new zzch(r123, r02, this.zzd, null);     // Catch: Exception -> L10
        this.zza = 1;     // Catch: Exception -> L10
        r12 = TimeoutKt.withTimeout(r4, r6, this);     // Catch: Exception -> L10
        if (r12 == r1) goto L26;
    L15:
        return Unit.INSTANCE;
    L10:
        e = move-exception;
        zzcl.zze(this.zzb, false);
        this.zza = 2;
        r12 = zzcm.zza(e, new zzhg(zzhf.zzb, zzhe.zzz, e.getMessage(), null, 8, null));
        if (r12 != r1) goto L24;
        goto L24
    }
}
