package androidx.media3.session;

import android.os.RemoteException;

/* loaded from: classes4.dex */
abstract class SessionUtil {
    public static void disconnectIMediaController(IMediaController r1) {
        if (r1 != null) goto L7;
        return;
    L7:
        r1.onDisconnected(0);     // Catch: RemoteException -> L6
        return;
    }
}
