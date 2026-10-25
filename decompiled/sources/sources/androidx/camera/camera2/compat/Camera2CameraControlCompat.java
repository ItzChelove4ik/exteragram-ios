package androidx.camera.camera2.compat;

import androidx.camera.camera2.impl.UseCaseCameraRequestControl;
import androidx.camera.camera2.interop.CaptureRequestOptions;
import androidx.camera.camera2.pipe.Request;
import kotlin.Metadata;
import kotlinx.coroutines.Deferred;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00020\u0002H&¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0004H&¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\u0004H&¢\u0006\u0004\b\u000b\u0010\nJ+\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u00102\b\u0010\r\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000eH&¢\u0006\u0004\b\u0012\u0010\u0013ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0014À\u0006\u0003"}, d2 = {"Landroidx/camera/camera2/compat/Camera2CameraControlCompat;", "Landroidx/camera/camera2/pipe/Request$Listener;", "Landroidx/camera/camera2/interop/CaptureRequestOptions;", "bundle", _UrlKt.FRAGMENT_ENCODE_SET, "addRequestOption", "(Landroidx/camera/camera2/interop/CaptureRequestOptions;)V", "getRequestOption", "()Landroidx/camera/camera2/interop/CaptureRequestOptions;", "clearRequestOption", "()V", "cancelCurrentTask", "Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "requestControl", _UrlKt.FRAGMENT_ENCODE_SET, "cancelPreviousTask", "Lkotlinx/coroutines/Deferred;", "Ljava/lang/Void;", "applyAsync", "(Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;Z)Lkotlinx/coroutines/Deferred;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface Camera2CameraControlCompat extends Request.Listener {
    static /* synthetic */ Deferred applyAsync$default(Camera2CameraControlCompat r0, UseCaseCameraRequestControl r1, boolean r2, int r3, Object r4) {
        if (r4 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: applyAsync");
        return null;
    L4:
        if ((r3 & 2) == 0) goto L7;
        r2 = true;
    L7:
        return r0.applyAsync(r1, r2);
    }

    void addRequestOption(CaptureRequestOptions r1);

    Deferred<Void> applyAsync(UseCaseCameraRequestControl r1, boolean r2);

    void cancelCurrentTask();

    void clearRequestOption();

    CaptureRequestOptions getRequestOption();
}
