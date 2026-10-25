package com.google.firebase.remoteconfig.internal;

import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.inject.Provider;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class Personalization {
    private final Provider<AnalyticsConnector> analyticsConnector;
    private final Map<String, String> loggedChoiceIds;

    public Personalization(Provider<AnalyticsConnector> r2) {
        this.loggedChoiceIds = Collections.synchronizedMap(new HashMap());
        this.analyticsConnector = r2;
    }
}
