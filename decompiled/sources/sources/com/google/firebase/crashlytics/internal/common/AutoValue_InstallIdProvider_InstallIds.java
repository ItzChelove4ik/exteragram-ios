package com.google.firebase.crashlytics.internal.common;

import c.f$$ExternalSyntheticBUOutline2;
import com.google.firebase.crashlytics.internal.common.InstallIdProvider;

/* loaded from: classes.dex */
final class AutoValue_InstallIdProvider_InstallIds extends InstallIdProvider.InstallIds {
    private final String crashlyticsInstallId;
    private final String firebaseAuthenticationToken;
    private final String firebaseInstallationId;

    public AutoValue_InstallIdProvider_InstallIds(String r1, String r2, String r3) {
        if (r1 == null) goto L6;
        this.crashlyticsInstallId = r1;
        this.firebaseInstallationId = r2;
        this.firebaseAuthenticationToken = r3;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("Null crashlyticsInstallId");
        throw null;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof InstallIdProvider.InstallIds) == false) goto L24;
        InstallIdProvider.InstallIds r52 = (InstallIdProvider.InstallIds) r5;
        if (this.crashlyticsInstallId.equals(r52.getCrashlyticsInstallId()) == false) goto L24;
        String r1 = this.firebaseInstallationId;
        if (r1 != null) goto L15;
        if (r52.getFirebaseInstallationId() != null) goto L24;
    L16:
        String r4 = this.firebaseAuthenticationToken;
        if (r4 != null) goto L22;
        if (r52.getFirebaseAuthenticationToken() != null) goto L24;
    L23:
        return true;
    L22:
        if (r4.equals(r52.getFirebaseAuthenticationToken()) == false) goto L24;
    L15:
        if (r1.equals(r52.getFirebaseInstallationId()) == true) goto L16;
    L24:
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.common.InstallIdProvider.InstallIds
    public String getCrashlyticsInstallId() {
        return this.crashlyticsInstallId;
    }

    @Override // com.google.firebase.crashlytics.internal.common.InstallIdProvider.InstallIds
    public String getFirebaseAuthenticationToken() {
        return this.firebaseAuthenticationToken;
    }

    @Override // com.google.firebase.crashlytics.internal.common.InstallIdProvider.InstallIds
    public String getFirebaseInstallationId() {
        return this.firebaseInstallationId;
    }

    public int hashCode() {
        int r0 = (this.crashlyticsInstallId.hashCode() ^ 1000003) * 1000003;
        String r2 = this.firebaseInstallationId;
        int r3 = 0;
        if (r2 != null) goto L5;
        int r22 = 0;
    L6:
        int r02 = (r0 ^ r22) * 1000003;
        String r4 = this.firebaseAuthenticationToken;
        if (r4 == null) goto L11;
        r3 = r4.hashCode();
    L11:
        return r02 ^ r3;
    L5:
        r22 = r2.hashCode();
        goto L6
    }

    public String toString() {
        return "InstallIds{crashlyticsInstallId=" + this.crashlyticsInstallId + ", firebaseInstallationId=" + this.firebaseInstallationId + ", firebaseAuthenticationToken=" + this.firebaseAuthenticationToken + "}";
    }
}
