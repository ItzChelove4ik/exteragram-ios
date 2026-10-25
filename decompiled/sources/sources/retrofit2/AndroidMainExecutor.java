package retrofit2;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
final class AndroidMainExecutor implements Executor {
    private final Handler handler;

    public AndroidMainExecutor() {
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable r1) {
        this.handler.post(r1);
    }
}
