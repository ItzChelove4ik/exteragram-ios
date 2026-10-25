package com.google.firebase.messaging;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.PowerManager;
import android.util.Log;
import java.io.IOException;

/* loaded from: classes5.dex */
class TopicsSyncTask implements Runnable {
    private static final Object TOPIC_SYNC_TASK_LOCK = null;
    private static Boolean hasAccessNetworkStatePermission;
    private static Boolean hasWakeLockPermission;
    private final Context context;
    private final Metadata metadata;
    private final long nextDelaySeconds;
    private final PowerManager.WakeLock syncWakeLock;
    private final TopicsSubscriber topicsSubscriber;

    public class ConnectivityChangeReceiver extends BroadcastReceiver {
        private TopicsSyncTask task;
        final /* synthetic */ TopicsSyncTask this$0;

        public ConnectivityChangeReceiver(TopicsSyncTask r1, TopicsSyncTask r2) {
            this.this$0 = r1;
            this.task = r2;
        }

        @Override // android.content.BroadcastReceiver
        public synchronized void onReceive(Context r4, Intent r5) {
            monitor-enter(this);
            TopicsSyncTask r52 = this.task;     // Catch: Throwable -> L14
            if (r52 != null) goto L8;
            monitor-exit(this);
            return;
        L8:
            if (TopicsSyncTask.access$000(r52) == true) goto L12;
            monitor-exit(this);
            return;
        L12:
            if (TopicsSyncTask.access$100() == false) goto L16;
            Log.d("FirebaseMessaging", "Connectivity changed. Starting background sync.");     // Catch: Throwable -> L14
        L16:
            TopicsSyncTask.access$200(this.task).scheduleSyncTaskWithDelaySeconds(this.task, 0);     // Catch: Throwable -> L14
            r4.unregisterReceiver(this);     // Catch: Throwable -> L14
            this.task = null;     // Catch: Throwable -> L14
            monitor-exit(this);
            return;
        L14:
            th = move-exception;
            throw th;
        }

        public void registerReceiver() {
            if (TopicsSyncTask.access$100() == false) goto L5;
            Log.d("FirebaseMessaging", "Connectivity change received registered");
        L5:
            TopicsSyncTask.access$300(this.this$0).registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
    }

    static {
        TOPIC_SYNC_TASK_LOCK = new Object();
    }

    public TopicsSyncTask(TopicsSubscriber r1, Context r2, Metadata r3, long r4) {
        this.topicsSubscriber = r1;
        this.context = r2;
        this.nextDelaySeconds = r4;
        this.metadata = r3;
        this.syncWakeLock = ((PowerManager) r2.getSystemService("power")).newWakeLock(1, "wake:com.google.firebase.messaging");
    }

    public static /* synthetic */ boolean access$000(TopicsSyncTask r0) {
        return r0.isDeviceConnected();
    }

    public static /* synthetic */ boolean access$100() {
        return isLoggable();
    }

    public static /* synthetic */ TopicsSubscriber access$200(TopicsSyncTask r0) {
        return r0.topicsSubscriber;
    }

    public static /* synthetic */ Context access$300(TopicsSyncTask r0) {
        return r0.context;
    }

    private static String createPermissionMissingLog(String r2) {
        return "Missing Permission: " + r2 + ". This permission should normally be included by the manifest merger, but may needed to be manually added to your manifest";
    }

    private static boolean hasAccessNetworkStatePermission(Context r3) {
        Object r0 = TOPIC_SYNC_TASK_LOCK;
        monitor-enter(r0);
        Boolean r1 = hasAccessNetworkStatePermission;     // Catch: Throwable -> L7
        if (r1 != null) goto L9;
        boolean r32 = hasPermission(r3, "android.permission.ACCESS_NETWORK_STATE", r1);     // Catch: Throwable -> L7
    L10:
        Boolean r33 = Boolean.valueOf(r32);     // Catch: Throwable -> L7
        hasAccessNetworkStatePermission = r33;     // Catch: Throwable -> L7
        boolean r34 = r33.booleanValue();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r34;
    L9:
        r32 = r1.booleanValue();     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        throw th;
    }

    private static boolean hasPermission(Context r1, String r2, Boolean r3) {
        if (r3 == null) goto L6;
        return r3.booleanValue();
    L6:
        if (r1.checkCallingOrSelfPermission(r2) != 0) goto L8;
        boolean r12 = true;
    L9:
        if (r12 == false) goto L11;
    L13:
        return r12;
    L11:
        if (Log.isLoggable("FirebaseMessaging", 3) == false) goto L13;
        Log.d("FirebaseMessaging", createPermissionMissingLog(r2));
        goto L13
    L8:
        r12 = false;
        goto L9
    }

