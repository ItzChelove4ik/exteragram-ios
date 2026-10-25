package androidx.camera.camera2.pipe.compat;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.pipe.compat.PruningCamera2DeviceManager", f = "Camera2DeviceManager.kt", i = {0, 0, 0, 1, 1}, l = {526, 533}, m = "retrieveActiveCamera-RzXb1QE", n = {"cameraId", "requestOpen", "activeCamera", "cameraId", "requestOpen"}, s = {"L$0", "L$1", "L$3", "L$0", "L$1"}, v = 1)
/* loaded from: classes3.dex */
public final class PruningCamera2DeviceManager$retrieveActiveCamera$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PruningCamera2DeviceManager this$0;

    public PruningCamera2DeviceManager$retrieveActiveCamera$1(PruningCamera2DeviceManager r1, Continuation<? super PruningCamera2DeviceManager$retrieveActiveCamera$1> r2) {
        this.this$0 = r1;
        super(r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r2) {
        this.result = r2;
        this.label |= Integer.MIN_VALUE;
        return PruningCamera2DeviceManager.m469access$retrieveActiveCameraRzXb1QE(this.this$0, null, null, this);
    }
}
