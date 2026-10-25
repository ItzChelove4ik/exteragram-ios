package com.google.mlkit.common.internal;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.common.sdkinternal.MlKitThreadPool;

/* loaded from: classes.dex */
public final /* synthetic */ class zzd implements ComponentFactory {
    public /* synthetic */ zzd() {
    }

    @Override // com.google.firebase.components.ComponentFactory
    public final Object create(ComponentContainer r2) {
        return new ExecutorSelector(r2.getProvider(MlKitThreadPool.class));
    }
}
