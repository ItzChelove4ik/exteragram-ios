package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.LogRequest;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class AutoValue_LogRequest extends LogRequest {
    private final ClientInfo clientInfo;
    private final List<LogEvent> logEvents;
    private final Integer logSource;
    private final String logSourceName;
    private final QosTier qosTier;
    private final long requestTimeMs;
    private final long requestUptimeMs;

    /* renamed from: com.google.android.datatransport.cct.internal.AutoValue_LogRequest$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder extends LogRequest.Builder {
        private ClientInfo clientInfo;
        private List<LogEvent> logEvents;
        private Integer logSource;
        private String logSourceName;
        private QosTier qosTier;
        private Long requestTimeMs;
        private Long requestUptimeMs;

        public Builder() {
        }

        @Override // com.google.android.datatransport.cct.internal.LogRequest.Builder
        public LogRequest build() {
            if (this.requestTimeMs != null) goto L5;
            String r0 = " requestTimeMs";
        L7:
            if (this.requestUptimeMs != null) goto L10;
            r0 = r0.concat(" requestUptimeMs");
        L10:
            if (r0.isEmpty() == true) goto L12;
            Segment$$ExternalSyntheticBUOutline1.m("Missing required properties:".concat(r0));
            return null;
        L12:
            return new AutoValue_LogRequest(this.requestTimeMs.longValue(), this.requestUptimeMs.longValue(), this.clientInfo, this.logSource, this.logSourceName, this.logEvents, this.qosTier, null);
        L5:
            r0 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L7
        }

        @Override // com.google.android.datatransport.cct.internal.LogRequest.Builder
        public LogRequest.Builder setClientInfo(ClientInfo r1) {
            this.clientInfo = r1;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogRequest.Builder
        public LogRequest.Builder setLogEvents(List<LogEvent> r1) {
            this.logEvents = r1;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogRequest.Builder
        public LogRequest.Builder setLogSource(Integer r1) {
            this.logSource = r1;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogRequest.Builder
        public LogRequest.Builder setLogSourceName(String r1) {
            this.logSourceName = r1;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogRequest.Builder
        public LogRequest.Builder setQosTier(QosTier r1) {
            this.qosTier = r1;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogRequest.Builder
        public LogRequest.Builder setRequestTimeMs(long r1) {
            this.requestTimeMs = Long.valueOf(r1);
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.LogRequest.Builder
        public LogRequest.Builder setRequestUptimeMs(long r1) {
            this.requestUptimeMs = Long.valueOf(r1);
            return this;
        }
    }

    private AutoValue_LogRequest(long r1, long r3, ClientInfo r5, Integer r6, String r7, List<LogEvent> r8, QosTier r9) {
        this.requestTimeMs = r1;
        this.requestUptimeMs = r3;
        this.clientInfo = r5;
        this.logSource = r6;
        this.logSourceName = r7;
        this.logEvents = r8;
        this.qosTier = r9;
    }

    public /* synthetic */ AutoValue_LogRequest(long r1, long r3, ClientInfo r5, Integer r6, String r7, List r8, QosTier r9, AnonymousClass1 r10) {
        this(r1, r3, r5, r6, r7, r8, r9);
    }

    public boolean equals(Object r8) {
        if (r8 != this) goto L6;
        return true;
    L6:
        if ((r8 instanceof LogRequest) == false) goto L47;
        LogRequest r82 = (LogRequest) r8;
        if (this.requestTimeMs != r82.getRequestTimeMs()) goto L47;
        if (this.requestUptimeMs != r82.getRequestUptimeMs()) goto L47;
        ClientInfo r1 = this.clientInfo;
        if (r1 != null) goto L17;
        if (r82.getClientInfo() != null) goto L47;
    L18:
        Integer r12 = this.logSource;
        if (r12 != null) goto L24;
        if (r82.getLogSource() != null) goto L47;
    L25:
        String r13 = this.logSourceName;
        if (r13 != null) goto L31;
        if (r82.getLogSourceName() != null) goto L47;
    L32:
        List<LogEvent> r14 = this.logEvents;
        if (r14 != null) goto L38;
        if (r82.getLogEvents() != null) goto L47;
    L39:
        QosTier r7 = this.qosTier;
        if (r7 != null) goto L45;
        if (r82.getQosTier() != null) goto L47;
    L46:
        return true;
    L45:
        if (r7.equals(r82.getQosTier()) == false) goto L47;
    L38:
        if (r14.equals(r82.getLogEvents()) == false) goto L47;
    L31:
        if (r13.equals(r82.getLogSourceName()) == false) goto L47;
    L24:
        if (r12.equals(r82.getLogSource()) == false) goto L47;
    L17:
        if (r1.equals(r82.getClientInfo()) == true) goto L18;
    L47:
        return false;
    }

    @Override // com.google.android.datatransport.cct.internal.LogRequest
    public ClientInfo getClientInfo() {
        return this.clientInfo;
    }

    @Override // com.google.android.datatransport.cct.internal.LogRequest
    public List<LogEvent> getLogEvents() {
        return this.logEvents;
    }

    @Override // com.google.android.datatransport.cct.internal.LogRequest
    public Integer getLogSource() {
        return this.logSource;
    }

    @Override // com.google.android.datatransport.cct.internal.LogRequest
    public String getLogSourceName() {
        return this.logSourceName;
    }

    @Override // com.google.android.datatransport.cct.internal.LogRequest
    public QosTier getQosTier() {
        return this.qosTier;
    }

    @Override // com.google.android.datatransport.cct.internal.LogRequest
    public long getRequestTimeMs() {
        return this.requestTimeMs;
    }

    @Override // com.google.android.datatransport.cct.internal.LogRequest
    public long getRequestUptimeMs() {
        return this.requestUptimeMs;
    }

    public int hashCode() {
        long r0 = this.requestTimeMs;
        long r3 = this.requestUptimeMs;
        int r02 = (((((int) (r0 ^ (r0 >>> 32))) ^ 1000003) * 1000003) ^ ((int) ((r3 >>> 32) ^ r3))) * 1000003;
        ClientInfo r2 = this.clientInfo;
        int r32 = 0;
        if (r2 != null) goto L5;
        int r22 = 0;
    L6:
        int r03 = (r02 ^ r22) * 1000003;
        Integer r23 = this.logSource;
        if (r23 != null) goto L9;
        int r24 = 0;
    L10:
        int r04 = (r03 ^ r24) * 1000003;
        String r25 = this.logSourceName;
        if (r25 != null) goto L13;
        int r26 = 0;
    L14:
        int r05 = (r04 ^ r26) * 1000003;
        List<LogEvent> r27 = this.logEvents;
        if (r27 != null) goto L17;
        int r28 = 0;
    L18:
        int r06 = (r05 ^ r28) * 1000003;
        QosTier r7 = this.qosTier;
        if (r7 == null) goto L23;
        r32 = r7.hashCode();
    L23:
        return r06 ^ r32;
    L17:
        r28 = r27.hashCode();
        goto L18
    L13:
        r26 = r25.hashCode();
        goto L14
    L9:
        r24 = r23.hashCode();
        goto L10
    L5:
        r22 = r2.hashCode();
        goto L6
    }

    public String toString() {
        return "LogRequest{requestTimeMs=" + this.requestTimeMs + ", requestUptimeMs=" + this.requestUptimeMs + ", clientInfo=" + this.clientInfo + ", logSource=" + this.logSource + ", logSourceName=" + this.logSourceName + ", logEvents=" + this.logEvents + ", qosTier=" + this.qosTier + "}";
    }
}
