package com.google.firebase.crashlytics.internal.model;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

/* loaded from: classes.dex */
final class AutoValue_CrashlyticsReport_Session_Event_RolloutAssignment extends CrashlyticsReport.Session.Event.RolloutAssignment {
    private final String parameterKey;
    private final String parameterValue;
    private final CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant rolloutVariant;
    private final long templateVersion;

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_RolloutAssignment$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    /* loaded from: classes5.dex */
    public static final class Builder extends CrashlyticsReport.Session.Event.RolloutAssignment.Builder {
        private String parameterKey;
        private String parameterValue;
        private CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant rolloutVariant;
        private byte set$0;
        private long templateVersion;

        public Builder() {
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.RolloutAssignment.Builder
        public CrashlyticsReport.Session.Event.RolloutAssignment build() {
            if (this.set$0 != 1) goto L13;
            CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant r3 = this.rolloutVariant;
            if (r3 == null) goto L13;
            String r4 = this.parameterKey;
            if (r4 == null) goto L13;
            String r5 = this.parameterValue;
            if (r5 == null) goto L13;
            return new AutoValue_CrashlyticsReport_Session_Event_RolloutAssignment(r3, r4, r5, this.templateVersion, null);
        L13:
            StringBuilder r0 = new StringBuilder();
            if (this.rolloutVariant != null) goto L17;
            r0.append(" rolloutVariant");
        L17:
            if (this.parameterKey != null) goto L20;
            r0.append(" parameterKey");
        L20:
            if (this.parameterValue != null) goto L23;
            r0.append(" parameterValue");
        L23:
            if ((this.set$0 & 1) != 0) goto L25;
            r0.append(" templateVersion");
        L25:
            DexMaker$$ExternalSyntheticBUOutline0.m("Missing required properties:", r0);
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.RolloutAssignment.Builder
        public CrashlyticsReport.Session.Event.RolloutAssignment.Builder setParameterKey(String r1) {
            if (r1 == null) goto L5;
            this.parameterKey = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null parameterKey");
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.RolloutAssignment.Builder
        public CrashlyticsReport.Session.Event.RolloutAssignment.Builder setParameterValue(String r1) {
            if (r1 == null) goto L5;
            this.parameterValue = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null parameterValue");
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.RolloutAssignment.Builder
        public CrashlyticsReport.Session.Event.RolloutAssignment.Builder setRolloutVariant(CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant r1) {
            if (r1 == null) goto L5;
            this.rolloutVariant = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null rolloutVariant");
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.RolloutAssignment.Builder
        public CrashlyticsReport.Session.Event.RolloutAssignment.Builder setTemplateVersion(long r1) {
            this.templateVersion = r1;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }
    }

    private AutoValue_CrashlyticsReport_Session_Event_RolloutAssignment(CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant r1, String r2, String r3, long r4) {
        this.rolloutVariant = r1;
        this.parameterKey = r2;
        this.parameterValue = r3;
        this.templateVersion = r4;
    }

    public /* synthetic */ AutoValue_CrashlyticsReport_Session_Event_RolloutAssignment(CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant r1, String r2, String r3, long r4, AnonymousClass1 r6) {
        this(r1, r2, r3, r4);
    }

    public boolean equals(Object r6) {
        if (r6 != this) goto L6;
        return true;
    L6:
        if ((r6 instanceof CrashlyticsReport.Session.Event.RolloutAssignment) == false) goto L16;
        CrashlyticsReport.Session.Event.RolloutAssignment r62 = (CrashlyticsReport.Session.Event.RolloutAssignment) r6;
        if (this.rolloutVariant.equals(r62.getRolloutVariant()) == false) goto L16;
        if (this.parameterKey.equals(r62.getParameterKey()) == false) goto L16;
        if (this.parameterValue.equals(r62.getParameterValue()) == false) goto L16;
        if (this.templateVersion != r62.getTemplateVersion()) goto L16;
        return true;
    L16:
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.RolloutAssignment
    public String getParameterKey() {
        return this.parameterKey;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.RolloutAssignment
    public String getParameterValue() {
        return this.parameterValue;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.RolloutAssignment
    public CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant getRolloutVariant() {
        return this.rolloutVariant;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.RolloutAssignment
    public long getTemplateVersion() {
        return this.templateVersion;
    }

    public int hashCode() {
        int r0 = (((((this.rolloutVariant.hashCode() ^ 1000003) * 1000003) ^ this.parameterKey.hashCode()) * 1000003) ^ this.parameterValue.hashCode()) * 1000003;
        long r1 = this.templateVersion;
        return ((int) (r1 ^ (r1 >>> 32))) ^ r0;
    }

    public String toString() {
        return "RolloutAssignment{rolloutVariant=" + this.rolloutVariant + ", parameterKey=" + this.parameterKey + ", parameterValue=" + this.parameterValue + ", templateVersion=" + this.templateVersion + "}";
    }
}
