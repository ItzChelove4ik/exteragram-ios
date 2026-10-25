package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003Bw\u0012B\u0010\u0004\u001a>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0006\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0005¢\u0006\u0002\b\r\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0015¢\u0006\u0004\b\u0016\u0010\u0017J&\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00010\u001a2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0014J\u001c\u0010\u001b\u001a\u00020\u000b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00010\u0006H\u0094@¢\u0006\u0002\u0010\u001dRL\u0010\u0004\u001a>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0006\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0005¢\u0006\u0002\b\rX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018¨\u0006\u001e"}, d2 = {"Lkotlinx/coroutines/flow/internal/ChannelFlowTransformLatest;", "T", "R", "Lkotlinx/coroutines/flow/internal/ChannelFlowOperator;", "transform", "Lkotlin/Function3;", "Lkotlinx/coroutines/flow/FlowCollector;", "Lkotlin/ParameterName;", "name", "value", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ExtensionFunctionType;", "flow", "Lkotlinx/coroutines/flow/Flow;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", _UrlKt.FRAGMENT_ENCODE_SET, "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "<init>", "(Lkotlin/jvm/functions/Function3;Lkotlinx/coroutines/flow/Flow;Lkotlin/coroutines/CoroutineContext;ILkotlinx/coroutines/channels/BufferOverflow;)V", "Lkotlin/jvm/functions/Function3;", "create", "Lkotlinx/coroutines/flow/internal/ChannelFlow;", "flowCollect", "collector", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nMerge.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Merge.kt\nkotlinx/coroutines/flow/internal/ChannelFlowTransformLatest\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,96:1\n1#2:97\n*E\n"})
/* loaded from: classes5.dex */
public final class ChannelFlowTransformLatest<T, R> extends ChannelFlowOperator<T, R> {
    private final Function3<FlowCollector<? super R>, T, Continuation<? super Unit>, Object> transform;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3", f = "Merge.kt", i = {0, 0}, l = {23}, m = "invokeSuspend", n = {"$this$coroutineScope", "previousFlow"}, s = {"L$0", "L$1"}, v = 1)
    /* renamed from: kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3, reason: invalid class name */
    public static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ FlowCollector<R> $collector;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ ChannelFlowTransformLatest<T, R> this$0;

        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        /* renamed from: kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$1, reason: invalid class name */
        public static final class AnonymousClass1<T> implements FlowCollector {
            final /* synthetic */ CoroutineScope $$this$coroutineScope;
            final /* synthetic */ FlowCollector<R> $collector;
            final /* synthetic */ Ref.ObjectRef<Job> $previousFlow;
            final /* synthetic */ ChannelFlowTransformLatest<T, R> this$0;

            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
            @DebugMetadata(c = "kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$1$2", f = "Merge.kt", i = {}, l = {30}, m = "invokeSuspend", n = {}, s = {}, v = 1)
            /* renamed from: kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$1$2, reason: invalid class name */
            public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ FlowCollector<R> $collector;
                final /* synthetic */ T $value;
                int label;
                final /* synthetic */ ChannelFlowTransformLatest<T, R> this$0;

