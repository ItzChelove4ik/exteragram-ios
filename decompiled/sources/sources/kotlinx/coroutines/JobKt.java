package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;

@Metadata(d1 = {"kotlinx/coroutines/JobKt__JobKt"}, d2 = {}, k = 4, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class JobKt {
    public static final CompletableJob Job(Job r0) {
        return JobKt__JobKt.Job(r0);
    }

    public static /* synthetic */ CompletableJob Job$default(Job r0, int r1, Object r2) {
        return JobKt__JobKt.Job$default(r0, r1, r2);
    }

    public static final void cancel(CoroutineContext r0, CancellationException r1) {
        JobKt__JobKt.cancel(r0, r1);
    }

    public static final void cancel(Job r0, String r1, Throwable r2) {
        JobKt__JobKt.cancel(r0, r1, r2);
    }

    public static /* synthetic */ void cancel$default(Job r0, String r1, Throwable r2, int r3, Object r4) {
        JobKt__JobKt.cancel$default(r0, r1, r2, r3, r4);
    }

    public static final Object cancelAndJoin(Job r0, Continuation<? super Unit> r1) {
        return JobKt__JobKt.cancelAndJoin(r0, r1);
    }

    public static final void cancelChildren(CoroutineContext r0, CancellationException r1) {
        JobKt__JobKt.cancelChildren(r0, r1);
    }

    public static /* synthetic */ void cancelChildren$default(CoroutineContext r0, CancellationException r1, int r2, Object r3) {
        JobKt__JobKt.cancelChildren$default(r0, r1, r2, r3);
    }

    public static final DisposableHandle disposeOnCompletion(Job r0, DisposableHandle r1) {
        return JobKt__JobKt.disposeOnCompletion(r0, r1);
    }

    public static final void ensureActive(CoroutineContext r0) {
        JobKt__JobKt.ensureActive(r0);
    }

    public static final void ensureActive(Job r0) {
        JobKt__JobKt.ensureActive(r0);
    }

    public static final Job getJob(CoroutineContext r0) {
        return JobKt__JobKt.getJob(r0);
    }

    public static final DisposableHandle invokeOnCompletion(Job r0, boolean r1, JobNode r2) {
        return JobKt__JobKt.invokeOnCompletion(r0, r1, r2);
    }

    public static /* synthetic */ DisposableHandle invokeOnCompletion$default(Job r0, boolean r1, JobNode r2, int r3, Object r4) {
        return JobKt__JobKt.invokeOnCompletion$default(r0, r1, r2, r3, r4);
    }
}
