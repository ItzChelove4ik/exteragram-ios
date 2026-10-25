package androidx.work.impl.constraints;

import android.net.ConnectivityManager;
import android.net.NetworkRequest;
import android.os.Build;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.impl.constraints.controllers.ConstraintController;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.NetworkTypeCompatKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/work/impl/constraints/NetworkRequestConstraintController;", "Landroidx/work/impl/constraints/controllers/ConstraintController;", "connManager", "Landroid/net/ConnectivityManager;", "timeoutMs", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Landroid/net/ConnectivityManager;J)V", "track", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/work/impl/constraints/ConstraintsState;", "constraints", "Landroidx/work/Constraints;", "hasConstraint", _UrlKt.FRAGMENT_ENCODE_SET, "workSpec", "Landroidx/work/impl/model/WorkSpec;", "isCurrentlyConstrained", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public final class NetworkRequestConstraintController implements ConstraintController {
    private final ConnectivityManager connManager;
    private final long timeoutMs;

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/channels/ProducerScope;", "Landroidx/work/impl/constraints/ConstraintsState;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.work.impl.constraints.NetworkRequestConstraintController$track$1", f = "WorkConstraintsTracker.kt", i = {}, l = {191}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.work.impl.constraints.NetworkRequestConstraintController$track$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<ProducerScope<? super ConstraintsState>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Constraints $constraints;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ NetworkRequestConstraintController this$0;

        public static Unit $r8$lambda$EEF3RNdjJsLLDFf8FNMkKjZ6NCc(Function0 r0) {
            r0.invoke();
            return Unit.INSTANCE;
        }

        public static Unit $r8$lambda$U9H9NeuZSZvBxANXmk1Y2QUyWkY(Job r2, ProducerScope r3, ConstraintsState r4) {
            Job.DefaultImpls.cancel$default(r2, null, 1, null);
            r3.mo3532trySendJP2dKIU(r4);
            return Unit.INSTANCE;
        }

        public AnonymousClass1(Constraints r1, NetworkRequestConstraintController r2, Continuation<? super AnonymousClass1> r3) {
            this.$constraints = r1;
            this.this$0 = r2;
            super(2, r3);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
            AnonymousClass1 r0 = new AnonymousClass1(this.$constraints, this.this$0, r4);
            r0.L$0 = r3;
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
        public final Object invokeSuspend(Object r11) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L8;
            if (r1 != 1) goto L6;
            ResultKt.throwOnFailure(r11);
        L22:
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r11);
            ProducerScope r4 = (ProducerScope) this.L$0;
            NetworkRequest r112 = this.$constraints.getRequiredNetworkRequest();
            if (r112 != null) goto L11;
            r112 = NetworkTypeCompatKt.toNetworkRequest(this.$constraints.getRequiredNetworkType());
        L11:
            if (r112 != null) goto L14;
            SendChannel.DefaultImpls.close$default(r4.getChannel(), null, 1, null);
            return Unit.INSTANCE;
        L14:
            NetworkRequestConstraintController$track$1$$ExternalSyntheticLambda0 r2 = new NetworkRequestConstraintController$track$1$$ExternalSyntheticLambda0(BuildersKt.launch$default(r4, null, null, new NetworkRequestConstraintController$track$1$timeoutJob$1(this.this$0, r4, null), 3, null), r4);
            if (Build.VERSION.SDK_INT < 30) goto L17;
            Function0<Unit> r113 = SharedNetworkCallback.INSTANCE.addCallback(NetworkRequestConstraintController.access$getConnManager$p(this.this$0), r112, r2);
        L18:
            NetworkRequestConstraintController$track$1$$ExternalSyntheticLambda1 r12 = new NetworkRequestConstraintController$track$1$$ExternalSyntheticLambda1(r113);
            this.label = 1;
            if (ProduceKt.awaitClose(r4, r12, this) != r0) goto L22;
            return r0;
        L17:
            r113 = IndividualNetworkCallback.Companion.addCallback(NetworkRequestConstraintController.access$getConnManager$p(this.this$0), r112, r2);
            goto L18
        }
    }

    public NetworkRequestConstraintController(ConnectivityManager r1, long r2) {
        this.connManager = r1;
        this.timeoutMs = r2;
    }

    public /* synthetic */ NetworkRequestConstraintController(ConnectivityManager r1, long r2, int r4, DefaultConstructorMarker r5) {
        if ((r4 & 2) == 0) goto L5;
        r2 = 1000;
    L5:
        this(r1, r2);
    }

    public static final /* synthetic */ ConnectivityManager access$getConnManager$p(NetworkRequestConstraintController r0) {
        return r0.connManager;
    }

    public static final /* synthetic */ long access$getTimeoutMs$p(NetworkRequestConstraintController r2) {
        return r2.timeoutMs;
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    public boolean hasConstraint(WorkSpec r1) {
        if (r1.constraints.getRequiredNetworkRequest() == null) goto L5;
        return true;
    L5:
        if (r1.constraints.getRequiredNetworkType() != NetworkType.NOT_REQUIRED) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    public boolean isCurrentlyConstrained(WorkSpec r1) {
        if (hasConstraint(r1) == true) goto L6;
        return false;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("isCurrentlyConstrained() must never be called onNetworkRequestConstraintController. isCurrentlyConstrained() is called only on older platforms where NetworkRequest isn't supported");
        return false;
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    public Flow<ConstraintsState> track(Constraints r3) {
        return FlowKt.callbackFlow(new AnonymousClass1(r3, this, null));
    }
}
