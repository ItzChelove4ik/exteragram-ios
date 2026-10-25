package androidx.work.impl.utils;

import android.os.Build;
import androidx.work.Configuration;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.WorkRequest;
import androidx.work.impl.Scheduler;
import androidx.work.impl.WorkContinuationImpl;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.workers.ConstraintTrackingWorker;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u001a'\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a\u0017\u0010\u000b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0007¢\u0006\u0004\b\u000b\u0010\f\u001a\u0017\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0000¢\u0006\u0004\b\r\u0010\f\u001a%\u0010\u0011\u001a\u00020\t2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\n\u001a\u00020\tH\u0000¢\u0006\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Landroidx/work/impl/WorkDatabase;", "workDatabase", "Landroidx/work/Configuration;", "configuration", "Landroidx/work/impl/WorkContinuationImpl;", "continuation", _UrlKt.FRAGMENT_ENCODE_SET, "checkContentUriTriggerWorkerLimits", "(Landroidx/work/impl/WorkDatabase;Landroidx/work/Configuration;Landroidx/work/impl/WorkContinuationImpl;)V", "Landroidx/work/impl/model/WorkSpec;", "workSpec", "tryDelegateRemoteListenableWorker", "(Landroidx/work/impl/model/WorkSpec;)Landroidx/work/impl/model/WorkSpec;", "tryDelegateConstrainedWorkSpec", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/work/impl/Scheduler;", "schedulers", "wrapWorkSpecIfNeeded", "(Ljava/util/List;Landroidx/work/impl/model/WorkSpec;)Landroidx/work/impl/model/WorkSpec;", "work-runtime_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nEnqueueUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EnqueueUtils.kt\nandroidx/work/impl/utils/EnqueueUtilsKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Data_.kt\nandroidx/work/Data\n*L\n1#1,149:1\n1788#2,4:150\n1#3:154\n231#4:155\n231#4:156\n231#4:157\n*S KotlinDebug\n*F\n+ 1 EnqueueUtils.kt\nandroidx/work/impl/utils/EnqueueUtilsKt\n*L\n42#1:150,4\n68#1:155\n69#1:156\n70#1:157\n*E\n"})
/* loaded from: classes.dex */
public abstract class EnqueueUtilsKt {
    public static final void checkContentUriTriggerWorkerLimits(WorkDatabase r6, Configuration r7, WorkContinuationImpl r8) {
        List r82 = CollectionsKt.mutableListOf(new WorkContinuationImpl[]{r8});
        int r1 = 0;
    L4:
        if (r82.isEmpty() == true) goto L21;
        WorkContinuationImpl r2 = (WorkContinuationImpl) CollectionsKt.removeLast(r82);
        List<? extends WorkRequest> r3 = r2.getWork();
        if ((r3 instanceof Collection) == true) goto L8;
    L10:
        Iterator<T> r32 = r3.iterator();
        int r4 = 0;
    L12:
        if (r32.hasNext() == false) goto L18;
        if (((WorkRequest) r32.next()).getWorkSpec().constraints.hasContentUriTriggers() == false) goto L12;
        r4 = r4 + 1;
        if (r4 >= 0) goto L12;
        CollectionsKt.throwCountOverflow();
    L18:
        r1 = r1 + r4;
        List<WorkContinuationImpl> r22 = r2.getParents();
        if (r22 == null) goto L4;
        r82.addAll(r22);
        goto L4
    L8:
        if (r3.isEmpty() == false) goto L10;
        r4 = 0;
        goto L18
    L21:
        if (r1 == 0) goto L40;
        int r62 = r6.workSpecDao().countNonFinishedContentUriTriggerWorkers();
        int r72 = r7.getContentUriTriggerWorkersLimit();
        if ((r62 + r1) > r72) goto L27;
        return;
    L27:
        throw new IllegalArgumentException("Too many workers with contentUriTriggers are enqueued:\ncontentUriTrigger workers limit: " + r72 + ";\nalready enqueued count: " + r62 + ";\ncurrent enqueue operation count: " + r1 + ".\nTo address this issue you can: \n1. enqueue less workers or batch some of workers with content uri triggers together;\n2. increase limit via Configuration.Builder.setContentUriTriggerWorkersLimit;\nPlease beware that workers with content uri triggers immediately occupy slots in JobScheduler so no updates to content uris are missed.");
    }

    public static final WorkSpec tryDelegateConstrainedWorkSpec(WorkSpec r37) {
        Constraints r0 = r37.constraints;
        String r2 = r37.workerClassName;
        if (Intrinsics.areEqual(r2, ConstraintTrackingWorker.class.getName()) == false) goto L5;
    L10:
        return r37;
    L5:
        if (r0.requiresBatteryNotLow() == false) goto L7;
    L8:
        Data r6 = new Data.Builder().putAll(r37.input).putString("androidx.work.impl.workers.ConstraintTrackingWorker.ARGUMENT_CLASS_NAME", r2).build();
        return WorkSpec.copy$default(r37, null, null, ConstraintTrackingWorker.class.getName(), null, r6, null, 0, 0, 0, null, 0, null, 0, 0, 0, 0, false, null, 0, 0, 0, 0, 0, null, null, 33554411, null);
    L7:
        if (r0.requiresStorageNotLow() == false) goto L10;
        goto L8
    }

    public static final WorkSpec tryDelegateRemoteListenableWorker(WorkSpec r37) {
        boolean r0 = r37.input.hasKeyWithValueOfType("androidx.work.multiprocess.RemoteListenableDelegatingWorker.ARGUMENT_REMOTE_LISTENABLE_WORKER_NAME", String.class);
        boolean r4 = r37.input.hasKeyWithValueOfType("androidx.work.impl.workers.RemoteListenableWorker.ARGUMENT_PACKAGE_NAME", String.class);
        boolean r3 = r37.input.hasKeyWithValueOfType("androidx.work.impl.workers.RemoteListenableWorker.ARGUMENT_CLASS_NAME", String.class);
        if (r0 == true) goto L8;
        if (r4 == false) goto L8;
        if (r3 == false) goto L8;
        String r02 = r37.workerClassName;
        return WorkSpec.copy$default(r37, null, null, "androidx.work.multiprocess.RemoteListenableDelegatingWorker", null, new Data.Builder().putAll(r37.input).putString("androidx.work.multiprocess.RemoteListenableDelegatingWorker.ARGUMENT_REMOTE_LISTENABLE_WORKER_NAME", r02).build(), null, 0, 0, 0, null, 0, null, 0, 0, 0, 0, false, null, 0, 0, 0, 0, 0, null, null, 33554411, null);
    L8:
        return r37;
    }

    public static final WorkSpec wrapWorkSpecIfNeeded(List<? extends Scheduler> r1, WorkSpec r2) {
        WorkSpec r12 = tryDelegateRemoteListenableWorker(r2);
        if (Build.VERSION.SDK_INT <= 25) goto L5;
        return r12;
    L5:
        return tryDelegateConstrainedWorkSpec(r12);
    }
}
