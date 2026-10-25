package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.TimeoutCancellationException;

/* loaded from: classes5.dex */
final class zzil extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzdp zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ zzakg zzd;
    final /* synthetic */ zzaka zze;
    final /* synthetic */ long zzf;
    private /* synthetic */ Object zzg;

    public zzil(zzdp r1, String r2, zzakg r3, zzaka r4, long r5, Continuation r7) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        this.zze = r4;
        this.zzf = r5;
        super(2, r7);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r9, Continuation r10) {
        zzil r0 = new zzil(this.zzb, this.zzc, this.zzd, this.zze, this.zzf, r10);
        r0.zzg = r9;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzil) create((zzlp) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r11) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        if (r1 == 0) goto L10;
        if (r1 == 1) goto L7;
        ResultKt.throwOnFailure(r11);     // Catch: Exception -> L19 TimeoutCancellationException -> L22 zzhg -> L25
    L16:
        return (zzaku) r11;
    L7:
        zzlp r12 = (zzlp) this.zzg;
        ResultKt.throwOnFailure(r11);     // Catch: Exception -> L19 TimeoutCancellationException -> L22 zzhg -> L25
        zzil r9 = this;
    L13:
        r9.zzg = null;     // Catch: Exception -> L19 TimeoutCancellationException -> L22 zzhg -> L25
        r9.zza = 2;     // Catch: Exception -> L19 TimeoutCancellationException -> L22 zzhg -> L25
        r11 = ((zzmb) r11).zza(r12.zza(), r9);     // Catch: Exception -> L19 TimeoutCancellationException -> L22 zzhg -> L25
        if (r11 != r0) goto L16;
    L18:
        return r0;
    L10:
        ResultKt.throwOnFailure(r11);
        r12 = (zzlp) this.zzg;
        zzdp r3 = this.zzb;     // Catch: Exception -> L19 TimeoutCancellationException -> L22 zzhg -> L25
        String r4 = this.zzc;     // Catch: Exception -> L19 TimeoutCancellationException -> L22 zzhg -> L25
        String r5 = this.zzd.zzb();     // Catch: Exception -> L19 TimeoutCancellationException -> L22 zzhg -> L25
        zzaka r6 = this.zze;     // Catch: Exception -> L19 TimeoutCancellationException -> L22 zzhg -> L25
        long r7 = this.zzf;     // Catch: Exception -> L19 TimeoutCancellationException -> L22 zzhg -> L25
        this.zzg = r12;     // Catch: Exception -> L19 TimeoutCancellationException -> L22 zzhg -> L25
        this.zza = 1;     // Catch: Exception -> L19 TimeoutCancellationException -> L22 zzhg -> L25
        r9 = this;
        r11 = r3.zzb(r4, r5, r6, r7, r9);     // Catch: Exception -> L19 TimeoutCancellationException -> L22 zzhg -> L25
        if (r11 == r0) goto L18;
    L25:
        e = move-exception;
        throw e;
    L22:
        e = move-exception;
        throw new zzhg(zzhf.zzb, zzhe.zzb, e.getMessage(), null, 8, null);
    L19:
        e = move-exception;
        throw new zzhg(zzhf.zzb, zzhe.zzt, e.getMessage(), null, 8, null);
    }
}
