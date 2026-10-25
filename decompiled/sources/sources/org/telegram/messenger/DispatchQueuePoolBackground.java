package org.telegram.messenger;

import android.os.SystemClock;
import android.util.SparseIntArray;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.util.ArrayList;
import org.telegram.ui.Components.Reactions.HwEmojis;

/* loaded from: classes.dex */
public class DispatchQueuePoolBackground {
    public static final String THREAD_PREFIX = "DispatchQueuePoolThreadSafety_";
    private static DispatchQueuePoolBackground backgroundQueue;
    private static final Runnable finishCollectUpdateRunnable = null;
    private static final ArrayList<ArrayList<Runnable>> freeCollections = null;
    static ArrayList<Runnable> updateTaskCollection;
    private final ArrayList<DispatchQueue> busyQueues;
    private final SparseIntArray busyQueuesMap;
    private final Runnable cleanupRunnable;
    private boolean cleanupScheduled;
    private int createdCount;
    private final int guid;
    private final int maxCount;
    private final ArrayList<DispatchQueue> queues;
    private int totalTasksCount;

    /* renamed from: org.telegram.messenger.DispatchQueuePoolBackground$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ DispatchQueuePoolBackground this$0;

        public AnonymousClass1(DispatchQueuePoolBackground r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (DispatchQueuePoolBackground.m3982$$Nest$fgetqueues(this.this$0).isEmpty() == true) goto L12;
            long r5 = SystemClock.elapsedRealtime();
            int r0 = 0;
        L6:
            if (r0 >= DispatchQueuePoolBackground.m3982$$Nest$fgetqueues(this.this$0).size()) goto L12;
            DispatchQueue r7 = (DispatchQueue) DispatchQueuePoolBackground.m3982$$Nest$fgetqueues(this.this$0).get(r0);
            if (r7.getLastTaskTime() >= (r5 - NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS)) goto L10;
            r7.recycle();
            DispatchQueuePoolBackground.m3982$$Nest$fgetqueues(this.this$0).remove(r0);
            DispatchQueuePoolBackground r72 = this.this$0;
            DispatchQueuePoolBackground.m3984$$Nest$fputcreatedCount(r72, DispatchQueuePoolBackground.m3981$$Nest$fgetcreatedCount(r72) - 1);
            r0 = r0 - 1;
        L10:
            r0 = r0 + 1;
        L12:
            if (DispatchQueuePoolBackground.m3982$$Nest$fgetqueues(this.this$0).isEmpty() == true) goto L14;
        L18:
            Utilities.globalQueue.postRunnable(this, NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS);
            DispatchQueuePoolBackground.m3983$$Nest$fputcleanupScheduled(this.this$0, true);
            return;
        L14:
            if (DispatchQueuePoolBackground.m3980$$Nest$fgetbusyQueues(this.this$0).isEmpty() == false) goto L18;
            DispatchQueuePoolBackground.m3983$$Nest$fputcleanupScheduled(this.this$0, false);
        }
    }

    /* renamed from: $r8$lambda$4Y9T0IqFPoUs0-sKxr8HQlHMEVk, reason: not valid java name */
    public static /* synthetic */ void m3977$r8$lambda$4Y9T0IqFPoUs0sKxr8HQlHMEVk(DispatchQueuePoolBackground r0, DispatchQueue r1) {
        r0.lambda$execute$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$PzeFzOlDbRlWJN_Hd37KhUYeDk8(ArrayList r1) {
        freeCollections.add(r1);
    }

    public static /* synthetic */ void $r8$lambda$UkoJN5qmhyyvcV9KB1nije8lTfU() {
        finishCollectUpdateRunnables();
    }

    /* renamed from: $r8$lambda$bzkCRCK-r_loUDDzM3r7kwBC4QA, reason: not valid java name */
    public static /* synthetic */ void m3978$r8$lambda$bzkCRCKr_loUDDzM3r7kwBC4QA(DispatchQueuePoolBackground r0, Runnable r1, DispatchQueue r2) {
        r0.lambda$execute$1(r1, r2);
    }

    /* renamed from: $r8$lambda$o-lCmqjv06XbZi7A_RYb7NkHJQA, reason: not valid java name */
    public static /* synthetic */ void m3979$r8$lambda$olCmqjv06XbZi7A_RYb7NkHJQA(ArrayList r1) {
        backgroundQueue.execute(r1);
        r1.clear();
        AndroidUtilities.runOnUIThread(new DispatchQueuePoolBackground$$ExternalSyntheticLambda3(r1));
    }

    /* renamed from: -$$Nest$fgetbusyQueues, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m3980$$Nest$fgetbusyQueues(DispatchQueuePoolBackground r0) {
        return r0.busyQueues;
    }

    /* renamed from: -$$Nest$fgetcreatedCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m3981$$Nest$fgetcreatedCount(DispatchQueuePoolBackground r0) {
        return r0.createdCount;
    }

    /* renamed from: -$$Nest$fgetqueues, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m3982$$Nest$fgetqueues(DispatchQueuePoolBackground r0) {
        return r0.queues;
    }

    /* renamed from: -$$Nest$fputcleanupScheduled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m3983$$Nest$fputcleanupScheduled(DispatchQueuePoolBackground r0, boolean r1) {
        r0.cleanupScheduled = r1;
    }

    /* renamed from: -$$Nest$fputcreatedCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m3984$$Nest$fputcreatedCount(DispatchQueuePoolBackground r0, int r1) {
        r0.createdCount = r1;
    }

    static {
        freeCollections = new ArrayList();
        finishCollectUpdateRunnable = new DispatchQueuePoolBackground$$ExternalSyntheticLambda4();
    }

    private DispatchQueuePoolBackground(int r3) {
        this.queues = new ArrayList(10);
        this.busyQueuesMap = new SparseIntArray();
        this.busyQueues = new ArrayList(10);
        this.cleanupRunnable = new AnonymousClass1(this);
        this.maxCount = r3;
        this.guid = Utilities.random.nextInt();
    }

    public static void execute(Runnable r1) {
        execute(r1, false);
    }

    public static void execute(Runnable r2, boolean r3) {
        if (Thread.currentThread() == ApplicationLoader.applicationHandler.getLooper().getThread()) goto L9;
        if (BuildVars.DEBUG_VERSION == false) goto L20;
        FileLog.e(new RuntimeException("wrong thread"));
        return;
    L20:
        return;
    L9:
        if (updateTaskCollection != null) goto L16;
        ArrayList<ArrayList<Runnable>> r0 = freeCollections;
        if (r0.isEmpty() == true) goto L13;
        updateTaskCollection = r0.remove(r0.size() - 1);
    L14:
        if (r3 == true) goto L16;
        AndroidUtilities.runOnUIThread(finishCollectUpdateRunnable);
        goto L16
    L13:
        updateTaskCollection = new ArrayList(100);
    L16:
        updateTaskCollection.add(r2);
        if (r3 == false) goto L21;
        Runnable r22 = finishCollectUpdateRunnable;
        AndroidUtilities.cancelRunOnUIThread(r22);
        r22.run();
        return;
    }

    private void execute(ArrayList<Runnable> r11) {
        int r1 = 0;
    L4:
        if (r1 >= r11.size()) goto L32;
        Runnable r2 = r11.get(r1);
        if (r2 == null) goto L31;
        if (this.busyQueues.isEmpty() == true) goto L18;
        if ((this.totalTasksCount / 2) > this.busyQueues.size()) goto L13;
    L16:
        DispatchQueue r3 = this.busyQueues.remove(0);
    L22:
        if (this.cleanupScheduled == true) goto L24;
        Utilities.globalQueue.postRunnable(this.cleanupRunnable, NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS);
        this.cleanupScheduled = true;
    L24:
        this.totalTasksCount++;
        this.busyQueues.add(r3);
        this.busyQueuesMap.put(r3.index, this.busyQueuesMap.get(r3.index, 0) + 1);
        if (HwEmojis.isHwEnabled() == false) goto L28;
        r3.setPriority(1);
    L30:
        r3.postRunnable(new DispatchQueuePoolBackground$$ExternalSyntheticLambda1(this, r2, r3));
        goto L31
    L28:
        if (r3.getPriority() == 10) goto L30;
        r3.setPriority(10);
        goto L30
    L13:
        if (this.queues.isEmpty() == false) goto L18;
        if (this.createdCount >= this.maxCount) goto L16;
    L18:
        if (this.queues.isEmpty() == false) goto L20;
        r3 = new DispatchQueue(THREAD_PREFIX + this.guid + "_" + Utilities.random.nextInt());
        r3.setPriority(10);
        this.createdCount = this.createdCount + 1;
        goto L22
    L20:
        r3 = this.queues.remove(0);
    L31:
        r1 = r1 + 1;
        goto L4
    }

    private static void finishCollectUpdateRunnables() {
        ArrayList<Runnable> r0 = updateTaskCollection;
        if (r0 != null) goto L5;
    L12:
        updateTaskCollection = null;
        return;
    L5:
        if (r0.isEmpty() == true) goto L12;
        ArrayList<Runnable> r02 = updateTaskCollection;
        updateTaskCollection = null;
        if (backgroundQueue != null) goto L10;
        backgroundQueue = new DispatchQueuePoolBackground(Math.max(1, Runtime.getRuntime().availableProcessors()));
    L10:
        Utilities.globalQueue.postRunnable(new DispatchQueuePoolBackground$$ExternalSyntheticLambda2(r02));
    }

    private /* synthetic */ void lambda$execute$0(DispatchQueue r3) {
        this.totalTasksCount--;
        int r0 = this.busyQueuesMap.get(r3.index) - 1;
        SparseIntArray r1 = this.busyQueuesMap;
        if (r0 != 0) goto L6;
        r1.delete(r3.index);
        this.busyQueues.remove(r3);
        this.queues.add(r3);
        return;
    L6:
        r1.put(r3.index, r0);
    }

    private /* synthetic */ void lambda$execute$1(Runnable r2, DispatchQueue r3) {
        r2.run();
        Utilities.globalQueue.postRunnable(new DispatchQueuePoolBackground$$ExternalSyntheticLambda0(this, r3));
    }
}
