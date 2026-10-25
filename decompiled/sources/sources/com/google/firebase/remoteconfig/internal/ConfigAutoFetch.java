package com.google.firebase.remoteconfig.internal;

import android.util.Log;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.remoteconfig.ConfigUpdate;
import com.google.firebase.remoteconfig.ConfigUpdateListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigClientException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigServerException;
import com.google.firebase.remoteconfig.internal.ConfigFetchHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import okhttp3.internal.url._UrlKt;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class ConfigAutoFetch {
    private final ConfigCacheClient activatedCache;
    private final Clock clock;
    private final ConfigFetchHandler configFetchHandler;
    private final Set<ConfigUpdateListener> eventListeners;
    private final HttpURLConnection httpURLConnection;
    private boolean isInBackground;
    private final Random random;
    private final ConfigUpdateListener retryCallback;
    private final ScheduledExecutorService scheduledExecutorService;
    private final ConfigSharedPrefsClient sharedPrefsClient;

    /* renamed from: com.google.firebase.remoteconfig.internal.ConfigAutoFetch$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ ConfigAutoFetch this$0;
        final /* synthetic */ int val$remainingAttempts;
        final /* synthetic */ long val$targetVersion;

        public AnonymousClass1(ConfigAutoFetch r1, int r2, long r3) {
            this.this$0 = r1;
            this.val$remainingAttempts = r2;
            this.val$targetVersion = r3;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.fetchLatestConfig(this.val$remainingAttempts, this.val$targetVersion);
        }
    }

    /* renamed from: $r8$lambda$ANmcpkzunHxZeIZpnwJj9GhH-SM, reason: not valid java name */
    public static /* synthetic */ Task m1972$r8$lambda$ANmcpkzunHxZeIZpnwJj9GhHSM(ConfigAutoFetch r2, Task r3, Task r4, long r5, int r7, Task r8) {
        r2.getClass();
        if (r3.isSuccessful() == true) goto L7;
        return Tasks.forException(new FirebaseRemoteConfigClientException("Failed to auto-fetch config update.", r3.getException()));
    L7:
        if (r4.isSuccessful() == false) goto L9;
        ConfigFetchHandler.FetchResponse r32 = (ConfigFetchHandler.FetchResponse) r3.getResult();
        ConfigContainer r42 = (ConfigContainer) r4.getResult();
        if (fetchResponseIsUpToDate(r32, r5).booleanValue() == true) goto L15;
        Log.d("FirebaseRemoteConfig", "Fetched template version is the same as SDK's current version. Retrying fetch.");
        r2.autoFetch(r7, r5);
        return Tasks.forResult(null);
    L15:
        if (r32.getFetchedConfigs() != null) goto L18;
        Log.d("FirebaseRemoteConfig", "The fetch succeeded, but the backend had no updates.");
        return Tasks.forResult(null);
    L18:
        if (r42 != null) goto L20;
        r42 = ConfigContainer.newBuilder().build();
    L20:
        Set<String> r33 = r42.getChangedParams(r32.getFetchedConfigs());
        if (r33.isEmpty() == false) goto L24;
        Log.d("FirebaseRemoteConfig", "Config was fetched, but no params changed.");
        return Tasks.forResult(null);
    L24:
        r2.executeAllListenerCallbacks(ConfigUpdate.create(r33));
        return Tasks.forResult(null);
    L9:
        return Tasks.forException(new FirebaseRemoteConfigClientException("Failed to get activated config for auto-fetch", r4.getException()));
    }

    public ConfigAutoFetch(HttpURLConnection r1, ConfigFetchHandler r2, ConfigCacheClient r3, Set<ConfigUpdateListener> r4, ConfigUpdateListener r5, ScheduledExecutorService r6, ConfigSharedPrefsClient r7) {
        this.httpURLConnection = r1;
        this.configFetchHandler = r2;
        this.activatedCache = r3;
        this.eventListeners = r4;
        this.retryCallback = r5;
        this.scheduledExecutorService = r6;
        this.random = new Random();
        this.isInBackground = false;
        this.sharedPrefsClient = r7;
        this.clock = DefaultClock.getInstance();
    }

    private void autoFetch(int r4, long r5) {
        if (r4 != 0) goto L5;
        propagateErrors(new FirebaseRemoteConfigServerException("Unable to fetch the latest version of the template.", FirebaseRemoteConfigException.Code.CONFIG_UPDATE_NOT_FETCHED));
        return;
    L5:
        this.scheduledExecutorService.schedule(new AnonymousClass1(this, r4, r5), this.random.nextInt(4), TimeUnit.SECONDS);
    }

    private synchronized void executeAllListenerCallbacks(ConfigUpdate r3) {
        monitor-enter(this);
        Iterator<ConfigUpdateListener> r0 = this.eventListeners.iterator();     // Catch: Throwable -> L8
    L4:
        if (r0.hasNext() == false) goto L10;
        r0.next().onUpdate(r3);     // Catch: Throwable -> L8
        goto L4
    L10:
        monitor-exit(this);
        return;
    L8:
        th = move-exception;
        throw th;
    }

    private static Boolean fetchResponseIsUpToDate(ConfigFetchHandler.FetchResponse r5, long r6) {
        boolean r1 = false;
        if (r5.getFetchedConfigs() == null) goto L10;
        if (r5.getFetchedConfigs().getTemplateVersionNumber() < r6) goto L8;
        r1 = true;
    L8:
        return Boolean.valueOf(r1);
    L10:
        if (r5.getStatus() != 1) goto L13;
        r1 = true;
    L13:
        return Boolean.valueOf(r1);
    }

    private void handleNotifications(InputStream r11) {
        BufferedReader r3 = new BufferedReader(new InputStreamReader(r11, "utf-8"));
    L3:
        String r4 = _UrlKt.FRAGMENT_ENCODE_SET;
    L4:
        String r5 = r3.readLine();
        if (r5 == null) goto L31;
        r4 = r4.concat(r5);
        if (r5.contains("}") == false) goto L4;
        r4 = parseAndValidateConfigUpdateMessage(r4);
        if (r4.isEmpty() == true) goto L4;
        JSONObject r52 = new JSONObject(r4);     // Catch: JSONException -> L16
        if (r52.has("featureDisabled") == false) goto L19;
        if (r52.getBoolean("featureDisabled") == false) goto L19;
        this.retryCallback.onError(new FirebaseRemoteConfigServerException("The server is temporarily unavailable. Try again in a few minutes.", FirebaseRemoteConfigException.Code.CONFIG_UPDATE_UNAVAILABLE));     // Catch: JSONException -> L16
    L19:
        if (isEventListenersEmpty() == true) goto L31;
        if (r52.has("latestTemplateVersionNumber") == false) goto L27;
        long r6 = this.configFetchHandler.getTemplateVersionNumber();     // Catch: JSONException -> L16
        long r8 = r52.getLong("latestTemplateVersionNumber");     // Catch: JSONException -> L16
        if (r8 <= r6) goto L27;
        autoFetch(3, r8);     // Catch: JSONException -> L16
    L27:
        if (r52.has("retryIntervalSeconds") == false) goto L3;
        updateBackoffMetadataWithRetryInterval(r52.getInt("retryIntervalSeconds"));     // Catch: JSONException -> L16
    L16:
        e = move-exception;
        propagateErrors(new FirebaseRemoteConfigClientException("Unable to parse config update message.", e.getCause(), FirebaseRemoteConfigException.Code.CONFIG_UPDATE_MESSAGE_INVALID));
        Log.e("FirebaseRemoteConfig", "Unable to parse latest config update message.", e);
    L31:
        r3.close();
    }

    private synchronized boolean isEventListenersEmpty() {
        monitor-enter(this);
        boolean r0 = this.eventListeners.isEmpty();     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    private String parseAndValidateConfigUpdateMessage(String r3) {
        int r2 = r3.indexOf(123);
        int r0 = r3.lastIndexOf(125);
        if (r2 < 0) goto L10;
        if (r0 < 0) goto L10;
        if (r2 < r0) goto L9;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L9:
        return r3.substring(r2, r0 + 1);
    L10:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    private synchronized void propagateErrors(FirebaseRemoteConfigException r3) {
        monitor-enter(this);
        Iterator<ConfigUpdateListener> r0 = this.eventListeners.iterator();     // Catch: Throwable -> L8
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

    private synchronized void updateBackoffMetadataWithRetryInterval(int r6) {
        monitor-enter(this);
        this.sharedPrefsClient.setRealtimeBackoffEndTime(new Date(new Date(this.clock.currentTimeMillis()).getTime() + (r6 * 1000)));     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized Task<Void> fetchLatestConfig(int r9, long r10) {
        monitor-enter(this);
        int r6 = r9 - 1;
        Task<ConfigFetchHandler.FetchResponse> r2 = this.configFetchHandler.fetchNowWithTypeAndAttemptNumber(ConfigFetchHandler.FetchType.REALTIME, 3 - r6);     // Catch: Throwable -> L11
        Task<ConfigContainer> r3 = this.activatedCache.get();     // Catch: Throwable -> L11
        Task r8 = Tasks.whenAllComplete(new Task[]{r2, r3}).continueWithTask(this.scheduledExecutorService, new ConfigAutoFetch$$ExternalSyntheticLambda0(this, r2, r3, r10, r6));     // Catch: Throwable -> L9
        monitor-exit(this);
        return r8;
    L9:
        th = th;
    L10:
        Throwable r82 = th;
        monitor-exit(this);     // Catch: Throwable -> L9
        throw r82;
    L11:
        th = th;
        goto L10
    }

    public void listenForNotifications() {
        HttpURLConnection r2 = this.httpURLConnection;
        if (r2 == null) goto L35;
        InputStream r3 = null;
        r3 = r2.getInputStream();     // Catch: Throwable -> L12 IOException -> L14
        handleNotifications(r3);     // Catch: Throwable -> L12 IOException -> L14
        if (r3 == null) goto L32;
        r3.close();     // Catch: IOException -> L10
        return;
    L32:
        return;
    L10:
        e = move-exception;
        Log.d("FirebaseRemoteConfig", "Exception thrown when closing connection stream. Retrying connection...", e);
        return;
    L14:
        e = move-exception;
        if (this.isInBackground == true) goto L18;
        Log.d("FirebaseRemoteConfig", "Real-time connection was closed due to an exception.", e);     // Catch: Throwable -> L12
    L18:
        if (r3 == null) goto L34;
        r3.close();     // Catch: IOException -> L10
        return;
    L34:
        return;
    L12:
        th = move-exception;
        if (0 != 0) goto L28;
    L26:
        throw th;
    L28:
        r3.close();     // Catch: IOException -> L24
    L24:
        e = move-exception;
        Log.d("FirebaseRemoteConfig", "Exception thrown when closing connection stream. Retrying connection...", e);
        goto L26
    }

    public void setIsInBackground(boolean r1) {
        this.isInBackground = r1;
    }
}
