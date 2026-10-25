package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.sync.Mutex;

/* loaded from: classes5.dex */
final class zzjq extends SuspendLambda implements Function2 {
    Object zza;
    Object zzb;
    Object zzc;
    Object zzd;
    long zze;
    int zzf;
    final /* synthetic */ zzjt zzg;
    final /* synthetic */ zziw zzh;
    final /* synthetic */ String zzi;
    final /* synthetic */ long zzj;

    public zzjq(zzjt r1, zziw r2, String r3, long r4, Continuation r6) {
        this.zzg = r1;
        this.zzh = r2;
        this.zzi = r3;
        this.zzj = r4;
        super(2, r6);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r8, Continuation r9) {
        return new zzjq(this.zzg, this.zzh, this.zzi, this.zzj, r9);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzjq) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r15) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzf;
        if (r1 == 0) goto L12;
        if (r1 == 1) goto L10;
        Mutex r14 = (Mutex) this.zza;
        ResultKt.throwOnFailure(r15);     // Catch: Throwable -> L8
    L25:
        zzjj r152 = (zzjj) r15;     // Catch: Throwable -> L8
        r14.unlock(null);
        return r152;
    L8:
        th = move-exception;
        Throwable r153 = th;
    L30:
        r14.unlock(null);
        throw r153;
    L10:
        long r12 = this.zze;
        String r4 = (String) this.zzd;
        zzjt r5 = (zzjt) this.zzc;
        zziw r6 = (zziw) this.zzb;
        Mutex r7 = (Mutex) this.zza;
        ResultKt.throwOnFailure(r15);
        Mutex r154 = r7;
        long r72 = r12;
    L11:
        zziw r9 = r6;
        String r62 = r4;
    L28:
        th = move-exception;
        Mutex r13 = r154;
        r153 = th;
        r14 = r13;
        goto L30
    L16:
        if (Intrinsics.areEqual(r9, zziw.zza) == false) goto L19;
        int r16 = 3;
    L22:
        zzlj r11 = zzjt.zzc(r5).zza();     // Catch: Throwable -> L28
        zzht r122 = zzjt.zzc(r5).zzb();     // Catch: Throwable -> L28
        zzjp r42 = new zzjp(r5, r62, r72, r9, null);     // Catch: Throwable -> L28
        this.zza = r154;     // Catch: Throwable -> L28
        this.zzb = null;     // Catch: Throwable -> L28
        this.zzc = null;     // Catch: Throwable -> L28
        this.zzd = null;     // Catch: Throwable -> L28
        this.zzf = 2;     // Catch: Throwable -> L28
        Object r142 = r42.invoke(new zzmd(r11, r122, r62, r16), this);     // Catch: Throwable -> L28
        if (r142 == r0) goto L32;
        Mutex r132 = r154;
        r15 = r142;
        r14 = r132;
    L32:
        return r0;
    L19:
        if (Intrinsics.areEqual(r9, zziw.zzb) == false) goto L21;
        r16 = 4;
        goto L22
    L21:
        r16 = 2;
        goto L22
    L12:
        ResultKt.throwOnFailure(r15);
        r5 = this.zzg;
        r6 = this.zzh;
        r4 = this.zzi;
        r72 = this.zzj;
        r154 = zzjt.zzb(r5);
        this.zza = r154;
        this.zzb = r6;
        this.zzc = r5;
        this.zzd = r4;
        this.zze = r72;
        this.zzf = 1;
        if (r154.lock(null, this) == r0) goto L32;
        goto L32
    }
}
