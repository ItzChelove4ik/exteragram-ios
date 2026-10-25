package com.google.firebase.remoteconfig.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* loaded from: classes5.dex */
public final /* synthetic */ class ConfigAutoFetch$$ExternalSyntheticLambda0 implements Continuation {
    public final /* synthetic */ ConfigAutoFetch f$0;
    public final /* synthetic */ Task f$1;
    public final /* synthetic */ Task f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ int f$4;

    public /* synthetic */ ConfigAutoFetch$$ExternalSyntheticLambda0(ConfigAutoFetch r1, Task r2, Task r3, long r4, int r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final Object then(Task r8) {
        return ConfigAutoFetch.m1972$r8$lambda$ANmcpkzunHxZeIZpnwJj9GhHSM(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8);
    }
}
