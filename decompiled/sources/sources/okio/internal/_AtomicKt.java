package okio.internal;

import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0000¨\u0006\u0004"}, d2 = {"setBitsOrZero", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/util/concurrent/atomic/AtomicInteger;", "bits", "okio"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public abstract class _AtomicKt {
    public static final int setBitsOrZero(AtomicInteger r2, int r3) {
    L2:
        int r0 = r2.get();
        if ((r0 & r3) != 0) goto L4;
        int r1 = r0 | r3;
        if (r2.compareAndSet(r0, r1) == false) goto L2;
        return r1;
    L4:
        return 0;
    }
}
