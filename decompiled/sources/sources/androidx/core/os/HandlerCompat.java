package androidx.core.os;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public abstract class HandlerCompat {

    public static class Api28Impl {
        public static Handler createAsync(Looper r0) {
            return Handler.createAsync(r0);
        }

        public static boolean postDelayed(Handler r0, Runnable r1, Object r2, long r3) {
            return r0.postDelayed(r1, r2, r3);
        }
    }

    public static Handler createAsync(Looper r5) {
        if (Build.VERSION.SDK_INT < 28) goto L29;
        return Api28Impl.createAsync(r5);
    L29:
        return (Handler) Handler.class.getDeclaredConstructor(new Class[]{Looper.class, Handler.Callback.class, Boolean.TYPE}).newInstance(new Object[]{r5, null, Boolean.TRUE});
    L15:
        e = e;
    L27:
        Log.w("HandlerCompat", "Unable to invoke Handler(Looper, Callback, boolean) constructor", e);
        return new Handler(r5);
    L13:
        e = e;
    L11:
        e = e;
    L9:
        e = move-exception;
        Throwable r52 = e.getCause();
        if ((r52 instanceof RuntimeException) == true) goto L26;
        if ((r52 instanceof Error) == true) goto L24;
        HttpUrl$$ExternalSyntheticBUOutline0.m(r52);
        return null;
    L24:
        throw ((Error) r52);
    L26:
        throw ((RuntimeException) r52);
    }

    public static boolean postDelayed(Handler r2, Runnable r3, Object r4, long r5) {
        if (Build.VERSION.SDK_INT >= 28) goto L5;
        Message r32 = Message.obtain(r2, r3);
        r32.obj = r4;
        return r2.sendMessageDelayed(r32, r5);
    L5:
        return Api28Impl.postDelayed(r2, r3, r4, r5);
    }
}
