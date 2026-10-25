package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.sync.Mutex;

/* loaded from: classes5.dex */
final class zznx extends SuspendLambda implements Function2 {
    Object zza;
    Object zzb;
    Object zzc;
    int zzd;
    final /* synthetic */ zzoa zze;
    final /* synthetic */ zzagx zzf;
    private /* synthetic */ Object zzg;

    public zznx(zzoa r1, zzagx r2, Continuation r3) {
        this.zze = r1;
        this.zzf = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r3, Continuation r4) {
        zznx r0 = new zznx(this.zze, this.zzf, r4);
        r0.zzg = r3;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zznx) create((zzlp) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r9) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzd;
        if (r1 == 0) goto L17;
        if (r1 == 1) goto L16;
        if (r1 == 2) goto L11;
        zzagx r02 = (zzagx) this.zza;
        Mutex r8 = (Mutex) this.zzg;
        ResultKt.throwOnFailure(r9);     // Catch: Throwable -> L9
    L32:
        r8.unlock(null);
        return r02;
    L9:
        Throwable th = th;
    L36:
        r8.unlock(null);
        throw th;
    L11:
        zzagx r12 = (zzagx) this.zzc;
        zzoa r2 = (zzoa) this.zzb;
        Mutex r3 = (Mutex) this.zza;
        zzlp r5 = (zzlp) this.zzg;
        ResultKt.throwOnFailure(r9);     // Catch: Throwable -> L14 Exception -> L39
    L23:
        zzagx r92 = (zzagx) r9;     // Catch: Throwable -> L14 Exception -> L39
    L42:
        String r52 = r5.zza().zza().zza();     // Catch: Throwable -> L34
        this.zzg = r3;     // Catch: Throwable -> L34
        this.zza = r92;     // Catch: Throwable -> L34
        this.zzb = null;     // Catch: Throwable -> L34
        this.zzc = null;     // Catch: Throwable -> L34
        this.zzd = 3;     // Catch: Throwable -> L34
        if (zzoa.zzj(r2, r12, r52, this) == r0) goto L38;
        r02 = r92;
        r8 = r3;
    L38:
        return r0;
    L34:
        th = move-exception;
        th = th;
    L15:
        r8 = r3;
        goto L36
    L14:
        th = th;
    L28:
        r92 = null;
        goto L42
    L16:
        r12 = (zzagx) this.zzc;
        zzoa r32 = (zzoa) this.zzb;
        Mutex r53 = (Mutex) this.zza;
        zzlp r6 = (zzlp) this.zzg;
        ResultKt.throwOnFailure(r9);
        zzlp r93 = r6;
    L44:
        String r62 = r93.zza().zza().zza();     // Catch: Throwable -> L25 Exception -> L27
        this.zzg = r93;     // Catch: Throwable -> L25 Exception -> L27
        this.zza = r53;     // Catch: Throwable -> L25 Exception -> L27
        this.zzb = r32;     // Catch: Throwable -> L25 Exception -> L27
        this.zzc = r12;     // Catch: Throwable -> L25 Exception -> L27
        this.zzd = 2;     // Catch: Throwable -> L25 Exception -> L27
        Object r22 = zzoa.zzk(r32, r62, this);     // Catch: Throwable -> L25 Exception -> L27
        if (r22 == r0) goto L38;
        Mutex r7 = r53;
        r5 = r93;
        r9 = r22;
        r2 = r32;
        r3 = r7;
        goto L23
    L27:
        r2 = r32;
        r3 = r53;
        r5 = r93;
    L25:
        th = move-exception;
        th = th;
        r8 = r53;
        goto L36
    L17:
        ResultKt.throwOnFailure(r9);
        r93 = (zzlp) this.zzg;
        zzoa r13 = this.zze;
        zzagx r54 = this.zzf;
        Mutex r63 = r13.zzd();
        this.zzg = r93;
        this.zza = r63;
        this.zzb = r13;
        this.zzc = r54;
        this.zzd = 1;
        if (r63.lock(null, this) == r0) goto L38;
        r32 = r13;
        r12 = r54;
        r53 = r63;
        goto L44
    }
}
