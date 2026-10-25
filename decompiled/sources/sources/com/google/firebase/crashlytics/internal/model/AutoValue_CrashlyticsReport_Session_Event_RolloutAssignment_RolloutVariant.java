package com.google.firebase.crashlytics.internal.model;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

/* loaded from: classes.dex */
final class AutoValue_CrashlyticsReport_Session_Event_RolloutAssignment_RolloutVariant extends CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant {
    private final String rolloutId;
    private final String variantId;

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_RolloutAssignment_RolloutVariant$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    /* loaded from: classes5.dex */
    public static final class Builder extends CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant.Builder {
        private String rolloutId;
        private String variantId;

        public Builder() {
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant.Builder
        public CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant build() {
            String r0 = this.rolloutId;
            if (r0 == null) goto L9;
            String r1 = this.variantId;
            if (r1 == null) goto L9;
            return new AutoValue_CrashlyticsReport_Session_Event_RolloutAssignment_RolloutVariant(r0, r1, null);
        L9:
            StringBuilder r02 = new StringBuilder();
            if (this.rolloutId != null) goto L13;
            r02.append(" rolloutId");
        L13:
            if (this.variantId != null) goto L15;
            r02.append(" variantId");
        L15:
            DexMaker$$ExternalSyntheticBUOutline0.m("Missing required properties:", r02);
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant.Builder
        public CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant.Builder setRolloutId(String r1) {
            if (r1 == null) goto L5;
            this.rolloutId = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null rolloutId");
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant.Builder
        public CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant.Builder setVariantId(String r1) {
            if (r1 == null) goto L5;
            this.variantId = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null variantId");
            return null;
        }
    }

    private AutoValue_CrashlyticsReport_Session_Event_RolloutAssignment_RolloutVariant(String r1, String r2) {
        this.rolloutId = r1;
        this.variantId = r2;
    }

    public /* synthetic */ AutoValue_CrashlyticsReport_Session_Event_RolloutAssignment_RolloutVariant(String r1, String r2, AnonymousClass1 r3) {
        this(r1, r2);
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant) == false) goto L12;
        CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant r52 = (CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant) r5;
        if (this.rolloutId.equals(r52.getRolloutId()) == false) goto L12;
        if (this.variantId.equals(r52.getVariantId()) == false) goto L12;
        return true;
    L12:
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant
    public String getRolloutId() {
        return this.rolloutId;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant
    public String getVariantId() {
        return this.variantId;
    }

    public int hashCode() {
        int r0 = (this.rolloutId.hashCode() ^ 1000003) * 1000003;
        return this.variantId.hashCode() ^ r0;
    }

    public String toString() {
        return "RolloutVariant{rolloutId=" + this.rolloutId + ", variantId=" + this.variantId + "}";
    }
}
