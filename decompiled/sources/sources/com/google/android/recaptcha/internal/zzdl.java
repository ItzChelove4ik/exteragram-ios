package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;

/* loaded from: classes5.dex */
final class zzdl extends SuspendLambda implements Function2 {
    final /* synthetic */ zzdo zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ String zzc;
    private /* synthetic */ Object zzd;

    public zzdl(zzdo r1, String r2, String r3, Continuation r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r4, Continuation r5) {
        zzdl r0 = new zzdl(this.zza, this.zzb, this.zzc, r5);
        r0.zzd = r4;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzdl) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r13) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(r13);
        zzmg r1 = (zzmg) this.zzd;
        zzdo r2 = this.zza;
        String r3 = this.zzb;
        zzcz r5 = new zzcz(r2, r3, null, null);
        BuildersKt.launch$default(zzdo.zzk(r2).zzc(), null, null, new zzdk(r1, r2, r3, this.zzc, r5, null), 3, null);
        return Unit.INSTANCE;
    }
}
