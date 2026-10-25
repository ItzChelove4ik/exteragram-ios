package com.google.mlkit.vision.label.defaults.thin;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;

/* loaded from: classes.dex */
public final /* synthetic */ class zzj implements ComponentFactory {
    public static final /* synthetic */ zzj zza = null;

    static {
        zza = new zzj();
    }

    private /* synthetic */ zzj() {
    }

    @Override // com.google.firebase.components.ComponentFactory
    public final Object create(ComponentContainer r3) {
        return new zzd((zze) r3.get(zze.class), (ExecutorSelector) r3.get(ExecutorSelector.class));
    }
}
