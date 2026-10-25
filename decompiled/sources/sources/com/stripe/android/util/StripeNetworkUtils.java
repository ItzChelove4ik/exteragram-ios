package com.stripe.android.util;

import com.stripe.android.model.Card;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes5.dex */
public abstract class StripeNetworkUtils {
    public static Map<String, Object> hashMapFromCard(Card r4) {
        HashMap r0 = new HashMap();
        HashMap r1 = new HashMap();
        r1.put("number", StripeTextUtils.nullIfBlank(r4.getNumber()));
        r1.put("cvc", StripeTextUtils.nullIfBlank(r4.getCVC()));
        r1.put("exp_month", r4.getExpMonth());
        r1.put("exp_year", r4.getExpYear());
        r1.put("name", StripeTextUtils.nullIfBlank(r4.getName()));
        r1.put("currency", StripeTextUtils.nullIfBlank(r4.getCurrency()));
        r1.put("address_line1", StripeTextUtils.nullIfBlank(r4.getAddressLine1()));
        r1.put("address_line2", StripeTextUtils.nullIfBlank(r4.getAddressLine2()));
        r1.put("address_city", StripeTextUtils.nullIfBlank(r4.getAddressCity()));
        r1.put("address_zip", StripeTextUtils.nullIfBlank(r4.getAddressZip()));
        r1.put("address_state", StripeTextUtils.nullIfBlank(r4.getAddressState()));
        r1.put("address_country", StripeTextUtils.nullIfBlank(r4.getAddressCountry()));
        Iterator r42 = new HashSet(r1.keySet()).iterator();
    L4:
        if (r42.hasNext() == false) goto L8;
        String r2 = (String) r42.next();
        if (r1.get(r2) != null) goto L4;
        r1.remove(r2);
        goto L4
    L8:
        r0.put("card", r1);
        return r0;
    }
}
