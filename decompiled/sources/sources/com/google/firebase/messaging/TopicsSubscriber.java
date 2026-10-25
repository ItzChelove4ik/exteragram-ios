package com.google.firebase.messaging;

import android.content.Context;
import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.installations.FirebaseInstallationsApi;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
class TopicsSubscriber {
    private static final long MAX_DELAY_SEC = 0;
    private final Context context;
    private final Metadata metadata;
    private final Map<String, ArrayDeque<TaskCompletionSource<Void>>> pendingOperations;
    private final TopicsStore store;
    private final ScheduledExecutorService syncExecutor;
    private boolean syncScheduledOrRunning;
    private final TopicSubscriptionClient topicSubscriptionClient;

    public static /* synthetic */ TopicsSubscriber $r8$lambda$cQ44f2KmT4VOqIrhzI03BvbnygI(Context r3, ScheduledExecutorService r4, Metadata r5, FirebaseApp r6, FirebaseMessaging r7, FirebaseInstallationsApi r8) {
        return new TopicsSubscriber(r5, TopicsStore.getInstance(r3, r4), new TopicSubscriptionClient(r6, r7, r8), r3, r4);
    }

    static {
        MAX_DELAY_SEC = 28800;
    }

    public TopicsSubscriber(Metadata r2, TopicsStore r3, TopicSubscriptionClient r4, Context r5, ScheduledExecutorService r6) {
        this.pendingOperations = new ArrayMap();
        this.syncScheduledOrRunning = false;
        this.metadata = r2;
        this.store = r3;
        this.topicSubscriptionClient = r4;
        this.context = r5;
        this.syncExecutor = r6;
    }

    private void blockingSubscribeToTopic(String r1) {
        this.topicSubscriptionClient.subscribe(r1);
    }

    private void blockingUnsubscribeFromTopic(String r1) {
        this.topicSubscriptionClient.unsubscribe(r1);
    }

    public static Task<TopicsSubscriber> createInstance(FirebaseApp r7, FirebaseMessaging r8, FirebaseInstallationsApi r9, Metadata r10, Context r11, ScheduledExecutorService r12) {
        return Tasks.call(r12, new TopicsSubscriber$$ExternalSyntheticLambda0(r11, r12, r10, r7, r8, r9));
    }

    public static boolean isDebugLogEnabled() {
        return Log.isLoggable("FirebaseMessaging", 3);
    }

    private void markCompletePendingOperation(TopicOperation r5) {
        Map<String, ArrayDeque<TaskCompletionSource<Void>>> r0 = this.pendingOperations;
        monitor-enter(r0);
        String r52 = r5.serialize();     // Catch: Throwable -> L8
        if (this.pendingOperations.containsKey(r52) == true) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L10:
        ArrayDeque<TaskCompletionSource<Void>> r1 = this.pendingOperations.get(r52);     // Catch: Throwable -> L8
        TaskCompletionSource<Void> r2 = r1.poll();     // Catch: Throwable -> L8
        if (r2 == null) goto L14;
        r2.setResult(null);     // Catch: Throwable -> L8
    L14:
        if (r1.isEmpty() == false) goto L16;
        this.pendingOperations.remove(r52);     // Catch: Throwable -> L8
    L16:
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    }

    private void startSync() {
        if (isSyncScheduledOrRunning() == true) goto L6;
        syncWithDelaySecondsInternal(0);
        return;
    }

    public boolean hasPendingOperation() {
        if (this.store.getNextTopicOperation() == null) goto L6;
        return true;
    L6:
        return false;
    }

    public synchronized boolean isSyncScheduledOrRunning() {
        monitor-enter(this);
        boolean r0 = this.syncScheduledOrRunning;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public boolean performTopicOperation(TopicOperation r9) {
        String r4 = r9.getOperation();     // Catch: IOException -> L14
        int r5 = r4.hashCode();     // Catch: IOException -> L14
        if (r5 == 83) goto L17;
        if (r5 != 85) goto L22;
        if (r4.equals("U") == false) goto L22;
        blockingUnsubscribeFromTopic(r9.getTopic());     // Catch: IOException -> L14
        if (isDebugLogEnabled() == false) goto L39;
        Log.d("FirebaseMessaging", "Unsubscribe from topic: " + r9.getTopic() + " succeeded.");     // Catch: IOException -> L14
        return true;
    L39:
        return true;
    L22:
        if (isDebugLogEnabled() == false) goto L43;
        Log.d("FirebaseMessaging", "Unknown topic operation" + r9 + ".");     // Catch: IOException -> L14
        return true;
    L43:
        return true;
    L17:
        if (r4.equals("S") == false) goto L22;
        blockingSubscribeToTopic(r9.getTopic());     // Catch: IOException -> L14
        if (isDebugLogEnabled() == false) goto L41;
        Log.d("FirebaseMessaging", "Subscribe to topic: " + r9.getTopic() + " succeeded.");     // Catch: IOException -> L14
        return true;
    L41:
        return true;
    L14:
        e = move-exception;
        if ("SERVICE_NOT_AVAILABLE".equals(e.getMessage()) == false) goto L29;
    L36:
        Log.e("FirebaseMessaging", "Topic operation failed: " + e.getMessage() + ". Will retry Topic operation.");
        return false;
    L29:
        if ("INTERNAL_SERVER_ERROR".equals(e.getMessage()) == true) goto L36;
        if (e.getMessage() != null) goto L35;
        Log.e("FirebaseMessaging", "Topic operation failed without exception message. Will retry Topic operation.");
        return false;
    L35:
        throw e;
    }

    public void scheduleSyncTaskWithDelaySeconds(Runnable r2, long r3) {
        this.syncExecutor.schedule(r2, r3, TimeUnit.SECONDS);
    }

    public synchronized void setSyncScheduledOrRunning(boolean r1) {
        monitor-enter(this);
        this.syncScheduledOrRunning = r1;     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public void startTopicsSyncIfNecessary() {
        if (hasPendingOperation() == false) goto L6;
        startSync();
        return;
    }

    public boolean syncTopics() {
    L2:
        monitor-enter(this);
        TopicOperation r0 = this.store.getNextTopicOperation();     // Catch: Throwable -> L8
        if (r0 == null) goto L6;
        monitor-exit(this);     // Catch: Throwable -> L8
        if (performTopicOperation(r0) == false) goto L16;
        this.store.removeTopicOperation(r0);
        markCompletePendingOperation(r0);
        goto L2
    L16:
        return false;
    L6:
        if (isDebugLogEnabled() == false) goto L11;
        Log.d("FirebaseMessaging", "topic sync succeeded");     // Catch: Throwable -> L8
    L11:
        monitor-exit(this);     // Catch: Throwable -> L8
        return true;
    L8:
        th = move-exception;
        throw th;
    }

    public void syncWithDelaySecondsInternal(long r11) {
        long r8 = Math.min(Math.max(30, 2 * r11), MAX_DELAY_SEC);
        scheduleSyncTaskWithDelaySeconds(new TopicsSyncTask(this, this.context, this.metadata, r8), r11);
        setSyncScheduledOrRunning(true);
    }
}
