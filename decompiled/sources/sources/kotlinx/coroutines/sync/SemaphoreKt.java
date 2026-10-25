package kotlinx.coroutines.sync;

import kotlin.Metadata;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.SystemPropsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000&\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u001f\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a!\u0010\n\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0004\b\n\u0010\u000b\"\u0014\u0010\f\u001a\u00020\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010\r\"\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010\"\u0014\u0010\u0011\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0010\"\u0014\u0010\u0012\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0010\"\u0014\u0010\u0013\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0010\"\u0014\u0010\u0014\u001a\u00020\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\r¨\u0006\u0015"}, d2 = {_UrlKt.FRAGMENT_ENCODE_SET, "permits", "acquiredPermits", "Lkotlinx/coroutines/sync/Semaphore;", "Semaphore", "(II)Lkotlinx/coroutines/sync/Semaphore;", _UrlKt.FRAGMENT_ENCODE_SET, "id", "Lkotlinx/coroutines/sync/SemaphoreSegment;", "prev", "createSegment", "(JLkotlinx/coroutines/sync/SemaphoreSegment;)Lkotlinx/coroutines/sync/SemaphoreSegment;", "MAX_SPIN_CYCLES", "I", "Lkotlinx/coroutines/internal/Symbol;", "PERMIT", "Lkotlinx/coroutines/internal/Symbol;", "TAKEN", "BROKEN", "CANCELLED", "SEGMENT_SIZE", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class SemaphoreKt {
    private static final Symbol BROKEN = null;
    private static final Symbol CANCELLED = null;
    private static final int MAX_SPIN_CYCLES = 0;
    private static final Symbol PERMIT = null;
    private static final int SEGMENT_SIZE = 0;
    private static final Symbol TAKEN = null;

    static {
        MAX_SPIN_CYCLES = SystemPropsKt.systemProp$default("kotlinx.coroutines.semaphore.maxSpinCycles", 100, 0, 0, 12, null);
        PERMIT = new Symbol("PERMIT");
        TAKEN = new Symbol("TAKEN");
        BROKEN = new Symbol("BROKEN");
        CANCELLED = new Symbol("CANCELLED");
        SEGMENT_SIZE = SystemPropsKt.systemProp$default("kotlinx.coroutines.semaphore.segmentSize", 16, 0, 0, 12, null);
    }

    public static final Semaphore Semaphore(int r1, int r2) {
        return new SemaphoreImpl(r1, r2);
    }

    public static /* synthetic */ Semaphore Semaphore$default(int r0, int r1, int r2, Object r3) {
        if ((r2 & 2) == 0) goto L6;
        r1 = 0;
    L6:
        return Semaphore(r0, r1);
    }

    public static final /* synthetic */ SemaphoreSegment access$createSegment(long r0, SemaphoreSegment r2) {
        return createSegment(r0, r2);
    }

    public static final /* synthetic */ Symbol access$getBROKEN$p() {
        return BROKEN;
    }

    public static final /* synthetic */ Symbol access$getCANCELLED$p() {
        return CANCELLED;
    }

    public static final /* synthetic */ int access$getMAX_SPIN_CYCLES$p() {
        return MAX_SPIN_CYCLES;
    }

    public static final /* synthetic */ Symbol access$getPERMIT$p() {
        return PERMIT;
    }

    public static final /* synthetic */ int access$getSEGMENT_SIZE$p() {
        return SEGMENT_SIZE;
    }

    public static final /* synthetic */ Symbol access$getTAKEN$p() {
        return TAKEN;
    }

    private static final SemaphoreSegment createSegment(long r2, SemaphoreSegment r4) {
        return new SemaphoreSegment(r2, r4, 0);
    }
}
