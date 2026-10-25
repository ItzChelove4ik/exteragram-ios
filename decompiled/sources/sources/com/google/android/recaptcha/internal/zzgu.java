package com.google.android.recaptcha.internal;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* loaded from: classes5.dex */
final /* synthetic */ class zzgu extends FunctionReferenceImpl implements Function1 {
    public zzgu(Object r8) {
        super(1, r8, zzgy.class, "isRetriable", "isRetriable(Ljava/lang/Exception;)Z", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final /* bridge */ /* synthetic */ Object invoke(Object r1) {
        Exception r12 = (Exception) r1;
        zzgy r0 = (zzgy) this.receiver;
        return Boolean.TRUE;
    }
}
