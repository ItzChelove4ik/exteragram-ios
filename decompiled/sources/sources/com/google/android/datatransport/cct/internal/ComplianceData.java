package com.google.android.datatransport.cct.internal;

import android.util.SparseArray;
import com.google.android.datatransport.cct.internal.AutoValue_ComplianceData;

/* loaded from: classes4.dex */
public abstract class ComplianceData {

    public static abstract class Builder {
        public Builder() {
        }

        public abstract ComplianceData build();

        public abstract Builder setPrivacyContext(ExternalPrivacyContext r1);

        public abstract Builder setProductIdOrigin(ProductIdOrigin r1);
    }

    public enum ProductIdOrigin extends Enum<ProductIdOrigin> {
        private static final /* synthetic */ ProductIdOrigin[] $VALUES = null;
        public static final ProductIdOrigin EVENT_OVERRIDE = null;
        public static final ProductIdOrigin NOT_SET = null;
        private static final SparseArray<ProductIdOrigin> valueMap = null;
        private final int value;

        static {
            ProductIdOrigin r0 = new ProductIdOrigin("NOT_SET", 0, 0);
            NOT_SET = r0;
            ProductIdOrigin r1 = new ProductIdOrigin("EVENT_OVERRIDE", 1, 5);
            EVENT_OVERRIDE = r1;
            $VALUES = new ProductIdOrigin[]{r0, r1};
            SparseArray<ProductIdOrigin> r3 = new SparseArray();
            valueMap = r3;
            r3.put(0, r0);
            r3.put(5, r1);
        }

        ProductIdOrigin(String r1, int r2, int r3) {
            this.value = r3;
        }

        public static ProductIdOrigin valueOf(String r1) {
            return (ProductIdOrigin) Enum.valueOf(ProductIdOrigin.class, r1);
        }

        public static ProductIdOrigin[] values() {
            return (ProductIdOrigin[]) $VALUES.clone();
        }
    }

    public ComplianceData() {
    }

    public static Builder builder() {
        return new AutoValue_ComplianceData.Builder();
    }

    public abstract ExternalPrivacyContext getPrivacyContext();

    public abstract ProductIdOrigin getProductIdOrigin();
}
