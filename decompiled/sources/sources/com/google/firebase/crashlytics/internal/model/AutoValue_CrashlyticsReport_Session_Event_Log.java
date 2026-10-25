package com.google.firebase.crashlytics.internal.model;

import c.f$$ExternalSyntheticBUOutline2;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
final class AutoValue_CrashlyticsReport_Session_Event_Log extends CrashlyticsReport.Session.Event.Log {
    private final String content;

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_Event_Log$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    /* loaded from: classes5.dex */
    public static final class Builder extends CrashlyticsReport.Session.Event.Log.Builder {
        private String content;

        public Builder() {
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Log.Builder
        public CrashlyticsReport.Session.Event.Log build() {
            String r2 = this.content;
            if (r2 != null) goto L5;
            Segment$$ExternalSyntheticBUOutline1.m("Missing required properties: content");
            return null;
        L5:
            return new AutoValue_CrashlyticsReport_Session_Event_Log(r2, null);
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Log.Builder
        public CrashlyticsReport.Session.Event.Log.Builder setContent(String r1) {
            if (r1 == null) goto L5;
            this.content = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null content");
            return null;
        }
    }

    private AutoValue_CrashlyticsReport_Session_Event_Log(String r1) {
        this.content = r1;
    }

    public /* synthetic */ AutoValue_CrashlyticsReport_Session_Event_Log(String r1, AnonymousClass1 r2) {
        this(r1);
    }

    public boolean equals(Object r2) {
        if (r2 != this) goto L6;
        return true;
    L6:
        if ((r2 instanceof CrashlyticsReport.Session.Event.Log) == true) goto L8;
        return false;
    L8:
        return this.content.equals(((CrashlyticsReport.Session.Event.Log) r2).getContent());
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Log
    public String getContent() {
        return this.content;
    }

    public int hashCode() {
        return this.content.hashCode() ^ 1000003;
    }

    public String toString() {
        return "Log{content=" + this.content + "}";
    }
}
