package com.google.firebase.crashlytics.internal.common;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes5.dex */
public final /* synthetic */ class Utils$$ExternalSyntheticLambda0 implements Continuation {
    public final /* synthetic */ CountDownLatch f$0;

    public /* synthetic */ Utils$$ExternalSyntheticLambda0(CountDownLatch r1) {
        this.f$0 = r1;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final Object then(Task r1) {
        return Utils.m1941$r8$lambda$4ODRW4YtgOypI4EnuCu0xhdi2o(this.f$0, r1);
    }
}
