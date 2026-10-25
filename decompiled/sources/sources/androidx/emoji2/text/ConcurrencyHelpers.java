package androidx.emoji2.text;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
abstract class ConcurrencyHelpers {

    public static class Handler28Impl {
        public static Handler createAsync(Looper r0) {
            return Handler.createAsync(r0);
        }
    }

    public static /* synthetic */ Thread $r8$lambda$hDWNjMgTS47ccxPkL8ebwFGVHg4(String r1, Runnable r2) {
        Thread r0 = new Thread(r2, r1);
        r0.setPriority(10);
        return r0;
    }

    public static ThreadPoolExecutor createBackgroundPriorityExecutor(String r8) {
        ConcurrencyHelpers$$ExternalSyntheticLambda0 r7 = new ConcurrencyHelpers$$ExternalSyntheticLambda0(r8);
        LinkedBlockingDeque r6 = new LinkedBlockingDeque();
        ThreadPoolExecutor r0 = new ThreadPoolExecutor(0, 1, 15, TimeUnit.SECONDS, r6, r7);
        r0.allowCoreThreadTimeOut(true);
        return r0;
    }

    public static Handler mainHandlerAsync() {
        if (Build.VERSION.SDK_INT < 28) goto L7;
        return Handler28Impl.createAsync(Looper.getMainLooper());
    L7:
        return new Handler(Looper.getMainLooper());
    }
}
