package com.google.mlkit.vision.label.defaults.thin;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.mlkit.common.sdkinternal.MlKitContext;

/* loaded from: classes.dex */
public final /* synthetic */ class zzi implements ComponentFactory {
    public static final /* synthetic */ zzi zza = null;

    static {
        zza = new zzi();
    }

    private /* synthetic */ zzi() {
    }

    @Override // com.google.firebase.components.ComponentFactory
    public final Object create(ComponentContainer r2) {
        return new zze((MlKitContext) r2.get(MlKitContext.class));
    }
}
