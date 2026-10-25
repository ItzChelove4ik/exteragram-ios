package androidx.work.impl;

import androidx.work.Configuration;
import androidx.work.ExistingWorkPolicy;
import androidx.work.Operation;
import androidx.work.OperationKt;
import androidx.work.WorkInfo$State;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import androidx.work.WorkerFactory$$ExternalSyntheticBUOutline0;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkTagDao;
import androidx.work.impl.utils.EnqueueRunnable;
import androidx.work.impl.utils.EnqueueUtilsKt;
import com.sun.jna.Native$$ExternalSyntheticBUOutline0;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aK\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\n\u001a\u00020\t2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002¢\u0006\u0004\b\u000f\u0010\u0010\u001a#\u0010\u0016\u001a\u00020\u0015*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0013H\u0007¢\u0006\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Landroidx/work/impl/Processor;", "processor", "Landroidx/work/impl/WorkDatabase;", "workDatabase", "Landroidx/work/Configuration;", "configuration", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/work/impl/Scheduler;", "schedulers", "Landroidx/work/impl/model/WorkSpec;", "newWorkSpec", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "tags", "Landroidx/work/WorkManager$UpdateResult;", "updateWorkImpl", "(Landroidx/work/impl/Processor;Landroidx/work/impl/WorkDatabase;Landroidx/work/Configuration;Ljava/util/List;Landroidx/work/impl/model/WorkSpec;Ljava/util/Set;)Landroidx/work/WorkManager$UpdateResult;", "Landroidx/work/impl/WorkManagerImpl;", "name", "Landroidx/work/WorkRequest;", "workRequest", "Landroidx/work/Operation;", "enqueueUniquelyNamedPeriodic", "(Landroidx/work/impl/WorkManagerImpl;Ljava/lang/String;Landroidx/work/WorkRequest;)Landroidx/work/Operation;", "work-runtime_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
@JvmName(name = "WorkerUpdater")
@SourceDebugExtension({"SMAP\nWorkerUpdater.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WorkerUpdater.kt\nandroidx/work/impl/WorkerUpdater\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,165:1\n1869#2,2:166\n*S KotlinDebug\n*F\n+ 1 WorkerUpdater.kt\nandroidx/work/impl/WorkerUpdater\n*L\n56#1:166,2\n*E\n"})
/* loaded from: classes.dex */
public abstract class WorkerUpdater {
    public static Unit $r8$lambda$21ictocsYpVpgrrtxf26fSYa5jc(WorkManagerImpl r46, String r47, WorkRequest r48) {
        WorkerUpdater$$ExternalSyntheticLambda1 r1 = new WorkerUpdater$$ExternalSyntheticLambda1(r48, r46, r47);
        WorkSpecDao r4 = r46.getWorkDatabase().workSpecDao();
        List<WorkSpec.IdAndState> r5 = r4.getWorkSpecIdAndStatesForName(r47);
        if (r5.size() > 1) goto L22;
        WorkSpec.IdAndState r52 = (WorkSpec.IdAndState) CollectionsKt.firstOrNull(r5);
        if (r52 != null) goto L8;
        r1.invoke();
        return Unit.INSTANCE;
    L8:
        WorkSpec r6 = r4.getWorkSpec(r52.id);
        if (r6 != null) goto L11;
        WorkerFactory$$ExternalSyntheticBUOutline0.m("WorkSpec with ", r52.id, ", that matches a name \"", r47, "\", wasn't found");
        return null;
    L11:
        if (r6.isPeriodic() == true) goto L13;
        ByteString$$ExternalSyntheticBUOutline0.m("Can't update OneTimeWorker to Periodic Worker. Update operation must preserve worker's type.");
        return null;
    L13:
        if (r52.state != WorkInfo$State.CANCELLED) goto L16;
        r4.delete(r52.id);
        r1.invoke();
        return Unit.INSTANCE;
    L16:
        WorkSpec r44 = WorkSpec.copy$default(r48.getWorkSpec(), r52.id, null, null, null, null, null, 0, 0, 0, null, 0, null, 0, 0, 0, 0, false, null, 0, 0, 0, 0, 0, null, null, 33554430, null);
        updateWorkImpl(r46.getProcessor(), r46.getWorkDatabase(), r46.getConfiguration(), r46.getSchedulers(), r44, r48.getTags());
        return Unit.INSTANCE;
    L22:
        ByteString$$ExternalSyntheticBUOutline0.m("Can't apply UPDATE policy to the chains of work.");
        return null;
    }

