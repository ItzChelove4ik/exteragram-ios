package androidx.camera.camera2.pipe.compat;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.pipe.compat.Camera2DeviceCache", f = "Camera2DeviceCache.kt", i = {0, 0}, l = {169}, m = "getOrInitializeDeviceSetupWrapper-0r8Bogc", n = {"cameraId", "deferred"}, s = {"L$0", "L$1"}, v = 1)
/* loaded from: classes3.dex */
public final class Camera2DeviceCache$getOrInitializeDeviceSetupWrapper$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ Camera2DeviceCache this$0;

    public Camera2DeviceCache$getOrInitializeDeviceSetupWrapper$1(Camera2DeviceCache r1, Continuation<? super Camera2DeviceCache$getOrInitializeDeviceSetupWrapper$1> r2) {
        this.this$0 = r1;
        super(r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r2) {
        this.result = r2;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.m444getOrInitializeDeviceSetupWrapper0r8Bogc(null, this);
    }
}
