package com.android.billingclient.api;

import android.text.TextUtils;
import c.f$$ExternalSyntheticBUOutline1;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public final class UnfetchedProduct {
    private final String jsonString;
    private final String productId;
    private final String productType;
    private final String serializedDocid;
    private final int statusCode;

    public UnfetchedProduct(String r4) {
        this.jsonString = r4;
        JSONObject r0 = new JSONObject(r4);
        this.productId = r0.optString("productId");
        String r42 = r0.optString("type");
        this.productType = r42;
        if (r0.has("statusCode") == false) goto L5;
        int r1 = r0.optInt("statusCode");
    L6:
        this.statusCode = r1;
        if (TextUtils.isEmpty(r42) == true) goto L10;
        this.serializedDocid = r0.optString("serializedDocid");
        return;
    L10:
        f$$ExternalSyntheticBUOutline1.m("Product type cannot be empty.");
        throw null;
    L5:
        r1 = 0;
        goto L6
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof UnfetchedProduct) == true) goto L10;
        return false;
    L10:
        return TextUtils.equals(this.jsonString, ((UnfetchedProduct) r2).jsonString);
    }

    public int hashCode() {
        return this.jsonString.hashCode();
    }

    public String toString() {
        return "UnfetchedProduct{productId='" + this.productId + "', productType='" + this.productType + "', statusCode=" + this.statusCode + "}";
    }
}
