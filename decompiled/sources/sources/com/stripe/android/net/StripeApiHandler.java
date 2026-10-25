package com.stripe.android.net;

import com.stripe.android.exception.APIConnectionException;
import com.stripe.android.exception.APIException;
import com.stripe.android.exception.AuthenticationException;
import com.stripe.android.exception.CardException;
import com.stripe.android.exception.InvalidRequestException;
import com.stripe.android.exception.PermissionException;
import com.stripe.android.exception.RateLimitException;
import com.stripe.android.model.Token;
import com.stripe.android.net.ErrorParser;
import j$.net.URLEncoder;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Security;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.internal.url._UrlKt;
import org.json.JSONException;
import org.json.JSONObject;
import org.mvel2.MVEL;

/* loaded from: classes5.dex */
public abstract class StripeApiHandler {
    private static final SSLSocketFactory SSL_SOCKET_FACTORY = null;

    public static final class Parameter {
        public final String key;
        public final String value;

        public Parameter(String r1, String r2) {
            this.key = r1;
            this.value = r2;
        }
    }

    static {
        SSL_SOCKET_FACTORY = new StripeSSLSocketFactory();
    }

    private static HttpURLConnection createGetConnection(String r0, String r1, RequestOptions r2) {
        HttpURLConnection r02 = createStripeConnection(formatURL(r0, r1), r2);
        r02.setRequestMethod("GET");
        return r02;
    }

    private static HttpURLConnection createPostConnection(String r2, String r3, RequestOptions r4) {
        HttpURLConnection r22 = createStripeConnection(r2, r4);
        r22.setDoOutput(true);
        r22.setRequestMethod("POST");
        r22.setRequestProperty("Content-Type", String.format("application/x-www-form-urlencoded;charset=%s", new Object[]{"UTF-8"}));
        OutputStream r0 = r22.getOutputStream();     // Catch: Throwable -> L9
        r0.write(r3.getBytes("UTF-8"));     // Catch: Throwable -> L7
        r0.close();
        return r22;
    L7:
        th = th;
    L11:
        if (r0 == null) goto L13;
        r0.close();
    L13:
        throw th;
    L9:
        th = th;
        r0 = null;
        goto L11
    }

    public static String createQuery(Map<String, Object> r3) {
        StringBuilder r0 = new StringBuilder();
        Iterator<Parameter> r32 = flattenParams(r3).iterator();
    L4:
        if (r32.hasNext() == false) goto L10;
        if (r0.length() <= 0) goto L8;
        r0.append("&");
    L8:
        Parameter r1 = r32.next();
        r0.append(urlEncodePair(r1.key, r1.value));
        goto L4
    L10:
        return r0.toString();
    }

    private static HttpURLConnection createStripeConnection(String r2, RequestOptions r3) {
        HttpURLConnection r22 = (HttpURLConnection) new URL(r2).openConnection();
        r22.setConnectTimeout(30000);
        r22.setReadTimeout(80000);
        r22.setUseCaches(false);
        Iterator<Map.Entry<String, String>> r32 = getHeaders(r3).entrySet().iterator();
    L4:
        if (r32.hasNext() == false) goto L7;
        Map.Entry<String, String> r0 = r32.next();
        r22.setRequestProperty(r0.getKey(), r0.getValue());
        goto L4
    L7:
        if ((r22 instanceof HttpsURLConnection) == false) goto L9;
        ((HttpsURLConnection) r22).setSSLSocketFactory(SSL_SOCKET_FACTORY);
    L9:
        return r22;
    }

    public static Token createToken(Map<String, Object> r2, RequestOptions r3) {
        return requestToken("POST", getApiUrl(), r2, r3);
    }

    private static List<Parameter> flattenParams(Map<String, Object> r1) {
        return flattenParamsMap(r1, null);
    }

    private static List<Parameter> flattenParamsList(List<Object> r4, String r5) {
        LinkedList r0 = new LinkedList();
        Iterator<Object> r1 = r4.iterator();
        String r2 = String.format("%s[]", new Object[]{r5});
        if (r4.isEmpty() == false) goto L7;
        r0.add(new Parameter(r5, _UrlKt.FRAGMENT_ENCODE_SET));
        return r0;
    L7:
        if (r1.hasNext() == false) goto L9;
        r0.addAll(flattenParamsValue(r1.next(), r2));
        goto L7
    L9:
        return r0;
    }

    private static List<Parameter> flattenParamsMap(Map<String, Object> r4, String r5) {
        LinkedList r0 = new LinkedList();
        if (r4 == null) goto L12;
        Iterator<Map.Entry<String, Object>> r42 = r4.entrySet().iterator();
    L7:
        if (r42.hasNext() == false) goto L12;
        Map.Entry<String, Object> r1 = r42.next();
        String r2 = r1.getKey();
        Object r12 = r1.getValue();
        if (r5 == null) goto L11;
        r2 = String.format("%s[%s]", new Object[]{r5, r2});
    L11:
        r0.addAll(flattenParamsValue(r12, r2));
    L12:
        return r0;
    }

