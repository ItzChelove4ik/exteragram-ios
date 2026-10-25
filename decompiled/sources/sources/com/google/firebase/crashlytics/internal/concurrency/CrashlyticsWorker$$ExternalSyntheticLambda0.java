package com.google.firebase.crashlytics.internal.concurrency;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.Callable;

/* loaded from: classes5.dex */
public final /* synthetic */ class CrashlyticsWorker$$ExternalSyntheticLambda0 implements Continuation {
    public final /* synthetic */ Callable f$0;

    public /* synthetic */ CrashlyticsWorker$$ExternalSyntheticLambda0(Callable r1) {
        this.f$0 = r1;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final Object then(Task r1) {
        return CrashlyticsWorker.$r8$lambda$ppA3js9NHAaANozeGWXwdEyYIWw(this.f$0, r1);
    }
}
