package com.google.mlkit.vision.common.internal;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.mlkit.vision.common.internal.MultiFlavorDetectorCreator;

/* loaded from: classes.dex */
public final /* synthetic */ class zzf implements ComponentFactory {
    public static final /* synthetic */ zzf zza = null;

    static {
        zza = new zzf();
    }

    private /* synthetic */ zzf() {
    }

    @Override // com.google.firebase.components.ComponentFactory
    public final Object create(ComponentContainer r1) {
        return new MultiFlavorDetectorCreator(r1.setOf(MultiFlavorDetectorCreator.Registration.class));
    }
}
