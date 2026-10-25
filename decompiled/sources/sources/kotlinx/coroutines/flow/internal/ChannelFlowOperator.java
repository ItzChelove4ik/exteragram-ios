package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b \u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\b\u0012\u0004\u0012\u0002H\u00020\u0003B-\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u001c\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00010\u0011H¤@¢\u0006\u0002\u0010\u0012J$\u0010\u0013\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00010\u00112\u0006\u0010\u0014\u001a\u00020\u0007H\u0082@¢\u0006\u0002\u0010\u0015J\u001c\u0010\u0016\u001a\u00020\u000f2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00010\u0018H\u0094@¢\u0006\u0002\u0010\u0019J\u001c\u0010\u001a\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00010\u0011H\u0096@¢\u0006\u0002\u0010\u0012J\b\u0010\u001b\u001a\u00020\u001cH\u0016R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00058\u0004X\u0085\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lkotlinx/coroutines/flow/internal/ChannelFlowOperator;", "S", "T", "Lkotlinx/coroutines/flow/internal/ChannelFlow;", "flow", "Lkotlinx/coroutines/flow/Flow;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", _UrlKt.FRAGMENT_ENCODE_SET, "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "<init>", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/coroutines/CoroutineContext;ILkotlinx/coroutines/channels/BufferOverflow;)V", "flowCollect", _UrlKt.FRAGMENT_ENCODE_SET, "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collectWithContextUndispatched", "newContext", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collectTo", "scope", "Lkotlinx/coroutines/channels/ProducerScope;", "(Lkotlinx/coroutines/channels/ProducerScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collect", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class ChannelFlowOperator<S, T> extends ChannelFlow<T> {

    @JvmField
    protected final Flow<S> flow;

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "T", "it", "Lkotlinx/coroutines/flow/FlowCollector;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.internal.ChannelFlowOperator$collectWithContextUndispatched$2", f = "ChannelFlow.kt", i = {0}, l = {148}, m = "invokeSuspend", n = {"it"}, s = {"L$0"}, v = 1)
    /* renamed from: kotlinx.coroutines.flow.internal.ChannelFlowOperator$collectWithContextUndispatched$2, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<FlowCollector<? super T>, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ ChannelFlowOperator<S, T> this$0;

        public AnonymousClass2(ChannelFlowOperator<S, T> r1, Continuation<? super AnonymousClass2> r2) {
            this.this$0 = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
            AnonymousClass2 r0 = new AnonymousClass2(this.this$0, r3);
            r0.L$0 = r2;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object r1, Continuation<? super Unit> r2) {
            return invoke((FlowCollector) r1, r2);
        }

        public final Object invoke(FlowCollector<? super T> r1, Continuation<? super Unit> r2) {
            return ((AnonymousClass2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r5) {
            FlowCollector<? super T> r0 = (FlowCollector) this.L$0;
            Object r1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r2 = this.label;
            if (r2 == 0) goto L8;
            if (r2 != 1) goto L6;
            ResultKt.throwOnFailure(r5);
        L12:
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r5);
            ChannelFlowOperator<S, T> r52 = this.this$0;
            this.L$0 = SpillingKt.nullOutSpilledVariable(r0);
            this.label = 1;
            if (r52.flowCollect(r0, this) != r1) goto L12;
            return r1;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ChannelFlowOperator(Flow<? extends S> r1, CoroutineContext r2, int r3, BufferOverflow r4) {
        super(r2, r3, r4);
        this.flow = r1;
    }

    public static /* synthetic */ <S, T> Object collect$suspendImpl(ChannelFlowOperator<S, T> r4, FlowCollector<? super T> r5, Continuation<? super Unit> r6) {
        if (r4.capacity != (-3)) goto L18;
        CoroutineContext r0 = r6.getContext();
        CoroutineContext r1 = CoroutineContextKt.newCoroutineContext(r0, r4.context);
        if (Intrinsics.areEqual(r1, r0) == false) goto L11;
        Object r42 = r4.flowCollect(r5, r6);
        if (r42 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L10;
        return r42;
    L10:
        return Unit.INSTANCE;
    L11:
        ContinuationInterceptor.Key r2 = ContinuationInterceptor.Key;
        if (Intrinsics.areEqual(r1.get(r2), r0.get(r2)) == false) goto L18;
        Object r43 = r4.collectWithContextUndispatched(r5, r1, r6);
        if (r43 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L17;
        return r43;
    L17:
        return Unit.INSTANCE;
    L18:
        Object r44 = super.collect(r5, r6);
        if (r44 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L22;
        return r44;
    L22:
        return Unit.INSTANCE;
    }

    public static /* synthetic */ <S, T> Object collectTo$suspendImpl(ChannelFlowOperator<S, T> r1, ProducerScope<? super T> r2, Continuation<? super Unit> r3) {
        Object r12 = r1.flowCollect(new SendingCollector(r2), r3);
        if (r12 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        return r12;
    L6:
        return Unit.INSTANCE;
    }

    private final Object collectWithContextUndispatched(FlowCollector<? super T> r9, CoroutineContext r10, Continuation<? super Unit> r11) {
        return ChannelFlowKt.withContextUndispatched$default(r10, ChannelFlowKt.access$withUndispatchedContextCollector(r9, r11.getContext()), null, new AnonymousClass2(this, null), r11, 4, null);
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow, kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector<? super T> r1, Continuation<? super Unit> r2) {
        return collect$suspendImpl(this, r1, r2);
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    public Object collectTo(ProducerScope<? super T> r1, Continuation<? super Unit> r2) {
        return collectTo$suspendImpl(this, r1, r2);
    }

    public abstract Object flowCollect(FlowCollector<? super T> r1, Continuation<? super Unit> r2);

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    public String toString() {
        return this.flow + " -> " + super.toString();
    }
}
