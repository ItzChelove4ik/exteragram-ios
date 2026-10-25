package com.google.firebase.remoteconfig.internal;

import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public final /* synthetic */ class ConfigCacheClient$$ExternalSyntheticLambda0 implements Callable {
    public final /* synthetic */ ConfigStorageClient f$0;

    public /* synthetic */ ConfigCacheClient$$ExternalSyntheticLambda0(ConfigStorageClient r1) {
        this.f$0 = r1;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        return this.f$0.read();
    }
}
