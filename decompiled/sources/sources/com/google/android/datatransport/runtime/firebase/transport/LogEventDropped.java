package com.google.android.datatransport.runtime.firebase.transport;

import com.google.firebase.encoders.proto.ProtoEnum;
import com.google.firebase.encoders.proto.Protobuf;

/* loaded from: classes4.dex */
public final class LogEventDropped {
    private static final LogEventDropped DEFAULT_INSTANCE = null;
    private final long events_dropped_count_;
    private final Reason reason_;

    public static final class Builder {
        private long events_dropped_count_;
        private Reason reason_;

        public Builder() {
            this.events_dropped_count_ = 0;
            this.reason_ = Reason.REASON_UNKNOWN;
        }

        public LogEventDropped build() {
            return new LogEventDropped(this.events_dropped_count_, this.reason_);
        }

        public Builder setEventsDroppedCount(long r1) {
            this.events_dropped_count_ = r1;
            return this;
        }

        public Builder setReason(Reason r1) {
            this.reason_ = r1;
            return this;
        }
    }

    public enum Reason extends Enum<Reason> implements ProtoEnum {
        private static final /* synthetic */ Reason[] $VALUES = null;
        public static final Reason CACHE_FULL = null;
        public static final Reason INVALID_PAYLOD = null;
        public static final Reason MAX_RETRIES_REACHED = null;
        public static final Reason MESSAGE_TOO_OLD = null;
        public static final Reason PAYLOAD_TOO_BIG = null;
        public static final Reason REASON_UNKNOWN = null;
        public static final Reason SERVER_ERROR = null;
        private final int number_;

        static {
            Reason r0 = new Reason("REASON_UNKNOWN", 0, 0);
            REASON_UNKNOWN = r0;
            Reason r1 = new Reason("MESSAGE_TOO_OLD", 1, 1);
            MESSAGE_TOO_OLD = r1;
            Reason r2 = new Reason("CACHE_FULL", 2, 2);
            CACHE_FULL = r2;
            Reason r3 = new Reason("PAYLOAD_TOO_BIG", 3, 3);
            PAYLOAD_TOO_BIG = r3;
            Reason r4 = new Reason("MAX_RETRIES_REACHED", 4, 4);
            MAX_RETRIES_REACHED = r4;
            Reason r5 = new Reason("INVALID_PAYLOD", 5, 5);
            INVALID_PAYLOD = r5;
            Reason r6 = new Reason("SERVER_ERROR", 6, 6);
            SERVER_ERROR = r6;
            $VALUES = new Reason[]{r0, r1, r2, r3, r4, r5, r6};
        }

        Reason(String r1, int r2, int r3) {
            this.number_ = r3;
        }

        public static Reason valueOf(String r1) {
            return (Reason) Enum.valueOf(Reason.class, r1);
        }

        public static Reason[] values() {
            return (Reason[]) $VALUES.clone();
        }

        @Override // com.google.firebase.encoders.proto.ProtoEnum
        public int getNumber() {
            return this.number_;
        }
    }

    static {
        DEFAULT_INSTANCE = new Builder().build();
    }

    public LogEventDropped(long r1, Reason r3) {
        this.events_dropped_count_ = r1;
        this.reason_ = r3;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Protobuf(tag = 1)
    public long getEventsDroppedCount() {
        return this.events_dropped_count_;
    }

    @Protobuf(tag = 3)
    public Reason getReason() {
        return this.reason_;
    }
}