    private static boolean hasWakeLockPermission(Context r3) {
        Object r0 = TOPIC_SYNC_TASK_LOCK;
        monitor-enter(r0);
        Boolean r1 = hasWakeLockPermission;     // Catch: Throwable -> L7
        if (r1 != null) goto L9;
        boolean r32 = hasPermission(r3, "android.permission.WAKE_LOCK", r1);     // Catch: Throwable -> L7
    L10:
        Boolean r33 = Boolean.valueOf(r32);     // Catch: Throwable -> L7
        hasWakeLockPermission = r33;     // Catch: Throwable -> L7
        boolean r34 = r33.booleanValue();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r34;
    L9:
        r32 = r1.booleanValue();     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        throw th;
    }

    private synchronized boolean isDeviceConnected() {
        monitor-enter(this);
        ConnectivityManager r0 = (ConnectivityManager) this.context.getSystemService("connectivity");     // Catch: Throwable -> L6
        if (r0 == null) goto L8;
        NetworkInfo r02 = r0.getActiveNetworkInfo();     // Catch: Throwable -> L6
    L9:
        if (r02 == null) goto L13;
        if (r02.isConnected() == false) goto L13;
        boolean r03 = true;
    L14:
        monitor-exit(this);
        return r03;
    L13:
        r03 = false;
        goto L14
    L8:
        r02 = null;
    L6:
        th = move-exception;
        throw th;
    }

    private static boolean isLoggable() {
        return Log.isLoggable("FirebaseMessaging", 3);
    }

    @Override // java.lang.Runnable
    @SuppressLint({"Wakelock"})
    public void run() {
        if (hasWakeLockPermission(this.context) == false) goto L58;
        this.syncWakeLock.acquire(Constants.WAKE_LOCK_ACQUIRE_TIMEOUT_MILLIS);
    L58:
        this.topicsSubscriber.setSyncScheduledOrRunning(true);     // Catch: Throwable -> L15 IOException -> L17
        if (this.metadata.isGmscorePresent() == true) goto L20;
        this.topicsSubscriber.setSyncScheduledOrRunning(false);     // Catch: Throwable -> L15 IOException -> L17
        if (hasWakeLockPermission(this.context) == false) goto L59;
        this.syncWakeLock.release();     // Catch: RuntimeException -> L13
        return;
    L13:
        Log.i("FirebaseMessaging", "TopicsSyncTask's wakelock was already released due to timeout.");
        return;
    L59:
        return;
    L20:
        if (hasAccessNetworkStatePermission(this.context) == true) goto L22;
    L30:
        boolean r4 = this.topicsSubscriber.syncTopics();     // Catch: Throwable -> L15 IOException -> L17
        TopicsSubscriber r5 = this.topicsSubscriber;
        if (r4 == false) goto L34;
        r5.setSyncScheduledOrRunning(false);     // Catch: Throwable -> L15 IOException -> L17
    L36:
        if (hasWakeLockPermission(this.context) == false) goto L61;
        this.syncWakeLock.release();     // Catch: RuntimeException -> L39
        return;
    L61:
        return;
    L34:
        r5.syncWithDelaySecondsInternal(this.nextDelaySeconds);     // Catch: Throwable -> L15 IOException -> L17
        goto L36
    L22:
        if (isDeviceConnected() == true) goto L30;
        new ConnectivityChangeReceiver(this, this).registerReceiver();     // Catch: Throwable -> L15 IOException -> L17
        if (hasWakeLockPermission(this.context) == false) goto L60;
        this.syncWakeLock.release();     // Catch: RuntimeException -> L28
        return;
    L28:
        Log.i("FirebaseMessaging", "TopicsSyncTask's wakelock was already released due to timeout.");
        return;
    L60:
        return;
    L15:
        th = move-exception;
        if (hasWakeLockPermission(this.context) == true) goto L53;
    L50:
        throw th;
    L53:
        this.syncWakeLock.release();     // Catch: RuntimeException -> L49
    L49:
        Log.i("FirebaseMessaging", "TopicsSyncTask's wakelock was already released due to timeout.");
    L17:
        e = move-exception;
        Log.e("FirebaseMessaging", "Failed to sync topics. Won't retry sync. " + e.getMessage());     // Catch: Throwable -> L15
        this.topicsSubscriber.setSyncScheduledOrRunning(false);     // Catch: Throwable -> L15
        if (hasWakeLockPermission(this.context) == false) goto L63;
        this.syncWakeLock.release();     // Catch: RuntimeException -> L39
        return;
    L63:
        return;
    L39:
        Log.i("FirebaseMessaging", "TopicsSyncTask's wakelock was already released due to timeout.");
    }
}