    /* renamed from: $r8$lambda$5dx0Z4eBB8i1jJkoxlWJyGBK-xI, reason: not valid java name */
    public static void m979$r8$lambda$5dx0Z4eBB8i1jJkoxlWJyGBKxI(WorkDatabase r40, WorkSpec r41, WorkSpec r42, List r43, String r44, Set r45, boolean r46) {
        WorkSpecDao r2 = r40.workSpecDao();
        WorkTagDao r3 = r40.workTagDao();
        WorkInfo$State r6 = r41.state;
        int r4 = r41.runAttemptCount;
        long r7 = r41.lastEnqueueTime;
        int r31 = r41.getGeneration() + 1;
        WorkSpec r5 = WorkSpec.copy$default(r42, null, r6, null, null, null, null, 0, 0, 0, null, r4, null, 0, r7, 0, 0, false, null, r41.getPeriodCount(), r31, r41.getNextScheduleTimeOverride(), r41.getNextScheduleTimeOverrideGeneration(), 0, null, null, 29613053, null);
        if (r42.getNextScheduleTimeOverrideGeneration() != 1) goto L5;
        r5.setNextScheduleTimeOverride(r42.getNextScheduleTimeOverride());
        r5.setNextScheduleTimeOverrideGeneration(r5.getNextScheduleTimeOverrideGeneration() + 1);
    L5:
        r2.updateWorkSpec(EnqueueUtilsKt.wrapWorkSpecIfNeeded(r43, r5));
        r3.deleteByWorkSpecId(r44);
        r3.insertTags(r44, r45);
        if (r46 == true) goto L9;
        r2.markWorkSpecScheduled(r44, -1);
        r40.workProgressDao().delete(r44);
        return;
    }

    public static String $r8$lambda$KcsYtEjMmPba5EsO_zkeL_Tq55o(WorkSpec r0) {
        if (r0.isPeriodic() == false) goto L6;
        return "Periodic";
    L6:
        return "OneTime";
    }

    public static Unit $r8$lambda$M_uZdI89v6CUunycKVfl3IJJSEM(WorkRequest r2, WorkManagerImpl r3, String r4) {
        List r22 = CollectionsKt.listOf(r2);
        EnqueueRunnable.enqueue(new WorkContinuationImpl(r3, r4, ExistingWorkPolicy.KEEP, r22));
        return Unit.INSTANCE;
    }

    public static final Operation enqueueUniquelyNamedPeriodic(WorkManagerImpl r4, String r5, WorkRequest r6) {
        return OperationKt.launchOperation(r4.getConfiguration().getTracer(), "enqueueUniquePeriodic_" + r5, r4.getWorkTaskExecutor().getSerialTaskExecutor(), new WorkerUpdater$$ExternalSyntheticLambda0(r4, r5, r6));
    }

    private static final WorkManager.UpdateResult updateWorkImpl(Processor r8, WorkDatabase r9, Configuration r10, List<? extends Scheduler> r11, WorkSpec r12, Set<String> r13) {
        String r5 = r12.id;
        WorkSpec r2 = r9.workSpecDao().getWorkSpec(r5);
        if (r2 != null) goto L5;
        Native$$ExternalSyntheticBUOutline0.m("Worker with ", r5, " doesn't exist");
        return null;
    L5:
        if (r2.state.isFinished() == false) goto L9;
        return WorkManager.UpdateResult.NOT_APPLIED;
    L9:
        if ((r2.isPeriodic() ^ r12.isPeriodic()) == true) goto L24;
        boolean r7 = r8.isEnqueued(r5);
        if (r7 == true) goto L16;
        Iterator<T> r82 = r11.iterator();
    L14:
        if (r82.hasNext() == false) goto L16;
        ((Scheduler) r82.next()).cancel(r5);
    L16:
        r9.runInTransaction(new WorkerUpdater$$ExternalSyntheticLambda3(r9, r2, r12, r11, r5, r13, r7));
        if (r7 == true) goto L19;
        Schedulers.schedule(r10, r9, r11);
    L19:
        if (r7 == false) goto L23;
        return WorkManager.UpdateResult.APPLIED_FOR_NEXT_RUN;
    L23:
        return WorkManager.UpdateResult.APPLIED_IMMEDIATELY;
    L24:
        WorkerUpdater$$ExternalSyntheticLambda2 r83 = new WorkerUpdater$$ExternalSyntheticLambda2();
        throw new UnsupportedOperationException("Can't update " + ((String) r83.invoke(r2)) + " Worker to " + ((String) r83.invoke(r12)) + " Worker. Update operation must preserve worker's type.");
    }
}
