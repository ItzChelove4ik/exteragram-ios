package androidx.camera.camera2.pipe.internal;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.pipe.internal.GraphSessionLock", f = "GraphSessionLock.kt", i = {0}, l = {105}, m = "acquireToken$camera_camera2_pipe", n = {"$this$acquireToken$iv"}, s = {"L$0"}, v = 1)
/* loaded from: classes3.dex */
public final class GraphSessionLock$acquireToken$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ GraphSessionLock this$0;

    public GraphSessionLock$acquireToken$1(GraphSessionLock r1, Continuation<? super GraphSessionLock$acquireToken$1> r2) {
        this.this$0 = r1;
        super(r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r2) {
        this.result = r2;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.acquireToken$camera_camera2_pipe(this);
    }
}
