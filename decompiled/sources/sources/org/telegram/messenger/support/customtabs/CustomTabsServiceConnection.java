package org.telegram.messenger.support.customtabs;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import org.telegram.messenger.support.customtabs.ICustomTabsService;

/* loaded from: classes.dex */
public abstract class CustomTabsServiceConnection implements ServiceConnection {

    /* renamed from: org.telegram.messenger.support.customtabs.CustomTabsServiceConnection$1, reason: invalid class name */
    public class AnonymousClass1 extends CustomTabsClient {
        final /* synthetic */ CustomTabsServiceConnection this$0;

        public AnonymousClass1(CustomTabsServiceConnection r1, ICustomTabsService r2, ComponentName r3) {
            this.this$0 = r1;
            super(r2, r3);
        }
    }

    public CustomTabsServiceConnection() {
    }

    public abstract void onCustomTabsServiceConnected(ComponentName r1, CustomTabsClient r2);

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName r2, IBinder r3) {
        onCustomTabsServiceConnected(r2, new AnonymousClass1(this, ICustomTabsService.Stub.asInterface(r3), r2));
    }
}
