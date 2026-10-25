package com.google.firebase.messaging;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import androidx.credentials.CredentialManager$$ExternalSyntheticLambda0;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.messaging.WithinAppServiceBinder;
import java.util.concurrent.ExecutorService;

@SuppressLint({"UnwrappedWakefulBroadcastReceiver"})
/* loaded from: classes.dex */
public abstract class EnhancedIntentService extends Service {
    static final long MESSAGE_TIMEOUT_S = 20;
    private static final String TAG = "EnhancedIntentService";
    private Binder binder;
    final ExecutorService executor;
    private int lastStartId;
    private final Object lock;
    private int runningTasks;

    /* renamed from: com.google.firebase.messaging.EnhancedIntentService$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass1 implements WithinAppServiceBinder.IntentHandler {
        final /* synthetic */ EnhancedIntentService this$0;

        public AnonymousClass1(EnhancedIntentService r1) {
            this.this$0 = r1;
        }

        @Override // com.google.firebase.messaging.WithinAppServiceBinder.IntentHandler
        public Task<Void> handle(Intent r1) {
            return EnhancedIntentService.access$000(this.this$0, r1);
        }
    }

    /* renamed from: $r8$lambda$9b4qTh6k1amVzUe1kAE6oojA2-8, reason: not valid java name */
    public static /* synthetic */ void m1955$r8$lambda$9b4qTh6k1amVzUe1kAE6oojA28(EnhancedIntentService r1, Intent r2, TaskCompletionSource r3) {
        r1.getClass();
        r1.handleIntent(r2);     // Catch: Throwable -> L6
        r3.setResult(null);
        return;
    L6:
        th = move-exception;
        r3.setResult(null);
        throw th;
    }

    public static /* synthetic */ void $r8$lambda$WY92l0hxChBULatAfpI9V2iAiEU(EnhancedIntentService r0, Intent r1, Task r2) {
        r0.finishTask(r1);
    }

    public EnhancedIntentService() {
        this.executor = FcmExecutors.newIntentHandleExecutor();
        this.lock = new Object();
        this.runningTasks = 0;
    }

    public static /* synthetic */ Task access$000(EnhancedIntentService r0, Intent r1) {
        return r0.processIntent(r1);
    }

    private void finishTask(Intent r2) {
        if (r2 == null) goto L4;
        WakeLockHolder.completeWakefulIntent(r2);
    L4:
        Object r22 = this.lock;
        monitor-enter(r22);
        int r0 = this.runningTasks - 1;
        this.runningTasks = r0;     // Catch: Throwable -> L9
        if (r0 != 0) goto L11;
        stopSelfResultHook(this.lastStartId);     // Catch: Throwable -> L9
    L11:
        monitor-exit(r22);     // Catch: Throwable -> L9
        return;
    L9:
        th = move-exception;
        throw th;
    }

    private Task<Void> processIntent(Intent r4) {
        if (handleIntentOnMainThread(r4) == true) goto L5;
        TaskCompletionSource r0 = new TaskCompletionSource();
        this.executor.execute(new EnhancedIntentService$$ExternalSyntheticLambda1(this, r4, r0));
        return r0.getTask();
    L5:
        return Tasks.forResult(null);
    }

    public abstract Intent getStartCommandIntent(Intent r1);

    public abstract void handleIntent(Intent r1);

    public boolean handleIntentOnMainThread(Intent r1) {
        return false;
    }

    @Override // android.app.Service
    public final synchronized IBinder onBind(Intent r2) {
        monitor-enter(this);
    L6:
        th = move-exception;
        throw th;
    L4:
        if (Log.isLoggable(TAG, 3) == false) goto L9;
        Log.d(TAG, "Service received bind request");     // Catch: Throwable -> L6
    L9:
        if (this.binder != null) goto L11;
        this.binder = new WithinAppServiceBinder(new AnonymousClass1(this));     // Catch: Throwable -> L6
    L11:
        Binder r22 = this.binder;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r22;
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.executor.shutdown();
        super.onDestroy();
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent r2, int r3, int r4) {
        Object r32 = this.lock;
        monitor-enter(r32);
        this.lastStartId = r4;     // Catch: Throwable -> L16
        this.runningTasks++;
        monitor-exit(r32);     // Catch: Throwable -> L16
        Intent r33 = getStartCommandIntent(r2);
        if (r33 != null) goto L10;
        finishTask(r2);
        return 2;
    L10:
        Task<Void> r34 = processIntent(r33);
        if (r34.isComplete() == false) goto L14;
        finishTask(r2);
        return 2;
    L14:
        r34.addOnCompleteListener(new CredentialManager$$ExternalSyntheticLambda0(), new EnhancedIntentService$$ExternalSyntheticLambda0(this, r2));
        return 3;
    L16:
        th = move-exception;
        throw th;
    }

    public boolean stopSelfResultHook(int r1) {
        return stopSelfResult(r1);
    }
}
