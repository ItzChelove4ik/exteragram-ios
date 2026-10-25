package okhttp3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.url._UrlKt;
import okio.Utf8$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003B\u0013\b\u0016\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0002\u0010\u0006J\u0019\u0010\u001e\u001a\u00020\u001f2\n\u0010 \u001a\u00060\u001aR\u00020\u001bH\u0000¢\u0006\u0002\b!J\u0016\u0010\"\u001a\b\u0018\u00010\u001aR\u00020\u001b2\u0006\u0010#\u001a\u00020$H\u0002J\u0006\u0010%\u001a\u00020\u001fJ4\u0010&\u001a\u00020\u001f2\u000e\b\u0002\u0010'\u001a\b\u0018\u00010\u001aR\u00020\u001b2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u001b2\u000e\b\u0002\u0010)\u001a\b\u0018\u00010\u001aR\u00020\u001bH\u0002J\u0015\u0010*\u001a\u00020+2\u0006\u0010 \u001a\u00020\u001bH\u0000¢\u0006\u0002\b,J\u0019\u0010-\u001a\u00020\u001f2\n\u0010 \u001a\u00060\u001aR\u00020\u001bH\u0000¢\u0006\u0002\b.J\u0015\u0010-\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001bH\u0000¢\u0006\u0002\b.J\f\u0010/\u001a\b\u0012\u0004\u0012\u00020100J\f\u00102\u001a\b\u0012\u0004\u0012\u00020100J\u0006\u00103\u001a\u00020\bJ\u0006\u00104\u001a\u00020\bJ\r\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\b5R$\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR$\u0010\r\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0017R\u0018\u0010\u0018\u001a\f\u0012\b\u0012\u00060\u001aR\u00020\u001b0\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u001c\u001a\f\u0012\b\u0012\u00060\u001aR\u00020\u001b0\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0019X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00066"}, d2 = {"Lokhttp3/Dispatcher;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "executorService", "Ljava/util/concurrent/ExecutorService;", "(Ljava/util/concurrent/ExecutorService;)V", "maxRequests", _UrlKt.FRAGMENT_ENCODE_SET, "getMaxRequests", "()I", "setMaxRequests", "(I)V", "maxRequestsPerHost", "getMaxRequestsPerHost", "setMaxRequestsPerHost", "idleCallback", "Ljava/lang/Runnable;", "getIdleCallback", "()Ljava/lang/Runnable;", "setIdleCallback", "(Ljava/lang/Runnable;)V", "executorServiceOrNull", "()Ljava/util/concurrent/ExecutorService;", "readyAsyncCalls", "Ljava/util/ArrayDeque;", "Lokhttp3/internal/connection/RealCall$AsyncCall;", "Lokhttp3/internal/connection/RealCall;", "runningAsyncCalls", "runningSyncCalls", "enqueue", _UrlKt.FRAGMENT_ENCODE_SET, "call", "enqueue$okhttp", "findExistingCallWithHost", "host", _UrlKt.FRAGMENT_ENCODE_SET, "cancelAll", "promoteAndExecute", "enqueuedCall", "finishedCall", "finishedAsyncCall", "executed", _UrlKt.FRAGMENT_ENCODE_SET, "executed$okhttp", "finished", "finished$okhttp", "queuedCalls", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/Call;", "runningCalls", "queuedCallsCount", "runningCallsCount", "-deprecated_executorService", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDispatcher.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Dispatcher.kt\nokhttp3/Dispatcher\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 -UtilJvm.kt\nokhttp3/internal/_UtilJvmKt\n*L\n1#1,299:1\n1#2:300\n1563#3:301\n1634#3,3:302\n1563#3:306\n1634#3,3:307\n242#4:305\n242#4:310\n*S KotlinDebug\n*F\n+ 1 Dispatcher.kt\nokhttp3/Dispatcher\n*L\n279#1:301\n279#1:302,3\n283#1:306\n283#1:307,3\n279#1:305\n283#1:310\n*E\n"})
/* loaded from: classes.dex */
public final class Dispatcher {
    private ExecutorService executorServiceOrNull;
    private Runnable idleCallback;
    private int maxRequests;
    private int maxRequestsPerHost;
    private final ArrayDeque<RealCall.AsyncCall> readyAsyncCalls;
    private final ArrayDeque<RealCall.AsyncCall> runningAsyncCalls;
    private final ArrayDeque<RealCall> runningSyncCalls;

    @Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B#\u0012\u0010\u0010\u0002\u001a\f\u0012\b\u0012\u00060\u0004R\u00020\u00050\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tR\u001b\u0010\u0002\u001a\f\u0012\b\u0012\u00060\u0004R\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"okhttp3/Dispatcher$promoteAndExecute$Effects", _UrlKt.FRAGMENT_ENCODE_SET, "callsToExecute", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/internal/connection/RealCall$AsyncCall;", "Lokhttp3/internal/connection/RealCall;", "idleCallbackToRun", "Ljava/lang/Runnable;", "<init>", "(Ljava/util/List;Ljava/lang/Runnable;)V", "getCallsToExecute", "()Ljava/util/List;", "getIdleCallbackToRun", "()Ljava/lang/Runnable;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Effects {
        private final List<RealCall.AsyncCall> callsToExecute;
        private final Runnable idleCallbackToRun;

        public Effects(List<RealCall.AsyncCall> r1, Runnable r2) {
            this.callsToExecute = r1;
            this.idleCallbackToRun = r2;
        }

        public final List<RealCall.AsyncCall> getCallsToExecute() {
            return this.callsToExecute;
        }

        public final Runnable getIdleCallbackToRun() {
            return this.idleCallbackToRun;
        }
    }

    public Dispatcher() {
        this.maxRequests = 64;
        this.maxRequestsPerHost = 5;
        this.readyAsyncCalls = new ArrayDeque();
        this.runningAsyncCalls = new ArrayDeque();
        this.runningSyncCalls = new ArrayDeque();
    }

    public Dispatcher(ExecutorService r1) {
        this();
        this.executorServiceOrNull = r1;
    }

    private final RealCall.AsyncCall findExistingCallWithHost(String r4) {
        Iterator<RealCall.AsyncCall> r0 = this.runningAsyncCalls.iterator();
    L4:
        if (r0.hasNext() == false) goto L8;
        RealCall.AsyncCall r1 = r0.next();
        if (Intrinsics.areEqual(r1.getHost(), r4) == false) goto L4;
        return r1;
    L8:
        Iterator<RealCall.AsyncCall> r3 = this.readyAsyncCalls.iterator();
    L10:
        if (r3.hasNext() == false) goto L14;
        RealCall.AsyncCall r02 = r3.next();
        if (Intrinsics.areEqual(r02.getHost(), r4) == false) goto L10;
        return r02;
    L14:
        return null;
    }

    private final void promoteAndExecute(RealCall.AsyncCall r10, RealCall r11, RealCall.AsyncCall r12) {
        _UtilJvmKt.assertLockNotHeld(this);
        boolean r0 = executorService().isShutdown();
        monitor-enter(this);
        if (r11 != null) goto L65;
    L12:
        if (r12 == null) goto L18;
        r12.getCallsPerHost().decrementAndGet();     // Catch: Throwable -> L10
        if (this.runningAsyncCalls.remove(r12) == true) goto L18;
        throw new IllegalStateException("Call wasn't in-flight!");     // Catch: Throwable -> L10
    L18:
        if (r10 == null) goto L25;
        this.readyAsyncCalls.add(r10);     // Catch: Throwable -> L10
        if (r10.getCall().getForWebSocket() == true) goto L25;
        RealCall.AsyncCall r1 = findExistingCallWithHost(r10.getHost());     // Catch: Throwable -> L10
        if (r1 == null) goto L25;
        r10.reuseCallsPerHostFrom(r1);     // Catch: Throwable -> L10
    L25:
        if (r11 != null) goto L27;
        if (r12 != null) goto L27;
    L33:
        Runnable r112 = null;
    L34:
        if (r0 == false) goto L36;
        List r122 = CollectionsKt.toList(this.readyAsyncCalls);     // Catch: Throwable -> L10
        this.readyAsyncCalls.clear();     // Catch: Throwable -> L10
        Effects r2 = new Effects(r122, r112);     // Catch: Throwable -> L10
    L45:
        monitor-exit(this);
        int r113 = r2.getCallsToExecute().size();
        int r4 = 0;
        boolean r5 = true;
    L47:
        if (r4 >= r113) goto L56;
        RealCall.AsyncCall r6 = r2.getCallsToExecute().get(r4);
        if (r6 != r10) goto L51;
        r5 = false;
    L52:
        if (r0 == false) goto L54;
        RealCall.AsyncCall.failRejected$okhttp$default(r6, null, 1, null);
    L55:
        r4 = r4 + 1;
        goto L47
    L54:
        r6.executeOn(executorService());
        goto L55
    L51:
        r6.getCall().getEventListener$okhttp().dispatcherQueueEnd(r6.getCall(), this);
        goto L52
    L56:
        if (r5 == false) goto L59;
        if (r10 == null) goto L59;
        r10.getCall().getEventListener$okhttp().dispatcherQueueStart(r10.getCall(), this);
    L59:
        Runnable r9 = r2.getIdleCallbackToRun();
        if (r9 == null) goto L75;
        r9.run();
        return;
    L75:
        return;
    L36:
        ArrayList r123 = new ArrayList();     // Catch: Throwable -> L10
        Iterator<RealCall.AsyncCall> r22 = this.readyAsyncCalls.iterator();     // Catch: Throwable -> L10
    L38:
        if (r22.hasNext() == false) goto L44;
        RealCall.AsyncCall r3 = r22.next();     // Catch: Throwable -> L10
        if (this.runningAsyncCalls.size() >= this.maxRequests) goto L44;
        if (r3.getCallsPerHost().get() >= this.maxRequestsPerHost) goto L38;
        r22.remove();     // Catch: Throwable -> L10
        r3.getCallsPerHost().incrementAndGet();     // Catch: Throwable -> L10
        r123.add(r3);     // Catch: Throwable -> L10
        this.runningAsyncCalls.add(r3);     // Catch: Throwable -> L10
    L44:
        r2 = new Effects(r123, r112);     // Catch: Throwable -> L10
    L27:
        if (r0 == true) goto L31;
        if (this.runningAsyncCalls.isEmpty() == false) goto L33;
    L31:
        if (this.runningSyncCalls.isEmpty() == false) goto L33;
        r112 = this.idleCallback;     // Catch: Throwable -> L10
        goto L34
    L65:
    L10:
        th = move-exception;
        throw th;
    L6:
        if (this.runningSyncCalls.remove(r11) == true) goto L12;
        throw new IllegalStateException("Call wasn't in-flight!");     // Catch: Throwable -> L10
    }

    public static /* synthetic */ void promoteAndExecute$default(Dispatcher r1, RealCall.AsyncCall r2, RealCall r3, RealCall.AsyncCall r4, int r5, Object r6) {
        if ((r5 & 1) == 0) goto L6;
        r2 = null;
    L6:
        if ((r5 & 2) == 0) goto L9;
        r3 = null;
    L9:
        if ((r5 & 4) == 0) goto L11;
        r4 = null;
    L11:
        r1.promoteAndExecute(r2, r3, r4);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "executorService", imports = {}))
    @JvmName(name = "-deprecated_executorService")
    /* renamed from: -deprecated_executorService, reason: not valid java name */
    public final ExecutorService m3641deprecated_executorService() {
        return executorService();
    }

    public final synchronized void cancelAll() {
        monitor-enter(this);
        Iterator<RealCall.AsyncCall> r0 = this.readyAsyncCalls.iterator();     // Catch: Throwable -> L7
    L5:
        if (r0.hasNext() == false) goto L9;
        r0.next().getCall().cancel();     // Catch: Throwable -> L7
        goto L5
    L9:
        Iterator<RealCall.AsyncCall> r02 = this.runningAsyncCalls.iterator();     // Catch: Throwable -> L7
    L11:
        if (r02.hasNext() == false) goto L13;
        r02.next().getCall().cancel();     // Catch: Throwable -> L7
        goto L11
    L13:
        Iterator<RealCall> r03 = this.runningSyncCalls.iterator();     // Catch: Throwable -> L7
    L14:
        if (r03.hasNext() == false) goto L18;
        r03.next().cancel();     // Catch: Throwable -> L7
        goto L14
    L18:
        monitor-exit(this);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public final void enqueue$okhttp(RealCall.AsyncCall r7) {
        promoteAndExecute$default(this, r7, null, null, 6, null);
    }

    public final synchronized boolean executed$okhttp(RealCall r2) {
        monitor-enter(this);
        boolean r22 = this.runningSyncCalls.add(r2);     // Catch: Throwable -> L6
        monitor-exit(this);
        return r22;
    L6:
        th = move-exception;
        throw th;
    }

    @JvmName(name = "executorService")
    public final synchronized ExecutorService executorService() {
        monitor-enter(this);
    L6:
        th = move-exception;
        throw th;
    L4:
        if (this.executorServiceOrNull != null) goto L8;
        this.executorServiceOrNull = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), _UtilJvmKt.threadFactory(_UtilJvmKt.okHttpName + " Dispatcher", false));     // Catch: Throwable -> L6
    L8:
        ExecutorService r0 = this.executorServiceOrNull;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    }

    public final void finished$okhttp(RealCall.AsyncCall r7) {
        promoteAndExecute$default(this, null, null, r7, 3, null);
    }

    public final void finished$okhttp(RealCall r7) {
        promoteAndExecute$default(this, null, r7, null, 5, null);
    }

    public final synchronized Runnable getIdleCallback() {
        monitor-enter(this);
        Runnable r0 = this.idleCallback;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public final synchronized int getMaxRequests() {
        monitor-enter(this);
        int r0 = this.maxRequests;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public final synchronized int getMaxRequestsPerHost() {
        monitor-enter(this);
        int r0 = this.maxRequestsPerHost;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public final synchronized List<Call> queuedCalls() {
        monitor-enter(this);
        ArrayDeque<RealCall.AsyncCall> r0 = this.readyAsyncCalls;     // Catch: Throwable -> L7
        ArrayList r1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r0, 10));     // Catch: Throwable -> L7
        Iterator<T> r02 = r0.iterator();     // Catch: Throwable -> L7
    L5:
        if (r02.hasNext() == false) goto L9;
        r1.add(((RealCall.AsyncCall) r02.next()).getCall());     // Catch: Throwable -> L7
        goto L5
    L9:
        List<Call> r03 = Collections.unmodifiableList(r1);     // Catch: Throwable -> L7
        monitor-exit(this);
        return r03;
    L7:
        th = move-exception;
        throw th;
    }

    public final synchronized int queuedCallsCount() {
        monitor-enter(this);
        int r0 = this.readyAsyncCalls.size();     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public final synchronized List<Call> runningCalls() {
        monitor-enter(this);
        ArrayDeque<RealCall> r0 = this.runningSyncCalls;     // Catch: Throwable -> L7
        ArrayDeque<RealCall.AsyncCall> r1 = this.runningAsyncCalls;     // Catch: Throwable -> L7
        ArrayList r2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r1, 10));     // Catch: Throwable -> L7
        Iterator<T> r12 = r1.iterator();     // Catch: Throwable -> L7
    L5:
        if (r12.hasNext() == false) goto L9;
        r2.add(((RealCall.AsyncCall) r12.next()).getCall());     // Catch: Throwable -> L7
        goto L5
    L9:
        List<Call> r02 = Collections.unmodifiableList(CollectionsKt.plus(r0, r2));     // Catch: Throwable -> L7
        monitor-exit(this);
        return r02;
    L7:
        th = move-exception;
        throw th;
    }

    public final synchronized int runningCallsCount() {
        monitor-enter(this);
        int r0 = this.runningAsyncCalls.size() + this.runningSyncCalls.size();
        monitor-exit(this);
        return r0;
    L7:
        th = move-exception;
        throw th;
    }

    public final synchronized void setIdleCallback(Runnable r1) {
        monitor-enter(this);
        this.idleCallback = r1;     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public final void setMaxRequests(int r8) {
        if (r8 < 1) goto L13;
        monitor-enter(this);
        this.maxRequests = r8;     // Catch: Throwable -> L9
        Unit r82 = Unit.INSTANCE;     // Catch: Throwable -> L9
        monitor-exit(this);
        promoteAndExecute$default(this, null, null, null, 7, null);
        return;
    L9:
        th = move-exception;
        throw th;
    L13:
        Utf8$$ExternalSyntheticBUOutline1.m("max < 1: ", r8);
    }

    public final void setMaxRequestsPerHost(int r8) {
        if (r8 < 1) goto L13;
        monitor-enter(this);
        this.maxRequestsPerHost = r8;     // Catch: Throwable -> L9
        Unit r82 = Unit.INSTANCE;     // Catch: Throwable -> L9
        monitor-exit(this);
        promoteAndExecute$default(this, null, null, null, 7, null);
        return;
    L9:
        th = move-exception;
        throw th;
    L13:
        Utf8$$ExternalSyntheticBUOutline1.m("max < 1: ", r8);
    }
}
