package kotlinx.coroutines.android;

import android.os.Build;
import java.lang.Thread;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineExceptionHandler;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\n\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lkotlinx/coroutines/android/AndroidExceptionPreHandler;", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "<init>", "()V", "_preHandler", _UrlKt.FRAGMENT_ENCODE_SET, "preHandler", "Ljava/lang/reflect/Method;", "handleException", _UrlKt.FRAGMENT_ENCODE_SET, "context", "Lkotlin/coroutines/CoroutineContext;", "exception", _UrlKt.FRAGMENT_ENCODE_SET, "kotlinx-coroutines-android"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class AndroidExceptionPreHandler extends AbstractCoroutineContextElement implements CoroutineExceptionHandler {
    private volatile Object _preHandler;

    public AndroidExceptionPreHandler() {
        super(CoroutineExceptionHandler.Key);
        this._preHandler = this;
    }

    private final Method preHandler() {
        Object r0 = this._preHandler;
        if (r0 != this) goto L5;
        Method r02 = null;
        Method r1 = Thread.class.getDeclaredMethod("getUncaughtExceptionPreHandler", null);     // Catch: Throwable -> L14
        if (Modifier.isPublic(r1.getModifiers()) == false) goto L12;
        if (Modifier.isStatic(r1.getModifiers()) == false) goto L12;
        r02 = r1;
    L12:
        this._preHandler = r02;
        return r02;
    L5:
        return (Method) r0;
    }

    @Override // kotlinx.coroutines.CoroutineExceptionHandler
    public void handleException(CoroutineContext r2, Throwable r3) {
        int r22 = Build.VERSION.SDK_INT;
        if (26 <= r22) goto L5;
        return;
    L5:
        if (r22 >= 28) goto L17;
        Method r1 = preHandler();
        Thread.UncaughtExceptionHandler r23 = null;
        if (r1 == null) goto L9;
        Object r12 = r1.invoke(null, null);
    L11:
        if ((r12 instanceof Thread.UncaughtExceptionHandler) == false) goto L13;
        r23 = (Thread.UncaughtExceptionHandler) r12;
    L13:
        if (r23 == null) goto L18;
        r23.uncaughtException(Thread.currentThread(), r3);
        return;
    L18:
        return;
    L9:
        r12 = null;
        goto L11
    }
}
