package androidx.camera.camera2.pipe.compat;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.pipe.compat.CameraStateOpener", f = "RetryingCameraStateOpener.kt", i = {0, 0, 0, 0, 0}, l = {236, 275}, m = "tryOpenCamera-7pD7j80$camera_camera2_pipe", n = {"cameraId", "camera2DeviceCloser", "audioRestrictionController", "attempts", "requestTimestamp"}, s = {"L$0", "L$1", "L$2", "I$0", "J$0"}, v = 1)
/* loaded from: classes3.dex */
public final class CameraStateOpener$tryOpenCamera$1 extends ContinuationImpl {
    int I$0;
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CameraStateOpener this$0;

    public CameraStateOpener$tryOpenCamera$1(CameraStateOpener r1, Continuation<? super CameraStateOpener$tryOpenCamera$1> r2) {
        this.this$0 = r1;
        super(r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r9) {
        this.result = r9;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.m464tryOpenCamera7pD7j80$camera_camera2_pipe(null, 0, 0, null, null, this);
    }
}