                /* JADX WARN: Multi-variable type inference failed */
                public AnonymousClass2(ChannelFlowTransformLatest<T, R> r1, FlowCollector<? super R> r2, T r3, Continuation<? super AnonymousClass2> r4) {
                    this.this$0 = r1;
                    this.$collector = r2;
                    this.$value = r3;
                    super(2, r4);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
                    return new AnonymousClass2(this.this$0, this.$collector, this.$value, r4);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
                    return invoke2(r1, r2);
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
                    return ((AnonymousClass2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object r5) {
                    Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int r1 = this.label;
                    if (r1 == 0) goto L8;
                    if (r1 != 1) goto L6;
                    ResultKt.throwOnFailure(r5);
                L12:
                    return Unit.INSTANCE;
                L6:
                    Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
                    return null;
                L8:
                    ResultKt.throwOnFailure(r5);
                    Function3 r52 = ChannelFlowTransformLatest.access$getTransform$p(this.this$0);
                    FlowCollector<R> r12 = this.$collector;
                    T r3 = this.$value;
                    this.label = 1;
                    if (r52.invoke(r12, r3, this) != r0) goto L12;
                    return r0;
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass1(Ref.ObjectRef<Job> r1, CoroutineScope r2, ChannelFlowTransformLatest<T, R> r3, FlowCollector<? super R> r4) {
                this.$previousFlow = r1;
                this.$$this$coroutineScope = r2;
                this.this$0 = r3;
                this.$collector = r4;
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(T r11, Continuation<? super Unit> r12) {
                if ((r12 instanceof ChannelFlowTransformLatest$flowCollect$3$1$emit$1) == false) goto L7;
                ChannelFlowTransformLatest$flowCollect$3$1$emit$1 r0 = (ChannelFlowTransformLatest$flowCollect$3$1$emit$1) r12;
                int r1 = r0.label;
                if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
                r0.label = r1 - Integer.MIN_VALUE;
            L8:
                Object r122 = r0.result;
                Object r13 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int r2 = r0.label;
                if (r2 == 0) goto L14;
                if (r2 != 1) goto L12;
                Job r112 = (Job) r0.L$2;
                Job r113 = (Job) r0.L$1;
                r11 = (T) r0.L$0;
                ResultKt.throwOnFailure(r122);
            L19:
                Ref.ObjectRef<Job> r123 = this.$previousFlow;
                r123.element = (T) BuildersKt.launch$default(this.$$this$coroutineScope, null, CoroutineStart.UNDISPATCHED, new AnonymousClass2(this.this$0, this.$collector, r11, null), 1, null);
                return Unit.INSTANCE;
            L12:
                Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
                return null;
            L14:
                ResultKt.throwOnFailure(r122);
                Job r124 = this.$previousFlow.element;
                if (r124 == null) goto L19;
                r124.cancel(new ChildCancelledException());
                r0.L$0 = r11;
                r0.L$1 = r124;
                r0.L$2 = SpillingKt.nullOutSpilledVariable(r124);
                r0.I$0 = 0;
                r0.label = 1;
                if (r124.join(r0) != r13) goto L19;
                return r13;
            L7:
                r0 = new ChannelFlowTransformLatest$flowCollect$3$1$emit$1(this, r12);
                goto L8
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(ChannelFlowTransformLatest<T, R> r1, FlowCollector<? super R> r2, Continuation<? super AnonymousClass3> r3) {
            this.this$0 = r1;
            this.$collector = r2;
            super(2, r3);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
            AnonymousClass3 r0 = new AnonymousClass3(this.this$0, this.$collector, r4);
            r0.L$0 = r3;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((AnonymousClass3) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r8) {
            CoroutineScope r0 = (CoroutineScope) this.L$0;
            Object r1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r2 = this.label;
            if (r2 == 0) goto L8;
            if (r2 != 1) goto L6;
            Ref.ObjectRef r7 = (Ref.ObjectRef) this.L$1;
            ResultKt.throwOnFailure(r8);
        L12:
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r8);
            Ref.ObjectRef r82 = new Ref.ObjectRef();
            ChannelFlowTransformLatest<T, R> r22 = this.this$0;
            Flow<S> r4 = r22.flow;
            AnonymousClass1 r5 = new AnonymousClass1(r82, r0, r22, this.$collector);
            this.L$0 = SpillingKt.nullOutSpilledVariable(r0);
            this.L$1 = SpillingKt.nullOutSpilledVariable(r82);
            this.label = 1;
            if (r4.collect(r5, this) != r1) goto L12;
            return r1;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ChannelFlowTransformLatest(Function3<? super FlowCollector<? super R>, ? super T, ? super Continuation<? super Unit>, ? extends Object> r1, Flow<? extends T> r2, CoroutineContext r3, int r4, BufferOverflow r5) {
        super(r2, r3, r4, r5);
        this.transform = r1;
    }

    public /* synthetic */ ChannelFlowTransformLatest(Function3 r7, Flow r8, CoroutineContext r9, int r10, BufferOverflow r11, int r12, DefaultConstructorMarker r13) {
        if ((r12 & 4) == 0) goto L5;
        r9 = EmptyCoroutineContext.INSTANCE;
    L5:
        CoroutineContext r3 = r9;
        if ((r12 & 8) == 0) goto L8;
        r10 = -2;
    L8:
        int r4 = r10;
        if ((r12 & 16) == 0) goto L11;
        r11 = BufferOverflow.SUSPEND;
    L11:
        this(r7, r8, r3, r4, r11);
    }

    public static final /* synthetic */ Function3 access$getTransform$p(ChannelFlowTransformLatest r0) {
        return r0.transform;
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    public ChannelFlow<R> create(CoroutineContext r7, int r8, BufferOverflow r9) {
        return new ChannelFlowTransformLatest(this.transform, this.flow, r7, r8, r9);
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlowOperator
    public Object flowCollect(FlowCollector<? super R> r3, Continuation<? super Unit> r4) {
        Object r2 = CoroutineScopeKt.coroutineScope(new AnonymousClass3(this, r3, null), r4);
        if (r2 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        return r2;
    L6:
        return Unit.INSTANCE;
    }
}
