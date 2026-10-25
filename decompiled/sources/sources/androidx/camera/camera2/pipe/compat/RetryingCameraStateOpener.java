package androidx.camera.camera2.pipe.compat;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b`\u0018\u00002\u00020\u0001J6\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tH¦@¢\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¢\u0006\u0004\b\u0010\u0010\u0011J\b\u0010\u0012\u001a\u00020\nH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0013À\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/compat/RetryingCameraStateOpener;", _UrlKt.FRAGMENT_ENCODE_SET, "openCameraWithRetry", "Landroidx/camera/camera2/pipe/compat/OpenCameraResult;", "cameraId", "Landroidx/camera/camera2/pipe/CameraId;", "camera2DeviceCloser", "Landroidx/camera/camera2/pipe/compat/Camera2DeviceCloser;", "isForegroundObserver", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "openCameraWithRetry-aeCOTgg", "(Ljava/lang/String;Landroidx/camera/camera2/pipe/compat/Camera2DeviceCloser;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "openAndAwaitCameraWithRetry", "Landroidx/camera/camera2/pipe/compat/AwaitOpenCameraResult;", "openAndAwaitCameraWithRetry-0r8Bogc", "(Ljava/lang/String;Landroidx/camera/camera2/pipe/compat/Camera2DeviceCloser;)Landroidx/camera/camera2/pipe/compat/AwaitOpenCameraResult;", "cancelOpen", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface RetryingCameraStateOpener {
    /* renamed from: $r8$lambda$P9BprrKm-ujFTusqBJ7x2IEeI6M, reason: not valid java name */
    static boolean m475$r8$lambda$P9BprrKmujFTusqBJ7x2IEeI6M(Unit r0) {
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: openCameraWithRetry-aeCOTgg$default, reason: not valid java name */
    static /* synthetic */ Object m476openCameraWithRetryaeCOTgg$default(RetryingCameraStateOpener r0, String r1, Camera2DeviceCloser r2, Function1 r3, Continuation r4, int r5, Object r6) {
        if (r6 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: openCameraWithRetry-aeCOTgg");
        return null;
    L4:
        if ((r5 & 4) == 0) goto L7;
        r3 = new RetryingCameraStateOpener$$ExternalSyntheticLambda0();
    L7:
        return r0.mo478openCameraWithRetryaeCOTgg(r1, r2, r3, r4);
    }

    void cancelOpen();

    /* renamed from: openAndAwaitCameraWithRetry-0r8Bogc, reason: not valid java name */
    AwaitOpenCameraResult mo477openAndAwaitCameraWithRetry0r8Bogc(String r1, Camera2DeviceCloser r2);

    /* renamed from: openCameraWithRetry-aeCOTgg, reason: not valid java name */
    Object mo478openCameraWithRetryaeCOTgg(String r1, Camera2DeviceCloser r2, Function1<? super Unit, Boolean> r3, Continuation<? super OpenCameraResult> r4);
}
