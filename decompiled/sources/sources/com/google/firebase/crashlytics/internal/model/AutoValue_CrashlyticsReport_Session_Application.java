package com.google.firebase.crashlytics.internal.model;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

/* loaded from: classes.dex */
final class AutoValue_CrashlyticsReport_Session_Application extends CrashlyticsReport.Session.Application {
    private final String developmentPlatform;
    private final String developmentPlatformVersion;
    private final String displayVersion;
    private final String identifier;
    private final String installationUuid;
    private final String version;

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Application$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder extends CrashlyticsReport.Session.Application.Builder {
        private String developmentPlatform;
        private String developmentPlatformVersion;
        private String displayVersion;
        private String identifier;
        private String installationUuid;
        private String version;

        public Builder() {
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application.Builder
        public CrashlyticsReport.Session.Application build() {
            String r1 = this.identifier;
            if (r1 == null) goto L9;
            String r2 = this.version;
            if (r2 == null) goto L9;
            return new AutoValue_CrashlyticsReport_Session_Application(r1, r2, this.displayVersion, null, this.installationUuid, this.developmentPlatform, this.developmentPlatformVersion, null);
        L9:
            StringBuilder r0 = new StringBuilder();
            if (this.identifier != null) goto L13;
            r0.append(" identifier");
        L13:
            if (this.version != null) goto L15;
            r0.append(" version");
        L15:
            DexMaker$$ExternalSyntheticBUOutline0.m("Missing required properties:", r0);
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application.Builder
        public CrashlyticsReport.Session.Application.Builder setDevelopmentPlatform(String r1) {
            this.developmentPlatform = r1;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application.Builder
        public CrashlyticsReport.Session.Application.Builder setDevelopmentPlatformVersion(String r1) {
            this.developmentPlatformVersion = r1;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application.Builder
        public CrashlyticsReport.Session.Application.Builder setDisplayVersion(String r1) {
            this.displayVersion = r1;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application.Builder
        public CrashlyticsReport.Session.Application.Builder setIdentifier(String r1) {
            if (r1 == null) goto L5;
            this.identifier = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null identifier");
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application.Builder
        public CrashlyticsReport.Session.Application.Builder setInstallationUuid(String r1) {
            this.installationUuid = r1;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application.Builder
        public CrashlyticsReport.Session.Application.Builder setVersion(String r1) {
            if (r1 == null) goto L5;
            this.version = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null version");
            return null;
        }
    }

    private AutoValue_CrashlyticsReport_Session_Application(String r1, String r2, String r3, CrashlyticsReport.Session.Application.Organization r4, String r5, String r6, String r7) {
        this.identifier = r1;
        this.version = r2;
        this.displayVersion = r3;
        this.installationUuid = r5;
        this.developmentPlatform = r6;
        this.developmentPlatformVersion = r7;
    }

    public /* synthetic */ AutoValue_CrashlyticsReport_Session_Application(String r1, String r2, String r3, CrashlyticsReport.Session.Application.Organization r4, String r5, String r6, String r7, AnonymousClass1 r8) {
        this(r1, r2, r3, r4, r5, r6, r7);
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof CrashlyticsReport.Session.Application) == false) goto L40;
        CrashlyticsReport.Session.Application r52 = (CrashlyticsReport.Session.Application) r5;
        if (this.identifier.equals(r52.getIdentifier()) == false) goto L40;
        if (this.version.equals(r52.getVersion()) == false) goto L40;
        String r1 = this.displayVersion;
        if (r1 != null) goto L17;
        if (r52.getDisplayVersion() != null) goto L40;
    L18:
        r52.getOrganization();
        String r12 = this.installationUuid;
        if (r12 != null) goto L24;
        if (r52.getInstallationUuid() != null) goto L40;
    L25:
        String r13 = this.developmentPlatform;
        if (r13 != null) goto L31;
        if (r52.getDevelopmentPlatform() != null) goto L40;
    L32:
        String r4 = this.developmentPlatformVersion;
        if (r4 != null) goto L38;
        if (r52.getDevelopmentPlatformVersion() != null) goto L40;
    L39:
        return true;
    L38:
        if (r4.equals(r52.getDevelopmentPlatformVersion()) == false) goto L40;
    L31:
        if (r13.equals(r52.getDevelopmentPlatform()) == false) goto L40;
    L24:
        if (r12.equals(r52.getInstallationUuid()) == false) goto L40;
    L17:
        if (r1.equals(r52.getDisplayVersion()) == true) goto L18;
    L40:
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application
    public String getDevelopmentPlatform() {
        return this.developmentPlatform;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application
    public String getDevelopmentPlatformVersion() {
        return this.developmentPlatformVersion;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application
    public String getDisplayVersion() {
        return this.displayVersion;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application
    public String getIdentifier() {
        return this.identifier;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application
    public String getInstallationUuid() {
        return this.installationUuid;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application
    public CrashlyticsReport.Session.Application.Organization getOrganization() {
        return null;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application
    public String getVersion() {
        return this.version;
    }

    public int hashCode() {
        int r0 = (((this.identifier.hashCode() ^ 1000003) * 1000003) ^ this.version.hashCode()) * 1000003;
        String r2 = this.displayVersion;
        int r3 = 0;
        if (r2 != null) goto L5;
        int r22 = 0;
    L6:
        int r02 = (r0 ^ r22) * (-721379959);
        String r23 = this.installationUuid;
        if (r23 != null) goto L9;
        int r24 = 0;
    L10:
        int r03 = (r02 ^ r24) * 1000003;
        String r25 = this.developmentPlatform;
        if (r25 != null) goto L13;
        int r26 = 0;
    L14:
        int r04 = (r03 ^ r26) * 1000003;
        String r4 = this.developmentPlatformVersion;
        if (r4 == null) goto L19;
        r3 = r4.hashCode();
    L19:
        return r04 ^ r3;
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
        return "Application{identifier=" + this.identifier + ", version=" + this.version + ", displayVersion=" + this.displayVersion + ", organization=null, installationUuid=" + this.installationUuid + ", developmentPlatform=" + this.developmentPlatform + ", developmentPlatformVersion=" + this.developmentPlatformVersion + "}";
    }
}
