package com.google.firebase.crashlytics.internal.model;

import c.f$$ExternalSyntheticBUOutline2;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
final class AutoValue_CrashlyticsReport_ProfilingManagerInfo extends CrashlyticsReport.ProfilingManagerInfo {
    private final CrashlyticsReport.ProfilingManagerInfo.ProfilingTrigger profilingTrigger;

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_ProfilingManagerInfo$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder extends CrashlyticsReport.ProfilingManagerInfo.Builder {
        private CrashlyticsReport.ProfilingManagerInfo.ProfilingTrigger profilingTrigger;

        public Builder() {
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.ProfilingManagerInfo.Builder
        public CrashlyticsReport.ProfilingManagerInfo build() {
            CrashlyticsReport.ProfilingManagerInfo.ProfilingTrigger r2 = this.profilingTrigger;
            if (r2 != null) goto L5;
            Segment$$ExternalSyntheticBUOutline1.m("Missing required properties: profilingTrigger");
            return null;
        L5:
            return new AutoValue_CrashlyticsReport_ProfilingManagerInfo(r2, null);
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.ProfilingManagerInfo.Builder
        public CrashlyticsReport.ProfilingManagerInfo.Builder setProfilingTrigger(CrashlyticsReport.ProfilingManagerInfo.ProfilingTrigger r1) {
            if (r1 == null) goto L5;
            this.profilingTrigger = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null profilingTrigger");
            return null;
        }
    }

    private AutoValue_CrashlyticsReport_ProfilingManagerInfo(CrashlyticsReport.ProfilingManagerInfo.ProfilingTrigger r1) {
        this.profilingTrigger = r1;
    }

    public /* synthetic */ AutoValue_CrashlyticsReport_ProfilingManagerInfo(CrashlyticsReport.ProfilingManagerInfo.ProfilingTrigger r1, AnonymousClass1 r2) {
        this(r1);
    }

    public boolean equals(Object r2) {
        if (r2 != this) goto L6;
        return true;
    L6:
        if ((r2 instanceof CrashlyticsReport.ProfilingManagerInfo) == true) goto L8;
        return false;
    L8:
        return this.profilingTrigger.equals(((CrashlyticsReport.ProfilingManagerInfo) r2).getProfilingTrigger());
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.ProfilingManagerInfo
    public CrashlyticsReport.ProfilingManagerInfo.ProfilingTrigger getProfilingTrigger() {
        return this.profilingTrigger;
    }

    public int hashCode() {
        return this.profilingTrigger.hashCode() ^ 1000003;
    }

    public String toString() {
        return "ProfilingManagerInfo{profilingTrigger=" + this.profilingTrigger + "}";
    }
}
