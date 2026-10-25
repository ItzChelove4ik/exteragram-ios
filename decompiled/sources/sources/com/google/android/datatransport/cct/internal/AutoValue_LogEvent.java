package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.LogEvent;
import java.util.Arrays;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class AutoValue_LogEvent extends LogEvent {
    private final ComplianceData complianceData;
    private final Integer eventCode;
    private final long eventTimeMs;
    private final long eventUptimeMs;
    private final ExperimentIds experimentIds;
    private final NetworkConnectionInfo networkConnectionInfo;
    private final byte[] sourceExtension;
    private final String sourceExtensionJsonProto3;
    private final long timezoneOffsetSeconds;

    /* renamed from: com.google.android.datatransport.cct.internal.AutoValue_LogEvent$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder extends LogEvent.Builder {
        private ComplianceData complianceData;
        private Integer eventCode;
        private Long eventTimeMs;
        private Long eventUptimeMs;
        private ExperimentIds experimentIds;
        private NetworkConnectionInfo networkConnectionInfo;
        private byte[] sourceExtension;
        private String sourceExtensionJsonProto3;
        private Long timezoneOffsetSeconds;

        public Builder() {
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        public LogEvent build() {
            if (this.eventTimeMs != null) goto L5;
            String r1 = " eventTimeMs";
        L7:
            if (this.eventUptimeMs != null) goto L10;
            r1 = r1.concat(" eventUptimeMs");
        L10:
            if (this.timezoneOffsetSeconds != null) goto L13;
            r1 = r1.concat(" timezoneOffsetSeconds");
        L13:
            if (r1.isEmpty() == true) goto L15;
            Segment$$ExternalSyntheticBUOutline1.m("Missing required properties:".concat(r1));
            return null;
        L15:
            return new AutoValue_LogEvent(this.eventTimeMs.longValue(), this.eventCode, this.complianceData, this.eventUptimeMs.longValue(), this.sourceExtension, this.sourceExtensionJsonProto3, this.timezoneOffsetSeconds.longValue(), this.networkConnectionInfo, this.experimentIds, null);
        L5:
            r1 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L7
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        public LogEvent.Builder setComplianceData(ComplianceData r1) {
            this.complianceData = r1;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        public LogEvent.Builder setEventCode(Integer r1) {
            this.eventCode = r1;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        public LogEvent.Builder setEventTimeMs(long r1) {
            this.eventTimeMs = Long.valueOf(r1);
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        public LogEvent.Builder setEventUptimeMs(long r1) {
            this.eventUptimeMs = Long.valueOf(r1);
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        public LogEvent.Builder setExperimentIds(ExperimentIds r1) {
            this.experimentIds = r1;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        public LogEvent.Builder setNetworkConnectionInfo(NetworkConnectionInfo r1) {
            this.networkConnectionInfo = r1;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        public LogEvent.Builder setSourceExtension(byte[] r1) {
            this.sourceExtension = r1;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        public LogEvent.Builder setSourceExtensionJsonProto3(String r1) {
            this.sourceExtensionJsonProto3 = r1;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogEvent.Builder
        public LogEvent.Builder setTimezoneOffsetSeconds(long r1) {
            this.timezoneOffsetSeconds = Long.valueOf(r1);
            return this;
        }
    }

    private AutoValue_LogEvent(long r1, Integer r3, ComplianceData r4, long r5, byte[] r7, String r8, long r9, NetworkConnectionInfo r11, ExperimentIds r12) {
        this.eventTimeMs = r1;
        this.eventCode = r3;
        this.complianceData = r4;
        this.eventUptimeMs = r5;
        this.sourceExtension = r7;
        this.sourceExtensionJsonProto3 = r8;
        this.timezoneOffsetSeconds = r9;
        this.networkConnectionInfo = r11;
        this.experimentIds = r12;
    }

    public /* synthetic */ AutoValue_LogEvent(long r1, Integer r3, ComplianceData r4, long r5, byte[] r7, String r8, long r9, NetworkConnectionInfo r11, ExperimentIds r12, AnonymousClass1 r13) {
        this(r1, r3, r4, r5, r7, r8, r9, r11, r12);
    }

    public boolean equals(Object r8) {
        if (r8 != this) goto L6;
        return true;
    L6:
        if ((r8 instanceof LogEvent) == false) goto L55;
        LogEvent r82 = (LogEvent) r8;
        if (this.eventTimeMs != r82.getEventTimeMs()) goto L55;
        Integer r1 = this.eventCode;
        if (r1 != null) goto L15;
        if (r82.getEventCode() != null) goto L55;
    L16:
        ComplianceData r12 = this.complianceData;
        if (r12 != null) goto L22;
        if (r82.getComplianceData() != null) goto L55;
    L24:
        if (this.eventUptimeMs != r82.getEventUptimeMs()) goto L55;
        byte[] r13 = this.sourceExtension;
        if ((r82 instanceof AutoValue_LogEvent) == false) goto L28;
        byte[] r3 = ((AutoValue_LogEvent) r82).sourceExtension;
    L30:
        if (Arrays.equals(r13, r3) == false) goto L55;
        String r14 = this.sourceExtensionJsonProto3;
        if (r14 != null) goto L37;
        if (r82.getSourceExtensionJsonProto3() != null) goto L55;
    L39:
        if (this.timezoneOffsetSeconds != r82.getTimezoneOffsetSeconds()) goto L55;
        NetworkConnectionInfo r15 = this.networkConnectionInfo;
        if (r15 != null) goto L46;
        if (r82.getNetworkConnectionInfo() != null) goto L55;
    L47:
        ExperimentIds r7 = this.experimentIds;
        if (r7 != null) goto L53;
        if (r82.getExperimentIds() != null) goto L55;
    L54:
        return true;
    L53:
        if (r7.equals(r82.getExperimentIds()) == false) goto L55;
    L46:
        if (r15.equals(r82.getNetworkConnectionInfo()) == false) goto L55;
    L37:
        if (r14.equals(r82.getSourceExtensionJsonProto3()) == false) goto L55;
    L28:
        r3 = r82.getSourceExtension();
        goto L30
    L22:
        if (r12.equals(r82.getComplianceData()) == false) goto L55;
    L15:
        if (r1.equals(r82.getEventCode()) == true) goto L16;
    L55:
        return false;
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    public ComplianceData getComplianceData() {
        return this.complianceData;
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    public Integer getEventCode() {
        return this.eventCode;
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    public long getEventTimeMs() {
        return this.eventTimeMs;
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    public long getEventUptimeMs() {
        return this.eventUptimeMs;
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    public ExperimentIds getExperimentIds() {
        return this.experimentIds;
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    public NetworkConnectionInfo getNetworkConnectionInfo() {
        return this.networkConnectionInfo;
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    public byte[] getSourceExtension() {
        return this.sourceExtension;
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    public String getSourceExtensionJsonProto3() {
        return this.sourceExtensionJsonProto3;
    }

    @Override // com.google.android.datatransport.cct.internal.LogEvent
    public long getTimezoneOffsetSeconds() {
        return this.timezoneOffsetSeconds;
    }

    public int hashCode() {
        long r0 = this.eventTimeMs;
        int r02 = (((int) (r0 ^ (r0 >>> 32))) ^ 1000003) * 1000003;
        Integer r3 = this.eventCode;
        int r4 = 0;
        if (r3 != null) goto L5;
        int r32 = 0;
    L6:
        int r03 = (r02 ^ r32) * 1000003;
        ComplianceData r33 = this.complianceData;
        if (r33 != null) goto L9;
        int r34 = 0;
    L10:
        long r5 = this.eventUptimeMs;
        int r04 = (((((r03 ^ r34) * 1000003) ^ ((int) (r5 ^ (r5 >>> 32)))) * 1000003) ^ Arrays.hashCode(this.sourceExtension)) * 1000003;
        String r35 = this.sourceExtensionJsonProto3;
        if (r35 != null) goto L13;
        int r36 = 0;
    L14:
        long r52 = this.timezoneOffsetSeconds;
        int r05 = (((r04 ^ r36) * 1000003) ^ ((int) ((r52 >>> 32) ^ r52))) * 1000003;
        NetworkConnectionInfo r2 = this.networkConnectionInfo;
        if (r2 != null) goto L17;
        int r22 = 0;
    L18:
        int r06 = (r05 ^ r22) * 1000003;
        ExperimentIds r9 = this.experimentIds;
        if (r9 == null) goto L23;
        r4 = r9.hashCode();
    L23:
        return r06 ^ r4;
    L17:
        r22 = r2.hashCode();
        goto L18
    L13:
        r36 = r35.hashCode();
        goto L14
    L9:
        r34 = r33.hashCode();
        goto L10
    L5:
        r32 = r3.hashCode();
        goto L6
    }

    public String toString() {
        return "LogEvent{eventTimeMs=" + this.eventTimeMs + ", eventCode=" + this.eventCode + ", complianceData=" + this.complianceData + ", eventUptimeMs=" + this.eventUptimeMs + ", sourceExtension=" + Arrays.toString(this.sourceExtension) + ", sourceExtensionJsonProto3=" + this.sourceExtensionJsonProto3 + ", timezoneOffsetSeconds=" + this.timezoneOffsetSeconds + ", networkConnectionInfo=" + this.networkConnectionInfo + ", experimentIds=" + this.experimentIds + "}";
    }
}
