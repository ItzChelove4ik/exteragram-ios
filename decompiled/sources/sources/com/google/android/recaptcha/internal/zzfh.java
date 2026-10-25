package com.google.android.recaptcha.internal;

import com.google.android.play.core.integrity.StandardIntegrityException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutKt;

/* loaded from: classes5.dex */
final class zzfh extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzfp zzc;

    public zzfh(zzfp r1, Continuation r2) {
        this.zzc = r1;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r1, Continuation r2) {
        return new zzfh(this.zzc, r2);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzfh) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r9v29, types: [java.lang.Object, kotlin.Unit] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r10) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        Ref.ObjectRef r1 = this.zzb;
        if (r1 == 0) goto L9;
        Ref.ObjectRef r12 = (Ref.ObjectRef) this.zza;
        ResultKt.throwOnFailure(r10);     // Catch: Exception -> L7
        r1 = r12;
    L13:
        zzfh this = Unit.INSTANCE;
        return this;
    L9:
        ResultKt.throwOnFailure(r10);
        Ref.ObjectRef r13 = new Ref.ObjectRef();
        zzfg r102 = new zzfg(this.zzc, r13, null);     // Catch: Exception -> L7
        this.zza = r13;     // Catch: Exception -> L7
        this.zzb = 1;     // Catch: Exception -> L7
        r1 = r13;
        if (TimeoutKt.withTimeout(60000, r102, this) != r0) goto L13;
        return r0;
    L7:
        e = move-exception;
        zzfp r9 = this.zzc;
        CompletableDeferred r02 = r9.zza();
        Throwable r2 = (Throwable) r1.element;
        if (r2 != null) goto L18;
        r2 = e;
    L18:
        r02.completeExceptionally(r2);
        zzfp.zzf(r9, zzfq.zza);
        zzhf r3 = zzhf.zzb;
        Throwable r92 = (Throwable) r1.element;
        if (r92 != null) goto L22;
        r92 = e;
    L22:
        if ((r92 instanceof StandardIntegrityException) == false) goto L53;
        int r93 = ((StandardIntegrityException) r92).getErrorCode();
        if (r93 != (-100)) goto L26;
        zzhe r94 = zzhe.zzag;
    L36:
        zzhe r4 = r94;
        throw new zzhg(r3, r4, e.getMessage(), null, 8, null);
    L26:
        if (r93 != (-12)) goto L28;
        r94 = zzhe.zzZ;
        goto L36
    L28:
        if (r93 != (-3)) goto L30;
        r94 = zzhe.zzT;
        goto L36
    L30:
        if (r93 != (-2)) goto L32;
        r94 = zzhe.zzS;
        goto L36
    L32:
        if (r93 == (-1)) goto L48;
        switch(r93) {
            case -19: goto L47;
            case -18: goto L46;
            case -17: goto L45;
            case -16: goto L44;
            case -15: goto L43;
            case -14: goto L42;
            default: goto L34;
        };
    L34:
        switch(r93) {
            case -9: goto L41;
            case -8: goto L40;
            case -7: goto L39;
            case -6: goto L38;
            case -5: goto L37;
            default: goto L35;
        };
    L35:
        r94 = zzhe.zza;
        goto L36
    L37:
        r94 = zzhe.zzU;
        goto L36
    L38:
        r94 = zzhe.zzV;
        goto L36
    L39:
        r94 = zzhe.zzW;
        goto L36
    L40:
        r94 = zzhe.zzX;
        goto L36
    L41:
        r94 = zzhe.zzY;
        goto L36
    L42:
        r94 = zzhe.zzaa;
        goto L36
    L43:
        r94 = zzhe.zzab;
        goto L36
    L44:
        r94 = zzhe.zzac;
        goto L36
    L45:
        r94 = zzhe.zzad;
        goto L36
    L46:
        r94 = zzhe.zzae;
        goto L36
    L47:
        r94 = zzhe.zzaf;
        goto L36
    L48:
        r94 = zzhe.zzR;
        goto L36
    L53:
        r94 = zzhe.zza;
        goto L36
    }
}
