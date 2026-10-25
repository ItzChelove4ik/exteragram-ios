package com.google.android.recaptcha.internal;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;

/* loaded from: classes5.dex */
final class zzdd extends SuspendLambda implements Function2 {
    Object zza;
    Object zzb;
    int zzc;
    final /* synthetic */ zzdo zzd;
    final /* synthetic */ String zze;
    final /* synthetic */ String zzf;
    final /* synthetic */ zzaka zzg;
    final /* synthetic */ long zzh;
    private /* synthetic */ Object zzi;

    public zzdd(zzdo r1, String r2, String r3, zzaka r4, long r5, Continuation r7) {
        this.zzd = r1;
        this.zze = r2;
        this.zzf = r3;
        this.zzg = r4;
        this.zzh = r5;
        super(2, r7);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r9, Continuation r10) {
        zzdd r0 = new zzdd(this.zzd, this.zze, this.zzf, this.zzg, this.zzh, r10);
        r0.zzi = r9;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzdd) create((zzlp) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r20) {
        Object r7 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r0 = this.zzc;
        if (r0 == 0) goto L9;
        if (r0 == 1) goto L8;
        if (r0 == 2) goto L7;
        zzaku r02 = (zzaku) this.zzi;
        ResultKt.throwOnFailure(r20);
        Object r1 = r20;
    L19:
        Object r12 = ((Result) r1).m2027unboximpl();
        if (Result.m2021exceptionOrNullimpl(r12) != null) goto L26;
        zzakt r03 = (zzakt) r02.zzG();
        r03.zzq((zzaku) r12);
        return (zzaku) r03.zzp();
    L26:
        return r02;
    L7:
        Deferred r04 = (Deferred) this.zza;
        zzlp r13 = (zzlp) this.zzi;
        ResultKt.throwOnFailure(r20);
        zzlp r10 = r13;
        Deferred r14 = r04;
        Object r05 = r20;
    L15:
        r02 = (zzaku) r05;
        if (r14.isCompleted() == false) goto L23;
        this.zzi = r02;
        this.zza = null;
        this.zzc = 3;
        r1 = r14.await(this);
        if (r1 != r7) goto L19;
    L25:
        return r7;
    L23:
        zzdo r11 = this.zzd;
        String r122 = this.zze;
        zzcz r142 = new zzcz(r11, r122, r14, this.zzg);
        BuildersKt.launch$default(zzdo.zzk(r11).zzc(), null, null, new zzdb(r10, r11, r122, this.zzf, r142, null), 3, null);
        return r02;
    L8:
        zzlp r06 = (zzlp) this.zzb;
        r14 = (Deferred) this.zza;
        zzlp r2 = (zzlp) this.zzi;
        ResultKt.throwOnFailure(r20);
        zzlp r112 = r06;
        Object r07 = r20;
    L12:
        this.zzi = r2;
        this.zza = r14;
        this.zzb = null;
        this.zzc = 2;
        r05 = ((zzmb) r07).zza(r112.zza(), this);
        if (r05 == r7) goto L25;
        r10 = r2;
        goto L15
    L9:
        ResultKt.throwOnFailure(r20);
        r112 = (zzlp) this.zzi;
        zzdo r123 = this.zzd;
        CoroutineScope r08 = zzdo.zzk(r123).zzc();
        String r132 = this.zze;
        zzaka r143 = this.zzg;
        Deferred r102 = BuildersKt.async$default(r08, null, null, new zzdc(r112, r123, r132, r143, null), 3, null);
        String r22 = this.zzf;
        long r4 = this.zzh;
        zzcx r09 = zzdo.zzd(r123);
        this.zzi = r112;
        this.zza = r102;
        this.zzb = r112;
        this.zzc = 1;
        r07 = r09.zzb(r132, r22, r143, r4, this);
        if (r07 == r7) goto L25;
        r14 = r102;
        r2 = r112;
        goto L12
    }
}
