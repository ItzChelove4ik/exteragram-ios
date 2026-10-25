package kotlinx.atomicfu;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlinx.atomicfu.TraceBase;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\u001a)\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006\u001a\u001f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u0002¢\u0006\u0002\u0010\u0007\u001a\u0018\u0010\u0000\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\t2\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u001a\u000e\u0010\u0000\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\t\u001a\u0018\u0010\u0000\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u000b2\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u001a\u000e\u0010\u0000\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u000b\u001a\u0018\u0010\u0000\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\r2\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u001a\u000e\u0010\u0000\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\r¨\u0006\u000e"}, d2 = {"atomic", "Lkotlinx/atomicfu/AtomicRef;", "T", "initial", "trace", "Lkotlinx/atomicfu/TraceBase;", "(Ljava/lang/Object;Lkotlinx/atomicfu/TraceBase;)Lkotlinx/atomicfu/AtomicRef;", "(Ljava/lang/Object;)Lkotlinx/atomicfu/AtomicRef;", "Lkotlinx/atomicfu/AtomicInt;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/atomicfu/AtomicLong;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/atomicfu/AtomicBoolean;", _UrlKt.FRAGMENT_ENCODE_SET, "atomicfu"}, k = 2, mv = {2, 1, 0}, xi = 48)
@JvmName(name = "AtomicFU")
/* loaded from: classes5.dex */
public abstract class AtomicFU {
    public static final AtomicBoolean atomic(boolean r1) {
        return atomic(r1, TraceBase.None.INSTANCE);
    }

    public static final AtomicBoolean atomic(boolean r1, TraceBase r2) {
        return new AtomicBoolean(r1, r2);
    }

    public static final AtomicInt atomic(int r1) {
        return atomic(r1, TraceBase.None.INSTANCE);
    }

    public static final AtomicInt atomic(int r1, TraceBase r2) {
        return new AtomicInt(r1, r2);
    }

    public static final AtomicLong atomic(long r1) {
        return atomic(r1, TraceBase.None.INSTANCE);
    }

    public static final AtomicLong atomic(long r1, TraceBase r3) {
        return new AtomicLong(r1, r3);
    }

    public static final <T> AtomicRef<T> atomic(T r1) {
        return atomic(r1, TraceBase.None.INSTANCE);
    }

    public static final <T> AtomicRef<T> atomic(T r1, TraceBase r2) {
        return new AtomicRef(r1, r2);
    }
}
