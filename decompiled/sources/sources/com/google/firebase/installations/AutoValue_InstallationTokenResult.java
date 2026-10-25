package com.google.firebase.installations;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import com.google.firebase.installations.InstallationTokenResult;

/* loaded from: classes5.dex */
final class AutoValue_InstallationTokenResult extends InstallationTokenResult {
    private final String token;
    private final long tokenCreationTimestamp;
    private final long tokenExpirationTimestamp;

    /* renamed from: com.google.firebase.installations.AutoValue_InstallationTokenResult$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder extends InstallationTokenResult.Builder {
        private byte set$0;
        private String token;
        private long tokenCreationTimestamp;
        private long tokenExpirationTimestamp;

        public Builder() {
        }

        @Override // com.google.firebase.installations.InstallationTokenResult.Builder
        public InstallationTokenResult build() {
            if (this.set$0 != 3) goto L9;
            String r3 = this.token;
            if (r3 == null) goto L9;
            return new AutoValue_InstallationTokenResult(r3, this.tokenExpirationTimestamp, this.tokenCreationTimestamp, null);
        L9:
            StringBuilder r0 = new StringBuilder();
            if (this.token != null) goto L13;
            r0.append(" token");
        L13:
            if ((this.set$0 & 1) != 0) goto L16;
            r0.append(" tokenExpirationTimestamp");
        L16:
            if ((this.set$0 & 2) != 0) goto L18;
            r0.append(" tokenCreationTimestamp");
        L18:
            DexMaker$$ExternalSyntheticBUOutline0.m("Missing required properties:", r0);
            return null;
        }

        @Override // com.google.firebase.installations.InstallationTokenResult.Builder
        public InstallationTokenResult.Builder setToken(String r1) {
            if (r1 == null) goto L5;
            this.token = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null token");
            return null;
        }

        @Override // com.google.firebase.installations.InstallationTokenResult.Builder
        public InstallationTokenResult.Builder setTokenCreationTimestamp(long r1) {
            this.tokenCreationTimestamp = r1;
            this.set$0 = (byte) (this.set$0 | 2);
            return this;
        }

        @Override // com.google.firebase.installations.InstallationTokenResult.Builder
        public InstallationTokenResult.Builder setTokenExpirationTimestamp(long r1) {
            this.tokenExpirationTimestamp = r1;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }
    }

    private AutoValue_InstallationTokenResult(String r1, long r2, long r4) {
        this.token = r1;
        this.tokenExpirationTimestamp = r2;
        this.tokenCreationTimestamp = r4;
    }

    public /* synthetic */ AutoValue_InstallationTokenResult(String r1, long r2, long r4, AnonymousClass1 r6) {
        this(r1, r2, r4);
    }

    public boolean equals(Object r8) {
        if (r8 != this) goto L6;
        return true;
    L6:
        if ((r8 instanceof InstallationTokenResult) == false) goto L14;
        InstallationTokenResult r82 = (InstallationTokenResult) r8;
        if (this.token.equals(r82.getToken()) == false) goto L14;
        if (this.tokenExpirationTimestamp != r82.getTokenExpirationTimestamp()) goto L14;
        if (this.tokenCreationTimestamp != r82.getTokenCreationTimestamp()) goto L14;
        return true;
    L14:
        return false;
    }

    @Override // com.google.firebase.installations.InstallationTokenResult
    public String getToken() {
        return this.token;
    }

    @Override // com.google.firebase.installations.InstallationTokenResult
    public long getTokenCreationTimestamp() {
        return this.tokenCreationTimestamp;
    }

    @Override // com.google.firebase.installations.InstallationTokenResult
    public long getTokenExpirationTimestamp() {
        return this.tokenExpirationTimestamp;
    }

    public int hashCode() {
        int r0 = (this.token.hashCode() ^ 1000003) * 1000003;
        long r2 = this.tokenExpirationTimestamp;
        long r1 = this.tokenCreationTimestamp;
        int r7 = (int) (r1 ^ (r1 >>> 32));
        return r7 ^ ((r0 ^ ((int) (r2 ^ (r2 >>> 32)))) * 1000003);
    }

    public String toString() {
        return "InstallationTokenResult{token=" + this.token + ", tokenExpirationTimestamp=" + this.tokenExpirationTimestamp + ", tokenCreationTimestamp=" + this.tokenCreationTimestamp + "}";
    }
}
