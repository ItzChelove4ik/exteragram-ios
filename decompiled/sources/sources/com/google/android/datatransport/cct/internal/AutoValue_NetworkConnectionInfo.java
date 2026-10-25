package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.NetworkConnectionInfo;

/* loaded from: classes4.dex */
final class AutoValue_NetworkConnectionInfo extends NetworkConnectionInfo {
    private final NetworkConnectionInfo.MobileSubtype mobileSubtype;
    private final NetworkConnectionInfo.NetworkType networkType;

    /* renamed from: com.google.android.datatransport.cct.internal.AutoValue_NetworkConnectionInfo$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder extends NetworkConnectionInfo.Builder {
        private NetworkConnectionInfo.MobileSubtype mobileSubtype;
        private NetworkConnectionInfo.NetworkType networkType;

        public Builder() {
        }

        @Override // com.google.android.datatransport.cct.internal.NetworkConnectionInfo.Builder
        public NetworkConnectionInfo build() {
            return new AutoValue_NetworkConnectionInfo(this.networkType, this.mobileSubtype, null);
        }

        @Override // com.google.android.datatransport.cct.internal.NetworkConnectionInfo.Builder
        public NetworkConnectionInfo.Builder setMobileSubtype(NetworkConnectionInfo.MobileSubtype r1) {
            this.mobileSubtype = r1;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.NetworkConnectionInfo.Builder
        public NetworkConnectionInfo.Builder setNetworkType(NetworkConnectionInfo.NetworkType r1) {
            this.networkType = r1;
            return this;
        }
    }

    private AutoValue_NetworkConnectionInfo(NetworkConnectionInfo.NetworkType r1, NetworkConnectionInfo.MobileSubtype r2) {
        this.networkType = r1;
        this.mobileSubtype = r2;
    }

    public /* synthetic */ AutoValue_NetworkConnectionInfo(NetworkConnectionInfo.NetworkType r1, NetworkConnectionInfo.MobileSubtype r2, AnonymousClass1 r3) {
        this(r1, r2);
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof NetworkConnectionInfo) == false) goto L22;
        NetworkConnectionInfo r52 = (NetworkConnectionInfo) r5;
        NetworkConnectionInfo.NetworkType r1 = this.networkType;
        if (r1 != null) goto L13;
        if (r52.getNetworkType() != null) goto L22;
    L14:
        NetworkConnectionInfo.MobileSubtype r4 = this.mobileSubtype;
        if (r4 != null) goto L20;
        if (r52.getMobileSubtype() != null) goto L22;
    L21:
        return true;
    L20:
        if (r4.equals(r52.getMobileSubtype()) == false) goto L22;
    L13:
        if (r1.equals(r52.getNetworkType()) == true) goto L14;
    L22:
        return false;
    }

    @Override // com.google.android.datatransport.cct.internal.NetworkConnectionInfo
    public NetworkConnectionInfo.MobileSubtype getMobileSubtype() {
        return this.mobileSubtype;
    }

    @Override // com.google.android.datatransport.cct.internal.NetworkConnectionInfo
    public NetworkConnectionInfo.NetworkType getNetworkType() {
        return this.networkType;
    }

    public int hashCode() {
        NetworkConnectionInfo.NetworkType r0 = this.networkType;
        int r1 = 0;
        if (r0 != null) goto L5;
        int r02 = 0;
    L6:
        int r03 = (r02 ^ 1000003) * 1000003;
        NetworkConnectionInfo.MobileSubtype r3 = this.mobileSubtype;
        if (r3 == null) goto L11;
        r1 = r3.hashCode();
    L11:
        return r03 ^ r1;
    L5:
        r02 = r0.hashCode();
        goto L6
    }

    public String toString() {
        return "NetworkConnectionInfo{networkType=" + this.networkType + ", mobileSubtype=" + this.mobileSubtype + "}";
    }
}
