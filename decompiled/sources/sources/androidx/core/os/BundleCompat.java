package androidx.core.os;

import android.os.Build;
import android.os.Bundle;

/* loaded from: classes3.dex */
public abstract class BundleCompat {

    public static class Api33Impl {
        public static <T> T getParcelable(Bundle r0, String r1, Class<T> r2) {
            return (T) r0.getParcelable(r1, r2);
        }
    }

    public static <T> T getParcelable(Bundle r2, String r3, Class<T> r4) {
        if (Build.VERSION.SDK_INT >= 34) goto L5;
        T r22 = (T) r2.getParcelable(r3);
        if (r4.isInstance(r22) == false) goto L9;
        return r22;
    L9:
        return null;
    L5:
        return (T) Api33Impl.getParcelable(r2, r3, r4);
    }
}
