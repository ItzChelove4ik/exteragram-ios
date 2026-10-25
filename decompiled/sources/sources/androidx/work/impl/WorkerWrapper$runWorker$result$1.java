package androidx.work.impl;

import android.content.Context;
import androidx.work.ForegroundUpdater;
import androidx.work.ListenableWorker;
import androidx.work.Logger;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.WorkForegroundKt;
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
import kotlinx.coroutines.CoroutineScope;
import okhttp3.internal.http.HttpStatusCodesKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "Landroidx/work/ListenableWorker$Result;", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.work.impl.WorkerWrapper$runWorker$result$1", f = "WorkerWrapper.kt", i = {}, l = {297, HttpStatusCodesKt.HTTP_PERM_REDIRECT}, m = "invokeSuspend", n = {}, s = {})
@SourceDebugExtension({"SMAP\nWorkerWrapper.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WorkerWrapper.kt\nandroidx/work/impl/WorkerWrapper$runWorker$result$1\n+ 2 LoggerExt.kt\nandroidx/work/LoggerExtKt\n*L\n1#1,623:1\n19#2:624\n*S KotlinDebug\n*F\n+ 1 WorkerWrapper.kt\nandroidx/work/impl/WorkerWrapper$runWorker$result$1\n*L\n304#1:624\n*E\n"})
/* loaded from: classes4.dex */
public final class WorkerWrapper$runWorker$result$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ListenableWorker.Result>, Object> {
    final /* synthetic */ ForegroundUpdater $foregroundUpdater;
    final /* synthetic */ ListenableWorker $worker;
    int label;
    final /* synthetic */ WorkerWrapper this$0;

    public WorkerWrapper$runWorker$result$1(WorkerWrapper r1, ListenableWorker r2, ForegroundUpdater r3, Continuation<? super WorkerWrapper$runWorker$result$1> r4) {
        this.this$0 = r1;
        this.$worker = r2;
        this.$foregroundUpdater = r3;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
        return new WorkerWrapper$runWorker$result$1(this.this$0, this.$worker, this.$foregroundUpdater, r4);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super ListenableWorker.Result> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope r1, Continuation<? super ListenableWorker.Result> r2) {
        return ((WorkerWrapper$runWorker$result$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r11) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L11;
        if (r1 == 1) goto L10;
        if (r1 != 2) goto L8;
        ResultKt.throwOnFailure(r11);
        return r11;
    L8:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L10:
        ResultKt.throwOnFailure(r11);
        WorkerWrapper$runWorker$result$1 r9 = this;
    L14:
        String r10 = WorkerWrapperKt.access$getTAG$p();
        WorkerWrapper r112 = r9.this$0;
        Logger.get().debug(r10, "Starting work for " + r112.getWorkSpec().workerClassName);
        ListenableFuture<ListenableWorker.Result> r102 = r9.$worker.startWork();
        ListenableWorker r113 = r9.$worker;
        r9.label = 2;
        Object r103 = WorkerWrapperKt.awaitWithin(r102, r113, r9);
        if (r103 != r0) goto L17;
    L16:
        return r0;
    L17:
        return r103;
    L11:
        ResultKt.throwOnFailure(r11);
        Context r4 = WorkerWrapper.access$getAppContext$p(this.this$0);
        WorkSpec r5 = this.this$0.getWorkSpec();
        ListenableWorker r6 = this.$worker;
        ForegroundUpdater r7 = this.$foregroundUpdater;
        TaskExecutor r8 = WorkerWrapper.access$getWorkTaskExecutor$p(this.this$0);
        this.label = 1;
        r9 = this;
        if (WorkForegroundKt.workForeground(r4, r5, r6, r7, r8, r9) != r0) goto L14;
        goto L14
    }
}
