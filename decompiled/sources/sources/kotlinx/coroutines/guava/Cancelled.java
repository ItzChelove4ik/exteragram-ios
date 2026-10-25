package kotlinx.coroutines.guava;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lkotlinx/coroutines/guava/Cancelled;", _UrlKt.FRAGMENT_ENCODE_SET, "exception", "Ljava/util/concurrent/CancellationException;", "<init>", "(Ljava/util/concurrent/CancellationException;)V", "kotlinx-coroutines-guava"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
final class Cancelled {

    @JvmField
    public final CancellationException exception;

    public Cancelled(CancellationException r1) {
        this.exception = r1;
    }
}
