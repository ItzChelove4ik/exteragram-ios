package okhttp3.internal.connection;

import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.connection.RoutePlanner;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\n\u0010\u0016\u001a\u0004\u0018\u00010\u0012H\u0002J\u001a\u0010\u0017\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0002R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\u0010\u0012\f\u0012\n \u0013*\u0004\u0018\u00010\u00120\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lokhttp3/internal/connection/FastFallbackExchangeFinder;", "Lokhttp3/internal/connection/ExchangeFinder;", "routePlanner", "Lokhttp3/internal/connection/RoutePlanner;", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "<init>", "(Lokhttp3/internal/connection/RoutePlanner;Lokhttp3/internal/concurrent/TaskRunner;)V", "getRoutePlanner", "()Lokhttp3/internal/connection/RoutePlanner;", "connectDelayNanos", _UrlKt.FRAGMENT_ENCODE_SET, "nextTcpConnectAtNanos", "tcpConnectsInFlight", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lokhttp3/internal/connection/RoutePlanner$Plan;", "connectResults", "Ljava/util/concurrent/BlockingQueue;", "Lokhttp3/internal/connection/RoutePlanner$ConnectResult;", "kotlin.jvm.PlatformType", "find", "Lokhttp3/internal/connection/RealConnection;", "launchTcpConnect", "awaitTcpConnect", "timeout", "unit", "Ljava/util/concurrent/TimeUnit;", "cancelInFlightConnects", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class FastFallbackExchangeFinder implements ExchangeFinder {
    private final long connectDelayNanos;
    private final BlockingQueue<RoutePlanner.ConnectResult> connectResults;
    private long nextTcpConnectAtNanos;
    private final RoutePlanner routePlanner;
    private final TaskRunner taskRunner;
    private final CopyOnWriteArrayList<RoutePlanner.Plan> tcpConnectsInFlight;

    @Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"okhttp3/internal/connection/FastFallbackExchangeFinder$launchTcpConnect$1", "Lokhttp3/internal/concurrent/Task;", "runOnce", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: okhttp3.internal.connection.FastFallbackExchangeFinder$launchTcpConnect$1, reason: invalid class name */
    public static final class AnonymousClass1 extends Task {
        final /* synthetic */ RoutePlanner.Plan $plan;
        final /* synthetic */ FastFallbackExchangeFinder this$0;

        public AnonymousClass1(String r2, RoutePlanner.Plan r3, FastFallbackExchangeFinder r4) {
            this.$plan = r3;
            this.this$0 = r4;
            super(r2, false, 2, null);
        }

        @Override // okhttp3.internal.concurrent.Task
        public long runOnce() {
            RoutePlanner.ConnectResult r0 = this.$plan.mo3750connectTcp();     // Catch: Throwable -> L4
        L7:
            if (FastFallbackExchangeFinder.access$getTcpConnectsInFlight$p(this.this$0).contains(this.$plan) == false) goto L13;
            FastFallbackExchangeFinder.access$getConnectResults$p(this.this$0).put(r0);
            return -1;
        L13:
            return -1;
        L4:
            th = move-exception;
            r0 = new RoutePlanner.ConnectResult(this.$plan, null, th, 2, null);
            goto L7
        }
    }

    public FastFallbackExchangeFinder(RoutePlanner r3, TaskRunner r4) {
        this.routePlanner = r3;
        this.taskRunner = r4;
        this.connectDelayNanos = 250000000;
        this.nextTcpConnectAtNanos = Long.MIN_VALUE;
        this.tcpConnectsInFlight = new CopyOnWriteArrayList();
        this.connectResults = r4.getBackend().decorate(new LinkedBlockingDeque());
    }

    public static final /* synthetic */ BlockingQueue access$getConnectResults$p(FastFallbackExchangeFinder r0) {
        return r0.connectResults;
    }

    public static final /* synthetic */ CopyOnWriteArrayList access$getTcpConnectsInFlight$p(FastFallbackExchangeFinder r0) {
        return r0.tcpConnectsInFlight;
    }

    private final RoutePlanner.ConnectResult awaitTcpConnect(long r3, TimeUnit r5) {
        if (this.tcpConnectsInFlight.isEmpty() == false) goto L5;
        return null;
    L5:
        RoutePlanner.ConnectResult r32 = this.connectResults.poll(r3, r5);
        if (r32 != null) goto L8;
        return null;
    L8:
        this.tcpConnectsInFlight.remove(r32.getPlan());
        return r32;
    }

    private final void cancelInFlightConnects() {
        Iterator<RoutePlanner.Plan> r0 = this.tcpConnectsInFlight.iterator();
    L4:
        if (r0.hasNext() == false) goto L9;
        RoutePlanner.Plan r1 = r0.next();
        r1.mo3746cancel();
        RoutePlanner.Plan r12 = r1.mo3748retry();
        if (r12 == null) goto L4;
        getRoutePlanner().getDeferredPlans().addLast(r12);
        goto L4
    L9:
        this.tcpConnectsInFlight.clear();
    }

    private final RoutePlanner.ConnectResult launchTcpConnect() {
        if (RoutePlanner.hasNext$default(getRoutePlanner(), null, 1, null) == true) goto L18;
    L17:
        return null;
    L18:
        RoutePlanner.Plan r4 = getRoutePlanner().plan();     // Catch: Throwable -> L6
    L9:
        if (r4.isReady() == false) goto L13;
        return new RoutePlanner.ConnectResult(r4, null, null, 6, null);
    L13:
        if ((r4 instanceof FailedPlan) == true) goto L15;
        this.tcpConnectsInFlight.add(r4);
        TaskQueue.schedule$default(this.taskRunner.newQueue(), new AnonymousClass1(_UtilJvmKt.okHttpName + " connect " + getRoutePlanner().getAddress().url().redact(), r4, this), 0, 2, null);
        goto L17
    L15:
        return ((FailedPlan) r4).getResult();
    L6:
        th = move-exception;
        r4 = new FailedPlan(th);
        goto L9
    }

    @Override // okhttp3.internal.connection.ExchangeFinder
    public RealConnection find() {
        IOException r1 = null;
    L50:
    L10:
        th = move-exception;
        cancelInFlightConnects();
        throw th;
    L4:
        if (this.tcpConnectsInFlight.isEmpty() == false) goto L13;
        if (RoutePlanner.hasNext$default(getRoutePlanner(), null, 1, null) == true) goto L13;
        cancelInFlightConnects();
        throw r1;
    L13:
        if (getRoutePlanner().isCanceled() == true) goto L47;
        long r2 = this.taskRunner.getBackend().nanoTime();     // Catch: Throwable -> L10
        long r4 = this.nextTcpConnectAtNanos - r2;     // Catch: Throwable -> L10
        if (this.tcpConnectsInFlight.isEmpty() == false) goto L17;
    L20:
        RoutePlanner.ConnectResult r42 = launchTcpConnect();     // Catch: Throwable -> L10
        long r5 = this.connectDelayNanos;     // Catch: Throwable -> L10
        this.nextTcpConnectAtNanos = r2 + r5;     // Catch: Throwable -> L10
    L21:
        if (r42 != null) goto L26;
        r42 = awaitTcpConnect(r5, TimeUnit.NANOSECONDS);     // Catch: Throwable -> L10
        if (r42 == null) goto L50;
    L26:
        if (r42.isSuccess() == false) goto L35;
        cancelInFlightConnects();     // Catch: Throwable -> L10
        if (r42.getPlan().isReady() == true) goto L31;
        r42 = r42.getPlan().mo3751connectTlsEtc();     // Catch: Throwable -> L10
    L31:
        if (r42.isSuccess() == false) goto L35;
        RealConnection r0 = r42.getPlan().mo3747handleSuccess();     // Catch: Throwable -> L10
        cancelInFlightConnects();
        return r0;
    L35:
        Throwable r22 = r42.getThrowable();     // Catch: Throwable -> L10
        if (r22 != null) goto L38;
    L43:
        RoutePlanner.Plan r23 = r42.getNextPlan();     // Catch: Throwable -> L10
        if (r23 == null) goto L50;
        getRoutePlanner().getDeferredPlans().addFirst(r23);     // Catch: Throwable -> L10
        goto L50
    L38:
        if ((r22 instanceof IOException) == false) goto L42;
        if (r1 != null) goto L41;
        r1 = (IOException) r22;     // Catch: Throwable -> L10
        goto L43
    L41:
        ExceptionsKt.addSuppressed(r1, r22);     // Catch: Throwable -> L10
        goto L43
    L42:
        throw r22;     // Catch: Throwable -> L10
    L17:
        if (r4 <= 0) goto L20;
        r5 = r4;
        r42 = null;
        goto L21
    L47:
        throw new IOException("Canceled");     // Catch: Throwable -> L10
    }

    @Override // okhttp3.internal.connection.ExchangeFinder
    public RoutePlanner getRoutePlanner() {
        return this.routePlanner;
    }
}
