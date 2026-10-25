package androidx.camera.camera2.pipe.internal;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.pipe.internal.GraphSessionLock$asyncUndispatched$result$1", f = "GraphSessionLock.kt", i = {}, l = {90}, m = "invokeSuspend", n = {}, s = {}, v = 1)
/* loaded from: classes3.dex */
public final class GraphSessionLock$asyncUndispatched$result$1<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
    final /* synthetic */ Function2<CoroutineScope, Continuation<? super T>, Object> $block;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: Multi-variable type inference failed */
    public GraphSessionLock$asyncUndispatched$result$1(Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> r1, Continuation<? super GraphSessionLock$asyncUndispatched$result$1> r2) {
        this.$block = r1;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
        GraphSessionLock$asyncUndispatched$result$1 r0 = new GraphSessionLock$asyncUndispatched$result$1(this.$block, r3);
        r0.L$0 = r2;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Object r2) {
        return invoke(r1, (Continuation) r2);
    }

    public final Object invoke(CoroutineScope r1, Continuation<? super T> r2) {
        return ((GraphSessionLock$asyncUndispatched$result$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r4) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L9;
        if (r1 != 1) goto L7;
        ResultKt.throwOnFailure(r4);
        return r4;
    L7:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L9:
        ResultKt.throwOnFailure(r4);
        CoroutineScope r42 = (CoroutineScope) this.L$0;
        CoroutineScopeKt.ensureActive(r42);
        Function2<CoroutineScope, Continuation<? super T>, Object> r12 = this.$block;
        this.label = 1;
        Object r3 = r12.invoke(r42, this);
        if (r3 != r0) goto L12;
        return r0;
    L12:
        return r3;
    }
}
