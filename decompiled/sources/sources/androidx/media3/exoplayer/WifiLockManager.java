package androidx.media3.exoplayer;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Looper;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.common.util.Log;

/* loaded from: classes4.dex */
final class WifiLockManager {
    private boolean enabled;
    private boolean stayAwake;
    private final HandlerWrapper wifiLockHandler;
    private final WifiLockManagerInternal wifiLockManagerInternal;

    public static final class WifiLockManagerInternal {
        private final Context applicationContext;
        private WifiManager.WifiLock wifiLock;

        public WifiLockManagerInternal(Context r1) {
            this.applicationContext = r1;
        }

        public void updateWifiLock(boolean r4, boolean r5) {
            if (r4 == true) goto L4;
        L10:
            WifiManager.WifiLock r3 = this.wifiLock;
            if (r3 != null) goto L13;
            return;
        L13:
            if (r4 == false) goto L17;
            if (r5 == false) goto L17;
            r3.acquire();
            return;
        L17:
            r3.release();
            return;
        L4:
            if (this.wifiLock != null) goto L10;
            WifiManager r0 = (WifiManager) this.applicationContext.getApplicationContext().getSystemService("wifi");
            if (r0 != null) goto L9;
            Log.w("WifiLockManager", "WifiManager is null, therefore not creating the WifiLock.");
            return;
        L9:
            WifiManager.WifiLock r02 = r0.createWifiLock(3, "ExoPlayer:WifiLockManager");
            this.wifiLock = r02;
            r02.setReferenceCounted(false);
            goto L10
        }
    }

    /* renamed from: $r8$lambda$12ZmkP5EWMdLr_g4UBcBPg1-OuQ, reason: not valid java name */
    public static /* synthetic */ void m794$r8$lambda$12ZmkP5EWMdLr_g4UBcBPg1OuQ(WifiLockManager r0, boolean r1, boolean r2) {
        r0.wifiLockManagerInternal.updateWifiLock(r1, r2);
    }

    /* renamed from: $r8$lambda$jyg-wITSXfM3bYGjmHHPi_75LQc, reason: not valid java name */
    public static /* synthetic */ void m795$r8$lambda$jygwITSXfM3bYGjmHHPi_75LQc(WifiLockManager r1, boolean r2) {
        r1.wifiLockManagerInternal.updateWifiLock(true, r2);
    }

    public WifiLockManager(Context r2, Looper r3, Clock r4) {
        this.wifiLockManagerInternal = new WifiLockManagerInternal(r2.getApplicationContext());
        this.wifiLockHandler = r4.createHandler(r3, null);
    }

    public void setEnabled(boolean r4) {
        if (this.enabled != r4) goto L5;
        return;
    L5:
        this.enabled = r4;
        boolean r0 = this.stayAwake;
        this.wifiLockHandler.post(new WifiLockManager$$ExternalSyntheticLambda0(this, r4, r0));
    }

    public void setStayAwake(boolean r3) {
        if (this.stayAwake == r3) goto L10;
        this.stayAwake = r3;
        if (this.enabled == false) goto L9;
        this.wifiLockHandler.post(new WifiLockManager$$ExternalSyntheticLambda1(this, r3));
        return;
    L9:
        return;
    }
}
