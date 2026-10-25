package androidx.work.impl.background.systemalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.work.Logger;
import androidx.work.impl.WorkManagerImpl;

/* loaded from: classes.dex */
public class RescheduleReceiver extends BroadcastReceiver {
    private static final String TAG = null;

    static {
        TAG = Logger.tagWithPrefix("RescheduleReceiver");
    }

    public RescheduleReceiver() {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context r5, Intent r6) {
        Logger.get().debug(TAG, "Received intent " + r6);
        WorkManagerImpl.getInstance(r5).setReschedulePendingResult(goAsync());     // Catch: IllegalStateException -> L5
        return;
    L5:
        e = move-exception;
        Logger.get().error(TAG, "Cannot reschedule jobs. WorkManager needs to be initialized via a ContentProvider#onCreate() or an Application#onCreate().", e);
    }
}
