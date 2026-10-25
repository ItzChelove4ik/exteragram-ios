package androidx.legacy.content;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;
import android.util.SparseArray;

@Deprecated
/* loaded from: classes3.dex */
public abstract class WakefulBroadcastReceiver extends BroadcastReceiver {
    private static int mNextId;
    private static final SparseArray<PowerManager.WakeLock> sActiveWakeLocks = null;

    static {
        sActiveWakeLocks = new SparseArray();
        mNextId = 1;
    }

    public WakefulBroadcastReceiver() {
    }

    public static boolean completeWakefulIntent(Intent r5) {
        int r52 = r5.getIntExtra("androidx.contentpager.content.wakelockid", 0);
        if (r52 != 0) goto L5;
        return false;
    L5:
        SparseArray<PowerManager.WakeLock> r1 = sActiveWakeLocks;
        monitor-enter(r1);
        PowerManager.WakeLock r2 = r1.get(r52);     // Catch: Throwable -> L12
        if (r2 == null) goto L14;
        r2.release();     // Catch: Throwable -> L12
        r1.remove(r52);     // Catch: Throwable -> L12
        monitor-exit(r1);     // Catch: Throwable -> L12
        return true;
    L14:
        Log.w("WakefulBroadcastReceiv.", "No active wake lock id #" + r52);     // Catch: Throwable -> L12
        monitor-exit(r1);     // Catch: Throwable -> L12
        return true;
    L12:
        th = move-exception;
        throw th;
    }

    public static ComponentName startWakefulService(Context r5, Intent r6) {
        SparseArray<PowerManager.WakeLock> r1 = sActiveWakeLocks;
        monitor-enter(r1);
        int r2 = mNextId;     // Catch: Throwable -> L7
        int r3 = r2 + 1;     // Catch: Throwable -> L7
        mNextId = r3;     // Catch: Throwable -> L7
        if (r3 > 0) goto L9;
        mNextId = 1;     // Catch: Throwable -> L7
    L9:
        r6.putExtra("androidx.contentpager.content.wakelockid", r2);     // Catch: Throwable -> L7
        ComponentName r62 = r5.startService(r6);     // Catch: Throwable -> L7
        if (r62 == null) goto L12;
        PowerManager.WakeLock r52 = ((PowerManager) r5.getSystemService("power")).newWakeLock(1, "androidx.core:wake:" + r62.flattenToShortString());     // Catch: Throwable -> L7
        r52.setReferenceCounted(false);     // Catch: Throwable -> L7
        r52.acquire(60000);     // Catch: Throwable -> L7
        r1.put(r2, r52);     // Catch: Throwable -> L7
        monitor-exit(r1);     // Catch: Throwable -> L7
        return r62;
    L12:
        monitor-exit(r1);     // Catch: Throwable -> L7
        return null;
    L7:
        th = move-exception;
        throw th;
    }
}
