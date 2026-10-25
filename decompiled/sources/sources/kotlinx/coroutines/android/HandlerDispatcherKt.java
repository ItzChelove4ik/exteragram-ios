package kotlinx.coroutines.android;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001b\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0001¢\u0006\u0004\b\u0004\u0010\u0005\"\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0000X\u0081\u0004¢\u0006\f\n\u0004\b\u0007\u0010\b\u0012\u0004\b\t\u0010\n\"\u0018\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroid/os/Looper;", _UrlKt.FRAGMENT_ENCODE_SET, "async", "Landroid/os/Handler;", "asHandler", "(Landroid/os/Looper;Z)Landroid/os/Handler;", "Lkotlinx/coroutines/android/HandlerDispatcher;", "Main", "Lkotlinx/coroutines/android/HandlerDispatcher;", "getMain$annotations", "()V", "Landroid/view/Choreographer;", "choreographer", "Landroid/view/Choreographer;", "kotlinx-coroutines-android"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nHandlerDispatcher.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HandlerDispatcher.kt\nkotlinx/coroutines/android/HandlerDispatcherKt\n+ 2 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,212:1\n433#2,10:213\n433#2,10:223\n1#3:233\n*S KotlinDebug\n*F\n+ 1 HandlerDispatcher.kt\nkotlinx/coroutines/android/HandlerDispatcherKt\n*L\n184#1:213,10\n192#1:223,10\n*E\n"})
/* loaded from: classes.dex */
public abstract class HandlerDispatcherKt {

    @JvmField
    public static final HandlerDispatcher Main = null;
    private static volatile Choreographer choreographer;

    static {
        Object r0 = null;
        Result.Companion r1 = Result.Companion;     // Catch: Throwable -> L5
        Object r12 = Result.m2018constructorimpl(new HandlerContext(asHandler(Looper.getMainLooper(), true), null, 2, null));     // Catch: Throwable -> L5
    L8:
        if (Result.m2024isFailureimpl(r12) == true) goto L11;
        r0 = r12;
    L11:
        Main = (HandlerDispatcher) r0;
        return;
    L5:
        th = move-exception;
        Result.Companion r2 = Result.Companion;
        r12 = Result.m2018constructorimpl(ResultKt.createFailure(th));
        goto L8
    }

    public static final Handler asHandler(Looper r4, boolean r5) {
        if (r5 == false) goto L14;
        if (Build.VERSION.SDK_INT < 28) goto L15;
        return (Handler) Handler.class.getDeclaredMethod("createAsync", new Class[]{Looper.class}).invoke(null, new Object[]{r4});
    L15:
        return (Handler) Handler.class.getDeclaredConstructor(new Class[]{Looper.class, Handler.Callback.class, Boolean.TYPE}).newInstance(new Object[]{r4, null, Boolean.TRUE});
    L12:
        return new Handler(r4);
    L14:
        return new Handler(r4);
    }
}
