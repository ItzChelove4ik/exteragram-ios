package com.google.android.recaptcha.internal;

import com.android.dx.io.Opcodes;
import java.util.Optional;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.Deferred;

/* loaded from: classes5.dex */
final class zzfx extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzgo zzb;
    final /* synthetic */ String zzc;
    private /* synthetic */ Object zzd;

    public zzfx(zzgo r1, String r2, Continuation r3) {
        this.zzb = r1;
        this.zzc = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r3, Continuation r4) {
        zzfx r0 = new zzfx(this.zzb, this.zzc, r4);
        r0.zzd = r3;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzfx) create((zzlp) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r7) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.zza == 0) goto L9;
        zzlp r02 = (zzlp) this.zzd;
        ResultKt.throwOnFailure(r7);     // Catch: Exception -> L7
    L13:
        Object r72 = ((Result) r7).m2027unboximpl();     // Catch: Exception -> L7
        ResultKt.throwOnFailure(r72);     // Catch: Exception -> L7
        byte[] r73 = (byte[]) r72;     // Catch: Exception -> L7
        zzaer r1 = zzaer.zza;     // Catch: Exception -> L7
        zzalt r74 = zzalt.zzb(zzaer.zzs(r73, 0, r73.length));     // Catch: Exception -> L7
        zzgo r12 = this.zzb;     // Catch: Exception -> L7
        zzgo.zzy(r12, r02.zza());     // Catch: Exception -> L7
        return Result.m2017boximpl(Result.m2018constructorimpl(zzgo.zzz(r12, this.zzc, r74)));
    L9:
        ResultKt.throwOnFailure(r7);
        zzlp r75 = (zzlp) this.zzd;
        zzgo r13 = this.zzb;     // Catch: Exception -> L7
        zzgo.zzw(r13).put(Boxing.boxInt(Opcodes.REM_INT_LIT16), this.zzc);     // Catch: Exception -> L7
        Deferred r14 = zzgo.zzv(r13, Boxing.boxLong(((Number) r13.zzh().get(1)).longValue()), Optional.empty());     // Catch: Exception -> L7
        this.zzd = r75;     // Catch: Exception -> L7
        this.zza = 1;     // Catch: Exception -> L7
        Object r15 = r14.await(this);     // Catch: Exception -> L7
        if (r15 == r0) goto L16;
        r02 = r75;
        r7 = r15;
        goto L13
    L16:
        return r0;
    L7:
        e = move-exception;
        throw zzgo.zzA(this.zzb, e);
    }
}
