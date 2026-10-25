package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;

/* loaded from: classes4.dex */
public abstract class PersistedEvent {
    public PersistedEvent() {
    }

    public static PersistedEvent create(long r1, TransportContext r3, EventInternal r4) {
        return new AutoValue_PersistedEvent(r1, r3, r4);
    }

    public abstract EventInternal getEvent();

    public abstract long getId();

    public abstract TransportContext getTransportContext();
}
