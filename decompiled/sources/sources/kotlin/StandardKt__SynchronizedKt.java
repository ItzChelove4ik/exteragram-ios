package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0012\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a;\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0005H\u0087\u0088\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0002\u0010\u0006\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0007"}, d2 = {"synchronized", "R", "lock", _UrlKt.FRAGMENT_ENCODE_SET, "block", "Lkotlin/Function0;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/StandardKt")
/* loaded from: classes5.dex */
class StandardKt__SynchronizedKt extends StandardKt__StandardKt {
    public StandardKt__SynchronizedKt() {
    }

    @IgnorableReturnValue
    @InlineOnly
    /* renamed from: synchronized, reason: not valid java name */
    private static final <R> R m2028synchronized(Object r1, Function0<? extends R> r2) {
        monitor-enter(r1);
        R r22 = r2.invoke();     // Catch: Throwable -> L9
        InlineMarker.finallyStart(1);
        monitor-exit(r1);
        InlineMarker.finallyEnd(1);
        return r22;
    L9:
        th = move-exception;
        InlineMarker.finallyStart(1);
        InlineMarker.finallyEnd(1);
        throw th;
    }
}
