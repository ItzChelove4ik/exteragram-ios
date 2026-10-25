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
final class zzck extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ long zzb;
    final /* synthetic */ zzcl zzc;
    final /* synthetic */ String zzd;
    final /* synthetic */ zzaka zze;
    private /* synthetic */ Object zzf;

    public zzck(long r1, zzcl r3, String r4, zzaka r5, Continuation r6) {
        this.zzb = r1;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        super(2, r6);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r8, Continuation r9) {
        zzck r0 = new zzck(this.zzb, this.zzc, this.zzd, this.zze, r9);
        r0.zzf = r8;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzck) create((zzlp) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r12) {
        Object r1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r0 = this.zza;
        if (r0 == 0) goto L10;
        if (r0 != 1) goto L9;
        ResultKt.throwOnFailure(r12);     // Catch: Exception -> L7
    L13:
        Object r122 = ((Result) r12).m2027unboximpl();     // Catch: Exception -> L7
        ResultKt.throwOnFailure(r122);     // Catch: Exception -> L7
        return (zzaku) r122;
    L9:
        zzhg r11 = (zzhg) this.zzf;
        ResultKt.throwOnFailure(r12);
        throw r11;
    L10:
        ResultKt.throwOnFailure(r12);
        zzlp r4 = (zzlp) this.zzf;
        long r9 = this.zzb;     // Catch: Exception -> L7
        zzcj r3 = new zzcj(r4, this.zzc, this.zzd, this.zze, null);     // Catch: Exception -> L7
        this.zza = 1;     // Catch: Exception -> L7
        r12 = TimeoutKt.withTimeout(r9, r3, this);     // Catch: Exception -> L7
        if (r12 != r1) goto L13;
    L17:
        return r1;
    L7:
        e = move-exception;
        zzhg r123 = zzcm.zza(e, new zzhg(zzhf.zzb, zzhe.zzt, e.getMessage(), null, 8, null));
        this.zzf = r123;
        this.zza = 2;
        if (Unit.INSTANCE == r1) goto L17;
        throw r123;
    }
}
