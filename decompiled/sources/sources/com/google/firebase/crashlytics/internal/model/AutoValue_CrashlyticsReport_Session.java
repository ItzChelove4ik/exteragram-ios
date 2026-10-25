package com.google.firebase.crashlytics.internal.model;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.util.List;

/* loaded from: classes.dex */
final class AutoValue_CrashlyticsReport_Session extends CrashlyticsReport.Session {
    private final CrashlyticsReport.Session.Application app;
    private final String appQualitySessionId;
    private final boolean crashed;
    private final CrashlyticsReport.Session.Device device;
    private final Long endedAt;
    private final List<CrashlyticsReport.Session.Event> events;
    private final String generator;
    private final int generatorType;
    private final String identifier;
    private final CrashlyticsReport.Session.OperatingSystem os;
    private final long startedAt;
    private final CrashlyticsReport.Session.User user;

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder extends CrashlyticsReport.Session.Builder {
        private CrashlyticsReport.Session.Application app;
        private String appQualitySessionId;
        private boolean crashed;
        private CrashlyticsReport.Session.Device device;
        private Long endedAt;
        private List<CrashlyticsReport.Session.Event> events;
        private String generator;
        private int generatorType;
        private String identifier;
        private CrashlyticsReport.Session.OperatingSystem os;
        private byte set$0;
        private long startedAt;
        private CrashlyticsReport.Session.User user;

        public Builder() {
        }

        private Builder(CrashlyticsReport.Session r3) {
            this.generator = r3.getGenerator();
            this.identifier = r3.getIdentifier();
            this.appQualitySessionId = r3.getAppQualitySessionId();
            this.startedAt = r3.getStartedAt();
            this.endedAt = r3.getEndedAt();
            this.crashed = r3.isCrashed();
            this.app = r3.getApp();
            this.user = r3.getUser();
            this.os = r3.getOs();
            this.device = r3.getDevice();
            this.events = r3.getEvents();
            this.generatorType = r3.getGeneratorType();
            this.set$0 = 7;
        }

        public /* synthetic */ Builder(CrashlyticsReport.Session r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session build() {
            if (this.set$0 != 7) goto L13;
            String r4 = this.generator;
            if (r4 == null) goto L13;
            String r5 = this.identifier;
            if (r5 == null) goto L13;
            CrashlyticsReport.Session.Application r11 = this.app;
            if (r11 == null) goto L13;
            return new AutoValue_CrashlyticsReport_Session(r4, r5, this.appQualitySessionId, this.startedAt, this.endedAt, this.crashed, r11, this.user, this.os, this.device, this.events, this.generatorType, null);
        L13:
            StringBuilder r1 = new StringBuilder();
            if (this.generator != null) goto L17;
            r1.append(" generator");
        L17:
            if (this.identifier != null) goto L20;
            r1.append(" identifier");
        L20:
            if ((this.set$0 & 1) != 0) goto L23;
            r1.append(" startedAt");
        L23:
            if ((this.set$0 & 2) != 0) goto L26;
            r1.append(" crashed");
        L26:
            if (this.app != null) goto L29;
            r1.append(" app");
        L29:
            if ((this.set$0 & 4) != 0) goto L31;
            r1.append(" generatorType");
        L31:
            DexMaker$$ExternalSyntheticBUOutline0.m("Missing required properties:", r1);
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session.Builder setApp(CrashlyticsReport.Session.Application r1) {
            if (r1 == null) goto L5;
            this.app = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null app");
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session.Builder setAppQualitySessionId(String r1) {
            this.appQualitySessionId = r1;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session.Builder setCrashed(boolean r1) {
            this.crashed = r1;
            this.set$0 = (byte) (this.set$0 | 2);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session.Builder setDevice(CrashlyticsReport.Session.Device r1) {
            this.device = r1;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session.Builder setEndedAt(Long r1) {
            this.endedAt = r1;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session.Builder setEvents(List<CrashlyticsReport.Session.Event> r1) {
            this.events = r1;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session.Builder setGenerator(String r1) {
            if (r1 == null) goto L5;
            this.generator = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null generator");
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session.Builder setGeneratorType(int r1) {
            this.generatorType = r1;
            this.set$0 = (byte) (this.set$0 | 4);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session.Builder setIdentifier(String r1) {
            if (r1 == null) goto L5;
            this.identifier = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null identifier");
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session.Builder setOs(CrashlyticsReport.Session.OperatingSystem r1) {
            this.os = r1;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session.Builder setStartedAt(long r1) {
            this.startedAt = r1;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session.Builder setUser(CrashlyticsReport.Session.User r1) {
            this.user = r1;
            return this;
        }
    }

    private AutoValue_CrashlyticsReport_Session(String r1, String r2, String r3, long r4, Long r6, boolean r7, CrashlyticsReport.Session.Application r8, CrashlyticsReport.Session.User r9, CrashlyticsReport.Session.OperatingSystem r10, CrashlyticsReport.Session.Device r11, List<CrashlyticsReport.Session.Event> r12, int r13) {
        this.generator = r1;
        this.identifier = r2;
        this.appQualitySessionId = r3;
        this.startedAt = r4;
        this.endedAt = r6;
        this.crashed = r7;
        this.app = r8;
        this.user = r9;
        this.os = r10;
        this.device = r11;
        this.events = r12;
        this.generatorType = r13;
    }

    public /* synthetic */ AutoValue_CrashlyticsReport_Session(String r1, String r2, String r3, long r4, Long r6, boolean r7, CrashlyticsReport.Session.Application r8, CrashlyticsReport.Session.User r9, CrashlyticsReport.Session.OperatingSystem r10, CrashlyticsReport.Session.Device r11, List r12, int r13, AnonymousClass1 r14) {
        this(r1, r2, r3, r4, r6, r7, r8, r9, r10, r11, r12, r13);
    }

    public boolean equals(Object r8) {
        if (r8 != this) goto L6;
        return true;
    L6:
        if ((r8 instanceof CrashlyticsReport.Session) == false) goto L62;
        CrashlyticsReport.Session r82 = (CrashlyticsReport.Session) r8;
        if (this.generator.equals(r82.getGenerator()) == false) goto L62;
        if (this.identifier.equals(r82.getIdentifier()) == false) goto L62;
        String r1 = this.appQualitySessionId;
        if (r1 != null) goto L17;
        if (r82.getAppQualitySessionId() != null) goto L62;
    L19:
        if (this.startedAt != r82.getStartedAt()) goto L62;
        Long r12 = this.endedAt;
        if (r12 != null) goto L26;
        if (r82.getEndedAt() != null) goto L62;
    L28:
        if (this.crashed != r82.isCrashed()) goto L62;
        if (this.app.equals(r82.getApp()) == false) goto L62;
        CrashlyticsReport.Session.User r13 = this.user;
        if (r13 != null) goto L37;
        if (r82.getUser() != null) goto L62;
    L38:
        CrashlyticsReport.Session.OperatingSystem r14 = this.os;
        if (r14 != null) goto L44;
        if (r82.getOs() != null) goto L62;
    L45:
        CrashlyticsReport.Session.Device r15 = this.device;
        if (r15 != null) goto L51;
        if (r82.getDevice() != null) goto L62;
    L52:
        List<CrashlyticsReport.Session.Event> r16 = this.events;
        if (r16 != null) goto L58;
        if (r82.getEvents() != null) goto L62;
    L60:
        if (this.generatorType != r82.getGeneratorType()) goto L62;
        return true;
    L58:
        if (r16.equals(r82.getEvents()) == false) goto L62;
    L51:
        if (r15.equals(r82.getDevice()) == false) goto L62;
    L44:
        if (r14.equals(r82.getOs()) == false) goto L62;
    L37:
        if (r13.equals(r82.getUser()) == false) goto L62;
    L26:
        if (r12.equals(r82.getEndedAt()) == false) goto L62;
    L17:
        if (r1.equals(r82.getAppQualitySessionId()) == true) goto L19;
    L62:
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    public CrashlyticsReport.Session.Application getApp() {
        return this.app;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    public String getAppQualitySessionId() {
        return this.appQualitySessionId;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    public CrashlyticsReport.Session.Device getDevice() {
        return this.device;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    public Long getEndedAt() {
        return this.endedAt;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    public List<CrashlyticsReport.Session.Event> getEvents() {
        return this.events;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    public String getGenerator() {
        return this.generator;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    public int getGeneratorType() {
        return this.generatorType;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    public String getIdentifier() {
        return this.identifier;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    public CrashlyticsReport.Session.OperatingSystem getOs() {
        return this.os;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    public long getStartedAt() {
        return this.startedAt;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    public CrashlyticsReport.Session.User getUser() {
        return this.user;
    }

    public int hashCode() {
        int r0 = (((this.generator.hashCode() ^ 1000003) * 1000003) ^ this.identifier.hashCode()) * 1000003;
        String r2 = this.appQualitySessionId;
        int r3 = 0;
        if (r2 != null) goto L5;
        int r22 = 0;
    L6:
        long r4 = this.startedAt;
        int r02 = (((r0 ^ r22) * 1000003) ^ ((int) (r4 ^ (r4 >>> 32)))) * 1000003;
        Long r23 = this.endedAt;
        if (r23 != null) goto L9;
        int r24 = 0;
    L10:
        int r03 = (r02 ^ r24) * 1000003;
        if (this.crashed == false) goto L13;
        int r25 = 1231;
    L14:
        int r04 = (((r03 ^ r25) * 1000003) ^ this.app.hashCode()) * 1000003;
        CrashlyticsReport.Session.User r26 = this.user;
        if (r26 != null) goto L17;
        int r27 = 0;
    L18:
        int r05 = (r04 ^ r27) * 1000003;
        CrashlyticsReport.Session.OperatingSystem r28 = this.os;
        if (r28 != null) goto L21;
        int r29 = 0;
    L22:
        int r06 = (r05 ^ r29) * 1000003;
        CrashlyticsReport.Session.Device r210 = this.device;
        if (r210 != null) goto L25;
        int r211 = 0;
    L26:
        int r07 = (r06 ^ r211) * 1000003;
        List<CrashlyticsReport.Session.Event> r212 = this.events;
        if (r212 == null) goto L31;
        r3 = r212.hashCode();
    L31:
        return this.generatorType ^ ((r07 ^ r3) * 1000003);
    L25:
        r211 = r210.hashCode();
        goto L26
    L21:
        r29 = r28.hashCode();
        goto L22
    L17:
        r27 = r26.hashCode();
        goto L18
    L13:
        r25 = 1237;
        goto L14
    L9:
        r24 = r23.hashCode();
        goto L10
    L5:
        r22 = r2.hashCode();
        goto L6
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    public boolean isCrashed() {
        return this.crashed;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    public CrashlyticsReport.Session.Builder toBuilder() {
        return new Builder(this, null);
    }

    public String toString() {
        return "Session{generator=" + this.generator + ", identifier=" + this.identifier + ", appQualitySessionId=" + this.appQualitySessionId + ", startedAt=" + this.startedAt + ", endedAt=" + this.endedAt + ", crashed=" + this.crashed + ", app=" + this.app + ", user=" + this.user + ", os=" + this.os + ", device=" + this.device + ", events=" + this.events + ", generatorType=" + this.generatorType + "}";
    }
}
