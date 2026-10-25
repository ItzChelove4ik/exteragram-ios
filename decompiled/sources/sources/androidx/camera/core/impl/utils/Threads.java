package androidx.camera.core.impl.utils;

import android.os.Handler;
import android.os.Looper;
import androidx.core.util.Preconditions;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public abstract class Threads {
    public static /* synthetic */ void $r8$lambda$QhFdgKsW1QVHuVXVeUeDa68bDs0(Runnable r0, CountDownLatch r1) {
        r0.run();     // Catch: Throwable -> L5
        r1.countDown();
        return;
    L5:
        th = move-exception;
        r1.countDown();
        throw th;
    }

    public static void checkMainThread() {
        Preconditions.checkState(isMainThread(), "Not in application's main thread");
    }

    private static Handler getMainHandler() {
        return new Handler(Looper.getMainLooper());
    }

    public static boolean isMainThread() {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) goto L6;
        return true;
    L6:
        return false;
    }

    public static void runOnMain(Runnable r1) {
        if (isMainThread() == false) goto L6;
        r1.run();
        return;
    L6:
        Preconditions.checkState(getMainHandler().post(r1), "Unable to post to main thread");
    }

    public static void runOnMainSync(Runnable r3) {
        if (isMainThread() == false) goto L6;
        r3.run();
        return;
    L6:
        CountDownLatch r0 = new CountDownLatch(1);
        Preconditions.checkState(getMainHandler().post(new Threads$$ExternalSyntheticLambda0(r3, r0)), "Unable to post to main thread");
    L12:
        e = move-exception;
        throw new InterruptedRuntimeException(e);
    L8:
        if (r0.await(NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS, TimeUnit.MILLISECONDS) == false) goto L11;
        return;
    L11:
        throw new IllegalStateException("Timeout to wait main thread execution");     // Catch: InterruptedException -> L12
    }
}
