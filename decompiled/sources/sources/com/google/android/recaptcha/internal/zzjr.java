package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzjr extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzjt zzc;
    final /* synthetic */ long zzd;
    final /* synthetic */ String zze;
    final /* synthetic */ zziw zzf;
    final /* synthetic */ zzmd zzg;
    private /* synthetic */ Object zzh;

    public zzjr(zzjt r1, long r2, String r4, zziw r5, zzmd r6, Continuation r7) {
        this.zzc = r1;
        this.zzd = r2;
        this.zze = r4;
        this.zzf = r5;
        this.zzg = r6;
        super(2, r7);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r9, Continuation r10) {
        zzjr r0 = new zzjr(this.zzc, this.zzd, this.zze, this.zzf, this.zzg, r10);
        r0.zzh = r9;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzjr) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r11) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzb;
        if (r1 == 0) goto L19;
        if (r1 == 1) goto L16;
        if (r1 == 2) goto L13;
        if (r1 == 3) goto L10;
        zzkn r02 = (zzkn) this.zzh;
        ResultKt.throwOnFailure(r11);     // Catch: Exception -> L36 zzhg -> L39
    L33:
        String r12 = this.zze;     // Catch: Exception -> L36 zzhg -> L39
        zzmd r2 = this.zzg;     // Catch: Exception -> L36 zzhg -> L39
        zzjt r10 = this.zzc;     // Catch: Exception -> L36 zzhg -> L39
        zzjj r112 = new zzjj(r02, r12, r2, zzjt.zzc(r10).zze());     // Catch: Exception -> L36 zzhg -> L39
        zzjt.zzf(r10, r112);     // Catch: Exception -> L36 zzhg -> L39
        return r112;
    L10:
        zzmg r13 = (zzmg) this.zza;
        zzkn r3 = (zzkn) this.zzh;
        ResultKt.throwOnFailure(r11);     // Catch: Exception -> L36 zzhg -> L39
    L29:
        this.zzh = r3;     // Catch: Exception -> L36 zzhg -> L39
        this.zza = null;     // Catch: Exception -> L36 zzhg -> L39
        this.zzb = 4;     // Catch: Exception -> L36 zzhg -> L39
        if (((zzmc) r11).zza(r13, this) == r0) goto L35;
        r02 = r3;
    L35:
        return r0;
    L13:
        r13 = (zzmg) this.zzh;
        ResultKt.throwOnFailure(r11);     // Catch: Exception -> L36 zzhg -> L39
    L26:
        zzkn r113 = zzjt.zzc(this.zzc).zzc(this.zze, this.zzf);     // Catch: Exception -> L36 zzhg -> L39
        long r4 = this.zzd;     // Catch: Exception -> L36 zzhg -> L39
        this.zzh = r113;     // Catch: Exception -> L36 zzhg -> L39
        this.zza = r13;     // Catch: Exception -> L36 zzhg -> L39
        this.zzb = 3;     // Catch: Exception -> L36 zzhg -> L39
        Object r32 = r113.zzb(r4, this);     // Catch: Exception -> L36 zzhg -> L39
        if (r32 == r0) goto L35;
        r3 = r113;
        r11 = r32;
        goto L29
    L16:
        zzmg r14 = (zzmg) this.zza;
        zzmg r5 = (zzmg) this.zzh;
        ResultKt.throwOnFailure(r11);     // Catch: Exception -> L36 zzhg -> L39
    L23:
        this.zzh = r5;     // Catch: Exception -> L36 zzhg -> L39
        this.zza = null;     // Catch: Exception -> L36 zzhg -> L39
        this.zzb = 2;     // Catch: Exception -> L36 zzhg -> L39
        if (((zzmc) r11).zza(r14, this) == r0) goto L35;
        r13 = r5;
        goto L26
    L19:
        ResultKt.throwOnFailure(r11);
        r14 = (zzmg) this.zzh;
        zzjt r114 = this.zzc;     // Catch: Exception -> L36 zzhg -> L39
        zzjt.zze(r114, this.zzd, this.zze);     // Catch: Exception -> L36 zzhg -> L39
        zzgy r115 = zzjt.zzc(r114).zzd();     // Catch: Exception -> L36 zzhg -> L39
        this.zzh = r14;     // Catch: Exception -> L36 zzhg -> L39
        this.zza = r14;     // Catch: Exception -> L36 zzhg -> L39
        this.zzb = 1;     // Catch: Exception -> L36 zzhg -> L39
        r11 = r115.zzb(this);     // Catch: Exception -> L36 zzhg -> L39
        if (r11 == r0) goto L35;
        r5 = r14;
    L39:
        e = move-exception;
        throw e;
    L36:
        e = move-exception;
        throw new zzhg(zzhf.zzb, zzhe.zza, e.getMessage(), null, 8, null);
    }
}
