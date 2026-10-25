package androidx.core.provider;

import android.os.Handler;
import android.os.Looper;

/* loaded from: classes3.dex */
abstract class CalleeHandler {
    public static Handler create() {
        if (Looper.myLooper() != null) goto L7;
        return new Handler(Looper.getMainLooper());
    L7:
        return new Handler();
    }
}
