package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;

/* loaded from: classes.dex */
public final class EventStoreModule_StoreConfigFactory implements Factory<EventStoreConfig> {

    public static final class InstanceHolder {
        private static final EventStoreModule_StoreConfigFactory INSTANCE = null;

        static {
            INSTANCE = new EventStoreModule_StoreConfigFactory();
        }

        public static /* synthetic */ EventStoreModule_StoreConfigFactory access$000() {
            return INSTANCE;
        }
    }

    public EventStoreModule_StoreConfigFactory() {
    }

    public static EventStoreModule_StoreConfigFactory create() {
        return InstanceHolder.access$000();
    }

    public static EventStoreConfig storeConfig() {
        return (EventStoreConfig) Preconditions.checkNotNullFromProvides(EventStoreModule.storeConfig());
    }

    @Override // javax.inject.Provider
    public EventStoreConfig get() {
        return storeConfig();
    }

    @Override // javax.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        return get();
    }
}
