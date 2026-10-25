package com.google.android.gms.internal.mlkit_genai_prompt;

import android.content.Context;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes4.dex */
final class zzwo extends SuspendLambda implements Function2 {
    /* synthetic */ Object zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzxg zzc;
    final /* synthetic */ Context zzd;

    public zzwo(String r1, zzxg r2, Context r3, Continuation r4) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r4, Continuation r5) {
        zzwo r0 = new zzwo(this.zzb, this.zzc, this.zzd, r5);
        r0.zza = r4;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzwo) create((Map) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r2) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(r2);
        zzxi r22 = (zzxi) ((Map) this.zza).remove(this.zzb);
        if (r22 == null) goto L7;
        return Boxing.boxBoolean(zzxg.zzh(this.zzc, this.zzd, r22));
    L7:
        return Boxing.boxBoolean(false);
    }
}
