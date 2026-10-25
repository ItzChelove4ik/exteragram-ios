package androidx.privacysandbox.ads.adservices.java.internal;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import kotlin.Metadata;
import kotlinx.coroutines.Deferred;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u001a*\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0000¨\u0006\u0006"}, d2 = {"asListenableFuture", "Lcom/google/common/util/concurrent/ListenableFuture;", "T", "Lkotlinx/coroutines/Deferred;", "tag", _UrlKt.FRAGMENT_ENCODE_SET, "ads-adservices-java_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public abstract class CoroutineAdapterKt {
    public static Object $r8$lambda$u17zFg4_yVVb03rPOrSamnVgzLA(Deferred r1, Object r2, CallbackToFutureAdapter.Completer r3) {
        r1.invokeOnCompletion(new CoroutineAdapterKt$asListenableFuture$1$1(r3, r1));
        return r2;
    }

    public static final <T> ListenableFuture<T> asListenableFuture(Deferred<? extends T> r1, Object r2) {
        return CallbackToFutureAdapter.getFuture(new CoroutineAdapterKt$$ExternalSyntheticLambda0(r1, r2));
    }

    public static /* synthetic */ ListenableFuture asListenableFuture$default(Deferred r0, Object r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = "Deferred.asListenableFuture";
    L6:
        return asListenableFuture(r0, r1);
    }
}
