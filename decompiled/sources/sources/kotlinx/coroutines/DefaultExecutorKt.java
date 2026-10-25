package kotlinx.coroutines;

import kotlin.Metadata;
import kotlinx.coroutines.internal.MainDispatchersKt;
import kotlinx.coroutines.internal.SystemPropsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\u001a\u000f\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0001\u0010\u0002\"\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0005\" \u0010\u0006\u001a\u00020\u00008\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\b\u0006\u0010\u0007\u0012\u0004\b\t\u0010\n\u001a\u0004\b\b\u0010\u0002¨\u0006\u000b"}, d2 = {"Lkotlinx/coroutines/Delay;", "initializeDefaultDelay", "()Lkotlinx/coroutines/Delay;", _UrlKt.FRAGMENT_ENCODE_SET, "defaultMainDelayOptIn", "Z", "DefaultDelay", "Lkotlinx/coroutines/Delay;", "getDefaultDelay", "getDefaultDelay$annotations", "()V", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class DefaultExecutorKt {
    private static final Delay DefaultDelay = null;
    private static final boolean defaultMainDelayOptIn = false;

    static {
        defaultMainDelayOptIn = SystemPropsKt.systemProp("kotlinx.coroutines.main.delay", false);
        DefaultDelay = initializeDefaultDelay();
    }

    public static final Delay getDefaultDelay() {
        return DefaultDelay;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final Delay initializeDefaultDelay() {
        if (defaultMainDelayOptIn == false) goto L5;
        MainCoroutineDispatcher r0 = Dispatchers.getMain();
        if (MainDispatchersKt.isMissing(r0) == true) goto L14;
        if ((r0 instanceof Delay) == false) goto L14;
        return (Delay) r0;
    L14:
        return DefaultExecutor.INSTANCE;
    L5:
        return DefaultExecutor.INSTANCE;
    }
}
