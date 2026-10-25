package com.google.firebase.remoteconfig.internal;

import android.content.SharedPreferences;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ConfigSharedPrefsClient {
    static final Date LAST_FETCH_TIME_NO_FETCH_YET = null;
    static final Date NO_BACKOFF_TIME = null;
    private final Object backoffMetadataLock;
    private final Object customSignalsLock;
    private final Object frcInfoLock;
    private final SharedPreferences frcSharedPrefs;
    private final Object realtimeBackoffMetadataLock;

    /* loaded from: classes5.dex */
    public static class BackoffMetadata {
        private Date backoffEndTime;
        private int numFailedFetches;

        public BackoffMetadata(int r1, Date r2) {
            this.numFailedFetches = r1;
            this.backoffEndTime = r2;
        }

        public Date getBackoffEndTime() {
            return this.backoffEndTime;
        }

        public int getNumFailedFetches() {
            return this.numFailedFetches;
        }
    }

    public static class RealtimeBackoffMetadata {
        private Date backoffEndTime;
        private int numFailedStreams;

        public RealtimeBackoffMetadata(int r1, Date r2) {
            this.numFailedStreams = r1;
            this.backoffEndTime = r2;
        }

        public Date getBackoffEndTime() {
            return this.backoffEndTime;
        }

        public int getNumFailedStreams() {
            return this.numFailedStreams;
        }
    }

    static {
        LAST_FETCH_TIME_NO_FETCH_YET = new Date(-1);
        NO_BACKOFF_TIME = new Date(-1);
    }

    public ConfigSharedPrefsClient(SharedPreferences r1) {
        this.frcSharedPrefs = r1;
        this.frcInfoLock = new Object();
        this.backoffMetadataLock = new Object();
        this.realtimeBackoffMetadataLock = new Object();
        this.customSignalsLock = new Object();
    }

    public BackoffMetadata getBackoffMetadata() {
        Object r0 = this.backoffMetadataLock;
        monitor-enter(r0);
        BackoffMetadata r1 = new BackoffMetadata(this.frcSharedPrefs.getInt("num_failed_fetches", 0), new Date(this.frcSharedPrefs.getLong("backoff_end_time_in_millis", -1)));     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public Map<String, String> getCustomSignals() {
        JSONObject r0 = new JSONObject(this.frcSharedPrefs.getString("customSignals", "{}"));     // Catch: JSONException -> L9
        HashMap r4 = new HashMap();     // Catch: JSONException -> L9
        Iterator<String> r1 = r0.keys();     // Catch: JSONException -> L9
    L4:
        if (r1.hasNext() == false) goto L8;
        String r2 = r1.next();     // Catch: JSONException -> L9
        r4.put(r2, r0.optString(r2));     // Catch: JSONException -> L9
        goto L4
    L8:
        return r4;
    L10:
        return new HashMap();
    }

    public long getFetchTimeoutInSeconds() {
        return this.frcSharedPrefs.getLong("fetch_timeout_in_seconds", 60);
    }

    public String getLastFetchETag() {
        return this.frcSharedPrefs.getString("last_fetch_etag", null);
    }

    public Date getLastSuccessfulFetchTime() {
        return new Date(this.frcSharedPrefs.getLong("last_fetch_time_in_millis", -1));
    }

    public long getLastTemplateVersion() {
        return this.frcSharedPrefs.getLong("last_template_version", 0);
    }

    public RealtimeBackoffMetadata getRealtimeBackoffMetadata() {
        Object r0 = this.realtimeBackoffMetadataLock;
        monitor-enter(r0);
        RealtimeBackoffMetadata r1 = new RealtimeBackoffMetadata(this.frcSharedPrefs.getInt("num_failed_realtime_streams", 0), new Date(this.frcSharedPrefs.getLong("realtime_backoff_end_time_in_millis", -1)));     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public void resetBackoff() {
        setBackoffMetadata(0, NO_BACKOFF_TIME);
    }

    public void resetRealtimeBackoff() {
        setRealtimeBackoffMetadata(0, NO_BACKOFF_TIME);
    }

    public void setBackoffMetadata(int r4, Date r5) {
        Object r0 = this.backoffMetadataLock;
        monitor-enter(r0);
        this.frcSharedPrefs.edit().putInt("num_failed_fetches", r4).putLong("backoff_end_time_in_millis", r5.getTime()).apply();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public void setLastFetchETag(String r3) {
        Object r0 = this.frcInfoLock;
        monitor-enter(r0);
        this.frcSharedPrefs.edit().putString("last_fetch_etag", r3).apply();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public void setLastTemplateVersion(long r3) {
        Object r0 = this.frcInfoLock;
        monitor-enter(r0);
        this.frcSharedPrefs.edit().putLong("last_template_version", r3).apply();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public void setRealtimeBackoffEndTime(Date r5) {
        Object r0 = this.realtimeBackoffMetadataLock;
        monitor-enter(r0);
        this.frcSharedPrefs.edit().putLong("realtime_backoff_end_time_in_millis", r5.getTime()).apply();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public void setRealtimeBackoffMetadata(int r4, Date r5) {
        Object r0 = this.realtimeBackoffMetadataLock;
        monitor-enter(r0);
        this.frcSharedPrefs.edit().putInt("num_failed_realtime_streams", r4).putLong("realtime_backoff_end_time_in_millis", r5.getTime()).apply();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public void updateLastFetchAsFailed() {
        Object r0 = this.frcInfoLock;
        monitor-enter(r0);
        this.frcSharedPrefs.edit().putInt("last_fetch_status", 1).apply();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public void updateLastFetchAsSuccessfulAt(Date r5) {
        Object r0 = this.frcInfoLock;
        monitor-enter(r0);
        this.frcSharedPrefs.edit().putInt("last_fetch_status", -1).putLong("last_fetch_time_in_millis", r5.getTime()).apply();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public void updateLastFetchAsThrottled() {
        Object r0 = this.frcInfoLock;
        monitor-enter(r0);
        this.frcSharedPrefs.edit().putInt("last_fetch_status", 2).apply();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }
}
