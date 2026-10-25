package kotlinx.coroutines.flow.internal;

import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.YieldKt;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u00008\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u008f\u0001\u0010\u000e\u001a\u00020\n\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u00022\u0014\u0010\u0005\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00028\u00010\u00040\u00032\u0016\u0010\u0007\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00018\u0001\u0018\u00010\u00030\u000629\u0010\r\u001a5\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\b¢\u0006\u0002\b\fH\u0081@¢\u0006\u0004\b\u000e\u0010\u000f*\u001c\b\u0002\u0010\u0011\"\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u00102\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0010¨\u0006\u0012"}, d2 = {"R", "T", "Lkotlinx/coroutines/flow/FlowCollector;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/flow/Flow;", "flows", "Lkotlin/Function0;", "arrayFactory", "Lkotlin/Function3;", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ExtensionFunctionType;", "transform", "combineInternal", "(Lkotlinx/coroutines/flow/FlowCollector;[Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlin/collections/IndexedValue;", "Update", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCombine.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Combine.kt\nkotlinx/coroutines/flow/internal/CombineKt\n+ 2 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt\n*L\n1#1,140:1\n105#2:141\n*S KotlinDebug\n*F\n+ 1 Combine.kt\nkotlinx/coroutines/flow/internal/CombineKt\n*L\n83#1:141\n*E\n"})
/* loaded from: classes5.dex */
public abstract class CombineKt {

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2", f = "Combine.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {51, 73, 76}, m = "invokeSuspend", n = {"$this$flowScope", "latestValues", "resultChannel", "nonClosed", "lastReceivedEpoch", "size", "remainingAbsentValues", "currentEpoch", "$this$flowScope", "latestValues", "resultChannel", "nonClosed", "lastReceivedEpoch", "element", "results", "size", "remainingAbsentValues", "currentEpoch", "$this$flowScope", "latestValues", "resultChannel", "nonClosed", "lastReceivedEpoch", "element", "results", "size", "remainingAbsentValues", "currentEpoch"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "I$1", "I$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "I$0", "I$1", "I$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "I$0", "I$1", "I$2"}, v = 1)
    /* renamed from: kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2, reason: invalid class name */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function0<T[]> $arrayFactory;
        final /* synthetic */ Flow<T>[] $flows;
        final /* synthetic */ FlowCollector<R> $this_combineInternal;
        final /* synthetic */ Function3<FlowCollector<? super R>, T[], Continuation<? super Unit>, Object> $transform;
        int I$0;
        int I$1;
        int I$2;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2$1", f = "Combine.kt", i = {}, l = {28}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        /* renamed from: kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2$1, reason: invalid class name */
        public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Flow<T>[] $flows;
            final /* synthetic */ int $i;
            final /* synthetic */ AtomicInteger $nonClosed;
            final /* synthetic */ Channel<IndexedValue<Object>> $resultChannel;
            int label;

            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            /* renamed from: kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2$1$1, reason: invalid class name and collision with other inner class name */
            public static final class C00311<T> implements FlowCollector {
                final /* synthetic */ int $i;
                final /* synthetic */ Channel<IndexedValue<Object>> $resultChannel;

                public C00311(Channel<IndexedValue<Object>> r1, int r2) {
                    this.$resultChannel = r1;
                    this.$i = r2;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(T r6, Continuation<? super Unit> r7) {
                    if ((r7 instanceof CombineKt$combineInternal$2$1$1$emit$1) == false) goto L7;
                    CombineKt$combineInternal$2$1$1$emit$1 r0 = (CombineKt$combineInternal$2$1$1$emit$1) r7;
                    int r1 = r0.label;
                    if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
                    r0.label = r1 - Integer.MIN_VALUE;
                L8:
                    Object r72 = r0.result;
                    Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int r2 = r0.label;
                    if (r2 == 0) goto L16;
                    if (r2 == 1) goto L15;
                    if (r2 != 2) goto L13;
                    ResultKt.throwOnFailure(r72);
                L23:
                    return Unit.INSTANCE;
                L13:
                    Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
                    return null;
                L15:
                    r6 = (T) r0.L$0;
                    ResultKt.throwOnFailure(r72);
                L19:
                    r0.L$0 = SpillingKt.nullOutSpilledVariable(r6);
                    r0.label = 2;
                    if (YieldKt.yield(r0) != r12) goto L23;
                L21:
                    return r12;
                L16:
                    ResultKt.throwOnFailure(r72);
                    Channel<IndexedValue<Object>> r73 = this.$resultChannel;
                    IndexedValue<Object> r22 = new IndexedValue(this.$i, r6);
                    r0.L$0 = SpillingKt.nullOutSpilledVariable(r6);
                    r0.label = 1;
                    if (r73.send(r22, r0) != r12) goto L19;
                L7:
                    r0 = new CombineKt$combineInternal$2$1$1$emit$1(this, r7);
                    goto L8
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass1(Flow<? extends T>[] r1, int r2, AtomicInteger r3, Channel<IndexedValue<Object>> r4, Continuation<? super AnonymousClass1> r5) {
                this.$flows = r1;
                this.$i = r2;
                this.$nonClosed = r3;
                this.$resultChannel = r4;
                super(2, r5);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object r7, Continuation<?> r8) {
                return new AnonymousClass1(this.$flows, this.$i, this.$nonClosed, this.$resultChannel, r8);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
                return invoke2(r1, r2);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
                return ((AnonymousClass1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object r7) {
                Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int r1 = this.label;
                if (r1 == 0) goto L11;
                if (r1 != 1) goto L9;
                ResultKt.throwOnFailure(r7);     // Catch: Throwable -> L7
            L15:
                if (this.$nonClosed.decrementAndGet() != 0) goto L19;
                SendChannel.close$default(this.$resultChannel, null, 1, null);
            L19:
                return Unit.INSTANCE;
            L9:
                Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
                return null;
            L11:
                ResultKt.throwOnFailure(r7);
                Flow[] r72 = this.$flows;     // Catch: Throwable -> L7
                int r12 = this.$i;     // Catch: Throwable -> L7
                Flow r73 = r72[r12];     // Catch: Throwable -> L7
                C00311 r4 = new C00311(this.$resultChannel, r12);     // Catch: Throwable -> L7
                this.label = 1;     // Catch: Throwable -> L7
                if (r73.collect(r4, this) != r0) goto L15;
                return r0;
            L7:
                th = move-exception;
                if (this.$nonClosed.decrementAndGet() != 0) goto L23;
                SendChannel.close$default(this.$resultChannel, null, 1, null);
            L23:
                throw th;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(Flow<? extends T>[] r1, Function0<T[]> r2, Function3<? super FlowCollector<? super R>, ? super T[], ? super Continuation<? super Unit>, ? extends Object> r3, FlowCollector<? super R> r4, Continuation<? super AnonymousClass2> r5) {
            this.$flows = r1;
            this.$arrayFactory = r2;
            this.$transform = r3;
            this.$this_combineInternal = r4;
            super(2, r5);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r7, Continuation<?> r8) {
            AnonymousClass2 r0 = new AnonymousClass2(this.$flows, this.$arrayFactory, this.$transform, this.$this_combineInternal, r8);
            r0.L$0 = r7;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((AnonymousClass2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r15v1, types: [kotlinx.coroutines.flow.Flow<T>[], kotlinx.coroutines.flow.Flow[]] */
        /* JADX WARN: Type inference failed for: r3v1, types: [kotlinx.coroutines.flow.Flow<T>[]] */
        /* JADX WARN: Type inference failed for: r3v12, types: [int] */
        /* JADX WARN: Type inference failed for: r3v14, types: [int] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:48:0x01ad -> B:20:0x00c1). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r23) {
            CoroutineScope r2 = (CoroutineScope) this.L$0;
            Object r1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r3 = this.label;
            int r10 = 1;
            Object r11 = null;
            if (r3 == 0) goto L12;
            if (r3 == 1) goto L11;
            if (r3 == 2) goto L10;
            if (r3 == 3) goto L10;
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L10:
            ?? r32 = this.I$2;
            int r4 = this.I$1;
            int r5 = this.I$0;
            Object[] r6 = (Object[]) this.L$6;
            IndexedValue r62 = (IndexedValue) this.L$5;
            byte[] r63 = (byte[]) this.L$4;
            AtomicInteger r7 = (AtomicInteger) this.L$3;
            Channel r12 = (Channel) this.L$2;
            Object[] r13 = (Object[]) this.L$1;
            ResultKt.throwOnFailure(r23);
            int r122 = r4;
            byte[] r42 = r63;
            Channel r64 = r12;
            byte r20 = r32;
            AtomicInteger r33 = r7;
        L44:
            r10 = 1;
            r11 = null;
        L20:
            byte r72 = (byte) (r20 + 1);
            this.L$0 = SpillingKt.nullOutSpilledVariable(r2);
            this.L$1 = r13;
            this.L$2 = r64;
            this.L$3 = SpillingKt.nullOutSpilledVariable(r33);
            this.L$4 = r42;
            this.L$5 = r11;
            this.L$6 = r11;
            this.I$0 = r5;
            this.I$1 = r122;
            this.I$2 = r72;
            this.label = r10;
            Object r14 = r64.mo3530receiveCatchingJP2dKIU(this);
            if (r14 == r1) goto L47;
            Channel r21 = r64;
            byte[] r65 = r42;
            int r43 = r122;
            Channel r123 = r21;
        L24:
            IndexedValue r142 = (IndexedValue) ChannelResult.m3538getOrNullimpl(r14);
            if (r142 == null) goto L27;
        L28:
            IndexedValue r202 = r142;
            int r143 = r202.getIndex();
            Object r15 = r13[r143];
            r13[r143] = r202.getValue();
            if (r15 != NullSurrogateKt.UNINITIALIZED) goto L32;
            r43 = r43 - 1;
        L32:
            if (r65[r143] == r72) goto L37;
            r65[r143] = r72;
            r142 = (IndexedValue) ChannelResult.m3538getOrNullimpl(r123.mo3531tryReceivePtdJZtk());
            if (r142 != null) goto L28;
        L37:
            if (r43 != 0) goto L48;
            Object[] r144 = (Object[]) this.$arrayFactory.invoke();
            if (r144 != null) goto L45;
            Function3 r102 = this.$transform;
            Object r152 = this.$this_combineInternal;
            this.L$0 = SpillingKt.nullOutSpilledVariable(r2);
            this.L$1 = r13;
            this.L$2 = r123;
            this.L$3 = SpillingKt.nullOutSpilledVariable(r33);
            this.L$4 = r65;
            this.L$5 = SpillingKt.nullOutSpilledVariable(r202);
            this.L$6 = SpillingKt.nullOutSpilledVariable(r144);
            this.I$0 = r5;
            this.I$1 = r43;
            this.I$2 = r72;
            this.label = 2;
            if (r102.invoke(r152, r13, this) == r1) goto L47;
        L43:
            Channel r203 = r123;
            r122 = r43;
            r42 = r65;
            r64 = r203;
            r20 = r72;
            goto L44
        L45:
            ArraysKt.copyInto$default(r13, r144, 0, 0, 0, 14, null);
            Function3 r103 = this.$transform;
            Object r112 = this.$this_combineInternal;
            this.L$0 = SpillingKt.nullOutSpilledVariable(r2);
            this.L$1 = r13;
            this.L$2 = r123;
            this.L$3 = SpillingKt.nullOutSpilledVariable(r33);
            this.L$4 = r65;
            this.L$5 = SpillingKt.nullOutSpilledVariable(r202);
            this.L$6 = SpillingKt.nullOutSpilledVariable(r144);
            this.I$0 = r5;
            this.I$1 = r43;
            this.I$2 = r72;
            this.label = 3;
            if (r103.invoke(r112, r144, this) != r1) goto L43;
            Channel r204 = r123;
            r122 = r43;
            r42 = r65;
            r64 = r204;
            r20 = r72;
            goto L44
        L48:
            Channel r104 = r123;
            r122 = r43;
            r42 = r65;
            r64 = r104;
            r20 = r72;
            r10 = 1;
            goto L20
        L27:
            return Unit.INSTANCE;
        L47:
            return r1;
        L11:
            ?? r34 = this.I$2;
            r43 = this.I$1;
            r5 = this.I$0;
            r65 = (byte[]) this.L$4;
            AtomicInteger r73 = (AtomicInteger) this.L$3;
            r123 = (Channel) this.L$2;
            r13 = (Object[]) this.L$1;
            ResultKt.throwOnFailure(r23);
            r14 = ((ChannelResult) r23).m3544unboximpl();
            r72 = r34;
            r33 = r73;
            goto L24
        L12:
            ResultKt.throwOnFailure(r23);
            r122 = this.$flows.length;
            if (r122 == 0) goto L15;
            r13 = new Object[r122];
            ArraysKt.fill$default(r13, NullSurrogateKt.UNINITIALIZED, 0, 0, 6, null);
            Channel r18 = ChannelKt.Channel$default(r122, null, null, 6, null);
            AtomicInteger r35 = new AtomicInteger(r122);
            r20 = 0;
            int r44 = 0;
        L17:
            if (r44 >= r122) goto L19;
            AtomicInteger r17 = r35;
            int r16 = r44;
            BuildersKt.launch$default(r2, null, null, new AnonymousClass1(this.$flows, r16, r17, r18, null), 3, null);
            r44 = r16 + 1;
            r35 = r17;
            goto L17
        L19:
            r42 = new byte[r122];
            r5 = r122;
            r33 = r35;
            r64 = r18;
            goto L20
        L15:
            return Unit.INSTANCE;
        }
    }

    @PublishedApi
    public static final <R, T> Object combineInternal(FlowCollector<? super R> r6, Flow<? extends T>[] r7, Function0<T[]> r8, Function3<? super FlowCollector<? super R>, ? super T[], ? super Continuation<? super Unit>, ? extends Object> r9, Continuation<? super Unit> r10) {
        Object r62 = FlowCoroutineKt.flowScope(new AnonymousClass2(r7, r8, r9, r6, null), r10);
        if (r62 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        return r62;
    L6:
        return Unit.INSTANCE;
    }
}
