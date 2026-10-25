package com.google.firebase.installations.remote;

import com.google.firebase.installations.remote.AutoValue_InstallationResponse;

/* loaded from: classes5.dex */
public abstract class InstallationResponse {

    public static abstract class Builder {
        public Builder() {
        }

        public abstract InstallationResponse build();

        public abstract Builder setAuthToken(TokenResult r1);

        public abstract Builder setFid(String r1);

        public abstract Builder setRefreshToken(String r1);

        public abstract Builder setResponseCode(ResponseCode r1);

        public abstract Builder setUri(String r1);
    }

    public enum ResponseCode extends Enum<ResponseCode> {
        private static final /* synthetic */ ResponseCode[] $VALUES = null;
        public static final ResponseCode BAD_CONFIG = null;
        public static final ResponseCode OK = null;

        private static /* synthetic */ ResponseCode[] $values() {
            return new ResponseCode[]{OK, BAD_CONFIG};
        }

        static {
            OK = new ResponseCode("OK", 0);
            BAD_CONFIG = new ResponseCode("BAD_CONFIG", 1);
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

    public InstallationResponse() {
    }

    public static Builder builder() {
        return new AutoValue_InstallationResponse.Builder();
    }

    public abstract TokenResult getAuthToken();

    public abstract String getFid();

    public abstract String getRefreshToken();

    public abstract ResponseCode getResponseCode();

    public abstract String getUri();
}
