package com.google.android.datatransport.runtime;

import android.content.Context;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import java.io.Closeable;

/* loaded from: classes.dex */
abstract class TransportRuntimeComponent implements Closeable {

    public interface Builder {
        TransportRuntimeComponent build();

        Builder setApplicationContext(Context r1);
    }

    public TransportRuntimeComponent() {
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        getEventStore().close();
    }

    public abstract EventStore getEventStore();

    public abstract TransportRuntime getTransportRuntime();
}
