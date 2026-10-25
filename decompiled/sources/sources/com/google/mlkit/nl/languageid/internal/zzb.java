package com.google.mlkit.nl.languageid.internal;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.nl.languageid.internal.LanguageIdentifierImpl;

/* loaded from: classes.dex */
public final /* synthetic */ class zzb implements ComponentFactory {
    public static final /* synthetic */ zzb zza = null;

    static {
        zza = new zzb();
    }

    private /* synthetic */ zzb() {
    }

    @Override // com.google.firebase.components.ComponentFactory
    public final Object create(ComponentContainer r3) {
        return new LanguageIdentifierImpl.Factory((zzg) r3.get(zzg.class), (ExecutorSelector) r3.get(ExecutorSelector.class));
    }
}
