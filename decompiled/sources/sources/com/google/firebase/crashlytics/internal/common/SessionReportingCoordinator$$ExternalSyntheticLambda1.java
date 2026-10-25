package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.metadata.EventMetadata;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

/* loaded from: classes5.dex */
public final /* synthetic */ class SessionReportingCoordinator$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ SessionReportingCoordinator f$0;
    public final /* synthetic */ CrashlyticsReport.Session.Event f$1;
    public final /* synthetic */ EventMetadata f$2;
    public final /* synthetic */ boolean f$3;

    public /* synthetic */ SessionReportingCoordinator$$ExternalSyntheticLambda1(SessionReportingCoordinator r1, CrashlyticsReport.Session.Event r2, EventMetadata r3, boolean r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SessionReportingCoordinator.$r8$lambda$GDz_lcqQNqo8aFPcI70zJauOLvQ(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
