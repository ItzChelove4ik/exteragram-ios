package com.google.firebase.crashlytics.internal.persistence;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.util.function.Function;

/* loaded from: classes5.dex */
public final /* synthetic */ class CrashlyticsReportPersistence$$ExternalSyntheticLambda4 implements Function {
    public final /* synthetic */ CrashlyticsReport.Session.Event f$0;

    public /* synthetic */ CrashlyticsReportPersistence$$ExternalSyntheticLambda4(CrashlyticsReport.Session.Event r1) {
        this.f$0 = r1;
    }

    @Override // java.util.function.Function
    public final Object apply(Object r1) {
        return CrashlyticsReportPersistence.$r8$lambda$8iUI8wc_FwuExXlMT9At9xdFNyY(this.f$0, (CrashlyticsReport.ProfilingManagerInfo) r1);
    }
}
