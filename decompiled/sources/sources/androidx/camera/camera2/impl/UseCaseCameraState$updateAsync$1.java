package androidx.camera.camera2.impl;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.impl.UseCaseCameraState", f = "UseCaseCameraState.kt", i = {0}, l = {150}, m = "updateAsync-Tp9XwKQ", n = {"result"}, s = {"L$0"}, v = 1)
/* loaded from: classes3.dex */
public final class UseCaseCameraState$updateAsync$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ UseCaseCameraState this$0;

    public UseCaseCameraState$updateAsync$1(UseCaseCameraState r1, Continuation<? super UseCaseCameraState$updateAsync$1> r2) {
        this.this$0 = r1;
        super(r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r10) {
        this.result = r10;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.m104updateAsyncTp9XwKQ(null, false, null, false, null, null, null, this);
    }
}
