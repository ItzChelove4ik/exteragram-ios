package com.google.firebase.remoteconfig;

/* loaded from: classes5.dex */
public interface ConfigUpdateListener {
    void onError(FirebaseRemoteConfigException r1);

    void onUpdate(ConfigUpdate r1);
}
