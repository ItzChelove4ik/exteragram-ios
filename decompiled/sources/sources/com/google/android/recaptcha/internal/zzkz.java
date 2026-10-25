package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.TimeoutCancellationException;
import kotlinx.coroutines.TimeoutKt;

/* loaded from: classes5.dex */
final class zzkz extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ long zzb;
    final /* synthetic */ zzlf zzc;
    final /* synthetic */ zzamf zzd;
    private /* synthetic */ Object zze;

    public zzkz(long r1, zzlf r3, zzamf r4, Continuation r5) {
        this.zzb = r1;
        this.zzc = r3;
        this.zzd = r4;
        super(2, r5);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r7, Continuation r8) {
        zzkz r0 = new zzkz(this.zzb, this.zzc, this.zzd, r8);
        r0.zze = r7;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzkz) create((zzlp) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r8) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.zza == 0) goto L12;
        ResultKt.throwOnFailure(r8);     // Catch: Exception -> L6 TimeoutCancellationException -> L8 zzhg -> L10
    L16:
        return (zzakg) r8;
    L12:
        ResultKt.throwOnFailure(r8);
        zzlp r82 = (zzlp) this.zze;
        long r1 = this.zzb;     // Catch: Exception -> L6 TimeoutCancellationException -> L8 zzhg -> L10
        Boxing.boxLong(r1);     // Catch: Exception -> L6 TimeoutCancellationException -> L8 zzhg -> L10
        zzky r3 = new zzky(r82, this.zzc, this.zzd, null);     // Catch: Exception -> L6 TimeoutCancellationException -> L8 zzhg -> L10
        this.zza = 1;     // Catch: Exception -> L6 TimeoutCancellationException -> L8 zzhg -> L10
        r8 = TimeoutKt.withTimeout(r1, r3, this);     // Catch: Exception -> L6 TimeoutCancellationException -> L8 zzhg -> L10
        if (r8 != r0) goto L16;
        return r0;
    L10:
        e = move-exception;
        if (Intrinsics.areEqual(e.zza(), zzhf.zzc) == true) goto L25;
        throw e;
    L25:
        throw zzlf.zzf(this.zzc).zza(e, e);
    L8:
        e = move-exception;
        throw zzlf.zzf(this.zzc).zza(e, new zzhg(zzhf.zzc, zzhe.zzb, e.getMessage(), null, 8, null));
    L6:
        e = move-exception;
        throw zzlf.zzf(this.zzc).zza(e, new zzhg(zzhf.zzc, zzhe.zzL, e.getMessage(), null, 8, null));
    }
}
