package com.android.billingclient.api;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public final class QueryProductDetailsParams {
    private final com.google.android.gms.internal.play_billing.zzbt zza;

    public static class Builder {
        private com.google.android.gms.internal.play_billing.zzbt zza;

        public /* synthetic */ Builder(zzcy r1) {
        }

        public static /* bridge */ /* synthetic */ com.google.android.gms.internal.play_billing.zzbt zza(Builder r0) {
            return r0.zza;
        }

        public QueryProductDetailsParams build() {
            if (this.zza != null) goto L5;
            f$$ExternalSyntheticBUOutline1.m("Product list must be set to a non empty list.");
            return null;
        L5:
            return new QueryProductDetailsParams(this, null);
        }

        public Builder setProductList(List<Product> r7) {
            if (r7 != null) goto L5;
        L18:
            f$$ExternalSyntheticBUOutline1.m("Product list cannot be empty.");
            return null;
        L5:
            if (r7.isEmpty() == true) goto L18;
            HashSet r1 = new HashSet();
            Iterator<Product> r2 = r7.iterator();
        L8:
            if (r2.hasNext() == false) goto L13;
            Product r3 = r2.next();
            if ("play_pass_subs".equals(r3.zzb()) == true) goto L8;
            r1.add(r3.zzb());
            goto L8
        L13:
            if (r1.size() > 1) goto L16;
            this.zza = com.google.android.gms.internal.play_billing.zzbt.zzj(r7);
            return this;
        L16:
            f$$ExternalSyntheticBUOutline1.m("All products should be of the same product type.");
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static class Product {
        private final String zza;
        private final String zzb;

        public static class Builder {
            private String zza;
            private String zzb;

            public /* synthetic */ Builder(zzcy r1) {
            }

            public static /* bridge */ /* synthetic */ String zza(Builder r0) {
                return r0.zza;
            }

            public static /* bridge */ /* synthetic */ String zzb(Builder r0) {
                return r0.zzb;
            }

            public Product build() {
                if ("first_party".equals(this.zzb) == false) goto L5;
                f$$ExternalSyntheticBUOutline1.m("Serialized doc id must be provided for first party products.");
                return null;
            L5:
                if (this.zza != null) goto L7;
                f$$ExternalSyntheticBUOutline1.m("Product id must be provided.");
                return null;
            L7:
                if (this.zzb != null) goto L9;
                f$$ExternalSyntheticBUOutline1.m("Product type must be provided.");
                return null;
            L9:
                return new Product(this, null);
            }

            public Builder setProductId(String r1) {
                this.zza = r1;
                return this;
            }

            public Builder setProductType(String r1) {
                this.zzb = r1;
                return this;
            }
        }

        public /* synthetic */ Product(Builder r1, zzcy r2) {
            this.zza = Builder.zza(r1);
            this.zzb = Builder.zzb(r1);
        }

        public static Builder newBuilder() {
            return new Builder(null);
        }

        public final String zza() {
            return this.zza;
        }

        public final String zzb() {
            return this.zzb;
        }
    }

    public /* synthetic */ QueryProductDetailsParams(Builder r1, zzcy r2) {
        this.zza = Builder.zza(r1);
    }

    public static Builder newBuilder() {
        return new Builder(null);
    }

    public final com.google.android.gms.internal.play_billing.zzbt zza() {
        return this.zza;
    }

    public final String zzb() {
        return ((Product) this.zza.get(0)).zzb();
    }
}
