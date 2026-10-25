package com.google.android.recaptcha.internal;

import android.os.Build;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzeb extends SuspendLambda implements Function2 {
    final /* synthetic */ zzec zza;
    private /* synthetic */ Object zzb;

    public zzeb(zzec r1, Continuation r2) {
        this.zza = r1;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r2, Continuation r3) {
        zzeb r0 = new zzeb(this.zza, r3);
        r0.zzb = r2;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzeb) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r5) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(r5);
        zzmg r52 = (zzmg) this.zzb;
        int r0 = Build.VERSION.SDK_INT;
        zzajv r1 = zzajw.zza();
        r1.zza(r52.zza().zza());
        zzec r4 = this.zza;
        r1.zzb(zzec.zze(r4).getPackageName());
        if ((zzec.zzf(r4).zzd() - 2) == 1) goto L5;
        int r2 = 4;
    L6:
        r1.zzu(r2);
        r1.zzc("18.9.2");
        r1.zzd(String.valueOf(r0));
        r1.zze(r52.zza().zzb());
        r1.zzf(zzec.zzf(r4).zza());
        r1.zzg(zzec.zzf(r4).zzb());
        r1.zzh(zzec.zzf(r4).zzc());
        r1.zzt(zzgp.zzb());
        byte[] r53 = ((zzajw) r1.zzp()).zzw();
        String r54 = zzrv.zzj().zzg(r53, 0, r53.length);
        zzaly r02 = zzamb.zzb();
        zzalz r12 = zzama.zza();
        r12.zzc(r54);
        r02.zzc(CollectionsKt.listOf((zzama) r12.zzp()));
        return zzew.zza(r4, (zzamb) r02.zzp());
    L5:
        r2 = 3;
        goto L6
    }
}
