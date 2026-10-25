package com.google.firebase.crashlytics.internal.model;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.util.List;

/* loaded from: classes.dex */
final class AutoValue_CrashlyticsReport_Session_Event_Application extends CrashlyticsReport.Session.Event.Application {
    private final List<CrashlyticsReport.Session.Event.Application.ProcessDetails> appProcessDetails;
    private final Boolean background;
    private final CrashlyticsReport.Session.Event.Application.ProcessDetails currentProcessDetails;
    private final List<CrashlyticsReport.CustomAttribute> customAttributes;
    private final CrashlyticsReport.Session.Event.Application.Execution execution;
    private final List<CrashlyticsReport.CustomAttribute> internalKeys;
    private final int uiOrientation;

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_Application$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    /* loaded from: classes5.dex */
    public static final class Builder extends CrashlyticsReport.Session.Event.Application.Builder {
        private List<CrashlyticsReport.Session.Event.Application.ProcessDetails> appProcessDetails;
        private Boolean background;
        private CrashlyticsReport.Session.Event.Application.ProcessDetails currentProcessDetails;
        private List<CrashlyticsReport.CustomAttribute> customAttributes;
        private CrashlyticsReport.Session.Event.Application.Execution execution;
        private List<CrashlyticsReport.CustomAttribute> internalKeys;
        private byte set$0;
        private int uiOrientation;

        public Builder() {
        }

        private Builder(CrashlyticsReport.Session.Event.Application r2) {
            this.execution = r2.getExecution();
            this.customAttributes = r2.getCustomAttributes();
            this.internalKeys = r2.getInternalKeys();
            this.background = r2.getBackground();
            this.currentProcessDetails = r2.getCurrentProcessDetails();
            this.appProcessDetails = r2.getAppProcessDetails();
            this.uiOrientation = r2.getUiOrientation();
            this.set$0 = 1;
        }

