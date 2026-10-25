package com.google.android.play.integrity.internal;

import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import java.util.IllegalFormatException;
import java.util.Locale;

/* loaded from: classes5.dex */
public final class s {

    /* renamed from: a, reason: collision with root package name */
    private final String f129a;

    public s(String r5) {
        this.f129a = ("UID: [" + Process.myUid() + "]  PID: [" + Process.myPid() + "] ").concat(r5);
    }

    private static String f(String r4, String r5, Object... r6) {
        if (r6.length <= 0) goto L9;
        r5 = String.format(Locale.US, r5, r6);     // Catch: IllegalFormatException -> L6
    L6:
        e = move-exception;
        Log.e("PlayCore", "Unable to format ".concat(String.valueOf(r5)), e);
        r5 = r5 + " [" + TextUtils.join(", ", r6) + "]";
    L9:
        return r4 + " : " + r5;
    }

    public final int c(Throwable r3, String r4, Object... r5) {
        if (Log.isLoggable("PlayCore", 6) == true) goto L5;
        return 0;
    L5:
        return Log.e("PlayCore", f(this.f129a, r4, r5), r3);
    }

    public final int d(String r3, Object... r4) {
        if (Log.isLoggable("PlayCore", 4) == true) goto L5;
        return 0;
    L5:
        return Log.i("PlayCore", f(this.f129a, r3, r4));
    }

    public final int e(String r3, Object... r4) {
        if (Log.isLoggable("PlayCore", 5) == true) goto L5;
        return 0;
    L5:
        return Log.w("PlayCore", f(this.f129a, r3, r4));
    }
}
