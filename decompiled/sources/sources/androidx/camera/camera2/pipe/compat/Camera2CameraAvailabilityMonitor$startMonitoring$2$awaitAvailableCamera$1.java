package androidx.camera.camera2.pipe.compat;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.pipe.compat.Camera2CameraAvailabilityMonitor$startMonitoring$2", f = "RetryingCameraStateOpener.kt", i = {0}, l = {184}, m = "awaitAvailableCamera", n = {"listener"}, s = {"L$0"}, v = 1)
/* loaded from: classes3.dex */
public final class Camera2CameraAvailabilityMonitor$startMonitoring$2$awaitAvailableCamera$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ Camera2CameraAvailabilityMonitor$startMonitoring$2 this$0;

    public Camera2CameraAvailabilityMonitor$startMonitoring$2$awaitAvailableCamera$1(Camera2CameraAvailabilityMonitor$startMonitoring$2 r1, Continuation<? super Camera2CameraAvailabilityMonitor$startMonitoring$2$awaitAvailableCamera$1> r2) {
        this.this$0 = r1;
        super(r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r3) {
        this.result = r3;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.awaitAvailableCamera(0, this);
    }
}
