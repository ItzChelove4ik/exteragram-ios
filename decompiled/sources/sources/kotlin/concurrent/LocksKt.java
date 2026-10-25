package kotlin.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata(d1 = {"\u0000\u001a\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a7\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004H\u0087\u0088\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\u0005\u001a7\u0010\u0006\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00072\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004H\u0087\u0088\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\b\u001a7\u0010\t\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00072\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004H\u0087\u0088\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\n"}, d2 = {"withLock", "T", "Ljava/util/concurrent/locks/Lock;", "action", "Lkotlin/Function0;", "(Ljava/util/concurrent/locks/Lock;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "read", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "(Ljava/util/concurrent/locks/ReentrantReadWriteLock;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "write", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
@JvmName(name = "LocksKt")
@SourceDebugExtension({"SMAP\nLocks.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Locks.kt\nkotlin/concurrent/LocksKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,77:1\n1#2:78\n*E\n"})
/* loaded from: classes5.dex */
public final class LocksKt {
    @IgnorableReturnValue
    @InlineOnly
    private static final <T> T read(ReentrantReadWriteLock r1, Function0<? extends T> r2) {
        ReentrantReadWriteLock.ReadLock r12 = r1.readLock();
        r12.lock();
        T r22 = r2.invoke();     // Catch: Throwable -> L6
        InlineMarker.finallyStart(1);
        r12.unlock();
        InlineMarker.finallyEnd(1);
        return r22;
    L6:
        th = move-exception;
        InlineMarker.finallyStart(1);
        r12.unlock();
        InlineMarker.finallyEnd(1);
        throw th;
    }

    @IgnorableReturnValue
    @InlineOnly
    private static final <T> T withLock(Lock r1, Function0<? extends T> r2) {
        r1.lock();
        T r22 = r2.invoke();     // Catch: Throwable -> L6
        InlineMarker.finallyStart(1);
        r1.unlock();
        InlineMarker.finallyEnd(1);
        return r22;
    L6:
        th = move-exception;
        InlineMarker.finallyStart(1);
        r1.unlock();
        InlineMarker.finallyEnd(1);
        throw th;
    }

    @IgnorableReturnValue
    @InlineOnly
    private static final <T> T write(ReentrantReadWriteLock r4, Function0<? extends T> r5) {
        ReentrantReadWriteLock.ReadLock r0 = r4.readLock();
        int r2 = 0;
        if (r4.getWriteHoldCount() != 0) goto L5;
        int r1 = r4.getReadHoldCount();
    L6:
        int r3 = 0;
    L7:
        if (r3 >= r1) goto L9;
        r0.unlock();
        r3 = r3 + 1;
        goto L7
    L9:
        ReentrantReadWriteLock.WriteLock r42 = r4.writeLock();
        r42.lock();
        T r52 = r5.invoke();     // Catch: Throwable -> L16
        InlineMarker.finallyStart(1);
    L12:
        if (r2 >= r1) goto L14;
        r0.lock();
        r2 = r2 + 1;
        goto L12
    L14:
        r42.unlock();
        InlineMarker.finallyEnd(1);
        return r52;
    L16:
        th = move-exception;
        InlineMarker.finallyStart(1);
    L18:
        if (r2 >= r1) goto L20;
        r0.lock();
        r2 = r2 + 1;
        goto L18
    L20:
        r42.unlock();
        InlineMarker.finallyEnd(1);
        throw th;
    L5:
        r1 = 0;
        goto L6
    }
}
