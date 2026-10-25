package com.google.android.recaptcha.internal;

import com.google.android.gms.cast.framework.media.NotificationOptions;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes5.dex */
final class zzdf extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzcz zzc;
    final /* synthetic */ String zzd;
    final /* synthetic */ zzdo zze;
    private /* synthetic */ Object zzg;

    public zzdf(zzcz r1, String r2, zzdo r3, Continuation r4) {
        this.zzc = r1;
        this.zzd = r2;
        this.zze = r3;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r4, Continuation r5) {
        zzdf r0 = new zzdf(this.zzc, this.zzd, this.zze, r5);
        r0.zzg = r4;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzdf) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r15) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzb;
        if (r1 == 0) goto L15;
        if (r1 == 1) goto L14;
        if (r1 == 2) goto L13;
        if (r1 == 3) goto L12;
        if (r1 == 4) goto L11;
        if (r1 == 5) goto L10;
        ResultKt.throwOnFailure(r15);
    L37:
        if (((Boolean) r15).booleanValue() == false) goto L41;
        return Unit.INSTANCE;
    L41:
        throw new zzhg(zzhf.zzb, zzhe.zzaw, null, null, 12, null);
    L10:
        zzmg r12 = (zzmg) this.zzg;
        ResultKt.throwOnFailure(r15);
    L33:
        this.zzg = null;
        this.zzb = 6;
        r15 = ((zzmb) r15).zza(r12, this);
        if (r15 != r0) goto L37;
    L42:
        return r0;
    L11:
        r12 = (zzmg) this.zzg;
        ResultKt.throwOnFailure(r15);
    L31:
        zzdo r3 = this.zze;
        zzng r32 = zzdo.zzj(r3);
        zzde r5 = new zzde(this.zzc, (zzali) r15);
        this.zzg = r12;
        this.zzb = 5;
        r15 = r32.zzf(r5, this);
        if (r15 == r0) goto L42;
    L12:
        zzmg r13 = (zzmg) this.zza;
        zzmg r4 = (zzmg) this.zzg;
        ResultKt.throwOnFailure(r15);
    L28:
        this.zzg = r4;
        this.zza = null;
        this.zzb = 4;
        r15 = ((zzmb) r15).zza(r13, this);
        if (r15 == r0) goto L42;
        r12 = r4;
        goto L31
    L13:
        r13 = (zzmg) this.zzg;
        ResultKt.throwOnFailure(r15);
    L21:
        zzaku r152 = (zzaku) r15;
        zzakt r52 = (zzakt) r152.zzG();
        r52.zzb();
        if (Intrinsics.areEqual(r52.zzp(), zzaku.zzb()) == true) goto L24;
        zzalf r53 = zzalg.zza();
        r53.zzc(r152);
        r53.zzb(r13.zza().zza());
        r53.zza(this.zzd);
        zzalg r122 = (zzalg) r53.zzp();
        zzdv r9 = zzdo.zzi(this.zze);
        this.zzg = r13;
        this.zza = r13;
        this.zzb = 3;
        r15 = new zzmb(63, new zzdu(r9, NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS, r122, null), null);
        if (r15 == r0) goto L42;
        r4 = r13;
        goto L28
    L24:
        return Unit.INSTANCE;
    L14:
        zzmg r14 = (zzmg) this.zza;
        zzmg r6 = (zzmg) this.zzg;
        ResultKt.throwOnFailure(r15);
    L18:
        this.zzg = r6;
        this.zza = null;
        this.zzb = 2;
        r15 = ((zzmc) r15).zza(r14, this);
        if (r15 == r0) goto L42;
        r13 = r6;
        goto L21
    L15:
        ResultKt.throwOnFailure(r15);
        r14 = (zzmg) this.zzg;
        zzcz r153 = this.zzc;
        this.zzg = r14;
        this.zza = r14;
        this.zzb = 1;
        r15 = new zzmc(new zzcy(r153, r153.zza, null));
        if (r15 == r0) goto L42;
        r6 = r14;
        goto L18
    }
}
