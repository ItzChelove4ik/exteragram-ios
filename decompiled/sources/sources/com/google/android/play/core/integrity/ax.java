package com.google.android.play.core.integrity;

import android.content.Context;
import com.google.android.play.integrity.internal.ag;

/* loaded from: classes5.dex */
abstract class ax {

    /* renamed from: a, reason: collision with root package name */
    private static aw f45a;

    public static synchronized aw a(Context r3) {
        monitor-enter(ax.class);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (f45a != null) goto L9;
        u r1 = new u(null);     // Catch: Throwable -> L7
        r1.a(ag.a(r3));     // Catch: Throwable -> L7
        f45a = r1.b();     // Catch: Throwable -> L7
    L9:
        aw r32 = f45a;     // Catch: Throwable -> L7
        monitor-exit(ax.class);
        return r32;
    }
}
