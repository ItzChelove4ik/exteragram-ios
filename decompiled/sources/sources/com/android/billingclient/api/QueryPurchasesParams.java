package com.android.billingclient.api;

import c.f$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class QueryPurchasesParams {
    private final String zza;

    public static class Builder {
        private String zza;

        public /* synthetic */ Builder(zzda r1) {
        }

        public static /* bridge */ /* synthetic */ String zza(Builder r0) {
            return r0.zza;
        }

        public QueryPurchasesParams build() {
            if (this.zza != null) goto L5;
            f$$ExternalSyntheticBUOutline1.m("Product type must be set");
            return null;
        L5:
            return new QueryPurchasesParams(this, null);
        }

        public Builder setProductType(String r1) {
            this.zza = r1;
            return this;
        }
    }

    public /* synthetic */ QueryPurchasesParams(Builder r1, zzda r2) {
        this.zza = Builder.zza(r1);
    }

    public static Builder newBuilder() {
        return new Builder(null);
    }

    public final String zza() {
        return this.zza;
    }
}
