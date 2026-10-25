package com.google.android.gms.common.api.internal;

import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final class zaac {
    private final ApiKey zaa;
    private final TaskCompletionSource zab;

    public zaac(ApiKey r2) {
        this.zab = new TaskCompletionSource();
        this.zaa = r2;
    }

    public final ApiKey zaa() {
        return this.zaa;
    }

    public final TaskCompletionSource zab() {
        return this.zab;
    }
}
