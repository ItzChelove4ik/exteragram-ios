package com.google.mlkit.common.internal;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.mlkit.common.model.CustomRemoteModel;
import com.google.mlkit.common.model.RemoteModelManager;

/* loaded from: classes.dex */
public final /* synthetic */ class zzh implements ComponentFactory {
    public /* synthetic */ zzh() {
    }

    @Override // com.google.firebase.components.ComponentFactory
    public final Object create(ComponentContainer r3) {
        return new RemoteModelManager.RemoteModelManagerRegistration(CustomRemoteModel.class, r3.getProvider(com.google.mlkit.common.internal.model.zzg.class));
    }
}
