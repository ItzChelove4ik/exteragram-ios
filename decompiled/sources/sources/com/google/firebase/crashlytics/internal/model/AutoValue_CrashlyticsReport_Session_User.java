package com.google.firebase.crashlytics.internal.model;

import c.f$$ExternalSyntheticBUOutline2;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
final class AutoValue_CrashlyticsReport_Session_User extends CrashlyticsReport.Session.User {
    private final String identifier;

    /* renamed from: com.google.firebase.crashlytics.internal.model.AutoValue_CrashlyticsReport_Session_User$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    /* loaded from: classes5.dex */
    public static final class Builder extends CrashlyticsReport.Session.User.Builder {
        private String identifier;

        public Builder() {
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.User.Builder
        public CrashlyticsReport.Session.User build() {
            String r2 = this.identifier;
            if (r2 != null) goto L5;
            Segment$$ExternalSyntheticBUOutline1.m("Missing required properties: identifier");
            return null;
        L5:
            return new AutoValue_CrashlyticsReport_Session_User(r2, null);
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.User.Builder
        public CrashlyticsReport.Session.User.Builder setIdentifier(String r1) {
            if (r1 == null) goto L5;
            this.identifier = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null identifier");
            return null;
        }
    }

    private AutoValue_CrashlyticsReport_Session_User(String r1) {
        this.identifier = r1;
    }

    public /* synthetic */ AutoValue_CrashlyticsReport_Session_User(String r1, AnonymousClass1 r2) {
        this(r1);
    }

    public boolean equals(Object r2) {
        if (r2 != this) goto L6;
        return true;
    L6:
        if ((r2 instanceof CrashlyticsReport.Session.User) == true) goto L8;
        return false;
    L8:
        return this.identifier.equals(((CrashlyticsReport.Session.User) r2).getIdentifier());
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.User
    public String getIdentifier() {
        return this.identifier;
    }

    public int hashCode() {
        return this.identifier.hashCode() ^ 1000003;
    }

    public String toString() {
        return "User{identifier=" + this.identifier + "}";
    }
}
