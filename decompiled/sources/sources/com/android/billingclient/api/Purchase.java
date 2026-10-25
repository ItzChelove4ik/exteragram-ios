package com.android.billingclient.api;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class Purchase {
    private final String zza;
    private final String zzb;
    private final JSONObject zzc;

    public Purchase(String r1, String r2) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = new JSONObject(r1);
    }

    private final ArrayList zza() {
        ArrayList r0 = new ArrayList();
        JSONObject r3 = this.zzc;
        if (r3.has("productIds") == false) goto L11;
        JSONArray r32 = r3.optJSONArray("productIds");
        if (r32 == null) goto L13;
        int r1 = 0;
    L8:
        if (r1 >= r32.length()) goto L13;
        r0.add(r32.optString(r1));
        r1 = r1 + 1;
    L13:
        return r0;
    L11:
        if (r3.has("productId") == false) goto L13;
        r0.add(r3.optString("productId"));
        goto L13
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof Purchase) == true) goto L8;
        return false;
    L8:
        Purchase r52 = (Purchase) r5;
        if (TextUtils.equals(this.zza, r52.getOriginalJson()) == true) goto L11;
    L13:
        return false;
    L11:
        if (TextUtils.equals(this.zzb, r52.getSignature()) == false) goto L13;
        return true;
    }

    public AccountIdentifiers getAccountIdentifiers() {
        JSONObject r2 = this.zzc;
        String r0 = r2.optString("obfuscatedAccountId");
        String r22 = r2.optString("obfuscatedProfileId");
        if (r0 != null) goto L8;
        if (r22 != null) goto L8;
        return null;
    L8:
        return new AccountIdentifiers(r0, r22);
    }

    public String getOrderId() {
        String r1 = this.zzc.optString("orderId");
        if (TextUtils.isEmpty(r1) == false) goto L6;
        return null;
    L6:
        return r1;
    }

    public String getOriginalJson() {
        return this.zza;
    }

    public List<String> getProducts() {
        return zza();
    }

    public int getPurchaseState() {
        if (this.zzc.optInt("purchaseState", 1) == 4) goto L5;
        return 1;
    L5:
        return 2;
    }

    public String getPurchaseToken() {
        JSONObject r2 = this.zzc;
        return r2.optString("token", r2.optString("purchaseToken"));
    }

    public String getSignature() {
        return this.zzb;
    }

    public int hashCode() {
        return this.zza.hashCode();
    }

    public boolean isAcknowledged() {
        return this.zzc.optBoolean("acknowledged", true);
    }

    public String toString() {
        return "Purchase. Json: ".concat(String.valueOf(this.zza));
    }
}
