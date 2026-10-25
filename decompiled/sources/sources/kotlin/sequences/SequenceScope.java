package kotlin.sequences;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.RestrictsSuspension;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import okhttp3.internal.url._UrlKt;

@SinceKotlin(version = "1.3")
@RestrictsSuspension
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00020\u0002B\t\b@¢\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00028\u0000H¦À\u0004¢\u0006\u0002\u0010\bJ\u001d\u0010\t\u001a\u00020\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH¦À\u0004¢\u0006\u0002\u0010\fJ\u001d\u0010\t\u001a\u00020\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u000eH\u0086À\u0004¢\u0006\u0002\u0010\u000fJ\u001d\u0010\t\u001a\u00020\u00062\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011H\u0086À\u0004¢\u0006\u0002\u0010\u0012¨\u0006\u0013"}, d2 = {"Lkotlin/sequences/SequenceScope;", "T", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "yield", _UrlKt.FRAGMENT_ENCODE_SET, "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "yieldAll", "iterator", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/util/Iterator;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "elements", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/Iterable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sequence", "Lkotlin/sequences/Sequence;", "(Lkotlin/sequences/Sequence;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public abstract class SequenceScope<T> {
    public SequenceScope() {
    }

    public abstract Object yield(T r1, Continuation<? super Unit> r2);

    public final Object yieldAll(Iterable<? extends T> r2, Continuation<? super Unit> r3) {
        if ((r2 instanceof Collection) == true) goto L5;
    L8:
        Object r1 = yieldAll(r2.iterator(), r3);
        if (r1 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L12;
        return r1;
    L12:
        return Unit.INSTANCE;
    L5:
        if (((Collection) r2).isEmpty() == false) goto L8;
        return Unit.INSTANCE;
    }

    public abstract Object yieldAll(Iterator<? extends T> r1, Continuation<? super Unit> r2);

    public final Object yieldAll(Sequence<? extends T> r1, Continuation<? super Unit> r2) {
        Object r0 = yieldAll(r1.iterator(), r2);
        if (r0 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        return r0;
    L6:
        return Unit.INSTANCE;
    }
}
