package com.stripe.android.net;

import com.stripe.android.util.StripeJsonUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
abstract class ErrorParser {

    public static class StripeError {
        public String charge;
        public String code;
        public String decline_code;
        public String message;
        public String param;
        public String type;

        public StripeError() {
        }
    }

    public static StripeError parseError(String r2) {
        StripeError r0 = new StripeError();
        JSONObject r22 = new JSONObject(r2).getJSONObject("error");     // Catch: JSONException -> L5
        r0.charge = StripeJsonUtils.optString(r22, "charge");     // Catch: JSONException -> L5
        r0.code = StripeJsonUtils.optString(r22, "code");     // Catch: JSONException -> L5
        r0.decline_code = StripeJsonUtils.optString(r22, "decline_code");     // Catch: JSONException -> L5
        r0.message = StripeJsonUtils.optString(r22, "message");     // Catch: JSONException -> L5
        r0.param = StripeJsonUtils.optString(r22, "param");     // Catch: JSONException -> L5
        r0.type = StripeJsonUtils.optString(r22, "type");     // Catch: JSONException -> L5
        return r0;
    L5:
        r0.message = "An improperly formatted error response was found.";
        return r0;
    }
}
