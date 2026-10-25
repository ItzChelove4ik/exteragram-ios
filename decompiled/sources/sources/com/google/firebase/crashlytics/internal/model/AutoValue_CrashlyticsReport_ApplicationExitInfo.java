package com.google.firebase.crashlytics.internal.model;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.util.List;

/* loaded from: classes.dex */
final class AutoValue_CrashlyticsReport_ApplicationExitInfo extends CrashlyticsReport.ApplicationExitInfo {
    private final List<CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch> buildIdMappingForArch;
    private final int importance;
    private final int pid;
    private final String processName;
    private final long pss;
    private final int reasonCode;
    private final long rss;
    private final long timestamp;
    private final String traceFile;

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_ApplicationExitInfo$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    /* loaded from: classes5.dex */
    public static final class Builder extends CrashlyticsReport.ApplicationExitInfo.Builder {
        private List<CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch> buildIdMappingForArch;
        private int importance;
        private int pid;
        private String processName;
        private long pss;
        private int reasonCode;
        private long rss;
        private byte set$0;
        private long timestamp;
        private String traceFile;

        public Builder() {
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.ApplicationExitInfo.Builder
        public CrashlyticsReport.ApplicationExitInfo build() {
            if (this.set$0 != 63) goto L9;
            String r5 = this.processName;
            if (r5 == null) goto L9;
            return new AutoValue_CrashlyticsReport_ApplicationExitInfo(this.pid, r5, this.reasonCode, this.importance, this.pss, this.rss, this.timestamp, this.traceFile, this.buildIdMappingForArch, null);
        L9:
            StringBuilder r1 = new StringBuilder();
            if ((this.set$0 & 1) != 0) goto L13;
            r1.append(" pid");
        L13:
            if (this.processName != null) goto L16;
            r1.append(" processName");
        L16:
            if ((this.set$0 & 2) != 0) goto L19;
            r1.append(" reasonCode");
        L19:
            if ((this.set$0 & 4) != 0) goto L22;
            r1.append(" importance");
        L22:
            if ((this.set$0 & 8) != 0) goto L25;
            r1.append(" pss");
        L25:
            if ((this.set$0 & 16) != 0) goto L28;
            r1.append(" rss");
        L28:
            if ((this.set$0 & 32) != 0) goto L30;
            r1.append(" timestamp");
        L30:
            DexMaker$$ExternalSyntheticBUOutline0.m("Missing required properties:", r1);
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.ApplicationExitInfo.Builder
        public CrashlyticsReport.ApplicationExitInfo.Builder setBuildIdMappingForArch(List<CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch> r1) {
            this.buildIdMappingForArch = r1;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.ApplicationExitInfo.Builder
        public CrashlyticsReport.ApplicationExitInfo.Builder setImportance(int r1) {
            this.importance = r1;
            this.set$0 = (byte) (this.set$0 | 4);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.ApplicationExitInfo.Builder
        public CrashlyticsReport.ApplicationExitInfo.Builder setPid(int r1) {
            this.pid = r1;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.ApplicationExitInfo.Builder
        public CrashlyticsReport.ApplicationExitInfo.Builder setProcessName(String r1) {
            if (r1 == null) goto L5;
            this.processName = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null processName");
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.ApplicationExitInfo.Builder
        public CrashlyticsReport.ApplicationExitInfo.Builder setPss(long r1) {
            this.pss = r1;
            this.set$0 = (byte) (this.set$0 | 8);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.ApplicationExitInfo.Builder
        public CrashlyticsReport.ApplicationExitInfo.Builder setReasonCode(int r1) {
            this.reasonCode = r1;
            this.set$0 = (byte) (this.set$0 | 2);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.ApplicationExitInfo.Builder
        public CrashlyticsReport.ApplicationExitInfo.Builder setRss(long r1) {
            this.rss = r1;
            this.set$0 = (byte) (this.set$0 | 16);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.ApplicationExitInfo.Builder
        public CrashlyticsReport.ApplicationExitInfo.Builder setTimestamp(long r1) {
            this.timestamp = r1;
            this.set$0 = (byte) (this.set$0 | 32);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.ApplicationExitInfo.Builder
        public CrashlyticsReport.ApplicationExitInfo.Builder setTraceFile(String r1) {
            this.traceFile = r1;
            return this;
        }
    }

    private AutoValue_CrashlyticsReport_ApplicationExitInfo(int r1, String r2, int r3, int r4, long r5, long r7, long r9, String r11, List<CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch> r12) {
        this.pid = r1;
        this.processName = r2;
        this.reasonCode = r3;
        this.importance = r4;
        this.pss = r5;
        this.rss = r7;
        this.timestamp = r9;
        this.traceFile = r11;
        this.buildIdMappingForArch = r12;
    }

    public /* synthetic */ AutoValue_CrashlyticsReport_ApplicationExitInfo(int r1, String r2, int r3, int r4, long r5, long r7, long r9, String r11, List r12, AnonymousClass1 r13) {
        this(r1, r2, r3, r4, r5, r7, r9, r11, r12);
    }

    public boolean equals(Object r8) {
        if (r8 != this) goto L6;
        return true;
    L6:
        if ((r8 instanceof CrashlyticsReport.ApplicationExitInfo) == false) goto L36;
        CrashlyticsReport.ApplicationExitInfo r82 = (CrashlyticsReport.ApplicationExitInfo) r8;
        if (this.pid != r82.getPid()) goto L36;
        if (this.processName.equals(r82.getProcessName()) == false) goto L36;
        if (this.reasonCode != r82.getReasonCode()) goto L36;
        if (this.importance != r82.getImportance()) goto L36;
        if (this.pss != r82.getPss()) goto L36;
        if (this.rss != r82.getRss()) goto L36;
        if (this.timestamp != r82.getTimestamp()) goto L36;
        String r1 = this.traceFile;
        if (r1 != null) goto L27;
        if (r82.getTraceFile() != null) goto L36;
    L28:
        List<CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch> r7 = this.buildIdMappingForArch;
        if (r7 != null) goto L34;
        if (r82.getBuildIdMappingForArch() != null) goto L36;
    L35:
        return true;
    L34:
        if (r7.equals(r82.getBuildIdMappingForArch()) == false) goto L36;
    L27:
        if (r1.equals(r82.getTraceFile()) == true) goto L28;
    L36:
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.ApplicationExitInfo
    public List<CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch> getBuildIdMappingForArch() {
        return this.buildIdMappingForArch;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.ApplicationExitInfo
    public int getImportance() {
        return this.importance;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.ApplicationExitInfo
    public int getPid() {
        return this.pid;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.ApplicationExitInfo
    public String getProcessName() {
        return this.processName;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.ApplicationExitInfo
    public long getPss() {
        return this.pss;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.ApplicationExitInfo
    public int getReasonCode() {
        return this.reasonCode;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.ApplicationExitInfo
    public long getRss() {
        return this.rss;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.ApplicationExitInfo
    public long getTimestamp() {
        return this.timestamp;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.ApplicationExitInfo
    public String getTraceFile() {
        return this.traceFile;
    }

    public int hashCode() {
        int r0 = (((((((this.pid ^ 1000003) * 1000003) ^ this.processName.hashCode()) * 1000003) ^ this.reasonCode) * 1000003) ^ this.importance) * 1000003;
        long r2 = this.pss;
        int r02 = (r0 ^ ((int) (r2 ^ (r2 >>> 32)))) * 1000003;
        long r22 = this.rss;
        int r03 = (r02 ^ ((int) (r22 ^ (r22 >>> 32)))) * 1000003;
        long r23 = this.timestamp;
        int r04 = (r03 ^ ((int) (r23 ^ (r23 >>> 32)))) * 1000003;
        String r24 = this.traceFile;
        int r3 = 0;
        if (r24 != null) goto L5;
        int r25 = 0;
    L6:
        int r05 = (r04 ^ r25) * 1000003;
        List<CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch> r7 = this.buildIdMappingForArch;
        if (r7 == null) goto L11;
        r3 = r7.hashCode();
    L11:
        return r05 ^ r3;
    L5:
        r25 = r24.hashCode();
        goto L6
    }

    public String toString() {
        return "ApplicationExitInfo{pid=" + this.pid + ", processName=" + this.processName + ", reasonCode=" + this.reasonCode + ", importance=" + this.importance + ", pss=" + this.pss + ", rss=" + this.rss + ", timestamp=" + this.timestamp + ", traceFile=" + this.traceFile + ", buildIdMappingForArch=" + this.buildIdMappingForArch + "}";
    }
}
