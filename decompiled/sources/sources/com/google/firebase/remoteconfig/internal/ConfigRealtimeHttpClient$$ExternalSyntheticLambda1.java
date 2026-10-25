package com.google.firebase.remoteconfig.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* loaded from: classes5.dex */
public final /* synthetic */ class ConfigRealtimeHttpClient$$ExternalSyntheticLambda1 implements Continuation {
    public final /* synthetic */ ConfigRealtimeHttpClient f$0;
    public final /* synthetic */ Task f$1;
    public final /* synthetic */ Task f$2;

    public /* synthetic */ ConfigRealtimeHttpClient$$ExternalSyntheticLambda1(ConfigRealtimeHttpClient r1, Task r2, Task r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final Object then(Task r3) {
        return ConfigRealtimeHttpClient.$r8$lambda$IjdOYUXD83twZvh8ZERrOwFUcw8(this.f$0, this.f$1, this.f$2, r3);
    }
}