    private static List<Parameter> flattenParamsValue(Object r8, String r9) {
        if ((r8 instanceof Map) == false) goto L7;
        return flattenParamsMap((Map) r8, r9);
    L7:
        if ((r8 instanceof List) == false) goto L11;
        return flattenParamsList((List) r8, r9);
    L11:
        if (_UrlKt.FRAGMENT_ENCODE_SET.equals(r8) == true) goto L18;
        if (r8 != null) goto L15;
        LinkedList r82 = new LinkedList();
        r82.add(new Parameter(r9, _UrlKt.FRAGMENT_ENCODE_SET));
        return r82;
    L15:
        LinkedList r0 = new LinkedList();
        r0.add(new Parameter(r9, r8.toString()));
        return r0;
    L18:
        throw new InvalidRequestException("You cannot set '" + r9 + "' to an empty string. We interpret empty strings as null in requests. You may set '" + r9 + "' to null to delete the property.", r9, null, 0, null);
    }

    private static String formatURL(String r2, String r3) {
        if (r3 != null) goto L4;
        return r2;
    L4:
        if (r3.isEmpty() == true) goto L12;
        String r0 = "?";
        if (r2.contains("?") == false) goto L10;
        r0 = "&";
    L10:
        return String.format("%s%s%s", new Object[]{r2, r0, r3});
    L12:
        return r2;
    }

    public static String getApiUrl() {
        return String.format("%s/v1/%s", new Object[]{"https://api.stripe.com", "tokens"});
    }

    public static Map<String, String> getHeaders(RequestOptions r12) {
        HashMap r0 = new HashMap();
        String r1 = r12.getApiVersion();
        r0.put("Accept-Charset", "UTF-8");
        r0.put("Accept", "application/json");
        r0.put("User-Agent", String.format("Stripe/v1 JavaBindings/%s", new Object[]{"3.5.0"}));
        r0.put("Authorization", String.format("Bearer %s", new Object[]{r12.getPublishableApiKey()}));
        String[] r3 = {"os.name", "os.version", "os.arch", "java.version", "java.vendor", "java.vm.version", "java.vm.vendor"};
        HashMap r4 = new HashMap();
        int r5 = 0;
    L4:
        if (r5 >= 7) goto L6;
        String r6 = r3[r5];
        r4.put(r6, System.getProperty(r6));
        r5 = r5 + 1;
        goto L4
    L6:
        r4.put("bindings.version", "3.5.0");
        r4.put("lang", "Java");
        r4.put("publisher", "Stripe");
        r0.put("X-Stripe-Client-User-Agent", new JSONObject(r4).toString());
        if (r1 == null) goto L10;
        r0.put("Stripe-Version", r1);
    L10:
        if (r12.getIdempotencyKey() == null) goto L12;
        r0.put("Idempotency-Key", r12.getIdempotencyKey());
    L12:
        return r0;
    }

    private static String getResponseBody(InputStream r2) {
        String r0 = new Scanner(r2, "UTF-8").useDelimiter("\\A").next();
        r2.close();
        return r0;
    }

    private static StripeResponse getStripeResponse(String r6, String r7, Map<String, Object> r8, RequestOptions r9) {
        return makeURLConnectionRequest(r6, r7, createQuery(r8), r9);
    L5:
        e = move-exception;
        throw new InvalidRequestException("Unable to encode parameters to UTF-8. Please contact support@stripe.com for assistance.", null, null, 0, e);
    }

    private static void handleAPIError(String r12, int r13, String r14) {
        ErrorParser.StripeError r122 = ErrorParser.parseError(r12);
        if (r13 == 429) goto L18;
        switch(r13) {
            case 400: goto L16;
            case 401: goto L14;
            case 402: goto L12;
            case 403: goto L10;
            case 404: goto L8;
            default: goto L6;
        };
    L6:
        throw new APIException(r122.message, r14, Integer.valueOf(r13), null);
    L8:
        throw new InvalidRequestException(r122.message, r122.param, r14, Integer.valueOf(r13), null);
    L10:
        throw new PermissionException(r122.message, r14, Integer.valueOf(r13));
    L12:
        throw new CardException(r122.message, r14, r122.code, r122.param, r122.decline_code, r122.charge, Integer.valueOf(r13), null);
    L14:
        throw new AuthenticationException(r122.message, r14, Integer.valueOf(r13));
    L16:
        throw new InvalidRequestException(r122.message, r122.param, r14, Integer.valueOf(r13), null);
    L18:
        throw new RateLimitException(r122.message, r122.param, r14, Integer.valueOf(r13), null);
    }

