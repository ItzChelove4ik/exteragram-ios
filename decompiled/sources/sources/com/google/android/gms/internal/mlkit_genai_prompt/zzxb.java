package com.google.android.gms.internal.mlkit_genai_prompt;

import android.content.Context;
import android.util.Log;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes4.dex */
final class zzxb extends SuspendLambda implements Function2 {
    /* synthetic */ Object zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzxi zzc;
    final /* synthetic */ zzxg zzd;
    final /* synthetic */ Context zze;

    public zzxb(String r1, zzxi r2, zzxg r3, Context r4, Continuation r5) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        this.zze = r4;
        super(2, r5);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r7, Continuation r8) {
        zzxb r0 = new zzxb(this.zzb, this.zzc, this.zzd, this.zze, r8);
        r0.zza = r7;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzxb) create((Map) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r5) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(r5);
        Map r52 = (Map) this.zza;
        String r0 = this.zzb;
        zzxi r1 = (zzxi) r52.get(r0);
        if (r1 == null) goto L7;
        StringBuilder r3 = new StringBuilder(String.valueOf(r0).length() + 31);
        r3.append("Overwriting cache with name '");
        r3.append(r0);
        r3.append("'.");
        Log.i("CacheFileManager", r3.toString());
        zzxi r2 = this.zzc;
        if (Intrinsics.areEqual(r1.zzb(), r2.zzb()) == true) goto L7;
        zzxg.zzh(this.zzd, this.zze, r1);
    L7:
        r52.put(r0, this.zzc);
        return Unit.INSTANCE;
    }
}
