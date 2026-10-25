package com.google.android.datatransport;

/* loaded from: classes4.dex */
public abstract class ProductData {
    public ProductData() {
    }

    public static ProductData withProductId(Integer r1) {
        return new AutoValue_ProductData(r1);
    }

    public abstract Integer getProductId();
}
