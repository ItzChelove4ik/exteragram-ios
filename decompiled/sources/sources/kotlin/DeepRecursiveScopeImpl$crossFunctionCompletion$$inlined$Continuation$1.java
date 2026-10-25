package kotlin;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001d\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0096\u0080\u0004¢\u0006\u0002\u0010\nR\u0015\u0010\u0002\u001a\u00020\u00038VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u000b¸\u0006\u0000"}, d2 = {"kotlin/coroutines/ContinuationKt$Continuation$1", "Lkotlin/coroutines/Continuation;", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "resumeWith", _UrlKt.FRAGMENT_ENCODE_SET, "result", "Lkotlin/Result;", "(Ljava/lang/Object;)V", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nContinuation.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Continuation.kt\nkotlin/coroutines/ContinuationKt$Continuation$1\n+ 2 DeepRecursive.kt\nkotlin/DeepRecursiveScopeImpl\n*L\n1#1,161:1\n180#2,6:162\n*E\n"})
/* loaded from: classes5.dex */
public final class DeepRecursiveScopeImpl$crossFunctionCompletion$$inlined$Continuation$1 implements Continuation<Object> {
    final /* synthetic */ Continuation $cont$inlined;
    final /* synthetic */ CoroutineContext $context;
    final /* synthetic */ Function3 $currentFunction$inlined;
    final /* synthetic */ DeepRecursiveScopeImpl this$0;

    public DeepRecursiveScopeImpl$crossFunctionCompletion$$inlined$Continuation$1(CoroutineContext r1, DeepRecursiveScopeImpl r2, Function3 r3, Continuation r4) {
        this.$context = r1;
        this.this$0 = r2;
        this.$currentFunction$inlined = r3;
        this.$cont$inlined = r4;
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return this.$context;
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(Object r3) {
        DeepRecursiveScopeImpl.access$setFunction$p(this.this$0, this.$currentFunction$inlined);
        DeepRecursiveScopeImpl.access$setCont$p(this.this$0, this.$cont$inlined);
        DeepRecursiveScopeImpl.access$setResult$p(this.this$0, r3);
    }
}
