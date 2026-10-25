package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public final class zzms implements zzmw {
    private final zzmx zza;

    public zzms(zzmx r1) {
        this.zza = r1;
    }

    @Override // com.google.android.recaptcha.internal.zzmw
    public final Object zza(zzhq r8, zzala r9, Continuation r10) {
        if ((r10 instanceof zzmr) == false) goto L7;
        zzmr r0 = (zzmr) r10;
        int r1 = r0.zzc;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzc = r1 - Integer.MIN_VALUE;
    L8:
        Object r102 = r0.zza;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzc;
        if (r2 == 0) goto L15;
        if (r2 != 1) goto L13;
        ResultKt.throwOnFailure(r102);     // Catch: Exception -> L21 zzhg -> L24
    L19:
        return ((zznb) r102).zzd(zzalc.zze());
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L15:
        ResultKt.throwOnFailure(r102);
        zzmx r7 = this.zza;     // Catch: Exception -> L21 zzhg -> L24
        String r82 = r8.zzd();     // Catch: Exception -> L21 zzhg -> L24
        byte[] r92 = r9.zzw();     // Catch: Exception -> L21 zzhg -> L24
        r0.zzc = 1;     // Catch: Exception -> L21 zzhg -> L24
        r102 = r7.zza(r82, r92, r0);     // Catch: Exception -> L21 zzhg -> L24
        if (r102 != r12) goto L19;
        return r12;
    L24:
        e = move-exception;
        throw e;
    L21:
        e = move-exception;
        throw new zzhg(zzhf.zzc, zzhe.zzk, e.getMessage(), null, 8, null);
    L7:
        r0 = new zzmr(this, r10);
        goto L8
    }
}
