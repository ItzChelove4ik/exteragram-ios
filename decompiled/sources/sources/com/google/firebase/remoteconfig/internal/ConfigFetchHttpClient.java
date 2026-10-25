package com.google.firebase.remoteconfig.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import androidx.annotation.Keep;
import androidx.core.content.pm.PackageInfoCompat;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.Hex;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigClientException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigServerException;
import com.google.firebase.remoteconfig.internal.ConfigContainer;
import com.google.firebase.remoteconfig.internal.ConfigFetchHandler;
import j$.util.DesugarTimeZone;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ConfigFetchHttpClient {
    private static final Pattern GMP_APP_ID_PATTERN = null;
    private final String apiKey;
    private final String appId;
    private final long connectTimeoutInSeconds;
    private final Context context;
    private final String namespace;
    private final String projectNumber;
    private final long readTimeoutInSeconds;

    static {
        GMP_APP_ID_PATTERN = Pattern.compile("^[^:]+:([0-9]+):(android|ios|web):([0-9a-f]+)");
    }

    public ConfigFetchHttpClient(Context r1, String r2, String r3, String r4, long r5, long r7) {
        this.context = r1;
        this.appId = r2;
        this.apiKey = r3;
        this.projectNumber = extractProjectNumberFromAppId(r2);
        this.namespace = r4;
        this.connectTimeoutInSeconds = r5;
        this.readTimeoutInSeconds = r7;
    }

    private boolean backendHasUpdates(JSONObject r2) {
        return true ^ r2.get("state").equals("NO_CHANGE");
    L9:
        return true;
    }

    private String convertToISOString(long r3) {
        SimpleDateFormat r2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        r2.setTimeZone(DesugarTimeZone.getTimeZone("UTC"));
        return r2.format(Long.valueOf(r3));
    }

    private JSONObject createFetchRequestBody(String r4, String r5, Map<String, String> r6, Long r7, Map<String, String> r8) {
        HashMap r0 = new HashMap();
        if (r4 == null) goto L16;
        r0.put("appInstanceId", r4);
        r0.put("appInstanceIdToken", r5);
        r0.put("appId", this.appId);
        Locale r42 = this.context.getResources().getConfiguration().locale;
        r0.put("countryCode", r42.getCountry());
        r0.put("languageCode", r42.toLanguageTag());
        r0.put("platformVersion", Integer.toString(Build.VERSION.SDK_INT));
        r0.put("timeZone", TimeZone.getDefault().getID());
        PackageInfo r43 = this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0);     // Catch: PackageManager.NameNotFoundException -> L17
        if (r43 == null) goto L8;
        r0.put("appVersion", r43.versionName);     // Catch: PackageManager.NameNotFoundException -> L17
        r0.put("appBuild", Long.toString(PackageInfoCompat.getLongVersionCode(r43)));     // Catch: PackageManager.NameNotFoundException -> L17
    L8:
        r0.put("packageName", this.context.getPackageName());
        r0.put("sdkVersion", "23.1.0");
        r0.put("analyticsUserProperties", new JSONObject(r6));
        if (r8.isEmpty() == true) goto L11;
        r0.put("customSignals", new JSONObject(r8));
        Log.d("FirebaseRemoteConfig", "Keys of custom signals during fetch: " + r8.keySet());
    L11:
        if (r7 == null) goto L14;
        r0.put("firstOpenTime", convertToISOString(r7.longValue()));
    L14:
        return new JSONObject(r0);
    L16:
        throw new FirebaseRemoteConfigClientException("Fetch failed: Firebase installation id is null.");
    }

    private static ConfigContainer extractConfigs(JSONObject r4, Date r5) {
        ConfigContainer.Builder r52 = ConfigContainer.newBuilder().withFetchTime(r5);     // Catch: JSONException -> L31
        JSONArray r1 = null;
        JSONObject r2 = r4.getJSONObject("entries");     // Catch: JSONException -> L7
    L8:
        if (r2 == null) goto L35;
        r52 = r52.replaceConfigsWith(r2);     // Catch: JSONException -> L31
    L35:
        JSONArray r22 = r4.getJSONArray("experimentDescriptions");     // Catch: JSONException -> L12
    L13:
        if (r22 == null) goto L43;
        r52 = r52.withAbtExperiments(r22);     // Catch: JSONException -> L31
    L43:
        JSONObject r23 = r4.getJSONObject("personalizationMetadata");     // Catch: JSONException -> L17
    L18:
        if (r23 == null) goto L21;
        r52 = r52.withPersonalizationMetadata(r23);     // Catch: JSONException -> L31
    L21:
        if (r4.has("templateVersion") == false) goto L23;
        String r0 = r4.getString("templateVersion");     // Catch: JSONException -> L31
    L24:
        if (r0 == null) goto L37;
        r52.withTemplateVersionNumber(Long.parseLong(r0));     // Catch: JSONException -> L31
    L37:
        r1 = r4.getJSONArray("rolloutMetadata");     // Catch: JSONException -> L34
    L27:
        if (r1 == null) goto L29;
        r52 = r52.withRolloutMetadata(r1);     // Catch: JSONException -> L31
    L29:
        return r52.build();
    L23:
        r0 = null;
    L17:
        r23 = null;
    L12:
        r22 = null;
    L7:
        r2 = null;
    L31:
        e = move-exception;
        throw new FirebaseRemoteConfigClientException("Fetch failed: fetch response could not be parsed.", e);
    }

    private static String extractProjectNumberFromAppId(String r1) {
        Matcher r12 = GMP_APP_ID_PATTERN.matcher(r1);
        if (r12.matches() == true) goto L5;
        return null;
    L5:
        return r12.group(1);
    }

    private JSONObject getFetchResponseBody(URLConnection r3) {
        BufferedReader r2 = new BufferedReader(new InputStreamReader(r3.getInputStream(), "utf-8"));
        StringBuilder r32 = new StringBuilder();
    L3:
        int r0 = r2.read();
        if (r0 == (-1)) goto L7;
        r32.append((char) r0);
        goto L3
    L7:
        return new JSONObject(r32.toString());
    }

    private String getFetchUrl(String r1, String r2) {
        return String.format("https://firebaseremoteconfig.googleapis.com/v1/projects/%s/namespaces/%s:fetch", new Object[]{r1, r2});
    }

    private String getFingerprintHashForPackage() {
        Context r3 = this.context;     // Catch: PackageManager.NameNotFoundException -> L7
        byte[] r32 = AndroidUtilsLight.getPackageCertificateHashBytes(r3, r3.getPackageName());     // Catch: PackageManager.NameNotFoundException -> L7
        if (r32 != null) goto L10;
        Log.e("FirebaseRemoteConfig", "Could not get fingerprint hash for package: " + this.context.getPackageName());     // Catch: PackageManager.NameNotFoundException -> L7
        return null;
    L10:
        return Hex.bytesToStringUppercase(r32, false);
    L7:
        e = move-exception;
        Log.e("FirebaseRemoteConfig", "No such package: " + this.context.getPackageName(), e);
        return null;
    }

    private void setCommonRequestHeaders(HttpURLConnection r3, String r4) {
        r3.setRequestProperty("X-Goog-Api-Key", this.apiKey);
        r3.setRequestProperty("X-Android-Package", this.context.getPackageName());
        r3.setRequestProperty("X-Android-Cert", getFingerprintHashForPackage());
        r3.setRequestProperty("X-Google-GFE-Can-Retry", "yes");
        r3.setRequestProperty("X-Goog-Firebase-Installations-Auth", r4);
        r3.setRequestProperty("Content-Type", "application/json");
        r3.setRequestProperty("Accept", "application/json");
    }

    private void setCustomRequestHeaders(HttpURLConnection r2, Map<String, String> r3) {
        Iterator<Map.Entry<String, String>> r1 = r3.entrySet().iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        Map.Entry<String, String> r32 = r1.next();
        r2.setRequestProperty(r32.getKey(), r32.getValue());
        goto L4
    }

    private void setFetchRequestBody(HttpURLConnection r1, byte[] r2) {
        r1.setFixedLengthStreamingMode(r2.length);
        BufferedOutputStream r0 = new BufferedOutputStream(r1.getOutputStream());
        r0.write(r2);
        r0.flush();
        r0.close();
    }

    private void setUpUrlConnection(HttpURLConnection r4, String r5, String r6, Map<String, String> r7) {
        r4.setDoOutput(true);
        long r0 = this.connectTimeoutInSeconds;
        TimeUnit r2 = TimeUnit.SECONDS;
        r4.setConnectTimeout((int) r2.toMillis(r0));
        r4.setReadTimeout((int) r2.toMillis(this.readTimeoutInSeconds));
        r4.setRequestProperty("If-None-Match", r5);
        setCommonRequestHeaders(r4, r6);
        setCustomRequestHeaders(r4, r7);
    }

    public HttpURLConnection createHttpURLConnection() {
        return (HttpURLConnection) new URL(getFetchUrl(this.projectNumber, this.namespace)).openConnection();
    L4:
        e = move-exception;
        throw new FirebaseRemoteConfigException(e.getMessage());
    }

    @Keep
    public ConfigFetchHandler.FetchResponse fetch(HttpURLConnection r2, String r3, String r4, Map<String, String> r5, String r6, Map<String, String> r7, Long r8, Date r9, Map<String, String> r10) {
        setUpUrlConnection(r2, r6, r4, r7);
        setFetchRequestBody(r2, createFetchRequestBody(r3, r4, r5, r8, r10).toString().getBytes("utf-8"));     // Catch: Throwable -> L14 Throwable -> L16 IOException -> L18
        r2.connect();     // Catch: Throwable -> L14 Throwable -> L16 IOException -> L18
        int r1 = r2.getResponseCode();     // Catch: Throwable -> L14 Throwable -> L16 IOException -> L18
        if (r1 != 200) goto L21;
        String r12 = r2.getHeaderField("ETag");     // Catch: Throwable -> L14 Throwable -> L16 IOException -> L18
        JSONObject r42 = getFetchResponseBody(r2);     // Catch: Throwable -> L14 Throwable -> L16 IOException -> L18
        r2.disconnect();
        r2.getInputStream().close();     // Catch: IOException -> L27
    L8:
        ConfigContainer r22 = extractConfigs(r42, r9);
        if (backendHasUpdates(r42) == true) goto L13;
        return ConfigFetchHandler.FetchResponse.forBackendHasNoUpdates(r9, r22);
    L13:
        return ConfigFetchHandler.FetchResponse.forBackendUpdatesFetched(r22, r12);
    L21:
        throw new FirebaseRemoteConfigServerException(r1, r2.getResponseMessage());     // Catch: Throwable -> L14 Throwable -> L16 IOException -> L18
    L14:
        th = move-exception;
        r2.disconnect();
        r2.getInputStream().close();     // Catch: IOException -> L28
        throw th;
    L34:
        throw th;
    L16:
        e = move-exception;
        throw new FirebaseRemoteConfigClientException("The client had an error while calling the backend!", e);     // Catch: Throwable -> L14
    }
}
