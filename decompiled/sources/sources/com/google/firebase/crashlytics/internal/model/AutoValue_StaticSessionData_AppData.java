package com.google.firebase.crashlytics.internal.model;

import c.f$$ExternalSyntheticBUOutline2;
import com.google.firebase.crashlytics.internal.DevelopmentPlatformProvider;
import com.google.firebase.crashlytics.internal.model.StaticSessionData;

/* loaded from: classes.dex */
final class AutoValue_StaticSessionData_AppData extends StaticSessionData.AppData {
    private final String appIdentifier;
    private final int deliveryMechanism;
    private final DevelopmentPlatformProvider developmentPlatformProvider;
    private final String installUuid;
    private final String versionCode;
    private final String versionName;

    public AutoValue_StaticSessionData_AppData(String r2, String r3, String r4, String r5, int r6, DevelopmentPlatformProvider r7) {
        if (r2 == null) goto L22;
        this.appIdentifier = r2;
        if (r3 == null) goto L20;
        this.versionCode = r3;
        if (r4 == null) goto L18;
        this.versionName = r4;
        if (r5 == null) goto L16;
        this.installUuid = r5;
        this.deliveryMechanism = r6;
        if (r7 == null) goto L14;
        this.developmentPlatformProvider = r7;
        return;
    L14:
        f$$ExternalSyntheticBUOutline2.m("Null developmentPlatformProvider");
        throw null;
    L16:
        f$$ExternalSyntheticBUOutline2.m("Null installUuid");
        throw null;
    L18:
        f$$ExternalSyntheticBUOutline2.m("Null versionName");
        throw null;
    L20:
        f$$ExternalSyntheticBUOutline2.m("Null versionCode");
        throw null;
    L22:
        f$$ExternalSyntheticBUOutline2.m("Null appIdentifier");
        throw null;
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData.AppData
    public String appIdentifier() {
        return this.appIdentifier;
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData.AppData
    public int deliveryMechanism() {
        return this.deliveryMechanism;
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData.AppData
    public DevelopmentPlatformProvider developmentPlatformProvider() {
        return this.developmentPlatformProvider;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof StaticSessionData.AppData) == false) goto L20;
        StaticSessionData.AppData r52 = (StaticSessionData.AppData) r5;
        if (this.appIdentifier.equals(r52.appIdentifier()) == false) goto L20;
        if (this.versionCode.equals(r52.versionCode()) == false) goto L20;
        if (this.versionName.equals(r52.versionName()) == false) goto L20;
        if (this.installUuid.equals(r52.installUuid()) == false) goto L20;
        if (this.deliveryMechanism != r52.deliveryMechanism()) goto L20;
        if (this.developmentPlatformProvider.equals(r52.developmentPlatformProvider()) == false) goto L20;
        return true;
    L20:
        return false;
    }

    public int hashCode() {
        int r0 = (((((((((this.appIdentifier.hashCode() ^ 1000003) * 1000003) ^ this.versionCode.hashCode()) * 1000003) ^ this.versionName.hashCode()) * 1000003) ^ this.installUuid.hashCode()) * 1000003) ^ this.deliveryMechanism) * 1000003;
        return this.developmentPlatformProvider.hashCode() ^ r0;
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData.AppData
    public String installUuid() {
        return this.installUuid;
    }

    public String toString() {
        return "AppData{appIdentifier=" + this.appIdentifier + ", versionCode=" + this.versionCode + ", versionName=" + this.versionName + ", installUuid=" + this.installUuid + ", deliveryMechanism=" + this.deliveryMechanism + ", developmentPlatformProvider=" + this.developmentPlatformProvider + "}";
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData.AppData
    public String versionCode() {
        return this.versionCode;
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData.AppData
    public String versionName() {
        return this.versionName;
    }
}
