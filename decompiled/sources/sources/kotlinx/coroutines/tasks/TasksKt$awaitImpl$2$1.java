package kotlinx.coroutines.tasks;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class TasksKt$awaitImpl$2$1<TResult> implements OnCompleteListener {
    final /* synthetic */ CancellableContinuation<T> $cont;

    /* JADX WARN: Multi-variable type inference failed */
    public TasksKt$awaitImpl$2$1(CancellableContinuation<? super T> r1) {
        this.$cont = r1;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task<T> r2) {
        Exception r0 = r2.getException();
        if (r0 != null) goto L10;
        boolean r02 = r2.isCanceled();
        CancellableContinuation<T> r1 = this.$cont;
        if (r02 == false) goto L8;
        CancellableContinuation.cancel$default(r1, null, 1, null);
        return;
    L8:
        Result.Companion r03 = Result.Companion;
        r1.resumeWith(Result.m2018constructorimpl(r2.getResult()));
        return;
    L10:
        Continuation r12 = this.$cont;
        Result.Companion r22 = Result.Companion;
        r12.resumeWith(Result.m2018constructorimpl(ResultKt.createFailure(r0)));
    }
}
