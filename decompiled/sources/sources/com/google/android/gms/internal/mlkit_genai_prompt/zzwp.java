package com.google.android.gms.internal.mlkit_genai_prompt;

import android.content.Context;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes4.dex */
final class zzwp extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzxg zzb;
    final /* synthetic */ Context zzc;
    final /* synthetic */ String zzd;

    public zzwp(zzxg r1, Context r2, String r3, Continuation r4) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r3, Continuation r4) {
        return new zzwp(this.zzb, this.zzc, this.zzd, r4);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzwp) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r6) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        ResultKt.throwOnFailure(r6);
        if (r1 == 0) goto L5;
        return r6;
    L5:
        zzxg r62 = this.zzb;
        Context r12 = this.zzc;
        zzwo r3 = new zzwo(this.zzd, r62, r12, null);
        this.zza = 1;
        Object r5 = zzxg.zzk(r62, r12, r3, this);
        if (r5 != r0) goto L8;
        return r0;
    L8:
        return r5;
    }
}
