package androidx.camera.camera2.pipe.core;

import kotlin.Metadata;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.coroutines.sync.Mutex;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.BuildConfig;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\f\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Landroidx/camera/camera2/pipe/core/MutexToken;", "Landroidx/camera/camera2/pipe/core/Token;", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "<init>", "(Lkotlinx/coroutines/sync/Mutex;)V", "_released", "Lkotlinx/atomicfu/AtomicBoolean;", "released", _UrlKt.FRAGMENT_ENCODE_SET, "getReleased", "()Z", BuildConfig.BUILD_TYPE, "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MutexToken implements Token {
    private final AtomicBoolean _released;
    private final Mutex mutex;

    public MutexToken(Mutex r1) {
        this.mutex = r1;
        this._released = AtomicFU.atomic(false);
    }

    @Override // androidx.camera.camera2.pipe.core.Token
    public boolean getReleased() {
        return this._released.getValue();
    }

    @Override // androidx.camera.camera2.pipe.core.Token
    public boolean release() {
        if (this._released.compareAndSet(false, true) == false) goto L6;
        Mutex.DefaultImpls.unlock$default(this.mutex, null, 1, null);
        return true;
    L6:
        return false;
    }
}
