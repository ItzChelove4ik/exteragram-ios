package androidx.core.app;

import android.os.Bundle;
import android.os.IBinder;

@Deprecated
/* loaded from: classes3.dex */
public abstract class BundleCompat {
    @Deprecated
    public static IBinder getBinder(Bundle r0, String r1) {
        return r0.getBinder(r1);
    }

    @Deprecated
    public static void putBinder(Bundle r0, String r1, IBinder r2) {
        r0.putBinder(r1, r2);
    }
}
