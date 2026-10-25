package okhttp3.internal;

import androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.reflect.KClass;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000 \n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a=\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00010\bH\u0000¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"computeIfAbsent", "T", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/util/concurrent/atomic/AtomicReference;", "Lokhttp3/internal/Tags;", "type", "Lkotlin/reflect/KClass;", "compute", "Lkotlin/Function0;", "(Ljava/util/concurrent/atomic/AtomicReference;Lkotlin/reflect/KClass;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "okhttp"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class TagsKt {
    public static final <T> T computeIfAbsent(AtomicReference<Tags> r3, KClass<T> r4, Function0<? extends T> r5) {
        T r0 = null;
    L3:
        Tags r1 = r3.get();
        T r2 = (T) r1.get(r4);
        if (r2 != null) goto L5;
        if (r0 != null) goto L9;
        r0 = r5.invoke();
    L9:
        if (AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticBackportWithForwarding0.m(r3, r1, r1.plus(r4, r0)) == false) goto L3;
        return r0;
    L5:
        return r2;
    }
}
