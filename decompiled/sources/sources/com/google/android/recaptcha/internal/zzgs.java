package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
final class zzgs extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzmg zzc;
    final /* synthetic */ zzgy zzd;

    public zzgs(zzmg r1, zzgy r2, Continuation r3) {
        this.zzc = r1;
        this.zzd = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r2, Continuation r3) {
        return new zzgs(this.zzc, this.zzd, r3);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzgs) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r6) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzb;
        if (r1 == 0) goto L10;
        if (r1 == 1) goto L7;
        ResultKt.throwOnFailure(r6);     // Catch: Exception -> L19
    L16:
        return (String) r6;
    L7:
        zzmg r12 = (zzmg) this.zza;
        ResultKt.throwOnFailure(r6);     // Catch: Exception -> L19
    L13:
        this.zza = null;     // Catch: Exception -> L19
        this.zzb = 2;     // Catch: Exception -> L19
        r6 = ((zzmb) r6).zza(r12, this);     // Catch: Exception -> L19
        if (r6 != r0) goto L16;
    L18:
        return r0;
    L10:
        ResultKt.throwOnFailure(r6);
        r12 = this.zzc;     // Catch: Exception -> L19
        zzgy r62 = this.zzd;     // Catch: Exception -> L19
        this.zza = r12;     // Catch: Exception -> L19
        this.zzb = 1;     // Catch: Exception -> L19
        r6 = new zzmb(49, new zzgx(r62, null), null);     // Catch: Exception -> L19
        if (r6 == r0) goto L18;
    L19:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }
}
