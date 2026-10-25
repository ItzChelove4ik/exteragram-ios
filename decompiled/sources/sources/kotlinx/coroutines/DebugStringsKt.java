package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.internal.DispatchedContinuation;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0010\u0010\u0005\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0006H\u0000\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0018\u0010\u0007\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0004¨\u0006\t"}, d2 = {"hexAddress", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "getHexAddress", "(Ljava/lang/Object;)Ljava/lang/String;", "toDebugString", "Lkotlin/coroutines/Continuation;", "classSimpleName", "getClassSimpleName", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDebugStrings.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DebugStrings.kt\nkotlinx/coroutines/DebugStringsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,18:1\n1#2:19\n*E\n"})
/* loaded from: classes5.dex */
public abstract class DebugStringsKt {
    public static final String getClassSimpleName(Object r0) {
        return r0.getClass().getSimpleName();
    }

    public static final String getHexAddress(Object r0) {
        return Integer.toHexString(System.identityHashCode(r0));
    }

    public static final String toDebugString(Continuation<?> r3) {
        if ((r3 instanceof DispatchedContinuation) == false) goto L17;
        return ((DispatchedContinuation) r3).toString();
    L17:
        Result.Companion r1 = Result.Companion;     // Catch: Throwable -> L9
        Object r12 = Result.m2018constructorimpl(r3 + '@' + getHexAddress(r3));     // Catch: Throwable -> L9
    L12:
        if (Result.m2021exceptionOrNullimpl(r12) == null) goto L16;
        r12 = r3.getClass().getName() + '@' + getHexAddress(r3);
    L16:
        return (String) r12;
    L9:
        th = move-exception;
        Result.Companion r2 = Result.Companion;
        r12 = Result.m2018constructorimpl(ResultKt.createFailure(th));
        goto L12
    }
}
