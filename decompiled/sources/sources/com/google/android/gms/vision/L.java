package com.google.android.gms.vision;

import android.annotation.SuppressLint;
import android.util.Log;
import androidx.annotation.RecentlyNonNull;

/* loaded from: classes5.dex */
public abstract class L {
    public static int d(@RecentlyNonNull String r2, @RecentlyNonNull Object... r3) {
        if (Log.isLoggable("Vision", 3) == true) goto L5;
        return 0;
    L5:
        return Log.d("Vision", String.format(r2, r3));
    }

    public static int e(@RecentlyNonNull String r2, @RecentlyNonNull Object... r3) {
        if (Log.isLoggable("Vision", 6) == true) goto L5;
        return 0;
    L5:
        return Log.e("Vision", String.format(r2, r3));
    }

    @SuppressLint({"LogTagMismatch"})
    public static int e(@RecentlyNonNull Throwable r2, @RecentlyNonNull String r3, @RecentlyNonNull Object... r4) {
        if (Log.isLoggable("Vision", 6) == true) goto L5;
        return 0;
    L5:
        if (Log.isLoggable("Vision", 3) == true) goto L7;
        String r32 = String.format(r3, r4);
        String r22 = String.valueOf(r2);
        StringBuilder r0 = new StringBuilder((r32.length() + 2) + r22.length());
        r0.append(r32);
        r0.append(": ");
        r0.append(r22);
        return Log.e("Vision", r0.toString());
    L7:
        return Log.e("Vision", String.format(r3, r4), r2);
    }

    public static int i(@RecentlyNonNull String r2, @RecentlyNonNull Object... r3) {
        if (Log.isLoggable("Vision", 4) == true) goto L5;
        return 0;
    L5:
        return Log.i("Vision", String.format(r2, r3));
    }

    public static int v(@RecentlyNonNull String r2, @RecentlyNonNull Object... r3) {
        if (Log.isLoggable("Vision", 2) == true) goto L5;
        return 0;
    L5:
        return Log.v("Vision", String.format(r2, r3));
    }
}
