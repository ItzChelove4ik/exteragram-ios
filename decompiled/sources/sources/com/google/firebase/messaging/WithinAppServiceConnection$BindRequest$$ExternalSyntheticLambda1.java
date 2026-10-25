package com.google.firebase.messaging;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.WithinAppServiceConnection;
import java.util.concurrent.ScheduledFuture;

/* loaded from: classes5.dex */
public final /* synthetic */ class WithinAppServiceConnection$BindRequest$$ExternalSyntheticLambda1 implements OnCompleteListener {
    public final /* synthetic */ ScheduledFuture f$0;

    public /* synthetic */ WithinAppServiceConnection$BindRequest$$ExternalSyntheticLambda1(ScheduledFuture r1) {
        this.f$0 = r1;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task r1) {
        WithinAppServiceConnection.BindRequest.m1967$r8$lambda$UBVoOrSRgSIh9JgCOmYHbkdm3Q(this.f$0, r1);
    }
}
