package com.google.android.datatransport;

/* loaded from: classes4.dex */
final class AutoValue_ProductData extends ProductData {
    private final Integer productId;

    public AutoValue_ProductData(Integer r1) {
        this.productId = r1;
    }

    public boolean equals(Object r4) {
        if (r4 != this) goto L6;
        return true;
    L6:
        if ((r4 instanceof ProductData) == false) goto L15;
        ProductData r42 = (ProductData) r4;
        Integer r3 = this.productId;
        if (r3 != null) goto L14;
        if (r42.getProductId() != null) goto L12;
        return true;
    L12:
        return false;
    L14:
        return r3.equals(r42.getProductId());
    L15:
        return false;
    }

    @Override // com.google.android.datatransport.ProductData
    public Integer getProductId() {
        return this.productId;
    }

    public int hashCode() {
        Integer r1 = this.productId;
        if (r1 != null) goto L5;
        int r12 = 0;
    L7:
        return r12 ^ 1000003;
    L5:
        r12 = r1.hashCode();
        goto L7
    }

    public String toString() {
        return "ProductData{productId=" + this.productId + "}";
    }
}
