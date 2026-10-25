package androidx.work.impl;

import android.content.Context;
import androidx.work.Configuration;
import androidx.work.Logger;
import androidx.work.impl.background.systemalarm.RescheduleReceiver;
import androidx.work.impl.utils.PackageManagerHelper;
import androidx.work.impl.utils.ProcessUtils;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.time.DurationKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\u001a+\u0010\b\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0000¢\u0006\u0004\b\b\u0010\t\"\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\f\"\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "Landroid/content/Context;", "appContext", "Landroidx/work/Configuration;", "configuration", "Landroidx/work/impl/WorkDatabase;", "db", _UrlKt.FRAGMENT_ENCODE_SET, "maybeLaunchUnfinishedWorkListener", "(Lkotlinx/coroutines/CoroutineScope;Landroid/content/Context;Landroidx/work/Configuration;Landroidx/work/impl/WorkDatabase;)V", _UrlKt.FRAGMENT_ENCODE_SET, "TAG", "Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "MAX_DELAY_MS", "J", "work-runtime_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class UnfinishedWorkListenerKt {
    private static final long MAX_DELAY_MS = 0;
    private static final String TAG = null;

    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\t\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/flow/FlowCollector;", "throwable", _UrlKt.FRAGMENT_ENCODE_SET, "attempt", _UrlKt.FRAGMENT_ENCODE_SET}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.work.impl.UnfinishedWorkListenerKt$maybeLaunchUnfinishedWorkListener$1", f = "UnfinishedWorkListener.kt", i = {}, l = {59}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.work.impl.UnfinishedWorkListenerKt$maybeLaunchUnfinishedWorkListener$1, reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function4<FlowCollector<? super Boolean>, Throwable, Long, Continuation<? super Boolean>, Object> {
        /* synthetic */ long J$0;
        /* synthetic */ Object L$0;
        int label;

        public AnonymousClass1(Continuation<? super AnonymousClass1> r2) {
            super(4, r2);
        }

        @Override // kotlin.jvm.functions.Function4
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super Boolean> r7, Throwable r8, Long r9, Continuation<? super Boolean> r10) {
            return invoke(r7, r8, r9.longValue(), r10);
        }

        public final Object invoke(FlowCollector<? super Boolean> r1, Throwable r2, long r3, Continuation<? super Boolean> r5) {
            AnonymousClass1 r0 = new AnonymousClass1(r5);
            r0.L$0 = r2;
            r0.J$0 = r3;
            return r0.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r8) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L8;
            if (r1 != 1) goto L6;
            ResultKt.throwOnFailure(r8);
        L12:
            return Boxing.boxBoolean(true);
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r8);
            Throwable r82 = (Throwable) this.L$0;
            long r3 = this.J$0;
            Logger.get().error(UnfinishedWorkListenerKt.access$getTAG$p(), "Cannot check for unfinished work", r82);
            long r32 = Math.min(r3 * NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS, UnfinishedWorkListenerKt.access$getMAX_DELAY_MS$p());
            this.label = 1;
            if (DelayKt.delay(r32, this) != r0) goto L12;
            return r0;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "hasUnfinishedWork", _UrlKt.FRAGMENT_ENCODE_SET}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.work.impl.UnfinishedWorkListenerKt$maybeLaunchUnfinishedWorkListener$2", f = "UnfinishedWorkListener.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.work.impl.UnfinishedWorkListenerKt$maybeLaunchUnfinishedWorkListener$2, reason: invalid class name */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<Boolean, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $appContext;
        /* synthetic */ boolean Z$0;
        int label;

        public AnonymousClass2(Context r1, Continuation<? super AnonymousClass2> r2) {
            this.$appContext = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
            AnonymousClass2 r0 = new AnonymousClass2(this.$appContext, r3);
            r0.Z$0 = ((Boolean) r2).booleanValue();
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Boolean r1, Continuation<? super Unit> r2) {
            return invoke(r1.booleanValue(), r2);
        }

        public final Object invoke(boolean r1, Continuation<? super Unit> r2) {
            return ((AnonymousClass2) create(Boolean.valueOf(r1), r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) goto L6;
            ResultKt.throwOnFailure(r2);
            boolean r22 = this.Z$0;
            PackageManagerHelper.setComponentEnabled(this.$appContext, RescheduleReceiver.class, r22);
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    static {
        TAG = Logger.tagWithPrefix("UnfinishedWorkListener");
        MAX_DELAY_MS = DurationKt.MILLIS_IN_HOUR;
    }

    public static final /* synthetic */ long access$getMAX_DELAY_MS$p() {
        return MAX_DELAY_MS;
    }

    public static final /* synthetic */ String access$getTAG$p() {
        return TAG;
    }

    public static final void maybeLaunchUnfinishedWorkListener(CoroutineScope r1, Context r2, Configuration r3, WorkDatabase r4) {
        if (ProcessUtils.isDefaultProcess(r2, r3) == false) goto L6;
        FlowKt.launchIn(FlowKt.onEach(FlowKt.distinctUntilChanged(FlowKt.conflate(FlowKt.retryWhen(r4.workSpecDao().hasUnfinishedWorkFlow(), new AnonymousClass1(null)))), new AnonymousClass2(r2, null)), r1);
        return;
    }
}
