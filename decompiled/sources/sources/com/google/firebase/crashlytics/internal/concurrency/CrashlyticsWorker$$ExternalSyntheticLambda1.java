package com.google.firebase.crashlytics.internal.concurrency;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* loaded from: classes.dex */
public final /* synthetic */ class CrashlyticsWorker$$ExternalSyntheticLambda1 implements Continuation {
    public final /* synthetic */ Runnable f$0;

    public /* synthetic */ CrashlyticsWorker$$ExternalSyntheticLambda1(Runnable r1) {
        this.f$0 = r1;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final Object then(Task r1) {
        return CrashlyticsWorker.$r8$lambda$QmZlDkBlDR5w6hZfr1BjiCUvNrE(this.f$0, r1);
    }
}
