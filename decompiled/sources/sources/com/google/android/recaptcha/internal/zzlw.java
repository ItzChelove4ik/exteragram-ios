package com.google.android.recaptcha.internal;

import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes5.dex */
final class zzlw extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzlx zzb;
    final /* synthetic */ int zzc;

    public zzlw(zzlx r1, int r2, Continuation r3) {
        this.zzb = r1;
        this.zzc = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r2, Continuation r3) {
        return new zzlw(this.zzb, this.zzc, r3);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzlw) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r5) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.zza == 0) goto L6;
        ResultKt.throwOnFailure(r5);     // Catch: Exception -> L14
    L15:
        goto L13
    L6:
        ResultKt.throwOnFailure(r5);
        zzlx r52 = this.zzb;     // Catch: Exception -> L14
        List r1 = zzlx.zzc(r52).zzc();     // Catch: Exception -> L14
        if (r1.size() < this.zzc) goto L15;
        zzlx.zzc(r52).zzd(r1);     // Catch: Exception -> L14
        this.zza = 1;     // Catch: Exception -> L14
        if (zzlx.zzd(r52, r1, this) != r0) goto L15;
        return r0;
    L13:
        return Unit.INSTANCE;
    }
}
