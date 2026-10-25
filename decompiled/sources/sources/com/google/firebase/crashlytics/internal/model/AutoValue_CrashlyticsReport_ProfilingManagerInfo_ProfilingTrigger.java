package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
final class AutoValue_CrashlyticsReport_ProfilingManagerInfo_ProfilingTrigger extends CrashlyticsReport.ProfilingManagerInfo.ProfilingTrigger {
    private final int trigger;

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_ProfilingManagerInfo_ProfilingTrigger$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder extends CrashlyticsReport.ProfilingManagerInfo.ProfilingTrigger.Builder {
        private byte set$0;
        private int trigger;

        public Builder() {
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.ProfilingManagerInfo.ProfilingTrigger.Builder
        public CrashlyticsReport.ProfilingManagerInfo.ProfilingTrigger build() {
            if (this.set$0 == 1) goto L5;
            Segment$$ExternalSyntheticBUOutline1.m("Missing required properties: trigger");
            return null;
        L5:
            return new AutoValue_CrashlyticsReport_ProfilingManagerInfo_ProfilingTrigger(this.trigger, null);
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.ProfilingManagerInfo.ProfilingTrigger.Builder
        public CrashlyticsReport.ProfilingManagerInfo.ProfilingTrigger.Builder setTrigger(int r1) {
            this.trigger = r1;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }
    }

    private AutoValue_CrashlyticsReport_ProfilingManagerInfo_ProfilingTrigger(int r1) {
        this.trigger = r1;
    }

    public /* synthetic */ AutoValue_CrashlyticsReport_ProfilingManagerInfo_ProfilingTrigger(int r1, AnonymousClass1 r2) {
        this(r1);
    }

    public boolean equals(Object r4) {
        if (r4 != this) goto L6;
        return true;
    L6:
        if ((r4 instanceof CrashlyticsReport.ProfilingManagerInfo.ProfilingTrigger) == true) goto L8;
    L10:
        return false;
    L8:
        if (this.trigger != ((CrashlyticsReport.ProfilingManagerInfo.ProfilingTrigger) r4).getTrigger()) goto L10;
        return true;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.ProfilingManagerInfo.ProfilingTrigger
    public int getTrigger() {
        return this.trigger;
    }

    public int hashCode() {
        return this.trigger ^ 1000003;
    }

    public String toString() {
        return "ProfilingTrigger{trigger=" + this.trigger + "}";
    }
}
