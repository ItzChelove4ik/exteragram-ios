package com.google.firebase.analytics.connector;

import android.os.Bundle;
import java.util.Map;

/* loaded from: classes.dex */
public interface AnalyticsConnector {

    public interface AnalyticsConnectorHandle {
    }

    public interface AnalyticsConnectorListener {
        void onMessageTriggered(int r1, Bundle r2);
    }

    Map<String, Object> getUserProperties(boolean r1);

    void logEvent(String r1, String r2, Bundle r3);

    AnalyticsConnectorHandle registerAnalyticsConnectorListener(String r1, AnalyticsConnectorListener r2);

    void setUserProperty(String r1, String r2, Object r3);
}
