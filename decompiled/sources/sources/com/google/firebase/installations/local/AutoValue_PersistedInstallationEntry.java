package com.google.firebase.installations.local;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import com.google.firebase.installations.local.PersistedInstallation;
import com.google.firebase.installations.local.PersistedInstallationEntry;

/* loaded from: classes5.dex */
final class AutoValue_PersistedInstallationEntry extends PersistedInstallationEntry {
    private final String authToken;
    private final long expiresInSecs;
    private final String firebaseInstallationId;
    private final String fisError;
    private final String refreshToken;
    private final PersistedInstallation.RegistrationStatus registrationStatus;
    private final long tokenCreationEpochInSecs;

    /* renamed from: com.google.firebase.installations.local.AutoValue_PersistedInstallationEntry$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder extends PersistedInstallationEntry.Builder {
        private String authToken;
        private long expiresInSecs;
        private String firebaseInstallationId;
        private String fisError;
        private String refreshToken;
        private PersistedInstallation.RegistrationStatus registrationStatus;
        private byte set$0;
        private long tokenCreationEpochInSecs;

        public Builder() {
        }

        private Builder(PersistedInstallationEntry r3) {
            this.firebaseInstallationId = r3.getFirebaseInstallationId();
            this.registrationStatus = r3.getRegistrationStatus();
            this.authToken = r3.getAuthToken();
            this.refreshToken = r3.getRefreshToken();
            this.expiresInSecs = r3.getExpiresInSecs();
            this.tokenCreationEpochInSecs = r3.getTokenCreationEpochInSecs();
            this.fisError = r3.getFisError();
            this.set$0 = 3;
        }

        public /* synthetic */ Builder(PersistedInstallationEntry r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // com.google.firebase.installations.local.PersistedInstallationEntry.Builder
        public PersistedInstallationEntry build() {
            if (this.set$0 == 3) goto L5;
        L9:
            StringBuilder r0 = new StringBuilder();
            if (this.registrationStatus != null) goto L13;
            r0.append(" registrationStatus");
        L13:
            if ((this.set$0 & 1) != 0) goto L16;
            r0.append(" expiresInSecs");
        L16:
            if ((this.set$0 & 2) != 0) goto L18;
            r0.append(" tokenCreationEpochInSecs");
        L18:
            DexMaker$$ExternalSyntheticBUOutline0.m("Missing required properties:", r0);
            return null;
        L5:
            if (this.registrationStatus == null) goto L9;
            return new AutoValue_PersistedInstallationEntry(this.firebaseInstallationId, this.registrationStatus, this.authToken, this.refreshToken, this.expiresInSecs, this.tokenCreationEpochInSecs, this.fisError, null);
        }

        @Override // com.google.firebase.installations.local.PersistedInstallationEntry.Builder
        public PersistedInstallationEntry.Builder setAuthToken(String r1) {
            this.authToken = r1;
            return this;
        }

        @Override // com.google.firebase.installations.local.PersistedInstallationEntry.Builder
        public PersistedInstallationEntry.Builder setExpiresInSecs(long r1) {
            this.expiresInSecs = r1;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        @Override // com.google.firebase.installations.local.PersistedInstallationEntry.Builder
        public PersistedInstallationEntry.Builder setFirebaseInstallationId(String r1) {
            this.firebaseInstallationId = r1;
            return this;
        }

        @Override // com.google.firebase.installations.local.PersistedInstallationEntry.Builder
        public PersistedInstallationEntry.Builder setFisError(String r1) {
            this.fisError = r1;
            return this;
        }

        @Override // com.google.firebase.installations.local.PersistedInstallationEntry.Builder
        public PersistedInstallationEntry.Builder setRefreshToken(String r1) {
            this.refreshToken = r1;
            return this;
        }

        @Override // com.google.firebase.installations.local.PersistedInstallationEntry.Builder
        public PersistedInstallationEntry.Builder setRegistrationStatus(PersistedInstallation.RegistrationStatus r1) {
            if (r1 == null) goto L5;
            this.registrationStatus = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null registrationStatus");
            return null;
        }

        @Override // com.google.firebase.installations.local.PersistedInstallationEntry.Builder
        public PersistedInstallationEntry.Builder setTokenCreationEpochInSecs(long r1) {
            this.tokenCreationEpochInSecs = r1;
            this.set$0 = (byte) (this.set$0 | 2);
            return this;
        }
    }

    private AutoValue_PersistedInstallationEntry(String r1, PersistedInstallation.RegistrationStatus r2, String r3, String r4, long r5, long r7, String r9) {
        this.firebaseInstallationId = r1;
        this.registrationStatus = r2;
        this.authToken = r3;
        this.refreshToken = r4;
        this.expiresInSecs = r5;
        this.tokenCreationEpochInSecs = r7;
        this.fisError = r9;
    }

    public /* synthetic */ AutoValue_PersistedInstallationEntry(String r1, PersistedInstallation.RegistrationStatus r2, String r3, String r4, long r5, long r7, String r9, AnonymousClass1 r10) {
        this(r1, r2, r3, r4, r5, r7, r9);
    }

    public boolean equals(Object r8) {
        if (r8 != this) goto L6;
        return true;
    L6:
        if ((r8 instanceof PersistedInstallationEntry) == false) goto L42;
        PersistedInstallationEntry r82 = (PersistedInstallationEntry) r8;
        String r1 = this.firebaseInstallationId;
        if (r1 != null) goto L13;
        if (r82.getFirebaseInstallationId() != null) goto L42;
    L15:
        if (this.registrationStatus.equals(r82.getRegistrationStatus()) == false) goto L42;
        String r12 = this.authToken;
        if (r12 != null) goto L22;
        if (r82.getAuthToken() != null) goto L42;
    L23:
        String r13 = this.refreshToken;
        if (r13 != null) goto L29;
        if (r82.getRefreshToken() != null) goto L42;
    L31:
        if (this.expiresInSecs != r82.getExpiresInSecs()) goto L42;
        if (this.tokenCreationEpochInSecs != r82.getTokenCreationEpochInSecs()) goto L42;
        String r7 = this.fisError;
        if (r7 != null) goto L40;
        if (r82.getFisError() != null) goto L42;
    L41:
        return true;
    L40:
        if (r7.equals(r82.getFisError()) == false) goto L42;
    L29:
        if (r13.equals(r82.getRefreshToken()) == false) goto L42;
    L22:
        if (r12.equals(r82.getAuthToken()) == false) goto L42;
    L13:
        if (r1.equals(r82.getFirebaseInstallationId()) == true) goto L15;
    L42:
        return false;
    }

    @Override // com.google.firebase.installations.local.PersistedInstallationEntry
    public String getAuthToken() {
        return this.authToken;
    }

    @Override // com.google.firebase.installations.local.PersistedInstallationEntry
    public long getExpiresInSecs() {
        return this.expiresInSecs;
    }

    @Override // com.google.firebase.installations.local.PersistedInstallationEntry
    public String getFirebaseInstallationId() {
        return this.firebaseInstallationId;
    }

    @Override // com.google.firebase.installations.local.PersistedInstallationEntry
    public String getFisError() {
        return this.fisError;
    }

    @Override // com.google.firebase.installations.local.PersistedInstallationEntry
    public String getRefreshToken() {
        return this.refreshToken;
    }

    @Override // com.google.firebase.installations.local.PersistedInstallationEntry
    public PersistedInstallation.RegistrationStatus getRegistrationStatus() {
        return this.registrationStatus;
    }

    @Override // com.google.firebase.installations.local.PersistedInstallationEntry
    public long getTokenCreationEpochInSecs() {
        return this.tokenCreationEpochInSecs;
    }

    public int hashCode() {
        String r0 = this.firebaseInstallationId;
        int r1 = 0;
        if (r0 != null) goto L5;
        int r02 = 0;
    L6:
        int r03 = (((r02 ^ 1000003) * 1000003) ^ this.registrationStatus.hashCode()) * 1000003;
        String r3 = this.authToken;
        if (r3 != null) goto L9;
        int r32 = 0;
    L10:
        int r04 = (r03 ^ r32) * 1000003;
        String r33 = this.refreshToken;
        if (r33 != null) goto L13;
        int r34 = 0;
    L14:
        int r05 = (r04 ^ r34) * 1000003;
        long r35 = this.expiresInSecs;
        int r06 = (r05 ^ ((int) (r35 ^ (r35 >>> 32)))) * 1000003;
        long r36 = this.tokenCreationEpochInSecs;
        int r07 = (r06 ^ ((int) (r36 ^ (r36 >>> 32)))) * 1000003;
        String r8 = this.fisError;
        if (r8 == null) goto L19;
        r1 = r8.hashCode();
    L19:
        return r07 ^ r1;
    L13:
        r34 = r33.hashCode();
        goto L14
    L9:
        r32 = r3.hashCode();
        goto L10
    L5:
        r02 = r0.hashCode();
        goto L6
    }

    @Override // com.google.firebase.installations.local.PersistedInstallationEntry
    public PersistedInstallationEntry.Builder toBuilder() {
        return new Builder(this, null);
    }

    public String toString() {
        return "PersistedInstallationEntry{firebaseInstallationId=" + this.firebaseInstallationId + ", registrationStatus=" + this.registrationStatus + ", authToken=" + this.authToken + ", refreshToken=" + this.refreshToken + ", expiresInSecs=" + this.expiresInSecs + ", tokenCreationEpochInSecs=" + this.tokenCreationEpochInSecs + ", fisError=" + this.fisError + "}";
    }
}
