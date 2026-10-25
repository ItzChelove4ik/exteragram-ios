package androidx.camera.camera2.pipe.compat;

import androidx.camera.camera2.pipe.CameraError;
import kotlin.Metadata;
import kotlinx.coroutines.flow.Flow;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b`\u0018\u00002\u00020\u0001J\u001b\u0010\u0007\u001a\u00020\u00042\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006R\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\b8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0003"}, d2 = {"Landroidx/camera/camera2/pipe/compat/VirtualCamera;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraError;", "lastCameraError", _UrlKt.FRAGMENT_ENCODE_SET, "disconnect-TPqeGZw", "(Landroidx/camera/camera2/pipe/CameraError;)V", "disconnect", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/camera/camera2/pipe/compat/CameraState;", "getState", "()Lkotlinx/coroutines/flow/Flow;", "state", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface VirtualCamera {
    /* renamed from: disconnect-TPqeGZw$default, reason: not valid java name */
    static /* synthetic */ void m485disconnectTPqeGZw$default(VirtualCamera r0, CameraError r1, int r2, Object r3) {
        if (r3 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: disconnect-TPqeGZw");
        return;
    L4:
        if ((r2 & 1) == 0) goto L6;
        r1 = null;
    L6:
        r0.mo486disconnectTPqeGZw(r1);
    }

    /* renamed from: disconnect-TPqeGZw, reason: not valid java name */
    void mo486disconnectTPqeGZw(CameraError r1);

    Flow<CameraState> getState();
}
