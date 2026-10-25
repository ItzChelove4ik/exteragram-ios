package com.google.firebase.remoteconfig.internal;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.internal.ConfigFetchHandler;

/* loaded from: classes5.dex */
public final /* synthetic */ class ConfigFetchHandler$$ExternalSyntheticLambda3 implements SuccessContinuation {
    public final /* synthetic */ ConfigFetchHandler.FetchResponse f$0;

    public /* synthetic */ ConfigFetchHandler$$ExternalSyntheticLambda3(ConfigFetchHandler.FetchResponse r1) {
        this.f$0 = r1;
    }

    @Override // com.google.android.gms.tasks.SuccessContinuation
    public final Task then(Object r1) {
        return ConfigFetchHandler.$r8$lambda$8iaYh9jpPTPF5HG1As1LuJkf5hQ(this.f$0, (ConfigContainer) r1);
    }
}
