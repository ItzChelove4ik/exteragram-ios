package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.AutoValue_LogRequest;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class LogRequest {

    public static abstract class Builder {
        public Builder() {
        }

        public abstract LogRequest build();

        public abstract Builder setClientInfo(ClientInfo r1);

        public abstract Builder setLogEvents(List<LogEvent> r1);

        public abstract Builder setLogSource(Integer r1);

        public abstract Builder setLogSourceName(String r1);

        public abstract Builder setQosTier(QosTier r1);

        public abstract Builder setRequestTimeMs(long r1);

        public abstract Builder setRequestUptimeMs(long r1);

        public Builder setSource(int r1) {
            return setLogSource(Integer.valueOf(r1));
        }

        public Builder setSource(String r1) {
            return setLogSourceName(r1);
        }
    }

    public LogRequest() {
    }

    public static Builder builder() {
        return new AutoValue_LogRequest.Builder();
    }

    public abstract ClientInfo getClientInfo();

    public abstract List<LogEvent> getLogEvents();

    public abstract Integer getLogSource();

    public abstract String getLogSourceName();

    public abstract QosTier getQosTier();

    public abstract long getRequestTimeMs();

    public abstract long getRequestUptimeMs();
}
