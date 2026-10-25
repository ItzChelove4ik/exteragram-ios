package com.google.firebase.remoteconfig.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.Map;

/* loaded from: classes5.dex */
public final /* synthetic */ class ConfigFetchHandler$$ExternalSyntheticLambda0 implements Continuation {
    public final /* synthetic */ ConfigFetchHandler f$0;
    public final /* synthetic */ Map f$1;

    public /* synthetic */ ConfigFetchHandler$$ExternalSyntheticLambda0(ConfigFetchHandler r1, Map r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final Object then(Task r2) {
        return ConfigFetchHandler.$r8$lambda$NfboY5OASkxYGjrHqo8X6GPuijM(this.f$0, this.f$1, r2);
    }
}
