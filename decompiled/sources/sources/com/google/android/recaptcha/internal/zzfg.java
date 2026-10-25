package com.google.android.recaptcha.internal;

import com.google.android.play.core.integrity.StandardIntegrityException;
import com.google.android.play.core.integrity.StandardIntegrityManager;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* loaded from: classes5.dex */
final class zzfg extends SuspendLambda implements Function2 {
    long zza;
    boolean zzb;
    int zzc;
    final /* synthetic */ zzfp zzd;
    final /* synthetic */ Ref.ObjectRef zze;

    public zzfg(zzfp r1, Ref.ObjectRef r2, Continuation r3) {
        this.zzd = r1;
        this.zze = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r2, Continuation r3) {
        return new zzfg(this.zzd, this.zze, r3);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzfg) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r8v2, types: [T, java.lang.Exception, java.lang.Throwable] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0042 -> B:13:0x0024). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x0075 -> B:6:0x0013). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r8) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzc;
        if (r1 == 0) goto L12;
        if (r1 == 1) goto L7;
        boolean r12 = this.zzb;
        long r4 = this.zza;
        ResultKt.throwOnFailure(r8);
    L6:
        boolean r82 = r12;
        r4 = r4 + r4;
    L13:
        if (r82 == false) goto L40;
        zzfp r83 = this.zzd;     // Catch: Exception -> L10
        this.zza = r4;     // Catch: Exception -> L10
        this.zzc = 1;     // Catch: Exception -> L10
        r8 = zzfp.zzg(r83, this);     // Catch: Exception -> L10
        if (r8 == r0) goto L37;
    L16:
        zzfp r13 = this.zzd;     // Catch: Exception -> L10
        r13.zza().complete((StandardIntegrityManager.StandardIntegrityTokenProvider) r8);     // Catch: Exception -> L10
        zzfp.zzf(r13, zzfq.zzc);     // Catch: Exception -> L10
        r82 = false;
    L37:
        return r0;
    L10:
        e = move-exception;
        this.zze.element = e;
        if ((e instanceof StandardIntegrityException) == false) goto L30;
        int r14 = ((StandardIntegrityException) e).getErrorCode();
        if (r14 != (-100)) goto L23;
    L31:
        r12 = true;
    L32:
        if (r12 == false) goto L38;
        this.zza = r4;
        this.zzb = true;
        this.zzc = 2;
        if (DelayKt.delay(r4, this) == r0) goto L37;
    L38:
        throw e;
    L23:
        if (r14 == (-18)) goto L31;
        if (r14 == (-12)) goto L31;
        if (r14 == (-8)) goto L31;
        if (r14 == (-3)) goto L31;
    L30:
        r12 = false;
        goto L32
    L40:
        return Unit.INSTANCE;
    L7:
        r4 = this.zza;
        ResultKt.throwOnFailure(r8);     // Catch: Exception -> L10
        goto L16
    L12:
        ResultKt.throwOnFailure(r8);
        r4 = 1000;
        r82 = true;
        goto L13
    }
}
