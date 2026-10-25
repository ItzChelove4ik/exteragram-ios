package org.telegram.messenger.support.customtabs;

import android.content.ComponentName;
import android.os.IBinder;

/* loaded from: classes3.dex */
public final class CustomTabsSession {
    private final ICustomTabsCallback mCallback;
    private final ComponentName mComponentName;
    private final Object mLock;
    private final ICustomTabsService mService;

    public CustomTabsSession(ICustomTabsService r2, ICustomTabsCallback r3, ComponentName r4) {
        this.mLock = new Object();
        this.mService = r2;
        this.mCallback = r3;
        this.mComponentName = r4;
    }

    public IBinder getBinder() {
        return this.mCallback.asBinder();
    }

    public ComponentName getComponentName() {
        return this.mComponentName;
    }
}
