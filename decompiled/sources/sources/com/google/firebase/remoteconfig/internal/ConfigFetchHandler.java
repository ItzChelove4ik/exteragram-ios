package com.google.firebase.remoteconfig.internal;

import android.text.format.DateUtils;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.inject.Provider;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.installations.InstallationTokenResult;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigClientException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigFetchThrottledException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigServerException;
import com.google.firebase.remoteconfig.internal.ConfigSharedPrefsClient;
import java.net.HttpURLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class ConfigFetchHandler {
    static final int[] BACKOFF_TIME_DURATIONS_IN_MINUTES = null;
    public static final long DEFAULT_MINIMUM_FETCH_INTERVAL_IN_SECONDS = 0;
    private final Provider<AnalyticsConnector> analyticsConnector;
    private final Clock clock;
    private final Map<String, String> customHttpHeaders;
    private final Executor executor;
    private final ConfigCacheClient fetchedConfigsCache;
    private final FirebaseInstallationsApi firebaseInstallations;
    private final ConfigFetchHttpClient frcBackendApiClient;
    private final ConfigSharedPrefsClient frcSharedPrefs;
    private final Random randomGenerator;

    /* loaded from: classes5.dex */
    public static class FetchResponse {
        private final Date fetchTime;
        private final ConfigContainer fetchedConfigs;
        private final String lastFetchETag;
        private final int status;

        private FetchResponse(Date r1, int r2, ConfigContainer r3, String r4) {
            this.fetchTime = r1;
            this.status = r2;
            this.fetchedConfigs = r3;
            this.lastFetchETag = r4;
        }

        public static FetchResponse forBackendHasNoUpdates(Date r3, ConfigContainer r4) {
            return new FetchResponse(r3, 1, r4, null);
        }

        public static FetchResponse forBackendUpdatesFetched(ConfigContainer r3, String r4) {
            return new FetchResponse(r3.getFetchTime(), 0, r3, r4);
        }

        public static FetchResponse forLocalStorageUsed(Date r3) {
            return new FetchResponse(r3, 2, null, null);
        }

        public ConfigContainer getFetchedConfigs() {
            return this.fetchedConfigs;
        }

        public String getLastFetchETag() {
            return this.lastFetchETag;
        }

        public int getStatus() {
            return this.status;
        }
    }

    /* loaded from: classes5.dex */
    public enum FetchType extends Enum<FetchType> {
        private static final /* synthetic */ FetchType[] $VALUES = null;
        public static final FetchType BASE = null;
        public static final FetchType REALTIME = null;
        private final String value;

        private static /* synthetic */ FetchType[] $values() {
            return new FetchType[]{BASE, REALTIME};
        }

        static {
            BASE = new FetchType("BASE", 0, "BASE");
            REALTIME = new FetchType("REALTIME", 1, "REALTIME");
            $VALUES = $values();
        }

        FetchType(String r1, int r2, String r3) {
            this.value = r3;
        }

        public static FetchType valueOf(String r1) {
            return (FetchType) Enum.valueOf(FetchType.class, r1);
        }

        public static FetchType[] values() {
            return (FetchType[]) $VALUES.clone();
        }

        public String getValue() {
            return this.value;
        }
    }

    /* renamed from: $r8$lambda$2M_YxNX84RHSO_CvrLRaJfB-cIg, reason: not valid java name */
    public static /* synthetic */ Task m1974$r8$lambda$2M_YxNX84RHSO_CvrLRaJfBcIg(ConfigFetchHandler r0, Task r1, Task r2, Date r3, Map r4, Task r5) {
        r0.getClass();
        if (r1.isSuccessful() == true) goto L7;
        return Tasks.forException(new FirebaseRemoteConfigClientException("Firebase Installations failed to get installation ID for fetch.", r1.getException()));
    L7:
        if (r2.isSuccessful() == true) goto L11;
        return Tasks.forException(new FirebaseRemoteConfigClientException("Firebase Installations failed to get installation auth token for fetch.", r2.getException()));
    L11:
        return r0.fetchFromBackendAndCacheResponse((String) r1.getResult(), ((InstallationTokenResult) r2.getResult()).getToken(), r3, r4);
    }

    public static /* synthetic */ Task $r8$lambda$8iaYh9jpPTPF5HG1As1LuJkf5hQ(FetchResponse r0, ConfigContainer r1) {
        return Tasks.forResult(r0);
    }

    /* renamed from: $r8$lambda$IXMfN8uxtaDcb-XYjI5JectKUFY, reason: not valid java name */
    public static /* synthetic */ Task m1975$r8$lambda$IXMfN8uxtaDcbXYjI5JectKUFY(ConfigFetchHandler r0, Date r1, Task r2) {
        r0.updateLastFetchStatusAndTime(r2, r1);
        return r2;
    }

    public static /* synthetic */ Task $r8$lambda$NfboY5OASkxYGjrHqo8X6GPuijM(ConfigFetchHandler r2, Map r3, Task r4) {
        return r2.fetchIfCacheExpiredAndNotThrottled(r4, 0, r3);
    }

    static {
        DEFAULT_MINIMUM_FETCH_INTERVAL_IN_SECONDS = 43200;
        BACKOFF_TIME_DURATIONS_IN_MINUTES = new int[]{2, 4, 8, 16, 32, 64, 128, 256};
    }

    public ConfigFetchHandler(FirebaseInstallationsApi r1, Provider<AnalyticsConnector> r2, Executor r3, Clock r4, Random r5, ConfigCacheClient r6, ConfigFetchHttpClient r7, ConfigSharedPrefsClient r8, Map<String, String> r9) {
        this.firebaseInstallations = r1;
        this.analyticsConnector = r2;
        this.executor = r3;
        this.clock = r4;
        this.randomGenerator = r5;
        this.fetchedConfigsCache = r6;
        this.frcBackendApiClient = r7;
        this.frcSharedPrefs = r8;
        this.customHttpHeaders = r9;
    }

    private boolean areCachedFetchConfigsValid(long r4, Date r6) {
        Date r3 = this.frcSharedPrefs.getLastSuccessfulFetchTime();
        if (r3.equals(ConfigSharedPrefsClient.LAST_FETCH_TIME_NO_FETCH_YET) == false) goto L7;
        return false;
    L7:
        return r6.before(new Date(r3.getTime() + TimeUnit.SECONDS.toMillis(r4)));
    }

    private FirebaseRemoteConfigServerException createExceptionWithGenericMessage(FirebaseRemoteConfigServerException r4) {
        int r3 = r4.getHttpStatusCode();
        if (r3 != 401) goto L5;
        String r32 = "The request did not have the required credentials. Please make sure your google-services.json is valid.";
    L19:
        return new FirebaseRemoteConfigServerException(r4.getHttpStatusCode(), "Fetch failed: ".concat(r32), r4);
    L5:
        if (r3 != 403) goto L7;
        r32 = "The user is not authorized to access the project. Please make sure you are using the API key that corresponds to your Firebase project.";
        goto L19
    L7:
        if (r3 == 429) goto L15;
        if (r3 == 500) goto L13;
        switch(r3) {
            case 502: goto L12;
            case 503: goto L12;
            case 504: goto L12;
            default: goto L11;
        };
    L11:
        r32 = "The server returned an unexpected error.";
        goto L19
    L12:
        r32 = "The server is unavailable. Please try again later.";
        goto L19
    L13:
        r32 = "There was an internal server error.";
        goto L19
    L15:
        throw new FirebaseRemoteConfigClientException("The throttled response from the server was not handled correctly by the FRC SDK.");
    }

    private String createThrottledMessage(long r3) {
        return String.format("Fetch is throttled. Please wait before calling fetch again: %s", new Object[]{DateUtils.formatElapsedTime(r3 / 1000)});
    }

    private FetchResponse fetchFromBackend(String r12, String r13, Date r14, Map<String, String> r15) {
        HttpURLConnection r2 = this.frcBackendApiClient.createHttpURLConnection();     // Catch: FirebaseRemoteConfigServerException -> L14
        Date r9 = r14;
        FetchResponse r122 = this.frcBackendApiClient.fetch(r2, r12, r13, getUserProperties(), this.frcSharedPrefs.getLastFetchETag(), r15, getFirstOpenTime(), r9, this.frcSharedPrefs.getCustomSignals());     // Catch: FirebaseRemoteConfigServerException -> L7
        if (r122.getFetchedConfigs() == null) goto L10;
        this.frcSharedPrefs.setLastTemplateVersion(r122.getFetchedConfigs().getTemplateVersionNumber());     // Catch: FirebaseRemoteConfigServerException -> L7
    L10:
        if (r122.getLastFetchETag() == null) goto L12;
        this.frcSharedPrefs.setLastFetchETag(r122.getLastFetchETag());     // Catch: FirebaseRemoteConfigServerException -> L7
    L12:
        this.frcSharedPrefs.resetBackoff();     // Catch: FirebaseRemoteConfigServerException -> L7
        return r122;
    L7:
        e = e;
    L8:
        FirebaseRemoteConfigServerException r123 = e;
        ConfigSharedPrefsClient.BackoffMetadata r132 = updateAndReturnBackoffMetadata(r123.getHttpStatusCode(), r9);
        if (shouldThrottle(r132, r123.getHttpStatusCode()) == false) goto L21;
        throw new FirebaseRemoteConfigFetchThrottledException(r132.getBackoffEndTime().getTime());
    L21:
        throw createExceptionWithGenericMessage(r123);
    L14:
        e = e;
        r9 = r14;
        goto L8
    }

    private Task<FetchResponse> fetchFromBackendAndCacheResponse(String r1, String r2, Date r3, Map<String, String> r4) {
        FetchResponse r12 = fetchFromBackend(r1, r2, r3, r4);     // Catch: FirebaseRemoteConfigException -> L8
        if (r12.getStatus() == 0) goto L7;
        return Tasks.forResult(r12);
    L7:
        return this.fetchedConfigsCache.put(r12.getFetchedConfigs()).onSuccessTask(this.executor, new ConfigFetchHandler$$ExternalSyntheticLambda3(r12));
    L8:
        e = move-exception;
        return Tasks.forException(e);
    }

    private Task<FetchResponse> fetchIfCacheExpiredAndNotThrottled(Task<ConfigContainer> r7, long r8, Map<String, String> r10) {
        Date r4 = new Date(this.clock.currentTimeMillis());
        if (r7.isSuccessful() == true) goto L5;
    L8:
        Date r72 = getBackoffEndTimeInMillis(r4);
        if (r72 == null) goto L11;
        Task r73 = Tasks.forException(new FirebaseRemoteConfigFetchThrottledException(createThrottledMessage(r72.getTime() - r4.getTime()), r72.getTime()));
        ConfigFetchHandler r1 = this;
    L13:
        return r73.continueWithTask(r1.executor, new ConfigFetchHandler$$ExternalSyntheticLambda2(r1, r4));
    L11:
        Task<String> r2 = this.firebaseInstallations.getId();
        Task<InstallationTokenResult> r3 = this.firebaseInstallations.getToken(false);
        r1 = this;
        r73 = Tasks.whenAllComplete(new Task[]{r2, r3}).continueWithTask(this.executor, new ConfigFetchHandler$$ExternalSyntheticLambda1(r1, r2, r3, r4, r10));
        goto L13
    L5:
        if (areCachedFetchConfigsValid(r8, r4) == false) goto L8;
        return Tasks.forResult(FetchResponse.forLocalStorageUsed(r4));
    }

    private Date getBackoffEndTimeInMillis(Date r1) {
        Date r0 = this.frcSharedPrefs.getBackoffMetadata().getBackoffEndTime();
        if (r1.before(r0) == false) goto L5;
        return r0;
    L5:
        return null;
    }

    private Long getFirstOpenTime() {
        AnalyticsConnector r1 = this.analyticsConnector.get();
        if (r1 != null) goto L7;
        return null;
    L7:
        return (Long) r1.getUserProperties(true).get("_fot");
    }

    private long getRandomizedBackoffDurationInMillis(int r5) {
        return (TimeUnit.MINUTES.toMillis(BACKOFF_TIME_DURATIONS_IN_MINUTES[Math.min(r5, r0.length) - 1]) / 2) + this.randomGenerator.nextInt((int) r0);
    }

    private Map<String, String> getUserProperties() {
        HashMap r0 = new HashMap();
        AnalyticsConnector r3 = this.analyticsConnector.get();
        if (r3 == null) goto L9;
        Iterator<Map.Entry<String, Object>> r32 = r3.getUserProperties(false).entrySet().iterator();
    L7:
        if (r32.hasNext() == false) goto L9;
        Map.Entry<String, Object> r1 = r32.next();
        r0.put(r1.getKey(), r1.getValue().toString());
    L9:
        return r0;
    }

    private boolean isThrottleableServerError(int r1) {
        if (r1 != 429) goto L5;
        return true;
    L5:
        if (r1 != 502) goto L7;
        return true;
    L7:
        if (r1 != 503) goto L9;
        return true;
    L9:
        if (r1 == 504) goto L17;
        return false;
    L17:
        return true;
    }

    private boolean shouldThrottle(ConfigSharedPrefsClient.BackoffMetadata r1, int r2) {
        if (r1.getNumFailedFetches() <= 1) goto L5;
    L9:
        return true;
    L5:
        if (r2 == 429) goto L9;
        return false;
    }

    private ConfigSharedPrefsClient.BackoffMetadata updateAndReturnBackoffMetadata(int r1, Date r2) {
        if (isThrottleableServerError(r1) == false) goto L6;
        updateBackoffMetadataWithLastFailedFetchTime(r2);
    L6:
        return this.frcSharedPrefs.getBackoffMetadata();
    }

    private void updateBackoffMetadataWithLastFailedFetchTime(Date r7) {
        int r0 = this.frcSharedPrefs.getBackoffMetadata().getNumFailedFetches() + 1;
        long r1 = getRandomizedBackoffDurationInMillis(r0);
        Date r3 = new Date(r7.getTime() + r1);
        this.frcSharedPrefs.setBackoffMetadata(r0, r3);
    }

    private void updateLastFetchStatusAndTime(Task<FetchResponse> r2, Date r3) {
        if (r2.isSuccessful() == false) goto L6;
        this.frcSharedPrefs.updateLastFetchAsSuccessfulAt(r3);
        return;
    L6:
        Exception r22 = r2.getException();
        if (r22 != null) goto L9;
        return;
    L9:
        boolean r23 = r22 instanceof FirebaseRemoteConfigFetchThrottledException;
        ConfigSharedPrefsClient r1 = this.frcSharedPrefs;
        if (r23 == false) goto L13;
        r1.updateLastFetchAsThrottled();
        return;
    L13:
        r1.updateLastFetchAsFailed();
    }

    public Task<FetchResponse> fetchNowWithTypeAndAttemptNumber(FetchType r3, int r4) {
        HashMap r0 = new HashMap(this.customHttpHeaders);
        r0.put("X-Firebase-RC-Fetch-Type", r3.getValue() + "/" + r4);
        return this.fetchedConfigsCache.get().continueWithTask(this.executor, new ConfigFetchHandler$$ExternalSyntheticLambda0(this, r0));
    }

    public long getTemplateVersionNumber() {
        return this.frcSharedPrefs.getLastTemplateVersion();
    }
}
