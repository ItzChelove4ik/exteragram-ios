package kotlinx.coroutines.flow;

import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt__ArraysKt$$ExternalSyntheticBUOutline0;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.DelayKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import org.telegram.messenger.NotificationBadge;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0096\u0002J\b\u0010\u0013\u001a\u00020\fH\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lkotlinx/coroutines/flow/StartedWhileSubscribed;", "Lkotlinx/coroutines/flow/SharingStarted;", "stopTimeout", _UrlKt.FRAGMENT_ENCODE_SET, "replayExpiration", "<init>", "(JJ)V", "command", "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/SharingCommand;", "subscriptionCount", "Lkotlinx/coroutines/flow/StateFlow;", _UrlKt.FRAGMENT_ENCODE_SET, "toString", _UrlKt.FRAGMENT_ENCODE_SET, "equals", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSharingStarted.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SharingStarted.kt\nkotlinx/coroutines/flow/StartedWhileSubscribed\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,205:1\n1#2:206\n*E\n"})
/* loaded from: classes5.dex */
final class StartedWhileSubscribed implements SharingStarted {
    private final long replayExpiration;
    private final long stopTimeout;

    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/flow/FlowCollector;", "Lkotlinx/coroutines/flow/SharingCommand;", NotificationBadge.NewHtcHomeBadger.COUNT, _UrlKt.FRAGMENT_ENCODE_SET}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.StartedWhileSubscribed$command$1", f = "SharingStarted.kt", i = {0, 0, 1, 1, 2, 2, 3, 3, 4, 4}, l = {175, 177, 179, 180, 182}, m = "invokeSuspend", n = {"$this$transformLatest", NotificationBadge.NewHtcHomeBadger.COUNT, "$this$transformLatest", NotificationBadge.NewHtcHomeBadger.COUNT, "$this$transformLatest", NotificationBadge.NewHtcHomeBadger.COUNT, "$this$transformLatest", NotificationBadge.NewHtcHomeBadger.COUNT, "$this$transformLatest", NotificationBadge.NewHtcHomeBadger.COUNT}, s = {"L$0", "I$0", "L$0", "I$0", "L$0", "I$0", "L$0", "I$0", "L$0", "I$0"}, v = 1)
    /* renamed from: kotlinx.coroutines.flow.StartedWhileSubscribed$command$1, reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function3<FlowCollector<? super SharingCommand>, Integer, Continuation<? super Unit>, Object> {
        /* synthetic */ int I$0;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ StartedWhileSubscribed this$0;

        public AnonymousClass1(StartedWhileSubscribed r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(3, r2);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super SharingCommand> r1, Integer r2, Continuation<? super Unit> r3) {
            return invoke(r1, r2.intValue(), r3);
        }

        public final Object invoke(FlowCollector<? super SharingCommand> r2, int r3, Continuation<? super Unit> r4) {
            AnonymousClass1 r0 = new AnonymousClass1(this.this$0, r4);
            r0.L$0 = r2;
            r0.I$0 = r3;
            return r0.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r12) {
            FlowCollector r0 = (FlowCollector) this.L$0;
            int r1 = this.I$0;
            Object r2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r3 = this.label;
            if (r3 == 0) goto L16;
            if (r3 == 1) goto L15;
            if (r3 == 2) goto L14;
            if (r3 == 3) goto L13;
            if (r3 == 4) goto L12;
            if (r3 == 5) goto L15;
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L12:
            ResultKt.throwOnFailure(r12);
        L32:
            SharingCommand r122 = SharingCommand.STOP_AND_RESET_REPLAY_CACHE;
            this.L$0 = SpillingKt.nullOutSpilledVariable(r0);
            this.I$0 = r1;
            this.label = 5;
            if (r0.emit(r122, this) != r2) goto L36;
        L34:
            return r2;
        L36:
            return Unit.INSTANCE;
        L13:
            ResultKt.throwOnFailure(r12);
        L29:
            long r6 = StartedWhileSubscribed.access$getReplayExpiration$p(this.this$0);
            this.L$0 = r0;
            this.I$0 = r1;
            this.label = 4;
            if (DelayKt.delay(r6, this) != r2) goto L32;
        L14:
            ResultKt.throwOnFailure(r12);
        L25:
            if (StartedWhileSubscribed.access$getReplayExpiration$p(this.this$0) <= 0) goto L32;
            SharingCommand r123 = SharingCommand.STOP;
            this.L$0 = r0;
            this.I$0 = r1;
            this.label = 3;
            if (r0.emit(r123, this) != r2) goto L29;
        L15:
            ResultKt.throwOnFailure(r12);
            goto L36
        L16:
            ResultKt.throwOnFailure(r12);
            if (r1 <= 0) goto L21;
            SharingCommand r124 = SharingCommand.START;
            this.L$0 = SpillingKt.nullOutSpilledVariable(r0);
            this.I$0 = r1;
            this.label = 1;
            if (r0.emit(r124, this) != r2) goto L36;
        L21:
            long r8 = StartedWhileSubscribed.access$getStopTimeout$p(this.this$0);
            this.L$0 = r0;
            this.I$0 = r1;
            this.label = 2;
            if (DelayKt.delay(r8, this) != r2) goto L25;
            goto L34
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "it", "Lkotlinx/coroutines/flow/SharingCommand;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.StartedWhileSubscribed$command$2", f = "SharingStarted.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: kotlinx.coroutines.flow.StartedWhileSubscribed$command$2, reason: invalid class name */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<SharingCommand, Continuation<? super Boolean>, Object> {
        /* synthetic */ Object L$0;
        int label;

        public AnonymousClass2(Continuation<? super AnonymousClass2> r2) {
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            AnonymousClass2 r0 = new AnonymousClass2(r2);
            r0.L$0 = r1;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(SharingCommand r1, Continuation<? super Boolean> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(SharingCommand r1, Continuation<? super Boolean> r2) {
            return ((AnonymousClass2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            SharingCommand r0 = (SharingCommand) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) goto L10;
            ResultKt.throwOnFailure(r2);
            if (r0 == SharingCommand.START) goto L7;
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

    public StartedWhileSubscribed(long r4, long r6) {
        this.stopTimeout = r4;
        this.replayExpiration = r6;
        if (r4 >= 0) goto L5;
        ArraysKt__ArraysKt$$ExternalSyntheticBUOutline0.m("stopTimeout(", r4, " ms) cannot be negative");
        throw null;
    L5:
        if (r6 < 0) goto L7;
        return;
    L7:
        ArraysKt__ArraysKt$$ExternalSyntheticBUOutline0.m("replayExpiration(", r6, " ms) cannot be negative");
        throw null;
    }

    public static final /* synthetic */ long access$getReplayExpiration$p(StartedWhileSubscribed r2) {
        return r2.replayExpiration;
    }

    public static final /* synthetic */ long access$getStopTimeout$p(StartedWhileSubscribed r2) {
        return r2.stopTimeout;
    }

    @Override // kotlinx.coroutines.flow.SharingStarted
    public Flow<SharingCommand> command(StateFlow<Integer> r3) {
        return FlowKt.distinctUntilChanged(FlowKt.dropWhile(FlowKt.transformLatest(r3, new AnonymousClass1(this, null)), new AnonymousClass2(null)));
    }

    public boolean equals(Object r5) {
        if ((r5 instanceof StartedWhileSubscribed) == false) goto L10;
        StartedWhileSubscribed r52 = (StartedWhileSubscribed) r5;
        if (this.stopTimeout == r52.stopTimeout) goto L7;
        return false;
    L7:
        if (this.replayExpiration != r52.replayExpiration) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    @IgnoreJRERequirement
    public int hashCode() {
        return (Long.hashCode(this.stopTimeout) * 31) + Long.hashCode(this.replayExpiration);
    }

    public String toString() {
        List r0 = CollectionsKt.createListBuilder(2);
        if (this.stopTimeout <= 0) goto L6;
        r0.add("stopTimeout=" + this.stopTimeout + "ms");
    L6:
        if (this.replayExpiration >= LongCompanionObject.MAX_VALUE) goto L9;
        r0.add("replayExpiration=" + this.replayExpiration + "ms");
    L9:
        return "SharingStarted.WhileSubscribed(" + CollectionsKt.joinToString$default(CollectionsKt.build(r0), null, null, null, 0, null, null, 63, null) + ')';
    }
}
