package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineDispatcher;
import okhttp3.internal.url._UrlKt;
import okio.Utf8$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\u0016\u0010\u0003\u001a\u00020\u0004*\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0000¨\u0006\u0007"}, d2 = {"checkParallelism", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "namedOrThis", "Lkotlinx/coroutines/CoroutineDispatcher;", "name", _UrlKt.FRAGMENT_ENCODE_SET, "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nLimitedDispatcher.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LimitedDispatcher.kt\nkotlinx/coroutines/internal/LimitedDispatcherKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,154:1\n1#2:155\n*E\n"})
/* loaded from: classes.dex */
public abstract class LimitedDispatcherKt {
    public static final void checkParallelism(int r1) {
        if (r1 < 1) goto L5;
        return;
    L5:
        Utf8$$ExternalSyntheticBUOutline1.m("Expected positive parallelism level, but got ", r1);
    }

    public static final CoroutineDispatcher namedOrThis(CoroutineDispatcher r1, String r2) {
        if (r2 != null) goto L4;
        return r1;
    L4:
        return new NamedDispatcher(r1, r2);
    }
}
