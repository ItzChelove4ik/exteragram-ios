package com.google.android.recaptcha.internal;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.Deferred;

/* loaded from: classes5.dex */
final class zzla extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzlf zzc;
    final /* synthetic */ zzamf zzd;
    final /* synthetic */ long zze;
    private /* synthetic */ Object zzf;

    public zzla(zzlf r1, zzamf r2, long r3, Continuation r5) {
        this.zzc = r1;
        this.zzd = r2;
        this.zze = r3;
        super(2, r5);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r7, Continuation r8) {
        zzla r0 = new zzla(this.zzc, this.zzd, this.zze, r8);
        r0.zzf = r7;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzla) create((zzlp) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r14) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzb;
        if (r1 == 0) goto L16;
        if (r1 == 1) goto L15;
        if (r1 == 2) goto L14;
        if (r1 == 3) goto L11;
        if (r1 == 4) goto L9;
        ResultKt.throwOnFailure(r14);
    L33:
        Object r13 = ((Result) r14).m2027unboximpl();
        ResultKt.throwOnFailure(r13);
        zzakg r132 = (zzakg) r13;
        if (r132 == null) goto L37;
        return r132;
    L37:
        throw new zzhg(zzhf.zzb, zzhe.zza, null, null, 12, null);
    L9:
        ResultKt.throwOnFailure(r14);     // Catch: zzhg -> L28
    L26:
        return (zzakg) r14;
    L11:
        zzlp r12 = (zzlp) this.zzf;
        ResultKt.throwOnFailure(r14);     // Catch: zzhg -> L28
    L24:
        this.zzf = null;     // Catch: zzhg -> L28
        this.zzb = 4;     // Catch: zzhg -> L28
        r14 = ((zzmc) r14).zza(r12.zza(), this);     // Catch: zzhg -> L28
        if (r14 != r0) goto L26;
    L38:
        return r0;
    L14:
        r12 = (zzlp) this.zzf;
        ResultKt.throwOnFailure(r14);
    L22:
        zzon r142 = zzlf.zzb(this.zzc);     // Catch: zzhg -> L28
        this.zzf = r12;     // Catch: zzhg -> L28
        this.zzb = 3;     // Catch: zzhg -> L28
        r14 = r142.zzf(this);     // Catch: zzhg -> L28
        if (r14 == r0) goto L38;
    L15:
        zzlp r15 = (zzlp) this.zza;
        zzlp r5 = (zzlp) this.zzf;
        ResultKt.throwOnFailure(r14);
    L19:
        this.zzf = r5;
        this.zza = null;
        this.zzb = 2;
        if (((zzmc) r14).zza(r15.zza(), this) == r0) goto L38;
        r12 = r5;
        goto L22
    L16:
        ResultKt.throwOnFailure(r14);
        r15 = (zzlp) this.zzf;
        zzlf r8 = this.zzc;
        zzamf r9 = this.zzd;
        long r10 = this.zze;
        this.zzf = r15;
        this.zza = r15;
        this.zzb = 1;
        r14 = new zzmc(new zzle(r8, r9, r10, null));
        if (r14 == r0) goto L38;
        r5 = r15;
    L28:
        Deferred r143 = zzlf.zzc(this.zzc);
        if (r143 == null) goto L37;
        this.zzf = null;
        this.zzb = 5;
        r14 = r143.await(this);
        if (r14 != r0) goto L33;
        goto L33
    }
}
