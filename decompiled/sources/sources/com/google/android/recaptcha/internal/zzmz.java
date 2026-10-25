package com.google.android.recaptcha.internal;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.chromium.net.UrlRequest;

/* loaded from: classes5.dex */
final class zzmz implements Function1 {
    final /* synthetic */ UrlRequest zza;

    public zzmz(String r1, UrlRequest r2) {
        this.zza = r2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final /* synthetic */ Object invoke(Object r1) {
        Throwable r12 = (Throwable) r1;
        this.zza.cancel();
        return Unit.INSTANCE;
    }
}
