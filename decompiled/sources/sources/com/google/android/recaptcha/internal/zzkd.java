package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;

/* loaded from: classes5.dex */
final class zzkd extends SuspendLambda implements Function2 {
    final /* synthetic */ zzke zza;
    final /* synthetic */ long zzb;
    private /* synthetic */ Object zzc;

    public zzkd(zzke r1, long r2, Continuation r4) {
        this.zza = r1;
        this.zzb = r2;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r5, Continuation r6) {
        zzkd r0 = new zzkd(this.zza, this.zzb, r6);
        r0.zzc = r5;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzkd) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r11) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(r11);
        zzmg r3 = (zzmg) this.zzc;
        zzke r1 = this.zza;
        if (Intrinsics.areEqual(r1.zza(), zzkm.zzb()) == true) goto L16;
        if (Intrinsics.areEqual(r1.zza(), zzkm.zzc()) == true) goto L16;
        if (Intrinsics.areEqual(r1.zza(), zzkm.zzd()) == true) goto L10;
    L13:
        zzke.zzg(r1, zzkm.zzc());
        CompletableDeferred r2 = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        zzke.zzh(r1, r2);
        BuildersKt.launch$default(zzke.zzo(r1).zzc(), null, null, new zzkc(r1, r2, r3, this.zzb, null), 3, null);
        return Unit.INSTANCE;
    L10:
        if (zzke.zze(r1, zzke.zzf(r1)) == true) goto L13;
        return Unit.INSTANCE;
    L16:
        return Unit.INSTANCE;
    }
}
