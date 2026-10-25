package com.google.firebase.crashlytics.internal.common;

import c.f$$ExternalSyntheticBUOutline2;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.io.File;

/* loaded from: classes5.dex */
final class AutoValue_CrashlyticsReportWithSessionId extends CrashlyticsReportWithSessionId {
    private final CrashlyticsReport report;
    private final File reportFile;
    private final String sessionId;

    public AutoValue_CrashlyticsReportWithSessionId(CrashlyticsReport r2, String r3, File r4) {
        if (r2 == null) goto L14;
        this.report = r2;
        if (r3 == null) goto L12;
        this.sessionId = r3;
        if (r4 == null) goto L10;
        this.reportFile = r4;
        return;
    L10:
        f$$ExternalSyntheticBUOutline2.m("Null reportFile");
        throw null;
    L12:
        f$$ExternalSyntheticBUOutline2.m("Null sessionId");
        throw null;
    L14:
        f$$ExternalSyntheticBUOutline2.m("Null report");
        throw null;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof CrashlyticsReportWithSessionId) == false) goto L14;
        CrashlyticsReportWithSessionId r52 = (CrashlyticsReportWithSessionId) r5;
        if (this.report.equals(r52.getReport()) == false) goto L14;
        if (this.sessionId.equals(r52.getSessionId()) == false) goto L14;
        if (this.reportFile.equals(r52.getReportFile()) == false) goto L14;
        return true;
    L14:
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.common.CrashlyticsReportWithSessionId
    public CrashlyticsReport getReport() {
        return this.report;
    }

    @Override // com.google.firebase.crashlytics.internal.common.CrashlyticsReportWithSessionId
    public File getReportFile() {
        return this.reportFile;
    }

    @Override // com.google.firebase.crashlytics.internal.common.CrashlyticsReportWithSessionId
    public String getSessionId() {
        return this.sessionId;
    }

    public int hashCode() {
        int r0 = (((this.report.hashCode() ^ 1000003) * 1000003) ^ this.sessionId.hashCode()) * 1000003;
        return this.reportFile.hashCode() ^ r0;
    }

    public String toString() {
        return "CrashlyticsReportWithSessionId{report=" + this.report + ", sessionId=" + this.sessionId + ", reportFile=" + this.reportFile + "}";
    }
}
