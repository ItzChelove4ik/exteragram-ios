package com.google.firebase.crashlytics.internal.network;

/* loaded from: classes5.dex */
public class HttpResponse {
    private final String body;
    private final int code;

    public HttpResponse(int r1, String r2) {
        this.code = r1;
        this.body = r2;
    }

    public String body() {
        return this.body;
    }

    public int code() {
        return this.code;
    }
}
