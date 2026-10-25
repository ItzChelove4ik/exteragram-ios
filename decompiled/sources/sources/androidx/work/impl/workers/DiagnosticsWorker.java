package androidx.work.impl.workers;

import android.content.Context;
import androidx.work.ListenableWorker;
import androidx.work.Logger;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.SystemIdInfoDao;
import androidx.work.impl.model.WorkNameDao;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkTagDao;
import java.util.List;
import kotlin.Metadata;
import kotlin.time.DurationKt;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Landroidx/work/impl/workers/DiagnosticsWorker;", "Landroidx/work/Worker;", "context", "Landroid/content/Context;", "parameters", "Landroidx/work/WorkerParameters;", "<init>", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class DiagnosticsWorker extends Worker {
    public DiagnosticsWorker(Context r1, WorkerParameters r2) {
        super(r1, r2);
    }

    @Override // androidx.work.Worker
    public ListenableWorker.Result doWork() {
        WorkManagerImpl r8 = WorkManagerImpl.getInstance(getApplicationContext());
        WorkDatabase r0 = r8.getWorkDatabase();
        WorkSpecDao r1 = r0.workSpecDao();
        WorkNameDao r2 = r0.workNameDao();
        WorkTagDao r3 = r0.workTagDao();
        SystemIdInfoDao r02 = r0.systemIdInfoDao();
        List<WorkSpec> r82 = r1.getRecentlyCompletedWork(r8.getConfiguration().getClock().currentTimeMillis() - DurationKt.MILLIS_IN_DAY);
        List<WorkSpec> r4 = r1.getRunningWork();
        List<WorkSpec> r12 = r1.getAllEligibleWorkSpecsForScheduling(200);
        if (r82.isEmpty() == true) goto L6;
        Logger.get().info(DiagnosticsWorkerKt.access$getTAG$p(), "Recently completed work:\n\n");
        Logger.get().info(DiagnosticsWorkerKt.access$getTAG$p(), DiagnosticsWorkerKt.access$workSpecRows(r2, r3, r02, r82));
    L6:
        if (r4.isEmpty() == true) goto L9;
        Logger.get().info(DiagnosticsWorkerKt.access$getTAG$p(), "Running work:\n\n");
        Logger.get().info(DiagnosticsWorkerKt.access$getTAG$p(), DiagnosticsWorkerKt.access$workSpecRows(r2, r3, r02, r4));
    L9:
        if (r12.isEmpty() == true) goto L12;
        Logger.get().info(DiagnosticsWorkerKt.access$getTAG$p(), "Enqueued work:\n\n");
        Logger.get().info(DiagnosticsWorkerKt.access$getTAG$p(), DiagnosticsWorkerKt.access$workSpecRows(r2, r3, r02, r12));
    L12:
        return ListenableWorker.Result.success();
    }
}
