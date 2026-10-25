package androidx.room;

import androidx.room.util.DBUtil;
import java.util.ArrayList;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/flow/FlowCollector;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.room.TriggerBasedInvalidationTracker$createFlow$1", f = "InvalidationTracker.kt", i = {0, 1}, l = {239, 239, 243}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
/* loaded from: classes.dex */
public final class TriggerBasedInvalidationTracker$createFlow$1 extends SuspendLambda implements Function2<FlowCollector<? super Set<? extends String>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $emitInitialState;
    final /* synthetic */ String[] $resolvedTableNames;
    final /* synthetic */ int[] $tableIds;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ TriggerBasedInvalidationTracker this$0;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.TriggerBasedInvalidationTracker$createFlow$1$1", f = "InvalidationTracker.kt", i = {}, l = {239}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.room.TriggerBasedInvalidationTracker$createFlow$1$1, reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ TriggerBasedInvalidationTracker this$0;

        public AnonymousClass1(TriggerBasedInvalidationTracker r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new AnonymousClass1(this.this$0, r2);
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
        public final Object invokeSuspend(Object r4) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L8;
            if (r1 != 1) goto L6;
            ResultKt.throwOnFailure(r4);
        L12:
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r4);
            TriggerBasedInvalidationTracker r42 = this.this$0;
            this.label = 1;
            if (r42.syncTriggers$room_runtime(this) != r0) goto L12;
            return r0;
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nInvalidationTracker.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InvalidationTracker.kt\nandroidx/room/TriggerBasedInvalidationTracker$createFlow$1$2\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,640:1\n3912#2:641\n4011#2:642\n13537#2,2:643\n4012#2,2:645\n13539#2:647\n4014#2:648\n*S KotlinDebug\n*F\n+ 1 InvalidationTracker.kt\nandroidx/room/TriggerBasedInvalidationTracker$createFlow$1$2\n*L\n251#1:641\n251#1:642\n251#1:643,2\n251#1:645,2\n251#1:647\n251#1:648\n*E\n"})
    /* renamed from: androidx.room.TriggerBasedInvalidationTracker$createFlow$1$2, reason: invalid class name */
    public static final class AnonymousClass2<T> implements FlowCollector {
        final /* synthetic */ FlowCollector<Set<String>> $$this$flow;
        final /* synthetic */ Ref.ObjectRef<int[]> $currentVersions;
        final /* synthetic */ boolean $emitInitialState;
        final /* synthetic */ String[] $resolvedTableNames;
        final /* synthetic */ int[] $tableIds;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(Ref.ObjectRef<int[]> r1, boolean r2, FlowCollector<? super Set<String>> r3, String[] r4, int[] r5) {
            this.$currentVersions = r1;
            this.$emitInitialState = r2;
            this.$$this$flow = r3;
            this.$resolvedTableNames = r4;
            this.$tableIds = r5;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public /* bridge */ /* synthetic */ Object emit(Object r1, Continuation r2) {
            return emit((int[]) r1, r2);
        }

        public final Object emit(int[] r14, Continuation<? super Unit> r15) {
            if ((r15 instanceof TriggerBasedInvalidationTracker$createFlow$1$2$emit$1) == false) goto L7;
            TriggerBasedInvalidationTracker$createFlow$1$2$emit$1 r0 = (TriggerBasedInvalidationTracker$createFlow$1$2$emit$1) r15;
            int r1 = r0.label;
            if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
            r0.label = r1 - Integer.MIN_VALUE;
        L8:
            Object r152 = r0.result;
            Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r2 = r0.label;
            if (r2 == 0) goto L16;
            if (r2 == 1) goto L15;
            if (r2 == 2) goto L15;
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L15:
            Object r142 = (T) ((int[]) r0.L$0);
            ResultKt.throwOnFailure(r152);
            Object r143 = r142;
        L38:
            this.$currentVersions.element = (T) r143;
            return Unit.INSTANCE;
        L16:
            ResultKt.throwOnFailure(r152);
            Ref.ObjectRef<int[]> r153 = this.$currentVersions;
            if (r153.element != null) goto L23;
            r143 = r14;
            if (this.$emitInitialState == false) goto L38;
            FlowCollector<Set<String>> r154 = this.$$this$flow;
            Set<String> r22 = ArraysKt.toSet(this.$resolvedTableNames);
            r0.L$0 = r14;
            r0.label = 1;
            r143 = r14;
            if (r154.emit(r22, r0) != r12) goto L38;
        L37:
            return r12;
        L23:
            String[] r23 = this.$resolvedTableNames;
            int[] r5 = this.$tableIds;
            ArrayList r6 = new ArrayList();
            int r7 = r23.length;
            int r8 = 0;
            int r9 = 0;
        L24:
            if (r8 >= r7) goto L33;
            String r10 = r23[r8];
            int r11 = r9 + 1;
            int[] r122 = r153.element;
            if (r122 == null) goto L31;
            int r92 = r5[r9];
            if (r122[r92] == r14[r92]) goto L30;
            r6.add(r10);
        L30:
            r8 = r8 + 1;
            r9 = r11;
            goto L24
        L31:
            Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
            return null;
        L33:
            r143 = r14;
            if (r6.isEmpty() == true) goto L38;
            FlowCollector<Set<String>> r155 = this.$$this$flow;
            Set<String> r24 = CollectionsKt.toSet(r6);
            r0.L$0 = r14;
            r0.label = 2;
            r143 = r14;
            if (r155.emit(r24, r0) != r12) goto L38;
        L7:
            r0 = new TriggerBasedInvalidationTracker$createFlow$1$2$emit$1(this, r15);
            goto L8
        }
    }

    public TriggerBasedInvalidationTracker$createFlow$1(TriggerBasedInvalidationTracker r1, int[] r2, boolean r3, String[] r4, Continuation<? super TriggerBasedInvalidationTracker$createFlow$1> r5) {
        this.this$0 = r1;
        this.$tableIds = r2;
        this.$emitInitialState = r3;
        this.$resolvedTableNames = r4;
        super(2, r5);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r7, Continuation<?> r8) {
        TriggerBasedInvalidationTracker$createFlow$1 r0 = new TriggerBasedInvalidationTracker$createFlow$1(this.this$0, this.$tableIds, this.$emitInitialState, this.$resolvedTableNames, r8);
        r0.L$0 = r7;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super Set<? extends String>> r1, Continuation<? super Unit> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(FlowCollector<? super Set<String>> r1, Continuation<? super Unit> r2) {
        return ((TriggerBasedInvalidationTracker$createFlow$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r12) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L15;
        if (r1 == 1) goto L14;
        if (r1 == 2) goto L13;
        if (r1 == 3) goto L9;
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L9:
        ResultKt.throwOnFailure(r12);     // Catch: Throwable -> L11
    L30:
        throw new KotlinNothingValueException();     // Catch: Throwable -> L11
    L13:
        FlowCollector r13 = (FlowCollector) this.L$0;
        ResultKt.throwOnFailure(r12);
    L24:
        FlowCollector r7 = r13;
    L26:
        Ref.ObjectRef r5 = new Ref.ObjectRef();     // Catch: Throwable -> L11
        ObservedTableVersions r122 = TriggerBasedInvalidationTracker.access$getObservedTableVersions$p(this.this$0);     // Catch: Throwable -> L11
        AnonymousClass2 r4 = new AnonymousClass2(r5, this.$emitInitialState, r7, this.$resolvedTableNames, this.$tableIds);     // Catch: Throwable -> L11
        this.L$0 = null;     // Catch: Throwable -> L11
        this.label = 3;     // Catch: Throwable -> L11
        if (r122.collect(r4, this) != r0) goto L30;
    L28:
        return r0;
    L14:
        r13 = (FlowCollector) this.L$0;
        ResultKt.throwOnFailure(r12);
    L21:
        AnonymousClass1 r52 = new AnonymousClass1(this.this$0, null);
        this.L$0 = r13;
        this.label = 2;
        if (BuildersKt.withContext((CoroutineContext) r12, r52, this) != r0) goto L24;
    L15:
        ResultKt.throwOnFailure(r12);
        FlowCollector r123 = (FlowCollector) this.L$0;
        if (TriggerBasedInvalidationTracker.access$getObservedTableStates$p(this.this$0).onObserverAdded$room_runtime(this.$tableIds) == false) goto L25;
        RoomDatabase r14 = TriggerBasedInvalidationTracker.access$getDatabase$p(this.this$0);
        this.L$0 = r123;
        this.label = 1;
        Object r15 = DBUtil.getCoroutineContext(r14, false, this);
        if (r15 == r0) goto L28;
        r13 = r123;
        r12 = r15;
        goto L21
    L25:
        r7 = r123;
    L11:
        th = move-exception;
        TriggerBasedInvalidationTracker.access$getObservedTableStates$p(this.this$0).onObserverRemoved$room_runtime(this.$tableIds);
        throw th;
    }
}
