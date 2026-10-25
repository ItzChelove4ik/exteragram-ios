package com.google.android.recaptcha.internal;

import java.util.UUID;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public final class zzmg {
    private final zzlj zza;
    private final zzht zzb;
    private final zzmd zzc;
    private final String zzd;

    public zzmg(zzlj r1, zzht r2, zzmd r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = UUID.randomUUID().toString();
    }

    public final zzmd zza() {
        return this.zzc;
    }

    public final String zzb() {
        return this.zzd;
    }

    public final Object zzc(zzmb r5, Continuation r6) {
        if ((r6 instanceof zzmf) == false) goto L7;
        zzmf r0 = (zzmf) r6;
        int r1 = r0.zzc;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzc = r1 - Integer.MIN_VALUE;
    L8:
        Object r62 = r0.zza;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzc;
        if (r2 == 0) goto L15;
        if (r2 != 1) goto L13;
        ResultKt.throwOnFailure(r62);     // Catch: Exception -> L21
    L19:
        return Unit.INSTANCE;
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L15:
        ResultKt.throwOnFailure(r62);
        r0.zzc = 1;     // Catch: Exception -> L21
        if (r5.zza(this, r0) != r12) goto L19;
        return r12;
    L22:
        return Unit.INSTANCE;
    L7:
        r0 = new zzmf(this, r6);
        goto L8
    }

    public final void zzd(zzajh r2, zzaja r3) {
        String r0 = this.zzc.zza();
        this.zza.zza(r2, r0, r3);
    }

    public final Object zze(int r2, Integer r3, Function2 r4, Continuation r5) {
        return new zzmb(r2, r4, r3).zza(this, r5);
    }

    public final zzlp zzf(int r3, Integer r4) {
        return new zzlp(this.zzb, this, r3, r4);
    }
}
