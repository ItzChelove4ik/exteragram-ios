package androidx.work.impl.diagnostics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.work.Logger;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.impl.workers.DiagnosticsWorker;

/* loaded from: classes4.dex */
public class DiagnosticsReceiver extends BroadcastReceiver {
    private static final String TAG = null;

    static {
        TAG = Logger.tagWithPrefix("DiagnosticsRcvr");
    }

    public DiagnosticsReceiver() {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context r2, Intent r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        Logger.get().debug(TAG, "Requesting diagnostics");
        WorkManager.getInstance(r2).enqueue(OneTimeWorkRequest.from(DiagnosticsWorker.class));     // Catch: IllegalStateException -> L7
        return;
    L7:
        e = move-exception;
        Logger.get().error(TAG, "WorkManager is not initialized", e);
    }
}
