package com.android.billingclient.api;

import c.f$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public final class PendingPurchasesParams {
    private final boolean enableOneTimeProducts;
    private final boolean enablePrepaidPlans;

    public static final class Builder {
        private boolean enableOneTimeProducts;
        private boolean enablePrepaidPlans;

        private Builder() {
        }

        public /* synthetic */ Builder(zzcr r1) {
            this();
        }

        public PendingPurchasesParams build() {
            if (this.enableOneTimeProducts == true) goto L5;
            f$$ExternalSyntheticBUOutline1.m("Pending purchases for one-time products must be supported.");
            return null;
        L5:
            return new PendingPurchasesParams(true, this.enablePrepaidPlans, null);
        }

        public Builder enableOneTimeProducts() {
            this.enableOneTimeProducts = true;
            return this;
        }
    }

    private PendingPurchasesParams(boolean r1, boolean r2) {
        this.enableOneTimeProducts = r1;
        this.enablePrepaidPlans = r2;
    }

    public /* synthetic */ PendingPurchasesParams(boolean r1, boolean r2, zzcr r3) {
        this(r1, r2);
    }

    public static Builder newBuilder() {
        return new Builder(null);
    }

    public boolean isEnabledForOneTimeProducts() {
        return this.enableOneTimeProducts;
    }

    public boolean isEnabledForPrepaidPlans() {
        return this.enablePrepaidPlans;
    }
}
