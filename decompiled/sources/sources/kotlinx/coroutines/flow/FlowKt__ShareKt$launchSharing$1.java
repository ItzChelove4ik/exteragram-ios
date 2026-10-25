package kotlinx.coroutines.flow;

import com.android.dx.io.Opcodes;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.internal.Symbol;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1", f = "Share.kt", i = {}, l = {Opcodes.MUL_INT_LIT16, Opcodes.OR_INT_LIT16, Opcodes.XOR_INT_LIT16, Opcodes.AND_INT_LIT8}, m = "invokeSuspend", n = {}, s = {}, v = 1)
/* loaded from: classes5.dex */
public final class FlowKt__ShareKt$launchSharing$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ T $initialValue;
    final /* synthetic */ MutableSharedFlow<T> $shared;
    final /* synthetic */ SharingStarted $started;
    final /* synthetic */ Flow<T> $upstream;
    int label;

    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "it", _UrlKt.FRAGMENT_ENCODE_SET}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$1", f = "Share.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$1, reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<Integer, Continuation<? super Boolean>, Object> {
        /* synthetic */ int I$0;
        int label;

        public AnonymousClass1(Continuation<? super AnonymousClass1> r2) {
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            AnonymousClass1 r0 = new AnonymousClass1(r2);
            r0.I$0 = ((Number) r1).intValue();
            return r0;
        }

        public final Object invoke(int r1, Continuation<? super Boolean> r2) {
            return ((AnonymousClass1) create(Integer.valueOf(r1), r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Integer r1, Continuation<? super Boolean> r2) {
            return invoke(r1.intValue(), r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            int r0 = this.I$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) goto L10;
            ResultKt.throwOnFailure(r2);
            if (r0 <= 0) goto L7;
            boolean r1 = true;
        L9:
            return Boxing.boxBoolean(r1);
        L7:
            r1 = false;
            goto L9
        L10:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "it", "Lkotlinx/coroutines/flow/SharingCommand;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$2", f = "Share.kt", i = {0}, l = {Opcodes.XOR_INT_LIT8}, m = "invokeSuspend", n = {"it"}, s = {"L$0"}, v = 1)
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$2, reason: invalid class name */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<SharingCommand, Continuation<? super Unit>, Object> {
        final /* synthetic */ T $initialValue;
        final /* synthetic */ MutableSharedFlow<T> $shared;
        final /* synthetic */ Flow<T> $upstream;
        /* synthetic */ Object L$0;
        int label;

        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        /* renamed from: kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$2$WhenMappings */
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

            static {
                int[] r0 = new int[SharingCommand.values().length];
                r0[SharingCommand.START.ordinal()] = 1;     // Catch: NoSuchFieldError -> L8
            L11:
                r0[SharingCommand.STOP.ordinal()] = 2;     // Catch: NoSuchFieldError -> L9
            L15:
                r0[SharingCommand.STOP_AND_RESET_REPLAY_CACHE.ordinal()] = 3;     // Catch: NoSuchFieldError -> L10
            L6:
                $EnumSwitchMapping$0 = r0;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(Flow<? extends T> r1, MutableSharedFlow<T> r2, T r3, Continuation<? super AnonymousClass2> r4) {
            this.$upstream = r1;
            this.$shared = r2;
            this.$initialValue = r3;
            super(2, r4);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r4, Continuation<?> r5) {
            AnonymousClass2 r0 = new AnonymousClass2(this.$upstream, this.$shared, this.$initialValue, r5);
            r0.L$0 = r4;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(SharingCommand r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(SharingCommand r1, Continuation<? super Unit> r2) {
            return ((AnonymousClass2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r6) {
            SharingCommand r0 = (SharingCommand) this.L$0;
            Object r1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r2 = this.label;
            if (r2 == 0) goto L8;
            if (r2 != 1) goto L6;
            ResultKt.throwOnFailure(r6);
        L24:
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r6);
            int r62 = WhenMappings.$EnumSwitchMapping$0[r0.ordinal()];
            if (r62 != 1) goto L11;
            Flow<T> r63 = this.$upstream;
            SharedFlow r22 = this.$shared;
            this.L$0 = SpillingKt.nullOutSpilledVariable(r0);
            this.label = 1;
            if (r63.collect(r22, this) != r1) goto L24;
            return r1;
        L11:
            if (r62 == 2) goto L24;
            if (r62 != 3) goto L18;
            T r64 = this.$initialValue;
            Symbol r02 = SharedFlowKt.NO_VALUE;
            MutableSharedFlow<T> r5 = this.$shared;
            if (r64 != r02) goto L17;
            r5.resetReplayCache();
            goto L24
        L17:
            Boxing.boxBoolean(r5.tryEmit(r64));
            goto L24
        L18:
            LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FlowKt__ShareKt$launchSharing$1(SharingStarted r1, Flow<? extends T> r2, MutableSharedFlow<T> r3, T r4, Continuation<? super FlowKt__ShareKt$launchSharing$1> r5) {
        this.$started = r1;
        this.$upstream = r2;
        this.$shared = r3;
        this.$initialValue = r4;
        super(2, r5);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r7, Continuation<?> r8) {
        return new FlowKt__ShareKt$launchSharing$1(this.$started, this.$upstream, this.$shared, this.$initialValue, r8);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
        return ((FlowKt__ShareKt$launchSharing$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r8) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L13;
        if (r1 == 1) goto L12;
        if (r1 == 2) goto L11;
        if (r1 == 3) goto L12;
        if (r1 == 4) goto L12;
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L11:
        ResultKt.throwOnFailure(r8);
    L23:
        Flow<T> r82 = this.$upstream;
        SharedFlow r12 = this.$shared;
        this.label = 3;
        if (r82.collect(r12, this) != r0) goto L30;
    L28:
        return r0;
    L30:
        return Unit.INSTANCE;
    L12:
        ResultKt.throwOnFailure(r8);
        goto L30
    L13:
        ResultKt.throwOnFailure(r8);
        SharingStarted r83 = this.$started;
        SharingStarted.Companion r13 = SharingStarted.Companion;
        if (r83 != r13.getEagerly()) goto L19;
        Flow<T> r84 = this.$upstream;
        SharedFlow r14 = this.$shared;
        this.label = 1;
        if (r84.collect(r14, this) != r0) goto L30;
    L19:
        if (this.$started != r13.getLazily()) goto L26;
        StateFlow<Integer> r85 = this.$shared.getSubscriptionCount();
        AnonymousClass1 r15 = new AnonymousClass1(null);
        this.label = 2;
        if (FlowKt.first(r85, r15, this) != r0) goto L23;
    L26:
        Flow r86 = FlowKt.distinctUntilChanged(this.$started.command(this.$shared.getSubscriptionCount()));
        AnonymousClass2 r16 = new AnonymousClass2(this.$upstream, this.$shared, this.$initialValue, null);
        this.label = 4;
        if (FlowKt.collectLatest(r86, r16, this) != r0) goto L30;
        goto L28
    }
}
