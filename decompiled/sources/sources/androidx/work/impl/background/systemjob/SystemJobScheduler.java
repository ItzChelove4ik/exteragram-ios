package androidx.work.impl.background.systemjob;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.PersistableBundle;
import androidx.core.util.Consumer;
import androidx.work.Configuration;
import androidx.work.Logger;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.WorkInfo$State;
import androidx.work.impl.Scheduler;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.model.SystemIdInfo;
import androidx.work.impl.model.SystemIdInfoKt;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkSpecKt;
import androidx.work.impl.utils.IdGenerator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public class SystemJobScheduler implements Scheduler {
    private static final String TAG = null;
    private final Configuration mConfiguration;
    private final Context mContext;
    private final JobScheduler mJobScheduler;
    private final SystemJobInfoConverter mSystemJobInfoConverter;
    private final WorkDatabase mWorkDatabase;

    static {
        TAG = Logger.tagWithPrefix("SystemJobScheduler");
    }

    public SystemJobScheduler(Context r7, WorkDatabase r8, Configuration r9) {
        this(r7, r8, r9, JobSchedulerExtKt.getWmJobScheduler(r7), new SystemJobInfoConverter(r7, r9.getClock(), r9.isMarkingJobsAsImportantWhileForeground()));
    }

    public SystemJobScheduler(Context r1, WorkDatabase r2, Configuration r3, JobScheduler r4, SystemJobInfoConverter r5) {
        this.mContext = r1;
        this.mJobScheduler = r4;
        this.mSystemJobInfoConverter = r5;
        this.mWorkDatabase = r2;
        this.mConfiguration = r3;
    }

    public static void cancelAllInAllNamespaces(Context r2) {
        if (Build.VERSION.SDK_INT < 34) goto L5;
        JobSchedulerExtKt.getWmJobScheduler(r2).cancelAll();
    L5:
        JobScheduler r0 = (JobScheduler) r2.getSystemService("jobscheduler");
        List<JobInfo> r22 = getPendingJobs(r2, r0);
        if (r22 != null) goto L8;
        return;
    L8:
        if (r22.isEmpty() == true) goto L15;
        Iterator<JobInfo> r23 = r22.iterator();
    L11:
        if (r23.hasNext() == false) goto L16;
        cancelJobById(r0, r23.next().getId());
        goto L11
    L16:
        return;
    }

    private static void cancelJobById(JobScheduler r4, int r5) {
        r4.cancel(r5);     // Catch: Throwable -> L4
        return;
    L4:
        th = move-exception;
        Logger.get().error(TAG, String.format(Locale.getDefault(), "Exception while trying to cancel job (%d)", new Object[]{Integer.valueOf(r5)}), th);
    }

    private static List<Integer> getPendingJobIds(Context r2, JobScheduler r3, String r4) {
        List<JobInfo> r22 = getPendingJobs(r2, r3);
        if (r22 != null) goto L6;
        return null;
    L6:
        ArrayList r32 = new ArrayList(2);
        Iterator<JobInfo> r23 = r22.iterator();
    L8:
        if (r23.hasNext() == false) goto L14;
        JobInfo r0 = r23.next();
        WorkGenerationalId r1 = getWorkGenerationalIdFromJobInfo(r0);
        if (r1 == null) goto L8;
        if (r4.equals(r1.getWorkSpecId()) == false) goto L8;
        r32.add(Integer.valueOf(r0.getId()));
        goto L8
    L14:
        return r32;
    }

    public static List<JobInfo> getPendingJobs(Context r3, JobScheduler r4) {
        List<JobInfo> r42 = JobSchedulerExtKt.getSafePendingJobs(r4);
        if (r42 != null) goto L6;
        return null;
    L6:
        ArrayList r0 = new ArrayList(r42.size());
        ComponentName r1 = new ComponentName(r3, SystemJobService.class);
        Iterator<JobInfo> r32 = r42.iterator();
    L8:
        if (r32.hasNext() == false) goto L12;
        JobInfo r43 = r32.next();
        if (r1.equals(r43.getService()) == false) goto L8;
        r0.add(r43);
        goto L8
    L12:
        return r0;
    }

    private static WorkGenerationalId getWorkGenerationalIdFromJobInfo(JobInfo r3) {
        PersistableBundle r32 = r3.getExtras();
        if (r32 != null) goto L11;
        return null;
    L11:
        if (r32.containsKey("EXTRA_WORK_SPEC_ID") == false) goto L13;
        int r1 = r32.getInt("EXTRA_WORK_SPEC_GENERATION", 0);     // Catch: NullPointerException -> L10
        return new WorkGenerationalId(r32.getString("EXTRA_WORK_SPEC_ID"), r1);
    L13:
        return null;
    L14:
        return null;
    }

    public static boolean reconcileJobs(Context r6, WorkDatabase r7) {
        JobScheduler r0 = JobSchedulerExtKt.getWmJobScheduler(r6);
        List<JobInfo> r62 = getPendingJobs(r6, r0);
        List<String> r1 = r7.systemIdInfoDao().getWorkSpecIds();
        boolean r2 = false;
        if (r62 == null) goto L5;
        int r3 = r62.size();
    L6:
        HashSet r4 = new HashSet(r3);
        if (r62 != null) goto L9;
    L17:
        Iterator<String> r63 = r1.iterator();
    L19:
        if (r63.hasNext() == false) goto L23;
        if (r4.contains(r63.next()) == true) goto L19;
        Logger.get().debug(TAG, "Reconciling jobs");
        r2 = true;
    L23:
        if (r2 == false) goto L36;
        r7.beginTransaction();
        WorkSpecDao r64 = r7.workSpecDao();     // Catch: Throwable -> L29
        Iterator<String> r02 = r1.iterator();     // Catch: Throwable -> L29
    L27:
        if (r02.hasNext() == false) goto L31;
        r64.markWorkSpecScheduled(r02.next(), -1);     // Catch: Throwable -> L29
        goto L27
    L31:
        r7.setTransactionSuccessful();     // Catch: Throwable -> L29
        r7.endTransaction();
        return r2;
    L29:
        th = move-exception;
        r7.endTransaction();
        throw th;
    L36:
        return r2;
    L9:
        if (r62.isEmpty() == true) goto L17;
        Iterator<JobInfo> r65 = r62.iterator();
    L12:
        if (r65.hasNext() == false) goto L17;
        JobInfo r32 = r65.next();
        WorkGenerationalId r5 = getWorkGenerationalIdFromJobInfo(r32);
        if (r5 != null) goto L15;
        cancelJobById(r0, r32.getId());
        goto L12
    L15:
        r4.add(r5.getWorkSpecId());
        goto L12
    L5:
        r3 = 0;
        goto L6
    }

    @Override // androidx.work.impl.Scheduler
    public void cancel(String r4) {
        List<Integer> r0 = getPendingJobIds(this.mContext, this.mJobScheduler, r4);
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isEmpty() == true) goto L14;
        Iterator<Integer> r02 = r0.iterator();
    L8:
        if (r02.hasNext() == false) goto L10;
        int r1 = r02.next().intValue();
        cancelJobById(this.mJobScheduler, r1);
        goto L8
    L10:
        this.mWorkDatabase.systemIdInfoDao().removeSystemIdInfo(r4);
        return;
    }

    @Override // androidx.work.impl.Scheduler
    public boolean hasLimitedSchedulingSlots() {
        return true;
    }

    @Override // androidx.work.impl.Scheduler
    public void schedule(WorkSpec... r9) {
        IdGenerator r0 = new IdGenerator(this.mWorkDatabase);
        int r1 = r9.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L26;
        WorkSpec r3 = r9[r2];
        this.mWorkDatabase.beginTransaction();
        WorkSpec r4 = this.mWorkDatabase.workSpecDao().getWorkSpec(r3.id);     // Catch: Throwable -> L10
        if (r4 != null) goto L13;
        Logger.get().warning(TAG, "Skipping scheduling " + r3.id + " because it's no longer in the DB");     // Catch: Throwable -> L10
        this.mWorkDatabase.setTransactionSuccessful();     // Catch: Throwable -> L10
    L9:
        this.mWorkDatabase.endTransaction();
        r2 = r2 + 1;
        goto L3
    L13:
        if (r4.state == WorkInfo$State.ENQUEUED) goto L15;
        Logger.get().warning(TAG, "Skipping scheduling " + r3.id + " because it is no longer enqueued");     // Catch: Throwable -> L10
        this.mWorkDatabase.setTransactionSuccessful();     // Catch: Throwable -> L10
        goto L9
    L15:
        WorkGenerationalId r42 = WorkSpecKt.generationalId(r3);     // Catch: Throwable -> L10
        SystemIdInfo r5 = this.mWorkDatabase.systemIdInfoDao().getSystemIdInfo(r42);     // Catch: Throwable -> L10
        if (r5 == null) goto L18;
        int r6 = r5.systemId;     // Catch: Throwable -> L10
    L19:
        if (r5 != null) goto L21;
        this.mWorkDatabase.systemIdInfoDao().insertSystemIdInfo(SystemIdInfoKt.systemIdInfo(r42, r6));     // Catch: Throwable -> L10
    L21:
        scheduleInternal(r3, r6);     // Catch: Throwable -> L10
        this.mWorkDatabase.setTransactionSuccessful();     // Catch: Throwable -> L10
        goto L9
    L18:
        r6 = r0.nextJobSchedulerIdWithRange(this.mConfiguration.getMinJobSchedulerId(), this.mConfiguration.getMaxJobSchedulerId());     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        this.mWorkDatabase.endTransaction();
        throw th;
    }

    public void scheduleInternal(WorkSpec r7, int r8) {
        JobInfo r1 = this.mSystemJobInfoConverter.convert(r7, r8);
        Logger r2 = Logger.get();
        String r3 = TAG;
        r2.debug(r3, "Scheduling work ID " + r7.id + "Job ID " + r8);
    L13:
        e = move-exception;
        String r82 = JobSchedulerExtKt.createErrorMessage(this.mContext, this.mWorkDatabase, this.mConfiguration);
        Logger.get().error(TAG, r82);
        IllegalStateException r0 = new IllegalStateException(r82, e);
        Consumer<Throwable> r6 = this.mConfiguration.getSchedulingExceptionHandler();
        if (r6 == null) goto L20;
        r6.accept(r0);
        return;
    L20:
        throw r0;
    L11:
        th = move-exception;
        Logger.get().error(TAG, "Unable to schedule " + r7, th);
        return;
    L4:
        if (this.mJobScheduler.schedule(r1) != 0) goto L23;
        Logger.get().warning(r3, "Unable to schedule work ID " + r7.id);     // Catch: Throwable -> L11 IllegalStateException -> L13
        if (r7.expedited == true) goto L8;
        return;
    L8:
        if (r7.outOfQuotaPolicy != OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST) goto L25;
        r7.expedited = false;     // Catch: Throwable -> L11 IllegalStateException -> L13
        Logger.get().debug(r3, String.format("Scheduling a non-expedited job (work ID %s)", new Object[]{r7.id}));     // Catch: Throwable -> L11 IllegalStateException -> L13
        scheduleInternal(r7, r8);     // Catch: Throwable -> L11 IllegalStateException -> L13
        return;
    L25:
        return;
    }
}
