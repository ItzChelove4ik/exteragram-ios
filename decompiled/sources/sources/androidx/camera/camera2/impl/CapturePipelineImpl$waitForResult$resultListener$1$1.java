package androidx.camera.camera2.impl;

import androidx.camera.camera2.pipe.FrameInfo;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.impl.CapturePipelineImpl$waitForResult$resultListener$1$1", f = "CapturePipeline.kt", i = {}, l = {788}, m = "invokeSuspend", n = {}, s = {}, v = 1)
/* loaded from: classes3.dex */
public final class CapturePipelineImpl$waitForResult$resultListener$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ResultListener $listener;
    int label;
    final /* synthetic */ CapturePipelineImpl this$0;

    public CapturePipelineImpl$waitForResult$resultListener$1$1(ResultListener r1, CapturePipelineImpl r2, Continuation<? super CapturePipelineImpl$waitForResult$resultListener$1$1> r3) {
        this.$listener = r1;
        this.this$0 = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
        return new CapturePipelineImpl$waitForResult$resultListener$1$1(this.$listener, this.this$0, r3);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
        return ((CapturePipelineImpl$waitForResult$resultListener$1$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r4) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L8;
        if (r1 != 1) goto L6;
        ResultKt.throwOnFailure(r4);
    L11:
        CapturePipelineImpl.access$getRequestListener$p(this.this$0).removeListener(this.$listener);
        return Unit.INSTANCE;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L8:
        ResultKt.throwOnFailure(r4);
        Deferred<FrameInfo> r42 = this.$listener.getResult();
        this.label = 1;
        if (r42.join(this) != r0) goto L11;
        return r0;
    }
}
