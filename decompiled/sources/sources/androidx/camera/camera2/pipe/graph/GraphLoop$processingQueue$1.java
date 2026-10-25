package androidx.camera.camera2.pipe.graph;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* synthetic */ class GraphLoop$processingQueue$1 extends FunctionReferenceImpl implements Function1<List<? extends GraphCommand>, Unit> {
    public GraphLoop$processingQueue$1(Object r8) {
        super(1, r8, GraphLoop.class, "finalizeUnprocessedCommands", "finalizeUnprocessedCommands(Ljava/util/List;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(List<? extends GraphCommand> r1) {
        invoke2(r1);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(List<? extends GraphCommand> r1) {
        GraphLoop.access$finalizeUnprocessedCommands((GraphLoop) this.receiver, r1);
    }
}