    private static StripeResponse makeURLConnectionRequest(String r3, String r4, String r5, RequestOptions r6) {
        HttpURLConnection r0 = null;
        int r1 = r3.hashCode();     // Catch: Throwable -> L11 IOException -> L13
        if (r1 == 70454) goto L16;
        if (r1 != 2461856) goto L28;
        if (r3.equals("POST") == false) goto L28;
        HttpURLConnection r32 = createPostConnection(r4, r5, r6);     // Catch: Throwable -> L11 IOException -> L13
    L10:
        HttpURLConnection r02 = r32;
        int r33 = r02.getResponseCode();     // Catch: Throwable -> L11 IOException -> L13
        if (r33 >= 200) goto L21;
    L23:
        String r42 = getResponseBody(r02.getErrorStream());     // Catch: Throwable -> L11 IOException -> L13
    L24:
        StripeResponse r62 = new StripeResponse(r33, r42, r02.getHeaderFields());     // Catch: Throwable -> L11 IOException -> L13
        r02.disconnect();
        return r62;
    L21:
        if (r33 >= 300) goto L23;
        r42 = getResponseBody(r02.getInputStream());     // Catch: Throwable -> L11 IOException -> L13
    L28:
        throw new APIConnectionException(String.format("Unrecognized HTTP method %s. This indicates a bug in the Stripe bindings. Please contact support@stripe.com for assistance.", new Object[]{r3}));     // Catch: Throwable -> L11 IOException -> L13
    L16:
        if (r3.equals("GET") == false) goto L28;
        r32 = createGetConnection(r4, r5, r6);     // Catch: Throwable -> L11 IOException -> L13
    L13:
        e = move-exception;
        throw new APIConnectionException(String.format("IOException during API request to Stripe (%s): %s Please check your internet connection and try again. If this problem persists, you should check Stripe's service status at https://twitter.com/stripestatus, or let us know at support@stripe.com.", new Object[]{getApiUrl(), e.getMessage()}), e);     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        if (0 == 0) goto L33;
        r0.disconnect();
    L33:
        throw th;
    }

    private static Token requestToken(String r7, String r8, Map<String, Object> r9, RequestOptions r10) {
        if (r10 != null) goto L5;
        return null;
    L5:
        Boolean r3 = Boolean.TRUE;
        String r4 = Security.getProperty("networkaddress.cache.ttl");     // Catch: SecurityException -> L9
        Security.setProperty("networkaddress.cache.ttl", MVEL.VERSION_SUB);     // Catch: SecurityException -> L50
    L12:
        if (r10.getPublishableApiKey().trim().isEmpty() == true) goto L49;
        StripeResponse r72 = getStripeResponse(r7, r8, r9, r10);     // Catch: Throwable -> L21 JSONException -> L42
        int r82 = r72.getResponseCode();     // Catch: Throwable -> L21 JSONException -> L42
        String r92 = r72.getResponseBody();     // Catch: Throwable -> L21 JSONException -> L42
        Map<String, List<String>> r73 = r72.getResponseHeaders();     // Catch: Throwable -> L21 JSONException -> L42
        if (r73 != null) goto L16;
        List<String> r74 = null;
    L17:
        if (r74 != null) goto L19;
    L23:
        String r75 = null;
    L25:
        if (r82 >= 200) goto L27;
    L28:
        handleAPIError(r92, r82, r75);     // Catch: Throwable -> L21 JSONException -> L42
    L29:
        Token r76 = TokenParser.parseToken(r92);     // Catch: Throwable -> L21 JSONException -> L42
        goto L43
    L31:
        if (r3.booleanValue() == false) goto L35;
        if (r4 != null) goto L34;
        Security.setProperty("networkaddress.cache.ttl", "-1");
        goto L35
    L34:
        Security.setProperty("networkaddress.cache.ttl", r4);
    L35:
        return r76;
    L27:
        if (r82 < 300) goto L29;
    L19:
        if (r74.size() <= 0) goto L23;
        r75 = r74.get(0);     // Catch: Throwable -> L21 JSONException -> L42
        goto L25
    L16:
        r74 = r73.get("Request-Id");     // Catch: Throwable -> L21 JSONException -> L42
    L21:
        th = move-exception;
        if (r3.booleanValue() == false) goto L41;
        if (r4 != null) goto L40;
        Security.setProperty("networkaddress.cache.ttl", "-1");
        goto L41
    L40:
        Security.setProperty("networkaddress.cache.ttl", r4);
    L41:
        throw th;
    L43:
        if (r3.booleanValue() == false) goto L47;
        if (r4 != null) goto L46;
        Security.setProperty("networkaddress.cache.ttl", "-1");
        goto L47
    L46:
        Security.setProperty("networkaddress.cache.ttl", r4);
    L47:
        return null;
    L49:
        throw new AuthenticationException("No API key provided. (HINT: set your API key using 'Stripe.apiKey = <API-KEY>'. You can generate API keys from the Stripe web interface. See https://stripe.com/api for details or email support@stripe.com if you have questions.", null, 0);
    L10:
        r3 = Boolean.FALSE;
    L9:
        r4 = null;
        goto L10
    }

    private static String urlEncode(String r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        return URLEncoder.encode(r1, "UTF-8");
    }

    private static String urlEncodePair(String r0, String r1) {
        return String.format("%s=%s", new Object[]{urlEncode(r0), urlEncode(r1)});
    }
}
