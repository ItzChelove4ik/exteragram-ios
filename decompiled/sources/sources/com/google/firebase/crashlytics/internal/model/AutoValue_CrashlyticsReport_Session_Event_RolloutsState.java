package com.google.firebase.crashlytics.internal.model;

import c.f$$ExternalSyntheticBUOutline2;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.util.List;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
final class AutoValue_CrashlyticsReport_Session_Event_RolloutsState extends CrashlyticsReport.Session.Event.RolloutsState {
    private final List<CrashlyticsReport.Session.Event.RolloutAssignment> rolloutAssignments;

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_RolloutsState$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    /* loaded from: classes5.dex */
    public static final class Builder extends CrashlyticsReport.Session.Event.RolloutsState.Builder {
        private List<CrashlyticsReport.Session.Event.RolloutAssignment> rolloutAssignments;

        public Builder() {
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.RolloutsState.Builder
        public CrashlyticsReport.Session.Event.RolloutsState build() {
            List<CrashlyticsReport.Session.Event.RolloutAssignment> r2 = this.rolloutAssignments;
            if (r2 != null) goto L5;
            Segment$$ExternalSyntheticBUOutline1.m("Missing required properties: rolloutAssignments");
            return null;
        L5:
            return new AutoValue_CrashlyticsReport_Session_Event_RolloutsState(r2, null);
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.RolloutsState.Builder
        public CrashlyticsReport.Session.Event.RolloutsState.Builder setRolloutAssignments(List<CrashlyticsReport.Session.Event.RolloutAssignment> r1) {
            if (r1 == null) goto L5;
            this.rolloutAssignments = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null rolloutAssignments");
            return null;
        }
    }

    private AutoValue_CrashlyticsReport_Session_Event_RolloutsState(List<CrashlyticsReport.Session.Event.RolloutAssignment> r1) {
        this.rolloutAssignments = r1;
    }

    public /* synthetic */ AutoValue_CrashlyticsReport_Session_Event_RolloutsState(List r1, AnonymousClass1 r2) {
        this(r1);
    }

    public boolean equals(Object r2) {
        if (r2 != this) goto L6;
        return true;
    L6:
        if ((r2 instanceof CrashlyticsReport.Session.Event.RolloutsState) == true) goto L8;
        return false;
    L8:
        return this.rolloutAssignments.equals(((CrashlyticsReport.Session.Event.RolloutsState) r2).getRolloutAssignments());
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.RolloutsState
    public List<CrashlyticsReport.Session.Event.RolloutAssignment> getRolloutAssignments() {
        return this.rolloutAssignments;
    }

    public int hashCode() {
        return this.rolloutAssignments.hashCode() ^ 1000003;
    }

    public String toString() {
        return "RolloutsState{rolloutAssignments=" + this.rolloutAssignments + "}";
    }
}
