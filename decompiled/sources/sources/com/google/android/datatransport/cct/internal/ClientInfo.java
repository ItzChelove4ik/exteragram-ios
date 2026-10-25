package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.AutoValue_ClientInfo;

/* loaded from: classes4.dex */
public abstract class ClientInfo {

    public static abstract class Builder {
        public Builder() {
        }

        public abstract ClientInfo build();

        public abstract Builder setAndroidClientInfo(AndroidClientInfo r1);

        public abstract Builder setClientType(ClientType r1);
    }

    public enum ClientType extends Enum<ClientType> {
        private static final /* synthetic */ ClientType[] $VALUES = null;
        public static final ClientType ANDROID_FIREBASE = null;
        public static final ClientType UNKNOWN = null;
        private final int value;

        static {
            ClientType r0 = new ClientType("UNKNOWN", 0, 0);
            UNKNOWN = r0;
            ClientType r1 = new ClientType("ANDROID_FIREBASE", 1, 23);
            ANDROID_FIREBASE = r1;
            $VALUES = new ClientType[]{r0, r1};
        }

        ClientType(String r1, int r2, int r3) {
            this.value = r3;
        }

        public static ClientType valueOf(String r1) {
            return (ClientType) Enum.valueOf(ClientType.class, r1);
        }

        public static ClientType[] values() {
            return (ClientType[]) $VALUES.clone();
        }
    }

    public ClientInfo() {
    }

    public static Builder builder() {
        return new AutoValue_ClientInfo.Builder();
    }

    public abstract AndroidClientInfo getAndroidClientInfo();

    public abstract ClientType getClientType();
}
