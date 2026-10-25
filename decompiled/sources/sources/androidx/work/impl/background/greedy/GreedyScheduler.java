package androidx.work.impl.background.greedy;

import android.content.Context;
import android.text.TextUtils;
import androidx.work.Configuration;
import androidx.work.Constraints;
import androidx.work.Logger;
import androidx.work.RunnableScheduler;
import androidx.work.WorkInfo$State;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.Processor;
import androidx.work.impl.Scheduler;
import androidx.work.impl.StartStopToken;
import androidx.work.impl.StartStopTokens;
import androidx.work.impl.WorkLauncher;
import androidx.work.impl.constraints.ConstraintsState;
import androidx.work.impl.constraints.OnConstraintsStateChangedListener;
import androidx.work.impl.constraints.WorkConstraintsTracker;
import androidx.work.impl.constraints.WorkConstraintsTrackerKt;
import androidx.work.impl.constraints.trackers.Trackers;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecKt;
import androidx.work.impl.utils.ProcessUtils;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import kotlinx.coroutines.Job;

/* loaded from: classes.dex */
public class GreedyScheduler implements Scheduler, OnConstraintsStateChangedListener, ExecutionListener {
    private static final String TAG = null;
    private final Configuration mConfiguration;
    private final Map<WorkGenerationalId, Job> mConstrainedWorkSpecs;
    private final WorkConstraintsTracker mConstraintsTracker;
    private final Context mContext;
    private DelayedWorkTracker mDelayedWorkTracker;
    private final Map<WorkGenerationalId, AttemptData> mFirstRunAttempts;
    Boolean mInDefaultProcess;
    private final Object mLock;
    private final Processor mProcessor;
    private boolean mRegisteredExecutionListener;
    private final StartStopTokens mStartStopTokens;
    private final TaskExecutor mTaskExecutor;
    private final TimeLimiter mTimeLimiter;
    private final WorkLauncher mWorkLauncher;

