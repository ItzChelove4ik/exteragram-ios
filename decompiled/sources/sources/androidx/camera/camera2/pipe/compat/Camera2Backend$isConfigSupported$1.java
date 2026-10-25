package androidx.camera.camera2.pipe.compat;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.pipe.compat.Camera2Backend", f = "Camera2Backend.kt", i = {0, 1, 1, 1}, l = {111, 129}, m = "isConfigSupported-NpXggIU", n = {"graphConfig", "graphConfig", "cameraDeviceSetupCompat", "sessionConfig"}, s = {"L$0", "L$0", "L$1", "L$2"}, v = 1)
/* loaded from: classes3.dex */
public final class Camera2Backend$isConfigSupported$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ Camera2Backend this$0;

    public Camera2Backend$isConfigSupported$1(Camera2Backend r1, Continuation<? super Camera2Backend$isConfigSupported$1> r2) {
        this.this$0 = r1;
        super(r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r2) {
        this.result = r2;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.mo150isConfigSupportedNpXggIU(null, this);
    }
}
