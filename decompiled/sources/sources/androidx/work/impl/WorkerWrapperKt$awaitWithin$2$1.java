package androidx.work.impl;

import androidx.work.ListenableWorker;
import com.google.common.util.concurrent.ListenableFuture;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class WorkerWrapperKt$awaitWithin$2$1 implements Function1<Throwable, Unit> {
    final /* synthetic */ ListenableFuture<T> $this_awaitWithin;
    final /* synthetic */ ListenableWorker $worker;

    public WorkerWrapperKt$awaitWithin$2$1(ListenableWorker r1, ListenableFuture<T> r2) {
        this.$worker = r1;
        this.$this_awaitWithin = r2;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable r1) {
        invoke2(r1);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(Throwable r2) {
        if ((r2 instanceof WorkerStoppedException) == false) goto L5;
        this.$worker.stop(((WorkerStoppedException) r2).getReason());
    L5:
        this.$this_awaitWithin.cancel(false);
    }
}