    /* renamed from: androidx.work.impl.background.greedy.GreedyScheduler$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static class AttemptData {
        final int mRunAttemptCount;
        final long mTimeStamp;

        private AttemptData(int r1, long r2) {
            this.mRunAttemptCount = r1;
            this.mTimeStamp = r2;
        }

        public /* synthetic */ AttemptData(int r1, long r2, AnonymousClass1 r4) {
            this(r1, r2);
        }
    }

    static {
        TAG = Logger.tagWithPrefix("GreedyScheduler");
    }

    public GreedyScheduler(Context r3, Configuration r4, Trackers r5, Processor r6, WorkLauncher r7, TaskExecutor r8) {
        this.mConstrainedWorkSpecs = new HashMap();
        this.mLock = new Object();
        this.mStartStopTokens = StartStopTokens.create();
        this.mFirstRunAttempts = new HashMap();
        this.mContext = r3;
        RunnableScheduler r32 = r4.getRunnableScheduler();
        this.mDelayedWorkTracker = new DelayedWorkTracker(this, r32, r4.getClock());
        this.mTimeLimiter = new TimeLimiter(r32, r7);
        this.mTaskExecutor = r8;
        this.mConstraintsTracker = new WorkConstraintsTracker(r5);
        this.mConfiguration = r4;
        this.mProcessor = r6;
        this.mWorkLauncher = r7;
    }

    private void checkDefaultProcess() {
        this.mInDefaultProcess = Boolean.valueOf(ProcessUtils.isDefaultProcess(this.mContext, this.mConfiguration));
    }

    private void registerExecutionListenerIfNeeded() {
        if (this.mRegisteredExecutionListener == true) goto L6;
        this.mProcessor.addExecutionListener(this);
        this.mRegisteredExecutionListener = true;
        return;
    }

    private void removeConstraintTrackingFor(WorkGenerationalId r5) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        Job r4 = this.mConstrainedWorkSpecs.remove(r5);     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        if (r4 == null) goto L14;
        Logger.get().debug(TAG, "Stopping tracking for " + r5);
        r4.cancel(null);
        return;
    L14:
        return;
    L9:
        th = move-exception;
        throw th;
    }

    private long throttleIfNeeded(WorkSpec r8) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        WorkGenerationalId r1 = WorkSpecKt.generationalId(r8);     // Catch: Throwable -> L7
        AttemptData r2 = this.mFirstRunAttempts.get(r1);     // Catch: Throwable -> L7
        if (r2 != null) goto L9;
        r2 = new AttemptData(r8.runAttemptCount, this.mConfiguration.getClock().currentTimeMillis(), null);     // Catch: Throwable -> L7
        this.mFirstRunAttempts.put(r1, r2);     // Catch: Throwable -> L7
    L9:
        long r3 = r2.mTimeStamp + (Math.max((r8.runAttemptCount - r2.mRunAttemptCount) - 5, 0) * NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r3;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.work.impl.Scheduler
    public void cancel(String r5) {
        if (this.mInDefaultProcess != null) goto L6;
        checkDefaultProcess();
    L6:
        if (this.mInDefaultProcess.booleanValue() == true) goto L9;
        Logger.get().info(TAG, "Ignoring schedule request in non-main process");
        return;
    L9:
        registerExecutionListenerIfNeeded();
        Logger.get().debug(TAG, "Cancelling work ID " + r5);
        DelayedWorkTracker r0 = this.mDelayedWorkTracker;
        if (r0 == null) goto L12;
        r0.unschedule(r5);
    L12:
        Iterator<StartStopToken> r52 = this.mStartStopTokens.remove(r5).iterator();
    L14:
        if (r52.hasNext() == false) goto L16;
        StartStopToken r02 = r52.next();
        this.mTimeLimiter.cancel(r02);
        this.mWorkLauncher.stopWork(r02);
        goto L14
    }

    @Override // androidx.work.impl.Scheduler
    public boolean hasLimitedSchedulingSlots() {
        return false;
    }

    @Override // androidx.work.impl.constraints.OnConstraintsStateChangedListener
    public void onConstraintsStateChanged(WorkSpec r5, ConstraintsState r6) {
        WorkGenerationalId r52 = WorkSpecKt.generationalId(r5);
        if ((r6 instanceof ConstraintsState.ConstraintsMet) == true) goto L5;
        Logger.get().debug(TAG, "Constraints not met: Cancelling work ID " + r52);
        StartStopToken r53 = this.mStartStopTokens.remove(r52);
        if (r53 == null) goto L13;
        this.mTimeLimiter.cancel(r53);
        this.mWorkLauncher.stopWorkWithReason(r53, ((ConstraintsState.ConstraintsNotMet) r6).getReason());
        return;
    L13:
        return;
    L5:
        if (this.mStartStopTokens.contains(r52) == true) goto L12;
        Logger.get().debug(TAG, "Constraints met: Scheduling work ID " + r52);
        StartStopToken r54 = this.mStartStopTokens.tokenFor(r52);
        this.mTimeLimiter.track(r54);
        this.mWorkLauncher.startWork(r54);
        return;
    }

    @Override // androidx.work.impl.ExecutionListener
    public void onExecuted(WorkGenerationalId r3, boolean r4) {
        StartStopToken r0 = this.mStartStopTokens.remove(r3);
        if (r0 == null) goto L5;
        this.mTimeLimiter.cancel(r0);
    L5:
        removeConstraintTrackingFor(r3);
        if (r4 == true) goto L15;
        Object r42 = this.mLock;
        monitor-enter(r42);
        this.mFirstRunAttempts.remove(r3);     // Catch: Throwable -> L12
        monitor-exit(r42);     // Catch: Throwable -> L12
        return;
    L12:
        th = move-exception;
        throw th;
    }

    @Override // androidx.work.impl.Scheduler
    public void schedule(WorkSpec... r12) {
        if (this.mInDefaultProcess != null) goto L6;
        checkDefaultProcess();
    L6:
        if (this.mInDefaultProcess.booleanValue() == true) goto L9;
        Logger.get().info(TAG, "Ignoring schedule request in a secondary process");
        return;
    L9:
        registerExecutionListenerIfNeeded();
        HashSet r0 = new HashSet();
        HashSet r1 = new HashSet();
        int r2 = r12.length;
        int r3 = 0;
    L10:
        if (r3 >= r2) goto L34;
        WorkSpec r4 = r12[r3];
        if (this.mStartStopTokens.contains(WorkSpecKt.generationalId(r4)) == true) goto L33;
        long r5 = Math.max(r4.calculateNextRunTime(), throttleIfNeeded(r4));
        long r7 = this.mConfiguration.getClock().currentTimeMillis();
        if (r4.state != WorkInfo$State.ENQUEUED) goto L33;
        if (r7 >= r5) goto L22;
        DelayedWorkTracker r72 = this.mDelayedWorkTracker;
        if (r72 == null) goto L33;
        r72.schedule(r4, r5);
        goto L33
    L22:
        if (r4.hasConstraints() == false) goto L31;
        Constraints r52 = r4.constraints;
        if (r52.requiresDeviceIdle() == false) goto L27;
        Logger.get().debug(TAG, "Ignoring " + r4 + ". Requires device idle.");
        goto L33
    L27:
        if (r52.hasContentUriTriggers() == false) goto L29;
        Logger.get().debug(TAG, "Ignoring " + r4 + ". Requires ContentUri triggers.");
        goto L33
    L29:
        r0.add(r4);
        r1.add(r4.id);
        goto L33
    L31:
        if (this.mStartStopTokens.contains(WorkSpecKt.generationalId(r4)) == true) goto L33;
        Logger.get().debug(TAG, "Starting work for " + r4.id);
        StartStopToken r42 = this.mStartStopTokens.tokenFor(r4);
        this.mTimeLimiter.track(r42);
        this.mWorkLauncher.startWork(r42);
    L33:
        r3 = r3 + 1;
        goto L10
    L34:
        Object r122 = this.mLock;
        monitor-enter(r122);
    L44:
        th = move-exception;
        throw th;
    L37:
        if (r0.isEmpty() == true) goto L46;
        String r13 = TextUtils.join(",", r1);     // Catch: Throwable -> L44
        Logger.get().debug(TAG, "Starting tracking for " + r13);     // Catch: Throwable -> L44
        Iterator r02 = r0.iterator();     // Catch: Throwable -> L44
    L40:
        if (r02.hasNext() == false) goto L46;
        WorkSpec r14 = (WorkSpec) r02.next();     // Catch: Throwable -> L44
        WorkGenerationalId r22 = WorkSpecKt.generationalId(r14);     // Catch: Throwable -> L44
        if (this.mConstrainedWorkSpecs.containsKey(r22) == true) goto L40;
        this.mConstrainedWorkSpecs.put(r22, WorkConstraintsTrackerKt.listen(this.mConstraintsTracker, r14, this.mTaskExecutor.getTaskCoroutineDispatcher(), this));     // Catch: Throwable -> L44
    L46:
        monitor-exit(r122);     // Catch: Throwable -> L44
    }
}
