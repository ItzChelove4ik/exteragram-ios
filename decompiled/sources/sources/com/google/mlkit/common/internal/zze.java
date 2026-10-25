package com.google.mlkit.common.internal;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.mlkit.common.sdkinternal.Cleaner;

/* loaded from: classes.dex */
public final /* synthetic */ class zze implements ComponentFactory {
    public /* synthetic */ zze() {
    }

    @Override // com.google.firebase.components.ComponentFactory
    public final Object create(ComponentContainer r1) {
        return Cleaner.create();
    }
}
