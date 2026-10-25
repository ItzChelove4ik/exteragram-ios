package org.telegram.messenger;

import android.os.SystemClock;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import org.telegram.messenger.SharedConfig;
import org.telegram.tgnet.ConnectionsManager;

/* loaded from: classes.dex */
public class ProxyPingController {
    private static final ProxyPingController INSTANCE = null;
    private final Runnable pingRunnable;

    public static /* synthetic */ void $r8$lambda$UPB6wCO57vVD8ZYKwK3BlEt1I8M(ProxyPingController r0) {
        r0.lambda$new$0();
    }

    static {
        INSTANCE = new ProxyPingController();
    }

    public ProxyPingController() {
        this.pingRunnable = new ProxyPingController$$ExternalSyntheticLambda0(this);
    }

    public static void init() {
        INSTANCE.scheduleNextPing();
    }

    private /* synthetic */ void lambda$new$0() {
        if (SharedConfig.isProxyEnabled() == false) goto L11;
        SharedConfig.ProxyInfo r0 = SharedConfig.currentProxy;
        if (r0 == null) goto L11;
        int r1 = ConnectionsManager.native_getCurrentPingTime(UserConfig.selectedAccount);
        if (r1 <= 0) goto L9;
        r0.ping = r1;
        r0.availableCheckTime = SystemClock.elapsedRealtime();
        r0.available = true;
    L10:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.proxyPingUpdated, new Object[]{Integer.valueOf(r1)});
        goto L11
    L9:
        r0.ping = 0;
        r0.available = false;
    L11:
        scheduleNextPing();
    }

    private void scheduleNextPing() {
        AndroidUtilities.cancelRunOnUIThread(this.pingRunnable);
        AndroidUtilities.runOnUIThread(this.pingRunnable, NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);
    }
}
