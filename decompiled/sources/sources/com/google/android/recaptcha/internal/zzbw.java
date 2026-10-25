package com.google.android.recaptcha.internal;

import android.webkit.WebView;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.TimeoutKt;

/* loaded from: classes5.dex */
final class zzbw extends SuspendLambda implements Function1 {
    Object zza;
    int zzb;
    final /* synthetic */ zzby zzc;

    public zzbw(zzby r1, Continuation r2) {
        this.zzc = r1;
        super(1, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Continuation r2) {
        return new zzbw(this.zzc, r2);
    }

    @Override // kotlin.jvm.functions.Function1
    public final /* bridge */ /* synthetic */ Object invoke(Object r1) {
        return ((zzbw) create((Continuation) r1)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r7) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.zzb == 0) goto L9;
        WebView r6 = (WebView) this.zza;
        ResultKt.throwOnFailure(r7);     // Catch: Exception -> L7
    L13:
        WebView r72 = (WebView) r7;     // Catch: Exception -> L7
        return r6;
    L7:
        Exception e = e;
    L18:
        r6.stopLoading();
        r6.destroy();
        throw e;
    L9:
        ResultKt.throwOnFailure(r7);
        zzby r73 = this.zzc;
        CompletableDeferred r3 = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        WebView r74 = zzby.zze(r73, r3);
        zzbv r4 = new zzbv(r3, null);     // Catch: Exception -> L16
        this.zza = r74;     // Catch: Exception -> L16
        this.zzb = 1;     // Catch: Exception -> L16
        Object r62 = TimeoutKt.withTimeout(15000, r4, this);     // Catch: Exception -> L16
        if (r62 == r0) goto L15;
        r7 = r62;
        r6 = r74;
        goto L13
    L15:
        return r0;
    L16:
        e = move-exception;
        e = e;
        r6 = r74;
        goto L18
    }
}
