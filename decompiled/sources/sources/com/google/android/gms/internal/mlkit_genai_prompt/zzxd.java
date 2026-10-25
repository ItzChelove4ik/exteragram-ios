package com.google.android.gms.internal.mlkit_genai_prompt;

import android.content.Context;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes4.dex */
final class zzxd extends SuspendLambda implements Function2 {
    int zza;
    /* synthetic */ Object zzb;
    final /* synthetic */ Function2 zzc;
    final /* synthetic */ zzxg zzd;
    final /* synthetic */ Context zze;

    public zzxd(Function2 r1, zzxg r2, Context r3, Continuation r4) {
        this.zzc = r1;
        this.zzd = r2;
        this.zze = r3;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r4, Continuation r5) {
        zzxd r0 = new zzxd(this.zzc, this.zzd, this.zze, r5);
        r0.zzb = r4;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzxd) create((Map) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r6) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        if (r1 == 0) goto L9;
        Object r3 = this.zzb;
        if (r1 == 1) goto L8;
        ResultKt.throwOnFailure(r6);
        return r3;
    L8:
        Map r32 = (Map) r3;
        ResultKt.throwOnFailure(r6);
    L11:
        zzxg r12 = this.zzd;
        Context r2 = this.zze;
        this.zzb = r6;
        this.zza = 2;
        if (zzxg.zzn(r12, r2, r32, this) == r0) goto L14;
        return r6;
    L14:
        return r0;
    L9:
        ResultKt.throwOnFailure(r6);
        r32 = (Map) this.zzb;
        Function2 r62 = this.zzc;
        this.zzb = r32;
        this.zza = 1;
        r6 = r62.invoke(r32, this);
        if (r6 == r0) goto L14;
        goto L11
    }
}
