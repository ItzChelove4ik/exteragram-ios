package com.google.android.recaptcha.internal;

import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
public final class zzmc {
    private final Function2 zza;

    public zzmc(Function2 r1) {
        this.zza = r1;
    }

    public final Object zza(zzmg r1, Continuation r2) {
        return this.zza.invoke(r1, r2);
    }
}
