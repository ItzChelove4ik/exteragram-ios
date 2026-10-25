package kotlinx.coroutines.scheduling;

import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\b \u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lkotlinx/coroutines/scheduling/SchedulerTimeSource;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "nanoTime", _UrlKt.FRAGMENT_ENCODE_SET, "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class SchedulerTimeSource {
    public SchedulerTimeSource() {
    }

    public abstract long nanoTime();
}
