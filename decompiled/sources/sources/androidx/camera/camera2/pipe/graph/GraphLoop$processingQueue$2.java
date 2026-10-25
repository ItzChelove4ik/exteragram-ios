package androidx.camera.camera2.pipe.graph;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* synthetic */ class GraphLoop$processingQueue$2 extends FunctionReferenceImpl implements Function2<List<GraphCommand>, Continuation<? super Unit>, Object>, SuspendFunction {
    public GraphLoop$processingQueue$2(Object r8) {
        super(2, r8, GraphLoop.class, "process", "process(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", 0);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(List<GraphCommand> r1, Continuation<? super Unit> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(List<GraphCommand> r1, Continuation<? super Unit> r2) {
        return GraphLoop.access$process((GraphLoop) this.receiver, r1, r2);
    }
}
