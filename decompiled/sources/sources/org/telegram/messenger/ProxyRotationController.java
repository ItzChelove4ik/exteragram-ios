package org.telegram.messenger;

import android.content.SharedPreferences;
import android.os.SystemClock;
import com.exteragram.messenger.proxy.ProxyController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.SharedConfig;
import org.telegram.tgnet.ConnectionsManager;

/* loaded from: classes.dex */
public class ProxyRotationController implements NotificationCenter.NotificationCenterDelegate {
    public static final int DEFAULT_TIMEOUT_INDEX = 1;
    private static final ProxyRotationController INSTANCE = null;
    public static final List<Integer> ROTATION_TIMEOUTS = null;
    private Runnable checkProxyAndSwitchRunnable;
    private boolean isCurrentlyChecking;

    public static /* synthetic */ int $r8$lambda$1qJ21Y6U4pSijLu67isAkLum5lk(SharedConfig.ProxyInfo r2, SharedConfig.ProxyInfo r3) {
        return Long.compare(r2.ping, r3.ping);
    }

    public static /* synthetic */ void $r8$lambda$FJmGRCN67bPTU1pXgzMUZvFLhhE(ProxyRotationController r0) {
        r0.lambda$new$2();
    }

    /* renamed from: $r8$lambda$eWTdX8ujv-si5gV2KcZj1iGeQgI, reason: not valid java name */
    public static /* synthetic */ void m4814$r8$lambda$eWTdX8ujvsi5gV2KcZj1iGeQgI(SharedConfig.ProxyInfo r1, long r2) {
        AndroidUtilities.runOnUIThread(new ProxyRotationController$$ExternalSyntheticLambda3(r1, r2));
    }

    public static /* synthetic */ void $r8$lambda$y4AyqLfFAqbbC1e8rcgYYgTc97s(SharedConfig.ProxyInfo r3, long r4) {
        r3.availableCheckTime = SystemClock.elapsedRealtime();
        r3.checking = false;
        if (r4 != (-1)) goto L5;
        r3.available = false;
        r3.ping = 0;
    L6:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.proxyCheckDone, new Object[]{r3});
        return;
    L5:
        r3.ping = r4;
        r3.available = true;
        goto L6
    }

    static {
        INSTANCE = new ProxyRotationController();
        ROTATION_TIMEOUTS = Arrays.asList(new Integer[]{5, 10, 15, 30, 60});
    }

    public ProxyRotationController() {
        this.checkProxyAndSwitchRunnable = new ProxyRotationController$$ExternalSyntheticLambda0(this);
    }

    public static void init() {
        INSTANCE.initInternal();
    }

    private void initInternal() {
        int r0 = 0;
    L4:
        if (r0 >= 16) goto L6;
        NotificationCenter.getInstance(r0).addObserver(this, NotificationCenter.didUpdateConnectionState);
        r0 = r0 + 1;
        goto L4
    L6:
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.proxyCheckDone);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.proxySettingsChanged);
    }

    private /* synthetic */ void lambda$new$2() {
        this.isCurrentlyChecking = true;
        int r1 = UserConfig.selectedAccount;
        int r3 = 0;
        boolean r4 = false;
    L4:
        if (r3 >= SharedConfig.proxyList.size()) goto L12;
        SharedConfig.ProxyInfo r5 = SharedConfig.proxyList.get(r3);
        if (r5.checking == true) goto L11;
        if ((SystemClock.elapsedRealtime() - r5.availableCheckTime) < 120000) goto L11;
        r5.checking = true;
        ConnectionsManager.getInstance(r1).checkProxy(r5.settings, new ProxyRotationController$$ExternalSyntheticLambda2(r5));
        r4 = true;
    L11:
        r3 = r3 + 1;
        goto L4
    L12:
        if (r4 == true) goto L19;
        this.isCurrentlyChecking = false;
        switchToAvailable();
        return;
    }

    private void switchToAvailable() {
        this.isCurrentlyChecking = false;
        if (SharedConfig.proxyRotationEnabled == false) goto L24;
        ArrayList r5 = new ArrayList(SharedConfig.proxyList);
        Collections.sort(r5, new ProxyRotationController$$ExternalSyntheticLambda1());
        int r1 = r5.size();
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L25;
        Object r3 = r5.get(r2);
        r2 = r2 + 1;
        SharedConfig.ProxyInfo r32 = (SharedConfig.ProxyInfo) r3;
        if (r32 == SharedConfig.currentProxy) goto L6;
        if (r32.checking == true) goto L6;
        if (r32.available == false) goto L6;
        SharedPreferences.Editor r52 = MessagesController.getGlobalMainSettings().edit();
        r52.putBoolean("proxy_enabled", true);
        r32.settings.toSharedPreferences(r52);
        r52.apply();
        ProxyController.getInstance().setCurrentProxy(r32);
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.proxySettingsChanged, new Object[0]);
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.proxyChangedByRotation, new Object[0]);
        ConnectionsManager.setProxySettings(true, r32.settings);
        return;
    L25:
        return;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r3 != NotificationCenter.proxyCheckDone) goto L16;
        if (SharedConfig.isProxyEnabled() == true) goto L7;
        return;
    L7:
        if (SharedConfig.proxyRotationEnabled == true) goto L9;
        return;
    L9:
        if (SharedConfig.proxyList.size() > 1) goto L11;
        return;
    L11:
        if (this.isCurrentlyChecking == false) goto L46;
        switchToAvailable();
        return;
    L46:
        return;
    L16:
        if (r3 != NotificationCenter.proxySettingsChanged) goto L20;
        AndroidUtilities.cancelRunOnUIThread(this.checkProxyAndSwitchRunnable);
        return;
    L20:
        if (r3 == NotificationCenter.didUpdateConnectionState) goto L22;
        return;
    L22:
        if (r4 == UserConfig.selectedAccount) goto L24;
        return;
    L24:
        if (SharedConfig.isProxyEnabled() == true) goto L26;
        return;
    L26:
        if (SharedConfig.proxyRotationEnabled == true) goto L28;
        return;
    L28:
        if (SharedConfig.proxyList.size() > 1) goto L31;
        return;
    L31:
        if (ConnectionsManager.getInstance(r4).getConnectionState() == 4) goto L33;
        AndroidUtilities.cancelRunOnUIThread(this.checkProxyAndSwitchRunnable);
        return;
    L33:
        if (this.isCurrentlyChecking == true) goto L45;
        AndroidUtilities.runOnUIThread(this.checkProxyAndSwitchRunnable, ROTATION_TIMEOUTS.get(SharedConfig.proxyRotationTimeout).intValue() * 1000);
        return;
    }
}
