package com.google.firebase.remoteconfig.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.Hex;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.installations.InstallationTokenResult;
import com.google.firebase.remoteconfig.ConfigUpdate;
import com.google.firebase.remoteconfig.ConfigUpdateListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigClientException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigServerException;
import com.google.firebase.remoteconfig.internal.ConfigSharedPrefsClient;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ConfigRealtimeHttpClient {
    static final int[] BACKOFF_TIME_DURATIONS_IN_MINUTES = null;
    private static final Pattern GMP_APP_ID_PATTERN = null;
    private final int ORIGINAL_RETRIES;
    ConfigCacheClient activatedCache;
    private final Object backgroundLock;
    private final Clock clock;
    private ConfigAutoFetch configAutoFetch;
    private final ConfigFetchHandler configFetchHandler;
    private final Context context;
    private final FirebaseApp firebaseApp;
    private final FirebaseInstallationsApi firebaseInstallations;
    private int httpRetriesRemaining;
    private HttpURLConnection httpURLConnection;
    private boolean isHttpConnectionRunning;
    private boolean isInBackground;
    private boolean isRealtimeDisabled;
    private final Set<ConfigUpdateListener> listeners;
    private final String namespace;
    private final Random random;
    private final ScheduledExecutorService scheduledExecutorService;
    private final ConfigSharedPrefsClient sharedPrefsClient;

    /* renamed from: com.google.firebase.remoteconfig.internal.ConfigRealtimeHttpClient$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ ConfigRealtimeHttpClient this$0;

        public AnonymousClass1(ConfigRealtimeHttpClient r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.beginRealtimeHttpStream();
        }
    }

    /* renamed from: com.google.firebase.remoteconfig.internal.ConfigRealtimeHttpClient$2, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass2 implements ConfigUpdateListener {
        final /* synthetic */ ConfigRealtimeHttpClient this$0;

        public AnonymousClass2(ConfigRealtimeHttpClient r1) {
            this.this$0 = r1;
        }

        @Override // com.google.firebase.remoteconfig.ConfigUpdateListener
        public void onError(FirebaseRemoteConfigException r2) {
            ConfigRealtimeHttpClient.access$000(this.this$0);
            ConfigRealtimeHttpClient.access$100(this.this$0, r2);
        }

        @Override // com.google.firebase.remoteconfig.ConfigUpdateListener
        public void onUpdate(ConfigUpdate r1) {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Task $r8$lambda$5ldnYuFP0_42QqI3IUPNf7Ezrnw(ConfigRealtimeHttpClient r10, Task r11, Task r12) {
        r10.getClass();
        boolean r1 = true;
    L43:
        e = e;
        InputStream r112 = null;
        InputStream r5 = null;
    L40:
        Integer r7 = r5;
    L48:
        if (r10.isInBackground == false) goto L50;
        r10.resetRetryCount();     // Catch: Throwable -> L11
    L51:
        r10.closeRealtimeHttpConnection(r112, r5);
        r10.setIsHttpConnectionRunning(false);
        if (r10.isInBackground == true) goto L57;
        if (r7 != 0) goto L55;
    L58:
        if (r1 == false) goto L60;
        r10.updateBackoffMetadataWithLastFailedStreamConnectionTime(new Date(r10.clock.currentTimeMillis()));
    L60:
        if (r1 == false) goto L62;
    L31:
        r10.retryHttpConnectionWhenBackoffEnds();
    L68:
        r10.httpURLConnection = null;
        r10.configAutoFetch = null;
        return Tasks.forResult(null);
    L62:
        if (r7.intValue() == 200) goto L31;
        String r113 = String.format("Unable to connect to the server. Try again in a few minutes. HTTP status code: %d", new Object[]{r7});
        if (r7.intValue() != 403) goto L67;
        r113 = r10.parseForbiddenErrorResponseMessage(r10.httpURLConnection.getErrorStream());
    L67:
        FirebaseRemoteConfigServerException r122 = new FirebaseRemoteConfigServerException(r7.intValue(), r113, FirebaseRemoteConfigException.Code.CONFIG_UPDATE_STREAM_ERROR);
    L30:
        r10.propagateErrors(r122);
        goto L68
    L55:
        if (r10.isStatusCodeRetryable(r7.intValue()) == true) goto L58;
    L57:
        r1 = false;
        goto L58
    L50:
        Log.d("FirebaseRemoteConfig", "Exception connecting to real-time RC backend. Retrying the connection...", e);     // Catch: Throwable -> L11
    L41:
        th = move-exception;
        r5 = null;
        r7 = 0;
        Throwable th = th;
        r112 = null;
    L70:
        r10.closeRealtimeHttpConnection(r112, r5);
        r10.setIsHttpConnectionRunning(false);
        if (r10.isInBackground == true) goto L76;
        if (r7 != 0) goto L74;
    L77:
        if (r1 == false) goto L79;
        r10.updateBackoffMetadataWithLastFailedStreamConnectionTime(new Date(r10.clock.currentTimeMillis()));
    L79:
        if (r1 == false) goto L81;
    L86:
        r10.retryHttpConnectionWhenBackoffEnds();
    L87:
        throw th;
    L81:
        if (r7.intValue() == 200) goto L86;
        String r114 = String.format("Unable to connect to the server. Try again in a few minutes. HTTP status code: %d", new Object[]{r7});
        if (r7.intValue() != 403) goto L85;
        r114 = r10.parseForbiddenErrorResponseMessage(r10.httpURLConnection.getErrorStream());
    L85:
        r10.propagateErrors(new FirebaseRemoteConfigServerException(r7.intValue(), r114, FirebaseRemoteConfigException.Code.CONFIG_UPDATE_STREAM_ERROR));
        goto L87
    L74:
        if (r10.isStatusCodeRetryable(r7.intValue()) == true) goto L77;
    L76:
        r1 = false;
        goto L77
    L4:
        if (r11.isSuccessful() == false) goto L46;
        HttpURLConnection r115 = (HttpURLConnection) r11.getResult();     // Catch: Throwable -> L41 IOException -> L43
        r10.httpURLConnection = r115;     // Catch: Throwable -> L41 IOException -> L43
        r112 = r115.getInputStream();     // Catch: Throwable -> L41 IOException -> L43
        r5 = r10.httpURLConnection.getErrorStream();     // Catch: Throwable -> L36 IOException -> L38
        int r6 = r10.httpURLConnection.getResponseCode();     // Catch: Throwable -> L32 IOException -> L34
        r7 = Integer.valueOf(r6);     // Catch: Throwable -> L32 IOException -> L34
        if (r6 == 200) goto L88;
    L15:
        r10.closeRealtimeHttpConnection(r112, r5);
        r10.setIsHttpConnectionRunning(false);
        if (r10.isInBackground == false) goto L18;
    L20:
        r1 = false;
    L21:
        if (r1 == false) goto L23;
        r10.updateBackoffMetadataWithLastFailedStreamConnectionTime(new Date(r10.clock.currentTimeMillis()));
    L23:
        if (r1 == true) goto L31;
        if (r6 == 200) goto L31;
        String r116 = String.format("Unable to connect to the server. Try again in a few minutes. HTTP status code: %d", new Object[]{r7});
        if (r6 != 403) goto L29;
        r116 = r10.parseForbiddenErrorResponseMessage(r10.httpURLConnection.getErrorStream());
    L29:
        r122 = new FirebaseRemoteConfigServerException(r6, r116, FirebaseRemoteConfigException.Code.CONFIG_UPDATE_STREAM_ERROR);
        goto L30
    L18:
        if (r10.isStatusCodeRetryable(r6) == false) goto L20;
    L88:
        r10.resetRetryCount();     // Catch: Throwable -> L11 IOException -> L13
        r10.sharedPrefsClient.resetRealtimeBackoff();     // Catch: Throwable -> L11 IOException -> L13
        ConfigAutoFetch r8 = r10.startAutoFetch(r10.httpURLConnection);     // Catch: Throwable -> L11 IOException -> L13
        r10.configAutoFetch = r8;     // Catch: Throwable -> L11 IOException -> L13
        r8.listenForNotifications();     // Catch: Throwable -> L11 IOException -> L13
    L13:
        e = e;
    L11:
        th = th;
        goto L70
    L34:
        e = e;
        r7 = 0;
    L32:
        th = move-exception;
        r7 = 0;
        th = th;
        goto L70
    L38:
        e = e;
        r5 = null;
    L36:
        th = move-exception;
        r7 = 0;
        th = th;
        r5 = null;
        goto L70
    L46:
        throw new IOException(r11.getException());     // Catch: Throwable -> L41 IOException -> L43
    }

    public static /* synthetic */ Task $r8$lambda$IjdOYUXD83twZvh8ZERrOwFUcw8(ConfigRealtimeHttpClient r0, Task r1, Task r2, Task r3) {
        r0.getClass();
        if (r1.isSuccessful() == true) goto L7;
        return Tasks.forException(new FirebaseRemoteConfigClientException("Firebase Installations failed to get installation auth token for config update listener connection.", r1.getException()));
    L7:
        if (r2.isSuccessful() == false) goto L9;
        HttpURLConnection r32 = (HttpURLConnection) r0.getUrl().openConnection();     // Catch: IOException -> L13
        String r12 = ((InstallationTokenResult) r1.getResult()).getToken();     // Catch: IOException -> L13
        r0.setRequestParams(r32, (String) r2.getResult(), r12);     // Catch: IOException -> L13
        return Tasks.forResult(r32);
    L13:
        e = move-exception;
        return Tasks.forException(new FirebaseRemoteConfigClientException("Failed to open HTTP stream connection", e));
    L9:
        return Tasks.forException(new FirebaseRemoteConfigClientException("Firebase Installations failed to get installation ID for config update listener connection.", r2.getException()));
    }

    static {
        BACKOFF_TIME_DURATIONS_IN_MINUTES = new int[]{2, 4, 8, 16, 32, 64, 128, 256};
        GMP_APP_ID_PATTERN = Pattern.compile("^[^:]+:([0-9]+):(android|ios|web):([0-9a-f]+)");
    }

    public ConfigRealtimeHttpClient(FirebaseApp r2, FirebaseInstallationsApi r3, ConfigFetchHandler r4, ConfigCacheClient r5, Context r6, String r7, Set<ConfigUpdateListener> r8, ConfigSharedPrefsClient r9, ScheduledExecutorService r10) {
        this.ORIGINAL_RETRIES = 8;
        this.listeners = r8;
        this.isHttpConnectionRunning = false;
        this.scheduledExecutorService = r10;
        this.random = new Random();
        this.httpRetriesRemaining = Math.max(8 - r9.getRealtimeBackoffMetadata().getNumFailedStreams(), 1);
        this.clock = DefaultClock.getInstance();
        this.firebaseApp = r2;
        this.configFetchHandler = r4;
        this.firebaseInstallations = r3;
        this.activatedCache = r5;
        this.context = r6;
        this.namespace = r7;
        this.sharedPrefsClient = r9;
        this.isRealtimeDisabled = false;
        this.isInBackground = false;
        this.backgroundLock = new Object();
    }

    public static /* synthetic */ void access$000(ConfigRealtimeHttpClient r0) {
        r0.enableBackoff();
    }

    public static /* synthetic */ void access$100(ConfigRealtimeHttpClient r0, FirebaseRemoteConfigException r1) {
        r0.propagateErrors(r1);
    }

    private synchronized boolean canMakeHttpStreamConnection() {
        monitor-enter(this);
    L12:
        th = move-exception;
        throw th;
    L4:
        if (this.listeners.isEmpty() == false) goto L6;
    L14:
        boolean r0 = false;
    L15:
        monitor-exit(this);
        return r0;
    L6:
        if (this.isHttpConnectionRunning == true) goto L14;
        if (this.isRealtimeDisabled == true) goto L14;
        if (this.isInBackground == true) goto L14;
        r0 = true;
        goto L15
    }

    private synchronized boolean checkAndSetHttpConnectionFlagIfNotRunning() {
        monitor-enter(this);
        boolean r0 = canMakeHttpStreamConnection();     // Catch: Throwable -> L7
        if (r0 == false) goto L9;
        setIsHttpConnectionRunning(true);     // Catch: Throwable -> L7
    L9:
        monitor-exit(this);
        return r0;
    L7:
        th = move-exception;
        throw th;
    }

    private void closeHttpConnectionInputStream(InputStream r2) {
        if (r2 == null) goto L10;
        r2.close();     // Catch: IOException -> L5
        return;
    L5:
        e = move-exception;
        Log.d("FirebaseRemoteConfig", "Error closing connection stream.", e);
        return;
    }

    private JSONObject createRequestBody(String r4) {
        HashMap r0 = new HashMap();
        r0.put("project", extractProjectNumberFromAppId(this.firebaseApp.getOptions().getApplicationId()));
        r0.put("namespace", this.namespace);
        r0.put("lastKnownVersionNumber", Long.toString(this.configFetchHandler.getTemplateVersionNumber()));
        r0.put("appId", this.firebaseApp.getOptions().getApplicationId());
        r0.put("sdkVersion", "23.1.0");
        r0.put("appInstanceId", r4);
        return new JSONObject(r0);
    }

    private synchronized void enableBackoff() {
        monitor-enter(this);
        this.isRealtimeDisabled = true;     // Catch: Throwable -> L7
        monitor-exit(this);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    private static String extractProjectNumberFromAppId(String r1) {
        Matcher r12 = GMP_APP_ID_PATTERN.matcher(r1);
        if (r12.matches() == true) goto L5;
        return null;
    L5:
        return r12.group(1);
    }

    private String getFingerprintHashForPackage() {
        Context r3 = this.context;     // Catch: PackageManager.NameNotFoundException -> L9
        byte[] r32 = AndroidUtilsLight.getPackageCertificateHashBytes(r3, r3.getPackageName());     // Catch: PackageManager.NameNotFoundException -> L9
        if (r32 != null) goto L8;
        Log.e("FirebaseRemoteConfig", "Could not get fingerprint hash for package: " + this.context.getPackageName());     // Catch: PackageManager.NameNotFoundException -> L9
        return null;
    L8:
        return Hex.bytesToStringUppercase(r32, false);
    L9:
        Log.i("FirebaseRemoteConfig", "No such package: " + this.context.getPackageName());
        return null;
    }

    private long getRandomizedBackoffDurationInMillis(int r5) {
        int[] r0 = BACKOFF_TIME_DURATIONS_IN_MINUTES;
        int r1 = r0.length;
        if (r5 < r1) goto L7;
        r5 = r1;
    L7:
        return (TimeUnit.MINUTES.toMillis(r0[r5 - 1]) / 2) + this.random.nextInt((int) r0);
    }

    private String getRealtimeURL(String r1) {
        return String.format("https://firebaseremoteconfigrealtime.googleapis.com/v1/projects/%s/namespaces/%s:streamFetchInvalidations", new Object[]{extractProjectNumberFromAppId(this.firebaseApp.getOptions().getApplicationId()), r1});
    }

    private URL getUrl() {
        return new URL(getRealtimeURL(this.namespace));
    L4:
        Log.e("FirebaseRemoteConfig", "URL is malformed");
        return null;
    }

    private boolean isStatusCodeRetryable(int r1) {
        if (r1 != 408) goto L5;
        return true;
    L5:
        if (r1 != 429) goto L7;
        return true;
    L7:
        if (r1 != 502) goto L9;
        return true;
    L9:
        if (r1 != 503) goto L11;
        return true;
    L11:
        if (r1 == 504) goto L20;
        return false;
    L20:
        return true;
    }

    private synchronized void makeRealtimeHttpConnection(long r4) {
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L4:
        if (canMakeHttpStreamConnection() == true) goto L7;
        monitor-exit(this);
        return;
    L7:
        int r0 = this.httpRetriesRemaining;     // Catch: Throwable -> L10
        if (r0 <= 0) goto L13;
        this.httpRetriesRemaining = r0 - 1;     // Catch: Throwable -> L10
        this.scheduledExecutorService.schedule(new AnonymousClass1(this), r4, TimeUnit.MILLISECONDS);     // Catch: Throwable -> L10
    L15:
        monitor-exit(this);
        return;
    L13:
        if (this.isInBackground == true) goto L15;
        propagateErrors(new FirebaseRemoteConfigClientException("Unable to connect to the server. Check your connection and try again.", FirebaseRemoteConfigException.Code.CONFIG_UPDATE_STREAM_ERROR));     // Catch: Throwable -> L10
        goto L15
    }

    private String parseForbiddenErrorResponseMessage(InputStream r3) {
        StringBuilder r2 = new StringBuilder();
        BufferedReader r0 = new BufferedReader(new InputStreamReader(r3));     // Catch: IOException -> L8
    L4:
        String r32 = r0.readLine();     // Catch: IOException -> L8
        if (r32 == null) goto L13;
        r2.append(r32);     // Catch: IOException -> L8
    L13:
        return r2.toString();
    L9:
        if (r2.length() != 0) goto L13;
        return "Unable to connect to the server, access is forbidden. HTTP status code: 403";
    }

    private synchronized void propagateErrors(FirebaseRemoteConfigException r3) {
        monitor-enter(this);
        Iterator<ConfigUpdateListener> r0 = this.listeners.iterator();     // Catch: Throwable -> L8
    L4:
        if (r0.hasNext() == false) goto L10;
        r0.next().onError(r3);     // Catch: Throwable -> L8
        goto L4
    L10:
        monitor-exit(this);
        return;
    L8:
        th = move-exception;
        throw th;
    }

    private synchronized void resetRetryCount() {
        monitor-enter(this);
        this.httpRetriesRemaining = 8;     // Catch: Throwable -> L7
        monitor-exit(this);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    private void setCommonRequestHeaders(HttpURLConnection r2, String r3) {
        r2.setRequestProperty("X-Goog-Firebase-Installations-Auth", r3);
        r2.setRequestProperty("X-Goog-Api-Key", this.firebaseApp.getOptions().getApiKey());
        r2.setRequestProperty("X-Android-Package", this.context.getPackageName());
        r2.setRequestProperty("X-Android-Cert", getFingerprintHashForPackage());
        r2.setRequestProperty("X-Google-GFE-Can-Retry", "yes");
        r2.setRequestProperty("X-Accept-Response-Streaming", "true");
        r2.setRequestProperty("Content-Type", "application/json");
        r2.setRequestProperty("Accept", "application/json");
    }

    private synchronized void setIsHttpConnectionRunning(boolean r1) {
        monitor-enter(this);
        this.isHttpConnectionRunning = r1;     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    private void updateBackoffMetadataWithLastFailedStreamConnectionTime(Date r7) {
        int r0 = this.sharedPrefsClient.getRealtimeBackoffMetadata().getNumFailedStreams() + 1;
        long r1 = getRandomizedBackoffDurationInMillis(r0);
        Date r3 = new Date(r7.getTime() + r1);
        this.sharedPrefsClient.setRealtimeBackoffMetadata(r0, r3);
    }

    @SuppressLint({"VisibleForTests", "DefaultLocale"})
    public void beginRealtimeHttpStream() {
        if (checkAndSetHttpConnectionFlagIfNotRunning() == true) goto L5;
        return;
    L5:
        ConfigSharedPrefsClient.RealtimeBackoffMetadata r0 = this.sharedPrefsClient.getRealtimeBackoffMetadata();
        if (new Date(this.clock.currentTimeMillis()).before(r0.getBackoffEndTime()) == false) goto L9;
        retryHttpConnectionWhenBackoffEnds();
        return;
    L9:
        Task<HttpURLConnection> r02 = createRealtimeConnection();
        Tasks.whenAllComplete(new Task[]{r02}).continueWith(this.scheduledExecutorService, new ConfigRealtimeHttpClient$$ExternalSyntheticLambda0(this, r02));
    }

    public void closeRealtimeHttpConnection(InputStream r3, InputStream r4) {
        HttpURLConnection r0 = this.httpURLConnection;
        if (r0 != null) goto L5;
    L7:
        closeHttpConnectionInputStream(r3);
        closeHttpConnectionInputStream(r4);
        return;
    L5:
        if (this.isInBackground == true) goto L7;
        r0.disconnect();
        goto L7
    }

    @SuppressLint({"VisibleForTests"})
    public Task<HttpURLConnection> createRealtimeConnection() {
        Task<InstallationTokenResult> r0 = this.firebaseInstallations.getToken(false);
        Task<String> r1 = this.firebaseInstallations.getId();
        return Tasks.whenAllComplete(new Task[]{r0, r1}).continueWithTask(this.scheduledExecutorService, new ConfigRealtimeHttpClient$$ExternalSyntheticLambda1(this, r0, r1));
    }

    @SuppressLint({"VisibleForTests"})
    public synchronized void retryHttpConnectionWhenBackoffEnds() {
        monitor-enter(this);
        Date r0 = new Date(this.clock.currentTimeMillis());     // Catch: Throwable -> L6
        makeRealtimeHttpConnection(Math.max(0, this.sharedPrefsClient.getRealtimeBackoffMetadata().getBackoffEndTime().getTime() - r0.getTime()));     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public void setIsInBackground(boolean r4) {
        Object r0 = this.backgroundLock;
        monitor-enter(r0);
        this.isInBackground = r4;     // Catch: Throwable -> L7
        ConfigAutoFetch r1 = this.configAutoFetch;     // Catch: Throwable -> L7
        if (r1 == null) goto L10;
        r1.setIsInBackground(r4);     // Catch: Throwable -> L7
    L10:
        if (Build.VERSION.SDK_INT < 26) goto L15;
        if (r4 == false) goto L15;
        HttpURLConnection r3 = this.httpURLConnection;     // Catch: Throwable -> L7
        if (r3 == null) goto L15;
        r3.disconnect();     // Catch: Throwable -> L7
    L15:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    @SuppressLint({"VisibleForTests"})
    public void setRequestParams(HttpURLConnection r2, String r3, String r4) {
        r2.setRequestMethod("POST");
        setCommonRequestHeaders(r2, r4);
        byte[] r1 = createRequestBody(r3).toString().getBytes("utf-8");
        BufferedOutputStream r32 = new BufferedOutputStream(r2.getOutputStream());
        r32.write(r1);
        r32.flush();
        r32.close();
    }

    @SuppressLint({"VisibleForTests"})
    public synchronized ConfigAutoFetch startAutoFetch(HttpURLConnection r9) {
        monitor-enter(this);
        AnonymousClass2 r5 = new AnonymousClass2(this);     // Catch: Throwable -> L6
        ConfigAutoFetch r0 = new ConfigAutoFetch(r9, this.configFetchHandler, this.activatedCache, this.listeners, r5, this.scheduledExecutorService, this.sharedPrefsClient);     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public void startHttpConnection() {
        makeRealtimeHttpConnection(0);
    }
}
