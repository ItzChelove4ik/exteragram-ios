package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.internal.LongCompanionObject;
import kotlinx.coroutines.internal.Symbol;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0017\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0002\u0010\u0003\"\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006\"\u0014\u0010\u0007\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u0006*\u001e\b\u0002\u0010\n\u001a\u0004\b\u0000\u0010\b\"\b\u0012\u0004\u0012\u00028\u00000\t2\b\u0012\u0004\u0012\u00028\u00000\t¨\u0006\u000b"}, d2 = {_UrlKt.FRAGMENT_ENCODE_SET, "timeMillis", "delayToNanos", "(J)J", "Lkotlinx/coroutines/internal/Symbol;", "DISPOSED_TASK", "Lkotlinx/coroutines/internal/Symbol;", "CLOSED_EMPTY", "T", "Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;", "Queue", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class EventLoop_commonKt {
    private static final Symbol CLOSED_EMPTY = null;
    private static final Symbol DISPOSED_TASK = null;

    static {
        DISPOSED_TASK = new Symbol("REMOVED_TASK");
        CLOSED_EMPTY = new Symbol("CLOSED_EMPTY");
    }

    public static final /* synthetic */ Symbol access$getCLOSED_EMPTY$p() {
        return CLOSED_EMPTY;
    }

    public static final /* synthetic */ Symbol access$getDISPOSED_TASK$p() {
        return DISPOSED_TASK;
    }

    public static final long delayToNanos(long r3) {
        if (r3 > 0) goto L6;
        return 0;
    L6:
        if (r3 < 9223372036854L) goto L10;
        return LongCompanionObject.MAX_VALUE;
    L10:
        return r3 * 1000000;
    }
}
