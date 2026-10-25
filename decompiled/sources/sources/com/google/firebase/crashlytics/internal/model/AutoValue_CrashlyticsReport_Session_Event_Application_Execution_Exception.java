package com.google.firebase.crashlytics.internal.model;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.util.List;

/* loaded from: classes.dex */
final class AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Exception extends CrashlyticsReport.Session.Event.Application.Execution.Exception {
    private final CrashlyticsReport.Session.Event.Application.Execution.Exception causedBy;
    private final List<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> frames;
    private final int overflowCount;
    private final String reason;
    private final String type;

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Exception$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    /* loaded from: classes5.dex */
    public static final class Builder extends CrashlyticsReport.Session.Event.Application.Execution.Exception.Builder {
        private CrashlyticsReport.Session.Event.Application.Execution.Exception causedBy;
        private List<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> frames;
        private int overflowCount;
        private String reason;
        private byte set$0;
        private String type;

        public Builder() {
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Exception.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.Exception build() {
            if (this.set$0 != 1) goto L11;
            String r3 = this.type;
            if (r3 == null) goto L11;
            List<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> r5 = this.frames;
            if (r5 == null) goto L11;
            return new AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Exception(r3, this.reason, r5, this.causedBy, this.overflowCount, null);
        L11:
            StringBuilder r0 = new StringBuilder();
            if (this.type != null) goto L15;
            r0.append(" type");
        L15:
            if (this.frames != null) goto L18;
            r0.append(" frames");
        L18:
            if ((this.set$0 & 1) != 0) goto L20;
            r0.append(" overflowCount");
        L20:
            DexMaker$$ExternalSyntheticBUOutline0.m("Missing required properties:", r0);
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Exception.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.Exception.Builder setCausedBy(CrashlyticsReport.Session.Event.Application.Execution.Exception r1) {
            this.causedBy = r1;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Exception.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.Exception.Builder setFrames(List<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> r1) {
            if (r1 == null) goto L5;
            this.frames = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null frames");
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Exception.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.Exception.Builder setOverflowCount(int r1) {
            this.overflowCount = r1;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Exception.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.Exception.Builder setReason(String r1) {
            this.reason = r1;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Exception.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.Exception.Builder setType(String r1) {
            if (r1 == null) goto L5;
            this.type = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null type");
            return null;
        }
    }

    private AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Exception(String r1, String r2, List<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> r3, CrashlyticsReport.Session.Event.Application.Execution.Exception r4, int r5) {
        this.type = r1;
        this.reason = r2;
        this.frames = r3;
        this.causedBy = r4;
        this.overflowCount = r5;
    }

    public /* synthetic */ AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Exception(String r1, String r2, List r3, CrashlyticsReport.Session.Event.Application.Execution.Exception r4, int r5, AnonymousClass1 r6) {
        this(r1, r2, r3, r4, r5);
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof CrashlyticsReport.Session.Event.Application.Execution.Exception) == false) goto L28;
        CrashlyticsReport.Session.Event.Application.Execution.Exception r52 = (CrashlyticsReport.Session.Event.Application.Execution.Exception) r5;
        if (this.type.equals(r52.getType()) == false) goto L28;
        String r1 = this.reason;
        if (r1 != null) goto L15;
        if (r52.getReason() != null) goto L28;
    L17:
        if (this.frames.equals(r52.getFrames()) == false) goto L28;
        CrashlyticsReport.Session.Event.Application.Execution.Exception r12 = this.causedBy;
        if (r12 != null) goto L24;
        if (r52.getCausedBy() != null) goto L28;
    L26:
        if (this.overflowCount != r52.getOverflowCount()) goto L28;
        return true;
    L24:
        if (r12.equals(r52.getCausedBy()) == false) goto L28;
    L15:
        if (r1.equals(r52.getReason()) == true) goto L17;
    L28:
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Exception
    public CrashlyticsReport.Session.Event.Application.Execution.Exception getCausedBy() {
        return this.causedBy;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Exception
    public List<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> getFrames() {
        return this.frames;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Exception
    public int getOverflowCount() {
        return this.overflowCount;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Exception
    public String getReason() {
        return this.reason;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Exception
    public String getType() {
        return this.type;
    }

    public int hashCode() {
        int r0 = (this.type.hashCode() ^ 1000003) * 1000003;
        String r2 = this.reason;
        int r3 = 0;
        if (r2 != null) goto L5;
        int r22 = 0;
    L6:
        int r02 = (((r0 ^ r22) * 1000003) ^ this.frames.hashCode()) * 1000003;
        CrashlyticsReport.Session.Event.Application.Execution.Exception r23 = this.causedBy;
        if (r23 == null) goto L11;
        r3 = r23.hashCode();
    L11:
        return this.overflowCount ^ ((r02 ^ r3) * 1000003);
    L5:
        r22 = r2.hashCode();
        goto L6
    }

    public String toString() {
        return "Exception{type=" + this.type + ", reason=" + this.reason + ", frames=" + this.frames + ", causedBy=" + this.causedBy + ", overflowCount=" + this.overflowCount + "}";
    }
}
