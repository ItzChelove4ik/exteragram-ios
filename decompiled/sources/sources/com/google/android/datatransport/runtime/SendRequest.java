package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.runtime.AutoValue_SendRequest;

/* loaded from: classes4.dex */
abstract class SendRequest {

    public static abstract class Builder {
        public Builder() {
        }

        public abstract SendRequest build();

        public abstract Builder setEncoding(Encoding r1);

        public abstract Builder setEvent(Event<?> r1);

        public abstract Builder setTransformer(Transformer<?, byte[]> r1);

        public abstract Builder setTransportContext(TransportContext r1);

        public abstract Builder setTransportName(String r1);
    }

    public SendRequest() {
    }

    public static Builder builder() {
        return new AutoValue_SendRequest.Builder();
    }

    public abstract Encoding getEncoding();

    public abstract Event<?> getEvent();

    public byte[] getPayload() {
        return getTransformer().apply(getEvent().getPayload());
    }

    public abstract Transformer<?, byte[]> getTransformer();

    public abstract TransportContext getTransportContext();

    public abstract String getTransportName();
}
