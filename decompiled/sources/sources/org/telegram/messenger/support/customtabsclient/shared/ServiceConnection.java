package org.telegram.messenger.support.customtabsclient.shared;

import android.content.ComponentName;
import java.lang.ref.WeakReference;
import org.telegram.messenger.support.customtabs.CustomTabsClient;
import org.telegram.messenger.support.customtabs.CustomTabsServiceConnection;

/* loaded from: classes.dex */
public class ServiceConnection extends CustomTabsServiceConnection {
    private WeakReference<ServiceConnectionCallback> mConnectionCallback;

    public ServiceConnection(ServiceConnectionCallback r2) {
        this.mConnectionCallback = new WeakReference(r2);
    }

    @Override // org.telegram.messenger.support.customtabs.CustomTabsServiceConnection
    public void onCustomTabsServiceConnected(ComponentName r1, CustomTabsClient r2) {
        ServiceConnectionCallback r0 = this.mConnectionCallback.get();
        if (r0 == null) goto L6;
        r0.onServiceConnected(r2);
        return;
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName r1) {
        ServiceConnectionCallback r0 = this.mConnectionCallback.get();
        if (r0 == null) goto L6;
        r0.onServiceDisconnected();
        return;
    }
}
