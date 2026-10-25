package androidx.work.impl.constraints.controllers;

import androidx.work.Constraints;
import androidx.work.impl.constraints.ConstraintsState;
import androidx.work.impl.constraints.trackers.ConstraintTracker;
import androidx.work.impl.model.WorkSpec;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00028\u0000H\u0014¢\u0006\u0004\b\t\u0010\nJ\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0015R\u001a\u0010\u001b\u001a\u00020\u00168$X¤\u0004¢\u0006\f\u0012\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001c"}, d2 = {"Landroidx/work/impl/constraints/controllers/BaseConstraintController;", "T", "Landroidx/work/impl/constraints/controllers/ConstraintController;", "Landroidx/work/impl/constraints/trackers/ConstraintTracker;", "tracker", "<init>", "(Landroidx/work/impl/constraints/trackers/ConstraintTracker;)V", "value", _UrlKt.FRAGMENT_ENCODE_SET, "isConstrained", "(Ljava/lang/Object;)Z", "Landroidx/work/Constraints;", "constraints", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/work/impl/constraints/ConstraintsState;", "track", "(Landroidx/work/Constraints;)Lkotlinx/coroutines/flow/Flow;", "Landroidx/work/impl/model/WorkSpec;", "workSpec", "isCurrentlyConstrained", "(Landroidx/work/impl/model/WorkSpec;)Z", "Landroidx/work/impl/constraints/trackers/ConstraintTracker;", _UrlKt.FRAGMENT_ENCODE_SET, "getReason", "()I", "getReason$annotations", "()V", "reason", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class BaseConstraintController<T> implements ConstraintController {
    private final ConstraintTracker<T> tracker;

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/channels/ProducerScope;", "Landroidx/work/impl/constraints/ConstraintsState;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.work.impl.constraints.controllers.BaseConstraintController$track$1", f = "ContraintControllers.kt", i = {}, l = {62}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.work.impl.constraints.controllers.BaseConstraintController$track$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<ProducerScope<? super ConstraintsState>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ BaseConstraintController<T> this$0;

        public static Unit $r8$lambda$1CIZy7DANcz1w69WzqC4B6yVGbU(BaseConstraintController r0, BaseConstraintController$track$1$listener$1 r1) {
            BaseConstraintController.access$getTracker$p(r0).removeListener(r1);
            return Unit.INSTANCE;
        }

        public AnonymousClass1(BaseConstraintController<T> r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
            AnonymousClass1 r0 = new AnonymousClass1(this.this$0, r3);
            r0.L$0 = r2;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(ProducerScope<? super ConstraintsState> r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(ProducerScope<? super ConstraintsState> r1, Continuation<? super Unit> r2) {
            return ((AnonymousClass1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r6) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L8;
            if (r1 != 1) goto L6;
            ResultKt.throwOnFailure(r6);
        L12:
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r6);
            ProducerScope r62 = (ProducerScope) this.L$0;
            BaseConstraintController$track$1$listener$1 r12 = new BaseConstraintController$track$1$listener$1(this.this$0, r62);
            BaseConstraintController.access$getTracker$p(this.this$0).addListener(r12);
            BaseConstraintController$track$1$$ExternalSyntheticLambda0 r4 = new BaseConstraintController$track$1$$ExternalSyntheticLambda0(this.this$0, r12);
            this.label = 1;
            if (ProduceKt.awaitClose(r62, r4, this) != r0) goto L12;
            return r0;
        }
    }

    public BaseConstraintController(ConstraintTracker<T> r1) {
        this.tracker = r1;
    }

    public static final /* synthetic */ ConstraintTracker access$getTracker$p(BaseConstraintController r0) {
        return r0.tracker;
    }

    public abstract int getReason();

    public abstract boolean isConstrained(T r1);

    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    public boolean isCurrentlyConstrained(WorkSpec r1) {
        if (hasConstraint(r1) == true) goto L5;
        return false;
    L5:
        if (isConstrained(this.tracker.readSystemState()) == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    public Flow<ConstraintsState> track(Constraints r2) {
        return FlowKt.callbackFlow(new AnonymousClass1(this, null));
    }
}
