package androidx.camera.camera2.pipe.internal;

import androidx.camera.camera2.pipe.core.Token;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.Deferred;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/Deferred;", "T", "token", "Landroidx/camera/camera2/pipe/core/Token;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.pipe.internal.GraphSessionLock$withTokenInAsync$1$deferred$1", f = "GraphSessionLock.kt", i = {}, l = {64}, m = "invokeSuspend", n = {}, s = {}, v = 1)
/* loaded from: classes3.dex */
public final class GraphSessionLock$withTokenInAsync$1$deferred$1<T> extends SuspendLambda implements Function2<Token, Continuation<? super Deferred<? extends T>>, Object> {
    final /* synthetic */ Function2<Token, Continuation<? super Deferred<? extends T>>, Object> $action;
    /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: Multi-variable type inference failed */
    public GraphSessionLock$withTokenInAsync$1$deferred$1(Function2<? super Token, ? super Continuation<? super Deferred<? extends T>>, ? extends Object> r1, Continuation<? super GraphSessionLock$withTokenInAsync$1$deferred$1> r2) {
        this.$action = r1;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
        GraphSessionLock$withTokenInAsync$1$deferred$1 r0 = new GraphSessionLock$withTokenInAsync$1$deferred$1(this.$action, r3);
        r0.L$0 = r2;
        return r0;
    }

    public final Object invoke(Token r1, Continuation<? super Deferred<? extends T>> r2) {
        return ((GraphSessionLock$withTokenInAsync$1$deferred$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Token r1, Object r2) {
        return invoke(r1, (Continuation) r2);
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
        Token r42 = (Token) this.L$0;
        Function2<Token, Continuation<? super Deferred<? extends T>>, Object> r12 = this.$action;
        this.label = 1;
        Object r3 = r12.invoke(r42, this);
        if (r3 != r0) goto L12;
        return r0;
    L12:
        return r3;
    }
}
