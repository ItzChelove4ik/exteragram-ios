package kotlinx.coroutines.sync;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J\u001b\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001H&¢\u0006\u0004\b\u0004\u0010\u0005J\u001c\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001H¦@¢\u0006\u0004\b\u0007\u0010\bJ\u001b\u0010\t\u001a\u00020\u00062\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001H&¢\u0006\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\u00038&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\rÀ\u0006\u0003"}, d2 = {"Lkotlinx/coroutines/sync/Mutex;", _UrlKt.FRAGMENT_ENCODE_SET, "owner", _UrlKt.FRAGMENT_ENCODE_SET, "tryLock", "(Ljava/lang/Object;)Z", _UrlKt.FRAGMENT_ENCODE_SET, "lock", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "unlock", "(Ljava/lang/Object;)V", "isLocked", "()Z", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public interface Mutex {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ Object lock$default(Mutex r0, Object r1, Continuation r2, int r3, Object r4) {
            return Mutex.lock$default(r0, r1, r2, r3, r4);
        }

        public static /* synthetic */ boolean tryLock$default(Mutex r0, Object r1, int r2, Object r3) {
            return Mutex.tryLock$default(r0, r1, r2, r3);
        }

        public static /* synthetic */ void unlock$default(Mutex r0, Object r1, int r2, Object r3) {
            Mutex.unlock$default(r0, r1, r2, r3);
        }
    }

    static /* synthetic */ Object lock$default(Mutex r0, Object r1, Continuation r2, int r3, Object r4) {
        if (r4 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: lock");
        return null;
    L4:
        if ((r3 & 1) == 0) goto L7;
        r1 = null;
    L7:
        return r0.lock(r1, r2);
    }

    static /* synthetic */ boolean tryLock$default(Mutex r0, Object r1, int r2, Object r3) {
        if (r3 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: tryLock");
        return false;
    L4:
        if ((r2 & 1) == 0) goto L7;
        r1 = null;
    L7:
        return r0.tryLock(r1);
    }

    static /* synthetic */ void unlock$default(Mutex r0, Object r1, int r2, Object r3) {
        if (r3 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: unlock");
        return;
    L4:
        if ((r2 & 1) == 0) goto L6;
        r1 = null;
    L6:
        r0.unlock(r1);
    }

    boolean isLocked();

    Object lock(Object r1, Continuation<? super Unit> r2);

    boolean tryLock(Object r1);

    void unlock(Object r1);
}
