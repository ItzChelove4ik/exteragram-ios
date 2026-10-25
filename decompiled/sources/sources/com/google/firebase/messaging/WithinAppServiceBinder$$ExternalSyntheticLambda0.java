package com.google.firebase.messaging;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.WithinAppServiceConnection;

/* loaded from: classes5.dex */
public final /* synthetic */ class WithinAppServiceBinder$$ExternalSyntheticLambda0 implements OnCompleteListener {
    public final /* synthetic */ WithinAppServiceConnection.BindRequest f$0;

    public /* synthetic */ WithinAppServiceBinder$$ExternalSyntheticLambda0(WithinAppServiceConnection.BindRequest r1) {
        this.f$0 = r1;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task r1) {
        WithinAppServiceBinder.$r8$lambda$t7ts_4_AauwgtOGOXEuv2hjyze8(this.f$0, r1);
    }
}
