package androidx.work.impl.background.greedy;

import androidx.work.Clock;
import androidx.work.Logger;
import androidx.work.RunnableScheduler;
import androidx.work.impl.Scheduler;
import androidx.work.impl.model.WorkSpec;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class DelayedWorkTracker {
    static final String TAG = null;
    private final Clock mClock;
    final Scheduler mImmediateScheduler;
    private final RunnableScheduler mRunnableScheduler;
    private final Map<String, Runnable> mRunnables;

    /* renamed from: androidx.work.impl.background.greedy.DelayedWorkTracker$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ DelayedWorkTracker this$0;
        final /* synthetic */ WorkSpec val$workSpec;

        public AnonymousClass1(DelayedWorkTracker r1, WorkSpec r2) {
            this.this$0 = r1;
            this.val$workSpec = r2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Logger.get().debug(DelayedWorkTracker.TAG, "Scheduling work " + this.val$workSpec.id);
            this.this$0.mImmediateScheduler.schedule(new WorkSpec[]{this.val$workSpec});
        }
    }

    static {
        TAG = Logger.tagWithPrefix("DelayedWorkTracker");
    }

    public DelayedWorkTracker(Scheduler r1, RunnableScheduler r2, Clock r3) {
        this.mImmediateScheduler = r1;
        this.mRunnableScheduler = r2;
        this.mClock = r3;
        this.mRunnables = new HashMap();
    }

    public void schedule(WorkSpec r4, long r5) {
        Runnable r0 = this.mRunnables.remove(r4.id);
        if (r0 == null) goto L5;
        this.mRunnableScheduler.cancel(r0);
    L5:
        AnonymousClass1 r02 = new AnonymousClass1(this, r4);
        this.mRunnables.put(r4.id, r02);
        long r52 = r5 - this.mClock.currentTimeMillis();
        this.mRunnableScheduler.scheduleWithDelay(r52, r02);
    }

    public void unschedule(String r2) {
        Runnable r22 = this.mRunnables.remove(r2);
        if (r22 == null) goto L6;
        this.mRunnableScheduler.cancel(r22);
        return;
    }
}
