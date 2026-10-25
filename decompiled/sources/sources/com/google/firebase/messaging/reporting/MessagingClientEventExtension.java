package com.google.firebase.messaging.reporting;

import com.google.firebase.encoders.proto.Protobuf;
import com.google.firebase.messaging.ProtoEncoderDoNotUse;

/* loaded from: classes5.dex */
public final class MessagingClientEventExtension {
    private static final MessagingClientEventExtension DEFAULT_INSTANCE = null;
    private final MessagingClientEvent messaging_client_event_;

    public static final class Builder {
        private MessagingClientEvent messaging_client_event_;

        public Builder() {
            this.messaging_client_event_ = null;
        }

        public MessagingClientEventExtension build() {
            return new MessagingClientEventExtension(this.messaging_client_event_);
        }

        public Builder setMessagingClientEvent(MessagingClientEvent r1) {
            this.messaging_client_event_ = r1;
            return this;
        }
    }

    static {
        DEFAULT_INSTANCE = new Builder().build();
    }

    public MessagingClientEventExtension(MessagingClientEvent r1) {
        this.messaging_client_event_ = r1;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Protobuf(tag = 1)
    public MessagingClientEvent getMessagingClientEventInternal() {
        return this.messaging_client_event_;
    }

    public byte[] toByteArray() {
        return ProtoEncoderDoNotUse.encode(this);
    }
}
