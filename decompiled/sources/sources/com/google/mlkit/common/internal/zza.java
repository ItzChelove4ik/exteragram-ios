package com.google.mlkit.common.internal;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.model.ModelFileHelper;

/* loaded from: classes.dex */
public final /* synthetic */ class zza implements ComponentFactory {
    public /* synthetic */ zza() {
    }

    @Override // com.google.firebase.components.ComponentFactory
    public final Object create(ComponentContainer r2) {
        return new ModelFileHelper((MlKitContext) r2.get(MlKitContext.class));
    }
}
