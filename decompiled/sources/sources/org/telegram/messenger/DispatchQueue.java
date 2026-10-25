package org.telegram.messenger;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.SystemClock;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes.dex */
public class DispatchQueue extends Thread {
    private static final int THREAD_PRIORITY_DEFAULT = -1000;
    private static int indexPointer;
    private volatile Handler handler;
    public final int index;
    private long lastTaskTime;
    private final CountDownLatch syncLatch;
    private int threadPriority;

    /* renamed from: $r8$lambda$o-aRjxAeBCO5MK-a9i6a8pFcdxs, reason: not valid java name */
    public static /* synthetic */ boolean m3976$r8$lambda$oaRjxAeBCO5MKa9i6a8pFcdxs(DispatchQueue r0, Message r1) {
        return r0.lambda$run$0(r1);
    }

    static {
    }

    public DispatchQueue(String r2) {
        this(r2, true);
    }

    public DispatchQueue(String r3, boolean r4) {
        this.handler = null;
        this.syncLatch = new CountDownLatch(1);
        int r0 = indexPointer;
        indexPointer = r0 + 1;
        this.index = r0;
        this.threadPriority = THREAD_PRIORITY_DEFAULT;
        setName(r3);
        if (r4 == false) goto L6;
        start();
        return;
    }

    public DispatchQueue(String r3, boolean r4, int r5) {
        this.handler = null;
        this.syncLatch = new CountDownLatch(1);
        int r0 = indexPointer;
        indexPointer = r0 + 1;
        this.index = r0;
        this.threadPriority = r5;
        setName(r3);
        if (r4 == false) goto L6;
        start();
        return;
    }

    private /* synthetic */ boolean lambda$run$0(Message r1) {
        handleMessage(r1);
        return true;
    }

    public void cancelRunnable(Runnable r2) {
        this.syncLatch.await();     // Catch: Exception -> L4
        this.handler.removeCallbacks(r2);     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    public void cancelRunnables(Runnable[] r5) {
        this.syncLatch.await();     // Catch: Exception -> L7
        int r0 = r5.length;     // Catch: Exception -> L7
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L6;
        Runnable r2 = r5[r1];     // Catch: Exception -> L7
        this.handler.removeCallbacks(r2);     // Catch: Exception -> L7
        r1 = r1 + 1;
        goto L3
    L6:
        return;
    L7:
        e = move-exception;
        FileLog.e(e);
    }

    public void cleanupQueue() {
        this.syncLatch.await();     // Catch: Exception -> L4
        this.handler.removeCallbacksAndMessages(null);     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    public Handler getHandler() {
        return this.handler;
    }

    public long getLastTaskTime() {
        return this.lastTaskTime;
    }

    public void handleMessage(Message r1) {
    }

    public boolean isReady() {
        if (this.syncLatch.getCount() != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public void onLooperPrepared() {
    }

    public boolean postRunnable(Runnable r3) {
        this.lastTaskTime = SystemClock.elapsedRealtime();
        return postRunnable(r3, 0);
    }

    public boolean postRunnable(Runnable r3, long r4) {
        this.syncLatch.await();     // Catch: Exception -> L4
    L6:
        Handler r2 = this.handler;
        if (r4 > 0) goto L11;
        return r2.post(r3);
    L11:
        return r2.postDelayed(r3, r4);
    L4:
        e = move-exception;
        FileLog.e(e);
        goto L6
    }

    public boolean postToFrontRunnable(Runnable r2) {
        this.syncLatch.await();     // Catch: Exception -> L4
    L7:
        return this.handler.postAtFrontOfQueue(r2);
    L4:
        e = move-exception;
        FileLog.e(e);
        goto L7
    }

    public void recycle() {
        this.handler.getLooper().quit();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Looper.prepare();
        this.handler = new Handler(Looper.myLooper(), new DispatchQueue$$ExternalSyntheticLambda0(this));
        this.syncLatch.countDown();
        int r0 = this.threadPriority;
        if (r0 == THREAD_PRIORITY_DEFAULT) goto L5;
        Process.setThreadPriority(r0);
    L5:
        onLooperPrepared();
        Looper.loop();
    }

    public void sendMessage(Message r3, int r4) {
        this.syncLatch.await();     // Catch: Exception -> L9
        Handler r2 = this.handler;
        if (r4 > 0) goto L7;
        r2.sendMessage(r3);     // Catch: Exception -> L9
        return;
    L7:
        r2.sendMessageDelayed(r3, r4);     // Catch: Exception -> L9
        return;
    }
}
