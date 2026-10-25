package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.ReceiveChannel;

@Metadata(d1 = {"kotlinx/coroutines/flow/FlowKt__BuildersKt", "kotlinx/coroutines/flow/FlowKt__ChannelsKt", "kotlinx/coroutines/flow/FlowKt__CollectKt", "kotlinx/coroutines/flow/FlowKt__ContextKt", "kotlinx/coroutines/flow/FlowKt__DistinctKt", "kotlinx/coroutines/flow/FlowKt__EmittersKt", "kotlinx/coroutines/flow/FlowKt__ErrorsKt", "kotlinx/coroutines/flow/FlowKt__LimitKt", "kotlinx/coroutines/flow/FlowKt__MergeKt", "kotlinx/coroutines/flow/FlowKt__ReduceKt", "kotlinx/coroutines/flow/FlowKt__ShareKt", "kotlinx/coroutines/flow/FlowKt__TransformKt"}, d2 = {}, k = 4, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class FlowKt {
    public static final <T> SharedFlow<T> asSharedFlow(MutableSharedFlow<T> r0) {
        return FlowKt__ShareKt.asSharedFlow(r0);
    }

    public static final <T> StateFlow<T> asStateFlow(MutableStateFlow<T> r0) {
        return FlowKt__ShareKt.asStateFlow(r0);
    }

    public static final <T> Flow<T> buffer(Flow<? extends T> r0, int r1, BufferOverflow r2) {
        return FlowKt__ContextKt.buffer(r0, r1, r2);
    }

    public static /* synthetic */ Flow buffer$default(Flow r0, int r1, BufferOverflow r2, int r3, Object r4) {
        return FlowKt__ContextKt.buffer$default(r0, r1, r2, r3, r4);
    }

    public static final <T> Flow<T> callbackFlow(Function2<? super ProducerScope<? super T>, ? super Continuation<? super Unit>, ? extends Object> r0) {
        return FlowKt__BuildersKt.callbackFlow(r0);
    }

    /* renamed from: catch, reason: not valid java name */
    public static final <T> Flow<T> m3551catch(Flow<? extends T> r0, Function3<? super FlowCollector<? super T>, ? super Throwable, ? super Continuation<? super Unit>, ? extends Object> r1) {
        return FlowKt__ErrorsKt.m3552catch(r0, r1);
    }

    public static final <T> Object catchImpl(Flow<? extends T> r0, FlowCollector<? super T> r1, Continuation<? super Throwable> r2) {
        return FlowKt__ErrorsKt.catchImpl(r0, r1, r2);
    }

    public static final <T> Flow<T> channelFlow(Function2<? super ProducerScope<? super T>, ? super Continuation<? super Unit>, ? extends Object> r0) {
        return FlowKt__BuildersKt.channelFlow(r0);
    }

    public static final Object collect(Flow<?> r0, Continuation<? super Unit> r1) {
        return FlowKt__CollectKt.collect(r0, r1);
    }

    public static final <T> Object collectLatest(Flow<? extends T> r0, Function2<? super T, ? super Continuation<? super Unit>, ? extends Object> r1, Continuation<? super Unit> r2) {
        return FlowKt__CollectKt.collectLatest(r0, r1, r2);
    }

    public static final <T> Flow<T> conflate(Flow<? extends T> r0) {
        return FlowKt__ContextKt.conflate(r0);
    }

    public static final <T> Flow<T> distinctUntilChanged(Flow<? extends T> r0) {
        return FlowKt__DistinctKt.distinctUntilChanged(r0);
    }

    public static final <T> Flow<T> dropWhile(Flow<? extends T> r0, Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> r1) {
        return FlowKt__LimitKt.dropWhile(r0, r1);
    }

    public static final <T> Object emitAll(FlowCollector<? super T> r0, ReceiveChannel<? extends T> r1, Continuation<? super Unit> r2) {
        return FlowKt__ChannelsKt.emitAll(r0, r1, r2);
    }

    public static final <T> Object emitAll(FlowCollector<? super T> r0, Flow<? extends T> r1, Continuation<? super Unit> r2) {
        return FlowKt__CollectKt.emitAll(r0, r1, r2);
    }

    public static final void ensureActive(FlowCollector<?> r0) {
        FlowKt__EmittersKt.ensureActive(r0);
    }

    public static final <T> Object first(Flow<? extends T> r0, Continuation<? super T> r1) {
        return FlowKt__ReduceKt.first(r0, r1);
    }

    public static final <T> Object first(Flow<? extends T> r0, Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> r1, Continuation<? super T> r2) {
        return FlowKt__ReduceKt.first(r0, r1, r2);
    }

    public static final <T> Object firstOrNull(Flow<? extends T> r0, Continuation<? super T> r1) {
        return FlowKt__ReduceKt.firstOrNull(r0, r1);
    }

    public static final <T> Flow<T> flow(Function2<? super FlowCollector<? super T>, ? super Continuation<? super Unit>, ? extends Object> r0) {
        return FlowKt__BuildersKt.flow(r0);
    }

    public static final <T> Flow<T> flowOf(T r0) {
        return FlowKt__BuildersKt.flowOf(r0);
    }

    public static final <T> Job launchIn(Flow<? extends T> r0, CoroutineScope r1) {
        return FlowKt__CollectKt.launchIn(r0, r1);
    }

    public static final <T, R> Flow<R> mapLatest(Flow<? extends T> r0, Function2<? super T, ? super Continuation<? super R>, ? extends Object> r1) {
        return FlowKt__MergeKt.mapLatest(r0, r1);
    }

    public static final <T> Flow<T> merge(Iterable<? extends Flow<? extends T>> r0) {
        return FlowKt__MergeKt.merge(r0);
    }

    public static final <T> Flow<T> merge(Flow<? extends T>... r0) {
        return FlowKt__MergeKt.merge(r0);
    }

    public static final <T> Flow<T> onCompletion(Flow<? extends T> r0, Function3<? super FlowCollector<? super T>, ? super Throwable, ? super Continuation<? super Unit>, ? extends Object> r1) {
        return FlowKt__EmittersKt.onCompletion(r0, r1);
    }

    public static final <T> Flow<T> onEach(Flow<? extends T> r0, Function2<? super T, ? super Continuation<? super Unit>, ? extends Object> r1) {
        return FlowKt__TransformKt.onEach(r0, r1);
    }

    public static final <T> Flow<T> onStart(Flow<? extends T> r0, Function2<? super FlowCollector<? super T>, ? super Continuation<? super Unit>, ? extends Object> r1) {
        return FlowKt__EmittersKt.onStart(r0, r1);
    }

    public static final <T> Flow<T> retryWhen(Flow<? extends T> r0, Function4<? super FlowCollector<? super T>, ? super Throwable, ? super Long, ? super Continuation<? super Boolean>, ? extends Object> r1) {
        return FlowKt__ErrorsKt.retryWhen(r0, r1);
    }

    public static final <T> SharedFlow<T> shareIn(Flow<? extends T> r0, CoroutineScope r1, SharingStarted r2, int r3) {
        return FlowKt__ShareKt.shareIn(r0, r1, r2, r3);
    }

    public static final <T> Flow<T> takeWhile(Flow<? extends T> r0, Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> r1) {
        return FlowKt__LimitKt.takeWhile(r0, r1);
    }

    public static final <T, R> Flow<R> transformLatest(Flow<? extends T> r0, Function3<? super FlowCollector<? super R>, ? super T, ? super Continuation<? super Unit>, ? extends Object> r1) {
        return FlowKt__MergeKt.transformLatest(r0, r1);
    }
}
