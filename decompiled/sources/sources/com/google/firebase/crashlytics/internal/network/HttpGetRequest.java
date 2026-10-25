package com.google.firebase.crashlytics.internal.network;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.concurrency.CrashlyticsWorkers;
import j$.net.URLEncoder;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public class HttpGetRequest {
    private final Map<String, String> headers;
    private final Map<String, String> queryParams;
    private final String url;

    public HttpGetRequest(String r1, Map<String, String> r2) {
        this.url = r1;
        this.queryParams = r2;
        this.headers = new HashMap();
    }

    private String createParamsString(Map<String, String> r6) {
        StringBuilder r5 = new StringBuilder();
        Iterator<Map.Entry<String, String>> r62 = r6.entrySet().iterator();
        Map.Entry<String, String> r0 = r62.next();
        r5.append(r0.getKey());
        r5.append("=");
        if (r0.getValue() == null) goto L5;
        String r02 = URLEncoder.encode(r0.getValue(), "UTF-8");
    L6:
        r5.append(r02);
    L8:
        if (r62.hasNext() == false) goto L15;
        Map.Entry<String, String> r03 = r62.next();
        r5.append("&");
        r5.append(r03.getKey());
        r5.append("=");
        if (r03.getValue() == null) goto L12;
        String r04 = URLEncoder.encode(r03.getValue(), "UTF-8");
    L13:
        r5.append(r04);
        goto L8
    L12:
        r04 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L13
    L15:
        return r5.toString();
    L5:
        r02 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }

    private String createUrlWithParams(String r2, Map<String, String> r3) {
        String r1 = createParamsString(r3);
        if (r1.isEmpty() == false) goto L6;
        return r2;
    L6:
        if (r2.contains("?") == false) goto L13;
        if (r2.endsWith("&") == true) goto L11;
        r1 = "&".concat(r1);
    L11:
        return r2.concat(r1);
    L13:
        return r2 + "?" + r1;
    }

    private String readStream(InputStream r4) {
        BufferedReader r3 = new BufferedReader(new InputStreamReader(r4, "UTF-8"));
        char[] r42 = new char[8192];
        StringBuilder r0 = new StringBuilder();
    L3:
        int r1 = r3.read(r42);
        if (r1 == (-1)) goto L7;
        r0.append(r42, 0, r1);
        goto L3
    L7:
        return r0.toString();
    }

    public HttpResponse execute() {
        CrashlyticsWorkers.checkBlockingThread();
        InputStream r1 = null;
        String r12 = null;
        r1 = null;
        String r2 = createUrlWithParams(this.url, this.queryParams);     // Catch: Throwable -> L21
        Logger.getLogger().v("GET Request URL: " + r2);     // Catch: Throwable -> L21
        HttpsURLConnection r0 = (HttpsURLConnection) new URL(r2).openConnection();     // Catch: Throwable -> L21
        r0.setReadTimeout(10000);     // Catch: Throwable -> L9
        r0.setConnectTimeout(10000);     // Catch: Throwable -> L9
        r0.setRequestMethod("GET");     // Catch: Throwable -> L9
        Iterator<Map.Entry<String, String>> r22 = this.headers.entrySet().iterator();     // Catch: Throwable -> L9
    L7:
        if (r22.hasNext() == false) goto L11;
        Map.Entry<String, String> r3 = r22.next();     // Catch: Throwable -> L9
        r0.addRequestProperty(r3.getKey(), r3.getValue());     // Catch: Throwable -> L9
        goto L7
    L11:
        r0.connect();     // Catch: Throwable -> L9
        int r23 = r0.getResponseCode();     // Catch: Throwable -> L9
        InputStream r32 = r0.getInputStream();     // Catch: Throwable -> L9
        if (r32 != null) goto L28;
    L17:
        if (r32 == null) goto L19;
        r32.close();
    L19:
        r0.disconnect();
        return new HttpResponse(r23, r12);
    L28:
        r12 = readStream(r32);     // Catch: Throwable -> L15
    L15:
        th = th;
        r1 = r32;
    L23:
        if (r1 == null) goto L25;
        r1.close();
    L25:
        if (r0 == null) goto L27;
        r0.disconnect();
    L27:
        throw th;
    L9:
        th = th;
    L21:
        th = th;
        r0 = null;
        goto L23
    }

    public HttpGetRequest header(String r2, String r3) {
        this.headers.put(r2, r3);
        return this;
    }
}
