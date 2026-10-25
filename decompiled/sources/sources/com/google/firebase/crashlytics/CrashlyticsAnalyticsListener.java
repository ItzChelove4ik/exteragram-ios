package com.google.firebase.crashlytics;

import android.os.Bundle;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.analytics.AnalyticsEventReceiver;
import java.util.Locale;

/* loaded from: classes.dex */
class CrashlyticsAnalyticsListener implements AnalyticsConnector.AnalyticsConnectorListener {
    private AnalyticsEventReceiver breadcrumbEventReceiver;
    private AnalyticsEventReceiver crashlyticsOriginEventReceiver;

    public CrashlyticsAnalyticsListener() {
    }

    private static void notifyEventReceiver(AnalyticsEventReceiver r0, String r1, Bundle r2) {
        if (r0 != null) goto L4;
        return;
    L4:
        r0.onEvent(r1, r2);
    }

    private void notifyEventReceivers(String r3, Bundle r4) {
        if ("clx".equals(r4.getString("_o")) == false) goto L5;
        AnalyticsEventReceiver r2 = this.crashlyticsOriginEventReceiver;
    L6:
        notifyEventReceiver(r2, r3, r4);
        return;
    L5:
        r2 = this.breadcrumbEventReceiver;
        goto L6
    }

    @Override // com.google.firebase.analytics.connector.AnalyticsConnector.AnalyticsConnectorListener
    public void onMessageTriggered(int r4, Bundle r5) {
        Logger.getLogger().v(String.format(Locale.US, "Analytics listener received message. ID: %d, Extras: %s", new Object[]{Integer.valueOf(r4), r5}));
        if (r5 == null) goto L13;
        String r42 = r5.getString("name");
        if (r42 == null) goto L12;
        Bundle r52 = r5.getBundle(PluginsConstants.PARAMS);
        if (r52 != null) goto L10;
        r52 = new Bundle();
    L10:
        notifyEventReceivers(r42, r52);
        return;
    L12:
        return;
    }

    public void setBreadcrumbEventReceiver(AnalyticsEventReceiver r1) {
        this.breadcrumbEventReceiver = r1;
    }

    public void setCrashlyticsOriginEventReceiver(AnalyticsEventReceiver r1) {
        this.crashlyticsOriginEventReceiver = r1;
    }
}
