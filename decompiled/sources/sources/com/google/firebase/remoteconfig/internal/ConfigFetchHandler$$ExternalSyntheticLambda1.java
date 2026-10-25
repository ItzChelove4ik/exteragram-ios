package com.google.firebase.remoteconfig.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.Date;
import java.util.Map;

/* loaded from: classes5.dex */
public final /* synthetic */ class ConfigFetchHandler$$ExternalSyntheticLambda1 implements Continuation {
    public final /* synthetic */ ConfigFetchHandler f$0;
    public final /* synthetic */ Task f$1;
    public final /* synthetic */ Task f$2;
    public final /* synthetic */ Date f$3;
    public final /* synthetic */ Map f$4;

    public /* synthetic */ ConfigFetchHandler$$ExternalSyntheticLambda1(ConfigFetchHandler r1, Task r2, Task r3, Date r4, Map r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final Object then(Task r7) {
        return ConfigFetchHandler.m1974$r8$lambda$2M_YxNX84RHSO_CvrLRaJfBcIg(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r7);
    }
}
