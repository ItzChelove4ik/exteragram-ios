package com.google.firebase.crashlytics.internal.settings;

import com.google.firebase.crashlytics.internal.common.InstallIdProvider;

/* loaded from: classes.dex */
class SettingsRequest {
    public final String buildVersion;
    public final String deviceModel;
    public final String displayVersion;
    public final String googleAppId;
    public final InstallIdProvider installIdProvider;
    public final String instanceId;
    public final String osBuildVersion;
    public final String osDisplayVersion;
    public final int source;

    public SettingsRequest(String r1, String r2, String r3, String r4, InstallIdProvider r5, String r6, String r7, String r8, int r9) {
        this.googleAppId = r1;
        this.deviceModel = r2;
        this.osBuildVersion = r3;
        this.osDisplayVersion = r4;
        this.installIdProvider = r5;
        this.instanceId = r6;
        this.displayVersion = r7;
        this.buildVersion = r8;
        this.source = r9;
    }
}
