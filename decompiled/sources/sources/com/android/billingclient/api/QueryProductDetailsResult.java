package com.android.billingclient.api;

import java.util.List;

/* loaded from: classes4.dex */
public final class QueryProductDetailsResult {
    private final List<ProductDetails> productDetailsList;
    private final List<UnfetchedProduct> unfetchedProductList;

    public QueryProductDetailsResult(List<ProductDetails> r1, List<UnfetchedProduct> r2) {
        this.productDetailsList = r1;
        this.unfetchedProductList = r2;
    }

    public List<ProductDetails> getProductDetailsList() {
        return this.productDetailsList;
    }
}
