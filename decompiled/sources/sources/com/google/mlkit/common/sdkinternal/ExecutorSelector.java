package com.google.mlkit.common.sdkinternal;

import com.google.firebase.inject.Provider;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public class ExecutorSelector {
    private final Provider zza;

    public ExecutorSelector(Provider r1) {
        this.zza = r1;
    }

    public Executor getExecutorToUse(Executor r1) {
        if (r1 == null) goto L5;
        return r1;
    L5:
        return (Executor) this.zza.get();
    }
}
