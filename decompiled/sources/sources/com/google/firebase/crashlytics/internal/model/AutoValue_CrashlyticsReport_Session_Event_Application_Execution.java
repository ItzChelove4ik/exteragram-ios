package com.google.firebase.crashlytics.internal.model;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.util.List;

/* loaded from: classes.dex */
final class AutoValue_CrashlyticsReport_Session_Event_Application_Execution extends CrashlyticsReport.Session.Event.Application.Execution {
    private final CrashlyticsReport.ApplicationExitInfo appExitInfo;
    private final List<CrashlyticsReport.Session.Event.Application.Execution.BinaryImage> binaries;
    private final CrashlyticsReport.Session.Event.Application.Execution.Exception exception;
    private final CrashlyticsReport.ProfilingManagerInfo profilingManagerInfo;
    private final CrashlyticsReport.Session.Event.Application.Execution.Signal signal;
    private final List<CrashlyticsReport.Session.Event.Application.Execution.Thread> threads;

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_Application_Execution$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    /* loaded from: classes5.dex */
    public static final class Builder extends CrashlyticsReport.Session.Event.Application.Execution.Builder {
        private CrashlyticsReport.ApplicationExitInfo appExitInfo;
        private List<CrashlyticsReport.Session.Event.Application.Execution.BinaryImage> binaries;
        private CrashlyticsReport.Session.Event.Application.Execution.Exception exception;
        private CrashlyticsReport.ProfilingManagerInfo profilingManagerInfo;
        private CrashlyticsReport.Session.Event.Application.Execution.Signal signal;
        private List<CrashlyticsReport.Session.Event.Application.Execution.Thread> threads;

        public Builder() {
        }

        private Builder(CrashlyticsReport.Session.Event.Application.Execution r2) {
            this.threads = r2.getThreads();
            this.exception = r2.getException();
            this.appExitInfo = r2.getAppExitInfo();
            this.profilingManagerInfo = r2.getProfilingManagerInfo();
            this.signal = r2.getSignal();
            this.binaries = r2.getBinaries();
        }

