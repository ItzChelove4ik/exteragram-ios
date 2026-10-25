package com.google.firebase.remoteconfig.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* loaded from: classes5.dex */
public final /* synthetic */ class ConfigRealtimeHttpClient$$ExternalSyntheticLambda0 implements Continuation {
    public final /* synthetic */ ConfigRealtimeHttpClient f$0;
    public final /* synthetic */ Task f$1;

    public /* synthetic */ ConfigRealtimeHttpClient$$ExternalSyntheticLambda0(ConfigRealtimeHttpClient r1, Task r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final Object then(Task r2) {
        return ConfigRealtimeHttpClient.$r8$lambda$5ldnYuFP0_42QqI3IUPNf7Ezrnw(this.f$0, this.f$1, r2);
    }
}
