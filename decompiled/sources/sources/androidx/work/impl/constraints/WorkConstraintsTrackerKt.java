package androidx.work.impl.constraints;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;
import androidx.work.Logger;
import androidx.work.impl.constraints.controllers.BaseConstraintController;
import androidx.work.impl.constraints.controllers.BatteryChargingController;
import androidx.work.impl.constraints.controllers.BatteryNotLowController;
import androidx.work.impl.constraints.controllers.ConstraintController;
import androidx.work.impl.constraints.controllers.NetworkConnectedControllerPre28;
import androidx.work.impl.constraints.controllers.NetworkMeteredControllerPre28;
import androidx.work.impl.constraints.controllers.NetworkNotRoamingControllerPre28;
import androidx.work.impl.constraints.controllers.NetworkUnmeteredControllerPre28;
import androidx.work.impl.constraints.controllers.StorageNotLowController;
import androidx.work.impl.constraints.trackers.Trackers;
import androidx.work.impl.model.WorkSpec;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a)\u0010\b\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\t\u001a\u001d\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0017\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0010H\u0007¢\u0006\u0004\b\u0013\u0010\u0014\"\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017*$\b\u0002\u0010\u001b\"\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a0\u00182\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a0\u0018¨\u0006\u001c"}, d2 = {"Landroidx/work/impl/constraints/WorkConstraintsTracker;", "Landroidx/work/impl/model/WorkSpec;", "spec", "Lkotlinx/coroutines/CoroutineDispatcher;", "dispatcher", "Landroidx/work/impl/constraints/OnConstraintsStateChangedListener;", "listener", "Lkotlinx/coroutines/Job;", "listen", "(Landroidx/work/impl/constraints/WorkConstraintsTracker;Landroidx/work/impl/model/WorkSpec;Lkotlinx/coroutines/CoroutineDispatcher;Landroidx/work/impl/constraints/OnConstraintsStateChangedListener;)Lkotlinx/coroutines/Job;", "Landroidx/work/impl/constraints/trackers/Trackers;", "trackers", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/work/impl/constraints/controllers/ConstraintController;", "createControllers", "(Landroidx/work/impl/constraints/trackers/Trackers;)Ljava/util/List;", "Landroid/content/Context;", "context", "Landroidx/work/impl/constraints/NetworkRequestConstraintController;", "NetworkRequestConstraintController", "(Landroid/content/Context;)Landroidx/work/impl/constraints/NetworkRequestConstraintController;", _UrlKt.FRAGMENT_ENCODE_SET, "TAG", "Ljava/lang/String;", "Lkotlin/Function1;", "Landroidx/work/impl/constraints/ConstraintsState;", _UrlKt.FRAGMENT_ENCODE_SET, "OnConstraintState", "work-runtime_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class WorkConstraintsTrackerKt {
    private static final String TAG = null;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.work.impl.constraints.WorkConstraintsTrackerKt$listen$1", f = "WorkConstraintsTracker.kt", i = {}, l = {69}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.work.impl.constraints.WorkConstraintsTrackerKt$listen$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ OnConstraintsStateChangedListener $listener;
        final /* synthetic */ WorkSpec $spec;
        final /* synthetic */ WorkConstraintsTracker $this_listen;
        int label;

        @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
        /* renamed from: androidx.work.impl.constraints.WorkConstraintsTrackerKt$listen$1$1, reason: invalid class name and collision with other inner class name */
        public static final class C00111<T> implements FlowCollector {
            final /* synthetic */ OnConstraintsStateChangedListener $listener;
            final /* synthetic */ WorkSpec $spec;

            public C00111(OnConstraintsStateChangedListener r1, WorkSpec r2) {
                this.$listener = r1;
                this.$spec = r2;
            }

            public final Object emit(ConstraintsState r1, Continuation<? super Unit> r2) {
                this.$listener.onConstraintsStateChanged(this.$spec, r1);
                return Unit.INSTANCE;
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object r1, Continuation r2) {
                return emit((ConstraintsState) r1, r2);
            }
        }

        public AnonymousClass1(WorkConstraintsTracker r1, WorkSpec r2, OnConstraintsStateChangedListener r3, Continuation<? super AnonymousClass1> r4) {
            this.$this_listen = r1;
            this.$spec = r2;
            this.$listener = r3;
            super(2, r4);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
            return new AnonymousClass1(this.$this_listen, this.$spec, this.$listener, r4);
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
            Flow<ConstraintsState> r62 = this.$this_listen.track(this.$spec);
            C00111 r12 = new C00111(this.$listener, this.$spec);
            this.label = 1;
            if (r62.collect(r12, this) != r0) goto L12;
            return r0;
        }
    }

    static {
        TAG = Logger.tagWithPrefix("WorkConstraintsTracker");
    }

    public static final NetworkRequestConstraintController NetworkRequestConstraintController(Context r6) {
        return new NetworkRequestConstraintController((ConnectivityManager) r6.getSystemService("connectivity"), 0, 2, null);
    }

    public static final /* synthetic */ List access$createControllers(Trackers r0) {
        return createControllers(r0);
    }

    public static final /* synthetic */ String access$getTAG$p() {
        return TAG;
    }

    private static final List<ConstraintController> createControllers(Trackers r9) {
        List<ConstraintController> r2 = CollectionsKt.mutableListOf(new ConstraintController[]{new BatteryChargingController(r9.getBatteryChargingTracker()), new BatteryNotLowController(r9.getBatteryNotLowTracker()), new StorageNotLowController(r9.getStorageNotLowTracker())});
        if (Build.VERSION.SDK_INT < 28) goto L6;
        r2.add(NetworkRequestConstraintController(r9.getContext()));
        return r2;
    L6:
        r2.addAll(CollectionsKt.listOf(new BaseConstraintController[]{new NetworkConnectedControllerPre28(r9.getNetworkStateTracker()), new NetworkUnmeteredControllerPre28(r9.getNetworkStateTracker()), new NetworkNotRoamingControllerPre28(r9.getNetworkStateTracker()), new NetworkMeteredControllerPre28(r9.getNetworkStateTracker())}));
        return r2;
    }

    public static final Job listen(WorkConstraintsTracker r6, WorkSpec r7, CoroutineDispatcher r8, OnConstraintsStateChangedListener r9) {
        return BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(r8), null, null, new AnonymousClass1(r6, r7, r9, null), 3, null);
    }
}
