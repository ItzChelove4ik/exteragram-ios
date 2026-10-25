package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a\u001b\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lkotlinx/coroutines/flow/internal/AbortFlowException;", _UrlKt.FRAGMENT_ENCODE_SET, "owner", _UrlKt.FRAGMENT_ENCODE_SET, "checkOwnership", "(Lkotlinx/coroutines/flow/internal/AbortFlowException;Ljava/lang/Object;)V", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public abstract class FlowExceptions_commonKt {
    public static final void checkOwnership(AbortFlowException r1, Object r2) {
        if (r1.owner != r2) goto L5;
        return;
    L5:
        throw r1;
    }
}
