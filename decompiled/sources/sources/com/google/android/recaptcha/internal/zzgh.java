package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;

/* loaded from: classes5.dex */
final class zzgh extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzgo zzb;
    final /* synthetic */ zzalm zzc;
    private /* synthetic */ Object zzd;

    public zzgh(zzgo r1, zzalm r2, Continuation r3) {
        this.zzb = r1;
        this.zzc = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r3, Continuation r4) {
        zzgh r0 = new zzgh(this.zzb, this.zzc, r4);
        r0.zzd = r3;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzgh) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r7) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        ResultKt.throwOnFailure(r7);
        if (r1 != 0) goto L9;
        zzmg r72 = (zzmg) this.zzd;
        zzgo r12 = this.zzb;
        CoroutineContext r2 = zzgo.zzC(r12).zzc().getCoroutineContext();
        zzgg r4 = new zzgg(r72, r12, this.zzc, null);
        this.zza = 1;
        if (BuildersKt.withContext(r2, r4, this) != r0) goto L9;
        return r0;
    L9:
        return Unit.INSTANCE;
    }
}
