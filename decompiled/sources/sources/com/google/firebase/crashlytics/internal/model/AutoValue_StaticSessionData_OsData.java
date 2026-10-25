package com.google.firebase.crashlytics.internal.model;

import c.f$$ExternalSyntheticBUOutline2;
import com.google.firebase.crashlytics.internal.model.StaticSessionData;

/* loaded from: classes.dex */
final class AutoValue_StaticSessionData_OsData extends StaticSessionData.OsData {
    private final boolean isRooted;
    private final String osCodeName;
    private final String osRelease;

    public AutoValue_StaticSessionData_OsData(String r2, String r3, boolean r4) {
        if (r2 == null) goto L10;
        this.osRelease = r2;
        if (r3 == null) goto L8;
        this.osCodeName = r3;
        this.isRooted = r4;
        return;
    L8:
        f$$ExternalSyntheticBUOutline2.m("Null osCodeName");
        throw null;
    L10:
        f$$ExternalSyntheticBUOutline2.m("Null osRelease");
        throw null;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof StaticSessionData.OsData) == false) goto L14;
        StaticSessionData.OsData r52 = (StaticSessionData.OsData) r5;
        if (this.osRelease.equals(r52.osRelease()) == false) goto L14;
        if (this.osCodeName.equals(r52.osCodeName()) == false) goto L14;
        if (this.isRooted != r52.isRooted()) goto L14;
        return true;
    L14:
        return false;
    }

    public int hashCode() {
        int r0 = (((this.osRelease.hashCode() ^ 1000003) * 1000003) ^ this.osCodeName.hashCode()) * 1000003;
        if (this.isRooted == false) goto L5;
        int r3 = 1231;
    L7:
        return r3 ^ r0;
    L5:
        r3 = 1237;
        goto L7
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData.OsData
    public boolean isRooted() {
        return this.isRooted;
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData.OsData
    public String osCodeName() {
        return this.osCodeName;
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData.OsData
    public String osRelease() {
        return this.osRelease;
    }

    public String toString() {
        return "OsData{osRelease=" + this.osRelease + ", osCodeName=" + this.osCodeName + ", isRooted=" + this.isRooted + "}";
    }
}
