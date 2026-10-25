package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzfe extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzfp zzb;
    final /* synthetic */ String zzc;
    private /* synthetic */ Object zzd;

    public zzfe(zzfp r1, String r2, Continuation r3) {
        this.zzb = r1;
        this.zzc = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r3, Continuation r4) {
        zzfe r0 = new zzfe(this.zzb, this.zzc, r4);
        r0.zzd = r3;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzfe) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v6 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r7) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        Object r1 = this.zza;
        if (r1 == 0) goto L13;
        if (r1 == 1) goto L10;
        if (r1 == 2) goto L9;
        ResultKt.throwOnFailure(r7);
        if (r1 != 3) goto L28;
    L24:
        zzfp r72 = this.zzb;
        String r12 = this.zzc;
        this.zza = 4;
        r7 = zzfp.zzj(r72, r12, this);
        if (r7 != r0) goto L28;
    L26:
        return r0;
    L28:
        return (String) r7;
    L9:
        zzmg r13 = (zzmg) this.zzd;
        ResultKt.throwOnFailure(r7);
        zzmg r14 = r13;
    L21:
        this.zzd = null;
        this.zza = 3;
        if (((zzmc) r7).zza(r14, this) != r0) goto L24;
    L10:
        zzmg r15 = (zzmg) this.zzd;
        ResultKt.throwOnFailure(r7);     // Catch: Exception -> L18
        r1 = r15;
    L16:
        return (String) r7;
    L13:
        ResultKt.throwOnFailure(r7);
        zzmg r16 = (zzmg) this.zzd;
        zzfp r73 = this.zzb;     // Catch: Exception -> L18
        String r5 = this.zzc;     // Catch: Exception -> L18
        this.zzd = r16;     // Catch: Exception -> L18
        this.zza = 1;     // Catch: Exception -> L18
        r7 = zzfp.zzj(r73, r5, this);     // Catch: Exception -> L18
        r1 = r16;
        if (r7 == r0) goto L26;
    L18:
        zzfp r74 = this.zzb;
        this.zzd = r1;
        this.zza = 2;
        r7 = r74.zzc(this);
        r14 = r1;
        if (r7 != r0) goto L21;
        goto L21
    }
}
