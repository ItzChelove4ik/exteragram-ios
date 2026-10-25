package androidx.camera.camera2.pipe.compat;

import androidx.camera.camera2.pipe.CameraId;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Landroidx/camera/camera2/pipe/compat/RequestCloseById;", "Landroidx/camera/camera2/pipe/compat/CameraRequest;", "activeCameraId", "Landroidx/camera/camera2/pipe/CameraId;", "<init>", "(Ljava/lang/String;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getActiveCameraId-Dz_R5H8", "()Ljava/lang/String;", "Ljava/lang/String;", "deferred", "Lkotlinx/coroutines/CompletableDeferred;", _UrlKt.FRAGMENT_ENCODE_SET, "getDeferred", "()Lkotlinx/coroutines/CompletableDeferred;", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RequestCloseById extends CameraRequest {
    private final String activeCameraId;
    private final CompletableDeferred<Unit> deferred;

    private RequestCloseById(String r2) {
        super(null);
        this.activeCameraId = r2;
        this.deferred = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
    }

    public /* synthetic */ RequestCloseById(String r1, DefaultConstructorMarker r2) {
        this(r1);
    }

    /* renamed from: getActiveCameraId-Dz_R5H8, reason: not valid java name */
    public final String m474getActiveCameraIdDz_R5H8() {
        return this.activeCameraId;
    }

    public final CompletableDeferred<Unit> getDeferred() {
        return this.deferred;
    }

    public String toString() {
        return "RequestCloseById(" + CameraId.m232toStringimpl(this.activeCameraId) + ')';
    }
}
