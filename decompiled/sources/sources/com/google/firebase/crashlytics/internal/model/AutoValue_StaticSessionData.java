package com.google.firebase.crashlytics.internal.model;

import c.f$$ExternalSyntheticBUOutline2;
import com.google.firebase.crashlytics.internal.model.StaticSessionData;

/* loaded from: classes.dex */
final class AutoValue_StaticSessionData extends StaticSessionData {
    private final StaticSessionData.AppData appData;
    private final StaticSessionData.DeviceData deviceData;
    private final StaticSessionData.OsData osData;

    public AutoValue_StaticSessionData(StaticSessionData.AppData r2, StaticSessionData.OsData r3, StaticSessionData.DeviceData r4) {
        if (r2 == null) goto L14;
        this.appData = r2;
        if (r3 == null) goto L12;
        this.osData = r3;
        if (r4 == null) goto L10;
        this.deviceData = r4;
        return;
    L10:
        f$$ExternalSyntheticBUOutline2.m("Null deviceData");
        throw null;
    L12:
        f$$ExternalSyntheticBUOutline2.m("Null osData");
        throw null;
    L14:
        f$$ExternalSyntheticBUOutline2.m("Null appData");
        throw null;
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData
    public StaticSessionData.AppData appData() {
        return this.appData;
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData
    public StaticSessionData.DeviceData deviceData() {
        return this.deviceData;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof StaticSessionData) == false) goto L14;
        StaticSessionData r52 = (StaticSessionData) r5;
        if (this.appData.equals(r52.appData()) == false) goto L14;
        if (this.osData.equals(r52.osData()) == false) goto L14;
        if (this.deviceData.equals(r52.deviceData()) == false) goto L14;
        return true;
    L14:
        return false;
    }

    public int hashCode() {
        int r0 = (((this.appData.hashCode() ^ 1000003) * 1000003) ^ this.osData.hashCode()) * 1000003;
        return this.deviceData.hashCode() ^ r0;
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData
    public StaticSessionData.OsData osData() {
        return this.osData;
    }

    public String toString() {
        return "StaticSessionData{appData=" + this.appData + ", osData=" + this.osData + ", deviceData=" + this.deviceData + "}";
    }
}
