package com.google.android.datatransport.runtime.firebase.transport;

import com.google.firebase.encoders.proto.Protobuf;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class LogSourceMetrics {
    private static final LogSourceMetrics DEFAULT_INSTANCE = null;
    private final List<LogEventDropped> log_event_dropped_;
    private final String log_source_;

    public static final class Builder {
        private List<LogEventDropped> log_event_dropped_;
        private String log_source_;

        public Builder() {
            this.log_source_ = _UrlKt.FRAGMENT_ENCODE_SET;
            this.log_event_dropped_ = new ArrayList();
        }

        public LogSourceMetrics build() {
            return new LogSourceMetrics(this.log_source_, Collections.unmodifiableList(this.log_event_dropped_));
        }

        public Builder setLogEventDroppedList(List<LogEventDropped> r1) {
            this.log_event_dropped_ = r1;
            return this;
        }

        public Builder setLogSource(String r1) {
            this.log_source_ = r1;
            return this;
        }
    }

    static {
        DEFAULT_INSTANCE = new Builder().build();
    }

    public LogSourceMetrics(String r1, List<LogEventDropped> r2) {
        this.log_source_ = r1;
        this.log_event_dropped_ = r2;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Protobuf(tag = 2)
    public List<LogEventDropped> getLogEventDroppedList() {
        return this.log_event_dropped_;
    }

    @Protobuf(tag = 1)
    public String getLogSource() {
        return this.log_source_;
    }
}
