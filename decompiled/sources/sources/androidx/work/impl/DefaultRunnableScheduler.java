package androidx.work.impl;

import android.os.Handler;
import android.os.Looper;
import androidx.core.os.HandlerCompat;
import androidx.work.RunnableScheduler;

/* loaded from: classes.dex */
public class DefaultRunnableScheduler implements RunnableScheduler {
    private final Handler mHandler;

    public DefaultRunnableScheduler() {
        this.mHandler = HandlerCompat.createAsync(Looper.getMainLooper());
    }

    @Override // androidx.work.RunnableScheduler
    public void cancel(Runnable r1) {
        this.mHandler.removeCallbacks(r1);
    }

    @Override // androidx.work.RunnableScheduler
    public void scheduleWithDelay(long r1, Runnable r3) {
        this.mHandler.postDelayed(r3, r1);
    }
}
