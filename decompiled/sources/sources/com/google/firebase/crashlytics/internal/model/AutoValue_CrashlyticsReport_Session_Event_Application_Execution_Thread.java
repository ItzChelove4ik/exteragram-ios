package com.google.firebase.crashlytics.internal.model;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.util.List;

/* loaded from: classes.dex */
final class AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Thread extends CrashlyticsReport.Session.Event.Application.Execution.Thread {
    private final List<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> frames;
    private final int importance;
    private final String name;

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Thread$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    /* loaded from: classes5.dex */
    public static final class Builder extends CrashlyticsReport.Session.Event.Application.Execution.Thread.Builder {
        private List<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> frames;
        private int importance;
        private String name;
        private byte set$0;

        public Builder() {
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.Thread build() {
            if (this.set$0 != 1) goto L11;
            String r0 = this.name;
            if (r0 == null) goto L11;
            List<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> r2 = this.frames;
            if (r2 == null) goto L11;
            return new AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Thread(r0, this.importance, r2, null);
        L11:
            StringBuilder r02 = new StringBuilder();
            if (this.name != null) goto L15;
            r02.append(" name");
        L15:
            if ((1 & this.set$0) != 0) goto L18;
            r02.append(" importance");
        L18:
            if (this.frames != null) goto L20;
            r02.append(" frames");
        L20:
            DexMaker$$ExternalSyntheticBUOutline0.m("Missing required properties:", r02);
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.Thread.Builder setFrames(List<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> r1) {
            if (r1 == null) goto L5;
            this.frames = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null frames");
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.Thread.Builder setImportance(int r1) {
            this.importance = r1;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.Thread.Builder setName(String r1) {
            if (r1 == null) goto L5;
            this.name = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null name");
            return null;
        }
    }

    private AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Thread(String r1, int r2, List<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> r3) {
        this.name = r1;
        this.importance = r2;
        this.frames = r3;
    }

    public /* synthetic */ AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Thread(String r1, int r2, List r3, AnonymousClass1 r4) {
        this(r1, r2, r3);
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof CrashlyticsReport.Session.Event.Application.Execution.Thread) == false) goto L14;
        CrashlyticsReport.Session.Event.Application.Execution.Thread r52 = (CrashlyticsReport.Session.Event.Application.Execution.Thread) r5;
        if (this.name.equals(r52.getName()) == false) goto L14;
        if (this.importance != r52.getImportance()) goto L14;
        if (this.frames.equals(r52.getFrames()) == false) goto L14;
        return true;
    L14:
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread
    public List<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> getFrames() {
        return this.frames;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread
    public int getImportance() {
        return this.importance;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Thread
    public String getName() {
        return this.name;
    }

    public int hashCode() {
        int r0 = (((this.name.hashCode() ^ 1000003) * 1000003) ^ this.importance) * 1000003;
        return this.frames.hashCode() ^ r0;
    }

    public String toString() {
        return "Thread{name=" + this.name + ", importance=" + this.importance + ", frames=" + this.frames + "}";
    }
}
