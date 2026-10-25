package kotlinx.atomicfu;

import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0017\u0018\u00002\u00020\u0001:\u0001\bB\t\b\u0000¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0001H\u0017¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lkotlinx/atomicfu/TraceBase;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "event", _UrlKt.FRAGMENT_ENCODE_SET, "append", "(Ljava/lang/Object;)V", "None", "atomicfu"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes5.dex */
public abstract class TraceBase {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lkotlinx/atomicfu/TraceBase$None;", "Lkotlinx/atomicfu/TraceBase;", "<init>", "()V", "atomicfu"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class None extends TraceBase {
        public static final None INSTANCE = null;

        static {
            INSTANCE = new None();
        }

        private None() {
        }
    }

    public TraceBase() {
    }

    public void append(Object r1) {
    }
}
