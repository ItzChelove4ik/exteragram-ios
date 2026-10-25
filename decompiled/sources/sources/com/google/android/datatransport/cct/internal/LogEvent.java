package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.AutoValue_LogEvent;

/* loaded from: classes4.dex */
public abstract class LogEvent {

    public static abstract class Builder {
        public Builder() {
        }

        public abstract LogEvent build();

        public abstract Builder setComplianceData(ComplianceData r1);

        public abstract Builder setEventCode(Integer r1);

        public abstract Builder setEventTimeMs(long r1);

        public abstract Builder setEventUptimeMs(long r1);

        public abstract Builder setExperimentIds(ExperimentIds r1);

        public abstract Builder setNetworkConnectionInfo(NetworkConnectionInfo r1);

        public abstract Builder setSourceExtension(byte[] r1);

        public abstract Builder setSourceExtensionJsonProto3(String r1);

        public abstract Builder setTimezoneOffsetSeconds(long r1);
    }

    public LogEvent() {
    }

    private static Builder builder() {
        return new AutoValue_LogEvent.Builder();
    }

    public static Builder jsonBuilder(String r1) {
        return builder().setSourceExtensionJsonProto3(r1);
    }

    public static Builder protoBuilder(byte[] r1) {
        return builder().setSourceExtension(r1);
    }

    public abstract ComplianceData getComplianceData();

    public abstract Integer getEventCode();

    public abstract long getEventTimeMs();

    public abstract long getEventUptimeMs();

    public abstract ExperimentIds getExperimentIds();

    public abstract NetworkConnectionInfo getNetworkConnectionInfo();

    public abstract byte[] getSourceExtension();

    public abstract String getSourceExtensionJsonProto3();

    public abstract long getTimezoneOffsetSeconds();
}
