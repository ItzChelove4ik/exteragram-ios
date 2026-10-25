package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002Bg\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u0012:\u0010\u0007\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\b¢\u0006\u0004\b\u000e\u0010\u000fJ\u001c\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013H\u0096@¢\u0006\u0002\u0010\u0014R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000RD\u0010\u0007\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lkotlinx/coroutines/flow/DistinctFlowImpl;", "T", "Lkotlinx/coroutines/flow/Flow;", "upstream", "keySelector", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, "areEquivalent", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "old", "new", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)V", "collect", _UrlKt.FRAGMENT_ENCODE_SET, "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
final class DistinctFlowImpl<T> implements Flow<T> {

    @JvmField
    public final Function2<Object, Object, Boolean> areEquivalent;

    @JvmField
    public final Function1<T, Object> keySelector;
    private final Flow<T> upstream;

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    /* renamed from: kotlinx.coroutines.flow.DistinctFlowImpl$collect$2, reason: invalid class name */
    public static final class AnonymousClass2<T> implements FlowCollector {
        final /* synthetic */ FlowCollector<T> $collector;
        final /* synthetic */ Ref.ObjectRef<Object> $previousKey;
        final /* synthetic */ DistinctFlowImpl<T> this$0;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(DistinctFlowImpl<T> r1, Ref.ObjectRef<Object> r2, FlowCollector<? super T> r3) {
            this.this$0 = r1;
            this.$previousKey = r2;
            this.$collector = r3;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(T r6, Continuation<? super Unit> r7) {
            if ((r7 instanceof DistinctFlowImpl$collect$2$emit$1) == false) goto L7;
            DistinctFlowImpl$collect$2$emit$1 r0 = (DistinctFlowImpl$collect$2$emit$1) r7;
            int r1 = r0.label;
            if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
            r0.label = r1 - Integer.MIN_VALUE;
        L8:
            Object r72 = r0.result;
            Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r2 = r0.label;
            if (r2 == 0) goto L14;
            if (r2 != 1) goto L12;
            ResultKt.throwOnFailure(r72);
        L25:
            return Unit.INSTANCE;
        L12:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L14:
            ResultKt.throwOnFailure(r72);
            T r73 = (T) this.this$0.keySelector.invoke(r6);
            Object r22 = this.$previousKey.element;
            if (r22 != NullSurrogateKt.NULL) goto L17;
        L21:
            this.$previousKey.element = r73;
            FlowCollector<T> r5 = this.$collector;
            r0.L$0 = SpillingKt.nullOutSpilledVariable(r6);
            r0.L$1 = SpillingKt.nullOutSpilledVariable(r73);
            r0.label = 1;
            if (r5.emit(r6, r0) != r12) goto L25;
            return r12;
        L17:
            if (this.this$0.areEquivalent.invoke(r22, r73).booleanValue() == false) goto L21;
            return Unit.INSTANCE;
        L7:
            r0 = new DistinctFlowImpl$collect$2$emit$1(this, r7);
            goto L8
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DistinctFlowImpl(Flow<? extends T> r1, Function1<? super T, ? extends Object> r2, Function2<Object, Object, Boolean> r3) {
        this.upstream = r1;
        this.keySelector = r2;
        this.areEquivalent = r3;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector<? super T> r4, Continuation<? super Unit> r5) {
        Ref.ObjectRef r0 = new Ref.ObjectRef();
        r0.element = (T) NullSurrogateKt.NULL;
        Object r3 = this.upstream.collect(new AnonymousClass2(this, r0, r4), r5);
        if (r3 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        return r3;
    L6:
        return Unit.INSTANCE;
    }
}
