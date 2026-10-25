package com.google.firebase.remoteconfig;

/* loaded from: classes5.dex */
public class FirebaseRemoteConfigFetchThrottledException extends FirebaseRemoteConfigException {
    private final long throttleEndTimeMillis;

    public FirebaseRemoteConfigFetchThrottledException(long r2) {
        this("Fetch was throttled.", r2);
    }

    public FirebaseRemoteConfigFetchThrottledException(String r1, long r2) {
        super(r1);
        this.throttleEndTimeMillis = r2;
    }
}
