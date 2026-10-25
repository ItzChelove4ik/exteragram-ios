package com.google.android.datatransport.cct;

import com.google.android.datatransport.cct.CctTransportBackend;
import com.google.android.datatransport.runtime.retries.RetryStrategy;

/* loaded from: classes4.dex */
public final /* synthetic */ class CctTransportBackend$$ExternalSyntheticLambda1 implements RetryStrategy {
    public /* synthetic */ CctTransportBackend$$ExternalSyntheticLambda1() {
    }

    @Override // com.google.android.datatransport.runtime.retries.RetryStrategy
    public final Object shouldRetry(Object r1, Object r2) {
        return CctTransportBackend.m1802$r8$lambda$ZhERf3WZxxKL1vqOg2FJv71EaQ((CctTransportBackend.HttpRequest) r1, (CctTransportBackend.HttpResponse) r2);
    }
}
