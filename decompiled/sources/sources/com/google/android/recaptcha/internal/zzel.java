package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScopeKt;

/* loaded from: classes5.dex */
final class zzel extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzeo zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ zzaka zzd;
    private /* synthetic */ Object zze;

    public zzel(zzeo r1, String r2, zzaka r3, Continuation r4) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r4, Continuation r5) {
        zzel r0 = new zzel(this.zzb, this.zzc, this.zzd, r5);
        r0.zze = r4;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzel) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r8) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        ResultKt.throwOnFailure(r8);
        if (r1 == 0) goto L5;
        return r8;
    L5:
        zzmg r4 = (zzmg) this.zze;
        zzek r12 = new zzek(this.zzb, this.zzc, r4, this.zzd, null);
        this.zza = 1;
        Object r7 = CoroutineScopeKt.coroutineScope(r12, this);
        if (r7 != r0) goto L8;
        return r0;
    L8:
        return r7;
    }
}
