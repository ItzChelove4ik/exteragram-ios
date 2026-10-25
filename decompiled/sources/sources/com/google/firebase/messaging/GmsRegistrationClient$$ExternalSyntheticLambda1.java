package com.google.firebase.messaging;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.ExecutorService;

/* loaded from: classes5.dex */
public final /* synthetic */ class GmsRegistrationClient$$ExternalSyntheticLambda1 implements Continuation {
    public final /* synthetic */ GmsRegistrationClient f$0;
    public final /* synthetic */ ExecutorService f$1;

    public /* synthetic */ GmsRegistrationClient$$ExternalSyntheticLambda1(GmsRegistrationClient r1, ExecutorService r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final Object then(Task r2) {
        return GmsRegistrationClient.m1964$r8$lambda$VrPDTirHwHxD1EQWet49g8Xzrc(this.f$0, this.f$1, r2);
    }
}
