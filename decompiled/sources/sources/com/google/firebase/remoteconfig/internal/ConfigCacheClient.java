package com.google.firebase.remoteconfig.internal;

import android.util.Log;
import androidx.credentials.CredentialManager$$ExternalSyntheticLambda0;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes.dex */
public class ConfigCacheClient {
    private static final Executor DIRECT_EXECUTOR = null;
    private static final Map<String, ConfigCacheClient> clientInstances = null;
    private Task<ConfigContainer> cachedContainerTask;
    private final Executor executor;
    private final ConfigStorageClient storageClient;

    /* renamed from: com.google.firebase.remoteconfig.internal.ConfigCacheClient$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    /* loaded from: classes5.dex */
    public static class AwaitListener<TResult> implements OnSuccessListener<TResult>, OnFailureListener, OnCanceledListener {
        private final CountDownLatch latch;

        private AwaitListener() {
            this.latch = new CountDownLatch(1);
        }

        public /* synthetic */ AwaitListener(AnonymousClass1 r1) {
            this();
        }

        public boolean await(long r1, TimeUnit r3) {
            return this.latch.await(r1, r3);
        }

        @Override // com.google.android.gms.tasks.OnCanceledListener
        public void onCanceled() {
            this.latch.countDown();
        }

        @Override // com.google.android.gms.tasks.OnFailureListener
        public void onFailure(Exception r1) {
            this.latch.countDown();
        }

        @Override // com.google.android.gms.tasks.OnSuccessListener
        public void onSuccess(TResult r1) {
            this.latch.countDown();
        }
    }

    public static /* synthetic */ Task $r8$lambda$LvQjX7XEpXst4uSOi9kWWub2vbA(ConfigCacheClient r0, boolean r1, ConfigContainer r2, Void r3) {
        if (r1 == false) goto L4;
        r0.updateInMemoryConfigContainer(r2);
    L6:
        return Tasks.forResult(r2);
    L4:
        r0.getClass();
        goto L6
    }

    /* renamed from: $r8$lambda$bkoyCoRu0Y1EC4-9BSzoJc4Y0Fo, reason: not valid java name */
    public static /* synthetic */ Void m1973$r8$lambda$bkoyCoRu0Y1EC49BSzoJc4Y0Fo(ConfigCacheClient r0, ConfigContainer r1) {
        return r0.storageClient.write(r1);
    }

    static {
        clientInstances = new HashMap();
        DIRECT_EXECUTOR = new CredentialManager$$ExternalSyntheticLambda0();
    }

    private ConfigCacheClient(Executor r1, ConfigStorageClient r2) {
        this.executor = r1;
        this.storageClient = r2;
        this.cachedContainerTask = null;
    }

    private static <TResult> TResult await(Task<TResult> r2, long r3, TimeUnit r5) {
        AwaitListener r0 = new AwaitListener(null);
        Executor r1 = DIRECT_EXECUTOR;
        r2.addOnSuccessListener(r1, r0);
        r2.addOnFailureListener(r1, r0);
        r2.addOnCanceledListener(r1, r0);
        if (r0.await(r3, r5) == false) goto L11;
        if (r2.isSuccessful() == false) goto L9;
        return r2.getResult();
    L9:
        throw new ExecutionException(r2.getException());
    L11:
        throw new TimeoutException("Task await timed out.");
    }

    public static synchronized ConfigCacheClient getInstance(Executor r4, ConfigStorageClient r5) {
        monitor-enter(ConfigCacheClient.class);
        String r1 = r5.getFileName();     // Catch: Throwable -> L7
        Map<String, ConfigCacheClient> r2 = clientInstances;     // Catch: Throwable -> L7
        if (r2.containsKey(r1) == true) goto L9;
        r2.put(r1, new ConfigCacheClient(r4, r5));     // Catch: Throwable -> L7
    L9:
        ConfigCacheClient r42 = r2.get(r1);     // Catch: Throwable -> L7
        monitor-exit(ConfigCacheClient.class);
        return r42;
    L7:
        th = move-exception;
        throw th;
    }

    private synchronized void updateInMemoryConfigContainer(ConfigContainer r1) {
        monitor-enter(this);
        this.cachedContainerTask = Tasks.forResult(r1);     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized Task<ConfigContainer> get() {
        monitor-enter(this);
        Task<ConfigContainer> r0 = this.cachedContainerTask;     // Catch: Throwable -> L10
        if (r0 != null) goto L6;
    L12:
        Executor r02 = this.executor;     // Catch: Throwable -> L10
        ConfigStorageClient r1 = this.storageClient;     // Catch: Throwable -> L10
        Objects.requireNonNull(r1);     // Catch: Throwable -> L10
        this.cachedContainerTask = Tasks.call(r02, new ConfigCacheClient$$ExternalSyntheticLambda0(r1));     // Catch: Throwable -> L10
    L13:
        Task<ConfigContainer> r03 = this.cachedContainerTask;     // Catch: Throwable -> L10
        monitor-exit(this);
        return r03;
    L6:
        if (r0.isComplete() == false) goto L13;
        if (this.cachedContainerTask.isSuccessful() == true) goto L13;
    L10:
        th = move-exception;
        throw th;
    }

    public ConfigContainer getBlocking() {
        return getBlocking(5);
    }

    public ConfigContainer getBlocking(long r2) {
        monitor-enter(this);
        Task<ConfigContainer> r0 = this.cachedContainerTask;     // Catch: Throwable -> L10
        if (r0 != null) goto L6;
    L12:
        monitor-exit(this);     // Catch: Throwable -> L10
        return (ConfigContainer) await(get(), r2, TimeUnit.SECONDS);
    L15:
        e = move-exception;
        Log.d("FirebaseRemoteConfig", "Reading from storage file failed.", e);
        return null;
    L6:
        if (r0.isSuccessful() == false) goto L12;
        ConfigContainer r22 = this.cachedContainerTask.getResult();     // Catch: Throwable -> L10
        monitor-exit(this);     // Catch: Throwable -> L10
        return r22;
    L10:
        th = move-exception;
        throw th;
    }

    public Task<ConfigContainer> put(ConfigContainer r2) {
        return put(r2, true);
    }

    public Task<ConfigContainer> put(ConfigContainer r4, boolean r5) {
        return Tasks.call(this.executor, new ConfigCacheClient$$ExternalSyntheticLambda1(this, r4)).onSuccessTask(this.executor, new ConfigCacheClient$$ExternalSyntheticLambda2(this, r5, r4));
    }
}
