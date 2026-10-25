package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import java.io.Closeable;

/* loaded from: classes.dex */
public interface EventStore extends Closeable {
    int cleanUp();

    long getNextCallTime(TransportContext r1);

    boolean hasPendingEventsFor(TransportContext r1);

    Iterable<TransportContext> loadActiveContexts();

    Iterable<PersistedEvent> loadBatch(TransportContext r1);

    PersistedEvent persist(TransportContext r1, EventInternal r2);

    void recordFailure(Iterable<PersistedEvent> r1);

    void recordNextCallTime(TransportContext r1, long r2);

    void recordSuccess(Iterable<PersistedEvent> r1);
}
