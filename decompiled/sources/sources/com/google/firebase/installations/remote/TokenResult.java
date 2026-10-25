package com.google.firebase.installations.remote;

import com.google.firebase.installations.remote.AutoValue_TokenResult;

/* loaded from: classes5.dex */
public abstract class TokenResult {

    public static abstract class Builder {
        public Builder() {
        }

        public abstract TokenResult build();

        public abstract Builder setResponseCode(ResponseCode r1);

        public abstract Builder setToken(String r1);

        public abstract Builder setTokenExpirationTimestamp(long r1);
    }

    public enum ResponseCode extends Enum<ResponseCode> {
        private static final /* synthetic */ ResponseCode[] $VALUES = null;
        public static final ResponseCode AUTH_ERROR = null;
        public static final ResponseCode BAD_CONFIG = null;
        public static final ResponseCode OK = null;

        private static /* synthetic */ ResponseCode[] $values() {
            return new ResponseCode[]{OK, BAD_CONFIG, AUTH_ERROR};
        }

        static {
            OK = new ResponseCode("OK", 0);
            BAD_CONFIG = new ResponseCode("BAD_CONFIG", 1);
            AUTH_ERROR = new ResponseCode("AUTH_ERROR", 2);
            $VALUES = $values();
        }

        ResponseCode(String r1, int r2) {
        }

        public static ResponseCode valueOf(String r1) {
            return (ResponseCode) Enum.valueOf(ResponseCode.class, r1);
        }

        public static ResponseCode[] values() {
            return (ResponseCode[]) $VALUES.clone();
        }
    }

    public TokenResult() {
    }

    public static Builder builder() {
        return new AutoValue_TokenResult.Builder().setTokenExpirationTimestamp(0);
    }

    public abstract ResponseCode getResponseCode();

    public abstract String getToken();

    public abstract long getTokenExpirationTimestamp();
}
