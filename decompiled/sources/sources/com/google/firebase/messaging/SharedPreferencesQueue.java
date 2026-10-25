package com.google.firebase.messaging;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.concurrent.Executor;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
final class SharedPreferencesQueue {
    private boolean bulkOperation;
    final ArrayDeque<String> internalQueue;
    private final String itemSeparator;
    private final String queueName;
    private final SharedPreferences sharedPreferences;
    private final Executor syncExecutor;

    public static /* synthetic */ void $r8$lambda$E9XW8fIBuBlfy6ibDQdajXfq4Mo(SharedPreferencesQueue r0) {
        r0.syncState();
    }

    private SharedPreferencesQueue(SharedPreferences r2, String r3, String r4, Executor r5) {
        this.internalQueue = new ArrayDeque();
        this.bulkOperation = false;
        this.sharedPreferences = r2;
        this.queueName = r3;
        this.itemSeparator = r4;
        this.syncExecutor = r5;
    }

    private boolean checkAndSyncState(boolean r2) {
        if (r2 == true) goto L4;
    L6:
        return r2;
    L4:
        if (this.bulkOperation == true) goto L6;
        syncStateAsync();
        goto L6
    }

    public static SharedPreferencesQueue createInstance(SharedPreferences r1, String r2, String r3, Executor r4) {
        SharedPreferencesQueue r0 = new SharedPreferencesQueue(r1, r2, r3, r4);
        r0.initQueue();
        return r0;
    }

    private void initQueue() {
        ArrayDeque<String> r0 = this.internalQueue;
        monitor-enter(r0);
        this.internalQueue.clear();     // Catch: Throwable -> L12
        String r1 = this.sharedPreferences.getString(this.queueName, _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Throwable -> L12
        if (TextUtils.isEmpty(r1) == false) goto L7;
    L22:
        monitor-exit(r0);     // Catch: Throwable -> L12
        return;
    L7:
        if (r1.contains(this.itemSeparator) == false) goto L22;
        String[] r12 = r1.split(this.itemSeparator, -1);     // Catch: Throwable -> L12
        if (r12.length != 0) goto L14;
        Log.e("FirebaseMessaging", "Corrupted queue. Please check the queue contents and item separator provided");     // Catch: Throwable -> L12
    L14:
        int r2 = r12.length;     // Catch: Throwable -> L12
        int r3 = 0;
    L15:
        if (r3 >= r2) goto L20;
        String r4 = r12[r3];     // Catch: Throwable -> L12
        if (TextUtils.isEmpty(r4) == true) goto L19;
        this.internalQueue.add(r4);     // Catch: Throwable -> L12
    L19:
        r3 = r3 + 1;     // Catch: Throwable -> L12
        goto L15
    L20:
        monitor-exit(r0);     // Catch: Throwable -> L12
        return;
    L12:
        th = move-exception;
        throw th;
    }

    private void syncState() {
        ArrayDeque<String> r0 = this.internalQueue;
        monitor-enter(r0);
        this.sharedPreferences.edit().putString(this.queueName, serialize()).apply();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    private void syncStateAsync() {
        this.syncExecutor.execute(new SharedPreferencesQueue$$ExternalSyntheticLambda0(this));
    }

    public String peek() {
        ArrayDeque<String> r0 = this.internalQueue;
        monitor-enter(r0);
        String r1 = this.internalQueue.peek();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public boolean remove(Object r3) {
        ArrayDeque<String> r0 = this.internalQueue;
        monitor-enter(r0);
        boolean r2 = checkAndSyncState(this.internalQueue.remove(r3));     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r2;
    L7:
        th = move-exception;
        throw th;
    }

    public String serialize() {
        StringBuilder r0 = new StringBuilder();
        Iterator<String> r1 = this.internalQueue.iterator();
    L4:
        if (r1.hasNext() == false) goto L7;
        r0.append(r1.next());
        r0.append(this.itemSeparator);
        goto L4
    L7:
        return r0.toString();
    }
}
