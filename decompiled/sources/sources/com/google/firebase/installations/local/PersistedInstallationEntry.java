package com.google.firebase.installations.local;

import com.google.firebase.installations.local.AutoValue_PersistedInstallationEntry;
import com.google.firebase.installations.local.PersistedInstallation;

/* loaded from: classes5.dex */
public abstract class PersistedInstallationEntry {
    public static PersistedInstallationEntry INSTANCE;

    public static abstract class Builder {
        public Builder() {
        }

        public abstract PersistedInstallationEntry build();

        public abstract Builder setAuthToken(String r1);

        public abstract Builder setExpiresInSecs(long r1);

        public abstract Builder setFirebaseInstallationId(String r1);

        public abstract Builder setFisError(String r1);

        public abstract Builder setRefreshToken(String r1);

        public abstract Builder setRegistrationStatus(PersistedInstallation.RegistrationStatus r1);

        public abstract Builder setTokenCreationEpochInSecs(long r1);
    }

    static {
        INSTANCE = builder().build();
    }

    public PersistedInstallationEntry() {
    }

    public static Builder builder() {
        return new AutoValue_PersistedInstallationEntry.Builder().setTokenCreationEpochInSecs(0).setRegistrationStatus(PersistedInstallation.RegistrationStatus.ATTEMPT_MIGRATION).setExpiresInSecs(0);
    }

    public abstract String getAuthToken();

    public abstract long getExpiresInSecs();

    public abstract String getFirebaseInstallationId();

    public abstract String getFisError();

    public abstract String getRefreshToken();

    public abstract PersistedInstallation.RegistrationStatus getRegistrationStatus();

    public abstract long getTokenCreationEpochInSecs();

    public boolean isErrored() {
        if (getRegistrationStatus() != PersistedInstallation.RegistrationStatus.REGISTER_ERROR) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isNotGenerated() {
        if (getRegistrationStatus() != PersistedInstallation.RegistrationStatus.NOT_GENERATED) goto L5;
        return true;
    L5:
        if (getRegistrationStatus() == PersistedInstallation.RegistrationStatus.ATTEMPT_MIGRATION) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean isRegistered() {
        if (getRegistrationStatus() != PersistedInstallation.RegistrationStatus.REGISTERED) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isUnregistered() {
        if (getRegistrationStatus() != PersistedInstallation.RegistrationStatus.UNREGISTERED) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean shouldAttemptMigration() {
        if (getRegistrationStatus() != PersistedInstallation.RegistrationStatus.ATTEMPT_MIGRATION) goto L6;
        return true;
    L6:
        return false;
    }

    public abstract Builder toBuilder();

    public PersistedInstallationEntry withAuthToken(String r1, long r2, long r4) {
        return toBuilder().setAuthToken(r1).setExpiresInSecs(r2).setTokenCreationEpochInSecs(r4).build();
    }

    public PersistedInstallationEntry withClearedAuthToken() {
        return toBuilder().setAuthToken(null).build();
    }

    public PersistedInstallationEntry withFisError(String r1) {
        return toBuilder().setFisError(r1).setRegistrationStatus(PersistedInstallation.RegistrationStatus.REGISTER_ERROR).build();
    }

    public PersistedInstallationEntry withNoGeneratedFid() {
        return toBuilder().setRegistrationStatus(PersistedInstallation.RegistrationStatus.NOT_GENERATED).build();
    }

    public PersistedInstallationEntry withRegisteredFid(String r1, String r2, long r3, String r5, long r6) {
        return toBuilder().setFirebaseInstallationId(r1).setRegistrationStatus(PersistedInstallation.RegistrationStatus.REGISTERED).setAuthToken(r5).setRefreshToken(r2).setExpiresInSecs(r6).setTokenCreationEpochInSecs(r3).build();
    }

    public PersistedInstallationEntry withUnregisteredFid(String r1) {
        return toBuilder().setFirebaseInstallationId(r1).setRegistrationStatus(PersistedInstallation.RegistrationStatus.UNREGISTERED).build();
    }
}
