package com.google.android.gms.internal.mlkit_genai_prompt;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;

/* loaded from: classes4.dex */
final class zzyo extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ CoroutineScope zzb;
    final /* synthetic */ Function2 zzc;
    private /* synthetic */ Object zze;

    public zzyo(CoroutineScope r1, Function2 r2, Continuation r3) {
        this.zzb = r1;
        this.zzc = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r3, Continuation r4) {
        zzyo r0 = new zzyo(this.zzb, this.zzc, r4);
        r0.zze = r3;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzyo) create((ProducerScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r9) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        ResultKt.throwOnFailure(r9);
        if (r1 != 0) goto L9;
        ProducerScope r92 = (ProducerScope) this.zze;
        zzym r12 = new zzym(r92);
        zzyl r2 = new zzyl(BuildersKt.launch$default(this.zzb, null, null, new zzyn(this.zzc, r12, r92, null), 3, null));
        this.zza = 1;
        if (ProduceKt.awaitClose(r92, r2, this) != r0) goto L9;
        return r0;
    L9:
        return Unit.INSTANCE;
    }
}
