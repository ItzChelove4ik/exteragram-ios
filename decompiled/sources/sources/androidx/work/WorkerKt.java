package androidx.work;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a&\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0002¨\u0006\u0006"}, d2 = {"future", "Lcom/google/common/util/concurrent/ListenableFuture;", "T", "Ljava/util/concurrent/Executor;", "block", "Lkotlin/Function0;", "work-runtime_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public abstract class WorkerKt {
    public static void $r8$lambda$06LNzu7McnKR6G06fSbfQ2BCegc(AtomicBoolean r0, CallbackToFutureAdapter.Completer r1, Function0 r2) {
        if (r0.get() == false) goto L10;
        return;
    L10:
        r1.set(r2.invoke());     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        r1.setException(th);
    }

    public static void $r8$lambda$fQfnDvynAvnSQwUWbYAmb21KapE(AtomicBoolean r1) {
        r1.set(true);
    }

    /* renamed from: $r8$lambda$stbDiVrUcYsUSVFbJy_5-j0wuK4, reason: not valid java name */
    public static Unit m972$r8$lambda$stbDiVrUcYsUSVFbJy_5j0wuK4(Executor r3, Function0 r4, CallbackToFutureAdapter.Completer r5) {
        AtomicBoolean r0 = new AtomicBoolean(false);
        r5.addCancellationListener(new WorkerKt$$ExternalSyntheticLambda1(r0), DirectExecutor.INSTANCE);
        r3.execute(new WorkerKt$$ExternalSyntheticLambda2(r0, r5, r4));
        return Unit.INSTANCE;
    }

    public static final /* synthetic */ ListenableFuture access$future(Executor r0, Function0 r1) {
        return future(r0, r1);
    }

    private static final <T> ListenableFuture<T> future(Executor r1, Function0<? extends T> r2) {
        return CallbackToFutureAdapter.getFuture(new WorkerKt$$ExternalSyntheticLambda0(r1, r2));
    }
}
