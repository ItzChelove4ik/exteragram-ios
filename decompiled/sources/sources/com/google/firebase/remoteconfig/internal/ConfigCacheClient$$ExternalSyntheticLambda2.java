package com.google.firebase.remoteconfig.internal;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;

/* loaded from: classes5.dex */
public final /* synthetic */ class ConfigCacheClient$$ExternalSyntheticLambda2 implements SuccessContinuation {
    public final /* synthetic */ ConfigCacheClient f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ ConfigContainer f$2;

    public /* synthetic */ ConfigCacheClient$$ExternalSyntheticLambda2(ConfigCacheClient r1, boolean r2, ConfigContainer r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // com.google.android.gms.tasks.SuccessContinuation
    public final Task then(Object r3) {
        return ConfigCacheClient.$r8$lambda$LvQjX7XEpXst4uSOi9kWWub2vbA(this.f$0, this.f$1, this.f$2, (Void) r3);
    }
}
