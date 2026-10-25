package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

/* loaded from: classes5.dex */
final class zzdm extends SuspendLambda implements Function1 {
    Object zza;
    int zzb;
    final /* synthetic */ zzmg zzc;
    final /* synthetic */ zzdo zzd;
    final /* synthetic */ String zze;
    final /* synthetic */ zzcz zzf;
    final /* synthetic */ String zzg;

    public zzdm(zzmg r1, zzdo r2, String r3, zzcz r4, String r5, Continuation r6) {
        this.zzc = r1;
        this.zzd = r2;
        this.zze = r3;
        this.zzf = r4;
        this.zzg = r5;
        super(1, r6);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Continuation r8) {
        return new zzdm(this.zzc, this.zzd, this.zze, this.zzf, this.zzg, r8);
    }

    @Override // kotlin.jvm.functions.Function1
    public final /* bridge */ /* synthetic */ Object invoke(Object r1) {
        return ((zzdm) create((Continuation) r1)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r7) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzb;
        if (r1 == 0) goto L10;
        if (r1 == 1) goto L7;
        ResultKt.throwOnFailure(r7);     // Catch: Exception -> L19
    L18:
        return Unit.INSTANCE;
    L7:
        zzmg r12 = (zzmg) this.zza;
        ResultKt.throwOnFailure(r7);     // Catch: Exception -> L19
    L14:
        this.zza = null;     // Catch: Exception -> L19
        this.zzb = 2;     // Catch: Exception -> L19
        if (((zzmc) r7).zza(r12, this) != r0) goto L18;
    L16:
        return r0;
    L10:
        ResultKt.throwOnFailure(r7);
        r12 = this.zzc;     // Catch: Exception -> L19
        zzdo r72 = this.zzd;     // Catch: Exception -> L19
        zzcz r4 = this.zzf;     // Catch: Exception -> L19
        String r5 = this.zzg;     // Catch: Exception -> L19
        this.zza = r12;     // Catch: Exception -> L19
        this.zzb = 1;     // Catch: Exception -> L19
        r7 = new zzmc(new zzdf(r4, r5, r72, null));     // Catch: Exception -> L19
        if (r7 != r0) goto L14;
    L19:
        e = move-exception;
        e.getMessage();
        throw e;
    }
}
