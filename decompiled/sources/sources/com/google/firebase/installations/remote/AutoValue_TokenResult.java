package com.google.firebase.installations.remote;

import com.google.firebase.installations.remote.TokenResult;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
final class AutoValue_TokenResult extends TokenResult {
    private final TokenResult.ResponseCode responseCode;
    private final String token;
    private final long tokenExpirationTimestamp;

    /* renamed from: com.google.firebase.installations.remote.AutoValue_TokenResult$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder extends TokenResult.Builder {
        private TokenResult.ResponseCode responseCode;
        private byte set$0;
        private String token;
        private long tokenExpirationTimestamp;

        public Builder() {
        }

        @Override // com.google.firebase.installations.remote.TokenResult.Builder
        public TokenResult build() {
            if (this.set$0 == 1) goto L5;
            Segment$$ExternalSyntheticBUOutline1.m("Missing required properties: tokenExpirationTimestamp");
            return null;
        L5:
            return new AutoValue_TokenResult(this.token, this.tokenExpirationTimestamp, this.responseCode, null);
        }

        @Override // com.google.firebase.installations.remote.TokenResult.Builder
        public TokenResult.Builder setResponseCode(TokenResult.ResponseCode r1) {
            this.responseCode = r1;
            return this;
        }

        @Override // com.google.firebase.installations.remote.TokenResult.Builder
        public TokenResult.Builder setToken(String r1) {
            this.token = r1;
            return this;
        }

        @Override // com.google.firebase.installations.remote.TokenResult.Builder
        public TokenResult.Builder setTokenExpirationTimestamp(long r1) {
            this.tokenExpirationTimestamp = r1;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }
    }

    private AutoValue_TokenResult(String r1, long r2, TokenResult.ResponseCode r4) {
        this.token = r1;
        this.tokenExpirationTimestamp = r2;
        this.responseCode = r4;
    }

    public /* synthetic */ AutoValue_TokenResult(String r1, long r2, TokenResult.ResponseCode r4, AnonymousClass1 r5) {
        this(r1, r2, r4);
    }

    public boolean equals(Object r8) {
        if (r8 != this) goto L6;
        return true;
    L6:
        if ((r8 instanceof TokenResult) == false) goto L24;
        TokenResult r82 = (TokenResult) r8;
        String r1 = this.token;
        if (r1 != null) goto L13;
        if (r82.getToken() != null) goto L24;
    L15:
        if (this.tokenExpirationTimestamp != r82.getTokenExpirationTimestamp()) goto L24;
        TokenResult.ResponseCode r7 = this.responseCode;
        if (r7 != null) goto L22;
        if (r82.getResponseCode() != null) goto L24;
    L23:
        return true;
    L22:
        if (r7.equals(r82.getResponseCode()) == false) goto L24;
    L13:
        if (r1.equals(r82.getToken()) == true) goto L15;
    L24:
        return false;
    }

    @Override // com.google.firebase.installations.remote.TokenResult
    public TokenResult.ResponseCode getResponseCode() {
        return this.responseCode;
    }

    @Override // com.google.firebase.installations.remote.TokenResult
    public String getToken() {
        return this.token;
    }

    @Override // com.google.firebase.installations.remote.TokenResult
    public long getTokenExpirationTimestamp() {
        return this.tokenExpirationTimestamp;
    }

    public int hashCode() {
        String r0 = this.token;
        int r1 = 0;
        if (r0 != null) goto L5;
        int r02 = 0;
    L6:
        long r3 = this.tokenExpirationTimestamp;
        int r03 = (((r02 ^ 1000003) * 1000003) ^ ((int) (r3 ^ (r3 >>> 32)))) * 1000003;
        TokenResult.ResponseCode r7 = this.responseCode;
        if (r7 == null) goto L11;
        r1 = r7.hashCode();
    L11:
        return r03 ^ r1;
    L5:
        r02 = r0.hashCode();
        goto L6
    }

    public String toString() {
        return "TokenResult{token=" + this.token + ", tokenExpirationTimestamp=" + this.tokenExpirationTimestamp + ", responseCode=" + this.responseCode + "}";
    }
}
