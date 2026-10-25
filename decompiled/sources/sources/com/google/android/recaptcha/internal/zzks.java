package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzks extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzaku zzc;
    final /* synthetic */ zzakg zzd;
    final /* synthetic */ zzkw zze;
    final /* synthetic */ long zzf;
    private /* synthetic */ Object zzg;

    public zzks(String r1, zzaku r2, zzakg r3, String r4, zzkw r5, long r6, Continuation r8) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        this.zze = r5;
        this.zzf = r6;
        super(2, r8);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r10, Continuation r11) {
        zzkw r5 = this.zze;
        long r6 = this.zzf;
        zzks r0 = new zzks(this.zzb, this.zzc, this.zzd, null, r5, r6, r11);
        r0.zzg = r10;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzks) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r11) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        if (r1 == 0) goto L7;
        if (r1 == 1) goto L6;
        ResultKt.throwOnFailure(r11);
    L13:
        return (zzalc) r11;
    L6:
        zzmg r12 = (zzmg) this.zzg;
        ResultKt.throwOnFailure(r11);
    L9:
        this.zzg = null;
        this.zza = 2;
        r11 = ((zzmb) r11).zza(r12, this);
        if (r11 != r0) goto L13;
    L14:
        return r0;
    L7:
        ResultKt.throwOnFailure(r11);
        r12 = (zzmg) this.zzg;
        String r112 = this.zzb;
        zzaku r4 = this.zzc;
        zzakg r5 = this.zzd;
        String r6 = r12.zza().zza();
        zzakz r7 = zzala.zza();
        r7.zzc(r6);
        r7.zzd(r112);
        r7.zzb(r5.zzb());
        r7.zza(r5.zza());
        r7.zze(r4);
        zzala r8 = (zzala) r7.zzp();
        zzkw r52 = this.zze;
        long r62 = this.zzf;
        this.zzg = r12;
        this.zza = 1;
        r11 = new zzmb(28, new zzkv(r52, r62, r8, null), null);
        if (r11 == r0) goto L14;
        goto L9
    }
}
