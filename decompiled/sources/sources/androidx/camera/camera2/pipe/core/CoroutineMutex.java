package androidx.camera.camera2.pipe.core;

import kotlin.Metadata;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/camera/camera2/pipe/core/CoroutineMutex;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "getMutex$camera_camera2_pipe", "()Lkotlinx/coroutines/sync/Mutex;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CoroutineMutex {
    private final Mutex mutex;

    public CoroutineMutex() {
        this.mutex = MutexKt.Mutex$default(false, 1, null);
    }

    public final Mutex getMutex$camera_camera2_pipe() {
        return this.mutex;
    }
}
