package kotlinx.coroutines;

import kotlin.Metadata;
import kotlinx.coroutines.internal.LimitedDispatcherKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\b\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0011\u0010\n\u001a\u0004\u0018\u00010\u0006H\u0005¢\u0006\u0004\b\n\u0010\u000bR\u0014\u0010\u000e\u001a\u00020\u00008&X¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u000f"}, d2 = {"Lkotlinx/coroutines/MainCoroutineDispatcher;", "Lkotlinx/coroutines/CoroutineDispatcher;", "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "parallelism", _UrlKt.FRAGMENT_ENCODE_SET, "name", "limitedParallelism", "(ILjava/lang/String;)Lkotlinx/coroutines/CoroutineDispatcher;", "toStringInternalImpl", "()Ljava/lang/String;", "getImmediate", "()Lkotlinx/coroutines/MainCoroutineDispatcher;", "immediate", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class MainCoroutineDispatcher extends CoroutineDispatcher {
    public MainCoroutineDispatcher() {
    }

    public abstract MainCoroutineDispatcher getImmediate();

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public CoroutineDispatcher limitedParallelism(int r1, String r2) {
        LimitedDispatcherKt.checkParallelism(r1);
        return LimitedDispatcherKt.namedOrThis(this, r2);
    }

    public final String toStringInternalImpl() {
        MainCoroutineDispatcher r0 = Dispatchers.getMain();
        if (this != r0) goto L14;
        return "Dispatchers.Main";
    L14:
        MainCoroutineDispatcher r02 = r0.getImmediate();     // Catch: UnsupportedOperationException -> L9
    L10:
        if (this != r02) goto L13;
        return "Dispatchers.Main.immediate";
    L13:
        return null;
    L9:
        r02 = null;
        goto L10
    }
}
