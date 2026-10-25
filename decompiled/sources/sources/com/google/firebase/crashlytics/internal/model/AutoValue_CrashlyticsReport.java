package com.google.firebase.crashlytics.internal.model;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

/* loaded from: classes.dex */
final class AutoValue_CrashlyticsReport extends CrashlyticsReport {
    private final CrashlyticsReport.ApplicationExitInfo appExitInfo;
    private final String appQualitySessionId;
    private final String buildVersion;
    private final String displayVersion;
    private final String firebaseAuthenticationToken;
    private final String firebaseInstallationId;
    private final String gmpAppId;
    private final String installationUuid;
    private final CrashlyticsReport.FilesPayload ndkPayload;
    private final int platform;
    private final String sdkVersion;
    private final CrashlyticsReport.Session session;

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder extends CrashlyticsReport.Builder {
        private CrashlyticsReport.ApplicationExitInfo appExitInfo;
        private String appQualitySessionId;
        private String buildVersion;
        private String displayVersion;
        private String firebaseAuthenticationToken;
        private String firebaseInstallationId;
        private String gmpAppId;
        private String installationUuid;
        private CrashlyticsReport.FilesPayload ndkPayload;
        private int platform;
        private String sdkVersion;
        private CrashlyticsReport.Session session;
        private byte set$0;

        public Builder() {
        }

        private Builder(CrashlyticsReport r2) {
            this.sdkVersion = r2.getSdkVersion();
            this.gmpAppId = r2.getGmpAppId();
            this.platform = r2.getPlatform();
            this.installationUuid = r2.getInstallationUuid();
            this.firebaseInstallationId = r2.getFirebaseInstallationId();
            this.firebaseAuthenticationToken = r2.getFirebaseAuthenticationToken();
            this.appQualitySessionId = r2.getAppQualitySessionId();
            this.buildVersion = r2.getBuildVersion();
            this.displayVersion = r2.getDisplayVersion();
            this.session = r2.getSession();
            this.ndkPayload = r2.getNdkPayload();
            this.appExitInfo = r2.getAppExitInfo();
            this.set$0 = 1;
        }

        public /* synthetic */ Builder(CrashlyticsReport r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Builder
        public CrashlyticsReport build() {
            if (this.set$0 == 1) goto L5;
        L17:
            StringBuilder r1 = new StringBuilder();
            if (this.sdkVersion != null) goto L21;
            r1.append(" sdkVersion");
        L21:
            if (this.gmpAppId != null) goto L24;
            r1.append(" gmpAppId");
        L24:
            if ((1 & this.set$0) != 0) goto L27;
            r1.append(" platform");
        L27:
            if (this.installationUuid != null) goto L30;
            r1.append(" installationUuid");
        L30:
            if (this.buildVersion != null) goto L33;
            r1.append(" buildVersion");
        L33:
            if (this.displayVersion != null) goto L35;
            r1.append(" displayVersion");
        L35:
            DexMaker$$ExternalSyntheticBUOutline0.m("Missing required properties:", r1);
            return null;
        L5:
            if (this.sdkVersion == null) goto L17;
            if (this.gmpAppId == null) goto L17;
            if (this.installationUuid == null) goto L17;
            if (this.buildVersion == null) goto L17;
            if (this.displayVersion == null) goto L17;
            return new AutoValue_CrashlyticsReport(this.sdkVersion, this.gmpAppId, this.platform, this.installationUuid, this.firebaseInstallationId, this.firebaseAuthenticationToken, this.appQualitySessionId, this.buildVersion, this.displayVersion, this.session, this.ndkPayload, this.appExitInfo, null);
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Builder
        public CrashlyticsReport.Builder setAppExitInfo(CrashlyticsReport.ApplicationExitInfo r1) {
            this.appExitInfo = r1;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Builder
        public CrashlyticsReport.Builder setAppQualitySessionId(String r1) {
            this.appQualitySessionId = r1;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Builder
        public CrashlyticsReport.Builder setBuildVersion(String r1) {
            if (r1 == null) goto L5;
            this.buildVersion = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null buildVersion");
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Builder
        public CrashlyticsReport.Builder setDisplayVersion(String r1) {
            if (r1 == null) goto L5;
            this.displayVersion = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null displayVersion");
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Builder
        public CrashlyticsReport.Builder setFirebaseAuthenticationToken(String r1) {
            this.firebaseAuthenticationToken = r1;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Builder
        public CrashlyticsReport.Builder setFirebaseInstallationId(String r1) {
            this.firebaseInstallationId = r1;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Builder
        public CrashlyticsReport.Builder setGmpAppId(String r1) {
            if (r1 == null) goto L5;
            this.gmpAppId = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null gmpAppId");
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Builder
        public CrashlyticsReport.Builder setInstallationUuid(String r1) {
            if (r1 == null) goto L5;
            this.installationUuid = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null installationUuid");
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Builder
        public CrashlyticsReport.Builder setNdkPayload(CrashlyticsReport.FilesPayload r1) {
            this.ndkPayload = r1;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Builder
        public CrashlyticsReport.Builder setPlatform(int r1) {
            this.platform = r1;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Builder
        public CrashlyticsReport.Builder setSdkVersion(String r1) {
            if (r1 == null) goto L5;
            this.sdkVersion = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null sdkVersion");
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Builder
        public CrashlyticsReport.Builder setSession(CrashlyticsReport.Session r1) {
            this.session = r1;
            return this;
        }
    }

    private AutoValue_CrashlyticsReport(String r1, String r2, int r3, String r4, String r5, String r6, String r7, String r8, String r9, CrashlyticsReport.Session r10, CrashlyticsReport.FilesPayload r11, CrashlyticsReport.ApplicationExitInfo r12) {
        this.sdkVersion = r1;
        this.gmpAppId = r2;
        this.platform = r3;
        this.installationUuid = r4;
        this.firebaseInstallationId = r5;
        this.firebaseAuthenticationToken = r6;
        this.appQualitySessionId = r7;
        this.buildVersion = r8;
        this.displayVersion = r9;
        this.session = r10;
        this.ndkPayload = r11;
        this.appExitInfo = r12;
    }

    public /* synthetic */ AutoValue_CrashlyticsReport(String r1, String r2, int r3, String r4, String r5, String r6, String r7, String r8, String r9, CrashlyticsReport.Session r10, CrashlyticsReport.FilesPayload r11, CrashlyticsReport.ApplicationExitInfo r12, AnonymousClass1 r13) {
        this(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12);
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof CrashlyticsReport) == false) goto L62;
        CrashlyticsReport r52 = (CrashlyticsReport) r5;
        if (this.sdkVersion.equals(r52.getSdkVersion()) == false) goto L62;
        if (this.gmpAppId.equals(r52.getGmpAppId()) == false) goto L62;
        if (this.platform != r52.getPlatform()) goto L62;
        if (this.installationUuid.equals(r52.getInstallationUuid()) == false) goto L62;
        String r1 = this.firebaseInstallationId;
        if (r1 != null) goto L21;
        if (r52.getFirebaseInstallationId() != null) goto L62;
    L22:
        String r12 = this.firebaseAuthenticationToken;
        if (r12 != null) goto L28;
        if (r52.getFirebaseAuthenticationToken() != null) goto L62;
    L29:
        String r13 = this.appQualitySessionId;
        if (r13 != null) goto L35;
        if (r52.getAppQualitySessionId() != null) goto L62;
    L37:
        if (this.buildVersion.equals(r52.getBuildVersion()) == false) goto L62;
        if (this.displayVersion.equals(r52.getDisplayVersion()) == false) goto L62;
        CrashlyticsReport.Session r14 = this.session;
        if (r14 != null) goto L46;
        if (r52.getSession() != null) goto L62;
    L47:
        CrashlyticsReport.FilesPayload r15 = this.ndkPayload;
        if (r15 != null) goto L53;
        if (r52.getNdkPayload() != null) goto L62;
    L54:
        CrashlyticsReport.ApplicationExitInfo r4 = this.appExitInfo;
        if (r4 != null) goto L60;
        if (r52.getAppExitInfo() != null) goto L62;
    L61:
        return true;
    L60:
        if (r4.equals(r52.getAppExitInfo()) == false) goto L62;
    L53:
        if (r15.equals(r52.getNdkPayload()) == false) goto L62;
    L46:
        if (r14.equals(r52.getSession()) == false) goto L62;
    L35:
        if (r13.equals(r52.getAppQualitySessionId()) == false) goto L62;
    L28:
        if (r12.equals(r52.getFirebaseAuthenticationToken()) == false) goto L62;
    L21:
        if (r1.equals(r52.getFirebaseInstallationId()) == true) goto L22;
    L62:
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport
    public CrashlyticsReport.ApplicationExitInfo getAppExitInfo() {
        return this.appExitInfo;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport
    public String getAppQualitySessionId() {
        return this.appQualitySessionId;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport
    public String getBuildVersion() {
        return this.buildVersion;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport
    public String getDisplayVersion() {
        return this.displayVersion;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport
    public String getFirebaseAuthenticationToken() {
        return this.firebaseAuthenticationToken;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport
    public String getFirebaseInstallationId() {
        return this.firebaseInstallationId;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport
    public String getGmpAppId() {
        return this.gmpAppId;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport
    public String getInstallationUuid() {
        return this.installationUuid;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport
    public CrashlyticsReport.FilesPayload getNdkPayload() {
        return this.ndkPayload;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport
    public int getPlatform() {
        return this.platform;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport
    public String getSdkVersion() {
        return this.sdkVersion;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport
    public CrashlyticsReport.Session getSession() {
        return this.session;
    }

    public int hashCode() {
        int r0 = (((((((this.sdkVersion.hashCode() ^ 1000003) * 1000003) ^ this.gmpAppId.hashCode()) * 1000003) ^ this.platform) * 1000003) ^ this.installationUuid.hashCode()) * 1000003;
        String r2 = this.firebaseInstallationId;
        int r3 = 0;
        if (r2 != null) goto L5;
        int r22 = 0;
    L6:
        int r02 = (r0 ^ r22) * 1000003;
        String r23 = this.firebaseAuthenticationToken;
        if (r23 != null) goto L9;
        int r24 = 0;
    L10:
        int r03 = (r02 ^ r24) * 1000003;
        String r25 = this.appQualitySessionId;
        if (r25 != null) goto L13;
        int r26 = 0;
    L14:
        int r04 = (((((r03 ^ r26) * 1000003) ^ this.buildVersion.hashCode()) * 1000003) ^ this.displayVersion.hashCode()) * 1000003;
        CrashlyticsReport.Session r27 = this.session;
        if (r27 != null) goto L17;
        int r28 = 0;
    L18:
        int r05 = (r04 ^ r28) * 1000003;
        CrashlyticsReport.FilesPayload r29 = this.ndkPayload;
        if (r29 != null) goto L21;
        int r210 = 0;
    L22:
        int r06 = (r05 ^ r210) * 1000003;
        CrashlyticsReport.ApplicationExitInfo r4 = this.appExitInfo;
        if (r4 == null) goto L27;
        r3 = r4.hashCode();
    L27:
        return r06 ^ r3;
    L21:
        r210 = r29.hashCode();
        goto L22
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

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport
    public CrashlyticsReport.Builder toBuilder() {
        return new Builder(this, null);
    }

    public String toString() {
        return "CrashlyticsReport{sdkVersion=" + this.sdkVersion + ", gmpAppId=" + this.gmpAppId + ", platform=" + this.platform + ", installationUuid=" + this.installationUuid + ", firebaseInstallationId=" + this.firebaseInstallationId + ", firebaseAuthenticationToken=" + this.firebaseAuthenticationToken + ", appQualitySessionId=" + this.appQualitySessionId + ", buildVersion=" + this.buildVersion + ", displayVersion=" + this.displayVersion + ", session=" + this.session + ", ndkPayload=" + this.ndkPayload + ", appExitInfo=" + this.appExitInfo + "}";
    }
}
