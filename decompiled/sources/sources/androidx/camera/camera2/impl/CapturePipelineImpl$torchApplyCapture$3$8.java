package androidx.camera.camera2.impl;

import androidx.camera.camera2.pipe.FrameInfo;
import androidx.camera.core.impl.ConvergenceUtils;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CapturePipelineImpl$torchApplyCapture$3$8 implements Function1<FrameInfo, Boolean> {
    final /* synthetic */ CapturePipelineImpl this$0;

    public CapturePipelineImpl$torchApplyCapture$3$8(CapturePipelineImpl r1) {
        this.this$0 = r1;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Boolean invoke2(FrameInfo r1) {
        return Boolean.valueOf(ConvergenceUtils.is3AConverged(CapturePipelineImpl.access$toCameraCaptureResult(this.this$0, r1.getMetadata()), true));
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Boolean invoke(FrameInfo r1) {
        return invoke2(r1);
    }
}
