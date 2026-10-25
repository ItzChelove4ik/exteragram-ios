package kotlinx.coroutines.flow;

import com.android.dx.io.Opcodes;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__EmittersKt", f = "Emitters.kt", i = {0, 0, 0}, l = {Opcodes.MUL_INT_LIT16}, m = "invokeSafely$FlowKt__EmittersKt", n = {"$this$invokeSafely", "action", "cause"}, s = {"L$0", "L$1", "L$2"}, v = 1)
/* loaded from: classes5.dex */
public final class FlowKt__EmittersKt$invokeSafely$1<T> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;

    public FlowKt__EmittersKt$invokeSafely$1(Continuation<? super FlowKt__EmittersKt$invokeSafely$1> r1) {
        super(r1);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r2) {
        this.result = r2;
        this.label |= Integer.MIN_VALUE;
        return FlowKt__EmittersKt.access$invokeSafely$FlowKt__EmittersKt(null, null, null, this);
    }
}
