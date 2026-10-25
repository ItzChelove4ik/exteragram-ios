package com.google.android.datatransport.runtime.firebase.transport;

import com.google.firebase.encoders.proto.Protobuf;

/* loaded from: classes4.dex */
public final class TimeWindow {
    private static final TimeWindow DEFAULT_INSTANCE = null;
    private final long end_ms_;
    private final long start_ms_;

    public static final class Builder {
        private long end_ms_;
        private long start_ms_;

        public Builder() {
            this.start_ms_ = 0;
            this.end_ms_ = 0;
        }

        public TimeWindow build() {
            return new TimeWindow(this.start_ms_, this.end_ms_);
        }

        public Builder setEndMs(long r1) {
            this.end_ms_ = r1;
            return this;
        }

        public Builder setStartMs(long r1) {
            this.start_ms_ = r1;
            return this;
        }
    }

    static {
        DEFAULT_INSTANCE = new Builder().build();
    }

    public TimeWindow(long r1, long r3) {
        this.start_ms_ = r1;
        this.end_ms_ = r3;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Protobuf(tag = 2)
    public long getEndMs() {
        return this.end_ms_;
    }

    @Protobuf(tag = 1)
    public long getStartMs() {
        return this.start_ms_;
    }
}
