package com.google.firebase.messaging;

import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.tasks.Task;
import java.util.Map;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
class RequestDeduplicator {
    private final Executor executor;
    private final Map<String, Task<String>> getTokenRequests;

    /* loaded from: classes5.dex */
    public interface GetTokenRequest {
        Task<String> start();
    }

    public static /* synthetic */ Task $r8$lambda$7vbEhMqRR2Cih64GBiItUUcAhYE(RequestDeduplicator r1, String r2, Task r3) {
        monitor-enter(r1);
        r1.getTokenRequests.remove(r2);     // Catch: Throwable -> L6
        monitor-exit(r1);     // Catch: Throwable -> L6
        return r3;
    L6:
        th = move-exception;
        throw th;
    }

    public RequestDeduplicator(Executor r2) {
        this.getTokenRequests = new ArrayMap();
        this.executor = r2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public synchronized Task<String> getOrStartGetTokenRequest(String r5, GetTokenRequest r6) {
        monitor-enter(this);
        Task<String> r2 = this.getTokenRequests.get(r5);     // Catch: Throwable -> L10
        if (r2 == null) goto L15;
        if (Log.isLoggable("FirebaseMessaging", 3) == false) goto L12;
        Log.d("FirebaseMessaging", "Joining ongoing request for: " + r5);     // Catch: Throwable -> L10
    L12:
        monitor-exit(this);
        return r2;
    L15:
        if (Log.isLoggable("FirebaseMessaging", 3) == false) goto L17;
        Log.d("FirebaseMessaging", "Making new request for: " + r5);     // Catch: Throwable -> L10
    L17:
        Task r62 = r6.start().continueWithTask(this.executor, new RequestDeduplicator$$ExternalSyntheticLambda0(this, r5));     // Catch: Throwable -> L10
        this.getTokenRequests.put(r5, r62);     // Catch: Throwable -> L10
        monitor-exit(this);
        return r62;
    L10:
        th = move-exception;
        throw th;
    }
}
