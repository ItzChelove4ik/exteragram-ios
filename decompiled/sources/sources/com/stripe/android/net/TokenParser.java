package com.stripe.android.net;

import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.android.util.StripeJsonUtils;
import com.stripe.android.util.StripeTextUtils;
import java.util.Date;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public abstract class TokenParser {
    public static Token parseToken(String r10) {
        JSONObject r0 = new JSONObject(r10);
        String r2 = StripeJsonUtils.getString(r0, "id");
        long r3 = r0.getLong("created");
        boolean r102 = r0.getBoolean("livemode");
        String r7 = StripeTextUtils.asTokenType(StripeJsonUtils.getString(r0, "type"));
        Boolean r5 = Boolean.valueOf(r0.getBoolean("used"));
        Card r6 = CardParser.parseCard(r0.getJSONObject("card"));
        return new Token(r2, r102, new Date(r3 * 1000), r5, r6, r7);
    }
}