        public /* synthetic */ Builder(CrashlyticsReport.Session.Event.Application.Execution r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Builder
        public CrashlyticsReport.Session.Event.Application.Execution build() {
            CrashlyticsReport.Session.Event.Application.Execution.Signal r5 = this.signal;
            if (r5 == null) goto L9;
            List<CrashlyticsReport.Session.Event.Application.Execution.BinaryImage> r6 = this.binaries;
            if (r6 == null) goto L9;
            return new AutoValue_CrashlyticsReport_Session_Event_Application_Execution(this.threads, this.exception, this.appExitInfo, this.profilingManagerInfo, r5, r6, null);
        L9:
            StringBuilder r0 = new StringBuilder();
            if (this.signal != null) goto L13;
            r0.append(" signal");
        L13:
            if (this.binaries != null) goto L15;
            r0.append(" binaries");
        L15:
            DexMaker$$ExternalSyntheticBUOutline0.m("Missing required properties:", r0);
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.Builder setAppExitInfo(CrashlyticsReport.ApplicationExitInfo r1) {
            this.appExitInfo = r1;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.Builder setBinaries(List<CrashlyticsReport.Session.Event.Application.Execution.BinaryImage> r1) {
            if (r1 == null) goto L5;
            this.binaries = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null binaries");
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.Builder setException(CrashlyticsReport.Session.Event.Application.Execution.Exception r1) {
            this.exception = r1;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.Builder setProfilingManagerInfo(CrashlyticsReport.ProfilingManagerInfo r1) {
            this.profilingManagerInfo = r1;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.Builder setSignal(CrashlyticsReport.Session.Event.Application.Execution.Signal r1) {
            if (r1 == null) goto L5;
            this.signal = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null signal");
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.Builder setThreads(List<CrashlyticsReport.Session.Event.Application.Execution.Thread> r1) {
            this.threads = r1;
            return this;
        }
    }

    private AutoValue_CrashlyticsReport_Session_Event_Application_Execution(List<CrashlyticsReport.Session.Event.Application.Execution.Thread> r1, CrashlyticsReport.Session.Event.Application.Execution.Exception r2, CrashlyticsReport.ApplicationExitInfo r3, CrashlyticsReport.ProfilingManagerInfo r4, CrashlyticsReport.Session.Event.Application.Execution.Signal r5, List<CrashlyticsReport.Session.Event.Application.Execution.BinaryImage> r6) {
        this.threads = r1;
        this.exception = r2;
        this.appExitInfo = r3;
        this.profilingManagerInfo = r4;
        this.signal = r5;
        this.binaries = r6;
    }

    public /* synthetic */ AutoValue_CrashlyticsReport_Session_Event_Application_Execution(List r1, CrashlyticsReport.Session.Event.Application.Execution.Exception r2, CrashlyticsReport.ApplicationExitInfo r3, CrashlyticsReport.ProfilingManagerInfo r4, CrashlyticsReport.Session.Event.Application.Execution.Signal r5, List r6, AnonymousClass1 r7) {
        this(r1, r2, r3, r4, r5, r6);
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof CrashlyticsReport.Session.Event.Application.Execution) == false) goto L40;
        CrashlyticsReport.Session.Event.Application.Execution r52 = (CrashlyticsReport.Session.Event.Application.Execution) r5;
        List<CrashlyticsReport.Session.Event.Application.Execution.Thread> r1 = this.threads;
        if (r1 != null) goto L13;
        if (r52.getThreads() != null) goto L40;
    L14:
        CrashlyticsReport.Session.Event.Application.Execution.Exception r12 = this.exception;
        if (r12 != null) goto L20;
        if (r52.getException() != null) goto L40;
    L21:
        CrashlyticsReport.ApplicationExitInfo r13 = this.appExitInfo;
        if (r13 != null) goto L27;
        if (r52.getAppExitInfo() != null) goto L40;
    L28:
        CrashlyticsReport.ProfilingManagerInfo r14 = this.profilingManagerInfo;
        if (r14 != null) goto L34;
        if (r52.getProfilingManagerInfo() != null) goto L40;
    L36:
        if (this.signal.equals(r52.getSignal()) == false) goto L40;
        if (this.binaries.equals(r52.getBinaries()) == false) goto L40;
        return true;
    L34:
        if (r14.equals(r52.getProfilingManagerInfo()) == false) goto L40;
    L27:
        if (r13.equals(r52.getAppExitInfo()) == false) goto L40;
    L20:
        if (r12.equals(r52.getException()) == false) goto L40;
    L13:
        if (r1.equals(r52.getThreads()) == true) goto L14;
    L40:
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution
    public CrashlyticsReport.ApplicationExitInfo getAppExitInfo() {
        return this.appExitInfo;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution
    public List<CrashlyticsReport.Session.Event.Application.Execution.BinaryImage> getBinaries() {
        return this.binaries;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution
    public CrashlyticsReport.Session.Event.Application.Execution.Exception getException() {
        return this.exception;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution
    public CrashlyticsReport.ProfilingManagerInfo getProfilingManagerInfo() {
        return this.profilingManagerInfo;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution
    public CrashlyticsReport.Session.Event.Application.Execution.Signal getSignal() {
        return this.signal;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution
    public List<CrashlyticsReport.Session.Event.Application.Execution.Thread> getThreads() {
        return this.threads;
    }

    public int hashCode() {
        List<CrashlyticsReport.Session.Event.Application.Execution.Thread> r0 = this.threads;
        int r1 = 0;
        if (r0 != null) goto L5;
        int r02 = 0;
    L6:
        int r03 = (r02 ^ 1000003) * 1000003;
        CrashlyticsReport.Session.Event.Application.Execution.Exception r3 = this.exception;
        if (r3 != null) goto L9;
        int r32 = 0;
    L10:
        int r04 = (r03 ^ r32) * 1000003;
        CrashlyticsReport.ApplicationExitInfo r33 = this.appExitInfo;
        if (r33 != null) goto L13;
        int r34 = 0;
    L14:
        int r05 = (r04 ^ r34) * 1000003;
        CrashlyticsReport.ProfilingManagerInfo r35 = this.profilingManagerInfo;
        if (r35 == null) goto L19;
        r1 = r35.hashCode();
    L19:
        return this.binaries.hashCode() ^ ((((r05 ^ r1) * 1000003) ^ this.signal.hashCode()) * 1000003);
    L13:
        r34 = r33.hashCode();
        goto L14
    L9:
        r32 = r3.hashCode();
        goto L10
    L5:
        r02 = r0.hashCode();
        goto L6
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution
    public CrashlyticsReport.Session.Event.Application.Execution.Builder toBuilder() {
        return new Builder(this, null);
    }

    public String toString() {
        return "Execution{threads=" + this.threads + ", exception=" + this.exception + ", appExitInfo=" + this.appExitInfo + ", profilingManagerInfo=" + this.profilingManagerInfo + ", signal=" + this.signal + ", binaries=" + this.binaries + "}";
    }
}
