package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.text.Charsets;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public final class zzmq {
    private final String zza;
    private final zzmx zzb;

    public zzmq(String r1, zzmx r2) {
        this.zza = "https://www.gstatic.com/recaptcha/verify_key/orcas/prod/android/verify_key.txt";
        this.zzb = r2;
    }

    public final Object zza(Continuation r9) {
        if ((r9 instanceof zzmp) == false) goto L8;
        zzmp r0 = (zzmp) r9;
        int r1 = r0.zzc;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L8;
        r0.zzc = r1 - Integer.MIN_VALUE;
    L7:
        zzmp r5 = r0;
        Object r92 = r5.zza;
        Object r02 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r12 = r5.zzc;
        if (r12 == 0) goto L16;
        if (r12 != 1) goto L14;
        ResultKt.throwOnFailure(r92);     // Catch: Exception -> L22 zzhg -> L25
    L20:
        zznb r93 = (zznb) r92;     // Catch: Exception -> L22 zzhg -> L25
        r93.zzb();     // Catch: Exception -> L22 zzhg -> L25
        return new String(r93.zza(), Charsets.UTF_8);
    L14:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L16:
        ResultKt.throwOnFailure(r92);
        zzmx r13 = this.zzb;     // Catch: Exception -> L22 zzhg -> L25
        String r8 = this.zza;     // Catch: Exception -> L22 zzhg -> L25
        r5.zzc = 1;     // Catch: Exception -> L22 zzhg -> L25
        r92 = zzmx.zzc(r13, r8, null, null, r5, 6, null);     // Catch: Exception -> L22 zzhg -> L25
        if (r92 != r02) goto L20;
        return r02;
    L25:
        e = move-exception;
        throw e;
    L22:
        e = move-exception;
        throw new zzhg(zzhf.zzc, zzhe.zzap, e.getMessage(), null, 8, null);
    L8:
        r0 = new zzmp(this, r9);
        goto L7
    }
}
