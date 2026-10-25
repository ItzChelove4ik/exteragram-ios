package androidx.camera.camera2.pipe.core;

import android.os.Handler;
import androidx.core.os.ExecutorCompat$HandlerExecutor$$ExternalSyntheticBUOutline0;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Landroidx/camera/camera2/pipe/core/HandlerExecutor;", "Ljava/util/concurrent/Executor;", "handler", "Landroid/os/Handler;", "<init>", "(Landroid/os/Handler;)V", "execute", _UrlKt.FRAGMENT_ENCODE_SET, "command", "Ljava/lang/Runnable;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class HandlerExecutor implements Executor {
    private final Handler handler;

    public HandlerExecutor(Handler r1) {
        this.handler = r1;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable r2) {
        if (this.handler.post(r2) == false) goto L5;
        return;
    L5:
        ExecutorCompat$HandlerExecutor$$ExternalSyntheticBUOutline0.m(this.handler, " is shutting down");
    }
}
