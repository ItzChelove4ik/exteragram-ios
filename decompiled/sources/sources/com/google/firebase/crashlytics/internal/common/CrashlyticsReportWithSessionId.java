package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.io.File;

/* loaded from: classes5.dex */
public abstract class CrashlyticsReportWithSessionId {
    public CrashlyticsReportWithSessionId() {
    }

    public static CrashlyticsReportWithSessionId create(CrashlyticsReport r1, String r2, File r3) {
        return new AutoValue_CrashlyticsReportWithSessionId(r1, r2, r3);
    }

    public abstract CrashlyticsReport getReport();

    public abstract File getReportFile();

    public abstract String getSessionId();
}
