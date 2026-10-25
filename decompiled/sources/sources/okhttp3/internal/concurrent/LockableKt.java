package okhttp3.internal.concurrent;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal._UtilJvmKt$$ExternalSyntheticBUOutline1;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0080\b\u001a\r\u0010\u0003\u001a\u00020\u0001*\u00020\u0002H\u0080\b\u001a\r\u0010\u0004\u001a\u00020\u0001*\u00020\u0002H\u0080\b\u001a\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0080\b\u001a\r\u0010\b\u001a\u00020\u0001*\u00020\u0002H\u0080\b\u001a\r\u0010\t\u001a\u00020\u0001*\u00020\u0002H\u0080\b\u001a6\u0010\n\u001a\u0002H\u000b\"\u0004\b\u0000\u0010\u000b*\u00020\u00022\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\rH\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\u000e\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u000f"}, d2 = {"wait", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/internal/concurrent/Lockable;", "notify", "notifyAll", "awaitNanos", "nanos", _UrlKt.FRAGMENT_ENCODE_SET, "assertLockNotHeld", "assertLockHeld", "withLock", "T", "action", "Lkotlin/Function0;", "(Lokhttp3/internal/concurrent/Lockable;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "okhttp"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class LockableKt {
    public static final void assertLockHeld(Lockable r2) {
        if (_UtilJvmKt.assertionsEnabled == true) goto L5;
        return;
    L5:
        if (Thread.holdsLock(r2) == true) goto L10;
        _UtilJvmKt$$ExternalSyntheticBUOutline1.m(Thread.currentThread().getName(), " MUST hold lock on ", r2);
        return;
    }

    public static final void assertLockNotHeld(Lockable r2) {
        if (_UtilJvmKt.assertionsEnabled == true) goto L5;
        return;
    L5:
        if (Thread.holdsLock(r2) == false) goto L10;
        _UtilJvmKt$$ExternalSyntheticBUOutline1.m(Thread.currentThread().getName(), " MUST NOT hold lock on ", r2);
        return;
    }

    public static final void awaitNanos(Lockable r7, long r8) {
        long r2 = r8 / 1000000;
        long r0 = r8 - (1000000 * r2);
        if (r2 <= 0) goto L5;
    L8:
        r7.wait(r2, (int) r0);
        return;
    L5:
        if (r8 > 0) goto L8;
    }

    public static final void notify(Lockable r0) {
        r0.notify();
    }

    public static final void notifyAll(Lockable r0) {
        r0.notifyAll();
    }

    public static final void wait(Lockable r0) {
        r0.wait();
    }

    public static final <T> T withLock(Lockable r1, Function0<? extends T> r2) {
        monitor-enter(r1);
        T r22 = r2.invoke();     // Catch: Throwable -> L9
        InlineMarker.finallyStart(1);
        monitor-exit(r1);
        InlineMarker.finallyEnd(1);
        return r22;
    L9:
        th = move-exception;
        InlineMarker.finallyStart(1);
        InlineMarker.finallyEnd(1);
        throw th;
    }
}
