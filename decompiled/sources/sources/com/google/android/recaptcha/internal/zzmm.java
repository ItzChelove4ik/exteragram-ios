package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public final class zzmm {
    private final zzhq zza;
    private final zzmx zzb;

    public zzmm(zzhq r1, zzmx r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    public final Object zza(zzajl r5, Continuation r6) {
        if ((r6 instanceof zzml) == false) goto L7;
        zzml r0 = (zzml) r6;
        int r1 = r0.zzc;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzc = r1 - Integer.MIN_VALUE;
    L8:
        Object r62 = r0.zza;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzc;
        boolean r3 = true;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        ResultKt.throwOnFailure(r62);
    L17:
        if (((zznb) r62).zzc() == 200) goto L21;
        r3 = false;
    L21:
        return Boxing.boxBoolean(r3);
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r62);
        zzmx r63 = this.zzb;
        String r4 = this.zza.zzc();
        byte[] r52 = r5.zzw();
        r0.zzc = 1;
        r62 = r63.zza(r4, r52, r0);
        if (r62 != r12) goto L17;
        return r12;
    L7:
        r0 = new zzml(this, r6);
        goto L8
    }
}
