package kotlinx.coroutines.tasks;

import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CancellableContinuationImpl;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a \u0010\u0002\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0086@¢\u0006\u0004\b\u0002\u0010\u0003\u001a*\u0010\u0006\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0082@¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"T", "Lcom/google/android/gms/tasks/Task;", "await", "(Lcom/google/android/gms/tasks/Task;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/google/android/gms/tasks/CancellationTokenSource;", "cancellationTokenSource", "awaitImpl", "(Lcom/google/android/gms/tasks/Task;Lcom/google/android/gms/tasks/CancellationTokenSource;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-play-services"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nTasks.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Tasks.kt\nkotlinx/coroutines/tasks/TasksKt\n+ 2 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n*L\n1#1,164:1\n433#2,10:165\n*S KotlinDebug\n*F\n+ 1 Tasks.kt\nkotlinx/coroutines/tasks/TasksKt\n*L\n136#1:165,10\n*E\n"})
/* loaded from: classes5.dex */
public abstract class TasksKt {
    public static final <T> Object await(Task<T> r1, Continuation<? super T> r2) {
        return awaitImpl(r1, null, r2);
    }

    private static final <T> Object awaitImpl(Task<T> r3, CancellationTokenSource r4, Continuation<? super T> r5) {
        if (r3.isComplete() == false) goto L13;
        Exception r42 = r3.getException();
        if (r42 == null) goto L7;
        throw r42;
    L7:
        if (r3.isCanceled() == true) goto L11;
        return r3.getResult();
    L11:
        throw new CancellationException("Task " + r3 + " was cancelled normally.");
    L13:
        CancellableContinuationImpl r0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(r5), 1);
        r0.initCancellability();
        r3.addOnCompleteListener(DirectExecutor.INSTANCE, new TasksKt$awaitImpl$2$1(r0));
        if (r4 == null) goto L16;
        r0.invokeOnCancellation(new TasksKt$awaitImpl$2$2(r4));
    L16:
        Object r32 = r0.getResult();
        if (r32 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L19;
        DebugProbesKt.probeCoroutineSuspended(r5);
    L19:
        return r32;
    }
}
