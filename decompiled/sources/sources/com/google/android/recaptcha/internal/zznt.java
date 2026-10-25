package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.sync.Mutex;

/* loaded from: classes5.dex */
final class zznt extends SuspendLambda implements Function2 {
    Object zza;
    Object zzb;
    Object zzc;
    int zzd;
    final /* synthetic */ zzoa zze;
    final /* synthetic */ Function1 zzf;
    private /* synthetic */ Object zzg;

    public zznt(zzoa r1, Function1 r2, Continuation r3) {
        this.zze = r1;
        this.zzf = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r3, Continuation r4) {
        zznt r0 = new zznt(this.zze, this.zzf, r4);
        r0.zzg = r3;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zznt) create((zzlp) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r10) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzd;
        if (r1 == 0) goto L17;
        if (r1 == 1) goto L16;
        if (r1 == 2) goto L11;
        Mutex r9 = (Mutex) this.zzg;
        ResultKt.throwOnFailure(r10);     // Catch: Throwable -> L9
    L40:
        Mutex r5 = r9;
        Boolean r92 = Boxing.boxBoolean(true);     // Catch: Throwable -> L9
    L42:
        r5.unlock(null);
        return r92;
    L9:
        Throwable th = th;
    L44:
        r9.unlock(null);
        throw th;
    L11:
        Function1 r12 = (Function1) this.zzc;
        zzoa r2 = (zzoa) this.zzb;
        r5 = (Mutex) this.zza;
        zzlp r6 = (zzlp) this.zzg;
        ResultKt.throwOnFailure(r10);     // Catch: Throwable -> L14 Exception -> L47
    L23:
        zzagx r102 = (zzagx) r10;     // Catch: Throwable -> L14 Exception -> L47
    L50:
        if (r102 != null) goto L34;
        r92 = Boxing.boxBoolean(false);     // Catch: Throwable -> L32
        goto L42
    L34:
        zzagx r103 = (zzagx) r12.invoke(r102);     // Catch: Throwable -> L32
        if (r103 != null) goto L37;
        r92 = Boxing.boxBoolean(false);     // Catch: Throwable -> L32
        goto L42
    L37:
        String r13 = r6.zza().zza().zza();     // Catch: Throwable -> L32
        this.zzg = r5;     // Catch: Throwable -> L32
        this.zza = null;     // Catch: Throwable -> L32
        this.zzb = null;     // Catch: Throwable -> L32
        this.zzc = null;     // Catch: Throwable -> L32
        this.zzd = 3;     // Catch: Throwable -> L32
        if (zzoa.zzj(r2, r103, r13, this) == r0) goto L46;
        r9 = r5;
    L46:
        return r0;
    L32:
        th = move-exception;
        th = th;
    L15:
        r9 = r5;
    L14:
        th = th;
    L28:
        r102 = null;
        goto L50
    L16:
        r12 = (Function1) this.zzc;
        zzoa r52 = (zzoa) this.zzb;
        Mutex r62 = (Mutex) this.zza;
        zzlp r7 = (zzlp) this.zzg;
        ResultKt.throwOnFailure(r10);
        zzlp r104 = r7;
    L53:
        String r72 = r104.zza().zza().zza();     // Catch: Throwable -> L25 Exception -> L27
        this.zzg = r104;     // Catch: Throwable -> L25 Exception -> L27
        this.zza = r62;     // Catch: Throwable -> L25 Exception -> L27
        this.zzb = r52;     // Catch: Throwable -> L25 Exception -> L27
        this.zzc = r12;     // Catch: Throwable -> L25 Exception -> L27
        this.zzd = 2;     // Catch: Throwable -> L25 Exception -> L27
        Object r22 = zzoa.zzk(r52, r72, this);     // Catch: Throwable -> L25 Exception -> L27
        if (r22 == r0) goto L46;
        Mutex r8 = r62;
        r6 = r104;
        r10 = r22;
        r2 = r52;
        r5 = r8;
        goto L23
    L27:
        r2 = r52;
        r5 = r62;
        r6 = r104;
    L25:
        th = move-exception;
        th = th;
        r9 = r62;
        goto L44
    L17:
        ResultKt.throwOnFailure(r10);
        r104 = (zzlp) this.zzg;
        zzoa r14 = this.zze;
        Function1 r53 = this.zzf;
        r62 = r14.zzd();
        this.zzg = r104;
        this.zza = r62;
        this.zzb = r14;
        this.zzc = r53;
        this.zzd = 1;
        if (r62.lock(null, this) == r0) goto L46;
        r52 = r14;
        r12 = r53;
        goto L53
    }
}
