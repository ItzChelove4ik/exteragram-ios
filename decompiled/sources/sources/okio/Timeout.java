package okio;

import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\n\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\b\u001a\u00020\u0007H\u0016J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0016\u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fJ\b\u0010\u000f\u001a\u00020\u0000H\u0016J\b\u0010\u0010\u001a\u00020\u0000H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0012H\u0016J\u0010\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0001H\u0016J-\u0010\u0019\u001a\u0002H\u001a\"\u0004\b\u0000\u0010\u001a2\u0006\u0010\u001b\u001a\u00020\u00002\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u001a0\u001dH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u001eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006 "}, d2 = {"Lokio/Timeout;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "hasDeadline", _UrlKt.FRAGMENT_ENCODE_SET, "deadlineNanoTime", _UrlKt.FRAGMENT_ENCODE_SET, "timeoutNanos", "cancelMark", "timeout", "unit", "Ljava/util/concurrent/TimeUnit;", "deadline", "duration", "clearTimeout", "clearDeadline", "throwIfReached", _UrlKt.FRAGMENT_ENCODE_SET, "cancel", "awaitSignal", "condition", "Ljava/util/concurrent/locks/Condition;", "waitUntilNotified", "monitor", "intersectWith", "T", "other", "block", "Lkotlin/Function0;", "(Lokio/Timeout;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "Companion", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nTimeout.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Timeout.kt\nokio/Timeout\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,358:1\n1#2:359\n*E\n"})
/* loaded from: classes.dex */
public class Timeout {
    public static final Companion Companion = null;

    @JvmField
    public static final Timeout NONE = null;
    private volatile Object cancelMark;
    private long deadlineNanoTime;
    private boolean hasDeadline;
    private long timeoutNanos;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bR\u0014\u0010\n\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lokio/Timeout$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "aNanos", "bNanos", "minTimeout", "(JJ)J", "Lokio/Timeout;", "NONE", "Lokio/Timeout;", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final long minTimeout(long r3, long r5) {
            if (r3 != 0) goto L6;
        L11:
            return r5;
        L6:
            if (r5 != 0) goto L9;
        L10:
            return r3;
        L9:
            if (r3 >= r5) goto L11;
            goto L10
        }
    }

    static {
        Companion = new Companion(null);
        NONE = new Timeout$Companion$NONE$1();
    }

    public Timeout() {
    }

    public void awaitSignal(Condition r10) {
        boolean r0 = hasDeadline();     // Catch: InterruptedException -> L27
        long r1 = timeoutNanos();     // Catch: InterruptedException -> L27
        if (r0 == false) goto L5;
    L8:
        if (r0 == true) goto L10;
    L12:
        if (r0 == true) goto L14;
    L16:
        if (r1 <= 0) goto L26;
        Object r02 = this.cancelMark;     // Catch: InterruptedException -> L27
        if (r10.awaitNanos(r1) <= 0) goto L21;
        return;
    L21:
        if (this.cancelMark == r02) goto L24;
        return;
    L24:
        throw new InterruptedIOException("timeout");     // Catch: InterruptedException -> L27
    L26:
        throw new InterruptedIOException("timeout");     // Catch: InterruptedException -> L27
    L14:
        r1 = deadlineNanoTime() - System.nanoTime();
        goto L16
    L10:
        if (r1 == 0) goto L12;
        r1 = Math.min(r1, deadlineNanoTime() - System.nanoTime());     // Catch: InterruptedException -> L27
        goto L16
    L5:
        if (r1 != 0) goto L8;
        r10.await();     // Catch: InterruptedException -> L27
        return;
    L27:
        Thread.currentThread().interrupt();
        throw new InterruptedIOException("interrupted");
    }

    public void cancel() {
        this.cancelMark = new Object();
    }

    public Timeout clearDeadline() {
        this.hasDeadline = false;
        return this;
    }

    public Timeout clearTimeout() {
        this.timeoutNanos = 0;
        return this;
    }

    public final Timeout deadline(long r3, TimeUnit r5) {
        if (r3 > 0) goto L5;
        Buffer$$ExternalSyntheticBUOutline3.m("duration <= 0: ", r3);
        return null;
    L5:
        return deadlineNanoTime(System.nanoTime() + r5.toNanos(r3));
    }

    public long deadlineNanoTime() {
        if (this.hasDeadline == true) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("No deadline");
        return 0;
    L5:
        return this.deadlineNanoTime;
    }

    public Timeout deadlineNanoTime(long r2) {
        this.hasDeadline = true;
        this.deadlineNanoTime = r2;
        return this;
    }

    public boolean hasDeadline() {
        return this.hasDeadline;
    }

    public final <T> T intersectWith(Timeout r12, Function0<? extends T> r13) {
        long r0 = timeoutNanos();
        long r2 = Companion.minTimeout(r12.timeoutNanos(), timeoutNanos());
        TimeUnit r4 = TimeUnit.NANOSECONDS;
        timeout(r2, r4);
        if (hasDeadline() == false) goto L20;
        long r5 = deadlineNanoTime();
        if (r12.hasDeadline() == false) goto L36;
        deadlineNanoTime(Math.min(deadlineNanoTime(), r12.deadlineNanoTime()));
    L36:
        T r132 = r13.invoke();     // Catch: Throwable -> L13
        InlineMarker.finallyStart(1);
        timeout(r0, r4);
        if (r12.hasDeadline() == false) goto L11;
        deadlineNanoTime(r5);
    L11:
        InlineMarker.finallyEnd(1);
        return r132;
    L13:
        th = move-exception;
        InlineMarker.finallyStart(1);
        timeout(r0, r4);
        if (r12.hasDeadline() == false) goto L17;
        deadlineNanoTime(r5);
    L17:
        InlineMarker.finallyEnd(1);
        throw th;
    L20:
        if (r12.hasDeadline() == false) goto L34;
        deadlineNanoTime(r12.deadlineNanoTime());
    L34:
        T r133 = r13.invoke();     // Catch: Throwable -> L28
        InlineMarker.finallyStart(1);
        timeout(r0, r4);
        if (r12.hasDeadline() == false) goto L26;
        clearDeadline();
    L26:
        InlineMarker.finallyEnd(1);
        return r133;
    L28:
        th = move-exception;
        InlineMarker.finallyStart(1);
        timeout(r0, r4);
        if (r12.hasDeadline() == false) goto L32;
        clearDeadline();
    L32:
        InlineMarker.finallyEnd(1);
        throw th;
    }

    public void throwIfReached() {
        if (Thread.currentThread().isInterrupted() == true) goto L13;
        if (this.hasDeadline == true) goto L7;
        return;
    L7:
        if ((this.deadlineNanoTime - System.nanoTime()) <= 0) goto L10;
        return;
    L10:
        throw new InterruptedIOException("deadline reached");
    L13:
        throw new InterruptedIOException("interrupted");
    }

    public Timeout timeout(long r3, TimeUnit r5) {
        if (r3 < 0) goto L6;
        this.timeoutNanos = r5.toNanos(r3);
        return this;
    L6:
        Buffer$$ExternalSyntheticBUOutline3.m("timeout < 0: ", r3);
        return null;
    }

    public long timeoutNanos() {
        return this.timeoutNanos;
    }

    public void waitUntilNotified(Object r12) {
        boolean r0 = hasDeadline();     // Catch: InterruptedException -> L28
        long r1 = timeoutNanos();     // Catch: InterruptedException -> L28
        if (r0 == false) goto L5;
    L8:
        long r5 = System.nanoTime();     // Catch: InterruptedException -> L28
        if (r0 == true) goto L11;
    L13:
        if (r0 == true) goto L15;
    L17:
        if (r1 <= 0) goto L27;
        Object r02 = this.cancelMark;     // Catch: InterruptedException -> L28
        long r9 = r1 / 1000000;     // Catch: InterruptedException -> L28
        r12.wait(r9, (int) (r1 - (1000000 * r9)));     // Catch: InterruptedException -> L28
        if ((System.nanoTime() - r5) >= r1) goto L22;
        return;
    L22:
        if (this.cancelMark == r02) goto L25;
        return;
    L25:
        throw new InterruptedIOException("timeout");     // Catch: InterruptedException -> L28
    L27:
        throw new InterruptedIOException("timeout");     // Catch: InterruptedException -> L28
    L15:
        r1 = deadlineNanoTime() - r5;
        goto L17
    L11:
        if (r1 == 0) goto L13;
        r1 = Math.min(r1, deadlineNanoTime() - r5);     // Catch: InterruptedException -> L28
        goto L17
    L5:
        if (r1 != 0) goto L8;
        r12.wait();     // Catch: InterruptedException -> L28
        return;
    L28:
        Thread.currentThread().interrupt();
        throw new InterruptedIOException("interrupted");
    }
}
