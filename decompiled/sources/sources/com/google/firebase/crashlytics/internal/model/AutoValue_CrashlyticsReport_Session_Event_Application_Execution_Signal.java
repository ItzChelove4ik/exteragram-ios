package com.google.firebase.crashlytics.internal.model;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

/* loaded from: classes.dex */
final class AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Signal extends CrashlyticsReport.Session.Event.Application.Execution.Signal {
    private final long address;
    private final String code;
    private final String name;

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Signal$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    /* loaded from: classes5.dex */
    public static final class Builder extends CrashlyticsReport.Session.Event.Application.Execution.Signal.Builder {
        private long address;
        private String code;
        private String name;
        private byte set$0;

        public Builder() {
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Signal.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.Signal build() {
            if (this.set$0 != 1) goto L11;
            String r3 = this.name;
            if (r3 == null) goto L11;
            String r4 = this.code;
            if (r4 == null) goto L11;
            return new AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Signal(r3, r4, this.address, null);
        L11:
            StringBuilder r0 = new StringBuilder();
            if (this.name != null) goto L15;
            r0.append(" name");
        L15:
            if (this.code != null) goto L18;
            r0.append(" code");
        L18:
            if ((this.set$0 & 1) != 0) goto L20;
            r0.append(" address");
        L20:
            DexMaker$$ExternalSyntheticBUOutline0.m("Missing required properties:", r0);
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Signal.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.Signal.Builder setAddress(long r1) {
            this.address = r1;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Signal.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.Signal.Builder setCode(String r1) {
            if (r1 == null) goto L5;
            this.code = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null code");
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Signal.Builder
        public CrashlyticsReport.Session.Event.Application.Execution.Signal.Builder setName(String r1) {
            if (r1 == null) goto L5;
            this.name = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null name");
            return null;
        }
    }

    private AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Signal(String r1, String r2, long r3) {
        this.name = r1;
        this.code = r2;
        this.address = r3;
    }

    public /* synthetic */ AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Signal(String r1, String r2, long r3, AnonymousClass1 r5) {
        this(r1, r2, r3);
    }

    public boolean equals(Object r6) {
        if (r6 != this) goto L6;
        return true;
    L6:
        if ((r6 instanceof CrashlyticsReport.Session.Event.Application.Execution.Signal) == false) goto L14;
        CrashlyticsReport.Session.Event.Application.Execution.Signal r62 = (CrashlyticsReport.Session.Event.Application.Execution.Signal) r6;
        if (this.name.equals(r62.getName()) == false) goto L14;
        if (this.code.equals(r62.getCode()) == false) goto L14;
        if (this.address != r62.getAddress()) goto L14;
        return true;
    L14:
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Signal
    public long getAddress() {
        return this.address;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Signal
    public String getCode() {
        return this.code;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Execution.Signal
    public String getName() {
        return this.name;
    }

    public int hashCode() {
        int r0 = (((this.name.hashCode() ^ 1000003) * 1000003) ^ this.code.hashCode()) * 1000003;
        long r1 = this.address;
        return ((int) (r1 ^ (r1 >>> 32))) ^ r0;
    }

    public String toString() {
        return "Signal{name=" + this.name + ", code=" + this.code + ", address=" + this.address + "}";
    }
}
