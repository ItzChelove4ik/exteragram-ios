package com.google.firebase.remoteconfig;

import android.content.Context;
import com.google.firebase.FirebaseApp;
import com.google.firebase.abt.FirebaseABTesting;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.remoteconfig.internal.ConfigCacheClient;
import com.google.firebase.remoteconfig.internal.ConfigFetchHandler;
import com.google.firebase.remoteconfig.internal.ConfigGetParameterHandler;
import com.google.firebase.remoteconfig.internal.ConfigRealtimeHandler;
import com.google.firebase.remoteconfig.internal.ConfigSharedPrefsClient;
import com.google.firebase.remoteconfig.internal.rollouts.RolloutsStateSubscriptionsHandler;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public class FirebaseRemoteConfig {
    public static final byte[] DEFAULT_VALUE_FOR_BYTE_ARRAY = null;
    private final ConfigCacheClient activatedConfigsCache;
    private final ConfigRealtimeHandler configRealtimeHandler;
    private final Context context;
    private final ConfigCacheClient defaultConfigsCache;
    private final Executor executor;
    private final ConfigFetchHandler fetchHandler;
    private final ConfigCacheClient fetchedConfigsCache;
    private final FirebaseABTesting firebaseAbt;
    private final FirebaseApp firebaseApp;
    private final FirebaseInstallationsApi firebaseInstallations;
    private final ConfigSharedPrefsClient frcSharedPrefs;
    private final ConfigGetParameterHandler getHandler;
    private final RolloutsStateSubscriptionsHandler rolloutsStateSubscriptionsHandler;

    static {
        DEFAULT_VALUE_FOR_BYTE_ARRAY = new byte[0];
    }

    public FirebaseRemoteConfig(Context r1, FirebaseApp r2, FirebaseInstallationsApi r3, FirebaseABTesting r4, Executor r5, ConfigCacheClient r6, ConfigCacheClient r7, ConfigCacheClient r8, ConfigFetchHandler r9, ConfigGetParameterHandler r10, ConfigSharedPrefsClient r11, ConfigRealtimeHandler r12, RolloutsStateSubscriptionsHandler r13) {
        this.context = r1;
        this.firebaseApp = r2;
        this.firebaseInstallations = r3;
        this.firebaseAbt = r4;
        this.executor = r5;
        this.fetchedConfigsCache = r6;
        this.activatedConfigsCache = r7;
        this.defaultConfigsCache = r8;
        this.fetchHandler = r9;
        this.getHandler = r10;
        this.frcSharedPrefs = r11;
        this.configRealtimeHandler = r12;
        this.rolloutsStateSubscriptionsHandler = r13;
    }

    public RolloutsStateSubscriptionsHandler getRolloutsStateSubscriptionsHandler() {
        return this.rolloutsStateSubscriptionsHandler;
    }

    public void setConfigUpdateBackgroundState(boolean r1) {
        this.configRealtimeHandler.setBackgroundState(r1);
    }

    public void startLoadingConfigsFromDisk() {
        this.activatedConfigsCache.get();
        this.defaultConfigsCache.get();
        this.fetchedConfigsCache.get();
    }
}
