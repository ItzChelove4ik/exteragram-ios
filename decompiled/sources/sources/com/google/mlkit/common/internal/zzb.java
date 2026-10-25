package com.google.mlkit.common.internal;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.mlkit.common.sdkinternal.MlKitThreadPool;

/* loaded from: classes.dex */
public final /* synthetic */ class zzb implements ComponentFactory {
    public /* synthetic */ zzb() {
    }

    @Override // com.google.firebase.components.ComponentFactory
    public final Object create(ComponentContainer r1) {
        return new MlKitThreadPool();
    }
}
