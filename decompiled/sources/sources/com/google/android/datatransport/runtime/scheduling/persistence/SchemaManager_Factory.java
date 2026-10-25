package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class SchemaManager_Factory implements Factory<SchemaManager> {
    private final Provider<Context> contextProvider;
    private final Provider<String> dbNameProvider;
    private final Provider<Integer> schemaVersionProvider;

    public SchemaManager_Factory(Provider<Context> r1, Provider<String> r2, Provider<Integer> r3) {
        this.contextProvider = r1;
        this.dbNameProvider = r2;
        this.schemaVersionProvider = r3;
    }

    public static SchemaManager_Factory create(Provider<Context> r1, Provider<String> r2, Provider<Integer> r3) {
        return new SchemaManager_Factory(r1, r2, r3);
    }

    public static SchemaManager newInstance(Context r1, String r2, int r3) {
        return new SchemaManager(r1, r2, r3);
    }

    @Override // javax.inject.Provider
    public SchemaManager get() {
        return newInstance(this.contextProvider.get(), this.dbNameProvider.get(), this.schemaVersionProvider.get().intValue());
    }

    @Override // javax.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        return get();
    }
}
