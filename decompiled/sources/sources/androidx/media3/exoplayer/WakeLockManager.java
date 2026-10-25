package androidx.media3.exoplayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Looper;
import android.os.PowerManager;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.common.util.Log;

/* loaded from: classes4.dex */
final class WakeLockManager {
    private boolean enabled;
    private boolean stayAwake;
    private final HandlerWrapper wakeLockHandler;
    private final WakeLockManagerInternal wakeLockManagerInternal;

    public static final class WakeLockManagerInternal {
        private final Context applicationContext;
        private PowerManager.WakeLock wakeLock;

        public WakeLockManagerInternal(Context r1) {
            this.applicationContext = r1;
        }

        @SuppressLint({"WakelockTimeout"})
        public void updateWakeLock(boolean r4, boolean r5) {
            if (r4 == true) goto L4;
        L10:
            PowerManager.WakeLock r3 = this.wakeLock;
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
            if (this.wakeLock != null) goto L10;
            PowerManager r0 = (PowerManager) this.applicationContext.getSystemService("power");
            if (r0 != null) goto L9;
            Log.w("WakeLockManager", "PowerManager is null, therefore not creating the WakeLock.");
            return;
        L9:
            PowerManager.WakeLock r02 = r0.newWakeLock(1, "ExoPlayer:WakeLockManager");
            this.wakeLock = r02;
            r02.setReferenceCounted(false);
            goto L10
        }
    }

    public static /* synthetic */ void $r8$lambda$Je_LlpXVeFIMb6podN33_FfRN0s(WakeLockManager r0, boolean r1, boolean r2) {
        r0.wakeLockManagerInternal.updateWakeLock(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$yigYl5XHX6XpokeGgU8o5AUstYs(WakeLockManager r1, boolean r2) {
        r1.wakeLockManagerInternal.updateWakeLock(true, r2);
    }

    public WakeLockManager(Context r2, Looper r3, Clock r4) {
        this.wakeLockManagerInternal = new WakeLockManagerInternal(r2.getApplicationContext());
        this.wakeLockHandler = r4.createHandler(r3, null);
    }

    public void setEnabled(boolean r4) {
        if (this.enabled != r4) goto L5;
        return;
    L5:
        this.enabled = r4;
        boolean r0 = this.stayAwake;
        this.wakeLockHandler.post(new WakeLockManager$$ExternalSyntheticLambda1(this, r4, r0));
    }

    public void setStayAwake(boolean r3) {
        if (this.stayAwake == r3) goto L10;
        this.stayAwake = r3;
        if (this.enabled == false) goto L9;
        this.wakeLockHandler.post(new WakeLockManager$$ExternalSyntheticLambda0(this, r3));
        return;
    L9:
        return;
    }
}
