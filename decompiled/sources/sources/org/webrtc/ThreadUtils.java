package org.webrtc;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes6.dex */
public class ThreadUtils {

    /* renamed from: org.webrtc.ThreadUtils$1, reason: invalid class name */
    public class AnonymousClass1 implements BlockingOperation {
        final /* synthetic */ Thread val$thread;

        public AnonymousClass1(Thread r1) {
            this.val$thread = r1;
        }

        @Override // org.webrtc.ThreadUtils.BlockingOperation
        public void run() {
            this.val$thread.join();
        }
    }

    /* renamed from: org.webrtc.ThreadUtils$1CaughtException, reason: invalid class name */
    public class C1CaughtException {
        Exception e;

        public C1CaughtException() {
        }
    }

    /* renamed from: org.webrtc.ThreadUtils$1Result, reason: invalid class name */
    public class C1Result {
        public V value;

        public C1Result() {
        }
    }

    /* renamed from: org.webrtc.ThreadUtils$2, reason: invalid class name */
    public class AnonymousClass2 implements BlockingOperation {
        final /* synthetic */ CountDownLatch val$latch;

        public AnonymousClass2(CountDownLatch r1) {
            this.val$latch = r1;
        }

        @Override // org.webrtc.ThreadUtils.BlockingOperation
        public void run() {
            this.val$latch.await();
        }
    }

    /* renamed from: org.webrtc.ThreadUtils$3, reason: invalid class name */
    public class AnonymousClass3 implements Runnable {
        final /* synthetic */ CountDownLatch val$barrier;
        final /* synthetic */ Callable val$callable;
        final /* synthetic */ C1CaughtException val$caughtException;
        final /* synthetic */ C1Result val$result;

        public AnonymousClass3(C1Result r1, Callable r2, C1CaughtException r3, CountDownLatch r4) {
            this.val$result = r1;
            this.val$callable = r2;
            this.val$caughtException = r3;
            this.val$barrier = r4;
        }

        /* JADX WARN: Type inference failed for: r1v2, types: [V, java.lang.Object] */
        @Override // java.lang.Runnable
        public void run() {
            C1Result r0 = this.val$result;     // Catch: Exception -> L4
            r0.value = this.val$callable.call();     // Catch: Exception -> L4
        L6:
            this.val$barrier.countDown();
            return;
        L4:
            e = move-exception;
            this.val$caughtException.e = e;
            goto L6
        }
    }

    /* renamed from: org.webrtc.ThreadUtils$4, reason: invalid class name */
    public class AnonymousClass4 implements Callable<Void> {
        final /* synthetic */ Runnable val$runner;

        public AnonymousClass4(Runnable r1) {
            this.val$runner = r1;
        }

        @Override // java.util.concurrent.Callable
        public /* bridge */ /* synthetic */ Void call() {
            return call2();
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: call, reason: avoid collision after fix types in other method */
        public Void call2() {
            this.val$runner.run();
            return null;
        }
    }

    public interface BlockingOperation {
        void run();
    }

    public static class ThreadChecker {
        private Thread thread;

        public ThreadChecker() {
            this.thread = Thread.currentThread();
        }

        public void checkIsOnValidThread() {
            if (this.thread != null) goto L6;
            this.thread = Thread.currentThread();
        L6:
            if (Thread.currentThread() != this.thread) goto L8;
            return;
        L8:
            Segment$$ExternalSyntheticBUOutline1.m("Wrong thread");
        }

        public void detachThread() {
            this.thread = null;
        }
    }

    public ThreadUtils() {
    }

    public static void awaitUninterruptibly(CountDownLatch r1) {
        executeUninterruptibly(new AnonymousClass2(r1));
    }

    public static boolean awaitUninterruptibly(CountDownLatch r7, long r8) {
        long r0 = SystemClock.elapsedRealtime();
        boolean r2 = false;
        long r3 = r8;
        boolean r5 = false;
    L12:
        r2 = r7.await(r3, TimeUnit.MILLISECONDS);     // Catch: InterruptedException -> L5
    L8:
        if (r5 == false) goto L10;
        Thread.currentThread().interrupt();
    L10:
        return r2;
    L5:
        r3 = r8 - (SystemClock.elapsedRealtime() - r0);
        if (r3 <= 0) goto L7;
        r5 = true;
        goto L12
    L7:
        r5 = true;
        goto L8
    }

    public static void checkIsOnMainThread() {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("Not on main thread!");
    }

    public static StackTraceElement[] concatStackTraces(StackTraceElement[] r3, StackTraceElement[] r4) {
        StackTraceElement[] r0 = new StackTraceElement[r3.length + r4.length];
        System.arraycopy(r3, 0, r0, 0, r3.length);
        System.arraycopy(r4, 0, r0, r3.length, r4.length);
        return r0;
    }

    public static void executeUninterruptibly(BlockingOperation r1) {
        boolean r0 = false;
    L8:
        r1.run();     // Catch: InterruptedException -> L7
        if (r0 == false) goto L11;
        Thread.currentThread().interrupt();
        return;
    L11:
        return;
    L7:
        r0 = true;
        goto L8
    }

    public static <V> V invokeAtFrontUninterruptibly(Handler r4, Callable<V> r5) {
        if (r4.getLooper().getThread() == Thread.currentThread()) goto L15;
        C1Result r0 = new C1Result();
        C1CaughtException r1 = new C1CaughtException();
        CountDownLatch r2 = new CountDownLatch(1);
        r4.post(new AnonymousClass3(r0, r5, r1, r2));
        awaitUninterruptibly(r2);
        Exception r42 = r1.e;
        if (r42 == null) goto L12;
        RuntimeException r52 = new RuntimeException(r42);
        r52.setStackTrace(concatStackTraces(r1.e.getStackTrace(), r52.getStackTrace()));
        throw r52;
    L12:
        return r0.value;
    L15:
        return r5.call();
    L6:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    public static void invokeAtFrontUninterruptibly(Handler r1, Runnable r2) {
        invokeAtFrontUninterruptibly(r1, new AnonymousClass4(r2));
    }

    public static void joinUninterruptibly(Thread r1) {
        executeUninterruptibly(new AnonymousClass1(r1));
    }

    public static boolean joinUninterruptibly(Thread r7, long r8) {
        long r0 = SystemClock.elapsedRealtime();
        boolean r2 = false;
        long r3 = r8;
    L4:
        if (r3 <= 0) goto L8;
        r7.join(r3);     // Catch: InterruptedException -> L7
    L7:
        r3 = r8 - (SystemClock.elapsedRealtime() - r0);
        r2 = true;
    L8:
        if (r2 == false) goto L11;
        Thread.currentThread().interrupt();
    L11:
        return !r7.isAlive();
    }
}
