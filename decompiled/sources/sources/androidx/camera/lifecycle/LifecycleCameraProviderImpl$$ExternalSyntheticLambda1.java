package androidx.camera.lifecycle;

import androidx.camera.core.impl.utils.futures.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import kotlin.jvm.functions.Function1;

/* loaded from: classes3.dex */
public final /* synthetic */ class LifecycleCameraProviderImpl$$ExternalSyntheticLambda1 implements AsyncFunction {
    public final /* synthetic */ Function1 f$0;

    public /* synthetic */ LifecycleCameraProviderImpl$$ExternalSyntheticLambda1(Function1 r1) {
        this.f$0 = r1;
    }

    @Override // androidx.camera.core.impl.utils.futures.AsyncFunction
    public final ListenableFuture apply(Object r1) {
        return LifecycleCameraProviderImpl.m627$r8$lambda$1d6fK4V_iwXm_23qvpSvEcBbqc(this.f$0, r1);
    }
}
