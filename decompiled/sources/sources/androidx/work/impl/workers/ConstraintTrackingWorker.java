package androidx.work.impl.workers;

import android.content.Context;
import android.os.Build;
import androidx.concurrent.futures.ListenableFutureKt;
import androidx.core.util.Consumer;
import androidx.work.CoroutineWorker;
import androidx.work.ListenableWorker;
import androidx.work.Logger;
import androidx.work.WorkerExceptionInfo;
import androidx.work.WorkerParameters;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.constraints.WorkConstraintsTracker;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.WorkerExceptionUtilsKt;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.Job;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001:\u0001\u0014B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\b\u001a\u00020\tH\u0096@¢\u0006\u0002\u0010\nJ\u000e\u0010\u000b\u001a\u00020\tH\u0082@¢\u0006\u0002\u0010\nJ&\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0082@¢\u0006\u0002\u0010\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Landroidx/work/impl/workers/ConstraintTrackingWorker;", "Landroidx/work/CoroutineWorker;", "appContext", "Landroid/content/Context;", "workerParameters", "Landroidx/work/WorkerParameters;", "<init>", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setupAndRunConstraintTrackingWork", "runWorker", "delegate", "Landroidx/work/ListenableWorker;", "workConstraintsTracker", "Landroidx/work/impl/constraints/WorkConstraintsTracker;", "workSpec", "Landroidx/work/impl/model/WorkSpec;", "(Landroidx/work/ListenableWorker;Landroidx/work/impl/constraints/WorkConstraintsTracker;Landroidx/work/impl/model/WorkSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ConstraintUnsatisfiedException", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nConstraintTrackingWorker.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConstraintTrackingWorker.kt\nandroidx/work/impl/workers/ConstraintTrackingWorker\n+ 2 LoggerExt.kt\nandroidx/work/LoggerExtKt\n*L\n1#1,168:1\n29#2:169\n19#2:170\n19#2:171\n19#2:172\n*S KotlinDebug\n*F\n+ 1 ConstraintTrackingWorker.kt\nandroidx/work/impl/workers/ConstraintTrackingWorker\n*L\n65#1:169\n75#1:170\n78#1:171\n87#1:172\n*E\n"})
/* loaded from: classes4.dex */
public final class ConstraintTrackingWorker extends CoroutineWorker {
    private final WorkerParameters workerParameters;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/work/impl/workers/ConstraintTrackingWorker$ConstraintUnsatisfiedException;", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "stopReason", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(I)V", "getStopReason", "()I", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class ConstraintUnsatisfiedException extends CancellationException {
        private final int stopReason;

        public ConstraintUnsatisfiedException(int r1) {
            this.stopReason = r1;
        }

        public final int getStopReason() {
            return this.stopReason;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Landroidx/work/ListenableWorker$Result;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.work.impl.workers.ConstraintTrackingWorker$doWork$2", f = "ConstraintTrackingWorker.kt", i = {}, l = {58}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.work.impl.workers.ConstraintTrackingWorker$doWork$2, reason: invalid class name */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ListenableWorker.Result>, Object> {
        int label;
        final /* synthetic */ ConstraintTrackingWorker this$0;

        public AnonymousClass2(ConstraintTrackingWorker r1, Continuation<? super AnonymousClass2> r2) {
            this.this$0 = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new AnonymousClass2(this.this$0, r2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super ListenableWorker.Result> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super ListenableWorker.Result> r2) {
            return ((AnonymousClass2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r4) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L9;
            if (r1 != 1) goto L7;
            ResultKt.throwOnFailure(r4);
            return r4;
        L7:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L9:
            ResultKt.throwOnFailure(r4);
            ConstraintTrackingWorker r42 = this.this$0;
            this.label = 1;
            Object r3 = ConstraintTrackingWorker.access$setupAndRunConstraintTrackingWork(r42, this);
            if (r3 != r0) goto L12;
            return r0;
        L12:
            return r3;
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.work.impl.workers.ConstraintTrackingWorker", f = "ConstraintTrackingWorker.kt", i = {}, l = {125}, m = "runWorker", n = {}, s = {})
    /* renamed from: androidx.work.impl.workers.ConstraintTrackingWorker$runWorker$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ ConstraintTrackingWorker this$0;

        public AnonymousClass1(ConstraintTrackingWorker r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return ConstraintTrackingWorker.access$runWorker(this.this$0, null, null, null, this);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "Landroidx/work/ListenableWorker$Result;", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.work.impl.workers.ConstraintTrackingWorker$runWorker$2", f = "ConstraintTrackingWorker.kt", i = {0, 0, 0}, l = {134}, m = "invokeSuspend", n = {"atomicReason", "future", "constraintTrackingJob"}, s = {"L$0", "L$1", "L$2"})
    @SourceDebugExtension({"SMAP\nConstraintTrackingWorker.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConstraintTrackingWorker.kt\nandroidx/work/impl/workers/ConstraintTrackingWorker$runWorker$2\n+ 2 LoggerExt.kt\nandroidx/work/LoggerExtKt\n*L\n1#1,168:1\n22#2:169\n22#2:170\n*S KotlinDebug\n*F\n+ 1 ConstraintTrackingWorker.kt\nandroidx/work/impl/workers/ConstraintTrackingWorker$runWorker$2\n*L\n137#1:169\n144#1:170\n*E\n"})
    /* renamed from: androidx.work.impl.workers.ConstraintTrackingWorker$runWorker$2, reason: invalid class name and case insensitive filesystem */
    public static final class C01302 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ListenableWorker.Result>, Object> {
        final /* synthetic */ ListenableWorker $delegate;
        final /* synthetic */ WorkConstraintsTracker $workConstraintsTracker;
        final /* synthetic */ WorkSpec $workSpec;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        public C01302(ListenableWorker r1, WorkConstraintsTracker r2, WorkSpec r3, Continuation<? super C01302> r4) {
            this.$delegate = r1;
            this.$workConstraintsTracker = r2;
            this.$workSpec = r3;
            super(2, r4);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r4, Continuation<?> r5) {
            C01302 r0 = new C01302(this.$delegate, this.$workConstraintsTracker, this.$workSpec, r5);
            r0.L$0 = r4;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super ListenableWorker.Result> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super ListenableWorker.Result> r2) {
            return ((C01302) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference failed for: r1v0, types: [int, kotlinx.coroutines.Job] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r15) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ?? r1 = this.label;
            if (r1 == 0) goto L14;
            if (r1 != 1) goto L12;
            Job r12 = (Job) this.L$2;
            ListenableFuture<ListenableWorker.Result> r6 = (ListenableFuture) this.L$1;
            AtomicInteger r7 = (AtomicInteger) this.L$0;
            ResultKt.throwOnFailure(r15);     // Catch: Throwable -> L8 CancellationException -> L10
        L19:
            ListenableWorker.Result r152 = (ListenableWorker.Result) r15;     // Catch: Throwable -> L8 CancellationException -> L10
            Job.DefaultImpls.cancel$default(r12, null, 1, null);
            return r152;
        L10:
            e = e;
        L11:
            CancellationException r153 = e;
            String r02 = ConstraintTrackingWorkerKt.access$getTAG$p();     // Catch: Throwable -> L28
            ListenableWorker r14 = this.$delegate;     // Catch: Throwable -> L28
            Logger.get().debug(r02, "Delegated worker " + r14.getClass() + " was cancelled", r153);     // Catch: Throwable -> L28
            if (r7.get() == (-256)) goto L33;
            boolean r142 = true;
        L35:
            if (r6.isCancelled() == false) goto L39;
            if (r142 == true) goto L38;
            throw r153;     // Catch: Throwable -> L28
        L38:
            throw new ConstraintUnsatisfiedException(r7.get());     // Catch: Throwable -> L28
        L39:
            throw r153;     // Catch: Throwable -> L28
        L33:
            r142 = false;
        L8:
            th = move-exception;
            Throwable r154 = th;
        L26:
            String r03 = ConstraintTrackingWorkerKt.access$getTAG$p();     // Catch: Throwable -> L28
            ListenableWorker r143 = this.$delegate;     // Catch: Throwable -> L28
            Logger.get().debug(r03, "Delegated worker " + r143.getClass() + " threw exception in startWork.", r154);     // Catch: Throwable -> L28
            throw r154;     // Catch: Throwable -> L28
        L12:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L14:
            ResultKt.throwOnFailure(r15);
            CoroutineScope r62 = (CoroutineScope) this.L$0;
            AtomicInteger r10 = new AtomicInteger(-256);
            ListenableFuture<ListenableWorker.Result> r11 = this.$delegate.startWork();
            Job r63 = BuildersKt.launch$default(r62, null, null, new ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1(this.$workConstraintsTracker, this.$workSpec, r10, r11, null), 3, null);
            this.L$0 = r10;     // Catch: Throwable -> L22 CancellationException -> L24
            this.L$1 = r11;     // Catch: Throwable -> L22 CancellationException -> L24
            this.L$2 = r63;     // Catch: Throwable -> L22 CancellationException -> L24
            this.label = 1;     // Catch: Throwable -> L22 CancellationException -> L24
            Object r72 = ListenableFutureKt.await(r11, this);     // Catch: Throwable -> L22 CancellationException -> L24
            if (r72 != r0) goto L18;
            return r0;
        L18:
            r6 = r11;
            r15 = r72;
            r7 = r10;
            r12 = r63;
            goto L19
        L24:
            e = e;
            r7 = r10;
            r6 = r11;
        L22:
            th = move-exception;
            r154 = th;
        L28:
            th = move-exception;
            Job.DefaultImpls.cancel$default(r1, null, 1, null);
            throw th;
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.work.impl.workers.ConstraintTrackingWorker", f = "ConstraintTrackingWorker.kt", i = {0}, l = {97}, m = "setupAndRunConstraintTrackingWork", n = {"delegate"}, s = {"L$0"})
    /* renamed from: androidx.work.impl.workers.ConstraintTrackingWorker$setupAndRunConstraintTrackingWork$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01311 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ ConstraintTrackingWorker this$0;

        public C01311(ConstraintTrackingWorker r1, Continuation<? super C01311> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return ConstraintTrackingWorker.access$setupAndRunConstraintTrackingWork(this.this$0, this);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Landroidx/work/ListenableWorker$Result;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.work.impl.workers.ConstraintTrackingWorker$setupAndRunConstraintTrackingWork$5", f = "ConstraintTrackingWorker.kt", i = {}, l = {98}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.work.impl.workers.ConstraintTrackingWorker$setupAndRunConstraintTrackingWork$5, reason: invalid class name */
    public static final class AnonymousClass5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ListenableWorker.Result>, Object> {
        final /* synthetic */ ListenableWorker $delegate;
        final /* synthetic */ WorkConstraintsTracker $workConstraintsTracker;
        final /* synthetic */ WorkSpec $workSpec;
        int label;
        final /* synthetic */ ConstraintTrackingWorker this$0;

        public AnonymousClass5(ConstraintTrackingWorker r1, ListenableWorker r2, WorkConstraintsTracker r3, WorkSpec r4, Continuation<? super AnonymousClass5> r5) {
            this.this$0 = r1;
            this.$delegate = r2;
            this.$workConstraintsTracker = r3;
            this.$workSpec = r4;
            super(2, r5);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r7, Continuation<?> r8) {
            return new AnonymousClass5(this.this$0, this.$delegate, this.$workConstraintsTracker, this.$workSpec, r8);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super ListenableWorker.Result> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super ListenableWorker.Result> r2) {
            return ((AnonymousClass5) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r6) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L9;
            if (r1 != 1) goto L7;
            ResultKt.throwOnFailure(r6);
            return r6;
        L7:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L9:
            ResultKt.throwOnFailure(r6);
            ConstraintTrackingWorker r62 = this.this$0;
            ListenableWorker r12 = this.$delegate;
            WorkConstraintsTracker r3 = this.$workConstraintsTracker;
            WorkSpec r4 = this.$workSpec;
            this.label = 1;
            Object r5 = ConstraintTrackingWorker.access$runWorker(r62, r12, r3, r4, this);
            if (r5 != r0) goto L12;
            return r0;
        L12:
            return r5;
        }
    }

    public ConstraintTrackingWorker(Context r1, WorkerParameters r2) {
        super(r1, r2);
        this.workerParameters = r2;
    }

    public static final /* synthetic */ Object access$runWorker(ConstraintTrackingWorker r0, ListenableWorker r1, WorkConstraintsTracker r2, WorkSpec r3, Continuation r4) {
        return r0.runWorker(r1, r2, r3, r4);
    }

    public static final /* synthetic */ Object access$setupAndRunConstraintTrackingWork(ConstraintTrackingWorker r0, Continuation r1) {
        return r0.setupAndRunConstraintTrackingWork(r1);
    }

    private final Object runWorker(ListenableWorker r5, WorkConstraintsTracker r6, WorkSpec r7, Continuation<? super ListenableWorker.Result> r8) {
        if ((r8 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r8;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r4 = r0.result;
        Object r82 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r12 = r0.label;
        if (r12 == 0) goto L14;
        if (r12 != 1) goto L12;
        ResultKt.throwOnFailure(r4);
    L17:
        return r4;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r4);
        C01302 r42 = new C01302(r5, r6, r7, null);
        r0.label = 1;
        r4 = CoroutineScopeKt.coroutineScope(r42, r0);
        if (r4 != r82) goto L17;
        return r82;
    L7:
        r0 = new AnonymousClass1(this, r8);
        goto L8
    }

    private final Object setupAndRunConstraintTrackingWork(Continuation<? super ListenableWorker.Result> r15) {
        if ((r15 instanceof C01311) == false) goto L7;
        C01311 r0 = (C01311) r15;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r152 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L18;
        if (r2 != 1) goto L16;
        ListenableWorker r13 = (ListenableWorker) r0.L$0;
        ResultKt.throwOnFailure(r152);     // Catch: CancellationException -> L14
        ConstraintTrackingWorker r9 = this;
    L85:
        return (ListenableWorker.Result) r152;
    L42:
        e = move-exception;
        CancellationException r153 = e;
    L49:
        if (r9.isStopped() == true) goto L53;
        if ((r153 instanceof ConstraintUnsatisfiedException) == true) goto L53;
    L63:
        if ((r153 instanceof ConstraintUnsatisfiedException) == true) goto L65;
        throw r153;
    L65:
        return ListenableWorker.Result.retry();
    L53:
        if (Build.VERSION.SDK_INT >= 31) goto L56;
        int r14 = -512;
    L61:
        r13.stop(r14);
        goto L63
    L56:
        if (r9.isStopped() == false) goto L59;
        r14 = r9.getStopReason();
        goto L61
    L59:
        if ((r153 instanceof ConstraintUnsatisfiedException) == false) goto L67;
        r14 = ((ConstraintUnsatisfiedException) r153).getStopReason();
        goto L61
    L67:
        Segment$$ExternalSyntheticBUOutline1.m("Unreachable");
        return null;
    L14:
        e = move-exception;
        r153 = e;
        r9 = this;
        goto L49
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L18:
        ResultKt.throwOnFailure(r152);
        String r154 = getInputData().getString("androidx.work.impl.workers.ConstraintTrackingWorker.ARGUMENT_CLASS_NAME");
        if (r154 != null) goto L21;
    L75:
        Logger.get().error(ConstraintTrackingWorkerKt.access$getTAG$p(), "No worker to delegate to.");
        return ListenableWorker.Result.failure();
    L21:
        if (r154.length() == 0) goto L75;
        WorkManagerImpl r5 = WorkManagerImpl.getInstance(getApplicationContext());
        WorkSpec r122 = r5.getWorkDatabase().workSpecDao().getWorkSpec(getId().toString());
        if (r122 == null) goto L26;
        WorkConstraintsTracker r11 = new WorkConstraintsTracker(r5.getTrackers());
        if (r11.areAllConstraintsMet(r122) == true) goto L31;
        String r142 = ConstraintTrackingWorkerKt.access$getTAG$p();
        Logger.get().debug(r142, "Constraints not met for delegate " + r154 + ". Requesting retry.");
        return ListenableWorker.Result.retry();
    L31:
        String r6 = ConstraintTrackingWorkerKt.access$getTAG$p();
        Logger.get().debug(r6, "Constraints met for delegate " + r154);
        ListenableWorker r10 = getWorkerFactory().createWorkerWithDefaultFallback(getApplicationContext(), r154, this.workerParameters);     // Catch: Throwable -> L69
        CoroutineDispatcher r155 = ExecutorsKt.from(this.workerParameters.getTaskExecutor().getMainThreadExecutor());     // Catch: CancellationException -> L46
        r9 = this;
        AnonymousClass5 r8 = new AnonymousClass5(r9, r10, r11, r122, null);     // Catch: CancellationException -> L44
        r0.L$0 = r10;     // Catch: CancellationException -> L44
        r0.label = 1;     // Catch: CancellationException -> L44
        r152 = BuildersKt.withContext(r155, r8, r0);     // Catch: CancellationException -> L44
        if (r152 != r12) goto L39;
        return r12;
    L39:
        r13 = r10;
    L44:
        e = e;
    L45:
        r153 = e;
        r13 = r10;
    L46:
        e = e;
        r9 = this;
    L69:
        th = move-exception;
        Logger.get().debug(ConstraintTrackingWorkerKt.access$getTAG$p(), "No worker to delegate to.");
        Consumer<WorkerExceptionInfo> r02 = r5.getConfiguration().getWorkerInitializationExceptionHandler();
        if (r02 == null) goto L74;
        WorkerExceptionUtilsKt.safeAccept(r02, new WorkerExceptionInfo(r154, this.workerParameters, th), ConstraintTrackingWorkerKt.access$getTAG$p());
    L74:
        return ListenableWorker.Result.failure();
    L26:
        return ListenableWorker.Result.failure();
    L7:
        r0 = new C01311(this, r15);
        goto L8
    }

    @Override // androidx.work.CoroutineWorker
    public Object doWork(Continuation<? super ListenableWorker.Result> r4) {
        return BuildersKt.withContext(ExecutorsKt.from(getBackgroundExecutor()), new AnonymousClass2(this, null), r4);
    }
}
