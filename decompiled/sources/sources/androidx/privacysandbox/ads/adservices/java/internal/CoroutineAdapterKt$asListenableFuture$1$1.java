package androidx.privacysandbox.ads.adservices.java.internal;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.Deferred;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "T", "it", _UrlKt.FRAGMENT_ENCODE_SET, "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class CoroutineAdapterKt$asListenableFuture$1$1 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ CallbackToFutureAdapter.Completer<T> $completer;
    final /* synthetic */ Deferred<T> $this_asListenableFuture;

    /* JADX WARN: Multi-variable type inference failed */
    public CoroutineAdapterKt$asListenableFuture$1$1(CallbackToFutureAdapter.Completer<T> r1, Deferred<? extends T> r2) {
        this.$completer = r1;
        this.$this_asListenableFuture = r2;
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable r1) {
        invoke2(r1);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(Throwable r2) {
        if (r2 == null) goto L9;
        boolean r0 = r2 instanceof CancellationException;
        CallbackToFutureAdapter.Completer<T> r1 = this.$completer;
        if (r0 == false) goto L7;
        r1.setCancelled();
        return;
    L7:
        r1.setException(r2);
        return;
    L9:
        this.$completer.set(this.$this_asListenableFuture.getCompleted());
    }
}
