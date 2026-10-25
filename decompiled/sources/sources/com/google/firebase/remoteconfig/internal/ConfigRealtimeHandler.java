package com.google.firebase.remoteconfig.internal;

import android.content.Context;
import com.google.firebase.FirebaseApp;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.remoteconfig.ConfigUpdateListener;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes.dex */
public class ConfigRealtimeHandler {
    private final ConfigCacheClient activatedCacheClient;
    private final ConfigFetchHandler configFetchHandler;
    private final ConfigRealtimeHttpClient configRealtimeHttpClient;
    private final Context context;
    private final FirebaseApp firebaseApp;
    private final FirebaseInstallationsApi firebaseInstallations;
    private final Set<ConfigUpdateListener> listeners;
    private final String namespace;
    private final ScheduledExecutorService scheduledExecutorService;
    private final ConfigSharedPrefsClient sharedPrefsClient;

    public ConfigRealtimeHandler(FirebaseApp r11, FirebaseInstallationsApi r12, ConfigFetchHandler r13, ConfigCacheClient r14, Context r15, String r16, ConfigSharedPrefsClient r17, ScheduledExecutorService r18) {
        LinkedHashSet r7 = new LinkedHashSet();
        this.listeners = r7;
        this.configRealtimeHttpClient = new ConfigRealtimeHttpClient(r11, r12, r13, r14, r15, r16, r7, r17, r18);
        this.firebaseApp = r11;
        this.configFetchHandler = r13;
        this.firebaseInstallations = r12;
        this.activatedCacheClient = r14;
        this.context = r15;
        this.namespace = r16;
        this.sharedPrefsClient = r17;
        this.scheduledExecutorService = r18;
    }

    private synchronized void beginRealtime() {
        monitor-enter(this);
    L7:
        th = move-exception;
        throw th;
    L4:
        if (this.listeners.isEmpty() == true) goto L9;
        this.configRealtimeHttpClient.startHttpConnection();     // Catch: Throwable -> L7
    L9:
        monitor-exit(this);
    }

    public synchronized void setBackgroundState(boolean r2) {
        monitor-enter(this);
        this.configRealtimeHttpClient.setIsInBackground(r2);     // Catch: Throwable -> L7
        if (r2 == true) goto L9;
        beginRealtime();     // Catch: Throwable -> L7
    L9:
        monitor-exit(this);
        return;
    L7:
        th = move-exception;
        throw th;
    }
}
