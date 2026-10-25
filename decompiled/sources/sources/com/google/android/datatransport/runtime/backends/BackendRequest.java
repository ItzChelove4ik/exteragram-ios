package com.google.android.datatransport.runtime.backends;

import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.backends.AutoValue_BackendRequest;

/* loaded from: classes4.dex */
public abstract class BackendRequest {

    public static abstract class Builder {
        public Builder() {
        }

        public abstract BackendRequest build();

        public abstract Builder setEvents(Iterable<EventInternal> r1);

        public abstract Builder setExtras(byte[] r1);
    }

    public BackendRequest() {
    }

    public static Builder builder() {
        return new AutoValue_BackendRequest.Builder();
    }

    public abstract Iterable<EventInternal> getEvents();

    public abstract byte[] getExtras();
}
