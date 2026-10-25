package com.google.mlkit.vision.label.defaults.thin;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.mlkit.vision.common.internal.MultiFlavorDetectorCreator;
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions;

/* loaded from: classes.dex */
public final /* synthetic */ class zzk implements ComponentFactory {
    public static final /* synthetic */ zzk zza = null;

    static {
        zza = new zzk();
    }

    private /* synthetic */ zzk() {
    }

    @Override // com.google.firebase.components.ComponentFactory
    public final Object create(ComponentContainer r3) {
        return new MultiFlavorDetectorCreator.Registration(ImageLabelerOptions.class, r3.getProvider(zzd.class), 0);
    }
}
