package com.google.firebase.crashlytics.internal.model;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

/* loaded from: classes.dex */
final class AutoValue_CrashlyticsReport_Session_Event_Application_ProcessDetails extends CrashlyticsReport.Session.Event.Application.ProcessDetails {
    private final boolean defaultProcess;
    private final int importance;
    private final int pid;
    private final String processName;

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_Application_ProcessDetails$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder extends CrashlyticsReport.Session.Event.Application.ProcessDetails.Builder {
        private boolean defaultProcess;
        private int importance;
        private int pid;
        private String processName;
        private byte set$0;

        public Builder() {
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.ProcessDetails.Builder
        public CrashlyticsReport.Session.Event.Application.ProcessDetails build() {
            if (this.set$0 != 7) goto L9;
            String r3 = this.processName;
            if (r3 == null) goto L9;
            return new AutoValue_CrashlyticsReport_Session_Event_Application_ProcessDetails(r3, this.pid, this.importance, this.defaultProcess, null);
        L9:
            StringBuilder r0 = new StringBuilder();
            if (this.processName != null) goto L13;
            r0.append(" processName");
        L13:
            if ((this.set$0 & 1) != 0) goto L16;
            r0.append(" pid");
        L16:
            if ((this.set$0 & 2) != 0) goto L19;
            r0.append(" importance");
        L19:
            if ((this.set$0 & 4) != 0) goto L21;
            r0.append(" defaultProcess");
        L21:
            DexMaker$$ExternalSyntheticBUOutline0.m("Missing required properties:", r0);
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.ProcessDetails.Builder
        public CrashlyticsReport.Session.Event.Application.ProcessDetails.Builder setDefaultProcess(boolean r1) {
            this.defaultProcess = r1;
            this.set$0 = (byte) (this.set$0 | 4);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.ProcessDetails.Builder
        public CrashlyticsReport.Session.Event.Application.ProcessDetails.Builder setImportance(int r1) {
            this.importance = r1;
            this.set$0 = (byte) (this.set$0 | 2);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.ProcessDetails.Builder
        public CrashlyticsReport.Session.Event.Application.ProcessDetails.Builder setPid(int r1) {
            this.pid = r1;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.ProcessDetails.Builder
        public CrashlyticsReport.Session.Event.Application.ProcessDetails.Builder setProcessName(String r1) {
            if (r1 == null) goto L5;
            this.processName = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null processName");
            return null;
        }
    }

    private AutoValue_CrashlyticsReport_Session_Event_Application_ProcessDetails(String r1, int r2, int r3, boolean r4) {
        this.processName = r1;
        this.pid = r2;
        this.importance = r3;
        this.defaultProcess = r4;
    }

    public /* synthetic */ AutoValue_CrashlyticsReport_Session_Event_Application_ProcessDetails(String r1, int r2, int r3, boolean r4, AnonymousClass1 r5) {
        this(r1, r2, r3, r4);
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof CrashlyticsReport.Session.Event.Application.ProcessDetails) == false) goto L16;
        CrashlyticsReport.Session.Event.Application.ProcessDetails r52 = (CrashlyticsReport.Session.Event.Application.ProcessDetails) r5;
        if (this.processName.equals(r52.getProcessName()) == false) goto L16;
        if (this.pid != r52.getPid()) goto L16;
        if (this.importance != r52.getImportance()) goto L16;
        if (this.defaultProcess != r52.isDefaultProcess()) goto L16;
        return true;
    L16:
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.ProcessDetails
    public int getImportance() {
        return this.importance;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.ProcessDetails
    public int getPid() {
        return this.pid;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.ProcessDetails
    public String getProcessName() {
        return this.processName;
    }

    public int hashCode() {
        int r0 = (((((this.processName.hashCode() ^ 1000003) * 1000003) ^ this.pid) * 1000003) ^ this.importance) * 1000003;
        if (this.defaultProcess == false) goto L5;
        int r3 = 1231;
    L7:
        return r3 ^ r0;
    L5:
        r3 = 1237;
        goto L7
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.ProcessDetails
    public boolean isDefaultProcess() {
        return this.defaultProcess;
    }

    public String toString() {
        return "ProcessDetails{processName=" + this.processName + ", pid=" + this.pid + ", importance=" + this.importance + ", defaultProcess=" + this.defaultProcess + "}";
    }
}
