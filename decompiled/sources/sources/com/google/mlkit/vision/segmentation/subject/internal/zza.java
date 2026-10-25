package com.google.mlkit.vision.segmentation.subject.internal;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.mlkit.common.sdkinternal.MlKitContext;

/* loaded from: classes.dex */
public final /* synthetic */ class zza implements ComponentFactory {
    public static final /* synthetic */ zza zza = null;

    static {
        zza = new zza();
    }

    private /* synthetic */ zza() {
    }

    @Override // com.google.firebase.components.ComponentFactory
    public final Object create(ComponentContainer r2) {
        return new zze((MlKitContext) r2.get(MlKitContext.class));
    }
}
