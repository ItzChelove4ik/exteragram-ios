package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.time.Clock;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class SQLiteEventStore_Factory implements Factory<SQLiteEventStore> {
    private final Provider<Clock> clockProvider;
    private final Provider<EventStoreConfig> configProvider;
    private final Provider<String> packageNameProvider;
    private final Provider<SchemaManager> schemaManagerProvider;
    private final Provider<Clock> wallClockProvider;

    public SQLiteEventStore_Factory(Provider<Clock> r1, Provider<Clock> r2, Provider<EventStoreConfig> r3, Provider<SchemaManager> r4, Provider<String> r5) {
        this.wallClockProvider = r1;
        this.clockProvider = r2;
        this.configProvider = r3;
        this.schemaManagerProvider = r4;
        this.packageNameProvider = r5;
    }

    public static SQLiteEventStore_Factory create(Provider<Clock> r6, Provider<Clock> r7, Provider<EventStoreConfig> r8, Provider<SchemaManager> r9, Provider<String> r10) {
        return new SQLiteEventStore_Factory(r6, r7, r8, r9, r10);
    }

    public static SQLiteEventStore newInstance(Clock r6, Clock r7, Object r8, Object r9, Provider<String> r10) {
        return new SQLiteEventStore(r6, r7, (EventStoreConfig) r8, (SchemaManager) r9, r10);
    }

    @Override // javax.inject.Provider
    public SQLiteEventStore get() {
        return newInstance(this.wallClockProvider.get(), this.clockProvider.get(), this.configProvider.get(), this.schemaManagerProvider.get(), this.packageNameProvider);
    }

    @Override // javax.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        return get();
    }
}
