package com.google.firebase.abt.component;

import android.content.Context;
import com.google.firebase.abt.FirebaseABTesting;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.inject.Provider;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class AbtComponent {
    private final Map<String, FirebaseABTesting> abtOriginInstances;
    private final Provider<AnalyticsConnector> analyticsConnector;
    private final Context appContext;

    public AbtComponent(Context r2, Provider<AnalyticsConnector> r3) {
        this.abtOriginInstances = new HashMap();
        this.appContext = r2;
        this.analyticsConnector = r3;
    }

    public FirebaseABTesting createAbtInstance(String r3) {
        return new FirebaseABTesting(this.appContext, this.analyticsConnector, r3);
    }

    public synchronized FirebaseABTesting get(String r3) {
        monitor-enter(this);
    L6:
        th = move-exception;
        throw th;
    L4:
        if (this.abtOriginInstances.containsKey(r3) == true) goto L8;
        this.abtOriginInstances.put(r3, createAbtInstance(r3));     // Catch: Throwable -> L6
    L8:
        FirebaseABTesting r32 = this.abtOriginInstances.get(r3);     // Catch: Throwable -> L6
        monitor-exit(this);
        return r32;
    }
}
