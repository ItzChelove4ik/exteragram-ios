package androidx.camera.camera2.pipe.core;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.channels.ChannelResult;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "T", "it"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.pipe.core.PruningProcessingQueue$processingLoop$2$1$1", f = "PruningProcessingQueue.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
@SourceDebugExtension({"SMAP\nPruningProcessingQueue.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PruningProcessingQueue.kt\nandroidx/camera/camera2/pipe/core/PruningProcessingQueue$processingLoop$2$1$1\n+ 2 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,213:1\n50#2,2:214\n*S KotlinDebug\n*F\n+ 1 PruningProcessingQueue.kt\nandroidx/camera/camera2/pipe/core/PruningProcessingQueue$processingLoop$2$1$1\n*L\n132#1:214,2\n*E\n"})
/* loaded from: classes3.dex */
public final class PruningProcessingQueue$processingLoop$2$1$1<T> extends SuspendLambda implements Function2<T, Continuation<? super Unit>, Object> {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ PruningProcessingQueue<T> this$0;

    public PruningProcessingQueue$processingLoop$2$1$1(PruningProcessingQueue<T> r1, Continuation<? super PruningProcessingQueue$processingLoop$2$1$1> r2) {
        this.this$0 = r1;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
        PruningProcessingQueue$processingLoop$2$1$1 r0 = new PruningProcessingQueue$processingLoop$2$1$1(this.this$0, r3);
        r0.L$0 = r2;
        return r0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Object r1, Continuation<? super Unit> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(T r1, Continuation<? super Unit> r2) {
        return ((PruningProcessingQueue$processingLoop$2$1$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r3) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) goto L13;
        ResultKt.throwOnFailure(r3);
        PruningProcessingQueue.access$getQueue$p(this.this$0).add(this.L$0);
        Object r32 = PruningProcessingQueue.access$getChannel$p(this.this$0).mo3531tryReceivePtdJZtk();
    L6:
        if (ChannelResult.m3542isSuccessimpl(r32) == false) goto L8;
        PruningProcessingQueue.access$getQueue$p(this.this$0).add(ChannelResult.m3539getOrThrowimpl(r32));
        r32 = PruningProcessingQueue.access$getChannel$p(this.this$0).mo3531tryReceivePtdJZtk();
        goto L6
    L8:
        Log r33 = Log.INSTANCE;
        PruningProcessingQueue<T> r0 = this.this$0;
        if (r33.getDEBUG_LOGGABLE() == false) goto L11;
        android.util.Log.d("CXCP", "PruningProcessingQueue: Pruning " + PruningProcessingQueue.access$getQueue$p(r0));
    L11:
        PruningProcessingQueue.access$getPrune$p(this.this$0).invoke(PruningProcessingQueue.access$getQueue$p(this.this$0));
        return Unit.INSTANCE;
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    }
}
