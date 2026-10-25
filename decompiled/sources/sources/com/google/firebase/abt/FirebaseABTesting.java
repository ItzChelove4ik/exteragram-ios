package com.google.firebase.abt;

import android.content.Context;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.inject.Provider;

/* loaded from: classes.dex */
public class FirebaseABTesting {
    private final Provider<AnalyticsConnector> analyticsConnector;
    private Integer maxUserProperties;
    private final String originService;

    public FirebaseABTesting(Context r1, Provider<AnalyticsConnector> r2, String r3) {
        this.analyticsConnector = r2;
        this.originService = r3;
        this.maxUserProperties = null;
    }
}
