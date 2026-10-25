package androidx.camera.camera2.pipe.graph;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.pipe.graph.Controller3A", f = "Controller3A.kt", i = {0, 0, 0, 0, 0, 0, 0, 0}, l = {373}, m = "lock3A-Qz1gx5w", n = {"aeLockBehavior", "awbLockBehavior", "afTriggerStartAeMode", "lockedCondition", "lockedTimeLimitNs", "afLockBehaviorSanitized", "listener", "frameLimit"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "I$0"}, v = 1)
/* loaded from: classes3.dex */
public final class Controller3A$lock3A$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ Controller3A this$0;

    public Controller3A$lock3A$1(Controller3A r1, Continuation<? super Controller3A$lock3A$1> r2) {
        this.this$0 = r1;
        super(r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r15) {
        this.result = r15;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.m518lock3AQz1gx5w(null, null, null, null, null, null, null, null, null, 0, null, null, this);
    }
}
