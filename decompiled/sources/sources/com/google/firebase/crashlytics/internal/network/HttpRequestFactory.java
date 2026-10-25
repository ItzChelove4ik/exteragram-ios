package com.google.firebase.crashlytics.internal.network;

import java.util.Map;

/* loaded from: classes.dex */
public class HttpRequestFactory {
    public HttpRequestFactory() {
    }

    public HttpGetRequest buildHttpGetRequest(String r1, Map<String, String> r2) {
        return new HttpGetRequest(r1, r2);
    }
}
