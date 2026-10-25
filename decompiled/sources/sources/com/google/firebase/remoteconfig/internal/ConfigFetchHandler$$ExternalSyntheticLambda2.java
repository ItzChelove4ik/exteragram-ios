package com.google.firebase.remoteconfig.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.Date;

/* loaded from: classes5.dex */
public final /* synthetic */ class ConfigFetchHandler$$ExternalSyntheticLambda2 implements Continuation {
    public final /* synthetic */ ConfigFetchHandler f$0;
    public final /* synthetic */ Date f$1;

    public /* synthetic */ ConfigFetchHandler$$ExternalSyntheticLambda2(ConfigFetchHandler r1, Date r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final Object then(Task r2) {
        return ConfigFetchHandler.m1975$r8$lambda$IXMfN8uxtaDcbXYjI5JectKUFY(this.f$0, this.f$1, r2);
    }
}
