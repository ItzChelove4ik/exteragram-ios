package com.google.android.recaptcha.internal;

import android.os.Build;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzey extends SuspendLambda implements Function2 {
    final /* synthetic */ zzez zza;

    public zzey(zzez r1, Continuation r2) {
        this.zza = r1;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r1, Continuation r2) {
        return new zzey(this.zza, r2);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzey) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r3) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(r3);
        zzez r2 = this.zza;
        int r32 = Build.VERSION.SDK_INT;
        zzaly r0 = zzamb.zzb();
        zzalz r1 = zzama.zza();
        r1.zzc(String.valueOf(r32));
        r0.zzc(CollectionsKt.listOf(r1.zzp()));
        return zzew.zza(r2, (zzamb) r0.zzp());
    }
}
