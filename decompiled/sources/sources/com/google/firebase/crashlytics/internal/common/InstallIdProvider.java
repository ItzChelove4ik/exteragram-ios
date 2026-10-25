package com.google.firebase.crashlytics.internal.common;

/* loaded from: classes.dex */
public interface InstallIdProvider {

    public static abstract class InstallIds {
        public InstallIds() {
        }

        public static InstallIds create(String r2, FirebaseInstallationId r3) {
            return new AutoValue_InstallIdProvider_InstallIds(r2, r3.getFid(), r3.getAuthToken());
        }

        public static InstallIds createWithoutFid(String r2) {
            return new AutoValue_InstallIdProvider_InstallIds(r2, null, null);
        }

        public abstract String getCrashlyticsInstallId();

        public abstract String getFirebaseAuthenticationToken();

        public abstract String getFirebaseInstallationId();
    }

    InstallIds getInstallIds();
}
