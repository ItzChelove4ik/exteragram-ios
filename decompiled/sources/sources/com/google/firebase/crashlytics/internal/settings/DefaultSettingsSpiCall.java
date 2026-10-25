package com.google.firebase.crashlytics.internal.settings;

import android.text.TextUtils;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CrashlyticsCore;
import com.google.firebase.crashlytics.internal.concurrency.CrashlyticsWorkers;
import com.google.firebase.crashlytics.internal.network.HttpGetRequest;
import com.google.firebase.crashlytics.internal.network.HttpRequestFactory;
import com.google.firebase.crashlytics.internal.network.HttpResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
class DefaultSettingsSpiCall implements SettingsSpiCall {
    private final Logger logger;
    private final HttpRequestFactory requestFactory;
    private final String url;

    public DefaultSettingsSpiCall(String r2, HttpRequestFactory r3) {
        this(r2, r3, Logger.getLogger());
    }

    public DefaultSettingsSpiCall(String r1, HttpRequestFactory r2, Logger r3) {
        if (r1 == null) goto L6;
        this.logger = r3;
        this.requestFactory = r2;
        this.url = r1;
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("url must not be null.");
        throw null;
    }

    private HttpGetRequest applyHeadersTo(HttpGetRequest r3, SettingsRequest r4) {
        applyNonNullHeader(r3, "X-CRASHLYTICS-GOOGLE-APP-ID", r4.googleAppId);
        applyNonNullHeader(r3, "X-CRASHLYTICS-API-CLIENT-TYPE", "android");
        applyNonNullHeader(r3, "X-CRASHLYTICS-API-CLIENT-VERSION", CrashlyticsCore.getVersion());
        applyNonNullHeader(r3, "Accept", "application/json");
        applyNonNullHeader(r3, "X-CRASHLYTICS-DEVICE-MODEL", r4.deviceModel);
        applyNonNullHeader(r3, "X-CRASHLYTICS-OS-BUILD-VERSION", r4.osBuildVersion);
        applyNonNullHeader(r3, "X-CRASHLYTICS-OS-DISPLAY-VERSION", r4.osDisplayVersion);
        applyNonNullHeader(r3, "X-CRASHLYTICS-INSTALLATION-ID", r4.installIdProvider.getInstallIds().getCrashlyticsInstallId());
        return r3;
    }

    private void applyNonNullHeader(HttpGetRequest r1, String r2, String r3) {
        if (r3 == null) goto L5;
        r1.header(r2, r3);
        return;
    }

    private JSONObject getJsonObjectFrom(String r5) {
        return new JSONObject(r5);
    L4:
        e = move-exception;
        this.logger.w("Failed to parse settings JSON from " + this.url, e);
        this.logger.w("Settings response " + r5);
        return null;
    }

    private Map<String, String> getQueryParamsFor(SettingsRequest r3) {
        HashMap r2 = new HashMap();
        r2.put("build_version", r3.buildVersion);
        r2.put("display_version", r3.displayVersion);
        r2.put("source", Integer.toString(r3.source));
        String r32 = r3.instanceId;
        if (TextUtils.isEmpty(r32) == true) goto L5;
        r2.put("instance", r32);
    L5:
        return r2;
    }

    public HttpGetRequest createHttpGetRequest(Map<String, String> r2) {
        return this.requestFactory.buildHttpGetRequest(this.url, r2).header("User-Agent", "Crashlytics Android SDK/" + CrashlyticsCore.getVersion()).header("X-CRASHLYTICS-DEVELOPER-TOKEN", "470fa2b4ae81cd56ecbcda9735803434cec591fa");
    }

    public JSONObject handleResponse(HttpResponse r5) {
        int r0 = r5.code();
        this.logger.v("Settings response code was: " + r0);
        if (requestWasSuccessful(r0) == true) goto L5;
        this.logger.e("Settings request failed; (status: " + r0 + ") from " + this.url);
        return null;
    L5:
        return getJsonObjectFrom(r5.body());
    }

    @Override // com.google.firebase.crashlytics.internal.settings.SettingsSpiCall
    public JSONObject invoke(SettingsRequest r6, boolean r7) {
        CrashlyticsWorkers.checkBlockingThread();
        if (r7 == true) goto L11;
        GlShader$$ExternalSyntheticBUOutline1.m("An invalid data collection token was used.");
        return null;
    L11:
        Map<String, String> r72 = getQueryParamsFor(r6);     // Catch: IOException -> L6
        HttpGetRequest r62 = applyHeadersTo(createHttpGetRequest(r72), r6);     // Catch: IOException -> L6
        this.logger.d("Requesting settings from " + this.url);     // Catch: IOException -> L6
        this.logger.v("Settings query params were: " + r72);     // Catch: IOException -> L6
        return handleResponse(r62.execute());
    L6:
        e = move-exception;
        this.logger.e("Settings request failed.", e);
        return null;
    }

    public boolean requestWasSuccessful(int r1) {
        if (r1 != 200) goto L5;
        return true;
    L5:
        if (r1 != 201) goto L7;
        return true;
    L7:
        if (r1 != 202) goto L9;
        return true;
    L9:
        if (r1 == 203) goto L17;
        return false;
    L17:
        return true;
    }
}
