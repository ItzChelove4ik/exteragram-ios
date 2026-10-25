package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.ClientInfo;

/* loaded from: classes4.dex */
final class AutoValue_ClientInfo extends ClientInfo {
    private final AndroidClientInfo androidClientInfo;
    private final ClientInfo.ClientType clientType;

    /* renamed from: com.google.android.datatransport.cct.internal.AutoValue_ClientInfo$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder extends ClientInfo.Builder {
        private AndroidClientInfo androidClientInfo;
        private ClientInfo.ClientType clientType;

        public Builder() {
        }

        @Override // com.google.android.datatransport.cct.internal.ClientInfo.Builder
        public ClientInfo build() {
            return new AutoValue_ClientInfo(this.clientType, this.androidClientInfo, null);
        }

        @Override // com.google.android.datatransport.cct.internal.ClientInfo.Builder
        public ClientInfo.Builder setAndroidClientInfo(AndroidClientInfo r1) {
            this.androidClientInfo = r1;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.ClientInfo.Builder
        public ClientInfo.Builder setClientType(ClientInfo.ClientType r1) {
            this.clientType = r1;
            return this;
        }
    }

    private AutoValue_ClientInfo(ClientInfo.ClientType r1, AndroidClientInfo r2) {
        this.clientType = r1;
        this.androidClientInfo = r2;
    }

    public /* synthetic */ AutoValue_ClientInfo(ClientInfo.ClientType r1, AndroidClientInfo r2, AnonymousClass1 r3) {
        this(r1, r2);
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof ClientInfo) == false) goto L22;
        ClientInfo r52 = (ClientInfo) r5;
        ClientInfo.ClientType r1 = this.clientType;
        if (r1 != null) goto L13;
        if (r52.getClientType() != null) goto L22;
    L14:
        AndroidClientInfo r4 = this.androidClientInfo;
        if (r4 != null) goto L20;
        if (r52.getAndroidClientInfo() != null) goto L22;
    L21:
        return true;
    L20:
        if (r4.equals(r52.getAndroidClientInfo()) == false) goto L22;
    L13:
        if (r1.equals(r52.getClientType()) == true) goto L14;
    L22:
        return false;
    }

    @Override // com.google.android.datatransport.cct.internal.ClientInfo
    public AndroidClientInfo getAndroidClientInfo() {
        return this.androidClientInfo;
    }

    @Override // com.google.android.datatransport.cct.internal.ClientInfo
    public ClientInfo.ClientType getClientType() {
        return this.clientType;
    }

    public int hashCode() {
        ClientInfo.ClientType r0 = this.clientType;
        int r1 = 0;
        if (r0 != null) goto L5;
        int r02 = 0;
    L6:
        int r03 = (r02 ^ 1000003) * 1000003;
        AndroidClientInfo r3 = this.androidClientInfo;
        if (r3 == null) goto L11;
        r1 = r3.hashCode();
    L11:
        return r03 ^ r1;
    L5:
        r02 = r0.hashCode();
        goto L6
    }

    public String toString() {
        return "ClientInfo{clientType=" + this.clientType + ", androidClientInfo=" + this.androidClientInfo + "}";
    }
}
