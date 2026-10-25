package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.Context;

/* loaded from: classes.dex */
public abstract class EventStoreModule {
    public static String dbName() {
        return "com.google.android.datatransport.events";
    }

    public static String packageName(Context r0) {
        return r0.getPackageName();
    }

    public static int schemaVersion() {
        return SchemaManager.SCHEMA_VERSION;
    }

    public static EventStoreConfig storeConfig() {
        return EventStoreConfig.DEFAULT;
    }
}
