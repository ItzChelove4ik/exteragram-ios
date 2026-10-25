package com.google.firebase.crashlytics.internal.send;

import com.google.android.datatransport.TransportScheduleCallback;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.crashlytics.internal.common.CrashlyticsReportWithSessionId;

/* loaded from: classes5.dex */
public final /* synthetic */ class ReportQueue$$ExternalSyntheticLambda0 implements TransportScheduleCallback {
    public final /* synthetic */ ReportQueue f$0;
    public final /* synthetic */ TaskCompletionSource f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ CrashlyticsReportWithSessionId f$3;

    public /* synthetic */ ReportQueue$$ExternalSyntheticLambda0(ReportQueue r1, TaskCompletionSource r2, boolean r3, CrashlyticsReportWithSessionId r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // com.google.android.datatransport.TransportScheduleCallback
    public final void onSchedule(Exception r4) {
        ReportQueue.$r8$lambda$4lFm4kq1MZXZfcwIBR3zscs57No(this.f$0, this.f$1, this.f$2, this.f$3, r4);
    }
}
