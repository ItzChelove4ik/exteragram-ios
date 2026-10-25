package com.google.android.datatransport.runtime.scheduling.persistence;

import c.f$$ExternalSyntheticBUOutline2;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;

/* loaded from: classes4.dex */
final class AutoValue_PersistedEvent extends PersistedEvent {
    private final EventInternal event;
    private final long id;
    private final TransportContext transportContext;

    public AutoValue_PersistedEvent(long r1, TransportContext r3, EventInternal r4) {
        this.id = r1;
        if (r3 == null) goto L10;
        this.transportContext = r3;
        if (r4 == null) goto L8;
        this.event = r4;
        return;
    L8:
        f$$ExternalSyntheticBUOutline2.m("Null event");
        throw null;
    L10:
        f$$ExternalSyntheticBUOutline2.m("Null transportContext");
        throw null;
    }

    public boolean equals(Object r8) {
        if (r8 != this) goto L6;
        return true;
    L6:
        if ((r8 instanceof PersistedEvent) == false) goto L14;
        PersistedEvent r82 = (PersistedEvent) r8;
        if (this.id != r82.getId()) goto L14;
        if (this.transportContext.equals(r82.getTransportContext()) == false) goto L14;
        if (this.event.equals(r82.getEvent()) == false) goto L14;
        return true;
    L14:
        return false;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.PersistedEvent
    public EventInternal getEvent() {
        return this.event;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.PersistedEvent
    public long getId() {
        return this.id;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.PersistedEvent
    public TransportContext getTransportContext() {
        return this.transportContext;
    }

    public int hashCode() {
        long r0 = this.id;
        int r02 = (((((int) (r0 ^ (r0 >>> 32))) ^ 1000003) * 1000003) ^ this.transportContext.hashCode()) * 1000003;
        return this.event.hashCode() ^ r02;
    }

    public String toString() {
        return "PersistedEvent{id=" + this.id + ", transportContext=" + this.transportContext + ", event=" + this.event + "}";
    }
}
