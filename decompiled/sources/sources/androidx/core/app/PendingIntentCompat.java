package androidx.core.app;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

/* loaded from: classes3.dex */
public abstract class PendingIntentCompat {
    public static int addMutabilityFlags(boolean r1, int r2) {
        if (r1 == true) goto L4;
        int r12 = 67108864;
    L7:
        return r12 | r2;
    L4:
        if (Build.VERSION.SDK_INT < 31) goto L8;
        r12 = 33554432;
        goto L7
    L8:
        return r2;
    }

    public static PendingIntent getActivity(Context r0, int r1, Intent r2, int r3, boolean r4) {
        return PendingIntent.getActivity(r0, r1, r2, addMutabilityFlags(r4, r3));
    }
}
