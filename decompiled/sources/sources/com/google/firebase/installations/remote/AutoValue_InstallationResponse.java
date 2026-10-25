package com.google.firebase.installations.remote;

import com.google.firebase.installations.remote.InstallationResponse;

/* loaded from: classes5.dex */
final class AutoValue_InstallationResponse extends InstallationResponse {
    private final TokenResult authToken;
    private final String fid;
    private final String refreshToken;
    private final InstallationResponse.ResponseCode responseCode;
    private final String uri;

    /* renamed from: com.google.firebase.installations.remote.AutoValue_InstallationResponse$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder extends InstallationResponse.Builder {
        private TokenResult authToken;
        private String fid;
        private String refreshToken;
        private InstallationResponse.ResponseCode responseCode;
        private String uri;

        public Builder() {
        }

        @Override // com.google.firebase.installations.remote.InstallationResponse.Builder
        public InstallationResponse build() {
            return new AutoValue_InstallationResponse(this.uri, this.fid, this.refreshToken, this.authToken, this.responseCode, null);
        }

        @Override // com.google.firebase.installations.remote.InstallationResponse.Builder
        public InstallationResponse.Builder setAuthToken(TokenResult r1) {
            this.authToken = r1;
            return this;
        }

        @Override // com.google.firebase.installations.remote.InstallationResponse.Builder
        public InstallationResponse.Builder setFid(String r1) {
            this.fid = r1;
            return this;
        }

        @Override // com.google.firebase.installations.remote.InstallationResponse.Builder
        public InstallationResponse.Builder setRefreshToken(String r1) {
            this.refreshToken = r1;
            return this;
        }

        @Override // com.google.firebase.installations.remote.InstallationResponse.Builder
        public InstallationResponse.Builder setResponseCode(InstallationResponse.ResponseCode r1) {
            this.responseCode = r1;
            return this;
        }

        @Override // com.google.firebase.installations.remote.InstallationResponse.Builder
        public InstallationResponse.Builder setUri(String r1) {
            this.uri = r1;
            return this;
        }
    }

    private AutoValue_InstallationResponse(String r1, String r2, String r3, TokenResult r4, InstallationResponse.ResponseCode r5) {
        this.uri = r1;
        this.fid = r2;
        this.refreshToken = r3;
        this.authToken = r4;
        this.responseCode = r5;
    }

    public /* synthetic */ AutoValue_InstallationResponse(String r1, String r2, String r3, TokenResult r4, InstallationResponse.ResponseCode r5, AnonymousClass1 r6) {
        this(r1, r2, r3, r4, r5);
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof InstallationResponse) == false) goto L43;
        InstallationResponse r52 = (InstallationResponse) r5;
        String r1 = this.uri;
        if (r1 != null) goto L13;
        if (r52.getUri() != null) goto L43;
    L14:
        String r12 = this.fid;
        if (r12 != null) goto L20;
        if (r52.getFid() != null) goto L43;
    L21:
        String r13 = this.refreshToken;
        if (r13 != null) goto L27;
        if (r52.getRefreshToken() != null) goto L43;
    L28:
        TokenResult r14 = this.authToken;
        if (r14 != null) goto L34;
        if (r52.getAuthToken() != null) goto L43;
    L35:
        InstallationResponse.ResponseCode r4 = this.responseCode;
        if (r4 != null) goto L41;
        if (r52.getResponseCode() != null) goto L43;
    L42:
        return true;
    L41:
        if (r4.equals(r52.getResponseCode()) == false) goto L43;
    L34:
        if (r14.equals(r52.getAuthToken()) == false) goto L43;
    L27:
        if (r13.equals(r52.getRefreshToken()) == false) goto L43;
    L20:
        if (r12.equals(r52.getFid()) == false) goto L43;
    L13:
        if (r1.equals(r52.getUri()) == true) goto L14;
    L43:
        return false;
    }

    @Override // com.google.firebase.installations.remote.InstallationResponse
    public TokenResult getAuthToken() {
        return this.authToken;
    }

    @Override // com.google.firebase.installations.remote.InstallationResponse
    public String getFid() {
        return this.fid;
    }

    @Override // com.google.firebase.installations.remote.InstallationResponse
    public String getRefreshToken() {
        return this.refreshToken;
    }

    @Override // com.google.firebase.installations.remote.InstallationResponse
    public InstallationResponse.ResponseCode getResponseCode() {
        return this.responseCode;
    }

    @Override // com.google.firebase.installations.remote.InstallationResponse
    public String getUri() {
        return this.uri;
    }

    public int hashCode() {
        String r0 = this.uri;
        int r1 = 0;
        if (r0 != null) goto L5;
        int r02 = 0;
    L6:
        int r03 = (r02 ^ 1000003) * 1000003;
        String r3 = this.fid;
        if (r3 != null) goto L9;
        int r32 = 0;
    L10:
        int r04 = (r03 ^ r32) * 1000003;
        String r33 = this.refreshToken;
        if (r33 != null) goto L13;
        int r34 = 0;
    L14:
        int r05 = (r04 ^ r34) * 1000003;
        TokenResult r35 = this.authToken;
        if (r35 != null) goto L17;
        int r36 = 0;
    L18:
        int r06 = (r05 ^ r36) * 1000003;
        InstallationResponse.ResponseCode r4 = this.responseCode;
        if (r4 == null) goto L23;
        r1 = r4.hashCode();
    L23:
        return r06 ^ r1;
    L17:
        r36 = r35.hashCode();
        goto L18
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

    public String toString() {
        return "InstallationResponse{uri=" + this.uri + ", fid=" + this.fid + ", refreshToken=" + this.refreshToken + ", authToken=" + this.authToken + ", responseCode=" + this.responseCode + "}";
    }
}
