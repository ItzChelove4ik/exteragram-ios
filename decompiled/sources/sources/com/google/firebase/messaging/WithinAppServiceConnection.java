package com.google.firebase.messaging;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
class WithinAppServiceConnection implements ServiceConnection {
    private WithinAppServiceBinder binder;
    private boolean connectionInProgress;
    private final Intent connectionIntent;
    private final Context context;
    private final Queue<BindRequest> intentQueue;
    private final ScheduledExecutorService scheduledExecutorService;

    public static class BindRequest {
        final Intent intent;
        private final TaskCompletionSource<Void> taskCompletionSource;

        /* renamed from: $r8$lambda$UBVoOrSRgSIh9J-gCOmYHbkdm3Q, reason: not valid java name */
        public static /* synthetic */ void m1967$r8$lambda$UBVoOrSRgSIh9JgCOmYHbkdm3Q(ScheduledFuture r0, Task r1) {
            r0.cancel(false);
        }

        /* renamed from: $r8$lambda$hKs5bTLff-ofgXyu-UxvS59KUCk, reason: not valid java name */
        public static /* synthetic */ void m1968$r8$lambda$hKs5bTLffofgXyuUxvS59KUCk(BindRequest r2) {
            Log.w("FirebaseMessaging", "Service took too long to process intent: " + r2.intent.getAction() + " finishing.");
            r2.finish();
        }

        public BindRequest(Intent r2) {
            this.taskCompletionSource = new TaskCompletionSource();
            this.intent = r2;
        }

        public void arrangeTimeout(ScheduledExecutorService r5) {
            ScheduledFuture<?> r0 = r5.schedule(new WithinAppServiceConnection$BindRequest$$ExternalSyntheticLambda0(this), 20, TimeUnit.SECONDS);
            getTask().addOnCompleteListener(r5, new WithinAppServiceConnection$BindRequest$$ExternalSyntheticLambda1(r0));
        }

        public void finish() {
            this.taskCompletionSource.trySetResult(null);
        }

        public Task<Void> getTask() {
            return this.taskCompletionSource.getTask();
        }
    }

    public WithinAppServiceConnection(Context r2, String r3) {
        this(r2, r3, createScheduledThreadPoolExecutor());
    }

    public WithinAppServiceConnection(Context r2, String r3, ScheduledExecutorService r4) {
        this.intentQueue = new ArrayDeque();
        this.connectionInProgress = false;
        Context r22 = r2.getApplicationContext();
        this.context = r22;
        this.connectionIntent = new Intent(r3).setPackage(r22.getPackageName());
        this.scheduledExecutorService = r4;
    }

    @SuppressLint({"ThreadPoolCreation"})
    private static ScheduledThreadPoolExecutor createScheduledThreadPoolExecutor() {
        ScheduledThreadPoolExecutor r0 = new ScheduledThreadPoolExecutor(1, new NamedThreadFactory("Firebase-FirebaseInstanceIdServiceConnection"));
        r0.setKeepAliveTime(40, TimeUnit.SECONDS);
        r0.allowCoreThreadTimeOut(true);
        return r0;
    }

    private void finishAllInQueue() {
    L3:
        if (this.intentQueue.isEmpty() == true) goto L5;
        this.intentQueue.poll().finish();
        goto L3
    }

    private synchronized void flushQueue() {
        monitor-enter(this);
    L6:
        th = move-exception;
        throw th;
    L4:
        if (Log.isLoggable("FirebaseMessaging", 3) == false) goto L9;
        Log.d("FirebaseMessaging", "flush queue called");     // Catch: Throwable -> L6
    L9:
        if (this.intentQueue.isEmpty() == true) goto L24;
        if (Log.isLoggable("FirebaseMessaging", 3) == false) goto L13;
        Log.d("FirebaseMessaging", "found intent to be delivered");     // Catch: Throwable -> L6
    L13:
        WithinAppServiceBinder r0 = this.binder;     // Catch: Throwable -> L6
        if (r0 == null) goto L21;
        if (r0.isBinderAlive() == false) goto L21;
        if (Log.isLoggable("FirebaseMessaging", 3) == false) goto L20;
        Log.d("FirebaseMessaging", "binder is alive, sending the intent.");     // Catch: Throwable -> L6
    L20:
        BindRequest r02 = this.intentQueue.poll();     // Catch: Throwable -> L6
        this.binder.send(r02);     // Catch: Throwable -> L6
    L21:
        startConnectionIfNeeded();     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L24:
        monitor-exit(this);
    }

    private void startConnectionIfNeeded() {
        if (Log.isLoggable("FirebaseMessaging", 3) == false) goto L6;
        StringBuilder r0 = new StringBuilder("binder is dead. start connection? ");
        r0.append(!this.connectionInProgress);
        Log.d("FirebaseMessaging", r0.toString());
    L6:
        if (this.connectionInProgress == true) goto L20;
        this.connectionInProgress = true;
    L14:
        e = move-exception;
        Log.e("FirebaseMessaging", "Exception while binding the service", e);
    L16:
        this.connectionInProgress = false;
        finishAllInQueue();
        return;
    L10:
        if (ConnectionTracker.getInstance().bindService(this.context, this.connectionIntent, this, 65) == false) goto L12;
        return;
    L12:
        Log.e("FirebaseMessaging", "binding to the service failed");     // Catch: SecurityException -> L14
        goto L16
    }

    @Override // android.content.ServiceConnection
    public synchronized void onServiceConnected(ComponentName r5, IBinder r6) {
        monitor-enter(this);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (Log.isLoggable("FirebaseMessaging", 3) == false) goto L9;
        Log.d("FirebaseMessaging", "onServiceConnected: " + r5);     // Catch: Throwable -> L7
    L9:
        this.connectionInProgress = false;     // Catch: Throwable -> L7
        if ((r6 instanceof WithinAppServiceBinder) == true) goto L14;
        Log.e("FirebaseMessaging", "Invalid service connection: " + r6);     // Catch: Throwable -> L7
        finishAllInQueue();     // Catch: Throwable -> L7
        monitor-exit(this);
        return;
    L14:
        this.binder = (WithinAppServiceBinder) r6;     // Catch: Throwable -> L7
        flushQueue();     // Catch: Throwable -> L7
        monitor-exit(this);
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName r4) {
        if (Log.isLoggable("FirebaseMessaging", 3) == false) goto L5;
        Log.d("FirebaseMessaging", "onServiceDisconnected: " + r4);
    L5:
        flushQueue();
    }

    public synchronized Task<Void> sendIntent(Intent r3) {
        monitor-enter(this);
    L6:
        th = move-exception;
        throw th;
    L4:
        if (Log.isLoggable("FirebaseMessaging", 3) == false) goto L8;
        Log.d("FirebaseMessaging", "new intent queued in the bind-strategy delivery");     // Catch: Throwable -> L6
    L8:
        BindRequest r0 = new BindRequest(r3);     // Catch: Throwable -> L6
        r0.arrangeTimeout(this.scheduledExecutorService);     // Catch: Throwable -> L6
        this.intentQueue.add(r0);     // Catch: Throwable -> L6
        flushQueue();     // Catch: Throwable -> L6
        Task<Void> r32 = r0.getTask();     // Catch: Throwable -> L6
        monitor-exit(this);
        return r32;
    }
}
