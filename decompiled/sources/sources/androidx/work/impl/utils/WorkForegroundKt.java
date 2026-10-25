package androidx.work.impl.utils;

import android.content.Context;
import android.os.Build;
import androidx.concurrent.futures.ListenableFutureKt;
import androidx.work.ForegroundInfo;
import androidx.work.ForegroundUpdater;
import androidx.work.ListenableWorker;
import androidx.work.Logger;
import androidx.work.impl.WorkerWrapperKt;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.properties.NotNullVar$$ExternalSyntheticBUOutline0;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.ExecutorsKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a8\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0086@¢\u0006\u0004\b\u000b\u0010\f\"\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Landroid/content/Context;", "context", "Landroidx/work/impl/model/WorkSpec;", "spec", "Landroidx/work/ListenableWorker;", "worker", "Landroidx/work/ForegroundUpdater;", "foregroundUpdater", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "taskExecutor", _UrlKt.FRAGMENT_ENCODE_SET, "workForeground", "(Landroid/content/Context;Landroidx/work/impl/model/WorkSpec;Landroidx/work/ListenableWorker;Landroidx/work/ForegroundUpdater;Landroidx/work/impl/utils/taskexecutor/TaskExecutor;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "TAG", "Ljava/lang/String;", "work-runtime_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public abstract class WorkForegroundKt {
    private static final String TAG = null;

    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "Ljava/lang/Void;", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.work.impl.utils.WorkForegroundKt$workForeground$2", f = "WorkForeground.kt", i = {}, l = {42, 50}, m = "invokeSuspend", n = {}, s = {})
    @SourceDebugExtension({"SMAP\nWorkForeground.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WorkForeground.kt\nandroidx/work/impl/utils/WorkForegroundKt$workForeground$2\n+ 2 LoggerExt.kt\nandroidx/work/LoggerExtKt\n*L\n1#1,55:1\n19#2:56\n*S KotlinDebug\n*F\n+ 1 WorkForeground.kt\nandroidx/work/impl/utils/WorkForegroundKt$workForeground$2\n*L\n49#1:56\n*E\n"})
    /* renamed from: androidx.work.impl.utils.WorkForegroundKt$workForeground$2, reason: invalid class name */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Void>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ ForegroundUpdater $foregroundUpdater;
        final /* synthetic */ WorkSpec $spec;
        final /* synthetic */ ListenableWorker $worker;
        int label;

        public AnonymousClass2(ListenableWorker r1, WorkSpec r2, ForegroundUpdater r3, Context r4, Continuation<? super AnonymousClass2> r5) {
            this.$worker = r1;
            this.$spec = r2;
            this.$foregroundUpdater = r3;
            this.$context = r4;
            super(2, r5);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r7, Continuation<?> r8) {
            return new AnonymousClass2(this.$worker, this.$spec, this.$foregroundUpdater, this.$context, r8);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Void> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Void> r2) {
            return ((AnonymousClass2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r8) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L11;
            if (r1 == 1) goto L10;
            if (r1 != 2) goto L8;
            ResultKt.throwOnFailure(r8);
            return r8;
        L8:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L10:
            ResultKt.throwOnFailure(r8);
        L14:
            ForegroundInfo r82 = (ForegroundInfo) r8;
            if (r82 == null) goto L20;
            String r12 = WorkForegroundKt.access$getTAG$p();
            WorkSpec r2 = this.$spec;
            Logger.get().debug(r12, "Updating notification for " + r2.workerClassName);
            ListenableFuture<Void> r83 = this.$foregroundUpdater.setForegroundAsync(this.$context, this.$worker.getId(), r82);
            this.label = 2;
            Object r7 = ListenableFutureKt.await(r83, this);
            if (r7 != r0) goto L19;
        L18:
            return r0;
        L19:
            return r7;
        L20:
            NotNullVar$$ExternalSyntheticBUOutline0.m("Worker was marked important (", this.$spec.workerClassName, ") but did not provide ForegroundInfo");
            return null;
        L11:
            ResultKt.throwOnFailure(r8);
            ListenableFuture<ForegroundInfo> r84 = this.$worker.getForegroundInfoAsync();
            ListenableWorker r13 = this.$worker;
            this.label = 1;
            r8 = WorkerWrapperKt.awaitWithin(r84, r13, this);
            if (r8 != r0) goto L14;
            goto L14
        }
    }

    static {
        TAG = Logger.tagWithPrefix("WorkForegroundRunnable");
    }

    public static final /* synthetic */ String access$getTAG$p() {
        return TAG;
    }

    public static final Object workForeground(Context r6, WorkSpec r7, ListenableWorker r8, ForegroundUpdater r9, TaskExecutor r10, Continuation<? super Unit> r11) {
        if (r7.expedited == false) goto L13;
        if (Build.VERSION.SDK_INT >= 31) goto L13;
        Object r62 = BuildersKt.withContext(ExecutorsKt.from(r10.getMainThreadExecutor()), new AnonymousClass2(r8, r7, r9, r6, null), r11);
        if (r62 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L11;
        return r62;
    L11:
        return Unit.INSTANCE;
    L13:
        return Unit.INSTANCE;
    }
}
