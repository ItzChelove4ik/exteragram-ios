package androidx.work.impl.foreground;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import androidx.work.ForegroundInfo;
import androidx.work.Logger;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.constraints.ConstraintsState;
import androidx.work.impl.constraints.OnConstraintsStateChangedListener;
import androidx.work.impl.constraints.WorkConstraintsTracker;
import androidx.work.impl.constraints.WorkConstraintsTrackerKt;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecKt;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import kotlinx.coroutines.Job;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public class SystemForegroundDispatcher implements OnConstraintsStateChangedListener, ExecutionListener {
    static final String TAG = null;
    private Callback mCallback;
    final WorkConstraintsTracker mConstraintsTracker;
    private Context mContext;
    WorkGenerationalId mCurrentForegroundId;
    final Map<WorkGenerationalId, ForegroundInfo> mForegroundInfoById;
    final Object mLock;
    private final TaskExecutor mTaskExecutor;
    final Map<WorkGenerationalId, Job> mTrackedWorkSpecs;
    private WorkManagerImpl mWorkManagerImpl;
    final Map<WorkGenerationalId, WorkSpec> mWorkSpecById;

    /* renamed from: androidx.work.impl.foreground.SystemForegroundDispatcher$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ SystemForegroundDispatcher this$0;
        final /* synthetic */ String val$workSpecId;

        public AnonymousClass1(SystemForegroundDispatcher r1, String r2) {
            this.this$0 = r1;
            this.val$workSpecId = r2;
        }

        @Override // java.lang.Runnable
        public void run() {
            WorkSpec r0 = SystemForegroundDispatcher.access$000(this.this$0).getProcessor().getRunningWorkSpec(this.val$workSpecId);
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.hasConstraints() == false) goto L17;
            Object r1 = this.this$0.mLock;
            monitor-enter(r1);
            this.this$0.mWorkSpecById.put(WorkSpecKt.generationalId(r0), r0);     // Catch: Throwable -> L11
            SystemForegroundDispatcher r2 = this.this$0;     // Catch: Throwable -> L11
            Job r22 = WorkConstraintsTrackerKt.listen(r2.mConstraintsTracker, r0, SystemForegroundDispatcher.access$100(r2).getTaskCoroutineDispatcher(), this.this$0);     // Catch: Throwable -> L11
            this.this$0.mTrackedWorkSpecs.put(WorkSpecKt.generationalId(r0), r22);     // Catch: Throwable -> L11
            monitor-exit(r1);     // Catch: Throwable -> L11
            return;
        L11:
            th = move-exception;
            throw th;
        }
    }

    public interface Callback {
        void cancelNotification(int r1);

        void notify(int r1, Notification r2);

        void startForeground(int r1, int r2, Notification r3);

        void stop(int r1);
    }

    static {
        TAG = Logger.tagWithPrefix("SystemFgDispatcher");
    }

    public SystemForegroundDispatcher(Context r2) {
        this.mContext = r2;
        this.mLock = new Object();
        WorkManagerImpl r22 = WorkManagerImpl.getInstance(r2);
        this.mWorkManagerImpl = r22;
        this.mTaskExecutor = r22.getWorkTaskExecutor();
        this.mCurrentForegroundId = null;
        this.mForegroundInfoById = new LinkedHashMap();
        this.mTrackedWorkSpecs = new HashMap();
        this.mWorkSpecById = new HashMap();
        this.mConstraintsTracker = new WorkConstraintsTracker(this.mWorkManagerImpl.getTrackers());
        this.mWorkManagerImpl.getProcessor().addExecutionListener(this);
    }

    public static /* synthetic */ WorkManagerImpl access$000(SystemForegroundDispatcher r0) {
        return r0.mWorkManagerImpl;
    }

    public static /* synthetic */ TaskExecutor access$100(SystemForegroundDispatcher r0) {
        return r0.mTaskExecutor;
    }

    public static Intent createNotifyIntent(Context r2, WorkGenerationalId r3, ForegroundInfo r4) {
        Intent r0 = new Intent(r2, SystemForegroundService.class);
        r0.setAction("ACTION_NOTIFY");
        r0.putExtra("KEY_NOTIFICATION_ID", r4.getNotificationId());
        r0.putExtra("KEY_FOREGROUND_SERVICE_TYPE", r4.getForegroundServiceType());
        r0.putExtra("KEY_NOTIFICATION", r4.getNotification());
        r0.putExtra("KEY_WORKSPEC_ID", r3.getWorkSpecId());
        r0.putExtra("KEY_GENERATION", r3.getGeneration());
        return r0;
    }

    public static Intent createStartForegroundIntent(Context r2, WorkGenerationalId r3, ForegroundInfo r4) {
        Intent r0 = new Intent(r2, SystemForegroundService.class);
        r0.setAction("ACTION_START_FOREGROUND");
        r0.putExtra("KEY_WORKSPEC_ID", r3.getWorkSpecId());
        r0.putExtra("KEY_GENERATION", r3.getGeneration());
        r0.putExtra("KEY_NOTIFICATION_ID", r4.getNotificationId());
        r0.putExtra("KEY_FOREGROUND_SERVICE_TYPE", r4.getForegroundServiceType());
        r0.putExtra("KEY_NOTIFICATION", r4.getNotification());
        return r0;
    }

    public static Intent createStopForegroundIntent(Context r2) {
        Intent r0 = new Intent(r2, SystemForegroundService.class);
        r0.setAction("ACTION_STOP_FOREGROUND");
        return r0;
    }

    private void handleCancelWork(Intent r5) {
        Logger.get().info(TAG, "Stopping foreground work for " + r5);
        String r52 = r5.getStringExtra("KEY_WORKSPEC_ID");
        if (r52 != null) goto L5;
        return;
    L5:
        if (TextUtils.isEmpty(r52) == true) goto L9;
        this.mWorkManagerImpl.cancelWorkById(UUID.fromString(r52));
        return;
    }

    private void handleNotify(Intent r10) {
        if (this.mCallback == null) goto L21;
        int r1 = 0;
        int r0 = r10.getIntExtra("KEY_NOTIFICATION_ID", 0);
        int r2 = r10.getIntExtra("KEY_FOREGROUND_SERVICE_TYPE", 0);
        String r3 = r10.getStringExtra("KEY_WORKSPEC_ID");
        WorkGenerationalId r5 = new WorkGenerationalId(r3, r10.getIntExtra("KEY_GENERATION", 0));
        Notification r102 = (Notification) r10.getParcelableExtra("KEY_NOTIFICATION");
        Logger.get().debug(TAG, "Notifying with (id:" + r0 + ", workSpecId: " + r3 + ", notificationType :" + r2 + ")");
        if (r102 == null) goto L19;
        ForegroundInfo r32 = new ForegroundInfo(r0, r102, r2);
        this.mForegroundInfoById.put(r5, r32);
        ForegroundInfo r22 = this.mForegroundInfoById.get(this.mCurrentForegroundId);
        if (r22 != null) goto L9;
        this.mCurrentForegroundId = r5;
    L17:
        this.mCallback.startForeground(r32.getNotificationId(), r32.getForegroundServiceType(), r32.getNotification());
        return;
    L9:
        this.mCallback.notify(r0, r102);
        if (Build.VERSION.SDK_INT < 29) goto L16;
        Iterator<Map.Entry<WorkGenerationalId, ForegroundInfo>> r103 = this.mForegroundInfoById.entrySet().iterator();
    L13:
        if (r103.hasNext() == false) goto L15;
        r1 = r1 | r103.next().getValue().getForegroundServiceType();
        goto L13
    L15:
        r32 = new ForegroundInfo(r22.getNotificationId(), r22.getNotification(), r1);
        goto L17
    L16:
        r32 = r22;
        goto L17
    L19:
        f$$ExternalSyntheticBUOutline1.m("Notification passed in the intent was null.");
        return;
    L21:
        Segment$$ExternalSyntheticBUOutline1.m("handleNotify was called on the destroyed dispatcher");
    }

    private void handleStartForeground(Intent r5) {
        Logger.get().info(TAG, "Started foreground service " + r5);
        this.mTaskExecutor.executeOnTaskThread(new AnonymousClass1(this, r5.getStringExtra("KEY_WORKSPEC_ID")));
    }

    public void handleStop(Intent r3, int r4) {
        Logger.get().info(TAG, "Stopping foreground service");
        Callback r2 = this.mCallback;
        if (r2 == null) goto L6;
        r2.stop(r4);
        return;
    }

    @Override // androidx.work.impl.constraints.OnConstraintsStateChangedListener
    public void onConstraintsStateChanged(WorkSpec r6, ConstraintsState r7) {
        if ((r7 instanceof ConstraintsState.ConstraintsNotMet) == false) goto L6;
        String r0 = r6.id;
        Logger.get().debug(TAG, "Constraints unmet for WorkSpec " + r0);
        this.mWorkManagerImpl.stopForegroundWork(WorkSpecKt.generationalId(r6), ((ConstraintsState.ConstraintsNotMet) r7).getReason());
        return;
    }

    public void onDestroy() {
        this.mCallback = null;
        Object r1 = this.mLock;
        monitor-enter(r1);
        Iterator<Job> r2 = this.mTrackedWorkSpecs.values().iterator();     // Catch: Throwable -> L8
    L6:
        if (r2.hasNext() == false) goto L10;
        r2.next().cancel(null);     // Catch: Throwable -> L8
        goto L6
    L10:
        monitor-exit(r1);     // Catch: Throwable -> L8
        this.mWorkManagerImpl.getProcessor().removeExecutionListener(this);
        return;
    L8:
        th = move-exception;
        throw th;
    }

    @Override // androidx.work.impl.ExecutionListener
    public void onExecuted(WorkGenerationalId r6, boolean r7) {
        Object r72 = this.mLock;
        monitor-enter(r72);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (this.mWorkSpecById.remove(r6) == null) goto L9;
        Job r0 = this.mTrackedWorkSpecs.remove(r6);     // Catch: Throwable -> L7
    L10:
        if (r0 == null) goto L12;
        r0.cancel(null);     // Catch: Throwable -> L7
    L12:
        monitor-exit(r72);     // Catch: Throwable -> L7
        ForegroundInfo r73 = this.mForegroundInfoById.remove(r6);
        if (r6.equals(this.mCurrentForegroundId) == true) goto L16;
    L25:
        Callback r5 = this.mCallback;
        if (r73 == null) goto L35;
        if (r5 == null) goto L36;
        Logger.get().debug(TAG, "Removing Notification (id: " + r73.getNotificationId() + ", workSpecId: " + r6 + ", notificationType: " + r73.getForegroundServiceType());
        r5.cancelNotification(r73.getNotificationId());
        return;
    L36:
        return;
    L35:
        return;
    L16:
        if (this.mForegroundInfoById.size() <= 0) goto L24;
        Iterator<Map.Entry<WorkGenerationalId, ForegroundInfo>> r02 = this.mForegroundInfoById.entrySet().iterator();
        Map.Entry<WorkGenerationalId, ForegroundInfo> r1 = r02.next();
    L18:
        Map.Entry<WorkGenerationalId, ForegroundInfo> r12 = r1;
        if (r02.hasNext() == false) goto L21;
        r1 = r02.next();
        goto L18
    L21:
        this.mCurrentForegroundId = r12.getKey();
        if (this.mCallback == null) goto L25;
        ForegroundInfo r03 = r12.getValue();
        this.mCallback.startForeground(r03.getNotificationId(), r03.getForegroundServiceType(), r03.getNotification());
        this.mCallback.cancelNotification(r03.getNotificationId());
        goto L25
    L24:
        this.mCurrentForegroundId = null;
        goto L25
    L9:
        r0 = null;
        goto L10
    }

    public void onStartCommand(Intent r3, int r4) {
        String r0 = r3.getAction();
        if ("ACTION_START_FOREGROUND".equals(r0) == false) goto L7;
        handleStartForeground(r3);
        handleNotify(r3);
        return;
    L7:
        if ("ACTION_NOTIFY".equals(r0) == false) goto L11;
        handleNotify(r3);
        return;
    L11:
        if ("ACTION_CANCEL_WORK".equals(r0) == false) goto L15;
        handleCancelWork(r3);
        return;
    L15:
        if ("ACTION_STOP_FOREGROUND".equals(r0) == false) goto L18;
        handleStop(r3, r4);
        return;
    }

    public void onTimeout(int r5, int r6) {
        Logger.get().info(TAG, "Foreground service timed out, FGS type: " + r6);
        Iterator<Map.Entry<WorkGenerationalId, ForegroundInfo>> r0 = this.mForegroundInfoById.entrySet().iterator();
    L4:
        if (r0.hasNext() == false) goto L8;
        Map.Entry<WorkGenerationalId, ForegroundInfo> r1 = r0.next();
        if (r1.getValue().getForegroundServiceType() != r6) goto L4;
        this.mWorkManagerImpl.stopForegroundWork(r1.getKey(), -128);
        goto L4
    L8:
        Callback r4 = this.mCallback;
        if (r4 == null) goto L17;
        r4.stop(r5);
        return;
    }

    public void setCallback(Callback r2) {
        if (this.mCallback == null) goto L6;
        Logger.get().error(TAG, "A callback already exists.");
        return;
    L6:
        this.mCallback = r2;
    }
}
