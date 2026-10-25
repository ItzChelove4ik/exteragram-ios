package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
final class zzef extends SuspendLambda implements Function2 {
    final /* synthetic */ zzaka zza;
    final /* synthetic */ zzeh zzb;

    public zzef(zzaka r1, zzeh r2, Continuation r3) {
        this.zza = r1;
        this.zzb = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r2, Continuation r3) {
        return new zzef(this.zza, this.zzb, r3);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzef) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r3) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(r3);
        zzaka r32 = this.zza;
        if (r32 == null) goto L6;
        String r33 = r32.zza();
        if (r33 == null) goto L6;
    L7:
        zzeh r2 = this.zzb;
        zzaly r0 = zzamb.zzb();
        zzalz r1 = zzama.zza();
        r1.zzc(r33);
        r0.zzc(CollectionsKt.listOf(r1.zzp()));
        return zzew.zza(r2, (zzamb) r0.zzp());
    L6:
        r33 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L7
    }
}
