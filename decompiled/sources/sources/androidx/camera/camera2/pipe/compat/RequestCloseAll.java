package androidx.camera.camera2.pipe.compat;

import kotlin.Metadata;
import kotlin.Unit;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\t\u001a\u00020\nH\u0016R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Landroidx/camera/camera2/pipe/compat/RequestCloseAll;", "Landroidx/camera/camera2/pipe/compat/CameraRequest;", "<init>", "()V", "deferred", "Lkotlinx/coroutines/CompletableDeferred;", _UrlKt.FRAGMENT_ENCODE_SET, "getDeferred", "()Lkotlinx/coroutines/CompletableDeferred;", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RequestCloseAll extends CameraRequest {
    private final CompletableDeferred<Unit> deferred;

    public RequestCloseAll() {
        super(null);
        this.deferred = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
    }

    public final CompletableDeferred<Unit> getDeferred() {
        return this.deferred;
    }

    public String toString() {
        return "RequestCloseAll";
    }
}
