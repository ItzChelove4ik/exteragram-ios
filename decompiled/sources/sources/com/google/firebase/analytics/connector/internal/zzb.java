package com.google.firebase.analytics.connector.internal;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;

/* loaded from: classes.dex */
final /* synthetic */ class zzb implements ComponentFactory {
    static final /* synthetic */ zzb zza = null;

    static {
        zza = new zzb();
    }

    private /* synthetic */ zzb() {
    }

    @Override // com.google.firebase.components.ComponentFactory
    public final /* synthetic */ Object create(ComponentContainer r1) {
        return AnalyticsConnectorRegistrar.zza(r1);
    }
}
