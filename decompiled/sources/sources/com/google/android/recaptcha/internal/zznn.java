package com.google.android.recaptcha.internal;

import java.util.Map;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;

/* loaded from: classes5.dex */
public final class zznn implements zzoo {
    private final zzno zza;

    public zznn(zzno r1) {
        this.zza = r1;
    }

    @Override // com.google.android.recaptcha.internal.zzoo
    public final Object zzb(String r1, String r2, Continuation r3) {
        Map r12 = MapsKt.mapOf(TuplesKt.to(r1, r2));
        Object r0 = this.zza.zza(r12, r3);
        if (r0 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        return r0;
    L6:
        return Unit.INSTANCE;
    }

    @Override // com.google.android.recaptcha.internal.zzoo
    public final Object zzc(String r1, Continuation r2) {
        return this.zza.zzb(r1, r2);
    }
}
