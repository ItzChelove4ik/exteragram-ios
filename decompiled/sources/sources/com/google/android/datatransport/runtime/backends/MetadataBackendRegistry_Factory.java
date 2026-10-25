package com.google.android.datatransport.runtime.backends;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class MetadataBackendRegistry_Factory implements Factory<MetadataBackendRegistry> {
    private final Provider<Context> applicationContextProvider;
    private final Provider<CreationContextFactory> creationContextFactoryProvider;

    public MetadataBackendRegistry_Factory(Provider<Context> r1, Provider<CreationContextFactory> r2) {
        this.applicationContextProvider = r1;
        this.creationContextFactoryProvider = r2;
    }

    public static MetadataBackendRegistry_Factory create(Provider<Context> r1, Provider<CreationContextFactory> r2) {
        return new MetadataBackendRegistry_Factory(r1, r2);
    }

    public static MetadataBackendRegistry newInstance(Context r1, Object r2) {
        return new MetadataBackendRegistry(r1, (CreationContextFactory) r2);
    }

    @Override // javax.inject.Provider
    public MetadataBackendRegistry get() {
        return newInstance(this.applicationContextProvider.get(), this.creationContextFactoryProvider.get());
    }

    @Override // javax.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        return get();
    }
}
