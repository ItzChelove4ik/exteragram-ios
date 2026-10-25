package org.telegram.messenger.support.customtabs;

import android.os.Bundle;

/* loaded from: classes3.dex */
public abstract class CustomTabsCallback {
    public CustomTabsCallback() {
    }

    public void extraCallback(String r1, Bundle r2) {
    }

    public void onMessageChannelReady(Bundle r1) {
    }

    public abstract void onNavigationEvent(int r1, Bundle r2);

    public void onPostMessage(String r1, Bundle r2) {
    }
}
