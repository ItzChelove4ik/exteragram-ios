package androidx.work;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001aV\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062'\u0010\u0007\u001a#\b\u0001\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\b¢\u0006\u0002\b\fH\u0000¢\u0006\u0002\u0010\r\u001a.\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0001\"\u0004\b\u0000\u0010\u000f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0013H\u0000¨\u0006\u0014"}, d2 = {"launchFuture", "Lcom/google/common/util/concurrent/ListenableFuture;", "T", "context", "Lkotlin/coroutines/CoroutineContext;", "start", "Lkotlinx/coroutines/CoroutineStart;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ExtensionFunctionType;", "(Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;)Lcom/google/common/util/concurrent/ListenableFuture;", "executeAsync", "V", "Ljava/util/concurrent/Executor;", "debugTag", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/Function0;", "work-runtime_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public abstract class ListenableFutureKt {
    public static void $r8$lambda$2J7WTnmfyHeSyx3GGU57K1DCNjw(AtomicBoolean r0, CallbackToFutureAdapter.Completer r1, Function0 r2) {
        if (r0.get() == false) goto L10;
        return;
    L10:
        r1.set(r2.invoke());     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        r1.setException(th);
    }

    /* renamed from: $r8$lambda$GaXsP0J9ZiqT-NKOjjWkBCoRah8, reason: not valid java name */
    public static Object m968$r8$lambda$GaXsP0J9ZiqTNKOjjWkBCoRah8(CoroutineContext r8, CoroutineStart r9, Function2 r10, CallbackToFutureAdapter.Completer r11) {
        r11.addCancellationListener(new ListenableFutureKt$$ExternalSyntheticLambda1((Job) r8.get(Job.Key)), DirectExecutor.INSTANCE);
        return BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(r8), null, r9, new ListenableFutureKt$launchFuture$1$2(r10, r11, null), 1, null);
    }

    public static void $r8$lambda$TzZqNjsaPiH60pYTFt2BnJ0hmLw(AtomicBoolean r1) {
        r1.set(true);
    }

    public static Object $r8$lambda$X5HWWttRZ_Ir0xD9aqd6GXUt6fY(Executor r3, String r4, Function0 r5, CallbackToFutureAdapter.Completer r6) {
        AtomicBoolean r0 = new AtomicBoolean(false);
        r6.addCancellationListener(new ListenableFutureKt$$ExternalSyntheticLambda3(r0), DirectExecutor.INSTANCE);
        r3.execute(new ListenableFutureKt$$ExternalSyntheticLambda4(r0, r6, r5));
        return r4;
    }

    public static void $r8$lambda$vr_XgQ5qIrw8Yb6kuj8zI4IjUEw(Job r2) {
        if (r2 == null) goto L5;
        Job.DefaultImpls.cancel$default(r2, null, 1, null);
        return;
    }

    public static final <V> ListenableFuture<V> executeAsync(Executor r1, String r2, Function0<? extends V> r3) {
        return CallbackToFutureAdapter.getFuture(new ListenableFutureKt$$ExternalSyntheticLambda2(r1, r2, r3));
    }

    public static final <T> ListenableFuture<T> launchFuture(CoroutineContext r1, CoroutineStart r2, Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> r3) {
        return CallbackToFutureAdapter.getFuture(new ListenableFutureKt$$ExternalSyntheticLambda0(r1, r2, r3));
    }

    public static /* synthetic */ ListenableFuture launchFuture$default(CoroutineContext r0, CoroutineStart r1, Function2 r2, int r3, Object r4) {
        if ((r3 & 1) == 0) goto L6;
        r0 = EmptyCoroutineContext.INSTANCE;
    L6:
        if ((r3 & 2) == 0) goto L9;
        r1 = CoroutineStart.DEFAULT;
    L9:
        return launchFuture(r0, r1, r2);
    }
}
