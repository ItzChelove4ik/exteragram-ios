package com.google.android.recaptcha.internal;

import android.net.TrafficStats;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public final class zzlz {
    private final zzmm zza;

    public zzlz(zzmm r1) {
        this.zza = r1;
    }

    public final Object zza(zzajl r8, Continuation r9) {
        if ((r9 instanceof zzly) == false) goto L7;
        zzly r0 = (zzly) r9;
        int r1 = r0.zzc;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzc = r1 - Integer.MIN_VALUE;
    L8:
        Object r92 = r0.zza;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzc;
        if (r2 == 0) goto L17;
        if (r2 != 1) goto L15;
        ResultKt.throwOnFailure(r92);     // Catch: Exception -> L13
    L22:
        if (((Boolean) r92).booleanValue() == false) goto L26;
        return Boxing.boxBoolean(true);
    L26:
        return Boxing.boxBoolean(false);
    L15:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L17:
        ResultKt.throwOnFailure(r92);
        TrafficStats.setThreadStatsTag((int) Thread.currentThread().getId());     // Catch: Exception -> L13
        zzmm r7 = this.zza;     // Catch: Exception -> L13
        r0.zzc = 1;     // Catch: Exception -> L13
        r92 = r7.zza(r8, r0);     // Catch: Exception -> L13
        if (r92 != r12) goto L22;
        return r12;
    L13:
        e = move-exception;
        e.getMessage();
        return Boxing.boxBoolean(false);
    L7:
        r0 = new zzly(this, r9);
        goto L8
    }
}
