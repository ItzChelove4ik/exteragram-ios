package com.google.firebase.installations.remote;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.TrafficStats;
import android.text.TextUtils;
import android.util.JsonReader;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.Hex;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.heartbeatinfo.HeartBeatController;
import com.google.firebase.inject.Provider;
import com.google.firebase.installations.FirebaseInstallationsException;
import com.google.firebase.installations.remote.InstallationResponse;
import com.google.firebase.installations.remote.TokenResult;
import com.google.protobuf.UnsafeUtil$$ExternalSyntheticBUOutline0;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;
import okhttp3.internal.url._UrlKt;
import org.json.JSONException;
import org.json.JSONObject;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public class FirebaseInstallationServiceClient {
    private static final Pattern EXPIRATION_TIMESTAMP_PATTERN = null;
    private static final Charset UTF_8 = null;
    private final Context context;
    private final Provider<HeartBeatController> heartBeatProvider;
    private final RequestLimiter requestLimiter;

    static {
        EXPIRATION_TIMESTAMP_PATTERN = Pattern.compile("[0-9]+s");
        UTF_8 = Charset.forName("UTF-8");
    }

    public FirebaseInstallationServiceClient(Context r1, Provider<HeartBeatController> r2) {
        this.context = r1;
        this.heartBeatProvider = r2;
        this.requestLimiter = new RequestLimiter();
    }

    private static String availableFirebaseOptions(String r2, String r3, String r4) {
        if (TextUtils.isEmpty(r2) == false) goto L5;
        String r22 = _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        return String.format("Firebase options used while communicating with Firebase server APIs: %s, %s%s", new Object[]{r3, r4, r22});
    L5:
        r22 = ", " + r2;
        goto L7
    }

    private static JSONObject buildCreateFirebaseInstallationRequestBody(String r2, String r3) {
        JSONObject r0 = new JSONObject();     // Catch: JSONException -> L4
        r0.put("fid", r2);     // Catch: JSONException -> L4
        r0.put("appId", r3);     // Catch: JSONException -> L4
        r0.put("authVersion", "FIS_v2");     // Catch: JSONException -> L4
        r0.put("sdkVersion", "a:19.1.2");     // Catch: JSONException -> L4
        return r0;
    L4:
        e = move-exception;
        UnsafeUtil$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    private static JSONObject buildGenerateAuthTokenRequestBody() {
        JSONObject r0 = new JSONObject();     // Catch: JSONException -> L4
        r0.put("sdkVersion", "a:19.1.2");     // Catch: JSONException -> L4
        JSONObject r1 = new JSONObject();     // Catch: JSONException -> L4
        r1.put("installation", r0);     // Catch: JSONException -> L4
        return r1;
    L4:
        e = move-exception;
        UnsafeUtil$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    private String getFingerprintHashForPackage() {
        Context r3 = this.context;     // Catch: PackageManager.NameNotFoundException -> L7
        byte[] r32 = AndroidUtilsLight.getPackageCertificateHashBytes(r3, r3.getPackageName());     // Catch: PackageManager.NameNotFoundException -> L7
        if (r32 != null) goto L10;
        Log.e("ContentValues", "Could not get fingerprint hash for package: " + this.context.getPackageName());     // Catch: PackageManager.NameNotFoundException -> L7
        return null;
    L10:
        return Hex.bytesToStringUppercase(r32, false);
    L7:
        e = move-exception;
        Log.e("ContentValues", "No such package: " + this.context.getPackageName(), e);
        return null;
    }

    private URL getFullyQualifiedRequestUri(String r4) {
        return new URL(String.format("https://%s/%s/%s", new Object[]{"firebaseinstallations.googleapis.com", "v1", r4}));
    L4:
        e = move-exception;
        throw new FirebaseInstallationsException(e.getMessage(), FirebaseInstallationsException.Status.UNAVAILABLE);
    }

    private static byte[] getJsonBytes(JSONObject r1) {
        return r1.toString().getBytes("UTF-8");
    }

    private static boolean isSuccessfulResponseCode(int r1) {
        if (r1 >= 200) goto L5;
        return false;
    L5:
        if (r1 >= 300) goto L10;
        return true;
    L10:
        return false;
    }

    private static void logBadConfigError() {
        Log.e("Firebase-Installations", "Firebase Installations can not communicate with Firebase server APIs due to invalid configuration. Please update your Firebase initialization process and set valid Firebase options (API key, Project ID, Application ID) when initializing Firebase.");
    }

    private static void logFisCommunicationError(HttpURLConnection r1, String r2, String r3, String r4) {
        String r12 = readErrorResponse(r1);
        if (TextUtils.isEmpty(r12) == true) goto L6;
        Log.w("Firebase-Installations", r12);
        Log.w("Firebase-Installations", availableFirebaseOptions(r2, r3, r4));
        return;
    }

    private HttpURLConnection openHttpURLConnection(URL r5, String r6) {
        HttpURLConnection r52 = (HttpURLConnection) r5.openConnection();     // Catch: IOException -> L16
        r52.setConnectTimeout(10000);
        r52.setUseCaches(false);
        r52.setReadTimeout(10000);
        r52.addRequestProperty("Content-Type", "application/json");
        r52.addRequestProperty("Accept", "application/json");
        r52.addRequestProperty("Content-Encoding", "gzip");
        r52.addRequestProperty("Cache-Control", "no-cache");
        r52.addRequestProperty("X-Android-Package", this.context.getPackageName());
        HeartBeatController r2 = this.heartBeatProvider.get();
        if (r2 != null) goto L20;
    L14:
        r52.addRequestProperty("X-Android-Cert", getFingerprintHashForPackage());
        r52.addRequestProperty("x-goog-api-key", r6);
        return r52;
    L20:
        r52.addRequestProperty("x-firebase-client", (String) Tasks.await(r2.getHeartBeatsHeader()));     // Catch: InterruptedException -> L8 ExecutionException -> L10
    L8:
        e = move-exception;
        Thread.currentThread().interrupt();
        Log.w("ContentValues", "Failed to get heartbeats header", e);
    L10:
        e = move-exception;
        Log.w("ContentValues", "Failed to get heartbeats header", e);
    L17:
        throw new FirebaseInstallationsException("Firebase Installations Service is unavailable. Please try again later.", FirebaseInstallationsException.Status.UNAVAILABLE);
    }

    public static long parseTokenExpirationTimestamp(String r2) {
        Preconditions.checkArgument(EXPIRATION_TIMESTAMP_PATTERN.matcher(r2).matches(), "Invalid Expiration Timestamp.");
        if (r2 != null) goto L5;
        return 0;
    L5:
        if (r2.length() != 0) goto L8;
        return 0;
    L8:
        return Long.parseLong(r2.substring(0, r2.length() - 1));
    }

    private InstallationResponse readCreateResponse(HttpURLConnection r5) {
        InputStream r4 = r5.getInputStream();
        JsonReader r52 = new JsonReader(new InputStreamReader(r4, UTF_8));
        TokenResult.Builder r0 = TokenResult.builder();
        InstallationResponse.Builder r1 = InstallationResponse.builder();
        r52.beginObject();
    L4:
        if (r52.hasNext() == false) goto L28;
        String r2 = r52.nextName();
        if (r2.equals("name") == true) goto L7;
        if (r2.equals("fid") == true) goto L10;
        if (r2.equals("refreshToken") == true) goto L13;
        if (r2.equals("authToken") == true) goto L16;
        r52.skipValue();
        goto L4
    L16:
        r52.beginObject();
    L18:
        if (r52.hasNext() == false) goto L26;
        String r22 = r52.nextName();
        if (r22.equals("token") == true) goto L21;
        if (r22.equals("expiresIn") == true) goto L24;
        r52.skipValue();
        goto L18
    L24:
        r0.setTokenExpirationTimestamp(parseTokenExpirationTimestamp(r52.nextString()));
        goto L18
    L21:
        r0.setToken(r52.nextString());
        goto L18
    L26:
        r1.setAuthToken(r0.build());
        r52.endObject();
        goto L4
    L13:
        r1.setRefreshToken(r52.nextString());
        goto L4
    L10:
        r1.setFid(r52.nextString());
        goto L4
    L7:
        r1.setUri(r52.nextString());
        goto L4
    L28:
        r52.endObject();
        r52.close();
        r4.close();
        return r1.setResponseCode(InstallationResponse.ResponseCode.OK).build();
    }

    private static String readErrorResponse(HttpURLConnection r5) {
        InputStream r0 = r5.getErrorStream();
        if (r0 != null) goto L5;
        return null;
    L5:
        BufferedReader r2 = new BufferedReader(new InputStreamReader(r0, UTF_8));
        StringBuilder r02 = new StringBuilder();     // Catch: Throwable -> L10 IOException -> L17
    L9:
        String r3 = r2.readLine();     // Catch: Throwable -> L10 IOException -> L17
        if (r3 == null) goto L12;
        r02.append(r3);     // Catch: Throwable -> L10 IOException -> L17
        r02.append('\n');     // Catch: Throwable -> L10 IOException -> L17
        goto L9
    L12:
        String r52 = String.format("Error when communicating with the Firebase Installations server API. HTTP response: [%d %s: %s]", new Object[]{Integer.valueOf(r5.getResponseCode()), r5.getResponseMessage(), r02});     // Catch: Throwable -> L10 IOException -> L17
        r2.close();     // Catch: IOException -> L19
    L14:
        return r52;
    L17:
        r2.close();     // Catch: IOException -> L21
    L10:
        th = move-exception;
        r2.close();     // Catch: IOException -> L20
    L16:
        throw th;
    L18:
        return null;
    }

    private TokenResult readGenerateAuthTokenResponse(HttpURLConnection r4) {
        InputStream r3 = r4.getInputStream();
        JsonReader r42 = new JsonReader(new InputStreamReader(r3, UTF_8));
        TokenResult.Builder r0 = TokenResult.builder();
        r42.beginObject();
    L4:
        if (r42.hasNext() == false) goto L12;
        String r1 = r42.nextName();
        if (r1.equals("token") == true) goto L7;
        if (r1.equals("expiresIn") == true) goto L10;
        r42.skipValue();
        goto L4
    L10:
        r0.setTokenExpirationTimestamp(parseTokenExpirationTimestamp(r42.nextString()));
        goto L4
    L7:
        r0.setToken(r42.nextString());
        goto L4
    L12:
        r42.endObject();
        r42.close();
        r3.close();
        return r0.setResponseCode(TokenResult.ResponseCode.OK).build();
    }

    private void writeFIDCreateRequestBodyToOutputStream(HttpURLConnection r1, String r2, String r3) {
        writeRequestBodyToOutputStream(r1, getJsonBytes(buildCreateFirebaseInstallationRequestBody(r2, r3)));
    }

    private void writeGenerateAuthTokenRequestBodyToOutputStream(HttpURLConnection r1) {
        writeRequestBodyToOutputStream(r1, getJsonBytes(buildGenerateAuthTokenRequestBody()));
    }

    private static void writeRequestBodyToOutputStream(URLConnection r1, byte[] r2) {
        OutputStream r12 = r1.getOutputStream();
        if (r12 == null) goto L11;
        GZIPOutputStream r0 = new GZIPOutputStream(r12);
        r0.write(r2);     // Catch: Throwable -> L8
        r0.close();     // Catch: IOException -> L13
        r12.close();     // Catch: IOException -> L13
        return;
    L21:
        return;
    L8:
        th = move-exception;
        r0.close();     // Catch: IOException -> L14
        r12.close();     // Catch: IOException -> L14
    L10:
        throw th;
    L11:
        Model$$ExternalSyntheticBUOutline0.m("Cannot send request to FIS servers. No OutputStream available.");
    }

    public InstallationResponse createFirebaseInstallation(String r8, String r9, String r10, String r11, String r12) {
        if (this.requestLimiter.isRequestAllowed() == false) goto L35;
        URL r0 = getFullyQualifiedRequestUri(String.format("projects/%s/installations", new Object[]{r10}));
        int r2 = 0;
    L6:
        if (r2 > 1) goto L33;
        TrafficStats.setThreadStatsTag(32769);
        HttpURLConnection r4 = openHttpURLConnection(r0, r8);
        r4.setRequestMethod("POST");     // Catch: Throwable -> L11 Throwable -> L36
        r4.setDoOutput(true);     // Catch: Throwable -> L11 Throwable -> L36
        if (r12 == null) goto L13;
        r4.addRequestProperty("x-goog-fis-android-iid-migration-auth", r12);     // Catch: Throwable -> L11 Throwable -> L36
    L13:
        writeFIDCreateRequestBodyToOutputStream(r4, r9, r11);     // Catch: Throwable -> L11 Throwable -> L36
        int r3 = r4.getResponseCode();     // Catch: Throwable -> L11 Throwable -> L36
        this.requestLimiter.setNextRequestTime(r3);     // Catch: Throwable -> L11 Throwable -> L36
        if (isSuccessfulResponseCode(r3) == true) goto L15;
        logFisCommunicationError(r4, r11, r8, r10);     // Catch: Throwable -> L11 Throwable -> L36 Throwable -> L36
        if (r3 == 429) goto L28;
        if (r3 < 500) goto L26;
        if (r3 < 600) goto L25;
    L26:
        logBadConfigError();     // Catch: Throwable -> L11 Throwable -> L36 Throwable -> L36
        InstallationResponse r7 = InstallationResponse.builder().setResponseCode(InstallationResponse.ResponseCode.BAD_CONFIG).build();     // Catch: Throwable -> L11 Throwable -> L36 Throwable -> L36
    L16:
        r4.disconnect();
        TrafficStats.clearThreadStatsTag();
        return r7;
    L28:
        throw new FirebaseInstallationsException("Firebase servers have received too many requests from this client in a short period of time. Please try again later.", FirebaseInstallationsException.Status.TOO_MANY_REQUESTS);     // Catch: Throwable -> L11 Throwable -> L36 Throwable -> L36
    L15:
        r7 = readCreateResponse(r4);     // Catch: Throwable -> L11 Throwable -> L36
    L11:
        th = move-exception;
        r4.disconnect();
        TrafficStats.clearThreadStatsTag();
        throw th;
    L25:
        r4.disconnect();
        TrafficStats.clearThreadStatsTag();
        r2 = r2 + 1;
        goto L6
    L33:
        throw new FirebaseInstallationsException("Firebase Installations Service is unavailable. Please try again later.", FirebaseInstallationsException.Status.UNAVAILABLE);
    L35:
        throw new FirebaseInstallationsException("Firebase Installations Service is unavailable. Please try again later.", FirebaseInstallationsException.Status.UNAVAILABLE);
    }

    public TokenResult generateAuthToken(String r8, String r9, String r10, String r11) {
        if (this.requestLimiter.isRequestAllowed() == false) goto L40;
        URL r92 = getFullyQualifiedRequestUri(String.format("projects/%s/installations/%s/authTokens:generate", new Object[]{r10, r9}));
        int r0 = 0;
    L6:
        if (r0 > 1) goto L38;
        TrafficStats.setThreadStatsTag(32771);
        HttpURLConnection r3 = openHttpURLConnection(r92, r8);
        r3.setRequestMethod("POST");     // Catch: Throwable -> L13 Throwable -> L41
        r3.addRequestProperty("Authorization", "FIS_v2 " + r11);     // Catch: Throwable -> L13 Throwable -> L41
        r3.setDoOutput(true);     // Catch: Throwable -> L13 Throwable -> L41
        writeGenerateAuthTokenRequestBodyToOutputStream(r3);     // Catch: Throwable -> L13 Throwable -> L41
        int r2 = r3.getResponseCode();     // Catch: Throwable -> L13 Throwable -> L41
        this.requestLimiter.setNextRequestTime(r2);     // Catch: Throwable -> L13 Throwable -> L41
        if (isSuccessfulResponseCode(r2) == true) goto L10;
        logFisCommunicationError(r3, null, r8, r10);     // Catch: Throwable -> L13 Throwable -> L41 Throwable -> L41
        if (r2 == 401) goto L32;
        if (r2 == 404) goto L32;
        if (r2 == 429) goto L31;
        if (r2 < 500) goto L29;
        if (r2 < 600) goto L28;
    L29:
        logBadConfigError();     // Catch: Throwable -> L13 Throwable -> L41 Throwable -> L41
        TokenResult r7 = TokenResult.builder().setResponseCode(TokenResult.ResponseCode.BAD_CONFIG).build();     // Catch: Throwable -> L13 Throwable -> L41 Throwable -> L41
    L11:
        r3.disconnect();
        TrafficStats.clearThreadStatsTag();
        return r7;
    L31:
        throw new FirebaseInstallationsException("Firebase servers have received too many requests from this client in a short period of time. Please try again later.", FirebaseInstallationsException.Status.TOO_MANY_REQUESTS);     // Catch: Throwable -> L13 Throwable -> L41 Throwable -> L41
    L32:
        r7 = TokenResult.builder().setResponseCode(TokenResult.ResponseCode.AUTH_ERROR).build();     // Catch: Throwable -> L13 Throwable -> L41 Throwable -> L41
        goto L11
    L10:
        r7 = readGenerateAuthTokenResponse(r3);     // Catch: Throwable -> L13 Throwable -> L41
    L13:
        th = move-exception;
        r3.disconnect();
        TrafficStats.clearThreadStatsTag();
        throw th;
    L28:
        r3.disconnect();
        TrafficStats.clearThreadStatsTag();
        r0 = r0 + 1;
        goto L6
    L38:
        throw new FirebaseInstallationsException("Firebase Installations Service is unavailable. Please try again later.", FirebaseInstallationsException.Status.UNAVAILABLE);
    L40:
        throw new FirebaseInstallationsException("Firebase Installations Service is unavailable. Please try again later.", FirebaseInstallationsException.Status.UNAVAILABLE);
    }
}
