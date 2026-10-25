package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public final class zzmk implements zzmu {
    private final zzhq zza;
    private final zzmx zzb;

    public zzmk(zzhq r1, zzmx r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.android.recaptcha.internal.zzmu
    public final Object zza(zzalg r8, Continuation r9) {
        if ((r9 instanceof zzmj) == false) goto L7;
        zzmj r0 = (zzmj) r9;
        int r1 = r0.zzc;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzc = r1 - Integer.MIN_VALUE;
    L8:
        Object r92 = r0.zza;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzc;
        if (r2 == 0) goto L15;
        if (r2 != 1) goto L13;
        ResultKt.throwOnFailure(r92);     // Catch: Exception -> L21 zzhg -> L24
    L19:
        return ((zznb) r92).zzd(zzali.zzc());
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L15:
        ResultKt.throwOnFailure(r92);
        zzmx r93 = this.zzb;     // Catch: Exception -> L21 zzhg -> L24
        String r7 = this.zza.zze();     // Catch: Exception -> L21 zzhg -> L24
        byte[] r82 = r8.zzw();     // Catch: Exception -> L21 zzhg -> L24
        r0.zzc = 1;     // Catch: Exception -> L21 zzhg -> L24
        r92 = r93.zza(r7, r82, r0);     // Catch: Exception -> L21 zzhg -> L24
        if (r92 != r12) goto L19;
        return r12;
    L24:
        e = move-exception;
        throw e;
    L21:
        e = move-exception;
        throw new zzhg(zzhf.zzc, zzhe.zzk, e.getMessage(), null, 8, null);
    L7:
        r0 = new zzmj(this, r9);
        goto L8
    }
}
