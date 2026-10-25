package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.dagger.internal.Factory;

/* loaded from: classes.dex */
public final class EventStoreModule_SchemaVersionFactory implements Factory<Integer> {

    public static final class InstanceHolder {
        private static final EventStoreModule_SchemaVersionFactory INSTANCE = null;

        static {
            INSTANCE = new EventStoreModule_SchemaVersionFactory();
        }

        public static /* synthetic */ EventStoreModule_SchemaVersionFactory access$000() {
            return INSTANCE;
        }
    }

    public EventStoreModule_SchemaVersionFactory() {
    }

    public static EventStoreModule_SchemaVersionFactory create() {
        return InstanceHolder.access$000();
    }

    public static int schemaVersion() {
        return EventStoreModule.schemaVersion();
    }

    @Override // javax.inject.Provider
    public Integer get() {
        return Integer.valueOf(schemaVersion());
    }

    @Override // javax.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        return get();
    }
}
