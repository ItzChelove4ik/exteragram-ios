package androidx.work.impl.utils;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.ApplicationExitInfo;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteAccessPermException;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteDiskIOException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.database.sqlite.SQLiteTableLockedException;
import android.os.Build;
import android.text.TextUtils;
import androidx.core.os.UserManagerCompat;
import androidx.core.util.Consumer;
import androidx.work.Configuration;
import androidx.work.Logger;
import androidx.work.WorkInfo$State;
import androidx.work.impl.Schedulers;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkDatabasePathHelper;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.background.systemjob.SystemJobScheduler;
import androidx.work.impl.model.WorkProgressDao;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class ForceStopRunnable implements Runnable {
    private static final String TAG = null;
    private static final long TEN_YEARS = 0;
    private final Context mContext;
    private final PreferenceUtils mPreferenceUtils;
    private int mRetryCount;
    private final WorkManagerImpl mWorkManager;

    public static class BroadcastReceiver extends android.content.BroadcastReceiver {
        private static final String TAG = null;

        static {
            TAG = Logger.tagWithPrefix("ForceStopRunnable$Rcvr");
        }

        public BroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context r2, Intent r3) {
            if (r3 != null) goto L4;
            return;
        L4:
            if ("ACTION_FORCE_STOP_RESCHEDULE".equals(r3.getAction()) == false) goto L8;
            Logger.get().verbose(TAG, "Rescheduling alarm that keeps track of force-stops.");
            ForceStopRunnable.setAlarm(r2);
            return;
        }
    }

    static {
        TAG = Logger.tagWithPrefix("ForceStopRunnable");
        TEN_YEARS = 315360000000L;
    }

    public ForceStopRunnable(Context r1, WorkManagerImpl r2) {
        this.mContext = r1.getApplicationContext();
        this.mWorkManager = r2;
        this.mPreferenceUtils = r2.getPreferenceUtils();
        this.mRetryCount = 0;
    }

    public static Intent getIntent(Context r3) {
        Intent r0 = new Intent();
        r0.setComponent(new ComponentName(r3, BroadcastReceiver.class));
        r0.setAction("ACTION_FORCE_STOP_RESCHEDULE");
        return r0;
    }

    private static PendingIntent getPendingIntent(Context r2, int r3) {
        return PendingIntent.getBroadcast(r2, -1, getIntent(r2), r3);
    }

    public static void setAlarm(Context r5) {
        AlarmManager r0 = (AlarmManager) r5.getSystemService("alarm");
        if (Build.VERSION.SDK_INT < 31) goto L5;
        int r1 = 167772160;
    L6:
        PendingIntent r52 = getPendingIntent(r5, r1);
        long r12 = System.currentTimeMillis() + TEN_YEARS;
        if (r0 == null) goto L10;
        r0.setExact(0, r12, r52);
        return;
    L10:
        return;
    L5:
        r1 = 134217728;
        goto L6
    }

    public boolean cleanUp() {
        boolean r0 = SystemJobScheduler.reconcileJobs(this.mContext, this.mWorkManager.getWorkDatabase());
        WorkDatabase r10 = this.mWorkManager.getWorkDatabase();
        WorkSpecDao r1 = r10.workSpecDao();
        WorkProgressDao r2 = r10.workProgressDao();
        r10.beginTransaction();
        List<WorkSpec> r3 = r1.getRunningWork();     // Catch: Throwable -> L8
        if (r3 != null) goto L6;
    L10:
        boolean r6 = false;
    L11:
        if (r6 == false) goto L16;
        Iterator<WorkSpec> r32 = r3.iterator();     // Catch: Throwable -> L8
    L14:
        if (r32.hasNext() == false) goto L16;
        WorkSpec r7 = r32.next();     // Catch: Throwable -> L8
        r1.setState(WorkInfo$State.ENQUEUED, r7.id);     // Catch: Throwable -> L8
        r1.setStopReason(r7.id, -512);     // Catch: Throwable -> L8
        r1.markWorkSpecScheduled(r7.id, -1);     // Catch: Throwable -> L8
    L16:
        r2.deleteAll();     // Catch: Throwable -> L8
        r10.setTransactionSuccessful();     // Catch: Throwable -> L8
        r10.endTransaction();
        if (r6 == true) goto L22;
        if (r0 == true) goto L22;
        return false;
    L22:
        return true;
    L6:
        if (r3.isEmpty() == true) goto L10;
        r6 = true;
    L8:
        th = move-exception;
        r10.endTransaction();
        throw th;
    }

    public void forceStopRunnable() {
        boolean r0 = cleanUp();
        if (shouldRescheduleWorkers() == false) goto L7;
        Logger.get().debug(TAG, "Rescheduling Workers.");
        this.mWorkManager.rescheduleEligibleWork();
        this.mWorkManager.getPreferenceUtils().setNeedsReschedule(false);
        return;
    L7:
        if (isForceStopped() == false) goto L10;
        Logger.get().debug(TAG, "Application was force-stopped, rescheduling.");
        this.mWorkManager.rescheduleEligibleWork();
        this.mPreferenceUtils.setLastForceStopEventMillis(this.mWorkManager.getConfiguration().getClock().currentTimeMillis());
        return;
    L10:
        if (r0 == false) goto L13;
        Logger.get().debug(TAG, "Found unfinished work, scheduling it.");
        Schedulers.schedule(this.mWorkManager.getConfiguration(), this.mWorkManager.getWorkDatabase(), this.mWorkManager.getSchedulers());
        return;
    }

    public boolean isForceStopped() {
        int r1 = Build.VERSION.SDK_INT;     // Catch: IllegalArgumentException -> L11 SecurityException -> L13
        if (r1 < 31) goto L6;
        int r2 = 570425344;
    L7:
        PendingIntent r22 = getPendingIntent(this.mContext, r2);     // Catch: IllegalArgumentException -> L11 SecurityException -> L13
        if (r1 < 30) goto L28;
        if (r22 == null) goto L15;
        r22.cancel();     // Catch: IllegalArgumentException -> L11 SecurityException -> L13
    L15:
        List<ApplicationExitInfo> r12 = ((ActivityManager) this.mContext.getSystemService("activity")).getHistoricalProcessExitReasons(null, 0, 0);     // Catch: IllegalArgumentException -> L11 SecurityException -> L13
        if (r12 != null) goto L18;
    L31:
        return false;
    L18:
        if (r12.isEmpty() == true) goto L31;
        long r23 = this.mPreferenceUtils.getLastForceStopEventMillis();     // Catch: IllegalArgumentException -> L11 SecurityException -> L13
        int r8 = 0;
    L21:
        if (r8 >= r12.size()) goto L31;
        ApplicationExitInfo r5 = ForceStopRunnable$$ExternalSyntheticApiModelOutline0.m(r12.get(r8));     // Catch: IllegalArgumentException -> L11 SecurityException -> L13
        if (r5.getReason() != 10) goto L27;
        if (r5.getTimestamp() < r23) goto L27;
        return true;
    L27:
        r8 = r8 + 1;     // Catch: IllegalArgumentException -> L11 SecurityException -> L13
        goto L21
    L28:
        if (r22 != null) goto L31;
        setAlarm(this.mContext);     // Catch: IllegalArgumentException -> L11 SecurityException -> L13
        return true;
    L6:
        r2 = 536870912;
    L11:
        e = e;
    L32:
        Logger.get().warning(TAG, "Ignoring exception", e);
        return true;
    L13:
        e = e;
        goto L32
    }

    public boolean multiProcessChecks() {
        Configuration r0 = this.mWorkManager.getConfiguration();
        if (TextUtils.isEmpty(r0.getDefaultProcessName()) == false) goto L6;
        Logger.get().debug(TAG, "The default process name was not specified.");
        return true;
    L6:
        boolean r4 = ProcessUtils.isDefaultProcess(this.mContext, r0);
        Logger.get().debug(TAG, "Is default app process = " + r4);
        return r4;
    }

    @Override // java.lang.Runnable
    public void run() {
    L10:
        th = move-exception;
        this.mWorkManager.onForceStopRunnableCompleted();
        throw th;
    L3:
        if (multiProcessChecks() == true) goto L46;
    L4:
        this.mWorkManager.onForceStopRunnableCompleted();
        return;
    L46:
        WorkDatabasePathHelper.migrateDatabase(this.mContext);     // Catch: Throwable -> L10 SQLiteException -> L38
        Logger.get().debug(TAG, "Performing cleanup operations.");     // Catch: Throwable -> L10
        forceStopRunnable();     // Catch: Throwable -> L10 SQLiteTableLockedException -> L12 SQLiteFullException -> L14 SQLiteDiskIOException -> L16 Throwable -> L18 SQLiteDatabaseCorruptException -> L20 SQLiteConstraintException -> L22 SQLiteCantOpenDatabaseException -> L24 SQLiteAccessPermException -> L26
    L18:
        e = move-exception;
        int r1 = this.mRetryCount + 1;     // Catch: Throwable -> L10
        this.mRetryCount = r1;     // Catch: Throwable -> L10
        if (r1 >= 3) goto L30;
        Logger.get().debug(TAG, "Retrying after " + (r1 * 300), e);     // Catch: Throwable -> L10
        sleep(((long) this.mRetryCount) * 300);     // Catch: Throwable -> L10
        goto L46
    L30:
        if (UserManagerCompat.isUserUnlocked(this.mContext) == false) goto L32;
        String r12 = "The file system on the device is in a bad state. WorkManager cannot access the app's internal data store.";
    L33:
        Logger r2 = Logger.get();     // Catch: Throwable -> L10
        String r3 = TAG;     // Catch: Throwable -> L10
        r2.error(r3, r12, e);     // Catch: Throwable -> L10
        IllegalStateException r22 = new IllegalStateException(r12, e);     // Catch: Throwable -> L10
        Consumer<Throwable> r0 = this.mWorkManager.getConfiguration().getInitializationExceptionHandler();     // Catch: Throwable -> L10
        if (r0 == null) goto L36;
        Logger.get().debug(r3, "Routing exception to the specified exception handler", r22);     // Catch: Throwable -> L10
        r0.accept(r22);     // Catch: Throwable -> L10
        goto L4
    L36:
        throw r22;     // Catch: Throwable -> L10
    L32:
        r12 = "WorkManager can't be accessed from direct boot, because credential encrypted storage isn't accessible.\nDon't access or initialise WorkManager from directAware components. See https://developer.android.com/training/articles/direct-boot";
        goto L33
    L38:
        e = move-exception;
        Logger.get().error(TAG, "Unexpected SQLite exception during migrations");     // Catch: Throwable -> L10
        IllegalStateException r23 = new IllegalStateException("Unexpected SQLite exception during migrations", e);     // Catch: Throwable -> L10
        Consumer<Throwable> r02 = this.mWorkManager.getConfiguration().getInitializationExceptionHandler();     // Catch: Throwable -> L10
        if (r02 == null) goto L42;
        r02.accept(r23);     // Catch: Throwable -> L10
        goto L4
    L42:
        throw r23;     // Catch: Throwable -> L10
    }

    public boolean shouldRescheduleWorkers() {
        return this.mWorkManager.getPreferenceUtils().getNeedsReschedule();
    }

    public void sleep(long r1) {
        Thread.sleep(r1);     // Catch: InterruptedException -> L4
        return;
    }
}
