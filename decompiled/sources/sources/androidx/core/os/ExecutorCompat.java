package androidx.core.os;

import android.os.Handler;
import androidx.core.util.Preconditions;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
public abstract class ExecutorCompat {

    public static class HandlerExecutor implements Executor {
        private final Handler mHandler;

        public HandlerExecutor(Handler r1) {
            this.mHandler = (Handler) Preconditions.checkNotNull(r1);
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable r2) {
            if (this.mHandler.post((Runnable) Preconditions.checkNotNull(r2)) == false) goto L5;
            return;
        L5:
            ExecutorCompat$HandlerExecutor$$ExternalSyntheticBUOutline0.m(this.mHandler, " is shutting down");
        }
    }

    public static Executor create(Handler r1) {
        return new HandlerExecutor(r1);
    }
}
