package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes5.dex */
final class zzdb extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzlp zzc;
    final /* synthetic */ zzdo zzd;
    final /* synthetic */ String zze;
    final /* synthetic */ String zzf;
    final /* synthetic */ zzcz zzg;

    public zzdb(zzlp r1, zzdo r2, String r3, String r4, zzcz r5, Continuation r6) {
        this.zzc = r1;
        this.zzd = r2;
        this.zze = r3;
        this.zzf = r4;
        this.zzg = r5;
        super(2, r6);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r8, Continuation r9) {
        return new zzdb(this.zzc, this.zzd, this.zze, this.zzf, this.zzg, r9);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzdb) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r7) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzb;
        if (r1 == 0) goto L10;
        if (r1 == 1) goto L7;
        ResultKt.throwOnFailure(r7);     // Catch: Exception -> L19
        goto L18
    L7:
        zzlp r12 = (zzlp) this.zza;
        ResultKt.throwOnFailure(r7);     // Catch: Exception -> L19
    L14:
        this.zza = null;     // Catch: Exception -> L19
        this.zzb = 2;     // Catch: Exception -> L19
        if (((zzmc) r7).zza(r12.zza(), this) != r0) goto L18;
    L16:
        return r0;
    L10:
        ResultKt.throwOnFailure(r7);
        r12 = this.zzc;     // Catch: Exception -> L19
        zzdo r72 = this.zzd;     // Catch: Exception -> L19
        String r3 = this.zze;     // Catch: Exception -> L19
        String r4 = this.zzf;     // Catch: Exception -> L19
        zzcz r5 = this.zzg;     // Catch: Exception -> L19
        this.zza = r12;     // Catch: Exception -> L19
        this.zzb = 1;     // Catch: Exception -> L19
        r7 = zzdo.zzg(r72, r3, r4, r5, this);     // Catch: Exception -> L19
        if (r7 != r0) goto L14;
    L18:
        return Unit.INSTANCE;
    }
}
