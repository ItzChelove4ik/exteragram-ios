package com.google.android.recaptcha.internal;

import com.android.dx.io.Opcodes;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzgc extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzgo zzc;
    final /* synthetic */ String zzd;
    private /* synthetic */ Object zze;

    public zzgc(zzgo r1, String r2, Continuation r3) {
        this.zzc = r1;
        this.zzd = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r3, Continuation r4) {
        zzgc r0 = new zzgc(this.zzc, this.zzd, r4);
        r0.zze = r3;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzgc) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r10) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzb;
        if (r1 == 0) goto L20;
        if (r1 == 1) goto L17;
        if (r1 == 2) goto L14;
        if (r1 == 3) goto L11;
        ResultKt.throwOnFailure(r10);     // Catch: Exception -> L9
    L32:
        return Result.m2017boximpl(((Result) r10).m2027unboximpl());
    L11:
        zzmg r12 = (zzmg) this.zze;
        ResultKt.throwOnFailure(r10);     // Catch: Exception -> L9
    L29:
        this.zze = null;     // Catch: Exception -> L9
        this.zzb = 4;     // Catch: Exception -> L9
        r10 = ((zzmb) r10).zza(r12, this);     // Catch: Exception -> L9
        if (r10 != r0) goto L32;
    L34:
        return r0;
    L14:
        r12 = (zzmg) this.zze;
        ResultKt.throwOnFailure(r10);     // Catch: Exception -> L9
    L27:
        zzgo r102 = this.zzc;     // Catch: Exception -> L9
        String r3 = this.zzd;     // Catch: Exception -> L9
        this.zze = r12;     // Catch: Exception -> L9
        this.zzb = 3;     // Catch: Exception -> L9
        r10 = new zzmb(51, new zzfx(r102, r3, null), null);     // Catch: Exception -> L9
        if (r10 == r0) goto L34;
    L17:
        zzmg r13 = (zzmg) this.zza;
        zzmg r4 = (zzmg) this.zze;
        ResultKt.throwOnFailure(r10);     // Catch: Exception -> L9
    L24:
        this.zze = r4;     // Catch: Exception -> L9
        this.zza = null;     // Catch: Exception -> L9
        this.zzb = 2;     // Catch: Exception -> L9
        if (((zzmc) r10).zza(r13, this) == r0) goto L34;
        r12 = r4;
        goto L27
    L20:
        ResultKt.throwOnFailure(r10);
        r13 = (zzmg) this.zze;
        zzgo r103 = this.zzc;
        zzgo.zzw(r103).put(Boxing.boxInt(Opcodes.REM_INT_LIT16), this.zzd);
        this.zze = r13;     // Catch: Exception -> L9
        this.zza = r13;     // Catch: Exception -> L9
        this.zzb = 1;     // Catch: Exception -> L9
        r10 = new zzmc(new zzgn(r103, null));     // Catch: Exception -> L9
        if (r10 == r0) goto L34;
        r4 = r13;
    L9:
        e = move-exception;
        Result.Companion r02 = Result.Companion;
        return Result.m2017boximpl(Result.m2018constructorimpl(ResultKt.createFailure(zzgo.zzA(this.zzc, e))));
    }
}
