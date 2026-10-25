package com.google.firebase.crashlytics.internal.model;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

/* loaded from: classes.dex */
final class AutoValue_CrashlyticsReport_Session_OperatingSystem extends CrashlyticsReport.Session.OperatingSystem {
    private final String buildVersion;
    private final boolean jailbroken;
    private final int platform;
    private final String version;

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_OperatingSystem$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder extends CrashlyticsReport.Session.OperatingSystem.Builder {
        private String buildVersion;
        private boolean jailbroken;
        private int platform;
        private byte set$0;
        private String version;

        public Builder() {
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.OperatingSystem.Builder
        public CrashlyticsReport.Session.OperatingSystem build() {
            if (this.set$0 != 3) goto L11;
            String r4 = this.version;
            if (r4 == null) goto L11;
            String r5 = this.buildVersion;
            if (r5 == null) goto L11;
            return new AutoValue_CrashlyticsReport_Session_OperatingSystem(this.platform, r4, r5, this.jailbroken, null);
        L11:
            StringBuilder r0 = new StringBuilder();
            if ((this.set$0 & 1) != 0) goto L15;
            r0.append(" platform");
        L15:
            if (this.version != null) goto L18;
            r0.append(" version");
        L18:
            if (this.buildVersion != null) goto L21;
            r0.append(" buildVersion");
        L21:
            if ((this.set$0 & 2) != 0) goto L23;
            r0.append(" jailbroken");
        L23:
            DexMaker$$ExternalSyntheticBUOutline0.m("Missing required properties:", r0);
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.OperatingSystem.Builder
        public CrashlyticsReport.Session.OperatingSystem.Builder setBuildVersion(String r1) {
            if (r1 == null) goto L5;
            this.buildVersion = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null buildVersion");
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.OperatingSystem.Builder
        public CrashlyticsReport.Session.OperatingSystem.Builder setJailbroken(boolean r1) {
            this.jailbroken = r1;
            this.set$0 = (byte) (this.set$0 | 2);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.OperatingSystem.Builder
        public CrashlyticsReport.Session.OperatingSystem.Builder setPlatform(int r1) {
            this.platform = r1;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.OperatingSystem.Builder
        public CrashlyticsReport.Session.OperatingSystem.Builder setVersion(String r1) {
            if (r1 == null) goto L5;
            this.version = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null version");
            return null;
        }
    }

    private AutoValue_CrashlyticsReport_Session_OperatingSystem(int r1, String r2, String r3, boolean r4) {
        this.platform = r1;
        this.version = r2;
        this.buildVersion = r3;
        this.jailbroken = r4;
    }

    public /* synthetic */ AutoValue_CrashlyticsReport_Session_OperatingSystem(int r1, String r2, String r3, boolean r4, AnonymousClass1 r5) {
        this(r1, r2, r3, r4);
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof CrashlyticsReport.Session.OperatingSystem) == false) goto L16;
        CrashlyticsReport.Session.OperatingSystem r52 = (CrashlyticsReport.Session.OperatingSystem) r5;
        if (this.platform != r52.getPlatform()) goto L16;
        if (this.version.equals(r52.getVersion()) == false) goto L16;
        if (this.buildVersion.equals(r52.getBuildVersion()) == false) goto L16;
        if (this.jailbroken != r52.isJailbroken()) goto L16;
        return true;
    L16:
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.OperatingSystem
    public String getBuildVersion() {
        return this.buildVersion;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.OperatingSystem
    public int getPlatform() {
        return this.platform;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.OperatingSystem
    public String getVersion() {
        return this.version;
    }

    public int hashCode() {
        int r0 = (((((this.platform ^ 1000003) * 1000003) ^ this.version.hashCode()) * 1000003) ^ this.buildVersion.hashCode()) * 1000003;
        if (this.jailbroken == false) goto L5;
        int r3 = 1231;
    L7:
        return r3 ^ r0;
    L5:
        r3 = 1237;
        goto L7
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.OperatingSystem
    public boolean isJailbroken() {
        return this.jailbroken;
    }

    public String toString() {
        return "OperatingSystem{platform=" + this.platform + ", version=" + this.version + ", buildVersion=" + this.buildVersion + ", jailbroken=" + this.jailbroken + "}";
    }
}
