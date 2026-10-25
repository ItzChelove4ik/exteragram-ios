package com.google.mlkit.common.internal;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.mlkit.common.model.RemoteModelManager;

/* loaded from: classes.dex */
public final /* synthetic */ class zzc implements ComponentFactory {
    public /* synthetic */ zzc() {
    }

    @Override // com.google.firebase.components.ComponentFactory
    public final Object create(ComponentContainer r1) {
        return new RemoteModelManager(r1.setOf(RemoteModelManager.RemoteModelManagerRegistration.class));
    }
}
