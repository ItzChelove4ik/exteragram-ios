package androidx.mediarouter.media;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

/* loaded from: classes4.dex */
class MediaRouterActiveScanThrottlingHelper {
    static final long MAX_ACTIVE_SCAN_DURATION_MS = 30000;
    private boolean mActiveScan;
    private long mCurrentTime;
    private final Handler mHandler;
    private long mSuppressActiveScanTimeout;
    private final Runnable mUpdateDiscoveryRequestRunnable;

    public MediaRouterActiveScanThrottlingHelper(Runnable r3) {
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mUpdateDiscoveryRequestRunnable = r3;
    }

    public boolean finalizeActiveScanAndScheduleSuppressActiveScanRunnable() {
        if (this.mActiveScan == false) goto L8;
        long r0 = this.mSuppressActiveScanTimeout;
        if (r0 <= 0) goto L8;
        this.mHandler.postDelayed(this.mUpdateDiscoveryRequestRunnable, r0);
    L8:
        return this.mActiveScan;
    }

    public void requestActiveScan(boolean r7, long r8) {
        if (r7 == false) goto L9;
        long r0 = this.mCurrentTime;
        if ((r0 - r8) < 30000) goto L7;
        return;
    L7:
        this.mSuppressActiveScanTimeout = Math.max(this.mSuppressActiveScanTimeout, (r8 + 30000) - r0);
        this.mActiveScan = true;
        return;
    }

    public void reset() {
        this.mSuppressActiveScanTimeout = 0;
        this.mActiveScan = false;
        this.mCurrentTime = SystemClock.elapsedRealtime();
        this.mHandler.removeCallbacks(this.mUpdateDiscoveryRequestRunnable);
    }
}
