package androidx.work.impl.utils;

import androidx.work.Operation;
import androidx.work.OperationKt;
import androidx.work.WorkInfo$State;
import androidx.work.impl.Scheduler;
import androidx.work.impl.Schedulers;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.WorkSpecDao;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u001f\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u0017\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0007\u0010\b\u001a\u001f\u0010\u000b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u000b\u0010\f\u001a\u001d\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u001d\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Landroidx/work/impl/WorkManagerImpl;", "workManagerImpl", _UrlKt.FRAGMENT_ENCODE_SET, "workSpecId", _UrlKt.FRAGMENT_ENCODE_SET, "cancel", "(Landroidx/work/impl/WorkManagerImpl;Ljava/lang/String;)V", "reschedulePendingWorkers", "(Landroidx/work/impl/WorkManagerImpl;)V", "Landroidx/work/impl/WorkDatabase;", "workDatabase", "iterativelyCancelWorkAndDependents", "(Landroidx/work/impl/WorkDatabase;Ljava/lang/String;)V", "Ljava/util/UUID;", "id", "Landroidx/work/Operation;", "forId", "(Ljava/util/UUID;Landroidx/work/impl/WorkManagerImpl;)Landroidx/work/Operation;", "name", "forNameInline", "(Ljava/lang/String;Landroidx/work/impl/WorkManagerImpl;)V", "work-runtime_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
@JvmName(name = "CancelWorkRunnable")
/* loaded from: classes4.dex */
public abstract class CancelWorkRunnable {
    /* renamed from: $r8$lambda$7CMxqlNCknD_Q1NSUJ5-L0sWGBQ, reason: not valid java name */
    public static void m1001$r8$lambda$7CMxqlNCknD_Q1NSUJ5L0sWGBQ(WorkDatabase r0, String r1, WorkManagerImpl r2) {
        Iterator<String> r02 = r0.workSpecDao().getUnfinishedWorkWithName(r1).iterator();
    L4:
        if (r02.hasNext() == false) goto L6;
        cancel(r2, r02.next());
        goto L4
    }

    public static Unit $r8$lambda$8EsI_obE8bQExJ_QYqMsYGJK8YY(WorkManagerImpl r2, UUID r3) {
        r2.getWorkDatabase().runInTransaction(new CancelWorkRunnable$$ExternalSyntheticLambda2(r2, r3));
        reschedulePendingWorkers(r2);
        return Unit.INSTANCE;
    }

    public static void $r8$lambda$9OOzgm9vmuSGphBLxiraZo1D9nc(WorkManagerImpl r0, UUID r1) {
        cancel(r0, r1.toString());
    }

    private static final void cancel(WorkManagerImpl r2, String r3) {
        iterativelyCancelWorkAndDependents(r2.getWorkDatabase(), r3);
        r2.getProcessor().stopAndCancelWork(r3, 1);
        Iterator<Scheduler> r22 = r2.getSchedulers().iterator();
    L4:
        if (r22.hasNext() == false) goto L6;
        r22.next().cancel(r3);
        goto L4
    }

    public static final Operation forId(UUID r3, WorkManagerImpl r4) {
        return OperationKt.launchOperation(r4.getConfiguration().getTracer(), "CancelWorkById", r4.getWorkTaskExecutor().getSerialTaskExecutor(), new CancelWorkRunnable$$ExternalSyntheticLambda0(r4, r3));
    }

    public static final void forNameInline(String r2, WorkManagerImpl r3) {
        WorkDatabase r0 = r3.getWorkDatabase();
        r0.runInTransaction(new CancelWorkRunnable$$ExternalSyntheticLambda1(r0, r2, r3));
    }

    private static final void iterativelyCancelWorkAndDependents(WorkDatabase r4, String r5) {
        WorkSpecDao r0 = r4.workSpecDao();
        DependencyDao r42 = r4.dependencyDao();
        List r52 = CollectionsKt.mutableListOf(new String[]{r5});
    L4:
        if (r52.isEmpty() == true) goto L11;
        String r1 = (String) CollectionsKt.removeLast(r52);
        WorkInfo$State r2 = r0.getState(r1);
        if (r2 == WorkInfo$State.SUCCEEDED) goto L10;
        if (r2 == WorkInfo$State.FAILED) goto L10;
        r0.setCancelledState(r1);
    L10:
        r52.addAll(r42.getDependentWorkIds(r1));
        goto L4
    }

    private static final void reschedulePendingWorkers(WorkManagerImpl r2) {
        Schedulers.schedule(r2.getConfiguration(), r2.getWorkDatabase(), r2.getSchedulers());
    }
}
