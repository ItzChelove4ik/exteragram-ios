package androidx.camera.camera2.compat.workaround;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.compat.workaround.CapturePipelineTorchCorrection", f = "CapturePipelineTorchCorrection.kt", i = {0}, l = {75}, m = "submitStillCaptures-BvXKQx0", n = {"needCorrectTorchState"}, s = {"Z$0"}, v = 1)
/* loaded from: classes3.dex */
public final class CapturePipelineTorchCorrection$submitStillCaptures$1 extends ContinuationImpl {
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CapturePipelineTorchCorrection this$0;

    public CapturePipelineTorchCorrection$submitStillCaptures$1(CapturePipelineTorchCorrection r1, Continuation<? super CapturePipelineTorchCorrection$submitStillCaptures$1> r2) {
        this.this$0 = r1;
        super(r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r9) {
        this.result = r9;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.mo33submitStillCapturesBvXKQx0(null, 0, null, 0, 0, 0, this);
    }
}
