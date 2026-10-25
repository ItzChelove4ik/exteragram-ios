package kotlinx.coroutines.sync;

import kotlin.Metadata;
import kotlinx.coroutines.internal.Symbol;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0017\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0003\u0010\u0004\"\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0007\"\u0014\u0010\b\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {_UrlKt.FRAGMENT_ENCODE_SET, "locked", "Lkotlinx/coroutines/sync/Mutex;", "Mutex", "(Z)Lkotlinx/coroutines/sync/Mutex;", "Lkotlinx/coroutines/internal/Symbol;", "NO_OWNER", "Lkotlinx/coroutines/internal/Symbol;", "ON_LOCK_ALREADY_LOCKED_BY_OWNER", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class MutexKt {
    private static final Symbol NO_OWNER = null;
    private static final Symbol ON_LOCK_ALREADY_LOCKED_BY_OWNER = null;

    static {
        NO_OWNER = new Symbol("NO_OWNER");
        ON_LOCK_ALREADY_LOCKED_BY_OWNER = new Symbol("ALREADY_LOCKED_BY_OWNER");
    }

    public static final Mutex Mutex(boolean r1) {
        return new MutexImpl(r1);
    }

    public static /* synthetic */ Mutex Mutex$default(boolean r0, int r1, Object r2) {
        if ((r1 & 1) == 0) goto L6;
        r0 = false;
    L6:
        return Mutex(r0);
    }

    public static final /* synthetic */ Symbol access$getNO_OWNER$p() {
        return NO_OWNER;
    }
}
