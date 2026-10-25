package com.google.android.recaptcha.internal;

import android.webkit.WebView;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes5.dex */
final class zzbx extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzby zzb;
    final /* synthetic */ CompletableDeferred zzc;

    public zzbx(zzby r1, CompletableDeferred r2, Continuation r3) {
        this.zzb = r1;
        this.zzc = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r2, Continuation r3) {
        return new zzbx(this.zzb, this.zzc, r3);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzbx) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r14) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.zza != 0) goto L23;
        ResultKt.throwOnFailure(r14);
        zzhp r1 = zzhp.zza;     // Catch: Exception -> L18
        zzbw r9 = new zzbw(this.zzb, null);     // Catch: Exception -> L18
        this.zza = 1;     // Catch: Exception -> L18
        zzbx r10 = this;
        r14 = zzhp.zzb(r1, 3, 0, 0, 0.0d, r9, r10, 14, null);     // Catch: Exception -> L16
        if (r14 != r0) goto L14;
        return r0;
    L14:
        WebView r142 = (WebView) r14;     // Catch: Exception -> L16
        zzby.zzf(r10.zzb).zzc(r142);     // Catch: Exception -> L16
        r10.zzc.complete(r142);     // Catch: Exception -> L16
    L22:
        return Unit.INSTANCE;
    L16:
        Exception e = e;
    L17:
        Exception r143 = e;
    L20:
        zzby r13 = r10.zzb;
        CompletableDeferred r02 = r10.zzc;
        zzby.zzf(r13).zzd(r02);
        r02.completeExceptionally(r143);
    L18:
        e = e;
        r10 = this;
        goto L17
    L23:
        ResultKt.throwOnFailure(r14);     // Catch: Exception -> L6
        r10 = this;
    L6:
        e = move-exception;
        r143 = e;
        r10 = this;
        goto L20
    }
}
