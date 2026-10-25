package com.stripe.android.net;

import com.stripe.android.model.Card;
import com.stripe.android.util.StripeJsonUtils;
import com.stripe.android.util.StripeTextUtils;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public abstract class CardParser {
    public static Card parseCard(JSONObject r18) {
        return new Card(null, Integer.valueOf(r18.getInt("exp_month")), Integer.valueOf(r18.getInt("exp_year")), null, StripeJsonUtils.optString(r18, "name"), StripeJsonUtils.optString(r18, "address_line1"), StripeJsonUtils.optString(r18, "address_line2"), StripeJsonUtils.optString(r18, "address_city"), StripeJsonUtils.optString(r18, "address_state"), StripeJsonUtils.optString(r18, "address_zip"), StripeJsonUtils.optString(r18, "address_country"), StripeTextUtils.asCardBrand(StripeJsonUtils.optString(r18, "brand")), StripeJsonUtils.optString(r18, "last4"), StripeJsonUtils.optString(r18, "fingerprint"), StripeTextUtils.asFundingType(StripeJsonUtils.optString(r18, "funding")), StripeJsonUtils.optString(r18, "country"), StripeJsonUtils.optString(r18, "currency"));
    }
}
