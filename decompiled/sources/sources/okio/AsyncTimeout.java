package okio;

import com.google.android.gms.cast.MediaStatus;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u0000 %2\u00020\u0001:\u0002$%B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\u000fJ\b\u0010\u0010\u001a\u00020\rH\u0016J\u0015\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\bH\u0000¢\u0006\u0002\b\u0013J\u0017\u0010\u0014\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\bH\u0000¢\u0006\u0002\b\u0015J\b\u0010\u0016\u001a\u00020\rH\u0014J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u001aJ%\u0010\u001b\u001a\u0002H\u001c\"\u0004\b\u0000\u0010\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u001c0\u001eH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u001fJ\u0012\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010!H\u0001J\u0012\u0010#\u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010!H\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u00020\u00058\u0000@\u0000X\u0081\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006&"}, d2 = {"Lokio/AsyncTimeout;", "Lokio/Timeout;", "<init>", "()V", "state", _UrlKt.FRAGMENT_ENCODE_SET, "index", "value", _UrlKt.FRAGMENT_ENCODE_SET, "timeoutAt", "getTimeoutAt$okio", "()J", "enter", _UrlKt.FRAGMENT_ENCODE_SET, "exit", _UrlKt.FRAGMENT_ENCODE_SET, "cancel", "remainingNanos", "now", "remainingNanos$okio", "setTimeoutAt", "setTimeoutAt$okio", "timedOut", "sink", "Lokio/Sink;", "source", "Lokio/Source;", "withTimeout", "T", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "access$newTimeoutException", "Ljava/io/IOException;", "cause", "newTimeoutException", "Watchdog", "Companion", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAsyncTimeout.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AsyncTimeout.kt\nokio/AsyncTimeout\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,514:1\n1#2:515\n*E\n"})
/* loaded from: classes.dex */
public class AsyncTimeout extends Timeout {
    private static final Companion Companion = null;
    private static final long IDLE_TIMEOUT_MILLIS = 0;
    private static final long IDLE_TIMEOUT_NANOS = 0;
    private static final int STATE_CANCELED = 3;
    private static final int STATE_IDLE = 0;
    private static final int STATE_IN_QUEUE = 1;
    private static final int STATE_TIMED_OUT = 2;
    private static final int TIMEOUT_WRITE_SIZE = 65536;
    private static final Condition condition = null;
    private static AsyncTimeout idleSentinel;
    private static final ReentrantLock lock = null;
    private static final PriorityQueue queue = null;

    @JvmField
    public int index;
    private int state;
    private long timeoutAt;

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\tH\u0002J\b\u0010\"\u001a\u0004\u0018\u00010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0017X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0017X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0017X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0017X\u0082T¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lokio/AsyncTimeout$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "queue", "Lokio/PriorityQueue;", "getQueue", "()Lokio/PriorityQueue;", "idleSentinel", "Lokio/AsyncTimeout;", "getIdleSentinel", "()Lokio/AsyncTimeout;", "setIdleSentinel", "(Lokio/AsyncTimeout;)V", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "getLock", "()Ljava/util/concurrent/locks/ReentrantLock;", "condition", "Ljava/util/concurrent/locks/Condition;", "getCondition", "()Ljava/util/concurrent/locks/Condition;", "TIMEOUT_WRITE_SIZE", _UrlKt.FRAGMENT_ENCODE_SET, "IDLE_TIMEOUT_MILLIS", _UrlKt.FRAGMENT_ENCODE_SET, "IDLE_TIMEOUT_NANOS", "STATE_IDLE", "STATE_IN_QUEUE", "STATE_TIMED_OUT", "STATE_CANCELED", "insertIntoQueue", _UrlKt.FRAGMENT_ENCODE_SET, "node", "awaitTimeout", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static final /* synthetic */ void access$insertIntoQueue(Companion r0, AsyncTimeout r1) {
            r0.insertIntoQueue(r1);
        }

        private final void insertIntoQueue(AsyncTimeout r5) {
            if (getIdleSentinel() != null) goto L5;
            setIdleSentinel(new AsyncTimeout());
            new Watchdog().start();
        L5:
            AsyncTimeout.setTimeoutAt$okio$default(r5, 0, 1, null);
            getQueue().add(r5);
            if (r5.index != 1) goto L9;
            getCondition().signal();
            return;
        }

        public final AsyncTimeout awaitTimeout() {
            AsyncTimeout r0 = getQueue().first();
            if (r0 != null) goto L11;
            long r2 = System.nanoTime();
            getCondition().await(AsyncTimeout.access$getIDLE_TIMEOUT_MILLIS$cp(), TimeUnit.MILLISECONDS);
            if (getQueue().first() == null) goto L7;
        L10:
            return null;
        L7:
            if ((System.nanoTime() - r2) < AsyncTimeout.access$getIDLE_TIMEOUT_NANOS$cp()) goto L10;
            return getIdleSentinel();
        L11:
            long r22 = r0.remainingNanos$okio(System.nanoTime());
            if (r22 <= 0) goto L15;
            getCondition().await(r22, TimeUnit.NANOSECONDS);
            return null;
        L15:
            getQueue().remove(r0);
            AsyncTimeout.access$setState$p(r0, 2);
            return r0;
        }

        public final Condition getCondition() {
            return AsyncTimeout.access$getCondition$cp();
        }

        public final AsyncTimeout getIdleSentinel() {
            return AsyncTimeout.access$getIdleSentinel$cp();
        }

        public final ReentrantLock getLock() {
            return AsyncTimeout.access$getLock$cp();
        }

        public final PriorityQueue getQueue() {
            return AsyncTimeout.access$getQueue$cp();
        }

        public final void setIdleSentinel(AsyncTimeout r1) {
            AsyncTimeout.access$setIdleSentinel$cp(r1);
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lokio/AsyncTimeout$Watchdog;", "Ljava/lang/Thread;", "<init>", "()V", "run", _UrlKt.FRAGMENT_ENCODE_SET, "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Watchdog extends Thread {
        public Watchdog() {
            super("Okio Watchdog");
            setDaemon(true);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
        L17:
            ReentrantLock r2 = AsyncTimeout.access$getCompanion$p().getLock();     // Catch: InterruptedException -> L16
            r2.lock();     // Catch: InterruptedException -> L16
            AsyncTimeout r0 = AsyncTimeout.access$getCompanion$p().awaitTimeout();     // Catch: Throwable -> L8
            if (r0 != AsyncTimeout.access$getCompanion$p().getIdleSentinel()) goto L10;
            AsyncTimeout.access$getCompanion$p().setIdleSentinel(null);     // Catch: Throwable -> L8
            goto L17
        L6:
            r2.unlock();     // Catch: InterruptedException -> L16
            return;
        L10:
            Unit r1 = Unit.INSTANCE;     // Catch: Throwable -> L8
            r2.unlock();     // Catch: InterruptedException -> L16
            if (r0 == null) goto L17;
            r0.timedOut();     // Catch: InterruptedException -> L16
        L8:
            th = move-exception;
            r2.unlock();     // Catch: InterruptedException -> L16
            throw th;     // Catch: InterruptedException -> L16
        }
    }

    @Metadata(d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\u0003H\u0016J\b\u0010\t\u001a\u00020\u0003H\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"okio/AsyncTimeout$sink$1", "Lokio/Sink;", "write", _UrlKt.FRAGMENT_ENCODE_SET, "source", "Lokio/Buffer;", "byteCount", _UrlKt.FRAGMENT_ENCODE_SET, "flush", "close", "timeout", "Lokio/AsyncTimeout;", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nAsyncTimeout.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AsyncTimeout.kt\nokio/AsyncTimeout$sink$1\n+ 2 AsyncTimeout.kt\nokio/AsyncTimeout\n*L\n1#1,514:1\n195#2,11:515\n195#2,11:526\n195#2,11:537\n*S KotlinDebug\n*F\n+ 1 AsyncTimeout.kt\nokio/AsyncTimeout$sink$1\n*L\n151#1:515,11\n157#1:526,11\n161#1:537,11\n*E\n"})
    /* renamed from: okio.AsyncTimeout$sink$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static final class AnonymousClass1 implements Sink {
        final /* synthetic */ Sink $sink;
        final /* synthetic */ AsyncTimeout this$0;

        public AnonymousClass1(AsyncTimeout r1, Sink r2) {
            this.this$0 = r1;
            this.$sink = r2;
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            AsyncTimeout r0 = this.this$0;
            Sink r2 = this.$sink;
            r0.enter();
            r2.close();     // Catch: Throwable -> L9 IOException -> L11
            Unit r22 = Unit.INSTANCE;     // Catch: Throwable -> L9 IOException -> L11
        L5:
            if (r0.exit() == true) goto L8;
            return;
        L8:
            throw r0.access$newTimeoutException(null);
        L9:
            th = move-exception;
            r0.exit();
            throw th;
        L11:
            e = move-exception;
            if (r0.exit() == true) goto L16;
            throw e;     // Catch: Throwable -> L9
        L16:
            throw r0.access$newTimeoutException(e);     // Catch: Throwable -> L9
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() {
            AsyncTimeout r0 = this.this$0;
            Sink r2 = this.$sink;
            r0.enter();
            r2.flush();     // Catch: Throwable -> L9 IOException -> L11
            Unit r22 = Unit.INSTANCE;     // Catch: Throwable -> L9 IOException -> L11
        L5:
            if (r0.exit() == true) goto L8;
            return;
        L8:
            throw r0.access$newTimeoutException(null);
        L9:
            th = move-exception;
            r0.exit();
            throw th;
        L11:
            e = move-exception;
            if (r0.exit() == true) goto L16;
            throw e;     // Catch: Throwable -> L9
        L16:
            throw r0.access$newTimeoutException(e);     // Catch: Throwable -> L9
        }

        @Override // okio.Sink
        public AsyncTimeout timeout() {
            return this.this$0;
        }

        @Override // okio.Sink
        public /* bridge */ /* synthetic */ Timeout timeout() {
            return timeout();
        }

        public String toString() {
            return "AsyncTimeout.sink(" + this.$sink + ')';
        }

        @Override // okio.Sink
        public void write(Buffer r7, long r8) {
            SegmentedByteString.checkOffsetAndCount(r7.size(), 0, r8);
        L3:
            long r0 = 0;
            if (r8 <= 0) goto L30;
            Segment r2 = r7.head;
        L7:
            if (r0 >= MediaStatus.COMMAND_FOLLOW) goto L12;
            r0 = r0 + (r2.limit - r2.pos);
            if (r0 >= r8) goto L10;
            r2 = r2.next;
            goto L7
        L10:
            r0 = r8;
        L12:
            AsyncTimeout r22 = this.this$0;
            Sink r3 = this.$sink;
            r22.enter();
            r3.write(r7, r0);     // Catch: Throwable -> L19 IOException -> L21
            Unit r32 = Unit.INSTANCE;     // Catch: Throwable -> L19 IOException -> L21
        L15:
            if (r22.exit() == true) goto L18;
            r8 = r8 - r0;
            goto L3
        L18:
            throw r22.access$newTimeoutException(null);
        L19:
            th = move-exception;
            r22.exit();
            throw th;
        L21:
            e = move-exception;
            if (r22.exit() == true) goto L27;
            throw e;     // Catch: Throwable -> L19
        L27:
            throw r22.access$newTimeoutException(e);     // Catch: Throwable -> L19
        }
    }

    @Metadata(d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0003H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"okio/AsyncTimeout$source$1", "Lokio/Source;", "read", _UrlKt.FRAGMENT_ENCODE_SET, "sink", "Lokio/Buffer;", "byteCount", "close", _UrlKt.FRAGMENT_ENCODE_SET, "timeout", "Lokio/AsyncTimeout;", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nAsyncTimeout.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AsyncTimeout.kt\nokio/AsyncTimeout$source$1\n+ 2 AsyncTimeout.kt\nokio/AsyncTimeout\n*L\n1#1,514:1\n195#2,11:515\n195#2,11:526\n*S KotlinDebug\n*F\n+ 1 AsyncTimeout.kt\nokio/AsyncTimeout$source$1\n*L\n177#1:515,11\n181#1:526,11\n*E\n"})
    /* renamed from: okio.AsyncTimeout$source$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes5.dex */
    public static final class C01911 implements Source {
        final /* synthetic */ Source $source;
        final /* synthetic */ AsyncTimeout this$0;

        public C01911(AsyncTimeout r1, Source r2) {
            this.this$0 = r1;
            this.$source = r2;
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            AsyncTimeout r0 = this.this$0;
            Source r2 = this.$source;
            r0.enter();
            r2.close();     // Catch: Throwable -> L9 IOException -> L11
            Unit r22 = Unit.INSTANCE;     // Catch: Throwable -> L9 IOException -> L11
        L5:
            if (r0.exit() == true) goto L8;
            return;
        L8:
            throw r0.access$newTimeoutException(null);
        L9:
            th = move-exception;
            r0.exit();
            throw th;
        L11:
            e = move-exception;
            if (r0.exit() == true) goto L16;
            throw e;     // Catch: Throwable -> L9
        L16:
            throw r0.access$newTimeoutException(e);     // Catch: Throwable -> L9
        }

        @Override // okio.Source
        public long read(Buffer r2, long r3) {
            AsyncTimeout r0 = this.this$0;
            Source r1 = this.$source;
            r0.enter();
            long r12 = r1.read(r2, r3);     // Catch: Throwable -> L9 IOException -> L11
        L5:
            if (r0.exit() == true) goto L8;
            return r12;
        L8:
            throw r0.access$newTimeoutException(null);
        L11:
            e = move-exception;
            if (r0.exit() == true) goto L16;
            throw e;     // Catch: Throwable -> L9
        L16:
            throw r0.access$newTimeoutException(e);     // Catch: Throwable -> L9
        L9:
            th = move-exception;
            r0.exit();
            throw th;
        }

        @Override // okio.Source
        public AsyncTimeout timeout() {
            return this.this$0;
        }

        @Override // okio.Source
        public /* bridge */ /* synthetic */ Timeout timeout() {
            return timeout();
        }

        public String toString() {
            return "AsyncTimeout.source(" + this.$source + ')';
        }
    }

    static {
        Companion = new Companion(null);
        queue = new PriorityQueue();
        ReentrantLock r0 = new ReentrantLock();
        lock = r0;
        condition = r0.newCondition();
        IDLE_TIMEOUT_MILLIS = 60000;
        IDLE_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(60000);
    }

    public AsyncTimeout() {
        this.index = -1;
    }

    public static final /* synthetic */ Companion access$getCompanion$p() {
        return Companion;
    }

    public static final /* synthetic */ Condition access$getCondition$cp() {
        return condition;
    }

    public static final /* synthetic */ long access$getIDLE_TIMEOUT_MILLIS$cp() {
        return IDLE_TIMEOUT_MILLIS;
    }

    public static final /* synthetic */ long access$getIDLE_TIMEOUT_NANOS$cp() {
        return IDLE_TIMEOUT_NANOS;
    }

    public static final /* synthetic */ AsyncTimeout access$getIdleSentinel$cp() {
        return idleSentinel;
    }

    public static final /* synthetic */ ReentrantLock access$getLock$cp() {
        return lock;
    }

    public static final /* synthetic */ PriorityQueue access$getQueue$cp() {
        return queue;
    }

    public static final /* synthetic */ void access$setIdleSentinel$cp(AsyncTimeout r0) {
        idleSentinel = r0;
    }

    public static final /* synthetic */ void access$setState$p(AsyncTimeout r0, int r1) {
        r0.state = r1;
    }

    public static /* synthetic */ void setTimeoutAt$okio$default(AsyncTimeout r0, long r1, int r3, Object r4) {
        if (r4 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: setTimeoutAt");
        return;
    L4:
        if ((r3 & 1) == 0) goto L6;
        r1 = System.nanoTime();
    L6:
        r0.setTimeoutAt$okio(r1);
    }

    @PublishedApi
    public final IOException access$newTimeoutException(IOException r1) {
        return newTimeoutException(r1);
    }

    @Override // okio.Timeout
    public void cancel() {
        super.cancel();
        ReentrantLock r0 = lock;
        r0.lock();
    L6:
        th = move-exception;
        r0.unlock();
        throw th;
    L4:
        if (this.state != 1) goto L8;
        queue.remove(this);     // Catch: Throwable -> L6
        this.state = 3;     // Catch: Throwable -> L6
    L8:
        Unit r3 = Unit.INSTANCE;     // Catch: Throwable -> L6
        r0.unlock();
    }

    public final void enter() {
        long r0 = timeoutNanos();
        boolean r2 = hasDeadline();
        if (r0 != 0) goto L6;
        if (r2 == true) goto L6;
        return;
    L6:
        ReentrantLock r02 = lock;
        r02.lock();
    L12:
        th = move-exception;
        r02.unlock();
        throw th;
    L8:
        if (this.state != 0) goto L15;
        this.state = 1;     // Catch: Throwable -> L12
        Companion.access$insertIntoQueue(Companion, this);     // Catch: Throwable -> L12
        Unit r5 = Unit.INSTANCE;     // Catch: Throwable -> L12
        r02.unlock();
        return;
    L15:
        throw new IllegalStateException("Unbalanced enter/exit");     // Catch: Throwable -> L12
    }

    public final boolean exit() {
        ReentrantLock r0 = lock;
        r0.lock();
        int r1 = this.state;     // Catch: Throwable -> L8
        boolean r2 = false;
        this.state = 0;     // Catch: Throwable -> L8
        if (r1 != 1) goto L11;
        queue.remove(this);     // Catch: Throwable -> L8
        r0.unlock();
        return false;
    L11:
        if (r1 != 2) goto L13;
        r2 = true;
    L13:
        r0.unlock();
        return r2;
    L8:
        th = move-exception;
        r0.unlock();
        throw th;
    }

    public final long getTimeoutAt$okio() {
        return this.timeoutAt;
    }

    public IOException newTimeoutException(IOException r2) {
        InterruptedIOException r1 = new InterruptedIOException("timeout");
        if (r2 == null) goto L5;
        r1.initCause(r2);
    L5:
        return r1;
    }

    public final long remainingNanos$okio(long r3) {
        return this.timeoutAt - r3;
    }

    public final void setTimeoutAt$okio(long r8) {
        long r0 = timeoutNanos();
        boolean r2 = hasDeadline();
        if (timeoutNanos() == 0) goto L9;
        if (hasDeadline() == false) goto L9;
        this.timeoutAt = r8 + Math.min(r0, deadlineNanoTime() - r8);
        return;
    L9:
        if (r0 == 0) goto L12;
        this.timeoutAt = r8 + r0;
        return;
    L12:
        if (r2 == false) goto L15;
        this.timeoutAt = deadlineNanoTime();
        return;
    L15:
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
    }

    public final Sink sink(Sink r2) {
        return new AnonymousClass1(this, r2);
    }

    public final Source source(Source r2) {
        return new C01911(this, r2);
    }

    public void timedOut() {
    }

    public final <T> T withTimeout(Function0<? extends T> r3) {
        enter();
        T r32 = r3.invoke();     // Catch: Throwable -> L10 IOException -> L12
        InlineMarker.finallyStart(1);
        if (exit() == true) goto L9;
        InlineMarker.finallyEnd(1);
        return r32;
    L9:
        throw access$newTimeoutException(null);
    L10:
        th = move-exception;
        InlineMarker.finallyStart(1);
        exit();
        InlineMarker.finallyEnd(1);
        throw th;
    L12:
        e = move-exception;
        if (exit() == true) goto L17;
        throw e;     // Catch: Throwable -> L10
    L17:
        throw access$newTimeoutException(e);     // Catch: Throwable -> L10
    }
}
