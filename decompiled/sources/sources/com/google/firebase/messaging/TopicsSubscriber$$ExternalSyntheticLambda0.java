package com.google.firebase.messaging;

import android.content.Context;
import com.google.firebase.FirebaseApp;
import com.google.firebase.installations.FirebaseInstallationsApi;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes5.dex */
public final /* synthetic */ class TopicsSubscriber$$ExternalSyntheticLambda0 implements Callable {
    public final /* synthetic */ Context f$0;
    public final /* synthetic */ ScheduledExecutorService f$1;
    public final /* synthetic */ Metadata f$2;
    public final /* synthetic */ FirebaseApp f$3;
    public final /* synthetic */ FirebaseMessaging f$4;
    public final /* synthetic */ FirebaseInstallationsApi f$5;

    public /* synthetic */ TopicsSubscriber$$ExternalSyntheticLambda0(Context r1, ScheduledExecutorService r2, Metadata r3, FirebaseApp r4, FirebaseMessaging r5, FirebaseInstallationsApi r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        return TopicsSubscriber.$r8$lambda$cQ44f2KmT4VOqIrhzI03BvbnygI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
