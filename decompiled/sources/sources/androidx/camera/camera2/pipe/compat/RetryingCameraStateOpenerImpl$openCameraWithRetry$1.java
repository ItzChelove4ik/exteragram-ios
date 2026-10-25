package androidx.camera.camera2.pipe.compat;

import com.google.android.gms.cast.MediaError;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.pipe.compat.RetryingCameraStateOpenerImpl", f = "RetryingCameraStateOpener.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2}, l = {418, MediaError.DetailedErrorCode.DASH_INVALID_SEGMENT_INFO, 476}, m = "openCameraWithRetry-aeCOTgg", n = {"cameraId", "camera2DeviceCloser", "isForegroundObserver", "attempts", "requestTimestamp", "cameraId", "camera2DeviceCloser", "isForegroundObserver", "attempts", "it", "requestTimestamp", "cameraId", "camera2DeviceCloser", "isForegroundObserver", "attempts", "it", "requestTimestamp"}, s = {"L$0", "L$1", "L$2", "L$3", "J$0", "L$0", "L$1", "L$2", "L$3", "L$5", "J$0", "L$0", "L$1", "L$2", "L$3", "L$5", "J$0"}, v = 1)
/* loaded from: classes3.dex */
public final class RetryingCameraStateOpenerImpl$openCameraWithRetry$1 extends ContinuationImpl {
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ RetryingCameraStateOpenerImpl this$0;

    public RetryingCameraStateOpenerImpl$openCameraWithRetry$1(RetryingCameraStateOpenerImpl r1, Continuation<? super RetryingCameraStateOpenerImpl$openCameraWithRetry$1> r2) {
        this.this$0 = r1;
        super(r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r2) {
        this.result = r2;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.mo478openCameraWithRetryaeCOTgg(null, null, null, this);
    }
}