        public /* synthetic */ Builder(CrashlyticsReport.Session.Event.Application r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Builder
        public CrashlyticsReport.Session.Event.Application build() {
            if (this.set$0 != 1) goto L9;
            CrashlyticsReport.Session.Event.Application.Execution r3 = this.execution;
            if (r3 == null) goto L9;
            return new AutoValue_CrashlyticsReport_Session_Event_Application(r3, this.customAttributes, this.internalKeys, this.background, this.currentProcessDetails, this.appProcessDetails, this.uiOrientation, null);
        L9:
            StringBuilder r0 = new StringBuilder();
            if (this.execution != null) goto L13;
            r0.append(" execution");
        L13:
            if ((this.set$0 & 1) != 0) goto L15;
            r0.append(" uiOrientation");
        L15:
            DexMaker$$ExternalSyntheticBUOutline0.m("Missing required properties:", r0);
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Builder
        public CrashlyticsReport.Session.Event.Application.Builder setAppProcessDetails(List<CrashlyticsReport.Session.Event.Application.ProcessDetails> r1) {
            this.appProcessDetails = r1;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Builder
        public CrashlyticsReport.Session.Event.Application.Builder setBackground(Boolean r1) {
            this.background = r1;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Builder
        public CrashlyticsReport.Session.Event.Application.Builder setCurrentProcessDetails(CrashlyticsReport.Session.Event.Application.ProcessDetails r1) {
            this.currentProcessDetails = r1;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Builder
        public CrashlyticsReport.Session.Event.Application.Builder setCustomAttributes(List<CrashlyticsReport.CustomAttribute> r1) {
            this.customAttributes = r1;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Builder
        public CrashlyticsReport.Session.Event.Application.Builder setExecution(CrashlyticsReport.Session.Event.Application.Execution r1) {
            if (r1 == null) goto L5;
            this.execution = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null execution");
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Builder
        public CrashlyticsReport.Session.Event.Application.Builder setInternalKeys(List<CrashlyticsReport.CustomAttribute> r1) {
            this.internalKeys = r1;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Builder
        public CrashlyticsReport.Session.Event.Application.Builder setUiOrientation(int r1) {
            this.uiOrientation = r1;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }
    }

    private AutoValue_CrashlyticsReport_Session_Event_Application(CrashlyticsReport.Session.Event.Application.Execution r1, List<CrashlyticsReport.CustomAttribute> r2, List<CrashlyticsReport.CustomAttribute> r3, Boolean r4, CrashlyticsReport.Session.Event.Application.ProcessDetails r5, List<CrashlyticsReport.Session.Event.Application.ProcessDetails> r6, int r7) {
        this.execution = r1;
        this.customAttributes = r2;
        this.internalKeys = r3;
        this.background = r4;
        this.currentProcessDetails = r5;
        this.appProcessDetails = r6;
        this.uiOrientation = r7;
    }

    public /* synthetic */ AutoValue_CrashlyticsReport_Session_Event_Application(CrashlyticsReport.Session.Event.Application.Execution r1, List r2, List r3, Boolean r4, CrashlyticsReport.Session.Event.Application.ProcessDetails r5, List r6, int r7, AnonymousClass1 r8) {
        this(r1, r2, r3, r4, r5, r6, r7);
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof CrashlyticsReport.Session.Event.Application) == false) goto L47;
        CrashlyticsReport.Session.Event.Application r52 = (CrashlyticsReport.Session.Event.Application) r5;
        if (this.execution.equals(r52.getExecution()) == false) goto L47;
        List<CrashlyticsReport.CustomAttribute> r1 = this.customAttributes;
        if (r1 != null) goto L15;
        if (r52.getCustomAttributes() != null) goto L47;
    L16:
        List<CrashlyticsReport.CustomAttribute> r12 = this.internalKeys;
        if (r12 != null) goto L22;
        if (r52.getInternalKeys() != null) goto L47;
    L23:
        Boolean r13 = this.background;
        if (r13 != null) goto L29;
        if (r52.getBackground() != null) goto L47;
    L30:
        CrashlyticsReport.Session.Event.Application.ProcessDetails r14 = this.currentProcessDetails;
        if (r14 != null) goto L36;
        if (r52.getCurrentProcessDetails() != null) goto L47;
    L37:
        List<CrashlyticsReport.Session.Event.Application.ProcessDetails> r15 = this.appProcessDetails;
        if (r15 != null) goto L43;
        if (r52.getAppProcessDetails() != null) goto L47;
    L45:
        if (this.uiOrientation != r52.getUiOrientation()) goto L47;
        return true;
    L43:
        if (r15.equals(r52.getAppProcessDetails()) == false) goto L47;
    L36:
        if (r14.equals(r52.getCurrentProcessDetails()) == false) goto L47;
    L29:
        if (r13.equals(r52.getBackground()) == false) goto L47;
    L22:
        if (r12.equals(r52.getInternalKeys()) == false) goto L47;
    L15:
        if (r1.equals(r52.getCustomAttributes()) == true) goto L16;
    L47:
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application
    public List<CrashlyticsReport.Session.Event.Application.ProcessDetails> getAppProcessDetails() {
        return this.appProcessDetails;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application
    public Boolean getBackground() {
        return this.background;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application
    public CrashlyticsReport.Session.Event.Application.ProcessDetails getCurrentProcessDetails() {
        return this.currentProcessDetails;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application
    public List<CrashlyticsReport.CustomAttribute> getCustomAttributes() {
        return this.customAttributes;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application
    public CrashlyticsReport.Session.Event.Application.Execution getExecution() {
        return this.execution;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application
    public List<CrashlyticsReport.CustomAttribute> getInternalKeys() {
        return this.internalKeys;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application
    public int getUiOrientation() {
        return this.uiOrientation;
    }

    public int hashCode() {
        int r0 = (this.execution.hashCode() ^ 1000003) * 1000003;
        List<CrashlyticsReport.CustomAttribute> r2 = this.customAttributes;
        int r3 = 0;
        if (r2 != null) goto L5;
        int r22 = 0;
    L6:
        int r02 = (r0 ^ r22) * 1000003;
        List<CrashlyticsReport.CustomAttribute> r23 = this.internalKeys;
        if (r23 != null) goto L9;
        int r24 = 0;
    L10:
        int r03 = (r02 ^ r24) * 1000003;
        Boolean r25 = this.background;
        if (r25 != null) goto L13;
        int r26 = 0;
    L14:
        int r04 = (r03 ^ r26) * 1000003;
        CrashlyticsReport.Session.Event.Application.ProcessDetails r27 = this.currentProcessDetails;
        if (r27 != null) goto L17;
        int r28 = 0;
    L18:
        int r05 = (r04 ^ r28) * 1000003;
        List<CrashlyticsReport.Session.Event.Application.ProcessDetails> r29 = this.appProcessDetails;
        if (r29 == null) goto L23;
        r3 = r29.hashCode();
    L23:
        return this.uiOrientation ^ ((r05 ^ r3) * 1000003);
    L17:
        r28 = r27.hashCode();
        goto L18
    L13:
        r26 = r25.hashCode();
        goto L14
    L9:
        r24 = r23.hashCode();
        goto L10
    L5:
        r22 = r2.hashCode();
        goto L6
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application
    public CrashlyticsReport.Session.Event.Application.Builder toBuilder() {
        return new Builder(this, null);
    }

    public String toString() {
        return "Application{execution=" + this.execution + ", customAttributes=" + this.customAttributes + ", internalKeys=" + this.internalKeys + ", background=" + this.background + ", currentProcessDetails=" + this.currentProcessDetails + ", appProcessDetails=" + this.appProcessDetails + ", uiOrientation=" + this.uiOrientation + "}";
    }
}
