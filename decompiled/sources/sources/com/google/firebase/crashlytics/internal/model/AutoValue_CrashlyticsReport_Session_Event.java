package com.google.firebase.crashlytics.internal.model;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

/* loaded from: classes.dex */
final class AutoValue_CrashlyticsReport_Session_Event extends CrashlyticsReport.Session.Event {
    private final CrashlyticsReport.Session.Event.Application app;
    private final CrashlyticsReport.Session.Event.Device device;
    private final CrashlyticsReport.Session.Event.Log log;
    private final CrashlyticsReport.Session.Event.RolloutsState rollouts;
    private final long timestamp;
    private final String type;

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    /* loaded from: classes5.dex */
    public static final class Builder extends CrashlyticsReport.Session.Event.Builder {
        private CrashlyticsReport.Session.Event.Application app;
        private CrashlyticsReport.Session.Event.Device device;
        private CrashlyticsReport.Session.Event.Log log;
        private CrashlyticsReport.Session.Event.RolloutsState rollouts;
        private byte set$0;
        private long timestamp;
        private String type;

        public Builder() {
        }

        private Builder(CrashlyticsReport.Session.Event r3) {
            this.timestamp = r3.getTimestamp();
            this.type = r3.getType();
            this.app = r3.getApp();
            this.device = r3.getDevice();
            this.log = r3.getLog();
            this.rollouts = r3.getRollouts();
            this.set$0 = 1;
        }

        public /* synthetic */ Builder(CrashlyticsReport.Session.Event r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Builder
        public CrashlyticsReport.Session.Event build() {
            if (this.set$0 != 1) goto L13;
            String r5 = this.type;
            if (r5 == null) goto L13;
            CrashlyticsReport.Session.Event.Application r6 = this.app;
            if (r6 == null) goto L13;
            CrashlyticsReport.Session.Event.Device r7 = this.device;
            if (r7 == null) goto L13;
            return new AutoValue_CrashlyticsReport_Session_Event(this.timestamp, r5, r6, r7, this.log, this.rollouts, null);
        L13:
            StringBuilder r0 = new StringBuilder();
            if ((1 & this.set$0) != 0) goto L17;
            r0.append(" timestamp");
        L17:
            if (this.type != null) goto L20;
            r0.append(" type");
        L20:
            if (this.app != null) goto L23;
            r0.append(" app");
        L23:
            if (this.device != null) goto L25;
            r0.append(" device");
        L25:
            DexMaker$$ExternalSyntheticBUOutline0.m("Missing required properties:", r0);
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Builder
        public CrashlyticsReport.Session.Event.Builder setApp(CrashlyticsReport.Session.Event.Application r1) {
            if (r1 == null) goto L5;
            this.app = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null app");
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Builder
        public CrashlyticsReport.Session.Event.Builder setDevice(CrashlyticsReport.Session.Event.Device r1) {
            if (r1 == null) goto L5;
            this.device = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null device");
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Builder
        public CrashlyticsReport.Session.Event.Builder setLog(CrashlyticsReport.Session.Event.Log r1) {
            this.log = r1;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Builder
        public CrashlyticsReport.Session.Event.Builder setRollouts(CrashlyticsReport.Session.Event.RolloutsState r1) {
            this.rollouts = r1;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Builder
        public CrashlyticsReport.Session.Event.Builder setTimestamp(long r1) {
            this.timestamp = r1;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Builder
        public CrashlyticsReport.Session.Event.Builder setType(String r1) {
            if (r1 == null) goto L5;
            this.type = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null type");
            return null;
        }
    }

    private AutoValue_CrashlyticsReport_Session_Event(long r1, String r3, CrashlyticsReport.Session.Event.Application r4, CrashlyticsReport.Session.Event.Device r5, CrashlyticsReport.Session.Event.Log r6, CrashlyticsReport.Session.Event.RolloutsState r7) {
        this.timestamp = r1;
        this.type = r3;
        this.app = r4;
        this.device = r5;
        this.log = r6;
        this.rollouts = r7;
    }

    public /* synthetic */ AutoValue_CrashlyticsReport_Session_Event(long r1, String r3, CrashlyticsReport.Session.Event.Application r4, CrashlyticsReport.Session.Event.Device r5, CrashlyticsReport.Session.Event.Log r6, CrashlyticsReport.Session.Event.RolloutsState r7, AnonymousClass1 r8) {
        this(r1, r3, r4, r5, r6, r7);
    }

    public boolean equals(Object r8) {
        if (r8 != this) goto L6;
        return true;
    L6:
        if ((r8 instanceof CrashlyticsReport.Session.Event) == false) goto L30;
        CrashlyticsReport.Session.Event r82 = (CrashlyticsReport.Session.Event) r8;
        if (this.timestamp != r82.getTimestamp()) goto L30;
        if (this.type.equals(r82.getType()) == false) goto L30;
        if (this.app.equals(r82.getApp()) == false) goto L30;
        if (this.device.equals(r82.getDevice()) == false) goto L30;
        CrashlyticsReport.Session.Event.Log r1 = this.log;
        if (r1 != null) goto L21;
        if (r82.getLog() != null) goto L30;
    L22:
        CrashlyticsReport.Session.Event.RolloutsState r7 = this.rollouts;
        if (r7 != null) goto L28;
        if (r82.getRollouts() != null) goto L30;
    L29:
        return true;
    L28:
        if (r7.equals(r82.getRollouts()) == false) goto L30;
    L21:
        if (r1.equals(r82.getLog()) == true) goto L22;
    L30:
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event
    public CrashlyticsReport.Session.Event.Application getApp() {
        return this.app;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event
    public CrashlyticsReport.Session.Event.Device getDevice() {
        return this.device;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event
    public CrashlyticsReport.Session.Event.Log getLog() {
        return this.log;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event
    public CrashlyticsReport.Session.Event.RolloutsState getRollouts() {
        return this.rollouts;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event
    public long getTimestamp() {
        return this.timestamp;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event
    public String getType() {
        return this.type;
    }

    public int hashCode() {
        long r0 = this.timestamp;
        int r02 = (((((((((int) (r0 ^ (r0 >>> 32))) ^ 1000003) * 1000003) ^ this.type.hashCode()) * 1000003) ^ this.app.hashCode()) * 1000003) ^ this.device.hashCode()) * 1000003;
        CrashlyticsReport.Session.Event.Log r2 = this.log;
        int r3 = 0;
        if (r2 != null) goto L5;
        int r22 = 0;
    L6:
        int r03 = (r02 ^ r22) * 1000003;
        CrashlyticsReport.Session.Event.RolloutsState r4 = this.rollouts;
        if (r4 == null) goto L11;
        r3 = r4.hashCode();
    L11:
        return r03 ^ r3;
    L5:
        r22 = r2.hashCode();
        goto L6
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event
    public CrashlyticsReport.Session.Event.Builder toBuilder() {
        return new Builder(this, null);
    }

    public String toString() {
        return "Event{timestamp=" + this.timestamp + ", type=" + this.type + ", app=" + this.app + ", device=" + this.device + ", log=" + this.log + ", rollouts=" + this.rollouts + "}";
    }
}
