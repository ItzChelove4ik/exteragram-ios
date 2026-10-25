package androidx.camera.camera2.impl;

import androidx.camera.camera2.pipe.FrameMetadata;
import kotlin.jvm.functions.Function1;

/* loaded from: classes3.dex */
public final /* synthetic */ class CapturePipelineImpl$$ExternalSyntheticLambda3 implements Function1 {
    public final /* synthetic */ CapturePipelineImpl f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ CapturePipelineImpl$$ExternalSyntheticLambda3(CapturePipelineImpl r1, boolean r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r2) {
        return Boolean.valueOf(CapturePipelineImpl.$r8$lambda$u8rpfFcSEU3zva8xfZhpabtQWUM(this.f$0, this.f$1, (FrameMetadata) r2));
    }
}
