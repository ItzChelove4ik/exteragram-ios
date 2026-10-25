package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;

/* loaded from: classes.dex */
public final class EventStoreModule_DbNameFactory implements Factory<String> {

    public static final class InstanceHolder {
        private static final EventStoreModule_DbNameFactory INSTANCE = null;

        static {
            INSTANCE = new EventStoreModule_DbNameFactory();
        }

        public static /* synthetic */ EventStoreModule_DbNameFactory access$000() {
            return INSTANCE;
        }
    }

    public EventStoreModule_DbNameFactory() {
    }

    public static EventStoreModule_DbNameFactory create() {
        return InstanceHolder.access$000();
    }

    public static String dbName() {
        return (String) Preconditions.checkNotNullFromProvides(EventStoreModule.dbName());
    }

    @Override // javax.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        return get();
    }

    @Override // javax.inject.Provider
    public String get() {
        return dbName();
    }
}
