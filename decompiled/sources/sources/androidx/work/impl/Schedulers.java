package androidx.work.impl;

import android.content.Context;
import androidx.work.Clock;
import androidx.work.Configuration;
import androidx.work.Logger;
import androidx.work.impl.background.systemjob.SystemJobScheduler;
import androidx.work.impl.background.systemjob.SystemJobService;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.utils.PackageManagerHelper;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public abstract class Schedulers {
    private static final String TAG = null;

    public static /* synthetic */ void $r8$lambda$1z_T5hxAFjQEk6BflvLItKL7TzY(Executor r0, List r1, Configuration r2, WorkDatabase r3, WorkGenerationalId r4, boolean r5) {
        r0.execute(new Schedulers$$ExternalSyntheticLambda1(r1, r4, r2, r3));
    }

    public static /* synthetic */ void $r8$lambda$UldOQUXkk9IlUfICp9vyZBPK1cE(List r3, WorkGenerationalId r4, Configuration r5, WorkDatabase r6) {
        Iterator r0 = r3.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        ((Scheduler) r0.next()).cancel(r4.getWorkSpecId());
        goto L4
    L6:
        schedule(r5, r6, r3);
    }

    static {
        TAG = Logger.tagWithPrefix("Schedulers");
    }

    public static Scheduler createBestAvailableBackgroundScheduler(Context r1, WorkDatabase r2, Configuration r3) {
        SystemJobScheduler r0 = new SystemJobScheduler(r1, r2, r3);
        PackageManagerHelper.setComponentEnabled(r1, SystemJobService.class, true);
        Logger.get().debug(TAG, "Created SystemJobScheduler and enabled SystemJobService");
        return r0;
    }

    private static void markScheduled(WorkSpecDao r2, Clock r3, List<WorkSpec> r4) {
        if (r4.size() <= 0) goto L8;
        long r0 = r3.currentTimeMillis();
        Iterator<WorkSpec> r32 = r4.iterator();
    L6:
        if (r32.hasNext() == false) goto L10;
        r2.markWorkSpecScheduled(r32.next().id, r0);
        goto L6
    L10:
        return;
    }

    public static void registerRescheduling(List<Scheduler> r1, Processor r2, Executor r3, WorkDatabase r4, Configuration r5) {
        r2.addExecutionListener(new Schedulers$$ExternalSyntheticLambda0(r3, r1, r5, r4));
    }

    public static void schedule(Configuration r3, WorkDatabase r4, List<Scheduler> r5) {
        if (r5 != null) goto L4;
        return;
    L4:
        if (r5.size() == 0) goto L45;
        WorkSpecDao r0 = r4.workSpecDao();
        r4.beginTransaction();
        List<WorkSpec> r1 = r0.getEligibleWorkForSchedulingWithContentUris();     // Catch: Throwable -> L10
        markScheduled(r0, r3.getClock(), r1);     // Catch: Throwable -> L10
        List<WorkSpec> r2 = r0.getEligibleWorkForScheduling(r3.getMaxSchedulerLimit());     // Catch: Throwable -> L10
        markScheduled(r0, r3.getClock(), r2);     // Catch: Throwable -> L10
        if (r1 == null) goto L12;
        r2.addAll(r1);     // Catch: Throwable -> L10
    L12:
        List<WorkSpec> r32 = r0.getAllEligibleWorkSpecsForScheduling(200);     // Catch: Throwable -> L10
        r4.setTransactionSuccessful();     // Catch: Throwable -> L10
        r4.endTransaction();
        if (r2.size() <= 0) goto L22;
        WorkSpec[] r42 = (WorkSpec[]) r2.toArray(new WorkSpec[r2.size()]);
        Iterator<Scheduler> r02 = r5.iterator();
    L17:
        if (r02.hasNext() == false) goto L22;
        Scheduler r12 = r02.next();
        if (r12.hasLimitedSchedulingSlots() == false) goto L17;
        r12.schedule(r42);
    L22:
        if (r32.size() <= 0) goto L44;
        WorkSpec[] r33 = (WorkSpec[]) r32.toArray(new WorkSpec[r32.size()]);
        Iterator<Scheduler> r43 = r5.iterator();
    L25:
        if (r43.hasNext() == false) goto L46;
        Scheduler r52 = r43.next();
        if (r52.hasLimitedSchedulingSlots() == true) goto L25;
        r52.schedule(r33);
        goto L25
    L46:
        return;
    L44:
        return;
    L10:
        th = move-exception;
        r4.endTransaction();
        throw th;
    }
}
