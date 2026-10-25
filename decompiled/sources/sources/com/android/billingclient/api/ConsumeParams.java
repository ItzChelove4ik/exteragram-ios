package com.android.billingclient.api;

import c.f$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class ConsumeParams {
    private String zza;

    public static final class Builder {
        private String zza;

        public /* synthetic */ Builder(zzck r1) {
        }

        public ConsumeParams build() {
            String r2 = this.zza;
            if (r2 == null) goto L6;
            ConsumeParams r1 = new ConsumeParams(null);
            ConsumeParams.zza(r1, r2);
            return r1;
        L6:
            f$$ExternalSyntheticBUOutline1.m("Purchase token must be set");
            return null;
        }

        public Builder setPurchaseToken(String r1) {
            this.zza = r1;
            return this;
        }
    }

    public /* synthetic */ ConsumeParams(zzck r1) {
    }

    public static Builder newBuilder() {
        return new Builder(null);
    }

    public static /* bridge */ /* synthetic */ void zza(ConsumeParams r0, String r1) {
        r0.zza = r1;
    }

    public String getPurchaseToken() {
        return this.zza;
    }
}
