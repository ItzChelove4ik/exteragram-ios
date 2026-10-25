package androidx.camera.camera2.pipe.compat;

import kotlin.Metadata;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/camera/camera2/pipe/compat/ConcurrentSessionSequencer;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "sharedMutex", "Lkotlinx/coroutines/sync/Mutex;", "getSharedMutex", "()Lkotlinx/coroutines/sync/Mutex;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ConcurrentSessionSequencer {
    private final Mutex sharedMutex;

    public ConcurrentSessionSequencer() {
        this.sharedMutex = MutexKt.Mutex$default(false, 1, null);
    }

    public final Mutex getSharedMutex() {
        return this.sharedMutex;
    }
}
