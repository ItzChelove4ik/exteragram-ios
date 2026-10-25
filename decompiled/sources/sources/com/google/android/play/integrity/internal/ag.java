package com.google.android.play.integrity.internal;

import android.content.Context;

/* loaded from: classes5.dex */
public abstract class ag {
    public static Context a(Context r1) {
        Context r0 = r1.getApplicationContext();
        if (r0 == null) goto L5;
        return r0;
    L5:
        return r1;
    }
}
