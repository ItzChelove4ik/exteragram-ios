package com.google.firebase.messaging;

import android.content.Context;
import android.content.SharedPreferences;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
final class TopicsStore {
    private static WeakReference<TopicsStore> topicsStoreWeakReference;
    private final SharedPreferences sharedPreferences;
    private final Executor syncExecutor;
    private SharedPreferencesQueue topicOperationsQueue;

    private TopicsStore(SharedPreferences r1, Executor r2) {
        this.syncExecutor = r2;
        this.sharedPreferences = r1;
    }

    public static synchronized TopicsStore getInstance(Context r3, Executor r4) {
        monitor-enter(TopicsStore.class);
        WeakReference<TopicsStore> r1 = topicsStoreWeakReference;     // Catch: Throwable -> L7
        if (r1 == null) goto L9;
        TopicsStore r12 = r1.get();     // Catch: Throwable -> L7
    L10:
        if (r12 != null) goto L12;
        r12 = new TopicsStore(r3.getSharedPreferences("com.google.android.gms.appid", 0), r4);     // Catch: Throwable -> L7
        r12.initStore();     // Catch: Throwable -> L7
        topicsStoreWeakReference = new WeakReference(r12);     // Catch: Throwable -> L7
    L12:
        monitor-exit(TopicsStore.class);
        return r12;
    L9:
        r12 = null;
    L7:
        th = move-exception;
        throw th;
    }

    private synchronized void initStore() {
        monitor-enter(this);
        this.topicOperationsQueue = SharedPreferencesQueue.createInstance(this.sharedPreferences, "topic_operation_queue", ",", this.syncExecutor);     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized TopicOperation getNextTopicOperation() {
        monitor-enter(this);
        TopicOperation r0 = TopicOperation.from(this.topicOperationsQueue.peek());     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized boolean removeTopicOperation(TopicOperation r2) {
        monitor-enter(this);
        boolean r22 = this.topicOperationsQueue.remove(r2.serialize());     // Catch: Throwable -> L6
        monitor-exit(this);
        return r22;
    L6:
        th = move-exception;
        throw th;
    }
}
