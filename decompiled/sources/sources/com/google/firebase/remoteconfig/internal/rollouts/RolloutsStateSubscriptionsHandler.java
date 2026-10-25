package com.google.firebase.remoteconfig.internal.rollouts;

import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException;
import com.google.firebase.remoteconfig.internal.ConfigCacheClient;
import com.google.firebase.remoteconfig.internal.ConfigContainer;
import com.google.firebase.remoteconfig.interop.rollouts.RolloutsState;
import com.google.firebase.remoteconfig.interop.rollouts.RolloutsStateSubscriber;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public class RolloutsStateSubscriptionsHandler {
    private ConfigCacheClient activatedConfigsCache;
    private Executor executor;
    private RolloutsStateFactory rolloutsStateFactory;
    private Set<RolloutsStateSubscriber> subscribers;

    public static /* synthetic */ void $r8$lambda$G2FlweAKkFv8RelwOveXNqMs9Nw(RolloutsStateSubscriptionsHandler r0, Task r1, RolloutsStateSubscriber r2, ConfigContainer r3) {
        r0.getClass();
        ConfigContainer r12 = (ConfigContainer) r1.getResult();     // Catch: FirebaseRemoteConfigException -> L7
        if (r12 == null) goto L12;
        RolloutsState r13 = r0.rolloutsStateFactory.getActiveRolloutsState(r12);     // Catch: FirebaseRemoteConfigException -> L7
        r0.executor.execute(new RolloutsStateSubscriptionsHandler$$ExternalSyntheticLambda1(r2, r13));     // Catch: FirebaseRemoteConfigException -> L7
        return;
    L12:
        return;
    L7:
        e = move-exception;
        Log.w("FirebaseRemoteConfig", "Exception publishing RolloutsState to subscriber. Continuing to listen for changes.", e);
    }

    /* renamed from: $r8$lambda$GuAt-kPfcOcvnxP43pO3Wmfx0_k, reason: not valid java name */
    public static /* synthetic */ void m1976$r8$lambda$GuAtkPfcOcvnxP43pO3Wmfx0_k(RolloutsStateSubscriber r0, RolloutsState r1) {
        r0.onRolloutsStateChanged(r1);
    }

    public RolloutsStateSubscriptionsHandler(ConfigCacheClient r2, RolloutsStateFactory r3, Executor r4) {
        this.subscribers = Collections.newSetFromMap(new ConcurrentHashMap());
        this.activatedConfigsCache = r2;
        this.rolloutsStateFactory = r3;
        this.executor = r4;
    }

    public void registerRolloutsStateSubscriber(RolloutsStateSubscriber r4) {
        this.subscribers.add(r4);
        Task<ConfigContainer> r0 = this.activatedConfigsCache.get();
        r0.addOnSuccessListener(this.executor, new RolloutsStateSubscriptionsHandler$$ExternalSyntheticLambda0(this, r0, r4));
    }
}
