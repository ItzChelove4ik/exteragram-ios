package com.google.mlkit.common.internal;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.mlkit.common.sdkinternal.Cleaner;
import com.google.mlkit.common.sdkinternal.CloseGuard$Factory;

/* loaded from: classes.dex */
public final /* synthetic */ class zzf implements ComponentFactory {
    public /* synthetic */ zzf() {
    }

    @Override // com.google.firebase.components.ComponentFactory
    public final Object create(ComponentContainer r2) {
        return new CloseGuard$Factory((Cleaner) r2.get(Cleaner.class));
    }
}
