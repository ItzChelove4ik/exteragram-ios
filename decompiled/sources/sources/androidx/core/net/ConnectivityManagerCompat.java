package androidx.core.net;

import android.net.ConnectivityManager;

/* loaded from: classes3.dex */
public abstract class ConnectivityManagerCompat {
    @Deprecated
    public static boolean isActiveNetworkMetered(ConnectivityManager r0) {
        return r0.isActiveNetworkMetered();
    }
}
