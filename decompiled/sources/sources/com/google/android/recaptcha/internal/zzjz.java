package com.google.android.recaptcha.internal;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* loaded from: classes5.dex */
final /* synthetic */ class zzjz extends FunctionReferenceImpl implements Function1 {
    public zzjz(Object r8) {
        super(1, r8, zzke.class, "isRetriable", "isRetriable(Ljava/lang/Exception;)Z", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final /* bridge */ /* synthetic */ Object invoke(Object r1) {
        zzke r0 = (zzke) this.receiver;
        return Boolean.valueOf(zzke.zze(r0, (Exception) r1));
    }
}
