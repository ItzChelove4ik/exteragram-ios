package com.google.android.recaptcha.internal;

import android.view.MotionEvent;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final /* synthetic */ class zzaz implements Function2 {
    private final /* synthetic */ zzbb zza;

    public /* synthetic */ zzaz(zzbb r1) {
        this.zza = r1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* synthetic */ Object invoke(Object r1, Object r2) {
        zzbb r0 = this.zza;
        String r12 = (String) r1;
        return zzbb.zzf(r0, r12, (MotionEvent) r2);
    }
}
