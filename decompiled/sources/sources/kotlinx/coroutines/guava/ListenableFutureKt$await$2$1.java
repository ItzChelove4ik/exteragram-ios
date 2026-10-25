package kotlinx.coroutines.guava;

import com.google.common.util.concurrent.ListenableFuture;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class ListenableFutureKt$await$2$1 implements Function1<Throwable, Unit> {
    final /* synthetic */ ListenableFuture<T> $this_await;

    public ListenableFutureKt$await$2$1(ListenableFuture<T> r1) {
        this.$this_await = r1;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable r1) {
        invoke2(r1);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(Throwable r1) {
        this.$this_await.cancel(false);
    }
}
