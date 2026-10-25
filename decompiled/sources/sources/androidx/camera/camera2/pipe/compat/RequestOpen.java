package androidx.camera.camera2.pipe.compat;

import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.graph.GraphListener;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0011\b\u0080\b\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\t0\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0011\u001a\u00020\u0010HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0014\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0018\u001a\u00020\t2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016HÖ\u0003¢\u0006\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010 \u001a\u0004\b!\u0010\"R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010#\u001a\u0004\b\n\u0010$R#\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\t0\u000b8\u0006¢\u0006\f\n\u0004\b\r\u0010%\u001a\u0004\b\r\u0010&¨\u0006'"}, d2 = {"Landroidx/camera/camera2/pipe/compat/RequestOpen;", "Landroidx/camera/camera2/pipe/compat/CameraRequest;", "Landroidx/camera/camera2/pipe/compat/VirtualCameraState;", "virtualCamera", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraId;", "sharedCameraIds", "Landroidx/camera/camera2/pipe/graph/GraphListener;", "graphListener", _UrlKt.FRAGMENT_ENCODE_SET, "isPrewarm", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, "isForegroundObserver", "<init>", "(Landroidx/camera/camera2/pipe/compat/VirtualCameraState;Ljava/util/List;Landroidx/camera/camera2/pipe/graph/GraphListener;ZLkotlin/jvm/functions/Function1;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", _UrlKt.FRAGMENT_ENCODE_SET, "other", "equals", "(Ljava/lang/Object;)Z", "Landroidx/camera/camera2/pipe/compat/VirtualCameraState;", "getVirtualCamera", "()Landroidx/camera/camera2/pipe/compat/VirtualCameraState;", "Ljava/util/List;", "getSharedCameraIds", "()Ljava/util/List;", "Landroidx/camera/camera2/pipe/graph/GraphListener;", "getGraphListener", "()Landroidx/camera/camera2/pipe/graph/GraphListener;", "Z", "()Z", "Lkotlin/jvm/functions/Function1;", "()Lkotlin/jvm/functions/Function1;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RequestOpen extends CameraRequest {
    private final GraphListener graphListener;
    private final Function1<Unit, Boolean> isForegroundObserver;
    private final boolean isPrewarm;
    private final List<CameraId> sharedCameraIds;
    private final VirtualCameraState virtualCamera;

    /* JADX WARN: Multi-variable type inference failed */
    public RequestOpen(VirtualCameraState r2, List<CameraId> r3, GraphListener r4, boolean r5, Function1<? super Unit, Boolean> r6) {
        super(null);
        this.virtualCamera = r2;
        this.sharedCameraIds = r3;
        this.graphListener = r4;
        this.isPrewarm = r5;
        this.isForegroundObserver = r6;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof RequestOpen) == true) goto L8;
        return false;
    L8:
        RequestOpen r52 = (RequestOpen) r5;
        if (Intrinsics.areEqual(this.virtualCamera, r52.virtualCamera) == true) goto L12;
        return false;
    L12:
        if (Intrinsics.areEqual(this.sharedCameraIds, r52.sharedCameraIds) == true) goto L15;
        return false;
    L15:
        if (Intrinsics.areEqual(this.graphListener, r52.graphListener) == true) goto L18;
        return false;
    L18:
        if (this.isPrewarm == r52.isPrewarm) goto L21;
        return false;
    L21:
        if (Intrinsics.areEqual(this.isForegroundObserver, r52.isForegroundObserver) == true) goto L23;
        return false;
    L23:
        return true;
    }

    public final List<CameraId> getSharedCameraIds() {
        return this.sharedCameraIds;
    }

    public final VirtualCameraState getVirtualCamera() {
        return this.virtualCamera;
    }

    public int hashCode() {
        return (((((((this.virtualCamera.hashCode() * 31) + this.sharedCameraIds.hashCode()) * 31) + this.graphListener.hashCode()) * 31) + Boolean.hashCode(this.isPrewarm)) * 31) + this.isForegroundObserver.hashCode();
    }

    public final Function1<Unit, Boolean> isForegroundObserver() {
        return this.isForegroundObserver;
    }

    public final boolean isPrewarm() {
        return this.isPrewarm;
    }

    public String toString() {
        return "RequestOpen(virtualCamera=" + this.virtualCamera + ", sharedCameraIds=" + this.sharedCameraIds + ", graphListener=" + this.graphListener + ", isPrewarm=" + this.isPrewarm + ", isForegroundObserver=" + this.isForegroundObserver + ')';
    }
}
