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
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
class SyncTask implements Runnable {
    private final FirebaseMessaging firebaseMessaging;
    private final long nextDelaySeconds;

    @SuppressLint({"ThreadPoolCreation"})
    ExecutorService processorExecutor;
    private final PowerManager.WakeLock syncWakeLock;

    public static class ConnectivityChangeReceiver extends BroadcastReceiver {
        private Context receiverContext;
        private SyncTask task;

        public ConnectivityChangeReceiver(SyncTask r1) {
            this.task = r1;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context r3, Intent r4) {
            SyncTask r32 = this.task;
            if (r32 != null) goto L6;
            return;
        L6:
            if (r32.isDeviceConnected() == true) goto L9;
            return;
        L9:
            if (SyncTask.isDebugLogEnabled() == false) goto L11;
            Log.d("FirebaseMessaging", "Connectivity changed. Starting background sync.");
        L11:
            SyncTask.access$000(this.task).enqueueTaskWithDelaySeconds(this.task, 0);
            Context r33 = this.receiverContext;
            if (r33 == null) goto L14;
            r33.unregisterReceiver(this);
        L14:
            this.task = null;
        }

        public void registerReceiver() {
            if (SyncTask.isDebugLogEnabled() == false) goto L5;
            Log.d("FirebaseMessaging", "Connectivity change received registered");
        L5:
            IntentFilter r0 = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
            SyncTask r1 = this.task;
            if (r1 == null) goto L9;
            Context r12 = r1.getContext();
            this.receiverContext = r12;
            r12.registerReceiver(this, r0);
            return;
        }
    }

    @SuppressLint({"InvalidWakeLockTag"})
    public SyncTask(FirebaseMessaging r9, long r10) {
        LinkedBlockingQueue r6 = new LinkedBlockingQueue();
        NamedThreadFactory r7 = new NamedThreadFactory("firebase-iid-executor");
        this.processorExecutor = new ThreadPoolExecutor(0, 1, 30, TimeUnit.SECONDS, r6, r7);
        this.firebaseMessaging = r9;
        this.nextDelaySeconds = r10;
        PowerManager.WakeLock r92 = ((PowerManager) getContext().getSystemService("power")).newWakeLock(1, "fiid-sync");
        this.syncWakeLock = r92;
        r92.setReferenceCounted(false);
    }

    public static /* synthetic */ FirebaseMessaging access$000(SyncTask r0) {
        return r0.firebaseMessaging;
    }

    public static boolean isDebugLogEnabled() {
        return Log.isLoggable("FirebaseMessaging", 3);
    }

    public Context getContext() {
        return this.firebaseMessaging.getApplicationContext();
    }

    public boolean isDeviceConnected() {
        ConnectivityManager r1 = (ConnectivityManager) getContext().getSystemService("connectivity");
        if (r1 == null) goto L5;
        NetworkInfo r12 = r1.getActiveNetworkInfo();
    L6:
        if (r12 != null) goto L8;
        return false;
    L8:
        if (r12.isConnected() == false) goto L13;
        return true;
    L13:
        return false;
    L5:
        r12 = null;
        goto L6
    }

    public boolean maybeRefreshToken() {
    L7:
        e = move-exception;
        if (GmsRpc.isErrorMessageForRetryableError(e.getMessage()) == false) goto L21;
        Log.w("FirebaseMessaging", "Token retrieval failed: " + e.getMessage() + ". Will retry token retrieval");
        return false;
    L21:
        if (e.getMessage() != null) goto L24;
        Log.w("FirebaseMessaging", "Token retrieval failed without exception message. Will retry token retrieval");
        return false;
    L24:
        throw e;
    L14:
        Log.w("FirebaseMessaging", "Token retrieval failed with SecurityException. Will retry token retrieval");
        return false;
    L4:
        if (this.firebaseMessaging.blockingGetToken() != null) goto L10;
        Log.e("FirebaseMessaging", "Token retrieval failed: null");     // Catch: IOException -> L7 SecurityException -> L14
        return false;
    L10:
        if (Log.isLoggable("FirebaseMessaging", 3) == false) goto L26;
        Log.d("FirebaseMessaging", "Token successfully retrieved");     // Catch: IOException -> L7 SecurityException -> L14
        return true;
    L26:
        return true;
    }

    @Override // java.lang.Runnable
    @SuppressLint({"WakelockTimeout"})
    public void run() {
        if (ServiceStarter.getInstance().hasWakeLockPermission(getContext()) == false) goto L43;
        this.syncWakeLock.acquire();
    L43:
        this.firebaseMessaging.setSyncScheduledOrRunning(true);     // Catch: Throwable -> L13 IOException -> L15
        if (this.firebaseMessaging.isGmsCorePresent() == true) goto L18;
        this.firebaseMessaging.setSyncScheduledOrRunning(false);     // Catch: Throwable -> L13 IOException -> L15
        if (ServiceStarter.getInstance().hasWakeLockPermission(getContext()) == false) goto L44;
    L11:
        this.syncWakeLock.release();
        return;
    L44:
        return;
    L18:
        if (ServiceStarter.getInstance().hasAccessNetworkStatePermission(getContext()) == true) goto L20;
    L25:
        boolean r2 = maybeRefreshToken();     // Catch: Throwable -> L13 IOException -> L15
        FirebaseMessaging r3 = this.firebaseMessaging;
        if (r2 == false) goto L29;
        r3.setSyncScheduledOrRunning(false);     // Catch: Throwable -> L13 IOException -> L15
    L31:
        if (ServiceStarter.getInstance().hasWakeLockPermission(getContext()) == true) goto L11;
        return;
    L29:
        r3.syncWithDelaySecondsInternal(this.nextDelaySeconds);     // Catch: Throwable -> L13 IOException -> L15
        goto L31
    L20:
        if (isDeviceConnected() == true) goto L25;
        new ConnectivityChangeReceiver(this).registerReceiver();     // Catch: Throwable -> L13 IOException -> L15
        if (ServiceStarter.getInstance().hasWakeLockPermission(getContext()) == true) goto L11;
        return;
    L15:
        e = move-exception;
        Log.e("FirebaseMessaging", "Topic sync or token retrieval failed on hard failure exceptions: " + e.getMessage() + ". Won't retry the operation.");     // Catch: Throwable -> L13
        this.firebaseMessaging.setSyncScheduledOrRunning(false);     // Catch: Throwable -> L13
        if (ServiceStarter.getInstance().hasWakeLockPermission(getContext()) == false) goto L47;
        this.syncWakeLock.release();
        return;
    L47:
        return;
    L13:
        th = move-exception;
        if (ServiceStarter.getInstance().hasWakeLockPermission(getContext()) == false) goto L41;
        this.syncWakeLock.release();
    L41:
        throw th;
    }
}
